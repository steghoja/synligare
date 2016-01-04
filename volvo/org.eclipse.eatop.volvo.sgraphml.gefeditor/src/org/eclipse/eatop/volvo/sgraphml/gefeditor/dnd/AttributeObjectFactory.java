package org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.EastAdlSgraphmlSynchronizer;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.Utils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.gef.requests.CreationFactory;

import eu.synligare.sgraphml.GeometryType;
import eu.synligare.sgraphml.HorizontalTextPositionType;
import eu.synligare.sgraphml.NodeLabelType;
import eu.synligare.sgraphml.PlacementType;
import eu.synligare.sgraphml.SgraphmlFactory;
import eu.synligare.sgraphml.VerticalTextPositionType;

/***
 * 
 * Creates SGraphML NodeLabelType object from the selected treenode and attribute  
 *
 */
public class AttributeObjectFactory implements CreationFactory {

	EObjectWithDotPath selectedTreeObject;
	String attribute;
	GeometryType parentGeometry;
	
	public GeometryType getParentGeometry() {
		return parentGeometry;
	}

	public void setParentGeometry(GeometryType geometry) {
		this.parentGeometry = geometry;
	}

	public EObjectWithDotPath getSelectedTreeObject() {
		return selectedTreeObject;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public void setSelectedTreeObject(EObjectWithDotPath eoWithDotPath) {
		this.selectedTreeObject = eoWithDotPath;
	}
	
	Point userDropLocationScreenCoords; 
	Point userDropLocationModelCoords;

	/*** 
	 * Returns a nodelabeltype object model object that shall be written to the model due to the current DND operation
	 */
	@Override
	public Object getNewObject() {

		NodeLabelType label = SgraphmlFactory.eINSTANCE.createNodeLabelType();
		label.setFontFamily("Dialog");
		label.setFontSize((short) 11);
		label.setTextColor("#000000");

		
		label.setHorizontalTextPosition(HorizontalTextPositionType.RIGHT);
		label.setPlacement(PlacementType.FREE);
		label.setVerticalTextPosition(VerticalTextPositionType.CENTER);

		label.setVisible(true);
		FeatureMap labelFM = label.getMixed();

		//Abs to Rel coords.
		int relX = userDropLocationModelCoords.x - (int)Math.round(parentGeometry.getX());
		int relY = userDropLocationModelCoords.y - (int)Math.round(parentGeometry.getY());
		
		label.setX(relX);
		label.setY(relY);
		
		//Use @ to indicate a dotPath. The @ is not a part of the dotPath itself.
		String labelText = attribute + ": @" + selectedTreeObject.dotPath + ".:" + attribute;
		FeatureMapUtil.addText(labelFM, labelText);
		
		Utils.INSTANCE.getModelSynchronizer().addEastAdlObjectAdapter(selectedTreeObject.eObject, selectedTreeObject.dotPath, label);
		
		return label;
	}

	@Override
	public Object getObjectType() {
		return NodeLabelType.class; 
	}

	
	public void setLocation(Point location) {
		userDropLocationScreenCoords = new Point(location);
		userDropLocationModelCoords = Utils.screenPoint2ModelPoint(userDropLocationScreenCoords); 
	}


	
	
}
