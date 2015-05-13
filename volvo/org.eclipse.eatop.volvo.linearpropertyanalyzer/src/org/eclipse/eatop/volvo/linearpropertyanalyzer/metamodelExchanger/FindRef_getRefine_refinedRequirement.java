package org.eclipse.eatop.volvo.linearpropertyanalyzer.metamodelExchanger;



import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sphinx.emf.explorer.BasicExplorerContentProvider;
import org.eclipse.eatop.eastadl21.Refine;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.volvo.linearpropertyanalyzer.popup.actions.Analyzer;

public class FindRef_getRefine_refinedRequirement extends
BasicExplorerContentProvider{
	
	public EList<Refine> getRefine_refinedRequirement(Requirement r) throws Exception {
		BasicEList<EObject> a = getAllContententsFromRoot();
		BasicEList<Refine> result = new BasicEList<Refine>();
		for (EObject item:a){
			if (item instanceof Refine){
				
				if (((Refine) item).getRefinedRequirement().contains(r)){
					result.add((Refine) item);
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
