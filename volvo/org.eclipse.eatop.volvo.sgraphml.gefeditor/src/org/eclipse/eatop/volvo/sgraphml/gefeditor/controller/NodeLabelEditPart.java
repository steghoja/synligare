package org.eclipse.eatop.volvo.sgraphml.gefeditor.controller;

import org.eclipse.draw2d.IFigure;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.controller.PortNodeEditPart.GraphTypeAdapter;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.controller.PortNodeEditPart.PortNodeTypeAdapter;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.policy.DeleteNodeLabelEditPolicy;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.policy.NodeTypeComponentEditPolicy;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.NodeLabelFigure;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.PortNodeFigure;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.PortNodeLabelFigure;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import eu.synligare.sgraphml.NodeLabelType;
import eu.synligare.sgraphml.PortNodeType;
import eu.synligare.sgraphml.SgraphmlPackage;
import eu.synligare.sgraphml.ShapeNodeType;

/*** 
 *  
 * @author yt11022
 *
 */

public class NodeLabelEditPart extends AbstractGraphicalEditPart {

		
	protected NodeLabelTypeAdapter NodeLabelAdapter;
	
	  public NodeLabelEditPart() {
		 super();
		 NodeLabelAdapter = new NodeLabelTypeAdapter();
	  }
	
	@Override
	protected IFigure createFigure() {
		return new NodeLabelFigure();
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub
		 installEditPolicy(EditPolicy.COMPONENT_ROLE, new DeleteNodeLabelEditPolicy()); //Delete  
	}

	 @Override 
	 protected void refreshVisuals() {
	    NodeLabelFigure figure = (NodeLabelFigure)getFigure();
	    NodeLabelType nodeLabel = (NodeLabelType)getModel();

	    figure.setNodeLabel(nodeLabel); 
	    figure.setLayout();
	    //  figure.setLayout();
		 
	 }
	
		@Override
		
		public void activate() {
		    if(!isActive()) {
		      ((NodeLabelType)getModel()).eAdapters().add(NodeLabelAdapter);
		    }
		    super.activate();
		  }
		 
		  @Override 
		  public void deactivate() {
		    if(isActive()) {
		    	((NodeLabelType)getModel()).eAdapters().remove(NodeLabelAdapter);
		    }
		    super.deactivate();
		  }
	 
	 //observer for change of NodeLabelType model objects
	  public class NodeLabelTypeAdapter implements Adapter {
	 
	    @Override 
	    public void notifyChanged(Notification notification) {
	    	refreshVisuals();   	
	    }
	 
	    @Override 
	    public Notifier getTarget() {
	      return (NodeLabelType)getModel();
	      
	    }
	 
	    @Override 
	    public void setTarget(Notifier newTarget) {
	      // Do nothing.
	    }
	 
	    @Override 
	    public boolean isAdapterForType(Object type) {
	      return type.equals(NodeLabelType.class);
	    }
	  }  

}
