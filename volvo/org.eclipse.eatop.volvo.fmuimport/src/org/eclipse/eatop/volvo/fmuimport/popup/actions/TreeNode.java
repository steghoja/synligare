package org.eclipse.eatop.volvo.fmuimport.popup.actions;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

public class TreeNode {
	EClass nodeType;

	EObject nodeObject;

	/**
	 * @return the parentType
	 */
	public EClass getNodeType() {
		return nodeType;
	}

	/**
	 * @return the parentObject
	 */
	public EObject getNodeObject() {
		return nodeObject;
	}

	/**
	 * @param nodeType the parentType to set
	 */
	public void setNodeType(EClass nodeType) {
		this.nodeType = nodeType;
	}

	/**
	 * @param nodeObject the parentObject to set
	 */
	public void setNodeObject(EObject nodeObject) {
		this.nodeObject = nodeObject;
	}

	
	
	
	
}
