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
package org.eclipse.eatop.examples.graphicaleditor.emtd.provider;

import org.eclipse.eatop.eastadl21.ErrorModelType;
import org.eclipse.eatop.eastadl21.FailureOutPort;
import org.eclipse.eatop.eastadl21.FaultInPort;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;


public class EMTDDiagramTypeProvider extends AbstractDiagramTypeProvider {


	public static int counter = 50;
	public static int y = 50;
	private IToolBehaviorProvider[] toolBehaviorProviders;
	public static String DIAGRAM_TYPE_ID = "org.eclipse.eatop.examples.graphicaleditor.emtd.DemoDiagramType";

	public EMTDDiagramTypeProvider() {
		setFeatureProvider(new EMTDFeatureProvider(this));
	}

	@Override
	public IToolBehaviorProvider[] getAvailableToolBehaviorProviders() {
		if (toolBehaviorProviders == null) {
			toolBehaviorProviders = new IToolBehaviorProvider[] { new EMTDToolBehaviorProvider(
					this) };
		}
		return toolBehaviorProviders;

	}

	/**
	 * If isAutoUpdateAtRuntime returns true, then the diagram will be updated,
	 * when it is already open in the graphical editor, but only if the editor
	 * is already dirty.
	 */
	@Override
	public boolean isAutoUpdateAtRuntime() {
		getports();
		return true;
	}

	/**
	 * If isAutoUpdateAtStartup returns true, then the diagram will be updated,
	 * when it is initially opened in the graphical editor. This will make the
	 * editor dirty.
	 */
	@Override
	public boolean isAutoUpdateAtStartup() {
		getports();
		return true;
	}

	/**
	 * If the editor is already dirty and the user chooses to discard his
	 * changes (reset the diagram), when a change from outside the diagram
	 * occurs.
	 */
	@Override
	public boolean isAutoUpdateAtReset() {
		getports();
		return true;
	}
	
	public void getports() {
		
		EObject parentBO = getDiagram().getLink().getBusinessObjects().get(0);
		
		
		if (parentBO instanceof ErrorModelType) {
			ErrorModelType newAFT = (ErrorModelType) parentBO;
			
			//FaultInPort
			
			EList<FaultInPort> portList = newAFT.getExternalFault();
	
			//all FaultInPort
			for (FaultInPort h : portList) 
			{
				if (h instanceof FaultInPort) 
				{
						FaultInPort h1 = (FaultInPort) h;
						boolean lock = true;
						
						EList<Anchor> sho = getDiagram().getAnchors();
						Anchor[] anchoArray = (Anchor[]) sho.toArray();
						
						//for lock
						for (Anchor gg : anchoArray) 
						{
							Anchor gggg = gg;
							EObject eOb = gggg.getLink().getBusinessObjects().get(0);
							if (!(eOb == null)) 
							{	
								if (eOb instanceof FaultInPort) 
								{	
									FaultInPort fFPort = (FaultInPort) eOb;
									if ((fFPort.getShortName().equals(h.getShortName()))) 
									{
										lock = false;	
									}
								}
							}
						}
						
						
						

						if (sho.isEmpty() || lock) 
						{
							AddContext context = new AddContext();
							context.setLocation(counter, y);
							context.setTargetContainer(this.getDiagram());
							context.setNewObject(h1);
							
							
							//canAdd
							
							getFeatureProvider().addIfPossible(new AddContext(context, h1));
							counter = counter + 30;
						}
				}
			}
		

		
		//FailureOutPort
		
		EList<FailureOutPort> portList2 = newAFT.getFailure();
		
		//all FailureOutPort
		for (FailureOutPort h : portList2) 
		{
			if (h instanceof FailureOutPort) 
			{
				
				FailureOutPort h1 = (FailureOutPort) h;
					boolean lock = true;
					
					EList<Anchor> sho = getDiagram().getAnchors();
					Anchor[] anchoArray = (Anchor[]) sho.toArray();
					
					
					for (Anchor gg : anchoArray) 
					{
						Anchor gggg = gg;
						EObject eOb = gggg.getLink().getBusinessObjects().get(0);
						if (!(eOb == null)) 
						{	
							if (eOb instanceof FailureOutPort) 
							{	
								FailureOutPort fFPort = (FailureOutPort) eOb;
								if ((fFPort.getShortName().equals(h.getShortName()))) 
								{
									lock = false;	
								}
							}
						}
					}
					
					
					
					if (sho.isEmpty() || lock) 
					{
						AddContext context = new AddContext();
						context.setLocation(counter, y);
						context.setTargetContainer(this.getDiagram());
						context.setNewObject(h1);
						
						
						
						getFeatureProvider().addIfPossible(new AddContext(context, h1));
						counter = counter + 30;
					}
			}
		}
	}
		}


	
}

	
