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

import java.io.IOException;
import java.net.URI;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.eatop.common.metamodel.EastADLReleaseDescriptor;
import org.eclipse.eatop.workspace.preferences.IEastADLWorkspacePreferenceConstants;
import org.eclipse.eatop.workspace.preferences.IEastADLWorkspacePreferences;
import org.eclipse.eatop.workspace.ui.internal.Activator;
import org.eclipse.eatop.workspace.ui.internal.messages.Messages;
import org.eclipse.eatop.workspace.ui.messages.ErrorUIMessages;
import org.eclipse.eatop.workspace.ui.messages.WorkspaceUIMessages;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sphinx.emf.metamodel.IMetaModelDescriptor;
import org.eclipse.sphinx.emf.metamodel.MetaModelDescriptorRegistry;
import org.eclipse.sphinx.platform.ui.fields.ComboField;
import org.eclipse.sphinx.platform.ui.fields.IField;
import org.eclipse.sphinx.platform.ui.fields.IFieldListener;
import org.eclipse.sphinx.platform.ui.fields.SelectionButtonField;
import org.eclipse.sphinx.platform.util.PlatformLogUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.eclipse.ui.internal.ide.IIDEHelpContextIds;
import org.eclipse.ui.internal.ide.dialogs.ProjectContentsLocationArea;
import org.eclipse.ui.internal.ide.dialogs.ProjectContentsLocationArea.IErrorMessageReporter;

/**
 * Standard main page for a wizard that is creates a project resource.
 */
@SuppressWarnings("restriction")
public class EastADLProjectWizardFirstPage extends WizardPage {

	private String initialProjectFieldValue;

	private Text projectNameField;

	private ReleaseGroup releaseGroup;

	private final String RELEASE_GROUP_TEXT = Messages.page_EastADLPreference_releaseGroupText;

	private ProjectContentsLocationArea locationArea;

	// constants
	private static final int SIZING_TEXT_FIELD_WIDTH = 250;

	/**
	 * Creates a new project creation wizard page.
	 * 
	 * @param pageName
	 *            the name of this page
	 */
	public EastADLProjectWizardFirstPage(String pageName) {
		super(pageName);
		setPageComplete(false);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createControl(Composite parent) {
		initializeDialogUnits(parent);

		final Composite composite = new Composite(parent, SWT.NULL);
		composite.setFont(parent.getFont());
		composite.setLayout(initGridLayout(new GridLayout(1, false), true));
		composite.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));

		createNameGroup(composite);
		createLocationGroup(composite);

		releaseGroup = createReleaseGroup(composite, true);

		createAdditionalGroups(composite);

		// Validate ??
		setPageComplete(validatePage());

		// Show description on opening
		setErrorMessage(null);
		setMessage(null);
		setControl(composite);
		final String contextId = IIDEHelpContextIds.NEW_PROJECT_WIZARD_PAGE;

