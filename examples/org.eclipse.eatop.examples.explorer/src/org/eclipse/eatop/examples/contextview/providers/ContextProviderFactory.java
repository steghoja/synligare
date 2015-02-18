package org.eclipse.eatop.examples.contextview.providers;

import org.eclipse.core.runtime.IAdapterFactory;

public class ContextProviderFactory implements IAdapterFactory {

	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adapterType == IContextProvider.class) {
			/* Contribute special context content providers here */
		}
		return null;
	}

	@Override
	public Class[] getAdapterList() {
		return new Class[] { IContextProvider.class };
	}

}
