package org.eclipse.eatop.connectorcreator.swcomponents;

import java.util.List;

import org.eclipse.eatop.connectorcreator.ports.PortPrototypeInterface;
import org.eclipse.eatop.connectorcreator.ports.PortRepresentationInterface;

public interface SwComponentPrototypeInterface {
	/**
	 * @return: The name of the Software Component.
	 */
	String getName();
	/**
	 * @return: The Object for the given Software Component.
	 */
	Object getObject();
	/**
	 * @return: The component's composition.
	 */
	SwCompositionInterface getComposition();
	/**
	 * @return: A list of Ports that can be connected for the given Software Component.
	 */
	List<PortRepresentationInterface> getInitialList();
	/**
	 * @return: The Software Component's Ports.
	 */
	List<PortPrototypeInterface> getPorts();
	
	List<String> getErrorConnectors();
	
	@Override
	boolean equals(Object o);
}
