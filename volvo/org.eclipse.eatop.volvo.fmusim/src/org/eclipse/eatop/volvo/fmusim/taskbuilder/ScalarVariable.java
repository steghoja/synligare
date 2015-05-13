package org.eclipse.eatop.volvo.fmusim.taskbuilder;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

//Below statement means that class "FmiModelDescription" is the root-element  
@XmlRootElement(namespace = "org.eatop.fmusim.taskbuilder.FmiModelDescription")  
public class ScalarVariable {

	String name;				//"In1"
	int valueReference;		    // 0
	String causality; 			//"input" / "output" / "parameter"
	
	public ScalarVariable(){
	}
	
	
	public String getName() {
		return name;
	}
	
	@XmlAttribute
	public void setName(String name) {
		this.name = name;
	}
	public int getValueReference() {
		return valueReference;
	}
	
	@XmlAttribute
	public void setValueReference(int valueReference) {
		this.valueReference = valueReference;
	}
	public String getCausality() {
		return causality;
	}
	
	@XmlAttribute
	public void setCausality(String causality) {
		this.causality = causality;
	}
	
	
	
}
