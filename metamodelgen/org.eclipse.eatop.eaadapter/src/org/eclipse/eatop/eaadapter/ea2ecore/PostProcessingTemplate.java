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

package org.eclipse.eatop.eaadapter.ea2ecore;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.eatop.eaadapter.logging.Logger;
import org.eclipse.eatop.eaadapter.util.IConstants;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.util.EcoreUtil;

import eaadapter.EAConnector;
import eaadapter.EAElement;
import eaadapter.EAPackage;
import eaadapter.EARepository;
import eaadapter.abstracthierachy.EANamedElement;

public abstract class PostProcessingTemplate {

	protected EPackage model;
	protected EARepository repository;
	protected Logger logger;
	protected EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;

	public abstract void execute() throws Exception;

	protected EAPackage findEAPackageByGUID(String guid) {
		EANamedElement res = findEANamedElementByGUID(guid);
		if (res instanceof EAPackage) {
			return (EAPackage) res;
		}
		return null;
	}

	protected EAElement findEAElementByGUID(String guid) {
		EANamedElement res = findEANamedElementByGUID(guid);
		if (res instanceof EAElement) {
			return (EAElement) res;
		}
		return null;
	}

	protected EANamedElement findEANamedElementByGUID(String guid) {
		for (TreeIterator<EObject> iter = EcoreUtil.getAllContents(repository.getModels()); iter.hasNext();) {
			EANamedElement element = (EANamedElement) iter.next();
			if (element.getGuid() != null && element.getGuid().equals(guid)) {
				return element;
			}
		}
		return null;
	}

	protected EPackage findEPackageByName(String name) {
		TreeIterator<EObject> iter = model.eAllContents();
		while (iter.hasNext()) {
			EObject element = iter.next();
			if (element instanceof EPackage) {
				if (((EPackage) element).getName().equals(name)) {
					return (EPackage) element;
				}
			}
		}
		return null;
	}

	protected ENamedElement findENamedElementByGUID(String guid) {
		TreeIterator<EObject> iter = model.eAllContents();
		while (iter.hasNext()) {
			EObject element = iter.next();
			if (element instanceof ENamedElement) {
				EAnnotation metaData = ((ENamedElement) element).getEAnnotation(IConstants.METADATA);
				if (metaData != null) {
					if (element.equals(guid)) {
						return (ENamedElement) element;
					}
				}
			}
		}
		return null;
	}

	protected EAConnector findEReferenceByGUID(String guid) {
		ENamedElement element = findENamedElementByGUID(guid);
		if (element != null && element instanceof EReference) {
			return (EAConnector) element;
		}
		return null;
	}

	protected ENamedElement findENamedElementByName(String name) {
		TreeIterator<EObject> iter = model.eAllContents();
		while (iter.hasNext()) {
			EObject element = iter.next();
			if (element instanceof ENamedElement) {
				if (((ENamedElement) element).getName().equalsIgnoreCase(name)) {
					return (ENamedElement) element;
				}
			}
		}
		return null;
	}

	protected EClassifier findEClassifierByName(String name) {
		TreeIterator<EObject> iter = model.eAllContents();
		while (iter.hasNext()) {
			EObject element = iter.next();
			if (element instanceof EClassifier) {
				if (((EClassifier) element).getName().equalsIgnoreCase(name)) {
					return (EClassifier) element;
				}
			}
		}
		return null;
	}

	protected static String createPathOfEPackage(EPackage pkg) {
		List<EPackage> superPackages = new LinkedList<EPackage>();
		superPackages.add(pkg);

		EPackage p = pkg;
		while (p.getESuperPackage() != null) {
			superPackages.add(0, p.getESuperPackage()); // insert to first place
			p = p.getESuperPackage();
		}

		String path = ""; //$NON-NLS-1$
		for (EPackage p2 : superPackages) {
			path = path + "/" + p2.getName(); //$NON-NLS-1$
		}
		return path;
	}

	protected EAConnector getEAConnectorForEReference(EReference eref) {
		EAnnotation annotation = eref.getEAnnotation(IConstants.METADATA);
		if (annotation == null) {
			return null;
		}

		String guid = annotation.getDetails().get(IConstants.GUID);
		EAConnector eaConnector = (EAConnector) findEANamedElementByGUID(guid);
		return eaConnector;
	}

	public EPackage getModel() {
		return model;
	}

	public void setModel(EPackage model) {
		this.model = model;
	}

	public EARepository getRepository() {
		return repository;
	}

	public void setRepository(EARepository repository) {
		this.repository = repository;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLoggingEnabled() {
		logger.setLoggingEnabled();
	}

	public void setLoggingDisabled() {
		logger.setLoggingDisabled();
	}

	public EPackage invoke() throws Exception {
		execute();
		return model;
	}
}
