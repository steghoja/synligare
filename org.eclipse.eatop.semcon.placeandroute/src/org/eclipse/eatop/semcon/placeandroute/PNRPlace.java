package org.eclipse.eatop.semcon.placeandroute;

import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.eatop.semcon.placeandroute.PNRGraph;
import org.eclipse.eatop.semcon.placeandroute.PNRNode;
import org.eclipse.eatop.semcon.placeandroute.PNREdge;

import java.awt.geom.Point2D;

class PNRPlace{
	private HashMap<PNRNode, Integer> coulomb_strengths;
	private HashMap<PNRNode, Double> masses;
	private double delta;
	private List<PNRNode> primary_input_blocks;
	private List<PNRNode> primary_output_blocks;
	private ArrayList<ArrayList<PNRNode>> bins;
	private double edgeStiffness;
	private HashMap<PNREdge, Integer> nominal_length;
	private HashMap<PNREdge, PNRNode> sameLevelSource;
	private HashMap<PNREdge, PNRNode> sameLevelTarget;
	private List<PNREdge> edgesToConsider;
	private List<PNRNodePair> countPairOfNodes;

	public PNRPlace(){
		this.delta = 1.0;
		this.edgeStiffness = 0.1;
		this.coulomb_strengths = new HashMap<PNRNode, Integer>();
		this.masses = new HashMap<PNRNode, Double>();
		this.primary_input_blocks = new ArrayList<PNRNode>();
		this.primary_output_blocks = new ArrayList<PNRNode>();
		this.bins = new ArrayList<ArrayList<PNRNode>>();
		this.nominal_length = new HashMap<PNREdge, Integer>();
		this.sameLevelSource = new HashMap<PNREdge, PNRNode>();
		this.sameLevelTarget = new HashMap<PNREdge, PNRNode>();
		this.edgesToConsider = new ArrayList<PNREdge>();
		this.countPairOfNodes = new ArrayList<PNRNodePair>();
	}

	public ArrayList<ArrayList<PNRNode>> getBins(){
		return this.bins;
	}

	public void applyUndirectedLayout(PNRGraph g){
		this.preprocessUndirected(g);
		System.out.println("In applyUndirectedLayout - preprocessed");
		if(g.getNodes().size() > 1){
			this.applyForceDirectedLayout(g, null);
			List<PNRNode> collidingGroupNodes = this.getGroupNodesWithCollisions(g);
			int counter = 0;
			double totalStringStretch = 0;
			for(Iterator<PNREdge> eit = this.edgesToConsider.iterator(); eit.hasNext();){
				PNREdge e = eit.next();
				PNRNode s = e.getSource();
				PNRNode t = e.getTarget();
				//System.out.println(s.getX() + " " + s.getY() + " " + t.getX() + " " + t.getY());
				
				double dist = Math.sqrt((s.getX() - t.getX()) * (s.getX() - t.getX()) + (s.getY() - t.getY()) * (s.getY() - t.getY()));
				double diff = Math.abs(this.nominal_length.get(e) - dist);
				//System.out.println("\t" + dist + " " + diff);
				totalStringStretch = totalStringStretch + diff;
			}
			double previousTotalStringStretch = totalStringStretch;
			System.out.println(" pTSS " + previousTotalStringStretch);

			while ((collidingGroupNodes.size() > 0) || (totalStringStretch > (previousTotalStringStretch / 5))){
				System.out.println(" pTSS " + previousTotalStringStretch + " tSS " + totalStringStretch);
				counter = counter + 1;
				this.applyForceDirectedLayout(g, null);
				collidingGroupNodes = this.getGroupNodesWithCollisions(g);
				if(counter % 100 == 0){
					System.out.println(" number of iterations " + counter);
				}
				if(counter > 5000){
					System.out.println(" giving up undirected placement");
					break;
				}
				for(Iterator<PNREdge> eit = this.edgesToConsider.iterator(); eit.hasNext();){
					PNREdge e = eit.next();
					PNRNode s = e.getSource();
					PNRNode t = e.getTarget();
					double dist = Math.sqrt((s.getX() - t.getX()) * (s.getX() - t.getX()) + (s.getY() - t.getY()) * (s.getY() - t.getY()));
					double diff = Math.abs(this.nominal_length.get(e) - dist);
					totalStringStretch = totalStringStretch + diff;
				}
			}
		}else{
			if(g.getNodes().size() == 1){
				PNRNode n = g.getNodes().get(0);
				if(g.getParentNode() != null){
					if(n.getType() == "GroupNode"){
						n.setXandUpdateContent(g.getParentNode().getX() + 2 * g.getParentNode().getSeparator());
						g.getParentNode().setW(n.getW() + 4 * g.getParentNode().getSeparator());
					}
				}else{
					if(n.getType() == "GroupNode"){
						n.setXandUpdateContent(2 * n.getSeparator());
					}
					
				}
			}
		}
		this.finalizePlacement(g);
	}

