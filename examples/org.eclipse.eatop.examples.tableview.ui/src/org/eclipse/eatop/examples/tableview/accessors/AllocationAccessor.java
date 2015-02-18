package org.eclipse.eatop.examples.tableview.accessors;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.eatop.eastadl21.EAPrototype;
import org.eclipse.eatop.eastadl21.ErrorModelPrototype;
import org.eclipse.eatop.geastadl.ginfrastructure.gelements.GReferrable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sphinx.emf.ecore.ECrossReferenceAdapterFactory;

public class AllocationAccessor extends CustomEatopPropertyAccessor {

	@Override
	public Object getDataValue(EObject rowObject) {
		
		Set<EObject> requirements = new LinkedHashSet<EObject>();
		
		ECrossReferenceAdapter referenceAdapter = ECrossReferenceAdapterFactory.INSTANCE.adapt(rowObject);
		Collection<Setting> inverseReferences = referenceAdapter.getInverseReferences(rowObject);
		
		for (Setting setting : inverseReferences) {
			EStructuralFeature feature = setting.getEStructuralFeature();
			if (feature instanceof EReference) {
				EReference reference = (EReference) feature;
				if (!reference.isContainer() && !reference.isContainment()) {
					EObject eObject = setting.getEObject();
					while (eObject != null && !(eObject instanceof GReferrable)) {
						eObject = eObject.eContainer();
					}
					if (eObject instanceof EAPrototype) {
						eObject = checkType(rowObject, (EAPrototype) eObject);
					}
					if (eObject instanceof ErrorModelPrototype) {
						eObject = checkTarget(rowObject, (ErrorModelPrototype) eObject);
					}
					if (eObject != null) {
						requirements.add(eObject);
					}
					
				}
			}
		}
		
		return new ArrayList<EObject>(requirements);
	}

	private EObject checkType(EObject referenced, EAPrototype reference) {
		try {
			Method getType = reference.getClass().getMethod("getType");
			Object type = getType.invoke(reference);
			if (type == referenced) {
				return null;
			}
		} catch (Exception e) {
			return reference;
		}
		return reference;
	}
	
	private EObject checkTarget(EObject referenced, ErrorModelPrototype reference) {
		if (reference.getTarget() == referenced) {
			return null;
		}
		return reference;
	}
	
	@Override
	public String getName() {
		return "Incoming references";
	}

	@Override
	public EObject getHeldData() {
		return null;
	}
}
