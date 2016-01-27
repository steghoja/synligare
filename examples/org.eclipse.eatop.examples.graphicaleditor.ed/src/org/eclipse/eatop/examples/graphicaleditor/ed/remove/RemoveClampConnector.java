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
package org.eclipse.eatop.examples.graphicaleditor.ed.remove;

import org.eclipse.eatop.eastadl21.ClampConnector;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.Environment;
import org.eclipse.eatop.eastadl21.FunctionPrototype;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.impl.DefaultRemoveFeature;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;


public class RemoveClampConnector extends
		DefaultRemoveFeature {

	public RemoveClampConnector(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canRemove(IRemoveContext context) {
		return true;
	}

	@Override
	public boolean canExecute(IContext context) {
		return false;
	}

	@Override
	public void preRemove(IRemoveContext context) 
	{
		// source and target of connection
		Connection element = (Connection) context.getPictogramElement();
		

		// delete the clampconnector	
		FunctionPrototype targetPrototype = null;
		FunctionPrototype sourcePrototype = null;

		// get SourcePrototype
		if (element.getStart().getParent().getLink().getBusinessObjects().get(0) != null
				&& element.getStart().getParent().getLink()
						.getBusinessObjects().get(0) instanceof FunctionPrototype) {
			sourcePrototype = (FunctionPrototype) element.getStart()
					.getParent().getLink().getBusinessObjects().get(0);
		}

		// get TargetPrototype
		if (element.getEnd().getParent().getLink().getBusinessObjects().get(0) != null
				&& element.getEnd().getParent().getLink().getBusinessObjects()
						.get(0) instanceof FunctionPrototype) {
			targetPrototype = (FunctionPrototype) element.getEnd().getParent()
					.getLink().getBusinessObjects().get(0);
		}

		//search all env
		EList<Shape> shapes =  getDiagram().getChildren();
		
		boolean sourceEnvironmentContainsCC = false;
		for(Shape s:shapes){
			//if env
			if(s.getLink().getBusinessObjects().get(0) instanceof Environment)
				{
					Environment e = (Environment) s.getLink().getBusinessObjects().get(0);
				//proto inside equals sourceproto?
				if(e.getEnvironmentModel().equals(sourcePrototype))
				{
					
					//look if port/proto of contextconnection 
					//equal of port/proto of CC
					for(ClampConnector cc: e.getClampConnector()){
						if(cc.getPort().get(1).getFunctionPort().equals(element.getStart().getLink().getBusinessObjects().get(0))
						&&cc.getPort().get(0).getFunctionPort().equals(element.getEnd().getLink().getBusinessObjects().get(0))
						&&cc.getPort().get(1).getFunctionPrototype().get(0).equals(sourcePrototype)
						&&cc.getPort().get(0).getFunctionPrototype().get(0).equals(targetPrototype)){
						sourceEnvironmentContainsCC = true;
						EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getEnvironment_ClampConnector();
						DiagramUtil.removeObjectFromBOResource(e, referenceId, cc);
						break;}
					}
				}
			}
		}
		
		if (!sourceEnvironmentContainsCC) {
			for (Shape s : shapes) {
				// if env
				if (s.getLink().getBusinessObjects().get(0) instanceof Environment) {
					Environment e = (Environment) s.getLink().getBusinessObjects().get(0);
					//proto inside equals targetproto?
					if (e.getEnvironmentModel().equals(targetPrototype)) {
					
					for(ClampConnector cc: e.getClampConnector()){
						if(cc.getPort().get(1).getFunctionPort().equals(element.getStart().getLink().getBusinessObjects().get(0))
								&&cc.getPort().get(0).getFunctionPort().equals(element.getEnd().getLink().getBusinessObjects().get(0))
								&&cc.getPort().get(1).getFunctionPrototype().get(0).equals(sourcePrototype)
								&&cc.getPort().get(0).getFunctionPrototype().get(0).equals(targetPrototype)){
							EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getEnvironment_ClampConnector();
							DiagramUtil.removeObjectFromBOResource(e, referenceId, cc);
						break;}
						}
					}
				}
			}
		}
	}
}


