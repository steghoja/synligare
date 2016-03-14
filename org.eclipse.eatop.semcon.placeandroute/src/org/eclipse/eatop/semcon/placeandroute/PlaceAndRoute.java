package org.eclipse.eatop.semcon.placeandroute;

import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PlaceAndRoute{

	/***
	 * 
	 * Main method that performs the actual place and route.
	 * 
	 * @param g0 - the top-level graph that holds the diagram to be fixed.
	 */
	public void doPNR(PNRGraph g0){		
		
		g0.setSeparation();
		g0.setEdgeDefault("undirected");
		List<PNRGraph> done = new ArrayList<PNRGraph>();
		Deque<PNRGraph> stack = new ArrayDeque<PNRGraph>();
		stack.push(g0);
		while(!stack.isEmpty()){
		    PNRGraph g = stack.peek();
		    //System.out.println("stack size " + stack.size());
		    // has all the child graphs been processed?
		    boolean foundNotYetProcessed = false;
		    for(Iterator<PNRNode> it = g.getNodes().iterator(); it.hasNext();){
				PNRNode n = it.next();
				if(n.getGraph() != null){
					if(!done.contains(n.getGraph())){
						foundNotYetProcessed = true;
					}
				}
		    }
		    if(!foundNotYetProcessed){
		    	// all the child graphs have been processed - this graph can be processed
		    	if(!done.contains(g)){
		    		System.out.println(g.getId());
		    		PNRPlace pl = new PNRPlace();
		    		if(g.getEdgeDefault() == "directed"){
		    			pl.applyLeftToRightConcept(g);
		    			PNRRoute r = new PNRRoute();
		    			r.tidyUp(g);
		    			r.XYrouting(g);
		    			r.finalizeRoute(g);
		    		}else{
		    			pl.applyUndirectedLayout(g);
		    		}    	
		    		done.add(g);
		    	}
		    	stack.pop();
		    }
		    // add child nodes to the stack, if they are not already in the stack
		    for(Iterator<PNRNode> it = g.getNodes().iterator(); it.hasNext();){
				PNRNode n = it.next();
				if (n.getGraph() != null){
					if(!done.contains(n.getGraph())){
						if(!stack.contains(n.getGraph())){
							stack.push(n.getGraph());
						}
					}
				}
		    }
		    
		} 
	}
}
