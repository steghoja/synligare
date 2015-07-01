package action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.eatop.eastadl21.ErrorModelType;
import org.eclipse.eatop.volvo.hiphopsbridge.Activator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.sphinx.emf.util.EcorePlatformUtil;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import command.Interpreter_EAXML2HIPXML;
import command.ShowCutSet;

import eaxml2hipx.ReadEAXML;

public class GetInterpreterArgsAction implements IObjectActionDelegate{
	private Shell shell;
	protected TreePath[] selectedObjects;
	private List<EObject> selectedEObjects;
	private EObject selectedEobject;
	protected EditingDomain ed;
	private String statusMessage;
	private ErrorModelType topType;
//	private EAXML root;
	private String hiphopsFailureEditorPath;
	private int numSelected = 0;
	private String fileName;
	private String filePath;
	protected ShowCutSet showCutSet;
	protected  Interpreter_EAXML2HIPXML command;

	public GetInterpreterArgsAction() {
		// TODO Auto-generated constructor stub
		super();
	}

	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub
		IWorkbenchPage page= Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();  
		
	     ISelection myselec= page.getSelection();
	     
	     //System.out.print(myselec.toString());
	     
	     TreeSelection myselecTree= (TreeSelection) myselec;
	     TreePath[] paths = myselecTree.getPaths();
	     this.selectedObjects = paths;
	     
	     try {
				init();
				
				initCommand();
				numSelected = selectedEObjects.size();
				selectedEobject=selectedEObjects.get(0);
				// Get Top ErrorModelType name
				String systemName = "";
				topType = (ErrorModelType) selectedEobject;
				systemName = topType.getShortName();
				// Get file name
				Resource resource = topType.eResource();
				getFile(resource);
				// Invoke to generate hipxml
				Interpreter_EAXML2HIPXML(filePath, systemName);
				// Get hiphopsPath
				initDialog();
				// Copy generated file to HiP-HOPS directory
				String hipFilePath = filePath.replace(fileName, "");
				String hipFileName = fileName.replace(".eaxml", "")+ "_OUTPUT.xml";
				String sourcePath = hipFilePath + hipFileName;
				String destPath = hiphopsFailureEditorPath+"/"+hipFileName;
				
				File source = new File(sourcePath);
				File dest = new File(destPath);
				// If filename conflict in destPath
				deleteExistFile(destPath);
				copyFile(source, dest);
				
				// Create FaultTreeAnalysis through hiphops
				createFTA(hipFileName);
				
				// Copy created FTA folder to eaxml source directory
				String sourceFolderPath = hiphopsFailureEditorPath+"/"+fileName.replace(".eaxml", "")+"-FMEAOutput";
				String destFolderPath = hipFilePath+fileName.replace(".eaxml", "")+"-FMEAOutput";
				showCutSet.getCutSetInfo(ed,sourceFolderPath, selectedEobject);
//				cutFTAFolder(sourceFolderPath,destFolderPath);
//				System.out.println();	
			}catch (Exception e){
				this.statusMessage = e.getMessage();
				e.printStackTrace();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{

			}
	}
	
//	private void cutFTAFolder(String sourceFolderPath, String destFolderPath) throws IOException{
//		// TODO Auto-generated method stub
//		File srcFolder = new File(sourceFolderPath);
//		File destFolder = new File(destFolderPath);
//		if(srcFolder.exists()){
//			copyFolder(srcFolder,destFolder);			
//		}
//		else{
//			showInformation("HiP-HOPS FMEA Error", "No FMEA has been generated corresponds to the selected top system");
//			System.out.println("No FMEA has been generated");
//		}
//	}
//
//    public static void copyFolder(File src, File dest)
//        	throws IOException{
//     
//        	if(src.isDirectory()){
//     
//        		//if directory not exists, create it
//        		if(!dest.exists()){
//        		   dest.mkdir();
//        		   System.out.println("Directory copied from " 
//                                  + src + "  to " + dest);
//        		}
//     
//        		//list all the directory contents
//        		String files[] = src.list();
//     
//        		for (String file : files) {
//        		   //construct the src and dest file structure
//        		   File srcFile = new File(src, file);
//        		   File destFile = new File(dest, file);
//        		   //recursive copy
//        		   copyFolder(srcFile,destFile);
//        		}
//     
//        	}else{
//        		//if file, then copy it
//        		//Use bytes stream to support all file types
//        		copyFile(src, dest);
//        		src.delete();
//        	}
//        }

