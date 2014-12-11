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
package org.eclipse.eatop.examples.graphicaleditor.epd.features;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

import org.eclipse.eatop.eastadl21.Dependability;
import org.eclipse.eatop.examples.graphicaleditor.common.dialog.OpenDiagramDialog;
import org.eclipse.eatop.examples.graphicaleditor.common.util.EASTADLEditorUtil;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.mm.pictograms.PictogramsFactory;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IPeService;
import org.eclipse.graphiti.ui.features.AbstractDrillDownFeature;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ListDialog;

public class OpenDEPDLDDiagramFeature extends AbstractDrillDownFeature {
	private IPeService peService;
	private Collection<Diagram> diagrams;
	public OpenDEPDLDDiagramFeature(IFeatureProvider fp) {
		super(fp);
		peService = Graphiti.getPeService();
		diagrams = new LinkedList<Diagram>();
		
		findAllDiagrams();
	}
	private void findAllDiagrams() {
		getDiagrams().add(this.getDiagram());

		TreeIterator<EObject> iter = this.getDiagram().eResource()
				.getAllContents();
		while (iter.hasNext()) {
			EObject element = iter.next();
			if (element instanceof Diagram) {
				diagrams.add((Diagram) element);
			}
		}
	}
	@Override
	protected Collection<Diagram> getDiagrams() {
		return diagrams;
	}
	@Override
	public String getName() {
		return "Open associated diagram";
	}

	@Override
	public void execute(ICustomContext context) {
		// some has doubleclicked on Dependability
		PictogramElement[] elements = context.getPictogramElements();
		if (elements.length == 1) {
			PictogramElement clickedPictogramElement = elements[0];
			EObject clickedBusinessObject = clickedPictogramElement.getLink()
					.getBusinessObjects().get(0);

			final Collection<Diagram> linkedDiagrams = getLinkedDiagrams(clickedPictogramElement);
			if (linkedDiagrams.size() == 0) {
				// there is no diagram associated with the element. Create a new
				// one!
				Diagram diagram = peService.createDiagram(
						"org.eclipse.eatop.examples.graphicaleditor.depdld", "DependabilityDesignLevel diagram",
						true);
				PictogramLink link = PictogramsFactory.eINSTANCE
						.createPictogramLink();
				link.getBusinessObjects().add(clickedBusinessObject);
				diagram.setLink(link);

				this.getDiagram().eResource().getContents().add(diagram);
				this.getDiagrams().add(diagram);
				try {
					this.getDiagram().eResource().save(null);
				} catch (IOException e) {
					e.printStackTrace();
				}
				openDiagramEditor(diagram);
			} else if (linkedDiagrams.size() == 1) { // if we have only 1 diagram, open it without dialog
				openDiagramEditor(linkedDiagrams.iterator().next());
			} else {
				// there are already diagrams associated with the double-clicked object
				ListDialog dialog = new OpenDiagramDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), linkedDiagrams.toArray());
				dialog.open();
				Object[] result = dialog.getResult();
				if (result != null) {
					for (int i = 0; i < result.length; i++) {
						Diagram diagram = (Diagram) result[i];
						openDiagramEditor(diagram);
					}
				}
			}
		}
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		PictogramElement[] pictogramElements = context.getPictogramElements();
		if (pictogramElements != null && pictogramElements.length == 1) {
			Object bo = getBusinessObjectForPictogramElement(pictogramElements[0]);
			
			if (bo instanceof Dependability) {
				Dependability dep=(Dependability) bo;
				if(dep.getName().equals("DependabilityDesignLevel")){
					return true;
				}
				
			}
		}
		return false;
	}

	@Override
	protected Collection<Diagram> getLinkedDiagrams(PictogramElement pe) {
		return super.getLinkedDiagrams(pe);
	}

	@Override
	protected void openDiagramEditor(Diagram d) {
		EASTADLEditorUtil.openDiagram(d);
	}
	@Override
	public String getDescription() {
		return "Open the associated diagram";
	}

}
