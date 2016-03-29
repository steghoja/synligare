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
package org.eclipse.eatop.examples.graphicaleditor.afp.pattern.features;

import org.eclipse.eatop.eastadl21.AnalysisFunctionPrototype;
import org.eclipse.eatop.eastadl21.Dependability;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.examples.common.ExampleUtil;
import org.eclipse.graphiti.features.IDirectEditingInfo;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.mm.GraphicsAlgorithmContainer;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.pattern.AbstractPattern;
import org.eclipse.graphiti.pattern.IPattern;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;


public class AnalysisFunctionalPrototypeDomainObjectPattern extends
		AbstractPattern implements IPattern {
	 private static final IColorConstant E_CLASS_TEXT_FOREGROUND =
		        IColorConstant.BLACK;
		 
		    private static final IColorConstant E_CLASS_FOREGROUND =
		        new ColorConstant(98, 131, 167);

		    private static final IColorConstant E_CLASS_BACKGROUND =
		        new ColorConstant(187, 218, 247);
		    private static final String TITLE = "Create AFT";
			 
			   private static final String USER_QUESTION = "Enter new AFT short name";
	public AnalysisFunctionalPrototypeDomainObjectPattern() {
		super(null);
	}

	@Override
	public String getCreateDescription() {
		return "Create AFP";
	}

	@Override
	public String getCreateName() {
		return "AnaysisFunctionalPrototype";
	}

	@Override
	public boolean isMainBusinessObjectApplicable(Object mainBusinessObject) {
		return  mainBusinessObject instanceof AnalysisFunctionPrototype;
	}

	@Override
	protected boolean isPatternControlled(PictogramElement pictogramElement) {
		Object domainObject = getBusinessObjectForPictogramElement(pictogramElement);
	       return isMainBusinessObjectApplicable(domainObject);
	}

	@Override
	protected boolean isPatternRoot(PictogramElement pictogramElement) {
		Object domainObject = getBusinessObjectForPictogramElement(pictogramElement);
	       return isMainBusinessObjectApplicable(domainObject);
	}

	private boolean containerAlreadyExists(IAddContext context) {
		/*the container of an object can't be dragged and dropped from the wizard
		if there is already a container of the same BO in the diagram*/
		if (context.getTargetContainer() instanceof Diagram) 
				for( Shape cs: context.getTargetContainer().getChildren())
				{
					if(cs.getLink().getBusinessObjects().get(0).equals(context.getNewObject()))
						return true;
					
				}
		return false;
		}

	@Override
	public boolean canAdd(IAddContext context) {
		
		if(containerAlreadyExists(context))
			return false;
		
		// check if user wants to add a EClass
        if (context.getNewObject() instanceof AnalysisFunctionPrototype) {
            // check if user wants to add to a diagram
            if (context.getTargetContainer() instanceof Diagram) {
                return true;
            }
        }
        return false;
	}
	@Override
	public PictogramElement add(IAddContext context) {
		AnalysisFunctionPrototype addedFSC = (AnalysisFunctionPrototype) context.getNewObject();
        Diagram targetDiagram = (Diagram) context.getTargetContainer();
 
      
       
        // CONTAINER SHAPE WITH ROUNDED RECTANGLE
       final IPeCreateService peCreateService = Graphiti.getPeCreateService();
      final  ContainerShape containerShape =
             peCreateService.createContainerShape(targetDiagram, true);
        
        // check whether the context has a size (e.g. from a create feature)
        // otherwise define a default size for the shape
        final int width = context.getWidth() <= 0 ? 170 : context.getWidth();
        final int height = context.getHeight() <= 0 ? 50 : context.getHeight();


       final IGaService gaService = Graphiti.getGaService();
        RoundedRectangle roundedRectangle; // need to access it later
 
        {
            // create and set graphics algorithm
        	

        	
             roundedRectangle =
                gaService.createRoundedRectangle(containerShape, 5, 5);
            roundedRectangle.setForeground(manageColor(E_CLASS_FOREGROUND));
            roundedRectangle.setBackground(manageColor(E_CLASS_BACKGROUND));
            roundedRectangle.setLineWidth(2);
            gaService.setLocationAndSize(roundedRectangle,
                context.getX(), context.getY(), width, height);
 
            // if added Class has no resource we add it to the resource 
            // of the diagram
            // in a real scenario the business model would have its own resource
            if (addedFSC.eResource() == null) {
                     getDiagram().eResource().getContents().add(addedFSC);
            }
            // create link and wire it
            link(containerShape, addedFSC);
        }
 
        // SHAPE WITH LINE
        {
            // create shape for line
            Shape shape = (Shape) peCreateService.createShape(containerShape, false);
 
            // create and set graphics algorithm
            Polyline polyline =
                gaService.createPolyline((GraphicsAlgorithmContainer) shape, new int[] { 0, 25, width, 25 });
            polyline.setForeground(manageColor(E_CLASS_FOREGROUND));
            polyline.setLineWidth(2);
        }
      
 
        // SHAPE WITH TEXT for ShortName
        {
            // create shape for text
            Shape shape = (Shape) peCreateService.createShape(containerShape, false);
            
            // create and set text graphics algorithm
            Text text = gaService.createText(shape, addedFSC.getShortName());
            text.setForeground(manageColor(E_CLASS_TEXT_FOREGROUND));
            text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT); 
            // vertical alignment has as default value "center"
            text.setFont(gaService.manageDefaultFont(getDiagram(), true, false));
            gaService.setLocationAndSize(text, 22,12, width, 10);
          
            
            // create link and wire it
            link(shape, addedFSC);
            
            //provide information to support direct-editing directly
            //after object creation (must be actived additionally)
            IDirectEditingInfo directEditingInfo=getFeatureProvider().getDirectEditingInfo();
            // set container shape for direct editing after object creation
            directEditingInfo.setMainPictogramElement(containerShape);
            //set container shape for direct and graphics algorithm where the editor for
            //direct editing shall be opened after object creation
            directEditingInfo.setPictogramElement(shape);
            directEditingInfo.setGraphicsAlgorithm(text);
            
          
        }
     // SHAPE WITH TEXT for HazardType
        {
        	 // create shape for text  
            Shape shape = (Shape) peCreateService.createShape(containerShape, false);
        	//add Text 
             Text text1=gaService.createText(shape, addedFSC.eClass().getName());
             text1.setForeground(manageColor(E_CLASS_TEXT_FOREGROUND));
             text1.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT );
             text1.setFont(gaService.manageDefaultFont(getDiagram(), false, true));
             gaService.setLocationAndSize(text1, 22, 2, width, 10);
             // create link and wire it
	            link(shape, addedFSC);
        }
    

     
        // add a chopbox anchor to the shape 
      
        peCreateService.createChopboxAnchor(containerShape);
        // call the layout feature
        layoutPictogramElement(containerShape);

    

        return containerShape;

}

	@Override
	public boolean canCreate(ICreateContext context) {
		 return context.getTargetContainer() instanceof Diagram;
			}

	@Override
	public Object[] create(ICreateContext context) {
		 // ask user for EClass name
        String userAskedString = ExampleUtil.askString(TITLE, USER_QUESTION, "");
        if (userAskedString == null || userAskedString.trim().length() == 0) {
            return EMPTY;
        }
        
        AnalysisFunctionPrototype newAFP = Eastadl21Factory.eINSTANCE.createAnalysisFunctionPrototype();
        newAFP.setShortName(userAskedString);
		
		Assert.isTrue(getDiagram().getLink().getBusinessObjects().get(0) instanceof Dependability);		
		Dependability parentBO = (Dependability) getDiagram().getLink().getBusinessObjects().get(0);
				
		//EReference referenceId =FunctionmodelingPackage.Literals.ANALYSIS_FUNCTION_PROTOTYPE__TYPE;//DependabilityPackage.Literals.DEPENDABILITY__FUNCTIONAL_SAFETY_CONCEPT;
		EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getEAPackage_Element();
		
		String fragment = EcoreUtil.getURI(newAFP).fragment();
		if (DiagramUtil.getEObject((EObject) parentBO, fragment) != null) {
			// the object already exist (Drag & Drop)
		} else {
			DiagramUtil.addObjectToBOResource((org.eclipse.emf.ecore.EObject) parentBO,
					referenceId, (org.eclipse.emf.ecore.EObject) newAFP);
		}		
				
		addGraphicalRepresentation(context, newAFP);
    
        // return newly created business object(s)
        return new Object[] {newAFP};
	}
	
}