	private void deleteExistFile(String filePath) {
		// TODO Auto-generated method stub
		File file = new File(filePath);
		if(file.isFile())
		{
			file.delete();
		}
		
	}

	public static boolean deleteDirectory(File directory) {		
	    if(directory.exists()){
	        File[] files = directory.listFiles();
	        if(null!=files){
	            for(int i=0; i<files.length; i++) {
	                if(files[i].isDirectory()) {
	                    deleteDirectory(files[i]);
	                }
	                else {
	                    files[i].delete();
	                }
	            }
	        }
	    }
	    return(directory.delete());
	}
	
	private void createFTA(String hipFileName) throws IOException{
		//idevns=false => treat hanging input deviations as basic events
		String HipFTA_CMD = "Hipop "+ hipFileName +" idevns=false outputtype=XML -openiex";
		Runtime rt = Runtime.getRuntime();

		// Delete html file if exists
		String urlFolderPath = hiphopsFailureEditorPath+ "/"+fileName.replace(".eaxml", "")+"-FMEAOutput";
//		File urlDirectory = new File(urlFolderPath);
//		deleteDirectory(urlDirectory);
//		rt.exec("cmd.exe /c start cmd", null, new File(newPath));
		rt.exec("cmd.exe /c cd \""+hiphopsFailureEditorPath+"\" & start cmd.exe /k "+ HipFTA_CMD);

		try {
			String urlPath = hiphopsFailureEditorPath+ "/"+fileName.replace(".eaxml", "")+"-FMEAOutput"+ "/Index.html";
			URL url = new File(urlPath).toURI().toURL();
			String browserID = urlPath+"/SystemName_"+topType.getShortName();
			PlatformUI.getWorkbench().getBrowserSupport().createBrowser(browserID).openURL(url);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		BrowserView browser = new BrowserView();
//		browser.gotoURL(urlPath);
		
//Open with default Browser      
//		File htmlFile = new File(urlPath);
//		Desktop.getDesktop().browse(htmlFile.toURI());

	}
	


	private static void copyFile(File source, File dest)
	        throws IOException {	
	    Files.copy(source.toPath(), dest.toPath());

	}

	public void Interpreter_EAXML2HIPXML(String filePath, String systemName) {
		// TODO Auto-generated method stub
		
		String file = filePath;
		File f = new File(file);
		String newFilePath = f.getAbsolutePath();
		
		ReadEAXML readFile = new ReadEAXML();
		readFile.EAXML2HIPXML(newFilePath, systemName);
		
	}
	
	private void getFile(Resource resource) throws URISyntaxException {
		// TODO Auto-generated method stub
		
		URI resourceUri = resource.getURI();
		IFile file = EcorePlatformUtil.getFile(resource);
		IPath fileLocation = file.getLocation();
		filePath = fileLocation.toString();
		
		boolean hasAbsolutePath = resourceUri.hasAbsolutePath();
		boolean isFile = resourceUri.isFile();		
		fileName = resourceUri.lastSegment();		
//		String relativePath = resourceUri.path();
//		File file = new File(relativePath);
//		filePath = file.getAbsolutePath();
		

//		IPath path = new Path(resourceUri.toFileString());
//		IFile file = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(path);

		
	}



	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// TODO Auto-generated method stub
		shell = targetPart.getSite().getShell();
	}
	
