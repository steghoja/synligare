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

import java.util.ArrayList;
import java.util.Collection;
import java.util.ListIterator;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.eatop.common.metamodel.EastADLReleaseDescriptor;
import org.eclipse.eatop.workspace.jobs.CreateEastADLFileJob;
import org.eclipse.eatop.workspace.preferences.IEastADLWorkspacePreferences;
import org.eclipse.eatop.workspace.ui.internal.Activator;
import org.eclipse.eatop.workspace.ui.internal.messages.Messages;
import org.eclipse.eatop.workspace.ui.messages.ErrorUIMessages;
import org.eclipse.eatop.workspace.ui.messages.WorkspaceUIMessages;
import org.eclipse.eatop.workspace.util.EastADLPlatformUtil;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sphinx.platform.ui.util.ExtendedPlatformUI;
import org.eclipse.sphinx.platform.util.ExtendedPlatform;
import org.eclipse.sphinx.platform.util.PlatformLogUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;

/**
 * This is a simple wizard for creating a new EAST-ADL file.
 */
public class BasicEastADLFileWizard extends BasicNewResourceWizard {

	/**
	 * The project-relative path of the wizard banner file.
	 */
	private static final String WIZBAN_ICON_FILE = "full/wizban/neweafile_wiz.png"; //$NON-NLS-1$

	/**
	 * This is the file creation page.
	 */
	protected EastADLFileWizardNewFileCreationPage fNewFileCreationPage;

	protected boolean fLoggedNoValidFileExtensionsFoundProblem = false;

	/**
	 * This is the one page of the wizard.
	 */
	public class EastADLFileWizardNewFileCreationPage extends WizardNewFileCreationPage {

		private Button fCreatePackage;
		private Text fPackageName;

		/**
		 * Pass in the selection.
		 */
		public EastADLFileWizardNewFileCreationPage(String pageId, IStructuredSelection selection) {
			super(pageId, selection);
		}

