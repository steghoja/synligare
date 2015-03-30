package org.eclipse.eatop.examples.tableview.ui;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.eatop.eastadl21.ClampConnector_port;
import org.eclipse.eatop.eastadl21.EAArrayValue;
import org.eclipse.eatop.eastadl21.EABooleanValue;
import org.eclipse.eatop.eastadl21.EACompositeValue;
import org.eclipse.eatop.eastadl21.EAEnumerationValue;
import org.eclipse.eatop.eastadl21.EANumericalValue;
import org.eclipse.eatop.eastadl21.EAStringValue;
import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.eatop.eastadl21.ErrorModelPrototype_functionTarget;
import org.eclipse.eatop.eastadl21.ErrorModelPrototype_hwTarget;
import org.eclipse.eatop.eastadl21.FaultFailurePort_functionTarget;
import org.eclipse.eatop.eastadl21.FaultFailurePort_hwTarget;
import org.eclipse.eatop.eastadl21.FaultFailurePropagationLink_fromPort;
import org.eclipse.eatop.eastadl21.FaultFailurePropagationLink_toPort;
import org.eclipse.eatop.eastadl21.FunctionAllocation_allocatedElement;
import org.eclipse.eatop.eastadl21.FunctionAllocation_target;
import org.eclipse.eatop.eastadl21.FunctionConnector_port;
import org.eclipse.eatop.eastadl21.HardwareConnector_port;
import org.eclipse.eatop.eastadl21.HardwarePortConnector_port;
import org.eclipse.eatop.eastadl21.Realization_realizedBy;
import org.eclipse.eatop.eastadl21.Refine_refinedBy;
import org.eclipse.eatop.eastadl21.Satisfy_satisfiedBy;
import org.eclipse.eatop.examples.tableview.TableViewHelpers;
import org.eclipse.eatop.geastadl.ginfrastructure.gelements.GReferrable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.nebula.widgets.nattable.data.convert.DisplayConverter;

public class GEatopDisplayConverter extends DisplayConverter {

	@Override
	public Object canonicalToDisplayValue(Object canonicalValue) {
		if (canonicalValue instanceof Collection<?>) {
			Collection<?> canonicalCollection = (Collection<?>) canonicalValue;
			ArrayList<Object> displayCollection = new ArrayList<Object>();
			for (Object canonicalElement : canonicalCollection) {
				displayCollection.add(canonicalToDisplayValue(canonicalElement));
			}
			return getConvertedMultiLine(displayCollection);
			
		} else if (canonicalValue instanceof GReferrable) {
			GReferrable canonicalReferrable = (GReferrable) canonicalValue;
			return canonicalReferrable.gGetShortName();
			
		} else if (canonicalValue instanceof EAArrayValue) {
			EAArrayValue value = (EAArrayValue) canonicalValue;
			return canonicalToDisplayValue(value.getValue());
			
		} else if (canonicalValue instanceof EABooleanValue) {
			EABooleanValue value = (EABooleanValue) canonicalValue;
			return value.getValue();
			
		} else if (canonicalValue instanceof EACompositeValue) {
			EACompositeValue value = (EACompositeValue) canonicalValue;
			return canonicalToDisplayValue(value.getValue());
			
		} else if (canonicalValue instanceof EAEnumerationValue) {
			EAEnumerationValue value = (EAEnumerationValue) canonicalValue;
			return canonicalToDisplayValue(value.getValue());
				
		} else if (canonicalValue instanceof EANumericalValue) {
			EANumericalValue value = (EANumericalValue) canonicalValue;
			return value.getValue();
			
		} else if (canonicalValue instanceof EAStringValue) {
			EAStringValue value = (EAStringValue) canonicalValue;
			return value.getValue();
			
		} else if (canonicalValue instanceof FunctionAllocation_target) {
			return getPathTo(((FunctionAllocation_target) canonicalValue).getAllocationTarget());
			
		} else if (canonicalValue instanceof FunctionAllocation_allocatedElement) {
			return getPathTo(((FunctionAllocation_allocatedElement) canonicalValue).getAllocateableElement());
			
		} else if (canonicalValue instanceof ErrorModelPrototype_functionTarget) {
			return getPathTo(((ErrorModelPrototype_functionTarget) canonicalValue).getFunctionPrototype());
			
		} else if (canonicalValue instanceof ErrorModelPrototype_hwTarget) {
			return getPathTo(((ErrorModelPrototype_hwTarget) canonicalValue).getHardwareComponentPrototype());
			
		} else if (canonicalValue instanceof FaultFailurePort_functionTarget) {
			return getPathTo(((FaultFailurePort_functionTarget) canonicalValue).getFunctionPort());
			
		} else if (canonicalValue instanceof FaultFailurePort_hwTarget) {
			return getPathTo(((FaultFailurePort_hwTarget) canonicalValue).getHardwarePort());
			
		} else if (canonicalValue instanceof ClampConnector_port) {
			return getPathTo(((ClampConnector_port) canonicalValue).getFunctionPort());
			
		} else if (canonicalValue instanceof FunctionConnector_port) {
			return getPathTo(((FunctionConnector_port) canonicalValue).getFunctionPort());
			
		} else if (canonicalValue instanceof FaultFailurePropagationLink_fromPort) {
			return getPathTo(((FaultFailurePropagationLink_fromPort) canonicalValue).getFaultFailurePort());
			
		} else if (canonicalValue instanceof FaultFailurePropagationLink_toPort) {
			return getPathTo(((FaultFailurePropagationLink_toPort) canonicalValue).getFaultFailurePort());
			
		} else if (canonicalValue instanceof HardwarePortConnector_port) {
			return getPathTo(((HardwarePortConnector_port) canonicalValue).getHardwareComponentPrototype());
			
		} else if (canonicalValue instanceof HardwareConnector_port) {
			return getPathTo(((HardwareConnector_port) canonicalValue).getHardwareComponentPrototype());
			
		} else if (canonicalValue instanceof Satisfy_satisfiedBy) {
			return getPathTo(((Satisfy_satisfiedBy) canonicalValue).getIdentifiable_target());
		
		} else if (canonicalValue instanceof Realization_realizedBy) {
			return getPathTo(((Realization_realizedBy) canonicalValue).getIdentifiable_target());

		} else if (canonicalValue instanceof Refine_refinedBy) {
			return getPathTo(((Refine_refinedBy) canonicalValue).getIdentifiable_target());
		
		} else if (canonicalValue != null){
		 	return canonicalValue.toString();
		
		} else {
			return "";
		}
		
	}
	
	private String getPathTo(EObject eo) {
		StringBuilder sb = new StringBuilder("");
		if (eo == null) return sb.toString();
		Object canonicalToDisplayValue = canonicalToDisplayValue(eo);
		if (canonicalToDisplayValue instanceof String) {
			sb.append("/" + canonicalToDisplayValue);
		}
		EObject parent = eo.eContainer();
		while (parent != null) {
			if (parent instanceof EAXML) break;
			Object parentVal = canonicalToDisplayValue(parent);
			if (parentVal instanceof String) {
				sb.insert(0, "/" + parentVal);
			}
			parent = parent.eContainer();
		}
		return sb.toString();
	}
	
	protected String getConvertedMultiLine(List<Object> displayCollection) {
		return TableViewHelpers.toMultilineString(displayCollection);
	}

	@Override
	public Object displayToCanonicalValue(Object displayValue) {
		return displayValue;
	}
	
	

}