	public void applyForceDirectedLayout(PNRGraph g,  List<Integer> specialBins){
		HashMap<PNRNode, Double> globalForceX = new HashMap<PNRNode, Double>();
		HashMap<PNRNode, Double> globalForceY = new HashMap<PNRNode, Double>();
		HashMap<PNRNode, Integer> collisionCount = new HashMap<PNRNode, Integer>();
		int iterations = 100;
		while(iterations > 0){
			iterations = iterations - 1;
			globalForceX.clear();
			globalForceY.clear();
			collisionCount.clear();
			for(Iterator<PNRNode> it = g.getNodes().iterator(); it.hasNext();){
				PNRNode n1 = it.next();
				for(Iterator<PNRNode> it2 = g.getNodes().iterator(); it2.hasNext();){
					PNRNode n2 = it2.next();
					if (n1 != n2){
						if ((n1.getType() == "GroupNode") && (n2.getType() == "GroupNode")){
							double cs;
							if(!n1.areAtAcceptableDistance(n2)){
								//System.out.println(" colliding " + n1.getId() + " " + n2.getId());
								if(collisionCount.keySet().contains(n1)){
									collisionCount.put(n1, collisionCount.get(n1) + 1);
								}else{
									collisionCount.put(n1, 1);
								}
								if(collisionCount.keySet().contains(n2)){  
									collisionCount.put(n2, collisionCount.get(n2) + 1);
								}else{
									collisionCount.put(n2, 1);
								}
								cs = (this.coulomb_strengths.get(n1) + this.coulomb_strengths.get(n2)) * 2;
								if(this.masses.get(n1) > this.masses.get(n2)){
									this.coulomb_strengths.put(n1, this.coulomb_strengths.get(n1) + 1000);
								}else{
									this.coulomb_strengths.put(n2, this.coulomb_strengths.get(n1) + 1000);
								}
								//System.out.println(" cs " + this.coulomb_strengths.get(n1) + " " + this.coulomb_strengths.get(n2));
							}else{
								cs = (this.coulomb_strengths.get(n1) + this.coulomb_strengths.get(n2)) / 2;
							}
							double distance = n1.midPointDistance(n2);
							if (distance < 2.0){
								n1.setYandUpdateContent(n1.getY() + 2);
								if(g.getParentNode() != null){
									if(n1.getY() + n1.getH() > g.getParentNode().getY() + g.getParentNode().getH() - n1.getSeparator()){
										n1.setYandUpdateContent(n1.getY() -3);
									}
								}
							}
							distance = n1.midPointDistance(n2);
							int deltaX = n1.deltaX(n2);
							int deltaY = n1.deltaY(n2);
							if(!globalForceX.keySet().contains(n1)){
								globalForceX.put(n1, 0.0);
								globalForceY.put(n1, 0.0);
							}
							if(!globalForceX.keySet().contains(n2)){
								globalForceX.put(n2, 0.0);
								globalForceY.put(n2, 0.0);
							}
							double dividerN1 = this.masses.get(n1) / (double)(this.masses.get(n1) + this.masses.get(n2));
							globalForceX.put(n1, globalForceX.get(n1) + dividerN1 * cs / (distance * distance) * (deltaX / distance));
							globalForceY.put(n1, globalForceY.get(n1) + dividerN1 * cs / (distance * distance) * (deltaY / distance));
							globalForceX.put(n2, globalForceX.get(n2) + (1 - dividerN1) * cs / (distance * distance) * (-deltaX / distance));
							globalForceY.put(n2, globalForceY.get(n2) + (1 - dividerN1) * cs / (distance * distance) * (-deltaY / distance));
						}
					}
				}
			}
		}
		for(Iterator<PNRNode> it = collisionCount.keySet().iterator(); it.hasNext();){
			PNRNode n = it.next();
			if(collisionCount.get(n) == 0){
				this.coulomb_strengths.put(n, this.coulomb_strengths.get(n) - 250);
			}
		}


		for(Iterator<PNREdge> it = this.edgesToConsider.iterator(); it.hasNext();){
			PNREdge e = it.next();
			PNRNode source = e.getSource();
			PNRNode target = e.getTarget();
			PNRNode thisLevelSource = this.sameLevelSource.get(e);
			PNRNode thisLevelTarget = this.sameLevelTarget.get(e);
			if(g.getNodes().contains(thisLevelSource)){
				if(g.getNodes().contains(thisLevelTarget)){
					
					double nominal_length;
					if (!thisLevelSource.areAtAcceptableDistance(thisLevelTarget)){
						nominal_length = (int)this.nominal_length.get(e) * 1.5;
					}else{
						nominal_length = this.nominal_length.get(e);
					}
					if (specialBins != null){
						int s = 0;
						while(s < this.bins.size()){
							if(this.bins.get(s).contains(thisLevelSource)){
								break;
							}
							s = s + 1;
						}
						int t = 0;
						while(t < this.bins.size()){
							if(this.bins.get(t).contains(thisLevelTarget)){
								break;
							}
							t = t + 1;
						}
						if (Math.abs(s - t) > 1){
							nominal_length = nominal_length * 2;
						}
					}
					int deltaX = source.deltaX(target);
					int deltaY = source.deltaY(target);
					double spring_length = (double)source.midPointDistance(target) + 0.0001;
					double dividerSource = 0.5;
					if ((thisLevelSource.getType() == "GroupNode") && (thisLevelTarget.getType() == "GroupNode")){
						dividerSource = this.masses.get(thisLevelSource) / (double)(this.masses.get(thisLevelSource) + this.masses.get(thisLevelTarget));
					}
					int k = 1;
					for(Iterator<PNRNodePair> it4 = this.countPairOfNodes.iterator(); it4.hasNext();){
						PNRNodePair np = it4.next();
						if ((np.getFirst() == thisLevelSource) && (np.getSecond() == thisLevelTarget)){
							k = np.getOccurrence();
							break;
						}
					}
					if (thisLevelSource.getType() == "GroupNode"){
						//System.out.println(" edge id " + e.getId());
						//System.out.println(" thisLevelSource id " + thisLevelSource.getId());
						globalForceX.put(thisLevelSource, globalForceX.get(thisLevelSource) + dividerSource * (-this.edgeStiffness * (spring_length - nominal_length) * (deltaX / spring_length)) / k);
						globalForceY.put(thisLevelSource, globalForceY.get(thisLevelSource) + dividerSource * (-this.edgeStiffness * (spring_length - nominal_length) * (deltaY / spring_length)) / k);
					}
					if (thisLevelTarget.getType() == "GroupNode"){
						globalForceX.put(thisLevelTarget, globalForceX.get(thisLevelTarget) + (1 - dividerSource) * (-this.edgeStiffness * (spring_length - nominal_length) * (-deltaX / spring_length)) / k);
						globalForceY.put(thisLevelTarget, globalForceY.get(thisLevelTarget) + (1 - dividerSource) * (-this.edgeStiffness * (spring_length - nominal_length) * (-deltaY / spring_length)) / k);
					}
				}
			}
		}

		if (globalForceX.size() > 0){
			for(Iterator<PNRNode> it = g.getNodes().iterator(); it.hasNext();){
				PNRNode n = it.next();
				if (specialBins != null){
					if (specialBins.size() > 0){
						boolean found = false;
						for(int i = 0; i < specialBins.size(); i = i + 1){
							if (this.bins.get(i).contains(n)){
								found = true;
							}
						}
						if (found){
							double movey = this.delta * globalForceY.get(n);
							if (movey > 3 * n.getH()){
								movey = 3 * n.getH();
							}
							if (movey < -3 * n.getH()){
								movey = -3 * n.getH();
							}
							if (g.getParentNode() != null){
								if (n.getY() + movey < g.getParentNode().getY() + 2 * n.getSeparator()){
									n.setYandUpdateContent(g.getParentNode().getY() + 2 * n.getSeparator());
								}else if (n.getY() + n.getH() + movey > g.getParentNode().getY() + g.getParentNode().getH() - 2 * n.getSeparator()){
									n.setYandUpdateContent(g.getParentNode().getY() + g.getParentNode().getH() - n.getH() - 2 * n.getSeparator());
								}else{
									n.setYandUpdateContent(n.getY() + (int)movey);
								}
							}else{
								n.setYandUpdateContent(n.getY() + (int)movey);
							}
						}
					}
				}else{
					double movex = this.delta * globalForceX.get(n);
					if (movex > 3 * n.getW()){
						movex = 3 * n.getW();
					}
					if (movex < -3 * n.getW()){
						movex = -3 * n.getW();
					}
					if (g.getParentNode() != null){
						if (n.getX() + movex < g.getParentNode().getX() + 4 * n.getSeparator()){
							n.setXandUpdateContent(g.getParentNode().getX() + 4 * n.getSeparator());
						}else if (n.getX() + n.getW() + movex > g.getParentNode().getX() + g.getParentNode().getW() - 4 * n.getSeparator()){
							n.setXandUpdateContent(g.getParentNode().getX() + g.getParentNode().getW() - n.getW() - 4 * n.getSeparator());
						}else{
							n.setXandUpdateContent(n.getX() + (int)movex);
						}
					}else{
						n.setXandUpdateContent(n.getX() + (int)movex);
					}
					double movey = this.delta * globalForceY.get(n);
					if (movey > 3 * n.getH()){
						movey = 3 * n.getH();
					}
					if (movey < -3 * n.getH()){
						movey = -3 * n.getH();
					}
					if (g.getParentNode() != null){
						if (n.getY() + movey < g.getParentNode().getY() + 2 * n.getSeparator()){
							n.setYandUpdateContent(g.getParentNode().getY() + 2 * n.getSeparator());
						}else if (n.getY() + n.getH() + movey > g.getParentNode().getY() + g.getParentNode().getH() - 2 * n.getSeparator()){
							n.setYandUpdateContent(g.getParentNode().getY() + g.getParentNode().getH() - n.getH() - 2 * n.getSeparator());
						}else{
							n.setYandUpdateContent(n.getY() + (int)movey);
						}
					}else{
						n.setYandUpdateContent(n.getY() + (int)movey);
					}

				}
			}
		}

	}

