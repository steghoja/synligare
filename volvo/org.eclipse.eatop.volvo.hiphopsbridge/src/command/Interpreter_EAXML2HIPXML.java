package command;

import org.eclipse.core.runtime.IPath;

import eaxml2hipx.ReadEAXML;

public class Interpreter_EAXML2HIPXML {

	public void Interpreter_EAXML2HIPXML(String filePath, String systemName) {
		// TODO Auto-generated method stub
		ReadEAXML readFile = new ReadEAXML();
		System.out.println(filePath);
		readFile.EAXML2HIPXML(filePath, systemName);
	}

}
