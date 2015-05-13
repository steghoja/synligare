package org.eclipse.eatop.volvo.linearpropertyanalyzer.metamodelExchanger;



import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sphinx.emf.explorer.BasicExplorerContentProvider;
import org.eclipse.eatop.eastadl21.GenericConstraint;
import org.eclipse.eatop.eastadl21.EAElement;
import org.eclipse.eatop.volvo.linearpropertyanalyzer.popup.actions.Analyzer;

public class FindRef_getGenericConstraint_target extends
BasicExplorerContentProvider{
	
	public EList<GenericConstraint> getGenericConstraint_target(EAElement r) throws Exception {
		BasicEList<EObject> a = getAllContententsFromRoot();
		BasicEList<GenericConstraint> result = new BasicEList<GenericConstraint>();
		for (EObject item:a){
			if (item instanceof GenericConstraint){
				
				if (((GenericConstraint) item).getTarget().contains(r)){
					result.add((GenericConstraint) item);
				}
			}
		}
		
		return result;
	}
	
	public BasicEList<EObject> getAllContententsFromRoot() throws Exception {
		ArrayList<EObject> tmpResult = new ArrayList<EObject>();
		Iterator<EObject> rootIt = Analyzer.rootEAXML.eAllContents();
		while(rootIt.hasNext()){
			tmpResult.add(rootIt.next());
		}
		if(tmpResult.isEmpty()){
			throw new Exception("No elements in the root");
		}
		return new BasicEList(tmpResult);
	}
	
}
