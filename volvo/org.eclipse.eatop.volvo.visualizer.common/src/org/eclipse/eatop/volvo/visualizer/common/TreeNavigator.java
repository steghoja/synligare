package org.eclipse.eatop.volvo.visualizer.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.eatop.common.metamodel.EastADLReleaseDescriptor;
import org.eclipse.eatop.common.resource.impl.EastADLXMLResourceImpl;
import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.Eastadl21Package;
import org.eclipse.eatop.eastadl21.util.Eastadl21ResourceFactoryImpl;
import org.eclipse.eatop.volvo.modelprocessor.ModelProcessor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.sphinx.emf.resource.ScopingResourceSetImpl;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

/**
 * Class to navigate to elements in an East-ADL tree in the EATOP EASTADL Explorer or the EAST-ADL Editor
 * 
 * @author Joanna Svantesson
 *
 */
public class TreeNavigator {

	private final static String EAST_ADL_EXPLORER = 
			"org.eclipse.eatop.examples.explorer.views.eastadlExplorer";

	/**
	 *  Selects the EAST-ADL element corresponding to the given VisualElement 
	 * in the EAST-ADL Explorer
	 * 
	 * @param elementpath The path in the EAST ADL model of the element we want to select
	 * @param fileName The name of the file to look for the element in
	 * @param root The EAXML root of the model we want to look at
	 * @return true if the element was selected, otherwise false
	 */
	public static boolean selectInEASTADLExplorer(String elementpath, String fileName, EAXML root) {
		System.out.println("Select element with path " + elementpath);

		// Find open projects
		// Search these for our file
		// Find the root of this model (Right now getting the root from the view)
		// Use ModelProcessor to find the element
		// Open EAST-ADL explorer if not already open
		// Select the element


		List<IFile> files = new ArrayList<IFile>();
		List<Object> openProjects = new ArrayList<Object>();

		for(IProject project : ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
			if(project.isOpen()) {
				openProjects.add(project);
			}
		}

		for (int i = 0; i < openProjects.size(); i++) {
			Object o = openProjects.get(i);
			if (o instanceof IProject) {
				IProject p = (IProject)o;
				files.addAll(findFilesInContainer(p, fileName));
			} else if (o instanceof IJavaProject) {
				IJavaProject jp = (IJavaProject)o;
				files.addAll(findFilesInContainer(jp.getProject(), fileName));
			}
		}

		List<EObject> foundObjects = new ArrayList<EObject>();
		List<IFile> filesWithObject = new ArrayList<IFile>();
		for (IFile f : files) {

			// TODO Here I would want to get the EAXML root when only having the IFile
			// How can this be done? Maybe by adapting the IFile to something like
			// an Eastadl21Resource? Or Item Provider? Have to dig in the EATOP code.
			// (The way with the loadEaxmlFile method doesn't work as I want it to
			// since it seems to create a new EAXML and I want the existing one.
			// The solution I use right now is that the EAXML root is stored in the view 
			// when generating the visualization. This is kind of a hack and I would not
			// prefer this way...

			/// Test
			EastADLXMLResourceImpl xmlResource = new EastADLXMLResourceImpl(
					URI.createFileURI(f.getFullPath().toPortableString()));
			System.out.println("I can has root uri? " + xmlResource.getURIFragment(root));
			// I get the URI for the root, but using that to get the object isn't working.
			// Why?
			System.out.println("I can has root? " + xmlResource.getEObject("/?type=EAXML"));
			///////


			//EAXML root = loadEaxmlFile(f);
			//System.out.println("roooot " + root);
			System.out.println("Trying to find element with path " + elementpath);
			EObject eObject = ModelProcessor.getEObject(root, elementpath);
			if (eObject != null) {
				foundObjects.add(eObject);
				filesWithObject.add(f);
			} else { // object wasn't found
				return false;
			}

		}

		// Go backwards and find the path
		TreePath[] treePaths = new TreePath[foundObjects.size()];
		for (int i = 0; i < foundObjects.size(); i++) {
			Object[] objectPath = findObjectPath(foundObjects.get(i), filesWithObject.get(i));	
			treePaths[i] = new TreePath(objectPath);
		}

		ITreeSelection t = new TreeSelection(treePaths);

		IWorkbenchPage page= Activator.getDefault().getWorkbench().
				getActiveWorkbenchWindow().getActivePage();

		try {
			IViewPart view = page.showView(EAST_ADL_EXPLORER);
			view.getSite().getSelectionProvider().setSelection(t);

		} catch (PartInitException e) {
			// TODO
			// give user some message
			e.printStackTrace();
		}
		return true;
	}

	/*public static void selectInEASTADLEditor(String elementpath, String fileName, EAXML root) {
		// TODO
	}*/

	/**
	 * Finds the files with the given name in the given <code>IContainer</code>
	 * 
	 * @param container The container to look for the files in
	 * @param fileName The name of the file to be found
	 * @return A list of the files with the given name. 
	 * If no file were found this list will be empty.
	 */
	public static List<IFile> findFilesInContainer(IContainer container, String fileName) {
		List<IFile> files = new ArrayList<IFile>();
		IResource[] resources;

		try {
			resources = container.members();
			for (int i = 0; i < resources.length; i++) {
				IResource r = resources[i];
				if (r instanceof IFile) {					
					if (((IFile) r).getName().equals(fileName)) {
						files.add((IFile)r);
					}
				}
				if (r instanceof IFolder) {
					List<IFile> fs = findFilesInContainer((IFolder)r, fileName);
					if (fs != null) {
						files.addAll(fs);
					}
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}

		return files;
	}

	private static EAXML loadEaxmlFile(IFile selectedEaxmlFile) throws Exception {
		URI eaxmlURI = URI.createFileURI(selectedEaxmlFile.getLocation().toPortableString());//getPath());
		ResourceSet resourceSet = new ScopingResourceSetImpl();
		resourceSet.getPackageRegistry().put(Eastadl21Package.eNS_URI, Eastadl21Package.eINSTANCE);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().
		put(EastADLReleaseDescriptor.EAXML_DEFAULT_FILE_EXTENSION, 
				new Eastadl21ResourceFactoryImpl());

		EAXML eaxmlRoot = Eastadl21Factory.eINSTANCE.createEAXML();

		Resource eaxmlResource = resourceSet.createResource(eaxmlURI);
		eaxmlResource.load(null);
		if (eaxmlResource.getContents().size() > 0) {
			if (eaxmlResource.getContents().get(0) instanceof EAXML)
				eaxmlRoot = (EAXML) eaxmlResource.getContents().get(0);
			else
				throw new Exception("The content of the eaxmlResource isn't EAXML");
		} else {
			throw new Exception("The file does not contain an EAXML element");
		}

		return eaxmlRoot;
	}

	private static Object[] findObjectPath(EObject eObject, IFile file) {

		List<Object> path = new ArrayList<Object>();
		path.add(eObject);
		Object parent = eObject.eContainer();
		while (!(parent instanceof IProject)) {

			path.add(parent);
			if (parent instanceof IContainer) {
				parent = ((IContainer) parent).getParent();
				continue;
			}
			if (parent instanceof IFile) {
				parent = ((IFile) parent).getParent();
				continue;
			}
			if (parent instanceof EAXML) {
				parent = file;
				continue;
			}
			// If it is an EObject but not an EAXML
			parent = ((EObject) parent).eContainer();
		}
		path.add(parent);
		Collections.reverse(path);
		return path.toArray();
	}
}
