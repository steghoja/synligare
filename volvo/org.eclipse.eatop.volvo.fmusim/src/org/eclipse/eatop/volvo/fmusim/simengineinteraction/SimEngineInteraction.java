package org.eclipse.eatop.volvo.fmusim.simengineinteraction;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.eatop.common.resource.EastADLURIFactory;
import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.FunctionFlowPort;
import org.eclipse.eatop.eastadl21.GenericConstraint;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.VVProcedure;
import org.eclipse.eatop.volvo.fmusim.ExtStyledText;
import org.eclipse.eatop.volvo.fmusim.Utils;
import org.eclipse.eatop.volvo.fmusim.ecore.ModelProcessor;
import org.eclipse.core.internal.registry.OffsetTable;
import org.eclipse.swt.SWT;

public class SimEngineInteraction {

	ExtStyledText logWidget;
	FMUSimResult simResult = new FMUSimResult();
	
	boolean bSimulationResult; 
	List<Requirement> passedReqs = new ArrayList<Requirement>();
	List<String> passedReqPaths = new ArrayList<String>();
	
	List<Requirement> failedReqs = new ArrayList<Requirement>();	
	List<String> failedReqPaths = new ArrayList<String>();	
	
//	List<String> passedMonPortPaths = new ArrayList<String>();
//	List<String> failedMonPortPaths = new ArrayList<String>();	
	
	
	boolean modelGenerationScriptExist = false;
	boolean simulationResultFileExist = false;
	
	
	
/*	public boolean modelGenerationScriptExist() {
		return modelGenerationScriptExist;
	}
*/


	public boolean simulationResultFileExist() {
		return simulationResultFileExist;
	}



	public SimEngineInteraction(ExtStyledText logWidget){
		this.logWidget = logWidget;
	}
	
	
	
	public boolean getSimulationResult() {
		return bSimulationResult;
	}



	public List<Requirement> getPassedReqs() {
		return passedReqs;
	}



	public List<Requirement> getFailedReqs() {
		return failedReqs;
	}



	public List<MonitorOutput> getMonitorOutput() {
		return simResult.getOutputs();
	}


	public List<String> getPassedReqPaths() {
		return passedReqPaths;
	}



	public List<String> getFailedReqPaths() {
		return failedReqPaths;
	}

/*	public List<String> getPassedMonPortPaths() {
		return passedMonPortPaths;
	}
*/


	/*public List<String> getFailedMonPortPaths() {
		return failedMonPortPaths;
	}
*/

	public boolean Go(VVProcedure vp)
	{

		logWidget.WriteStyleLn("Simulation Engine Interaction", SWT.BOLD);

		logWidget.Write("Invoking Simulink");
		SimulinkProxyInvoker invoker = new SimulinkProxyInvoker(logWidget);

		//skip invocation - TEMP
		//if (true)
		//	return false;
		
		boolean b = invoker.Execute();
		if (b)
		{
			this.simulationResultFileExist = true;
			
			logWidget.WriteLn("Parsing results");
			if (simResult.Parse()){
				if (CheckGotExpectedMonitors(vp, simResult.getOutputs())){
					CheckResult(simResult.getOutputs(), vp);
					CopyLogAnalysisFiles();
					return true;
				}
			}
			else{
				logWidget.WriteColorLn("Failed to invoke simulink.", SWT.COLOR_RED);
			}
		}
		else {
			this.simulationResultFileExist = false;
			logWidget.WriteColorLn("Failed to invoke simulink.", SWT.COLOR_RED);
		}
		logWidget.WriteLn("");
		
		return false;

	}
   		
 	private boolean CheckGotExpectedMonitors(VVProcedure vp, List<MonitorOutput> monitorOutputs){
		ModelProcessor mp = new ModelProcessor();
 		
 		List<FunctionFlowPort> expectedPorts = mp.findUsedMonitorOutPorts(vp);
		
		for (FunctionFlowPort ffp : expectedPorts){
			MonitorOutput mE = new MonitorOutput(ffp.getShortName());
			if (!monitorOutputs.contains(mE)){
				Utils.showErrorMessage("Expected simulink result data for out port " + ffp.getShortName());
				return false;
			}
		}
		return true;
	}

