package org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.eatop.common.ui.util.ModelSearcher;
import org.eclipse.eatop.eastadl21.AnalysisFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.EAConnector;
import org.eclipse.eatop.eastadl21.EADirectionKind;
import org.eclipse.eatop.eastadl21.EAElement;
import org.eclipse.eatop.eastadl21.EAPort;
import org.eclipse.eatop.eastadl21.EAPrototype;
import org.eclipse.eatop.eastadl21.EAType;
import org.eclipse.eatop.eastadl21.FailureOutPort;
import org.eclipse.eatop.eastadl21.FaultInPort;
import org.eclipse.eatop.eastadl21.FunctionFlowPort;
import org.eclipse.eatop.eastadl21.HardwarePin;
import org.eclipse.eatop.eastadl21.HardwarePort;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.Activator;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.Utils;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.controller.GroupNodeEditPart;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd.connectioninstrefs.ConnectionInstRefsAdapterFactory;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd.connectioninstrefs.IConnectionInstRefsProvider;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd.typeprototypecontent.HardwareComponentTypeContentProvider;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd.typeprototypecontent.ITypePrototypeContentProvider;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd.typeprototypecontent.TypePrototypeContentAdapterFactory;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.model.ModelProcessor;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.model.resources.ResourceManager;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.model.resources.SWTImageResource;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.graphdrawing.graphml.xmlns.DataType;
import org.graphdrawing.graphml.xmlns.EdgeType;
import org.graphdrawing.graphml.xmlns.GraphEdgedefaultType;
import org.graphdrawing.graphml.xmlns.GraphType;
import org.graphdrawing.graphml.xmlns.NodeType;
import org.graphdrawing.graphml.xmlns.XmlnsFactory;

import eu.synligare.sgraphml.ArrowTypeType;
import eu.synligare.sgraphml.ArrowsType;
import eu.synligare.sgraphml.EdgeLabelType;
import eu.synligare.sgraphml.FillType;
import eu.synligare.sgraphml.GeometryType;
import eu.synligare.sgraphml.GroupNodeType;
import eu.synligare.sgraphml.HorizontalTextPositionType;
import eu.synligare.sgraphml.ImageIconType;
import eu.synligare.sgraphml.LineStyleType;
import eu.synligare.sgraphml.LineTypeType;
import eu.synligare.sgraphml.NodeLabelType;
import eu.synligare.sgraphml.PathType;
import eu.synligare.sgraphml.PlacementType;
import eu.synligare.sgraphml.PolyLineEdgeType;
import eu.synligare.sgraphml.PortNodeType;
import eu.synligare.sgraphml.ResourceType;
import eu.synligare.sgraphml.ScaledIconType;
import eu.synligare.sgraphml.SgraphmlFactory;
import eu.synligare.sgraphml.SgraphmlPackage;
import eu.synligare.sgraphml.ShapeNodeType;
import eu.synligare.sgraphml.ShapeType;
import eu.synligare.sgraphml.ShapeTypeType;
import eu.synligare.sgraphml.VerticalTextPositionType;

/***
 * 
 * Creates SGraphML objects from the nodes selected nodes in the navigator view.  
 *
 */
public class SgraphMLObjectFactory implements CreationFactory {


	List<EObjectWithDotPath> selectedTreeNodes;
	Point userDropLocationScreenCoords; 
	Point userDropLocationModelCoords;

	//objects to be added to the model
	//	List<SGraphMLObjectWrapper> sgraphMLObjectsList = new ArrayList<SGraphMLObjectWrapper>();
	List<EObject> sgraphMLObjectsList = new ArrayList<EObject>();

	//ID attribute of Node or Edge elements in the sgraphMLObjectsList
	List<String> dotPa2thsList = new ArrayList<String>();

	//The EObjects whose representation will be put in the diagram. Subset of selectedTreeNodes but also EObjects contained in them.
	List<EObjectWithDotPath> actualDroppedEObjects = new ArrayList<EObjectWithDotPath>();


	Map<String, Integer> mapImageName2ID = new HashMap<String, Integer>(); //for resource IDs allocated by this DND
	int highestID;

	TypePrototypeContentAdapterFactory groupNodeAdapterFactory = new TypePrototypeContentAdapterFactory();
	ConnectionInstRefsAdapterFactory connectionAdapterFactory = new ConnectionInstRefsAdapterFactory();
	GridLayout topLevelGrid = new GridLayout();

	String groupNodeDropTargetDotPath = null;


	static final int nPortHeight = 14;
	static final int nPortWidth = 14;
	static final int nPortDistance = 20;
	static final int nTopMargin = (int)(nPortDistance * 1.5);
	static final int nBottomMargin = (int)(nPortDistance * 1.5);

	/*** 
	 * Returns a list of model objects that shall be written to the model due to the current DND operation
	 */
	@Override
	public Object getNewObject() {

		//TODO: Make sure same EAST-ADL object is only once on the canvas (dragged 0 or 1 time)?

		reset();

		//put edges last
		Collections.sort(selectedTreeNodes, selectedObjectsComparator);

		for (EObjectWithDotPath eObjectWithDotPath : selectedTreeNodes) {

			EObject eo = eObjectWithDotPath.eObject;

			if (isRepresentedByGroupNode(eo)){
				if (validateDropLocation(eObjectWithDotPath)){
					Dimension size = addGroupNode(eObjectWithDotPath);
					topLevelGrid.calculateNextGridPosition(size);
				}
			}
			else if (isRepresentedByShapeNode(eo)){
				if (validateDropLocation(eObjectWithDotPath)){
					Dimension size = addShapeNode(eObjectWithDotPath);
					topLevelGrid.calculateNextGridPosition(size);
				}
			}
			else if (isRepresentedByPortNode(eo)){
				if (validateDropLocation(eObjectWithDotPath)){
					addPortNode(eObjectWithDotPath);
				}
			}
			else if (isRepresentedByEdge(eo)){
				addEdge(eObjectWithDotPath);
			}

		}

		addAssociations();

		addExternComposition();

		//sort list
		Collections.sort(sgraphMLObjectsList, ModelObjectsComparator);

		return sgraphMLObjectsList;
	}

	protected void reset() {
		sgraphMLObjectsList.clear();
		actualDroppedEObjects.clear();
		mapImageName2ID.clear();
		highestID = 0;
		int nColumns = approximateSquare(selectedTreeNodes.size());
		topLevelGrid.setColumns(nColumns);
		topLevelGrid.reset(userDropLocationModelCoords);
		groupNodeDropTargetDotPath = null;
	}

	/***
	 * Calculates the square size corresponding to n grid elements. For 2 elements, we prefer
	 * one row with two columns rather than two rows with one column.
	 * @param nElements
	 * @return
	 */
	protected int approximateSquare(int n){
		return (n==2)?2:(int)Math.round(Math.sqrt(n));		
	}

