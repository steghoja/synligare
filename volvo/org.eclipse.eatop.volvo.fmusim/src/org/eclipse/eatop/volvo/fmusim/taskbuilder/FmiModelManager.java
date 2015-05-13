package org.eclipse.eatop.volvo.fmusim.taskbuilder;

import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.eclipse.eatop.volvo.fmusim.Utils;
import org.eclipse.eatop.volvo.fmusim.userconfig.VVStimuliXMLData;


public class FmiModelManager {

	public FmiModelManager(){
	}

	
	FmiModelDescription fmiModelDescription; 
	/*
	class portData{
		public portData(String name, int id) {
			this.name = name;
			this.id = id;
		}
		String name;
		int id;			//1.. as used in simulink
	}
	*/
	
	List <String> inports = new ArrayList<String>();
	List <String> outports = new ArrayList<String>();
	
	
	
	
	public boolean readModelDescriptionXML(String fmiFilename){
	
		
		try {  
		     
			   // create JAXB context and initializing Marshaller  
			   JAXBContext jaxbContext = JAXBContext.newInstance(FmiModelDescription.class);  
			   Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
			  			  
			   File file = new File(fmiFilename);
			   
			  if (!file.exists()){
				  Utils.showErrorMessage("The file " + fmiFilename + " is missing."); 
				  return false;
			  }
			   
			   fmiModelDescription  = (FmiModelDescription) jaxbUnmarshaller.unmarshal(file);
			   
			  } 
			  catch (JAXBException e) {  
				  Utils.showErrorMessage("Failed to parse xml file " + fmiFilename + " " + e.toString());
					return false;
		  
			  }  
		
			collectPorts();
			return true;
	}

	private void collectPorts() {

		for (ScalarVariable sv : fmiModelDescription.getScalarVariablesList()) {

			String causality = sv.getCausality();
			if (causality != null) {

				if (sv.getCausality().equalsIgnoreCase("input")) {

					// assume inports listed in valuereference order
					inports.add(sv.getName().trim());
				} else if (sv.getCausality().equalsIgnoreCase("output")) {
					// assume outports listed in valuereference order
					outports.add(sv.getName().trim());

				}
			}
		}
	}
		
	public int getInportNr(String name){
		int id = inports.indexOf(name.trim());
		
		if (id == -1)
			{
			 Utils.showErrorMessage("Failed to find id of FMU inport " + name);
			}

		return id + 1;
	}
		
	public int getOutportNr(String name){

	int id = outports.indexOf(name.trim());
		
		if (id == -1)
			{
			 Utils.showErrorMessage("Failed to find id of FMU outport " + name);
			}

		return id + 1;
	}

}
