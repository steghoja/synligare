package org.eclipse.eatop.volvo.fmusim.visualization;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;

import org.eclipse.eatop.volvo.fmusim.ExtStyledText;
import org.eclipse.eatop.volvo.fmusim.StyleRangeXML;
import org.eclipse.eatop.volvo.fmusim.Utils;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;



import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class FMUSimLogOffLineDialog extends Dialog {

	protected ExtStyledText log;
	String title;
		
	String text;
	List<StyleRangeXML> styleRangesXML;
	String simulinkInputfilename;
	String simulinkOutputfilename;
	
	
	
	public FMUSimLogOffLineDialog(Shell parentShell, String title, String text, String simulinkInputfilename, String simulinkOutputFilename, List<StyleRangeXML> styleRangesXML) {
		super(parentShell);
		this.title = title;
		this.text = text;
		this.styleRangesXML = styleRangesXML;
		this.simulinkInputfilename = simulinkInputfilename;
		this.simulinkOutputfilename = simulinkOutputFilename;
		
		
		}

	    @Override
	    protected void configureShell(Shell newShell) {
	        super.configureShell(newShell);
	        newShell.setText(title); 
	    }

	    //run on open
	    @Override
	    protected Control createDialogArea(Composite parent) {
	        Composite composite = (Composite) super.createDialogArea(parent);
	        composite.setLayout(null);
	        
	        log = new ExtStyledText(composite, SWT.BORDER);
	        log.setDoubleClickEnabled(false);
	        log.setBounds(20, 24, 645, 425);
	        log.setText(text);
	        log.setAllStyleRanges(styleRangesXML);
	        
	        
	        log.newLine();
	        log.newLine();
	        log.WriteStyle("Model generation script: ", SWT.BOLD);
	        
	        if (simulinkInputfilename.equals("")){
		        log.Write("No file created.");
	        }
	        else{
		        log.Writelink(simulinkInputfilename);
	        }
	        log.newLine();
	        
	        log.newLine();
	        log.WriteStyle("Simulation result file:  ", SWT.BOLD);
	        if (simulinkOutputfilename.equals("")){
		        log.Write("No file created.");
	        }
	        else{
		        log.Writelink(simulinkOutputfilename);
	        }

	        log.addListener(SWT.MouseDown, new Listener() {
				@Override
				public void handleEvent(Event event) {
					// It is up to the application to determine when and how a link should be activated.
					// In this snippet links are activated on mouse down when the control key is held down 
					//if ((event.stateMask & SWT.MOD1) != 0) {
						try {
							int	offset = log.getOffsetAtLocation(new Point (event.x, event.y));
							StyleRange style = log.getStyleRangeAtOffset(offset);
							if (style != null && style.underline && style.underlineStyle == SWT.UNDERLINE_LINK) {
								
								
								int line = log.getLineAtOffset(offset);
								String clickedLine = log.getLine(line);
								
								String[] words = clickedLine.split(": ");
								String filename = words[1].trim();
								
								try {
									URL url = new URL("file:///" + filename);

									//URL u = f.toURI().toURL();
					                //  Open default external browser 
					                PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser().openURL(url); //new URL("http://www.google.com"));     //new URL("file://" + filename));
					              } 
					             catch (PartInitException ex) {
					                Utils.showErrorMessage(ex.toString());
					            } 
					            catch (MalformedURLException ex) {
					                Utils.showErrorMessage(ex.toString());
					            }
							

							}
						} catch (IllegalArgumentException e) {
							// no character under event.x, event.y
						}
						
					//}
				}
			});   
	        
	        
	        
	        Label lblBugfixMargin = new Label(composite, SWT.NONE);
	        lblBugfixMargin.setBounds(664, 231, 20, 15);
	        
	        
	        return composite;
	        
	    }
	    
	
	    @Override
	    protected void createButtonsForButtonBar(Composite parent) {
	    	Button okButton = createButton(parent, IDialogConstants.OK_ID, 
	            "Ok", true); 
 
	    }   
	    
}
	 