package org.eclipse.eatop.connectorcreator.eadl.swcomponents;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.eatop.connectorcreator.eadl.ports.ErrorPortEATop;
import org.eclipse.eatop.connectorcreator.eadl.ports.FunctionPortEATop;
import org.eclipse.eatop.connectorcreator.ports.PortPrototypeInterface;
import org.eclipse.eatop.connectorcreator.swcomponents.SwComponentPrototypeInterface;
import org.eclipse.eatop.connectorcreator.swcomponents.SwCompositionInterface;
import org.eclipse.eatop.eastadl21.ErrorModelPrototype;
import org.eclipse.eatop.eastadl21.ErrorModelType;
import org.eclipse.eatop.eastadl21.FaultFailurePort;
import org.eclipse.eatop.eastadl21.FunctionPort;
import org.eclipse.eatop.eastadl21.FunctionPrototype;
import org.eclipse.eatop.eastadl21.FunctionType;
import org.eclipse.emf.ecore.EObject;


public class SwCompositionEATop implements SwCompositionInterface {
	EObject composition;
	
	public SwCompositionEATop(EObject eObject) {
		this.composition = eObject;
	}

	@Override
	public List<SwComponentPrototypeInterface> getPrototypes() {
		List<SwComponentPrototypeInterface> result = new ArrayList<SwComponentPrototypeInterface>();
		for (EObject element : composition.eContents()) {
			if (element instanceof FunctionPrototype) {
				FunctionPrototype prototype = (FunctionPrototype) element;
				result.add(new SwComponentPrototypeEATop(prototype));
			}
			if (element instanceof ErrorModelPrototype) {
				ErrorModelPrototype prototype = (ErrorModelPrototype) element;
				result.add(new SwComponentPrototypeEATop(prototype));
			}
		}
		
		return result;
	}

	@Override
	public List<PortPrototypeInterface> getPorts() {
		List<PortPrototypeInterface> result = new ArrayList<PortPrototypeInterface>();
		for (EObject element : composition.eContents()) {
			if (element instanceof FunctionPort) {
				FunctionPort port = (FunctionPort) element;
				result.add(new FunctionPortEATop(port));
			}
			if (element instanceof FaultFailurePort) {
				FaultFailurePort port = (FaultFailurePort) element;
				result.add(new ErrorPortEATop(port));
			}
		}
		return result;
	}

	@Override
	public String getName() {
		if (composition instanceof FunctionType) {
			FunctionType type = (FunctionType) composition;
			return type.getShortName();
		}
		if (composition instanceof ErrorModelType) {
			ErrorModelType type = (ErrorModelType) composition;
			return type.getShortName();
		}
		return "";
	}

	@Override
	public EObject getObject() {
		return composition;
	}
}
