/**
 * <copyright>
 * 
 * Copyright (c) 2014 Continental AG and others.
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 
 * which accompanies this distribution, and is
 * available at http://www.eclipse.org/org/documents/epl-v10.php
 * 
 * Contributors: 
 *     Continental AG - Initial API and implementation
 * 
 * </copyright>
 */
package org.eclipse.eatop.metamodelgen.postprocessings.generator;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.eatop.metamodelgen.util.IEASTADLConstants;
import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenPackageGeneratorAdapter;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

@SuppressWarnings("nls")
public class ExtendedGenPackageGeneratorAdapter extends GenPackageGeneratorAdapter {

	// use last super class ID as base for extra IDs
	protected static final int TRAVERSAL_HELPER_ID = GenPackageGeneratorAdapter.PACKAGE_EXAMPLE_ID + 1;
	protected static final int RELEASE_DESCRIPTOR_ID = GenPackageGeneratorAdapter.PACKAGE_EXAMPLE_ID + 2;
	protected static final int ITEM_LABEL_PROVIDER_ADAPTER_FACTORY_ID = GenPackageGeneratorAdapter.PACKAGE_EXAMPLE_ID + 3;
	protected static final int RELEASE_DESCRIPTOR_ITEM_LABEL_PROVIDER_ID = GenPackageGeneratorAdapter.PACKAGE_EXAMPLE_ID + 4;
	protected static final int FACTORY_CLASS_ID = GenPackageGeneratorAdapter.PACKAGE_EXAMPLE_ID + 5;
	protected static final int PACKAGE_ID = GenPackageGeneratorAdapter.PACKAGE_EXAMPLE_ID + 6;
	protected static final int SWITCH_ID = GenPackageGeneratorAdapter.PACKAGE_EXAMPLE_ID + 7;

	protected static final JETEmitterDescriptor[] JET_EMITTER_DESCRIPTORS = {
			new JETEmitterDescriptor(IEASTADLConstants.TRAVERSAL_HELPER_JAVAJET, IEASTADLConstants.TRAVERSAL_HELPER_CLASS),
			new JETEmitterDescriptor(IEASTADLConstants.RELEASE_DESCRIPTOR_JAVAJET, IEASTADLConstants.RELEASE_DESCRIPTOR_CLASS),
			new JETEmitterDescriptor(IEASTADLConstants.ITEM_LABEL_PROVIDER_ADAPTER_FACTORY_JAVAJET,
					IEASTADLConstants.ITEM_LABEL_PROVIDER_ADAPTER_FACTORY_CLASS),
			new JETEmitterDescriptor(IEASTADLConstants.RELEASE_DESCRIPTOR_ITEM_LABEL_PROVIDER_JAVAJET,
					IEASTADLConstants.RELEASE_DESCRIPTOR_ITEM_LABEL_PROVIDER_CLASS),
			new JETEmitterDescriptor(IEASTADLConstants.FACTORY_CLASS_JAVAJET, IEASTADLConstants.FACTORY_CLASS), };

	protected JETEmitterDescriptor[] mergedDescriptors;

	public ExtendedGenPackageGeneratorAdapter(GeneratorAdapterFactory generatorAdapterFactory) {
		super(generatorAdapterFactory);
	}

	@Override
	protected JETEmitterDescriptor[] getJETEmitterDescriptors() {
		if (mergedDescriptors == null) {
			JETEmitterDescriptor[] superDescriptors = super.getJETEmitterDescriptors();
			JETEmitterDescriptor[] thisDescriptors = JET_EMITTER_DESCRIPTORS;

			mergedDescriptors = new JETEmitterDescriptor[superDescriptors.length + thisDescriptors.length];

			System.arraycopy(superDescriptors, 0, mergedDescriptors, 0, superDescriptors.length);
			System.arraycopy(thisDescriptors, 0, mergedDescriptors, superDescriptors.length, thisDescriptors.length);
		}

		return mergedDescriptors;
	}

