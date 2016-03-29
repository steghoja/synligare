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

import org.eclipse.eatop.eastadl21.DeriveRequirement;
import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.eatop.eastadl21.Identifiable;
import org.eclipse.eatop.eastadl21.QualityRequirement;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.SafetyGoal;
import org.eclipse.eatop.eastadl21.Satisfy;
import org.eclipse.eatop.eastadl21.Satisfy_satisfiedBy;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;



public class RequirementsContentProvider  implements ITreeContentProvider{
	SafetyGoal safetyGoal;
	 //List<Requirement> derivedReq;
	 //List<Identifiable> analysisfps;
	public RequirementsContentProvider(SafetyGoal sg){
		safetyGoal=sg;
	}
	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		
	}


	
	@Override
	public Object[] getElements(Object inputElement) {

	 return (Object[]) inputElement;
	}
	
	//-----------------------------------getDerivedRequirements From QualityRequirement start-----------------
	public  List<DeriveRequirement> getDerivedRequirementsLink(SafetyGoal safetyGoal) {
		List<DeriveRequirement>result=new LinkedList<DeriveRequirement>();
		EAXML root=(EAXML) EcoreUtil.getRootContainer(safetyGoal);
		TreeIterator<EObject>iter=root.eAllContents();
		while (iter.hasNext()) {

	EObject element=iter.next();
	if(element instanceof DeriveRequirement){
		DeriveRequirement dr=(DeriveRequirement) element;
		result.add(dr);
	}
			
		}
		return result;
	}
	
	public List<Requirement> getDRequirements(SafetyGoal sg,QualityRequirement qr){
		Object[]drs = getDerivedRequirementsLink(sg).toArray();
		List<Requirement>  list1 =new ArrayList<Requirement>();
		
			for(int i=0;i<drs.length;i++){
				
			for(int j=0;j<((DeriveRequirement) drs[i]).getDerivedFrom().toArray().length;j++){
				if(qr==((DeriveRequirement) drs[i]).getDerivedFrom().get(j)){
					list1.addAll(((DeriveRequirement) drs[i]).getDerived());
					
				}
				
			}
			}	
			
			return list1;
		
	}
	//-----------------------------------getDerivedRequirements From QualityRequirement end-----------------
	
	//----------------------getAnalysisFunctionPrototypes() start---------------------
	public List<Satisfy> getSatisfys(SafetyGoal safetyGoal){
		List<Satisfy>result=new LinkedList<Satisfy>();
		EAXML root=(EAXML) EcoreUtil.getRootContainer(safetyGoal);
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
	public List<Satisfy_satisfiedBy>getSatisfy_satisfiedBys(SafetyGoal sg,Requirement derivedRequirement){
		Object[]ss=getSatisfys(sg).toArray();
		List<Satisfy_satisfiedBy>list2=new ArrayList<Satisfy_satisfiedBy>();
		
			for(int i=0;i<ss.length;i++){
				for(int j=0;j<((Satisfy) ss[i]).getSatisfiedRequirement().toArray().length;j++){
					if(((Requirement) derivedRequirement)==((Satisfy) ss[i]).getSatisfiedRequirement().get(j)){
						
						List<Satisfy_satisfiedBy> list1 = ((Satisfy) ss[i]).getSatisfiedBy();
						list2.addAll(list1);
					}
				}
				
			
		}
		return list2;
	}
	public List<Identifiable>getAnalysisFunctionPrototypes(SafetyGoal sg,Requirement derivedRequirement){
		List<Identifiable>afps2=new ArrayList<Identifiable>();
		List<Satisfy_satisfiedBy>ssBys=getSatisfy_satisfiedBys(sg,derivedRequirement);
		for(int i=0;i<ssBys.toArray().length;i++){
			List<Identifiable>afps1=ssBys.get(i).getIdentifiable_context();
			afps2.addAll(afps1);
		}
		return afps2;
	}
	//----------------------getAnalysisFunctionPrototypes() end---------------------
	//-----------------------------------getDerivedRequirements From DerivedRequirement start-----------------
	public List<Requirement> getDerivedRequirementsFromDerived(SafetyGoal sg,Requirement req){
		Object[]drs = getDerivedRequirementsLink(sg).toArray();
		List<Requirement>  list1 =new ArrayList<Requirement>();
		
			for(int i=0;i<drs.length;i++){
				
			for(int j=0;j<((DeriveRequirement) drs[i]).getDerivedFrom().toArray().length;j++){
				if(req==((DeriveRequirement) drs[i]).getDerivedFrom().get(j)){
					list1.addAll(((DeriveRequirement) drs[i]).getDerived());
					
				}
				
			}
			}	
			
			return list1;
		
	}
	//-----------------------------------getDerivedRequirements From DerivedRequirement end-----------------
	
	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof SafetyGoal) {
			SafetyGoal safetyFGoal = (SafetyGoal) parentElement;
			return safetyFGoal.getRequirement().toArray();
		}
		if (parentElement instanceof QualityRequirement) {
			List<Requirement> derivedReq = new ArrayList<Requirement>();
			for (int i = 0; i < safetyGoal.getRequirement().toArray().length; i++) {
				Requirement req1 = safetyGoal.getRequirement().get(i);
				req1 = (QualityRequirement) parentElement;
				derivedReq = getDRequirements(safetyGoal,
						(QualityRequirement) req1);
				return derivedReq.toArray();
			}
		}

		List<Requirement> derivedReq = new ArrayList<Requirement>();
		List<Requirement> derivedReqFromDerived = new ArrayList<Requirement>();
		List<EObject> obs = new ArrayList<EObject>();
		List<Identifiable> afps = new ArrayList<Identifiable>();

		for (int i = 0; i < safetyGoal.getRequirement().toArray().length; i++) {
			Requirement req1 = safetyGoal.getRequirement().get(i);
			derivedReq = getDRequirements(safetyGoal, (QualityRequirement) req1);

			for (int j = 0; j < derivedReq.toArray().length; j++) {
				if (parentElement instanceof Requirement) {
					Requirement req2 = derivedReq.get(j);

					req2 = (Requirement) parentElement;

					afps = getAnalysisFunctionPrototypes(safetyGoal, req2);
					derivedReqFromDerived = getDerivedRequirementsFromDerived(
							safetyGoal, req2);
					obs.addAll(afps);
					obs.addAll(derivedReqFromDerived);

					return obs.toArray();
				}
			}
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof SafetyGoal) {
			return true;
		}
		if (element instanceof QualityRequirement) {
			return true;
		}
		if (element instanceof Requirement) {
			return true;
		}

		return false;
	}
}
