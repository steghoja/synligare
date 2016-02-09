package org.eclipse.eatop.app.semcon.allocationassistant.filters;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Shell;

/**
 * This is an action that is added as a {@value #actionTitle} button.
 * It uses information from a {@link org.eclipse.jface.viewers.StructuredViewer <em>StructuredViewer</em>} to create a 
 * {@link java.util.TreeMap <em>set</em>} of &ltclass, list of class attributes &gt that can be found on the viewer.
 * It opens a {@link org.eclipse.eatop.app.semcon.allocationassistant.filters.FilterDialog <em>dialog</em>} containing 
 * the this set of classes to choose from, for performing the search and filter.
 * @author Andreea Olaru
 *
 */
public class FilterAction extends Action {

	private final StructuredViewer viewer;
	private final static String actionTitle = "Search and filter";
	TreeMap<EClass, List<EStructuralFeature>> elementTypes;
	
	Object modelRoot;
	public FilterAction(StructuredViewer viewer) {
		super(actionTitle, AS_PUSH_BUTTON);
		this.viewer = viewer;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run() {
		modelRoot = ((Resource)((TreeViewer) viewer).getInput()).getAllContents().next();
		getElementTypes();
		Shell shell = new Shell();
		FilterDialog dialog = new FilterDialog(shell, viewer, elementTypes);
		dialog.open();
	}
	private void getElementTypes(){
		Comparator<EClass> eclassComparator = new Comparator<EClass>(){
				public int compare(EClass o1, EClass o2) {
					return o1.getName().compareTo(o2.getName());
				}
		};
		elementTypes = new TreeMap<EClass, List<EStructuralFeature>>(eclassComparator);
		eAllContents((EObject) modelRoot);
		
	}
	
	 /*
	  * Copyright Source AcceleoLibraryOperationVisitor.java
	  */
	private List<EObject> eAllContents(EObject source){
		  final TreeIterator<EObject> contentIterator=source.eAllContents();
		  final List<EObject> result=new ArrayList<EObject>();
		  while (contentIterator.hasNext()) {
		    final EObject next=contentIterator.next();
		    EClass key = next.eClass();
		    if (!elementTypes.containsKey(key)){
		    	List<EStructuralFeature> sfeatures = getEStructuralFeatures(next);
		    	elementTypes.put(key, sfeatures);
		    }
		    result.add(next);
		  }
		  return result;
	}
	
	/*
	 * copyright Source org.eclipse.eatop.tableview
	*/
	private List<EStructuralFeature> getEStructuralFeatures(EObject data) {
		ArrayList<EStructuralFeature> list = new ArrayList<EStructuralFeature>();
		list.addAll(data.eClass().getEAllAttributes());
		list.addAll(data.eClass().getEReferences());
				
		return list;
	}
}
