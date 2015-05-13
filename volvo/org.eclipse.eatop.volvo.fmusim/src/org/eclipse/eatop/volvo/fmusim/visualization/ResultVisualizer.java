package org.eclipse.eatop.volvo.fmusim.visualization;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.eatop.common.resource.EastADLURIFactory;
import org.eclipse.eatop.eastadl21.Dependability;
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.Eastadl21Package;
import org.eclipse.eatop.eastadl21.FunctionFlowPort;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.VVCase;
import org.eclipse.eatop.eastadl21.VVLog;
import org.eclipse.eatop.eastadl21.VVProcedure;
import org.eclipse.eatop.eastadl21.impl.VVLogImpl;
import org.eclipse.eatop.volvo.fmusim.ExtStyledText;
import org.eclipse.eatop.volvo.fmusim.StyleRangeXML;
import org.eclipse.eatop.volvo.fmusim.Utils;
import org.eclipse.eatop.volvo.fmusim.VVLogTextXML;
import org.eclipse.eatop.volvo.fmusim.ecore.ModelProcessor;
import org.eclipse.eatop.volvo.fmusim.ecore.ModelUpdate;
import org.eclipse.eatop.volvo.fmusim.simengineinteraction.MonitorOutput;
import org.eclipse.eatop.volvo.fmusim.simengineinteraction.SimEngineInteraction;
import org.eclipse.eatop.volvo.fmusim.userconfig.VVStimuliDataManager;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl.EObjectOutputStream.Check;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;


//Singleton
public class ResultVisualizer {

	
	private static ResultVisualizer instance = null;
	private ResultVisualizer() {
		
	}
	public static synchronized ResultVisualizer getInstance() {
		if (instance == null) {
                	instance = new ResultVisualizer();
		}
		return instance;
	}

	
	ExtStyledText logWidget;
	VVProcedure vp;
	ModelProcessor mp = new ModelProcessor();
	ModelUpdate mUpdate = new ModelUpdate();
	//List<MonitorOutput> monitorOutputs;

	boolean bTestRun = false;
	
	
	public boolean Go(SimEngineInteraction simEngineInteraction, VVProcedure vp, ExtStyledText logWidget){


		this.vp = vp;
		this.logWidget = logWidget;
		bTestRun = false;
		
		logWidget.WriteStyleLn("Visualization", SWT.BOLD);

		logWidget.WriteLn("Creating VVLog element");

		//Create vvLogTextXML object
		VVLogTextXML vvLogTextXML = new VVLogTextXML();
		vvLogTextXML.setLogText(logWidget.getUnformattedText());
		
		String simulationResultFilename = "";
		if (simEngineInteraction.simulationResultFileExist()){
			simulationResultFilename  =  "c:/fmusim/FMUSimResult_" + Utils.getDateTimeUgly() + ".txt";
		}
		vvLogTextXML.setSimulationResultFilename(simulationResultFilename);
		
		String genModelScriptFilename ="";
		genModelScriptFilename = "c:/fmusim/generateFMUSimModel_" + Utils.getDateTimeUgly() + ".m";
		vvLogTextXML.setGenModelScriptFilename(genModelScriptFilename);

		vvLogTextXML.setSimulationResult(simEngineInteraction.getSimulationResult());
		vvLogTextXML.setPassedRequirementPaths(simEngineInteraction.getPassedReqPaths());
		vvLogTextXML.setFailedRequirementPaths(simEngineInteraction.getFailedReqPaths());
//		vvLogTextXML.setPassedMonitorPortPaths(simEngineInteraction.getPassedMonPortPaths());
//		vvLogTextXML.setFailedMonitorPortPaths(simEngineInteraction.getFailedMonPortPaths());
		
		List<StyleRangeXML> srList = toStyleRangeXMLList(logWidget.getAllStyleRanges());
		vvLogTextXML.setStyleRangeXMLs(srList);
		
		VVLog vvLog = mUpdate.AddVVLog(vp, vvLogTextXML);

	
		logWidget.WriteLn("Highlighting");
		Highlighter highlighter = Highlighter.getInstance();
		highlighter.Highlight(vvLog);
		
		mUpdate.updateDecorators();
		
		//Color proc node
		return true;
	}
	
	private List<StyleRangeXML> toStyleRangeXMLList(StyleRange[] styles){
		
		List<StyleRangeXML> out = new ArrayList<StyleRangeXML>(); 
		for (StyleRange s : styles) {
			
			StyleRangeXML srXML;
			if (s.foreground == null)
			{
				srXML = new StyleRangeXML(s.fontStyle, s.length, s.start);
			}
			else
			{
				srXML = new StyleRangeXML(s.fontStyle, s.length, s.start,	s.foreground.getRed(), s.foreground.getGreen(),	s.foreground.getBlue());				
			}
			out.add(srXML);
		}
		return out;
	}
	
	
	
	


 

	
	
	
}
