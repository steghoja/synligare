/**
 * <copyright>
 *  
 * Copyright (c) 2014 itemis and others.
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 
 * which accompanies this distribution, and is
 * available at http://www.eclipse.org/org/documents/epl-v10.php
 *  
 * Contributors: 
 *     itemis - Initial API and implementation
 *  
 * </copyright>
 * 
 */
package org.eclipse.eatop.workspace.ui.wizards.pages;

import org.eclipse.eatop.common.metamodel.EastADLReleaseDescriptor;
import org.eclipse.eatop.workspace.preferences.IEastADLWorkspacePreferences;
import org.eclipse.eatop.workspace.ui.messages.WorkspaceUIMessages;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sphinx.emf.workspace.ui.wizards.pages.NewModelFileCreationPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

/**
 * A main page for a wizard that creates a eastADL file resource. The new model file is to be created based on the given
 * {@linkplain NewModelFileProperties new model file properties} (metamodel, ePackage and eClassifier).
 */
public class NewEastADLFileCreationPage extends NewModelFileCreationPage<EastADLReleaseDescriptor> {
	private Button createInitialEAPackageButton;
	private Text initialEAPackageName;

	/**
	 * Creates a new instance of NewEastADLFileCreationPage.
	 * 
	 * @param pageId
	 *            the name of the page
	 * @param selection
	 *            the current resource selection
	 * @param newModelFileProperties
	 *            the {@linkplain NewModelFileProperties new model file properties} selected by previous page or by
	 *            initial setting
	 */
	public NewEastADLFileCreationPage(String pageId, IStructuredSelection selection) {
		super(pageId, selection, IEastADLWorkspacePreferences.EAST_ADL_RELEASE);
	}

	/*
	 * @see
	 * org.eclipse.sphinx.emf.workspace.ui.wizards.pages.NewModelFileCreationPage#createAdditionalControls(org.eclipse
	 * .swt.widgets.Composite)
	 */
	@Override
	protected void createAdditionalControls(Composite parent) {
		createInitialEAPackageGroup(parent);
		Dialog.applyDialogFont(getControl());
	}

	protected void createInitialEAPackageGroup(Composite parent) {
		Group fGroup = new Group(parent, SWT.NONE);
		fGroup.setText(WorkspaceUIMessages.label_EAPackageCreation);
		fGroup.setLayout(new GridLayout(2, false));
		fGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		createInitialEAPackageButton = new Button(fGroup, SWT.CHECK);
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		createInitialEAPackageButton.setLayoutData(gd);
		createInitialEAPackageButton.setText(WorkspaceUIMessages.label_WithEAPackage);
		createInitialEAPackageButton.setSelection(true);

		initialEAPackageName = new Text(fGroup, SWT.SINGLE | SWT.BORDER);
		initialEAPackageName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		initialEAPackageName.setText(WorkspaceUIMessages.text_defaultEaPackageName);
		createInitialEAPackageButton.addSelectionListener(new SelectionAdapter() {

			/*
			 * (non-Javadoc)
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {
				initialEAPackageName.setEnabled(createInitialEAPackageButton.getSelection());
			}
		});

	}

	public String getEAPackageName() {
		if (createInitialEAPackageButton.getSelection() && !(initialEAPackageName.getText().length() == 0)) {
			return initialEAPackageName.getText();
		}
		return null;
	}
}
