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
import java.net.URL;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import javax.activation.MimeType;
import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.eatop.eaadapter.ea2ecore.PostProcessingTemplate;
import org.eclipse.eatop.eaadapter.internal.messages.Messages;
import org.eclipse.eatop.eaadapter.util.IConstants;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;

public class SetInstanceClassNameForDataTypes extends PostProcessingTemplate {

	private static Map<String, Class<?>> mapping;
	static {
		mapping = new HashMap<String, Class<?>>();
		mapping.put(IConstants.STRING, String.class);
		mapping.put(IConstants.INTEGER, Integer.class);
		mapping.put(IConstants.FLOAT, Double.class);
		mapping.put(IConstants.NUMERICAL, String.class);
		mapping.put(IConstants.UNLIMITED_INTEGER, BigInteger.class);
		mapping.put(IConstants.UNLIMITED_NATURAL, BigInteger.class);
		mapping.put(IConstants.POSITIVE_INTEGER, Long.class);
		mapping.put(IConstants.BOOLEAN, Boolean.class);
		mapping.put(IConstants.DATE_TIME, XMLGregorianCalendar.class);
		mapping.put(IConstants.DATE, XMLGregorianCalendar.class);//
		mapping.put(IConstants.NAME_TOKEN, String.class);
		mapping.put(IConstants.EXT_ID_CLASS_ENUM, String.class);
		mapping.put(IConstants.REGULAR_EXPRESSION, String.class);
		mapping.put(IConstants.IDENTIFIER, String.class);
		mapping.put(IConstants.ADDRESS, String.class);
		mapping.put(IConstants.BASE_TYPE_ENCODING_STRING, String.class);
		mapping.put(IConstants.DISPLAY_FORMAT_STRING, String.class);
		mapping.put(IConstants.MIME_TYPE_STRING, MimeType.class);
		mapping.put(IConstants.URI_STRING, URI.class);
		mapping.put(IConstants.URL_STRING, URL.class);
		mapping.put(IConstants.IS_STRING, String.class);
		mapping.put(IConstants.NUMERICAL_VALUE, String.class);
		mapping.put(IConstants.NATIVE_DECLARATION_STRING, String.class);
		mapping.put(IConstants.REF, String.class);
		mapping.put(IConstants.REVISION_LABEL_STRING, String.class);
		mapping.put(IConstants.VERBATIM_STRING, String.class);
		mapping.put(IConstants.SUPER_SCRIPT, String.class);
		mapping.put(IConstants.MIXED_CONTENT_FOR_PLAINE_TEXT, String.class);
		mapping.put(IConstants.AXIS_INDEX_TYPE, String.class);
		mapping.put(IConstants.RECORD_LAYOUT_ITERATOR_POINT, String.class);
		mapping.put(IConstants.DIAG_REQUIREMENT_ID_STRING, String.class);
		mapping.put(IConstants.NAME_TOKENS, String.class);
		mapping.put(IConstants.CIDENTIFIER, String.class);
		mapping.put(IConstants.CSE_CODE_TYPE, Integer.class);
		mapping.put(IConstants.ASAM_RECORD_LAYOUT_SEMANTICS, String.class);
		mapping.put(IConstants.TABLE_SEPARATOR_STRING, String.class);
		mapping.put(IConstants.VIEW_TOKENS, String.class);
		mapping.put(IConstants.ALIGNMENT_TYPE, String.class);
		mapping.put(IConstants.CATEGORY_STRING, String.class);
		mapping.put(IConstants.SECTION_INITIALIZATION_POLICY_TYPE, String.class);
		mapping.put(IConstants.REFERRABLE_SUBTYPE_ENUM, String.class);
		mapping.put(IConstants.TIME_VALUE, Double.class);
	}

	@Override
	public void execute() {

		TreeIterator<EObject> iterator = model.eAllContents();
		while (iterator.hasNext()) {
			EObject element = iterator.next();

			if (element instanceof EEnum) {
				continue;
			}

			if (element instanceof EDataType) {
				EDataType dataType = (EDataType) element;
				String dataTypeName = dataType.getName();
				if (dataTypeName != null) {
					Class<?> javaClass = mapping.get(dataTypeName);
					if (javaClass != null) {
						dataType.setInstanceClassName(javaClass.getName());
						logger.info(MessageFormat.format(Messages.logger_SetDdataType, dataType.getName(), javaClass.getName(),
								((ENamedElement) element).getName()));
					} else {
						logger.error(MessageFormat.format(Messages.logger_NoDdataTypeMappingAvailable, dataTypeName,
								((ENamedElement) element).getName()));
					}
				}
			}
		}
	}
}
