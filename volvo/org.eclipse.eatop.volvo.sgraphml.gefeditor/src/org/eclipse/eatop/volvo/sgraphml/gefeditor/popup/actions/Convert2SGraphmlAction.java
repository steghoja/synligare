package org.eclipse.eatop.volvo.sgraphml.gefeditor.popup.actions;

public class Convert2SGraphmlAction extends XMLTransformAction {

	public Convert2SGraphmlAction() {
		super();
		
		setXsltFileName("y2s.xsl");
		setSourceExtension("graphml");
		setTargetExtension("sgraphml");
	}
	
}