package org.eclipse.eatop.connectorcreator.ports;

import java.util.List;

import org.eclipse.eatop.connectorcreator.ports.PortPrototypeInterface.PortDirection;

/**
 * This class contains the information for one of the nodes that is needed to
 * create a Connector; usually a port and some sort of container that holds the
 * given port.
 * 
 */
public interface PortRepresentationInterface {
	/**
	 * This method is called upon when a port is choosen. It goes through every
	 * possible Port in the given Port's root composition and the root
	 * composition's other components and returns the Ports that the given Port
	 * can connect to via a Connector.
	 * 
	 * @return: The list that the given Port can connect to.
	 */
	List<PortRepresentationInterface> getRightList(PortDirection direction);

	/**
	 * Retrieves all Ports in the given Port's near surrounding.
	 * 
	 * @return: All ports that is contained within the given Software Component
	 *          plus all the Ports contained in this Software Component's
	 *          parent, its container.
	 */
	List<PortRepresentationInterface> getAllPorts();

	/**
	 * @return: The name of the Port in the PortRepresentation and its
	 *          direction.
	 */
	String getPortName();

	/**
	 * @return: The name of the Container, or the owner, of the Port in the
	 *          PortRepresentation.
	 */
	String getContainerName();

	/**
	 * This method adds a Connector between the given element and the element
	 * that is given as a parameter.
	 * 
	 * @param lowerElement
	 *            : The element that is to be connected to the given element.
	 */
	void addConnection(PortRepresentationInterface lowerElement);

	/**
	 * Checks whether the given PortRepresentation and the choosen element (in
	 * the menu) are already connected.
	 * 
	 * @return: True if they already are connected, false if they are not.
	 */
	boolean getIsConnected();

	/**
	 * This method deletes a connection between the element and the parameter.
	 * 
	 * @param lowerElement
	 *            : The element that will be deleted given that it is connected
	 *            to the given element.
	 */
	void deleteConnection(PortRepresentationInterface lowerElement);

	/**
	 * Check whether the given port and the parameter is connected.
	 * 
	 * @param portRepresentation
	 *            : The parameter that the given element is checked against.
	 * @return: Returns true if there is a connection and false if it's not.
	 */
	boolean isConnected(PortRepresentationInterface portRepresentation);

	/**
	 * Checks whether the given element is connected to the parameter and returns the Connector if they are.
	 * 
	 * @param portRepresentation
	 *            : The parameter that the given element is checked against.
	 * @return: The connector if there is a connection, null if there is not.
	 */
	Object getConnector(PortRepresentationInterface portRepresentation);
	
	/**
	 * @return The direction of the PortRepresentation's port.
	 */
	PortDirection getPortDirection();
}