 	private void CheckResult(List<MonitorOutput> monitorOutputs, VVProcedure vp){
 		
 		List<MonitorOutput> failedMonitors = new ArrayList<MonitorOutput>();
 		List<MonitorOutput> okMonitors = new ArrayList<MonitorOutput>();

 		ModelProcessor mp = new ModelProcessor();
		
 		
 		for (MonitorOutput mo : monitorOutputs){
 			if (mo.IsAllTrue()){
 				okMonitors.add(mo);}
 			else{
 				failedMonitors.add(mo);
 				}
 				
 		}
 		
 		
 		logWidget.WriteLn("Passed requirements:");
 		if (okMonitors.isEmpty()){
 			logWidget.WriteLn("-");
 		}
		for (MonitorOutput m : okMonitors) {
 			//Requirements
			String monOutPortName = m.getName();
			Requirement R= mp.findRequirement(monOutPortName, vp);
			
			if (R == null) return;
			
			passedReqs.add(R);
			
			String path = EastADLURIFactory.getAbsoluteQualifiedName(R);
			passedReqPaths.add(path);
			logWidget.WriteColorLn(R.getShortName(), SWT.COLOR_DARK_GREEN);
			
			//Monitor
			//FunctionFlowPort mout = mp.findMonitorOutPort(monOutPortName);
			//String monPath = EastADLURIFactory.getAbsoluteQualifiedName(mout);
			//passedMonPortPaths.add(path);
			
		}

 		logWidget.WriteLn("Failed requirements:");
 		if (failedMonitors.isEmpty()){
 			logWidget.WriteLn("-");
 		}
 		for (MonitorOutput m : failedMonitors) {
 			String monOutPortName = m.getName();
			Requirement R= mp.findRequirement(monOutPortName, vp);
			
			if (R == null) return;
			failedReqs.add(R);
			String path = EastADLURIFactory.getAbsoluteQualifiedName(R);
			failedReqPaths.add(path);
			logWidget.WriteColorLn(R.getShortName(), SWT.COLOR_DARK_RED);

			//Monitor
			//FunctionFlowPort mout = mp.findMonitorOutPort(monOutPortName);
			//String monPath = EastADLURIFactory.getAbsoluteQualifiedName(mout);
			//failedMonPortPaths.add(path);

 		}
 		
 				
 		logWidget.Write("Procedure verification result: ");
		if (!(failedMonitors.isEmpty())){
			logWidget.WriteLn("FAIL", SWT.BOLD, SWT.COLOR_RED);
			bSimulationResult = false; 
 		}
 		else {
			logWidget.WriteLn("OK", SWT.BOLD, SWT.COLOR_DARK_GREEN);
			bSimulationResult = true; 

 		}
 		
		logWidget.WriteLn ("");
 	}


	 private void CopyLogAnalysisFiles()
	 {
		 String sMFileSaved = "c:/fmusim/generateFMUSimModel_" + Utils.getDateTimeUgly() + ".m";
		 String sRFileSaved = "c:/fmusim/FMUSimResult_" + Utils.getDateTimeUgly() + ".txt";
		 
		 File mFile = new File("c:/fmusim/generateFMUSimModel.m");
		 File mFileSaved = new File(sMFileSaved);

		 File rFile = new File("c:/fmusim/FMUSimResult.txt");
		 File rFileSaved = new File(sRFileSaved);

		 /*
		 if (! mFile.renameTo(mFileSaved)){
			 Utils.showErrorMessage("Failed to rename FMUSimResult.txt file");
		 }
		*/ 
		 try{
			 Utils.copyFileUsingFileStreams(mFile,  mFileSaved);
		 }
		catch (IOException e) {
			 Utils.showErrorMessage("Failed to copy generateFMUSimModel.m file");
		}
		 
		 try{
			 Utils.copyFileUsingFileStreams(rFile,  rFileSaved);
		 }
		catch (IOException e) {
			 Utils.showErrorMessage("Failed to copy FMUSimResult.m file");
		}
	
		 
	
		/* if (!rFile.renameTo(rFileSaved)){
			 Utils.showErrorMessage("Failed to rename FMUSimResult.txt file");
		 }
	*/
	 
	 }
		 

 	
}
