package org.eclipse.eatop.examples.contextview.providers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.eatop.eastadl21.ASILKind;
import org.eclipse.eatop.eastadl21.FaultFailure;
import org.eclipse.eatop.eastadl21.FaultFailurePort;
import org.eclipse.eatop.eastadl21.FaultFailure_anomaly;
import org.eclipse.eatop.eastadl21.SafetyConstraint;
import org.eclipse.eatop.examples.contextview.providers.TextOnlyNode.NodeType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sphinx.emf.ecore.ECrossReferenceAdapterFactory;

public class ASILLevelContentProvider extends BasicContextContentProvider {

	private Map<EObject, List<TextOnlyNode>> reqToAsilMap = new HashMap<EObject, List<TextOnlyNode>>();

	@Override
	public String getNameForInstanceReferencedBy() {
		return "ASIL Analysis";
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		List<Object> result = new ArrayList<Object>();
		if (parentElement instanceof TextOnlyNode && ((TextOnlyNode) parentElement).getType() == NodeType.INSTANCE_REFERENCED_BY) {
			reqToAsilMap.clear();
			if (input instanceof FaultFailurePort) {
				FaultFailurePort failurePort = (FaultFailurePort) input;
				ECrossReferenceAdapter adapt = ECrossReferenceAdapterFactory.INSTANCE.adapt(failurePort);
				Collection<Setting> inverseReferences = adapt.getInverseReferences(failurePort);
				for (Setting setting : inverseReferences) {
					EObject eObject = setting.getEObject();
					if (eObject instanceof FaultFailure_anomaly) {
						FaultFailure_anomaly failure = (FaultFailure_anomaly) eObject;
						EObject faultFailure = failure.eContainer();
						ECrossReferenceAdapter failAdapt = ECrossReferenceAdapterFactory.INSTANCE.adapt(faultFailure);
						Collection<Setting> failReferenced = failAdapt.getInverseReferences(faultFailure);
						for (Setting failRef : failReferenced) {
							EObject eob = failRef.getEObject();
							if (eob instanceof SafetyConstraint) {
								SafetyConstraint constraint = (SafetyConstraint) eob;
								ASILKind asilValue = constraint.getAsilValue();
								List<TextOnlyNode> asils = reqToAsilMap.get(failure);
								if (asils == null) {
									asils = new ArrayList<TextOnlyNode>();
								}
								asils.add(new TextOnlyNode(asilValue.getName(), NodeType.REFERENCES));
								reqToAsilMap.put(faultFailure, asils);
							}
						}
						result.add(faultFailure);
					}
				}

			}
		}
		if (parentElement instanceof FaultFailure) {
			if (reqToAsilMap.containsKey(parentElement)) {
				return reqToAsilMap.get(parentElement).toArray();
			}
		}

		return result.toArray();
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}
}
