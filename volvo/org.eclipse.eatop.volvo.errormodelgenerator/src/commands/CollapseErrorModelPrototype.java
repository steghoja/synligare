package commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import modelquery.ModelQueryException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CutToClipboardCommand;
import org.eclipse.emf.edit.command.PasteFromClipboardCommand;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;


import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.Eastadl21Package;
import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignFunctionType;
import org.eclipse.eatop.eastadl21.Enumeration;
import org.eclipse.eatop.eastadl21.EnumerationLiteral;
import org.eclipse.eatop.eastadl21.ErrorBehavior;
import org.eclipse.eatop.eastadl21.ErrorBehaviorKind;
import org.eclipse.eatop.eastadl21.FaultFailurePort;
import org.eclipse.eatop.eastadl21.FaultFailurePort_functionTarget;
import org.eclipse.eatop.eastadl21.FunctionClientServerInterface;
import org.eclipse.eatop.eastadl21.FunctionClientServerPort;
import org.eclipse.eatop.eastadl21.FunctionConnector;
import org.eclipse.eatop.eastadl21.FunctionConnector_port;
import org.eclipse.eatop.eastadl21.ErrorModelType;
import org.eclipse.eatop.eastadl21.ErrorModelPrototype;
import org.eclipse.eatop.eastadl21.FailureOutPort;
import org.eclipse.eatop.eastadl21.FaultInPort;
import org.eclipse.eatop.eastadl21.FunctionFlowPort;
import org.eclipse.eatop.eastadl21.FunctionPort;
import org.eclipse.eatop.eastadl21.FaultFailurePropagationLink;
import org.eclipse.eatop.eastadl21.FaultFailurePropagationLink_fromPort;
import org.eclipse.eatop.eastadl21.FaultFailurePropagationLink_toPort;
import org.eclipse.eatop.eastadl21.FunctionPowerPort;
import org.eclipse.eatop.eastadl21.FunctionPrototype;
import org.eclipse.eatop.eastadl21.ErrorModelPrototype_functionTarget;
import org.eclipse.eatop.eastadl21.FunctionType;

import commands.CreateErrorModelType;

