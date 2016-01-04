package org.eclipse.eatop.volvo.sgraphml.gefeditor.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.text.html.CSS;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.eatop.eastadl21.Eastadl21Package;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.commands.MoveChildNodeCommand;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.controller.GroupNodeEditPart.GroupNodeTypeAdapter;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.controller.ShapeNodeEditPart.GraphTypeAdapter;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.model.ModelProcessor;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.policy.GroupNodeXYLayoutPolicy;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.policy.NodeTypeComponentEditPolicy;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.PortNodeFigure;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.ShapeNodeFigure;
import org.eclipse.eatop.volvo.sgraphml.testcode.MovePortRequest;
import org.eclipse.eatop.volvo.sgraphml.testcode.SgraphmlEditDomain;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.graphdrawing.graphml.xmlns.DataType;
import org.graphdrawing.graphml.xmlns.GraphType;
import org.graphdrawing.graphml.xmlns.NodeType;
import org.graphdrawing.graphml.xmlns.XmlnsPackage;

import eu.synligare.sgraphml.GroupNodeType;
import eu.synligare.sgraphml.NodeLabelType;
import eu.synligare.sgraphml.PolyLineEdgeType;
import eu.synligare.sgraphml.PortNodeType;
import eu.synligare.sgraphml.SgraphmlPackage;
import eu.synligare.sgraphml.ShapeNodeType;

public class PortNodeEditPart extends AbstractGraphicalEditPart implements NodeEditPart {

	protected PortNodeTypeAdapter portNodeAdapter;
	protected GraphTypeAdapter rootGraphAdapter;
	
	  public PortNodeEditPart() {
		 super();
		  portNodeAdapter = new PortNodeTypeAdapter();
		  rootGraphAdapter = new GraphTypeAdapter();
	  }
	
	@Override
	protected IFigure createFigure() {
		return new PortNodeFigure();

	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub
		 installEditPolicy(EditPolicy.COMPONENT_ROLE, new NodeTypeComponentEditPolicy()); //Delete  

	}
	
	 @Override 
	 protected void refreshVisuals() {
	    PortNodeFigure figure = (PortNodeFigure)getFigure();
	    PortNodeType model = (PortNodeType)getModel();
	 
	    figure.setGeometry(model.getGeometry());
	    figure.setFill(model.getFill());
	    figure.setBorderStyle(model.getBorderStyle());
	//    figure.setNodeLabel(model.getNodeLabel().get(0));
		 
	    figure.setLayouts();
		 
	 }
	
	 
		@Override
		//All editParts are activated, so this is called for all PortNodeEditParts, i.e.
		//we listen to all PortNodeType objects in the model.
		public void activate() {
		    if(!isActive()) {
		      ((PortNodeType)getModel()).eAdapters().add(portNodeAdapter);
		      ModelProcessor.INSTANCE.getRootGraph().eAdapters().add(rootGraphAdapter);
		    }
		    super.activate();
		  }
		 
		  @Override 
		  public void deactivate() {
		    if(isActive()) {
		      ((PortNodeType)getModel()).eAdapters().remove(portNodeAdapter);
		      ModelProcessor.INSTANCE.getRootGraph().eAdapters().remove(rootGraphAdapter);
		    }
		    super.deactivate();
		  }
		 
		 //observer for change of PortNodeType model objects, i.e. position change
		  public class PortNodeTypeAdapter implements Adapter {
		 
		    @Override 
		    public void notifyChanged(Notification notification) {
		      refreshVisuals();
		      refreshSourceConnections();
		      refreshTargetConnections(); 
		    }
		 
		    @Override 
		    public Notifier getTarget() {
		      return (PortNodeType)getModel();
		      
		    }
		 
		    @Override 
		    public void setTarget(Notifier newTarget) {
		      // Do nothing.
		    }
		 
		    @Override 
		    public boolean isAdapterForType(Object type) {
		      return type.equals(PortNodeType.class);
		    }
		  }  
	
		  
		  //observer for change of edge in top GraphType, we need to refresh the connections if edge added/deleted 
		  //Optimization potential: Instead of listening to all edges, just listen
		  //to the edges that this PortNodeType is connected to. Tricky for undo.
		  public class GraphTypeAdapter implements Adapter {
		 
			  
		    @Override 
		    public void notifyChanged(Notification notification) {
		    	
		    	if (notification.getEventType() == Notification.REMOVING_ADAPTER) {
		    		//do nothing
		    	}
		    	
		    	if ((notification.getEventType() == Notification.ADD) && 
		    		(notification.getFeatureID(GraphType.class) == XmlnsPackage.GRAPH_TYPE__EDGE)){
		    		  //There's a new edge in the model, update
		    		  refreshSourceConnections(); //This causes error when I have deleted one before!!!!!
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
		  } //end graphType Adapter

		  @Override 
		  //Returns all PolyLineEdgeType that has this PortNodeType as source
		  protected List<PolyLineEdgeType> getModelSourceConnections() {
			    PortNodeType modelElement = (PortNodeType)getModel();
			    NodeType sourceGraphmlNode = (NodeType)modelElement.eContainer().eContainer();
			    return ModelProcessor.INSTANCE.FindConnectionsFrom(sourceGraphmlNode);
		  
		  }
			  @Override 
			  //Returns all PolyLineEdgeType that has this ShapeNodeType as target
			  protected List<PolyLineEdgeType> getModelTargetConnections() {
				    PortNodeType modelElement = (PortNodeType)getModel();
				    NodeType targetGraphmlNode = (NodeType)modelElement.eContainer().eContainer();
				    return ModelProcessor.INSTANCE.FindConnectionsTo(targetGraphmlNode);  
			  
			  }
			  
			@Override
			public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
				return ((PortNodeFigure)getFigure()).getConnectionAnchor();
			}

			@Override
			public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
				return ((PortNodeFigure)getFigure()).getConnectionAnchor();
			}

			@Override
			public ConnectionAnchor getSourceConnectionAnchor(Request request) {
				return ((PortNodeFigure)getFigure()).getConnectionAnchor();	
			}

			@Override
			public ConnectionAnchor getTargetConnectionAnchor(Request request) {
				return ((PortNodeFigure)getFigure()).getConnectionAnchor();
			}

	 

}
