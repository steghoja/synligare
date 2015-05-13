package org.eclipse.eatop.volvo.fmusim;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.eclipse.eatop.eastadl21.VVLog;
import org.eclipse.eatop.volvo.fmusim.ecore.ModelUpdate;
import org.eclipse.eatop.volvo.fmusim.userconfig.VVStimuliDataManager;


public class VVLogTextXMLManager {

	//Singleton stuff 
	private static VVLogTextXMLManager instance = null;
	private VVLogTextXMLManager() {

	}	
	public static synchronized VVLogTextXMLManager getInstance() {
		if (instance == null) {
			instance = new VVLogTextXMLManager();
		}
		return instance;
	}

	Map<VVLog, VVLogTextXML> vvLogMap = new HashMap<VVLog, VVLogTextXML>();

	//Note: Only call this when in a write transaction for vvLog
	public boolean SaveXML (VVLog vvLog, VVLogTextXML vvLogTextXML){

		StringWriter sw = new StringWriter();

		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(VVLogTextXML.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(vvLogTextXML, sw);


			//Save XML file
			File vvdir = new File("c:/fmusim/vvlogs");
			if (!vvdir.exists()) {
				vvdir.createNewFile();
			}


			File file = new File(GetFilename(vvLog));

			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			fw.write(sw.toString());
			fw.close();



			//Save pointer to file in VVLog Text attribute
			vvLog.setText("FMUsimulation vvLogFile=" + GetFilename(vvLog));

		} 

		catch (JAXBException e) {
			Utils.showErrorMessage("Failed to convert VVLogTextXML object to xml string. Additional information: " + e.toString());
			return false;
		}
		catch (IOException e) {
			Utils.showErrorMessage("Failed to write vvLog text file. Additional information: " + e.toString());
		}  
		return true;
	}


	private String GetFilename(VVLog vvLog){

		return "c:/fmusim/vvlogs/" + vvLog.getShortName() + ".xml";

	}


	public VVLogTextXML ReadXML(VVLog vvLog)  {	


		VVLogTextXML vvLogTextXML = null;

		try {  



			String text = vvLog.getText();
			if (!text.startsWith("FMUsimulation vvLogFile="))
			{
				Utils.showErrorMessage("VVLog element is not created by FMUsim.");
				return null;
			}

			String logFilename = text.substring(24, text.length()); 


			File file = new File(logFilename);


			FileReader fr = new FileReader(file.getAbsoluteFile());

			// create JAXB context and initializing Marshaller  
			JAXBContext jaxbContext = JAXBContext.newInstance(VVLogTextXML.class);  
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  

			vvLogTextXML = (VVLogTextXML) jaxbUnmarshaller.unmarshal(fr);

			fr.close();


		} 
		catch (JAXBException e) {  
			Utils.showErrorMessage("Failed to parse VVLog text attribute. Additional information: " + e.toString());

		}
		catch (IOException e) {  
			Utils.showErrorMessage("Failed to open VVLog file. Additional information: " + e.toString());
		}  


		vvLogMap.put(vvLog, vvLogTextXML);
		return vvLogTextXML;
	}



	public VVLogTextXML GetVVLogTextXML (VVLog vvLog)
	{
		if (vvLogMap.containsKey(vvLog))
			return vvLogMap.get(vvLog);
		else
		{
			VVLogTextXML vvLogText = ReadXML(vvLog);
			return vvLogText;
		}
	}


}
