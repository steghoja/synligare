package org.eclipse.eatop.examples.explorer;

import org.eclipse.emf.ecore.EObject;

public interface IEObjectContainer {

	public Iterable<EObject> getObjects();
}