	@Override
	protected Diagnostic generateModel(Object object, Monitor monitor) {
		monitor.beginTask("", 13);

		GenPackage genPackage = (GenPackage) object;
		message = CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingPackage_message", new Object[] { genPackage.getBasicPackageName() });
		monitor.subTask(message);

		GenModel genModel = genPackage.getGenModel();
		ensureProjectExists(genModel.getModelDirectory(), genPackage, MODEL_PROJECT_TYPE, genModel.isUpdateClasspath(), createMonitor(monitor, 1));

		generateSchema(genPackage, monitor);
		generatePackageSerialization(genPackage, monitor);
		generatePackageInterface(genPackage, monitor);
		generatePackageClass(genPackage, monitor);
		generateFactoryInterface(genPackage, monitor);
		generateFactoryClass(genPackage, monitor);
		generateXMLProcessorClass(genPackage, monitor);
		generateValidatorClass(genPackage, monitor);
		generateSwitchClass(genPackage, monitor);
		generateAdapterFactoryClass(genPackage, monitor);

		generateResourceFactoryClass(genPackage, monitor);
		generateResourceClass(genPackage, monitor);

		/*
		 * Removed TraversalHelper because it is not working correctly and it is not necessarry yet. For example if you
		 * create the following structure: EAXML [EAXML] > EA Package [EAPackage] > EARoot [EAPackage] > Vehicle Level
		 * [VehicleLevel] > Feature Model [FeatureModel] > flink [FeatureLink] > f1 [VehicleFeature] the current
		 * Eastadl-TraversalHelper will stop traversing at Vehicle Level, because it is not an EAPackage. This results
		 * in not recognizing the "child" elements flink and f1. The DefaultEcoreTraversalHelper will do the trick at
		 * the moement. If you intend to implement a "new" working and efficient Eastadl-TraversalHelper then uncomment
		 * the following method call and the line <%@ include file="ItemProvider/insert.javajetinc" fail="silent" %> in
		 * ItemProvider.javajet generateExtraClass(genPackage, monitor, genModel.getModelDirectory(),
		 * genPackage.getUtilitiesPackageName(), "TraversalHelper", TRAVERSAL_HELPER_ID);
		 */

		generateExtraClass(genPackage, monitor, genModel.getModelDirectory(), genPackage.getUtilitiesPackageName(), "ReleaseDescriptor",
				RELEASE_DESCRIPTOR_ID);
		generateExtraClass(genPackage, monitor, genModel.getModelDirectory(), genPackage.getUtilitiesPackageName(), "Factory", FACTORY_CLASS_ID);

		return Diagnostic.OK_INSTANCE;
	}

	@Override
	protected Diagnostic generateEdit(Object object, Monitor monitor) {
		GenPackage genPackage = (GenPackage) object;
		monitor.beginTask("", 2 + countCreateChildIcons(genPackage));

		message = CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingItemProvidersForPackage_message",
				new Object[] { genPackage.getBasicPackageName() });
		monitor.subTask(message);

		GenModel genModel = genPackage.getGenModel();
		ensureProjectExists(genModel.getEditDirectory(), genPackage, EDIT_PROJECT_TYPE, genModel.isUpdateClasspath(), createMonitor(monitor, 1));

		generateItemProviderAdapterFactory(genPackage, monitor);
		generateCreateChildIcons(genPackage, monitor);

		generateExtraFullyNamedClass(genPackage, monitor, genModel.getEditDirectory(), genModel.getEditPluginPackageName(),
				"ItemLabelProviderAdapterFactory", ITEM_LABEL_PROVIDER_ADAPTER_FACTORY_ID);
		generateExtraClass(genPackage, monitor, genModel.getEditDirectory(), genModel.getEditPluginPackageName(),
				"ReleaseDescriptorItemLabelProvider", RELEASE_DESCRIPTOR_ITEM_LABEL_PROVIDER_ID);

