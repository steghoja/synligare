package org.eclipse.eatop.volvo.fmusim;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.eclipse.eatop.common.resource.EastADLURIFactory;
import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.eatop.eastadl21.VVLog;
import org.eclipse.eatop.volvo.fmusim.ecore.ModelUpdate;
import org.eclipse.eatop.volvo.fmusim.popup.actions.FmuSimulationAction;
import org.eclipse.eatop.volvo.fmusim.userconfig.VVStimuliDataManager;
import org.eclipse.emf.ecore.util.EcoreUtil;


public class FMUSimViewXMLManager {

	//Singleton stuff 
	private static FMUSimViewXMLManager instance = null;
	private FMUSimViewXMLManager() {

	}	
	public static synchronized FMUSimViewXMLManager getInstance() {
		if (instance == null) {
			instance = new FMUSimViewXMLManager();
		}
		return instance;
	}
	
	//For eaxample: C:\MBAT\WS\eatop-runtime-workspace\.metadata\.plugins\org.eclipse.eatop.fmusim\fmusimview.xml
	String viewFileName = Activator.getDefault().getStateLocation().toString() + "/fmusimview.xml";

	public boolean SaveXML (FMUSimViewXML fmuSimView){
		StringWriter sw = new StringWriter();

		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(FMUSimViewXML.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(fmuSimView, sw);

			
			
			File viewFile = new File(viewFileName);
			if (!viewFile.exists()) {
				viewFile.createNewFile();
			}
			
			FileWriter fw = new FileWriter(viewFileName); 
			fw.write(sw.toString());
		
			fw.close();
			
		} 
		catch (JAXBException e) {
			Utils.showErrorMessage("Failed to convert highlighted VVLog list to xml string. Additional information: " + e.toString());
			return false;
		}


		catch (IOException e2) {
			// TODO: handle exception
			Utils.showErrorMessage(("Error when saving view file. Additional info: " + e2.getMessage()));

		}

		return true;
	}



	public FMUSimViewXML ReadXML()  {	

		FMUSimViewXML fmuSimView = null;

		File viewFile = new File(viewFileName);

		if (viewFile.exists()){

			try {
				FileReader fr = new FileReader(viewFile);

				// create JAXB context and initializing Marshaller  
				JAXBContext jaxbContext = JAXBContext.newInstance(FMUSimViewXML.class);  
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  

				fmuSimView = (FMUSimViewXML) jaxbUnmarshaller.unmarshal(fr);

			} 
			catch (JAXBException e) {  
				Utils.showErrorMessage("Failed to parse view file. Additional information: " + e.toString());

			}  

			catch (FileNotFoundException e) {
				Utils.showErrorMessage("File not found...  " + e.toString());
			}

		}
		return fmuSimView;
	}





}
