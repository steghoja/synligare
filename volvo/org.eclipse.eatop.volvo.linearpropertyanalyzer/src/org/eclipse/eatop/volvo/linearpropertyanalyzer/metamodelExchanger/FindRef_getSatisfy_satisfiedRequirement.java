package org.eclipse.eatop.volvo.linearpropertyanalyzer.metamodelExchanger;



import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sphinx.emf.explorer.BasicExplorerContentProvider;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.Satisfy;
import org.eclipse.eatop.volvo.linearpropertyanalyzer.popup.actions.Analyzer;

public class FindRef_getSatisfy_satisfiedRequirement extends
BasicExplorerContentProvider{
	
	public EList<Satisfy> getSatisfy_satisfiedRequirement(Requirement r) throws Exception {
		BasicEList<EObject> a = getAllContententsFromRoot();
		BasicEList<Satisfy> result = new BasicEList<Satisfy>();
		for (EObject item:a){
			if (item instanceof Satisfy){
				
				if (((Satisfy) item).getSatisfiedRequirement().contains(r)){
					result.add((Satisfy) item);
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
