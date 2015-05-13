package org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




import org.eclipse.eatop.eastadl21.ClampConnector;
import org.eclipse.eatop.eastadl21.ClampConnector_port;
import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignFunctionType;
import org.eclipse.eatop.eastadl21.EADirectionKind;
import org.eclipse.eatop.eastadl21.FunctionConnector;
import org.eclipse.eatop.eastadl21.FunctionConnector_port;
import org.eclipse.eatop.eastadl21.FunctionFlowPort;
import org.eclipse.eatop.eastadl21.FunctionPort;
import org.eclipse.eatop.eastadl21.FunctionPrototype;
import org.eclipse.eatop.volvo.fmusim.Utils;
import org.eclipse.eatop.volvo.fmusim.ecore.PortPrototype;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.DFTExecutionInfo;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.EventTriggeredDFTExecutionInfo;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.TimeTriggeredDFTExecutionInfo;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.Block;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.Clock;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.Constant;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.DetectDecrease;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.FMU_ME;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.From;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.Goto;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.Inport;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.LogicalOperator;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.Memory;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.MultipleInputsBlock;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.Outport;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.PulseGenerator;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.RelationalOperator;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.RepeatingSequenceInterpolated;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.Scope;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.Subsystem;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.Sum;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.Switch;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.Trigger;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.TriggeredSubsystem;
import org.eclipse.eatop.volvo.fmusim.userconfig.ConfigurationMgr;
import org.eclipse.eatop.volvo.fmusim.userconfig.InportXMLData;
import org.eclipse.eatop.volvo.fmusim.userconfig.VVStimuliDataManager;
import org.eclipse.eatop.volvo.fmusim.userconfig.VVStimuliXMLData;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.MessageBox;



import java.awt.Point;

public class SimulinkSystemBuilder {

	SimulinkSystem system;

	public SimulinkSystemBuilder(boolean topLevel) {

		system = new SimulinkSystem(topLevel);
	}

	public void setName(String name) {
		system.setName(name);
	}

	public String getName() {
		return system.getName();
	}

	public SimulinkSystem getSimulinkSystem() {
		return system;
	}

	public Subsystem getParentSubsystem() {
		return system.getParentSubsystem();
	}

	// use maps to find source and target for links easily
	// For SUT FMU leafs, the dfp mapped to containing subsys is put here (but
	// not the subsys that contains the FMUBlock)
	// For Monitor leafs, the dfp mapped to containing subsys is put here (that
	// is the subsys that contains the FMUBlock/.mdl goes here). Note that the wrap .mdl subsystem is not put here - to keep dfp key unique.
	// For non-leaves, the DFP is also put here
	
	//The same dfp can correspond to several subsystems, prototypepath is used as key to identify the subsystems in this table
	static Map<String, SimulinkSystemBuilder> mapPathDFPSubsystemBuilder = new HashMap<String, SimulinkSystemBuilder>();

	
	//struct like class to hold info about an eventConnection
	class EventConnection{
	  public PortPrototype eventSource; //the prototype is in the pathDFP subsystem
	  public Inport eventTriggerIn;
//	  public DesignFunctionPrototype dfp; //dfp of the subsystem with the line
	  public String pathContainingDFP;  //the dfp that corresponds to the subsystem with the line
	  
  	  }
	  private static List<EventConnection> s_eventConnections =  new ArrayList<EventConnection>();

	  // Methods to build model

	public Block addInport(String portName) {

		if (system.isTopLevel()) {

			RepeatingSequenceInterpolated rsi = new RepeatingSequenceInterpolated();
			rsi.setName(portName);
			rsi.setContainerSubsystem(null);
			rsi.setContainerSystem(system);
			InportXMLData inportData = ConfigurationMgr.getInstance().findXMLPort(portName);

			if (inportData == null) {
				Utils.showErrorMessage("Failed to find input data for inport " + portName);
				return null;
			}
			
			rsi.setOutValues(inportData.getValueVector());
			rsi.setTimeValues(inportData.getTimeVector());
			rsi.setSampleTime(inportData.getSampleTime());
			system.getValueSources().add(rsi);

			return rsi;
		} else { // subsystem level
			Inport inport = new Inport();
			inport.setName(portName);
			inport.setPortNum(system.getValueSources().size() + 1); 
																	
			inport.setContainerSubsystem(system.getParentSubsystem());
			inport.setContainerSystem(system);
			system.getValueSources().add(inport);
			return inport;
		}

	}

	public SimulinkSystemBuilder addTopLevelSubsystem(String pathDFP, DesignFunctionPrototype dfp) {
		SimulinkSystemBuilder ssBuilder = new SimulinkSystemBuilder(false);

		Subsystem subsystem = new Subsystem();
		subsystem.setSystem(ssBuilder.getSimulinkSystem());

		system.getBlocks().add(subsystem);
		subsystem.setName(dfp.getShortName());
		subsystem.setPrototypePathDFP(pathDFP);

		// toplevel
		subsystem.setContainerSubsystem(null);
		subsystem.setContainerSystem(system);

		subsystem.setFullPath(system.getName() + "/" + subsystem.getName());
		subsystem.setDfp(dfp);

		mapPathDFPSubsystemBuilder.put(pathDFP, ssBuilder);
			
		return ssBuilder;
	}

