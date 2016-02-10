package org.eclipse.eatop.semcon.placeandroute;

import org.eclipse.eatop.semcon.placeandroute.PNRNode;

public class PNRNodePair{
    private PNRNode first;
    private PNRNode second;
    private int occurrence;

    public PNRNodePair(PNRNode _first, PNRNode _second){
	this.first = _first;
	this.second = _second;
	this.occurrence = 1;
    }

    public PNRNode getFirst(){
	return first;
    }

    public PNRNode getSecond(){
	return second;
    }

    public void setOccurrence(int _occurrence){
	this.occurrence = _occurrence;
    }

    public int getOccurrence(){
	return this.occurrence;
    }

    @Override
    public int hashCode(){
	final int prime = 31;
	int result = 1;
	result = prime * result + 10000 * this.first.getX() + 100000 * this.first.getY() + 1000000 * this.second.getX() + 10000000 * this.second.getY();
	return result;
    }

    @Override
    public boolean equals(Object obj){
	if (this == obj){
	    return true;
	}
	if (obj == null){
	    return false;
	}
	if (this.getClass() != obj.getClass()){
	    return false;
	}
	PNRNodePair _p = (PNRNodePair) obj;
	if ((this.first == _p.getFirst()) && (this.second == _p.getSecond())){
	    return true;
	}
	if ( (this.first.getX() == _p.getFirst().getX()) && (this.first.getY() == _p.getFirst().getY()) && (this.second.getX() == _p.getSecond().getX()) && (this.second.getY() == _p.getSecond().getY()) ) {
	    return true;
	}
	return true;
    }
}

