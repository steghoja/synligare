package org.eclipse.eatop.volvo.visualizer.modeloverview.ui;

import org.eclipse.eatop.volvo.visualizer.modeloverview.models.VisualRepresentation;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class ChooseRepresentationDialog extends Dialog {

	private Button defaultRadioButton;
	private Button customRadioButton;
	
	private FieldEditorPreferencePage page;
	private FileFieldEditor representationFileEditor;

	private String representationFile; 

	private Boolean isDefault = true;

	public ChooseRepresentationDialog(Shell parentShell) {
		super(parentShell);
		representationFile = VisualRepresentation.DEFAULT_REPRESENTATION_PATH;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);    
		
		Composite radioButtonGroupContainer = new Composite(container, SWT.NONE);
		radioButtonGroupContainer.setLayout(new GridLayout());
		radioButtonGroupContainer.setSize(200, 100);

		defaultRadioButton = new Button(radioButtonGroupContainer, SWT.RADIO);
		defaultRadioButton.setText("Default");
		defaultRadioButton.setSelection(true);

		customRadioButton = new Button(radioButtonGroupContainer, SWT.RADIO);
		customRadioButton.setText("Custom");
		
		
		page = new FieldEditorPreferencePage(FieldEditorPreferencePage.GRID) {  
			@Override            
			protected void createFieldEditors() { 

				representationFileEditor = new FileFieldEditor("", "", getFieldEditorParent());
				representationFileEditor.setFileExtensions(new String[]{"*.xml", "*.*"});
				addField(representationFileEditor);
			}

			@Override 
			public void createControl(Composite parentComposite) {     
				noDefaultAndApplyButton();      
				super.createControl(parentComposite);  
			} 

		};
		page.createControl(container);
		Control pageControl = page.getControl();
		pageControl.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Listener listener = new Listener() {

			public void handleEvent(Event event) {

				Button button = (Button) event.widget;

				if (defaultRadioButton.equals(button)) {
					isDefault = true;
				}
				else {
					isDefault = false;
					representationFileEditor.setFocus();
				}
			}
		};

		defaultRadioButton.addListener(SWT.Selection, listener);
		customRadioButton.addListener(SWT.Selection, listener);
		
		return pageControl;
	}

	protected void buttonPressed(int buttonId) { 
		if (buttonId == IDialogConstants.OK_ID) { 
			if (isDefault || 
					(representationFileEditor.getStringValue() != null
							&& representationFileEditor.getStringValue().equals(""))) {
				representationFile = VisualRepresentation.DEFAULT_REPRESENTATION_PATH;
			} else { 
				representationFile = representationFileEditor.getStringValue();
			}
		}
		super.buttonPressed(buttonId); 
	} 


	// overriding this method allows to set the
	// title of the custom dialog
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Choose representation");
	}

	@Override
	protected Point getInitialSize() {
		return new Point(400, 200);
	}

	public String getRepresentationFile() {
		return representationFile;
	}
}
