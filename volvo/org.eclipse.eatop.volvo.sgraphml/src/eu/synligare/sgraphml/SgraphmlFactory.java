/**
 */
package eu.synligare.sgraphml;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see eu.synligare.sgraphml.SgraphmlPackage
 * @generated
 */
public interface SgraphmlFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SgraphmlFactory eINSTANCE = eu.synligare.sgraphml.impl.SgraphmlFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Arrows Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Arrows Type</em>'.
	 * @generated
	 */
	ArrowsType createArrowsType();

	/**
	 * Returns a new object of class '<em>Base Edge Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Base Edge Type</em>'.
	 * @generated
	 */
	BaseEdgeType createBaseEdgeType();

	/**
	 * Returns a new object of class '<em>Base Node Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Base Node Type</em>'.
	 * @generated
	 */
	BaseNodeType createBaseNodeType();

	/**
	 * Returns a new object of class '<em>Document Root</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Document Root</em>'.
	 * @generated
	 */
	DocumentRoot createDocumentRoot();

	/**
	 * Returns a new object of class '<em>Edge Label Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Edge Label Type</em>'.
	 * @generated
	 */
	EdgeLabelType createEdgeLabelType();

	/**
	 * Returns a new object of class '<em>Fill Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Fill Type</em>'.
	 * @generated
	 */
	FillType createFillType();

	/**
	 * Returns a new object of class '<em>Geometry Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Geometry Type</em>'.
	 * @generated
	 */
	GeometryType createGeometryType();

	/**
	 * Returns a new object of class '<em>Group Node Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Group Node Type</em>'.
	 * @generated
	 */
	GroupNodeType createGroupNodeType();

	/**
	 * Returns a new object of class '<em>Image Icon Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Image Icon Type</em>'.
	 * @generated
	 */
	ImageIconType createImageIconType();

	/**
	 * Returns a new object of class '<em>Label Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Label Type</em>'.
	 * @generated
	 */
	LabelType createLabelType();

	/**
	 * Returns a new object of class '<em>Line Style Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Line Style Type</em>'.
	 * @generated
	 */
	LineStyleType createLineStyleType();

	/**
	 * Returns a new object of class '<em>Node Label Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Node Label Type</em>'.
	 * @generated
	 */
	NodeLabelType createNodeLabelType();

	/**
	 * Returns a new object of class '<em>Path Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Path Type</em>'.
	 * @generated
	 */
	PathType createPathType();

	/**
	 * Returns a new object of class '<em>Point Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Point Type</em>'.
	 * @generated
	 */
	PointType createPointType();

	/**
	 * Returns a new object of class '<em>Poly Line Edge Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Poly Line Edge Type</em>'.
	 * @generated
	 */
	PolyLineEdgeType createPolyLineEdgeType();

	/**
	 * Returns a new object of class '<em>Port Node Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Port Node Type</em>'.
	 * @generated
	 */
	PortNodeType createPortNodeType();

	/**
	 * Returns a new object of class '<em>Resource Block Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resource Block Type</em>'.
	 * @generated
	 */
	ResourceBlockType createResourceBlockType();

	/**
	 * Returns a new object of class '<em>Resource Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resource Type</em>'.
	 * @generated
	 */
	ResourceType createResourceType();

	/**
	 * Returns a new object of class '<em>Scaled Icon Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Scaled Icon Type</em>'.
	 * @generated
	 */
	ScaledIconType createScaledIconType();

	/**
	 * Returns a new object of class '<em>Shape Node Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Shape Node Type</em>'.
	 * @generated
	 */
	ShapeNodeType createShapeNodeType();

	/**
	 * Returns a new object of class '<em>Shape Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Shape Type</em>'.
	 * @generated
	 */
	ShapeType createShapeType();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	SgraphmlPackage getSgraphmlPackage();

} //SgraphmlFactory
