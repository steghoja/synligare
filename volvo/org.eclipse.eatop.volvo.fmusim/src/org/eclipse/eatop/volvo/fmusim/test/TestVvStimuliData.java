package org.eclipse.eatop.volvo.fmusim.test;


import java.io.StringWriter;
import java.util.ArrayList; 
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.eclipse.eatop.volvo.fmusim.userconfig.InportXMLData;
import org.eclipse.eatop.volvo.fmusim.userconfig.VVStimuliXMLData;

public class TestVvStimuliData {

	public static void main(String[] args) {
		

		//JAVA to XML string
		
		
		//Create VvStimuliData
		VVStimuliXMLData d = new VVStimuliXMLData(); 
		d.setStartTime(0.5f);

		//Create Inports
		InportXMLData in1 = new InportXMLData("IN1", 10,"[0.1 1 2 7]", "[1 2 5 2]");
		InportXMLData in2 = new InportXMLData("IN2", 20,"[1.1 5 6]", "[1 2 2]");
		
		ArrayList<InportXMLData> inportlist = new ArrayList<InportXMLData>(); 
		inportlist.add(in1);
		inportlist.add(in2);
		d.setListOfInports(inportlist);
		
		
		StringWriter sw = new StringWriter();
		
		
 		  try {
	 
		
			JAXBContext jaxbContext = JAXBContext.newInstance(VVStimuliXMLData.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	 
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	 
			jaxbMarshaller.marshal(d, sw);

			 System.out.println("Marshalled to StringWriter:");
			 System.out.println(sw.toString());  
			
			
		      } catch (JAXBException e) {
			e.printStackTrace();
		      }
	 
		
	
	
	  //XML string to JAVA
	try {  
	     
		   // create JAXB context and initializing Marshaller  
		   JAXBContext jaxbContext = JAXBContext.newInstance(VVStimuliXMLData.class);  
  
		   Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
		     
		  
		   // this will create Java object - from the string in sw  
		   StringReader reader = new StringReader(sw.toString());
		   VVStimuliXMLData d2  = (VVStimuliXMLData) jaxbUnmarshaller.unmarshal(reader);

		   System.out.println("XML string parsed ok. Printing variables: ");		  
		   
		   System.out.println("startTime: "+ d2.getStartTime());  
		   System.out.println("stopTime: "+ d2.getStopTime());  

     
		   ArrayList<InportXMLData> l=d2.getListOfInports();  
		    
		   int i=0;   
		   for(InportXMLData ip:l)  
		   {  
		    i++;  
		    System.out.println("Inport:"+i+" "+ip.getName());  
		    System.out.println("  SampleTid:"+i+" "+ip.getSampleTime());
		    System.out.println("  value vector:"+i+" "+ip.getValueVector());
		    System.out.println("  timevector:"+i+" "+ip.getTimeVector());
		   }  
		  
		  } 
		  catch (JAXBException e) {  
			  // some exception occured  
			  e.printStackTrace();  
		  }  
		  
		   

			
	}	
		
}


