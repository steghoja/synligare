package org.eclipse.eatop.volvo.fmusim.userconfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.xml.bind.annotation.XmlRootElement;  
import javax.xml.bind.annotation.XmlAttribute;

import org.eclipse.core.internal.expressions.util.ToStringSorter;
import org.omg.CORBA.PUBLIC_MEMBER;

//Below statement means that class "VvStimuliData" is the root-element  
@XmlRootElement(namespace = "org.eatop.fmusim.VvStimuliData")  
public class InportXMLData {  

	//XML data
	String portName;
	float sampleTime;  
	String valueVector;
	String timeVector;
	
 
	public InportXMLData() {
		 } 
	
	public InportXMLData(String portname, float sampletime, String valuevector, String timevector) {  
		this.portName = portname;	  
		this.sampleTime = sampletime;  
		this.valueVector = valuevector;
		this.timeVector = timevector;
	 }  
	
	public float getSampleTime() {  
	return sampleTime;  
	}  
	
	public void setSampleTime(float sampletime ) {  
	this.sampleTime = sampletime;  
	}  
	
	public String getValueVector() {  
	return valueVector;  
	}  
	
	public void setValueVector(String valuevector) {  
	this.valueVector = valuevector;  
	}  

	public String getTimeVector() {  
	return timeVector;  
	}  
	
	public void setTimeVector(String timevector) {  
	this.timeVector = timevector;  
	}  

	public String getName() {  
		return this.portName;  
		}  
		
	@XmlAttribute
	public void setName(String name) {  
		this.portName = name;  

	}  
	
	@Override
	public String toString(){
		return getName();
	}


	// -- Methods to check that data is valid ---
	public boolean IsValueVectorFormatValid(){
		return IsVectorFormatValid(valueVector);
	}

	public boolean IsTimeVectorFormatValid(){
		return IsVectorFormatValid(timeVector);
	}

	

	/***
	 * Correct Format: "[10 11 134]"
	 * @param v
	 * @return
	 */
	protected boolean IsVectorFormatValid(String v){
		if (v.length() < 2) return false;
		
		if (!( (v.startsWith("[") && v.endsWith("]"))))  return false;

		String numbersString = trimBrackets(v);
		String[] flostrList = numbersString.split(" ");  

		
		float[] numList = toFloatVector(flostrList);
			
		return (numList != null);
	
		}

		
	private String trimBrackets(String sVector){
		return  sVector.substring(1, sVector.length()- 1);
	}
	
	
	
	/*** 
	 * Converts array of floats in strings to a float array.
	 * 
	 * @param sFloat
	 * @return
	 */
	private float[] toFloatVector (String[] sFloatList)
	{
		float[] floats = new float[sFloatList.length];
		for (int i=0; i<sFloatList.length; i++)
		{
			try {
				floats[i] = Float.parseFloat(sFloatList[i]);	
			} catch (NumberFormatException e) {
				return null;
			}
		}
		return floats;
	}
	

	/***
	 * -1 means phony string
	 * 
	 * @return
	 */
	public int timeVectorLength(){
		return vectorLength(timeVector);
	}

	public int valueVectorLength(){
		return vectorLength(valueVector);
	}

	private int vectorLength(String v){
		String vString = trimBrackets(v);
		String[] vList = vString.split(" ");  
		float[] vFloatList = toFloatVector(vList);

		return (vFloatList != null) ? vFloatList.length:-1;
	}
	
	
	public boolean IsTimeVectorMonotonic(){
		String vTimeString = trimBrackets(timeVector);
		String[] vTimeList = vTimeString.split(" ");  
		float[] vTimeFloatList = toFloatVector(vTimeList);

		if (vTimeFloatList == null) return false;

		for (int i = 0; i < vTimeFloatList.length - 1; i++){
			if (vTimeFloatList[i] >= vTimeFloatList[i+1])
			{
				return false;
			}
			
		}
		return true;
	}  

}