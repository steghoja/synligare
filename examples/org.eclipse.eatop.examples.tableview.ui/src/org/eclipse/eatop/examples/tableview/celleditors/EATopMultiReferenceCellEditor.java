package org.eclipse.eatop.examples.tableview.celleditors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.eatop.examples.tableview.TableViewHelpers;
import org.eclipse.eatop.examples.tableview.core.ShortNameLabelProvider;
import org.eclipse.eatop.examples.tableview.dataproviders.EObjectAccessorReferenceDataProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.ui.celleditor.FeatureEditorDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.nebula.widgets.nattable.selection.SelectionLayer.MoveDirectionEnum;

public class EATopMultiReferenceCellEditor extends ReferenceCellEditor {

	private FeatureEditorDialog dialog;
	
	public EATopMultiReferenceCellEditor(
			EObjectAccessorReferenceDataProvider referenceDataProvider) {
		super(referenceDataProvider);
	}
	
	protected EObject getObject() {
		return dataProvider.getEObject(getRowIndex());
	}
	
	private EObject getReference() {
		return dataProvider.getReferenceType(getColumnIndex());
	}
	
	
	protected List<EObject> getCandidates() {
		List<EObject> choices = new ArrayList<EObject>();
		
		EObject object = getObject();
		EObject reference = getReference();
		if (reference instanceof EStructuralFeature) {
			EStructuralFeature feature = (EStructuralFeature) reference;
			choices.addAll(TableViewHelpers.findPossibleReferences(object, feature));
		}
		return choices;
		
	}
	protected List<EObject> getDataValues() {
		List<EObject> values = new ArrayList<EObject>();
		EObject reference = getReference();
		if (reference instanceof EStructuralFeature) {
			EStructuralFeature feature = (EStructuralFeature) reference;
			EObject object = getObject();
			Object eGet = object.eGet(feature);
			if (eGet instanceof List<?>) {
				List<?> objects = (List<?>) eGet;
				for (Object obj : objects) {
					if (obj instanceof EObject) {
						EObject eo = (EObject) obj;
						values.add(eo);
					}
				}
				
			}
		}
		return values;
	}
	@Override
	public int open() {
		int returnCode = dialog.open();
		result = dialog.getResult();
		if (returnCode == Window.OK) {
			commit(MoveDirectionEnum.LEFT, false);
		}
		return returnCode;
	}
	private String getFeatureName() {
		EObject reference = getReference();
		if (reference instanceof EStructuralFeature) {
			EStructuralFeature feature = (EStructuralFeature) reference;
			return feature.getName();
		}
		return "";
	}
	@Override
	public Object createDialogInstance() {
		dialog = new FeatureEditorDialog(parent.getShell(), new ShortNameLabelProvider(), 
				getFeatureName(), getObject().eClass(), getDataValues(), "Edit mutiple references", getCandidates(), true, true, true);
		return dialog;
	}

	@Override
	public Object getDialogInstance() {
		return dialog;
	}

	@Override
	public Object getEditorValue() {
		return displayConverter.displayToCanonicalValue(result);
	}

	@Override
	public void setEditorValue(Object value) {
		
	}

	@Override
	public void close() {
		dialog.close();		
	}

	@Override
	public boolean isClosed() {
		return false;
	}
}
