package org.eclipse.eatop.tableview.accessorfactories;

import java.util.List;

import org.eclipse.eatop.tableview.accessors.IEObjectAccessor;
import org.eclipse.emf.ecore.EObject;

public interface IEObjectAccessorFactory {

	public IEObjectAccessor create(List<? extends EObject> data);
	public boolean canCreate(List<? extends EObject> data);
	
}