	public List<PNRNode> getGroupNodesWithCollisions(PNRGraph g){
		List<PNRNode> groupNodesWithCollisions = new ArrayList<PNRNode>();
		for(Iterator<PNRNode> it = g.getNodes().iterator(); it.hasNext();){
			PNRNode n1 = it.next();
			for(Iterator<PNRNode> it2 = g.getNodes().iterator(); it2.hasNext();){
				PNRNode n2 = it2.next();
				if((n1.getType() == "GroupNode") && (n2.getType() == "GroupNode")){
					if (n1 != n2){
						if(!n1.areAtAcceptableDistance(n2)){
							if(!groupNodesWithCollisions.contains(n1)){
								groupNodesWithCollisions.add(n1);
							}
							if(!groupNodesWithCollisions.contains(n2)){
								groupNodesWithCollisions.add(n2);
							}
						}
					}
				}
			}
		}
		return groupNodesWithCollisions;
	}

	public void finalizePlacement(PNRGraph g){
		int minx = 0;
		int miny = 0;
		for(Iterator<PNRNode> it = g.getNodes().iterator(); it.hasNext();){
			PNRNode n = it.next();
			if (n.getX() < minx){
				minx = n.getX();
			}
			if (n.getY() < miny){
				miny = n.getY();
			}
		}
		if (minx < 0){
			for(Iterator<PNRNode> it = g.getNodes().iterator(); it.hasNext();){
				PNRNode n = it.next();
				n.setXandUpdateContent(n.getX() - minx);
			}
		}
		if (miny < 0){
			for(Iterator<PNRNode> it = g.getNodes().iterator(); it.hasNext();){
				PNRNode n = it.next();
				n.setYandUpdateContent(n.getY() - miny);
			}
		}

		List<PNREdge> doneEdges = new ArrayList<PNREdge>();
		Queue<PNRNode> queue = new LinkedList<PNRNode>(g.getNodes());
		while(!queue.isEmpty()){
			PNRNode n = queue.remove();
			n.setOrigX(n.getX());
			n.setOrigY(n.getY());
			for(Iterator<PNREdge> it = n.getEdges().iterator(); it.hasNext();){
				PNREdge e = it.next();
				if (!doneEdges.contains(e)){
					doneEdges.add(e);
					if (e.getRoute().size() > 0){
						Iterator<Point2D> pit = e.getRoute().iterator();
						Point2D firstpoint = pit.next();
						int movex = (int)firstpoint.getX() - (int)(e.getSource().getX() / n.getSeparator());
						int movey = (int)firstpoint.getY() - (int)(e.getSource().getY() / n.getSeparator());
						for(Iterator<Point2D> pit2 = e.getRoute().iterator(); pit2.hasNext();){
							Point2D p = pit2.next();
							p.setLocation(p.getX() - movex, p.getY() - movey);
						}
					}
				}
			}
			if(n.getGraph() != null){
				for(Iterator<PNRNode> it2 = n.getGraph().getNodes().iterator(); it2.hasNext();){
					queue.add(it2.next());
				}
			}
		}
		if (g.getParentNode() != null){
			if (g.getNodes().size() > 0){
				int maxy = 0;
				for(Iterator<PNRNode> it3 = g.getNodes().iterator(); it3.hasNext();){
					PNRNode n = it3.next();
					if (n.getY() + n.getH() > maxy){
						maxy = n.getY() + n.getH();
					}
				}
				int newy = maxy - g.getParentNode().getY() + 4 * g.getParentNode().getSeparator();
				g.getParentNode().setH(newy);
			}
		}
		//System.out.println(" - placement finished");
	}

	public void preprocessUndirected(PNRGraph g){
		this.assignMassesToGroupNodes(g);
		List<PNREdge> startingSetOfEdges = new ArrayList<PNREdge>();
		Queue<PNRNode> queue = new LinkedList<PNRNode>(g.getNodes());
		while(!queue.isEmpty()){
			PNRNode n = queue.remove();
			for(Iterator<PNREdge> it = n.getEdges().iterator(); it.hasNext();){
				PNREdge e = it.next();
				for(Iterator<PNRNode> it2 = g.getNodes().iterator(); it2.hasNext();){
					PNRNode n2 = it2.next();
					if(n2 == e.getTarget()){
						if(!startingSetOfEdges.contains(e)){
							startingSetOfEdges.add(e);
						}
					}else if(n2 == e.getSource()){
						if(!startingSetOfEdges.contains(e)){
							startingSetOfEdges.add(e);
						}
					}
				}
			}
			if(n.getGraph() != null){
				for(Iterator<PNRNode> it3 = n.getGraph().getNodes().iterator(); it3.hasNext();){
					queue.add(it3.next());
				}
			}
		}
		for(Iterator<PNREdge> it = startingSetOfEdges.iterator(); it.hasNext();){
			PNREdge e = it.next();
			this.prepareEdgesToConsider(g, e);
		}
		for(Iterator<PNRNode> it = g.getNodes().iterator(); it.hasNext();){
			PNRNode n = it.next();
			for(Iterator<PNREdge> it2 = n.getEdges().iterator(); it2.hasNext();){
				PNREdge e = it2.next();
				this.prepareEdgesToConsider(g, e);
			}
			if(n.getGraph() != null){
				for(Iterator<PNRNode> it2 = n.getGraph().getNodes().iterator(); it2.hasNext();){
					PNRNode n2 = it2.next();
					if(n2.getType() != "GroupNode"){
						for(Iterator<PNREdge> it3 = n2.getEdges().iterator(); it3.hasNext();){
							PNREdge e = it3.next();
							this.prepareEdgesToConsider(g, e);

						}
					}
				}
			}
		}

		for(Iterator<PNREdge> it = this.edgesToConsider.iterator(); it.hasNext();){
			PNREdge e = it.next();
			//System.out.println("e " + e.getId());
			PNRNode sLSource = this.sameLevelSource.get(e);
			PNRNode sLTarget = this.sameLevelTarget.get(e);
			if(g.getNodes().contains(sLSource)){
				if(g.getNodes().contains(sLTarget)){
					
					// search pairsOfNodes for this pair
					boolean found = false;
					for(Iterator<PNRNodePair> it2 = this.countPairOfNodes.iterator(); it2.hasNext();){
						PNRNodePair p = it2.next();
						if ((p.getFirst() == sLSource) && (p.getSecond() == sLTarget)){
							p.setOccurrence(p.getOccurrence() + 1);
							found = true;
							break;
						}
					}
					if (!found) {
						this.countPairOfNodes.add(new PNRNodePair(sLSource, sLTarget));
					}
				}
			}

		}
	}

	public void prepareEdgesToConsider(PNRGraph g, PNREdge e){

		// write directly to members sameLevelSource, sameLevelTarget, nominal_length
		PNRNodePair foundNode = g.findChildNodeAndSameLevelParent(e.getTarget().getId());
		PNRNode sLTarget;
		PNRNode sLSource;
		if (foundNode != null){
			//System.out.println("A " + e.getId());
			PNRNode target = foundNode.getFirst();
			sLTarget = foundNode.getSecond();
			double targetDistToPeriferi =
					Math.min(
						Math.min(
							Math.abs(sLTarget.getX() - (target.getX() + target.getW() / 2)),
							Math.abs(sLTarget.getX() + sLTarget.getW() - (target.getX() + target.getW() / 2))),
						Math.min(
							Math.abs(sLTarget.getY() - (target.getY() + target.getH() / 2)),
							Math.abs(sLTarget.getY() + sLTarget.getH() - (target.getY() + target.getH() / 2))));

			PNRNodePair foundNode2 = g.findChildNodeAndSameLevelParent(e.getSource().getId());
			if (foundNode2 != null){
				//System.out.println("B " + target.getId() + " " + sLTarget.getId());
				PNRNode source = foundNode2.getFirst();
				sLSource = foundNode2.getSecond();
				double sourceDistToPeriferi =
						Math.min(
								Math.min(
										Math.abs(sLSource.getX() - (source.getX() + source.getW() / 2)),
										Math.abs(sLSource.getX() + sLSource.getW() - (source.getX() + source.getW() / 2))),
								Math.min(
										Math.abs(sLSource.getY() - (source.getY() + source.getH() / 2)),
										Math.abs(sLSource.getY() + sLSource.getH() - (source.getY() + source.getH() / 2))));
				//System.out.println("C " + source.getId() + " " + sLSource.getId());
				if (!this.edgesToConsider.contains(e)){
					this.edgesToConsider.add(e);
					this.nominal_length.put(e, (int)(1.0 * (sourceDistToPeriferi + targetDistToPeriferi) + 50)); // 1.5
					if (sLSource == null){
						System.out.println("Encountered sLSource being null for edge " + e.getId() + " and source " + e.getSource().getId());
					}
					this.sameLevelSource.put(e, sLSource);
					if (sLTarget == null){
						System.out.println("Encountered sLTarget being null for edge " + e.getId() + " and target " + e.getTarget().getId());
					}
					this.sameLevelTarget.put(e, sLTarget);
				}
			}
		}
	}

