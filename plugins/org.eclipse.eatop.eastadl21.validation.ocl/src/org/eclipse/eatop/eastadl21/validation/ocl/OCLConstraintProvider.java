/**
 * <copyright>
 * 
 * Copyright (c) 10, 2014 Continental AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *     Continental AG - Initial API and implementation
 * 
 * </copyright>
 */

package org.eclipse.eatop.eastadl21.validation.ocl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.Hashtable;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.eatop.eastadl21.validation.ocl.internal.Activator;
import org.eclipse.eatop.eastadl21.validation.ocl.internal.l10n.Messages;
import org.eclipse.eatop.eastadl21.validation.ocl.xml.IXMLMetaInformation;
import org.eclipse.eatop.eastadl21.validation.ocl.xml.XMLMetaInformationReader;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.validation.model.Category;
import org.eclipse.emf.validation.model.CategoryManager;
import org.eclipse.emf.validation.model.IModelConstraint;
import org.eclipse.emf.validation.service.AbstractConstraintProvider;
import org.eclipse.emf.validation.service.ConstraintExistsException;
import org.eclipse.ocl.OCLInput;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.options.ParsingOptions;
import org.eclipse.ocl.utilities.UMLReflection;
import org.osgi.framework.Bundle;
import org.xml.sax.SAXException;

//import org.eclipse.ocl.ecore;

/**
 * Provider of constraints parsed from OCL documents.
 */
public class OCLConstraintProvider extends AbstractConstraintProvider implements IExecutableExtension {
	private static final String E_OCL = "ocl"; //$NON-NLS-1$
	private static final String A_PATH = "path"; //$NON-NLS-1$
	private static final String A_CATEGORY = "categoryId"; //$NON-NLS-1$
	private static final String A_DESCRIPTION = "description"; //$NON-NLS-1$
	private static XMLMetaInformationReader metaInformationReader;
	private static String metaInformationPath = ""; //$NON-NLS-1$
	private Hashtable<String, IXMLMetaInformation> metaInformationList;

	protected static final String PARSING_FAILURE_CONFIG = Messages.parsing_failure_config;
	protected static final String MISSING_META_FILE = Messages.missing_meta_file;
	protected static final String OCL_REGISTRATION_FAILURE = Messages.ocl_registration_failure;
	protected static final String OCL_PARSING_FAILURE = Messages.ocl_parsing_failure;
	protected static final String OCL_LOADING_FAILURE = Messages.ocl_loading_failure;

	@Override
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		super.setInitializationData(config, propertyName, data);
		// create the constraint category

		String categoryID = config.getDeclaringExtension().getUniqueIdentifier();
		if (categoryID == null) {
			categoryID = "OCLProvider@" + Long.toHexString(System.identityHashCode(this)); //$NON-NLS-1$
		}

		categoryID = config.getAttribute(A_CATEGORY);

		Category category = CategoryManager.getInstance().getCategory(categoryID);
		category.setName(category.getName());

		Bundle contributor = Platform.getBundle(config.getDeclaringExtension().getNamespaceIdentifier());
		try {
			metaInformationReader = new XMLMetaInformationReader();
		} catch (ParserConfigurationException e) {
			String msg = String.format(PARSING_FAILURE_CONFIG);
			Activator.log(msg, e);
			e.printStackTrace();
		}

		parseDescriptions(contributor, config.getAttribute(A_DESCRIPTION));

		IConfigurationElement[] ocls = config.getChildren(E_OCL);
		for (IConfigurationElement ocl : ocls) {
			String path = ocl.getAttribute(A_PATH);

			CategoryManager.getInstance().getCategory(path);
			if (path != null && path.length() > 0) {
				// categorize by OCL document name
				IPath ipath = new Path(path);
				if (ipath.segmentCount() > 2) {
					Category[] categoryList = new Category[ipath.segmentCount() - 2];
					categoryList[0] = CategoryManager.getInstance().getCategory(category, ipath.removeFileExtension().segments()[1]);
					for (int i = 1; i < categoryList.length; i++) {
						categoryList[i] = CategoryManager.getInstance().getCategory(categoryList[i - 1],
								ipath.removeFileExtension().segments()[i + 1]);
					}
					parseConstraints(
							CategoryManager.getInstance().getCategory(categoryList[categoryList.length - 1],
									ipath.removeFileExtension().lastSegment()), contributor, path);
				} else {
					parseConstraints(CategoryManager.getInstance().getCategory(category, ipath.removeFileExtension().lastSegment()), contributor,
							path);
				}
			}
		}

		try {
			registerConstraints(getConstraints());
		} catch (ConstraintExistsException e) {
			throw new CoreException(new Status(IStatus.ERROR, Activator.getID(), 1, OCL_REGISTRATION_FAILURE, e));
		}
	}

	private void parseDescriptions(Bundle bundle, String path) {

		if (path != metaInformationPath) {
			metaInformationPath = path;
			URL url = bundle.getEntry(path);

			if (url != null) {
				try {
					InputStream input = url.openStream();

					try {
						metaInformationList = metaInformationReader.getDescription(input);
					} catch (ParserConfigurationException e) {
						String msg = String.format(PARSING_FAILURE_CONFIG, bundle.getSymbolicName(), path);
						Activator.log(msg, e);
					} catch (SAXException e) {
						String msg = String.format(PARSING_FAILURE_CONFIG, bundle.getSymbolicName(), path);
						Activator.log(msg, e);
					} finally {
						input.close();
					}
				} catch (IOException e) {
					String msg = String.format(MISSING_META_FILE, bundle.getSymbolicName(), path);
					Activator.log(msg, e);
				}
			}
		}
	}

	private void parseConstraints(Category category, Bundle bundle, String path) {
		URL url = bundle.getEntry(path);

		if (url != null) {
			try {
				InputStream input = url.openStream();

				try {
					parseConstraints(category, bundle.getSymbolicName(), input);
				} catch (ParserException e) {
					String msg = String.format(OCL_PARSING_FAILURE, bundle.getSymbolicName(), path);
					Activator.log(msg, e);
				} finally {
					input.close();
				}
			} catch (IOException e) {
				String msg = String.format(OCL_LOADING_FAILURE, bundle.getSymbolicName(), path);
				Activator.log(msg, e);
			}
		}
	}

	private void parseConstraints(Category category, String namespace, InputStream input) throws ParserException {

		OCLInput oclInput = new OCLInput(input);

		OCL ocl = OCL.newInstance();

		/**
		 * TODO: ParsingOption should be replaced or deleted as soon as the oclContainer function instead of eContainer
		 * is working
		 */
		ParsingOptions.setOption(ocl.getEnvironment(), ParsingOptions.implicitRootClass(ocl.getEnvironment()), EcorePackage.Literals.EOBJECT);

		for (Constraint constraint : ocl.parse(oclInput)) {
			if (isInvariant(constraint)) {
				// only add invariant constraints for validation
				addConstraint(category, namespace, ocl, constraint);
			}
		}
	}

	private boolean isInvariant(Constraint constraint) {
		return UMLReflection.INVARIANT.equals(constraint.getStereotype());
	}

	private void addConstraint(Category category, String namespace, OCL ocl, Constraint constraint) {

		Collection<IModelConstraint> constraints = getConstraints();

		OCLConstraintDescriptor desc = new OCLConstraintDescriptor(namespace, constraint, constraints.size() + 1, metaInformationList.get(constraint
				.getName()));

		if (category != null) {
			category.addConstraint(desc);
		}

		constraints.add(new OCLConstraint(desc, ocl));
	}
}
