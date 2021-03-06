package org.eclipse.eatop.examples.tableview.celleditors;

import org.eclipse.eatop.examples.tableview.dataproviders.EObjectAccessorReferenceDataProvider;
import org.eclipse.nebula.widgets.nattable.edit.gui.AbstractDialogCellEditor;


public abstract class ReferenceCellEditor extends AbstractDialogCellEditor {
	
	protected EObjectAccessorReferenceDataProvider dataProvider;
	protected Object result;
	
	public ReferenceCellEditor(EObjectAccessorReferenceDataProvider referenceDataProvider) {
		this.dataProvider = referenceDataProvider;
	}
}