	public void assignMassesToGroupNodes(PNRGraph g){
		for(Iterator<PNRNode> it = g.getNodes().iterator(); it.hasNext();){
			PNRNode n = it.next();
			if (n.getType() == "GroupNode"){
				double mass = (double)Math.sqrt((double)n.getH() * n.getH() + n.getW() * n.getW());
				this.masses.put(n, mass);
				this.coulomb_strengths.put(n, (int)(70000 * (mass / 130)));
			}
		}
	}


	public void preprocessDirected(PNRGraph g){
		for(Iterator<PNRNode> it = g.getNodes().iterator(); it.hasNext();){
			PNRNode n = it.next();
			if (n.getType() == "GroupNode"){
				if (n.getGraph().getNodes().size() == 1){
					List<PNRNode> ln = n.getGraph().getNodes();
					Iterator<PNRNode> it2 = ln.iterator();
					PNRNode n2 = it2.next();
					if (n2.getType() == "In"){
						this.primary_output_blocks.add(n);
					}
					if (n2.getType() == "Out"){
						this.primary_input_blocks.add(n);
					}
				}
			}
		}
		// the rest of the function is preparing for force directed layout - if no force directed layout is to be done, consider removing
		for(Iterator<PNRNode> it = g.getNodes().iterator(); it.hasNext();){
			PNRNode n = it.next();
			if (n.getType() != "GroupNode"){
				// n should be a port node on the periphery of the graph
				for(Iterator<PNREdge> eit = n.getEdges().iterator(); eit.hasNext();){
					PNREdge e = eit.next();
					PNRNode other_port = null;
					if(e.getSource() == n){
						other_port = e.getTarget();
					}else{
						other_port = e.getSource();
					}
					if (g.getNodes().contains(other_port)){
						int nom_len = (int)(0.5 * Math.sqrt(n.getW() * n.getW() + n.getH() * n.getH()) + 0.5 * Math.sqrt(other_port.getW() * other_port.getW() + other_port.getH() * other_port.getH()));
						this.edgesToConsider.add(e);
						this.sameLevelSource.put(e, n);
						this.sameLevelTarget.put(e, other_port);
						this.nominal_length.put(e, nom_len);
					}else{
						if(other_port.getParentGraph() != null){
							if(other_port.getParentGraph().getParentNode() != null){
								if(g.getNodes().contains(other_port.getParentGraph().getParentNode())){
									PNRNode port = other_port.getParentGraph().getParentNode();
									int nom_len = (int)(0.5 * Math.sqrt(n.getW() * n.getW() + n.getH() * n.getH()) + 0.5 * Math.sqrt(port.getW() * port.getW() + port.getH() * port.getH()));
									this.edgesToConsider.add(e);
									this.sameLevelSource.put(e, n);
									this.sameLevelTarget.put(e, port);
									this.nominal_length.put(e, nom_len);
								}
							}
						}
					}
				}
			}
			if(n.getGraph() != null){
				for(Iterator<PNRNode> it2 = n.getGraph().getNodes().iterator(); it2.hasNext();){
					PNRNode n2 = it2.next();
					if(n2.getType() != "GroupNode"){
						for(Iterator<PNREdge> eit = n2.getEdges().iterator(); eit.hasNext();){
							PNREdge e = eit.next();
							PNRNode other_port = null;
							if(e.getSource() == n2){
								other_port = e.getTarget();
							}else{
								other_port = e.getSource();
							}
							if(g.getNodes().contains(other_port)){
								int nom_len = (int)(0.5 * Math.sqrt(n.getW() * n.getW() + n.getH() * n.getH()) + 0.5 * Math.sqrt(other_port.getW() * other_port.getW() + other_port.getH() * other_port.getH()));
								this.edgesToConsider.add(e);
								this.sameLevelSource.put(e, n);
								this.sameLevelTarget.put(e, other_port);
								this.nominal_length.put(e, nom_len);
							}else{
								if(other_port.getParentGraph() != null){
									if(other_port.getParentGraph().getParentNode() != null){
										if(g.getNodes().contains(other_port.getParentGraph().getParentNode())){
											PNRNode port = other_port.getParentGraph().getParentNode();
											int nom_len = (int)(0.5 * Math.sqrt(n.getW() * n.getW() + n.getH() * n.getH()) + 0.5 * Math.sqrt(port.getW() * port.getW() + port.getH() * port.getH()));
											this.edgesToConsider.add(e);
											this.sameLevelSource.put(e, n);
											this.sameLevelTarget.put(e, port);
											this.nominal_length.put(e, nom_len);
											
										}
									}
								}
							}
						}
					}
				}
			}
		}
		this.assignMassesToGroupNodes(g);
		for(Iterator<PNREdge> it = this.edgesToConsider.iterator(); it.hasNext();){
			PNREdge e = it.next();
			//System.out.println("e " + e.getId());
			PNRNode sLSource = this.sameLevelSource.get(e);
			PNRNode sLTarget = this.sameLevelTarget.get(e);
			// search pairsOfNodes for this pair
			boolean found = false;
			for(Iterator<PNRNodePair> it2 = this.countPairOfNodes.iterator(); it2.hasNext();){
				PNRNodePair p = it2.next();
				if ((p.getFirst() == sLSource) && (p.getSecond() == sLTarget)){
					p.setOccurrence(p.getOccurrence() + 1);
					found = true;
					break;
				}
			}
			if (!found) {
				this.countPairOfNodes.add(new PNRNodePair(sLSource, sLTarget));
			}
		}
	}

