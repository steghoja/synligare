/**
 * <copyright>
 * 
 * Copyright (c) 2014 Continental AG and others.
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 
 * which accompanies this distribution, and is
 * available at http://www.eclipse.org/org/documents/epl-v10.php
 * 
 * Contributors: 
 *     Continental AG - Initial API and implementation
 * 
 * </copyright>
 */
package org.eclipse.eatop.workspace.ui.preferences;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.eatop.workspace.natures.EastADLNature;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.window.Window;
import org.eclipse.sphinx.platform.ui.dialogs.ProjectSelectionDialog;
import org.eclipse.sphinx.platform.ui.fields.IField;
import org.eclipse.sphinx.platform.ui.fields.IFieldListener;
import org.eclipse.sphinx.platform.ui.fields.SelectionButtonField;
import org.eclipse.sphinx.platform.ui.internal.util.LayoutUtil;
import org.eclipse.sphinx.platform.util.ExtendedPlatform;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.eclipse.ui.dialogs.PreferencesUtil;

@SuppressWarnings("restriction")
public abstract class PreferenceAndPropertyPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage, IWorkbenchPropertyPage {

	private Link fChangeWorkspaceSettings;
	private SelectionButtonField fUseProjectSettings;

	// Project or null when used as preference page
	private IProject fProject;
	// Page data
	private Map<String, Object> fData;

	public static final String DATA_NO_LINK = "PropertyAndPreferencePage.nolink"; //$NON-NLS-1$

	public PreferenceAndPropertyPage() {
		super(GRID);
	}

	@Override
	public void init(IWorkbench workbench) {

	}

	@Override
	public IAdaptable getElement() {
		return fProject;
	}

	@Override
	public void setElement(IAdaptable element) {
		fProject = (IProject) element.getAdapter(IResource.class);
	}

	protected abstract boolean hasProjectSpecificOptions(IProject project);

	/**
	 * Enable/Disable the fields to edit for a project
	 */
	protected abstract void enablePreferenceContent(boolean useProjectSpecificSettings);

	protected abstract String getPreferencePageID();

	protected abstract String getPropertyPageID();

