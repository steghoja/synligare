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
package org.eclipse.eatop.workspace.ui.preferences;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.eatop.common.preferences.IEastADLPreferenceConstants;
import org.eclipse.eatop.common.resource.impl.EastADLXMLResourceImpl;
import org.eclipse.eatop.workspace.natures.EastADLNature;
import org.eclipse.eatop.workspace.preferences.IEastADLWorkspacePreferenceConstants;
import org.eclipse.eatop.workspace.ui.internal.Activator;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.sphinx.platform.ui.preferences.AbstractPreferenceAndPropertyPage;
import org.eclipse.sphinx.platform.util.PlatformLogUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

public class EastADLPreferencePage extends AbstractPreferenceAndPropertyPage {

	// Loading Group Fields
	private Group fLoadingGroup;
	private BooleanFieldEditorEx fSchemaValidationField;
	private RadioGroupFieldEditor fShemaValidationSeverityEditor;
	private Composite shemaValidationSeverityEditorParent;
	private BooleanFieldEditorEx fProblemIndicationField;
	private IntegerFieldEditor fMaxProblemEditor;
	private BooleanFieldEditorEx fCacheResources;
	private FieldEditor fClearCache;

	// UUID Handling Group Fields
	private Group fUuidHandlingGroup;
	private BooleanFieldEditorEx fSyncUuidField;

	public EastADLPreferencePage() {
		this(EastADLNature.ID, GRID);
	}

	public EastADLPreferencePage(String requiredProjectNatureId, int style) {
		super(requiredProjectNatureId, style);

		setDescription(EastADLPreferenceMessages.EastADLPreferencePage_description);
	}

	@Override
	protected String getPreferencePageID() {
		return IEastADLWorkspacePreferenceConstants.EAST_ADL_PREFERENCE_PAGE_ID;
	}

	@Override
	protected String getPropertyPageID() {
		return null;
	}

	@Override
	protected IPreferenceStore doGetPreferenceStore() {
		return Activator.getPlugin().getCommonPreferenceStore();
	}

	@Override
	protected void enablePreferenceContent(boolean useProjectSpecificSettings) {
		// Nothing to do
	}

	@Override
	protected void addFields(Composite parent) {
		addLoadingGroup(parent);
		addUuidHandlingGroup(parent);
	}

