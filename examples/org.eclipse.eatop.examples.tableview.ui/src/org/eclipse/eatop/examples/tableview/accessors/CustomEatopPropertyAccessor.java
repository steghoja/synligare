package org.eclipse.eatop.examples.tableview.accessors;

import java.util.Comparator;
import java.util.List;

import org.eclipse.eatop.examples.tableview.IConfigLabels;
import org.eclipse.eatop.examples.tableview.accessors.IEObjectPropertyAccessor;
import org.eclipse.eatop.examples.tableview.ui.GEatopDisplayConverter;
import org.eclipse.eatop.geastadl.ginfrastructure.gelements.GReferrable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.nebula.widgets.nattable.config.CellConfigAttributes;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.data.convert.IDisplayConverter;
import org.eclipse.nebula.widgets.nattable.style.DisplayMode;

public abstract class CustomEatopPropertyAccessor implements IEObjectPropertyAccessor {

	protected final GEatopDisplayConverter displayConverter = new GEatopDisplayConverter();

	public CustomEatopPropertyAccessor() {
		super();
	}

	@Override
	public void setDataValue(EObject rowObject, Object newValue) {
	
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
	public Object canonicalToDisplayValue(Object canonicalValue, IConfigRegistry configRegistry) {
		IDisplayConverter displayConverter = configRegistry.getConfigAttribute(CellConfigAttributes.DISPLAY_CONVERTER, DisplayMode.NORMAL, IConfigLabels.DISPLAYCONVERTER);
		return displayConverter.canonicalToDisplayValue(canonicalValue);
	}

	@Override
	public Object displayToCanonicalValue(Object displayValue, IConfigRegistry configRegistry) {
		IDisplayConverter displayConverter = configRegistry.getConfigAttribute(CellConfigAttributes.DISPLAY_CONVERTER, DisplayMode.NORMAL, IConfigLabels.DISPLAYCONVERTER);
		return displayConverter.displayToCanonicalValue(displayValue);
	}
	
	@Override
	public Comparator<EObject> getComparator(int modifier) {
		return new ComparatorImpl(modifier);
	}
	
	private class ComparatorImpl implements Comparator<EObject> {
		
		private int modifier;
		public ComparatorImpl(int modifier) {
		this.modifier = modifier;	
		}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public int compare(EObject first, EObject second) {
			Object firstValue = getDataValue(first);
			Object secondValue = getDataValue(second);
			
			if (firstValue == null && secondValue == null) return modifier * 0;
			if (firstValue != null && secondValue == null) return modifier * 1;
			if (firstValue == null && secondValue != null) return modifier * -1;

			GReferrable compareValueOfFirst = null;
			GReferrable compareValueOfSecond = null;
			// We are sure of the type of the content of the list due to the definition of getDataValue of this class
			if (firstValue instanceof GReferrable) compareValueOfFirst = (GReferrable) firstValue;
			if (secondValue instanceof GReferrable) compareValueOfSecond = (GReferrable) secondValue;
			if (firstValue instanceof List<?> && !((List) firstValue).isEmpty()) {
				Object object = ((List<Object>) firstValue).get(0);
				if (object instanceof GReferrable) compareValueOfFirst = (GReferrable) object;
			}
			if (secondValue instanceof List<?> && !((List) secondValue).isEmpty()) {
				Object object = ((List<GReferrable>) secondValue).get(0);
				if (object instanceof GReferrable) compareValueOfSecond = (GReferrable) object;
				
			}
			
			// We check for null again, there is a possibility that it is not implemented and the objects could then be Strings, and should not be considered
			if (compareValueOfFirst == null && compareValueOfSecond == null) return modifier * 0;
			if (compareValueOfFirst != null && compareValueOfSecond == null) return modifier * 1;
			if (compareValueOfFirst == null && compareValueOfSecond != null) return modifier * -1;
			
			return modifier * compareValueOfFirst.gGetShortName().compareTo(compareValueOfSecond.gGetShortName());
		}
		
	}

}