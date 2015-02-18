package org.eclipse.eatop.examples.tableview.ui;


import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.eatop.eastadl21.ClampConnector_port;
import org.eclipse.eatop.eastadl21.EAArrayValue;
import org.eclipse.eatop.eastadl21.EABooleanValue;
import org.eclipse.eatop.eastadl21.EACompositeValue;
import org.eclipse.eatop.eastadl21.EAEnumerationValue;
import org.eclipse.eatop.eastadl21.EANumericalValue;
import org.eclipse.eatop.eastadl21.EAStringValue;
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
			return canonicalToDisplayValue(((FunctionAllocation_target) canonicalValue).getAllocationTarget())
					+ ": " + canonicalToDisplayValue(((FunctionAllocation_target) canonicalValue).getAllocationTarget_context());
			
		} else if (canonicalValue instanceof FunctionAllocation_allocatedElement) {
			return canonicalToDisplayValue(((FunctionAllocation_allocatedElement) canonicalValue).getAllocateableElement())
					+ ": " + canonicalToDisplayValue(((FunctionAllocation_allocatedElement) canonicalValue).getAllocateableElement_context());
			
		} else if (canonicalValue instanceof ErrorModelPrototype_functionTarget) {
			return canonicalToDisplayValue(((ErrorModelPrototype_functionTarget) canonicalValue).getFunctionPrototype())
					+ ": " + canonicalToDisplayValue(((ErrorModelPrototype_functionTarget) canonicalValue).getFunctionPrototype_context());
			
		} else if (canonicalValue instanceof ErrorModelPrototype_hwTarget) {
			return canonicalToDisplayValue(((ErrorModelPrototype_hwTarget) canonicalValue).getHardwareComponentPrototype())
					+ ": " + canonicalToDisplayValue(((ErrorModelPrototype_hwTarget) canonicalValue).getHardwareComponentPrototype_context());
			
		} else if (canonicalValue instanceof FaultFailurePort_functionTarget) {
			return canonicalToDisplayValue(((FaultFailurePort_functionTarget) canonicalValue).getFunctionPort())
					+ ": " + canonicalToDisplayValue(((FaultFailurePort_functionTarget) canonicalValue).getFunctionPrototype());
			
		} else if (canonicalValue instanceof FaultFailurePort_hwTarget) {
			return canonicalToDisplayValue(((FaultFailurePort_hwTarget) canonicalValue).getHardwarePort())
					+ ": " + canonicalToDisplayValue(((FaultFailurePort_hwTarget) canonicalValue).getHardwareComponentPrototype());
			
		} else if (canonicalValue instanceof ClampConnector_port) {
			return canonicalToDisplayValue(((ClampConnector_port) canonicalValue).getFunctionPrototype())
					+ ": " + canonicalToDisplayValue(((ClampConnector_port) canonicalValue).getFunctionPort());
			
		} else if (canonicalValue instanceof FunctionConnector_port) {
			return canonicalToDisplayValue(((FunctionConnector_port) canonicalValue).getFunctionPrototype())
					+ ": " + canonicalToDisplayValue(((FunctionConnector_port) canonicalValue).getFunctionPort());
			
		} else if (canonicalValue instanceof FaultFailurePropagationLink_fromPort) {
			return canonicalToDisplayValue(((FaultFailurePropagationLink_fromPort) canonicalValue).getFaultFailurePort()) 
					+ ": " + canonicalToDisplayValue(((FaultFailurePropagationLink_fromPort) canonicalValue).getErrorModelPrototype());
			
		} else if (canonicalValue instanceof FaultFailurePropagationLink_toPort) {
			return canonicalToDisplayValue(((FaultFailurePropagationLink_toPort) canonicalValue).getFaultFailurePort()) 
					+ ": " + canonicalToDisplayValue(((FaultFailurePropagationLink_toPort) canonicalValue).getErrorModelPrototype());
			
		} else if (canonicalValue instanceof HardwarePortConnector_port) {
			return canonicalToDisplayValue(((HardwarePortConnector_port) canonicalValue).getHardwareComponentPrototype()) 
					+ ": " + canonicalToDisplayValue(((HardwarePortConnector_port) canonicalValue).getHardwarePort());
			
		} else if (canonicalValue instanceof HardwareConnector_port) {
			return canonicalToDisplayValue(((HardwareConnector_port) canonicalValue).getHardwareComponentPrototype())
					+ ": " + canonicalToDisplayValue(((HardwareConnector_port) canonicalValue).getHardwarePin());

		} else if (canonicalValue instanceof Satisfy_satisfiedBy) {
			return canonicalToDisplayValue(((Satisfy_satisfiedBy) canonicalValue).getIdentifiable_target()) 
					+ ": " + canonicalToDisplayValue(((Satisfy_satisfiedBy) canonicalValue).getIdentifiable_context());
		
		} else if (canonicalValue instanceof Realization_realizedBy) {
			return canonicalToDisplayValue(((Satisfy_satisfiedBy) canonicalValue).getIdentifiable_target())
					+ ": " + canonicalToDisplayValue(((Realization_realizedBy) canonicalValue).getIdentifiable_context());

		} else if (canonicalValue instanceof Refine_refinedBy) {
			return canonicalToDisplayValue(((Refine_refinedBy) canonicalValue).getIdentifiable_target())
					+ ": " + canonicalToDisplayValue(((Refine_refinedBy) canonicalValue).getIdentifiable_context());
		
		} else if (canonicalValue != null){
			return canonicalValue.toString();
		
		} else {
			return "";
		}
		
	}
	
	protected Object getConvertedMultiLine(ArrayList<Object> displayCollection) {
		return TableViewHelpers.toMultilineString(displayCollection);
	}

	@Override
	public Object displayToCanonicalValue(Object displayValue) {
		return displayValue;
	}
	
	

}
