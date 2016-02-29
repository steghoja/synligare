package org.eclipse.eatop.examples.tableview.accessors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.Satisfy;
import org.eclipse.eatop.geastadl.ginfrastructure.gelements.GReferrable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sphinx.emf.ecore.ECrossReferenceAdapterFactory;

public class RequirementAccessor extends CustomEatopPropertyAccessor {

	@Override
	public Object getDataValue(EObject rowObject) {
		
		Set<EObject> incoming = new LinkedHashSet<EObject>();
		
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
					if (eObject != null) {
						incoming.add(eObject);
					}
				}
			}
		}
		
		
		Set<Requirement> requirements = new LinkedHashSet<Requirement>();
		
		for (EObject eObject : incoming) {
			if (eObject instanceof Satisfy) {
				Satisfy sat = (Satisfy) eObject;
				requirements.addAll(sat.getSatisfiedRequirement());
			}
			
		}
		
		return new ArrayList<EObject>(requirements);
	}

	@Override
	public String getName() {
		return "Satisfied Requirements";
	}

	@Override
	public EObject getHeldData() {
		// TODO Auto-generated method stub
		return null;
	}
}