	@Override
	protected Label createDescriptionLabel(Composite parent) {
		if (isProjectPreferencePage()) {
			Composite composite = new Composite(parent, SWT.NONE);
			composite.setFont(parent.getFont());
			GridLayout layout = new GridLayout();
			layout.marginHeight = 0;
			layout.marginWidth = 0;
			layout.numColumns = 2;
			composite.setLayout(layout);
			composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

			IFieldListener listener = new IFieldListener() {
				@Override
				public void dialogFieldChanged(IField field) {
					boolean enabled = ((SelectionButtonField) field).isSelected();
					enableProjectSpecificSettings(enabled);

					if (enabled && getData() != null) {
						applyData(getData());
					}

					if (enabled) {
						useProjectSettingsEnabled();
					}
				}
			};

			fUseProjectSettings = new SelectionButtonField(SWT.CHECK);
			fUseProjectSettings.addFieldListener(listener);
			fUseProjectSettings.setLabelText(EastADLPreferenceMessages.EastADLPreferencePage_enableProjectSpecificSettings);
			fUseProjectSettings.fillIntoGrid(composite, 1);
			fUseProjectSettings.setSelectionWithoutEvent(true);

			if (offerLink()) {
				// Access the workspace settings
				fChangeWorkspaceSettings = createLink(composite, EastADLPreferenceMessages.EastADLPreferencePage_configureWorkspaceSettings);
				fChangeWorkspaceSettings.setLayoutData(new GridData(SWT.END, SWT.CENTER, false, false));
			} else {
				LayoutUtil.setHorizontalSpan(fUseProjectSettings.getSelectionButton(null), 2);
			}

			Label horizontalLine = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
			horizontalLine.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false, 2, 1));
			horizontalLine.setFont(composite.getFont());
		} else if (supportsProjectSpecificOptions() && offerLink()) {
			// Access the project settings
			fChangeWorkspaceSettings = createLink(parent, EastADLPreferenceMessages.EastADLPreferencePage_configureProjectSpecificSettings);
			fChangeWorkspaceSettings.setLayoutData(new GridData(SWT.END, SWT.CENTER, true, false));
		}

		return super.createDescriptionLabel(parent);
	}

	/*
	 * @see org.eclipse.jface.preference.IPreferencePage#createContents(Composite)
	 */
	@Override
	protected Control createContents(Composite parent) {
		Control contents = super.createContents(parent);

		if (isProjectPreferencePage()) {
			boolean useProjectSettings = hasProjectSpecificOptions(getProject());
			enableProjectSpecificSettings(useProjectSettings);
		}

		return contents;
	}

	protected boolean isProjectPreferencePage() {
		return fProject != null;
	}

	protected IProject getProject() {
		return fProject;
	}

	protected boolean supportsProjectSpecificOptions() {
		return getPropertyPageID() != null;
	}

	protected final void openWorkspacePreferences(Object data) {
		String id = getPreferencePageID();
		PreferencesUtil.createPreferenceDialogOn(getShell(), id, new String[] { id }, data).open();
	}

	protected final void openProjectProperties(IProject project, Object data) {
		String id = getPropertyPageID();
		if (id != null) {
			PreferencesUtil.createPropertyDialogOn(getShell(), project, id, new String[] { id }, data).open();
		}
	}

	protected void enableProjectSpecificSettings(boolean useProjectSpecificSettings) {
		fUseProjectSettings.setSelection(useProjectSpecificSettings);
		enablePreferenceContent(useProjectSpecificSettings);
		updateLinkVisibility();
	}

	/**
	 * called when the user explicitly activates the corresponding checkbox
	 */
	protected void useProjectSettingsEnabled() {

	}

	@SuppressWarnings("unchecked")
	@Override
	public void applyData(Object data) {
		if (data instanceof Map<?, ?>) {
			fData = (Map<String, Object>) data;
		}
	}

	protected Map<String, Object> getData() {
		return fData;
	}

	protected boolean useProjectSettings() {
		return isProjectPreferencePage() && fUseProjectSettings != null && fUseProjectSettings.isSelected();
	}

	protected boolean offerLink() {
		return fData == null || !Boolean.TRUE.equals(fData.get(DATA_NO_LINK));
	}

	private final void doLinkActivated(Link link) {
		Map<String, Object> data = getData();
		if (data == null) {
			data = new HashMap<String, Object>();
		}
		data.put(DATA_NO_LINK, Boolean.TRUE);

		if (isProjectPreferencePage()) {
			openWorkspacePreferences(data);
		} else {
			Set<IProject> projectsWithSpecifics = new HashSet<IProject>();
			Collection<IProject> projects = ExtendedPlatform.getProjects(EastADLNature.ID);
			for (IProject project : projects) {
				if (hasProjectSpecificOptions(project)) {
					projectsWithSpecifics.add(project);
				}
			}
			ProjectSelectionDialog dialog = new ProjectSelectionDialog(getShell(), projectsWithSpecifics);
			if (dialog.open() == Window.OK) {
				IProject res = (IProject) dialog.getFirstResult();
				openProjectProperties(res.getProject(), data);
			}
		}
	}

	private Link createLink(Composite composite, String text) {
		Link link = new Link(composite, SWT.NONE);
		link.setFont(composite.getFont());
		link.setText("<A>" + text + "</A>"); //$NON-NLS-1$//$NON-NLS-2$
		link.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doLinkActivated((Link) e.widget);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				doLinkActivated((Link) e.widget);
			}
		});
		return link;
	}

	private void updateLinkVisibility() {
		if (fChangeWorkspaceSettings == null || fChangeWorkspaceSettings.isDisposed()) {
			return;
		}

		if (isProjectPreferencePage()) {
			fChangeWorkspaceSettings.setEnabled(!useProjectSettings());
		}
	}
}
