package org.eclipse.eatop.volvo.fmusim.test;

import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;

public class TestGenerateFmiModelSchema {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Class[] classes = new Class[2]; 
		classes[0] = org.eclipse.eatop.volvo.fmusim.taskbuilder.FmiModelDescription.class; 
		classes[1] = org.eclipse.eatop.volvo.fmusim.taskbuilder.ScalarVariable.class; 
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(classes);
			SchemaOutputResolver sor = new MyFmiModelSchema();
			try {
				jaxbContext.generateSchema(sor);
			
			//check out c:\test.txt	 
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		} catch (JAXBException e) {
	
			e.printStackTrace();
		}
		 

		
		
	}

}
