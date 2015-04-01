package org.eclipse.eatop.examples.tableview.accessors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.eatop.eastadl21.DesignFunctionType;
import org.eclipse.eatop.eastadl21.TraceableSpecification;
import org.eclipse.eatop.examples.tableview.ui.GEatopDisplayConverter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;

public class TraceableSpecificationAccessor extends CustomEatopPropertyAccessor {
	
	private static final GEatopDisplayConverter displayConverter = new GEatopDisplayConverter();
	@Override
	public Object getDataValue(EObject rowObject) {
		List<String> traces = new ArrayList<String>();
		if (rowObject instanceof DesignFunctionType) {
			DesignFunctionType new_name = (DesignFunctionType) rowObject;
			for (TraceableSpecification spec : new_name.getTraceableSpecification()) {
				traces.add(spec.getShortName());
			}
		}
		return traces;
	}

	@Override
	public void setDataValue(EObject rowObject, Object newValue) {
	}

	@Override
	public String getName() {
		return "Traceable Specification";
	}

	@Override
	public List<String> getConfigLabels() {
		return null;
	}

	@Override
	public List<?> getComboBoxData(EObject rowObject) {
		return null;
	}

	@Override
	public Object canonicalToDisplayValue(Object canonicalValue, IConfigRegistry configResistry) {
		return displayConverter.canonicalToDisplayValue(canonicalValue);
	}

	@Override
	public Object displayToCanonicalValue(Object displayValue, IConfigRegistry configResistry) {
		return displayConverter.displayToCanonicalValue(displayValue);
	}

	@Override
	public EObject getHeldData() {
		return null;
	}
}
