package org.eclipse.eatop.volvo.sgraphml.gefeditor.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.controller.ShapeNodeEditPart.ShapeNodeTypeAdapter;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.model.ModelProcessor;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.policy.CreateAttributeEditPolicy;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.policy.GroupNodeXYLayoutPolicy;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.policy.NodeTypeComponentEditPolicy;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.CutRectangleGroupNodeFigure;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.EllipseGroupNodeFigure;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.GroupNodeFigure;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.RectangleGroupNodeFigure;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.RoundRectangleGroupNodeFigure;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.ShapeNodeFigure;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.gef.CompoundSnapToHelper;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.SnapFeedbackPolicy;
import org.eclipse.gef.tools.MarqueeDragTracker;
import org.eclipse.gef.tools.MarqueeSelectionTool;
import org.graphdrawing.graphml.xmlns.DataType;
import org.graphdrawing.graphml.xmlns.GraphType;
import org.graphdrawing.graphml.xmlns.GraphmlType;
import org.graphdrawing.graphml.xmlns.NodeType;

import eu.synligare.sgraphml.GroupNodeType;
import eu.synligare.sgraphml.NodeLabelType;
import eu.synligare.sgraphml.PolyLineEdgeType;
import eu.synligare.sgraphml.PortNodeType;
import eu.synligare.sgraphml.SgraphmlPackage;
import eu.synligare.sgraphml.ShapeNodeType;
import eu.synligare.sgraphml.ShapeTypeType;

public class GroupNodeEditPart extends AbstractGraphicalEditPart implements NodeEditPart {

	protected GroupNodeTypeAdapter groupNodeAdapter;
	protected GraphTypeAdapter groupNodeGraphAdapter;
	protected GraphTypeAdapter rootGraphAdapter;
	 
	  public GroupNodeEditPart() {
	    super();
	    groupNodeAdapter = new GroupNodeTypeAdapter();
	    groupNodeGraphAdapter = new GraphTypeAdapter();
	    rootGraphAdapter = new GraphTypeAdapter();
	  }
	   
