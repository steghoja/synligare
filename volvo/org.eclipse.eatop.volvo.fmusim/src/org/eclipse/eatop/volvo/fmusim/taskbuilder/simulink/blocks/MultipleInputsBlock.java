package org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks;




public class MultipleInputsBlock extends Block {

	//blocks that have multiple inputs but is not a subsystem
	//example Logic or sum
	int inputs;
	
	public MultipleInputsBlock(String type, int _inputs) {
		super(type);
		inputs = _inputs;
}

	public int getInputs() {
		return inputs;
	}

	public void setInputs(int inputs) {
		this.inputs = inputs;
	}
	
	
	
	
}
