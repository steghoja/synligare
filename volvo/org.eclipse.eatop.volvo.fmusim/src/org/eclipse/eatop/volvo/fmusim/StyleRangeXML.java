package org.eclipse.eatop.volvo.fmusim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;  
import javax.xml.bind.annotation.XmlAttribute;

import org.eclipse.core.internal.expressions.util.ToStringSorter;
import org.eclipse.swt.graphics.Color;
import org.omg.CORBA.PUBLIC_MEMBER;

//Below statement means that class "VvStimuliData" is the root-element  
@XmlRootElement(namespace = "org.eatop.fmusim.VVLogTextXML")  
public class StyleRangeXML {  

	//XML data
	int fontStyle;
	int length;  
	int start;
	int foreground_red;
	int foreground_green;
	int foreground_blue;
	
 
	public StyleRangeXML(int fontStyle, int length, int start, int foreground_red, int foreground_green, int foreground_blue) {
		this.fontStyle = fontStyle;
		this.length = length;
		this.start = start;
		this.foreground_red = foreground_red;
		this.foreground_green = foreground_green;
		this.foreground_blue = foreground_blue;
	}

	public StyleRangeXML(int fontStyle, int length, int start) {
		this.fontStyle = fontStyle;
		this.length = length;
		this.start = start;
		this.foreground_red = -1;
		this.foreground_green = -1;
		this.foreground_blue = -1;
	}

	public StyleRangeXML() {
		 } 
	

	public int getFontStyle() {
		return fontStyle;
	}

	@XmlElement
	public void setFontStyle(int fontStyle) {
		this.fontStyle = fontStyle;
	}

	public int getLength() {
		return length;
	}

	@XmlElement
	public void setLength(int length) {
		this.length = length;
	}

	public int getStart() {
		return start;
	}

	@XmlElement
	public void setStart(int start) {
		this.start = start;
	}

	public int getForeground_red() {
		return foreground_red;
	}
	
	@XmlElement
	public void setForeground_red(int foreground_red) {
		this.foreground_red = foreground_red;
	}

	public int getForeground_green() {
		return foreground_green;
	}

	@XmlElement
	public void setForeground_green(int foreground_green) {
		this.foreground_green = foreground_green;
	}

	public int getForeground_blue() {
		return foreground_blue;
	}

	@XmlElement
	public void setForeground_blue(int foreground_blue) {
		this.foreground_blue = foreground_blue;
	}
	

	

}