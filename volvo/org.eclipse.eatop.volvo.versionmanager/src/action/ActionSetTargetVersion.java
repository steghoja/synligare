package action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.eclipse.eatop.eastadl21.EANumericalValue;
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.EAPackageableElement;
import org.eclipse.eatop.eastadl21.EAValue;
import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.Eastadl21Package;
import org.eclipse.eatop.eastadl21.UserAttributeDefinition;
import org.eclipse.eatop.eastadl21.UserElementType;
import org.eclipse.eatop.volvo.versionmanager.Activator;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;

public class ActionSetTargetVersion implements IObjectActionDelegate{

	protected TreePath[] selectedObjects;
	private List<EObject> selectedEObjects;
	private EObject selectedEobject;
	private EAPackage topPackage;
	protected EditingDomain ed;
	private String targetVersion = "";
	
	public ActionSetTargetVersion() {
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
			boolean isErrorElementVersionPackage = false;
			init();			
			initCommand();
			selectedEobject=selectedEObjects.get(0);
			topPackage = (EAPackage) selectedEobject;
			EList<EAPackageableElement> packageElements = topPackage.getElement();
			
			// Check whether ErrorElement Version Package
			for(EAPackageableElement packageElement : packageElements){
				if (packageElement instanceof UserElementType){
					UserElementType userEle = (UserElementType) packageElement;
					EList<UserAttributeDefinition> UaDefinitions = userEle.getUaDefinition();
					for(UserAttributeDefinition UaDefinition : UaDefinitions){
						if(UaDefinition.getShortName().equals("TargetVersion")){
							isErrorElementVersionPackage = true;
						}
					}					
				}
			}
			
			// Get and Set Target Version
			if(isErrorElementVersionPackage == true){
				targetVersion = getTargetVersion();
				setTargetVersionValue();
			}
			else{
				showInformation("Set TargetVersion Error", "The selected package does not contain Target Version");
			}
			// Create corresponding Version for each element
//			createVersion();

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String getTargetVersion() {
	    JFrame frame = new JFrame();
	    Object result = JOptionPane.showInputDialog(frame, "Set all Error Element's Target Versions as:");
	    return result.toString();
	}

	private void setTargetVersionValue() {
		EList<EAPackageableElement> packageElements = topPackage.getElement();
		
		// Check whether ErrorElement Version Package
		for(EAPackageableElement packageElement : packageElements){
			if (packageElement instanceof UserElementType){
				UserElementType userEle = (UserElementType) packageElement;
				EList<UserAttributeDefinition> UaDefinitions = userEle.getUaDefinition();
				for(UserAttributeDefinition UaDefinition : UaDefinitions){
					if(UaDefinition.getShortName().equals("TargetVersion")){
						final EANumericalValue EAValue = (EANumericalValue) UaDefinition.getDefaultValue();
						// Connect the externalFFPL with newPort			
						ed.getCommandStack().execute(new RecordingCommand((TransactionalEditingDomain)ed) {
						    protected void doExecute() {
						    	EAValue.setValue(targetVersion);
						    }
						});
						
					}
				}					
			}
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
