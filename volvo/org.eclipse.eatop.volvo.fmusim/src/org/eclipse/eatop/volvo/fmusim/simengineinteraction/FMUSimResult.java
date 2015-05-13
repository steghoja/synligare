package org.eclipse.eatop.volvo.fmusim.simengineinteraction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



import org.eclipse.eatop.volvo.fmusim.Utils;



/***
 * 
 * Parses the matlab output file MUSimResult.txt
 *
 */
public class FMUSimResult {

	

	List<MonitorOutput> outputs = new ArrayList<MonitorOutput>(); 
			
	public boolean Parse()   {
		File f= new File("c:\\fmusim\\FMUSimResult.txt");
		
		if (!f.exists()) {
			Utils.showErrorMessage("Can not find matlab result file FMUSimResult.txt");
			return false;
		}
		
		
		
		try{
			
		
		FileReader input = new FileReader(f);
		BufferedReader bufRead = new BufferedReader(input);
		String sLine = null;

		MonitorOutput mo = null;
		
		while ( (sLine = bufRead.readLine()) != null)
		{    
			
			sLine = sLine.trim();
			if (sLine.isEmpty())
			{
				//skip blank line
			}
			if (sLine.contains("="))
			{
				
				String sVarName = (sLine.substring(0, sLine.indexOf('=') - 1)).trim();
				
				mo = new MonitorOutput(sVarName);
				outputs.add(mo);
				
			}
			else{
				
				String[] sList = sLine.split(" ");
				
				try{
					
				
				float t = Float.parseFloat(sList[0]);
				float v = Float.parseFloat(sList[1]);
				mo.Add(t, v);
				}
				catch (NumberFormatException e)
				{
					Utils.showErrorMessage("Format error in FMUSimResult.txt");
					bufRead.close();
					return false;
				}
			}
		}
		
		}
		catch (IOException e)
		{
			Utils.showErrorMessage("Format error in FMUSimResult.txt");
			return false;

		}
	
		
		return true;
	}

	public List<MonitorOutput> getOutputs() {
		return outputs;
	}
	
	
	
	
}
