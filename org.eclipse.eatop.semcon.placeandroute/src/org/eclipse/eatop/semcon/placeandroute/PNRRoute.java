package org.eclipse.eatop.semcon.placeandroute;

import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;
import org.eclipse.eatop.semcon.placeandroute.PNRGraph;
import org.eclipse.eatop.semcon.placeandroute.PNRNode;
import org.eclipse.eatop.semcon.placeandroute.PNREdge;
import org.eclipse.eatop.semcon.placeandroute.PNRMarkStruct;
import org.eclipse.eatop.semcon.placeandroute.PNRReturnStruct;
import java.awt.geom.Point2D;

class PNRRoute{
    private HashMap<Point2D, Integer> grid;

    public PNRRoute(){
	this.grid = new HashMap<Point2D, Integer>();
    }

    public void finalizeRoute(PNRGraph g){
	System.out.println("finished routing");
    }

    public void tidyUp(PNRGraph g){
	if(g.getParentNode() != null){
	    g.getParentNode().setXandUpdateContent(((int) g.getParentNode().getX() / g.getParentNode().getSeparator()) * g.getParentNode().getSeparator());
	    g.getParentNode().setYandUpdateContent(((int) g.getParentNode().getY() / g.getParentNode().getSeparator()) * g.getParentNode().getSeparator());
	    g.getParentNode().setWandUpdateContent(((int) g.getParentNode().getW() / g.getParentNode().getSeparator()) * g.getParentNode().getSeparator());
	    g.getParentNode().setH(((int) g.getParentNode().getH() / g.getParentNode().getSeparator()) * g.getParentNode().getSeparator());
	}

	for(Iterator<PNRNode> it = g.getNodes().iterator(); it.hasNext();){
	    PNRNode n = it.next();
	    if(n.getType() == "GroupNode"){
		n.setWandUpdateContent(((int) n.getW() / n.getSeparator()) * n.getSeparator());
		n.setH(((int) n.getH() / n.getSeparator()) * n.getSeparator());
	    }
	}

	for(Iterator<PNRNode> it = g.getNodes().iterator(); it.hasNext();){
	    PNRNode n = it.next();
	    if(n.getType() == "GroupNode"){
		n.setXandUpdateContent(((int) n.getX() / n.getSeparator()) * n.getSeparator());
		n.setYandUpdateContent(((int) n.getY() / n.getSeparator()) * n.getSeparator());
		if(n.getGraph() != null){
		    List<PNRNode> q = new LinkedList<PNRNode>(n.getGraph().getNodes());
		    while(!q.isEmpty()){
			PNRNode n2 = q.remove(0);
			n2.setXandUpdateContent(((int) n2.getX() / n.getSeparator()) * n.getSeparator());
			n2.setYandUpdateContent(((int) n2.getY() / n.getSeparator()) * n.getSeparator());
			if(n2.getType() == "Out"){
			    n2.setX(n2.getParentGraph().getParentNode().getX() + n2.getParentGraph().getParentNode().getW() - n2.getW() - 1);
			}else if(n2.getType() == "In"){
			    n2.setX(n2.getParentGraph().getParentNode().getX() + 1);
			}else if(n2.getType() == "InOut"){
			    n2.setY(n2.getParentGraph().getParentNode().getY() + n2.getParentGraph().getParentNode().getH() - n2.getH() - 1);
			}
			if(n2.getGraph() != null){
			    for(Iterator<PNRNode> it2 = n2.getGraph().getNodes().iterator(); it2.hasNext();){
				q.add(it2.next());
			    }
			}
		    }
		}
	    }else{
		System.out.println("here with " + n.getId());
		n.setXandUpdateContent(((int) n.getX() / n.getSeparator()) * n.getSeparator());
		n.setYandUpdateContent(((int) n.getY() / n.getSeparator()) * n.getSeparator());
		if(g.getParentNode() != null){
		    if(n.getType() == "In"){
			n.setX(n.getParentGraph().getParentNode().getX() + 1);
		    }else if(n.getType() == "Out"){
			n.setX(n.getParentGraph().getParentNode().getX() + n.getParentGraph().getParentNode().getW() - n.getW() - 1);
		    }else if(n.getType() == "InOut"){
			n.setY(n.getParentGraph().getParentNode().getY() + n.getParentGraph().getParentNode().getH() - n.getH() - 1);
		    }
		    
		}
	    }
	    
	}
	if(g.getParentNode() != null){
	    g.getParentNode().setOrigX(g.getParentNode().getX());
	    g.getParentNode().setOrigY(g.getParentNode().getY());
	}
	List<PNRNode> q = new LinkedList<PNRNode>(g.getNodes());
	while(!q.isEmpty()){
	    PNRNode n = q.remove(0);
	    n.setOrigX(n.getX());
	    n.setOrigY(n.getY());
	    if(n.getGraph() != null){
		for(Iterator<PNRNode> it2 = n.getGraph().getNodes().iterator(); it2.hasNext();){
		    q.add(it2.next());
		}
	    }
	}

    }

    public void initializeGrid(PNRGraph g){
	int xMax = 0;
	int yMax = 0;
	int xMin = 10000;
	int yMin = 10000;
	if(g.getParentNode() != null){
	    xMax = g.getParentNode().getX() + g.getParentNode().getW(); 
	    yMax = g.getParentNode().getY() + g.getParentNode().getH(); 
	    xMin = g.getParentNode().getX();
	    yMin = g.getParentNode().getY();
	    for(int i = ((int)(xMin / g.getParentNode().getSeparator())); i < ((int)(xMax / g.getParentNode().getSeparator())) + 1; i = i + 1){
		for(int j = ((int)(yMin / g.getParentNode().getSeparator())); j < ((int)(yMax / g.getParentNode().getSeparator())) + 1; j = j + 1){
		    this.grid.put(new Point2D.Double(i, j), 0);
		}

	    }
	}else{
	    for(Iterator<PNRNode> it = g.getNodes().iterator(); it.hasNext();){
		PNRNode n = it.next();
		xMax = Math.max(xMax, n.getX() + n.getW()); 
		yMax = Math.max(xMax, n.getY() + n.getH());
		xMin = Math.min(xMin, n.getX());
		yMin = Math.min(yMin, n.getY());
	    }
	    for(int i = ((int)(xMin / g.getNodes().get(0).getSeparator())); i < ((int)(xMax / g.getNodes().get(0).getSeparator())) + 1; i = i + 1){
		for(int j = ((int)(yMin / g.getNodes().get(0).getSeparator())); j < ((int)(yMax / g.getNodes().get(0).getSeparator())) + 1; j = j + 1){
		    this.grid.put(new Point2D.Double(i, j), 0);
		}

	    }

	}
    }

