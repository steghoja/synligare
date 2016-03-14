package org.eclipse.eatop.semcon.placeandroute;

import java.util.HashMap;
import java.awt.geom.Point2D;

public class PNRReturnStruct{
	private boolean success;
	private HashMap<Point2D, Integer> mark;
	private int x;
	private int y;

	public PNRReturnStruct(boolean success, HashMap<Point2D, Integer> mark, int x, int y){
		this.success = success;
		this.mark = mark;
		this.x = x;
		this.y = y;
	}

	public boolean getSuccess(){
		return this.success;
	}

	public HashMap<Point2D, Integer> getMark(){
		return this.mark;
	}

	public int getX(){
		return this.x;
	}

	public int getY(){
		return this.y;
	}



}

