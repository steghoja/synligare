package org.eclipse.eatop.semcon.placeandroute;

import org.eclipse.eatop.semcon.placeandroute.PNRNode;

public class PNRNodeIntegerPair{
    private PNRNode first;
    private int second;

    public PNRNodeIntegerPair(PNRNode _first, int _second){
	this.first = _first;
	this.second = _second;
    }

    public PNRNode getFirst(){
	return first;
    }

    public int getSecond(){
	return second;
    }

}

