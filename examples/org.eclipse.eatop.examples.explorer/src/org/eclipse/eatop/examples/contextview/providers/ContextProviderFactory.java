package org.eclipse.eatop.examples.contextview.providers;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.eatop.eastadl21.FaultFailurePort;

public class ContextProviderFactory implements IAdapterFactory {

	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adapterType == IContextProvider.class) {
			if (adaptableObject instanceof FaultFailurePort) {
				return new ASILLevelContentProvider();
			}
		}
		return null;
	}

	@Override
	public Class[] getAdapterList() {
		return new Class[] { IContextProvider.class };
	}

}