    public void markTrace(PNRGraph g, PNREdge e){
	List<Point2D> vertexes = new LinkedList<Point2D>(e.getRoute());
	if(vertexes.size() > 0){
	    Point2D pn = vertexes.remove(0);
	    while(!vertexes.isEmpty()){
		Point2D nn = vertexes.remove(0);
		for(int x = Math.min((int)pn.getX(), (int)nn.getX()); x < Math.max((int)pn.getX(), (int)nn.getX()) + 1; x = x + 1){
		    for(int y = Math.min((int)pn.getY(), (int)nn.getY()); y < Math.max((int)pn.getY(), (int)nn.getY()) + 1; y = y + 1){
			if(this.grid.get(new Point2D.Double(x, y)) != 3){
			    if(pn.getX() == nn.getX()){
				this.grid.put(new Point2D.Double(x, y), this.grid.get(new Point2D.Double(x, y)) + 2);
			    }else{
				this.grid.put(new Point2D.Double(x, y), this.grid.get(new Point2D.Double(x, y)) + 1);
			    }
			}
		    }
		}
		this.grid.put(nn, 3);
		this.grid.put(pn, 3);
		pn = nn;
	    }
	}
    }


    public void markGrid(PNRGraph g){
	this.initializeGrid(g);
	for(Iterator<PNRNode> it = g.getNodes().iterator(); it.hasNext();){
	    PNRNode n = it.next();
	    if(n.getType() == "GroupNode"){
		for(int x = n.getX(); x < n.getX() + n.getW(); x = x + n.getSeparator()){
		    int gridx = (int)(x / n.getSeparator());
		    for(int y = n.getY(); y < n.getY() + n.getH(); y = y + n.getSeparator()){
			int gridy = (int)(y / n.getSeparator());
			this.grid.put(new Point2D.Double(gridx, gridy), 3);
		    }
		}
		if(n.getGraph() != null){
		    for(Iterator<PNRNode> it2 = n.getGraph().getNodes().iterator(); it2.hasNext();){
			PNRNode n2 = it2.next();
			if(n2.getType() == "In"){
			    this.grid.put(new Point2D.Double((int)(n2.getX() / n.getSeparator()), n2.getY() / n.getSeparator()), 1);
			}
			if(n2.getType() == "Out"){
			    this.grid.put(new Point2D.Double((int)(n2.getX() / n.getSeparator()), n2.getY() / n.getSeparator()), 1);
			}
			if(n2.getType() == "InOut"){
			    this.grid.put(new Point2D.Double((int)(n2.getX() / n.getSeparator()), n2.getY() / n.getSeparator()), 2);
			}
		    }
		}
	    }
	}
	List<PNREdge> doneEdges = new ArrayList<PNREdge>();
	for(Iterator<PNRNode> it = g.getNodes().iterator(); it.hasNext();){
	    PNRNode n = it.next();
	    for(Iterator<PNREdge> eit = n.getEdges().iterator(); eit.hasNext();){
		PNREdge e = eit.next();
		if(!doneEdges.contains(e)){
		    doneEdges.add(e);
		    this.markTrace(g, e);
		}
	    }
	    if(n.getGraph() != null){
		for(Iterator<PNRNode> it2 = n.getGraph().getNodes().iterator(); it2.hasNext();){
		    PNRNode n2 = it2.next();
		    for(Iterator<PNREdge> eit2 = n2.getEdges().iterator(); eit2.hasNext();){
			PNREdge e2 = eit2.next();
			if(!doneEdges.contains(e2)){
			    doneEdges.add(e2);
			    this.markTrace(g, e2);
			}
		    }
		}
	    }
	}
    }

    public List<PNREdge> getEdgesForTraceCoordinate(PNRGraph g, int xin, int yin){
	List<PNREdge> edges = new ArrayList<PNREdge>();
	List<PNREdge> doneEdges = new ArrayList<PNREdge>();
	for(Iterator<PNRNode> it = g.getNodes().iterator(); it.hasNext();){
	    PNRNode n = it.next();
	    for(Iterator<PNREdge> eit = n.getEdges().iterator(); eit.hasNext();){
		PNREdge e = eit.next();
		if(!doneEdges.contains(e)){
		    doneEdges.add(e);
		    List<Point2D> vertexes = new LinkedList<Point2D>(e.getRoute());
		    if(vertexes.size() > 0){
			Point2D pn = vertexes.remove(0);
			while(!vertexes.isEmpty()){
			    Point2D nn = vertexes.remove(0);
			    for(int x = Math.min((int)pn.getX(), (int)nn.getX()); x < Math.max((int)pn.getX(), (int)nn.getX()) + 1; x = x + 1){
				for(int y = Math.min((int)pn.getY(), (int)nn.getY()); y < Math.max((int)pn.getY(), (int)nn.getY()) + 1; y = y + 1){
				    if((x == xin) && (y == yin)){
					edges.add(e);
				    }
				}
			    }
			    pn = nn;
			}
		    }
		}
	    }
	    if(n.getGraph() != null){
		for(Iterator<PNRNode> it2 = n.getGraph().getNodes().iterator(); it2.hasNext();){
		    PNRNode n2 = it2.next();
		    for(Iterator<PNREdge> eit2 = n2.getEdges().iterator(); eit2.hasNext();){
			PNREdge e2 = eit2.next();
			if(!doneEdges.contains(e2)){
			    doneEdges.add(e2);

			    List<Point2D> vertexes = new LinkedList<Point2D>(e2.getRoute());
			    if(vertexes.size() > 0){
				Point2D pn = vertexes.remove(0);
				while(!vertexes.isEmpty()){
				    Point2D nn = vertexes.remove(0);
				    for(int x = Math.min((int)pn.getX(), (int)nn.getX()); x < Math.max((int)pn.getX(), (int)nn.getX()) + 1; x = x + 1){
					for(int y = Math.min((int)pn.getY(), (int)nn.getY()); y < Math.max((int)pn.getY(), (int)nn.getY()) + 1; y = y + 1){
					    if((x == xin) && (y == yin)){
						edges.add(e2);
					    }
					}
				    }
				    pn = nn;
				}
			    }
			}
		    }
		}
	    }
  
	}
	return edges;
    }


