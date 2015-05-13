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

import errorpropagationplugin.Activator;

//import commands.CombineElementCommandException;
//import commands.ExplodeElementCommand;

import commands.CreateErrorModelType;
import commands.IErrorModelCreation;
import commands.MyEContentAdapter;


public class ActionCreateErrorTypeModel implements IObjectActionDelegate{
	
	private Shell shell;
	protected TreePath[] selectedObjects;
	private List<EObject> selectedEObjects;
	private EObject selectedEobject;
	protected EditingDomain ed;
	protected IErrorModelCreation command;
	private String statusMessage;
	private EAXML root;
	private int numOfDep = 1;

	public ActionCreateErrorTypeModel() {
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
//			MyEContentAdapter elementEContentAdapter = new MyEContentAdapter(root);	
			//Create top level packages hierarchy for error model if it does not exist
//			EAPackage superPackage = createErrorModelTopPackages(ed, root);
			Dependability superDep = createErrorModelTopPackages(ed, root);
			
//			ParametersObject params = null;
			// Invoke createErrorModel in command
			for(EObject tempEObject: selectedEObjects){
				command.createErrorModel(ed, tempEObject, superDep);	
			}
				
			
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
		
		this.command = new CreateErrorModelType();


	}
		/**
		 * @see IActionDelegate#selectionChanged(IAction, ISelection)
		 */
		public void selectionChanged(IAction action, ISelection selection) {
		}

		/**
		 * Create the top level packages for later ErrorModel generation 
		 */
		private Dependability createErrorModelTopPackages(EditingDomain ed, EAXML root1) {
		// TODO Auto-generated method stub
		
			EAXML temproot = root1;			
			EList<EAPackage> existPak = temproot.getTopLevelPackage();
			for(int e=0; e<existPak.size();e++){
				String topPakName = existPak.get(e).getShortName();
				if(e<existPak.size()){
					if (!topPakName.equals("EASTADLExtensionElements")) {
						continue;						
					}
					else{
						// if Package "EASTADLExtensionElements" exists
						EAPackage eEE = existPak.get(e);
						EList<EAPackage> subeEEpak = eEE.getSubPackage();
						for(int s=0; s<subeEEpak.size();s++){
							String subPakName = subeEEpak.get(s).getShortName();
							// Package "ExtensionElements" does not contain "DependabilityPackage"
							if(s<subeEEpak.size()){
								if(!subPakName.equals("DependabilityPackage")){	
									continue;								
								}
								else 
								{								
									// if Package "DependabilityPackage" exists under "EASTADLExtensionElements"
									EAPackage depP = subeEEpak.get(s);
							        //Create Dependability called "Dependability" under "DependabilityPackage"							
									Dependability deDependability = Eastadl21Factory.eINSTANCE.createDependability();
									deDependability.setShortName("Dependability"+numOfDep);
									Command adddeDependability = AddCommand.create(ed, depP, Eastadl21Package.DEPENDABILITY, deDependability);
									ed.getCommandStack().execute(adddeDependability);	
									numOfDep++;
									return deDependability;
								}
								
							}
							else{
								//Create Package called Dependability under ExtensionElements
						        EAPackage dePackage = Eastadl21Factory.eINSTANCE.createEAPackage();
						        dePackage.setShortName("DependabilityPackage");
						        Command adddePackage = AddCommand.create(ed, eEE, Eastadl21Package.EA_PACKAGE, dePackage);
								ed.getCommandStack().execute(adddePackage);		
								
								//Create Dependability called "Dependability" under "DependabilityPackage"
								Dependability deDependability = Eastadl21Factory.eINSTANCE.createDependability();
								deDependability.setShortName("Dependability"+numOfDep);
								Command adddeDependability = AddCommand.create(ed, dePackage, Eastadl21Package.DEPENDABILITY, deDependability);
								ed.getCommandStack().execute(adddeDependability);
								numOfDep++;
								return deDependability;
							}
						
						}
					}
				}			
				else // root EAXML does not contain ExtensionElements package
				{ 
					//Create Packages for ExtensionElements
					EAPackage exPackage = Eastadl21Factory.eINSTANCE.createEAPackage();
			        exPackage.setShortName("EASTADLExtensionElements");
					Command addexPackage = AddCommand.create(ed, temproot, Eastadl21Package.EA_PACKAGE, exPackage);
					ed.getCommandStack().execute(addexPackage);
					
					//Create Package called Dependability under ExtensionElements
			        EAPackage dePackage = Eastadl21Factory.eINSTANCE.createEAPackage();
			        dePackage.setShortName("DependabilityPackage");
			        Command adddePackage = AddCommand.create(ed, exPackage, Eastadl21Package.EA_PACKAGE, dePackage);
					ed.getCommandStack().execute(adddePackage);	
					
					//Create Dependability called "Dependability" under "DependabilityPackage"
					Dependability deDependability = Eastadl21Factory.eINSTANCE.createDependability();
					deDependability.setShortName("Dependability"+numOfDep);
					Command adddeDependability = AddCommand.create(ed, dePackage, Eastadl21Package.DEPENDABILITY, deDependability);
					ed.getCommandStack().execute(adddeDependability);	
					return deDependability;
				}
			}
			
			//Create Packages for ExtensionElements
			EAPackage exPackage = Eastadl21Factory.eINSTANCE.createEAPackage();
	        exPackage.setShortName("EASTADLExtensionElements");
			Command addexPackage = AddCommand.create(ed, temproot, Eastadl21Package.EA_PACKAGE, exPackage);
			ed.getCommandStack().execute(addexPackage);
			
			//Create Package called Dependability under ExtensionElements
	        EAPackage dePackage = Eastadl21Factory.eINSTANCE.createEAPackage();
	        dePackage.setShortName("DependabilityPackage");
	        Command adddePackage = AddCommand.create(ed, exPackage, Eastadl21Package.EA_PACKAGE, dePackage);
			ed.getCommandStack().execute(adddePackage);	
			
			//Create Dependability called "Dependability" under "DependabilityPackage"
			Dependability deDependability = Eastadl21Factory.eINSTANCE.createDependability();
			deDependability.setShortName("Dependability"+numOfDep);
			Command adddeDependability = AddCommand.create(ed, dePackage, Eastadl21Package.DEPENDABILITY, deDependability);
			ed.getCommandStack().execute(adddeDependability);
			numOfDep++;
			return deDependability;
	}
	
}