	protected void addExternComposition(){

		//drop on Canvas?
		if (groupNodeDropTargetDotPath == null){

			for (EObjectWithDotPath droppedWithPath : actualDroppedEObjects){
				String droppedPath = droppedWithPath.dotPath;

				//is there an real or virtual owner of droppedPath in the diagram?
				String parentPath = EAXMLprocessor.toFatherPath(droppedPath);

				//if we dropped a top-level element, it cannot be in a composition
				if (!parentPath.isEmpty()) {
					checkIfOwnerInDiagram(droppedWithPath, parentPath);
				}

				//second case: is there a real/Virtual child in the diagram?
				//The node must be a groupnode to have containment children
				if (isRepresentedByGroupNode(droppedWithPath.eObject)){
					checkIfChildInDiagram(droppedWithPath);
				}
			}
		}
	}
	//is there a real/Virtual child in the diagram?
	protected void checkIfChildInDiagram(EObjectWithDotPath droppedWithPath) {
		List<NodeType> nodes = ModelProcessor.INSTANCE.getAllNodes();

		for (NodeType node : nodes) {
			String fatherID = EAXMLprocessor.toFatherPath(node.getId());
			if (fatherID.equals(droppedWithPath.dotPath)){
				//node is a real/virtual containment child

				EObject child = EAXMLprocessor.getEObjectbyDotPath(node.getId());

				//Find containment ref feature for relation				
				for (EReference ref : droppedWithPath.eObject.eClass().getEAllContainments()) {

					boolean bDrawEdge = false;
					List<EObject> targetCandidates = new ArrayList<EObject>();
					if (ref.isMany()){
						targetCandidates = (EList<EObject>)droppedWithPath.eObject.eGet(ref);
					}
					else{
						EObject targetCandidate = (EObject)droppedWithPath.eObject.eGet(ref);
						targetCandidates.add(targetCandidate);
					}

					//Check all objects that this feature points to 
					for (EObject candidate : targetCandidates) {
						if (candidate.equals(child)){
							bDrawEdge = true;
							break;
						}
					}

					if (bDrawEdge){
						String edgeID = droppedWithPath.dotPath + ref.getName() + node.getId();
						addEdge(edgeID, droppedWithPath.dotPath, node.getId(), ref.getName(), LineTypeType.LINE, ArrowTypeType.DIAMOND, ArrowTypeType.NONE);
						break;
					}
				}
			}
		}
	}

	protected void checkIfOwnerInDiagram(EObjectWithDotPath droppedWithPath,
			String parentPath) {

		NodeType parentNode = ModelProcessor.INSTANCE.findNode(parentPath);

		if (parentNode != null){

			//parentNode is in diagram. Find EObject in Model.
			String groupNodeDotPath = parentNode.getId();

			EObject parentGroupNode = EAXMLprocessor.getEObjectbyDotPath(groupNodeDotPath);
			boolean bDrawEdge = false;

			//Find containment ref feature for relation				
			for (EReference ref : parentGroupNode.eClass().getEAllContainments()) {

				List<EObject> targetCandidates = new ArrayList<EObject>();
				if (ref.isMany()){
					targetCandidates = (EList<EObject>)parentGroupNode.eGet(ref);
				}
				else{
					EObject targetCandidate = (EObject)parentGroupNode.eGet(ref);
					if (targetCandidate != null) 
						{
							targetCandidates.add(targetCandidate) ;
						}
				}

				//Check all objects that this feature points to 
				for (EObject candidate : targetCandidates) {
					if (candidate.equals(droppedWithPath.eObject)){
						bDrawEdge = true;
						break;
					}
				}

				if (bDrawEdge){
					String edgeID = parentPath + ref.getName() + droppedWithPath.dotPath;
					addEdge(edgeID, parentPath, droppedWithPath.dotPath, ref.getName(), LineTypeType.LINE, ArrowTypeType.DIAMOND, ArrowTypeType.NONE);
					break;
				}

			}

		}
	}


	//Which elements? Satisfy_SatisfiedBy, Refine_refinedBy, VVCase_vvSubject, (VVTarget_element), 
	//FunctionModelling; FunctionConnector_port - already handled, FunctionAllocatgion_target, FunctionAllocation_allocatedElement
	//Dependability: ErrorModel_Protoype_functionTarget, ErrorModel_Prototype_hwTarget, ...
	//Infrastructure elements: Realizatiom_realized, Realization_RealizedBy

	private void addAssociations() {

		//outgoing cross-references, i.e. non-containment references, and InstanceRefs from dropped objects
		for (EObjectWithDotPath droppedWithPath : actualDroppedEObjects){
			addVirtualEdgeForCrossReferences(droppedWithPath, true);
			addEdgeForInstanceRef(droppedWithPath, true);

		}

		//Incoming cross-references and instanceRefs, from diagram model objects to dropped objects
		List<NodeType> nodes = ModelProcessor.INSTANCE.getAllNodes();
		for (NodeType node : nodes) {
			String dotPath = node.getId();
			String eastADLPath = EAXMLprocessor.dotPath2EastADLPath(dotPath);
			EObject nodeRef = EAXMLprocessor.getEObjectbyEastADLPath(eastADLPath);
			if (nodeRef!= null){
				addVirtualEdgeForCrossReferences(new EObjectWithDotPath(dotPath, nodeRef), false);
				addEdgeForInstanceRef(new EObjectWithDotPath(dotPath, nodeRef), false);
			}
		}		
	}

	/**
	 * Adds edge for an InstanceRef, except EAConnectors.
	 * @param droppedWithPath
	 */
	protected void addEdgeForInstanceRef(EObjectWithDotPath droppedWithPath, boolean bTargetModelObjects) {
		EObject eo = droppedWithPath.eObject;

		ArrayList<EObject> instRefChildren = ModelSearcher.getInstanceRefChildren(eo);
		if (!instRefChildren.isEmpty()){
			for (EObject instRefChild : instRefChildren) {

				String instRefPath;
				if (eo instanceof EAConnector){
					//connectors are handled elsewhere
					continue;
				}
				else{
					instRefPath = EAXMLprocessor.instRef2DotPath(instRefChild);
				}

				//is instRefPath among other dropped objects?			
				boolean bDrawEdge = false;

				EObjectWithDotPath target = null;

				int targetIndx = actualDroppedEObjects.indexOf(new EObjectWithDotPath(instRefPath, null));
				if (targetIndx != -1){
					target = actualDroppedEObjects.get(targetIndx);
					bDrawEdge = true;
				}
				else if (bTargetModelObjects){
					//is instRefPath already on the Canvas?
					NodeType node = ModelProcessor.INSTANCE.findNode(instRefPath);
					if (node != null){
						target = new EObjectWithDotPath(instRefPath, null);
						bDrawEdge = true;
					}
				}
				
				
				String roleName = EAXMLprocessor.getRoleName(instRefChild);
				
				if (bDrawEdge){
					String virtualEdgePath = droppedWithPath.dotPath + ":" + roleName + ":" + target.dotPath; 
					addEdge(virtualEdgePath, droppedWithPath.dotPath, target.dotPath, roleName, LineTypeType.DASHED, ArrowTypeType.NONE, ArrowTypeType.PLAIN);
				}

			}
		}
	}