	public void placementDirected(PNRGraph g){
		
		List<PNRNode> initialQueue = new ArrayList<PNRNode>();
		List<PNRNode> my_group_nodes = new ArrayList<PNRNode>();
		for(Iterator<PNRNode> it = g.getNodes().iterator(); it.hasNext();){
			PNRNode n = it.next();
			if(n.getType() == "GroupNode"){
				boolean inputsSatisfied = true;
				// find edges with source in a node directly on g and target in a node directly in n
				my_group_nodes.add(n);
				if(n.getGraph() != null){
					for(Iterator<PNRNode> it2 = n.getGraph().getNodes().iterator(); it2.hasNext();){
						PNRNode n2 = it2.next();
						if(n2.getType() == "In"){
							for(Iterator<PNREdge> eit = n2.getEdges().iterator(); eit.hasNext();){
								PNREdge e = eit.next();
								boolean connectionWithGraphLevelPort = false;
								if(g.getNodes().contains(e.getTarget())){
									connectionWithGraphLevelPort = true;
								}
								if(g.getNodes().contains(e.getSource())){
									connectionWithGraphLevelPort = true;
								}
								if(!connectionWithGraphLevelPort){
									inputsSatisfied = false;
									//System.out.println("inputs not satisfied " + n.getId());
									break;
								}else{
									int no_op = 1;
									//System.out.println("input satisfied by graph level port " + n2.getId());
								}
							}
						}
					}
				}
				boolean foundNoInputs = true;
				if(n.getGraph() != null){
					for(Iterator<PNRNode> it2 = n.getGraph().getNodes().iterator(); it2.hasNext();){
						PNRNode n2 = it2.next();
						// is there a port marked as "In"?
						if(n2.getType() == "In"){	
							foundNoInputs = false;
						}else{
							// ok, to be really sure, are there any edges that have the port as target?
							for(Iterator<PNREdge> eit = n2.getEdges().iterator(); eit.hasNext();){
								PNREdge e = eit.next();
								if(n2 == e.getTarget()){
									foundNoInputs = false;
								}
							}
						}
					}
				}
				if(foundNoInputs){
					//System.out.println("For initialQueue: found no inputs for " + n.getId());
					if(!initialQueue.contains(n)){
						initialQueue.add(n);
					}
				}

				if (inputsSatisfied){
					if(!initialQueue.contains(n)){
						initialQueue.add(n);
					}
				}
			}
		}
		List<PNRNode> visitedGroupNodes = new ArrayList<PNRNode>();
		HashMap<PNRNode, Integer> visitedNodeDistance = new HashMap<PNRNode, Integer>();
		//Queue<PNRNodeIntegerPair> queue = new LinkedList<PNRNodeIntegerPair>();
		Queue<PNRNode> queue = new LinkedList<PNRNode>();//this.primary_input_blocks);
		while(!initialQueue.isEmpty()){
			PNRNode item = initialQueue.remove(0);
			System.out.println("from initialQueue " + item.getId());
			queue.add(item);
		}
		int bindistance = 20;
		while(!my_group_nodes.isEmpty()){
			if(queue.isEmpty()){
				// there are still nodes to place, but none have their predecessors placed yet - it is a feedback loop

				// - select a node that has at least one output - otherwise it cannot "break" the feedback loop
				List<PNRNode> candidates = new ArrayList<PNRNode>();
				int fLTRindex = 0;
				PNRNode n = my_group_nodes.get(fLTRindex);
				while(this.primary_output_blocks.contains(n)){
					fLTRindex = fLTRindex + 1;
					if (fLTRindex == my_group_nodes.size()){
						System.out.println("ERROR: all remaining group nodes are primary outputs");
						break;
					}
					n = my_group_nodes.get(fLTRindex);
				}
				for(Iterator<PNRNode> cnit = my_group_nodes.iterator(); cnit.hasNext();){
					PNRNode cn = cnit.next();
					if(!this.primary_input_blocks.contains(n)){
						if(cn.getGraph() != null){
							candidates.add(cn);
						}
					}
				}
				
				List<Double> ioratio = new ArrayList<Double>();
				
				for(int cit = 0; cit < candidates.size(); cit = cit + 1){
					PNRNode c = candidates.get(cit);
					int inputs = 0;
					int outputs = 0;
					if(c.getGraph() != null){
						for(Iterator<PNRNode> cnit = c.getGraph().getNodes().iterator(); cnit.hasNext();){
							PNRNode cn = cnit.next();
							if(cn.getType() == "In"){
								if(!cn.getEdges().isEmpty()){
									inputs = inputs + 1;
								}
							}
							if(cn.getType() == "Out"){
								if(!cn.getEdges().isEmpty()){
									outputs = outputs + 1;	
								}
							}
						}
						ioratio.add(inputs / (double)outputs);
					}else{
						ioratio.add((double)10);
					}
				}
				int lowestioratioindex = 0;
				for(int cit = 0; cit < ioratio.size(); cit = cit + 1){
					if(ioratio.get(lowestioratioindex) > ioratio.get(cit)){
						lowestioratioindex = cit;
					}
				}
				n = candidates.get(lowestioratioindex);
				

				// where to place this node?
				int maxdistance = -1;
				//-int minimumX = 0;
				//  consider the nodes that it is connected to
				if(n.getGraph() != null){
					for(Iterator<PNRNode> it = n.getGraph().getNodes().iterator(); it.hasNext();){
						//  n2 is likely to be a port 
						PNRNode n2 = it.next();
						for(Iterator<PNREdge> eit = n2.getEdges().iterator(); eit.hasNext();){
							//  e is an edge from a port of the node that is to be placed
							PNREdge e = eit.next();
							if(e.getTarget() == n2){
								//  the node that is to be placed should be to the right of other_port
								PNRNode other_port = e.getSource();
								if (g.getNodes().contains(other_port)){
									//  the node that is to be placed is preceded by a port on the subgraph's periphery (left hand side)
									int no_op = 1;
								}else if(other_port.getParentGraph() != null){
									//  the node that is to be placed is preceded by another node
									//  find out the other node's bin, if it is already placed
									if(other_port.getParentGraph().getParentNode() != null){
										PNRNode other_parent_node = other_port.getParentGraph().getParentNode();
										if(other_parent_node != n){
											if (visitedGroupNodes.contains(other_parent_node)){
												//  the other node has already been placed - the node that is to be placed should be on the right of all such nodes
												if(visitedNodeDistance.get(other_parent_node) > maxdistance){
													maxdistance = visitedNodeDistance.get(other_parent_node); 
												}
											}
										}
									}else{
										System.out.println("ERROR: could not get the parent of a port while placing a directed graph");
									}
								}
							}else if(e.getSource() == n2){
								// as we are placing a node, nodes that are dependent on this node may become next to be placed
								PNRNode other_port = e.getTarget();
								if(g.getNodes().contains(other_port)){
									//  the node to be placed precedes directly a port on the periphery of the subgraph (right hand side)
									int no_op = 1;
								}else if(other_port.getParentGraph() != null){
									//  nodes that has at least one input satisfied are enqueued
									if(other_port.getParentGraph().getParentNode() != null){
										PNRNode other_parent_node = other_port.getParentGraph().getParentNode();
										if (other_parent_node.getType() == "GroupNode"){
											if(other_parent_node != n){
												queue.add(other_parent_node);//new PNRNodeIntegerPair(other_parent_node, maxdistance + 1));
											}
										}else{
											System.out.println("Warning: port nodes in placementDirected");
										}
									}else{
										System.out.println("ERROR: could not get the parent of a port while placing a directed graph");
									}
								}
							}
						}
					}
				}
				System.out.println(" resolved cycle by adding " + n.getId());
				visitedGroupNodes.add(n);
				my_group_nodes.remove(n);
				visitedNodeDistance.put(n, maxdistance + 1);
			}
			while(!queue.isEmpty()){
				// take the next node from the queue to see if it can be placed
				
				PNRNode n = queue.remove();
				if(my_group_nodes.contains(n)){
					if(!visitedGroupNodes.contains(n)){
						System.out.println("attempting to place " + n.getId());
						int maxdistance = -1;
						List<PNRNode> toEnqueue = new ArrayList<PNRNode>();
						// only nodes that have all their inputs satisfied should be placed, the others have to wait longer in the queue
						//    while looking at if the inputs are satisfied, find out how far right the node needs to go if it can be placed (recorded in maxdistance) 
						boolean inputsSatisfied = true;
						if(n.getGraph() != null){
							// consider all the inputs to see if they are satisfied
							for(Iterator<PNRNode> it2 = n.getGraph().getNodes().iterator(); it2.hasNext();){
								PNRNode n2 = it2.next();
								if(n2.getType() == "In"){
									for(Iterator<PNREdge> eit = n2.getEdges().iterator(); eit.hasNext();){
										PNREdge e = eit.next();
										PNRNode other_port = e.getSource(); // when connected on graph level, it is not necessarily that source and target are correct
										if(n2 == e.getSource()){
											other_port = e.getTarget();
										}
										if(g.getNodes().contains(other_port)){
											//System.out.println("# one input is connected on graph level " + n2.getId());
											// this port (n2) is satisfied by a port on the periphery of the subgraph (left hand side)
											int no_op = 1;
										}else if(other_port.getParentGraph() != null){
											if(other_port.getParentGraph().getParentNode() != null){
												PNRNode other_parent_node = other_port.getParentGraph().getParentNode();
												if (!visitedGroupNodes.contains(other_parent_node)){
													// this port is not satisfied
													inputsSatisfied = false;
												}else{
													// this port (n2) is satisfied by an edge from a node that is already placed
													//    find out how far right the node needs to go if all other inputs are also satisfied (maxdistance)
													if(visitedNodeDistance.get(other_parent_node) >= maxdistance){
														maxdistance = visitedNodeDistance.get(other_parent_node);
													}
												}
											}else{
												System.out.println("ERROR: could not get the parent of a port while placing a directed graph");
											}
										}
									}
								}else if(n2.getType() == "Out"){
									// while finding out if the inputs are satisfied to decide if the node can be placed,
									//      find out what dependencies there are, that could be enqueued if the node is placed
									for(Iterator<PNREdge> eit = n2.getEdges().iterator(); eit.hasNext();){
										PNREdge e = eit.next();
										PNRNode other_port = e.getTarget();
										if(n2 == e.getTarget()){
											other_port = e.getSource();
										}
										if (g.getNodes().contains(other_port)){
											int no_op = 1;
										}else if(other_port.getParentGraph() != null){
											if(other_port.getParentGraph().getParentNode() != null){
												PNRNode other_parent_node = other_port.getParentGraph().getParentNode();
												if(other_parent_node.getType() == "GroupNode"){
													//System.out.println("#     to enqueue " + other_parent_node.getId());
													if(other_parent_node != n){
														toEnqueue.add(other_parent_node);
													}
												}else{
													System.out.println("Warning: port node in placementDirected");
												}
											}else{
												System.out.println("ERROR: could not get the parent of a port while placing a directed graph");
											}
										}
									}
								}
							}
						}
						if(inputsSatisfied){
							//System.out.println("#   had satisfied inputs " + n.getId());
							// place the node
							visitedGroupNodes.add(n);
							// set the "bin number"
							//System.out.println("#   placed on maxdistance " + (maxdistance + 1));
							visitedNodeDistance.put(n, maxdistance + 1);
							while(my_group_nodes.contains(n)){
								my_group_nodes.remove(n);
							}
							while(queue.contains(n)){
								queue.remove(n);
							}
							// enqueue nodes that are directly dependent on the placed node - preliminary position is to the right of the placed node
							for(Iterator<PNRNode> it3 = toEnqueue.iterator(); it3.hasNext();){
								PNRNode nn = it3.next();
								if(!queue.contains(nn)){
									queue.add(nn);
								}
							}
						}
					}
				}
			}
		}
		// find out how many bins to make
		if(visitedNodeDistance.values().size() > 0){
			// find out how many bins to make
			int maxbinindex = 0;
			for(Iterator<Integer> it = visitedNodeDistance.values().iterator(); it.hasNext();){
				int i = it.next();
				if (i > maxbinindex){
					maxbinindex = i;
				}
			}
			// make the bins
			while(this.bins.size() < maxbinindex + 1){
				this.bins.add(new ArrayList<PNRNode>());
			}


			// place the nodes in the bins
			for(Iterator<PNRNode> it = visitedGroupNodes.iterator(); it.hasNext();){
				PNRNode n = it.next();
				System.out.println(n.getId() + "\t: " + visitedNodeDistance.get(n));
				this.bins.get(visitedNodeDistance.get(n)).add(n);
			}

			// find out the width of each bin
			ArrayList<Integer> width_per_bin = new ArrayList<Integer>();
			Iterator<ArrayList<PNRNode>> binit = this.bins.iterator();
			while(binit.hasNext()){
				ArrayList<PNRNode> bin = binit.next();
				int width_this_bin = 0;
				for(Iterator<PNRNode> nit = bin.iterator(); nit.hasNext();){
					PNRNode n = nit.next();
					if(n.getW() > width_this_bin){
						width_this_bin = n.getW();
					}
				}
				width_per_bin.add(width_this_bin);
			}

			// set the horizontal coordinates of the nodes
			int xoffset = 0;
			if(g.getParentNode() != null){
				xoffset = (bindistance / 2) * g.getParentNode().getSeparator() + g.getParentNode().getX();
			}else{
				xoffset = (bindistance / 2) * g.getNodes().get(0).getSeparator();
			}
			int bin_index = 0;
			while(bin_index < this.bins.size()){
				ArrayList<PNRNode> bin = this.bins.get(bin_index);
				int width_this_bin = width_per_bin.get(bin_index);
				for(Iterator<PNRNode> nit = bin.iterator(); nit.hasNext();){
					PNRNode n = nit.next();
					n.setXandUpdateContent(xoffset);
				}
				xoffset = xoffset + width_this_bin + bindistance * g.getNodes().get(0).getSeparator();
				bin_index = bin_index + 1;
			}
			xoffset = xoffset - (bindistance / 2) * g.getNodes().get(0).getSeparator();

			// set initial vertical position of the nodes
			int binindex = 0;
			int largestYvalue = 0;
			int vDist = 8;
			while(binindex < this.bins.size()){
				int gnindex = 0;
				int verticalSpacing = 0;
				/*if (g.getParentNode() != null){
					int sumOfHeights = 0;
					for(Iterator<PNRNode> it2 = this.bins.get(binindex).iterator(); it2.hasNext();){
						sumOfHeights = sumOfHeights + it2.next().getH();
					}
					verticalSpacing = (int)(g.getParentNode().getH() - sumOfHeights) / (this.bins.get(binindex).size() + 1);
				}else{*/
				Iterator<PNRNode> mit = g.getNodes().iterator();
				verticalSpacing = vDist * mit.next().getSeparator();
				/*}
				if(g.getParentNode() != null){
					verticalSpacing = Math.max(verticalSpacing, vDist * g.getParentNode().getSeparator());
				}else{
					Iterator<PNRNode> mit = g.getNodes().iterator();
					verticalSpacing = Math.max(verticalSpacing, vDist * mit.next().getSeparator());
				}
				*/
				int yoffset = verticalSpacing;
				if(g.getParentNode() != null){
					yoffset = verticalSpacing + g.getParentNode().getY();
				}
				while(gnindex < this.bins.get(binindex).size()){
					PNRNode n = this.bins.get(binindex).get(gnindex);
					n.setYandUpdateContent(yoffset);
					yoffset = yoffset + verticalSpacing + n.getH();
					if (yoffset > largestYvalue){
						largestYvalue = yoffset;
					}
					gnindex = gnindex + 1;
				}
				binindex = binindex + 1;
			}
			if(!this.bins.get(0).isEmpty()){
				if(g.getParentNode() != null){
					if(largestYvalue > g.getParentNode().getY() + g.getParentNode().getH()){
						g.getParentNode().setH(largestYvalue - g.getParentNode().getY());
					}
					if(xoffset > g.getParentNode().getX() + g.getParentNode().getW()){
						g.getParentNode().setWandUpdateContent(xoffset - g.getParentNode().getX());
					}
				}
			}


		}else{
			
			System.out.println("Warning: no nodes have been placed in bins in directed placement");
			//System.out.println("\t" + g.getId());
			this.bins.add(new ArrayList<PNRNode>());
		}

	}

