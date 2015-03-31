package org.eclipse.eatop.connectorcreator.eadl.adapterfactory;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.Platform;
import org.eclipse.eatop.connectorcreator.eadl.swcomponents.SwComponentPrototypeEATop;
import org.eclipse.eatop.connectorcreator.swcomponents.SwComponentPrototypeInterface;
import org.eclipse.eatop.eastadl21.EAPrototype;
import org.eclipse.eatop.eastadl21.FunctionPrototype;


public class SwComponentAdapterFactory implements IAdapterFactory {
	public static void register() {
		Platform.getAdapterManager().registerAdapters(new SwComponentAdapterFactory(), FunctionPrototype.class);
	}
	
	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		EAPrototype prototype = (EAPrototype) adaptableObject;
		return new SwComponentPrototypeEATop(prototype);
	}

	@Override
	public Class[] getAdapterList() {
		List<Class> list = new ArrayList();
		list.add(SwComponentPrototypeInterface.class);
		return (Class[]) list.toArray();
	}
}
