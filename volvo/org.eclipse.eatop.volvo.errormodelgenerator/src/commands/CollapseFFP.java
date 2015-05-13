package commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.eclipse.eatop.common.ui.util.ModelSearcher;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.Eastadl21Package;
import org.eclipse.eatop.eastadl21.ErrorModelPrototype;
import org.eclipse.eatop.eastadl21.ErrorModelType;
import org.eclipse.eatop.eastadl21.FailureOutPort;
import org.eclipse.eatop.eastadl21.FaultFailurePort;
import org.eclipse.eatop.eastadl21.FaultFailurePort_functionTarget;
import org.eclipse.eatop.eastadl21.FaultFailurePropagationLink;
import org.eclipse.eatop.eastadl21.FaultFailurePropagationLink_fromPort;
import org.eclipse.eatop.eastadl21.FaultFailurePropagationLink_toPort;
import org.eclipse.eatop.eastadl21.FaultInPort;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

public class CollapseFFP implements ICollapseFaultFailurePorts{
	private Collection<FaultInPort> selectedFips = new ArrayList<FaultInPort>();
	private Collection<FailureOutPort> selectedFops = new ArrayList<FailureOutPort>();
	private Collection<FaultFailurePropagationLink> ffpls = new ArrayList<FaultFailurePropagationLink>();
	private ErrorModelType eMT;
	private int count = 0;

	
	public void collapseFFP(EditingDomain ed, List<EObject> selectedEObjects,
			int numSelected) {
		// TODO Auto-generated method stub
		if(selectedEObjects.size()>1){
			// Get all selected FFPs	
			getSelectedFFPs(selectedEObjects);	
			// Get the ErrorModelType of selectedErrorModelPrototype if all sit in the same ErrorModelType
			eMT = getErrorModelType();
			// Collapse FIP
			if(selectedFips.size()>0){
				count++;
				// Create new fop for collapsing all existing fops
				FaultInPort newFip = Eastadl21Factory.eINSTANCE.createFaultInPort();
				newFip.setShortName("CollapedFailureOutPort"+ count + "_fip");
				newFip.setUuid(setUUID());					
				Command addnewFip = AddCommand.create(ed, eMT, Eastadl21Package.FAILURE_OUT_PORT, newFip);
				ed.getCommandStack().execute(addnewFip);
				
				// Connect the reference to newPort and Get the referenceFFPL
				getRefFFPL(ed,newFip);					
				// Get all functionTarget and set to newPort
				setFunctionTarget(ed,newFip);
				// Remove Previous fops
				deleteFips(ed);
				
				
			}
			// Collapse FOP
			if(selectedFops.size()>0){
				count++;
				// Create new fop for collapsing all existing fops
				FailureOutPort newFop = Eastadl21Factory.eINSTANCE.createFailureOutPort();
				newFop.setShortName("CollapedFailureOutPort"+ count + "_fop");
				newFop.setUuid(setUUID());					
				Command addnewFop = AddCommand.create(ed, eMT, Eastadl21Package.FAILURE_OUT_PORT, newFop);
				ed.getCommandStack().execute(addnewFop);
				
				// Connect the reference to newPort and Get the referenceFFPL
				getRefFFPL(ed,newFop);					
				// Get all functionTarget and set to newPort
				setFunctionTarget(ed,newFop);
				// Remove Previous fops
				deleteFops(ed);
			}
			
		} // Need to be collapse
		else{
    		showInformation("Collapsing Error", "The selected items should be more than one to collapse");		    		
		}

		
	}
	
	private void deleteFips(EditingDomain ed) {
		// TODO Auto-generated method stub
		Command deleteFip = DeleteCommand.create(ed, selectedFips);
		ed.getCommandStack().execute(deleteFip);
	}


	private void deleteFops(EditingDomain ed) {
		// TODO Auto-generated method stub
			Command deleteFop = DeleteCommand.create(ed, selectedFops);
			ed.getCommandStack().execute(deleteFop);

	}

	private void setFunctionTarget(EditingDomain ed, FailureOutPort newFop) {
		// TODO Auto-generated method stub
		for(FailureOutPort fop: selectedFops){
			EList<FaultFailurePort_functionTarget> funTargets = fop.getFunctionTarget();
			for(FaultFailurePort_functionTarget funTarget : funTargets){
				// Set target for each FaultInPort
				FaultFailurePort_functionTarget functionTarget = Eastadl21Factory.eINSTANCE.createFaultFailurePort_functionTarget();
				functionTarget.setFunctionPort(funTarget.getFunctionPort());
				Command addfunTarget = AddCommand.create(ed, newFop, Eastadl21Package.FAULT_FAILURE_PORT__FUNCTION_TARGET, functionTarget);
				ed.getCommandStack().execute(addfunTarget);
			} // End of loop funTarget	
		} // End of loop fops
	}

	
	private void setFunctionTarget(EditingDomain ed, FaultInPort newFip) {
		// TODO Auto-generated method stub
		for(FaultInPort fip: selectedFips){
			EList<FaultFailurePort_functionTarget> funTargets = fip.getFunctionTarget();
			for(FaultFailurePort_functionTarget funTarget : funTargets){
				// Set target for each FaultInPort
				FaultFailurePort_functionTarget functionTarget = Eastadl21Factory.eINSTANCE.createFaultFailurePort_functionTarget();
				functionTarget.setFunctionPort(funTarget.getFunctionPort());
				Command addfunTarget = AddCommand.create(ed, newFip, Eastadl21Package.FAULT_FAILURE_PORT__FUNCTION_TARGET, functionTarget);
				ed.getCommandStack().execute(addfunTarget);
			} // End of loop funTarget	
		} // End of loop fops
		
	}
	