	// returns a builder for the system of the subsystem - not on top level
	//pathDFP = the prototype path
	public SimulinkSystemBuilder addSubsystem(String pathDFP, DesignFunctionPrototype dfp, String sName, boolean isMonitor) {

		SimulinkSystemBuilder ssBuilder = new SimulinkSystemBuilder(false);

		Subsystem subsystem = new Subsystem();
		subsystem.setSystem(ssBuilder.getSimulinkSystem());

		if (isMonitor){
			system.getMonitors().add(subsystem);
			subsystem.setLayoutColumn(2);
			subsystem.setbMonitor(true);
		}
		else{
			system.getBlocks().add(subsystem);
		}

		subsystem.setPrototypePathDFP(pathDFP);
		subsystem.setName(sName);
		Subsystem parent = system.getParentSubsystem();
		subsystem.setContainerSubsystem(parent);
		subsystem.setContainerSystem(system);

		if (system.isTopLevel()) {
			subsystem.setFullPath(system.getName() + "/" + subsystem.getName());
		} else {
			subsystem.setFullPath(parent.getFullPath() + "/"
					+ subsystem.getName());
		}

		subsystem.setDfp(dfp);
		
		if (dfp != null)
		{
			//.mdl wrap subsystem are not stored in the map.
			mapPathDFPSubsystemBuilder.put(pathDFP, ssBuilder);
		}
		return ssBuilder;

	}

	
/***
 * Adds simulation pattern for a Leaf DFP, behaviour in .MDL or FMU
 * 
 * Inports
 * One PulseGenerator with T and WCET
 * One InputData subsystem
 * One FMUBlock / .mdl subsystem
 * One OutputData subsystem
 * Outports
 * 
 * @param dfp
 * @param executionInfo
 * @param inFlowPorts
 * @param outFlowPorts
 */
	  public void addTimeTriggedDFPLeafPattern(DesignFunctionPrototype dfp, boolean isMonitor,
				TimeTriggeredDFTExecutionInfo executionInfo, List<FunctionFlowPort> inFlowPorts,
				List<FunctionFlowPort> outFlowPorts) {
		
		  DesignFunctionType dft = dfp.getType();

			// Add inputdata subsystem 
			SimulinkSystemBuilder trInputSubB = addTriggeredSubsystem("InputData", false);
			TriggeredSubsystem trInputSub = (TriggeredSubsystem) trInputSubB.getParentSubsystem();
			SimulinkSystem trInputSS = trInputSub.getSystem();

			// Add inports to the inputdata subsystem
			for (FunctionFlowPort ffp : inFlowPorts) {
				Inport inport = (Inport)trInputSubB.addInport(ffp.getShortName() + "_");
			
				if (!isMonitor){
					//Set Latch output signal on input port <Lo>, to avoid simulation failure when trigged subsystem in a loop.
					inport.setLatchByDelayingOutsideSignal("On");
				}
			}

			// Add outports to the inputdata subsystem
			for (FunctionFlowPort ffp : inFlowPorts) {
				// adds outports as scope
				trInputSubB.addOutport(ffp.getShortName());
			}

			// Add lines straight through the inputdata subsystem for each
			// in-out port set.
			for (int p = 0; p < trInputSS.getValueSources().size(); p++) {
				Inport inport = (Inport) trInputSS.getValueSources().get(p);
				Outport outport = (Outport) trInputSS.getValueSinks().get(p);

				addLine(inport.getName() + "_shortcircuit", trInputSS, inport,
						null, outport, null);
			}

			
			// Add PulseGenerator
			TimeTriggeredDFTExecutionInfo ttExeInfo = (TimeTriggeredDFTExecutionInfo) executionInfo;
			float period = ttExeInfo.getExecutionPeriod();
			
			float pulsewidth;
			float exeTime = ttExeInfo.getExecutionTime();
			if (exeTime == 0)
			{
				//for monitors etc exetime is not always in the model, use 50 as default
				pulsewidth = 50;
			}
			else 
			{
				pulsewidth = ttExeInfo.getExecutionTime() / period * 100;
			}
			PulseGenerator pg = addPulseGenerator("T_WCET", period, pulsewidth);

			float delayTime = ttExeInfo.getExecutionTime();
			getParentSubsystem().setOutDataEventSourceBlock(pg);	

				
		/*	//Add FMU block
			FMU_ME fmu = addFMU(dfp.getName()+"_FMU", ttExeInfo.getFilenameBehaviour(), this);
	*/
			//Add OutputData triggered subsystem
			SimulinkSystemBuilder trOutputSubB = addTriggeredSubsystem("OutputData", false);
			TriggeredSubsystem trOutputSub = (TriggeredSubsystem) trOutputSubB.getParentSubsystem();
			SimulinkSystem trOutputSS = trOutputSub.getSystem();
			trOutputSub.getTrigger().setTriggerType("falling");

			
			// Add inports to the outputdata subsystem
			for (FunctionFlowPort ffp : outFlowPorts) {
				trOutputSubB.addInport(ffp.getShortName());
			}

			// Add outports to the ouputdata subsystem
			for (FunctionFlowPort ffp : outFlowPorts) {
				// adds outports as scope
				trOutputSubB.addOutport(ffp.getShortName() + "_");
			}

			
			if (executionInfo.isFMUBehaviour()){
				addFMUBlockandConnectors(dfp, ttExeInfo.getFilenameBehaviour(), trInputSub, trOutputSub,  new Point(395, 312), new Point(540,368));
				if (isMonitor){
					setInitialOutputTRUE(trOutputSubB);
				}
			}
			else 	
			{
				//.mdl behaviour
				addSimulinkMdlandConnectors(dfp, isMonitor, ttExeInfo.getFilenameBehaviour(), trInputSub, trOutputSub);
				if (isMonitor){
					setInitialOutputTRUE(trOutputSubB);
				}

			}
	
			
			
			// Add lines straight through the ouptputdata subsystem for each
			// in-out port set.
			for (int p = 0; p < trOutputSS.getValueSources().size(); p++) {
				Inport inport = (Inport) trOutputSS.getValueSources().get(p);
				Outport outport = (Outport) trOutputSS.getValueSinks().get(p);

				addLine(inport.getName() + "_shortcircuit", trOutputSS, inport,
						null, outport, null);
			}

				
			
			// add line pulsegenerator - inputdata
			addLine(trInputSubB, trInputSub, pg);
			
					
			// add line pulsegenerator - trigger of ouput dataa
			addLine("Pulsegen_OutputDataTrigger", this.system, pg, null, trOutputSub.getTrigger(), trOutputSub);
				
			
			//add lines from input ports to InputData
			for (FunctionFlowPort ffp : inFlowPorts) {
				Inport inport = (Inport) FindInPort(ffp.getShortName());
				Inport inportInput = (Inport) trInputSubB.FindInPort(ffp.getShortName() + "_");

				addLine(inport.getName() + "delegate", system, inport, null,
						inportInput, trInputSub);
			}
			
			
			//add lines from output ports to OutputData
			for (FunctionFlowPort ffp : outFlowPorts) {
				Outport outportExeSub = (Outport) trOutputSubB.FindOutPort(ffp
						.getShortName() + "_");
				Outport outport = (Outport) FindOutPort(ffp.getShortName());

				addLine(outportExeSub.getName() + "delegate", system,
						outportExeSub, trOutputSub, outport, null);
			}

					  
		  return;
	  
	  
	  }
		  
