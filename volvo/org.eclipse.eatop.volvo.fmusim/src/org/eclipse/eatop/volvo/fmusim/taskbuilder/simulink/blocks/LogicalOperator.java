package org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks;


import java.io.BufferedWriter;
import java.io.IOException;


public class LogicalOperator extends MultipleInputsBlock {

	String operator;
	
	public LogicalOperator(int inputs) {
		super("Logic", inputs);
		layoutColumn = 0;
		layoutRow = 1;
		setTopLeftPoint();
		width = 25;
		heigth = 80;
	
		operator = "AND";
	}

	
	public String getOperator() {
		return operator;
	}


	public void setOperator(String operator) {
		this.operator = operator;
	}



	@Override
	public void Write(BufferedWriter bw) throws IOException{
		
		writeLn("add_block('Simulink/Logic and Bit Operations/Logical Operator', '" + getFullPath() + "', 'Position', " + GetPositionVector() +");"); 
		writeLn("set_param ('" + getFullPath() + "', 'operator', '" + operator + "');");  
		writeLn("set_param ('" + getFullPath() + "', 'inputs', '" + inputs + "');");  

	}
}
