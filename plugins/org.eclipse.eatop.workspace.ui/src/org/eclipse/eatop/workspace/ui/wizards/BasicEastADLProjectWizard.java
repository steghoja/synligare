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
package org.eclipse.eatop.workspace.ui.wizards;

import java.net.URI;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.eatop.workspace.jobs.CreateEatopProjectJob;
import org.eclipse.eatop.workspace.ui.internal.Activator;
import org.eclipse.eatop.workspace.ui.internal.messages.Messages;
import org.eclipse.eatop.workspace.ui.messages.ErrorUIMessages;
import org.eclipse.eatop.workspace.ui.messages.WorkspaceUIMessages;
import org.eclipse.eatop.workspace.ui.wizards.pages.EastADLProjectWizardFirstPage;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sphinx.platform.ui.util.ExtendedPlatformUI;
import org.eclipse.sphinx.platform.util.PlatformLogUtil;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveRegistry;
import org.eclipse.ui.IPluginContribution;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.activities.IActivityManager;
import org.eclipse.ui.activities.IIdentifier;
import org.eclipse.ui.activities.IWorkbenchActivitySupport;
import org.eclipse.ui.activities.WorkbenchActivityHelper;
import org.eclipse.ui.dialogs.WizardNewProjectReferencePage;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.ide.undo.WorkspaceUndoUtil;
import org.eclipse.ui.internal.IPreferenceConstants;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.ide.IDEInternalPreferences;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.eclipse.ui.internal.registry.PerspectiveDescriptor;
import org.eclipse.ui.internal.util.PrefUtil;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;

@SuppressWarnings("restriction")
public class BasicEastADLProjectWizard extends BasicNewResourceWizard implements IExecutableExtension {

	private EastADLProjectWizardFirstPage mainPage;

	private WizardNewProjectReferencePage referencePage;

	/**
	 * The config element which declares this wizard.
	 */
	private IConfigurationElement configElement;

	private static String WINDOW_PROBLEMS_TITLE = ErrorUIMessages.error_newProjectOpeningWindow;

	/**
	 * Extension attribute name for final perspective.
	 */
	private static final String FINAL_PERSPECTIVE = "finalPerspective"; //$NON-NLS-1$

	/**
	 * Extension attribute name for preferred perspectives.
	 */
	private static final String PREFERRED_PERSPECTIVES = "preferredPerspectives"; //$NON-NLS-1$

	/**
	 * The project-relative path of the wizard banner file.
	 */
	private static final String WIZBAN_ICON_FILE = "full/wizban/neweaprj_wiz.png"; //$NON-NLS-1$

	/**
	 * Creates a wizard for creating a new EAST-ADL project resource in the workspace.
	 */
	public BasicEastADLProjectWizard() {
		IDialogSettings workbenchSettings = Activator.getPlugin().getDialogSettings();
		IDialogSettings section = workbenchSettings.getSection("BasicNewProjectResourceWizard");//$NON-NLS-1$
		if (section == null) {
			section = workbenchSettings.addNewSection("BasicNewProjectResourceWizard");//$NON-NLS-1$
		}
		setDialogSettings(section);
	}

	/**
	 * Adds to the list all perspective IDs in the Workbench who's original ID matches the given ID.
	 * 
	 * @param perspectiveIds
	 *            the list of perspective IDs to supplement.
	 * @param id
	 *            the id to query.
	 * @since 3.x
	 */
	private static void addPerspectiveAndDescendants(List<String> perspectiveIds, String id) {
		IPerspectiveRegistry registry = PlatformUI.getWorkbench().getPerspectiveRegistry();
		IPerspectiveDescriptor[] perspectives = registry.getPerspectives();
		for (IPerspectiveDescriptor element : perspectives) {
			// @issue illegal ref to workbench internal class; consider adding getOriginalId() as API on
			// IPerspectiveDescriptor
			PerspectiveDescriptor descriptor = (PerspectiveDescriptor) element;
			if (descriptor.getOriginalId().equals(id)) {
				perspectiveIds.add(descriptor.getId());
			}
		}
	}

