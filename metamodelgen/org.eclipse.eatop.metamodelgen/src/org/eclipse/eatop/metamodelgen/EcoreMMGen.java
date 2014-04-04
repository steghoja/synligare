/**
 * <copyright>
 *  
 * Copyright (c) 2014 itemis and others.
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 
 * which accompanies this distribution, and is
 * available at http://www.eclipse.org/org/documents/epl-v10.php
 *  
 * Contributors: 
 *     itemis - Initial API and implementation
 *  
 * </copyright>
 * 
 */

package org.eclipse.eatop.metamodelgen;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.eatop.eaadapter.EAAdapter;
import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.AddOriginalQualifiedNameAnnotation;
import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.AddPersistenceMapping;
import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.AdjustGenModel;
import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.AdjustRootEPackageNsURI;
import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.ConvertEClassPrimitivesToEDataTypes;
import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.CreateExtendedMetaData;
import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.CreateGenModel;
import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.CreateXSDSchema;
import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.DeleteEmptyIdentifiablePackage;
import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.DeletePackageGeneratedEcore;
import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.FlattenPackages;
import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.GenerateEMFProjects;
import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.MakeAllFeaturesOrdered;
import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.MakeEEnumEDataTypeUnsettable;
import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.NameChanger;
import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.NormalizePackageNsURI;
import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.PackageNamesToLowercase;
import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.SetAttributesDefaultValue;
import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.SetInstanceClassNameForDataTypes;
import org.eclipse.eatop.eaadapter.util.IConstants;
import org.eclipse.eatop.metamodelgen.postprocessings.ConfigureInstanceRefAssociation;
import org.eclipse.eatop.metamodelgen.postprocessings.ConfigureInstanceRefClass;
import org.eclipse.eatop.metamodelgen.util.IEASTADLConstants;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import eaadapter.EARepository;

/**
 * Ecore and metamodel implementation code, xsd schema generator
 */
public class EcoreMMGen {

	protected String metaModelType;
	protected IFile eapFile;
	protected File ecoreFile;
	private EPackage ecoreRootPkg;
	protected EAAdapter adapter;
	private EARepository eaRepository;

	/**
	 * Specifies if we should generate MM code only.
	 */
	private boolean generateEcoreOnly;

	/**
	 * Specifies if we should generate MM code or XSD schema. Generate MM code only if true, otherwise generate XSD
	 * schema only.
	 */
	private boolean generateMMCode;

	/**
	 * Specifies if we should generate all, including Ecore, MM code and XSD schema. Generate all if true, otherwise
	 * generate persistence mapping Ecore only.
	 */
	private boolean generateAll;

	public EcoreMMGen(IFile eapFile, String metaModelType, boolean generateEcoreOnly, boolean generateMMCode, boolean generateAll) {
		this.eapFile = eapFile;
		this.metaModelType = metaModelType;
		this.generateEcoreOnly = generateEcoreOnly;
		this.generateMMCode = generateMMCode;
		this.generateAll = generateAll;

		ecoreFile = new File(eapFile.getLocation().toFile().getAbsolutePath().replace(IConstants.EAP_FILE_POSTFIX, IConstants.ECORE_FILE_POSTFIX));
	}

	/**
	 * generate Ecore
	 * 
	 * @param monitor
	 * @return
	 * @throws Exception
	 */
	public EObject generate(IProgressMonitor monitor) throws Exception {

		SubMonitor progress = SubMonitor.convert(monitor, 100);
		if (progress.isCanceled()) {
			throw new OperationCanceledException();
		}

		// create an adapter to load EAP file, and adapts to EARepository
		adapter = new EAAdapter(metaModelType, eapFile);
		adapter.loadEapFile(progress.newChild(40));
		eaRepository = adapter.getEARepository();

		String rootPckName = getRootPackageName();
		String version = getVersion();
		String geastadlRootDir = getGeastadlRootDir();
		String eastadlRootDir = getEastadlRootDir();
		String autosarRootDir = getAutosarRootDir();

		// create EA2Ecore, and add postprocessings based on the parameters, execute to generate the Ecore
		adapter.createEA2Ecore(rootPckName);
		addPostProcessings(rootPckName, version, geastadlRootDir, eastadlRootDir, autosarRootDir, progress.newChild(30));
		ecoreRootPkg = adapter.executeEA2Ecore(progress.newChild(30));
		adapter.saveEcore(ecoreRootPkg);

		return ecoreRootPkg;
	}