	public double averageCloseGroupNodeEdgeLengths(PNRGraph g, PNRNode n){
		int mybinindex = 0;
		for(int i = 0; i < this.bins.size(); i = i + 1){
			if(this.bins.get(i).contains(n)){
				mybinindex = i;
				break;
			}
		}
		double distance = 0.0;
		int edgecounter = 0;
		if(n.getGraph() != null){
			for(Iterator<PNRNode> it = n.getGraph().getNodes().iterator(); it.hasNext();){
				PNRNode n2 = it.next();
				if (n2.getType() != "GroupNode"){
					for(Iterator<PNREdge> eit = n2.getEdges().iterator(); eit.hasNext();){
						PNREdge e= eit.next();
						PNRNode other_port = e.getSource();
						if (e.getSource() == n2){
							other_port = e.getTarget();
						}
						if (other_port.getParentGraph() != null){
							if(other_port.getParentGraph().getParentNode() != null){
								PNRNode other_parent = other_port.getParentGraph().getParentNode();
								boolean done = false;
								if(mybinindex + 1 < this.bins.size()){
									if(this.bins.get(mybinindex + 1).contains(other_parent)){
										distance = distance + n.midPointDistance(other_parent);
										edgecounter = edgecounter + 1;
										done = true;
									}
								}
								if(mybinindex - 1 >= 0){
									if(this.bins.get(mybinindex - 1).contains(other_parent)){
										distance = distance + n.midPointDistance(other_parent);
										edgecounter = edgecounter + 1;
										done = true;
									}
								}
								if(!done){
									distance = distance + 0.25 * n.midPointDistance(other_parent);
									edgecounter = edgecounter + 1;
									done = true;
								}
							}else if (other_port.getParentGraph() == g){
								if(mybinindex == 0){
									if(other_port.getType() == "In"){
										distance = distance + n.midPointDistance(other_port);
										edgecounter = edgecounter + 1;
									}
								}
								if(this.bins.get(mybinindex) == this.bins.get(this.bins.size() - 1)){
									if(other_port.getType() == "Out"){
										distance = distance + n.midPointDistance(other_port);
										edgecounter = edgecounter + 1;
									}
								}
							}
						}
					}
				}
			}
		}
		if(edgecounter > 0){
			return distance / (double)edgecounter;
		}
		return 0.0;
	}