	/**
	 * Replaces the current perspective with the new one.
	 * 
	 * @param persp
	 *            The perspective descriptor.
	 */
	private static void replaceCurrentPerspective(IPerspectiveDescriptor persp) {

		// Get the active page.
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if (window == null) {
			return;
		}
		IWorkbenchPage page = window.getActivePage();
		if (page == null) {
			return;
		}

		// Set the perspective.
		page.setPerspective(persp);
	}

	/**
	 * Prompts the user for whether to switch perspectives.
	 * 
	 * @param window
	 *            The workbench window in which to switch perspectives; must not be <code>null</code>
	 * @param finalPersp
	 *            The perspective to switch to; must not be <code>null</code>.
	 * @return <code>true</code> if it's OK to switch, <code>false</code> otherwise
	 */
	private static boolean confirmPerspectiveSwitch(IWorkbenchWindow window, IPerspectiveDescriptor finalPersp) {
		IPreferenceStore store = IDEWorkbenchPlugin.getDefault().getPreferenceStore();
		String pspm = store.getString(IDEInternalPreferences.PROJECT_SWITCH_PERSP_MODE);
		if (!IDEInternalPreferences.PSPM_PROMPT.equals(pspm)) {
			// Return whether or not we should always switch
			return IDEInternalPreferences.PSPM_ALWAYS.equals(pspm);
		}
		String desc = finalPersp.getDescription();
		String message;
		if (desc == null || desc.length() == 0) {
			message = NLS.bind(WorkspaceUIMessages.label_newProjectPerspSwitchMessage, finalPersp.getLabel());
		} else {
			message = NLS.bind(WorkspaceUIMessages.label_newProjectPerspSwitchMessageWithDesc, new String[] { finalPersp.getLabel(), desc });
		}

		// Use default message for the toggle (i.e. use null)
		String toggleMessage = null;
		// Toggle is initially unchecked
		boolean toggleState = false;
		// Open the yes/no question dialog about perspective switching
		MessageDialogWithToggle dialog = MessageDialogWithToggle.openYesNoQuestion(window.getShell(),
				WorkspaceUIMessages.label_newProjectPerspSwitchTitle, message, toggleMessage, toggleState, store,
				IDEInternalPreferences.PROJECT_SWITCH_PERSP_MODE);
		int result = dialog.getReturnCode();

		// If we are not going to prompt anymore propagate the choice.
		if (dialog.getToggleState()) {
			String preferenceValue;
			if (result == IDialogConstants.YES_ID) {
				// Doesn't matter if it is replace or new window as we are going to use the open perspective setting
				preferenceValue = IWorkbenchPreferenceConstants.OPEN_PERSPECTIVE_REPLACE;
			} else {
				preferenceValue = IWorkbenchPreferenceConstants.NO_NEW_PERSPECTIVE;
			}

			// update PROJECT_OPEN_NEW_PERSPECTIVE to correspond
			PrefUtil.getAPIPreferenceStore().setValue(IDE.Preferences.PROJECT_OPEN_NEW_PERSPECTIVE, preferenceValue);
		}
		return result == IDialogConstants.YES_ID;
	}

	/**
	 * Opens a new window with a particular perspective and input.
	 * 
	 * @param desc
	 *            The perspective descriptor.
	 */
	private static void openInNewWindow(IPerspectiveDescriptor desc) {

		// Open the page.
		try {
			PlatformUI.getWorkbench().openWorkbenchWindow(desc.getId(), ResourcesPlugin.getWorkspace().getRoot());
		} catch (WorkbenchException e) {
			IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
			if (window != null) {
				ErrorDialog.openError(window.getShell(), WINDOW_PROBLEMS_TITLE, e.getMessage(), e.getStatus());
			}
		}
	}

	/**
	 * Updates the perspective for the active page within the window.
	 */
	protected void updatePerspective() {
		updatePerspective(configElement);
	}

