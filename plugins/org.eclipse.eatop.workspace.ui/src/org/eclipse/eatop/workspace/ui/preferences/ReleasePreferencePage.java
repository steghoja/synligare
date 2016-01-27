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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.eatop.common.metamodel.EastADLMetaModelVersionData;
import org.eclipse.eatop.common.metamodel.EastADLReleaseDescriptor;
import org.eclipse.eatop.common.preferences.IEastADLPreferenceConstants;
import org.eclipse.eatop.workspace.preferences.IEastADLWorkspacePreferenceConstants;
import org.eclipse.eatop.workspace.ui.internal.Activator;
import org.eclipse.eatop.workspace.ui.internal.messages.Messages;
import org.eclipse.eatop.workspace.ui.internal.preferences.ModelConverterDescriptionLabels;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sphinx.emf.metamodel.IMetaModelDescriptor;
import org.eclipse.sphinx.emf.metamodel.MetaModelDescriptorRegistry;
import org.eclipse.sphinx.emf.resource.IModelConverter;
import org.eclipse.sphinx.emf.resource.IModelConverterDescription;
import org.eclipse.sphinx.emf.resource.ModelConverterRegistry;
import org.eclipse.sphinx.emf.workspace.loading.ModelLoadManager;
import org.eclipse.sphinx.emf.workspace.saving.ModelSaveManager;
import org.eclipse.sphinx.emf.workspace.ui.saving.BasicModelSaveableFilter;
import org.eclipse.sphinx.platform.util.ExtendedPlatform;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

public class ReleasePreferencePage extends PreferenceAndPropertyPage {

	private String LABEL_PATTERN = "%1s (release %2s)"; //$NON-NLS-1$

	private static final String PROP_PAGE_ID = "org.eclipse.eatop.workspace.ui.propertyPages.eastadl.release"; //$NON-NLS-1$

	private static final String VERSION_SEPARATOR = "."; //$NON-NLS-1$

	private static final String OTHER_RESOURCE_VERSION_VALUE = "OTHER"; //$NON-NLS-1$

	private static final String DEFAULT_RESOURCE_VERSION = null;

	// Release fields
	private String initialRelease;
	private Group releaseGroup;
	private ComboFieldEditor releaseField;
	private boolean releaseValueChanged = false;

	// Resource Version fields
	private String initialResourceVersion;
	private boolean isResourceVersionSameAsOriginal;
	private Composite resourceVersionRadioBox;
	private Label resourceVersionLabel;
	private Combo resourceVersionCombo;
	private ModelConverterDescriptionLabels converterDescriptionLabels;

	final Map<String, EastADLReleaseDescriptor> resourceVersionNamesAndValues = new HashMap<String, EastADLReleaseDescriptor>();

	@Override
	protected String getPreferencePageID() {
		return IEastADLWorkspacePreferenceConstants.EAST_ADL_RELEASE_PREFERENCE_PAGE_ID;
	}

	@Override
	protected String getPropertyPageID() {
		return PROP_PAGE_ID;
	}

	@Override
	protected IPreferenceStore doGetPreferenceStore() {
		if (isProjectPreferencePage()) {
			return getProjectPreferenceStore(getProject());
		} else {
			return getWorkspacePreferenceStore();
		}
	}

	@Override
	protected boolean hasProjectSpecificOptions(IProject project) {
		return getProjectPreferenceStore(project).isDefault(IEastADLWorkspacePreferenceConstants.PREF_EAST_ADL_RELEASE) == false
				|| getProjectPreferenceStore(project).isDefault(IEastADLWorkspacePreferenceConstants.PREF_EAST_ADL_RESOURCE_VERSION) == false;
	}

	@Override
	protected void createFieldEditors() {
		addReleaseGroup(getFieldEditorParent());
	}

	@Override
	protected void initialize() {
		super.initialize();

		updateRelease(initialRelease);

		if (isProjectPreferencePage()) {
			updateResourceVersionWidgets();
			updateResourceVersionCombo(initialRelease, initialResourceVersion);
		}
	}

