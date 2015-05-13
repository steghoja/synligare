package action;

import java.util.ArrayList;
import java.util.List;

import modelquery.ModelQueryException;

import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;

import commands.CollapseFFP;
import errorpropagationplugin.Activator;

public class ActionCollapseFaultFailurePorts implements IObjectActionDelegate {
	private Shell shell;
	protected TreePath[] selectedObjects;
	private List<EObject> selectedEObjects;
	private EObject selectedEobject;
	protected EditingDomain ed;
	protected CollapseFFP command;
	private String statusMessage;
	private EAXML root;
	private int numSelected = 0;


	public ActionCollapseFaultFailurePorts() {
		// TODO Auto-generated constructor stub
		super();
	}


	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// TODO Auto-generated method stub
		shell = targetPart.getSite().getShell();
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
			initDialog();
			initCommand();
			numSelected = selectedEObjects.size();
			selectedEobject=selectedEObjects.get(0);
			root = (EAXML) EcoreUtil.getRootContainer(selectedEobject);
			
			command.collapseFFP(ed, selectedEObjects, numSelected);	
				
			
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
		this.command = new CollapseFFP();
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
		
	}


}
