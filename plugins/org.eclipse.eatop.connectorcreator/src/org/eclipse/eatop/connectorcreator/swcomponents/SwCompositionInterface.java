package org.eclipse.eatop.connectorcreator.swcomponents;

import java.util.List;

import org.eclipse.eatop.connectorcreator.ports.PortPrototypeInterface;
import org.eclipse.emf.ecore.EObject;

public interface SwCompositionInterface {
	/**
	 * @return: Returns all Software Components for the given Composition.
	 */
	List<SwComponentPrototypeInterface> getPrototypes();
	/**
	 * @return: The Ports that is contained within the given Composition.
	 */
	List<PortPrototypeInterface> getPorts();
	/**
	 * @return: The name of the Composition.
	 */
	String getName();
	/**
	 * @return: The Composition for the given CompositionInterface.
	 */
	EObject getObject();
}
