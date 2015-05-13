package org.eclipse.eatop.volvo.fmusim.taskbuilder;

/***
 * 
 * Execution information for a Design Function Type
 *
 */
public class DFTExecutionInfo {
	float executionTime;			//only used if > 0
	String filenameBehaviour;
	
	
	public String getFilenameBehaviour() {
		return filenameBehaviour;
	}

	public void setFilenameBehaviour(String filename) {
		this.filenameBehaviour = filename;
	}

	public boolean isFMUBehaviour(){
		if (filenameBehaviour == null)
			return false;
		
		String extension = filenameBehaviour.substring(filenameBehaviour.lastIndexOf(".") + 1, filenameBehaviour.length());
	
		return extension.equalsIgnoreCase("fmu");
	}

	public boolean isSimulinkBehaviour(){
		if (filenameBehaviour == null)
			return false;
		
		String extension = filenameBehaviour.substring(filenameBehaviour.lastIndexOf(".") + 1, filenameBehaviour.length());
	
		return extension.equalsIgnoreCase("mdl");
	}

	
	public DFTExecutionInfo() {
	}

	//i.e. WCET
	public float getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(float executionTime) {
		this.executionTime = executionTime;
	}
	
	
	
	
	
	
}