    public boolean XYrouting(PNRGraph g){
	List<PNREdge> doneEdges = new ArrayList<PNREdge>();
	for(Iterator<PNRNode> it = g.getNodes().iterator(); it.hasNext();){
	    PNRNode n = it.next();
	    for(Iterator<PNREdge> eit = n.getEdges().iterator(); eit.hasNext();){
		PNREdge e = eit.next();
		if(!doneEdges.contains(e)){
		    if(g.findChildNode(e.getSource().getId()) != null){
			if(g.findChildNode(e.getTarget().getId()) != null){
			    doneEdges.add(e);
			}
		    }
		    e.clearRoute();
		}
	    }
	    if(n.getGraph() != null){
		for(Iterator<PNRNode> it2 = n.getGraph().getNodes().iterator(); it2.hasNext();){
		    PNRNode n2 = it2.next();
		    for(Iterator<PNREdge> eit2 = n2.getEdges().iterator(); eit2.hasNext();){
			PNREdge e2 = eit2.next();
			if(!doneEdges.contains(e2)){
			    doneEdges.add(e2);
			    e2.clearRoute();
			}
		    }
		}
	    }
	}
	this.markGrid(g);
	List<PNREdge> failedRoutes = this.routeInOrder(g, doneEdges);
	int counter = 0;
	while(!failedRoutes.isEmpty()){
	    counter = counter + 1;
	    System.out.println(" + + + retrying " + counter + " + + +");
	    List<PNREdge> tmpObst = new ArrayList<PNREdge>();
	    List<PNREdge> uniqFailedRoutes = new ArrayList<PNREdge>();
	    for(Iterator<PNREdge> eit = failedRoutes.iterator(); eit.hasNext();){
		PNREdge e = eit.next();
		if(!uniqFailedRoutes.contains(e)){
		    uniqFailedRoutes.add(e);
		}
	    }
	    for(Iterator<PNREdge> eit = uniqFailedRoutes.iterator(); eit.hasNext();){
		PNREdge e = eit.next();
		PNRNode p1 = e.getSource();
		int gx = (int)(p1.getX() / p1.getSeparator());
		int gy = (int)(p1.getY() / p1.getSeparator());
		if(p1.getType() == "Out"){
		    // go right
		    for(int sx = gx; sx < gx + 5; sx = sx + 1){
			List<PNREdge> obst = this.getEdgesForTraceCoordinate(g, sx, gy);
			for(Iterator<PNREdge> me = obst.iterator(); me.hasNext();){
			    PNREdge e3 = me.next();
			    if(!tmpObst.contains(e3)){
				tmpObst.add(e3);
			    }
			}
		    }
		}else if(p1.getType() == "In"){
		    // go left
		    for(int sx = gx - 5; sx < gx; sx = sx + 1){
			List<PNREdge> obst = this.getEdgesForTraceCoordinate(g, sx, gy);
			for(Iterator<PNREdge> me = obst.iterator(); me.hasNext();){
			    PNREdge e3 = me.next();
			    if(!tmpObst.contains(e3)){
				tmpObst.add(e3);
			    }
			}
		    }

		}else if(p1.getType() == "InOut"){
		    // go left
		    for(int sy = gy; sy < gy + 5; sy = sy + 1){
			List<PNREdge> obst = this.getEdgesForTraceCoordinate(g, gx, sy);
			for(Iterator<PNREdge> me = obst.iterator(); me.hasNext();){
			    PNREdge e3 = me.next();
			    if(!tmpObst.contains(e3)){
				tmpObst.add(e3);
			    }
			}
		    }
		}else{
		    System.out.println("ERROR: expected a port");
		}
		PNRNode p2 = e.getTarget();
		int gx2 = (int)(p2.getX() / p2.getSeparator());
		int gy2 = (int)(p2.getY() / p2.getSeparator());
		if(p2.getType() == "Out"){
		    // go right
		    for(int sx = gx2; sx < gx2 + 5; sx = sx + 1){
			List<PNREdge> obst = this.getEdgesForTraceCoordinate(g, sx, gy2);
			for(Iterator<PNREdge> me = obst.iterator(); me.hasNext();){
			    PNREdge e3 = me.next();
			    if(!tmpObst.contains(e3)){
				tmpObst.add(e3);
			    }
			}
		    }
		}else if(p2.getType() == "In"){
		    // go left
		    for(int sx = gx2 - 5; sx < gx2; sx = sx + 1){
			List<PNREdge> obst = this.getEdgesForTraceCoordinate(g, sx, gy2);
			for(Iterator<PNREdge> me = obst.iterator(); me.hasNext();){
			    PNREdge e3 = me.next();
			    if(!tmpObst.contains(e3)){
				tmpObst.add(e3);
			    }
			}
		    }

		}else if(p2.getType() == "InOut"){
		    // go left
		    for(int sy = gy2; sy < gy2 + 5; sy = sy + 1){
			List<PNREdge> obst = this.getEdgesForTraceCoordinate(g, gx2, sy);
			for(Iterator<PNREdge> me = obst.iterator(); me.hasNext();){
			    PNREdge e3 = me.next();
			    if(!tmpObst.contains(e3)){
				tmpObst.add(e3);
			    }
			}
		    }
		}else{
		    System.out.println("ERROR: expected a port");
		}
	    }
	    for(Iterator<PNREdge> eit = doneEdges.iterator(); eit.hasNext();){
		PNREdge e = eit.next();
		if(e.getRoute().size() > 8){
		    if(!tmpObst.contains(e)){
			tmpObst.add(e);
		    }
		}
	    }
	    for(Iterator<PNREdge> eit = doneEdges.iterator(); eit.hasNext();){
		PNREdge e = eit.next();
		List<Point2D> mypath = new ArrayList<Point2D>();
		if(!e.getRoute().isEmpty()){
		    List<Point2D> seq = new ArrayList<Point2D>(e.getRoute());
		    Point2D pn = seq.remove(0);
		    while(!seq.isEmpty()){
			Point2D nn = seq.remove(0);
			for(int x = Math.min((int)pn.getX(), (int)nn.getX()); x < Math.max((int)pn.getX(), (int)nn.getX()) + 1; x = x + 1){
			    for(int y = Math.min((int)pn.getY(), (int)nn.getY()); y < Math.max((int)pn.getY(), (int)nn.getY()) + 1; y = y + 1){
				boolean found = false;
				for(Iterator<Point2D> pit = mypath.iterator(); pit.hasNext();){
				    Point2D p = pit.next();
				    int px = (int)p.getX();
				    int py = (int)p.getY();
				    if((x == px) && (y == py)){
					found = true;
				    }
							    
				}
				if(!found){
				    mypath.add(new Point2D.Double(x, y));
				}
			    }
			}
			pn = nn;
		    }
		    if(mypath.size() > 3 * this.manhattanDistance(e.getSource(), e.getTarget().getX(), e.getTarget().getY())){
			if(!tmpObst.contains(e)){
			    tmpObst.add(e);
			}
		    }
		}
	    }
	    List<PNREdge> ext = new ArrayList<PNREdge>();
	    for(Iterator<PNREdge> eit = uniqFailedRoutes.iterator(); eit.hasNext();){
		PNREdge e = eit.next();
		for(Iterator<PNREdge> sit = e.getSource().getEdges().iterator(); sit.hasNext();){
		    PNREdge se = sit.next();
		    if(!ext.contains(se)){
			ext.add(se);
		    }
		}
	    }
	    List<PNREdge> full = new ArrayList<PNREdge>();
	    for(Iterator<PNREdge> eit = tmpObst.iterator(); eit.hasNext();){
		PNREdge e = eit.next();
		PNRNode p1 = e.getSource();
		for(Iterator<PNREdge> eit2 = p1.getEdges().iterator(); eit2.hasNext();){
		    PNREdge e2 = eit2.next();
		    if(!full.contains(e2)){
			full.add(e2);
		    }
		}
		PNRNode p2 = e.getTarget();
		for(Iterator<PNREdge> eit2 = p2.getEdges().iterator(); eit2.hasNext();){
		    PNREdge e2 = eit2.next();
		    if(!full.contains(e2)){
			full.add(e2);
		    }
		}
	    }
	    for(Iterator<PNREdge> eit = ext.iterator(); eit.hasNext();){
		PNREdge e = eit.next();
		e.clearRoute();
	    }
 	    for(Iterator<PNREdge> eit = full.iterator(); eit.hasNext();){
		PNREdge e = eit.next();
		e.clearRoute();
	    }
	    this.markGrid(g);
	    List<PNREdge> edgesToReroute = new ArrayList<PNREdge>(ext);

 	    for(Iterator<PNREdge> eit = full.iterator(); eit.hasNext();){
		PNREdge e = eit.next();
		if(!edgesToReroute.contains(e)){
		    edgesToReroute.add(e);
		}
	    }
	    failedRoutes = this.routeInOrder(g, edgesToReroute);	    
	    if(counter > 1){
		System.out.println("ERROR: failed routes:");
		for(Iterator<PNREdge> eit = failedRoutes.iterator(); eit.hasNext();){
		    PNREdge e = eit.next();
		    System.out.println(" * " + e.getId());
		}
		return false;
	    }
	}
	return true;
    }