public class CollapseErrorModelPrototype implements
		ICollapseErrorModelPrototype {
	private Collection<ErrorModelPrototype> selectedErrorModelPrototypes = new ArrayList<ErrorModelPrototype>();
	private Collection<FaultFailurePropagationLink> internalFFPL = new ArrayList<FaultFailurePropagationLink>();
	private Collection<FaultFailurePropagationLink> externalFFPL = new ArrayList<FaultFailurePropagationLink>();
	private Collection<FaultFailurePropagationLink_fromPort> fromPorts2combinedType  = new ArrayList<FaultFailurePropagationLink_fromPort>();
	private Collection<FaultFailurePropagationLink_toPort> toPorts2combinedType  = new ArrayList<FaultFailurePropagationLink_toPort>();
	
	
	private Collection<Object>	clipboard = new ArrayList<Object>();
	private ErrorModelType eMT;
	private ErrorModelPrototype cEMP;
	private int count = 0;
	
	@Override
	public void collapseErrorModelPrototype(EditingDomain ed,
			Collection<EObject> selectedEObjects, int num)
			throws ModelQueryException, Exception {
		// TODO Auto-generated method stub
		clipboard = ed.getClipboard();
		ed.setClipboard(null);
		
		count++;
		
		int selectedNum = num;
		getSelectedErrorModelPrototype(selectedEObjects);
		// Get the ErrorModelType of selectedErrorModelPrototype if all sit in the same ErrorModelType
		eMT = getErrorModelType(selectedErrorModelPrototypes);

		if(eMT != null){			
			// Get the ErrorModelType contained FaultFailurePropagationLink and Group them to internal and external
			getFunctionConnector(eMT);
			getFunctionConnectorPortSorted();
						
			// Create a new ErrorModelType for the collapsed ErrorModelPrototypes
			EObject eMTContainer = eMT.eContainer();
			ErrorModelType cEMT = Eastadl21Factory.eINSTANCE.createErrorModelType();
			cEMT.setShortName("CollapsedEMT_EMT"+ count);		
			cEMT.setUuid(setUUID());
			//Set the Target of new ErrorModelType to the first SelectedErrorModelPrototype
			for(ErrorModelPrototype selectedErrorModelPrototype: selectedErrorModelPrototypes){
				ErrorModelType tempEMT = selectedErrorModelPrototype.getType();
				EList<FunctionType> tempFunTypes = tempEMT.getTarget();
				cEMT.getTarget().addAll(tempFunTypes);
			}
			
			// Add command addcEMT
			Command addcEMT = AddCommand.create(ed, eMTContainer, Eastadl21Package.ERROR_MODEL_TYPE, cEMT);
			ed.getCommandStack().execute(addcEMT);
			
			
			//cut and paste the selected ErrorModelPrototypes
			Command eMPCut2Clipboard = CutToClipboardCommand.create(ed, selectedErrorModelPrototypes);
			ed.getCommandStack().execute(eMPCut2Clipboard);
			
			Command eMPPaste2Clipboard = PasteFromClipboardCommand.create(ed, cEMT,  Eastadl21Package.ERROR_MODEL_TYPE);
			ed.getCommandStack().execute(eMPPaste2Clipboard);
			ed.getClipboard().clear();
			
			// cut and paste the internalFFPL
			if (internalFFPL.size()>0){
				Command inffplCut2Clipboard = CutToClipboardCommand.create(ed, internalFFPL);
				ed.getCommandStack().execute(inffplCut2Clipboard);
				Command inffplPaste4Clipboard = PasteFromClipboardCommand.create(ed, cEMT,Eastadl21Package.FAULT_FAILURE_PROPAGATION_LINK);
				ed.getCommandStack().execute(inffplPaste4Clipboard);
				ed.getClipboard().clear();
				}
			
			// Create a new ErrorModelPrototype has a Type of cEMT
			cEMP = Eastadl21Factory.eINSTANCE.createErrorModelPrototype();
			cEMP.setShortName("CollapsedEMProto_EMp"+count);
			cEMP.setUuid(setUUID());
			cEMP.setType(cEMT);
			// Add command addcEMP
			Command addcEMP = AddCommand.create(ed, eMT, Eastadl21Package.ERROR_MODEL_PROTOTYPE, cEMP);
			ed.getCommandStack().execute(addcEMP);
			
			// Create ErrorModelPrototype_functionTarget for the created ErrorModelPrototype cEMP
			for(ErrorModelPrototype tempEMP : selectedErrorModelPrototypes){			
				ErrorModelPrototype_functionTarget cEMP_funTarget = Eastadl21Factory.eINSTANCE.createErrorModelPrototype_functionTarget();
				cEMP_funTarget.setFunctionPrototype((FunctionPrototype) tempEMP.getTarget());
				// Add command cEMP_funTarget
				Command addcEMP_funTarget = AddCommand.create(ed, cEMP, Eastadl21Package.ERROR_MODEL_PROTOTYPE__FUNCTION_TARGET, cEMP_funTarget);
				ed.getCommandStack().execute(addcEMP_funTarget);				
			}

			
			// Create Ports on new created cEMT
			createPortsOnNewEMT(ed,cEMT);

			// Clean ClipBoard
			ed.setClipboard(null);			
		} // end if ErrorModelType legally exists
	} // End of collapseErrorModelPrototype Override


	private void createPortsOnNewEMT(EditingDomain ed, ErrorModelType cEMT) {
		// TODO Auto-generated method stub
		for(final FaultFailurePropagationLink_fromPort fromPort :fromPorts2combinedType){
			FaultFailurePort fffp = fromPort.getFaultFailurePort();
			EList<ErrorModelPrototype> fffprototype = fromPort.getErrorModelPrototype();
			final FaultFailurePort tempPort = createPorts(ed,cEMT,fffp,fffprototype);	
			
			// Connect the externalFFPL with newPort			
			ed.getCommandStack().execute(new RecordingCommand((TransactionalEditingDomain)ed) {
			    protected void doExecute() {
			    	fromPort.setFaultFailurePort(tempPort);
			    	fromPort.getErrorModelPrototype().add(cEMP);
			    }
			});
			
			
		}
		for(final FaultFailurePropagationLink_toPort toPort :toPorts2combinedType){
			FaultFailurePort fftp = toPort.getFaultFailurePort();
			EList<ErrorModelPrototype> fftprototype = toPort.getErrorModelPrototype();
			final FaultFailurePort tempPort = createPorts(ed,cEMT,fftp,fftprototype);
			// Connect the externalFFPL with newPort
			ed.getCommandStack().execute(new RecordingCommand((TransactionalEditingDomain)ed) {
			    protected void doExecute() {
			    	toPort.setFaultFailurePort(tempPort);
			    	toPort.getErrorModelPrototype().add(cEMP);
			    }
			});
		}
	}

	private FaultFailurePort createPorts(EditingDomain ed, ErrorModelType cEMT, FaultFailurePort ffp, EList<ErrorModelPrototype> ffprototype) {
		// TODO Auto-generated method stub
		FaultFailurePort newPort = null;
		if(ffp.getShortName().toString().contains("_fip")){
			// Add FaultInPort in newEMT
			newPort = Eastadl21Factory.eINSTANCE.createFaultInPort();
			newPort.setShortName(cEMT.getShortName()+"_fip");
			newPort.setUuid(setUUID());
			// Add command
			Command addnewPort = AddCommand.create(ed, cEMT, Eastadl21Package.FAULT_IN_PORT, newPort);
			ed.getCommandStack().execute(addnewPort);
			// Create FFPL for newPort(FaultInPort)
			createFFPL4newPort(ed,cEMT,newPort,ffp,ffprototype);
		}
		if(ffp.getShortName().toString().contains("_fop")){
			// Add FaultInPort in newEMT
			newPort = Eastadl21Factory.eINSTANCE.createFailureOutPort();
			newPort.setShortName(cEMT.getShortName()+"_fop");
			newPort.setUuid(setUUID());
			// Add command
			Command addnewPort = AddCommand.create(ed, cEMT, Eastadl21Package.FAILURE_OUT_PORT, newPort);
			ed.getCommandStack().execute(addnewPort);
			// Create FFPL for newPort(FailureOutPort)
			createFFPL4newPort(ed,cEMT,newPort,ffp,ffprototype);
		}		
		return newPort;
	}


	private void createFFPL4newPort(EditingDomain ed,
			ErrorModelType cEMT, FaultFailurePort newPort,
			FaultFailurePort ffp, EList<ErrorModelPrototype> ffprototype) {
		// TODO Auto-generated method stub
		
		// Create newFFPL
		FaultFailurePropagationLink newffpl = Eastadl21Factory.eINSTANCE.createFaultFailurePropagationLink();
		newffpl.setShortName(newPort.getShortName()+" & "+ffp.getShortName()+"_ffpl");
		newffpl.setUuid(setUUID());
		Command addnewffpl = AddCommand.create(ed, cEMT, Eastadl21Package.FAULT_FAILURE_PROPAGATION_LINK, newffpl);
		ed.getCommandStack().execute(addnewffpl);
		
		if(newPort instanceof FaultInPort){
			// Add FFPL_fromPort to newPort
			FaultFailurePropagationLink_fromPort newffpl_newPort = Eastadl21Factory.eINSTANCE.createFaultFailurePropagationLink_fromPort();
			newffpl_newPort.setFaultFailurePort(newPort);
			newffpl_newPort.getErrorModelPrototype().add(cEMP);
			Command add_newffpl_newPort = AddCommand.create(ed, newffpl, Eastadl21Package.FAULT_FAILURE_PROPAGATION_LINK__FROM_PORT, newffpl_newPort);
			ed.getCommandStack().execute(add_newffpl_newPort);
			
			// Add FFPL_toPort to ffp
			FaultFailurePropagationLink_toPort newffpl_ffp = Eastadl21Factory.eINSTANCE.createFaultFailurePropagationLink_toPort();
			newffpl_ffp.setFaultFailurePort(ffp);
			newffpl_ffp.getErrorModelPrototype().addAll(ffprototype);
			Command add_newffpl_ffp = AddCommand.create(ed, newffpl, Eastadl21Package.FAULT_FAILURE_PROPAGATION_LINK__TO_PORT, newffpl_ffp);
			ed.getCommandStack().execute(add_newffpl_ffp);
		}
		else{ // newPort is instanceof FailureOutPort
			// Add FFPL_toPort to newPort
			FaultFailurePropagationLink_toPort newffpl_newPort = Eastadl21Factory.eINSTANCE.createFaultFailurePropagationLink_toPort();
			newffpl_newPort.setFaultFailurePort(newPort);
			newffpl_newPort.getErrorModelPrototype().add(cEMP);
			Command add_newffpl_newPort = AddCommand.create(ed, newffpl, Eastadl21Package.FAULT_FAILURE_PROPAGATION_LINK__TO_PORT, newffpl_newPort);
			ed.getCommandStack().execute(add_newffpl_newPort);
			
			// Add FFPL_fromPort to ffp
			FaultFailurePropagationLink_fromPort newffpl_ffp = Eastadl21Factory.eINSTANCE.createFaultFailurePropagationLink_fromPort();
			newffpl_ffp.setFaultFailurePort(ffp);
			newffpl_ffp.getErrorModelPrototype().addAll(ffprototype);
			Command add_newffpl_ffp = AddCommand.create(ed, newffpl, Eastadl21Package.FAULT_FAILURE_PROPAGATION_LINK__FROM_PORT, newffpl_ffp);
			ed.getCommandStack().execute(add_newffpl_ffp);
			
		}

	}
	
	
	/**
	 * Set UUID for elements
	 */	
	private String setUUID() {
	// TODO Auto-generated method stub
		// Get timeStamp and generate UniqueID
		String timeStamp = String.valueOf(System.currentTimeMillis());
		UUID uniqueID = UUID.randomUUID();
		String tempUUID = uniqueID + "-" + timeStamp;
	return tempUUID;
}
	
	
	
	/**
	 * Get the ErrorModelType contained FaultFailurePropagationLink and Group them to internal and external
	 */
	private void getFunctionConnector(ErrorModelType eMT) {
		// TODO Auto-generated method stub
		 ErrorModelType tempEMT = eMT;
		 EList<FaultFailurePropagationLink> ffpls = tempEMT.getFaultFailureConnector();


		 for(FaultFailurePropagationLink ffpl : ffpls){
			 int numFFPLProto = 0;
			 Collection<ErrorModelPrototype> ffplAllPortProto = new ArrayList<ErrorModelPrototype>();
			 Collection<ErrorModelPrototype> ffplFPortProto = new ArrayList<ErrorModelPrototype>();
			 Collection<ErrorModelPrototype> ffplTPortProto = new ArrayList<ErrorModelPrototype>();
			 FaultFailurePropagationLink_fromPort ffplFromPort = ffpl.getFromPort();
			 FaultFailurePropagationLink_toPort ffplToPort = ffpl.getToPort();
			 ErrorModelPrototype containedProto = null;
			 
			 if(ffplFromPort!=null){
				 ffplFPortProto = ffplFromPort.getErrorModelPrototype();
			 }
			 if(ffplToPort!=null){
				 ffplTPortProto = ffplToPort.getErrorModelPrototype();
			 }
			 // Get all the contained prototype
			 ffplAllPortProto.addAll(ffplFPortProto);
			 ffplAllPortProto.addAll(ffplTPortProto);
			 if(ffplAllPortProto!=null){
				 for(ErrorModelPrototype tempPortProto : ffplAllPortProto){
					 // Check if the ffplportProto is included in the selectedErrorModelPrototype
					 if(selectedErrorModelPrototypes.contains(tempPortProto)){
						 containedProto = tempPortProto;
						 numFFPLProto++;
					 }
				 } // end of loop FFPLPortPrototypes
				 
				if (numFFPLProto >= 2){
					internalFFPL.add(ffpl);
				}
				else if (numFFPLProto == 1){
					externalFFPL.add(ffpl);
				}
		 
			 }
		 }// end of loop all faultfailurepropagationlink in EMT
	}
	
	private void getFunctionConnectorPortSorted() {
		for(FaultFailurePropagationLink exffpl : externalFFPL){
			FaultFailurePropagationLink_fromPort exffplFPort = exffpl.getFromPort();
			FaultFailurePropagationLink_toPort exffplTPort = exffpl.getToPort();
			EList<ErrorModelPrototype> exfffprotos = exffplFPort.getErrorModelPrototype();
			EList<ErrorModelPrototype> exfftprotos = exffplTPort.getErrorModelPrototype();
			
			for(ErrorModelPrototype exfffproto: exfffprotos){
				if(selectedErrorModelPrototypes.contains(exfffproto)){
					fromPorts2combinedType.add(exffplFPort);
				}				
			}
			for(ErrorModelPrototype exfftproto: exfftprotos){
				if(selectedErrorModelPrototypes.contains(exfftproto)){
					toPorts2combinedType.add(exffplTPort);
				}
				
			}
		}
	}
	

	/**
	 * Get the ErrorModelType of selectedErrorModelPrototype
	 */	
	private ErrorModelType getErrorModelType(
			Collection<ErrorModelPrototype> tempselectedErrorModelPrototypes) {
		// TODO Auto-generated method stub
		ErrorModelType tempEmt= null;
		ErrorModelType firstEmt = (ErrorModelType) tempselectedErrorModelPrototypes.iterator().next().eContainer();
		for(ErrorModelPrototype tempErrorModelProto : tempselectedErrorModelPrototypes){
			tempEmt = (ErrorModelType) tempErrorModelProto.eContainer();
			if(!tempEmt.getShortName().equals(firstEmt.getShortName())){
				MessageDialog.openInformation(null, "Error in ErrorModelPrototype Collapse","The selected ErrorModelPrototypes should belong to the same ErrorModelType");	
				return null;
			}	
		}		
		return tempEmt;
		
	}
	/**
	 * Get the selectedEObjects and cast to ErrorModelPrototype
	 */
	private void getSelectedErrorModelPrototype(Collection<EObject> selectedEObjects){
	    if(selectedEObjects== null)  {
	    	throw new NullPointerException();
	    }
	    if(selectedEObjects.size()<=1){
	    	showInformation("Collapsing Error", "The selected ErrorModelPrototypes for collapsing should be more than one! " );
	    	throw new NullPointerException();
	    }
	    
	    for(EObject e : selectedEObjects){ 
	    	if(e instanceof ErrorModelPrototype){
	    		selectedErrorModelPrototypes.add((ErrorModelPrototype) e);	
	    	}
	    	else{
	    		showInformation("Collapsing Error", "The selected items shoul be ErrorModelPrototype");
	    	}
		}
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
