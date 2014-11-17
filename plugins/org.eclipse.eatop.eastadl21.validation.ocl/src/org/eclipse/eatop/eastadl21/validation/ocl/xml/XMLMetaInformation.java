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

package org.eclipse.eatop.eastadl21.validation.ocl.xml;

import java.util.ArrayList;

import org.eclipse.emf.validation.model.ConstraintSeverity;
import org.eclipse.emf.validation.model.EvaluationMode;

public class XMLMetaInformation implements IXMLMetaInformation {

	private String message = null;
	private String description = null;
	private EvaluationMode<?> evaluationMode = null;
	private ConstraintSeverity severity = null;
	private int code;
	private Integer[] notificationEvents = null;
	private String name = null;
	private String oclName = null;

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public EvaluationMode<?> getEvaludationMode() {
		return evaluationMode;
	}

	@Override
	public ConstraintSeverity getSeverity() {
		return severity;
	}

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public Integer[] getNotificationEvent() {
		return notificationEvents;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void setEvaludationMode(EvaluationMode<?> mode) {
		evaluationMode = mode;
	}

	@Override
	public void setSeverity(ConstraintSeverity severity) {
		this.severity = severity;
	}

	@Override
	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public void setNotificationEvent(ArrayList<Integer> events) {
		if (events != null) {
			notificationEvents = events.toArray(new Integer[1]);
		}
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setOCLName(String name) {
		oclName = name;
	}

	@Override
	public String getOCLName() {
		return oclName;
	}

}
