package org.eclipse.eatop.tableview.ui;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.eatop.eastadl21.EAArrayValue;
import org.eclipse.eatop.eastadl21.EABooleanValue;
import org.eclipse.eatop.eastadl21.EACompositeValue;
import org.eclipse.eatop.eastadl21.EAEnumerationValue;
import org.eclipse.eatop.eastadl21.EANumericalValue;
import org.eclipse.eatop.eastadl21.EAStringValue;
import org.eclipse.eatop.eastadl21.FaultFailurePropagationLink_fromPort;
import org.eclipse.eatop.eastadl21.FaultFailurePropagationLink_toPort;
import org.eclipse.eatop.eastadl21.FunctionConnector_port;
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
			return toMultilineString(displayCollection);
			
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
			
		} else if (canonicalValue instanceof FunctionConnector_port) {
			return ((FunctionConnector_port) canonicalValue).getFunctionPrototype().getShortName();
			
		} else if (canonicalValue instanceof FaultFailurePropagationLink_fromPort) {
			return ((FaultFailurePropagationLink_fromPort) canonicalValue).getFaultFailurePort().getShortName();
			
		} else if (canonicalValue instanceof FaultFailurePropagationLink_toPort) {
			return ((FaultFailurePropagationLink_toPort) canonicalValue).getFaultFailurePort().getShortName();
			
		}
		else if (canonicalValue != null){
			return canonicalValue.toString();
		
		} else {
			return null;
		}
		
	}

	@Override
	public Object displayToCanonicalValue(Object displayValue) {
		return displayValue;
	}
	
	
	private String toMultilineString(List<Object> values) {
		String result = "";
		if (!values.isEmpty()) {
			int i = 0;
			for (; i < values.size() - 1; i++) {
				Object value = values.get(i);
				result += value == null ? "null" : value.toString();
				result += "\n";
			}
			Object display = values.get(i);
			result += display == null ? "null" : display.toString();
		}
		return result;
	}

}
