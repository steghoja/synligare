package org.eclipse.eatop.volvo.fmusim;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Utils {

	static private Shell shell;
	
	static public void setShell(Shell s){
		shell = s;
	}
	
	static public Shell getShell(){
		return shell;
	}
	
	
	static public void showInformationMessage(final String message){
	
		//run in gui thread
		Display.getDefault().syncExec(new Runnable() {
	            public void run() {
	            	MessageDialog.openInformation(shell, "Fmusim", message);
	            }});
				
		}
	
	static public void showErrorMessage(final String message){
		
		Display.getDefault().syncExec(new Runnable() {
            public void run() {
        		MessageDialog.openError(shell, "Fmusim", message);
            }});

		
	}

	static boolean bQuestion;
	static public boolean showQuestion(final String message){
		
		Display.getDefault().syncExec(new Runnable() {
            public void run() {
            	bQuestion = MessageDialog.openQuestion(shell, "Fmusim", message);
            }});

		return bQuestion;
	}
	
	
	static String dateTimePretty;
	static String dateTimeUgly;
	

	public static String getDateTimePretty() {
		return dateTimePretty;
	}

	public static String getDateTimeUgly() {
		return dateTimeUgly;
	}

	
	public static String prettyDateFormat(){
		return "yyyy/MM/dd HH:mm:ss";
	}
	
	public static void setDateTime() {

		DateFormat dateFormat = new SimpleDateFormat(prettyDateFormat());
		Date date = new Date();
		Utils.dateTimePretty = dateFormat.format(date);
		
		Utils.dateTimeUgly = Utils.dateTimePretty.replace('/', '-');
		Utils.dateTimeUgly = Utils.dateTimeUgly.replace(' ', '_');
		Utils.dateTimeUgly = Utils.dateTimeUgly.replace(':', '.');
	}
	
	//"./projects"
	static String projectsPath;

	public static String getProjectsPath() {
		return projectsPath;
	}

	public static void setProjectsPath(String projectsPath) {
		Utils.projectsPath = projectsPath;
	}
	
	
	static String eaxmlFilename;

	public static String getEaxmlFilename() {
		return eaxmlFilename;
	}

	public static void setEaxmlFilename(String eaxmlFilname) {
		Utils.eaxmlFilename = eaxmlFilname;
	}
	
	
	public static String getViewFilePath(){

		int i = eaxmlFilename.lastIndexOf('.');

		return projectsPath + eaxmlFilename.substring(0,i) + ".view";
				
	}

	public static String getFMUsimpath(){
		return projectsPath + "/fmusim";
	}
	
	//There's no copy file method in Java6. Do this instead.
	 public static void copyFileUsingFileStreams(File source, File dest)  throws IOException {  
	      InputStream input = null;  
	      OutputStream output = null;  
	      try {  

	          input = new FileInputStream(source);  
	          output = new FileOutputStream(dest);  

	          byte[] buf = new byte[1024];  
	          int bytesRead;  
	          while ((bytesRead = input.read(buf)) > 0) {  
	              output.write(buf, 0, bytesRead);  
	          }  
	      } finally {  
	          input.close();  
	          output.close();  
	      }  
	  } 

	
}
