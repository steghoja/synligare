package org.eclipse.eatop.volvo.fmusim.userconfig;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;






import org.eclipse.eatop.eastadl21.FunctionFlowPort;
import org.eclipse.eatop.eastadl21.VVStimuli;
import org.eclipse.eatop.volvo.fmusim.Utils;
import org.eclipse.eatop.volvo.fmusim.ecore.ModelUpdate;
import org.eclipse.emf.edit.command.InitializeCopyCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.sphinx.emf.util.WorkspaceEditingDomainUtil;
import org.eclipse.sphinx.emf.util.WorkspaceTransactionUtil;
import org.eclipse.swt.widgets.Shell;


/**
 * Singleton class
 *  
 */

public class VVStimuliDataManager {

	//Singleton stuff 
	private static VVStimuliDataManager instance = null;
	private VVStimuliDataManager() {
	
	}	
	public static synchronized VVStimuliDataManager getInstance() {
		if (instance == null) {
                	instance = new VVStimuliDataManager();
		}
		return instance;
	}

	VVStimuliXMLData vvStimuliXMLData;
	VVStimuli vvStimuli;
	private Shell shell;
	private List<FunctionFlowPort> modelPortList;

	public void Initialize(Shell s, VVStimuli vvS, List<FunctionFlowPort> mPorts ){
		shell = s;
		vvStimuli = vvS;
		modelPortList = mPorts;
	}
	
	/**
	 * XML String to Java objects, i.e. a VVStimuliXMLData object
	 *  
	 */
	public boolean ReadXMLString()  {

		
		String vvStimuliText = vvStimuli.getText();
		
		if (vvStimuliText.length() == 0)
		{
			Utils.showErrorMessage("The VVStimuli has no input data, text attribute is empty.");
				return false;
		}
		
		try {  
		     
			   // create JAXB context and initializing Marshaller  
			   JAXBContext jaxbContext = JAXBContext.newInstance(VVStimuliXMLData.class);  
			   Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
			  
			   StringReader reader = new StringReader(vvStimuliText);
			   vvStimuliXMLData  = (VVStimuliXMLData) jaxbUnmarshaller.unmarshal(reader);
		    
			  } 
			  catch (JAXBException e) {  
				  Utils.showErrorMessage("Failed to parse VVStimuli text attribute. Additional information: " + e.toString());
					return false;
		  
			  }  
		
		return true;
	}

	public boolean WriteXMLString(VVStimuliXMLData data) {
	
		StringWriter sw = new StringWriter();
		
 		  try {
		
			JAXBContext jaxbContext = JAXBContext.newInstance(VVStimuliXMLData.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	 
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(data, sw);

			ModelUpdate mu = new ModelUpdate();
			mu.setVVStimuliText(vvStimuli, sw.toString());
			
	      } 
 		  catch (JAXBException e) {
 			  Utils.showErrorMessage("Failed to convert VVStimuliData objects to xml string. Additional information: " + e.toString());
			  return false;
	      }
		return true;

	}

	public VVStimuliXMLData getVVStimuliXMLData() {
		return vvStimuliXMLData;
	}

	public boolean isEmptyXMLString(){
		return (vvStimuli.getText().length() == 0);
	}
	
		
	public void generateDefaultXMLString(){
	
		
		ModelUpdate mu = new ModelUpdate();
		mu.setVVStimuliText (vvStimuli, "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
			 		 	  "<vvStimuliXMLData xmlns:ns2=\"org.eatop.fmusim.VvStimuliData\">\n" +
			 				"<startTime>0.0</startTime>\n"+
			 				"<stopTime>10.0</stopTime>\n"+
							"</vvStimuliXMLData>");
	}		
		
		
	
	/**
	 * Changes the ports from the XML string (now in memory as objects) to comply with the ports 
	 * described by the model.  The XML string is updated if changes are done.
	 *  
	 *  XML ports:   vvStimuliXMLData.getListOfInports()
	 *  Model ports: modelPortList
	 */
	
	public void alignPorts(){

		boolean bXMLUpdate = false;	
		//Check if we have XML ports that are no longer part of model and shall be removed
		ArrayList<InportXMLData> xmlPorts = vvStimuliXMLData.getListOfInports();
		
		List<InportXMLData> obsoleteXMLPorts = new ArrayList<InportXMLData>();

		for (InportXMLData xmlPort : xmlPorts){
				if (findModelPort(xmlPort.getName()) == null)
				{
					obsoleteXMLPorts.add(xmlPort);
					bXMLUpdate = true;
				}
			}
		xmlPorts.removeAll(obsoleteXMLPorts);
				
		//Check if there are ports in the model that is not in the XML and needs to be added
		for (FunctionFlowPort ffPort : modelPortList){
			
			if (findXMLPort(ffPort.getShortName()) == null)
			{
				//Add an xml port with default values
				String valueVector = "[0 2 7 3 4]";
				String timeVector = "[0 1 2 5 7]";
				
				InportXMLData xmlPort = new InportXMLData(ffPort.getShortName(), 10, valueVector, timeVector);
				xmlPorts.add(xmlPort);
				bXMLUpdate= true;
			}
		}
			
		if (bXMLUpdate){
			WriteXMLString(vvStimuliXMLData);
		}
		
	}
	

	private FunctionFlowPort findModelPort(String name){
		if (modelPortList != null)
		{
			for (FunctionFlowPort ffp : modelPortList){
				if (ffp.getShortName().equals(name)){
					return ffp;
				}
			}
		}
		return null;
	}
	
	public InportXMLData findXMLPort(String name){
		ArrayList<InportXMLData> xmlPorts = vvStimuliXMLData.getListOfInports();
		
		if (xmlPorts != null)
		{
			for (InportXMLData xmlPort : xmlPorts){
		
				if (xmlPort.getName().equals(name)){
					return xmlPort;
				}
			}
		}
		return null;
	}
	
	
	
	
	boolean isVectorMonotonic(){
		return true;
	}

	//...



}
