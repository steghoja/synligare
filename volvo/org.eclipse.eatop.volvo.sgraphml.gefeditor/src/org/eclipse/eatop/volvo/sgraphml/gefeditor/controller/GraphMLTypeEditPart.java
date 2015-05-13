package org.eclipse.eatop.volvo.sgraphml.gefeditor.controller;

import java.util.ArrayList;
import java.util.List;





















import org.eclipse.draw2d.IFigure;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.model.ModelProcessor;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.policy.GraphMLTypeXYLayoutPolicy;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.requests.UpdateLabelRectanglesRequest;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.GraphMLTypeFigure;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.gef.CompoundSnapToHelper;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editpolicies.SnapFeedbackPolicy;
import org.eclipse.gef.tools.DragEditPartsTracker;
import org.eclipse.gef.tools.MarqueeSelectionTool;
import org.graphdrawing.graphml.xmlns.DataType;
import org.graphdrawing.graphml.xmlns.GraphType;
import org.graphdrawing.graphml.xmlns.GraphmlType;
import org.graphdrawing.graphml.xmlns.NodeType;
import org.omg.CORBA.OMGVMCID;
import org.eclipse.gef.tools.MarqueeDragTracker;
import org.eclipse.gef.tools.MarqueeSelectionTool;
import org.eclipse.gef.DragTracker;

import eu.synligare.sgraphml.BaseNodeType;
import eu.synligare.sgraphml.GroupNodeType;
import eu.synligare.sgraphml.SgraphmlPackage;
import eu.synligare.sgraphml.ShapeNodeType;

public class GraphMLTypeEditPart extends AbstractGraphicalEditPart {
//	Root Edit part
	
	  private GraphAdapter graphAdapter;
	  
	  public GraphMLTypeEditPart() {
	    super();
	    graphAdapter = new GraphAdapter();
	  }

	
	@Override
	  protected IFigure createFigure() {
		return new GraphMLTypeFigure();
	  }
	
	@Override
	  protected void createEditPolicies() {
	    installEditPolicy(EditPolicy.LAYOUT_ROLE, new GraphMLTypeXYLayoutPolicy());
	    installEditPolicy("Snap Feedback", new SnapFeedbackPolicy());
	  
	}
	
	
	//Don't override getDragTracker since it's not called by the framework.
    //Instead getDragTracker is called on the RootEditPart object.
  	
	
	/**
	 * Currently the class only adapts to create a {@link SnapToHelper}
	 * when the editor is in snapping mode (either to grid or to shapes).
	 */
	@Override 
	public Object getAdapter(Class key) {
	    if (key == SnapToHelper.class) {
	        List<SnapToHelper> helpers = new ArrayList<SnapToHelper>();
	        if (Boolean.TRUE.equals(getViewer().getProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED))) { //bllir true när "snap to geometry" intryckt
	            helpers.add(new SnapToGeometry(this));
	        }
	        if (Boolean.TRUE.equals(getViewer().getProperty(SnapToGrid.PROPERTY_GRID_ENABLED))) { //blir true när grid intryckt
	            helpers.add(new SnapToGrid(this));
	        }
	        if(helpers.size()==0) {
	            return null;
	        } else {
	            return new CompoundSnapToHelper(helpers.toArray(new SnapToHelper[0]));
	        }
	    }
	    
	    //SnapToGrid.PROPERTY_GRID_ENABLED;
	    return super.getAdapter(key);
	    
	   // SnapToGrid.PROPERTY_GRID_ENABLED = om grid funktionen är på
	   // SnapToGrid.PROPERTY_GRID_VISIBLE = OM gridfunktionen syns 
	} 
	
	@Override 
	protected List<BaseNodeType> getModelChildren() {
		 
			GraphmlType graphML = (GraphmlType) getModel();
		    List<BaseNodeType> rootNodeChildren = new ArrayList<BaseNodeType>();

		    GraphType graph = graphML.getGraph().get(0);
		    EList<org.graphdrawing.graphml.xmlns.NodeType> graphmlNodes = graph.getNode();
		  
		    for (org.graphdrawing.graphml.xmlns.NodeType graphmlNode : graphmlNodes) {
				EList<DataType> dataList = graphmlNode.getData();
				
				//assume only one data node
				if (dataList.size() != 1)
				{
					System.out.print("Expected exactly one data element.");
					return null;
				}

				FeatureMap fm = dataList.get(0).getMixed();
				
				for (FeatureMap.Entry entry : fm){
					
					//There could be different stuff in the feature map, look for our nodes
					if ((entry.getEStructuralFeature().getFeatureID() == SgraphmlPackage.DOCUMENT_ROOT__SHAPE_NODE) &&
							(entry.getValue() instanceof ShapeNodeType)){
						    ShapeNodeType shn = (ShapeNodeType)entry.getValue();
							rootNodeChildren.add(shn);
					}
					else if ((entry.getEStructuralFeature().getFeatureID() == SgraphmlPackage.DOCUMENT_ROOT__GROUP_NODE) &&
					(entry.getValue() instanceof GroupNodeType)){
						
					GroupNodeType groupNode = (GroupNodeType)entry.getValue();
						rootNodeChildren.add(groupNode);
					}
				}  
		    }
		return rootNodeChildren; 
	}
	
	@Override 
	public void performRequest(Request request) {
		
		Command command = null;
		
		if (request.getType() == UpdateLabelRectanglesRequest.REQ_UPDATE_LABEL_RECTANGLES)
		{
			command = getEditPolicy(EditPolicy.LAYOUT_ROLE).getCommand(request);
		}
		
		if (command != null){
			getViewer().getEditDomain().getCommandStack().execute(command);
		}
		
	};
	
	
	@Override 
	public void activate() {
	    if(!isActive()) {
	      ((GraphmlType)getModel()).getGraph().get(0).eAdapters().add(graphAdapter);
	    }
	    super.activate();
	  }
	 
	  @Override
	  public void deactivate() {
	    if(isActive()) {
	      ((GraphmlType)getModel()).getGraph().get(0).eAdapters().remove(graphAdapter);
	    }
	    super.deactivate();
	  }
	 
	  

	  //Listener for the GraphType
	  //When a node is deleted (or added), the graphtyoe that holds the node list
	  //is notified so we can redraw the list of nodes.
	  //GraphmlType         - only 1
	  //    GraphType(0)    - only first graph used
	  //      GetNode()     - ShapeNode & GroupNode   
	  public class GraphAdapter implements Adapter {
	 
	    @Override
	    public void notifyChanged(Notification notification) {
		      refreshChildren();						//note refreshChildren, not refreshVisuals
	    }
	 
	    @Override
	    public Notifier getTarget() {
	    	return ModelProcessor.INSTANCE.getRootGraph();
	    	//return (GraphMLType)getModel();
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

}
