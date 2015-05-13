/**
 */
package eu.synligare.sgraphml.util;

import eu.synligare.sgraphml.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see eu.synligare.sgraphml.SgraphmlPackage
 * @generated
 */
public class SgraphmlSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static SgraphmlPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SgraphmlSwitch() {
		if (modelPackage == null) {
			modelPackage = SgraphmlPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case SgraphmlPackage.ARROWS_TYPE: {
				ArrowsType arrowsType = (ArrowsType)theEObject;
				T result = caseArrowsType(arrowsType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SgraphmlPackage.BASE_EDGE_TYPE: {
				BaseEdgeType baseEdgeType = (BaseEdgeType)theEObject;
				T result = caseBaseEdgeType(baseEdgeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SgraphmlPackage.BASE_NODE_TYPE: {
				BaseNodeType baseNodeType = (BaseNodeType)theEObject;
				T result = caseBaseNodeType(baseNodeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SgraphmlPackage.DOCUMENT_ROOT: {
				DocumentRoot documentRoot = (DocumentRoot)theEObject;
				T result = caseDocumentRoot(documentRoot);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SgraphmlPackage.EDGE_LABEL_TYPE: {
				EdgeLabelType edgeLabelType = (EdgeLabelType)theEObject;
				T result = caseEdgeLabelType(edgeLabelType);
				if (result == null) result = caseLabelType(edgeLabelType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SgraphmlPackage.FILL_TYPE: {
				FillType fillType = (FillType)theEObject;
				T result = caseFillType(fillType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SgraphmlPackage.GEOMETRY_TYPE: {
				GeometryType geometryType = (GeometryType)theEObject;
				T result = caseGeometryType(geometryType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SgraphmlPackage.GROUP_NODE_TYPE: {
				GroupNodeType groupNodeType = (GroupNodeType)theEObject;
				T result = caseGroupNodeType(groupNodeType);
				if (result == null) result = caseShapeNodeType(groupNodeType);
				if (result == null) result = caseBaseNodeType(groupNodeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SgraphmlPackage.IMAGE_ICON_TYPE: {
				ImageIconType imageIconType = (ImageIconType)theEObject;
				T result = caseImageIconType(imageIconType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SgraphmlPackage.LABEL_TYPE: {
				LabelType labelType = (LabelType)theEObject;
				T result = caseLabelType(labelType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SgraphmlPackage.LINE_STYLE_TYPE: {
				LineStyleType lineStyleType = (LineStyleType)theEObject;
				T result = caseLineStyleType(lineStyleType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SgraphmlPackage.NODE_LABEL_TYPE: {
				NodeLabelType nodeLabelType = (NodeLabelType)theEObject;
				T result = caseNodeLabelType(nodeLabelType);
				if (result == null) result = caseLabelType(nodeLabelType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SgraphmlPackage.PATH_TYPE: {
				PathType pathType = (PathType)theEObject;
				T result = casePathType(pathType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SgraphmlPackage.POINT_TYPE: {
				PointType pointType = (PointType)theEObject;
				T result = casePointType(pointType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SgraphmlPackage.POLY_LINE_EDGE_TYPE: {
				PolyLineEdgeType polyLineEdgeType = (PolyLineEdgeType)theEObject;
				T result = casePolyLineEdgeType(polyLineEdgeType);
				if (result == null) result = caseBaseEdgeType(polyLineEdgeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SgraphmlPackage.PORT_NODE_TYPE: {
				PortNodeType portNodeType = (PortNodeType)theEObject;
				T result = casePortNodeType(portNodeType);
				if (result == null) result = caseBaseNodeType(portNodeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SgraphmlPackage.RESOURCE_BLOCK_TYPE: {
				ResourceBlockType resourceBlockType = (ResourceBlockType)theEObject;
				T result = caseResourceBlockType(resourceBlockType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SgraphmlPackage.RESOURCE_TYPE: {
				ResourceType resourceType = (ResourceType)theEObject;
				T result = caseResourceType(resourceType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SgraphmlPackage.SCALED_ICON_TYPE: {
				ScaledIconType scaledIconType = (ScaledIconType)theEObject;
				T result = caseScaledIconType(scaledIconType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SgraphmlPackage.SHAPE_NODE_TYPE: {
				ShapeNodeType shapeNodeType = (ShapeNodeType)theEObject;
				T result = caseShapeNodeType(shapeNodeType);
				if (result == null) result = caseBaseNodeType(shapeNodeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SgraphmlPackage.SHAPE_TYPE: {
				ShapeType shapeType = (ShapeType)theEObject;
				T result = caseShapeType(shapeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Arrows Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Arrows Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArrowsType(ArrowsType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Base Edge Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Base Edge Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBaseEdgeType(BaseEdgeType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Base Node Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Base Node Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBaseNodeType(BaseNodeType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Document Root</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Document Root</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDocumentRoot(DocumentRoot object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Edge Label Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Edge Label Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEdgeLabelType(EdgeLabelType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Fill Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Fill Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFillType(FillType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Geometry Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Geometry Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGeometryType(GeometryType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Group Node Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Group Node Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGroupNodeType(GroupNodeType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Image Icon Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Image Icon Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseImageIconType(ImageIconType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Label Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Label Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLabelType(LabelType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Line Style Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Line Style Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLineStyleType(LineStyleType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Node Label Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Node Label Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNodeLabelType(NodeLabelType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Path Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Path Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePathType(PathType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Point Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Point Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePointType(PointType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Poly Line Edge Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Poly Line Edge Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePolyLineEdgeType(PolyLineEdgeType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Port Node Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Port Node Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePortNodeType(PortNodeType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource Block Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource Block Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResourceBlockType(ResourceBlockType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResourceType(ResourceType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Scaled Icon Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Scaled Icon Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseScaledIconType(ScaledIconType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Shape Node Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Shape Node Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseShapeNodeType(ShapeNodeType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Shape Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Shape Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseShapeType(ShapeType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //SgraphmlSwitch
