package action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.eclipse.eatop.eastadl21.Dependability;
import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.EANumerical;
import org.eclipse.eatop.eastadl21.EANumericalValue;
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.EAPackageableElement;
import org.eclipse.eatop.eastadl21.EAString;
import org.eclipse.eatop.eastadl21.EAStringValue;
import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.Eastadl21Package;
import org.eclipse.eatop.eastadl21.ErrorBehavior;
import org.eclipse.eatop.eastadl21.ErrorModelPrototype;
import org.eclipse.eatop.eastadl21.ErrorModelType;
import org.eclipse.eatop.eastadl21.FailureOutPort;
import org.eclipse.eatop.eastadl21.FaultInPort;
import org.eclipse.eatop.eastadl21.FunctionConnector;
import org.eclipse.eatop.eastadl21.FunctionPort;
import org.eclipse.eatop.eastadl21.FunctionType;
import org.eclipse.eatop.eastadl21.DesignFunctionType;
import org.eclipse.eatop.eastadl21.FaultFailurePropagationLink;
import org.eclipse.eatop.eastadl21.HardwareComponentType;
import org.eclipse.eatop.eastadl21.Identifiable;
import org.eclipse.eatop.eastadl21.InternalFaultPrototype;
import org.eclipse.eatop.eastadl21.ProcessFaultPrototype;
import org.eclipse.eatop.eastadl21.UserAttributeDefinition;
import org.eclipse.eatop.eastadl21.UserAttributedElement;
import org.eclipse.eatop.eastadl21.UserElementType;
import org.eclipse.eatop.volvo.versionmanager.Activator;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;

import command.progress;


//import command.MyEContentAdapter;
public class ActionSetVersion implements IObjectActionDelegate{

	private Shell shell;
	protected TreePath[] selectedObjects;
	private List<EObject> selectedEObjects;
	private EObject selectedEobject;
	protected EditingDomain ed;
	private String statusMessage;
	private EAXML root;
	private Collection<EAPackageableElement> topElements = null;
	private Collection<EObject> funElements = null;
	private Collection<EObject> errorElements = null;
	private String version = "";
	private String targetVersion = "";
	
	public ActionSetVersion() {
		// TODO Auto-generated constructor stub
		super();
	}

	@Override
	public void run(IAction action) {
		progress bar = new progress();
		JFrame frmMain = null;
		
		// TODO Auto-generated method stub
		IWorkbenchPage page= Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();  
		
	     ISelection myselec= page.getSelection();
	     
	     //System.out.print(myselec.toString());
	     
	     TreeSelection myselecTree= (TreeSelection) myselec;
	     TreePath[] paths = myselecTree.getPaths();
	     this.selectedObjects = paths;
	     

		try {
			topElements = new ArrayList<EAPackageableElement>();
			funElements = new ArrayList<EObject>();
			errorElements = new ArrayList<EObject>();
			init();
			version = setVersion();
			targetVersion = setTargetVersion();
			initCommand();
			selectedEobject=selectedEObjects.get(0);
			root = (EAXML) selectedEobject;
			EList<EAPackage> topPackages = root.getTopLevelPackage();
			// Get all elements
			if(topPackages.size()>0){
				getTopElement(topPackages);
			}
			frmMain = progress.openProgressBar();
			// Create corresponding Version for each element
			createVersion();
			progress.closeProgressBar(frmMain);

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			progress.closeProgressBar(frmMain);
		}
		
	}
	private String setVersion() {
		    JFrame frame = new JFrame();
		    Object result = JOptionPane.showInputDialog(frame, "Set all Versions as:");
		    return result.toString();
	}
	
	private String setTargetVersion() {
	    JFrame frame = new JFrame();
	    Object result = JOptionPane.showInputDialog(frame, "Set all Target Versions as:");
	    return result.toString();
}