		@Override
		protected void createAdvancedControls(Composite parent) {
			super.createAdvancedControls(parent);
			Group fGroup = new Group(parent, SWT.NONE);
			fGroup.setText(WorkspaceUIMessages.label_EAPackageCreation);
			fGroup.setLayout(new GridLayout(2, false));
			fGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

			fCreatePackage = new Button(fGroup, SWT.CHECK);
			GridData gd = new GridData();
			gd.horizontalSpan = 2;
			fCreatePackage.setLayoutData(gd);
			fCreatePackage.setText(WorkspaceUIMessages.label_WithEAPackage);
			fCreatePackage.setSelection(true);

			fPackageName = new Text(fGroup, SWT.SINGLE | SWT.BORDER);
			fPackageName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			fPackageName.setText(WorkspaceUIMessages.text_defaultEaPackageName);
			fCreatePackage.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					fPackageName.setEnabled(fCreatePackage.getSelection());
				}
			});

		}

		/**
		 * The framework calls this to see if the file is correct.
		 */
		@Override
		protected boolean validatePage() {
			if (!super.validatePage()) {
				return false;
			}

			// Make sure that we are in an EAST-ADL project
			IFolder selectedFolder = getSelectedFolder();
			if (selectedFolder != null && !EastADLPlatformUtil.hasEastADLNature(selectedFolder.getProject())) {
				setErrorMessage(Messages.error_selectedFolderIsNotInEastADLProject);
				return false;
			}
			IProject selectedProject = getSelectedProject();
			if (selectedProject != null && !EastADLPlatformUtil.hasEastADLNature(selectedProject)) {
				setErrorMessage(Messages.error_selectedProjectIsNoEastADLProject);
				return false;
			}

			// Make sure the file has a valid extension
			String fileExtension = new Path(getFileName()).getFileExtension();
			Collection<String> validFileExtensions = getValidEastADLFileExtensions(selectedProject);
			if (!validFileExtensions.contains(fileExtension)) {
				setErrorMessage(NLS.bind(ErrorUIMessages.error_fileNameExtension, convertFileExtensionsToString(validFileExtensions)));
				return false;
			}

			return true;
		}

		private String convertFileExtensionsToString(Collection<String> fileExtensions) {
			Assert.isNotNull(fileExtensions);

			StringBuilder buf = new StringBuilder();
			ListIterator<String> iter = new ArrayList<String>(fileExtensions).listIterator();
			while (iter.hasNext()) {
				if (iter.hasPrevious()) {
					buf.append(", "); //$NON-NLS-1$
				}
				buf.append("*."); //$NON-NLS-1$
				buf.append(iter.next());
			}
			return buf.toString();
		}

		public IProject getSelectedProject() {
			if (getContainerFullPath().segmentCount() == 1) {
				return ResourcesPlugin.getWorkspace().getRoot().getProject(getContainerFullPath().segment(0));
			} else {
				IFolder selectedFolder = getSelectedFolder();
				if (selectedFolder != null) {
					return selectedFolder.getProject();
				}
			}
			return null;
		}

		public IFolder getSelectedFolder() {
			if (getContainerFullPath().segmentCount() > 1) {
				return ResourcesPlugin.getWorkspace().getRoot().getFolder(getContainerFullPath());
			}
			return null;
		}

		public IFile getEastADLFile() {
			return ResourcesPlugin.getWorkspace().getRoot().getFile(getContainerFullPath().append(getFileName()));
		}

		public String getEAPackageName() {
			if (fCreatePackage.getSelection() && !(fPackageName.getText().length() == 0)) {
				return fPackageName.getText();
			}
			return null;
		}
	}

	/**
	 * This just records the information.
	 */
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		super.init(workbench, selection);
		setWindowTitle(WorkspaceUIMessages.wizard_newEastadlFile_title);
	}

	@Override
	protected void initializeDefaultPageImageDescriptor() {
		setDefaultPageImageDescriptor(Activator.getPlugin().getImageDescriptor(WIZBAN_ICON_FILE));
	}

	/**
	 * Returns the content type identifier to be used when creating new EAST-ADL files in given {@link IProject project}
	 * .
	 * <p>
	 * This implementation returns the default content type identifier from {@link EastADLReleaseDescriptor release
	 * descriptor} behind given {@link IProject project}.
	 * </p>
	 * 
	 * @param project
	 *            The {@link IProject project}.
	 * @return The content type identifier to be used in given {@link IProject project} when creating new EAST-ADL
	 *         files.
	 */
	protected String getEastADLFileContentTypeId(IProject project) {
		EastADLReleaseDescriptor eastadlRelease = IEastADLWorkspacePreferences.EAST_ADL_RELEASE.get(project);
		return eastadlRelease.getDefaultContentTypeId();
	}

	/**
	 * Returns the valid file extensions for EAST-ADL files in given {@link IProject project}. They are retrieved from
	 * the content type to be used when creating new EAST-ADL files in given {@link IProject project}.
	 * 
	 * @param project
	 *            The {@link IProject project}.
	 * @return A collection of valid file extensions for EAST-ADL files in given {@link IProject project}, or "
	 *         {@link EastADLReleaseDescriptor#EAXML_DEFAULT_FILE_EXTENSION eaxml}" if other retrieval attempts do not
	 *         deliver any appropriate result.
	 * @see #getEastADLFileContentTypeId(IProject)
	 * @see EastADLReleaseDescriptor#EAXML_DEFAULT_FILE_EXTENSION
	 */
	protected Collection<String> getValidEastADLFileExtensions(IProject project) {
		String contentTypeId = getEastADLFileContentTypeId(project);
		Collection<String> validFileExtensions = new ArrayList<String>(ExtendedPlatform.getContentTypeFileExtensions(contentTypeId));
		if (validFileExtensions.size() == 0) {
			if (!fLoggedNoValidFileExtensionsFoundProblem) {
				PlatformLogUtil.logAsWarning(Activator.getPlugin(), new RuntimeException(
						"No valid file extensions for content type identifer '" + contentTypeId + "' found.")); //$NON-NLS-1$ //$NON-NLS-2$
				fLoggedNoValidFileExtensionsFoundProblem = true;
			}
			validFileExtensions.add(EastADLReleaseDescriptor.EAXML_DEFAULT_FILE_EXTENSION);
		}
		return validFileExtensions;
	}

	/**
	 * The framework calls this to create the contents of the wizard.
	 */
	@Override
	public void addPages() {
		// Create a page, set the title, and the initial model file name.
		//
		fNewFileCreationPage = new EastADLFileWizardNewFileCreationPage("Whatever", selection); //$NON-NLS-1$
		fNewFileCreationPage.setTitle(WorkspaceUIMessages.label_eastadlFileWizard);
		fNewFileCreationPage.setDescription(WorkspaceUIMessages.description_eastadlFileWizard);
		fNewFileCreationPage.setFileName(WorkspaceUIMessages.defaultFileName + "." + WorkspaceUIMessages.defaultFileExtension); //$NON-NLS-1$
		addPage(fNewFileCreationPage);

		// Try and get the resource selection to determine a current directory for the file dialog.
		if (selection != null && !selection.isEmpty()) {
			// Get the resource...
			Object selectedElement = selection.iterator().next();
			if (selectedElement instanceof IResource) {
				// Get the resource parent, if it is a file.
				IResource selectedResource = (IResource) selectedElement;
				if (selectedResource.getType() == IResource.FILE) {
					selectedResource = selectedResource.getParent();
				}

				// This gives us a directory...
				if (selectedResource instanceof IFolder || selectedResource instanceof IProject) {
					// Set this for the container.
					fNewFileCreationPage.setContainerFullPath(selectedResource.getFullPath());

					// Make up a unique new name here.
					String defaultFileBaseName = WorkspaceUIMessages.defaultFileName;
					String defaultFileExtension = WorkspaceUIMessages.defaultFileExtension;
					String fileName = defaultFileBaseName + "." + defaultFileExtension; //$NON-NLS-1$
					for (int i = 1; ((IContainer) selectedResource).findMember(fileName) != null; ++i) {
						fileName = defaultFileBaseName + i + "." + defaultFileExtension; //$NON-NLS-1$
					}
					fNewFileCreationPage.setFileName(fileName);
				}
			}
		}
	}

	@Override
	public boolean performFinish() {
		// Make wizard result information accessible for asynchronous operation
		final IFile eastadlFile = fNewFileCreationPage.getEastADLFile();
		final String eaPackageName = fNewFileCreationPage.getEAPackageName();

		Job job = new CreateEastADLFileJob(Messages.job_creatingEastADLFile_name, eastadlFile,
				IEastADLWorkspacePreferences.EAST_ADL_RELEASE.get(eastadlFile.getProject()), eaPackageName);
		job.addJobChangeListener(new JobChangeAdapter() {
			@Override
			public void done(IJobChangeEvent event) {
				if (event.getResult() != null && event.getResult().isOK()) {
					// Select the newly created file in all parts of the active workbench window's active page
					Display display = ExtendedPlatformUI.getDisplay();
					if (display != null) {
						display.asyncExec(new Runnable() {
							@Override
							public void run() {
								selectAndReveal(eastadlFile, PlatformUI.getWorkbench().getActiveWorkbenchWindow());
							}
						});
					}
				}
			}
		});
		job.schedule();

		return true;
	}

}
