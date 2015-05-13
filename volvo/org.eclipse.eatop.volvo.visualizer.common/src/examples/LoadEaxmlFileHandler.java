package examples;

import java.io.File;

import org.eclipse.eatop.common.metamodel.EastADLReleaseDescriptor;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.sphinx.emf.resource.ScopingResourceSetImpl;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.dialogs.ListDialog;
import org.eclipse.ui.handlers.HandlerUtil;

//import provider.ListDialogContentProvider;
//import provider.ListDialogLabelProvider;
import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.Eastadl21Package;
import org.eclipse.eatop.eastadl21.util.Eastadl21ResourceFactoryImpl;
//import org.eatop.eel.eastadl21.Eastadl21ResourceFactoryImpl;

/**
 * This Handler loads a .eaxml file in your workspace
 * and displays its content.
 * 
 * @author Matthias Nick - Matthias.Nick@continental-corporation.com
 */
public class LoadEaxmlFileHandler extends AbstractHandler {

	private ExecutionEvent event = null;
	private ResourceSet resourceSet = null;
	private Resource eaxmlResource = null;	
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		this.event = event;
		try {
			File selectedEaxmlFile = askUserForEaxmlFile();
			if (selectedEaxmlFile != null) {
				org.eclipse.eatop.eastadl21.EAXML eaxml = loadEaxmlFile(selectedEaxmlFile);
				displayContentOf(eaxml);
			}
			
		} catch (Exception e) {
			MessageDialog.openError(HandlerUtil.getActiveShell(event), "Error", String.format("An error occured while creating the .eaxml file: %s", e.getMessage()));			
		}
		return null;
	}

	private void displayContentOf(EAXML eaxml) {
		ListDialog dialog = new ListDialog(HandlerUtil.getActiveShell(event));
		//dialog.setContentProvider(new ListDialogContentProvider());
		//dialog.setLabelProvider(new ListDialogLabelProvider());
		dialog.setInput(eaxml);
		dialog.setTitle(String.format("Content of %s", eaxml.eResource().getURI().path()));
		dialog.setMessage(String.format("Found %d elements in EAXML file", numberOfElementsInEaxml(eaxml)));
		dialog.open();
	}

	private int numberOfElementsInEaxml(EAXML eaxml) {
		int sum = 0;
		TreeIterator<Object> iter = EcoreUtil.getAllContents(eaxml, true);
		for (;iter.hasNext(); iter.next()) {
			sum++;
		}
		return sum;
	}

	private EAXML loadEaxmlFile(File selectedEaxmlFile) throws Exception {
		URI eaxmlURI = URI.createFileURI(selectedEaxmlFile.getPath());
		resourceSet = new ScopingResourceSetImpl();
		resourceSet.getPackageRegistry().put(Eastadl21Package.eNS_URI, Eastadl21Package.eINSTANCE);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(EastADLReleaseDescriptor.EAXML_DEFAULT_FILE_EXTENSION, new Eastadl21ResourceFactoryImpl());
			
		EAXML eaxmlRoot = Eastadl21Factory.eINSTANCE.createEAXML();
		
		eaxmlResource = resourceSet.createResource(eaxmlURI);
		eaxmlResource.load(null);
		if (eaxmlResource.getContents().size() > 0) {
			if (eaxmlResource.getContents().get(0) instanceof EAXML)
				eaxmlRoot = (EAXML) eaxmlResource.getContents().get(0);
			else
				throw new Exception("The content of the eaxmlResource isn't EAXML");
		} else {
			throw new Exception("The file does not contain a EAXML element");
		}
		
		return eaxmlRoot;
	}

	private File askUserForEaxmlFile() throws Exception {
		FileDialog dialog = new FileDialog(HandlerUtil.getActiveShell(event),SWT.OPEN);
		dialog.setFilterExtensions(new String[] { "*.eaxml" });
		String selectedFilename = dialog.open();
		if (selectedFilename == null) {
			return null;
		} 
		
		File selectedFile = new File(selectedFilename);
		if (!selectedFile.exists()) {
			throw new Exception(String.format("'%s' does not exist!", selectedFile.getPath()));
		}
		
		return selectedFile;
	}

}
