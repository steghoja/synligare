package org.eclipse.eatop.examples.tableview;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.sphinx.emf.edit.ExtendedItemProviderAdapter;
import org.eclipse.sphinx.emf.util.WorkspaceEditingDomainUtil;

public class TableViewHelpers {

	
	public static String toMultilineString(List<?> list) {
		return separateList(list, "\n");
	}

	public static String toCommaSeparatedString(List<?> list) {
		return separateList(list, ", ");
	}
	
	private static String separateList(List<?> list, String separator) {
		String result = "";
		if (!list.isEmpty()) {
			int i = 0;
			for (; i < list.size() - 1; i++) {
				Object value = list.get(i);
				result += value == null ? "null" : value.toString();
				result += separator;
			}
			Object display = list.get(i);
			result += display == null ? "null" : display.toString();
		}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static Collection<EObject> findPossibleReferences(EObject object, EStructuralFeature refFeature) {
		// Collect the choices of values for the specified feature of the given object
		AdapterFactoryEditingDomain editingDomain = (AdapterFactoryEditingDomain) WorkspaceEditingDomainUtil.getEditingDomain(object);
		AdapterFactory adapterFactory = editingDomain.getAdapterFactory();
		ExtendedItemProviderAdapter adapt = (ExtendedItemProviderAdapter) adapterFactory.adapt(object, IEditingDomainItemProvider.class);
		Collection<EObject> result = (Collection<EObject>) adapt.getChoiceOfValues(object, null, refFeature);
		return result;
	}
}
