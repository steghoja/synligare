package org.eclipse.eatop.volvo.requirementmetricsviewer;

import org.eclipse.swt.graphics.Color;

public enum ChartColor {
	RED(new Color(null,255,0,0)),
	GREEN(new Color(null,102,255,179)),
	BLUE(new Color(null,41,41,255)),
	YELLOW(new Color(null,255,255,102)),
	PURPLE(new Color(null,179,102,255)),
	PINK(new Color(null,255,102,179)),
	LIGHTBLUE(new Color(null,102,179,255)),
	ORANGE(new Color(null,255,179,102));
	Color color;

	ChartColor(Color c) {
		color = c;
	}

	public Color toColor() {
		return color;
	}
	
	public static Color getColor(int i) {
		switch (i%8) {
		case 0 : return RED.color;
		case 1 : return GREEN.color;
		case 2 : return LIGHTBLUE.color;
		case 3 : return YELLOW.color;
		case 4 : return PURPLE.color;
		case 5 : return PINK.color;
		case 6 : return BLUE.color;
		case 7 : return ORANGE.color;
		default: return RED.color;
		}
	}
}