	/***
	 * If there's a cross-reference from sourceElement to target element, where target element is 
	 * a droppedObject (or a diagram model object if bTargetModelObject is true), then an edge 
	 * is added to the diagram.
	 * 
	 * bTargetModelObjetcs - if the diagram model objects shall be checked as possible targets
	 */
	protected void addVirtualEdgeForCrossReferences(EObjectWithDotPath sourceElement, boolean bTargetModelObjects) {

		EObject eoDropped = sourceElement.eObject;

		//check all cross-references, i.e. non-containment references
		for (@SuppressWarnings("rawtypes") EContentsEList.FeatureIterator featureIterator = 
				(EContentsEList.FeatureIterator)eoDropped.eCrossReferences().iterator();
				featureIterator.hasNext(); )
		{
			EObject target = (EObject)featureIterator.next();
			EReference ref = (EReference)featureIterator.feature();

			if (ref.getEType().getName().equals("GEAPackageableElement") ||
					ref.getEType().getName().equals("GEAPackage")) 			    		
			{
				//skip infrastructure elements like gEAPackage_element etc.
				continue;
			}
            
			if (target.eIsProxy()){
				//skip proxy objects as a reference to a missing object, for example a reference to a type that is not there
				continue;
			}
			
			//om target är en missing länk ballar det ur här. Filtrera bort det. hur kolla. URI = undefined och skräp.
			
			
			boolean bDrawEdge = false;

			//is target among dropped objects?
			EObjectWithDotPath targetWithPath = findEObjectWithPathfromEObject(actualDroppedEObjects, target);
			String targetDotPath = null;
			if (targetWithPath != null){
				bDrawEdge = true;
				targetDotPath = targetWithPath.dotPath;
			}
			else if (bTargetModelObjects){
				//is target already in diagram? (path must be real node since not an instance ref.)						

				String targetPathWithSlashes = EAXMLprocessor.getSafeAbsoluteQualifiedName(target);
				targetDotPath = EAXMLprocessor.eastADLPath2dotPath(targetPathWithSlashes);
				NodeType node = ModelProcessor.INSTANCE.findNode(targetDotPath);

				if (node != null){
					bDrawEdge = true;
				}
			}
			if (bDrawEdge){

				String virtualEdgePath = sourceElement.dotPath + ":" + ref.getName() + ":" + targetDotPath; 
				addEdge(virtualEdgePath, sourceElement.dotPath, targetDotPath, ref.getName(), LineTypeType.LINE, ArrowTypeType.NONE, ArrowTypeType.PLAIN);
			}

		}
	}

	protected EObjectWithDotPath findEObjectWithPathfromEObject(List<EObjectWithDotPath> list, EObject eo){
		for (EObjectWithDotPath eoWithPath : list) {
			if (eoWithPath.eObject == eo)
				return eoWithPath;
		}
		return null;
	}



	protected void addPortNode(EObjectWithDotPath portNodeWithPath){

		EAPort port = (EAPort)portNodeWithPath.eObject;


		//GroupNode
		EObject groupNode = portNodeWithPath.eObject.eContainer();
		EObjectWithDotPath groupNodeWithPath = new EObjectWithDotPath(EAXMLprocessor.toFatherPath(portNodeWithPath.dotPath), groupNode);

		//Geometry
		GeometryType portGeometry = SgraphmlFactory.eINSTANCE.createGeometryType();
		portGeometry.setHeight(nPortHeight);
		portGeometry.setWidth(nPortWidth);
		Point currentLoc = topLevelGrid.getCurrentLocation();
		portGeometry.setX(currentLoc.x);
		portGeometry.setY(currentLoc.y);

		//Alternativ 1:
		//getModel() -> får upp graphml-objeketet
		//EditPart mappar till graf själv



		EADirectionKind direction = portDirection(port);

		NodeType portNode = createPortNode(groupNodeWithPath, portGeometry, port, direction);
		sgraphMLObjectsList.add(portNode);

	}


	protected boolean validateDropLocation(EObjectWithDotPath eoWithPath) {
		//Is the drop location valid? dropped on the right groupnode or directly on the canvas
		GraphicalViewer viewer = Utils.getGraphicalViewer();

		
		//IFigure contentPane=((GraphicalEditPart)getHost()).getContentPane();
	    //contentPane.translateToRelative(pt);
		
		
		EditPart targetPartSC = viewer.findObjectAt(userDropLocationScreenCoords);

		
//		EditPart targetPartMC = viewer.findObjectAt(userDropLocationModelCoords);

		
		EditPart targetPart = targetPartSC;
		
		
		boolean validDrop = false;
		String groupNodeDotPath = null;

		if (targetPart instanceof ScalableRootEditPart){
			//but we don't allow putting ports directly on the Canvas
			validDrop = !(eoWithPath.eObject instanceof EAPort);
		}
		else if (targetPart instanceof GroupNodeEditPart)
		{
			GroupNodeType groupNode = (GroupNodeType)targetPart.getModel();

			//make sure it's the right GroupNode
			NodeType gmlNode = (NodeType)(groupNode.eContainer().eContainer()); 
			groupNodeDropTargetDotPath = gmlNode.getId();


			if (groupNodeDropTargetDotPath.equals(EAXMLprocessor.toFatherPath(eoWithPath.dotPath))){
				validDrop = true;
			}			
		}


		if (!validDrop)
		{
			if (eoWithPath.eObject instanceof EAPort){
				Utils.showErrorMessage("The port " + EAXMLprocessor.elementName(eoWithPath.eObject) + " can only be dropped in its container.");
			}
			else{
				Utils.showErrorMessage("The element " + EAXMLprocessor.elementName(eoWithPath.eObject) + " can only be dropped directly on the Canvas or in its container.");
			}
			return false;
		}
		return true;
	}


	protected EADirectionKind portDirection(EAPort port){

		if (port instanceof FunctionFlowPort)
			return ((FunctionFlowPort)port).getDirection();
		else if (port instanceof FailureOutPort){
			return EADirectionKind.OUT;
		}
		else if (port instanceof FaultInPort){
			return EADirectionKind.IN;
		}
		else if (port instanceof HardwarePin){
			return ((HardwarePin)port).getDirection();
		}
		else if (port instanceof HardwarePort){
			return HardwareComponentTypeContentProvider.hwPortDirectionKind((HardwarePort)port);
		}
		else {
			Utils.showErrorMessage("Port type " + port.eClass().getName() + " not supported.");
			return EADirectionKind.INOUT;
		}
	}


	/***
	 * Checks that the source and target nodes are in the model or about to be written to the model due
	 * to the current dnd operation. If so, a new edge node is put in the list.	
	 * @param edgeWithPath
	 */
	private void addEdge(EObjectWithDotPath edgeWithPath) {

		//create Edge node
		EdgeType graphmlEdge = createEdge(edgeWithPath);
		DataType dataEdgeGraphics = createAndAttachEdgeGraphicsKey(graphmlEdge);

		//add source and target attribute...
		EAConnector connector = (EAConnector)edgeWithPath.eObject;

		IConnectionInstRefsProvider pathsProvider = (IConnectionInstRefsProvider)connectionAdapterFactory.getAdapter(connector, IConnectionInstRefsProvider.class);

		if (pathsProvider.getNumberOfPorts() != 2){
			Utils.showErrorMessage("The Connector " + EAXMLprocessor.elementName(connector) + " does not have exactly 2 ports.");
		}

		boolean bSourceOk = true;
		boolean bTargetOk = true;

		EObject sourceRef = pathsProvider.getSourceInstancRef();
		String sourcePath = EAXMLprocessor.instRef2DotPath(edgeWithPath.dotPath, sourceRef);
		NodeType sourceNode = ModelProcessor.INSTANCE.findNode(sourcePath);
		if (sourceNode == null)	{
			if (!actualDroppedEObjects.contains(new EObjectWithDotPath(sourcePath, null)))	{
				bSourceOk = false;
			}
		}

		EObject targetRef = pathsProvider.getTargetInstanceRef();
		String targetPath = EAXMLprocessor.instRef2DotPath(edgeWithPath.dotPath, targetRef);
		NodeType targetNode = ModelProcessor.INSTANCE.findNode(targetPath);
		if (targetNode == null)	{
			if (!actualDroppedEObjects.contains(new EObjectWithDotPath(targetPath, null))) {
				bTargetOk = false;
			}
		}


		if (!bSourceOk && bTargetOk) {
			Utils.showInformationMessage("Element: " + edgeWithPath.dotPath + "\n\nThe Node\n\n " + sourcePath + "\n\nis not on Canvas.");
			return;
		}
		else if (bSourceOk && !bTargetOk) {
			Utils.showInformationMessage("Element: " + edgeWithPath.dotPath + "\n\nThe Node\n\n " + targetPath + "\n\nis not on Canvas.");
			return;
		}
		else if (!bSourceOk && !bTargetOk) {
			Utils.showInformationMessage("Element: " + edgeWithPath.dotPath + "\n\nThe Nodes\n\n" + sourcePath + targetPath + "\n\nare not on Canvas.");
			return;
		}

		setEdgeAttributes(graphmlEdge, sourcePath, targetPath, "", ArrowTypeType.NONE, ArrowTypeType.NONE, LineTypeType.LINE);
		actualDroppedEObjects.add(edgeWithPath);
	}

