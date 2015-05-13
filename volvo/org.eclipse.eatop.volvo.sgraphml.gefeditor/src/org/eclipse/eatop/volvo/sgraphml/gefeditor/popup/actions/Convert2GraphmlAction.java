package org.eclipse.eatop.volvo.sgraphml.gefeditor.popup.actions;

public class Convert2GraphmlAction extends XMLTransformAction {

	public Convert2GraphmlAction() {
		super();
		
		setXsltFileName("s2y.xsl");
		setSourceExtension("sgraphml");
		setTargetExtension("graphml");
	}
	
}
