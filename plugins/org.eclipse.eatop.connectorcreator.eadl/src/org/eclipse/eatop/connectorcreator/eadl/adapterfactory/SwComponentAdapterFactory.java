package org.eclipse.eatop.connectorcreator.eadl.adapterfactory;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.Platform;
import org.eclipse.eatop.connectorcreator.eadl.swcomponents.SwComponentPrototypeEATop;
import org.eclipse.eatop.connectorcreator.swcomponents.SwComponentPrototypeInterface;
import org.eclipse.eatop.eastadl21.FunctionPrototype;


public class SwComponentAdapterFactory implements IAdapterFactory {
	public static void register() {
		Platform.getAdapterManager().registerAdapters(new SwComponentAdapterFactory(), FunctionPrototype.class);
	}
	
	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		FunctionPrototype prototype = (FunctionPrototype) adaptableObject;
		return new SwComponentPrototypeEATop(prototype);
	}

	@Override
	public Class[] getAdapterList() {
		return new Class[] { SwComponentPrototypeInterface.class };
	}
}