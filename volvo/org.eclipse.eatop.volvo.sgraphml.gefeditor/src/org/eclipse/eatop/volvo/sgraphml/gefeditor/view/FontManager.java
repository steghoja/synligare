package org.eclipse.eatop.volvo.sgraphml.gefeditor.view;

import java.security.KeyManagementException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;

public class FontManager {

	Map<String, Font> fontMap = new HashMap<String, Font>();
	
	//singleton
	static public FontManager INSTANCE = new FontManager();
	private FontManager() {
	}
	
	public Font getFont (String fontName, int height, int style){
	
		String key = fontName + ",h=" + Integer.toString(height) + ", s=" + Integer.toString(style);
		Font font;
		
		if (fontMap.containsKey(key)){
			font = fontMap.get(key);
		}
		else{
			
			font = new Font(Display.getCurrent(), fontName, height, style);	
			fontMap.put(key, font);
		}
		
		return font;
	}

	public void dispose(){
		Collection<Font> fonts =  fontMap.values();
	
		for (Font font : fonts) {
			font.dispose();
		}
	}
	
}
