package org.eclipse.eatop.semcon.placeandroute;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.lang.Math;
import java.awt.geom.Point2D;

public class PNRNode{
    private String id;
    private List<PNREdge> edges;
    private int origX;
    private int origY;
    private int x;
    private int y;
    private int w;
    private int h;
    private String type; // "GroupNode", "In", "Out", "InOut"
    private PNRGraph graph;
    private PNRGraph parentGraph;
    private int separator;

    public PNRNode(String _id, String _type, PNRGraph _parentGraph){
	this.setId(_id);
	this.setType(_type);
	this.setParentGraph(_parentGraph);
	//this.setOrigX(0); these are set in setX and setY
	//this.setOrigY(0);
	this.setX(0);
	this.setY(0);
	this.setW(0);
	this.setH(0);
	this.setGraph(null);
	this.setSeparator(1);
	this.clearEdges();
    }

    public void setId(String _id){
	this.id = _id;
    }

    public String getId(){
	return this.id;
    }

    public void clearEdges(){
	this.edges = new ArrayList<PNREdge>();
    }

    public void addEdge(PNREdge _edge){
	this.edges.add(_edge);
    }

    public List<PNREdge> getEdges(){
	return this.edges;
    }

    public void setX(int _x){
	this.origX = _x;
	this.x = _x;
    }

    public void setXandUpdateContent(int _x){
	this.x = _x;
	int xmove = this.getOrigX() - this.getX();
	this.setOrigX(_x);
	if (this.getGraph() != null){
	    Queue<PNRNode> queue = new LinkedList<PNRNode>(this.getGraph().getNodes());
	    while(!queue.isEmpty()){
		PNRNode n = queue.remove();
		n.setX(n.getOrigX() - xmove);
		//for(Iterator<PNREdge> eit = n.getEdges().iterator(); eit.hasNext();){
		//    PNREdge e = eit.next();
		//    if (!e.getRoute().isEmpty()){
		//	for(int i = 0; i < e.getRoute().size(); i = i + 1){
		//	    e.getRoute().set(i, new Point2D.Double((int)(e.getRoute().get(i).getX() - (int)(xmove / n.getSeparator())), (int)(e.getRoute().get(i).getY())));
		//	}
		//    }
		//}
		if (n.getGraph() != null){
		    for(Iterator<PNRNode> it = n.getGraph().getNodes().iterator(); it.hasNext();){
			queue.add(it.next());
		    }
		}
	    }
	}
    }

    public int getX(){
	return this.x;
    }

    public int getOrigX(){
	return this.origX;
    }

    public void setOrigX(int _origX){
	this.origX = _origX;
    }


    public void setY(int _y){
	this.origY = _y;
	this.y = _y;
    }

    public void setYandUpdateContent(int _y){
	this.y = _y;
	int ymove = this.getOrigY() - this.getY();
	this.setOrigY(_y);
	if (this.getGraph() != null){
	    Queue<PNRNode> queue = new LinkedList<PNRNode>(this.getGraph().getNodes());
	    while(!queue.isEmpty()){
		PNRNode n = queue.remove();
		n.setY(n.getOrigY() - ymove);
		//for(Iterator<PNREdge> eit = n.getEdges().iterator(); eit.hasNext();){
		//    PNREdge e = eit.next();
		//    if (!e.getRoute().isEmpty()){
		//	for(int i = 0; i < e.getRoute().size(); i = i + 1){
		//	    e.getRoute().set(i, new Point2D.Double((int)(e.getRoute().get(i).getX()), (int)(e.getRoute().get(i).getY() - (int)(ymove / n.getSeparator()))));
		//	}
		//    }
		//}
		if (n.getGraph() != null){
		    for(Iterator<PNRNode> it = n.getGraph().getNodes().iterator(); it.hasNext();){
			queue.add(it.next());
		    }
		}
	    }
	} 
    }

    public int getY(){
	return this.y;
    }

    public int getOrigY(){
	return this.origY;
    }

    public void setOrigY(int _origY){
	this.origY = _origY;
    }

    public void setW(int _w){
	this.w = _w;
    }

    public void setWandUpdateContent(int _w){
	if (this.getType() == "GroupNode"){
	    this.setW(_w);
	    if (this.getGraph() != null){
		for(Iterator<PNRNode> it = this.getGraph().getNodes().iterator(); it.hasNext();){
		    PNRNode n = it.next();
		    if (n.getType() == "Out"){
			n.setX(this.getX() + this.getW() - this.getSeparator() + 1); // assume that a port node is two units smaller than the grid spacing 
		    }
		}
	    } 
	}
    }

    public int getW(){
	return this.w;
    }

    public void setH(int _h){
	this.h = _h;
    }

    public int getH(){
	return this.h;
    }

    public void setType(String _type){
	if (_type == "GroupNode"){
	    this.type = _type;
	}else if(_type == "In"){
	    this.type = _type;
	}else if(_type == "Out"){
	    this.type = _type;
	}else{
	    System.out.println("ERROR: unexpected type " + _type + ". Valid types are GroupNode, In and Out.\n");
	}
    }

    public String getType(){
	return this.type;
    }

    public void setGraph(PNRGraph _graph){
	this.graph = _graph;
    }

    public PNRGraph getGraph(){
	return this.graph;
    }

    public void setParentGraph(PNRGraph _parentGraph){
	this.parentGraph = _parentGraph;
    }

    public PNRGraph getParentGraph(){
	return this.parentGraph;
    }

    public void setSeparator(int _separator){
	this.separator = _separator;
    }

    public int getSeparator(){
	return this.separator;
    }

    public boolean areColliding(PNRNode _n){
	return (this.x < _n.x + _n.w) && (this.y < _n.y + _n.h) && (this.x + this.w > _n.x) && (this.y + this.h > _n.y);
    }

    public boolean areAtAcceptableDistance(PNRNode _n){
	int padding = 2 * this.getSeparator();
	return !((this.x < _n.x + _n.w + padding) && (this.y < _n.y + _n.h + padding) && (this.x + this.w + padding > _n.x) && (this.y + this.h + padding > _n.y));
    }

    public int deltaX(PNRNode _n){
	return this.x + this.w / 2 - (_n.x + _n.w / 2);
    }

    public int deltaY(PNRNode _n){
	return this.y + this.h / 2 - (_n.y + _n.h / 2);
    }

    public double midPointDistance(PNRNode _n){
	int xdist = this.deltaX(_n);
	int ydist = this.deltaY(_n);
	return Math.sqrt((double)(xdist * xdist + ydist * ydist));
    }
}
