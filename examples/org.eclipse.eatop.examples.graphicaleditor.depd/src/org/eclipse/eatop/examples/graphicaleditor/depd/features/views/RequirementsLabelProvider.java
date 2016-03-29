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
package org.eclipse.eatop.examples.graphicaleditor.depd.features.views;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.eatop.eastadl21.AnalysisFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.eatop.eastadl21.Identifiable;
import org.eclipse.eatop.eastadl21.QualityRequirement;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.SafetyGoal;
import org.eclipse.eatop.eastadl21.Satisfy;
import org.eclipse.eatop.examples.graphicaleditor.depd.provider.DEPDImageProvider;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

public class RequirementsLabelProvider extends StyledCellLabelProvider  {
	SafetyGoal safetyGoal;
	public RequirementsLabelProvider(SafetyGoal sg) {
		safetyGoal=sg;
	}

	@Override
	public void update(ViewerCell cell) {
		 Object element = cell.getElement();
		    StyledString text = new StyledString();
		    if (element instanceof SafetyGoal){
		    	
		    	  SafetyGoal safetyGoal = (SafetyGoal) element;
		    	  text.append(safetyGoal.getShortName()+"["+((EObject) safetyGoal).eClass().getName()+"]");
		    	  cell.setImage(GraphitiUi.getImageService().getPlatformImageForId(DEPDImageProvider.IMAGE_SAFETY_GOAL));
		    	  text.append(" ("+safetyGoal.getRequirement().size() +") ", StyledString.COUNTER_STYLER);
		    } if(element instanceof QualityRequirement){
		    	QualityRequirement qr=(QualityRequirement) element;
		    	text.append(qr.getShortName()+"["+((EObject) qr).eClass().getName()+"]");
		    	cell.setImage(GraphitiUi.getImageService().getPlatformImageForId(DEPDImageProvider.IMAGE_QUALITY_REQUIREMENT));
		    }
		    List<Requirement>reqs=new ArrayList<Requirement>();
		    
		     if(element instanceof Requirement&&!(element instanceof QualityRequirement)){ 
		    	 Requirement requirement=(Requirement) element;
		    	 reqs =getAllRequirementsInSatisfies(safetyGoal);
		    	 if(reqs.isEmpty()){
		    		 cell.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_RED)); 
		    	
		    	 }
		    	 for(Requirement req:reqs){
		    		 if(req.equals(requirement)){
		    			
		       cell.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GREEN));
		       break;
		    		 }
		    		 else{
		    			 cell.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));                 
		    		 }
		    	 }
		    	  
		    	 text.append(requirement.getShortName()+"["+((EObject) requirement).eClass().getName()+"]");
	    		 cell.setImage( GraphitiUi.getImageService().getPlatformImageForId(DEPDImageProvider.IMAGE_REQUIREMENT)); 
	    		
		    	
		    } if (element instanceof Identifiable) {
		    	
		    	 if(element instanceof AnalysisFunctionPrototype){
		    		 text.append(((Identifiable) element).getShortName()+"["+((EObject) element).eClass().getName()+"]");
		    		 cell.setImage(GraphitiUi.getImageService().getPlatformImageForId(DEPDImageProvider.IMAGE_ANALYSIS_FUNCTION_PROTOTYPE));
		   		  }if(element instanceof DesignFunctionPrototype){
		   			text.append(((Identifiable) element).getShortName()+"["+((EObject) element).eClass().getName()+"]");
		   			cell.setImage( GraphitiUi.getImageService().getPlatformImageForId(DEPDImageProvider.IMAGE_DESIGN_FUNCTION_PROTOTYPE));
		   		 
	   		  }
				  }
		    
		   cell.setText(text.toString());
		    cell.setStyleRanges(text.getStyleRanges());
		super.update(cell);
	}



//	@Override
//	public String getText(Object element) {
//	  if (element instanceof SafetyGoal) {
//		  SafetyGoal safetyGoal = (SafetyGoal) element;
//	    return safetyGoal.getShortName()+"["+safetyGoal.eClass().getName()+"]";
//	  }
//	  if (element instanceof QualityRequirement) {
//		  return ((QualityRequirement) element).getShortName()+"["+((QualityRequirement) element).eClass().getName()+"]";
//	  }
//	
//	  List<Requirement>reqs=new ArrayList<Requirement>();
//	  if (element instanceof Requirement) {
//		  Requirement req=(Requirement) element;
//		  reqs =getAllRequirementsInSatisfies(safetyGoal);
//		
//		  for(Requirement requirement:reqs){
//			 
//			  if(requirement.getShortName().equals(req.getShortName())){
//				  return req.getShortName()+"["+req.eClass().getName()+"]";
//			  }
//		  }
//		  return ((Requirement) element).getShortName()+"["+((Requirement) element).eClass().getName()+"]"+"-> not satisfied!";
//				  }
//	  
//	  if (element instanceof Identifiable) {
//		  return ((Identifiable) element).getShortName()+"["+((Identifiable) element).eClass().getName()+"]";
//				  }
//	  else{
//	  return null;
//	  }
//	}
//
//	@Override
//	public Image getImage(Object element) {
//	  if (element instanceof SafetyGoal) {
//		  return GraphitiUi.getImageService().getImageForId(DEPDImageProvider.IMAGE_SAFETY_GOAL);
//	  } else if (element instanceof QualityRequirement) {
//		  return GraphitiUi.getImageService().getImageForId(DEPDImageProvider.IMAGE_QUALITY_REQUIREMENT);  
//	  }else if (element instanceof Requirement) {
//		  return GraphitiUi.getImageService().getImageForId(DEPDImageProvider.IMAGE_REQUIREMENT);  
//	  }else if (element instanceof Identifiable) {
//		  if(element instanceof AnalysisFunctionPrototype){
//		  return GraphitiUi.getImageService().getImageForId(DEPDImageProvider.IMAGE_ANALYSIS_FUNCTION_PROTOTYPE);
//		  }if(element instanceof DesignFunctionPrototype){
//			  return GraphitiUi.getImageService().getImageForId(DEPDImageProvider.IMAGE_DESIGN_FUNCTION_PROTOTYPE);
//		  
//		  }
//		  
//	  }
//	  
//	  return GraphitiUi.getImageService().getImageForId(DEPDImageProvider.IMAGE_REQUIREMENT);
//	}
	
		
		//----------------------getAllRequirementInSatisfies() start-------------------------------
		public List<Satisfy> getSatisfys(SafetyGoal safetyGoal){
			List<Satisfy>result=new LinkedList<Satisfy>();
			EAXML root=(EAXML) EcoreUtil.getRootContainer((EObject) safetyGoal);
			TreeIterator<EObject>iter=root.eAllContents();
			while (iter.hasNext()) {
				EObject element=iter.next();
				if(element instanceof Satisfy){
					Satisfy s=(Satisfy) element;
					result.add(s);
					}
			}
			return result;
			
			}
		public List<Requirement> getAllRequirementsInSatisfies(SafetyGoal safetyGoal){
			List<Requirement>reqrs=new ArrayList<Requirement>();
			List<Satisfy>ss=getSatisfys(safetyGoal);
			List<Requirement>satisfiedReqs=new ArrayList<Requirement>();
			for(Satisfy s:ss){
				satisfiedReqs=s.getSatisfiedRequirement();
			
				reqrs.addAll(satisfiedReqs);
				
			}
			return reqrs;
		}
		
}
