package org.eclipse.eatop.volvo.linearpropertyanalyzer.analysis.helpers; 

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTree;

import org.eclipse.eatop.eastadl21.GenericConstraint;
import org.eclipse.eatop.eastadl21.EAElement;

/**
 * This class will be used to save the result of finding all the elements that is relevant to a analysis. The result is a List with the 
 * matched EAElements and a JTree that could be used to display the elements in a treeview
 * @author Erik
 *
 */
public class SearchResult {
	Map<GenericConstraint,List<EAElement>> resultMapFindEleBySatisfy = new HashMap<GenericConstraint,List<EAElement>>();
	Map<GenericConstraint,JTree> resultTreeMapFindEleBySatisfy = new HashMap<GenericConstraint,JTree>();
	
	/** 
	 * Creates a new instance with the two maps as parameters
	 * @param resultMapFindEleBySatisfy
	 * @param resultTreeMapFindEleBySatisfy
	 */
	public SearchResult(
			Map<GenericConstraint, List<EAElement>> resultMapFindEleBySatisfy,
			Map<GenericConstraint, JTree> resultTreeMapFindEleBySatisfy) {
		super();
		this.resultMapFindEleBySatisfy = resultMapFindEleBySatisfy;
		this.resultTreeMapFindEleBySatisfy = resultTreeMapFindEleBySatisfy;
	}
	
	/** 
	 * This method will return the map that contains a GenericConstraintKind together with a List<EAElement>
	 * @return
	 */
	public Map<GenericConstraint, List<EAElement>> getResultMapFindEleBySatisfy() {
		return resultMapFindEleBySatisfy;
	}
	
	/**
	 * This method will set the map that contains a GenericConstraintKind together with a List<EAElement>
	 * @param resultMapFindEleBySatisfy
	 */
	public void setResultMapFindEleBySatisfy(
			Map<GenericConstraint, List<EAElement>> resultMapFindEleBySatisfy) {
		this.resultMapFindEleBySatisfy = resultMapFindEleBySatisfy;
	}
	/**
	 * This method will return the map that contains a GenericConstraintKind together with a JTree
	 * @return
	 */
	public Map<GenericConstraint, JTree> getResultTreeMapFindEleBySatisfy() {
		return resultTreeMapFindEleBySatisfy;
	}
	
	/**
	 * This method will set the map that contains a GenericConstraintKind together with a JTree
	 * @param resultTreeMapFindEleBySatisfy
	 */
	public void setResultTreeMapFindEleBySatisfy(
			Map<GenericConstraint, JTree> resultTreeMapFindEleBySatisfy) {
		this.resultTreeMapFindEleBySatisfy = resultTreeMapFindEleBySatisfy;
	}
	
	
}