	//set initial output = 1 for all outports
    //This is needed for time/event trigged monitors, to make sure the first samples until the monitor has calculated something useful is 1
	public void setInitialOutputTRUE(SimulinkSystemBuilder trOutputSubB){
		for(Block block: trOutputSubB.getSimulinkSystem().getValueSinks())
		{
			Outport out = (Outport)block;
			out.setInitialOutput("1");
		}
	}

	  
	  //triggerPT = Prototype of port that sends trigger event
	  //triggerFFP = Port of trigger event
	  public void addEventTriggedDFPLeafPattern(DesignFunctionPrototype dfp, boolean isMonitor, 
				EventTriggeredDFTExecutionInfo executionInfo, List<FunctionFlowPort> inFlowPorts,
				List<FunctionFlowPort> outFlowPorts, PortPrototype eventSource) {

		  
		  //We'll include coordinates when the blocks are put into this subsystem, so skip automatic layout handling
		  system.setAutomaticLayout(false);
		  
		  //Add EventTriggerIn Inport - not in .eaxml
		  Inport eventTriggerIn = (Inport)addInport("EventTriggerIn");
		  eventTriggerIn.setPosition(5,   163,    35,   177);
	  
	  
		  DesignFunctionType dft = dfp.getType();

			// Add inputdata subsystem 
			SimulinkSystemBuilder trInputSubB = addTriggeredSubsystem("InputData", false);
			TriggeredSubsystem trInputSub = (TriggeredSubsystem) trInputSubB.getParentSubsystem();
			trInputSub.setPosition(85, 306, 215, 374);
			SimulinkSystem trInputSS = trInputSub.getSystem();

			// Add inports to the inputdata subsystem
			for (FunctionFlowPort ffp : inFlowPorts) {
				trInputSubB.addInport(ffp.getShortName() + "_");
			}

			// Add outports to the inputdata subsystem
			for (FunctionFlowPort ffp : inFlowPorts) {
				// adds outports as scope
				trInputSubB.addOutport(ffp.getShortName());
			}

			// Add lines straight through the inputdata subsystem for each
			// in-out port set.
			for (int p = 0; p < trInputSS.getValueSources().size(); p++) {
				Inport inport = (Inport) trInputSS.getValueSources().get(p);
				Outport outport = (Outport) trInputSS.getValueSinks().get(p);

				addLine(inport.getName() + "_shortcircuit", trInputSS, inport,
						null, outport, null);
			}
			
	/*		//Add FMU block
			FMU_ME fmu = addFMU(dfp.getName()+"_FMU", executionInfo.getFilenameBehaviour(), this);
			fmu.setPosition(395, 312, 540, 368);
	*/	
			//Add OutputData triggered subsystem
			SimulinkSystemBuilder trOutputSubB = addTriggeredSubsystem("OutputData", false);
			TriggeredSubsystem trOutputSub = (TriggeredSubsystem) trOutputSubB.getParentSubsystem();
			trOutputSub.setPosition(665, 307, 795,  373);
			SimulinkSystem trOutputSS = trOutputSub.getSystem();
			trOutputSub.getTrigger().setTriggerType("falling");

			
			// Add inports to the outputdata subsystem
			for (FunctionFlowPort ffp : outFlowPorts) {
				trOutputSubB.addInport(ffp.getShortName());
			}

			// Add outports to the outputdata subsystem
			for (FunctionFlowPort ffp : outFlowPorts) {
				// adds outports as scope
				trOutputSubB.addOutport(ffp.getShortName() + "_");
			}

			if (executionInfo.isFMUBehaviour()){
				addFMUBlockandConnectors(dfp, executionInfo.getFilenameBehaviour(), trInputSub, trOutputSub,  new Point(395, 312), new Point(540,368));
				if (isMonitor){
					setInitialOutputTRUE(trOutputSubB);
				}
			}
			else 	
			{
				//.mdl behaviour
				addSimulinkMdlandConnectors(dfp, isMonitor, executionInfo.getFilenameBehaviour(), trInputSub, trOutputSub);
				if (isMonitor){
					setInitialOutputTRUE(trOutputSubB);
				}
			}
			
			// Add lines straight through the ouptputdata subsystem for each
			// in-out port set.
			for (int p = 0; p < trOutputSS.getValueSources().size(); p++) {
				Inport inport = (Inport) trOutputSS.getValueSources().get(p);
				Outport outport = (Outport) trOutputSS.getValueSinks().get(p);

				addLine(inport.getName() + "_shortcircuit", trOutputSS, inport,
						null, outport, null);
			}

				
			
			//add lines from input ports to InputData
			for (FunctionFlowPort ffp : inFlowPorts) {
				Inport inport = (Inport) FindInPort(ffp.getShortName());
				Inport inportInput = (Inport) trInputSubB.FindInPort(ffp.getShortName() + "_");

				addLine(inport.getName() + "delegate", system, inport, null,
						inportInput, trInputSub);
			}
			
			
			//add lines from output ports to OutputData
			for (FunctionFlowPort ffp : outFlowPorts) {
				Outport outportExeSub = (Outport) trOutputSubB.FindOutPort(ffp
						.getShortName() + "_");
				Outport outport = (Outport) FindOutPort(ffp.getShortName());

				addLine(outportExeSub.getName() + "delegate", system,
						outportExeSub, trOutputSub, outport, null);
			}

			//add other blocks
			Memory decMemory = addMemory("last");
			decMemory.setPosition(230, 170, 260, 200);
			RelationalOperator roIsFalling = addRelationalOperator("is falling", "<");
			roIsFalling.setPosition(295,147,330,198);
			Memory memory = addMemory("Memory");
			memory.setPosition( 230, 210, 260, 240);
			LogicalOperator orLogic = addLogic("started", "or", 2);
			orLogic.setPosition(295, 143, 320, 252);
			Clock Clock1 = addClock("Clock 1");
			Clock1.setPosition(380,25,400,45);
			Clock Clock2 = addClock("Clock 2");
			Clock2.setPosition( 175, 60, 195, 80);
			Switch stepDown = addSwitch("StepDown if Trigg Time and started", 1.0f);
			stepDown.setPosition(595,87,670,203);
			getParentSubsystem().setOutDataEventSourceBlock(stepDown);	
			
			Constant zeroConstant = addConstant("zero", 0.0f);
			zeroConstant.setPosition(510,90,540,120);
			RelationalOperator triggTime = addRelationalOperator("Next_trigg_time", ">=");
			triggTime.setPosition(430, 19, 460,86);
			
			//subsystem Next Trigg Time
			SimulinkSystemBuilder trNextTriggTimeSubB = addTriggeredSubsystem("clock_plus_WCET", false);
			TriggeredSubsystem trNextTriggTimeSub = (TriggeredSubsystem) trNextTriggTimeSubB.getParentSubsystem();
			trNextTriggTimeSub.setPosition( 220,    45,   350,    95);
			SimulinkSystem trNextTriggTimeSS = trNextTriggTimeSub.getSystem();
			trNextTriggTimeSub.getTrigger().setTriggerType("falling");
			Inport inportClock =(Inport)trNextTriggTimeSubB.addInport("clock");
			inportClock.setPosition(45, 118, 75, 132);
			Outport outportNextTriggTime = (Outport)trNextTriggTimeSubB.addOutport("Next Trigg time");
			outportNextTriggTime.setPosition(270, 133, 300, 147);
			
			//content blocks of next trigg time subsystem
			Sum nextTriggTimeSum = trNextTriggTimeSubB.addSum("calc_next", 2, "++");
			nextTriggTimeSum.setPosition(155, 109, 190, 171);
			Constant wcetConstant = trNextTriggTimeSubB.addConstant("WCET", executionInfo.getExecutionTime());
			wcetConstant.setPosition(90, 140, 120, 170);		
			 
			//lines in the pattern

			addLine("eventinport_decMemory", system, eventTriggerIn, null, decMemory, null);
			addLine("eventinport_isfalling", system, eventTriggerIn, null, roIsFalling, 1);
			addLine("decMemory_isfalling", system, decMemory, null, roIsFalling, 2);
			addLine("Clock2_NextTriggTime", system, Clock2, null, inportClock, trNextTriggTimeSub);
			addLine("Clock1_TriggTime", system, Clock1, null, triggTime, 1);
			addLine("NexttriggTime_TriggTime", system, outportNextTriggTime, trNextTriggTimeSub, triggTime,2);
			addLine("zero_stepdownswitch", system, zeroConstant, null, stepDown,1);
			addLine("triggtime_stepdownswitch", system, triggTime, null, stepDown,2);
			addLine("is falling-or", system, roIsFalling, null, orLogic, 1);
			addLine("memory-or", system, memory, null, orLogic, 2);
			addLine("or-stepdown", system, orLogic, null, stepDown, 3);
			addLine("EventTriggerIn_triggerNextTriggtime", system, eventTriggerIn,null, trNextTriggTimeSub.getTrigger(), trNextTriggTimeSub);
			addLine("EventTriggerIn-triggerInputData", system, eventTriggerIn, null, trInputSub.getTrigger(), trInputSub);
			addLine("stepdownswitch -> trigger OutputData", system, stepDown, null, trOutputSub.getTrigger(), trOutputSub);
			addLine("orLogic out -> memory in", system, orLogic, null, memory, null);
			
			
			//lines in next trigg time subsystem
			addLine("inport_sum", trNextTriggTimeSS, inportClock, null, nextTriggTimeSum, 1);
			addLine("WCET_SUM", trNextTriggTimeSS, wcetConstant, null, nextTriggTimeSum, 2);
			addLine("SUM_outport", trNextTriggTimeSS, nextTriggTimeSum, null, outportNextTriggTime,null);
			

			//Save info about event connection - to be created later when all subsystems corresponding to DFP leaves have been created
			EventConnection ec = new EventConnection();
			ec.eventSource = eventSource;
			ec.eventTriggerIn = eventTriggerIn;
			ec.pathContainingDFP = getParentSubsystem().getContainerSubsystem().getPrototypePathDFP();
			s_eventConnections.add(ec);
			
		  return;
	  }

  
	  //Inserts an FMU block between triggered subsystems trInputSub and trOutputSub
	  private void addFMUBlockandConnectors(DesignFunctionPrototype dfp, String sFMUFilename, TriggeredSubsystem trInputSub, TriggeredSubsystem trOutputSub,
			  Point topLeft, Point lowerRight)
	  {
			//Add FMU block
			FMU_ME fmu = addFMU(dfp.getShortName()+"_FMU", sFMUFilename, this);
			fmu.setPosition(topLeft.x, topLeft.y, lowerRight.x, lowerRight.y);
		
		  
			//add lines from inputdata subsystem outports to FMU inports
			//we assume that the port order is the same in EATOP-model & FMU block
			for (Block out : trInputSub.getSystem().getValueSinks()) {
				Outport outport = (Outport) out;

				addLine(outport.getName() + "delegate", system, outport, trInputSub, fmu, null);
				
			}
			
			//add lines from FMU outports to input of outputdata subsystem
			//we assume that the port order is the same in EATOP-model & FMU block
			for (Block in : trOutputSub.getSystem().getValueSources()) {
				Inport inport = (Inport) in;

				addLine(inport.getName() + "delegate", system,  fmu, null, inport, trOutputSub); 
			}
	  }
	  
