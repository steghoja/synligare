package command;

import java.io.File;
import java.io.IOException;

import eaxml2hipx.ReadEAXML;
public class EAXML2HIPXMLInterpreter{	
	public static void main(String[] args){
		String filePath = "C:/Users/A047486/runtime-EclipseApplication(1)/example/EMSimple_2FT.eaxml";
		String systemName = "s1";		
		ReadEAXML readFile = new ReadEAXML();
		readFile.EAXML2HIPXML(filePath, systemName);		
	}
}

