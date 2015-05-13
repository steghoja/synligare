package org.eclipse.eatop.volvo.fmuimport.popup.actions;



import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Parser {

	 
		public static Document parse(String fileName) {
	 
		  try {
	 
			File fXmlFile = new File(fileName);
			System.out.print(fXmlFile.toString());
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			
			
			return doc;

		  } catch (Exception e) {
			e.printStackTrace();
			return null;
		  }
	  }
	  
}