	@Override
	protected IFigure createFigure() {
		GroupNodeType model = (GroupNodeType)getModel();
		
		if (model.getShape() != null){
	
			switch (model.getShape().getType()){
			case ELLIPSE:
				return new EllipseGroupNodeFigure();
			case RECTANGLE:
				return new RectangleGroupNodeFigure();
			case ROUNDRECTANGLE:
				return new RoundRectangleGroupNodeFigure();
			case CUTRECTANGLE:
				return new CutRectangleGroupNodeFigure();
			case TRIANGLE:
				System.out.println("Triangle not supported for groupnode");
				return new RectangleGroupNodeFigure();
			}
		}
		return new RectangleGroupNodeFigure();
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new GroupNodeXYLayoutPolicy());
	    installEditPolicy(EditPolicy.COMPONENT_ROLE, new NodeTypeComponentEditPolicy()); //Delete  
		 installEditPolicy("Snap Feedback", new SnapFeedbackPolicy());
	    installEditPolicy(EditPolicy.CONTAINER_ROLE, new CreateAttributeEditPolicy());
	}
	/**
	 * Currently the class only adapts to create a {@link SnapToHelper}
	 * when the editor is in snapping mode (either to grid or to shapes).
	 */
	@Override 
	public Object getAdapter(Class key) {
	    if (key == SnapToHelper.class) {
	        List<SnapToHelper> helpers = new ArrayList<SnapToHelper>();
	        if (Boolean.TRUE.equals(getViewer().getProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED))) {
	            helpers.add(new SnapToGeometry(this));
	        }
	        if (Boolean.TRUE.equals(getViewer().getProperty(SnapToGrid.PROPERTY_GRID_ENABLED))) {
	         helpers.add(new SnapToGrid(this));
	        }
	        if(helpers.size()==0) {
	            return null;
	        } else {
	            return new CompoundSnapToHelper(helpers.toArray(new SnapToHelper[0]));
	        }
	    }
	    return super.getAdapter(key);
	} 
	
	 @Override 
	 protected void refreshVisuals() {
		    GroupNodeFigure figure = (GroupNodeFigure)getFigure();
		    GroupNodeType model = (GroupNodeType)getModel();
		    
		    figure.setGeometry(model.getGeometry());
		    figure.setFill(model.getFill());
		    figure.setBorderStyle(model.getBorderStyle());
//		    figure.setNodeLabel(model.getNodeLabel().get(0));
		    figure.setLayouts(); 
	    
	 }
	 
	 	 
		@Override 
		//returns NodeLabels, ShapeNodes, GroupNodes, then the portnodes and their labels, i. 
		// "NodeLabel1, NodeLabel2, ... NodeLabel n, PNodeLabel 1, Pnode1, PnodeLabel 2, PNode ..."

		protected List<EObject> getModelChildren() {
			 
				GroupNodeType groupNode = (GroupNodeType) getModel();
			    List<EObject> children = new ArrayList<EObject>();

			    //Get the encapsulating graphml node 
			    NodeType parentGraphmlNode = (NodeType)groupNode.eContainer().eContainer();
			   
			    //Get the graph nodes encapsulating the shapenode contents of the groupnode
			    EList<GraphType> graphs = parentGraphmlNode.getGraph();
			    
			    if (graphs.size() == 0){
			    	return Collections.EMPTY_LIST;
			    }
			    
			    if (graphs.size() > 1){
			    	System.out.println ("Does not support more than one graph for each GroupNode");
			    }
			    
			    
			    //Add GroupNode own labels first
			    if (groupNode.getNodeLabel().size() > 0){
				    for (NodeLabelType nodeLabel : groupNode.getNodeLabel()){
				    	children.add(nodeLabel);	
				    }
			    }
			    
			    //Then add stuff in its internal graph
			    GraphType graph = graphs.get(0);
			    
			    EList<NodeType> graphmlNodes = graph.getNode();
			  
			    for (NodeType graphmlNode : graphmlNodes) {
					EList<DataType> dataList = graphmlNode.getData();
					
					//assume only one data node
					if (dataList.size() != 1)
					{
						System.out.print("Expected exactly one data element.");
						return null;
					}

					FeatureMap fm = dataList.get(0).getMixed();
					
					for (FeatureMap.Entry entry : fm){
						
					
						if ((entry.getEStructuralFeature().getFeatureID() == SgraphmlPackage.DOCUMENT_ROOT__PORT_NODE) &&
								(entry.getValue() instanceof PortNodeType)){
								PortNodeType pn = (PortNodeType)entry.getValue();

								//add label object
								children.add(pn.getNodeLabel().get(0));
								//add port after, so it gets on top
								children.add(pn);
						}
						else if (entry.getEStructuralFeature().getFeatureID() == SgraphmlPackage.DOCUMENT_ROOT__SHAPE_NODE){
								ShapeNodeType sn = (ShapeNodeType)entry.getValue();
								children.add(sn);
						}
						else if (entry.getEStructuralFeature().getFeatureID() == SgraphmlPackage.DOCUMENT_ROOT__GROUP_NODE){
								GroupNodeType gn = (GroupNodeType)entry.getValue();
								children.add(gn);
						}
					}  
			    }
			return children; 
		}

		//Default implementation is to return the root figure
		//we need to return the rectangle to make sure the rectangle doesn't overwrite the ports
		 @Override
			 public IFigure getContentPane(){
			 GroupNodeFigure figure = (GroupNodeFigure)getFigure();
			 return figure.getContentsPane();
		    } 
	
			@Override
			//All editParts are activated, so this is called for all ShapeNodeEditPart, i.e.
			//we listen to all ShapeNodeType objects in the model.
			public void activate() {
				if(!isActive()) {
			      ((GroupNodeType)getModel()).eAdapters().add(groupNodeAdapter);

			      //groupnode graphAdapter
			      NodeType graphmlNode = (NodeType)((GroupNodeType)getModel()).eContainer().eContainer();
			      graphmlNode.getGraph().get(0).eAdapters().add(groupNodeGraphAdapter);
			    
			      //root graph
			      ModelProcessor.INSTANCE.getRootGraph().eAdapters().add(rootGraphAdapter);
				}
			    super.activate();
			  }
			 
			  @Override 
			  public void deactivate() {
			    if(isActive()) {
			      ((GroupNodeType)getModel()).eAdapters().remove(groupNodeAdapter);
			      
			      //graphAdapter
			      NodeType graphmlNode = (NodeType)((GroupNodeType)getModel()).eContainer().eContainer();
			      graphmlNode.getGraph().get(0).eAdapters().remove(groupNodeGraphAdapter);

			      //root graph
			      ModelProcessor.INSTANCE.getRootGraph().eAdapters().remove(rootGraphAdapter);

			    }
			    super.deactivate();
			  }
			 
			 //observer for change of ShapeNodeType model objects, i.e. position change
			  public class GroupNodeTypeAdapter implements Adapter {
			 
			    @Override 
			    public void notifyChanged(Notification notification) {

			    	if (((notification.getEventType() == notification.ADD) || 
			    			(notification.getEventType() == notification.REMOVE)) &&
			    			(notification.getFeatureID(ShapeNodeType.class) == SgraphmlPackage.GROUP_NODE_TYPE__NODE_LABEL))
			    	{
			    		//New label dropped
			    		refresh();
			    	}
			    	else{
			    		//Groupnopde changed, Label moved, ...
				    	refreshVisuals();
			    	}
			    }
			 
			    @Override 
			    public Notifier getTarget() {
			      return (GroupNodeType)getModel();
			    }
			 
			    @Override 
			    public void setTarget(Notifier newTarget) {
			      // Do nothing.
			    }
			 
			    @Override 
			    public boolean isAdapterForType(Object type) {
			      return type.equals(GroupNodeType.class);
			    }
			  }  
		

				  //Listener for the GraphType
				  //When a Portnode is deleted (or added), the graphtyoe that holds the node list
				  //is notified so we can redraw the list of nodes.
				  //GraphmlType         - only 1
				  //    GraphType(0)    - top graph
				  //      GroupNode()     - ShapeNode & GroupNode   
				  //        GraphType(0)  - the graph we listen to
				  //         PortNode
				  public class GraphTypeAdapter implements Adapter {
				 
				    @Override
				    public void notifyChanged(Notification notification) {
				    	refresh();
				    	
				    	/*
				    	//After DND of group node, we need to detect any new PortNodes and create their editParts
				    	//this means refreshVisuals() is not enough.
				    	refreshChildren(); 
				    	
				    	//When an edge has been added to the root graph, we need refreshVisuals
				    	*/
				    }
				 
				    @Override
				    public Notifier getTarget() {
				      return ModelProcessor.INSTANCE.getRootGraph();
				    }
				 
				    @Override
				    public void setTarget(Notifier newTarget) {
				      // Do nothing.
				    }
				 
				    @Override 
				    public boolean isAdapterForType(Object type) {
				    	return type.equals(GraphType.class);
				    }
				  }

		  @Override 
		  //Returns all PolyLineEdgeType that has this GroupNodeType as source
		  protected List<PolyLineEdgeType> getModelSourceConnections() {
			    GroupNodeType modelElement = (GroupNodeType)getModel();
			    NodeType sourceGraphmlNode = (NodeType)modelElement.eContainer().eContainer();
			    return ModelProcessor.INSTANCE.FindConnectionsFrom(sourceGraphmlNode);
			  
			  }
		  @Override 
		  //Returns all PolyLineEdgeType that has this GroupNodeType as target
		  protected List<PolyLineEdgeType> getModelTargetConnections() {
			    GroupNodeType modelElement = (GroupNodeType)getModel();
			    NodeType targetGraphmlNode = (NodeType)modelElement.eContainer().eContainer();
			    return ModelProcessor.INSTANCE.FindConnectionsTo(targetGraphmlNode);  
		  }

			@Override
			public ConnectionAnchor getSourceConnectionAnchor(
					ConnectionEditPart connection) {
				return ((GroupNodeFigure)getFigure()).getConnectionAnchor();
			}

			@Override
			public ConnectionAnchor getTargetConnectionAnchor(
					ConnectionEditPart connection) {
				return ((GroupNodeFigure)getFigure()).getConnectionAnchor();
			}

			@Override
			public ConnectionAnchor getSourceConnectionAnchor(Request request) {
				return ((GroupNodeFigure)getFigure()).getConnectionAnchor();	
			}

			@Override
			public ConnectionAnchor getTargetConnectionAnchor(Request request) {
				return ((GroupNodeFigure)getFigure()).getConnectionAnchor();
			}
				  
}
