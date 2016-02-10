package org.eclipse.eatop.semcon.placeandroute;


public class PNRMarkStruct{
    private int dist;
    private int x;
    private int y;

    public PNRMarkStruct(int x, int y, int dist){
	this.x = x;
	this.y = y;
	this.dist = dist;
    }
    
    public int getDist(){
	return this.dist;
    }

    public int getX(){
	return this.x;
    }

    public int getY(){
	return this.y;
    }



}