	  //Wraps the user .mdl in a subsystem, that is inserted between InputData & OutputData
	  private void addSimulinkMdlandConnectors(DesignFunctionPrototype dfp, Boolean isMonitor, String sMDLFilename, TriggeredSubsystem trInputSub, TriggeredSubsystem trOutputSub)
			 
	  {
			//Add encapsulating subsystem. Use dfp=null since we don't want to overwrite the dfp in the map on level up.
		  String name = dfp.getShortName() + "_wrap";
		  SimulinkSystemBuilder encapsulatingSubB = addSubsystem("Phony_Path_" + name, null, name, false); 
			Subsystem encapsulatingSub = encapsulatingSubB.getParentSubsystem();
	//		encapsulatingSub.setPosition(topLeft.x, topLeft.y, lowerRight.x, lowerRight.y);
		
			
			//add inports to subsystem
			for (Block out : trInputSub.getSystem().getValueSinks()) {
				Inport inport = (Inport)encapsulatingSubB.addInport(out.getName());
				inport.setSuppressWrite(true);
			}

			// Add outports to the subsystem
			for (Block out : trOutputSub.getSystem().getValueSources()) {
				// adds outports as scope
				Outport outport = (Outport)encapsulatingSubB.addOutport(out.getName());
				outport.setSuppressWrite(true);
			}

		  
			//add lines from inputdata subsystem outports to Encapsulating subsystem inports
			//we assume that the port order is the same in EATOP-model & simulink .mdl file
			for (Block out : trInputSub.getSystem().getValueSinks()) {
				Outport outport = (Outport) out;
				Inport inport = (Inport)encapsulatingSubB.FindInPort(out.getName());
				addLine(outport.getName() + "delegate", system, outport, trInputSub, inport, encapsulatingSub);
				
			}
			
			//add lines from Encapsulating subsystem outports to input of outputdata subsystem
			//we assume that the port order is the same in EATOP-model & FMU block
			for (Block in : trOutputSub.getSystem().getValueSources()) {
				Inport inport = (Inport) in;
				Outport outport = (Outport)encapsulatingSubB.FindOutPort(in.getName());
				addLine(inport.getName() + "delegate", system,  outport, encapsulatingSub, inport, trOutputSub); 
			}
			
			//Make sure we copy sMDLFilename to model
			encapsulatingSub.setMdlBehaviourFilename(sMDLFilename);
	  }
	  
	  
	  
