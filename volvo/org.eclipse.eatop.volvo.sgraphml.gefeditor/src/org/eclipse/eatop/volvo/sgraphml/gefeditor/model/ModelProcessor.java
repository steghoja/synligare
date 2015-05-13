package org.eclipse.eatop.volvo.sgraphml.gefeditor.model;

import java.awt.RenderingHints.Key;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.graphdrawing.graphml.xmlns.DataType;
import org.graphdrawing.graphml.xmlns.KeyType;
import org.graphdrawing.graphml.xmlns.EdgeType;
import org.graphdrawing.graphml.xmlns.GraphType;
import org.graphdrawing.graphml.xmlns.GraphmlType;
import org.graphdrawing.graphml.xmlns.NodeType;

import eu.synligare.sgraphml.PolyLineEdgeType;
import eu.synligare.sgraphml.PortNodeType;
import eu.synligare.sgraphml.ResourceBlockType;
import eu.synligare.sgraphml.SgraphmlPackage;

public class ModelProcessor {

	public static ModelProcessor INSTANCE = new ModelProcessor();

	protected GraphmlType theModel;

	public GraphmlType getTheModel() {
		return theModel;
	}


	public void setTheModel(GraphmlType theModel) {
		this.theModel = theModel;
	}

	//ist <FindPolyLineEdgeType> FindConnectionsFrom(ShapeNodeType Source)
	//List <FindPolyLineEdgeType> FindConnectionsTo(ShapeNodeType Target)

	public List<PolyLineEdgeType> FindConnectionsFrom(NodeType source){
		List<PolyLineEdgeType> connections = new ArrayList<PolyLineEdgeType>();
		String sourceId = source.getId();

		EList<EdgeType> edges = theModel.getGraph().get(0).getEdge();		
		for (EdgeType edge : edges){
			String edgeSource = edge.getSource();
			if (edgeSource != null){
				if (edgeSource.equals(sourceId)){
					//Find the Polylineedge element wrapped in one of the data elements
					for (DataType data : edge.getData())
					{
						FeatureMap fm = data.getMixed();
						for (Entry entry : fm) {
							if (entry.getValue() instanceof PolyLineEdgeType){
								connections.add((PolyLineEdgeType)entry.getValue());
								break;
							}
						}
					}
				}
			}
		}
		return connections;
	}

	public List<PolyLineEdgeType> FindConnectionsTo(NodeType dest){
		List<PolyLineEdgeType> connections = new ArrayList<PolyLineEdgeType>();
		String destId = dest.getId();

		EList<EdgeType> edges = theModel.getGraph().get(0).getEdge();		
		for (EdgeType edge : edges){
			String targetEdge = edge.getTarget();
			if (targetEdge != null){
				if (targetEdge.equals(destId)){
					//Find the Polylineedge element wrapped in one of the data elements
					for (DataType data : edge.getData())
					{
						FeatureMap fm = data.getMixed();
						for (Entry entry : fm) {
							if (entry.getValue() instanceof PolyLineEdgeType){
								connections.add((PolyLineEdgeType)entry.getValue());
								break;
							}
						}
					}
				}
			}
		}
		return connections;
	}

	public GraphType getRootGraph(){
		return theModel.getGraph().get(0);		
	}

	protected String getKeyID(String sgraphmlType){
		for (KeyType k : theModel.getKey())
		{
			if (k.getSgraphmlType().equals(sgraphmlType)){
				return k.getId();
			}

		}
	
		
		return null;
	}

	public String getNodeGraphicsKeyID(){
		return getKeyID("nodegraphics");
	}

	public String getEdgeGraphicsKeyID(){
		return getKeyID("edgegraphics");
	}

	public String getResourcesKeyID(){
		return getKeyID("resources");
	}

	/***
	 * finds the root resource block
	 * 
	 */
	public ResourceBlockType getResourceBlock(){

		String resourceID = getResourcesKeyID(); 

		for (DataType data : theModel.getData()) {
			if (data.getKey().equals(resourceID))
			{
				FeatureMap fm = data.getMixed();	

				for (FeatureMap.Entry entry : fm){

					//There could be different stuff in the feature map, look for the ResourceBlockType
					if ((entry.getEStructuralFeature().getFeatureID() == SgraphmlPackage.DOCUMENT_ROOT__RESOURCES) &&
							(entry.getValue() instanceof ResourceBlockType))
					{
						return (ResourceBlockType)entry.getValue();	
					}  
				}
			}

		}
		return null;
	}	

	/***
	 * Slow implementation that traverses the whole model. It would be faster to 
	 * keep a hash map of dotPath vs Node.
	 * 
	 * @param dotPath
	 */
	public NodeType findNode(String dotPath){
		
		for (Iterator iter =  
				EcoreUtil.getAllContents(Collections.singleton(getRootGraph()));
			     iter.hasNext(); ) {
			  EObject eObject = (EObject)iter.next();
			  
			  if (eObject instanceof NodeType){
				  NodeType node = (NodeType)eObject;
				  if (node.getId().equals(dotPath))
					  return node;
			  }
		}
		return null;
	}
	
	public List<NodeType> getAllNodes(){
		
		List<NodeType> nodes = new ArrayList<NodeType>();
		
		for (Iterator iter =  
				EcoreUtil.getAllContents(Collections.singleton(getRootGraph()));
			     iter.hasNext(); ) {
			  EObject eObject = (EObject)iter.next();
			  
			  if (eObject instanceof NodeType){
				  nodes.add((NodeType)eObject);
			  }
		}
		return nodes;
	}

	/***
	 * portNAme = elementName, i.e. shortName or classtype
	 * @param parentGraph
	 * @param portName
	 * @return
	 */

	public PortNodeType findPortNode(GraphType parentGraph, String portDotPath) {

		for (NodeType node : parentGraph.getNode()){
			if (node.getId().equals(portDotPath)) {
				
				FeatureMap fm = node.getData().get(0).getMixed();
				if (fm.getValue(0) instanceof PortNodeType){
					return (PortNodeType)fm.getValue(0);
				}
				return (PortNodeType)node;
			}
		}
		
		return null;
	}

	

	
}