	private void init() throws Exception{
		List<EObject> selectedEObjects = toEObjectList(this.selectedObjects);
//		System.out.println(selectedEObjects.toString());

		if(selectedEObjects == null || selectedEObjects.isEmpty()){
			throw new Exception("No elements are selected. Select an element in the model.");
		}
		this.selectedEObjects = selectedEObjects;

		//Get the EditingDomain for the first element of the selected EObjects.
		EditingDomain ed = AdapterFactoryEditingDomain.getEditingDomainFor(selectedEObjects.get(0));
		if(ed == null){
			throw new Exception("Cannot create EditingDomain for object " + selectedEObjects.get(0));
		}
		this.ed = ed;

	}
	
	public void initDialog() throws IOException, Throwable {
		// Initialize the HiP-HOPS_FailureEditor Directory or read from created path
	    String workDir =System.getProperty("user.dir")+"\\hiphopsPath.txt";
	    File pathFile = new File(workDir);
	    if(pathFile.exists()){
	    	ReadPathFile(workDir);
	    }
	    else{
			hiphopsFailureEditorPath = "";
			hiphopsFailureEditorPath = getHiphopsPath();
		    CreatePathFile(workDir);
	    	
	    }		
	}
		
	private void ReadPathFile(String workDir) {
		// TODO Auto-generated method stub
	    // The name of the file to open.
        String fileName = workDir;
        // This will reference one line at a time
        String line = null;
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                System.out.println("hiphops Path:"+line);
                hiphopsFailureEditorPath = line;
            }	
            // Always close files.
            bufferedReader.close();			
        }
        catch(FileNotFoundException ex) {
            System.out.println( "Unable to open file '" + fileName + "'");				
        }
        catch(IOException ex) {
            System.out.println( "Error reading file '" + fileName + "'");					
            // Or we could just do this: 
            // ex.printStackTrace();
        }
	}

	private void CreatePathFile(String workDir) {
		// TODO Auto-generated method stub
        File file = new File(workDir);
        boolean fileCreated = false;
        try {
            fileCreated = file.createNewFile();
        } catch (IOException ioe) {
            System.out.println("Error while creating empty file: " + ioe);
        }
 
        if (fileCreated) {
            System.out.println("Created empty file: " + file.getPath());
            WriteTXTFile(workDir);
        } else {
            System.out.println("Failed to create empty file: " + file.getPath());
        }
	}

	private void WriteTXTFile(String workDir) {
		// TODO Auto-generated method stub
		 try {
	            // Assume default encoding.
	            FileWriter fileWriter = new FileWriter(workDir);

	            // Always wrap FileWriter in BufferedWriter.
	            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

	            // Note that write() does not automatically
	            // append a newline character.
	            bufferedWriter.write(hiphopsFailureEditorPath);
	            // Always close files.
	            bufferedWriter.close();
	        }
	        catch(IOException ex) {
	            System.out.println( "Error writing to file '" + workDir + "'");
	            // Or we could just do this:
	            // ex.printStackTrace();
	        }
	}

	public void initCommand() {		
		this.command = new Interpreter_EAXML2HIPXML();
		this.showCutSet = new ShowCutSet();
	}

	//Returns a list of EObjects from the selected elements in the editor
	private List<EObject> toEObjectList(TreePath[] selections){
		ArrayList<EObject> elements = new ArrayList<EObject>();
		if(selections == null || selections.length == 0){
			return elements;
		}
		for(int i = 0; i < selections.length; i++){
			Object tmpElement = selections[i].getLastSegment();
			if(tmpElement instanceof EObject){
				elements.add((EObject)tmpElement);
			}
		}
		return elements;
	}
	
	private String getHiphopsPath() {
	    JFrame frame = new JFrame();
	    Object result = JOptionPane.showInputDialog(frame, "Please specify the path of HiP-HOPS/FailureEditor(C:/HiPHOPS/HiP-HOPS_FailureEditor)");
	    return result.toString();
	}

	/**
	 * Show an information dialog box.
	 */
	public void showInformation(final String title, final String message) {
		// TODO Auto-generated method stub
	  Display.getDefault().asyncExec(new Runnable() {
	    @Override
	    public void run() {
	      MessageDialog.openInformation(null, title, message);
	    }
	  });
	}

}