	@Override
	protected void enablePreferenceContent(boolean useProjectSpecificSettings) {
		releaseField.setEnabled(useProjectSpecificSettings, releaseGroup);
		resourceVersionLabel.setEnabled(useProjectSpecificSettings);
		setEnabledDeep(resourceVersionRadioBox, useProjectSpecificSettings);
		updateResourceVersionWidgets();
	}

	@Override
	protected void useProjectSettingsEnabled() {
		// Try to detect the exact EAST-ADL release from files, rather than using a fixed default
		EastADLReleaseDescriptor eastadlRelease = detectRelease(getProject());

		setMessage(NLS.bind(EastADLPreferenceMessages.ReleasePreferencePage_detectedRelease, eastadlRelease.getName()), INFORMATION);

		updateRelease(eastadlRelease.getIdentifier());
		updateResourceVersionCombo(eastadlRelease, DEFAULT_RESOURCE_VERSION);
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		super.propertyChange(event);

		if (isProjectPreferencePage()) {
			if (event.getSource().equals(releaseField) && event.getProperty().equals(FieldEditor.VALUE)) {
				releaseValueChanged = event.getNewValue().equals(initialRelease) == false;
				updateResourceVersionCombo((String) event.getNewValue(), DEFAULT_RESOURCE_VERSION);
			}
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean performOk() {
		super.performOk();

		if (!isProjectPreferencePage()) {
			return true;
		}

		if (useProjectSettings()) {
			String selectedResourceVersion;

			if (isResourceVersionSameAsOriginal) {
				selectedResourceVersion = IEastADLPreferenceConstants.PREF_VALUE_AUTO_CONVERSION_MODE;
			} else {
				selectedResourceVersion = getSelectedResourceVersion().getIdentifier();
			}

			getPreferenceStore().setValue(IEastADLWorkspacePreferenceConstants.PREF_EAST_ADL_RESOURCE_VERSION, selectedResourceVersion);
		} else {
			getPreferenceStore().setToDefault(IEastADLWorkspacePreferenceConstants.PREF_EAST_ADL_RELEASE);
			getPreferenceStore().setToDefault(IEastADLWorkspacePreferenceConstants.PREF_EAST_ADL_RESOURCE_VERSION);

			// determine wether a reload is necessary based on workspace level preference
			IPreferenceStore workspacePreferenceStore = getWorkspacePreferenceStore();

			releaseValueChanged = initialRelease.equals(workspacePreferenceStore
					.getString(IEastADLWorkspacePreferenceConstants.PREF_EAST_ADL_RELEASE)) == false;
		}

		// Release is about to change?
		if (releaseValueChanged) {
			// Give user a chance to save pending changes on EAST-ADL model with old release
			final boolean canceled[] = new boolean[1];
			final IProject project = getProject();
			PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
				@Override
				public void run() {
					SafeRunner.run(new SafeRunnable(Messages.error_failedToSaveModelsInWorkbench) {
						@Override
						public void run() {
							IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
							if (window == null) {
								IWorkbenchWindow[] windows = PlatformUI.getWorkbench().getWorkbenchWindows();
								if (windows.length > 0) {
									window = windows[0];
								}
							}
							if (window != null) {
								canceled[0] = !PlatformUI.getWorkbench().saveAll(window, window, new BasicModelSaveableFilter(project), true);
							} else {
								// TODO Save only EAST-ADL model
								ModelSaveManager.INSTANCE.saveProject(project, false, null);
								canceled[0] = false;
							}
						}
					});
				}
			});

			// Abort preference change if saving has been canceled by user
			if (canceled[0]) {
				return false;
			}

			// Force reset of dirty information on all models in given project for clearing dirty information of those
			// models that have not been taken into account by the save operation (happens e.g. when user deselects some
			// or all of them before proceeding with the save operation)
			Collection<IProject> projectGroup = ExtendedPlatform.getProjectGroup(project, true);
			for (IProject proj : projectGroup) {
				ModelSaveManager.INSTANCE.setSaved(proj);
			}

			// Schedule unload of EAST-ADL model with old release and load of EAST-ADL model with new release as a
			// background operation
			ModelLoadManager.INSTANCE.reloadProject(project, false, EastADLReleaseDescriptor.INSTANCE, true, null);
		}

		return true;
	}

