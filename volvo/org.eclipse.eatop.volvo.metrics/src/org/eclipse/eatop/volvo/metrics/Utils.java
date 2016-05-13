package org.eclipse.eatop.volvo.metrics;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class Utils {

	//Singleton
	static public Utils INSTANCE = new Utils();
	private Utils(){};
	
	static private Shell shell;

	public static void setShell(Shell shell) {
		Utils.shell = shell;
	}

	static public void showInformationMessage(final String message){
          	MessageDialog.openInformation(shell, "Metrics", message);
		}
	
	static public void showErrorMessage(final String message){
        		MessageDialog.openError(shell, "Metrics", message);
	}

	static public boolean showQuestion(final String message){
		boolean bQuestion;
	
       	bQuestion = MessageDialog.openQuestion(shell, "Metrics", message);
		return bQuestion;
	}
	
	
}
