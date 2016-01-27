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
package org.eclipse.eatop.eaadapter.ea2ecore.postprocessings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.eclipse.core.internal.resources.ResourceException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.eatop.eaadapter.ea2ecore.PostProcessingTemplate;
import org.eclipse.eatop.eaadapter.internal.messages.Messages;
import org.eclipse.eatop.eaadapter.util.IConstants;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;

@SuppressWarnings("restriction")
public class CreateReleaseZipFile extends PostProcessingTemplate {

	private String modelProject = null;
	private String editModelProject = null;
	private IProgressMonitor monitor = null;

	public CreateReleaseZipFile(IProgressMonitor monitor, String modelProject) {
		this.modelProject = modelProject;
		editModelProject = modelProject + "." + IConstants.EDIT_FOLDER; //$NON-NLS-1$
		this.monitor = monitor;
	}

	private IFile createJarFile(IFolder binFolder, IFolder libFolder, String jarName) throws CoreException, IOException {
		IFile file = libFolder.getFile(jarName);
		Manifest manifest = new Manifest();
		manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "0.5"); //$NON-NLS-1$
		JarOutputStream jout = new JarOutputStream(new FileOutputStream(file.getRawLocation().toFile()), manifest);
		try {
			binFolder.accept(new ZipResourceVisitor(jout, binFolder));
		} finally {
			try {
				jout.close();
			} catch (IOException e) {
				// ignore
			}
		}
		return file;
	}

	private IFile createZipFile(IFolder srcFolder, IFolder libFolder, String zipName) throws FileNotFoundException, CoreException {
		IFile file = libFolder.getFile(zipName);
		ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(file.getRawLocation().toFile()));
		try {
			srcFolder.accept(new ZipResourceVisitor(zout, srcFolder));
		} finally {
			try {
				zout.close();
			} catch (IOException e) {
				// ignore
			}
		}
		return file;
	}

	@Override
	public void execute() throws CoreException, IOException {
		try {
			monitor.beginTask(Messages.task_CreateReleaseArchives, 2);
			createArchives(modelProject, model.getName());

			/* Refresh */
			IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(modelProject);
			project.build(IncrementalProjectBuilder.FULL_BUILD, monitor);
			checkBuild(project);
			project.refreshLocal(IResource.DEPTH_INFINITE, monitor);

			createArchives(editModelProject, model.getName() + "." + IConstants.EDIT_FOLDER); //$NON-NLS-1$
		} finally {
			monitor.done();
		}
	}

	private void createArchives(String projectName, String archivName) throws CoreException, IOException {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		String srcArchivName = archivName + IConstants.SRC_FOLDER + "." + IConstants.ZIP; //$NON-NLS-1$
		String jarArchivName = archivName + "." + IConstants.JAR; //$NON-NLS-1$

		if (project.exists()) {
			IFolder libFolder = project.getFolder(IConstants.LIB_FOLDER);
			if (!libFolder.exists()) {
				try {
					libFolder.create(IResource.FORCE, true, monitor);
				} catch (CoreException e) {
					getLogger().error(e.getMessage());
					e.printStackTrace();
					return;
				}
			}

			IFolder srcFolder = project.getFolder(IConstants.SRC_FOLDER);

			addToJavaClasspath(project, JavaCore.newSourceEntry(srcFolder.getFullPath()));

			removeFromJavaClasspath(project, libFolder.getFile(jarArchivName).getFullPath());

			project.build(IncrementalProjectBuilder.FULL_BUILD, monitor);

			checkBuild(project);

			project.refreshLocal(IResource.DEPTH_INFINITE, monitor);

			IFile srcFile = null;
			if (srcFolder.exists()) {
				srcFile = createZipFile(srcFolder, libFolder, srcArchivName);
			}

			IFolder binFolder = project.getFolder(IConstants.BIN_FOLDER);
			IFile jarFile = null;
			if (binFolder.exists()) {
				jarFile = createJarFile(binFolder, libFolder, jarArchivName);
			}

			try {
				srcFolder.refreshLocal(IResource.DEPTH_INFINITE, monitor);
				srcFolder.delete(IResource.ALWAYS_DELETE_PROJECT_CONTENT, monitor);

				binFolder.refreshLocal(IResource.DEPTH_INFINITE, monitor);
				binFolder.delete(IResource.ALWAYS_DELETE_PROJECT_CONTENT, monitor);
			} catch (ResourceException e) {
				getLogger().warning(MessageFormat.format(Messages.logger_FileNotDelete, e.getMessage()));
			}
			if (jarFile != null && srcFile != null) {
				addToJavaClasspath(project, JavaCore.newLibraryEntry(jarFile.getFullPath(), srcFile.getFullPath(), null, true));
				removeFromJavaClasspath(project, srcFolder.getFullPath());
			}

			modifyManifestBundleClasspath(project.getName(), jarFile.getProjectRelativePath().toString());

			project.refreshLocal(IResource.DEPTH_INFINITE, monitor);

		}
	}

	protected void checkBuild(IResource resource) throws CoreException {
		if (resource == null) {
			System.out.println(Messages.error_ResourceNotSpecified);
		}

		if (hasErrors(resource)) {
			printErrors(resource);
			System.out.println(MessageFormat.format(Messages.error_Resource, resource));
		}
	}

	public static boolean hasErrors(IResource resource) throws CoreException {
		return resource.findMaxProblemSeverity(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE) >= IMarker.SEVERITY_ERROR;
	}

	public static void printErrors(IResource resource) throws CoreException {
		IMarker[] markers = resource.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);

		System.out.println(toString(markers));
	}

	/*
	 * code inspired by org.eclipse.ui.internal.views.markers.MarkerCopyHandler.createMarkerReport(ExtendedMarkersView,
	 * IMarker[])
	 */
	static String toString(IMarker[] markers) throws CoreException {
		StringBuilder report = new StringBuilder();

		final String NEWLINE = System.getProperty(IConstants.LINE_SEPARATOR_PROPERTY);
		final char DELIMITER = '\t';

		for (IMarker marker : markers) {
			Integer severity = (Integer) marker.getAttribute(IMarker.SEVERITY);

			// only include errors
			if (severity < IMarker.SEVERITY_ERROR) {
				continue;
			}

			report.append(marker.getAttribute(IMarker.MESSAGE));
			report.append(DELIMITER);
			report.append(marker.getResource().getName());
			report.append(DELIMITER);
			report.append(marker.getResource().getFullPath());
			report.append(DELIMITER);
			report.append(marker.getAttribute(IMarker.LINE_NUMBER));
			report.append(DELIMITER);
			report.append(marker.getAttribute(IMarker.LOCATION));
			report.append(DELIMITER);
			report.append(marker.getType());
			report.append(NEWLINE);
		}

		return report.toString();
	}

	private void modifyManifestBundleClasspath(String projectName, String newClassPathEntry) throws IOException, CoreException {

		IPath path = new Path("/" + projectName + "/" + IConstants.METAINF_FOLDER + "/" + IConstants.MANIFEST_FILE_DEFAULT); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		IFile eclipseFile = ResourcesPlugin.getWorkspace().getRoot().getFile(path);

		File file = eclipseFile.getLocation().toFile();
		if (file.exists()) {
			FileInputStream fis = new FileInputStream(file);
			try {
				Manifest manifest = new Manifest(fis);
				Attributes mainAttributes = manifest.getMainAttributes();
				if (mainAttributes != null) {
					String attributeValue = mainAttributes.getValue(IConstants.BUNDLE_CLASS_PATH_ATTRIBUTE);
					if (attributeValue != null) {
						List<String> entries = extractAttributeValueEntries(attributeValue);
						boolean skip = false;
						for (String entry : entries) {
							if (entry.equals(newClassPathEntry)) {
								skip = true;
								break;
							}
						}
						if (!skip) {
							mainAttributes.putValue(IConstants.BUNDLE_CLASS_PATH_ATTRIBUTE,
									addEntryToAttributeValue(attributeValue, newClassPathEntry));
							FileOutputStream fos = new FileOutputStream(file);
							try {
								manifest.write(fos);
								eclipseFile.refreshLocal(IResource.DEPTH_ZERO, null);
							} finally {
								fos.close();
							}
						}
					}
				}
			} finally {
				fis.close();
			}
		}
	}

	private String addEntryToAttributeValue(String attributeValue, String entry) {
		String result = attributeValue;
		if (result.length() > 0) {
			result += ","; //$NON-NLS-1$
		}
		result += entry;
		return result;
	}

	private List<String> extractAttributeValueEntries(String attributeValue) {
		List<String> result = new ArrayList<String>();
		String value = attributeValue;
		while (value.contains(",")) { //$NON-NLS-1$
			int index = value.indexOf(","); //$NON-NLS-1$
			String pre = value.substring(0, index);
			pre.replace(" ", ""); //$NON-NLS-1$ //$NON-NLS-2$
			result.add(pre);
			value = value.substring(index + 1);
		}
		result.add(value);
		return result;
	}

	private void addToJavaClasspath(IProject project, IClasspathEntry entry) throws CoreException, JavaModelException {

		IJavaProject jPrj = JavaCore.create(project);

		IClasspathEntry[] clsPathEntries = jPrj.getRawClasspath();
		IClasspathEntry[] newClsPathEntries = new IClasspathEntry[clsPathEntries.length + 1];

		for (int i = 0; i < clsPathEntries.length; i++) {
			if (clsPathEntries[i].getPath().equals(entry.getPath())) {
				return;
			}
			newClsPathEntries[i] = clsPathEntries[i];
		}

		newClsPathEntries[clsPathEntries.length] = entry;

		jPrj.setRawClasspath(newClsPathEntries, monitor);
	}

	private void removeFromJavaClasspath(IProject project, IPath path) throws CoreException, JavaModelException {

		IJavaProject jPrj = JavaCore.create(project);

		IClasspathEntry[] clsPathEntries = jPrj.getRawClasspath();
		List<IClasspathEntry> newClsPathEntries = new ArrayList<IClasspathEntry>();

		for (IClasspathEntry clsPathEntry : clsPathEntries) {
			if (!clsPathEntry.getPath().equals(path)) {
				newClsPathEntries.add(clsPathEntry);
			}
		}

		jPrj.setRawClasspath(newClsPathEntries.toArray(new IClasspathEntry[newClsPathEntries.size()]), monitor);
	}

	private class ZipResourceVisitor implements IResourceVisitor {

		private ZipOutputStream zout = null;
		private IFolder base = null;

		public ZipResourceVisitor(ZipOutputStream zout, IFolder base) {
			this.zout = zout;
			this.base = base;
		}

		@Override
		public boolean visit(IResource resource) throws CoreException {
			URI baseURI = base.getLocationURI();

			if (resource.equals(base)) {
				return true;
			}

			if (resource instanceof IFile) {
				IFile file = (IFile) resource;
				InputStream is = file.getContents(true);
				try {
					String name = getRelativePath(baseURI, file);
					ZipEntry ze = new ZipEntry(name);
					zout.putNextEntry(ze);
					int content = -1;
					while ((content = is.read()) != -1) {
						zout.write(content);
					}
					zout.closeEntry();
				} catch (IOException e) {
					getLogger().warning(e.getMessage());
					e.printStackTrace();
					return false;
				} finally {
					try {
						is.close();
					} catch (IOException e) {
						// ignore
					}
				}
			} else if (resource instanceof IFolder) {
				IFolder folder = (IFolder) resource;
				String name = getRelativePath(baseURI, folder);

				ZipEntry ze = new ZipEntry(name);
				try {
					zout.putNextEntry(ze);
				} catch (IOException e) {
					getLogger().warning(e.getMessage());
					e.printStackTrace();
					return false;
				}
			}

			return true;
		}

		private String getRelativePath(URI baseURI, IResource res) {
			URI uri = res.getLocationURI();
			uri = baseURI.relativize(uri);
			String name = uri.getPath();
			if (res instanceof IFolder) {
				name = name + "/"; //$NON-NLS-1$
			}
			return name;
		}
	}
}
