package org.eclipse.eatop.semcon.placeandroute;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import org.eclipse.eatop.semcon.placeandroute.PNRNodePair;
import org.eclipse.eatop.semcon.placeandroute.PNRNode;
import org.eclipse.eatop.semcon.placeandroute.PNREdge;
import java.util.Queue;

public class PNRGraph{
    private String id;
    private List<PNRNode> nodes;
    private String edgedefault;
    private PNRNode parentNode;

    public PNRGraph(String _id, String _edgedefault, PNRNode _parentNode){
	this.setId(_id);
	this.clearNodes();
	this.setEdgeDefault(_edgedefault);
	this.setParentNode(_parentNode);
    }

    public void setId(String _id){
	this.id = _id;
    }

    public String getId(){
	return this.id;
    }


    public void clearNodes(){
	this.nodes = new ArrayList<PNRNode>();
    }

    public void addNode(PNRNode _node){
	this.nodes.add(_node);
	_node.setParentGraph(this);
    }

    public List<PNRNode> getNodes(){
	return this.nodes;
    }

    public void setEdgeDefault(String _edgedefault){
	if (_edgedefault == "directed"){
	    this.edgedefault = _edgedefault;
	}else if (_edgedefault == "undirected"){
	    this.edgedefault = _edgedefault;
	}else{
	    System.out.println("ERROR: unexpected edgedefault " + _edgedefault + ". Valid values are directed and undirected.\n");
	}
    }

    public String getEdgeDefault(){
	return this.edgedefault;
    }

    public void setParentNode(PNRNode _parentNode){
	this.parentNode = _parentNode;
    }

    public PNRNode getParentNode(){
	return this.parentNode;
    }

    
    public PNRNode findChildNode(String _id){
	Queue<PNRNode> queue = new LinkedList<PNRNode>(this.getNodes());
	while(!queue.isEmpty()){
	    PNRNode n = queue.remove();
	    if (n.getId() == _id){
		return n;
	    }
	    if (n.getGraph() != null){
		for(Iterator<PNRNode> it = n.getGraph().getNodes().iterator(); it.hasNext();){
		    queue.add(it.next());
		}
	    }
	} 
	return null;
    }

    public PNRNodePair findChildNodeAndSameLevelParent(String _id){
	Queue<PNRNodePair> queue = new LinkedList<PNRNodePair>();
	for(Iterator<PNRNode> it = this.getNodes().iterator(); it.hasNext();){
	    PNRNode n = it.next();
	    queue.add(new PNRNodePair(n, n));
	}
	while(!queue.isEmpty()){
	    PNRNodePair p = queue.remove();
	    if (p.getFirst().getId() == _id){
		return p;
	    }
	    if (p.getFirst().getGraph() != null){
		for(Iterator<PNRNode> it2 = p.getFirst().getGraph().getNodes().iterator(); it2.hasNext();){
		    PNRNode n = it2.next();
		    queue.add(new PNRNodePair(n, p.getSecond()));
		}
	    }

	}
	return null;
    }

    public PNREdge getEdgeBySourceIdAndTargetId(String _sourceid, String _targetid){
	Queue<PNRNode> queue = new LinkedList<PNRNode>(this.getNodes());
	while(!queue.isEmpty()){
	    PNRNode n = queue.remove();
	    for(Iterator<PNREdge> it2 = n.getEdges().iterator(); it2.hasNext();){
		PNREdge e = it2.next();
		if ((e.getSource().getId() == _sourceid) &&
		    (e.getTarget().getId() == _targetid)){
		    return e;
		}
	    }
	    if (n.getGraph() != null){
		for(Iterator<PNRNode> it3 = n.getGraph().getNodes().iterator(); it3.hasNext();){
		    queue.add(it3.next());
		}
	    }
	} 
	return null;
    }

    public void setSeparation(){
	int portsize = 0;
	Queue<PNRNode> queue = new LinkedList<PNRNode>(this.getNodes());
	while(!queue.isEmpty()){
	    PNRNode n = queue.remove();
	    if (n.getType() != "GroupNode"){
		portsize = n.getW();
		break;
	    }
	    if (n.getGraph() != null){
		for(Iterator<PNRNode> it = n.getGraph().getNodes().iterator(); it.hasNext();){
		    queue.add(it.next());
		}
	    }
	}
	if (portsize > 0){
	    System.out.println("Updating separator values to " + portsize);
	    Queue<PNRNode> queue2 = new LinkedList<PNRNode>(this.getNodes());
	    while(!queue2.isEmpty()){
		PNRNode n = queue2.remove();
		n.setSeparator(portsize + 2);
		System.out.println("Updated " + n.getId() + " with " + n.getSeparator());
		if (n.getGraph() != null){
		    for(Iterator<PNRNode> it2 = n.getGraph().getNodes().iterator(); it2.hasNext();){
			queue2.add(it2.next());
		    }
		}
	    } 
	}else{
	    System.out.println("ERROR: failed attempt to set separation. No port nodes found in this graph.");
	}
    }
}

