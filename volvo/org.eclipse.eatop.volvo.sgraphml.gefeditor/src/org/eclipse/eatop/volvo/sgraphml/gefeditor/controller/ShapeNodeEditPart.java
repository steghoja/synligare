package org.eclipse.eatop.volvo.sgraphml.gefeditor.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.Utils;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.model.ModelProcessor;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.policy.CreateAttributeEditPolicy;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.policy.NodeTypeComponentEditPolicy;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.policy.ShapeNodeXYLayoutPolicy;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.EllipseShapeNodeFigure;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.GroupNodeFigure;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.RectangleShapeNodeFigure;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.RoundRectangleShapeNodeFigure;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.ShapeNodeFigure;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.TriangleShapeNodeFigure;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.gef.CompoundSnapToHelper;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.SnapFeedbackPolicy;
import org.eclipse.ui.IEditorPart;
import org.graphdrawing.graphml.xmlns.EdgeType;
import org.graphdrawing.graphml.xmlns.GraphType;
import org.graphdrawing.graphml.xmlns.GraphmlType;
import org.graphdrawing.graphml.xmlns.NodeType;
import org.graphdrawing.graphml.xmlns.XmlnsPackage;

import eu.synligare.sgraphml.GroupNodeType;
import eu.synligare.sgraphml.NodeLabelType;
import eu.synligare.sgraphml.PolyLineEdgeType;
import eu.synligare.sgraphml.PortNodeType;
import eu.synligare.sgraphml.SgraphmlPackage;
import eu.synligare.sgraphml.ShapeNodeType;
import eu.synligare.sgraphml.ShapeTypeType;


public class ShapeNodeEditPart extends AbstractGraphicalEditPart implements NodeEditPart {

	protected ShapeNodeTypeAdapter shapeNodeAdapter;
	protected GraphTypeAdapter graphTypeAdapter;
	 
	MouseListener mouseListener = new MouseListener()  {

		@Override
		public void mousePressed(MouseEvent me) {
	    }

		@Override
		public void mouseReleased(MouseEvent me) {			
		}

		@Override
		public void mouseDoubleClicked(MouseEvent me) {
			Utils.showInformationMessage("You clicked me");
		}
	};

	
//	protected List<EdgeTypeAdapter> edgeAdapters;// = new ArrayList<EdgeTypeAdapter>();
	
	  public ShapeNodeEditPart() {
	    super();
	    shapeNodeAdapter = new ShapeNodeTypeAdapter();
	    //edgeAdapters = new ArrayList<EdgeTypeAdapter>();
	    graphTypeAdapter = new GraphTypeAdapter();
	  }
	  
	@Override
	protected IFigure createFigure() {
		
		ShapeNodeType model = (ShapeNodeType)getModel();
		
		ShapeTypeType stt = model.getShape().getType();
		
		IFigure figure = null;
		
		switch (stt) {
		case RECTANGLE:
			figure = new RectangleShapeNodeFigure(true);
			break;	
		case ROUNDRECTANGLE:
			figure = new RoundRectangleShapeNodeFigure();
			break;	
		case ELLIPSE:
			figure = new EllipseShapeNodeFigure();
			break;	
		case TRIANGLE:
			figure = new TriangleShapeNodeFigure();
			break;
		}

		if (figure != null){
			figure.addMouseListener(mouseListener);
		}
		return figure;
	}

	@Override
	protected void createEditPolicies() {
		 installEditPolicy(EditPolicy.COMPONENT_ROLE, new NodeTypeComponentEditPolicy()); //Delete  
		 installEditPolicy(EditPolicy.LAYOUT_ROLE, new ShapeNodeXYLayoutPolicy()); //Move label  
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
	    ShapeNodeFigure figure = (ShapeNodeFigure)getFigure();
	    ShapeNodeType model = (ShapeNodeType)getModel();
    
	    figure.setGeometry(model.getGeometry());
	    figure.setFill(model.getFill());
	    figure.setBorderStyle(model.getBorderStyle());
	   // figure.setNodeLabels(model.getNodeLabel());
	    
	    
	    figure.setLayouts(); 
		 
	 }
	 
