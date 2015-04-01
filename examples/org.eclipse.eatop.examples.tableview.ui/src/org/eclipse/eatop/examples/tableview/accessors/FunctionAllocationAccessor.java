package org.eclipse.eatop.examples.tableview.accessors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.eatop.eastadl21.FunctionAllocation;
import org.eclipse.eatop.eastadl21.FunctionAllocation_target;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sphinx.emf.ecore.ECrossReferenceAdapterFactory;

public class FunctionAllocationAccessor extends CustomEatopPropertyAccessor {

	@Override
	public Object getDataValue(EObject rowObject) {
		Set<EObject> allocations = new LinkedHashSet<EObject>();
		ECrossReferenceAdapter referenceAdapter = ECrossReferenceAdapterFactory.INSTANCE.adapt(rowObject);
		Collection<Setting> inverseReferences = referenceAdapter.getInverseReferences(rowObject);
		for (Setting setting : inverseReferences) {
			EObject eo = setting.getEObject();
			if (eo instanceof FunctionAllocation_target) {
				EObject eContainer = eo.eContainer();
				if (eContainer instanceof FunctionAllocation) {
					FunctionAllocation funcAllocation = (FunctionAllocation) eContainer;
					allocations.add(funcAllocation.getAllocatedElement());
				}
			}
		}
		return new ArrayList<EObject>(allocations);
	}

	@Override
	public String getName() {
		return "Function Allocations";
	}

	@Override
	public EObject getHeldData() {
		return null;
	}
}