	/***
	 * Adds an edge that has no corresponding (real path or virtual path) EObject in the model.
	 * 
	 * @param virtualEdgePath
	 * @param sourcePath
	 * @param targetPath
	 */
	protected void addEdge(String virtualEdgeID, String sourcePath, String targetPath, String label, LineTypeType pattern, 
			ArrowTypeType sourceArrow, ArrowTypeType targetArrow){


		//create Edge node
		EdgeType graphmlEdge = XmlnsFactory.eINSTANCE.createEdgeType();
		graphmlEdge.setElementType("Not applicable");
		graphmlEdge.setId(virtualEdgeID);

		DataType dataEdgeGraphics = createAndAttachEdgeGraphicsKey(graphmlEdge);

		setEdgeAttributes(graphmlEdge, sourcePath, targetPath, label, sourceArrow, targetArrow, pattern);
	}


	protected void setEdgeAttributes(EdgeType graphmlEdge, String sourcePath, String targetPath,
			String label, ArrowTypeType sourceArrow, ArrowTypeType targetArrow, LineTypeType pattern) {

		DataType dataEdgeGraphics = graphmlEdge.getData().get(0);

		graphmlEdge.setSource(sourcePath);
		graphmlEdge.setTarget(targetPath);

		//PolylineEdge
		PolyLineEdgeType polyLineEdge = SgraphmlFactory.eINSTANCE.createPolyLineEdgeType();
		FeatureMap fmEdgeGraphics = dataEdgeGraphics.getMixed();
		fmEdgeGraphics.add(SgraphmlPackage.eINSTANCE.getDocumentRoot_PolyLineEdge(), polyLineEdge);

		//Path
		PathType path = SgraphmlFactory.eINSTANCE.createPathType();
		path.setSx(0);
		path.setSy(0);
		path.setTx(0);
		path.setTy(0);
		polyLineEdge.setPath(path);

		//LineStyle
		LineStyleType lineStyle = SgraphmlFactory.eINSTANCE.createLineStyleType();
		lineStyle.setColor("#000000");
		lineStyle.setType(pattern);
		lineStyle.setWidth(1);
		polyLineEdge.setLineStyle(lineStyle);

		//Arrows
		ArrowsType arrows = SgraphmlFactory.eINSTANCE.createArrowsType();	
		arrows.setSource(sourceArrow);
		arrows.setTarget(targetArrow);
		polyLineEdge.setArrows(arrows);

		if (!label.isEmpty()){
			EdgeLabelType edgeLabel = SgraphmlFactory.eINSTANCE.createEdgeLabelType();
			edgeLabel.setFontFamily("Dialog");
			edgeLabel.setFontSize((short)12);
			edgeLabel.setTextColor("#000000");
			edgeLabel.setVisible(true);
			FeatureMap fmEdgeLabel = edgeLabel.getMixed();

			FeatureMapUtil.addText(fmEdgeLabel, label);
			polyLineEdge.setEdgeLabel(edgeLabel);
		}

		sgraphMLObjectsList.add(graphmlEdge);
	}


	protected GroupNodeType createGroupNode(EObjectWithDotPath eoWithPath){
		//graphmlNode
		NodeType node = createNode(eoWithPath);
		DataType dataNodeGraphics = createAndAttachNodeGraphicsKey(node);
		FeatureMap fmDataNodeGraphics = dataNodeGraphics.getMixed();

		//GroupNode
		GroupNodeType groupNode = SgraphmlFactory.eINSTANCE.createGroupNodeType();
		ShapeType shape = SgraphmlFactory.eINSTANCE.createShapeType();
		ShapeTypeType shapeType = VisualAttributes.INSTANCE.getShape(eoWithPath.eObject);
		shape.setType(shapeType);
		groupNode.setShape(shape);

		//add graph to the graphml node
		GraphType graph = XmlnsFactory.eINSTANCE.createGraphType();
		graph.setId(node.getId() + ":");
		graph.setEdgedefault(GraphEdgedefaultType.DIRECTED);
		node.getGraph().add(graph);

		fmDataNodeGraphics.add(SgraphmlPackage.eINSTANCE.getDocumentRoot_GroupNode(), groupNode);
		actualDroppedEObjects.add(eoWithPath);

		return groupNode;
	} 



	protected Dimension setGroupNodeAttributes(EObjectWithDotPath eoWithPath, GroupNodeType groupNode, 
			GraphType graph, Point topLeft, Dimension contentSize, Dimension contentMargin,
			List<EAPort> inports, List<EAPort> outports, List<EAPort> inoutports){


		int nInports = inports.size();
		int nOutports = outports.size();
		int nInOutports = inoutports.size();

		FillType fill = SgraphmlFactory.eINSTANCE.createFillType();
		String color = VisualAttributes.INSTANCE.getBackgroundColor(eoWithPath.eObject);
		fill.setColor(color);
		String color2 = VisualAttributes.INSTANCE.getBackgroundColor2(eoWithPath.eObject);
		fill.setColor2(color2);
		groupNode.setFill(fill);

		LineStyleType border = SgraphmlFactory.eINSTANCE.createLineStyleType();
		String lineColor = VisualAttributes.INSTANCE.getLineColor(eoWithPath.eObject);
		border.setColor(lineColor);
		Float lineWidth = VisualAttributes.INSTANCE.getLineWidth(eoWithPath.eObject);
		border.setWidth(lineWidth);
		border.setType(LineTypeType.LINE);
		groupNode.setBorderStyle(border);

		NodeLabelType label = SgraphmlFactory.eINSTANCE.createNodeLabelType();
		label.setFontFamily("Dialog");
		label.setFontSize((short) 12);
		label.setIconTextGap((byte)4);
		label.setTextColor("#000000");
		if ((groupNode.getShape().getType() == ShapeTypeType.ELLIPSE)){
			label.setHorizontalTextPosition(HorizontalTextPositionType.RIGHT);
			label.setPlacement(PlacementType.CENTER);
			label.setVerticalTextPosition(VerticalTextPositionType.CENTER);
		}
		else{
			//roundrectangle or rectangle
			label.setHorizontalTextPosition(HorizontalTextPositionType.RIGHT);
			label.setPlacement(PlacementType.TOP);
			label.setVerticalTextPosition(VerticalTextPositionType.CENTER);
		}
		
		label.setVisible(true);
		FeatureMap labelFM = label.getMixed();
		FeatureMapUtil.addText(labelFM, EAXMLprocessor.elementName(eoWithPath.eObject));
		String iconID = addLabelImageResources(eoWithPath.eObject.eClass().getName());
		label.setIconData(iconID);				
		groupNode.getNodeLabel().add(label);

		GeometryType geometry = SgraphmlFactory.eINSTANCE.createGeometryType();
		geometry.setX(topLeft.x);
		geometry.setY(topLeft.y);

		
		Dimension dTopLevelSize;
		if (groupNode.getShape().getType() == ShapeTypeType.ELLIPSE){
			dTopLevelSize = new Dimension(150,100);
		}
		else{
			//Rectangular or RoundRectangle
			dTopLevelSize = calculateGroupNodeSize(nInports, nOutports, nInOutports);
			if (!contentSize.isEmpty()){
				dTopLevelSize = Dimension.max(dTopLevelSize, contentSize.expand(contentMargin.getScaled(2)));
			}	
			
		}
		geometry.setWidth(dTopLevelSize.width);
		geometry.setHeight(dTopLevelSize.height);
		groupNode.setGeometry(geometry);

		return dTopLevelSize;
	}

