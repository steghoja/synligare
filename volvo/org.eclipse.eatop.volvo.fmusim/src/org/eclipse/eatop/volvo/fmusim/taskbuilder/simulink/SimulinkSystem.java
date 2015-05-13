package org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink;

import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.xml.serialize.LineSeparator;
import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignFunctionType;
import org.eclipse.eatop.eastadl21.EADirectionKind;
import org.eclipse.eatop.eastadl21.FunctionConnector;
import org.eclipse.eatop.eastadl21.FunctionConnector_port;
import org.eclipse.eatop.eastadl21.FunctionFlowPort;
import org.eclipse.eatop.eastadl21.FunctionPort;
import org.eclipse.eatop.eastadl21.FunctionPrototype;
import org.eclipse.eatop.eastadl21.impl.DesignLevelImpl;
import org.eclipse.eatop.volvo.fmusim.Utils;
import org.eclipse.eatop.volvo.fmusim.ecore.ModelProcessor;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.DFTExecutionInfo;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.Block;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.FMU_ME;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.Inport;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.Outport;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.PulseGenerator;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.RepeatingSequenceInterpolated;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.Scope;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.Subsystem;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.Trigger;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.TriggeredSubsystem;
import org.eclipse.core.commands.operations.TriggeredOperations;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/***
 * 
 * This class represents a simulink system, i.e. a "System" in the .mdl file
 *

 */

public class SimulinkSystem {

	protected boolean topLevel;
	protected Subsystem parentSubsystem; // null on top-level, parentSubsystem holds this object as its system object 


	public SimulinkSystem(boolean topLevel) {
		this.topLevel = topLevel;
	}

	
	protected String name;
	protected List<Block> valueSources = new ArrayList<Block>(); // Sources that 
																// provide input
																// data values,
																// i.e. leftmost
																// blocks.
																// PulseGen is
																// not a
																// valueSource.
	protected List<Block> blocks = new ArrayList<Block>();
	protected List<Subsystem> monitors = new ArrayList<Subsystem>();
	protected List<Block> valueSinks = new ArrayList<Block>(); // Sinks that eat
																// output data
																// values, i.e.
																// rightmost
																// blocks
	protected List<Line> lines = new ArrayList<Line>();

	
	protected boolean automaticLayout = true;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Subsystem getParentSubsystem() {
		return parentSubsystem;
	}

	public void setParentSubsystem(Subsystem parent) {
		this.parentSubsystem = parent;
	}

	
	public List<Block> getValueSources() {
		return valueSources;
	}

	public List<Block> getBlocks() {
		return blocks;
	}

	public List<Block> getValueSinks() {
		return valueSinks;
	}

	public List<Line> getLines() {
		return lines;
	}

	public List<Subsystem> getMonitors() {
		return monitors;
	}

	public boolean isTopLevel() {
		return topLevel;
	}

	public boolean isAutomaticLayout() {
		return automaticLayout;
	}

	public void setAutomaticLayout(boolean hasAutomaticLayout) {
		this.automaticLayout = hasAutomaticLayout;
	}


	

}
