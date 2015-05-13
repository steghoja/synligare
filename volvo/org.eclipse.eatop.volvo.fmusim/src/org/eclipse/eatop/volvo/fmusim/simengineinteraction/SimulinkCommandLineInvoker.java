package org.eclipse.eatop.volvo.fmusim.simengineinteraction;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.eclipse.eatop.volvo.fmusim.ExtStyledText;
import org.eclipse.swt.SWT;

public class SimulinkCommandLineInvoker {

	ExtStyledText logWidget;
	
	public SimulinkCommandLineInvoker(ExtStyledText logWidget){
	this.logWidget = logWidget; 
	}
	
public boolean Execute(){	
	try {

	
		
		
		//First delete current result file
		File resultFile = new File("c:\\fmusim\\FMUSimResult.txt"); 
		resultFile.delete();
		
		
		//"..matlab.exe -sd c:\fmusim -r pwd" works, now it works again.
		
		
		//Start matlab via command line
	 	 
		 String sCommand = "matlab.exe -r \"automatic=true;run('C:\\fmusim\\generateFMUSimModel');exit\"";
	      String line;
      Process p = Runtime.getRuntime().exec (sCommand);
	      
	      //must read process output stream or else it may hang
	      //the start process terminates quite soon
	      BufferedReader input = new BufferedReader (new InputStreamReader(p.getInputStream()));
	      while ((line = input.readLine()) != null) {
	        System.out.println(line);
	      }
	      input.close();
    
	
		int tElapsed = 0;
		boolean bResultFileComplete = false;
//		while ((tElapsed < 3*60*1000) && (!bResultFileComplete)) {
		while ((tElapsed < 6*1000) && (!bResultFileComplete)) {
		
			Thread.sleep(1000);
			tElapsed = tElapsed + 1000;
			logWidget.Write(".");
			if (resultFile.exists())
			{
		
				//sleep another 500 ms so we are "sure" that file has been fully saved
				Thread.sleep(500);
				bResultFileComplete = true;

			}
		}
	
		logWidget.WriteLn("");
		return bResultFileComplete;
		
		}
	    catch (Exception err) {
	      err.printStackTrace();
	      return false;
	    }
		
	}	
}