	//Default implementation is to return the root figure
			//we need to return the rectangle to make sure the rectangle doesn't overwrite the ports
			 @Override
				 public IFigure getContentPane(){
				 ShapeNodeFigure figure = (ShapeNodeFigure)getFigure();
				 return figure.getContentsPane();
			    } 
	 
	 	 
		@Override
		//All editParts are activated, so this is called for all ShapeNodeEditPart, i.e.
		//we listen to all ShapeNodeType objects in the model.
		public void activate() {
		    if(!isActive()) {
		      ((ShapeNodeType)getModel()).eAdapters().add(shapeNodeAdapter);
		   
		      //GraphType
		      ModelProcessor.INSTANCE.getRootGraph().eAdapters().add(graphTypeAdapter);

		/*      
		      //Find edges to listen to, and add an adapter to each
		      List<PolyLineEdgeType> sourceConnections = getModelSourceConnections();
		      List<PolyLineEdgeType> destConnections = getModelTargetConnections();
		      List<PolyLineEdgeType> Connections = new ArrayList<PolyLineEdgeType>();
		      Connections.addAll(sourceConnections);
		      Connections.addAll(destConnections);
		      
		      for (PolyLineEdgeType pEdge : Connections) {
				EdgeType edge = (EdgeType)pEdge.eContainer().eContainer();
				EdgeTypeAdapter edgeTypeAdapter = new EdgeTypeAdapter();
				edgeTypeAdapter.setTarget(edge);
				edgeTypeAdapter.setEdgeSource(sourceConnections.contains(pEdge));
				edge.eAdapters().add(edgeTypeAdapter);
				edgeAdapters.add(edgeTypeAdapter);
		      }
	      */
		      super.activate();
		    }
		  }
		 
		
		  @Override 
		  public void deactivate() {
		    if(isActive()) {
		      ((ShapeNodeType)getModel()).eAdapters().remove(shapeNodeAdapter);

	/*	      for (EdgeTypeAdapter edgeAdapter : edgeAdapters){
		    	  EdgeType edge = (EdgeType)edgeAdapter.getTarget();
		    	  edge.eAdapters().remove(edgeAdapter);
		      }
		      edgeAdapters.clear();
		*/      
		      //GraphType
		      ModelProcessor.INSTANCE.getRootGraph().eAdapters().remove(graphTypeAdapter);	     
		    }
		    super.deactivate();
		  }
		 
		 //observer for change of ShapeNodeType model objects
		  public class ShapeNodeTypeAdapter implements Adapter {
		 
		    @Override 
		    public void notifyChanged(Notification notification) {
		    	
		    	int eventType = notification.getEventType();
		    	int featureID = notification.getFeatureID(ShapeNodeType.class);
		    	
		    	
		    	
		    	if ((((eventType== notification.ADD) || 
		    			(eventType == notification.REMOVE)) &&
		    			(featureID  == SgraphmlPackage.SHAPE_NODE_TYPE__NODE_LABEL)))
/*		    		||
		    		((eventType == notification.SET) && (featureID == SgraphmlPackage.SHAPE_NODE_TYPE__GEOMETRY))) //resize shapnode
*/
		    	{
		    		//New label dropped
		    		refresh();
		    	}
		    	else{
		    		//shapenode changed, Label moved, ...
			    	refreshVisuals();
		    	}
		    }
		 
		    @Override 
		    public Notifier getTarget() {
		      return (ShapeNodeType)getModel();
		    }
		 
		    @Override 
		    public void setTarget(Notifier newTarget) {
		      // Do nothing.
		    }
		 
		    @Override 
		    public boolean isAdapterForType(Object type) {
		      return type.equals(ShapeNodeType.class);
		    }
		  }

		  //observer for change of edge in top GraphType, we need to refresh the connections if edge added/deleted 
		  //Optimization potential: Instead of listening to all edges, just listen
		  //to the edges that this ShapeNodeType is connected to. Tricky for undo.
		  public class GraphTypeAdapter implements Adapter {
		 
