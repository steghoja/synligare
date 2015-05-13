package org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks;


import java.io.BufferedWriter;
import java.io.IOException;


public class RelationalOperator extends MultipleInputsBlock {

	String operator;
	
	
	public RelationalOperator() {
		super("RelationalOperator", 2);
		layoutColumn = 2;
		layoutRow = 2;
		setTopLeftPoint();
		width = 25;
		heigth = 80;
	
		operator = ">=";
		
	}

	
	public String getOperator() {
		return operator;
	}


	public void setOperator(String operator) {
		this.operator = operator;
	}


	@Override
	public void Write(BufferedWriter bw) throws IOException{
		
		writeLn("add_block('Simulink/Logic and Bit Operations/Relational Operator', '" + getFullPath() + "', 'Position', " + GetPositionVector() +");"); 
		writeLn("set_param ('" + getFullPath() + "', 'operator', '" + operator + "');");  
		
	}

}
