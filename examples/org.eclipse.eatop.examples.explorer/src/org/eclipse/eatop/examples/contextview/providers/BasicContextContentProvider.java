package org.eclipse.eatop.examples.contextview.providers;

import org.eclipse.jface.viewers.Viewer;

/* This is a basic implementation of the context content provider. Sane default
 * implementations so that it may be convenient to derive from this class instead
 * of implementing IContextProvider directly
 */
public class BasicContextContentProvider implements IContextProvider {

	protected Object input;
	protected boolean disablesInstanceReferencedBy;

	@Override
	public Object[] getElements(Object inputElement) {
		return new Object[] {};
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		return null;
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return getChildren(element).length > 0;
	}

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		input = newInput;
	}

	@Override
	public boolean disablesGenericReferences() {
		return false;
	}

	@Override
	public boolean disablesGenericReferencedBy() {
		return false;
	}

	@Override
	public boolean disablesGenericInstanceReferencedBy() {
		return disablesInstanceReferencedBy;
	}

	@Override
	public String getNameForReferences() {
		return "References"; //$NON-NLS-1$
	}

	@Override
	public String getNameForReferencedBy() {
		return "Referenced by"; //$NON-NLS-1$
	}

	@Override
	public String getNameForInstanceReferencedBy() {
		return "Instance referenced by"; //$NON-NLS-1$
	}

	@Override
	public void setDisablesGenericInstanceReferencedBy(boolean disablesInstRefs) {
		disablesInstanceReferencedBy = disablesInstRefs;
	}

}
