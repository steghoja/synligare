package org.eclipse.eatop.volvo.fmusim.taskbuilder;

import java.io.File;

import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignFunctionType;
import org.eclipse.eatop.eastadl21.Environment;
import org.eclipse.eatop.eastadl21.VVCase;
import org.eclipse.eatop.eastadl21.VVProcedure;
import org.eclipse.eatop.volvo.fmusim.ExtStyledText;
import org.eclipse.eatop.volvo.fmusim.FMUSimulationDialog;
import org.eclipse.eatop.volvo.fmusim.Utils;
import org.eclipse.eatop.volvo.fmusim.ecore.ModelProcessor;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.ModelScriptWriter;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.SimpleLayoutManager;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.SimulinkSystem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;


/***
 * This class translates the ExperimentSetup from the Ecore model to a simulink model in memory
 *- FMUs to simulate incl. execution time & period
 * - find the monitor
 * - find the outports (inports already found)
 * 
 * Then the simulink model is serialized
 * 
 * Finally a a script for controlling the simulation is generated.
 *   *  
 *  Output:
 *   generateFMUSimTopLevel_DATETIME.m - to generate simulink .mdl
 *   FMUsimulation_DATETIME.m          - to control simulation of the generated model
 *  
 */

public class FMUTaskBuilder {

	
	ExtStyledText logWidget;
	VVProcedure vvProcedure;
	
	public FMUTaskBuilder(ExtStyledText logWidget, VVProcedure vp) {
	
		this.logWidget = logWidget;
		this.vvProcedure = vp;
	}
	
	public boolean Go(){
		ModelProcessor mp = new ModelProcessor();
		DesignFunctionPrototype dfpSUT = mp.findSUT(vvProcedure);
		Environment environment = mp.findEnvironment(vvProcedure);
		
		//remove any old file - so we're sure that we don't run an old file later
    	 if (!deleteGenModelFile()) return false;
		
		logWidget.WriteStyleLn("FMUTaskBuilder starting.", SWT.BOLD);
		
		logWidget.WriteLn("Translating Ecore --> Simulink model");
		SimulinkSystem sModelRoot = mp.Translate(dfpSUT, environment);

		logWidget.WriteLn("Improving model layout");
		SimpleLayoutManager simpleLayoutManager = new SimpleLayoutManager();
		simpleLayoutManager.Improve(sModelRoot);
		
		logWidget.WriteLn("Saving model generator script.");
		ModelScriptWriter msw = new ModelScriptWriter();
		msw.writeModelScript(sModelRoot);
		
		logWidget.WriteLn("");
		
		
		return true;
	}

	protected boolean deleteGenModelFile() {
		File modelFile = new File("c:/fmusim/generateFMUSimModel.m");
		 if (modelFile.exists())
		 {
			 if (!modelFile.delete()){
				 Utils.showErrorMessage("Failed to delete old generateFMUSimModel.m. Still open in Matlab/Simulink?");
				 return false;
			 }
		 }
		 return true;
	}
	
}