	/*
	 * Method declared on BasicNewResourceWizard.
	 */
	@Override
	protected void initializeDefaultPageImageDescriptor() {
		ImageDescriptor desc = Activator.getPlugin().getImageDescriptor(WIZBAN_ICON_FILE);
		setDefaultPageImageDescriptor(desc);
	}

	/*
	 * Method declared on IWizard.
	 */
	@Override
	public boolean performFinish() {
		mainPage.saveDialogSettings();

		URI location = !mainPage.useDefaults() ? mainPage.getLocationURI() : null;
		IProject[] referencedProjects = referencePage != null ? referencePage.getReferencedProjects() : null;
		final IProject projectHandle = mainPage.getProjectHandle();

		CreateEatopProjectJob job = new CreateEatopProjectJob(Messages.job_creatingEastADLProject, projectHandle, location, mainPage.getRelease());
		job.setReferencedProjects(referencedProjects);
		job.setUiInfoAdaptable(WorkspaceUndoUtil.getUIInfoAdapter(getShell()));
		// reveal the project after creation
		job.addJobChangeListener(new JobChangeAdapter() {
			@Override
			public void done(IJobChangeEvent event) {
				if (event.getResult().getSeverity() == IStatus.OK) {
					Display display = ExtendedPlatformUI.getDisplay();
					if (display != null) {
						display.asyncExec(new Runnable() {
							@Override
							public void run() {
								updatePerspective();
								selectAndReveal(projectHandle, PlatformUI.getWorkbench().getActiveWorkbenchWindow());
							}
						});
					}
				}
			}
		});
		job.schedule();

		return true;
	}

