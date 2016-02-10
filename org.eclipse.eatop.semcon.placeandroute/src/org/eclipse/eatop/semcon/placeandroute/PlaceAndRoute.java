package org.eclipse.eatop.semcon.placeandroute;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlaceAndRoute{

	/***
	 * 
	 * Main method that performs the actual place and route.
	 * 
	 * @param g0 - the top-level graph that holds the diagram to be fixed.
	 */
	public void doPNR(PNRGraph g0){		
		
		g0.setSeparation();
		
		//Handle all subgraphs on top-level 
		for(Iterator<PNRNode> it2 = g0.getNodes().iterator(); it2.hasNext();){
			PNRNode myn = it2.next();
			if (myn.getGraph() != null){
				PNRGraph myg = myn.getGraph();
				if (myg.getEdgeDefault() == "directed"){

					//Place directed graph
					PNRPlace pl = new PNRPlace();
					System.out.println(" - in applyLeftToRightConcept " + myg.getId());
					pl.preprocessDirected(myg);
					System.out.println(" - - preprocess done ");
					pl.placementDirected(myg);
					System.out.println(" - - placementDirected done");


					pl.moveInputCloserToReciever(myg);
					pl.moveOutputCloserToSender(myg);
					pl.moveInputCloserToReciever(myg);
					pl.moveOutputCloserToSender(myg);
					System.out.println(" - - moved inputs and outputs 1");
					for(int binindex = 0; binindex < pl.getBins().size(); binindex = binindex + 1){
						pl.orderVertically(myg, pl.getBins().get(binindex));
					}
					System.out.println(" - - ordered vertically 1");
					for(int binindex = pl.getBins().size() - 1; binindex > -1; binindex = binindex - 1){
						pl.orderVertically(myg, pl.getBins().get(binindex));
					}
					System.out.println(" - - ordered vertically 2");
					pl.moveInputCloserToReciever(myg);
					pl.moveOutputCloserToSender(myg);
					pl.moveInputCloserToReciever(myg);
					pl.moveOutputCloserToSender(myg);
					System.out.println(" - - moved inputs and outputs 2");

					List<Integer> specialBins = new ArrayList<Integer>();
					for(int i = 0; i < pl.getBins().size(); i = i + 1){
						specialBins.add(i);
					}
					pl.applyForceDirectedLayout(myg, specialBins);
					List<PNRNode> collidingGroupNodes = pl.getGroupNodesWithCollisions(myg);
					int counter = 0;
					while(!collidingGroupNodes.isEmpty()){
						counter = counter + 1;
						pl.applyForceDirectedLayout(myg, specialBins);
						collidingGroupNodes = pl.getGroupNodesWithCollisions(myg);
					}
					pl.finalizePlacement(myg);

					//Route directed
					PNRRoute r = new PNRRoute();
					r.tidyUp(myg);
					r.XYrouting(myg);
					r.finalizeRoute(myg);

				}else{
					//Undirected

					PNRPlace pl = new PNRPlace();
					pl.preprocessUndirected(myg);
					pl.applyForceDirectedLayout(myg, null);
					List<PNRNode> collidingGroupNodes = pl.getGroupNodesWithCollisions(myg);
					int counter = 0;
					while (counter < 200){ //(collidingGroupNodes.size() > 0){
						counter = counter + 1;
						pl.applyForceDirectedLayout(myg, null);
						collidingGroupNodes = pl.getGroupNodesWithCollisions(myg);
					}
					pl.finalizePlacement(myg);

				}
			}
		}


		//"Diagram Input Global Placement"


		//Handle top-level
		PNRPlace pl = new PNRPlace();
		pl.preprocessUndirected(g0);
		pl.applyForceDirectedLayout(g0, null);
		List<PNRNode> collidingGroupNodes = pl.getGroupNodesWithCollisions(g0);
		int counter = 0;
		while (collidingGroupNodes.size() > 0){
			counter = counter + 1;
			pl.applyForceDirectedLayout(g0, null);
			collidingGroupNodes = pl.getGroupNodesWithCollisions(g0);
			System.out.println("colliding nodes");
			for(Iterator<PNRNode> cGN = collidingGroupNodes.iterator(); cGN.hasNext();){
				System.out.println(cGN.next().getId());
			}

		}
		pl.finalizePlacement(g0);


		//Diagram Output Global Placement");
	}

}
