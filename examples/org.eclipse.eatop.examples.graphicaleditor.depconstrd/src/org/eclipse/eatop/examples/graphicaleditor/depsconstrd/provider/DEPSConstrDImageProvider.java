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
 * 
 */
package org.eclipse.eatop.examples.graphicaleditor.depsconstrd.provider;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;

public class DEPSConstrDImageProvider extends AbstractImageProvider {
	
	public static final String FAULTFAILURE = DEPSConstrDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".FaultFailure";
	public static final String QUANTITATIVESAFETYCONSTRAINT = DEPSConstrDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".QuantitativeSafetyConstraint";
	public static final String SAFETYCONSTRAINT = DEPSConstrDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".SafetyConstraint";
	//Anomaly
	public static final String INTERNALFAULTPROTOTYPE = DEPSConstrDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".InternalFaultPrototype";
	public static final String PROCESSFAULTPROTOTYPE = DEPSConstrDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".ProcessFaultPrototype";
	
	public DEPSConstrDImageProvider() {
	}
	
	@Override
	protected void addAvailableImages() {
		 addImageFilePath(FAULTFAILURE, "/icons/obj16/FaultFailure.gif");
		 addImageFilePath(QUANTITATIVESAFETYCONSTRAINT, "/icons/obj16/QuantitativeSafetyConstraint.gif");
		 addImageFilePath(SAFETYCONSTRAINT, "/icons/obj16/SafetyConstraint.gif");
		 //Anomaly
		 addImageFilePath(INTERNALFAULTPROTOTYPE, "/icons/obj16/InternalFaultPrototype.gif");
		 addImageFilePath(PROCESSFAULTPROTOTYPE, "/icons/obj16/ProcessFaultPrototype.gif");
	}
}
