package command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.eatop.eastadl21.EANumericalValue;
import org.eclipse.eatop.eastadl21.EAValue;
import org.eclipse.eatop.eastadl21.ErrorModelType;
import org.eclipse.eatop.eastadl21.FunctionType;
import org.eclipse.eatop.eastadl21.Identifiable;
import org.eclipse.eatop.eastadl21.UserAttributeDefinition;
import org.eclipse.eatop.eastadl21.UserAttributedElement;
import org.eclipse.eatop.eastadl21.UserElementType;
import org.eclipse.eatop.volvo.versionmanager.Activator;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.sphinx.platform.ui.util.ExtendedPlatformUI;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.eclipse.ui.plugin.AbstractUIPlugin;



public class CheckConsistencyEMT{
	private UserElementType errElementType;
	private UserElementType targetElementType;
	private ErrorModelType EMT;
	private FunctionType FunType;
	private ArrayList<ErrorModelType> InEMTs =  new ArrayList<ErrorModelType>();
	private boolean Consistent = true;
	private static ImageDescriptor inconsistencyOverlay = AbstractUIPlugin.imageDescriptorFromPlugin("org.eatop.version_control",
			"icon/inconsistency.png");

	
	public boolean CheckConsistencyEMT(EditingDomain ed, EObject selectedEMTElementType, EObject funElementType, ErrorModelType emt) {
		errElementType = (UserElementType) selectedEMTElementType;
		targetElementType = (UserElementType) funElementType;
		EMT = emt;
		EList<FunctionType> FunTypes = emt.getTarget();
		FunType = FunTypes.get(0);
		
		
		EList<UserAttributeDefinition> errDefinitions = errElementType.getUaDefinition();
		EList<UserAttributeDefinition> targetDefinitions = targetElementType.getUaDefinition();
		for(UserAttributeDefinition errDefinition : errDefinitions){
			if(errDefinition.getShortName().equals("TargetVersion")){
				// Get targetVersion of ErrorModelType ElementType
				EAValue targetEAValue = errDefinition.getDefaultValue();
				EANumericalValue targetVersion = (EANumericalValue) targetEAValue;
				String targetVersionValue = targetVersion.getValue();
				// Get Version of Targeted FunctionType
				// Only one definition in FunctionElementType
				UserAttributeDefinition targetDefinition = targetDefinitions.get(0);
				EAValue targetedEAValue = targetDefinition.getDefaultValue();
				EANumericalValue targetedVersion = (EANumericalValue) targetedEAValue;
				String targetedVersionValue = targetedVersion.getValue();
				
				// Compare the version value of targeted and target
				if(targetedVersionValue.equals(targetVersionValue)){					
	              // Connect the externalFFPL with newPort			
					ed.getCommandStack().execute(new RecordingCommand((TransactionalEditingDomain)ed) {
					    protected void doExecute() {
					    	EMT.setText("");
					    }
					});
					
				}
				else{
					 String msg = "Version Inconsistency exists between ErrorModelType " +emt.getShortName()+ " AND it's target FunctionType "+ FunType.getShortName()+"!";
	                 log(msg);
	                 Consistent = false;
	                 
	              // Connect the externalFFPL with newPort			
					ed.getCommandStack().execute(new RecordingCommand((TransactionalEditingDomain)ed) {
					    protected void doExecute() {
					    	EMT.setText("Inconsistency");
					    }
					});
//	         		 if(!InEMTs.contains(emt)||InEMTs.size()==0){
//	        			 InEMTs.add(emt);
//	        		 }	
					
				}
			}
		}
		return Consistent;
	}
	
	public boolean isInconsistency(EObject element) {
		// TODO Auto-generated method stub
		if(element instanceof ErrorModelType){
			ErrorModelType tempEMT = (ErrorModelType) element;
			if(InEMTs.contains(tempEMT)){
				return true;
			}			
		}
	return false;
	}




	/**
     *  Write the message to Problem view
     */ 
       public void log(String msg) {
              log(msg, null);
           }
       
       public void log(String msg, Exception e) {
          String myPluginID = "org.eatop.version_control";         
          Activator activator = Activator.getDefault();
          activator.getLog().log(new Status(Status.INFO, myPluginID, Status.OK, msg, e));
       }



}