    public ArrayList<PNREdge> routeInOrder(PNRGraph g, List<PNREdge> edgesToRoute){
	ArrayList<PNREdge> failedRoutes = new ArrayList<PNREdge>();
	List<PNREdge> doneEdges = new ArrayList<PNREdge>();
	for(Iterator<PNREdge> eit = edgesToRoute.iterator(); eit.hasNext();){
	    PNREdge e = eit.next();
	    if(!doneEdges.contains(e)){
		PNRNode n = e.getSource();
		ArrayList<PNREdge> failedRoutesThisIteration = this.routeEdgesForward(g, n.getEdges(), n);
		for(Iterator<PNREdge> it = failedRoutesThisIteration.iterator(); it.hasNext();){
		    PNREdge failedRoute = it.next(); 
		    if(!failedRoutes.contains(failedRoute)){
			failedRoutes.add(failedRoute);
		    }
		}
		for(Iterator<PNREdge> it = n.getEdges().iterator(); it.hasNext();){
		    PNREdge myedge = it.next(); 
		    if(!failedRoutes.contains(myedge)){
			doneEdges.add(myedge);
		    }
		}
		ArrayList<PNREdge> moreFailedRoutes = new ArrayList<PNREdge>();
		for(Iterator<PNREdge> it = e.getTarget().getEdges().iterator(); it.hasNext();){
		    PNREdge edge2 = it.next(); 
		    if(!doneEdges.contains(edge2)){
			moreFailedRoutes = this.routeEdgesBackward(g, e.getTarget().getEdges(), edge2.getTarget());
		    }
		
		    for(Iterator<PNREdge> it2 = moreFailedRoutes.iterator(); it2.hasNext();){
			PNREdge failedRoute = it2.next(); 
			if(!failedRoutes.contains(failedRoute)){
			    failedRoutes.add(failedRoute);
			}
		    }
		    for(Iterator<PNREdge> it2 = e.getTarget().getEdges().iterator(); it2.hasNext();){
			PNREdge myedge = it2.next(); 
			if(!failedRoutes.contains(myedge)){
			    doneEdges.add(myedge);
			}
		    }
		}
	    }
	}
	return failedRoutes;
    }

    public int manhattanDistance(PNRNode pn, int x, int y){
	int xDist = (int)(pn.getX() / pn.getSeparator()) - x;
	if(xDist < 0){
	    xDist = -xDist;
	}
	int yDist = (int)(pn.getY() / pn.getSeparator()) - y;
	if(yDist < 0){
	    yDist = -yDist;
	}
	return xDist + yDist;
    }

