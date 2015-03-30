package org.eclipse.eatop.examples.tableview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.eatop.examples.tableview.accessorfactories.IEObjectAccessorFactory;
import org.eclipse.eatop.examples.tableview.accessors.IEObjectAccessor;
import org.eclipse.emf.ecore.EObject;

public class AccessorManager {

	public static final AccessorManager INSTANCE = new AccessorManager();
	
	private List<IEObjectAccessorFactory> factories = new ArrayList<IEObjectAccessorFactory>();
	
	private AccessorManager() {
		
		IConfigurationElement[] configurationElements = Platform.getExtensionRegistry().getConfigurationElementsFor("org.eclipse.eatop.examples.tableview.accessorfactory");
	
		final HashMap<IEObjectAccessorFactory, String> priorities = new HashMap<IEObjectAccessorFactory, String>();
		
		for (IConfigurationElement element : configurationElements) {
			if ("accessorfactory".equals(element.getName())) {
				try {
					Object extension = element.createExecutableExtension("class");
					if (extension instanceof IEObjectAccessorFactory) {
						IEObjectAccessorFactory factory = (IEObjectAccessorFactory) extension;
						String priority = element.getAttribute("priority");
						
						factories.add(factory);
						priorities.put(factory, priority);
						
					}
				} catch (CoreException e) {
					e.printStackTrace();
				}
				
			}
		}
		
		Collections.sort(factories, new Comparator<IEObjectAccessorFactory>() {
			@Override public int compare(IEObjectAccessorFactory o1, IEObjectAccessorFactory o2) {
				String p1 = priorities.get(o1);
				String p2 = priorities.get(o2);
				int val;
				
				if(p1 == null) val = 1;
				else if(p2 == null) val = -1;
				else if (p1.equals(p2)) val = 0;
				else                val = p1.compareTo(p2);
				
				return val;
			}

		});
	}
	
	
	public IEObjectAccessor createAccessor(List<? extends EObject> data) {
		
		for (IEObjectAccessorFactory factory : factories) {
			if (factory.canCreate(data)) {
				return factory.create(data);
			}
		}
		
		return null;
		
	}



}
