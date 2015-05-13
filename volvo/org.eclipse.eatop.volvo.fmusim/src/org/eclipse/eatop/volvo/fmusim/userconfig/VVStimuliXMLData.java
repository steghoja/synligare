package org.eclipse.eatop.volvo.fmusim.userconfig;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import java.util.ArrayList;

@XmlRootElement
@XmlType(propOrder = { "startTime", "stopTime", "listOfInports"}) 
public class VVStimuliXMLData {

	float startTime;
    float stopTime;
	
    private ArrayList<InportXMLData> listOfInports = new ArrayList<InportXMLData>();
    
	public float getStartTime() {
		return startTime;
	}
 
	@XmlElement
	public void setStartTime(float starttime) {
		this.startTime = starttime;
	}
 

	public float getStopTime() {
		return stopTime;
	}
 
	@XmlElement
	public void setStopTime(float stoptime) {
		this.stopTime = stoptime;
	}

	
	public ArrayList<InportXMLData> getListOfInports() {  
		  return listOfInports;  
		 }  
		   
	 // XmLElementWrapper generates a wrapper element around XML representation  
	   @XmlElementWrapper(name = "inportDataList")  
	  // XmlElement sets the name of the entities in collection  
	   @XmlElement(name = "inportData")  
	 public void setListOfInports(ArrayList<InportXMLData> listofinports) {  
	  this.listOfInports = listofinports;  
	 }  

	
}