	/**
	 * Add post processings based on the command parameters
	 * 
	 * @throws IOException
	 */
	public void addPostProcessings(String rootPckName, String version, String geastadlRootDir, String eastadlRootDir, String autosarRootDir,
			IProgressMonitor monitor) throws IOException {
		addInitialEcorePostProcessings(version);
		addAdjustEcorePostProcessings(version, geastadlRootDir, eastadlRootDir, autosarRootDir);
		addPersistenceMappingPostProcessings(monitor);

		if (!generateEcoreOnly) {
			// if generateEcoreOnly = true => Ecore
			// if generateMMCode = true => Ecore + MM code
			// if generateMMCode = false => Ecore + XSD
			// if generateAll = true => Ecore + MM code + XSD
			if (generateMMCode || generateAll) { // generate emf code only
				addMMCodePostProcessings(version, monitor);
			}

			if (!generateMMCode || generateAll) { // generate xsd schema only
				addXSDSchemaPostProcessings(monitor);
			}
		}

	}

	/**
	 * Add initial common ecore post processings
	 */
	protected void addInitialEcorePostProcessings(String version) {
		adapter.addPostProcessing(new DeletePackageGeneratedEcore());
		adapter.addPostProcessing(new AddOriginalQualifiedNameAnnotation());
		adapter.addPostProcessing(new NameChanger());

		adapter.addPostProcessing(new ConvertEClassPrimitivesToEDataTypes());
		adapter.addPostProcessing(new SetInstanceClassNameForDataTypes());
		adapter.addPostProcessing(new NormalizePackageNsURI(version));
		adapter.addPostProcessing(new MakeAllFeaturesOrdered());
		adapter.addPostProcessing(new MakeEEnumEDataTypeUnsettable());
		adapter.addPostProcessing(new SetAttributesDefaultValue());
		adapter.addPostProcessing(new ConfigureInstanceRefAssociation());
		adapter.addPostProcessing(new ConfigureInstanceRefClass());
		adapter.addPostProcessing(new DeleteEmptyIdentifiablePackage());
		adapter.addPostProcessing(new PackageNamesToLowercase());
	}

	/**
	 * Add adjust ecore post processings. Can be overriden by custom to provide its proper adjuste post processings.
	 */
	protected void addAdjustEcorePostProcessings(String version, String geastadlRootDir, String eastadlRootDir, String autosarRootDir) {
		adapter.addPostProcessing(new FlattenPackages());
		adapter.addPostProcessing(new CreateExtendedMetaData(version));
		adapter.addPostProcessing(new AdjustRootEPackageNsURI(version));
	}

	/**
	 * Add persistence mapping post processings
	 */
	protected void addPersistenceMappingPostProcessings(IProgressMonitor monitor) {
		adapter.addPostProcessing(new AddPersistenceMapping(ecoreFile, new SubProgressMonitor(monitor, 1)));
	}

	/**
	 * Add metamodel code post processings. Can be overriden by custom to provide its proper adjuste post processings.
	 * 
	 * @throws IOException
	 */
	protected void addMMCodePostProcessings(String version, IProgressMonitor monitor) throws IOException {
		adapter.addPostProcessing(new CreateGenModel(ecoreFile, getProjectName()));
		adapter.addPostProcessing(new AdjustGenModel(ecoreFile, getProjectName(), version));
		adapter.addPostProcessing(new GenerateEMFProjects(new SubProgressMonitor(monitor, 1), ecoreFile, true, true, version));
	}

	/**
	 * Add XSD schema post processings
	 */
	protected void addXSDSchemaPostProcessings(IProgressMonitor monitor) {
		adapter.addPostProcessing(new CreateXSDSchema(new SubProgressMonitor(monitor, 1), ecoreFile, eapFile));
	}

	/**
	 * Get the name of the project of the eapFile
	 * 
	 * @return
	 */
	protected String getProjectName() {
		return eapFile.getProject().getName();
	}

	protected String getRootPackageName() {
		return eaRepository.getModels().get(0).getName();
	}

	protected String getVersion() {
		return IEASTADLConstants.EASTADL21_VERSION;
	}

	/**
	 * Returns the absolute directory path of Geastadl plugin, which must be imported into the workspace before the
	 * generation.
	 */
	protected String getGeastadlRootDir() {
		// URI geastadlUri = URI.createPlatformResourceURI(geastadlPluginId, true);
		// IPath geastadlPath = EcorePlatformUtil.createAbsoluteFileLocation(geastadlUri);
		// return geastadlPath.toFile().getAbsolutePath();
		// return "C:\\Work\\Workspace\\runtime-EclipseApplication\\org.eclipse.eatop.geastadl";
		return IEASTADLConstants.GEASTADL_PLUGIN_ID + IConstants.MODEL_DIRECTORY;
	}

	/**
	 * Returns the absolute directory path of eastadl.stub plugin, which must be imported into the workspace before the
	 * SAFE generation.
	 */
	protected String getEastadlRootDir() {
		return ""; //$NON-NLS-1$
	}

	/**
	 * Returns the absolute directory path of autosar.stub plugin, which must be imported into the workspace before the
	 * SAFE generatio
	 */
	protected String getAutosarRootDir() {
		return ""; //$NON-NLS-1$
	}
}
