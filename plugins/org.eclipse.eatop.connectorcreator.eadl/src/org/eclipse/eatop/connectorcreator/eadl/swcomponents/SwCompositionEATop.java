package org.eclipse.eatop.connectorcreator.eadl.swcomponents;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.eatop.connectorcreator.eadl.ports.PortPrototypeEATop;
import org.eclipse.eatop.connectorcreator.ports.PortPrototypeInterface;
import org.eclipse.eatop.connectorcreator.swcomponents.SwComponentPrototypeInterface;
import org.eclipse.eatop.connectorcreator.swcomponents.SwCompositionInterface;
import org.eclipse.eatop.eastadl21.AnalysisFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignFunctionType;
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
		}
		
		return result;
	}

	@Override
	public List<PortPrototypeInterface> getPorts() {
		List<PortPrototypeInterface> result = new ArrayList<PortPrototypeInterface>();
		for (EObject element : composition.eContents()) {
			if (element instanceof FunctionPort) {
				FunctionPort port = (FunctionPort) element;
				result.add(new PortPrototypeEATop(port));
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
		return "";
	}

	@Override
	public EObject getObject() {
		return composition;
	}
}