	  public static void addEventConnections(){

		  for (EventConnection ec : s_eventConnections){
			  //Connect Event signal 

			  SimulinkSystemBuilder subContainingB = mapPathDFPSubsystemBuilder.get(ec.pathContainingDFP);
			
			  //this is the dfp in the type
			  DesignFunctionPrototype dfpEventSource = ec.eventSource.getFunctionPrototype();
			  Subsystem subSysEventSource = subContainingB.findSubsystem(dfpEventSource);
			  
			  //this is the prototype path down in the model
			  String pathEventSourceDFP = subSysEventSource.getPrototypePathDFP();
			  
			  SimulinkSystemBuilder eventSourceSysBuilder = mapPathDFPSubsystemBuilder.get(pathEventSourceDFP);
			 Outport outputEventPort = eventSourceSysBuilder.GetOutputEventPort();
				//Subsystem dfpLeafSub = eventSourceSysBuilder.getParentSubsystem();

				//Line between subsystems
				subContainingB.addLine("outputEventPort-eventTriggerIn", subContainingB.system, outputEventPort, subSysEventSource, ec.eventTriggerIn, ec.eventTriggerIn.getContainerSubsystem());
		  }
		  
		  s_eventConnections.clear();
		  
	  }
	  
	   
	  
	  
	private Outport GetOutputEventPort(){
		
		Subsystem dfpLeafSub = system.getParentSubsystem();
		Outport outputEventPort = dfpLeafSub.getOutputEventPort(); 
				
		if (outputEventPort == null)
		{
			//create port
			outputEventPort = (Outport)addOutport("EventTriggerOut");
			dfpLeafSub.setOutputEventPort(outputEventPort);
			
			if (dfpLeafSub.getOutDataEventSourceBlock() == null)
			{
				Utils.showErrorMessage("The subsystem " + dfpLeafSub.getName() + " has no OutDataEventSourceBlock set. Source block cannot be .mdl");
			}
			
		
			//connect event source block (Pulsegen for timetrigged, StepdownSwitch for eventtrigged) to OutDataEventPort
			addLine("EventSourceBlock-OutputEventPort", system,	dfpLeafSub.getOutDataEventSourceBlock(), null, outputEventPort, null);
		}
		
		return outputEventPort;
	}
	  
  

	//Just put the fmu block or user .mdl in the subsystem, i.e. add no triggering etc
	//- used for continous functions
	//- used for user specified functions (that could be triggered in any way defined by the user)
	
	public void addRawUserModelPattern(DesignFunctionPrototype dfp, DFTExecutionInfo ei)   
	{
		if (ei.isSimulinkBehaviour())
		{
			//mdl behaviour
			addRawMDLUserModel(dfp, ei.getFilenameBehaviour());
		}
		else{
			//FMU behaviour
			addRawFMUUserModel(dfp, ei.getFilenameBehaviour());
		}
	}
	

	//adds an FMU block in a subsystem and connects the ports to inports and outports of the subsystem
	protected void addRawFMUUserModel(DesignFunctionPrototype dfp, String fmuFileName)
	{
		//Add FMU block
		FMU_ME fmu = addFMU(dfp.getShortName()+"_FMU", fmuFileName, this);
		//fmu.setPosition(topLeft.x, topLeft.y, lowerRight.x, lowerRight.y);

		
		//add lines from inports to FMU block
		//we assume that the port order is the same in EATOP-model & simulink .mdl file
		for (Block in : system.getValueSources()) {
			Inport inport = (Inport) in;
			addLine(inport.getName() + "delegate", system, inport, null, fmu, null);
			
		}
		
		//add lines from FMU block to outports
		//we assume that the port order is the same in EATOP-model & FMU block
		for (Block out : system.getValueSinks()) {
			Outport outport = (Outport) out;
			addLine(outport.getName() + "delegate", system, fmu, null, outport, null);
		}
	
	} 	

	protected void addRawMDLUserModel(DesignFunctionPrototype dfp, String mdlFilename)
	{
		//Suppress writing of inports to .m file since copycontents function fixes the ports
		for (Block in : system.getValueSources()) {
			Inport inport = (Inport) in;
			inport.setSuppressWrite(true);
		}

		//Suppress writing of inports to .m file since copycontents function fixes the ports
		for (Block out : system.getValueSinks()) {
			Outport outport = (Outport) out;
			outport.setSuppressWrite(true);
		}

		//Make sure we copy sMDLFilename to model
		getParentSubsystem().setMdlBehaviourFilename(mdlFilename);
	} 	

  
	
	
	private Line addLine(String name, SimulinkSystem ContainerSystem,
			Block sourcePort, Block sourceSubsystem, Block destPort,
			Block destSubsystem) {
		Line line = new Line();

		line.setName(name);
		line.setContainerSystem(ContainerSystem);
		line.setParentSubsystem(ContainerSystem.getParentSubsystem());

		line.setSourcePortBlock(sourcePort);
		line.setSourceSubsystemBlock(sourceSubsystem);
		line.setDestinationPortBlock(destPort);
		line.setDestinationSubsystemBlock(destSubsystem);
		ContainerSystem.getLines().add(line);

		return line;
	}

	//Lines to a MultipleInputBlock, like "Sum"
	private Line addLine(String name, SimulinkSystem ContainerSystem,
			Block sourcePort, Block sourceSubsystem, MultipleInputsBlock destBlock,
			int destInput) {
		Line line = new Line();

		line.setName(name);
		line.setContainerSystem(ContainerSystem);
		line.setParentSubsystem(ContainerSystem.getParentSubsystem());

		line.setSourcePortBlock(sourcePort);
		line.setSourceSubsystemBlock(sourceSubsystem);
		line.setDestinationPortBlock(destBlock);
		line.setDestinationSubsystemBlock(null);
		line.setDestInput(destInput);
		ContainerSystem.getLines().add(line);

		return line;
	}

	
	/***
	 * 
	 * Add Line to this system between PulseGenerator and Trigged  Subsystem
	 * 
	 * @param trFMUsubB
	 * @param trFMUsub
	 * @param pg
	 * @return
	 */
	private Line addLine(SimulinkSystemBuilder trsubB,
			TriggeredSubsystem trsub, PulseGenerator pg) {
		Line pgToMonLine = new Line();
		pgToMonLine.setName(pg.getName() + "_" + trsubB.getName());
		pgToMonLine.setContainerSystem(system);
		pgToMonLine.setParentSubsystem(system.getParentSubsystem());

		pgToMonLine.setSourcePortBlock(pg);
		pgToMonLine.setSourceSubsystemBlock(null);
		pgToMonLine.setDestinationPortBlock(trsub.getTrigger());
		pgToMonLine.setDestinationSubsystemBlock(trsub);

		this.getSimulinkSystem().getLines().add(pgToMonLine);
		return pgToMonLine;
	}

