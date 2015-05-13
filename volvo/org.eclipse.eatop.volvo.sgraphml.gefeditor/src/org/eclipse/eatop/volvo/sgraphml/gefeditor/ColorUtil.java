package org.eclipse.eatop.volvo.sgraphml.gefeditor;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class ColorUtil	 {
	
	//	private ColorUtil(){};
	//	public static ColorUtil INSTANCE = new ColorUtil();
	
	    /**
	     * Decode a color string like '#F567BA;' into a SWT color
	     */
	    public static Color decode(String colorString)
	    {
	        Color color;
	    
	        if (colorString.startsWith("#"))
	        {
	            colorString = colorString.substring(1);
	        }
	        if (colorString.endsWith(";"))
	        {
	            colorString = colorString.substring(0, colorString.length() - 1);
	        }
	    
	        int red, green, blue;
	        switch (colorString.length())
	        {
	        case 6:
	            red = Integer.parseInt(colorString.substring(0, 2), 16);
	            green = Integer.parseInt(colorString.substring(2, 4), 16);
	            blue = Integer.parseInt(colorString.substring(4, 6), 16);
	            color = new Color(Display.getCurrent(), red, green, blue);
	            break;
        default:
	            throw new IllegalArgumentException("Invalid color: " + colorString);
	        }
	        return color;
	    }
	}