    public ArrayList<PNREdge> routeEdgesForward(PNRGraph g, List<PNREdge> edges, PNRNode sn){
	ArrayList<PNREdge> failedRoutes = new ArrayList<PNREdge>();
	List<Point2D> successfulPath = new ArrayList<Point2D>();
	List<Point2D> path = new ArrayList<Point2D>();
	path.add(new Point2D.Double((int)(sn.getX() / sn.getSeparator()), (int)(sn.getY() / sn.getSeparator())));
	System.out.println(" sn coord " + (int)(sn.getX() / 16) + " " + (int)(sn.getY() / 16));
	ArrayList<PNREdge> edgesToRoute = new ArrayList<PNREdge>();
	for(Iterator<PNREdge> eit = edges.iterator(); eit.hasNext();){
	    PNREdge e = eit.next();
	    System.out.println("e id " + e.getId());
	    for(int i = 0; i < e.getRoute().size(); i = i + 1){
		System.out.println(" " + i + " : " + e.getRoute().get(i).getX() + " " + e.getRoute().get(i).getY());
	    }
	    //System.out.println("e.getRoute() size " + e.getRoute().size());
	    //System.out.println("e source " + e.getSource().getId());
	    //System.out.println("e target " + e.getTarget().getId());
	    if(!e.getRoute().isEmpty()){
		System.out.println("#");
		List<Point2D> seqOfCoordinates = new ArrayList<Point2D>(e.getRoute());
		Point2D pn = seqOfCoordinates.remove(0);
		while(!seqOfCoordinates.isEmpty()){
		    Point2D nn = seqOfCoordinates.remove(0);
		    for(int x = Math.min((int)pn.getX(), (int)nn.getX()); x < Math.max((int)pn.getX(), (int)nn.getX()) + 1; x = x + 1){
			for(int y = Math.min((int)pn.getY(), (int)nn.getY()); y < Math.max((int)pn.getY(), (int)nn.getY()) + 1; y = y + 1){
			    boolean found = false;
			    for(Iterator<Point2D> pit = path.iterator(); pit.hasNext();){
				Point2D tp = pit.next();
				if((tp.getX() == x) && (tp.getY() == y)){
				    found = true;
				}
			    }
			    if(!found){
				path.add(new Point2D.Double(x, y));
			    }
			}
		    }
		    pn = nn;
		}

	    }else{
		if(!edgesToRoute.contains(e)){
		    edgesToRoute.add(e);
		}
	    }
	}
	while(!edgesToRoute.isEmpty()){
	    PNREdge e = edgesToRoute.remove(0);
	    PNRNode epn = e.getTarget();
	    List<Point2D> sortedCoordinates = new ArrayList<Point2D>(path);
	    PNRReturnStruct s = this.waveExpansion(g, sortedCoordinates, (int)(epn.getX() / epn.getSeparator()), (int)(epn.getY() / epn.getSeparator()), true, e);
	    System.out.println(" e " + e.getId());
	    if(s.getSuccess()){
		System.out.println(" success waveExpansion w limit");
		List<Point2D> myPath = this.traceback(g, s.getMark(), new Point2D.Double(s.getX(), s.getY()), new Point2D.Double((int)(epn.getX() / epn.getSeparator()), (int)(epn.getY() / epn.getSeparator())), e);
		//if(successfulPath != null){
		//    System.out.println("size successfulPath: " + successfulPath.size());
		//}
		
		if(myPath != null){
		    successfulPath = new ArrayList<Point2D>(myPath);
		    System.out.println(" success traceback  source coord " + (int)(e.getSource().getX() / 16) + " " + (int)(e.getSource().getY() / 16) + " target coord " + (int)(e.getTarget().getX() / 16) + " " + (int)(e.getTarget().getY() / 16));
		    for(int i = 0; i < path.size(); i = i + 1){
			System.out.println(" " + i + " : " + (path.get(i).getX()) + " " + (path.get(i).getY()));
		    }
		    
		    for(int i = 0; i < successfulPath.size(); i = i + 1){
			System.out.println(" " + i + " : " + (successfulPath.get(i).getX()) + " " + (successfulPath.get(i).getY()));
		    }

		}else{

		    PNRReturnStruct s2 = this.waveExpansion(g, sortedCoordinates, (int)(epn.getX() / epn.getSeparator()), (int)(epn.getY() / epn.getSeparator()), false, e);
		    if(s2.getSuccess()){
			System.out.println(" success waveExpansion");
			List<Point2D> myPath2 = this.traceback(g, s2.getMark(), new Point2D.Double(s2.getX(), s2.getY()), new Point2D.Double((int)(epn.getX() / epn.getSeparator()), (int)(epn.getY() / epn.getSeparator())), e);
			//if(successfulPath != null){
			//System.out.println("size successfulPath: " + successfulPath.size());
			//}
			if(myPath2 != null){
			    successfulPath = new ArrayList<Point2D>(myPath2);
			    System.out.println(" success traceback");
			}else{

			    failedRoutes.add(e);
			}
		    }
		}
	    }
	    if(!successfulPath.isEmpty()){
		//System.out.println(" A ");
		//System.out.println("size successfulPath: " + successfulPath.size());
		//for(Iterator<Point2D> tit = successfulPath.iterator(); tit.hasNext();){
		//System.out.println(" successfulPath: " + tit.next());
		//}

		Point2D sp = new Point2D.Double((int)(e.getSource().getX() / e.getSource().getSeparator()), (int)(e.getSource().getY() / e.getSource().getSeparator()));
		Point2D tp = new Point2D.Double((int)(e.getTarget().getX() / e.getTarget().getSeparator()), (int)(e.getTarget().getY() / e.getTarget().getSeparator()));
		if(!successfulPath.contains(sp)){
		    System.out.println(" path is not complete - combine with paths of edges from the same node");
		    List<Point2D> newpath = new ArrayList<Point2D>();
		    boolean found = false;
		    for(Iterator<PNREdge> eit = edges.iterator(); eit.hasNext();){
			PNREdge e2 = eit.next();
			if(!e2.getRoute().isEmpty()){
			    List<Point2D> seq = new ArrayList<Point2D>(e2.getRoute());
			    newpath.add(seq.remove(0));
			    while(!seq.isEmpty()){
				Point2D p = seq.remove(0);
				if( p.getX() == successfulPath.get(0).getX()){
				    if((Math.min(newpath.get(newpath.size() - 1).getY(), p.getY()) <= successfulPath.get(0).getY() ) && (Math.max(newpath.get(newpath.size() - 1).getY(), p.getY()) >= successfulPath.get(0).getY())){
					found = true;
					break;
				    }
				}
				if( p.getY() == successfulPath.get(0).getY()){
				    if((Math.min(newpath.get(newpath.size() - 1).getX(), p.getX()) <= successfulPath.get(0).getX() ) && (Math.max(newpath.get(newpath.size() - 1).getX(), p.getX()) >= successfulPath.get(0).getX())){
					found = true;
					break;
				    }
				}
				newpath.add(p);
			    }
			
			    if(found){
				break;
			    }
			
			    newpath = new ArrayList<Point2D>();
			}
		    }
		    if(found){
			for(Iterator<Point2D> pit = successfulPath.iterator(); pit.hasNext();){
			    Point2D p = pit.next();
			    newpath.add(p);
			}
			for(Iterator<Point2D> pit = newpath.iterator(); pit.hasNext();){
			    Point2D p = pit.next();
			    this.addRoutingNode((int)p.getX(), (int)p.getY(), e);
			}
		    }else{
			System.out.println("ERROR: failed to combine paths");
		    }

		}else{
		    System.out.println(" path complete - write route to edge");
		    for(Iterator<Point2D> pit = successfulPath.iterator(); pit.hasNext();){
			Point2D p = pit.next();
			this.addRoutingNode((int)p.getX(), (int)p.getY(), e);
		    }
		}
		
		List<Point2D> seqOfCoordinates = new ArrayList<Point2D>(successfulPath);
		//System.out.println("size seqOfCoordinates: " + seqOfCoordinates.size());
		//for(Iterator<Point2D> tit = seqOfCoordinates.iterator(); tit.hasNext();){
		//    System.out.println(" seqOfCoordinates: " + tit.next());
		//}
		Point2D pn = seqOfCoordinates.remove(0);
		while(!seqOfCoordinates.isEmpty()){
		    Point2D nn = seqOfCoordinates.remove(0);
		    for(int x = Math.min((int)pn.getX(), (int)nn.getX()); x < Math.max((int)pn.getX(), (int)nn.getX()) + 1; x = x + 1){
			for(int y = Math.min((int)pn.getY(), (int)nn.getY()); y < Math.max((int)pn.getY(), (int)nn.getY()) + 1; y = y + 1){
			    boolean found2 = false;
			    for(Iterator<Point2D> pit = path.iterator(); pit.hasNext();){
				Point2D myp = pit.next();
				if((myp.getX() == x) && (myp.getY() == y)){
				    found2 = true;
				}
			    }
			    if(!found2){
				path.add(new Point2D.Double(x, y));
			    }
			}
		    }
		    pn = nn;
		}
		
	    }
	}
	return failedRoutes;
    }