	private Dimension addGroupNode(EObjectWithDotPath groupNodeWithPath) {

			boolean bMayHavePorts = (groupNodeWithPath.eObject instanceof EAType) || (groupNodeWithPath.eObject instanceof EAPrototype);

			//For a prototype, make sure there is a type defined 
			if (groupNodeWithPath.eObject instanceof EAPrototype){
				EAType type = null;
			
				if (groupNodeWithPath.eObject instanceof AnalysisFunctionPrototype){
					type = ((AnalysisFunctionPrototype)(groupNodeWithPath.eObject)).getType();
				}
				else if (groupNodeWithPath.eObject instanceof DesignFunctionPrototype){
					type = ((DesignFunctionPrototype)(groupNodeWithPath.eObject)).getType();
					
				}
				if (type == null){
					Utils.showErrorMessage("The prototype " + ((EAElement)groupNodeWithPath.eObject).getShortName() + " has no type defined.");
					return new Dimension(0,0);
				}
			}
			
		
		List<EAPort> inports = new ArrayList<EAPort>();
		List<EAPort> outports = new ArrayList<EAPort>();
		List<EAPort> inoutports = new ArrayList<EAPort>();

		if (bMayHavePorts){
			ITypePrototypeContentProvider portsProvider = (ITypePrototypeContentProvider)groupNodeAdapterFactory.getAdapter(groupNodeWithPath.eObject, ITypePrototypeContentProvider.class);
			inports = portsProvider.getInports(); 
			outports = portsProvider.getOutports(); 
			inoutports = portsProvider.getInOutports(); 
		}

		Dimension topLevelNodeSize = new Dimension();
		Dimension contentMargin = new Dimension(100,100);
		Point contentTopLeft = topLevelGrid.getCurrentLocation().translate(contentMargin);

		GroupNodeType sgmlTopLevelGroupNode = createGroupNode(groupNodeWithPath); 
		NodeType gmlTopLevelGroupNode = (NodeType)sgmlTopLevelGroupNode.eContainer().eContainer();
		GraphType groupNodeGraph = (GraphType)gmlTopLevelGroupNode.getGraph().get(0);

		Dimension contentSize = new Dimension(0,0);
		if (bMayHavePorts){

			attachPortsWithoutGeometry(groupNodeWithPath, groupNodeGraph, inports, outports, inoutports);

			//Ports must be in the actualdropped list here, since we check that edges source and target are there
			contentSize = attachOrAddAdditionalGroupNodeContent(groupNodeWithPath, gmlTopLevelGroupNode, contentTopLeft);
		}

		topLevelNodeSize = setGroupNodeAttributes(groupNodeWithPath, sgmlTopLevelGroupNode, groupNodeGraph, topLevelGrid.getCurrentLocation(), 
				contentSize, contentMargin, inports, outports, inoutports);

		if (bMayHavePorts){
			setPortGeometry(groupNodeWithPath, groupNodeGraph, sgmlTopLevelGroupNode.getGeometry(), inports, outports, inoutports);		
		}

		sgraphMLObjectsList.add(gmlTopLevelGroupNode);

		return topLevelNodeSize;
	}


	/***
	 * Attaches other content than ports to EATypes
	 * 
	 * For Prototypes, we do not add other content
	 * 
	 * @param groupNodeWithPath
	 */
	protected Dimension attachOrAddAdditionalGroupNodeContent(EObjectWithDotPath groupNodeWithPath, NodeType gmlGroupNode, Point topLeft){

		EObject eoGroupNode= groupNodeWithPath.eObject;	

		GridLayout contentGrid = new GridLayout();

		//Ports already added when groupnode created
		if (eoGroupNode instanceof EAType){
			{
				//attach prototypes to groupnode object
				EAType groupNode = (EAType)eoGroupNode;

				//Get parts
				ITypePrototypeContentProvider contentProvider = (ITypePrototypeContentProvider)groupNodeAdapterFactory.getAdapter(groupNode, ITypePrototypeContentProvider.class);
				List<EAPrototype> prototypes = contentProvider.getParts();

				//Balance rows and columns in grid by setting #columns = sqrt(prototypes). Special case 2 elements => one row.
				int nColumns = approximateSquare(prototypes.size());
				contentGrid.setColumns (nColumns);
				contentGrid.reset(topLeft);

				for (EAPrototype prototype : prototypes) {
					ITypePrototypeContentProvider childContentProvider = (ITypePrototypeContentProvider)groupNodeAdapterFactory.getAdapter(prototype, ITypePrototypeContentProvider.class);

					List<EAPort> childInports = childContentProvider.getInports();
					List<EAPort> childOutports = childContentProvider.getOutports();
					List<EAPort> childInoutports = childContentProvider.getInOutports();

					String childGroupNodePath = groupNodeWithPath.dotPath + "." + EAXMLprocessor.elementName(prototype);

					EObjectWithDotPath childGroupNodeWithPath = new EObjectWithDotPath(childGroupNodePath, prototype);
					GroupNodeType sgmlChildGroupNode = createGroupNode(childGroupNodeWithPath);//, contentGrid.getCurrentLocation(), dSize);
					NodeType gmlChildGroupNode = (NodeType)sgmlChildGroupNode.eContainer().eContainer();
					GraphType graph = gmlChildGroupNode.getGraph().get(0);

					attachPortsWithoutGeometry(childGroupNodeWithPath, graph, childInports, childOutports, childInoutports);


					Dimension zeroSize = new Dimension(0,0);
					Dimension childSize = setGroupNodeAttributes(childGroupNodeWithPath, sgmlChildGroupNode, graph, contentGrid.getCurrentLocation(), zeroSize, 
							zeroSize, childInports, childOutports, childInoutports);

					setPortGeometry(childGroupNodeWithPath, graph, sgmlChildGroupNode.getGeometry(), childInports, childOutports, childInoutports);

					contentGrid.calculateNextGridPosition(childSize);

					//attach the new child groupnode to the parent groupnode
					gmlGroupNode.getGraph().get(0).getNode().add(gmlChildGroupNode);	
				}

				//add connectors as separate objects
				List<EAConnector> connectors = contentProvider.getConnectors();


				for (EAConnector connector : connectors) {

					String edgeDotPath = groupNodeWithPath.dotPath + "." + EAXMLprocessor.elementName(connector);
					EObjectWithDotPath edgeWithPath = new EObjectWithDotPath(edgeDotPath, connector);

					addEdge(edgeWithPath);
				}
			}
		}		

		return contentGrid.getCurrentSize();
	}



