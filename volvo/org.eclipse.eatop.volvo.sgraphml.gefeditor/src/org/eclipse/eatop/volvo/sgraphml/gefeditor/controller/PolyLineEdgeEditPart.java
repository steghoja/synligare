package org.eclipse.eatop.volvo.sgraphml.gefeditor.controller;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.policy.EdgeConnectionEditPolicy;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.policy.PolyLineEdgeEditPolicy;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.PolylineConnectionFigure;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;

import eu.synligare.sgraphml.GroupNodeType;
import eu.synligare.sgraphml.PathType;
import eu.synligare.sgraphml.PointType;
import eu.synligare.sgraphml.PolyLineEdgeType;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.geometry.Point;
public class PolyLineEdgeEditPart extends AbstractConnectionEditPart {

	
	private PathTypeAdapter adapter;
	   
		
	 public PolyLineEdgeEditPart() {
		    super();
		    adapter = new PathTypeAdapter();
		  }
	

	  @Override protected void createEditPolicies() {
		    installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new ConnectionEndpointEditPolicy());
		    installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE, new PolyLineEdgeEditPolicy());
		    installEditPolicy(EditPolicy.CONNECTION_ROLE, new EdgeConnectionEditPolicy());   
	  }
	  
		 
	  @Override protected IFigure createFigure() {

	    return new PolylineConnectionFigure();
	  }

	  
	  
	  @Override 
	 protected void refreshVisuals() {
		 
		  PolylineConnectionFigure figure = (PolylineConnectionFigure)getFigure();
		  PolyLineEdgeType model = (PolyLineEdgeType)getModel();
			
		   figure.setLineStyle(model.getLineStyle());
		   figure.setArrows(model.getArrows());
		   figure.setEdgeLabel(model.getEdgeLabel());  
		   
		   PathType modelConstraint = ((PolyLineEdgeType)getModel()).getPath();
		    
		    List<AbsoluteBendpoint> figureConstraint = new ArrayList<AbsoluteBendpoint>();
		    for (PointType p : modelConstraint.getPoint()) {
		      figureConstraint.add(new AbsoluteBendpoint((int)Math.round(p.getX()), (int) Math.round(p.getY())));
		    }
		    figure.setRoutingConstraint(figureConstraint);
		 }
	  
	  
	  @Override public void activate() {
		    if(!isActive()) {
		      ((PolyLineEdgeType)getModel()).getPath().eAdapters().add(adapter);
		    }
		    super.activate();
		  }
		 
		  @Override public void deactivate() {
		    if(isActive()) {
		      ((PolyLineEdgeType)getModel()).getPath().eAdapters().remove(adapter);   
		    }
		    super.deactivate();
		  } 
	
		  
		 
	  public class PathTypeAdapter implements Adapter {
		  
		    @Override public void notifyChanged(Notification notification) {

		    	if (notification.getEventType() != Notification.REMOVING_ADAPTER)
		    	{
		    		//Bendpoints change etc.
		    		refreshVisuals();
		    	}
		    }
		 
		    @Override public Notifier getTarget() {     
		      return (PathType)(((PolyLineEdgeType)(getModel())).getPath());
		    }
		 
		    @Override public void setTarget(Notifier newTarget) {
		      // Do nothing.
		    }
		 
		    @Override public boolean isAdapterForType(Object type) {
		      return type.equals(PathType.class);
		    }
		  }
}