	public void orderVertically(PNRGraph g, ArrayList<PNRNode> bin, int height){
		List<PNRNode> mybin = new ArrayList<PNRNode>(bin);
		int top = 0;
		int sep = 0;
		if(g.getParentNode() != null){
			top = g.getParentNode().getY();
			sep = g.getParentNode().getSeparator();
		}else{
			sep = g.getNodes().get(0).getSeparator();
		}
		int y = top + sep;
		HashMap<PNRNode, Double> currentDistances = new HashMap<PNRNode, Double>();
		for(Iterator<PNRNode> it = mybin.iterator(); it.hasNext();){
			PNRNode n = it.next();
			int origY = n.getY();
			n.setY(y);
			double myaveg = this.averageCloseGroupNodeEdgeLengths(g, n);
			n.setY(origY);
			currentDistances.put(n, myaveg);	
		}
		HashMap<PNRNode, Double> previousDistances = new HashMap<PNRNode, Double>(currentDistances);
		int minimumY = top + sep;
		while(y < top + height){
			y = y + sep;
			for(Iterator<PNRNode> it = mybin.iterator(); it.hasNext();){
				PNRNode n = it.next();
				int origY = n.getY();
				n.setY(y);
				double myaveg = this.averageCloseGroupNodeEdgeLengths(g, n);
				n.setY(origY);
				currentDistances.put(n, myaveg);
			}
			List<PNRNode> nodesToPlace = new ArrayList<PNRNode>();
			for(Iterator<PNRNode> it = currentDistances.keySet().iterator(); it.hasNext();){
				PNRNode n = it.next();
				if(currentDistances.get(n) > previousDistances.get(n)){
					nodesToPlace.add(n);
				}
			}
			previousDistances = new HashMap<PNRNode, Double>(currentDistances);
			int sumOfHeights = 0;
			for(Iterator<PNRNode> it2 = nodesToPlace.iterator(); it2.hasNext();){
				sumOfHeights = sumOfHeights + it2.next().getH();
			}

			int compareValue = y - (sumOfHeights + nodesToPlace.size() * 8 * sep) / 2;
			int placementY = Math.max(minimumY, compareValue);
			// skipping this python line sortedNodesToPlace = sorted(nodesToPlace, key= lambda item: currentDistances[item])
			for(Iterator<PNRNode> it = nodesToPlace.iterator(); it.hasNext();){
				PNRNode n = it.next();
				n.setYandUpdateContent(placementY);
				placementY = placementY + 8 * sep + n.getH();
				mybin.remove(n);
			}
			minimumY = placementY;
		}
		int lowestY = 100000;
		for(Iterator<PNRNode> it = g.getNodes().iterator(); it.hasNext();){
			PNRNode n = it.next();
			if (n.getY() < lowestY){
				lowestY = n.getY();
			}
		}
		int moveBy = lowestY - top - sep * 2;
		for(Iterator<PNRNode> it = g.getNodes().iterator(); it.hasNext();){
			PNRNode n = it.next();
			if(n.getType() == "GroupNode"){
				n.setYandUpdateContent(n.getY() - moveBy);
			}
		}
	}
	

	public void moveInputCloserToReciever(PNRGraph g){
		List<PNRNode> inputNodes = this.primary_input_blocks;
		for(Iterator<PNRNode> it = inputNodes.iterator(); it.hasNext();){
			PNRNode inputNode = it.next();
			PNRNode reciever;
			boolean candidate = true;
			if (inputNode.getGraph() != null){
				if(inputNode.getGraph().getNodes().size() > 1){
					System.out.println("Warning: it is not expected that primary inputs have more than one port. This primary input is not moved. " + inputNode.getId());
					candidate = false;
				}
				if(inputNode.getGraph().getNodes().size() > 0){
					if(inputNode.getGraph().getNodes().get(0).getEdges().size() > 1){
						System.out.println("Warning: it is not expected that a port on a primary input has more than one edge. This primary input is not moved. " + inputNode.getId());
						candidate = false;
					}
					if(inputNode.getGraph().getNodes().get(0).getEdges().size() == 0){
						System.out.println("ERROR: it is expected that a primary input block should have at least one edge");
						candidate = false;
					}
				}else{
					System.out.println("ERROR: expected node " + inputNode.getId() + " to have a child node");
					candidate = false;
				}
			}else{
				System.out.println("ERROR: expected node " + inputNode.getId() + " to have a graph");
				candidate = false;
			}
			if (candidate){
				PNREdge e = inputNode.getGraph().getNodes().get(0).getEdges().get(0);
				if (e.getSource().getParentGraph() != null){
					if (e.getSource().getParentGraph().getParentNode() != inputNode){
						System.out.println("ERROR: this group node only has a single output port, but it is not the source of its edges " + inputNode.getId());
						System.out.println("\t" + e.getId());
						System.out.println("\t" + e.getSource().getId());
						System.out.println("\t" + e.getTarget().getId());
						candidate = false;
					}
				}else{
					System.out.println("ERROR: expected the source " + e.getSource().getId() + " of edge " + e.getId() + " to have a parent");
					candidate = false;
				}
			
				if (candidate){
					int recieverbinindex = 0;
					if (e.getTarget().getParentGraph() != null){
						if(e.getTarget().getParentGraph().getParentNode() != null){
							reciever = e.getTarget().getParentGraph().getParentNode();
							while(recieverbinindex < this.bins.size()){
								if(this.bins.get(recieverbinindex).contains(reciever)){
									break;
								}
								recieverbinindex = recieverbinindex + 1;
							}
						}else{
							System.out.println("ERROR: expected the target " + e.getTarget().getId() + " of edge " + e.getId() + " to have a parent");
						}
					}else{
						System.out.println("ERROR: expected the target " + e.getTarget().getId() + " of edge " + e.getId() + " to have a parent");
					}

					int inputbinindex = 0;
					while(inputbinindex < this.bins.size()){
						if(this.bins.get(inputbinindex).contains(inputNode)){
							break;
						}
						inputbinindex = inputbinindex + 1;
					}
					int originalx = inputNode.getX();
					boolean moved = false;
					if(recieverbinindex > inputbinindex){
						for(int i = recieverbinindex - 1; i > inputbinindex - 1; i = i - 1){
							List<PNRNode> thisBin = this.bins.get(i);
							int xForThisBin = (int)(thisBin.get(0).getX() + thisBin.get(0).getW() / 2);
							inputNode.setXandUpdateContent((int)(xForThisBin - inputNode.getW() / 2));
							boolean noCollision = true;
							for(Iterator<PNRNode> it4 = thisBin.iterator(); it4.hasNext();){
								PNRNode thisBinNode = it4.next();
								if(thisBinNode != inputNode){
									if(inputNode.areColliding(thisBinNode)){
										noCollision = false;
										break;
									}
								}
							}
							if(noCollision){
								this.bins.get(inputbinindex).remove(inputNode);
								this.bins.get(i).add(inputNode);
								if(this.bins.get(i).isEmpty()){
									this.bins.remove(i);
								}
								moved = true;
								break;
							}
						}
					}
					if(!moved){
						inputNode.setXandUpdateContent(originalx);
					}
				}
			}
		}
	}




