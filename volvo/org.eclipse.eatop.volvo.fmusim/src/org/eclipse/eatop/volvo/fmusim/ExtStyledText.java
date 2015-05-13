package org.eclipse.eatop.volvo.fmusim;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Color;



import org.eclipse.emf.ecore.resource.URIConverter.Writeable;
import org.eclipse.emf.workspace.EMFCommandOperation;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.StyledTextContent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;


/***
 * 
 * Simple Wrapper to simplify usage of StyledText. 
 * The writes are mode from the GUI thread, i.e. it is safe to call this class from another thread.
 * 
 * Usage example
 * 
 * Write ("my text")
 * WriteLn ("my text")
 * WriteStyle ("my text", SWT.BOLD)
 * WriteColor ("my text", SWT.COLOR_RED)
 * WriteColorLn ("my text", SWT.COLOR_RED)
 * Write ("my text", SWT_BOLD, SWT.COLOR_RED)
 */
public class ExtStyledText extends StyledText {

	
	
	public ExtStyledText(Composite parent, int style) {
		super(parent, style);
		// TODO Auto-generated constructor stub
		
	}

	
	public void newLine(){
		this.Write("\n");
	}
	
	public void Write(final String s){
		Display.getDefault().asyncExec(new Runnable() {
            public void run() {
            	append(s);
            }
         });
 
	}

	public void WriteLn(String s){
		this.Write(s.concat("\n"));
	}

	public void WriteStyle(String s, int fontStyle){
	
		StyleRange sr = new StyleRange();
		sr.length = s.length();
		sr.fontStyle = fontStyle;

		updateFromGuiThread(s, sr);

	}
	
	public void WriteStyleLn(String s, int fontStyle){
		this.WriteStyle(s.concat("\n"), fontStyle);
	}
	
	
	public void WriteColor(String s, int swtColor){
		StyleRange sr = new StyleRange();
		sr.length = s.length();

		updateFromGuiThreadInclColor(s, sr, swtColor);
	}
	public void WriteColorLn(String s, int swtColor){
		this.WriteColor(s.concat("\n"), swtColor);
		
	}

	public void Write(String s, int fontStyle, int swtColor){

		StyleRange sr = new StyleRange();
		sr.length = s.length();
		sr.fontStyle = fontStyle;

		updateFromGuiThreadInclColor(s, sr, swtColor);
		
	}

	
	
	public void WriteLn(String s, int fontStyle, int swtColor){
		this.Write(s.concat("\n"), fontStyle, swtColor);
	}


	public void Writelink(String link){
		
		StyleRange sr = new StyleRange();
		sr.underline = true;
		sr.underlineStyle = SWT.UNDERLINE_LINK;
		sr.length = link.length();

		updateFromGuiThread(link, sr);

	
		

	}

	
	private void updateFromGuiThread(final String s, final StyleRange sr)
	{
		Display.getDefault().asyncExec(new Runnable() {
            public void run() {
            	sr.start = getCharCount();
            	append(s);
        		setStyleRange(sr);
            }});
			
	}
	
	private void updateFromGuiThreadInclColor(final String s, final StyleRange sr, final int swtColor)
	{
		Display.getDefault().asyncExec(new Runnable() {
            public void run() {
            	
        		Display display = Display.getCurrent();
        		Color c = display.getSystemColor(swtColor);
        		sr.foreground = c;
            	sr.start = getCharCount();
            	append(s);
        		setStyleRange(sr);
            }});
			
	}

	
	
	
	
	
	String sText;
	public String getUnformattedText(){

		Display.getDefault().syncExec(new Runnable() {
            public void run() {
            	sText = getText();
            }});
	
		return sText;
	}

	public String setUnformattedText(final String text){

		Display.getDefault().syncExec(new Runnable() {
            public void run() {
            	setText(text);
            }});
	
		return sText;
	}

	

	StyleRange[] styleRanges;
	public StyleRange[] getAllStyleRanges(){

		Display.getDefault().syncExec(new Runnable() {
            public void run() {
            	styleRanges = getStyleRanges();
            }});
	
		return styleRanges;
	}

	public String setAllStyleRanges(final List<StyleRangeXML> styles){

		Display.getDefault().syncExec(new Runnable() {
            public void run() {

//            	List <StyleRange> srs = new ArrayList<StyleRange>(); 
            	for (StyleRangeXML style : styles) {
				
            		StyleRange sr = new StyleRange();
            		sr.length = style.getLength();
            		sr.fontStyle = style.getFontStyle();
            		sr.start = style.getStart();

            		if (style.getForeground_red() != -1){ 
            		
	            		Display display = Display.getCurrent ();
	            		Color c = new Color (display, style.getForeground_red(), style.getForeground_green(), style.getForeground_blue());
	            		sr.foreground = c;
            		}
            		
            		setStyleRange(sr);
            	}
            	
            }});
	
		return sText;
	}
	
	
	
	
	
}
