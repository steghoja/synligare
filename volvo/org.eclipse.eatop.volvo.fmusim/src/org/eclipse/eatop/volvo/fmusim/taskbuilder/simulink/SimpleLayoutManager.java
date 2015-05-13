package org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink;

import java.awt.Point;

import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.Block;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.Constant;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.From;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.Goto;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.RepeatingSequenceInterpolated;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.Subsystem;



public class SimpleLayoutManager {
	
	protected static final int BLOCKSPERCOLUMN = 5;
	
	
	public SimpleLayoutManager() {
	}

	//traverse whole simulink model and rearrange all SimulinkSystem objects
	public void Improve(SimulinkSystem root){

		//rearrange root
		if (root.isAutomaticLayout())
			Rearrange(root);
		
		//rearrange children
		for (Block B : root.getBlocks()){
			if (B instanceof Subsystem){
				Subsystem sub = (Subsystem)B;

				if (sub.getSystem().isAutomaticLayout())
				{
					Rearrange(sub.getSystem());
				}
				Improve(sub.getSystem());
			}
		}
	}
	
	protected void Rearrange(SimulinkSystem sys)
	{
		//Layout: 
		//Column 0 = inports/repeating seq. interpolated (toplevel only)
		//Column 1..n = Blocks - subsystems etc
		//Column n+1 = monitors
		//Column n+2 = outports / scope(toplevel)
		
		//inports
		Point square = new Point();
		int n = 0;
		for (Block B : sys.getValueSources()){
			square.x = n / BLOCKSPERCOLUMN;
			square.y = n % BLOCKSPERCOLUMN;
			B.setSquare(square);
			n++;
		
			if (B instanceof RepeatingSequenceInterpolated)
			{
				square.x += 50;
			}
		
		}
		
		//Blocks - excluding from/Goto 
		int nFirstBlockColumn = n / BLOCKSPERCOLUMN + (((n % BLOCKSPERCOLUMN) > 0) ? 1 : 0);
		n = 0;
		for (Block B : sys.getBlocks()){
			if (!((B instanceof From) || (B instanceof Goto)))
			{
				square.x = nFirstBlockColumn + n / BLOCKSPERCOLUMN;
				square.y = n % BLOCKSPERCOLUMN;
				B.setSquare(square);
				n++;
			}
		}
		
		int nFirstMonitorColumn = nFirstBlockColumn + n / BLOCKSPERCOLUMN + (((n % BLOCKSPERCOLUMN) > 0) ? 1 : 0);
	
		n = 0;
		if (sys.getMonitors().size() > 0)
		{
			nFirstMonitorColumn = nFirstBlockColumn + n / BLOCKSPERCOLUMN + (((n % BLOCKSPERCOLUMN) > 0) ? 1 : 0);
			//Monitors
			n = 0;
			for (Block B : sys.getMonitors()){
				square.x = nFirstMonitorColumn + n / BLOCKSPERCOLUMN;
				square.y = n % BLOCKSPERCOLUMN;
				B.setSquare(square);
				n++;
			}
		}
	
		//Outports
		int nFirstOutportColumn = nFirstMonitorColumn + n / BLOCKSPERCOLUMN + (((n % BLOCKSPERCOLUMN) > 0) ? 1 : 0);
		n = 0;
		for (Block B : sys.getValueSinks()){
			square.x = nFirstOutportColumn + n / BLOCKSPERCOLUMN;
			square.y = n % BLOCKSPERCOLUMN;
			B.setSquare(square);
			n++;
		}

		//Blocks - only From/Goto - put the close to their connected block
		for (Block B : sys.getBlocks()){
			if (B instanceof From) 
			{
				From f = (From)B;
				Point base = f.getFromDestination().getTopLeftPoint();
				f.setTopLeftPoint(new Point(base.x-60, base.y + 5));
			}

			else if (B instanceof Goto)
			{
				Goto g = (Goto)B;
				Point base = g.getGotoSource().getTopLeftPoint();
				
				if (g.getGotoSource() instanceof Subsystem){
					g.setTopLeftPoint(new Point(base.x+200, base.y + 5));
				}
				else //inport / repeatingsequenceinterpolated
				{
					g.setTopLeftPoint(new Point(base.x+60, base.y + 5));
				}
			}
		}
	}		
		
}