    public ArrayList<PNREdge> routeEdgesBackward(PNRGraph g, List<PNREdge> edges, PNRNode en){
	ArrayList<PNREdge> failedRoutes = new ArrayList<PNREdge>();
	List<Point2D> successfulPath = new ArrayList<Point2D>();
	List<Point2D> path = new ArrayList<Point2D>();
	path.add(new Point2D.Double((int)(en.getX() / en.getSeparator()), (int)(en.getY() / en.getSeparator())));
	ArrayList<PNREdge> edgesToRoute = new ArrayList<PNREdge>();
	for(Iterator<PNREdge> eit = edges.iterator(); eit.hasNext();){
	    PNREdge e = eit.next();
	    if(!e.getRoute().isEmpty()){
		List<Point2D> seqOfCoordinates = new ArrayList<Point2D>(e.getRoute());
		Point2D pn =seqOfCoordinates.remove(0);
		while(!seqOfCoordinates.isEmpty()){
		    Point2D nn = seqOfCoordinates.remove(0);
		    for(int x = Math.min((int)pn.getX(), (int)nn.getX()); x < Math.max((int)pn.getX(), (int)nn.getX()) + 1; x = x + 1){
			for(int y = Math.min((int)pn.getY(), (int)nn.getY()); y < Math.max((int)pn.getY(), (int)nn.getY()) + 1; y = y + 1){
			    boolean found = false;
			    for(Iterator<Point2D> pit = path.iterator(); pit.hasNext();){
				Point2D tp = pit.next();
				if((tp.getX() == x) && (tp.getY() == y)){
				    found = true;
				}
			    }
			    if(!found){
				path.add(new Point2D.Double(x, y));
			    }
			}
		    }
		    pn = nn;
		}

	    }else{
		if(!edgesToRoute.contains(e)){
		    edgesToRoute.add(e);
		}
	    }
	}
	while(!edgesToRoute.isEmpty()){
	    PNREdge e = edgesToRoute.remove(0);
	    PNRNode spn = e.getTarget();
	    List<Point2D> sortedCoordinates = new ArrayList<Point2D>(path);
	    PNRReturnStruct s = this.waveExpansion(g, sortedCoordinates, (int)(spn.getX() / spn.getSeparator()), (int)(spn.getY() / spn.getSeparator()), true, e);
	    if(s.getSuccess()){
		List<Point2D> myPath = this.traceback(g, s.getMark(), new Point2D.Double(s.getX(), s.getY()), new Point2D.Double((int)(spn.getX() / spn.getSeparator()), (int)(spn.getY() / spn.getSeparator())), e);
	    
		if(myPath != null){
		    successfulPath = new ArrayList<Point2D>(myPath);
		}else{
		    PNRReturnStruct s2 = this.waveExpansion(g, sortedCoordinates, (int)(spn.getX() / spn.getSeparator()), (int)(spn.getY() / spn.getSeparator()), false, e);
		    if(s2.getSuccess()){
			List<Point2D> myPath2 = this.traceback(g, s2.getMark(), new Point2D.Double(s2.getX(), s2.getY()), new Point2D.Double((int)(spn.getX() / spn.getSeparator()), (int)(spn.getY() / spn.getSeparator())), e);
			if(myPath != null){
			    successfulPath = new ArrayList<Point2D>(myPath);
			}else{
			    failedRoutes.add(e);
			}
		    }
		}
	    }
	    if(!successfulPath.isEmpty()){
		Point2D sp = new Point2D.Double((int)(e.getSource().getX() / e.getSource().getSeparator()), (int)(e.getSource().getY() / e.getSource().getSeparator()));
		Point2D tp = new Point2D.Double((int)(e.getTarget().getX() / e.getTarget().getSeparator()), (int)(e.getTarget().getY() / e.getTarget().getSeparator()));
		if(!successfulPath.contains(tp)){
		    List<Point2D> newpath = new ArrayList<Point2D>();
		    boolean found = false;
		    for(Iterator<PNREdge> eit = edges.iterator(); eit.hasNext();){
			PNREdge e2 = eit.next();
			if(!e2.getRoute().isEmpty()){
			    List<Point2D> seq = new ArrayList<Point2D>(e2.getRoute());
			    newpath.add(seq.remove(0));
			    while(!seq.isEmpty()){
				Point2D p = seq.remove(seq.size() - 1);
				if( p.getX() == successfulPath.get(0).getX()){
				    if((Math.min(newpath.get(0).getY(), p.getY()) <= successfulPath.get(0).getY() ) && (Math.max(newpath.get(0).getY(), p.getY()) >= successfulPath.get(0).getY())){
					found = true;
					break;
				    }
				}
				if( p.getY() == successfulPath.get(0).getY()){
				    if((Math.min(newpath.get(0).getX(), p.getX()) <= successfulPath.get(0).getX() ) && (Math.max(newpath.get(0).getX(), p.getX()) >= successfulPath.get(0).getX())){
					found = true;
					break;
				    }
				}
				newpath.add(p);
			    }
			    if(found){
				break;
			    }
			    newpath = new ArrayList<Point2D>();
			}
		    }
		    if(found){
			for(Iterator<Point2D> pit = successfulPath.iterator(); pit.hasNext();){
			    Point2D p = pit.next();
			    newpath.add(0, p);
			}
			for(Iterator<Point2D> pit = newpath.iterator(); pit.hasNext();){
			    Point2D p = pit.next();
			    this.addRoutingNode((int)p.getX(), (int)p.getY(), e);
			}
		    }else{
			System.out.println("ERROR: failed to combine paths");
		    }
		}else{
		    for(Iterator<Point2D> pit = successfulPath.iterator(); pit.hasNext();){
			Point2D p = pit.next();
			this.addRoutingNode((int)p.getX(), (int)p.getY(), e);
		    }
		}
		
		List<Point2D> seqOfCoordinates = new ArrayList<Point2D>(successfulPath);
		Point2D pn = seqOfCoordinates.remove(seqOfCoordinates.size() - 1);
		while(!seqOfCoordinates.isEmpty()){
		    Point2D nn = seqOfCoordinates.remove(seqOfCoordinates.size() - 1);
		    for(int x = Math.min((int)pn.getX(), (int)nn.getX()); x < Math.max((int)pn.getX(), (int)nn.getX()) + 1; x = x + 1){
			for(int y = Math.min((int)pn.getY(), (int)nn.getY()); y < Math.max((int)pn.getY(), (int)nn.getY()) + 1; y = y + 1){
			    boolean found2 = false;
			    for(Iterator<Point2D> pit = path.iterator(); pit.hasNext();){
				Point2D myp = pit.next();
				if((myp.getX() == x) && (myp.getY() == y)){
				    found2 = true;
				}
			    }
			    if(!found2){
				path.add(new Point2D.Double(x, y));
			    }
			}
		    }
		    pn = nn;
		}
	    }
	}
	return failedRoutes;
    }