	/*
	 * (non-Javadoc) Method declared on IWorkbenchWizard.
	 */
	@Override
	public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
		super.init(workbench, currentSelection);
		setNeedsProgressMonitor(true);
		setWindowTitle(WorkspaceUIMessages.wizard_newEastADLProject_title);
	}

	/*
	 * (non-Javadoc) Method declared on IWizard.
	 */
	@Override
	public void addPages() {
		super.addPages();

		mainPage = new EastADLProjectWizardFirstPage("basicNewProjectPage"); //$NON-NLS-1$
		mainPage.setTitle(WorkspaceUIMessages.page_newEastADLProjectCreation_title);
		mainPage.setDescription(WorkspaceUIMessages.page_neweEastADLProjectCreation_description);
		addPage(mainPage);

		// only add page if there are already projects in the workspace
		if (ResourcesPlugin.getWorkspace().getRoot().getProjects().length > 0) {
			referencePage = new WizardNewProjectReferencePage("basicReferenceProjectPage"); //$NON-NLS-1$
			referencePage.setTitle(WorkspaceUIMessages.label_newProjectReferenceTitle);
			referencePage.setDescription(WorkspaceUIMessages.label_newProjectReferenceDescription);
			addPage(referencePage);
		}
	}

	/**
	 * Stores the configuration element for the wizard. The config element will be used in <code>performFinish</code> to
	 * set the result perspective.
	 */
	@Override
	public void setInitializationData(IConfigurationElement cfig, String propertyName, Object data) {
		configElement = cfig;
	}

	/**
	 * Updates the perspective based on the current settings in the Workbench/Perspectives preference page. Use the
	 * setting for the new perspective opening if we are set to open in a new perspective.
	 * <p>
	 * A new project wizard class will need to implement the <code>IExecutableExtension</code> interface so as to gain
	 * access to the wizard's <code>IConfigurationElement</code>. That is the configuration element to pass into this
	 * method.
	 * </p>
	 * 
	 * @param configElement
	 *            - the element we are updating with
	 * @see IPreferenceConstants#OPM_NEW_WINDOW
	 * @see IPreferenceConstants#OPM_ACTIVE_PAGE
	 * @see IWorkbenchPreferenceConstants#NO_NEW_PERSPECTIVE
	 */
	public static void updatePerspective(IConfigurationElement configElement) {
		// Do not change perspective if the configuration element is not specified.
		if (configElement == null) {
			return;
		}

		// Retrieve the new project open perspective preference setting
		String perspSetting = PrefUtil.getAPIPreferenceStore().getString(IDE.Preferences.PROJECT_OPEN_NEW_PERSPECTIVE);

		String promptSetting = IDEWorkbenchPlugin.getDefault().getPreferenceStore().getString(IDEInternalPreferences.PROJECT_SWITCH_PERSP_MODE);

		// Return if do not switch perspective setting and are not prompting
		if (!promptSetting.equals(MessageDialogWithToggle.PROMPT) && perspSetting.equals(IWorkbenchPreferenceConstants.NO_NEW_PERSPECTIVE)) {
			return;
		}

		// Read the requested perspective id to be opened.
		String finalPerspId = configElement.getAttribute(FINAL_PERSPECTIVE);
		if (finalPerspId == null) {
			return;
		}

		// Map perspective id to descriptor.
		IPerspectiveRegistry reg = PlatformUI.getWorkbench().getPerspectiveRegistry();

		// leave this code in - the perspective of a given project may map to activities other than those that the
		// wizard itself maps to.
		IPerspectiveDescriptor finalPersp = reg.findPerspectiveWithId(finalPerspId);
		if (finalPersp != null && finalPersp instanceof IPluginContribution) {
			IPluginContribution contribution = (IPluginContribution) finalPersp;
			if (contribution.getPluginId() != null) {
				IWorkbenchActivitySupport workbenchActivitySupport = PlatformUI.getWorkbench().getActivitySupport();
				IActivityManager activityManager = workbenchActivitySupport.getActivityManager();
				IIdentifier identifier = activityManager.getIdentifier(WorkbenchActivityHelper.createUnifiedId(contribution));
				@SuppressWarnings("unchecked")
				Set<String> idActivities = identifier.getActivityIds();

				if (!idActivities.isEmpty()) {
					@SuppressWarnings("unchecked")
					Set<String> enabledIds = new HashSet<String>(activityManager.getEnabledActivityIds());

					if (enabledIds.addAll(idActivities)) {
						workbenchActivitySupport.setEnabledActivityIds(enabledIds);
					}
				}
			}
		} else {
			PlatformLogUtil.logAsWarning(Activator.getPlugin(),
					new RuntimeException(MessageFormat.format(ErrorUIMessages.error_unableToFindPerspective, finalPerspId)));
			return;
		}

		// gather the preferred perspectives always consider the final perspective (and those derived from it) to be
		// preferred
		ArrayList<String> preferredPerspIds = new ArrayList<String>();
		addPerspectiveAndDescendants(preferredPerspIds, finalPerspId);
		String preferred = configElement.getAttribute(PREFERRED_PERSPECTIVES);
		if (preferred != null) {
			StringTokenizer tok = new StringTokenizer(preferred, " \t\n\r\f,"); //$NON-NLS-1$
			while (tok.hasMoreTokens()) {
				addPerspectiveAndDescendants(preferredPerspIds, tok.nextToken());
			}
		}

		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if (window != null) {
			IWorkbenchPage page = window.getActivePage();
			if (page != null) {
				IPerspectiveDescriptor currentPersp = page.getPerspective();

				// don't switch if the current perspective is a preferred perspective
				if (currentPersp != null && preferredPerspIds.contains(currentPersp.getId())) {
					return;
				}
			}

			// prompt the user to switch
			if (!confirmPerspectiveSwitch(window, finalPersp)) {
				return;
			}
		}

		int workbenchPerspectiveSetting = WorkbenchPlugin.getDefault().getPreferenceStore().getInt(IPreferenceConstants.OPEN_PERSP_MODE);

		// open perspective in new window setting
		if (workbenchPerspectiveSetting == IPreferenceConstants.OPM_NEW_WINDOW) {
			openInNewWindow(finalPersp);
			return;
		}

		// replace active perspective setting otherwise
		replaceCurrentPerspective(finalPersp);
	}
}