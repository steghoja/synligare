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

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.AddOriginalQualifiedNameAnnotation;
import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.ConvertEClassPrimitivesToEDataTypes;
import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.CreateGenModel;
import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.CreateReleaseZipFile;
import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.DeleteEmptyIdentifiablePackage;
import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.DeletePackageGeneratedEcore;
import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.FlattenPackages;
import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.MakeAllFeaturesOrdered;
import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.MakeEEnumEDataTypeUnsettable;
import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.MoveGeneratedEditPlugin;
import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.NameChanger;
import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.PackageNamesToLowercase;
import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.SetAttributesDefaultValue;
import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.SetInstanceClassNameForDataTypes;
import org.eclipse.eatop.metamodelgen.postprocessings.AddGEastadlSuperClasses;
import org.eclipse.eatop.metamodelgen.postprocessings.AddOppositeReferenceForGEASTADL;
import org.eclipse.eatop.metamodelgen.postprocessings.AnalysisPersistencePattern;
import org.eclipse.eatop.metamodelgen.postprocessings.ConfigureInstanceRefAssociation;
import org.eclipse.eatop.metamodelgen.postprocessings.ConfigureInstanceRefClass;
import org.eclipse.eatop.metamodelgen.postprocessings.CopyGEastadlFiles;
import org.eclipse.eatop.metamodelgen.postprocessings.EASTADLAddPersistenceMapping;
import org.eclipse.eatop.metamodelgen.postprocessings.EASTADLAdjustGenModel;
import org.eclipse.eatop.metamodelgen.postprocessings.EASTADLAdjustGenModelCDO;
import org.eclipse.eatop.metamodelgen.postprocessings.EASTADLAdjustRootEPackageNsURI;
import org.eclipse.eatop.metamodelgen.postprocessings.EASTADLCreateExtendedMetaData;
import org.eclipse.eatop.metamodelgen.postprocessings.EASTADLCreateXSDSchema;
import org.eclipse.eatop.metamodelgen.postprocessings.EASTADLGenerateEMFProjects;
import org.eclipse.eatop.metamodelgen.postprocessings.EASTADLNormalizePackageNsURI;
import org.eclipse.eatop.metamodelgen.util.IEASTADLConstants;

public class EASTADLEcoreMMGen extends EcoreMMGen {

	/**
	 * Specifies if we should generate CDO.
	 */
	private boolean generateCDO = false;

	static private String EASTADL_metaModelType = IEASTADLConstants.METAMODEL_TYPE_EASTADL;

	public EASTADLEcoreMMGen(IFile eapFile, boolean generateEcoreOnly, boolean generateMMCode, boolean generateAll, boolean generateCDO) {
		super(eapFile, EASTADL_metaModelType, generateEcoreOnly, generateMMCode, generateAll);
		this.generateCDO = generateCDO;
	}

	@Override
	protected void addInitialEcorePostProcessings(String version) {
		adapter.addPostProcessing(new DeletePackageGeneratedEcore());
		adapter.addPostProcessing(new AddOriginalQualifiedNameAnnotation());
		adapter.addPostProcessing(new AddOppositeReferenceForGEASTADL());
		adapter.addPostProcessing(new NameChanger());

		adapter.addPostProcessing(new ConvertEClassPrimitivesToEDataTypes());
		adapter.addPostProcessing(new SetInstanceClassNameForDataTypes());
		adapter.addPostProcessing(new EASTADLNormalizePackageNsURI(version));
		adapter.addPostProcessing(new MakeAllFeaturesOrdered());
		adapter.addPostProcessing(new MakeEEnumEDataTypeUnsettable());
		adapter.addPostProcessing(new SetAttributesDefaultValue());
		adapter.addPostProcessing(new ConfigureInstanceRefAssociation());
		adapter.addPostProcessing(new ConfigureInstanceRefClass());
		adapter.addPostProcessing(new DeleteEmptyIdentifiablePackage());
		adapter.addPostProcessing(new PackageNamesToLowercase());
	}

	@Override
	protected void addAdjustEcorePostProcessings(String version, String geastadlRootDir, String eastadlRootDir, String autosarRootDir) {
		adapter.addPostProcessing(new FlattenPackages());
		adapter.addPostProcessing(new EASTADLCreateExtendedMetaData(version));

		// EAST-ADL specific
		adapter.addPostProcessing(new CopyGEastadlFiles(ecoreFile, geastadlRootDir));
		adapter.addPostProcessing(new AddGEastadlSuperClasses(ecoreFile));
		adapter.addPostProcessing(new EASTADLAdjustRootEPackageNsURI(version));
	}

	@Override
	protected void addPersistenceMappingPostProcessings(IProgressMonitor monitor) {
		adapter.addPostProcessing(new EASTADLAddPersistenceMapping(ecoreFile, new SubProgressMonitor(monitor, 1)));
		// analysis and calculate patterns
		adapter.addPostProcessing(new AnalysisPersistencePattern());
	}

	@Override
	protected void addMMCodePostProcessings(String version, IProgressMonitor monitor) throws IOException {
		adapter.addPostProcessing(new CreateGenModel(ecoreFile, getProjectName()));
		if (generateCDO) {
			adapter.addPostProcessing(new EASTADLAdjustGenModelCDO(ecoreFile, getProjectName(), version, metaModelType));
		} else {
			adapter.addPostProcessing(new EASTADLAdjustGenModel(ecoreFile, getProjectName(), version, metaModelType));
		}
		adapter.addPostProcessing(new EASTADLGenerateEMFProjects(new SubProgressMonitor(monitor, 1), ecoreFile, true, true, version));

		// EAST-ADL specific
		adapter.addPostProcessing(new CreateReleaseZipFile(new SubProgressMonitor(monitor, 1), getProjectName()));
		adapter.addPostProcessing(new MoveGeneratedEditPlugin(new SubProgressMonitor(monitor, 1), getProjectName()));
	}

	@Override
	protected void addXSDSchemaPostProcessings(IProgressMonitor monitor) {
		adapter.addPostProcessing(new EASTADLCreateXSDSchema(new SubProgressMonitor(monitor, 1), ecoreFile, eapFile));
	}

	@Override
	protected String getRootPackageName() {
		return IEASTADLConstants.EASTADL21_ROOT_PACKAGE;
	}

	@Override
	protected String getVersion() {
		return IEASTADLConstants.EASTADL21_VERSION;
	}
}
