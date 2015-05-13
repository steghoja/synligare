package org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * @author yt11022
 *
 */
public class TriggeredSubsystem extends Subsystem {

   Trigger trigger;

   
   public TriggeredSubsystem() {
		super();
			
		layoutColumn = 2;
		outDataEventSourceBlock = null;
		//Use same blocktype as subsystem, i.e. don't use "simulink/Ports & subsystems/TriggeredSubsystem block to avoid messing with predefined ports.
	
   }

public Trigger getTrigger() {
	return trigger;
}

public void setTrigger(Trigger trigger) {
	this.trigger = trigger;
}







}