		return Diagnostic.OK_INSTANCE;
	}

	@Override
	protected void generatePackageSerialization(final GenPackage genPackage, Monitor monitor) {
		if (genPackage.hasClassifiers() && genPackage.isLoadingInitialization()) {
			monitor = createMonitor(monitor, 1);

			try {
				monitor.beginTask("", 2);

				final GenModel genModel = genPackage.getGenModel();
				String targetPathName = genModel.getModelDirectory() + "/" + genPackage.getClassPackageName().replace('.', '/') + "/"
						+ genPackage.getSerializedPackageFilename();
				message = CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingPackageSerialization_message", new Object[] { targetPathName });
				monitor.subTask(message);

				URI targetFile = toURI(targetPathName);
				ensureContainerExists(targetFile.trimSegments(1), createMonitor(monitor, 1));

				EPackage originalPackage = genPackage.getEcorePackage();
				final Resource originalResource = originalPackage.eResource();

				ResourceSet set = new ResourceSetImpl();
				set.getResourceFactoryRegistry().getExtensionToFactoryMap()
						.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new EcoreResourceFactoryImpl());
				URI targetURI = toPlatformResourceURI(targetFile);
				Resource outputResource = set.createResource(targetURI);

				// Copy the package, excluding unwanted annotations.
				EcoreUtil.Copier copier = new EcoreUtil.Copier() {
					private static final long serialVersionUID = 1L;

					@Override
					protected void copyContainment(EReference reference, EObject object, EObject copyEObject) {
						if (reference == EcorePackage.Literals.EMODEL_ELEMENT__EANNOTATIONS) {
							List<EAnnotation> result = ((EModelElement) copyEObject).getEAnnotations();
							result.clear();

							for (EAnnotation eAnnotation : ((EModelElement) object).getEAnnotations()) {
								if (!genModel.isSuppressedAnnotation(eAnnotation.getSource())) {
									result.add((EAnnotation) copy(eAnnotation));
								}
							}
							return;
						}
						super.copyContainment(reference, object, copyEObject);
					}
				};
				EPackage outputPackage = (EPackage) copier.copy(originalPackage);
				copier.copyReferences();
				outputResource.getContents().add(outputPackage);
				collapseEmptyPackages(outputPackage);

				// Compute a map of resource location URIs to logical namespace URIs so that cross references will be
				// resolved via package registry when deserialized.
				final Map<URI, URI> uriMap = new HashMap<URI, URI>();
				for (Resource resource : originalResource.getResourceSet().getResources()) {
					List<EObject> contents = resource.getContents();
					if (!contents.isEmpty() && contents.get(0) instanceof EPackage) {
						uriMap.put(resource.getURI(), URI.createURI(((EPackage) contents.get(0)).getNsURI()));
					}
				}

				// This URI handler redirects the URI based on the mapping.
				XMLResource.URIHandler uriHandler = new org.eclipse.emf.ecore.xmi.impl.URIHandlerImpl.PlatformSchemeAware() {
					protected URI redirect(URI uri) {
						EObject eObject = originalResource.getResourceSet().getEObject(uri, false);
						if (eObject instanceof EClassifier) {
							EClassifier eClassifier = (EClassifier) eObject;
							return URI.createURI(eClassifier.getEPackage().getNsURI()).appendFragment("//" + eClassifier.getName()); //$NON-NLS-1$
						} else if (eObject instanceof EStructuralFeature) {
							EStructuralFeature feature = (EStructuralFeature) eObject;
							EClass eContainingClass = feature.getEContainingClass();
							return URI.createURI(eContainingClass.getEPackage().getNsURI()).appendFragment(
									"//" + eContainingClass.getName() + "/" + feature.getName()); //$NON-NLS-1$//$NON-NLS-2$
						}
						URI mappedURI = uriMap.get(uri.trimFragment());
						return mappedURI == null ? uri : mappedURI.appendFragment(uri.fragment());
					}

					@Override
					public URI deresolve(URI uri) {
						return super.deresolve(redirect(uri));
					}

					@Override
					public URI resolve(URI uri) {
						return super.resolve(redirect(uri));
					}

					@Override
					public void setBaseURI(URI uri) {
						super.setBaseURI(redirect(uri));
					}
				};
				Map<Object, Object> options = new HashMap<Object, Object>();
				options.put(XMLResource.OPTION_URI_HANDLER, uriHandler);
				options.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);

				try {
					outputResource.save(options);
				} catch (IOException exception) {
					// DMS handle this well.
					CodeGenEcorePlugin.INSTANCE.log(exception);
				}
			} finally {
				monitor.done();
			}
		} else {
			monitor.worked(1);
		}
	}

	@Override
	protected void generatePackageClass(GenPackage genPackage, Monitor monitor) {

		super.generatePackageClass(genPackage, monitor);
	}

	@Override
	protected void generatePackageInterface(GenPackage genPackage, Monitor monitor) {

		super.generatePackageInterface(genPackage, monitor);
	}

	@Override
	protected void generateFactoryClass(GenPackage genPackage, Monitor monitor) {

		super.generateFactoryClass(genPackage, monitor);
	}

	@Override
	protected void generateFactoryInterface(GenPackage genPackage, Monitor monitor) {

		super.generateFactoryInterface(genPackage, monitor);
	}

	protected void generateExtraClass(GenPackage genPackage, Monitor monitor, String targetPath, String packageName, String classNameSuffix,
			int jetEmitterId) {
		generateExtraFullyNamedClass(genPackage, monitor, targetPath, packageName,
				getUtilityClassSimpleName(genPackage.getGenModel(), classNameSuffix), jetEmitterId);
	}

	protected void generateExtraFullyNamedClass(GenPackage genPackage, Monitor monitor, String targetPath, String packageName, String className,
			int jetEmitterId) {
		if (getRootGenPackage(genPackage.getGenModel()).equals(genPackage)) {
			message = CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingJavaClass_message", new Object[] { className });
			monitor.subTask(message);
			generateJava(targetPath, packageName, className, getJETEmitter(getJETEmitterDescriptors(), jetEmitterId), null, createMonitor(monitor, 1));
		} else {
			monitor.worked(1);
		}
	}

	public static GenPackage getRootGenPackage(GenModel genModel) {
		return genModel.getGenPackages().get(0);
	}

	public static String getUtilityClassSimpleName(GenModel genModel, String classNamePostfix) {
		return getRootGenPackage(genModel).getPrefix() + classNamePostfix;
	}
}
