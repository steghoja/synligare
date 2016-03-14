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
import org.eclipse.eatop.semcon.placeandroute.PNRTraceStepStruct;

import java.awt.geom.Point2D;

class PNRRoute{
	private HashMap<Point2D, Integer> grid;

	public PNRRoute(){
		this.grid = new HashMap<Point2D, Integer>();
	}

	public void finalizeRoute(PNRGraph g){
		int no_op = 1;
		//System.out.println("finished routing");
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
				//System.out.println("here with " + n.getId());
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
			//System.out.println("grid initialized for xMin " + xMin + " xMax " + xMax + " yMin " + yMin + " yMax " + yMax);

		}else{
			for(Iterator<PNRNode> it = g.getNodes().iterator(); it.hasNext();){
				PNRNode n = it.next();
				xMax = Math.max(xMax, n.getX() + n.getW()); 
				yMax = Math.max(yMax, n.getY() + n.getH());
				xMin = Math.min(xMin, n.getX());
				yMin = Math.min(yMin, n.getY());
			}
			
			for(int i = ((int)(xMin / g.getNodes().get(0).getSeparator())); i < ((int)(xMax / g.getNodes().get(0).getSeparator())) + 1; i = i + 1){
				for(int j = ((int)(yMin / g.getNodes().get(0).getSeparator())); j < ((int)(yMax / g.getNodes().get(0).getSeparator())) + 1; j = j + 1){
					this.grid.put(new Point2D.Double(i, j), 0);
				}

			}
			System.out.println("grid initialized for xMin " + xMin + " xMax " + xMax + " yMin " + yMin + " yMax " + yMax);
		}
	}

	public void markTrace(PNRGraph g, PNREdge e){
		//System.out.println("In markTrace for " + e.getId());
		//System.out.println("  source " + e.getSource().getId());
		//System.out.println("  target " + e.getTarget().getId());
		List<Point2D> vertexes = new LinkedList<Point2D>(e.getRoute());
		if(vertexes.size() > 0){
			Point2D pn = vertexes.remove(0);
			while(!vertexes.isEmpty()){
				Point2D nn = vertexes.remove(0);
				for(int x = Math.min((int)pn.getX(), (int)nn.getX()); x < Math.max((int)pn.getX(), (int)nn.getX()) + 1; x = x + 1){
					for(int y = Math.min((int)pn.getY(), (int)nn.getY()); y < Math.max((int)pn.getY(), (int)nn.getY()) + 1; y = y + 1){
						//System.out.println(" x " + x + "   y " + y);
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
		boolean printAllowed = false;
		// find the edges that should be routed inside this graph
		for(Iterator<PNRNode> it = g.getNodes().iterator(); it.hasNext();){
			PNRNode n = it.next();
			for(Iterator<PNREdge> eit = n.getEdges().iterator(); eit.hasNext();){
				PNREdge e = eit.next();
				
				if(e.getId().equals("Structure.systemModel.WiperDesignLevel.FunctionalDesignArchitecture.pWiperMotor_Divided.bWiperMotorLowSpeed_delegate_pElectricMotor_bWiperMotorLowSpeed")){
					System.out.println("In XYrouting");
					printAllowed = true;
				}
				if(!doneEdges.contains(e)){
					// are both source and target in g?
					boolean bothSourceAndTargetInG = true;
					if(g.findChildNode(e.getSource().getId()) == null){
						bothSourceAndTargetInG = false;
					}
					if(g.findChildNode(e.getTarget().getId()) == null){
						bothSourceAndTargetInG = false;	
					}
					if(bothSourceAndTargetInG){
						//System.out.println("Should this edge be routed in this graph " + e.getId());
						// do source and target have different parents?
						//    i.e. is the edge inward into the node?
						PNRNodePair p1 = g.findChildNodeAndSameLevelParent(e.getSource().getId());
						//System.out.println(" " + p1.getSecond().getId());
						PNRNodePair p2 = g.findChildNodeAndSameLevelParent(e.getTarget().getId());
						//System.out.println(" " + p2.getSecond().getId());
						if(p1.getSecond() != p2.getSecond()){
							//System.out.println("-- yes");
							doneEdges.add(e);
							e.clearRoute();
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
							boolean bothSourceAndTargetInG = true;
							if(g.findChildNode(e2.getSource().getId()) == null){
								bothSourceAndTargetInG = false;
							}
							if(g.findChildNode(e2.getTarget().getId()) == null){
								bothSourceAndTargetInG = false;	
							}
							if(bothSourceAndTargetInG){
								//System.out.println("Should this edge be routed in this graph " + e2.getId());
								// do source and target have different parents?
								//    i.e. is the edge inward into the node?
								PNRNodePair p1 = g.findChildNodeAndSameLevelParent(e2.getSource().getId());
								//System.out.println(" " + p1.getSecond().getId());
								PNRNodePair p2 = g.findChildNodeAndSameLevelParent(e2.getTarget().getId());
								//System.out.println(" " + p2.getSecond().getId());
								if(p1.getSecond() != p2.getSecond()){
									//System.out.println("-- yes");
									doneEdges.add(e2);
									e2.clearRoute();
								}
							}
						}
					}
				}
			}
			
		}
		this.markGrid(g);
		List<PNREdge> failedRoutes = this.routeInGeneral(g, doneEdges); //this.routeInOrder(g, doneEdges);
		if(!failedRoutes.isEmpty()){
			System.out.println(" Will retry routing ");
		}
		int counter = 0;
		while(!failedRoutes.isEmpty()){
			counter = counter + 1;
			System.out.println(" + + + retrying " + counter + " + + +");
			for(Iterator<PNREdge> eit = failedRoutes.iterator(); eit.hasNext();){
				PNREdge e = eit.next();
				System.out.println(" * " + e.getId());
			}
			// assembling routes to reroute - not only the failed routes
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
				// clear the space near the source and target terminals
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
					// go down
					// observe - no functionality has been tested on InOut
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
					// go down
					// observe - no functionality has been tested on InOut
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
			// also reroute edges with more than 8 "corners" - can probably be made simpler
			for(Iterator<PNREdge> eit = doneEdges.iterator(); eit.hasNext();){
				PNREdge e = eit.next();
				if(e.getRoute().size() > 8){
					if(!tmpObst.contains(e)){
						tmpObst.add(e);
					}
				}
			}
			// reroute also edges that are more than 3 times the manhattan distance between source and target - can probably be routed simpler
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
			// reroute all edges that share the source node or target node with those that failed
			List<PNREdge> ext = new ArrayList<PNREdge>();
			for(Iterator<PNREdge> eit = uniqFailedRoutes.iterator(); eit.hasNext();){
				PNREdge e = eit.next();
				for(Iterator<PNREdge> sit = e.getSource().getEdges().iterator(); sit.hasNext();){
					PNREdge se = sit.next();
					if(!ext.contains(se)){
						ext.add(se);
					}
				}
				for(Iterator<PNREdge> sit = e.getTarget().getEdges().iterator(); sit.hasNext();){
					PNREdge se = sit.next();
					if(!ext.contains(se)){
						ext.add(se);
					}
				}
			}
			// also for the rest of the list of routes to re-do - reroute also those that share source node or target node
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
			// filter so that we only reroute the edges that are actually within this graph
			List<PNREdge> ext2 = new ArrayList<PNREdge>();
			for(Iterator<PNREdge> eit = ext.iterator(); eit.hasNext();){
				PNREdge e = eit.next();
				if(g.findChildNode(e.getSource().getId()) != null){
					if(g.findChildNode(e.getTarget().getId()) != null){
						ext2.add(e);
					}
				}
			}
			List<PNREdge> full2 = new ArrayList<PNREdge>();
			for(Iterator<PNREdge> eit = full.iterator(); eit.hasNext();){
				PNREdge e = eit.next();
				if(g.findChildNode(e.getSource().getId()) != null){
					if(g.findChildNode(e.getTarget().getId()) != null){
						full2.add(e);
					}
				}
			}
			
			// clear the routes to be rerouted
			for(Iterator<PNREdge> eit = ext2.iterator(); eit.hasNext();){
				PNREdge e = eit.next();
				e.clearRoute();
			}
			for(Iterator<PNREdge> eit = full2.iterator(); eit.hasNext();){
				PNREdge e = eit.next();
				e.clearRoute();
			}
			this.markGrid(g);
			// make sure that the edges that had routing problems in the previous iteration are first in the list
			List<PNREdge> edgesToReroute = new ArrayList<PNREdge>(ext2);
			//    ... before the rest
			for(Iterator<PNREdge> eit = full2.iterator(); eit.hasNext();){
				PNREdge e = eit.next();
				if(!edgesToReroute.contains(e)){
					edgesToReroute.add(e);
				}
			}
			failedRoutes = this.routeInGeneral(g, edgesToReroute);
			//System.out.println("failedRoutes " + failedRoutes.size());
			//System.out.println("counter " + counter);
			if(counter > 10){
				if(!failedRoutes.isEmpty()){
					System.out.println("ERROR: failed routes");
					for(Iterator<PNREdge> eit = failedRoutes.iterator(); eit.hasNext();){
						PNREdge e = eit.next();
						System.out.println(" * " + e.getId());
					}
					return false;
				}else{
					return true;
				}
			}else{
				if(!failedRoutes.isEmpty()){
					System.out.println("Warning: failed routes");
					for(Iterator<PNREdge> eit = failedRoutes.iterator(); eit.hasNext();){
						PNREdge e = eit.next();
						//System.out.println(" * " + e.getId());
					}
					System.out.println(" Will retry routing ");
				}
			}
		}
		return true;
	}

	public ArrayList<PNREdge> routeInGeneral(PNRGraph g, List<PNREdge> edgesToRoute){
		List<Point2D> successful_path = new ArrayList<Point2D>();
		ArrayList<PNREdge> failed_routes = new ArrayList<PNREdge>();
		boolean printAllowed = false;
		for(Iterator<PNREdge> eit = edgesToRoute.iterator(); eit.hasNext();){
			PNREdge e = eit.next();
			PNRNode s = e.getSource();
			// put coordinates along existing routes from source into source_path
			ArrayList<Point2D> source_path = new ArrayList<Point2D>();
			for(Iterator<PNREdge> eit2 = s.getEdges().iterator(); eit2.hasNext();){
				PNREdge se = eit2.next();
				// only consider edges that have been pre-approved
				if(edgesToRoute.contains(se)){
					if(se.getRoute().size() > 0){
						// add coordinates along the route to source_path
						List<Point2D> seqOfCoordinates = new ArrayList<Point2D>(se.getRoute());
						Point2D pn = seqOfCoordinates.remove(0);
						while(!seqOfCoordinates.isEmpty()){
							Point2D nn = seqOfCoordinates.remove(0);
							for(int x = Math.min((int)pn.getX(), (int)nn.getX()); x < Math.max((int)pn.getX(), (int)nn.getX()) + 1; x = x + 1){
								for(int y = Math.min((int)pn.getY(), (int)nn.getY()); y < Math.max((int)pn.getY(), (int)nn.getY()) + 1; y = y + 1){
									Point2D p = new Point2D.Double(x, y);
									if(!source_path.contains(p)){
										source_path.add(p);
									}
								}
							}
							pn = nn;
						}
					}
				}
			}
			if(source_path.isEmpty()){
				source_path.add(new Point2D.Double((int)(s.getX() / s.getSeparator()),
						(int)(s.getY() / s.getSeparator())));
			}
			PNRNode t = e.getTarget();
			ArrayList<Point2D> target_path = new ArrayList<Point2D>();
			for(Iterator<PNREdge> eit2 = t.getEdges().iterator(); eit2.hasNext();){
				PNREdge se = eit2.next();
				// only consider edges that have been pre-approved
				if(edgesToRoute.contains(se)){
					if(se.getRoute().size() > 0){
						// add coordinates along the route to source_path
						List<Point2D> seqOfCoordinates = new ArrayList<Point2D>(se.getRoute());
						Point2D pn = seqOfCoordinates.remove(0);
						while(!seqOfCoordinates.isEmpty()){
							Point2D nn = seqOfCoordinates.remove(0);
							for(int x = Math.min((int)pn.getX(), (int)nn.getX()); x < Math.max((int)pn.getX(), (int)nn.getX()) + 1; x = x + 1){
								for(int y = Math.min((int)pn.getY(), (int)nn.getY()); y < Math.max((int)pn.getY(), (int)nn.getY()) + 1; y = y + 1){
									Point2D p = new Point2D.Double(x, y);
									if(!target_path.contains(p)){
										target_path.add(p);
									}
								}
							}
							pn = nn;
						}
					}
				}
			}
			if(target_path.isEmpty()){
				target_path.add(new Point2D.Double((int)(t.getX() / t.getSeparator()),
						(int)(t.getY() / t.getSeparator())));
			}

			PNRReturnStruct rs = this.general_wave_expansion(g, source_path, target_path, e);
			if(rs.getSuccess()){
				List<Point2D> trace_path = this.general_traceback(g, rs.getMark(), new Point2D.Double(rs.getX(), rs.getY()), e);
				if(trace_path != null){
					if(e.getId().equals("Structure.DesignLevelelements.FunctionalDesignElements.FunctionalDesignArchitecture.pvehicle_model_Vehiclespeed_to_pLDC_VehicleSpeedControl_HMICtrl_v1_WheelBasedVehicleSpeed")){
						printAllowed = true;
						System.out.println(" " + (int)(e.getSource().getX() / e.getSource().getSeparator()) + ":" + (int)(e.getSource().getY() / e.getSource().getSeparator()) + "    " + (int)(e.getTarget().getX() / e.getTarget().getSeparator()) + ":" + (int)(e.getTarget().getY() / e.getTarget().getSeparator()));
						System.out.println("source path ");
						for(Point2D p : source_path){
							System.out.println("\t" + p.getX() + ":" + p.getY());
						}
						System.out.println("trace path");
						for(Point2D p : trace_path){
							System.out.println("\t" + p.getX() + ":" + p.getY());
						}
						System.out.println("target path");
						for(Point2D p : target_path){
							System.out.println("\t" + p.getX() + ":" + p.getY());
						}
					}
					successful_path = trace_path;
				}else{
					failed_routes.add(e);
				}
			}else{
				failed_routes.add(e);
			}
			if(!successful_path.isEmpty()){
				// add missing parts of source_path and target_path
				Point2D sp = new Point2D.Double((int)(e.getSource().getX() / e.getSource().getSeparator()), (int)(e.getSource().getY() / e.getSource().getSeparator()));
				Point2D tp = new Point2D.Double((int)(e.getTarget().getX() / e.getTarget().getSeparator()), (int)(e.getTarget().getY() / e.getTarget().getSeparator()));

				ArrayList<Point2D> partial_path = new ArrayList<Point2D>(successful_path);
				if(!successful_path.contains(sp)){
					
					// add the missing part between successful_path and the source coordinate
					if(printAllowed){
						System.out.println(" joining source path");
					}
					List<Point2D> newpath = new ArrayList<Point2D>();
					boolean found = false;
					for(Iterator<PNREdge> eit2 = s.getEdges().iterator(); eit2.hasNext();){
						PNREdge e2 = eit2.next();
						if(edgesToRoute.contains(e2)){
							if(!e2.getRoute().isEmpty()){
								if(printAllowed){
									System.out.println(" still here");
								}
								List<Point2D> seq = new ArrayList<Point2D>(e2.getRoute());
								newpath.add(seq.remove(0));
								while(!seq.isEmpty()){
									Point2D p = seq.remove(0);
									// assume that the first element of successful_path should be closest to the source
									//     checked, seems to hold up
									if(p.getX() == successful_path.get(0).getX()){
										if((Math.min(newpath.get(newpath.size() - 1).getY(), p.getY()) <= successful_path.get(0).getY()) &&
												(Math.max(newpath.get(newpath.size() - 1).getY(), p.getY()) >= successful_path.get(0).getY())){
											found = true;
											break;
										}
									}
									// assume that the first element of successful_path should be closest to the source
									//     checked, seems to hold up
									if(p.getY() == successful_path.get(0).getY()){
										if((Math.min(newpath.get(newpath.size() - 1).getX(), p.getX()) <= successful_path.get(0).getX()) &&
												(Math.max(newpath.get(newpath.size() - 1).getX(), p.getX()) >= successful_path.get(0).getX())){
											found = true;
											break;
										}
									}
									newpath.add(p);
								}
							}
							if(found){
								break;
							}

							newpath = new ArrayList<Point2D>();
						}
					}
					if(found){
						for(int i = 0; i < newpath.size(); i = i + 1){
							partial_path.add(i, newpath.get(i));
						}
						if(printAllowed){
							System.out.println(" newpath after source path combining");
							for(Point2D p : partial_path){
								System.out.println("\t" + p.getX() + ":" + p.getY());
							}
						}
					}else{
						System.out.println("ERROR: failed to combine paths");
						System.out.println("\t" + e.getId());
					}
					
				}
				if(!successful_path.contains(tp)){

					List<Point2D> newpath = new ArrayList<Point2D>();
					boolean found = false;
					for(Iterator<PNREdge> eit2 = t.getEdges().iterator(); eit2.hasNext();){
						PNREdge e2 = eit2.next();
						if(edgesToRoute.contains(e2)){
							if(!e2.getRoute().isEmpty()){
								List<Point2D> seq = new ArrayList<Point2D>(e2.getRoute());
								newpath.add(0, seq.remove(seq.size() - 1));
								while(!seq.isEmpty()){
									Point2D p = seq.remove(seq.size() - 1);
									// assume that the first element of successful_path should be closest to the source
									//     checked, seems to hold up
									if(p.getX() == successful_path.get(successful_path.size() - 1).getX()){
										if((Math.min(newpath.get(0).getY(), p.getY()) <= successful_path.get(successful_path.size() - 1).getY()) &&
												(Math.max(newpath.get(0).getY(), p.getY()) >= successful_path.get(successful_path.size() - 1).getY())){
											found = true;
											break;
										}
									}
									// assume that the first element of successful_path should be closest to the source
									//     checked, seems to hold up
									if(p.getY() == successful_path.get(successful_path.size() - 1).getY()){
										if((Math.min(newpath.get(0).getX(), p.getX()) <= successful_path.get(successful_path.size() - 1).getX()) &&
												(Math.max(newpath.get(0).getX(), p.getX()) >= successful_path.get(successful_path.size() - 1).getX())){
											found = true;
											break;
										}
									}
									newpath.add(0, p);
								}
							}
							if(found){
								break;
							}

							newpath = new ArrayList<Point2D>();
						}
					}
					if(found){
						for(int i = 0; i < newpath.size(); i = i + 1){
							partial_path.add(newpath.get(i));
						}
						if(printAllowed){
							System.out.println(" newpath after target path combining");
							for(Point2D p : partial_path){
								System.out.println("\t" + p.getX() + ":" + p.getY());
							}
						}
					}else{
						System.out.println("ERROR: failed to combine paths");
						System.out.println("\t" + e.getId());
					}

				}
				for(Iterator<Point2D> pit = partial_path.iterator(); pit.hasNext();){
					Point2D p = pit.next();
					this.addRoutingNode((int)p.getX(), (int)p.getY(), e);
				}
				 
			}else{
				System.out.println("Warning: failed in routeInGeneral to route " + e.getId());
			}
			
		}
		return failed_routes;
	}
	
		
	public PNRReturnStruct general_wave_expansion(PNRGraph g, List<Point2D> source_path, List<Point2D> target_path, PNREdge e){
		HashMap<Point2D, Integer> mark = new HashMap<Point2D, Integer>();
		List<PNRMarkStruct> nodesToVisit = new ArrayList<PNRMarkStruct>();
		for(Iterator<Point2D> pit = target_path.iterator(); pit.hasNext();){
			Point2D p = pit.next();
			nodesToVisit.add(new PNRMarkStruct((int)p.getX(), (int)p.getY(), 0));
		}
		int x = 0;
		int y = 0;
		// find out limits
		int xmin = 1000000;
		int xmax = 0; 
		int ymin = 1000000;
		int ymax = 0;
		if(g.getParentNode() != null){
			xmin = (int)(g.getParentNode().getX() / g.getParentNode().getSeparator());
			xmax = (int)((g.getParentNode().getX() + g.getParentNode().getW())/ g.getParentNode().getSeparator());
			ymin = (int)(g.getParentNode().getY() / g.getParentNode().getSeparator());
			ymax = (int)((g.getParentNode().getY() + g.getParentNode().getH())/ g.getParentNode().getSeparator());
		}else{
			
			for(Iterator<PNRNode> nit = g.getNodes().iterator(); nit.hasNext();){
				PNRNode myn = nit.next();
				int sep = myn.getSeparator();
				if((int)(myn.getX() / sep) < xmin){
					xmin = (int)(myn.getX() / sep);
				}
				if((int)(myn.getY() / sep) < ymin){
					ymin = (int)(myn.getY() / sep);
				}
				if((int)((myn.getX() + myn.getW()) / sep) > xmax){
					xmax = (int)((myn.getX() + myn.getW()) / sep);
				}
				if((int)((myn.getY() + myn.getH()) / sep) > ymax){
					ymax = (int)((myn.getY() + myn.getH()) / sep);
				}
			}
		}
		//System.out.println();
		//System.out.println("In general_wave_expansion with " + e.getId());
		//System.out.println(" limits " + xmin + "-" + xmax + ", " + ymin + "-" + ymax);
		int waveDistMax = 2 * ((xmax - xmin) + (ymax - ymin));
		int counter = 0;
		int currentWaveDist = 0;
		while(currentWaveDist <= waveDistMax){
			counter = counter + 1;
			PNRMarkStruct vertex = nodesToVisit.remove(0);
			x = vertex.getX();
			y = vertex.getY();
			//System.out.print(x + ":" + y + ", ");
			int i = vertex.getDist();
			currentWaveDist = i;
			Point2D p = new Point2D.Double(x, y);
			if(source_path.contains(p)){
				mark.put(p, i);
				break;
			}
			if(!mark.keySet().contains(p)){
				mark.put(p, i);
				// left
				if(x - 1 >= xmin){
					Point2D pl = new Point2D.Double(x - 1, y);
					if((this.grid.get(pl) == 0) ||
						(this.grid.get(pl) == 2) ||
						(source_path.contains(pl))){
						nodesToVisit.add(new PNRMarkStruct(x - 1, y, i + 1));
					}
				}
				// right
				if(x + 1 < xmax){
					Point2D pr = new Point2D.Double(x + 1, y);
					if((this.grid.get(pr) == 0) ||
						(this.grid.get(pr) == 2) ||
						(source_path.contains(pr))){
						nodesToVisit.add(new PNRMarkStruct(x + 1, y, i + 1));
					}
				}
				// up
				if(y - 1 >= ymin){
					Point2D pu = new Point2D.Double(x, y - 1);
					if((this.grid.get(pu) == 0) ||
						(this.grid.get(pu) == 1) ||
						(source_path.contains(pu))){
						nodesToVisit.add(new PNRMarkStruct(x, y - 1, i + 1));
					}
				}
				// down
				if(y + 1 < ymax){
					Point2D pd = new Point2D.Double(x, y + 1);
					if((this.grid.get(pd) == 0) ||
						(this.grid.get(pd) == 1) ||
						(source_path.contains(pd))){
						nodesToVisit.add(new PNRMarkStruct(x, y + 1, i + 1));
					}
				}

			}
			if(nodesToVisit.isEmpty()){
				return new PNRReturnStruct(false, mark, x, y);
			}
		}
		return new PNRReturnStruct(true, mark, x, y);
	}
	
	public ArrayList<Point2D> general_traceback(PNRGraph g, HashMap<Point2D, Integer> mark, Point2D start, PNREdge e){
		List<Point2D> trace_path = new ArrayList<Point2D>();
		int x = (int) start.getX();
		int y = (int) start.getY();
		trace_path.add(start);
		int origDist = mark.get(start);
		e.clearRoute();
		int oldDirection = 1;
		while(mark.get(new Point2D.Double(x, y)) > 0){
			int leftOrRight = 1;
			// calculate how far one can move in a direction without encountering an obstacle
			int horiFreeDist = 0;
			int myx = x;
			while(horiFreeDist < 100){
				myx = myx + leftOrRight;
				if(g.getParentNode() != null){
					if(myx < 1 + (int)(g.getParentNode().getX() / g.getParentNode().getSeparator())){
						break;
					}
					if(myx > (int)((g.getParentNode().getX() + g.getParentNode().getW()) / g.getParentNode().getSeparator()) - 2){
						break;
					}
				
					if((this.grid.get(new Point2D.Double(myx, y)) == 0) || (this.grid.get(new Point2D.Double(myx, y)) == 2)){
						horiFreeDist = horiFreeDist + 1;
					}else{
						break;
					}
				}else{
					int xMax = 0;
					int yMax = 0;
					int xMin = 1000000;
					int yMin = 1000000;
					for(Iterator<PNRNode> it = g.getNodes().iterator(); it.hasNext();){
						PNRNode n = it.next();
						xMax = Math.max(xMax, n.getX() + n.getW()); 
						yMax = Math.max(yMax, n.getY() + n.getH());
						xMin = Math.min(xMin, n.getX());
						yMin = Math.min(yMin, n.getY());
					}
					if(myx < 1 + (int)(xMin / g.getNodes().get(0).getSeparator())){
						break;
					}
					if(myx > (int)(xMax / g.getNodes().get(0).getSeparator()) - 2){
						break;
					}
				
					if((this.grid.get(new Point2D.Double(myx, y)) == 0) || (this.grid.get(new Point2D.Double(myx, y)) == 2)){
						horiFreeDist = horiFreeDist + 1;
					}else{
						break;
					}
				}
			}
			boolean horiFirst = true;
			if(oldDirection == 2){
				horiFirst = false;
			}
			if((oldDirection == 1) && (mark.get(new Point2D.Double(x, y)) == (int)(origDist / 2))){
				horiFirst = false;
			}
			if(horiFreeDist < 4){
				if (mark.get(new Point2D.Double(x, y)) > horiFreeDist){
					horiFirst = false;
				}
			}

			boolean stepFound = false;
			if(horiFirst){
				//left
				int dx = -1;
				int dy = 0;
				int direction = 1;
				if(!stepFound){
					PNRTraceStepStruct t = this.general_tracebackStep(x, y, dx, dy, direction, oldDirection, mark, trace_path);
					stepFound = t.getStepFound();
					trace_path = t.getPath();
					oldDirection = t.getOldDirection();
					x = t.getX();
					y = t.getY();
				}
				//right
				dx = 1;
				dy = 0;
				direction = 1;
				if(!stepFound){
					PNRTraceStepStruct t = this.general_tracebackStep(x, y, dx, dy, direction, oldDirection, mark, trace_path);
					stepFound = t.getStepFound();
					trace_path = t.getPath();
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
				PNRTraceStepStruct t = this.general_tracebackStep(x, y, dx, dy, direction, oldDirection, mark, trace_path);
				stepFound = t.getStepFound();
				trace_path = t.getPath();
				oldDirection = t.getOldDirection();
				x = t.getX();
				y = t.getY();
			}
			//down
			dx = 0;
			dy = 1;
			direction = 2;
			if(!stepFound){
				PNRTraceStepStruct t = this.general_tracebackStep(x, y, dx, dy, direction, oldDirection, mark, trace_path);
				stepFound = t.getStepFound();
				trace_path = t.getPath();
				oldDirection = t.getOldDirection();
				x = t.getX();
				y = t.getY();
			}
			//left
			dx = -1;
			dy = 0;
			direction = 1;
			if(!stepFound){
				PNRTraceStepStruct t = this.general_tracebackStep(x, y, dx, dy, direction, oldDirection, mark, trace_path);
				stepFound = t.getStepFound();
				trace_path = t.getPath();
				oldDirection = t.getOldDirection();
				x = t.getX();
				y = t.getY();
			}
			//right
			dx = 1;
			dy = 0;
			direction = 1;
			if(!stepFound){
				PNRTraceStepStruct t = this.general_tracebackStep(x, y, dx, dy, direction, oldDirection, mark, trace_path);
				stepFound = t.getStepFound();
				trace_path = t.getPath();
				oldDirection = t.getOldDirection();
				x = t.getX();
				y = t.getY();
			}
			if(!stepFound){
				System.out.println("ERROR: could not backtrack");
				System.out.println("\t" + e.getId());
				return null;
			}
		}
		if(mark.get(new Point2D.Double(x, y)) == 0){
			this.grid.put(new Point2D.Double(x, y), 3);
			trace_path.add(new Point2D.Double(x,y));
			return new ArrayList<Point2D>(trace_path);
		}else{
			System.out.println("ERROR: reached end of traceback without success");
			return null;
		}

	}
	
	public PNRTraceStepStruct general_tracebackStep(int x, int y, int dx, int dy, int direction, int old_direction, HashMap<Point2D, Integer> mark, List<Point2D> path){
		boolean stepFound = false;
		Point2D p = new Point2D.Double(x + dx, y + dy);
		if(mark.keySet().contains(p)){
			if(mark.get(p) < mark.get(new Point2D.Double(x, y))){
				if(direction == 1){
					if ((this.grid.get(new Point2D.Double(x + dx, y + dy)) == 0) || (this.grid.get(new Point2D.Double(x + dx, y + dy)) == 2) || (mark.get(p) == 0)){
						stepFound = true;
					}else{
						stepFound = false;
					}
				}else{
					if ((this.grid.get(new Point2D.Double(x + dx, y + dy)) == 0) || (this.grid.get(new Point2D.Double(x + dx, y + dy)) == 1) || (mark.get(p) == 0)){
						stepFound = true;
					}else{
						stepFound = false;
					}
				}
				if(stepFound){
					if(mark.get(p) == 0){
						this.grid.put(p, 3);
					}else{
						this.grid.put(p, this.grid.get(p) + direction);
					}
					if(old_direction != direction){
						this.grid.put(new Point2D.Double(x, y), 3);
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

	public void addRoutingNode(int x, int y, PNREdge e){
		e.getRoute().add(new Point2D.Double(x, y));
	}
}
