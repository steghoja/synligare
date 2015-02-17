package org.eclipse.eatop.examples.tableview.ui;

import org.eclipse.eatop.examples.tableview.IConfigLabels;
import org.eclipse.eatop.examples.tableview.celleditors.EATopMultiReferenceCellEditor;
import org.eclipse.eatop.examples.tableview.celleditors.ReferenceCellEditor;
import org.eclipse.eatop.examples.tableview.dataproviders.EObjectAccessorComboBoxDataProvider;
import org.eclipse.eatop.examples.tableview.dataproviders.EObjectAccessorReferenceDataProvider;
import org.eclipse.nebula.widgets.nattable.config.AbstractRegistryConfiguration;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.config.IEditableRule;
import org.eclipse.nebula.widgets.nattable.edit.EditConfigAttributes;
import org.eclipse.nebula.widgets.nattable.edit.editor.ComboBoxCellEditor;
import org.eclipse.nebula.widgets.nattable.edit.editor.TextCellEditor;
import org.eclipse.nebula.widgets.nattable.style.DisplayMode;


public class EditConfiguration extends AbstractRegistryConfiguration {
	EObjectAccessorComboBoxDataProvider comboBoxDataProvider;
	EObjectAccessorReferenceDataProvider referenceDataProvider;
	public EditConfiguration(EObjectAccessorComboBoxDataProvider comboBoxDataProvider,
			EObjectAccessorReferenceDataProvider referenceDataProvider) {
		this.comboBoxDataProvider = comboBoxDataProvider;
		this.referenceDataProvider = referenceDataProvider;
	}

	@Override
	public void configureRegistry(IConfigRegistry configRegistry) {
		configRegistry.registerConfigAttribute(
				EditConfigAttributes.CELL_EDITABLE_RULE, 
				IEditableRule.ALWAYS_EDITABLE,
				DisplayMode.NORMAL,
				IConfigLabels.EDITOR_TEXT);
		configRegistry.registerConfigAttribute(
				EditConfigAttributes.CELL_EDITABLE_RULE, 
				IEditableRule.ALWAYS_EDITABLE,
				DisplayMode.NORMAL,
				IConfigLabels.EDITOR_COMBO);
		configRegistry.registerConfigAttribute(
				EditConfigAttributes.CELL_EDITABLE_RULE, 
				IEditableRule.ALWAYS_EDITABLE,
				DisplayMode.NORMAL,
				IConfigLabels.REFERENCE_MULTI);
		
		configRegistry.registerConfigAttribute(
				EditConfigAttributes.SUPPORT_MULTI_EDIT, 
				true);
		
		configRegistry.registerConfigAttribute(
				EditConfigAttributes.SUPPORT_MULTI_EDIT, 
				false,
				DisplayMode.EDIT,
				IConfigLabels.REFERENCE_MULTI);
		
		// Enums
		ComboBoxCellEditor comboBoxCellEditor = new ComboBoxCellEditor(comboBoxDataProvider);
		configRegistry.registerConfigAttribute(
				EditConfigAttributes.CELL_EDITOR, 
				comboBoxCellEditor, 
				DisplayMode.NORMAL,
				IConfigLabels.EDITOR_COMBO);

		// Reference editing
		ReferenceCellEditor referenceCellEditorMulti = new EATopMultiReferenceCellEditor(referenceDataProvider);

		configRegistry.registerConfigAttribute(
				EditConfigAttributes.CELL_EDITOR, 
				referenceCellEditorMulti, 
				DisplayMode.EDIT,
				IConfigLabels.REFERENCE_MULTI);


		TextCellEditor textCellEditor = new TextCellEditor(true, true);
		configRegistry.registerConfigAttribute(
				EditConfigAttributes.CELL_EDITOR, 
				textCellEditor,
				DisplayMode.EDIT,
				IConfigLabels.EDITOR_TEXT); 
	}

}
