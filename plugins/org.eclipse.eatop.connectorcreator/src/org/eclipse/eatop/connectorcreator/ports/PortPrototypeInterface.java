package org.eclipse.eatop.connectorcreator.ports;

/**
 * Port representation that can be used both in EATop and Autosar.
 *
 */
public interface PortPrototypeInterface {
	public enum PortDirection {
		IN, OUT, INOUT
	}
	/**
	 * @return: The name of the given Port.
	 */
	String getName();
	/**
	 * @return: The object the Interface-implementation contains.
	 */
	Object getObject();
	/**
	 * @return: Which direction a Port has.
	 */
	PortDirection getPortDirection();
	
	@Override
	boolean equals(Object o);
}
