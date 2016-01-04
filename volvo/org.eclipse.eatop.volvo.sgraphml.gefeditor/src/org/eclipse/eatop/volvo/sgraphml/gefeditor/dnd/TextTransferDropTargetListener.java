package org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.eatop.eastadl21.EAElement;
import org.eclipse.eatop.examples.explorer.ChildWrapper;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.Utils;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.requests.CreateAttributeRequest;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.dnd.AbstractTransferDropTargetListener;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.TextTransfer;

import eu.synligare.sgraphml.BaseNodeType;


public class TextTransferDropTargetListener extends
		AbstractTransferDropTargetListener {


	public TextTransferDropTargetListener(EditPartViewer viewer) {
		super(viewer, TextTransfer.getInstance()); 
	}

	protected AttributeObjectFactory factory = new AttributeObjectFactory();
	
	@Override
	protected Request createTargetRequest() {
		CreateAttributeRequest request = new CreateAttributeRequest();
		request.setFactory(factory);
		return request;
	}
	
	@Override
	protected void updateTargetRequest() {
		   ((CreateAttributeRequest)getTargetRequest()).setLocation(getDropLocation());
		}
	

	@Override
	protected void handleDragOver() {
		ISelection selection = Utils.getExplorerSelection();
		if (selection instanceof TreeSelection)
		{
			ITreeSelection treeSelection = (ITreeSelection)selection;
			
			if (treeSelection.size() == 1) {
				Object object = treeSelection.getFirstElement();
				if (object instanceof EObject){
					//make sure we don't delete the text in the property sheet
					getCurrentEvent().detail = DND.DROP_COPY; 
					//the event.data is not filled in yet 
					super.handleDragOver();
					return;
				}
			}	
		}		
		//Skip this drag
		getCurrentEvent().detail = DND.DROP_NONE; 
		super.handleDragOver();
	}  

	@Override
	protected void handleDrop() {
		String text = (String)getCurrentEvent().data;
		String attribute = text.substring(0, text.indexOf("\t"));
		
		ISelection selection = Utils.getExplorerSelection();
		if (selection instanceof TreeSelection)
		{
			ITreeSelection treeSelection = (ITreeSelection)selection;
			
			if (treeSelection.size() != 1){
				Utils.showInformationMessage("Drop of attributes only possible when node in explorer is selected");
				getCurrentEvent().detail = DND.DROP_NONE;
				return;
			}


			
			
			Object object = treeSelection.getFirstElement();
			
			
			if (!(object instanceof EObject) || (object instanceof ChildWrapper))
			{
				//something else selected in tree view
				getCurrentEvent().detail = DND.DROP_NONE;
				return;
			}
			
			List<EObjectWithDotPath> list = EAXMLprocessor.findDotPaths(treeSelection);
			EObjectWithDotPath eoWithDotPath = list.get(0);
		
			updateTargetEditPart();
			EditPart ep = getTargetEditPart();
			BaseNodeType node = (BaseNodeType)ep.getModel();
			
			factory.setLocation(getDropLocation());		
			factory.setAttribute(attribute);
	//		factory.setSelectedTreeEObject((EObject)object);
			factory.setSelectedTreeObject(eoWithDotPath);
			factory.setParentGeometry(node.getGeometry());
			
		   super.handleDrop();
		}
		
		//should not occur
	   getCurrentEvent().detail = DND.DROP_NONE;
	   return;

	}
  
	
	
}