	private void addLoadingGroup(Composite parent) {
		fLoadingGroup = new Group(parent, SWT.NONE);
		fLoadingGroup.setText(EastADLPreferenceMessages.EastADLPreferencePage_loadingGroupText);

		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.verticalSpacing = convertVerticalDLUsToPixels(6);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
		gridData.horizontalSpan = 2;

		fSchemaValidationField = new BooleanFieldEditorEx(IEastADLPreferenceConstants.PREF_XSD_VALIDATION_ON_LOAD,
				EastADLPreferenceMessages.EastADLPreferencePage_schemaValidationFieldLabel, BooleanFieldEditor.DEFAULT, fLoadingGroup);
		addField(fSchemaValidationField);
		fCacheResources = new BooleanFieldEditorEx(IEastADLPreferenceConstants.PREF_USE_BINARY_RESOURCE,
				EastADLPreferenceMessages.EastADLPreferencePage_useBinaryResources, BooleanFieldEditor.DEFAULT, fLoadingGroup);
		addField(fCacheResources);
		fClearCache = new FieldEditor() {
			{
				createControl(fLoadingGroup);
			}
			private Button button;

			@Override
			protected void adjustForNumColumns(int numColumns) {
				if (button != null) {
					((GridData) button.getLayoutData()).horizontalSpan = numColumns;
				}
			}

			@Override
			protected void doFillIntoGrid(Composite parent, int numColumns) {
				button = getChangeControl(parent);
				GridData gd = new GridData();
				gd.horizontalSpan = numColumns;
				button.setLayoutData(gd);
			}

			@Override
			protected void doLoad() {
			}

			@Override
			protected void doLoadDefault() {
			}

			@Override
			protected void doStore() {
			}

			@Override
			public int getNumberOfControls() {
				return 2;
			}

			protected Button getChangeControl(Composite parent) {
				if (button == null) {
					button = new Button(parent, SWT.PUSH | SWT.CENTER);
					button.setText("Clear cache"); //$NON-NLS-1$
					button.setFont(parent.getFont());
					button.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							try {
								IWorkbench wb = PlatformUI.getWorkbench();
								IProgressService ps = wb.getProgressService();
								ps.busyCursorWhile(new IRunnableWithProgress() {
									@Override
									public void run(IProgressMonitor monitor) {
										EastADLXMLResourceImpl.clearCache(monitor);
									}
								});
							} catch (Exception ex) {
								PlatformLogUtil.logAsError(Activator.getPlugin(), ex);
							}
						}
					});
					button.addDisposeListener(new DisposeListener() {
						@Override
						public void widgetDisposed(DisposeEvent event) {
							button = null;
						}
					});
				} else {
					checkParent(button, parent);
				}
				return button;
			}

			@Override
			public void setEnabled(boolean enabled, Composite parent) {
				if (button != null) {
					button.setEnabled(enabled);
					button.redraw();
				}
			};
		};
		addField(fClearCache);

		shemaValidationSeverityEditorParent = new Composite(fLoadingGroup, SWT.NULL);

		fShemaValidationSeverityEditor = new RadioGroupFieldEditor(IEastADLPreferenceConstants.PREF_XSD_VALIDATION_SEVERITY_LEVEL,
				EastADLPreferenceMessages.EastADLPreferencePage_schemaValidationSeverityLevel, 3, new String[][] {
						{ EastADLPreferenceMessages.EastADLPreferencePage_ErrorLabel, Integer.toString(IEastADLPreferenceConstants.SEVERITY_ERROR) },
						{ EastADLPreferenceMessages.EastADLPreferencePage_WarningLabel,
								Integer.toString(IEastADLPreferenceConstants.SEVERITY_WARNING) },
						{ EastADLPreferenceMessages.EastADLPreferencePage_InfoLabel, Integer.toString(IEastADLPreferenceConstants.SEVERITY_INFO) } },
				shemaValidationSeverityEditorParent);
		addField(fShemaValidationSeverityEditor);

		fProblemIndicationField = new BooleanFieldEditorEx(IEastADLPreferenceConstants.PREF_LIMIT_PROBLEM_INDICATION_ON_LOAD,
				EastADLPreferenceMessages.EastADLPreferencePage_problemIndicationFieldLabel, BooleanFieldEditor.DEFAULT, fLoadingGroup);
		fProblemIndicationField.getChangeControl(fLoadingGroup).addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				updateMaxProblemEditor();
			}
		});
		addField(fProblemIndicationField);

		fMaxProblemEditor = new IntegerFieldEditor(IEastADLPreferenceConstants.PREF_MAX_PROBLEM_REPORTED_ON_LOAD,
				EastADLPreferenceMessages.EastADLPreferencePage_maxProblemEditorlabel, fLoadingGroup);
		fMaxProblemEditor.setValidRange(0, 1000);
		addField(fMaxProblemEditor);
		fMaxProblemEditor.setEnabled(getPreferenceStore().getBoolean(IEastADLPreferenceConstants.PREF_LIMIT_PROBLEM_INDICATION_ON_LOAD),
				fLoadingGroup);

		fLoadingGroup.setLayoutData(gridData);
		fLoadingGroup.setLayout(gridLayout);
		Dialog.applyDialogFont(fLoadingGroup);
	}

	private void addUuidHandlingGroup(Composite parent) {
		fUuidHandlingGroup = new Group(parent, SWT.NONE);
		fUuidHandlingGroup.setText(EastADLPreferenceMessages.EastADLPreferencePage_uuidHandlingGroupText);
		GridLayout gridLayout = new GridLayout(2, false);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
		gridData.horizontalSpan = 2;

		fSyncUuidField = new BooleanFieldEditorEx(IEastADLPreferenceConstants.PREF_SYNC_UUIDS,
				EastADLPreferenceMessages.EastADLPreferencePage_syncUuidFieldLabel, BooleanFieldEditor.DEFAULT, fUuidHandlingGroup);
		addField(fSyncUuidField);
		fUuidHandlingGroup.setLayoutData(gridData);
		fUuidHandlingGroup.setLayout(gridLayout);
		Dialog.applyDialogFont(fUuidHandlingGroup);
	}

	private void updateMaxProblemEditor() {
		Button b = fProblemIndicationField.getChangeControl(fLoadingGroup);
		fMaxProblemEditor.getTextControl(fLoadingGroup).setEnabled(b.getSelection());
		fMaxProblemEditor.getLabelControl(fLoadingGroup).setEnabled(b.getSelection());
	}

	public class BooleanFieldEditorEx extends BooleanFieldEditor {

		private Button fChangeControl;

		/**
		 * @see BooleanFieldEditor#BooleanFieldEditor(java.lang.String, java.lang.String, int,
		 *      org.eclipse.swt.widgets.Composite)
		 */
		public BooleanFieldEditorEx(String name, String labelText, int style, Composite parent) {
			super(name, labelText, style, parent);
		}

		/**
		 * @see org.eclipse.jface.preference.BooleanFieldEditor#getChangeControl(Composite)
		 */
		@Override
		public Button getChangeControl(Composite parent) {
			if (fChangeControl == null) {
				fChangeControl = super.getChangeControl(parent);
			}
			return fChangeControl;
		}
	}
}