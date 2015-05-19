package org.eclipse.eatop.volvo.sgraphml.gefeditor.popup.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.Activator;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.Utils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.Page;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/***
 * Uses JAXP to transform an input xml to an output XML document
 * 
 * @author yt11022
 *
 */

public class XMLTransformAction implements IObjectActionDelegate {

	Shell shell;
	IWorkbenchPage page;
	
	String xsltFileName;
	String sourceExtension;
	String targetExtension;
	
	public XMLTransformAction() {
	
		xsltFileName= "s2y.xsl";
		sourceExtension = "sgraphml";
		targetExtension = "ygraphml";
	}
	
	
	
	public String getXsltFileName() {
		return xsltFileName;
	}



	public void setXsltFileName(String xsltFileName) {
		this.xsltFileName = xsltFileName;
	}



	public String getSourceExtension() {
		return sourceExtension;
	}



	public void setSourceExtension(String sourceExtension) {
		this.sourceExtension = sourceExtension;
	}



	public String getTargetExtension() {
		return targetExtension;
	}



	public void setTargetExtension(String targetExtension) {
		this.targetExtension = targetExtension;
	}



	@Override
	public void run(IAction action) {
		
		//Obtain user node selection
		ISelection myselec= page.getSelection();
        TreeSelection myselecTree= (TreeSelection) myselec;
	    TreePath[] selections = myselecTree.getPaths();
	     IFile selectedXMLFile = (IFile)selections[0].getLastSegment();

	     //Obtain xslt transform file from plugin directory
	     InputStream xsltStream = getTransformFileStream(); 
		 if (xsltStream == null) return;	

		 
		 //Create a transformer based on the xslt stream


		 //This one does not work for borderInsets calculation: com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl
	     
		 TransformerFactory factory = null;
		 try {
			 factory = TransformerFactory.newInstance("org.apache.xalan.processor.TransformerFactoryImpl", null);
			
		} catch (javax.xml.transform.TransformerFactoryConfigurationError e) {
			Utils.showErrorMessage("Failed to initiate org.apache.xalan.processor.TransformerFactoryImpl. Is Zalan 2.7.1 installed?");
			return;
		}

	 
//	     Source xslt = new StreamSource(new File(xsltPath));
	     Source xslt = new StreamSource(xsltStream);
	        Transformer transformer;
			try {
				transformer = factory.newTransformer(xslt);
			} catch (TransformerConfigurationException e) {
				Utils.showErrorMessage("Failed to create a xslt transformer from xslt/" + xsltFileName);
				e.printStackTrace();
				return;
			}

			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
			
			//make sure input xml file - which is in the workspace - is synchronized
			if (!selectedXMLFile.isSynchronized(0)){
				Utils.showInformationMessage("Selected file is not in synch with file system - synchronize before attempting to convert!");
				
/*				try {
					selectedXMLFile.refreshLocal(0, null);
				} catch (CoreException e) {
					System.out.println("Failed to refresh local"); 
					e.printStackTrace();
				}
*/
				
			}
			
			InputStream sgraphmlInput;
			try {
				sgraphmlInput = selectedXMLFile.getContents();
			} catch (CoreException e1) {
				Utils.showErrorMessage("Failed to create input stream from input file");
				e1.printStackTrace();
				return;
			}
			
			
			//This will be relative to the eclipse installation
			File workingDirPath = new File("working-dir");
			
		 
			//Make sure there is a /working-dir path
		//	String workingDir = Platform.getLocation().toString() + "/working-dir";
			if (!workingDirPath.exists()){
				workingDirPath.mkdir();
			}
			
	        IPath selectedPath = selectedXMLFile.getProjectRelativePath();
	        String selectedFilename = selectedPath.lastSegment();
	        String selectedFilenameNoExt = selectedFilename.substring(0, selectedFilename.lastIndexOf('.'));
	        String tempTargetFilePath = "working-dir/" + selectedFilenameNoExt + "." + targetExtension;
	        
	        //Delete any previous file with the same name in the working-dir
	        File tempGraphmlFile = new File(tempTargetFilePath);
	        if (tempGraphmlFile.exists()){
	        	tempGraphmlFile.delete();
	        }
			
	        //perform the actual xslt transform
	        Source text = new StreamSource(sgraphmlInput);
	        try {
				transformer.transform(text, new StreamResult(new File(tempTargetFilePath)));
			} catch (TransformerException e) {
				Utils.showErrorMessage("Failed to transform file, exception: " + e.getMessage());
				e.printStackTrace();
				return;
			}

	        IFile graphmlFile = Utils.INSTANCE.generateUniqueFilename(selectedXMLFile, targetExtension);
	        
	        //Move the tempfile into the workspace by creating it
			InputStream xmlInput;
			try {
				xmlInput = new FileInputStream(tempTargetFilePath);
			} catch (FileNotFoundException e) {
				Utils.showErrorMessage("Failed to create input stream from " + tempTargetFilePath);
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}

			try {
				graphmlFile.create(xmlInput, true, null);
			} catch (CoreException e) {
				Utils.showErrorMessage("Failed to create a new file");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}

	/***
	 * Find a filename that is not in the workspace - add a number until we find an unused filename
	 */
/*	protected IFile generateUniqueFilename(IFile selectedSGraphMLFile,
			IPath selectedPath, String selectedFilenameNoExt) {
		
		IPath graphmlPath = selectedPath.removeLastSegments(1).append(selectedFilenameNoExt + "." + targetExtension);
		IProject project = selectedSGraphMLFile.getProject();
		IFile graphmlFile = project.getFile(graphmlPath);
		boolean bFound = false;
		int n = 0;
		while (!bFound)
		{
			if (graphmlFile.exists()){
		    	n++;
				graphmlPath = selectedPath.removeLastSegments(1).append(selectedFilenameNoExt + n + "." + targetExtension);
				graphmlFile = project.getFile(graphmlPath);
			}
		else{
				bFound = true;
			}
		}
		return graphmlFile;
	}
*/
	protected InputStream getTransformFileStream() {

		//This solution using FileLocator.resolve requires jar to be unpacked. Better solution below.
		//bundleentry://930.fwk487819219/xslt/s2y.xslt
/*		URL bundleRootURL = null;
		try {
			bundleRootURL = new URL(Activator.getDefault().getBundle().getEntry("/xslt"), xsltFileName);
		} catch (Exception e) {
			System.out.print("Failed to get URL xslt/" + xsltFileName);
			e.printStackTrace();
			return null;
		}
		
		// get URL = file:/C:/Synligare/.../org.eclipse.eatop.volvo.sgraphml.gefeditor/xslt/s2y.xslt
		// Note: For FileLocator.resolve to work, the file must be unpacked from the jar-archive. See setting in feature.xml.
		URL pluginUrl = null;
		try {
			pluginUrl = FileLocator.resolve(bundleRootURL); 

		} catch (IOException ex) {
			System.out.print("Failed to resolve xslt/"+  xsltFileName);
			ex.printStackTrace();
			return null;
		}
		String xsltPath = pluginUrl.getPath();
		return xsltPath;
*/
		InputStream inputStream;
		try {
	        URL url = new URL("platform:/plugin/org.eclipse.eatop.volvo.sgraphml.gefeditor/xslt/" + xsltFileName);
	        inputStream = url.openConnection().getInputStream();
	      }
		catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	
		return inputStream;
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
		page = targetPart.getSite().getPage();   	    
	}

}
	