	private void addReleaseGroup(final Composite parent) {
		getWorkspacePreferenceStore().setDefault(IEastADLWorkspacePreferenceConstants.PREF_EAST_ADL_RELEASE,
				IEastADLWorkspacePreferenceConstants.PREF_EAST_ADL_RELEASE_DEFAULT);

		if (isProjectPreferencePage()) {
			// treat the missing project release preference based on what is in effect on the workspace level
			getPreferenceStore().setDefault(IEastADLWorkspacePreferenceConstants.PREF_EAST_ADL_RELEASE,
					getWorkspacePreferenceStore().getString(IEastADLWorkspacePreferenceConstants.PREF_EAST_ADL_RELEASE));
		}

		initialRelease = getPreferenceStore().getString(IEastADLWorkspacePreferenceConstants.PREF_EAST_ADL_RELEASE);

		releaseGroup = new Group(parent, SWT.NONE);
		releaseGroup.setText(EastADLPreferenceMessages.ReleasePreferencePage_releaseGroupText);

		// The list of supported EAST-ADL meta-model releases
		List<EastADLReleaseDescriptor> supportedReleases = MetaModelDescriptorRegistry.INSTANCE.getDescriptors(EastADLReleaseDescriptor.INSTANCE,
				true);

		String[][] entryNamesAndValues = new String[supportedReleases.size()][2];
		for (int index = 0; index < supportedReleases.size(); index++) {
			EastADLReleaseDescriptor eastadlRelease = supportedReleases.get(index);
			entryNamesAndValues[index][0] = String.format(LABEL_PATTERN, eastadlRelease.getName(), getVersionNumber(eastadlRelease));
			entryNamesAndValues[index][1] = eastadlRelease.getIdentifier();
		}

		releaseField = new ComboFieldEditor(IEastADLWorkspacePreferenceConstants.PREF_EAST_ADL_RELEASE,
				EastADLPreferenceMessages.ReleasePreferencePage_releaseFieldLabel, entryNamesAndValues, releaseGroup);
		addField(releaseField);

		// set layout of release group after field addition since it is being altered when field editors are added
		if (isProjectPreferencePage()) {
			GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
			gridData.horizontalSpan = 2;
			releaseGroup.setLayoutData(gridData);

			GridLayout gridLayout = new GridLayout(2, false);
			releaseGroup.setLayout(gridLayout);
		} else {
			GridData gridData = new GridData(SWT.FILL, SWT.TOP, true, false);
			gridData.horizontalSpan = 2;
			releaseGroup.setLayoutData(gridData);

			GridLayout gridLayout = new GridLayout(2, false);
			releaseGroup.setLayout(gridLayout);
		}

		if (isProjectPreferencePage()) {
			if (getPreferenceStore().isDefault(IEastADLWorkspacePreferenceConstants.PREF_EAST_ADL_RESOURCE_VERSION)) {
				initialResourceVersion = IEastADLPreferenceConstants.PREF_EAST_ADL_RESOURCE_VERSION_DEFAULT;
			} else {
				initialResourceVersion = getPreferenceStore().getString(IEastADLWorkspacePreferenceConstants.PREF_EAST_ADL_RESOURCE_VERSION);
			}
			isResourceVersionSameAsOriginal = initialResourceVersion.equals(IEastADLPreferenceConstants.PREF_VALUE_AUTO_CONVERSION_MODE);

			resourceVersionLabel = new Label(releaseGroup, SWT.LEFT);
			resourceVersionLabel.setText(EastADLPreferenceMessages.ReleasePreferencePage_resourceVersionLabel);
			{
				GridData gridData = new GridData(SWT.FILL, SWT.TOP, true, false);
				gridData.horizontalSpan = 2;
				resourceVersionLabel.setLayoutData(gridData);
			}

			resourceVersionRadioBox = new Composite(releaseGroup, SWT.NONE);
			{
				GridData gridData = new GridData(SWT.FILL, SWT.TOP, true, false);
				gridData.horizontalSpan = 2;
				resourceVersionRadioBox.setLayoutData(gridData);

				GridLayout gridLayout = new GridLayout();
				gridLayout.marginWidth = 0;
				gridLayout.marginHeight = 0;
				gridLayout.horizontalSpacing = 8;
				resourceVersionRadioBox.setLayout(gridLayout);
			}

			createResourceVersionRadioButton(EastADLPreferenceMessages.ReleasePreferencePage_sameAsInOriginalButtonLabel,
					IEastADLPreferenceConstants.PREF_EAST_ADL_RESOURCE_VERSION_DEFAULT, isResourceVersionSameAsOriginal);
			createResourceVersionRadioButton(EastADLPreferenceMessages.ReleasePreferencePage_otherResourceVersionButtonLabel,
					OTHER_RESOURCE_VERSION_VALUE, isResourceVersionSameAsOriginal == false);

			resourceVersionCombo = new Combo(releaseGroup, SWT.READ_ONLY);
			{
				GridData gridData = new GridData(SWT.FILL, SWT.TOP, true, false);
				gridData.horizontalSpan = 2;
				gridData.horizontalIndent = convertHorizontalDLUsToPixels(16);
				resourceVersionCombo.setLayoutData(gridData);
			}
			resourceVersionCombo.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					updateConverterDescriptionArea();
				}
			});

			ScrolledComposite scrolledComposite = new ScrolledComposite(releaseGroup, SWT.BORDER | SWT.V_SCROLL);
			{
				GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
				gridData.horizontalSpan = 2;
				gridData.horizontalIndent = convertHorizontalDLUsToPixels(16);
				scrolledComposite.setLayoutData(gridData);
			}

			converterDescriptionLabels = new ModelConverterDescriptionLabels(scrolledComposite);
			scrolledComposite.setContent(converterDescriptionLabels);
			scrolledComposite.setMinHeight(convertHeightInCharsToPixels(3));
			scrolledComposite.setAlwaysShowScrollBars(true);
		}

		Dialog.applyDialogFont(releaseGroup);
	}

	private String getVersionNumber(EastADLReleaseDescriptor releaseDesc) {
		EastADLMetaModelVersionData versionData = releaseDesc.getEastADLVersionData();
		StringBuilder versionNumber = new StringBuilder();
		versionNumber.append(versionData.getMajor());
		versionNumber.append(VERSION_SEPARATOR);
		versionNumber.append(versionData.getMinor());
		versionNumber.append(VERSION_SEPARATOR);
		versionNumber.append(versionData.getRevision());
		return versionNumber.toString();
	}

	private void updateRelease(String eastadlReleaseId) {
		// To trick combo field into selecting the different value
		// TODO replace combo field with custom combo
		String value = getPreferenceStore().getString(IEastADLWorkspacePreferenceConstants.PREF_EAST_ADL_RELEASE);
		getPreferenceStore().setValue(IEastADLWorkspacePreferenceConstants.PREF_EAST_ADL_RELEASE, eastadlReleaseId);
		releaseField.load();
		getPreferenceStore().setValue(IEastADLWorkspacePreferenceConstants.PREF_EAST_ADL_RELEASE, value);
	}

	private void updateResourceVersionCombo(IMetaModelDescriptor eastadlRelease, String resourceVersion) {
		List<EastADLReleaseDescriptor> relevantVersions = new ArrayList<EastADLReleaseDescriptor>();

		List<IModelConverter> converters = ModelConverterRegistry.INSTANCE.getConverters();
		for (IModelConverter modelConverter : converters) {
			// Check if meta-model version matches
			if (modelConverter.getMetaModelVersionDescriptor().equals(eastadlRelease)) {
				relevantVersions.add((EastADLReleaseDescriptor) modelConverter.getResourceVersionDescriptor());
			}
		}

		Collections.sort(relevantVersions);

		String[] resourceVersionNames = new String[relevantVersions.size()];
		int i = 0;
		int selectedIndex = 0;
		for (EastADLReleaseDescriptor relevantVersion : relevantVersions) {
			String name = "EAST-ADL " + getVersionNumber(relevantVersion); //$NON-NLS-1$
			resourceVersionNames[i] = name;
			String identifier = relevantVersion.getIdentifier();
			resourceVersionNamesAndValues.put(name, relevantVersion);
			// Used to select the serialized identifier in the Combo
			if (identifier.equals(resourceVersion)) {
				selectedIndex = i;
			}
			i++;
		}
		resourceVersionCombo.setItems(resourceVersionNames);

		if (relevantVersions.size() > 0) {
			resourceVersionCombo.select(selectedIndex);
		}

		updateConverterDescriptionArea();
	}

	private void updateResourceVersionCombo(String eastadlReleaseId, String resourceVersion) {
		IMetaModelDescriptor eastadlRelease = MetaModelDescriptorRegistry.INSTANCE.getDescriptor(eastadlReleaseId);
		updateResourceVersionCombo(eastadlRelease, resourceVersion);
	}

	private void updateConverterDescriptionArea() {
		if (isResourceVersionSameAsOriginal == false) {
			EastADLReleaseDescriptor eastadlRelease = getSelectedResourceVersion();

			if (eastadlRelease != null) {
				Collection<IModelConverterDescription> convDescs = ModelConverterRegistry.INSTANCE
						.getConverterDescriptions(getSelectedResourceVersion());
				if (convDescs.size() > 0) {
					converterDescriptionLabels.setConverterDescription(convDescs.iterator().next());
					setEnabledDeep(converterDescriptionLabels, true);
					return;
				}
			}
		}

		converterDescriptionLabels.setConverterDescription(null);
		setEnabledDeep(converterDescriptionLabels, false);
	}

	private void updateResourceVersionWidgets() {
		boolean enabled = isResourceVersionSameAsOriginal == false && useProjectSettings();
		resourceVersionCombo.setEnabled(enabled);
		setEnabledDeep(converterDescriptionLabels, enabled);
	}

	private EastADLReleaseDescriptor getSelectedResourceVersion() {
		int selectedIndex = resourceVersionCombo.getSelectionIndex();

		if (selectedIndex == -1) {
			return null;
		}

		String selectedName = resourceVersionCombo.getItem(selectedIndex);
		return resourceVersionNamesAndValues.get(selectedName);
	}

	private Button createResourceVersionRadioButton(String label, String value, boolean selected) {
		Button radio = new Button(resourceVersionRadioBox, SWT.RADIO | SWT.LEFT);
		radio.setText(label);
		radio.setData(value);
		radio.setSelection(selected);
		radio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				String value = (String) event.widget.getData();
				isResourceVersionSameAsOriginal = value.equals(IEastADLPreferenceConstants.PREF_VALUE_AUTO_CONVERSION_MODE);
				updateResourceVersionWidgets();
				updateConverterDescriptionArea();
			}
		});
		return radio;
	}

	private void setEnabledDeep(Composite parent, boolean isEnabled) {
		parent.setEnabled(isEnabled);
		for (Control c : parent.getChildren()) {
			c.setEnabled(isEnabled);
		}
	}

	private EastADLReleaseDescriptor detectRelease(IProject project) {
		Collection<IFile> files = ExtendedPlatform.getAllFiles(project, true);
		for (IFile file : files) {
			final IMetaModelDescriptor mmDescriptorFromFirstFile = MetaModelDescriptorRegistry.INSTANCE.getDescriptor(file);
			if (mmDescriptorFromFirstFile instanceof EastADLReleaseDescriptor) {
				// Take EAST-ADL release behind current file and assume it to be the EAST-ADL release of whole project.
				// This might be inappropriate as there might be
				// EAST-ADL XML files based on different EAST-ADL releases in the same project. But users can still go
				// and manually adjust the project's AUTOASR release in that case.
				return (EastADLReleaseDescriptor) mmDescriptorFromFirstFile;
			}
		}

		// use default if nothing was found
		return (EastADLReleaseDescriptor) MetaModelDescriptorRegistry.INSTANCE
				.getDescriptor(IEastADLWorkspacePreferenceConstants.PREF_EAST_ADL_RELEASE_DEFAULT);
	}

	private IPreferenceStore getProjectPreferenceStore(IProject project) {
		return new ScopedPreferenceStore(new ProjectScope(project), org.eclipse.eatop.workspace.internal.Activator.getPlugin().getBundle()
				.getSymbolicName());
	}

	private IPreferenceStore getWorkspacePreferenceStore() {
		return Activator.getPlugin().getWorkspacePreferenceStore();
	}

}