    public PNRReturnStruct waveExpansion(PNRGraph g, List<Point2D> path, int startNodeX, int startNodeY, boolean limit, PNREdge e){
	HashMap<Point2D, Integer> mark = new HashMap<Point2D, Integer>();
	List<PNRMarkStruct> nodesToVisit = new ArrayList<PNRMarkStruct>();
	nodesToVisit.add(new PNRMarkStruct(startNodeX, startNodeY, 0));
	int x = 0;
	int y = 0;
	int maxx = 0;
	int maxy = 0;
	for(Iterator<Point2D> git = this.grid.keySet().iterator(); git.hasNext();){
	    Point2D p = git.next();
	    if(p.getX() > maxx){
		maxx = (int)p.getX();
	    }
	    if(p.getY() > maxy){
		maxy = (int)p.getY();
	    }
	}
	int waveDistMax = Math.max(maxx, maxy);
	int myxmin = (int)(g.getParentNode().getX() / g.getParentNode().getSeparator());
	int myxmax = (int)((g.getParentNode().getX() + g.getParentNode().getW())/ g.getParentNode().getSeparator());
	int myymin = (int)(g.getParentNode().getY() / g.getParentNode().getSeparator());
	int myymax = (int)((g.getParentNode().getY() + g.getParentNode().getH())/ g.getParentNode().getSeparator());
	if(limit){
	    for(Iterator<Point2D> pit = path.iterator(); pit.hasNext();){
		Point2D p = pit.next();
		if(myxmin > ((int)p.getX()) - 2){
		    myxmin = ((int)p.getX()) - 2;
		}
		if(myxmax < ((int)p.getX()) + 2){
		    myxmax = ((int)p.getX()) + 2;
		}
		if(myymin > ((int)p.getY()) - 2){
		    myymin = ((int)p.getY()) - 2;
		}
		if(myymax < ((int)p.getY())+ 2){
		    myymax = ((int)p.getY()) + 2;
		}
	    }
	    myxmin = Math.max(myxmin, (int)(g.getParentNode().getX() / g.getParentNode().getSeparator()));
	    myxmax = Math.min(myxmax, (int)((g.getParentNode().getX() + g.getParentNode().getW())/ g.getParentNode().getSeparator()));
	    myymin = Math.max(myymin, (int)(g.getParentNode().getY() / g.getParentNode().getSeparator()));
	    myymax = Math.min(myymax, (int)((g.getParentNode().getY() + g.getParentNode().getH())/ g.getParentNode().getSeparator()));

	}
	int counter = 0;
	int currentWaveDist = 0;
	while(currentWaveDist <= waveDistMax){
	    counter = counter + 1;
	    PNRMarkStruct vertex = nodesToVisit.remove(0);
	    x = vertex.getX();
	    y = vertex.getY();
	    int i = vertex.getDist();
	    currentWaveDist = i;

	    boolean found = false;
	    for(Iterator<Point2D> pit = path.iterator(); pit.hasNext();){
		Point2D p = pit.next();
		if((p.getX() == x) && (p.getY() == y)){
		    found = true;
		    break;
		}
	    }
	    if(found){
		mark.put(new Point2D.Double(x, y), i);
		waveDistMax = i + 1;
		break;
	    }
	    if(!mark.keySet().contains(new Point2D.Double(x, y))){
		mark.put(new Point2D.Double(x, y), i);

		int xmax = myxmax;
		int ymax = myymax;
		// left
		if(x - 1 >= myxmin){
		    if((this.grid.get(new Point2D.Double(x - 1, y)) == 0) ||
		       (this.grid.get(new Point2D.Double(x - 1, y)) == 2) ||
		       (path.contains(new Point2D.Double(x - 1, y)))){
			nodesToVisit.add(new PNRMarkStruct(x - 1, y, i + 1));
		    }
		}
		// right
		if(x + 1 < xmax){
		    if((this.grid.get(new Point2D.Double(x + 1, y)) == 0) ||
		       (this.grid.get(new Point2D.Double(x + 1, y)) == 2) ||
		       (path.contains(new Point2D.Double(x + 1, y)))){
			nodesToVisit.add(new PNRMarkStruct(x + 1, y, i + 1));
		    }
		}
		// up
		if(y - 1 >= myymin){
		    if((this.grid.get(new Point2D.Double(x, y - 1)) == 0) ||
		       (this.grid.get(new Point2D.Double(x, y - 1)) == 1) ||
		       (path.contains(new Point2D.Double(x, y - 1)))){
			nodesToVisit.add(new PNRMarkStruct(x, y - 1, i + 1));
		    }
		}
		// down
		if(y + 1 < ymax){
		    //System.out.println("x " + x + " y " + y);
		    //System.out.println("maxx " + maxx + " maxy " + maxy);
		    if((this.grid.get(new Point2D.Double(x, y + 1)) == 0) ||
		       (this.grid.get(new Point2D.Double(x, y + 1)) == 1) ||
		       (path.contains(new Point2D.Double(x, y + 1)))){
			nodesToVisit.add(new PNRMarkStruct(x, y + 1, i + 1));
		    }
		}

	    }
	    if(nodesToVisit.isEmpty()){
		if(!limit){
		    System.out.println("Warning 2: could not find any path for edge");
		}
		return new PNRReturnStruct(false, mark, x, y);
	    }
	}
	return new PNRReturnStruct(true, mark, x, y);
    }