	private void getRefFFPL(EditingDomain ed, FailureOutPort newFop) {
		// TODO Auto-generated method stub
		final FailureOutPort tempNewPort = newFop;
		for(FailureOutPort fop : selectedFops){
				List<EObject> refs = ModelSearcher.findReferences(fop);
				for(EObject ref: refs){
					if(ref instanceof FaultFailurePropagationLink_fromPort){
						final FaultFailurePropagationLink_fromPort fromPort = (FaultFailurePropagationLink_fromPort) ref;
						
						ed.getCommandStack().execute(new RecordingCommand((TransactionalEditingDomain)ed) {
						    protected void doExecute() {
						    	fromPort.setFaultFailurePort(tempNewPort);
						    }
						});					
					}
					if(ref instanceof FaultFailurePropagationLink_toPort){
						final FaultFailurePropagationLink_toPort toPort = (FaultFailurePropagationLink_toPort) ref;
						
						ed.getCommandStack().execute(new RecordingCommand((TransactionalEditingDomain)ed) {
						    protected void doExecute() {
						    	toPort.setFaultFailurePort(tempNewPort);
						    }
						});					
					}
					EObject refContainer = ref.eContainer();
					if(refContainer!= null){
						ffpls.add((FaultFailurePropagationLink) refContainer);
					}
				}			
		} // End of fop
	}

	private void getRefFFPL(EditingDomain ed, FaultInPort newFip) {
		// TODO Auto-generated method stub
		final FaultInPort tempNewPort = newFip;
		for(FaultInPort fip : selectedFips){
				List<EObject> refs = ModelSearcher.findReferences(fip);
				
				for(EObject ref: refs){
					if(ref instanceof FaultFailurePropagationLink_fromPort){
						final FaultFailurePropagationLink_fromPort fromPort = (FaultFailurePropagationLink_fromPort) ref;						
						ed.getCommandStack().execute(new RecordingCommand((TransactionalEditingDomain)ed) {
						    protected void doExecute() {
						    	fromPort.setFaultFailurePort(tempNewPort);
						    }
						});					
					}
					if(ref instanceof FaultFailurePropagationLink_toPort){
						final FaultFailurePropagationLink_toPort toPort = (FaultFailurePropagationLink_toPort) ref;
						
						ed.getCommandStack().execute(new RecordingCommand((TransactionalEditingDomain)ed) {
						    protected void doExecute() {
						    	toPort.setFaultFailurePort(tempNewPort);
						    }
						});					
					}
					EObject refContainer = ref.eContainer();
					if(refContainer!= null){
						ffpls.add((FaultFailurePropagationLink) refContainer);
					}
				}	
				
		} // End of fop
	}

	private ErrorModelType getErrorModelType() {
		// TODO Auto-generated method stub
		ErrorModelType tempEmt= null;
		// Collapse FIP
		if(selectedFips.size()>0){
			ErrorModelType firstEmt = (ErrorModelType) selectedFips.iterator().next().eContainer();
			for(FaultInPort tempFip : selectedFips){
				tempEmt = (ErrorModelType) tempFip.eContainer();
				if(!tempEmt.getShortName().equals(firstEmt.getShortName())){
					MessageDialog.openInformation(null, "Collapsing Error","The selected FaultFailurePorts should belong to the same ErrorModelType");	
					throw new NullPointerException();
				}	
			}		
			return tempEmt;					
		}
		// Collapse FOP
		if(selectedFops.size()>0){
			ErrorModelType firstEmt = (ErrorModelType) selectedFops.iterator().next().eContainer();
			for(FailureOutPort tempFop : selectedFops){
				tempEmt = (ErrorModelType) tempFop.eContainer();
				if(!tempEmt.getShortName().equals(firstEmt.getShortName())){
					MessageDialog.openInformation(null, "Collapsing Error","The selected FaultFailurePorts should belong to the same ErrorModelType");
					throw new NullPointerException();
				}	
			}		
			return tempEmt;			
		}
		return null;

	}

	private void getSelectedFFPs(List<EObject> selectedEObjects) {
	    if(selectedEObjects== null)  {
	    	throw new NullPointerException();
	    }	    
	    for(EObject e : selectedEObjects){ 
	    	if(e instanceof FaultInPort){
	    		selectedFips.add((FaultInPort) e);	
	    	}
	    	else{
		    	if(e instanceof FailureOutPort){
		    		selectedFops.add((FailureOutPort) e);	
		    	}
		    	else{
		    		showInformation("Collapsing Error", "The selected items should be FaultFailurePort");		    		
		    	}
	    	}
		}
	    if(selectedFips.size()>0 && selectedFops.size()>0){
	    	showInformation("Collapsing Error", "The selected items should be Either FaultInPorts or FailureOutPorts");
	    	throw new NullPointerException();
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
