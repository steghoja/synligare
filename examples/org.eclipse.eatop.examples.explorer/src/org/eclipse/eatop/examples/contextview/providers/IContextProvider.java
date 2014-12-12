package org.eclipse.eatop.examples.contextview.providers;

import org.eclipse.jface.viewers.ITreeContentProvider;

public interface IContextProvider extends ITreeContentProvider {
	
	public boolean disablesGenericReferences();
	public boolean disablesGenericReferencedBy();
	public boolean disablesGenericInstanceReferencedBy();
	
	public void setDisablesGenericInstanceReferencedBy(boolean disablesInstRefs);
	
	public String getNameForReferences();
	public String getNameForReferencedBy();
	public String getNameForInstanceReferencedBy();

}
