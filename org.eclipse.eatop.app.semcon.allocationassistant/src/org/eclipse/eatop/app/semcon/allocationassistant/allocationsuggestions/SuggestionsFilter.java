package org.eclipse.eatop.app.semcon.allocationassistant.allocationsuggestions;

import java.util.ArrayList;

import org.eclipse.eatop.eastadl21.Identifiable;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * This filter keeps only the elements given as suggestions.
 * @author Andreea Olaru
 * 
 */
public class SuggestionsFilter extends ViewerFilter {
	
	/*
	 * A set of elements that are children nodes of a selected node.
	 * These should be kept in the selection after filtering.
	 */
	ArrayList<Identifiable> suggestedElements = new ArrayList<Identifiable>();
	
	public SuggestionsFilter(ArrayList<Identifiable> modelElementsHigher) {
		super();
		this.suggestedElements = modelElementsHigher;
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {

			if (suggestedElements.contains(element)){
				return true;
			}else {
			      StructuredViewer sviewer = (StructuredViewer) viewer;
			      ITreeContentProvider provider = (ITreeContentProvider) sviewer.getContentProvider();
			      for (Object child: provider.getChildren(element)){
			    	  if (select(viewer, element, child))
			          return true;
			      }
		    return false;
			}
	}

}
