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
package org.eclipse.eatop.eaadapter.ea2ecore.postprocessings;

import java.text.MessageFormat;

import org.eclipse.eatop.eaadapter.ea2ecore.PostProcessingTemplate;
import org.eclipse.eatop.eaadapter.internal.messages.Messages;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class AddOriginalQualifiedNameAnnotation extends PostProcessingTemplate {

	private String GENMODEL_ANNOTATION_SOURCE = "http://www.eclipse.org/emf/2002/GenModel"; //$NON-NLS-1$
	private String DOCUMENTATION_ANNOTATION = "documentation"; //$NON-NLS-1$

	public AddOriginalQualifiedNameAnnotation() {
	}

	@Override
	public void execute() {
		logger.info(Messages.logger_AddOriginalQualifiedNameAnnotation);
		EPackage rootPackage = (EPackage) EcoreUtil.getRootContainer(model);
		for (TreeIterator<EObject> iter = rootPackage.eAllContents(); iter.hasNext();) {
			EObject element = iter.next();
			if (element instanceof EClass) {
				EClass eclass = (EClass) element;
				String notes = EcoreUtil.getAnnotation(eclass, GENMODEL_ANNOTATION_SOURCE, DOCUMENTATION_ANNOTATION);
				// Add original fully qualified metamodel element name
				String qualifiedNameMsg = MessageFormat.format(Messages.qualified_Name_Message,
						getOriginalQualifiedName(eclass.getEPackage(), eclass.getName()));
				EcoreUtil.setAnnotation(eclass, GENMODEL_ANNOTATION_SOURCE, DOCUMENTATION_ANNOTATION, notes + qualifiedNameMsg);
			} else if (element instanceof EDataType) {
				EDataType edatatype = (EDataType) element;
				String notes = EcoreUtil.getAnnotation(edatatype, GENMODEL_ANNOTATION_SOURCE, DOCUMENTATION_ANNOTATION);
				// Add original fully qualified metamodel element name
				String qualifiedNameMsg = MessageFormat.format(Messages.qualified_Name_Message,
						getOriginalQualifiedName(edatatype.getEPackage(), edatatype.getName()));
				EcoreUtil.setAnnotation(edatatype, GENMODEL_ANNOTATION_SOURCE, DOCUMENTATION_ANNOTATION, notes + qualifiedNameMsg);
			}
		}
	}

	private String getOriginalQualifiedName(EPackage epkg, String name) {
		String pkgName = epkg.getName();
		String qualifiedName = pkgName + "." + name; //$NON-NLS-1$
		if (pkgName != model.getName()) {
			EPackage superPkg = epkg.getESuperPackage();
			return getOriginalQualifiedName(superPkg, qualifiedName);
		}
		return qualifiedName;
	}

}