	public void moveOutputCloserToSender(PNRGraph g){
		List<PNRNode> outputNodes = this.primary_output_blocks;
		for(Iterator<PNRNode> it = outputNodes.iterator(); it.hasNext();){
			PNRNode outputNode = it.next();
			PNRNode sender;
			boolean candidate = true;
			if (outputNode.getGraph() != null){
				if(outputNode.getGraph().getNodes().size() > 1){
					System.out.println("Warning: it is not expected that primary outputs have more than one port. This primary output is not moved. " + outputNode.getId());
					candidate = false;
				}
				if(outputNode.getGraph().getNodes().size() > 0){
					if(outputNode.getGraph().getNodes().get(0).getEdges().size() > 1){
						System.out.println("Warning: it is not expected that a port on a primary output has more than one edge. This primary output is not moved. " + outputNode.getId());
						candidate = false;
					}
					if(outputNode.getGraph().getNodes().get(0).getEdges().size() == 0){
						System.out.println("ERROR: it is expected that a primary output block should have at least one edge");
						candidate = false;
					}
				}else{
					System.out.println("ERROR: expected node " + outputNode.getId() + " to have a child node");
					candidate = false;
				}
			}else{
				System.out.println("ERROR: expected node " + outputNode.getId() + " to have a graph");
				candidate = false;
			}
			if(candidate){
				PNREdge e = outputNode.getGraph().getNodes().get(0).getEdges().get(0);
				if (e.getTarget().getParentGraph() != null){
					if (e.getTarget().getParentGraph().getParentNode() != outputNode){
						System.out.println("ERROR: this group node only has a single output port, but it is not the target of its edges " + outputNode.getId());
						System.out.println("\t" + e.getId());
						System.out.println("\t" + e.getSource().getId());
						System.out.println("\t" + e.getTarget().getId());
						candidate = false;
					}
				}else{
					System.out.println("ERROR: expected the target " + e.getTarget().getId() + " of edge " + e.getId() + " to have a parent");
					candidate = false;
				}
				if (candidate){
					int senderbinindex = 0;
					if (e.getSource().getParentGraph() != null){
						if(e.getSource().getParentGraph().getParentNode() != null){
							sender = e.getSource().getParentGraph().getParentNode();
							while(senderbinindex < this.bins.size()){
								if(this.bins.get(senderbinindex).contains(sender)){
									break;
								}
								senderbinindex = senderbinindex + 1;
							}
						}else{
							System.out.println("ERROR: expected the target " + e.getTarget().getId() + " of edge " + e.getId() + " to have a parent");
						}
					}else{
						System.out.println("ERROR: expected the target " + e.getTarget().getId() + " of edge " + e.getId() + " to have a parent");
					}

					int outputbinindex = 0;
					while(outputbinindex < this.bins.size()){
						if(this.bins.get(outputbinindex).contains(outputNode)){
							break;
						}
						outputbinindex = outputbinindex + 1;
					}
					int originalx = outputNode.getX();
					boolean moved = false;
					if(senderbinindex < outputbinindex){
						for(int i = senderbinindex + 1; i < outputbinindex + 1; i = i + 1){
							List<PNRNode> thisBin = this.bins.get(i);
							int xForThisBin = (int)(thisBin.get(0).getX() + thisBin.get(0).getW() / 2);
							outputNode.setXandUpdateContent((int)(xForThisBin - outputNode.getW() / 2));
							boolean noCollision = true;
							for(Iterator<PNRNode> it4 = thisBin.iterator(); it4.hasNext();){
								PNRNode thisBinNode = it4.next();
								if(thisBinNode != outputNode){
									if(outputNode.areColliding(thisBinNode)){
										noCollision = false;
										break;
									}
								}
							}
							if(noCollision){
								this.bins.get(outputbinindex).remove(outputNode);
								this.bins.get(i).add(outputNode);
								if(this.bins.get(i).isEmpty()){
									this.bins.remove(i);
								}
								moved = true;
								break;
							}
						}
					}
					if(!moved){
						outputNode.setXandUpdateContent(originalx);
					}
				}
			}
		}
	}

	public void applyLeftToRightConcept(PNRGraph g){
		System.out.println(" - in applyLeftToRightConcept " + g.getId());
		this.preprocessDirected(g);
		//System.out.println(" - - preprocess done ");
		this.placementDirected(g);
		//System.out.println(" - - placementDirected done");
		this.moveInputCloserToReciever(g);
		this.moveOutputCloserToSender(g);
		this.moveInputCloserToReciever(g);
		this.moveOutputCloserToSender(g);
		int top = 1000000000;
		int height = 0;
		for(int binindex = 0; binindex < this.bins.size(); binindex = binindex + 1){
			for(int gnindex = 0; gnindex < this.bins.get(binindex).size(); gnindex = gnindex + 1){
				PNRNode n = this.bins.get(binindex).get(gnindex);
				if(height < n.getY() + n.getH()){
					height = n.getY() + n.getH();
				}
				if(top > n.getY()){
					top = n.getY();
				}
			}
		}
		height = height - top;
		//System.out.println(" - - moved inputs and outputs 1");
		for(int binindex = 0; binindex < this.bins.size(); binindex = binindex + 1){
			this.orderVertically(g, this.bins.get(binindex), height);
		}
		//System.out.println(" - - ordered vertically 1");
		for(int binindex = this.bins.size() - 1; binindex > -1; binindex = binindex - 1){
			this.orderVertically(g, this.bins.get(binindex), height);
		}
		//System.out.println(" - - ordered vertically 2");		
		
		//this.moveInputCloserToReciever(g);
		//this.moveOutputCloserToSender(g);
		//this.moveInputCloserToReciever(g);
		//this.moveOutputCloserToSender(g);
		//System.out.println(" - - moved inputs and outputs 2");

		
		//		List<Integer> specialBins = new ArrayList<Integer>();
		//		for(int i = 0; i < this.bins.size(); i = i + 1){
		//			specialBins.add(i);
		//		}
		//		this.applyForceDirectedLayout(g, specialBins);
		//		List<PNRNode> collidingGroupNodes = this.getGroupNodesWithCollisions(g);
		//		int counter = 0;
		//		while(counter < 200){ //!collidingGroupNodes.isEmpty()){
		//			counter = counter + 1;
		//			this.applyForceDirectedLayout(g, specialBins);
		//			collidingGroupNodes = this.getGroupNodesWithCollisions(g);
		//
		//		}
		this.finalizePlacement(g);
	}

}