		PlatformUI.getWorkbench().getHelpSystem().setHelp(composite, contextId);
	}

	protected NameGroup createNameGroup(Composite parent) {
		return new NameGroup(parent);
	}

	protected LocationGroup createLocationGroup(Composite parent) {
		return new LocationGroup(parent);
	}

	protected ReleaseGroup createReleaseGroup(Composite parent, boolean defaultEnabled) {
		return new ReleaseGroup(parent, defaultEnabled);
	}

	/**
	 * This is a hook method for subclasses to add additional Groups to their pages.
	 * 
	 * @param parent
	 */
	protected void createAdditionalGroups(Composite parent) {
		// Adds nothing by default
	}

	/**
	 * Returns the current project location path as entered by the user, or its anticipated initial value. Note that if
	 * the default has been returned the path in a project description used to create a project should not be set.
	 * 
	 * @return the project location path or its anticipated initial value.
	 */
	public IPath getLocationPath() {
		return new Path(locationArea.getProjectLocation());
	}

	/**
	 * /** Returns the current project location URI as entered by the user, or <code>null</code> if a valid project
	 * location has not been entered.
	 * 
	 * @return the project location URI, or <code>null</code>
	 * @since 3.2
	 */
	public URI getLocationURI() {
		return locationArea.getProjectLocationURI();
	}

	/**
	 * Creates a project resource handle for the current project name field value. The project handle is created
	 * relative to the workspace root.
	 * <p>
	 * This method does not create the project resource; this is the responsibility of <code>IProject::create</code>
	 * invoked by the new project resource wizard.
	 * </p>
	 * 
	 * @return the new project resource handle
	 */
	public IProject getProjectHandle() {
		return ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName());
	}

	/**
	 * Returns the current project name as entered by the user, or its anticipated initial value.
	 * 
	 * @return the project name, its anticipated initial value, or <code>null</code> if no project name is known
	 */
	public String getProjectName() {
		if (projectNameField == null) {
			return initialProjectFieldValue;
		}
		return getProjectNameFieldValue();
	}

	/**
	 * Sets the initial project name that this page will use when created. The name is ignored if the
	 * createControl(Composite) method has already been called. Leading and trailing spaces in the name are ignored.
	 * Providing the name of an existing project will not necessarily cause the wizard to warn the user. Callers of this
	 * method should first check if the project name passed already exists in the workspace.
	 * 
	 * @param name
	 *            initial project name for this page
	 * @see IWorkspace#validateName(String, int)
	 */
	public void setInitialProjectName(String name) {
		if (name == null) {
			initialProjectFieldValue = null;
		} else {
			initialProjectFieldValue = name.trim();
			if (locationArea != null) {
				locationArea.updateProjectName(name.trim());
			}
		}
	}

	/*
	 * see @DialogPage.setVisible(boolean)
	 */
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) {
			projectNameField.setFocus();
		}
	}

	/**
	 * Returns the useDefaults.
	 * 
	 * @return boolean
	 */
	public boolean useDefaults() {
		return locationArea.isDefault();
	}

	public EastADLReleaseDescriptor getRelease() {
		return releaseGroup.getSelectedRelease();
	}

	public void saveDialogSettings() {
		releaseGroup.saveDialogSettings();
	}

	/**
	 * Set the location to the default location if we are set to useDefaults.
	 */
	void setLocationForSelection() {
		locationArea.updateProjectName(getProjectNameFieldValue());
	}

	/**
	 * Returns whether this page's controls currently all contain valid values.
	 * 
	 * @return <code>true</code> if all controls are valid, and <code>false</code> if at least one is invalid
	 */
	protected boolean validatePage() {
		String projectFieldContents = getProjectNameFieldValue();
		if (projectFieldContents.equals("")) { //$NON-NLS-1$
			setErrorMessage(null);
			setMessage(WorkspaceUIMessages.label_newProjectNameEmpty);
			return false;
		}

		IStatus nameStatus = ResourcesPlugin.getWorkspace().validateName(projectFieldContents, IResource.PROJECT);
		if (!nameStatus.isOK()) {
			setErrorMessage(nameStatus.getMessage());
			return false;
		}

		IProject handle = getProjectHandle();
		if (handle.exists()) {
			setErrorMessage(ErrorUIMessages.error_newProjectExistsMessage);
			return false;
		}

		/*
		 * If not using the default value validate the location.
		 */
		if (!locationArea.isDefault()) {
			String validLocationMessage = locationArea.checkValidLocation();
			if (validLocationMessage != null) { // there is no destination
				// location given
				setErrorMessage(validLocationMessage);
				return false;
			}
		} else {
			IPath projectLocation = ResourcesPlugin.getWorkspace().getRoot().getLocation().append(projectFieldContents);

			// detect whether another file exists at the location with a different case, i.e. a case variant.
			if (projectLocation.toFile().exists()) {
				try {
					// correct casing
					String canonicalPath = projectLocation.toFile().getCanonicalPath();
					IPath canonicalLocation = new Path(canonicalPath);

					String existingName = canonicalLocation.lastSegment();
					if (existingName.equals(projectFieldContents) == false) {
						setErrorMessage(NLS.bind(ErrorUIMessages.error_newProjectCaseVariantExists, projectFieldContents));
						return false;
					}
				} catch (IOException e) {
					PlatformLogUtil.logAsError(Activator.getPlugin(), e);
				}
			}
		}

		setErrorMessage(null);
		setMessage(null);

		return true;
	}

	/**
	 * Detects whether another file exists at the location with a different case, i.e. a case variant.
	 * 
	 * @param location
	 * @return
	 */
	public static boolean detectCaseVariant(IPath location) throws IOException {
		if (location.toFile().exists()) {
			IPath canonicalLocation;

			// correct casing
			String canonicalPath = location.toFile().getCanonicalPath();
			canonicalLocation = new Path(canonicalPath);

			String locationName = location.lastSegment();
			String existingName = canonicalLocation.lastSegment();
			if (existingName.equals(locationName) == false) {
				return true;
			}
		}

		return false;
	}

	private Layout initGridLayout(GridLayout gridLayout, boolean margins) {

		gridLayout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
		gridLayout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
		if (margins) {
			gridLayout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
			gridLayout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
		} else {
			gridLayout.marginWidth = 0;
			gridLayout.marginHeight = 0;
		}
		return gridLayout;
	}

	/**
	 * Returns the value of the project name field with leading and trailing spaces removed.
	 * 
	 * @return the project name in the field
	 */
	private String getProjectNameFieldValue() {
		if (projectNameField == null) {
			return ""; //$NON-NLS-1$
		}
		return projectNameField.getText().trim();
	}

	/**
	 * Get an error reporter for the receiver.
	 * 
	 * @return IErrorMessageReporter
	 */
	private IErrorMessageReporter getErrorReporter() {
		return new IErrorMessageReporter() {
			/*
			 * @see org.eclipse.ui.internal.ide.dialogs.ProjectContentsLocationArea
			 * .IErrorMessageReporter#reportError(java .lang.String, boolean)
			 */
			@Override
			public void reportError(String errorMessage, boolean infoOnly) {
				setErrorMessage(errorMessage);
				boolean valid = errorMessage == null;
				if (valid) {
					valid = validatePage();
				}
				setPageComplete(valid);
			}
		};
	}

	protected class NameGroup implements Listener {

		public NameGroup(Composite parent) {

			Composite composite = new Composite(parent, SWT.NONE);
			GridLayout layout = new GridLayout();
			layout.numColumns = 2;
			composite.setLayout(layout);
			composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

			// new project label
			Label projectLabel = new Label(composite, SWT.NONE);
			projectLabel.setText(WorkspaceUIMessages.label_newProjectCreationPageName);
			projectLabel.setFont(parent.getFont());

			// new project name entry field
			projectNameField = new Text(composite, SWT.BORDER);
			GridData data = new GridData(GridData.FILL_HORIZONTAL);
			data.widthHint = SIZING_TEXT_FIELD_WIDTH;
			projectNameField.setLayoutData(data);
			projectNameField.setFont(parent.getFont());

			// Set the initial value first before listener to avoid handling an event during the creation.
			if (initialProjectFieldValue != null) {
				projectNameField.setText(initialProjectFieldValue);
			}
			projectNameField.addListener(SWT.Modify, this);
		}

		@Override
		public void handleEvent(Event event) {
			setLocationForSelection();
			boolean valid = validatePage();
			setPageComplete(valid);
		}
	}

	protected class LocationGroup {

		private final Group locGroup;

		public LocationGroup(Composite composite) {

			locGroup = new Group(composite, SWT.NONE);
			locGroup.setText(Messages.EastADLProjectWizardFirstPage_location);
			locGroup.setFont(composite.getFont());
			locGroup.setLayout(initGridLayout(new GridLayout(2, false), false));
			locGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

			locationArea = new ProjectContentsLocationArea(getErrorReporter(), locGroup);
			if (initialProjectFieldValue != null) {
				locationArea.updateProjectName(initialProjectFieldValue);
			}

			// Scale the button based on the rest of the dialog
			setButtonLayoutData(locationArea.getBrowseButton());
		}

	}

	protected class ReleaseGroup implements IFieldListener, SelectionListener {

		private final String LAST_SELECTED_RELEASE_OPTION = Activator.getPlugin().getSymbolicName() + "last.selected.release"; //$NON-NLS-1$
		private final String LAST_SELECTED_RELEASE_KEY = Activator.getPlugin().getSymbolicName() + "last.selected.project.release"; //$NON-NLS-1$

		private static final int WORKSPACE_DEFAULT_RELEASE = 0;
		private static final int ALTERNATE_RELEASE = 1;

		private static final String RELEASE_LABEL_PATTERN = "%1s (%2s)"; //$NON-NLS-1$

		private final Group releaseGroup;
		private final SelectionButtonField workspaceDefaultReleaseButton, alternateReleaseButton;
		private final ComboField releaseCombo;
		private final Link configureWorkspaceSettingsLink;

		private List<EastADLReleaseDescriptor> supportedReleases = new ArrayList<EastADLReleaseDescriptor>();

		public ReleaseGroup(Composite composite, boolean defaultEnabled) {

			releaseGroup = new Group(composite, SWT.NONE);
			releaseGroup.setFont(composite.getFont());
			releaseGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			releaseGroup.setLayout(initGridLayout(new GridLayout(3, false), true));
			releaseGroup.setText(RELEASE_GROUP_TEXT);

			workspaceDefaultReleaseButton = new SelectionButtonField(SWT.RADIO);
			workspaceDefaultReleaseButton.setLabelText(Messages.EastADLProjectWizardFirstPage_defaultRelease + " (" //$NON-NLS-1$
					+ IEastADLWorkspacePreferences.EAST_ADL_RELEASE.getFromWorkspace().getName() + ")"); //$NON-NLS-1$
			workspaceDefaultReleaseButton.fillIntoGrid(releaseGroup, 2);
			workspaceDefaultReleaseButton.addFieldListener(this);

			configureWorkspaceSettingsLink = new Link(releaseGroup, SWT.NONE);
			configureWorkspaceSettingsLink.setFont(releaseGroup.getFont());
			configureWorkspaceSettingsLink.setText(MessageFormat.format("<a>{0}</a>", Messages.EastADLProjectWizardFirstPage_configureSettings)); //$NON-NLS-1$
			configureWorkspaceSettingsLink.setLayoutData(new GridData(SWT.END, SWT.CENTER, false, false));
			configureWorkspaceSettingsLink.addSelectionListener(this);

			alternateReleaseButton = new SelectionButtonField(SWT.RADIO);
			alternateReleaseButton.setLabelText(Messages.EastADLProjectWizardFirstPage_label_alternateRelease);
			alternateReleaseButton.fillIntoGrid(releaseGroup, 1);
			alternateReleaseButton.addFieldListener(this);

			releaseCombo = new ComboField(SWT.READ_ONLY);
			releaseCombo.fillIntoGrid(releaseGroup, 2);
			releaseCombo.addFieldListener(this);

			fillSupportedReleases();

			Combo releaseComboControl = (Combo) releaseCombo.getComboControl();
			releaseComboControl.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
			releaseComboControl.setVisibleItemCount(10);

			switch (getLastSelectedReleaseOption()) {
			case WORKSPACE_DEFAULT_RELEASE:
				workspaceDefaultReleaseButton.setSelection(true);
				break;
			case ALTERNATE_RELEASE:
				alternateReleaseButton.setSelection(true);
				break;
			default:
				workspaceDefaultReleaseButton.setSelection(true);
				break;
			}

			updateEnableState();
		}

		private void fillSupportedReleases() {
			supportedReleases = MetaModelDescriptorRegistry.INSTANCE.getDescriptors(EastADLReleaseDescriptor.INSTANCE, true);
			if (!supportedReleases.isEmpty()) {
				String[] items = new String[supportedReleases.size()];
				for (int index = 0; index < supportedReleases.size(); index++) {
					IMetaModelDescriptor descriptor = supportedReleases.get(index);
					items[index] = String.format(RELEASE_LABEL_PATTERN, descriptor.getName(), descriptor.getNamespace());
				}
				releaseCombo.setItems(items);
			}

			String selectedReleaseId = getLastSelectedReleaseIdentifier();
			IMetaModelDescriptor descriptor = MetaModelDescriptorRegistry.INSTANCE.getDescriptor(selectedReleaseId);
			if (supportedReleases.contains(descriptor)) {
				releaseCombo.selectItem(supportedReleases.indexOf(descriptor));
			} else {
				EastADLReleaseDescriptor workspaceRelease = IEastADLWorkspacePreferences.EAST_ADL_RELEASE.getFromWorkspace();
				if (supportedReleases.contains(workspaceRelease)) {
					releaseCombo.selectItem(supportedReleases.indexOf(workspaceRelease));
				}
			}
		}

		private int getLastSelectedReleaseOption() {
			IDialogSettings dialogSettings = Activator.getPlugin().getDialogSettings();
			if (dialogSettings.get(LAST_SELECTED_RELEASE_OPTION) == null) {
				return WORKSPACE_DEFAULT_RELEASE;
			}
			return dialogSettings.getInt(LAST_SELECTED_RELEASE_OPTION);
		}

		private void updateEnableState() {
			releaseCombo.setEnabled(alternateReleaseButton.isSelected());
			configureWorkspaceSettingsLink.setEnabled(workspaceDefaultReleaseButton.isSelected());
		}

		private String getLastSelectedReleaseIdentifier() {
			IDialogSettings dialogSettings = Activator.getPlugin().getDialogSettings();
			return dialogSettings.get(LAST_SELECTED_RELEASE_KEY);
		}

		private void storeSelectionState(ComboField comboField) {
			int index = comboField.getSelectionIndex();
			if (index > -1) {
				IMetaModelDescriptor descriptor = supportedReleases.get(index);
				if (descriptor != null) {
					String item = descriptor.getIdentifier();
					Activator.getPlugin().getDialogSettings().put(LAST_SELECTED_RELEASE_KEY, item);
				}
			}
		}

		public EastADLReleaseDescriptor getSelectedRelease() {
			if (workspaceDefaultReleaseButton.isSelected()) {
				return IEastADLWorkspacePreferences.EAST_ADL_RELEASE.getFromWorkspace();
			} else if (alternateReleaseButton.isSelected()) {
				int index = releaseCombo.getSelectionIndex();
				if (index > -1) {
					IMetaModelDescriptor descriptor = supportedReleases.get(index);
					if (descriptor instanceof EastADLReleaseDescriptor) {
						return (EastADLReleaseDescriptor) descriptor;
					}
				}
			}
			return null;
		}

		@Override
		public void dialogFieldChanged(IField field) {
			updateEnableState();
			if (field == workspaceDefaultReleaseButton) {
				Activator.getPlugin().getDialogSettings().put(LAST_SELECTED_RELEASE_OPTION, WORKSPACE_DEFAULT_RELEASE);
			} else if (field == alternateReleaseButton) {
				Activator.getPlugin().getDialogSettings().put(LAST_SELECTED_RELEASE_OPTION, ALTERNATE_RELEASE);
			} else if (field == releaseCombo) {
				if (alternateReleaseButton.isSelected()) {
					storeSelectionState((ComboField) field);
				}
			}
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			// Get the ID of the preference page that allows to choose the default EAST-ADL release for the workspace
			String releasePrefPageId = IEastADLWorkspacePreferenceConstants.EAST_ADL_RELEASE_PREFERENCE_PAGE_ID;
			PreferencesUtil.createPreferenceDialogOn(getShell(), releasePrefPageId, new String[] { releasePrefPageId }, null).open();
		}

		@Override
		public void widgetSelected(SelectionEvent e) {
			widgetDefaultSelected(e);
		}

		public void saveDialogSettings() {

		}
	}
}