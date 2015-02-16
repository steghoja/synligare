package org.eclipse.eatop.examples.tableview.accessors.impl;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.eatop.examples.tableview.IConfigLabels;
import org.eclipse.eatop.examples.tableview.accessors.IEObjectPropertyAccessor;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.workspace.WorkspaceEditingDomainFactory;
import org.eclipse.sphinx.emf.util.WorkspaceTransactionUtil;

public class EStructuralFeatureAccessor implements IEObjectPropertyAccessor {

	protected EStructuralFeature feature;

	public EStructuralFeatureAccessor(EStructuralFeature feature) {
		this.feature = feature;
	}

	@Override
	public String getName() {
		return feature.getName();
	}

	@Override
	public Object getDataValue(EObject rowObject) {
		return rowObject.eGet(feature);
	}

	private TransactionalEditingDomain getEditingDomain(EObject object) {
		ResourceSet resourceSet = null;
		if (object.eResource() != null) {
			resourceSet = object.eResource().getResourceSet();
		}
		Assert.isTrue(resourceSet != null, "Resource set must not be null.");
		return WorkspaceEditingDomainFactory.INSTANCE.getEditingDomain(resourceSet);
	}

	@Override
	public void setDataValue(final EObject rowObject, final Object newValue) {
		try {
			TransactionalEditingDomain editingDomain = getEditingDomain(rowObject);
			if (editingDomain != null) {
				WorkspaceTransactionUtil.executeInWriteTransaction(
						TransactionUtil.getEditingDomain(rowObject),
						getSetDataRunnable(rowObject, newValue),
						"Set " + getName());
			} else {
				getSetDataRunnable(rowObject, newValue).run();
			}

		} catch (OperationCanceledException e) {
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

	}

	private Runnable getSetDataRunnable(final EObject rowObject, final Object newValue) {
		return new Runnable() {
			@Override
			public void run() {
				doSetDataValue(rowObject, newValue);
			}
		};
	}

	protected void doSetDataValue(final EObject rowObject, final Object newValue) {
		rowObject.eSet(feature, newValue);
	}

	@Override
	public List<String> getConfigLabels() {
		List<String> labels = new ArrayList<String>();

		labels.add(IConfigLabels.EDITABLE);

		labels.add(IConfigLabels.ALIGN_LEFT);

		labels.add(IConfigLabels.EDITOR_TEXT);

		return labels;
	}

	@Override
	public List<?> getComboBoxData(EObject rowObject) {
		return null;
	}

	@Override
	public Object canonicalToDisplayValue(Object canonicalValue) {
		return canonicalValue;
	}

	@Override
	public Object displayToCanonicalValue(Object displayValue) {
		return displayValue;
	}

	@Override
	public EObject getHeldData() {
		return feature;
	}

	@Override
	public Comparator<EObject> getComparator(int modifier) {
		return new ComparatorImpl(modifier);
	}

	private class ComparatorImpl implements Comparator<EObject> {

		private int modifier = 1;

		public ComparatorImpl(int modifier) {
			this.modifier = modifier;
		}

		@Override
		public int compare(EObject first, EObject second) {
			Object firstValue = getDataValue(first);
			Object secondValue = getDataValue(second);

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
			} else return 0;
		}

	}

}
