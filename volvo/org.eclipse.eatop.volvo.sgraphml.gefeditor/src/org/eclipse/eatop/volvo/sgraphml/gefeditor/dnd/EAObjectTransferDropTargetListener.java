package org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;








import javax.lang.model.util.Elements;

import org.eclipse.eatop.common.resource.EastADLURIFactory;
import org.eclipse.eatop.eastadl21.EAElement;
import org.eclipse.eatop.examples.explorer.ChildWrapper;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.dnd.AbstractTransferDropTargetListener;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.part.ResourceTransfer;

public class EAObjectTransferDropTargetListener extends
		AbstractTransferDropTargetListener {

	public EAObjectTransferDropTargetListener(EditPartViewer viewer, Transfer xfer) {
		super(viewer, xfer);
	}

	public EAObjectTransferDropTargetListener(EditPartViewer viewer) {
		super(viewer, LocalSelectionTransfer.getTransfer()); 
	}

	protected SgraphMLObjectFactory factory = new SgraphMLObjectFactory();
	
	@Override
	protected Request createTargetRequest() {
		CreateRequest request = new CreateRequest();
		request.setFactory(factory);
		return request;
	}
	
	@Override
	protected void updateTargetRequest() {
		   ((CreateRequest)getTargetRequest()).setLocation(getDropLocation());
		}
	

	@Override
	protected void handleDragOver() {
	   //make sure we don't delete the object in the navigator tree
		getCurrentEvent().detail = DND.DROP_COPY;
		//the event.data is not filled in yet 
	   super.handleDragOver();
	}  

	@Override
	protected void handleDrop() {
		ITreeSelection selection = (ITreeSelection)getCurrentEvent().data;

	   factory.setLocation(getDropLocation());		
	   
	   List<EObjectWithDotPath> list = findDotPaths(selection);
	   factory.setSelectedTreeNodes(list);
	  
	   super.handleDrop();
	

	}
  
	/***
	 * Convert all selected treenodes to a list of eobjects with dotPaths
	 * 
	 * @param selectedTreeNodes
	 * @return
	 */
	protected List<EObjectWithDotPath> findDotPaths(ITreeSelection selectedTreeNodes){
		
		List<EObjectWithDotPath> list = new ArrayList<EObjectWithDotPath>();
		
		Iterator iterSelectedNodes = selectedTreeNodes.iterator();
		while(iterSelectedNodes.hasNext())
		{
			Object object = iterSelectedNodes.next(); 
		
			String pathWithSlashes = null;
			String pathWithDots = null;
			
			if (object instanceof EObject){
				//a non-virtual EObject selected
				EObject eo = (EObject)object;
				pathWithSlashes = EAXMLprocessor.getSafeAbsoluteQualifiedName(eo);
				pathWithDots = EAXMLprocessor.eastADLPath2dotPath(pathWithSlashes);
				list.add(new EObjectWithDotPath(pathWithDots, eo));
			}
			else if (object instanceof ChildWrapper){
				// a virtual object selected
				ChildWrapper childWrapper = (ChildWrapper)object;
				TreePath[] treePaths = selectedTreeNodes.getPathsFor(object);
			
				TreePath path = treePaths[0];
				
				list.add(prototypePath(path));
			}
		}	
		return list;
	}

	
	/***
	 * Builds the virtual dotPath from a TreePath

	 * @param path
	 * @return
	 */
	protected EObjectWithDotPath prototypePath(TreePath path) {
		//Skip first segments File
		String virtualPath = "";
		EObject eObj = null;
		for (int s = 3;  s < path.getSegmentCount(); s++){
			Object element = path.getSegment(s);
			
			if (element instanceof EObject){
				eObj = (EObject)element;
			}
			
			else if (element instanceof ChildWrapper){
				ChildWrapper wrapper = (ChildWrapper)element;
				eObj = wrapper.getObject();
			}

			if (!virtualPath.isEmpty())
			{
				virtualPath += ".";
			}
			virtualPath += ((EAElement)eObj).getShortName();
		}
		return new EObjectWithDotPath(virtualPath, eObj);
	}
	
	
}
