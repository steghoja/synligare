package org.eclipse.eatop.examples.tableview.accessors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.FunctionAllocation;
import org.eclipse.eatop.eastadl21.FunctionAllocation_allocatedElement;
import org.eclipse.eatop.eastadl21.FunctionAllocation_target;
import org.eclipse.eatop.eastadl21.FunctionConnector;
import org.eclipse.eatop.eastadl21.HardwareComponentPrototype;
import org.eclipse.eatop.eastadl21.HardwareComponentType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sphinx.emf.ecore.ECrossReferenceAdapterFactory;

/**
 * Shows the allocated functions on the ECU and also shows the ECUs the functions have allocated
 */
public class FunctionAllocationAccessor extends CustomEatopPropertyAccessor {
	EObject held = null;
	
	public FunctionAllocationAccessor(EObject eo) {
		held = eo;
	}
	@Override
	public Object getDataValue(EObject rowObject) {
		Set<EObject> allocations = new LinkedHashSet<EObject>();
		ECrossReferenceAdapter referenceAdapter = ECrossReferenceAdapterFactory.INSTANCE.adapt(rowObject);
		Collection<Setting> inverseReferences = referenceAdapter.getInverseReferences(rowObject);
		for (Setting setting : inverseReferences) {
			EObject eo = setting.getEObject();
			if (rowObject instanceof HardwareComponentPrototype) {
				if (eo instanceof FunctionAllocation_target) {
					if (((FunctionAllocation_target) eo).getAllocationTarget().equals(rowObject)) {
						EObject eContainer = eo.eContainer();
						if (eContainer instanceof FunctionAllocation) {
							FunctionAllocation funcAllocation = (FunctionAllocation) eContainer;
							allocations.add(funcAllocation.getAllocatedElement());
						}
					}
					
				}
			} else if (rowObject instanceof FunctionConnector || rowObject instanceof DesignFunctionPrototype) {
				if (eo instanceof FunctionAllocation_allocatedElement) {
					if (((FunctionAllocation_allocatedElement) eo).getAllocateableElement().equals(rowObject)) {
						EObject eContainer = eo.eContainer();
						if (eContainer instanceof FunctionAllocation) {
							FunctionAllocation funcAllocation = (FunctionAllocation) eContainer;
							allocations.add(funcAllocation.getTarget());
						}
					}
				}
			}
			
		}
		return new ArrayList<EObject>(allocations);
	}

	@Override
	public String getName() {
		if (held instanceof HardwareComponentPrototype) {
			return "Function Allocations";
			
		} else if (held instanceof FunctionConnector || held instanceof DesignFunctionPrototype) {
			return "Function Targets";
		}
		return "";
		
	}
	@Override
	public EObject getHeldData() {
		return null;
	}

}
