package org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

import org.eclipse.eatop.volvo.fmusim.taskbuilder.FmiModelManager;

public class FMU_ME extends Block {
	
	String filename; 				 //relative path from .mdl (unsaved?)
	FmiModelManager fmiModelManager; //port names from fmi .xml file
	
	
	public FMU_ME() {
		super("Reference");
		
		layoutColumn = 3;
		layoutRow = 2;
		setTopLeftPoint();
		width = 165;
		heigth = 142;

		
		fmiModelManager = new FmiModelManager();
	}
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	
	public boolean readPortsfromXML(){
		
		
		//C:/fmusim/fmu/Gain10/Gain10.fmu -> filename is "fmu\Gain10\gain10.fmu"
		//
		//model description file expected here:
		//C:/fmusim/fmu/Gain10/modeldescription.xml
		
		String sFMuFullpath = "C:\\fmusim\\" + filename;
	
//		String filePath = sFMuFullpath.substring(0,sFMuFullpath.lastIndexOf(File.separator));
//		String sModelDescFilename = filePath + "/ModelDescription.xml";

		File fmu = new File(sFMuFullpath);
		String sModelDescFilename = fmu.getParent() + "\\modelDescription.xml";
		
		
		
		return fmiModelManager.readModelDescriptionXML(sModelDescFilename); 
	}
	
	
	public int getInportId(String name){	
		return fmiModelManager.getInportNr(name);
	}
	
	public int getOutportId(String name){
			return fmiModelManager.getOutportNr(name);
	}

	
	@Override
	public void Write(BufferedWriter bw) throws IOException{
		
	
		writeLn("b = add_block('fmu_me_1_0_lib/FMU ME 1.0', '" + getFullPath() + "', 'Position', " + GetPositionVector() +");");
		
		writeLn("fmuLoadFMUSimulink('" + getFullPath() + "', '" + filename + "');");
		writeLn("fmuName = get_param(b, 'name');");
	}

		
	
}