	/***
	 *  
	 */

	private void attachPortsWithoutGeometry(EObjectWithDotPath groupNode, GraphType parentGraph, List<EAPort> inports, List<EAPort> outports, List<EAPort> inoutports ) {

		NodeType portNode;
		GeometryType zeroGeo = SgraphmlFactory.eINSTANCE.createGeometryType();

		for (EAPort port : inports) {
			portNode = createPortNode(groupNode, zeroGeo, port, EADirectionKind.IN);
			parentGraph.getNode().add(portNode);
		}

		for (EAPort port : outports) {
			portNode = createPortNode(groupNode, zeroGeo, port, EADirectionKind.OUT);
			parentGraph.getNode().add(portNode);
		}

		for (EAPort port : inoutports) {	
			portNode = createPortNode(groupNode, zeroGeo, port, EADirectionKind.INOUT);
			parentGraph.getNode().add(portNode);
		}	
	}

	private void setPortGeometry(EObjectWithDotPath groupNode, GraphType parentGraph, GeometryType parentGeometry, List <EAPort> inports, List <EAPort> outports, List <EAPort> inoutports ) {

		int inPortNr = 1;
		int outPortNr = 1;
		int inoutPortNr = 1;

		GeometryType portGeo;
		PortNodeType portNode;
		String portDotPath;

		for (EAPort port : inports) {
			portGeo = calcPortGeometry(parentGeometry, EADirectionKind.IN, inPortNr, inports.size());
			portDotPath = groupNode.dotPath + "." + EAXMLprocessor.elementName(port);
			portNode = ModelProcessor.INSTANCE.findPortNode(parentGraph, portDotPath);
			portNode.setGeometry(portGeo);
			inPortNr++;
		}

		for (EAPort port : outports) {
			portGeo = calcPortGeometry(parentGeometry, EADirectionKind.OUT, outPortNr, outports.size());
			portDotPath = groupNode.dotPath + "." + EAXMLprocessor.elementName(port);
			portNode = ModelProcessor.INSTANCE.findPortNode(parentGraph, portDotPath);
			portNode.setGeometry(portGeo);
			outPortNr++;
		}

		for (EAPort port : inoutports) {	
			portGeo = calcPortGeometry(parentGeometry, EADirectionKind.INOUT, inoutPortNr, inoutports.size());
			portDotPath = groupNode.dotPath + "." + EAXMLprocessor.elementName(port);
			portNode = ModelProcessor.INSTANCE.findPortNode(parentGraph, portDotPath);
			portNode.setGeometry(portGeo);
			inoutPortNr++;
		}	
	}

	protected GeometryType calcPortGeometry(GeometryType parentGeometry, EADirectionKind direction, int portNr, int ports){

		//Geometry
		GeometryType geometry = SgraphmlFactory.eINSTANCE.createGeometryType();
		//		Point p = topLevelGrid.getCurrentLocation();
		switch (direction){
		case IN:
			geometry.setX(parentGeometry.getX());
			geometry.setY(parentGeometry.getY() + parentGeometry.getHeight() / (ports + 1) * portNr - nPortHeight/2); //nTopMargin + (portNr - 1) * (nPortHeight + nPortDistance));
			break;
		case INOUT:
			geometry.setX(parentGeometry.getX() + parentGeometry.getWidth() / (ports + 1) * portNr);
			geometry.setY(parentGeometry.getY() + parentGeometry.getHeight() - nPortHeight);
			break;
		case OUT:
			geometry.setX(parentGeometry.getX() + parentGeometry.getWidth() - nPortWidth);
			geometry.setY(parentGeometry.getY() + parentGeometry.getHeight() / (ports + 1) * portNr - nPortHeight/2);  //nTopMargin + (portNr - 1) * (nPortHeight + nPortDistance));
			break;
		default:
			break;
		};
		geometry.setWidth(nPortWidth);
		geometry.setHeight(nPortHeight);

		return geometry;
	}


	//protected void addPort(EObjectWithDotPath groupNode, GraphType parent, GeometryType parentGeometry, EAElement port, EADirectionKind direction, int portNr, int ports){
	protected NodeType createPortNode(EObjectWithDotPath groupNode, GeometryType portGeometry, EAPort port, EADirectionKind direction){

		//Create graphmlnode & nodegraphics
		String portDotPath = groupNode.dotPath + "." + EAXMLprocessor.elementName(port);
		EObjectWithDotPath portWithPath = new EObjectWithDotPath (portDotPath, (EObject)port);
		NodeType node = createNode(portWithPath);
		DataType dataNodeGraphics = createAndAttachNodeGraphicsKey(node);

		//Create PortNode
		PortNodeType portNode = SgraphmlFactory.eINSTANCE.createPortNodeType();

		//Borderstyle
		LineStyleType border = SgraphmlFactory.eINSTANCE.createLineStyleType();
		border.setColor("#000000");
		Float lineWidth = VisualAttributes.INSTANCE.getLineWidth(port);
		border.setWidth(lineWidth);
		border.setType(LineTypeType.LINE);
		portNode.setBorderStyle(border);

		//Fill
		FillType fill = SgraphmlFactory.eINSTANCE.createFillType();
		String color = VisualAttributes.INSTANCE.getBackgroundColor(port);
		fill.setColor(color);
		String color2 = VisualAttributes.INSTANCE.getBackgroundColor2(port);
		fill.setColor2(color2);
		portNode.setFill(fill);

		//Geometry
		portNode.setGeometry(portGeometry);

		//Label
		NodeLabelType label = SgraphmlFactory.eINSTANCE.createNodeLabelType();
		label.setFontFamily("Dialog");
		label.setFontSize((short) 10);
		label.setIconTextGap((byte)4);
		label.setVisible(true);
		label.setTextColor("#000000");

		String iconID = null;

		switch (direction){
		case IN:
			label.setPlacement(PlacementType.LEFT);
			label.setHorizontalTextPosition(HorizontalTextPositionType.RIGHT);
			label.setVerticalTextPosition(VerticalTextPositionType.CENTER);
			iconID = addLabelImageResources("inport_13x13");
			break;

		case INOUT:
			label.setPlacement(PlacementType.BOTTOM);
			label.setHorizontalTextPosition(HorizontalTextPositionType.CENTER);
			label.setVerticalTextPosition(VerticalTextPositionType.TOP);
			iconID = addLabelImageResources("inoutport_13x13"); 
			break;
		case OUT:
			label.setPlacement(PlacementType.RIGHT);
			label.setHorizontalTextPosition(HorizontalTextPositionType.LEFT);
			label.setVerticalTextPosition(VerticalTextPositionType.CENTER);				
			iconID = addLabelImageResources("outport_13x13");
			break;
		default:
			break;

		}

		FeatureMap labelFM = label.getMixed();
		FeatureMapUtil.addText(labelFM, EAXMLprocessor.elementName(port));

		label.setIconData(iconID);				

		portNode.getNodeLabel().add(label);

		//add port node to datanode
		FeatureMap fmNodeGraphics = dataNodeGraphics.getMixed();
		fmNodeGraphics.add(SgraphmlPackage.eINSTANCE.getDocumentRoot_PortNode(), portNode);

		actualDroppedEObjects.add(portWithPath);
		return node;
	}		



