package org.eclipse.eatop.examples.tableview.accessorfactories.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.eatop.examples.tableview.accessorfactories.IEObjectAccessorFactory;
import org.eclipse.eatop.examples.tableview.accessors.IEObjectPropertyAccessor;
import org.eclipse.eatop.examples.tableview.accessors.impl.EStructuralFeatureAccessor;
import org.eclipse.eatop.examples.tableview.accessors.impl.EmptyPropertyAccessor;
import org.eclipse.eatop.examples.tableview.accessors.impl.PropertyAccessorEObjectAccessor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class GenericEObjectAccessorFactory implements IEObjectAccessorFactory {

	@Override
	public boolean canCreate(List<? extends EObject> data) {
		if (data == null) {
			return false;
		}
		return true;
	}

	@Override
	public PropertyAccessorEObjectAccessor create(List<? extends EObject> data) {
		List<IEObjectPropertyAccessor> accessors = createAccessors(data);
		IEObjectPropertyAccessor rowHeaderAccessor = createRowHeaderAccessor();
		return new PropertyAccessorEObjectAccessor(accessors, rowHeaderAccessor);
	}

	protected IEObjectPropertyAccessor createRowHeaderAccessor() {
		EmptyPropertyAccessor rowHeaderAccessor = new EmptyPropertyAccessor() {
			@Override public Object getDataValue(EObject rowObject) {
				if (rowObject != null) {
					EClass eClass = rowObject.eClass();
					if (eClass != null) {
						EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature("shortName");
						Object name = null;
						if (eStructuralFeature != null) {
							name = rowObject.eGet(eStructuralFeature);
						}
						if (name != null) {
							return name;
						}
						return eClass.getName();
					}
				}
				return super.getDataValue(rowObject);
			}
		};
		return rowHeaderAccessor;
	}
	
	protected Comparator<? super EStructuralFeature> getFeatureComparator() {
		Comparator<EStructuralFeature> comp = new Comparator<EStructuralFeature>() {
			
			@Override
			public int compare(EStructuralFeature arg0, EStructuralFeature arg1) {
				return arg0.getName().compareTo(arg1.getName());
			}
		};
		return comp;
	}
	
	protected Set<EStructuralFeature> getEStructuralFeatures(EObject data) {
		Set<EStructuralFeature> set = new TreeSet<EStructuralFeature>(getFeatureComparator());
		set.addAll(data.eClass().getEAllAttributes());
		return set;
	}
	
	protected EStructuralFeatureAccessor createAccessor(EStructuralFeature feature) {
		return new EStructuralFeatureAccessor(feature);
	}
	
	protected List<IEObjectPropertyAccessor> createAccessors(List<? extends EObject> data) {
		int eoCount = data.size();
		
		HashMap<EStructuralFeature, Integer> counts = new LinkedHashMap<EStructuralFeature, Integer>();
		for (EObject eo : data) {
			for (EStructuralFeature attribute : getEStructuralFeatures(eo)) {
				if(attribute.getName().length() < 2 || !attribute.getName().substring(0, 2).matches("[g][A-Z]")) {
					Integer count = counts.get(attribute);
					if (count == null) count = 0;
					counts.put(attribute, count + 1);
				}
			}
		}

		ArrayList<EStructuralFeature> inAll = new ArrayList<EStructuralFeature>();
		for (Map.Entry<EStructuralFeature, Integer> entry : counts.entrySet()) {
			if (entry.getValue() == eoCount) {
				inAll.add(entry.getKey());
			}
		}

		ArrayList<IEObjectPropertyAccessor> accessors = new ArrayList<IEObjectPropertyAccessor>();
		for (EStructuralFeature feature : inAll) {
			accessors.add(createAccessor(feature));
		}
		return accessors;
	}



}