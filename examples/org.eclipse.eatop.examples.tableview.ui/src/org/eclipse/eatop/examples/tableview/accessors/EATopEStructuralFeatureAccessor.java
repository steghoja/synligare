package org.eclipse.eatop.examples.tableview.accessors;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.eatop.examples.tableview.IConfigLabels;
import org.eclipse.eatop.examples.tableview.TableViewHelpers;
import org.eclipse.eatop.examples.tableview.accessors.impl.EStructuralFeatureAccessor;
import org.eclipse.eatop.examples.tableview.ui.GEatopDisplayConverter;
import org.eclipse.eatop.geastadl.ginfrastructure.gelements.GReferrable;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EAttributeImpl;
import org.eclipse.emf.ecore.impl.EEnumImpl;

public class EATopEStructuralFeatureAccessor extends EStructuralFeatureAccessor {
	private enum DataTypes {
		BOOLEAN, STRING, ENUM, REFERENCE, REFERENCE_MANY
	}
	private DataTypes type;
	private static final GEatopDisplayConverter displayConverter = new GEatopDisplayConverter();
	private Map<String, Object> literals = new HashMap<String, Object>();
	private Map<String, Object> referenceChoices = new HashMap<String, Object>();
	
	
	
	public EATopEStructuralFeatureAccessor(EStructuralFeature feature) {
		super(feature);
		if (feature instanceof EAttributeImpl) {
			EAttributeImpl attribute = (EAttributeImpl) getHeldData();
			switch (attribute.getEType().getInstanceClassName()) {
				case "java.lang.String":
					type = DataTypes.STRING;
					break;
				case "java.lang.Boolean":
					type = DataTypes.BOOLEAN;
					break;
				default:
			}
			if (attribute.getEGenericType().getERawType() instanceof EEnumImpl) {
				type = DataTypes.ENUM;
				EEnumImpl typ = (EEnumImpl) attribute.getEGenericType().getERawType();
				EList<EEnumLiteral> eLiterals = typ.getELiterals();
				for (EEnumLiteral literal : eLiterals) {
					Enumerator instance = literal.getInstance();
					try {
						Method getMethod = instance.getClass().getMethod("get", String.class);
						Object resolvedEnum = getMethod.invoke(instance, literal.getName().toUpperCase());
						literals.put(literal.getName().toUpperCase(), resolvedEnum);
					} catch (Exception e) {
						type = null;
					}
				}
			}	
		} else if (feature instanceof EReference){
			if(!(((EReference) feature).isContainment())) {
				if (feature.isMany()) {
					type = DataTypes.REFERENCE_MANY;
				} else {
					type = DataTypes.REFERENCE;
				}
			} 
		}
	}

	@Override
	public Object canonicalToDisplayValue(Object canonicalValue) {
		return displayConverter.canonicalToDisplayValue(canonicalValue);
	}

	@Override
	public Object displayToCanonicalValue(Object displayValue) {
		return displayConverter.displayToCanonicalValue(displayValue);
	}

	@Override
	protected void doSetDataValue(EObject rowObject, Object newValue) {
		if (type != null) {
			switch (type) {
			case BOOLEAN:
				if (newValue.equals(DELETE_ENUM)) {
					rowObject.eSet(feature, null);
				} else {
					rowObject.eSet(feature, Boolean.valueOf((String) newValue));
				}
				break;
			case ENUM:
				Object object = literals.get(newValue);
				rowObject.eSet(feature, object);
				break;
			case STRING:
				rowObject.eSet(feature, newValue);
				break;
			case REFERENCE:
				rowObject.eSet(feature, referenceChoices.get(newValue));
				break;
			default:
				rowObject.eSet(feature, newValue);
				break;
			}
		}

	}

	@Override
	public List<String> getConfigLabels() {
		List<String> labels = new ArrayList<String>();

		labels.add(IConfigLabels.ALIGN_LEFT);
		if (type != null) {
			switch (type) {
			case BOOLEAN:
			case ENUM:
				labels.add(IConfigLabels.EDITOR_COMBO);
				break;
			case STRING:
				labels.add(IConfigLabels.EDITOR_TEXT);
				break;
			case REFERENCE:
				labels.add(IConfigLabels.EDITOR_COMBO);
				break;
			case REFERENCE_MANY:
				labels.add(IConfigLabels.REFERENCE_MULTI);
				break;
			default:
				labels.add(IConfigLabels.EDITOR_TEXT);
				break;
			}
		}

		return labels;
	}
	
	@Override
	public List<?> getComboBoxData(EObject rowObject) {
		List<String> choices = new ArrayList<String>();
		referenceChoices = new HashMap<String, Object>();

		if (type != null) {
			switch (type) {
			case BOOLEAN:
				choices.add("true");
				choices.add("false");
				choices.add(DELETE_ENUM);
				break;
			case ENUM:
				for (String ss : literals.keySet()) {
					choices.add(ss);
				}
				break;
			case REFERENCE:
				Collection<EObject> findPossibleReferences = TableViewHelpers.findPossibleReferences(rowObject, (EReference) feature);
				
				for (EObject eObject : findPossibleReferences) {
					if (eObject instanceof GReferrable) {
						choices.add(((GReferrable) eObject).gGetShortName());
						referenceChoices.put(((GReferrable) eObject).gGetShortName(), eObject);
					}
				}
				break;
			default:
				break;
			}
		}

		return choices;
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
			
			if (firstValue instanceof String && secondValue instanceof String) {
				try {
					Integer integer = Integer.parseInt((String) firstValue);
					Integer integer2 = Integer.parseInt((String) secondValue);
					if (integer != null && integer2 != null) {
						return modifier * integer.compareTo(integer2);
					}
				} catch (NumberFormatException ex) {
					return modifier * ((String) firstValue).compareTo((String) secondValue);
				}

			}
			if (firstValue instanceof String) {
				return modifier * ((String) firstValue).compareTo(((String) secondValue));
			} else if (firstValue instanceof Boolean){
				return modifier * ((Boolean) firstValue).compareTo(((Boolean) secondValue));
			} else if (firstValue instanceof Enumerator) {
				return modifier * ((Enumerator) firstValue).getName().compareTo(((Enumerator) secondValue).getName());
			}
			
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
