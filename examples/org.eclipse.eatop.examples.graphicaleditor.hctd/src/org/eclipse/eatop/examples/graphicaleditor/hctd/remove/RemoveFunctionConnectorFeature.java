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
package org.eclipse.eatop.examples.graphicaleditor.hctd.remove;

import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.HardwareComponentType;
import org.eclipse.eatop.eastadl21.HardwareConnector;
import org.eclipse.eatop.eastadl21.HardwareConnector_port;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.impl.DefaultRemoveFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;


public class RemoveFunctionConnectorFeature extends DefaultRemoveFeature {

	public RemoveFunctionConnectorFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canRemove(IRemoveContext context) {
		return true;
	}

	@Override
	public void preRemove(IRemoveContext context) {
		/**
		 * Remove the item from the model as well!
		 */
		Connection element = (Connection) context.getPictogramElement();
		HardwareComponentType model = (HardwareComponentType) getDiagram()
				.getLink().getBusinessObjects().get(0);
		EList<HardwareConnector> fConnector = model.getConnector();
		Anchor startelement = element.getStart();
		Anchor endelement = element.getEnd();
		HardwareConnector[] fcArray = (HardwareConnector[]) fConnector
				.toArray();
		for (HardwareConnector g : fcArray) {
			EList<HardwareConnector_port> portList = g.getPort();
			HardwareConnector_port[] anchoArray = (HardwareConnector_port[]) portList
					.toArray();
			if (endelement.getLink() != null) {

				if (startelement.getLink().getBusinessObjects().get(0) != null
						&& endelement.getLink().getBusinessObjects().get(0) != null) {

					if (anchoArray[0].getHardwarePin().equals(
							startelement.getLink().getBusinessObjects().get(0))
							&& anchoArray[1].getHardwarePin().equals(
									endelement.getLink().getBusinessObjects()
											.get(0))) {
						EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getFunctionType_Connector();
						DiagramUtil
								.removeObjectFromBOResource(
										model,
										referenceId,
										g);
					}
				}
			}
		}
	}

	@Override
	public boolean canExecute(IContext context) {
		return true;
	}

}
