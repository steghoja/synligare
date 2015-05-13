package org.eclipse.eatop.volvo.fmusim;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlType(propOrder = { "logText", "simulationResult", "genModelScriptFilename", 
		               "simulationResultFilename", "passedRequirementPaths", "failedRequirementPaths", 
		               /*"passedMonitorPortPaths", "failedMonitorPortPaths", */"styleRangeXMLs" }) 
public class VVLogTextXML {

	String  logText;
	String  genModelScriptFilename;
	String  simulationResultFilename;
	boolean simulationResult;
		
	private List<String> passedRequirementPaths = new ArrayList<String>();
	private List<String> failedRequirementPaths = new ArrayList<String>();
	//private List<String> passedMonitorPortPaths = new ArrayList<String>();
	//private List<String> failedMonitorPortPaths = new ArrayList<String>();
	private List<StyleRangeXML> styleRangeXMLs = new ArrayList<StyleRangeXML>();

	
	
	public String getLogText() {
		return logText;
	}
	@XmlElement
	public void setLogText(String logText) {
		this.logText = logText;
	}
	public String getGenModelScriptFilename() {
		return genModelScriptFilename;
	}
	
	@XmlElement
	public void setGenModelScriptFilename(String genModelScriptFilename) {
		this.genModelScriptFilename = genModelScriptFilename;
	}
	public String getSimulationResultFilename() {
		return simulationResultFilename;
	}
	
	@XmlElement
	public void setSimulationResultFilename(String simulationResultFilename) {
		this.simulationResultFilename = simulationResultFilename;
	}
	public boolean isSimulationResult() {
		return simulationResult;
	}
	
	@XmlElement
	public void setSimulationResult(boolean simulationResult) {
		this.simulationResult = simulationResult;
	}
	
	
	public List<String> getPassedRequirementPaths() {
		return passedRequirementPaths;
	}
	@XmlElementWrapper(name = "PassedRequirementsList")  
	@XmlElement
	public void setPassedRequirementPaths(List<String> passedRequirementPaths) {
		this.passedRequirementPaths = passedRequirementPaths;
	}
	
	
	
	public List<String> getFailedRequirementPaths() {
		return failedRequirementPaths;
	}
	@XmlElementWrapper(name = "FailedRequirementsList")  
	@XmlElement
	public void setFailedRequirementPaths(List<String> failedRequirementPaths) {
		this.failedRequirementPaths = failedRequirementPaths;
	}
	
	/*public List<String> getPassedMonitorPortPaths() {
		return passedMonitorPortPaths;
	}
	@XmlElementWrapper(name = "PassedMonitorPortPaths")  
	@XmlElement
	public void setPassedMonitorPortPaths(List<String> passedMonitorPortPaths) {
		this.passedMonitorPortPaths = passedMonitorPortPaths;
	}
	
	public List<String> getFailedMonitorPortPaths() {
		return failedMonitorPortPaths;
	}
	@XmlElementWrapper(name = "FailedMonitorPortPaths")  
	@XmlElement
	public void setFailedMonitorPortPaths(List<String> failedMonitorPortPaths) {
		this.failedMonitorPortPaths = failedMonitorPortPaths;
	}
	*/	
	public List<StyleRangeXML> getStyleRangeXMLs() {
		return styleRangeXMLs;
	}

	@XmlElementWrapper(name = "StyleRangesList")
	@XmlElement
	public void setStyleRangeXMLs(List<StyleRangeXML> styleRangeXMLs) {
		this.styleRangeXMLs = styleRangeXMLs;
	}

	
	
} 