    public List<Point2D> traceback(PNRGraph g, HashMap<Point2D, Integer> mark, Point2D gVertex, Point2D sVertex, PNREdge e){
	int x = (int)gVertex.getX();
	int y = (int)gVertex.getY();
	List<Point2D> path = new ArrayList<Point2D>();
	path.add(gVertex);
	int origDist = mark.get(gVertex);
	if(!e.getRoute().isEmpty()){
	    System.out.println("ERROR: traceback for edge that already has a route");
	}
	e.clearRoute();
	int oldDirection = 1;
	while(mark.get(new Point2D.Double(x, y)) > 0){
	    boolean stepFound = false;
	    int horiFreeDist = 0;
	    int leftOrRight = 1;
	    if(x < sVertex.getX()){
		leftOrRight = 1;
	    }else{
		leftOrRight = -1;
	    }
	    boolean obstFound = false;
	    int myx = x;
	    while(!obstFound){
		myx += leftOrRight;
		if(myx < 1 + (int)(g.getParentNode().getX() / g.getParentNode().getSeparator())){
		    obstFound = true;
		}
		if(myx > (int)((g.getParentNode().getX() + g.getParentNode().getW()) / g.getParentNode().getSeparator()) - 2){
		    obstFound = true;
		}
		if((this.grid.get(new Point2D.Double(myx, y)) == 0) || (this.grid.get(new Point2D.Double(myx, y)) == 2)){
		    horiFreeDist = horiFreeDist + 1;
		}else{
		    obstFound = true;
		}
	    }
	    int deltaX = x - (int)sVertex.getX();
	    int abs_deltaX = Math.abs(deltaX);
	    int deltaY = y - (int)sVertex.getY();
	    int abs_deltaY = Math.abs(deltaY);
	    boolean horiFirst = true;
	    if(oldDirection == 2){
		horiFirst = false;
	    }
	    if((oldDirection == 1) && (mark.get(new Point2D.Double(x, y)) == (int)(origDist / 2))){
		horiFirst = false;
	    }
	    if(horiFreeDist > 4){
		if((oldDirection == 1) && (mark.get(new Point2D.Double(x, y)) == (int)(abs_deltaX / 2))){
		    horiFirst = false;
		}
		if((oldDirection == 1) && (mark.get(new Point2D.Double(x, y)) == (int)(horiFreeDist / 2))){
		    horiFirst = false;
		}
	    }
	    if(horiFreeDist < 4){
		if (mark.get(new Point2D.Double(x, y)) > horiFreeDist){
		    horiFirst = false;
		}
	    }

	    if(!horiFirst){
		for(int j = Math.min(y, (int)sVertex.getY()); j <= Math.max(y, (int)sVertex.getY()); j = j + 1){
		    for(Iterator<PNRNode> it = g.getNodes().iterator(); it.hasNext();){
			PNRNode n = it.next();
			deltaX = x - n.getX();
			abs_deltaX = Math.abs(deltaX);
			deltaY = y - n.getY();
			abs_deltaY = Math.abs(deltaY);
			if(deltaX >= 0){
			    if(deltaX <= n.getW()){
				if(deltaY >= 0){
				    if( deltaY <= n.getH()){
					horiFirst = true;
				    }
				}
			    }
			}
		    }
		    if(horiFirst){
			break;
		    }
		}
	    }
	    if(abs_deltaX > 3){
		if(mark.get(new Point2D.Double(x, y)) + 2 < origDist){
		    if(abs_deltaY > abs_deltaX){
			horiFirst = false;
		    }
		}   
	    }
	    
	    if(horiFirst){
		//left
		int dx = -1;
		int dy = 0;
		int direction = 1;
		if(!stepFound){
		    PNRTraceStepStruct t = this.tracebackStep(x, y, dx, dy, direction, oldDirection, mark, path, sVertex);
		    stepFound = t.getStepFound();
		    path = t.getPath();
		    oldDirection = t.getOldDirection();
		    x = t.getX();
		    y = t.getY();
		}
		//right
		dx = 1;
		dy = 0;
		direction = 1;
		if(!stepFound){
		    PNRTraceStepStruct t = this.tracebackStep(x, y, dx, dy, direction, oldDirection, mark, path, sVertex);
		    stepFound = t.getStepFound();
		    path = t.getPath();
		    oldDirection = t.getOldDirection();
		    x = t.getX();
		    y = t.getY();
		}
	    }
	    //up
	    int dx = 0;
	    int dy = -1;
	    int direction = 2;
	    if(!stepFound){
		PNRTraceStepStruct t = this.tracebackStep(x, y, dx, dy, direction, oldDirection, mark, path, sVertex);
		stepFound = t.getStepFound();
		path = t.getPath();
		oldDirection = t.getOldDirection();
		x = t.getX();
		y = t.getY();
	    }
	    //down
	    dx = 0;
	    dy = 1;
	    direction = 2;
	    if(!stepFound){
		PNRTraceStepStruct t = this.tracebackStep(x, y, dx, dy, direction, oldDirection, mark, path, sVertex);
		stepFound = t.getStepFound();
		path = t.getPath();
		oldDirection = t.getOldDirection();
		x = t.getX();
		y = t.getY();
	    }
	    //left
	    dx = -1;
	    dy = 0;
	    direction = 1;
	    if(!stepFound){
		PNRTraceStepStruct t = this.tracebackStep(x, y, dx, dy, direction, oldDirection, mark, path, sVertex);
		stepFound = t.getStepFound();
		path = t.getPath();
		oldDirection = t.getOldDirection();
		x = t.getX();
		y = t.getY();
	    }
	    //right
	    dx = 1;
	    dy = 0;
	    direction = 1;
	    if(!stepFound){
		PNRTraceStepStruct t = this.tracebackStep(x, y, dx, dy, direction, oldDirection, mark, path, sVertex);
		stepFound = t.getStepFound();
		path = t.getPath();
		oldDirection = t.getOldDirection();
		x = t.getX();
		y = t.getY();
	    }
	    if(!stepFound){
		System.out.println("ERROR: could not backtrack");
		return null;
	    }
	}
	if(mark.get(new Point2D.Double(x, y)) == 0){
	    this.grid.put(new Point2D.Double(x, y), 3);
	    path.add(new Point2D.Double(x,y));
	    return path;
	}else{
	    System.out.println("ERROR: reached end of traceback without success");
	    return null;
	}
    }

    public PNRTraceStepStruct tracebackStep(int x, int y, int dx, int dy, int direction, int old_direction, HashMap<Point2D, Integer> mark, List<Point2D> path, Point2D vertex){
	boolean stepFound = false;
	Point2D p = new Point2D.Double(x + dx, y + dy);
	if(mark.keySet().contains(p)){
	    if(mark.get(p) < mark.get(new Point2D.Double(x, y))){
		if(direction == 1){
		    if ((this.grid.get(new Point2D.Double(x + dx, y + dy)) == 0) || (this.grid.get(new Point2D.Double(x + dx, y + dy)) == 2) || ((vertex.getX() == x + dx) && (vertex.getY() == y + dy))){
			stepFound = true;
		    }else{
			stepFound = false;
		    }
		}else{
		    if ((this.grid.get(new Point2D.Double(x + dx, y + dy)) == 0) || (this.grid.get(new Point2D.Double(x + dx, y + dy)) == 1) || ((vertex.getX() == x + dx) && (vertex.getY() == y + dy))){
			stepFound = true;
		    }else{
			stepFound = false;
		    }
		}
		if(stepFound){
		    if((vertex.getX() == x + dx) && (vertex.getY() == y + dy)){
			this.grid.put(new Point2D.Double(x + dx, y + dy), 3);
		    }else{
			this.grid.put(new Point2D.Double(x + dx, y + dy), this.grid.get(new Point2D.Double(x + dx, y + dy)) + direction);
		    }
		    if(old_direction != direction){
			this.grid.put(new Point2D.Double(x + dx, y + dy), 3);
			path.add(new Point2D.Double(x, y));
		    }
		    old_direction = direction;
		    x = x + dx;
		    y = y + dy;
		}
	    }
	}
	return new PNRTraceStepStruct(stepFound, path, old_direction, x, y);
    }

    public void addRoutingNode(int x, int y, PNREdge e){
	e.getRoute().add(new Point2D.Double(x, y));
    }
}
