package org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.channels.Pipe.SinkChannel;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.FunctionConnector;
import org.eclipse.eatop.eastadl21.FunctionFlowPort;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.Line;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.SimulinkSystem;


public class Subsystem extends Block {
	
	
	protected SimulinkSystem system;
	String fullPath;				//In the simulink model
	
	
	//For Discrete DFP Leaf functions, i.e. Time or Event trigged function, we use 
    Block outDataEventSourceBlock; //The block that supplies trigger signal to OutputData subsystem
	Outport outputEventPort;       //The Output Port that carries the trigger signal to next function
	  
	
	DesignFunctionPrototype dfp;  //The corresponding DesignFunctionPrototype in ECORE
	String prototypePathDFP;				   //The prototype path, that is unique for all subsystems. The same DFP can give rise to several subsystems, with different prototype paths.
	
	
	String mdlBehaviourFilename;	//simulink behaviour .mdl (only for leaf non-FMU DFP)		

	Boolean bMonitor;				//if subsystem corresponds to monitor dfp
	
	public Subsystem() {
	super("Subsystem");
	
		setSystem(new SimulinkSystem(false));
		system.setParentSubsystem(this);
		
		layoutColumn = 1;
		//rnd 0-5
		layoutRow = (int)(Math.random() * 5);
		setTopLeftPoint();
		width = 100;
		
		mdlBehaviourFilename = null;
		bMonitor = false;
	}

	
	public Boolean getbMonitor() {
		return bMonitor;
	}


	public void setbMonitor(Boolean bMonitor) {
		this.bMonitor = bMonitor;
	}


	public String getPrototypePathDFP() {
		return prototypePathDFP;
	}


	public void setPrototypePathDFP(String pathDFP) {
		this.prototypePathDFP = pathDFP;
	}


			
	
	@Override
	public String getFullPath() {
		return fullPath;
	}


	public void setFullPath(String path) {
		this.fullPath = path;
	}

	//extracts system name from .mdl filename, i.e. ASub.mdl --> ASub
	public String getBehaviourName(){		
		
		File mdlBehaviourFile = new File(mdlBehaviourFilename);
		
		String name = mdlBehaviourFile.getName();
		name = name.substring(0, name.lastIndexOf(".")); //strip extension
		
		//String name = mdlBehaviourFilename.substring(mdlBehaviourFilename.lastIndexOf("\\") + 1, mdlBehaviourFilename.lastIndexOf("."));
		
		return name;
	}
	
	public boolean hasSimulinkBehaviour(){
		return (mdlBehaviourFilename != null);
	}

	public SimulinkSystem getSystem(){
		return system;
	}
	
	public SimulinkSystem setSystem(SimulinkSystem system){
		system.setParentSubsystem(this);
		return this.system = system;
	}

	
	@Override
	public void setName(String name) {
		super.setName(name);
		system.setName(name);
	}
	
	
	@Override
	public void Write(BufferedWriter bw) throws IOException{
		
		writeLn("add_block('Simulink/Ports & Subsystems/Subsystem', '" + getFullPath() + "', 'Position', " + GetPositionVector() +");");
		writeLn("Simulink.SubSystem.deleteContents('" + getFullPath() + "');");

		if (mdlBehaviourFilename != null){
			writeLn("load_system('" + mdlBehaviourFilename + "');");
			writeLn("Simulink.BlockDiagram.copyContentsToSubSystem ('" + getBehaviourName() + "', '" + getFullPath() + "');");
			//Obtain the name of the ports, so we can use that for name matching the port references
			writeLn(getBehaviourName() + "_inports = find_system('" + getBehaviourName() + "', 'SearchDepth', 1, 'BlockType', 'Inport');");
			writeLn(getBehaviourName() + "_outports = find_system('" + getBehaviourName() + "', 'SearchDepth', 1, 'BlockType', 'Outport');");
		}
	}
	
	
	@Override
	public int GetHeight(){
		int n = Math.max(getSystem().getValueSinks().size(), getSystem().getValueSources().size());

		int height = 50;
		if (n > 1)
			height += (n-1) * 20;
		
		return height;
	}

	
	
	
	public SimulinkSystem GetTopLevelSystem()
	{
		if (getContainerSubsystem() == null)
		{
			return getContainerSystem();
		}
		
		//find recursively
		return getContainerSubsystem().GetTopLevelSystem();
	}


	public Block getOutDataEventSourceBlock() {
		return outDataEventSourceBlock;
	}


	public void setOutDataEventSourceBlock(Block outDataEventSourceBlock) {
		this.outDataEventSourceBlock = outDataEventSourceBlock;
	}


	public Outport getOutputEventPort() {
		return outputEventPort;
	}


	public void setOutputEventPort(Outport outputEventPort) {
		this.outputEventPort = outputEventPort;
	}


	public DesignFunctionPrototype getDfp() {
		return dfp;
	}


	public void setDfp(DesignFunctionPrototype dfp) {
		this.dfp = dfp;
	}


	public String getMdlBehaviourFilename() {
		return mdlBehaviourFilename;
	}


	public void setMdlBehaviourFilename(String mdlBehaviourFilename) {
		this.mdlBehaviourFilename = mdlBehaviourFilename;
	}




	
	
}
