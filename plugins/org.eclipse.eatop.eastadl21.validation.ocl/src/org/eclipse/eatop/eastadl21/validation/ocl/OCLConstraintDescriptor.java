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

import org.eclipse.eatop.eastadl21.validation.ocl.internal.l10n.Messages;
import org.eclipse.eatop.eastadl21.validation.ocl.xml.IXMLMetaInformation;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.model.ConstraintSeverity;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.service.AbstractConstraintDescriptor;
import org.eclipse.ocl.ecore.Constraint;

public class OCLConstraintDescriptor extends AbstractConstraintDescriptor {
	private final Constraint constraint;
	private final String id;
	private String name;
	private final String namespace;
	private int code;
	private String message = null;
	private String description = null;
	private ConstraintSeverity severity = ConstraintSeverity.ERROR; // Standard value, will be overwritten by meta-data
	private EvaluationMode<?> evaluationMode = EvaluationMode.BATCH; // Standard value, will be overwritten by meta-data
	private Integer[] notificationEvents = null;

	protected static final String STANDARD_DESCRIPTION = Messages.standard_description;
	protected static final String STANDARD_MESSAGE = Messages.standard_message;

	public OCLConstraintDescriptor(String namespace, Constraint constraint, int code, IXMLMetaInformation metaInformation) {

		/* Initialization of necessary meta-data */
		this.constraint = constraint;
		this.namespace = namespace;
		name = constraint.getName();
		if (name == null) {
			name = Long.toHexString(System.identityHashCode(constraint));
		}

		id = namespace + '.' + constraint.getName();
		this.code = code;

		/* Overwrite initial data with assigned meta-data if available */
		if (metaInformation != null) {
			name = metaInformation.getName();
			code = metaInformation.getCode();
			message = metaInformation.getMessage();
			description = metaInformation.getDescription();
			severity = metaInformation.getSeverity();
			evaluationMode = metaInformation.getEvaludationMode();
			notificationEvents = metaInformation.getNotificationEvent();
		}
	}

	final Constraint getConstraint() {
		return constraint;
	}

	@Override
	public String getBody() {
		return constraint.getSpecification().getBodyExpression().toString();
	}

	@Override
	public String getDescription() {

		// Standard description in case of absence in the XML description
		if (description == null) {
			description = STANDARD_DESCRIPTION;
		}
		return description;
	}

	@Override
	public EvaluationMode<?> getEvaluationMode() {
		return evaluationMode;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getMessagePattern() {

		// Standard message in case of absence in the XML description
		if (message == null) {
			message = String.format(STANDARD_MESSAGE, getName());
		}
		return message;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getPluginId() {
		return namespace;
	}

	@Override
	public ConstraintSeverity getSeverity() {
		return severity;
	}

	@Override
	public int getStatusCode() {
		return code;
	}

	@Override
	public boolean targetsEvent(Notification notification) {

		if (notificationEvents != null) {
			for (int notificationEvent : notificationEvents) {
				if (notification.getEventType() == notificationEvent) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public boolean targetsTypeOf(EObject eObject) {
		return constraint.getSpecification().getContextVariable().getType().isInstance(eObject);
	}

}
