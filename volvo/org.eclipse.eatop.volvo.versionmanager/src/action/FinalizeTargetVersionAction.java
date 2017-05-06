package action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.eatop.common.ui.util.ModelSearcher;
import org.eclipse.eatop.eastadl21.EANumericalValue;
import org.eclipse.eatop.eastadl21.EAValue;
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
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;

public class FinalizeTargetVersionAction implements IObjectActionDelegate {
	private Shell shell;
	protected TreePath[] selectedObjects;
	private List<EObject> selectedEObjects;
	protected EditingDomain ed;
	private UserAttributedElement versionElement;
	private EList<UserElementType> uaTypes;
	private String statusMessage;

	public FinalizeTargetVersionAction() {
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
			initDialog();
			initCommand();
			
			for(EObject selectedEobject : selectedEObjects){	
				float targetedVersion = getTargetedVersion(selectedEobject);
				if(targetedVersion != 0){
					List<EObject> refs = ModelSearcher.findReferences(selectedEobject);
					for(EObject ref :refs){
						if(ref instanceof UserAttributedElement){
							versionElement = (UserAttributedElement) ref;
							uaTypes = versionElement.getUaType();
							for(UserElementType uaType:uaTypes){
								EList<UserAttributeDefinition> uaDefinitions = uaType.getUaDefinition();
								for(UserAttributeDefinition uaDefinition: uaDefinitions){
									if(uaDefinition.getShortName().equals("TargetVersion")){
										EAValue Value = uaDefinition.getDefaultValue();
										final EANumericalValue version = (EANumericalValue) Value;									
//										float versionValue = Float.parseFloat(version.getValue());
										float newValueInt = targetedVersion;
										final String newValue = Float.toString(newValueInt);
										ed.getCommandStack().execute(new RecordingCommand((TransactionalEditingDomain)ed) {
										    protected void doExecute() {
										    	version.setValue(newValue);
										    }
										});									
									}
								
								}
							}
						}
					}
				}

			}
				
		}catch (Exception e){
			this.statusMessage = e.getMessage();
			e.printStackTrace();
		}finally{

		}
	}
	
	 private float getTargetedVersion(EObject selectedEobject) {
		// TODO Auto-generated method stub
		 float funVersionValue = 0;
		 if(selectedEobject instanceof ErrorModelType){
			 ErrorModelType errType = (ErrorModelType) selectedEobject;
			 EList<FunctionType> funTypes = errType.getTarget();
			 if(funTypes.size()==1){
				 FunctionType funType = funTypes.get(0);
				 List<EObject> funRefs = ModelSearcher.findReferences(funType);
				 for(EObject funRef: funRefs){
					 if(funRef instanceof UserAttributedElement){
						 UserAttributedElement funVersionElement = (UserAttributedElement) funRef;
						 EList<UserElementType> funUaTypes = funVersionElement.getUaType();
						 for(UserElementType funUaType:funUaTypes){
							 EList<UserAttributeDefinition> funDefs = funUaType.getUaDefinition();
							 for(UserAttributeDefinition funDef : funDefs){
								 if(funDef.getShortName().equals("Version")){
									 EAValue funValue = funDef.getDefaultValue();
									 EANumericalValue funVersion = (EANumericalValue) funValue;
									 funVersionValue = Float.parseFloat(funVersion.getValue());
								 }
							 }
						 }
					 }
				 }
				 return funVersionValue;
			 }
			 else{
				 return 0;
			 }
		 }
		return 0;
		
	}

	/**
     * Round to certain number of decimals
     * 
     * @param d
     * @param decimalPlace
     * @return
     */
    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
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

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// TODO Auto-generated method stub
		shell = targetPart.getSite().getShell();
	}


}