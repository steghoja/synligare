package org.eclipse.eatop.volvo.fmusim.taskbuilder;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import java.util.ArrayList;

@XmlRootElement (name="fmiModelDescription")
public class FmiModelDescription {

		
	  private ArrayList<ScalarVariable> scalarVariablesList = new ArrayList<ScalarVariable>();
	  
		public ArrayList<ScalarVariable> getScalarVariablesList() {  
			  return scalarVariablesList;  
			 }  
			   
		 // XmLElementWrapper generates a wrapper element around XML representation  
		   @XmlElementWrapper(name = "ModelVariables")  
		  // XmlElement sets the name of the entities in collection  
		   @XmlElement(name = "ScalarVariable")  
		 public void setScalarVariablesList(ArrayList<ScalarVariable> scalarVariablesList) {  
		  this.scalarVariablesList = scalarVariablesList;  
		   }
}