	private void createVersion() {
		// TODO Auto-generated method stub
		
		// Create versionPackage for all versiontypes
		EAPackage versionPackage = Eastadl21Factory.eINSTANCE.createEAPackage();
		versionPackage.setShortName("VersionPackage");
		Command addversionPackage = AddCommand.create(ed, root, Eastadl21Package.EA_PACKAGE, versionPackage);
		ed.getCommandStack().execute(addversionPackage);
		
		// Create elementPackage for all VersionElements
		EAPackage versionElementPackage = Eastadl21Factory.eINSTANCE.createEAPackage();
		versionElementPackage.setShortName("VersionElementPackage");
		Command addversionElementPackage = AddCommand.create(ed, versionPackage, Eastadl21Package.EA_PACKAGE, versionElementPackage);
		ed.getCommandStack().execute(addversionElementPackage);
		
		// Create topElementPackage for all topElements
		EAPackage topElementPackage = Eastadl21Factory.eINSTANCE.createEAPackage();
		topElementPackage.setShortName("TopElementPackage");
		Command addtopElementPackage = AddCommand.create(ed, versionPackage, Eastadl21Package.EA_PACKAGE, topElementPackage);
		ed.getCommandStack().execute(addtopElementPackage);	
		// Create VersionType for topElements
		for(EAPackageableElement element : topElements){			
			String eShortName = element.getShortName();
			EObject Object = element;
			setVersionValue(topElementPackage,eShortName,Object,versionElementPackage);			
		}

		// Create funElementPackage for all funElements
		EAPackage funElementPackage = Eastadl21Factory.eINSTANCE.createEAPackage();
		funElementPackage.setShortName("FunElementPackage");
		Command addfunElementPackage = AddCommand.create(ed, versionPackage, Eastadl21Package.EA_PACKAGE, funElementPackage);
		ed.getCommandStack().execute(addfunElementPackage);
		// Create VersionType for functionElements
		for(EObject element : funElements){
			// Get element "shortName" attribute value
			EClass eClass = element.eClass();	
			EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature("shortName");
			if(eStructuralFeature != null){
				Object eGet = element.eGet(eStructuralFeature);
				String eShortName = eGet.toString();
				setVersionValue(funElementPackage,eShortName,element,versionElementPackage);
			}		
		}

		// Create errorElementPackage for all errorElements
		EAPackage errElementPackage = Eastadl21Factory.eINSTANCE.createEAPackage();
		errElementPackage.setShortName("ErrorElementPackage");
		Command adderrElementPackage = AddCommand.create(ed, versionPackage, Eastadl21Package.EA_PACKAGE, errElementPackage);
		ed.getCommandStack().execute(adderrElementPackage);
		// Create VersionType for ErrorElements
		for(EObject element: errorElements){
			// Get element "shortName" attribute value
			EClass eClass = element.eClass();	
			EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature("shortName");
			if(eStructuralFeature != null){
				Object eGet = element.eGet(eStructuralFeature);
				String eShortName = eGet.toString();
				UserElementType versionType = setVersionValue(errElementPackage,eShortName,element,versionElementPackage);
				// Set Target Version Ref
				setTargetVersionValue(versionType);
			}
			
		}

	}

	private void setTargetVersionValue(UserElementType versionType) {
		// TODO Auto-generated method stub
		
		// Create versionDefinition for version value
		UserAttributeDefinition versionDef = Eastadl21Factory.eINSTANCE.createUserAttributeDefinition();
		versionDef.setShortName("TargetVersion");
		Command addVersionDef = AddCommand.create(ed, versionType, Eastadl21Package.USER_ATTRIBUTE_DEFINITION, versionDef);
		ed.getCommandStack().execute(addVersionDef);
		
		// Create VersionValue for each element
		EANumericalValue value = Eastadl21Factory.eINSTANCE.createEANumericalValue();
		value.setValue(targetVersion);
		Command addValue = AddCommand.create(ed, versionDef, Eastadl21Package.EA_NUMERICAL_VALUE, value);
		ed.getCommandStack().execute(addValue);		
	}

