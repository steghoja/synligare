package org.eclipse.eatop.volvo.fmusim;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.eclipse.eatop.eastadl21.DesignFunctionType;
import org.eclipse.eatop.eastadl21.FunctionFlowPort;
import org.eclipse.eatop.eastadl21.VVCase;
import org.eclipse.eatop.eastadl21.VVProcedure;
import org.eclipse.eatop.eastadl21.VVStimuli;
import org.eclipse.eatop.volvo.fmusim.ecore.ModelProcessor;
import org.eclipse.eatop.volvo.fmusim.simengineinteraction.FMUSimResult;
import org.eclipse.eatop.volvo.fmusim.simengineinteraction.MonitorOutput;
import org.eclipse.eatop.volvo.fmusim.simengineinteraction.SimEngineInteraction;
import org.eclipse.eatop.volvo.fmusim.simengineinteraction.SimulinkProxyInvoker;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.FMUTaskBuilder;
import org.eclipse.eatop.volvo.fmusim.userconfig.ConfigurationMgr;
import org.eclipse.eatop.volvo.fmusim.userconfig.FMUSimulationConfigDialog;
import org.eclipse.eatop.volvo.fmusim.userconfig.VVStimuliDataManager;
import org.eclipse.eatop.volvo.fmusim.visualization.ResultVisualizer;
import org.eclipse.core.internal.registry.Handle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.actions.SimpleWildcardTester;
import org.omg.CORBA.portable.InvokeHandler;

public class FMUSimEngine {

	private CountDownLatch logReadyLatch;

	
	public FMUSimEngine() {

		
		
	}
	
	
	/***
	 * Main method for configuring, preparing, executing & visualizing an FMU simulation task.
	 */
		public void Go(final VVProcedure vp) {
		
			logReadyLatch = new CountDownLatch(1);
			
			//User configuration
			if (!ConfigurationMgr.getInstance().GetUserConfiguration(vp)) return;

			//create status dialog
		    final FMUSimulationDialog simDialog = new FMUSimulationDialog(Utils.getShell(), new LogReadyCB()); 
			
	
			//Run the rest of the engine in a separate worker thread, so we can have a 
			//status dialogue in the GUI thread
			Thread worker = new Thread(new Runnable() {
			     public void run() {
			      
			    	
			    	 
			    	 //Wait for the dialog to create the log widget
			    	 try {
						logReadyLatch.await();
					} catch (InterruptedException e) {
						Utils.showErrorMessage("FMUSimEngine method Go: Interrupted exception.");
						return;
					}
			    	 
			    	boolean bStatus;
			    	 
			    	
			    	ExtStyledText logWidget = simDialog.getLogWidget();
			    	logWidget.WriteStyleLn("FMUSimulation engine starting " + Utils.getDateTimePretty(), SWT.BOLD);
			    	logWidget.WriteLn("");

			    	//Start TaskBuilder engine
			    	 FMUTaskBuilder taskBuilder = new FMUTaskBuilder(logWidget, vp);
			    	 taskBuilder.Go();	
			     	 
			    	 //Simulink interaction
			    	 SimEngineInteraction simEngine = new SimEngineInteraction(logWidget);
			    	 bStatus = simEngine.Go(vp);
			  //  	 if (!bStatus) return;
			    	 
			    	 //Visualise results
			    	 ResultVisualizer resVisualizer = ResultVisualizer.getInstance();
			    	 bStatus = resVisualizer.Go(simEngine, vp, logWidget);
			    				    	 
			    	 		
			    	 
			     }});
			
			worker.start();
			
			simDialog.open();

			//returns upon ok or cancel
			
		}
			
		
		class LogReadyCB implements Runnable {
			public void run() {
				logReadyLatch.countDown();
			}
		} 
		
		
			
	}

		
		
		
		
	

