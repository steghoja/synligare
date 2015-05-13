package action;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import modelquery.ModelQueryException;

import org.eclipse.eatop.eastadl21.Dependability;
import org.eclipse.eatop.eastadl21.DesignFunctionType;
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.EAPackageableElement;
import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.Eastadl21Package;
import org.eclipse.eatop.eastadl21.FunctionConnector;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;

import commands.CreateErrorModelType;
import commands.IErrorModelCreation;
import commands.MyEContentAdapter;

import errorpropagationplugin.Activator;

public class ActionConsistencyChecking implements IObjectActionDelegate{
	
	private Shell shell;
	protected TreePath[] selectedObjects;
	private List<EObject> selectedEObjects;
	private EObject selectedEobject;
	protected EditingDomain ed;
	private String statusMessage;
	private EAXML root;
	
	public ActionConsistencyChecking() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}
	
	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		
		IWorkbenchPage page= Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();  
		
	     ISelection myselec= page.getSelection();
	     
	     //System.out.print(myselec.toString());
	     
	     TreeSelection myselecTree= (TreeSelection) myselec;
	     TreePath[] paths = myselecTree.getPaths();
	     this.selectedObjects = paths;
	     
		try {
			init();
			initDialog();
			initCommand();
			selectedEobject=selectedEObjects.get(0);
			root = (EAXML) EcoreUtil.getRootContainer(selectedEobject);
			
			MyEContentAdapter elementEContentAdapter = new MyEContentAdapter(root);	
			
		}catch (ModelQueryException e) {
			this.statusMessage = e.getMessage();
			MessageDialog.openError(this.shell,"Error occurred when performing query" , this.statusMessage);
			e.printStackTrace();
		}catch (Exception e){
			this.statusMessage = e.getMessage();
			e.printStackTrace();
		}finally{

		}
	}
	
	private void init() throws Exception{
		List<EObject> selectedEObjects = toEObjectList(this.selectedObjects);

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
	public void initDialog() {
	}
	public void initCommand() {
	}
	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

}



