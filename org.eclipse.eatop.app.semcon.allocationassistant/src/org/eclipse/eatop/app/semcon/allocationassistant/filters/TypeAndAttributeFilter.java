package org.eclipse.eatop.app.semcon.allocationassistant.filters;

import java.util.HashSet;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * This filter is used by a {@link org.eclipse.jface.viewers.StructuredViewer <em>StructuredViewer</em>} to keep just elements that are of <b>selectedClass</b>
 * and contain in a certain <b>attribute</b> a chosen <b>value</b>.
 * @author Andreea Olaru
 * @see {@link org.eclipse.eatop.app.semcon.allocationassistant.filters.TypeAndExcludeByAttribureFilter <em>TypeAndExcludeByAttribureFilter</em>}
 */
public class TypeAndAttributeFilter extends ViewerFilter {
	
	/*
	 * A set of elements that are children nodes of a selected node.
	 * These should be kept in the selection after filtering.
	 */
	HashSet<Object> elementsToInclude = new HashSet<Object>();
	
	/*
	 *The class that was selected as criteria for filtering.
	 *All the tree branches that include elements of 'selectedClass' will be shown after filtering
	 */
	EClass selectedClass;
	
	
	//The class attribute that was selected as criteria for filtering.
	EStructuralFeature attribute;
	
	//The attribute value that was written in the textbox as criteria for filtering.
	String value;
	
	public TypeAndAttributeFilter(EClass selectedClass, EStructuralFeature attribute, String value) {
		super();
		this.selectedClass = selectedClass;
		this.attribute =  attribute;
		this.value = value.toLowerCase();
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
			TreeIterator<EObject> currentSubElement;
			if (isSelectedElement(element)){
				currentSubElement = ((EObject)element).eAllContents();
				while (currentSubElement.hasNext()){
					elementsToInclude.add((Object)currentSubElement.next());
			    }
				return true;
			}else if(elementsToInclude.contains(element)){
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
	
	/**
	 * Returns <i>true</i> if the <b>element</b> is an instance of the <b>selectedClass</b> and if its <b>attribute</b>
	 * contains all the words from <b>value</b>. The comparison is not case-sensitive.
	 * If the attribute is null or the value is null, then it returns true just based on the class.
	 * <p>It returns  <i>false</i> otherwise.
	 */
	private boolean isSelectedElement(Object element){
		
		if (selectedClass.isInstance(element) ) {
			if (attribute != null){
				String elementAttributeValue = ((EObject)element).eGet(attribute).toString().trim().toLowerCase();
				String[] keyWords = value.split(" ");
				Boolean contains = true;
				for (int i=0; i<keyWords.length;i++){
					if (!(elementAttributeValue.contains(keyWords[i].trim())))
						contains = false;
				}
				return contains;
			}else
			return true;
			
		}else
			return false;
	}

}