	/***
	 * 
	 * Calculates GroupNode Size, does only take ports & labels into account, i.e. no additional content
	 * 
	 * Height: Place inports to the left, outports to the right and assume
	 * equal vertical distribution.
	 * 
	 * Width: nInOutPorts
	 * 
	 * @param nInPorts
	 * @param nOutports
	 * @return
	 */
	public Dimension calculateGroupNodeSize(int nInPorts, int nOutPorts, int nInOutPorts){
		Dimension d = new Dimension();

		int nMaxPorts = Math.max(nInPorts, nOutPorts);

		d.height = nMaxPorts * nPortHeight + nPortDistance * (nMaxPorts - 1) + nTopMargin + nBottomMargin;

		//width based on inoutports
		int w1 = 0, w2 = 0;
		if (nInOutPorts > 0){
			w1 = 100 + nInOutPorts * 100;
		}

		//based on inports / outports
		if (nInPorts > 0 && nOutPorts > 0){
			w2 = 250;
		}
		else if ((nInPorts > 0) || (nOutPorts > 0)){
			w2 = 200;
		}
		else { //nOutPorts ports
			w2 = 150;
		}
		d.width = Math.max(w1, w2); 

		return d;
	}


	protected EdgeType createEdge(EObjectWithDotPath eoWithPath){
		EdgeType edge = XmlnsFactory.eINSTANCE.createEdgeType();
		edge.setElementType(eoWithPath.eObject.eClass().getName());
		edge.setId(eoWithPath.dotPath);
		return edge;
	}


	protected NodeType createNode(EObjectWithDotPath eoWitPath){
		NodeType node = XmlnsFactory.eINSTANCE.createNodeType();
		node.setElementType(eoWitPath.eObject.eClass().getName());
		node.setId(eoWitPath.dotPath);
		return node;
	}

	protected DataType createAndAttachNodeGraphicsKey(NodeType node){
		DataType data = XmlnsFactory.eINSTANCE.createDataType();
		data.setKey(ModelProcessor.INSTANCE.getNodeGraphicsKeyID());
		node.getData().add(data);
		return data;
	}

	protected DataType createAndAttachEdgeGraphicsKey(EdgeType edge){
		DataType data = XmlnsFactory.eINSTANCE.createDataType();
		data.setKey(ModelProcessor.INSTANCE.getEdgeGraphicsKeyID());
		edge.getData().add(data);
		return data;
	}

	protected Dimension addShapeNode(EObjectWithDotPath shapeNodeWithPath) {

		//create graphml Node and data key for node graphics
		NodeType node = createNode(shapeNodeWithPath);
		DataType dataNodeGraphics = createAndAttachNodeGraphicsKey(node);

		FeatureMap fmDataNodeGraphics = dataNodeGraphics.getMixed();
		ShapeNodeType shapeNode = SgraphmlFactory.eINSTANCE.createShapeNodeType();

		ShapeType shape = SgraphmlFactory.eINSTANCE.createShapeType();
	
		ShapeTypeType type = VisualAttributes.INSTANCE.getShape(shapeNodeWithPath.eObject);
		shape.setType(type);
		shapeNode.setShape(shape);

		FillType fill = SgraphmlFactory.eINSTANCE.createFillType();
		String color = VisualAttributes.INSTANCE.getBackgroundColor(shapeNodeWithPath.eObject);
		fill.setColor(color);
		String color2 = VisualAttributes.INSTANCE.getBackgroundColor2(shapeNodeWithPath.eObject);
		fill.setColor2(color2);
		shapeNode.setFill(fill);

		LineStyleType border = SgraphmlFactory.eINSTANCE.createLineStyleType();
		String lineColor = VisualAttributes.INSTANCE.getLineColor(shapeNodeWithPath.eObject);
		border.setColor(lineColor);
		Float lineWidth = VisualAttributes.INSTANCE.getLineWidth(shapeNodeWithPath.eObject);
		border.setWidth(lineWidth);
		border.setType(LineTypeType.LINE);
		shapeNode.setBorderStyle(border);

		NodeLabelType label = SgraphmlFactory.eINSTANCE.createNodeLabelType();
		label.setFontFamily("Dialog");
		label.setFontSize((short) 12);
		label.setHorizontalTextPosition(HorizontalTextPositionType.RIGHT);
		label.setIconTextGap((byte)4);
		label.setPlacement(PlacementType.CENTER);
		label.setTextColor("#000000");
		label.setVerticalTextPosition(VerticalTextPositionType.CENTER);
			
		label.setVisible(true);
		FeatureMap labelFM = label.getMixed();
		FeatureMapUtil.addText(labelFM, EAXMLprocessor.elementName(shapeNodeWithPath.eObject));


		//Fix image - resource objects
		String iconID = addLabelImageResources(shapeNodeWithPath.eObject.eClass().getName());
		label.setIconData(iconID);				

		shapeNode.getNodeLabel().add(label);

		GeometryType geometry = SgraphmlFactory.eINSTANCE.createGeometryType();
		Point p = topLevelGrid.getCurrentLocation();
		geometry.setX(p.x);
		geometry.setY(p.y);
		geometry.setWidth(100);
		geometry.setHeight(100);
		shapeNode.setGeometry(geometry);

		fmDataNodeGraphics.add(SgraphmlPackage.eINSTANCE.getDocumentRoot_ShapeNode(), shapeNode);

		//		sgraphMLObjectsList.add(new SGraphMLObjectWrapper(ModelProcessor.INSTANCE.getRootGraph(), node));
		sgraphMLObjectsList.add(node);
		actualDroppedEObjects.add(shapeNodeWithPath);

		Dimension size;
		if (type == ShapeTypeType.ELLIPSE){
			size = new Dimension(150,100);
		}
		else{
			size = new Dimension(100,100);
		}

		return size;
	}

