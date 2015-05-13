package org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink;

import java.io.BufferedWriter;
import java.io.IOException;

import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.Block;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.FMU_ME;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.MultipleInputsBlock;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.Subsystem;
import org.eclipse.swt.internal.win32.INPUT;
import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;


public class Line {

	String name; //Not used in simulink model, used here for identification

	//Source
	Block sourcePortBlock;
	Block sourceSubsystemBlock; //null means "this" system, i.e. the system where line is contained 
	
	//dest
	Block destinationPortBlock;
	Block destinationSubsystemBlock;
	int destInput;						//Used For multipleinbputblocks
	
	Subsystem parentSubsystem;
    SimulinkSystem containerSystem;
	static BufferedWriter bw;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Block getSourcePortBlock() {
		return sourcePortBlock;
	}
	public void setSourcePortBlock(Block sourcePortBlock) {
		this.sourcePortBlock = sourcePortBlock;
	}
	public Block getSourceSubsystemBlock() {
		return sourceSubsystemBlock;
	}
	public void setSourceSubsystemBlock(Block sourceSubsystemBlock) {
		this.sourceSubsystemBlock = sourceSubsystemBlock;
	}
	public Block getDestinationPortBlock() {
		return destinationPortBlock;
	}
	public void setDestinationPortBlock(Block destinationPortBlock) {
		this.destinationPortBlock = destinationPortBlock;
	}
	public Block getDestinationSubsystemBlock() {
		return destinationSubsystemBlock;
	}
	public void setDestinationSubsystemBlock(Block destinationSubsystemBlock) {
		this.destinationSubsystemBlock = destinationSubsystemBlock;
	}
	public Subsystem getParentSubsystem() {
		return parentSubsystem;
	}
	public void setParentSubsystem(Subsystem parent) {
		this.parentSubsystem = parent;
	}
	
	public static void setBw(BufferedWriter bw) {
		Line.bw = bw;
	}
	
	
	
	public int getDestInput() {
		return destInput;
	}
	public void setDestInput(int destInput) {
		this.destInput = destInput;
	}
	public void setContainerSystem(SimulinkSystem containerSystem) {
		this.containerSystem = containerSystem;
	}
	public void Write() throws IOException{
		
        //Get path to system in which we want add the line
		String sys;
		if (parentSubsystem == null)
		{
			//'FMUSim'
			sys = "'" + containerSystem.getName() + "'"; 
		}
		else
		{
			//'FMUSim/ExperimentSetup/pSystemUnderTest'
			sys = "'" + parentSubsystem.getFullPath() + "'";
		}

		//Line source 
		String oport;
		if (sourceSubsystemBlock == null)
		{
	
			if (sourcePortBlock instanceof FMU_ME)
			{
				
				FMU_ME fmu = (FMU_ME)sourcePortBlock;
				//FMU - fnuName variable is calculated by .m-script when fmu is loaded
				//Create "[fmuName,'/2']", where 2 is taken from fmi file 
				oport = "[fmuName,'/" + fmu.getOutportId(destinationPortBlock.getName()) + "']";
			}
			else {
			
				//inport to system: 'In7/1', or #1 of a block 'PulseGen/1'
				oport = "'" + sourcePortBlock.getName() + "/1" + "'"; 
			}
		}
		else { //sourceSubsystemBlock != null

			Subsystem sSourceSub = (Subsystem)sourceSubsystemBlock;
			if (sSourceSub.hasSimulinkBehaviour()) {
				//line source is an outport of a subsystem in the system with mdl. Use script to find name matching. 
				//"[Kalle, num2str(strmatch('Kalle/InPortNamn', Kalle_Inports, 'exact')]"
				oport ="['" + sourceSubsystemBlock.getName() + "/', num2str(strmatch('" + sSourceSub.getBehaviourName() + "/"  + sourcePortBlock.getName() + "', " + sSourceSub.getBehaviourName() + "_outports, 'exact'))]";
			}
			else
			{
				//line source is an outport of a subsystem: "pF2/2"
				oport = "'" + sourceSubsystemBlock.getName() + "/" + sourcePortBlock.getPortId() + "'"; 
				
			}
		}


		//Line destination
		String iport;
		if (destinationSubsystemBlock == null){
			if (destinationPortBlock instanceof FMU_ME)
			{
				FMU_ME fmu = (FMU_ME)destinationPortBlock;

				//FMU - fnuName variable is calculated by .m-script when fmu is loaded
				//Create "[fmuName,'/2']", where 2 is taken from source port id 
				iport = "[fmuName,'/" + fmu.getInportId(sourcePortBlock.getName()) + "']";
			}
			
			else if (destinationPortBlock instanceof MultipleInputsBlock) {
				//Line destination is an input of a multipleinputsblock, "LogicBlock/3"
				iport ="'" + destinationPortBlock.getName() + "/" + destInput + "'";
			}
			else{
				//line destination is a outport in the system "Out/1", or the #1 port on a block ("TransitionDelay/1") 
				iport ="'" + destinationPortBlock.getName() + "/1" + "'"; 
			}
		}
		else{

			Subsystem sDestSub = (Subsystem)destinationSubsystemBlock;
			if (sDestSub.hasSimulinkBehaviour()) {
				//line destination is an inport of a subsystem in the system with mdl. Use script to find name matching. 
				//[Kalle, num2str(strmatch('Kalle/InPortNamn', Kalle_Inports, 'exact'))]
				iport ="['" + destinationSubsystemBlock.getName() + "/', num2str(strmatch('" + sDestSub.getBehaviourName() + "/" + destinationPortBlock.getName() + "', " + sDestSub.getBehaviourName() + "_inports, 'exact'))]";
			}
			else {
			
				//line destination is a inport of a subsystem in the system, not with mdl-behavior: "pF2/2"
				iport ="'" + destinationSubsystemBlock.getName() + "/" + destinationPortBlock.getPortId() + "'"; 	

			}
	
		
		}
		
		
		bw.write("add_line(" + sys + ", " + oport + ", " + iport + ", 'autorouting','on');\n");
	}
	
	
	
}