		    @Override 
		    public void notifyChanged(Notification notification) {
	
		    	if (notification.getEventType() == Notification.REMOVING_ADAPTER) {
		    		//do nothing
		    	}
		    	
		    	if ((notification.getEventType() == Notification.ADD) && 
		    		(notification.getFeatureID(GraphType.class) == XmlnsPackage.GRAPH_TYPE__EDGE)){
		    		  //There's a new edge in the model, update
		    		  refreshSourceConnections();
			    	  refreshTargetConnections();    
		    		
		    	}
		    	else if ((notification.getEventType() == Notification.REMOVE) &&
		    	         (notification.getFeatureID(GraphType.class) == XmlnsPackage.GRAPH_TYPE__EDGE)){
		    		  //An edge has been removed from the model, update
		    		  refreshSourceConnections();
			    	  refreshTargetConnections();    
		    		
		    	} 	
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

		
		  //This was a test to listen to individual edges instead of whole graphtype for the shapenodes.
		  //Works ok, but get's tricky for undo, especially when you delete a sequence of elements and the redo.... so skip this solution.
	/*	  public class EdgeTypeAdapter implements Adapter {
				 
			  Notifier target;
			  boolean edgeSource; //True => this adapter aimed for the source part of the edge, False => this adapter interested in the target part of the edge
			  
		   public boolean isEdgeSource() {
				return edgeSource;
			}


			public void setEdgeSource(boolean edgeSource) {
				this.edgeSource = edgeSource;
			}


				@Override 
			    public void notifyChanged(Notification notification) {
			    	  
		    	  if (notification.getEventType() == notification.REMOVING_ADAPTER){
		    		  //do nothing
		    	  }
			    	
		    	  else if ((notification.getEventType() == notification.SET) && 
		    		  (notification.getFeatureID(EdgeType.class) == XmlnsPackage.EDGE_TYPE__SOURCE) &&
		    		  edgeSource){

		    		  //set edge.source to a source adapter
		    		  
		    		  if (notification.getNewValue() == null){
			    		  //edge.source = "null" => remove adapter
			    		  getTarget().eAdapters().remove(this); //target becomes null
			    		  (ShapeNodeEditPart.this).edgeAdapters.remove(this);
		    		  }

		    		  refreshSourceConnections();
		    		  refreshVisuals();
		    	  }
		    	  else if ((notification.getEventType() == notification.SET) && 
		    		  (notification.getFeatureID(EdgeType.class) == XmlnsPackage.EDGE_TYPE__TARGET) &&
		    		  !edgeSource){

		    		  //set edge.target to a target adapter
		    		  
		    		  if (notification.getNewValue() == null){
		    		  //remove adapter
			    	  getTarget().eAdapters().remove(this);
		    		  (ShapeNodeEditPart.this).edgeAdapters.remove(this);

		    		  refreshTargetConnections();    
		    		  refreshVisuals();
		    	  }
		    	  }
			    }
			  
			 
			    @Override 
			    public Notifier getTarget() {
				    return target;	      
			    }
			 
			    @Override 
			    //An edge that the shapenode is connected to
			    public void setTarget(Notifier newTarget) {
			      target = newTarget;
			    }
			 
			    @Override 
			    public boolean isAdapterForType(Object type) {
			      return type.equals(EdgeType.class);
			    }
			  }
		  
 */
		  
	  @Override 
	  //Returns all PolyLineEdgeType that has this ShapeNodeType as source
	  protected List<PolyLineEdgeType> getModelSourceConnections() {
		    ShapeNodeType modelElement = (ShapeNodeType)getModel();
		    NodeType sourceGraphmlNode = (NodeType)modelElement.eContainer().eContainer();
		    return ModelProcessor.INSTANCE.FindConnectionsFrom(sourceGraphmlNode);
	  
	  }
		  @Override 
		  //Returns all PolyLineEdgeType that has this ShapeNodeType as target
		  protected List<PolyLineEdgeType> getModelTargetConnections() {
			    ShapeNodeType modelElement = (ShapeNodeType)getModel();
			    NodeType targetGraphmlNode = (NodeType)modelElement.eContainer().eContainer();
			    return ModelProcessor.INSTANCE.FindConnectionsTo(targetGraphmlNode);  
		  }

		@Override
		public ConnectionAnchor getSourceConnectionAnchor(
				ConnectionEditPart connection) {
			return ((ShapeNodeFigure)getFigure()).getConnectionAnchor();
		}

		@Override
		public ConnectionAnchor getTargetConnectionAnchor(
				ConnectionEditPart connection) {
			return ((ShapeNodeFigure)getFigure()).getConnectionAnchor();
		}

		@Override
		public ConnectionAnchor getSourceConnectionAnchor(Request request) {
			return ((ShapeNodeFigure)getFigure()).getConnectionAnchor();	
		}

		@Override
		public ConnectionAnchor getTargetConnectionAnchor(Request request) {
			return ((ShapeNodeFigure)getFigure()).getConnectionAnchor();
		}

		@SuppressWarnings("rawtypes")
		@Override
		protected List getModelChildren() {

			//return all nodelabels
			ShapeNodeType shapeNode = (ShapeNodeType) getModel();
		    List<EObject> children = new ArrayList<EObject>();

		    return shapeNode.getNodeLabel();
		
		}
		
}
