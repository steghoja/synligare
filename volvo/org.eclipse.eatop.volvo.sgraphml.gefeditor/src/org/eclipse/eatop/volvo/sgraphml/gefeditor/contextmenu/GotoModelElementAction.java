package org.eclipse.eatop.volvo.sgraphml.gefeditor.contextmenu;

import java.util.ArrayList;
import java.util.List;




















import org.eclipse.core.internal.registry.OffsetTable;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.eatop.common.resource.impl.EastADLXMLResourceImpl;
import org.eclipse.eatop.eastadl21.EAPrototype;
import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.eatop.examples.explorer.ChildWrapper;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.Activator;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.Utils;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.controller.GraphMLTypeEditPart;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.controller.NodeLabelEditPart;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.controller.PolyLineEdgeEditPart;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.controller.PortNodeLabelEditPart;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd.EAXMLprocessor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.sphinx.emf.util.EcorePlatformUtil;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.graphdrawing.graphml.xmlns.EdgeType;
import org.graphdrawing.graphml.xmlns.NodeType;



















import eu.synligare.sgraphml.BaseNodeType;
import eu.synligare.sgraphml.NodeLabelType;
import eu.synligare.sgraphml.PolyLineEdgeType;

public class GotoModelElementAction extends SelectionAction {
	
	private final static String EAST_ADL_EXPLORER = 
			"org.eclipse.eatop.examples.explorer.views.eastadlExplorer";
	
	 public static final String GOTO_MODEL_ELEMENT = "GotoModelElement";
	    public static final String REQ_GOTO_MODEL_ELEMENT = "GotoModelElement";
	 
	    Request request;
	 
	    /**
	     * Create a new instance of the class.
	     * @param part
	     */
	    public GotoModelElementAction(IWorkbenchPart part) {
	        super(part);
	        setId(GOTO_MODEL_ELEMENT);
	        setText("Goto model element");
	        request = new Request(REQ_GOTO_MODEL_ELEMENT);
	    }

	    /**
	     * The {@link GotoModelElementsAction#REQ_RESIZE_TO_CONTENTS REQ_RESIZE_TO_CONTENTS}.
	     *
	     * It is assumed that this method is executed directly after
	     * {@link GotoMResizeToContentsAction#calculateEnabled() calculateEnabled()}
	     *
	     * In general: To execute a command, use this->execute(command).
	     */
	    
	    @Override
	    public void run() {
	    	
	    	// selected objects must be nodes because the action is enabled.
	        @SuppressWarnings("unchecked") List<EditPart> editParts = getSelectedObjects();

	        EditPart editPart = editParts.get(0);
	        String dotPath = null;        
	        
	        if (editPart instanceof PortNodeLabelEditPart){
	        	//Move from portlabel to groupnode
	        	editPart = editPart.getParent();
	        }
	        
	        if (editPart instanceof PolyLineEdgeEditPart){
	        	PolyLineEdgeType p = (PolyLineEdgeType)editPart.getModel();
	        	EdgeType e = (EdgeType)p.eContainer().eContainer();
	        	dotPath = e.getId();
	        }
	        else if (editPart instanceof NodeLabelEditPart){
	        	NodeLabelType nodeLabel = (NodeLabelType)editPart.getModel();
	        	
	        	String sLabel = Utils.getLabelText(nodeLabel);
	    		if (sLabel.contains("@")){
					//label contains an attribute of some object
					int index = sLabel.indexOf('@');
					int colonindex = sLabel.indexOf(':', index);
					dotPath = sLabel.substring(index + 1, colonindex);
				}	    					
				else{
			        BaseNodeType snode = (BaseNodeType)nodeLabel.eContainer();
					NodeType gnode = (NodeType)snode.eContainer().eContainer();
			        dotPath = gnode.getId();        	    			
				
	        	}
	        }
	        else{
		        BaseNodeType snode = (BaseNodeType)editPart.getModel();
		        NodeType gnode = (NodeType)snode.eContainer().eContainer();
		        dotPath = gnode.getId();        	
	        }
	        
	        if (dotPath == null){
	        	Utils.showErrorMessage("Element id not set");
	        	return;
	        }
	        
	        EObject eObject = EAXMLprocessor.getEObjectbyDotPath(dotPath);
        	dotPath = EAXMLprocessor.toFatherPath(dotPath);
        	
        	EObject father = null;
        	if (!dotPath.isEmpty())	{        	
        		father = EAXMLprocessor.getEObjectbyDotPath(dotPath);
        	}
        	
        	StructuredSelection selection = null;
        	if (father instanceof EAPrototype){
        		//if the father is a prototype, then EObject is virtually contained in father
        		selection = new StructuredSelection( new ChildWrapper(eObject, father) );
        	} else {
        		selection = new StructuredSelection( eObject );
        	}
        	
			IWorkbenchPage page= Activator.getDefault().getWorkbench().
					getActiveWorkbenchWindow().getActivePage();

			try {
				IViewPart view = page.showView(EAST_ADL_EXPLORER);
				view.getSite().getSelectionProvider().setSelection(selection);

			} catch (PartInitException e) {
				// TODO
				// give user some message
				e.printStackTrace();
			}
	    }
	    
	@Override
	protected boolean calculateEnabled() {
		
		
		@SuppressWarnings("unchecked") List<Object> selected = getSelectedObjects();
		
		//Exactly one element selected? When you click RMB, an object at that position gets selected.
		if (selected.isEmpty() || selected.size() > 1){ 
			return false;
		}
		
		if (selected.get(0) instanceof EditPart){
			EditPart editPart = (EditPart)selected.get(0);
			
			return !(editPart instanceof GraphMLTypeEditPart);
		}
		
		return false;
	}

}
