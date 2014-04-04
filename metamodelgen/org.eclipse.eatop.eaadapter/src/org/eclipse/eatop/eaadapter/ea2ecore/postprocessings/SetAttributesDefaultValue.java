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

package org.eclipse.eatop.eaadapter.ea2ecore.postprocessings;

import java.math.BigInteger;
import java.net.URI;
import java.text.MessageFormat;

import javax.activation.MimeType;
import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.eatop.eaadapter.ea2ecore.PostProcessingTemplate;
import org.eclipse.eatop.eaadapter.internal.messages.Messages;
import org.eclipse.eatop.eaadapter.util.IConstants;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;

public class SetAttributesDefaultValue extends PostProcessingTemplate {

	@Override
	public void execute() {
		TreeIterator<EObject> iterator = model.eAllContents();
		while (iterator.hasNext()) {
			EObject element = iterator.next();

			if (element instanceof EAttribute) {
				EAttribute attribute = (EAttribute) element;
				String instanceClassName = getInstanceClassNameForAttribute(attribute);
				if (instanceClassName != null) {
					if (String.class.getName().equals(instanceClassName)) {
						setDefaultValueForAttribute(attribute, ""); //$NON-NLS-1$
					} else if (Boolean.class.getName().equals(instanceClassName)) {
						setDefaultValueForAttribute(attribute, IConstants.FALSE);
					} else if (IConstants.BOOLEAN_TYPE.equals(instanceClassName)) {
						setDefaultValueForAttribute(attribute, IConstants.FALSE);
					} else if (Integer.class.getName().equals(instanceClassName)) {
						setDefaultValueForAttribute(attribute, IConstants.ZERO);
					} else if (IConstants.INT_TYPE.equals(instanceClassName)) {
						setDefaultValueForAttribute(attribute, IConstants.ZERO);
					} else if (Float.class.getName().equals(instanceClassName)) {
						setDefaultValueForAttribute(attribute, IConstants.ZERO_FLOAT);
					} else if (IConstants.FLOAT_TYPE.equals(instanceClassName)) {
						setDefaultValueForAttribute(attribute, IConstants.ZERO_FLOAT);
					} else if (Long.class.getName().equals(instanceClassName)) {
						setDefaultValueForAttribute(attribute, IConstants.ZERO);
					} else if (IConstants.LONG_TYPE.equals(instanceClassName)) {
						setDefaultValueForAttribute(attribute, IConstants.ZERO);
					} else if (Double.class.getName().equals(instanceClassName)) {
						setDefaultValueForAttribute(attribute, IConstants.ZERO_FLOAT);
					} else if (IConstants.DOUBLE_TYPE.equals(instanceClassName)) {
						setDefaultValueForAttribute(attribute, IConstants.ZERO_FLOAT);
					} else if (BigInteger.class.getName().equals(instanceClassName)) {
						setDefaultValueForAttribute(attribute, IConstants.ZERO);
					} else if (XMLGregorianCalendar.class.getName().equals(instanceClassName)) {
						setDefaultValueForAttribute(attribute, IConstants.XML_GREGORIAN_CALENDAR_DEFAULT_VALUE);
					} else if (MimeType.class.getName().equals(instanceClassName)) {
						setDefaultValueForAttribute(attribute, "application/*"); //$NON-NLS-1$
					} else if (URI.class.getName().equals(instanceClassName)) {
						setDefaultValueForAttribute(attribute, ""); //$NON-NLS-1$
					} else if (EEnum.class.getName().equals(instanceClassName)) {
						EList<EEnumLiteral> literals = ((EEnum) attribute.getEType()).getELiterals();
						if (literals.size() > 0) {
							setDefaultValueForAttribute(attribute, literals.get(0).getLiteral());
						}
					}
				}
			}
		}
	}

	private void setDefaultValueForAttribute(EAttribute attribute, String defaultValue) {
		attribute.setDefaultValueLiteral(defaultValue);
		logger.info(MessageFormat.format(Messages.logger_SetDefaultValue, attribute.getName(), attribute.getDefaultValueLiteral()));
	}

	private String getInstanceClassNameForAttribute(EAttribute attribute) {
		EClassifier classifer = attribute.getEType();
		if (classifer instanceof EEnum) {
			return EEnum.class.getName();
		} else if (classifer instanceof EDataType) {
			EDataType dataType = (EDataType) classifer;
			return dataType.getInstanceClassName();
		}
		return null;
	}

}
