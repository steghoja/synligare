package action;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.eatop.common.ui.util.ModelSearcher;
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.EAPackageableElement;
import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.eatop.eastadl21.ErrorModelType;
import org.eclipse.eatop.eastadl21.FunctionType;
import org.eclipse.eatop.eastadl21.UserAttributeDefinition;
import org.eclipse.eatop.eastadl21.UserAttributedElement;
import org.eclipse.eatop.eastadl21.UserElementType;
import org.eclipse.eatop.volvo.versionmanager.Activator;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;

import command.CheckConsistencyEMT;
import command.VersionInconsistencyDecorator;

public class ActionCheckConsistencyEMT implements IObjectActionDelegate{

	protected TreePath[] selectedObjects;
	private List<EObject> selectedEObjects;
	private EAPackage topPackage;
	protected EditingDomain ed;
	protected CheckConsistencyEMT command;
	protected VersionInconsistencyDecorator addDecorator;
	private EList<EAPackageableElement> funElements;
	private UserElementType funElementType=null;
	private UserElementType errElementType=null;
//	private List<Boolean> allConsistency;
	
	public ActionCheckConsistencyEMT() {
		// TODO Auto-generated constructor stub
		super();
	}

	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub
		IWorkbenchPage page= Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();  		
	    ISelection myselec= page.getSelection();
	    TreeSelection myselecTree= (TreeSelection) myselec;
	    TreePath[] paths = myselecTree.getPaths();
	    this.selectedObjects = paths;
	    
	    try {
			init();			
			initCommand();
			ArrayList<Boolean> allConsistency =  new ArrayList<Boolean>();
			ArrayList<Boolean> allTargeted =  new ArrayList<Boolean>();
			for (EObject selectedEobject : selectedEObjects){
				if(selectedEobject instanceof FunctionType){
					FunctionType funType = (FunctionType) selectedEobject;
					List<EObject> funRefs = ModelSearcher.findReferences(funType);
					ErrorModelType errType = null;
					for(EObject funRef: funRefs){
						if(funRef instanceof ErrorModelType){
							errType = (ErrorModelType) funRef;
							List<EObject> errRefs = ModelSearcher.findReferences(errType);
							for(EObject errRef: errRefs){
								if(errRef instanceof UserAttributedElement){
									UserAttributedElement errUae = (UserAttributedElement) errRef;
									EList<UserElementType> errElementTypes = errUae.getUaType();
									errElementType = errElementTypes.get(0);									
								}
							}
						}
						if(funRef instanceof UserAttributedElement){
							UserAttributedElement funUae = (UserAttributedElement) funRef;
							EList<UserElementType> funElementTypes = funUae.getUaType();
							funElementType = funElementTypes.get(0);							
						}
					}
					if(errElementType!=null&&funElementType!=null&&errType!=null){
						boolean targeted = true;
						boolean Consistency = command.CheckConsistencyEMT(ed, errElementType,funElementType,errType);
						allConsistency.add(Consistency);
						allTargeted.add(targeted);
					}
					else{
						allTargeted.add(false);
					}
				
				}
				if(selectedEobject instanceof ErrorModelType){
					ErrorModelType errType = (ErrorModelType) selectedEobject;
					EList<FunctionType> funTypes = errType.getTarget();
					FunctionType funType = funTypes.get(0);
					List<EObject> errRefs = ModelSearcher.findReferences(errType);
					List<EObject> funRefs = ModelSearcher.findReferences(funType);
					for(EObject errRef:errRefs){
						if(errRef instanceof UserAttributedElement){
							UserAttributedElement errUae = (UserAttributedElement) errRef;
							EList<UserElementType> errElementTypes = errUae.getUaType();
							errElementType = errElementTypes.get(0);									
						}
					}
					for(EObject funRef:funRefs){
						if(funRef instanceof UserAttributedElement){
							UserAttributedElement funUae = (UserAttributedElement) funRef;
							EList<UserElementType> funElementTypes = funUae.getUaType();
							funElementType = funElementTypes.get(0);							
						}						
					}
					if(errElementType!=null&&funElementType!=null&&errType!=null){
						boolean targeted = true;
						boolean Consistency = command.CheckConsistencyEMT(ed, errElementType,funElementType,errType);
						allConsistency.add(Consistency);
						allTargeted.add(targeted);
					}
					else{
						allTargeted.add(false);
						
					}
				}
//				addDecorator.VersionInconsistencyDecorator(ed);
			}
			if(allConsistency.contains(false) && !allTargeted.contains(false)){
				showInformation("Consistency Checking", "Inconsistency Exists in selected files! Please check ErrorLog for more detailed information");				
			}else if(allTargeted.contains(false)){
				showInformation("Consistency Checking","Selected elements contains untargeted or non-versioned element!");
			}else{
				showInformation("Consistency Checking", "All Versions of Selected Elements are consistent between Error Model and Function Model!");
			}
			

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public void initCommand() {
		this.command = new CheckConsistencyEMT();
		this.addDecorator = new VersionInconsistencyDecorator();
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// TODO Auto-generated method stub
		
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
