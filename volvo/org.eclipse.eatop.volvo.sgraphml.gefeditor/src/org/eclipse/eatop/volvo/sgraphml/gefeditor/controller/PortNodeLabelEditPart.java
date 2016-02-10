package org.eclipse.eatop.volvo.sgraphml.gefeditor.controller;

import org.eclipse.draw2d.IFigure;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.controller.PortNodeEditPart.GraphTypeAdapter;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.controller.PortNodeEditPart.PortNodeTypeAdapter;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.PortNodeFigure;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.PortNodeLabelFigure;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import eu.synligare.sgraphml.NodeLabelType;
import eu.synligare.sgraphml.PortNodeType;
import eu.synligare.sgraphml.SgraphmlPackage;

/*** 
 * The Port label is handled by a separate editpart, then the 
 * port can be selected/resized etc without the label being affected.
 * 
 * The corresponding model element is a NodeLabelType.
 * 
 * @author yt11022
 *
 */

public class PortNodeLabelEditPart extends AbstractGraphicalEditPart {

//	protected PortNodeTypeAdapter portNodeAdapter;
	protected PortNodeLabelTypeAdapter portNodeLabelAdapter;
	
	  public PortNodeLabelEditPart() {
		 super();
		 portNodeLabelAdapter = new PortNodeLabelTypeAdapter();
	  }
	
	@Override
	protected IFigure createFigure() {
		return new PortNodeLabelFigure();
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub

	}

	 @Override 
	 protected void refreshVisuals() {
	    PortNodeLabelFigure figure = (PortNodeLabelFigure)getFigure();
	    NodeLabelType modelElement = (NodeLabelType)getModel();

	    PortNodeType portNode = (PortNodeType)modelElement.eContainer();
	    
	    figure.setPortGeometry(portNode.getGeometry());
	    figure.setNodeLabel(portNode.getNodeLabel().get(0));
		 
	    figure.setLayout();
		 
	 }
	
		@Override
		//All editParts are activated, so this is called for all PortNodeLabelEditParts, i.e.
		//we listen to all corresponding PortNodeType objects in the model.
		public void activate() {
		    if(!isActive()) {
//		      ((NodeLabelType)getModel()).eContainer().eAdapters().add(portNodeAdapter);
		      ((NodeLabelType)getModel()).eAdapters().add(portNodeLabelAdapter);

		    }
		    super.activate();
		  }
		 
		  @Override 
		  public void deactivate() {
		    if(isActive()) {
//		    	((NodeLabelType)getModel()).eContainer().eAdapters().remove(portNodeAdapter);
		    	((NodeLabelType)getModel()).eAdapters().remove(portNodeLabelAdapter);
		    }
		    super.deactivate();
		  }
	 
	 //observer for change of PortNodeType model objects, i.e. updates to NodeLabelType
	  public class PortNodeTypeAdapter implements Adapter {
	 
	    @Override 
	    public void notifyChanged(Notification notification) {

	    	if ((notification.getEventType() == notification.SET) && 
	    		(notification.getFeatureID(PortNodeType.class) == SgraphmlPackage.PORT_NODE_TYPE__NODE_LABEL)){
		    	refreshVisuals();
	    	}

	    	//skip update of geometry, we will get a label update after the port has been moved anyway
	    	
	    }
	 
	    @Override 
	    public Notifier getTarget() {
	      return (PortNodeType)((NodeLabelType)getModel()).eContainer();
	      
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

	  public class PortNodeLabelTypeAdapter implements Adapter {
			 
		    @Override 
		    public void notifyChanged(Notification notification) {

		    	if ((notification.getEventType() == notification.SET) && 
		    		(notification.getFeatureID(NodeLabelType.class) == SgraphmlPackage.NODE_LABEL_TYPE__PLACEMENT)){
			    	refreshVisuals();
		    	}

		    	//skip update of geometry, we will get a label update after the port has been moved anyway
		    	
		    }
		 
		    @Override 
		    public Notifier getTarget() {
		      return (NodeLabelType)((NodeLabelType)getModel()); //.eContainer();
		      
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
