package org.eclipse.eatop.volvo.linearpropertyanalyzer.metamodelExchanger;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.sphinx.emf.explorer.BasicExplorerContentProvider;

import org.eclipse.eatop.eastadl21.Mode;
import org.eclipse.eatop.eastadl21.ModeGroup;
public class FindRef_getModeGroup_mode extends
BasicExplorerContentProvider{
	public FindRef_getModeGroup_mode(){
		super();
	}
	
	public EObject getModeGroup_mode(Mode m){
		// find their parent node
		
		
		AdapterFactoryContentProvider contentProvider = getModelContentProvider(m);
		if (contentProvider != null) {
			Object tempParent = contentProvider.getParent(m);
			if ((EObject)tempParent instanceof ModeGroup)
				return (EObject)tempParent;
		}
		
		
		
		return null;
	}
	
	
}
