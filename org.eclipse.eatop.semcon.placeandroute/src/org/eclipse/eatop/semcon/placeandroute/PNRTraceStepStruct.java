package org.eclipse.eatop.semcon.placeandroute;

import java.awt.geom.Point2D;
import java.util.List;

public class PNRTraceStepStruct{
    private boolean step_found;
    private List<Point2D> path;
    private int old_direction;
    private int x;
    private int y;

    public PNRTraceStepStruct(boolean step_found, List<Point2D> path, int old_direction, int x, int y){
	this.step_found = step_found;
	this.path = path;
	this.x = x;
	this.y = y;
	this.old_direction = old_direction;
    }
    
    public boolean getStepFound(){
	return this.step_found;
    }

    public List<Point2D> getPath(){
	return this.path;
    }

    public int getOldDirection(){
	return this.old_direction;
    }

    public int getX(){
	return this.x;
    }

    public int getY(){
	return this.y;
    }



}

