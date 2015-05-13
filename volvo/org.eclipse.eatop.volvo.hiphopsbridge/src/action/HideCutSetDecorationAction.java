package action;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.EAPackageableElement;
import org.eclipse.eatop.eastadl21.ErrorModelPrototype;
import org.eclipse.eatop.eastadl21.ErrorModelType;
import org.eclipse.eatop.eastadl21.FunctionPrototype;
import org.eclipse.eatop.eastadl21.UserAttributeDefinition;
import org.eclipse.eatop.eastadl21.UserElementType;
import org.eclipse.eatop.volvo.hiphopsbridge.Activator;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;

public class HideCutSetDecorationAction implements IObjectActionDelegate{

	protected TreePath[] selectedObjects;
	private List<EObject> selectedEObjects;
	private ErrorModelType emt;
	protected EditingDomain ed;
	
	public HideCutSetDecorationAction() {
		// TODO Auto-generated constructor stub
		super();
	}

	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		IWorkbenchPage page= Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();  		
	    ISelection myselec= page.getSelection();
	    TreeSelection myselecTree= (TreeSelection) myselec;
	    TreePath[] paths = myselecTree.getPaths();
	    this.selectedObjects = paths;	
	    
		try {
			init();			
			initCommand();
			for(EObject selectedEObject : selectedEObjects){
				if(selectedEObject instanceof ErrorModelType){
					emt = (ErrorModelType) selectedEObject;
					EList<ErrorModelPrototype> parts = emt.getPart();
					for(final ErrorModelPrototype part: parts){
						ed.getCommandStack().execute(new RecordingCommand((TransactionalEditingDomain)ed) {
						    protected void doExecute() {
						    	part.setName("");
						    	if(part.getTarget()!=null){
						    		FunctionPrototype funPart = (FunctionPrototype) part.getTarget();
						    		funPart.setName("");
						    	}
						    }
						});
					}
				}
				
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
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// TODO Auto-generated method stub
		
	}

}