	/**
	 * 
	 * 
	 * @return the ID of the image resource
	 */
	protected String addLabelImageResources(String imageName) {
		Image image = getImage(imageName);
		String id = Utils.INSTANCE.getResourceManager().findID(image);
		if (id != null)
		{
			//look for a scaled icon ressource 1.0 that refers to the image

			String idUnscaledIcon = Utils.INSTANCE.getResourceManager().findUnScaledIconResourceID(id);

			if (idUnscaledIcon == null)
			{
				//TODO: define a new ScaledIconResource 
				throw new UnsupportedOperationException("New scaled icon resource not implemented yet");
			}

			else {
				return idUnscaledIcon;
			}
		}
		else{
			//add new "Image" resource 
			ResourceType resourceNode = SgraphmlFactory.eINSTANCE.createResourceType();
			//			sgraphMLObjectsList.add(new SGraphMLObjectWrapper(ModelProcessor.INSTANCE.getResourceBlock(), resourceNode));
			sgraphMLObjectsList.add(resourceNode);

			//find id for resource
			int newID = 0;
			if (mapImageName2ID.isEmpty()){
				//first new id
				newID = Utils.INSTANCE.getResourceManager().highestID() + 1;
			}
			else {
				//already have newly allocated IDs
				newID = ++highestID;
			}
			mapImageName2ID.put(imageName, newID);


			resourceNode.setId(Integer.toString(newID));
			resourceNode.setType(SWTImageResource.getType());

			//add image string to resourceNode
			String encodedImage = SWTImageResource.encodeImageBase64(image);
			FeatureMap resourceFM = resourceNode.getMixed();
			FeatureMapUtil.addText(resourceFM, encodedImage);

			//add new resource to hold scaledIconresoure with scaling 1.0
			ImageIconType imageIconNode = SgraphmlFactory.eINSTANCE.createImageIconType();
			imageIconNode.setImage(Integer.toString(newID));

			ScaledIconType scaledIconNode = SgraphmlFactory.eINSTANCE.createScaledIconType();
			scaledIconNode.setXScale(1.0);
			scaledIconNode.setYScale(1.0);
			scaledIconNode.setImageIcon(imageIconNode);

			ResourceType scaledIconResourceNode = SgraphmlFactory.eINSTANCE.createResourceType();
			int scaledIconResourceNodeID = newID + 1;
			highestID = scaledIconResourceNodeID;
			scaledIconResourceNode.setId(Integer.toString(scaledIconResourceNodeID));
			FeatureMap scaledIconResourceNodeFM = scaledIconResourceNode.getMixed();
			scaledIconResourceNodeFM.add(SgraphmlPackage.eINSTANCE.getDocumentRoot_ScaledIcon(), scaledIconNode);

			//			sgraphMLObjectsList.add(new SGraphMLObjectWrapper(ModelProcessor.INSTANCE.getResourceBlock(), scaledIconResourceNode));
			sgraphMLObjectsList.add(scaledIconResourceNode);

			return Integer.toString(scaledIconResourceNodeID);
		}
	}

	/***
	 * Gets an image for the EObject by checking the MetaClass.
	 */
	private Image getImage(String imageName) {

		boolean fileExist = true;
		
		//try loading from the icons of this plugin - it contains some icons
		URL bundleURL = null;
		try{
			bundleURL = new URL(Activator.getDefault().getBundle().getEntry("/"),
					"icons/" + imageName + ".png");
		}
		catch (MalformedURLException e){
			System.out.print(e);
		}

		//Check if the file is there by resolving the URL
		try {
			URL fileURL = FileLocator.resolve(bundleURL); 

		} catch (Exception ex) {
			fileExist = false;
		}
	
		ImageDescriptor imageDesc;
		if (fileExist){
			imageDesc = ImageDescriptor.createFromURL(bundleURL);
	
			Image image = imageDesc.createImage(false);
	
			if (image != null)
			{
				return image;
			}
		}

		//load icon from the eastadl2112.edit plugin 
		try{
			bundleURL = new URL("platform:/plugin/org.eclipse.eatop.eastadl2112.edit/icons/full/obj16/" +
					imageName + ".gif");
		}
		catch (MalformedURLException e){
			System.out.print(e);
		}
		imageDesc = ImageDescriptor.createFromURL(bundleURL);

		return imageDesc.createImage();
	}



	protected int graphMLtypeToInt(EObject e){
		if (e instanceof ResourceType){ 
			return 10;
		}
		else if (e instanceof NodeType){
			return 20;
		}
		else if (e instanceof EdgeType){
			return 30;
		}
		throw new IllegalArgumentException();
	}

	protected int eastADLtypeToInt(EObject e){

		return (isRepresentedByEdge(e))?20:10;
	}

	/**
	 * Comparator to sort the list in the following order: Resources, Nodes, Edges
	 * 
	 * We need to write the objects in that order so that when a editPart detects a new node/edge, 
	 * the resources and node are already available.
	 */

	public Comparator<EObject> ModelObjectsComparator 
	= new Comparator<EObject>() {

		//0 = equal
		//positive:  eo1 > eo2
		//negative:  eo2 > eo1
		public int compare(EObject eo1, EObject eo2) {

			int i1 = graphMLtypeToInt(eo1);
			int i2 = graphMLtypeToInt(eo2);

			int delta = i1 - i2;
			return delta;
		}
	};

	/***
	 * Put the edges last, so their source and target nodes are handled before.
	 */
	public Comparator<EObjectWithDotPath> selectedObjectsComparator 
	= new Comparator<EObjectWithDotPath>() {

		//0 = equal
		//positive:  eo1 > eo2
		//negative:  eo2 > eo1
		public int compare(EObjectWithDotPath e1, EObjectWithDotPath e2) {

			int i1 = eastADLtypeToInt(e1.eObject);
			int i2 = eastADLtypeToInt(e2.eObject);

			int delta = i1 - i2;
			return delta;
		}
	};


	//FunctionType, FunctionPrototype cannot be instantiated
	protected boolean isRepresentedByGroupNode (EObject eo){
		///FunctionType - AnalysisFunctionType, DesignFunctionType, 
		//FunctionPrototype - AnalysisFunctionPrototype, DesignFunctionPrototype, FunctionalDevice, BasicSoftwareFunctionType, HardwareFunctionType, LocalDeviceManager
		//HardwareComponentType - Actuator, ElectricalComponent, Node, Sensor
		//return (eo instanceof EAPrototype) || (eo instanceof EAType);

		if ((eo instanceof EAPrototype) || (eo instanceof EAType)) return true;

		if ((eo instanceof EAConnector) || (eo instanceof EAPort)) return false;

		EList<EReference> references = eo.eClass().getEAllReferences();
		for (EReference eReference : references) {
			if ((eReference.isContainment() && 
					!((eReference.getName().equals("ownedComment") || 
							eReference.getName().equals("ownedRelationship"))))){
				return true;
			}
		}

		return false;
	}


	protected boolean isRepresentedByPortNode (EObject eo){
		//FaultFailurePort - FailureOutPort, FaultInPort
		//FunctionPort - FunctionClientServerPort, FunctionFlowPort, FunctionPowerPort
		//HardwarePin - CommunicationHardwarePin, IOHardwarePin, PowerHardwarePin
		//HardwarePort  
		return eo instanceof EAPort;
	}

	protected boolean isRepresentedByShapeNode (EObject eo){
		return (!isRepresentedByGroupNode(eo)) && (!isRepresentedByPortNode(eo) && (!isRepresentedByEdge(eo)));
	}

	protected boolean isRepresentedByEdge (EObject eo){
		//FunctionConnector
		//HardwareConnector
		//HardwarePortConnector
		//FaultFailurePropagationLink
		return eo instanceof EAConnector; 
	}


	@Override
	public Object getObjectType() {
		return List.class; 
	}

	public void setSelectedTreeNodes(List<EObjectWithDotPath> nodes) {
		selectedTreeNodes = nodes;
	}

	public void setLocation(Point location) {
		userDropLocationScreenCoords = new Point(location);
		userDropLocationModelCoords = Utils.screenPoint2ModelPoint(userDropLocationScreenCoords); 
	}

	
	
}