	private UserElementType setVersionValue(EAPackage versionPackage,String eShortName, EObject element, EAPackage versionElementPackage) {
		// TODO Auto-generated method stub
		
		// Create versionTypes for all elements
		UserElementType versionType = Eastadl21Factory.eINSTANCE.createUserElementType();
		versionType.setShortName(eShortName+"_VersionType");
		Command addVersionType = AddCommand.create(ed, versionPackage, Eastadl21Package.USER_ELEMENT_TYPE, versionType);
		ed.getCommandStack().execute(addVersionType);
		
		// Create versionDefinition for version value
		UserAttributeDefinition versionDef = Eastadl21Factory.eINSTANCE.createUserAttributeDefinition();
		versionDef.setShortName("Version");
		Command addVersionDef = AddCommand.create(ed, versionType, Eastadl21Package.USER_ATTRIBUTE_DEFINITION, versionDef);
		ed.getCommandStack().execute(addVersionDef);
		
		// Create VersionValue for each element
		EANumericalValue value = Eastadl21Factory.eINSTANCE.createEANumericalValue();
		value.setValue(version);
		Command addValue = AddCommand.create(ed, versionDef, Eastadl21Package.EA_NUMERICAL_VALUE, value);
		ed.getCommandStack().execute(addValue);		
		
		// Create Reference Element to connect VersionType and Original element	
		UserAttributedElement uae = Eastadl21Factory.eINSTANCE.createUserAttributedElement();
		uae.setShortName(eShortName+"_VersionElement");
		uae.getUaType().add(versionType);
		
		Identifiable tempElement= (Identifiable) element;
		uae.setAttributedElement(tempElement);
		
		Command adduae = AddCommand.create(ed, versionElementPackage, Eastadl21Package.USER_ATTRIBUTED_ELEMENT, uae);
		ed.getCommandStack().execute(adduae);	
		
		return versionType;
	}

	private void getTopElement( EList<EAPackage> subPackages) {
		// TODO Auto-generated method stub
		for(EAPackage tempPackage : subPackages){
			Collection<EAPackageableElement> tempElements = tempPackage.getElement();
			if(tempElements.size()>0){
				for(EAPackageableElement tempElement : tempElements){
					topElements.add(tempElement);
					EObject subElement = tempElement;
					getSubElement(subElement);

				}
				
			}

			EList<EAPackage> tempSubPackages = tempPackage.getSubPackage();
			if(tempSubPackages.size()>0){
				getTopElement(tempSubPackages);
			}else{
				
			}// End of packages
		}

	}

	private void getSubElement(EObject subElement) {
		// TODO Auto-generated method stub
		if(subElement instanceof FunctionType){
			FunctionType funType;
			funType = (FunctionType) subElement;
			EList<FunctionConnector> funConnectors = funType.getConnector();
			funElements.addAll(funConnectors);
			EList<FunctionPort> funPorts = funType.getPort();
			funElements.addAll(funPorts);
			if(subElement instanceof DesignFunctionType){
				DesignFunctionType deFunType;
				deFunType = (DesignFunctionType) subElement;
				EList<DesignFunctionPrototype> deFunPart = deFunType.getPart();
				funElements.addAll(deFunPart);
			}
		}
		if(subElement instanceof Dependability){
			Dependability depend;
			depend = (Dependability) subElement;
			EList<ErrorModelType> errorTypes = depend.getErrorModelType();
			errorElements.addAll(errorTypes);
			for(EObject errorType : errorTypes){
				getSubElement(errorType);
			}			
			// Future work
			depend.getFaultFailure();
			depend.getFeatureFlaw();
			depend.getFunctionalSafetyConcept();
		}
		if(subElement instanceof ErrorModelType){
			ErrorModelType emt;
			emt = (ErrorModelType) subElement;
			// Get all subelements
			EList<ErrorBehavior> errBehaviors = emt.getErrorBehaviorDescription();
			EList<FaultInPort> faultInPorts = emt.getExternalFault();
			EList<FailureOutPort> failureOutPorts = emt.getFailure();
			EList<FaultFailurePropagationLink> ffpls = emt.getFaultFailureConnector();
			EList<ErrorModelPrototype> errParts = emt.getPart();
			EList<HardwareComponentType> errHwTargets = emt.getHwTarget();  // Currently Omitted
			EList<InternalFaultPrototype> errInternalFaults = emt.getInternalFault();			
			EList<ProcessFaultPrototype> errProcessFaults = emt.getProcessFault();
			// Add subelements to variable errorElements
			errorElements.addAll(errBehaviors);
			errorElements.addAll(faultInPorts);
			errorElements.addAll(failureOutPorts);
			errorElements.addAll(ffpls);
			errorElements.addAll(errParts);
			errorElements.addAll(errInternalFaults);
			errorElements.addAll(errProcessFaults);
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
		shell = targetPart.getSite().getShell();
	}

}