	// returns builder of the system that is contained in the triggered
	// subsystem
	// not for the top-level
	public SimulinkSystemBuilder addTriggeredSubsystem(String name,
			boolean isMonitor) {

		SimulinkSystemBuilder ssBuilder = new SimulinkSystemBuilder(false);

		TriggeredSubsystem ts = new TriggeredSubsystem();
		ts.setSystem(ssBuilder.getSimulinkSystem());

		ts.setName(name);
		ts.setContainerSystem(system);
		Subsystem parent = system.getParentSubsystem();
		ts.setContainerSubsystem(parent);

		Trigger t = addTrigger(ts);
		ts.setTrigger(t);

		
		if (system.isTopLevel()) {
			ts.setFullPath(system.getName() + "/" + ts.getName());
		} else {
			ts.setFullPath(parent.getFullPath() + "/" + ts.getName());
		}

		if (isMonitor) {
			this.system.getMonitors().add(ts);
		} else {
			system.getBlocks().add(ts);
		}

		return ssBuilder;

	}

	private Trigger addTrigger(Subsystem ts) {
		Trigger trigger = new Trigger();
		ts.getSystem().getBlocks().add(trigger);
		trigger.setContainerSystem(ts.getSystem());
		trigger.setContainerSubsystem(ts);
		trigger.setName(ts.getName() + "_trigger");
		return trigger;
	}

	public PulseGenerator addPulseGenerator(String sName, float period, float pulseWidth) {
		PulseGenerator pg = new PulseGenerator();
		pg.setContainerSubsystem(system.getParentSubsystem());
		pg.setContainerSystem(system);
		pg.setName(sName);
		pg.setPeriod(period);
		pg.setPulseWidth(pulseWidth);
		system.getBlocks().add(pg);
		return pg;
	}
	
	public LogicalOperator addLogic(String sName, String operator, int inputs) {
		LogicalOperator l = new LogicalOperator(inputs);
		l.setContainerSubsystem(system.getParentSubsystem());
		l.setContainerSystem(system);
		l.setName(sName);
	    l.setOperator(operator);
		system.getBlocks().add(l);
		return l;
	}

	public RelationalOperator addRelationalOperator(String sName, String operator) {
		RelationalOperator r = new RelationalOperator();
		r.setContainerSubsystem(system.getParentSubsystem());
		r.setContainerSystem(system);
		r.setName(sName);
	    r.setOperator(operator);
		system.getBlocks().add(r);
		return r;
	}

	
	public Switch addSwitch(String sName, float threshold ) {
		Switch s = new Switch();
		s.setContainerSubsystem(system.getParentSubsystem());
		s.setContainerSystem(system);
		s.setName(sName);
	    s.setThreshold(threshold);
		system.getBlocks().add(s);
		return s;
	}

	public Constant addConstant(String sName, float c) {
		Constant s = new Constant();
		s.setContainerSubsystem(system.getParentSubsystem());
		s.setContainerSystem(system);
		s.setName(sName);
	    s.setValue(c);
		system.getBlocks().add(s);
		return s;
	}

	public Sum addSum(String sName, int inputs, String sOperators) {
		Sum s = new Sum(inputs);
		s.setContainerSubsystem(system.getParentSubsystem());
		s.setContainerSystem(system);
		s.setName(sName);
		s.setsInputs(sOperators);
		system.getBlocks().add(s);
		return s;
	}

	
	public DetectDecrease addDetectDecrease(String sName) {
		DetectDecrease dd = new DetectDecrease();
		dd.setContainerSubsystem(system.getParentSubsystem());
		dd.setContainerSystem(system);
		dd.setName(sName);
		system.getBlocks().add(dd);
		return dd;
	}

	public Clock addClock(String sName) {
		Clock clock = new Clock();
		clock.setContainerSubsystem(system.getParentSubsystem());
		clock.setContainerSystem(system);
		clock.setName(sName);
		system.getBlocks().add(clock);
		return clock;
	}

	public Memory addMemory(String sName) {
		Memory m = new Memory();
		m.setContainerSubsystem(system.getParentSubsystem());
		m.setContainerSystem(system);
		m.setName(sName);
		system.getBlocks().add(m);
		return m;
	}

	public Goto addGoto(String sName, String sGotoTag) {
		Goto g = new Goto();
		g.setContainerSubsystem(system.getParentSubsystem());
		g.setContainerSystem(system);
		g.setName(sName);
		g.setGotoTag(sGotoTag);
		system.getBlocks().add(g);
		return g;
	}

	public From addFrom(String sName, String sGotoTag) {
		From f = new From();
		f.setContainerSubsystem(system.getParentSubsystem());
		f.setContainerSystem(system);
		f.setName(sName);
		f.setGotoTag(sGotoTag);
		system.getBlocks().add(f);
		return f;
	}

	
	public FMU_ME addFMU(String name, String filename, SimulinkSystemBuilder container) {
		FMU_ME fmu = new FMU_ME();
		fmu.setName(name); // overridden by load
		fmu.setFilename(filename);
		fmu.setContainerSubsystem(container.getParentSubsystem());
		fmu.setContainerSystem(container.getSimulinkSystem());
		fmu.readPortsfromXML();

		container.system.getBlocks().add(fmu);
		
		return fmu;
	}

