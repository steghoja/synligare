package org.eclipse.eatop.semcon.placeandroute;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.geom.Point2D;

public class PNREdge{
    private String id;
    private PNRNode source;
    private PNRNode target;
    private List<Point2D> route;

    public PNREdge(String _id, PNRNode _source, PNRNode _target){
	this.setId(_id);
	this.setSource(_source);
	this.setTarget(_target);
	this.route = new ArrayList<Point2D>();
    }

    public void setId(String _id){
	this.id = _id;
    }

    public String getId(){
	return this.id;
    }

    public void setSource(PNRNode _source){
	this.source = _source;
    }

    public PNRNode getSource(){
	return this.source;
    }

    public void setTarget(PNRNode _target){
	this.target = _target;
    }

    public PNRNode getTarget(){
	return this.target;
    }

    public void clearRoute(){
	if (this.route == null){
	    this.route = new ArrayList<Point2D>();
	}else{
	    this.route.clear();
	}
    }

    public void addPointToRoute(Point2D _point){
	this.route.add(_point);
    }

    public List<Point2D> getRoute(){
	return this.route;
    }
}

