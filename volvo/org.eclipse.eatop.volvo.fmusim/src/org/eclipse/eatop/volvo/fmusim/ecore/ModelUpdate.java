package org.eclipse.eatop.volvo.fmusim.ecore;

import java.util.Collection;
import java.util.List;

import org.eclipse.eatop.eastadl21.Dependability;
import org.eclipse.eatop.eastadl21.EAElement;
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.Eastadl21Package;
import org.eclipse.eatop.eastadl21.Identifiable;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.VVCase;
import org.eclipse.eatop.eastadl21.VVLog;
import org.eclipse.eatop.eastadl21.VVProcedure;
import org.eclipse.eatop.eastadl21.VVStimuli;
import org.eclipse.eatop.volvo.fmusim.Utils;
import org.eclipse.eatop.volvo.fmusim.VVLogTextXML;
import org.eclipse.eatop.volvo.fmusim.VVLogTextXMLManager;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.workspace.EMFCommandOperation;
import org.eclipse.sphinx.emf.util.WorkspaceEditingDomainUtil;
import org.eclipse.sphinx.emf.util.WorkspaceTransactionUtil;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;



/**
 * This class handles all ecore model updates, i.e. all write operations.
 * 
 * Writing to EMF model needs to be done in a Transaction. 
 *  
 */

//There are several ways to create the transaction, one can also use "commands", like AddCommand etc.
//see http://techblog.goelite.org/making-your-emf-model-transactional/ 
public class ModelUpdate {

	

	public void setVVStimuliText(final VVStimuli vvStimuli, final String text) {
	
		
		Runnable runnable = new Runnable() {
			public void run() {
				
				vvStimuli.setText(text);
				
			}
		};
		
		TransactionalEditingDomain editingDomain = WorkspaceEditingDomainUtil.getEditingDomain(vvStimuli);
  	    try {
		 	 WorkspaceTransactionUtil.executeInWriteTransaction(editingDomain, runnable, "updateVVStimuliText"); //$NON-NLS-1$
		 	 Thread.sleep(0); //if Transaction is async, increase chance it is finished now
  	    } catch (Exception ex) {
  	    	    Utils.showErrorMessage("Exception in ModelUpdate.executeInWriteTransaction. Additional information: " + ex.toString());
				// Ignore exception
			}
	}

	/*
	public void setVVLogText(final VVLog vvLog, final String text) {
	
	EditingDomain ed = AdapterFactoryEditingDomain.getEditingDomainFor(vvLog);
	
	ed.getCommandStack().execute(new RecordingCommand((TransactionalEditingDomain)ed) {
		public void doExecute() {
			vvLog.setText(text);
		}
	});
		
	}
	*/

	public VVLog AddVVLog(VVProcedure vp, VVLogTextXML vvLogTextXML) {
		// TODO Auto-generated method stub
		//Get the EditingDomain 
		EditingDomain ed = AdapterFactoryEditingDomain.getEditingDomainFor(vp);

		//Get the parent VVCase
		EAElement ea = (EAElement)vp;
		VVCase vc = (VVCase)ea.eContainer();
		
		//Create vvLog node
		VVLog vvLog = Eastadl21Factory.eINSTANCE.createVVLog();
		vvLog.setShortName(vp.getShortName() + "_" + Utils.getDateTimeUgly());
		vvLog.setPerformedVVProcedure(vp);
		vvLog.setDate(Utils.getDateTimePretty());	
		
		//Add text attribute as xml string
		VVLogTextXMLManager vvLogTextMgr = VVLogTextXMLManager.getInstance();
		vvLogTextMgr.SaveXML(vvLog,  vvLogTextXML);
				
		Command addvvLog = AddCommand.create(ed, vc, Eastadl21Package.VV_LOG, vvLog);
		ed.getCommandStack().execute(addvvLog);
		
		
		return vvLog;
	}
		

		
		public void updateDecorators(){
			Display.getDefault().asyncExec(new Runnable() {
	            public void run() {
					//Update must be called in GUI thread, this updates all FMUSimStatusDecorators, but will they be shown? 
					PlatformUI.getWorkbench().getDecoratorManager().update("org.eclipse.eatop.fmusim.FMUSimStatusDecorator");
	            }});
			
		}

		
		public void AddPackages(final EAPackage targetPackage, final List<EAPackage> packages) {
			
			EditingDomain ed = AdapterFactoryEditingDomain.getEditingDomainFor(targetPackage);
			
			ed.getCommandStack().execute(new RecordingCommand((TransactionalEditingDomain)ed) {
				public void doExecute() {
					targetPackage.getSubPackage().addAll(packages);
				}
			});
				
			}
		
		
}

	
	 

			
	