	public Block addOutport(String name) {
		if (system.isTopLevel()) {

			//Monitor outport = scope
			Scope scope = new Scope();
			scope.setName(name);
			system.getValueSinks().add(scope);
			scope.setContainerSubsystem(null);
			scope.setContainerSystem(system);
			
			//add a From block for outports on top-level and connect it
			From f = addFrom(name + "_from", name);
			addLine(name + "_from_to_ + " + name, system, f, null, scope, null);
		//	Point base = scope.getTopLeftPoint();
		//	f.setTopLeftPoint(new Point(base.x-60, base.y + 5));
			f.setFromDestination(scope);
			return scope;

		} else { // subsystem level
			Outport outport = new Outport();
			outport.setName(name);
			outport.setPortNum(system.getValueSinks().size() + 1); // Integer.toString(system.getValueSinks().size()
																	// + 1));
			outport.setContainerSubsystem(system.getParentSubsystem());
			outport.setContainerSystem(system);
			system.getValueSinks().add(outport);

			Subsystem s = outport.getContainerSubsystem();
			if (s.getbMonitor()){
				//add a goto block for this monitor outport in the father system
				SimulinkSystem sFatherSys = s.getContainerSystem();
				SimulinkSystemBuilder sFatherBuilder = mapPathDFPSubsystemBuilder.get(sFatherSys.getParentSubsystem().getPrototypePathDFP());
				
				Goto g = sFatherBuilder.addGoto(name + "_goto", name);
				addLine(name + "_goto_to_ + " + name, sFatherSys, outport, s, g, null);
			//	Point base = outport.getTopLeftPoint();
			//	g.setTopLeftPoint(new Point(base.x+60, base.y + 5));
				g.setGotoSource(s); //attach to the subsystem of the monitor
				outport.setHasGoto(true);
			}
			
			return outport;
		}
	}

	/***
	 * Assumes subsystem has been created & all inports 
	 */
	public void addTopLevelDelegateConnectorLines(SimulinkSystemBuilder SUTBuilder) {
/*		if (!(system.isTopLevel() && (system.getBlocks().size() == 1) && (system
				.getBlocks().get(0) instanceof Subsystem))) {
			Utils.showErrorMessage("Top-level system should contain a single subsystem");
			return;
		}
*/
//		Subsystem sub = (Subsystem) system.getBlocks().get(0);
		// inports
		Subsystem subSUT = SUTBuilder.getParentSubsystem();
		for (Block source : system.getValueSources()) {
			Line line = new Line();
			system.getLines().add(line);
			line.setName("Delegate_" + source.getName() + "_" + subSUT.getName());
			line.setParentSubsystem(null);
			line.setContainerSystem(system);

			line.setSourcePortBlock(source);
			line.setSourceSubsystemBlock(null);

			Block subsysPort = SUTBuilder.FindInPort(source.getName());
			line.setDestinationPortBlock(subsysPort);
			line.setDestinationSubsystemBlock(subSUT);
		}
/*
		// outports
		for (Block sink : system.getValueSinks()) {
			Line line = new Line();
			system.getLines().add(line);
			line.setName("Delegate_" + sink.getName() + "_" + sub.getName());
			line.setParentSubsystem(null);
			line.setContainerSystem(system);
			
			Block subsysPort = subBuilder.FindOutPort(sink.getName());
			line.setSourcePortBlock(subsysPort);
			line.setSourceSubsystemBlock(sub);

			line.setDestinationPortBlock(sink);
			line.setDestinationSubsystemBlock(null);
		}
*/
	}

	// Adds the lines for which there is a function connector in the ecore model
	// (i.e. not other lines generated by translation patterns (for example
	// pulsegen - trigged subvsystem block))
	// Delegation connector: Inport - blockport (IN-IN), blockport - Outport (OUT-OUT), Inport - OutPort (IN - OUT)
	// Assembly connector: blockport - blockport (IN - OUT)
	public Line addLine(FunctionConnector fc) {
		Line line = new Line();
		system.getLines().add(line);
		line.setName(fc.getShortName()); // Note: Name not used in .mdl. Use
											// here for id only. Name = "" for
											// some FC in ecore model, so use
											// short name instead.

		line.setParentSubsystem(system.getParentSubsystem());
		line.setContainerSystem(system);

		EList<FunctionConnector_port> portList = fc.getPort();

		FunctionConnector_port fcp0 = portList.get(0);
		FunctionPrototype fp0 = fcp0.getFunctionPrototype();

		FunctionConnector_port fcp1 = portList.get(1);
		FunctionPrototype fp1 = fcp1.getFunctionPrototype();
		
		
		boolean reverse0 = false;
		boolean reverse1 = false;

		if ((fp0 == null) && (fp1 == null)) {
			// Delegate right through, reverse 0 and 1
			reverse0 = true;
			reverse1 = true;
		} else if ((fp0 != null) && (fp1 != null)) {
			// assembly connector, directions ok
		} else if ((fp0 != null) && (fp1 == null)) {
			// delegate connector, 1 shall reverse direction
			reverse1 = true;

		} else // ((fp0 == null) && (fp1 != null)){
		{
			// delegate connector, 0 shall reverse direction
			reverse0 = true;
		}

		boolean bSourceFound = false;
		boolean bDestinationFound = false;

		for (int p = 0; p < 2; p++) {
			FunctionConnector_port fcp = portList.get(p);
			FunctionPrototype fPrototype = fcp.getFunctionPrototype();
			FunctionPort fPort = fcp.getFunctionPort();

			// We know there is a a port element, instance of FunctionFlowPort

			FunctionFlowPort ffPort = (FunctionFlowPort) fPort;

			EADirectionKind dir = ffPort.getDirection();
			if (p == 0) {
				if (reverse0)
					dir = reverseDirection(dir);
			} else // p==1
			{
				if (reverse1)
					dir = reverseDirection(dir);
			}

			boolean bSource = dir == EADirectionKind.OUT;
			bSourceFound = bSourceFound || (dir == EADirectionKind.OUT);
			bDestinationFound = bDestinationFound
					|| (dir == EADirectionKind.IN);

			if (fPrototype == null) {
				// this port is a inport or outport to this system

				Block port = FindPort(fPort.getShortName());

				if (bSource) {
					// source
					line.setSourcePortBlock(port);
					line.setSourceSubsystemBlock(null);

				} else {
					// dest
					line.setDestinationPortBlock(port);
					line.setDestinationSubsystemBlock(null);
				}

			} else {
				// this port is an inport or outport to the subsystem described
				// by fcpProt

				DesignFunctionPrototype dfp = (DesignFunctionPrototype)fPrototype;
				Subsystem sub = findSubsystem(dfp);
				
				if (sub == null) {
					Utils.showErrorMessage("Failed to find subsystem for Function Prototype "
							+ fPrototype.getShortName());
					return null;
				}

				// look for the port in the subsystem
				SimulinkSystemBuilder subBuilder = mapPathDFPSubsystemBuilder.get(sub.getPrototypePathDFP());
				Block port = subBuilder.FindPort(fPort.getShortName());

				if (bSource) {
					// source
					line.setSourceSubsystemBlock(sub);
					line.setSourcePortBlock(port);
				} else {
					// dest
					line.setDestinationSubsystemBlock(sub);
					line.setDestinationPortBlock(port);
				}
			}
		}// for

		if (!(bSourceFound && bDestinationFound)) {
			Utils.showErrorMessage("Direction error for FunctionConnector "
					+ fc.getShortName());
			return null;
		}

		return line;

	}
	
