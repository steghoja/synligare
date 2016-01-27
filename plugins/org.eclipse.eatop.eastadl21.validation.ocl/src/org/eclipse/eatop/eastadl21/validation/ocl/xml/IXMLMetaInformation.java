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

public interface IXMLMetaInformation {

	public void setMessage(String message);

	public void setDescription(String description);

	public void setEvaludationMode(EvaluationMode<?> mode);

	public void setSeverity(ConstraintSeverity severity);

	public void setCode(int code);

	public void setNotificationEvent(ArrayList<Integer> events);

	public void setName(String name);

	public void setOCLName(String name);

	public String getMessage();

	public String getDescription();

	public EvaluationMode<?> getEvaludationMode();

	public ConstraintSeverity getSeverity();

	public int getCode();

	public Integer[] getNotificationEvent();

	public String getName();

	public String getOCLName();

}
