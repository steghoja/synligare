package org.eclipse.eatop.tableview.ui;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.eatop.eastadl21.DesignFunctionType;
import org.eclipse.eatop.geastadl.ginfrastructure.gelements.GelementsPackage;
import org.eclipse.eatop.tableview.accessorfactories.impl.GenericEObjectAccessorFactory;
import org.eclipse.eatop.tableview.accessors.AllocationAccessor;
import org.eclipse.eatop.tableview.accessors.EATopEStructuralFeatureAccessor;
import org.eclipse.eatop.tableview.accessors.IEObjectPropertyAccessor;
import org.eclipse.eatop.tableview.accessors.RequirementAccessor;
import org.eclipse.eatop.tableview.accessors.TraceableSpecificationAccessor;
import org.eclipse.eatop.tableview.accessors.impl.EStructuralFeatureAccessor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class EObjectAccessorFactory extends GenericEObjectAccessorFactory {

	public EObjectAccessorFactory() {
	}

	@Override
	public boolean canCreate(List<? extends EObject> data) {
		for (EObject eo : data) {
			if (!(eo instanceof EObject)) {
				return false;
			}
		}
		return true;
	}

	@Override
	protected List<IEObjectPropertyAccessor> createAccessors(List<? extends EObject> data) {
		List<IEObjectPropertyAccessor> accessors = super.createAccessors(data);
		Collections.addAll(accessors, new RequirementAccessor(),
				new AllocationAccessor());
		for (EObject eo : data) {
			if (eo instanceof DesignFunctionType) {
				accessors.add(new TraceableSpecificationAccessor());
				break;
			}
		}
		return accessors;
	}
	

	@Override
	protected Set<EStructuralFeature> getEStructuralFeatures(EObject data) {
		Set<EStructuralFeature> set = new TreeSet<EStructuralFeature>(getFeatureComparator());
		set.addAll(data.eClass().getEAllAttributes());
		set.addAll(data.eClass().getEReferences());
		set.addAll(data.eClass().getEAttributes());
		return set;
	}
	

	@Override
	protected IEObjectPropertyAccessor createRowHeaderAccessor() {
		return new EStructuralFeatureAccessor(
				GelementsPackage.eINSTANCE.getGReferrable_GShortName()) {
			@Override
			public Object getDataValue(EObject rowObject) {
				Object dataValue = super.getDataValue(rowObject);
				if (dataValue != null) {
					return dataValue;
				} else {
					return rowObject.eClass().getName();
				}
			}
		};
	}

	@Override
	protected EStructuralFeatureAccessor createAccessor(
			EStructuralFeature feature) {
		return new EATopEStructuralFeatureAccessor(feature);
	}

}