	public Subsystem findSubsystem(DesignFunctionPrototype dfp){
		for (Block block : system.getBlocks()){
			if (block instanceof Subsystem)			{
				Subsystem candidate = (Subsystem)block;
				if (dfp == candidate.getDfp()){
					return candidate;
				}
			}
		}
		return null;
	}

	// Adds a From Goto connection for a clamp connector in the ecore model
//	public void addGotoFromConnection(ClampConnector cc) {
	public void addGotoFromConnection(String name, String dfpPathSource, FunctionFlowPort ffpSource, String dfpPathDest, FunctionFlowPort ffpDest) {

		//Source
		SimulinkSystemBuilder sourceSubsystemBuilder = mapPathDFPSubsystemBuilder.get(dfpPathSource);
		if (sourceSubsystemBuilder == null) {
			Utils.showErrorMessage("Failed to find simulink block for Function Prototype "
					+ dfpPathSource + " for clampconnector " + name);
			return;
		}
		Block sourcePortBlock = sourceSubsystemBuilder.FindPort(ffpSource.getShortName());

		//Dest
		SimulinkSystemBuilder destSubsystemBuilder = mapPathDFPSubsystemBuilder.get(dfpPathDest);
		if (destSubsystemBuilder == null) {
			Utils.showErrorMessage("Failed to find simulink block for Function Prototype "
					+ dfpPathDest + " for clampconnector " + name);
			return;
		}
		Block destPortBlock = destSubsystemBuilder.FindPort(ffpDest.getShortName());

		//skip direction checks for now - assume first is source and second dest

		
		//Add Source block - Goto
//		String tag = sourcePortBlock.getFullPath() + sourcePortBlock.getName();
		if (ffpSource.getDirection() == EADirectionKind.OUT)
		{
			//make sure that there isn't already a goto for this OUTport (i.e. two clamp connectors from same port), which is not allowed
			//it is allowed if they have different tags
			Outport outport = (Outport)sourcePortBlock;
		//	if (!outport.hasGoto())
		//	{
				//utport från subsystem = lägg goto i samma system som subsystemet
				SimulinkSystem sourceFather = sourceSubsystemBuilder.getParentSubsystem().getContainerSystem();
				String  pathDFPFather = sourceFather.getParentSubsystem().getPrototypePathDFP();
				SimulinkSystemBuilder sourceFatherBuilder = mapPathDFPSubsystemBuilder.get(pathDFPFather);

				
				Goto g = sourceFatherBuilder.addGoto(name + "_goto", name);
				g.setGotoSource(sourceSubsystemBuilder.getParentSubsystem()); //attach to subsystem rather than port
				addLine(name+"_goto", sourceFather, sourcePortBlock, sourceSubsystemBuilder.getParentSubsystem(), g, null);
				outport.setHasGoto(true);
		//	}

		}
		else //inport till subsystem = lägg goto inne i subsystemet
		{
			//make sure that there isn't already a goto for this INport (i.e. two clamp connectors from same port), which is not allowed
			//it is allowed if they have different tags
			Inport inport = (Inport)sourcePortBlock;
		//	if (!inport.hasGoto())
		//	{
				Goto g = sourceSubsystemBuilder.addGoto(name + "_goto", name);
				g.setGotoSource(inport);
				addLine(name + "_goto", sourceSubsystemBuilder.system, sourcePortBlock, null, g, null);
				inport.setHasGoto(true);
		//	}
		}
		
		
		//Add From Block for dest
		if (ffpDest.getDirection() == EADirectionKind.OUT)
		{
			Utils.showErrorMessage("OUT not supported for ClampConnector destination. Sure you want to do this?");	
		}
		else{
			//inport => lägg From utanför subsystemet, i.e. i fadersystemet
			SimulinkSystem destFather = destSubsystemBuilder.getParentSubsystem().getContainerSystem();
			String pathDFPFather = destFather.getParentSubsystem().getPrototypePathDFP();
			SimulinkSystemBuilder destFatherBuilder = mapPathDFPSubsystemBuilder.get(pathDFPFather);
			From f = destFatherBuilder.addFrom(name+"_from", name);
			f.setFromDestination(destSubsystemBuilder.getParentSubsystem()); //attach to subsystem of destPortBlock
			addLine(destPortBlock.getName() + "_from", destFather, f, null, destPortBlock, destSubsystemBuilder.getParentSubsystem());
		}
	}

	
	
	/***
	 * find inport or outport in this system by name
	 * 
	 * returns null if not found
	 * */
	protected Block FindPort(String name) {
		Block port = null;

		// look in inports
		Block dummy = new Block();
		dummy.setName(name);

		int index = system.getValueSources().indexOf(dummy);

		if (index != -1) {

			port = system.getValueSources().get(index);
		} else {

			// look in outports
			index = system.getValueSinks().indexOf(dummy);

			if (index == -1) {
				Utils.showErrorMessage("Could not find Port " + name);
				return null;
			}

			port = system.getValueSinks().get(index);
		}

		return port;
	}

	//Finds inport in this system
	protected Block FindInPort(String name) {
		Block port = null;

		// look in inports
		Block dummy = new Block();
		dummy.setName(name);

		int index = system.getValueSources().indexOf(dummy);

		if (index != -1) {

			port = system.getValueSources().get(index);
		} 
		else {

			Utils.showErrorMessage("Could not find inport " + name);
				return null;
			}
		return port;
	}

	protected Block FindOutPort(String name) {
		Block port = null;

		// look in outports
		Block dummy = new Block();
		dummy.setName(name);

		int index = system.getValueSinks().indexOf(dummy);

		if (index != -1) {

			port = system.getValueSinks().get(index);
		} 
		else {

			Utils.showErrorMessage("Could not find outport " + name);
			return null;
		}
		return port;
	}

	
	
	
	private EADirectionKind reverseDirection(EADirectionKind dir) {
		if (dir == EADirectionKind.IN)
			return EADirectionKind.OUT;
		else if (dir == EADirectionKind.OUT)
			return EADirectionKind.IN;
		else {
			Utils.showErrorMessage("Only EADirection IN and OUT supported.");
			return null;
		}

	}

}
