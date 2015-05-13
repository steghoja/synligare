package command;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.eatop.eastadl21.ErrorModelPrototype;
import org.eclipse.eatop.eastadl21.ErrorModelType;
import org.eclipse.eatop.eastadl21.FunctionPrototype;
import org.eclipse.eatop.eastadl21.Identifiable;
import org.eclipse.eatop.volvo.hiphopsbridge.Activator;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ShowCutSet {
	private ArrayList<String> eventIDs;
	private ArrayList<String> eventNames;
	private ArrayList<String> elementList;
	private ErrorModelType topEMT;
	public int cutsetFile = 0;
	public String systemName= "";
	
	public void getCutSetInfo(EditingDomain ed, String FolderPath,EObject selectedEObject){
		if(selectedEObject instanceof ErrorModelType){
			topEMT = (ErrorModelType) selectedEObject;
			systemName = topEMT.getShortName();
			EList<ErrorModelPrototype> emtParts = topEMT.getPart();
			for(final ErrorModelPrototype emtPart :emtParts){
				ed.getCommandStack().execute(new RecordingCommand((TransactionalEditingDomain)ed) {
				    protected void doExecute() {
				    	emtPart.setName("");
				    }
				});	
			}
		}
		
		File folder = new File(FolderPath);
		if(folder.exists()){
			//list all the directory contents
    		String filesName[] = folder.list();    		
    		for(String fileName: filesName){
    			String filePath = FolderPath +"/"+fileName; 
    			File file = new File(filePath);
//    			String fileName = file.getName();
    			if(fileName.contains("CutSets")){
    				ArrayList<String> eventIDs = new ArrayList<String>();
    				ArrayList<String> eventNames = new ArrayList<String>();    				
    				cutsetFile++;
					try {
	    				// Read CutSets Files
	    				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    				DocumentBuilder dBuilder;
						dBuilder = dbFactory.newDocumentBuilder();
	    				Document doc = dBuilder.parse(file);
	    				doc.getDocumentElement().normalize();
	    				
	    				NodeList nList = doc.getElementsByTagName("Event");
	    				if(nList.getLength()>0){
	    					for (int temp = 0; temp < nList.getLength(); temp++) {	    			 
		    					Node nNode = nList.item(temp);	    			 
		    					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		    						Element eElement = (Element) nNode;
		    						String eventID = eElement.getAttribute("ID");
		    						eventIDs.add(eventID);
//		    						System.out.println("Event ID : " + eElement.getAttribute("ID"));	    			 
		    					}
		    				}
		    				
		    				NodeList basicEvents = doc.getElementsByTagName("BasicEvent");
		    				if(basicEvents.getLength()>0){
			    				for(int be=0; be<basicEvents.getLength();be++){
			    					Node basicEvent = basicEvents.item(be);
			    					if (basicEvent.getNodeType() == Node.ELEMENT_NODE) {
			    						Element eElement = (Element) basicEvent;
			    						String basicEventID = eElement.getAttribute("ID");
			    						if(eventIDs.contains(basicEventID)){
			    							String objectName = eElement.getElementsByTagName("Name").item(0).getTextContent();
			    							eventNames.add(objectName);
			    						}
			    					}
			    				}
			    				
			    				StringBuffer result = new StringBuffer();
			    				for (int i = 0; i < eventNames.size(); i++) {
			    				   result.append( eventNames.get(i));
			    				   result.append("; ");
			    				}
			    				String allEvents = result.toString();
			    				
			    				log("The CutSets for selected system "+systemName+" are "+ allEvents);
			    				// Decorate all cutsets
			    				decorateCutSet(eventNames,ed);
		    					
		    				}
	    					
	    				}
	    					    				
					} catch (ParserConfigurationException | SAXException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

    			}
    		} // End of loop files
			
		}
		else{
			log("No Fault Tree has been generated");
		}
		
	}
	
	private void decorateCutSet(ArrayList<String> eventNames,EditingDomain ed) {
		// TODO Auto-generated method stub
		ArrayList<String> protoNames = new ArrayList<String>();
		for (int i = 0; i < eventNames.size(); i++) {
			String str = eventNames.get(i);
			if(str.contains(".")){
				String[] strParts = str.split("\\.");
				protoNames.add(strParts[1]);
			}
		}// End of loop each event
		EList<ErrorModelPrototype> parts = topEMT.getPart();
		for(final ErrorModelPrototype part:parts){
			if(protoNames.contains(part.getShortName())){
				String newName="";
				if(part.getName().equals("")){
					newName = "cutset"+ cutsetFile;
				}
				else{
					newName = part.getName()+"; cutset"+ cutsetFile;
				}

				final String partName = newName;
				ed.getCommandStack().execute(new RecordingCommand((TransactionalEditingDomain)ed) {
				    protected void doExecute() {
				    	part.setName(partName);
				    	if(part.getTarget()!=null){
				    		FunctionPrototype funPart = (FunctionPrototype) part.getTarget();
				    		funPart.setName(partName);
				    	}
				    }
				});	
			}
		}

		
	}

	/**
     *  Write the message to Problem view
     */ 
       public void log(String msg) {
              log(msg, null);
           }
       
       public void log(String msg, Exception e) {
          String myPluginID = "org.eatop.hip_hops_FTA";         
          Activator activator = Activator.getDefault();
          activator.getLog().log(new Status(Status.INFO, myPluginID, Status.OK, msg, e));
       }



}
