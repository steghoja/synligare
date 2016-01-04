/**
 */
package eu.synligare.sgraphml;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see eu.synligare.sgraphml.SgraphmlFactory
 * @model kind="package"
 * @generated
 */
public interface SgraphmlPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "sgraphml";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.synligare.eu/sgraphml";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "sgraphml";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SgraphmlPackage eINSTANCE = eu.synligare.sgraphml.impl.SgraphmlPackageImpl.init();

	/**
	 * The meta object id for the '{@link eu.synligare.sgraphml.impl.ArrowsTypeImpl <em>Arrows Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.synligare.sgraphml.impl.ArrowsTypeImpl
	 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getArrowsType()
	 * @generated
	 */
	int ARROWS_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARROWS_TYPE__SOURCE = 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARROWS_TYPE__TARGET = 1;

	/**
	 * The number of structural features of the '<em>Arrows Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARROWS_TYPE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Arrows Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARROWS_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.synligare.sgraphml.impl.BaseEdgeTypeImpl <em>Base Edge Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.synligare.sgraphml.impl.BaseEdgeTypeImpl
	 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getBaseEdgeType()
	 * @generated
	 */
	int BASE_EDGE_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Path</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_EDGE_TYPE__PATH = 0;

	/**
	 * The feature id for the '<em><b>Line Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_EDGE_TYPE__LINE_STYLE = 1;

	/**
	 * The feature id for the '<em><b>Arrows</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_EDGE_TYPE__ARROWS = 2;

	/**
	 * The feature id for the '<em><b>Edge Label</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_EDGE_TYPE__EDGE_LABEL = 3;

	/**
	 * The number of structural features of the '<em>Base Edge Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_EDGE_TYPE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Base Edge Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_EDGE_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.synligare.sgraphml.impl.BaseNodeTypeImpl <em>Base Node Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.synligare.sgraphml.impl.BaseNodeTypeImpl
	 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getBaseNodeType()
	 * @generated
	 */
	int BASE_NODE_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Geometry</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_NODE_TYPE__GEOMETRY = 0;

	/**
	 * The feature id for the '<em><b>Fill</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_NODE_TYPE__FILL = 1;

	/**
	 * The feature id for the '<em><b>Border Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_NODE_TYPE__BORDER_STYLE = 2;

	/**
	 * The feature id for the '<em><b>Node Label</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_NODE_TYPE__NODE_LABEL = 3;

	/**
	 * The number of structural features of the '<em>Base Node Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_NODE_TYPE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Base Node Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_NODE_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.synligare.sgraphml.impl.DocumentRootImpl <em>Document Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.synligare.sgraphml.impl.DocumentRootImpl
	 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getDocumentRoot()
	 * @generated
	 */
	int DOCUMENT_ROOT = 3;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MIXED = 0;

	/**
	 * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

	/**
	 * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

	/**
	 * The feature id for the '<em><b>Group Node</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__GROUP_NODE = 3;

	/**
	 * The feature id for the '<em><b>Poly Line Edge</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__POLY_LINE_EDGE = 4;

	/**
	 * The feature id for the '<em><b>Port Node</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PORT_NODE = 5;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__RESOURCES = 6;

	/**
	 * The feature id for the '<em><b>Scaled Icon</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SCALED_ICON = 7;

	/**
	 * The feature id for the '<em><b>Shape Node</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SHAPE_NODE = 8;

	/**
	 * The number of structural features of the '<em>Document Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_FEATURE_COUNT = 9;

	/**
	 * The number of operations of the '<em>Document Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.synligare.sgraphml.impl.LabelTypeImpl <em>Label Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.synligare.sgraphml.impl.LabelTypeImpl
	 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getLabelType()
	 * @generated
	 */
	int LABEL_TYPE = 9;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_TYPE__MIXED = 0;

	/**
	 * The feature id for the '<em><b>Font Family</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_TYPE__FONT_FAMILY = 1;

	/**
	 * The feature id for the '<em><b>Font Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_TYPE__FONT_SIZE = 2;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_TYPE__HEIGHT = 3;

	/**
	 * The feature id for the '<em><b>Text Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_TYPE__TEXT_COLOR = 4;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_TYPE__VISIBLE = 5;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_TYPE__WIDTH = 6;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_TYPE__X = 7;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_TYPE__Y = 8;

	/**
	 * The number of structural features of the '<em>Label Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_TYPE_FEATURE_COUNT = 9;

	/**
	 * The number of operations of the '<em>Label Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.synligare.sgraphml.impl.EdgeLabelTypeImpl <em>Edge Label Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.synligare.sgraphml.impl.EdgeLabelTypeImpl
	 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getEdgeLabelType()
	 * @generated
	 */
	int EDGE_LABEL_TYPE = 4;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_LABEL_TYPE__MIXED = LABEL_TYPE__MIXED;

	/**
	 * The feature id for the '<em><b>Font Family</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_LABEL_TYPE__FONT_FAMILY = LABEL_TYPE__FONT_FAMILY;

	/**
	 * The feature id for the '<em><b>Font Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_LABEL_TYPE__FONT_SIZE = LABEL_TYPE__FONT_SIZE;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_LABEL_TYPE__HEIGHT = LABEL_TYPE__HEIGHT;

	/**
	 * The feature id for the '<em><b>Text Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_LABEL_TYPE__TEXT_COLOR = LABEL_TYPE__TEXT_COLOR;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_LABEL_TYPE__VISIBLE = LABEL_TYPE__VISIBLE;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_LABEL_TYPE__WIDTH = LABEL_TYPE__WIDTH;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_LABEL_TYPE__X = LABEL_TYPE__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_LABEL_TYPE__Y = LABEL_TYPE__Y;

	/**
	 * The number of structural features of the '<em>Edge Label Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_LABEL_TYPE_FEATURE_COUNT = LABEL_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Edge Label Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_LABEL_TYPE_OPERATION_COUNT = LABEL_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.synligare.sgraphml.impl.FillTypeImpl <em>Fill Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.synligare.sgraphml.impl.FillTypeImpl
	 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getFillType()
	 * @generated
	 */
	int FILL_TYPE = 5;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILL_TYPE__COLOR = 0;

	/**
	 * The feature id for the '<em><b>Color2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILL_TYPE__COLOR2 = 1;

	/**
	 * The number of structural features of the '<em>Fill Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILL_TYPE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Fill Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILL_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.synligare.sgraphml.impl.GeometryTypeImpl <em>Geometry Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.synligare.sgraphml.impl.GeometryTypeImpl
	 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getGeometryType()
	 * @generated
	 */
	int GEOMETRY_TYPE = 6;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEOMETRY_TYPE__HEIGHT = 0;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEOMETRY_TYPE__WIDTH = 1;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEOMETRY_TYPE__X = 2;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEOMETRY_TYPE__Y = 3;

	/**
	 * The number of structural features of the '<em>Geometry Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEOMETRY_TYPE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Geometry Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEOMETRY_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.synligare.sgraphml.impl.ShapeNodeTypeImpl <em>Shape Node Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.synligare.sgraphml.impl.ShapeNodeTypeImpl
	 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getShapeNodeType()
	 * @generated
	 */
	int SHAPE_NODE_TYPE = 19;

	/**
	 * The feature id for the '<em><b>Geometry</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE_NODE_TYPE__GEOMETRY = BASE_NODE_TYPE__GEOMETRY;

	/**
	 * The feature id for the '<em><b>Fill</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE_NODE_TYPE__FILL = BASE_NODE_TYPE__FILL;

	/**
	 * The feature id for the '<em><b>Border Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE_NODE_TYPE__BORDER_STYLE = BASE_NODE_TYPE__BORDER_STYLE;

	/**
	 * The feature id for the '<em><b>Node Label</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE_NODE_TYPE__NODE_LABEL = BASE_NODE_TYPE__NODE_LABEL;

	/**
	 * The feature id for the '<em><b>Shape</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE_NODE_TYPE__SHAPE = BASE_NODE_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Shape Node Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE_NODE_TYPE_FEATURE_COUNT = BASE_NODE_TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Shape Node Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE_NODE_TYPE_OPERATION_COUNT = BASE_NODE_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.synligare.sgraphml.impl.GroupNodeTypeImpl <em>Group Node Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.synligare.sgraphml.impl.GroupNodeTypeImpl
	 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getGroupNodeType()
	 * @generated
	 */
	int GROUP_NODE_TYPE = 7;

	/**
	 * The feature id for the '<em><b>Geometry</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_NODE_TYPE__GEOMETRY = SHAPE_NODE_TYPE__GEOMETRY;

	/**
	 * The feature id for the '<em><b>Fill</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_NODE_TYPE__FILL = SHAPE_NODE_TYPE__FILL;

	/**
	 * The feature id for the '<em><b>Border Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_NODE_TYPE__BORDER_STYLE = SHAPE_NODE_TYPE__BORDER_STYLE;

	/**
	 * The feature id for the '<em><b>Node Label</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_NODE_TYPE__NODE_LABEL = SHAPE_NODE_TYPE__NODE_LABEL;

	/**
	 * The feature id for the '<em><b>Shape</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_NODE_TYPE__SHAPE = SHAPE_NODE_TYPE__SHAPE;

	/**
	 * The number of structural features of the '<em>Group Node Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_NODE_TYPE_FEATURE_COUNT = SHAPE_NODE_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Group Node Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_NODE_TYPE_OPERATION_COUNT = SHAPE_NODE_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.synligare.sgraphml.impl.ImageIconTypeImpl <em>Image Icon Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.synligare.sgraphml.impl.ImageIconTypeImpl
	 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getImageIconType()
	 * @generated
	 */
	int IMAGE_ICON_TYPE = 8;

	/**
	 * The feature id for the '<em><b>Image</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_ICON_TYPE__IMAGE = 0;

	/**
	 * The number of structural features of the '<em>Image Icon Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_ICON_TYPE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Image Icon Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_ICON_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.synligare.sgraphml.impl.LineStyleTypeImpl <em>Line Style Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.synligare.sgraphml.impl.LineStyleTypeImpl
	 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getLineStyleType()
	 * @generated
	 */
	int LINE_STYLE_TYPE = 10;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_STYLE_TYPE__COLOR = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_STYLE_TYPE__TYPE = 1;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_STYLE_TYPE__WIDTH = 2;

	/**
	 * The number of structural features of the '<em>Line Style Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_STYLE_TYPE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Line Style Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_STYLE_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.synligare.sgraphml.impl.NodeLabelTypeImpl <em>Node Label Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.synligare.sgraphml.impl.NodeLabelTypeImpl
	 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getNodeLabelType()
	 * @generated
	 */
	int NODE_LABEL_TYPE = 11;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_LABEL_TYPE__MIXED = LABEL_TYPE__MIXED;

	/**
	 * The feature id for the '<em><b>Font Family</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_LABEL_TYPE__FONT_FAMILY = LABEL_TYPE__FONT_FAMILY;

	/**
	 * The feature id for the '<em><b>Font Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_LABEL_TYPE__FONT_SIZE = LABEL_TYPE__FONT_SIZE;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_LABEL_TYPE__HEIGHT = LABEL_TYPE__HEIGHT;

	/**
	 * The feature id for the '<em><b>Text Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_LABEL_TYPE__TEXT_COLOR = LABEL_TYPE__TEXT_COLOR;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_LABEL_TYPE__VISIBLE = LABEL_TYPE__VISIBLE;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_LABEL_TYPE__WIDTH = LABEL_TYPE__WIDTH;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_LABEL_TYPE__X = LABEL_TYPE__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_LABEL_TYPE__Y = LABEL_TYPE__Y;

	/**
	 * The feature id for the '<em><b>Horizontal Text Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_LABEL_TYPE__HORIZONTAL_TEXT_POSITION = LABEL_TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Icon Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_LABEL_TYPE__ICON_DATA = LABEL_TYPE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Icon Text Gap</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_LABEL_TYPE__ICON_TEXT_GAP = LABEL_TYPE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Placement</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_LABEL_TYPE__PLACEMENT = LABEL_TYPE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Vertical Text Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_LABEL_TYPE__VERTICAL_TEXT_POSITION = LABEL_TYPE_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Node Label Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_LABEL_TYPE_FEATURE_COUNT = LABEL_TYPE_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>Node Label Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_LABEL_TYPE_OPERATION_COUNT = LABEL_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.synligare.sgraphml.impl.PathTypeImpl <em>Path Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.synligare.sgraphml.impl.PathTypeImpl
	 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getPathType()
	 * @generated
	 */
	int PATH_TYPE = 12;

	/**
	 * The feature id for the '<em><b>Point</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_TYPE__POINT = 0;

	/**
	 * The feature id for the '<em><b>Sx</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_TYPE__SX = 1;

	/**
	 * The feature id for the '<em><b>Sy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_TYPE__SY = 2;

	/**
	 * The feature id for the '<em><b>Tx</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_TYPE__TX = 3;

	/**
	 * The feature id for the '<em><b>Ty</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_TYPE__TY = 4;

	/**
	 * The number of structural features of the '<em>Path Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_TYPE_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Path Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.synligare.sgraphml.impl.PointTypeImpl <em>Point Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.synligare.sgraphml.impl.PointTypeImpl
	 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getPointType()
	 * @generated
	 */
	int POINT_TYPE = 13;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT_TYPE__X = 0;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT_TYPE__Y = 1;

	/**
	 * The number of structural features of the '<em>Point Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT_TYPE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Point Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.synligare.sgraphml.impl.PolyLineEdgeTypeImpl <em>Poly Line Edge Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.synligare.sgraphml.impl.PolyLineEdgeTypeImpl
	 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getPolyLineEdgeType()
	 * @generated
	 */
	int POLY_LINE_EDGE_TYPE = 14;

	/**
	 * The feature id for the '<em><b>Path</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLY_LINE_EDGE_TYPE__PATH = BASE_EDGE_TYPE__PATH;

	/**
	 * The feature id for the '<em><b>Line Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLY_LINE_EDGE_TYPE__LINE_STYLE = BASE_EDGE_TYPE__LINE_STYLE;

	/**
	 * The feature id for the '<em><b>Arrows</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLY_LINE_EDGE_TYPE__ARROWS = BASE_EDGE_TYPE__ARROWS;

	/**
	 * The feature id for the '<em><b>Edge Label</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLY_LINE_EDGE_TYPE__EDGE_LABEL = BASE_EDGE_TYPE__EDGE_LABEL;

	/**
	 * The number of structural features of the '<em>Poly Line Edge Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLY_LINE_EDGE_TYPE_FEATURE_COUNT = BASE_EDGE_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Poly Line Edge Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLY_LINE_EDGE_TYPE_OPERATION_COUNT = BASE_EDGE_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.synligare.sgraphml.impl.PortNodeTypeImpl <em>Port Node Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.synligare.sgraphml.impl.PortNodeTypeImpl
	 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getPortNodeType()
	 * @generated
	 */
	int PORT_NODE_TYPE = 15;

	/**
	 * The feature id for the '<em><b>Geometry</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_NODE_TYPE__GEOMETRY = BASE_NODE_TYPE__GEOMETRY;

	/**
	 * The feature id for the '<em><b>Fill</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_NODE_TYPE__FILL = BASE_NODE_TYPE__FILL;

	/**
	 * The feature id for the '<em><b>Border Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_NODE_TYPE__BORDER_STYLE = BASE_NODE_TYPE__BORDER_STYLE;

	/**
	 * The feature id for the '<em><b>Node Label</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_NODE_TYPE__NODE_LABEL = BASE_NODE_TYPE__NODE_LABEL;

	/**
	 * The number of structural features of the '<em>Port Node Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_NODE_TYPE_FEATURE_COUNT = BASE_NODE_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Port Node Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_NODE_TYPE_OPERATION_COUNT = BASE_NODE_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.synligare.sgraphml.impl.ResourceBlockTypeImpl <em>Resource Block Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.synligare.sgraphml.impl.ResourceBlockTypeImpl
	 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getResourceBlockType()
	 * @generated
	 */
	int RESOURCE_BLOCK_TYPE = 16;

	/**
	 * The feature id for the '<em><b>Resource</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_BLOCK_TYPE__RESOURCE = 0;

	/**
	 * The number of structural features of the '<em>Resource Block Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_BLOCK_TYPE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Resource Block Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_BLOCK_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.synligare.sgraphml.impl.ResourceTypeImpl <em>Resource Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.synligare.sgraphml.impl.ResourceTypeImpl
	 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getResourceType()
	 * @generated
	 */
	int RESOURCE_TYPE = 17;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_TYPE__MIXED = 0;

	/**
	 * The feature id for the '<em><b>Any</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_TYPE__ANY = 1;

	/**
	 * The feature id for the '<em><b>Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_TYPE__FORMAT = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_TYPE__ID = 3;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_TYPE__TYPE = 4;

	/**
	 * The number of structural features of the '<em>Resource Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_TYPE_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Resource Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.synligare.sgraphml.impl.ScaledIconTypeImpl <em>Scaled Icon Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.synligare.sgraphml.impl.ScaledIconTypeImpl
	 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getScaledIconType()
	 * @generated
	 */
	int SCALED_ICON_TYPE = 18;

	/**
	 * The feature id for the '<em><b>Image Icon</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALED_ICON_TYPE__IMAGE_ICON = 0;

	/**
	 * The feature id for the '<em><b>XScale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALED_ICON_TYPE__XSCALE = 1;

	/**
	 * The feature id for the '<em><b>YScale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALED_ICON_TYPE__YSCALE = 2;

	/**
	 * The number of structural features of the '<em>Scaled Icon Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALED_ICON_TYPE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Scaled Icon Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCALED_ICON_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.synligare.sgraphml.impl.ShapeTypeImpl <em>Shape Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.synligare.sgraphml.impl.ShapeTypeImpl
	 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getShapeType()
	 * @generated
	 */
	int SHAPE_TYPE = 20;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE_TYPE__TYPE = 0;

	/**
	 * The number of structural features of the '<em>Shape Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE_TYPE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Shape Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.synligare.sgraphml.ArrowTypeType <em>Arrow Type Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.synligare.sgraphml.ArrowTypeType
	 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getArrowTypeType()
	 * @generated
	 */
	int ARROW_TYPE_TYPE = 21;

	/**
	 * The meta object id for the '{@link eu.synligare.sgraphml.HorizontalTextPositionType <em>Horizontal Text Position Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.synligare.sgraphml.HorizontalTextPositionType
	 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getHorizontalTextPositionType()
	 * @generated
	 */
	int HORIZONTAL_TEXT_POSITION_TYPE = 22;

	/**
	 * The meta object id for the '{@link eu.synligare.sgraphml.LineTypeType <em>Line Type Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.synligare.sgraphml.LineTypeType
	 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getLineTypeType()
	 * @generated
	 */
	int LINE_TYPE_TYPE = 23;

	/**
	 * The meta object id for the '{@link eu.synligare.sgraphml.PlacementType <em>Placement Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.synligare.sgraphml.PlacementType
	 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getPlacementType()
	 * @generated
	 */
	int PLACEMENT_TYPE = 24;

	/**
	 * The meta object id for the '{@link eu.synligare.sgraphml.ShapeTypeType <em>Shape Type Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.synligare.sgraphml.ShapeTypeType
	 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getShapeTypeType()
	 * @generated
	 */
	int SHAPE_TYPE_TYPE = 25;

	/**
	 * The meta object id for the '{@link eu.synligare.sgraphml.VerticalTextPositionType <em>Vertical Text Position Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.synligare.sgraphml.VerticalTextPositionType
	 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getVerticalTextPositionType()
	 * @generated
	 */
	int VERTICAL_TEXT_POSITION_TYPE = 26;

	/**
	 * The meta object id for the '<em>Arrow Type Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.synligare.sgraphml.ArrowTypeType
	 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getArrowTypeTypeObject()
	 * @generated
	 */
	int ARROW_TYPE_TYPE_OBJECT = 27;

	/**
	 * The meta object id for the '<em>Color Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getColorType()
	 * @generated
	 */
	int COLOR_TYPE = 28;

	/**
	 * The meta object id for the '<em>Horizontal Text Position Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.synligare.sgraphml.HorizontalTextPositionType
	 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getHorizontalTextPositionTypeObject()
	 * @generated
	 */
	int HORIZONTAL_TEXT_POSITION_TYPE_OBJECT = 29;

	/**
	 * The meta object id for the '<em>Line Type Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.synligare.sgraphml.LineTypeType
	 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getLineTypeTypeObject()
	 * @generated
	 */
	int LINE_TYPE_TYPE_OBJECT = 30;

	/**
	 * The meta object id for the '<em>Placement Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.synligare.sgraphml.PlacementType
	 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getPlacementTypeObject()
	 * @generated
	 */
	int PLACEMENT_TYPE_OBJECT = 31;

	/**
	 * The meta object id for the '<em>Shape Type Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.synligare.sgraphml.ShapeTypeType
	 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getShapeTypeTypeObject()
	 * @generated
	 */
	int SHAPE_TYPE_TYPE_OBJECT = 32;

	/**
	 * The meta object id for the '<em>Vertical Text Position Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.synligare.sgraphml.VerticalTextPositionType
	 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getVerticalTextPositionTypeObject()
	 * @generated
	 */
	int VERTICAL_TEXT_POSITION_TYPE_OBJECT = 33;


	/**
	 * Returns the meta object for class '{@link eu.synligare.sgraphml.ArrowsType <em>Arrows Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Arrows Type</em>'.
	 * @see eu.synligare.sgraphml.ArrowsType
	 * @generated
	 */
	EClass getArrowsType();

	/**
	 * Returns the meta object for the attribute '{@link eu.synligare.sgraphml.ArrowsType#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source</em>'.
	 * @see eu.synligare.sgraphml.ArrowsType#getSource()
	 * @see #getArrowsType()
	 * @generated
	 */
	EAttribute getArrowsType_Source();

	/**
	 * Returns the meta object for the attribute '{@link eu.synligare.sgraphml.ArrowsType#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target</em>'.
	 * @see eu.synligare.sgraphml.ArrowsType#getTarget()
	 * @see #getArrowsType()
	 * @generated
	 */
	EAttribute getArrowsType_Target();

	/**
	 * Returns the meta object for class '{@link eu.synligare.sgraphml.BaseEdgeType <em>Base Edge Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Base Edge Type</em>'.
	 * @see eu.synligare.sgraphml.BaseEdgeType
	 * @generated
	 */
	EClass getBaseEdgeType();

	/**
	 * Returns the meta object for the containment reference '{@link eu.synligare.sgraphml.BaseEdgeType#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Path</em>'.
	 * @see eu.synligare.sgraphml.BaseEdgeType#getPath()
	 * @see #getBaseEdgeType()
	 * @generated
	 */
	EReference getBaseEdgeType_Path();

	/**
	 * Returns the meta object for the containment reference '{@link eu.synligare.sgraphml.BaseEdgeType#getLineStyle <em>Line Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Line Style</em>'.
	 * @see eu.synligare.sgraphml.BaseEdgeType#getLineStyle()
	 * @see #getBaseEdgeType()
	 * @generated
	 */
	EReference getBaseEdgeType_LineStyle();

	/**
	 * Returns the meta object for the containment reference '{@link eu.synligare.sgraphml.BaseEdgeType#getArrows <em>Arrows</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Arrows</em>'.
	 * @see eu.synligare.sgraphml.BaseEdgeType#getArrows()
	 * @see #getBaseEdgeType()
	 * @generated
	 */
	EReference getBaseEdgeType_Arrows();

	/**
	 * Returns the meta object for the containment reference '{@link eu.synligare.sgraphml.BaseEdgeType#getEdgeLabel <em>Edge Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Edge Label</em>'.
	 * @see eu.synligare.sgraphml.BaseEdgeType#getEdgeLabel()
	 * @see #getBaseEdgeType()
	 * @generated
	 */
	EReference getBaseEdgeType_EdgeLabel();

	/**
	 * Returns the meta object for class '{@link eu.synligare.sgraphml.BaseNodeType <em>Base Node Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Base Node Type</em>'.
	 * @see eu.synligare.sgraphml.BaseNodeType
	 * @generated
	 */
	EClass getBaseNodeType();

	/**
	 * Returns the meta object for the containment reference '{@link eu.synligare.sgraphml.BaseNodeType#getGeometry <em>Geometry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Geometry</em>'.
	 * @see eu.synligare.sgraphml.BaseNodeType#getGeometry()
	 * @see #getBaseNodeType()
	 * @generated
	 */
	EReference getBaseNodeType_Geometry();

	/**
	 * Returns the meta object for the containment reference '{@link eu.synligare.sgraphml.BaseNodeType#getFill <em>Fill</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Fill</em>'.
	 * @see eu.synligare.sgraphml.BaseNodeType#getFill()
	 * @see #getBaseNodeType()
	 * @generated
	 */
	EReference getBaseNodeType_Fill();

	/**
	 * Returns the meta object for the containment reference '{@link eu.synligare.sgraphml.BaseNodeType#getBorderStyle <em>Border Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Border Style</em>'.
	 * @see eu.synligare.sgraphml.BaseNodeType#getBorderStyle()
	 * @see #getBaseNodeType()
	 * @generated
	 */
	EReference getBaseNodeType_BorderStyle();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.synligare.sgraphml.BaseNodeType#getNodeLabel <em>Node Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Node Label</em>'.
	 * @see eu.synligare.sgraphml.BaseNodeType#getNodeLabel()
	 * @see #getBaseNodeType()
	 * @generated
	 */
	EReference getBaseNodeType_NodeLabel();

	/**
	 * Returns the meta object for class '{@link eu.synligare.sgraphml.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see eu.synligare.sgraphml.DocumentRoot
	 * @generated
	 */
	EClass getDocumentRoot();

	/**
	 * Returns the meta object for the attribute list '{@link eu.synligare.sgraphml.DocumentRoot#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see eu.synligare.sgraphml.DocumentRoot#getMixed()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Mixed();

	/**
	 * Returns the meta object for the map '{@link eu.synligare.sgraphml.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see eu.synligare.sgraphml.DocumentRoot#getXMLNSPrefixMap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '{@link eu.synligare.sgraphml.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see eu.synligare.sgraphml.DocumentRoot#getXSISchemaLocation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XSISchemaLocation();

	/**
	 * Returns the meta object for the containment reference '{@link eu.synligare.sgraphml.DocumentRoot#getGroupNode <em>Group Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Group Node</em>'.
	 * @see eu.synligare.sgraphml.DocumentRoot#getGroupNode()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_GroupNode();

	/**
	 * Returns the meta object for the containment reference '{@link eu.synligare.sgraphml.DocumentRoot#getPolyLineEdge <em>Poly Line Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Poly Line Edge</em>'.
	 * @see eu.synligare.sgraphml.DocumentRoot#getPolyLineEdge()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_PolyLineEdge();

	/**
	 * Returns the meta object for the containment reference '{@link eu.synligare.sgraphml.DocumentRoot#getPortNode <em>Port Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Port Node</em>'.
	 * @see eu.synligare.sgraphml.DocumentRoot#getPortNode()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_PortNode();

	/**
	 * Returns the meta object for the containment reference '{@link eu.synligare.sgraphml.DocumentRoot#getResources <em>Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Resources</em>'.
	 * @see eu.synligare.sgraphml.DocumentRoot#getResources()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Resources();

	/**
	 * Returns the meta object for the containment reference '{@link eu.synligare.sgraphml.DocumentRoot#getScaledIcon <em>Scaled Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Scaled Icon</em>'.
	 * @see eu.synligare.sgraphml.DocumentRoot#getScaledIcon()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ScaledIcon();

	/**
	 * Returns the meta object for the containment reference '{@link eu.synligare.sgraphml.DocumentRoot#getShapeNode <em>Shape Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Shape Node</em>'.
	 * @see eu.synligare.sgraphml.DocumentRoot#getShapeNode()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ShapeNode();

	/**
	 * Returns the meta object for class '{@link eu.synligare.sgraphml.EdgeLabelType <em>Edge Label Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Edge Label Type</em>'.
	 * @see eu.synligare.sgraphml.EdgeLabelType
	 * @generated
	 */
	EClass getEdgeLabelType();

	/**
	 * Returns the meta object for class '{@link eu.synligare.sgraphml.FillType <em>Fill Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fill Type</em>'.
	 * @see eu.synligare.sgraphml.FillType
	 * @generated
	 */
	EClass getFillType();

	/**
	 * Returns the meta object for the attribute '{@link eu.synligare.sgraphml.FillType#getColor <em>Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Color</em>'.
	 * @see eu.synligare.sgraphml.FillType#getColor()
	 * @see #getFillType()
	 * @generated
	 */
	EAttribute getFillType_Color();

	/**
	 * Returns the meta object for the attribute '{@link eu.synligare.sgraphml.FillType#getColor2 <em>Color2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Color2</em>'.
	 * @see eu.synligare.sgraphml.FillType#getColor2()
	 * @see #getFillType()
	 * @generated
	 */
	EAttribute getFillType_Color2();

	/**
	 * Returns the meta object for class '{@link eu.synligare.sgraphml.GeometryType <em>Geometry Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Geometry Type</em>'.
	 * @see eu.synligare.sgraphml.GeometryType
	 * @generated
	 */
	EClass getGeometryType();

	/**
	 * Returns the meta object for the attribute '{@link eu.synligare.sgraphml.GeometryType#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see eu.synligare.sgraphml.GeometryType#getHeight()
	 * @see #getGeometryType()
	 * @generated
	 */
	EAttribute getGeometryType_Height();

	/**
	 * Returns the meta object for the attribute '{@link eu.synligare.sgraphml.GeometryType#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see eu.synligare.sgraphml.GeometryType#getWidth()
	 * @see #getGeometryType()
	 * @generated
	 */
	EAttribute getGeometryType_Width();

	/**
	 * Returns the meta object for the attribute '{@link eu.synligare.sgraphml.GeometryType#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see eu.synligare.sgraphml.GeometryType#getX()
	 * @see #getGeometryType()
	 * @generated
	 */
	EAttribute getGeometryType_X();

	/**
	 * Returns the meta object for the attribute '{@link eu.synligare.sgraphml.GeometryType#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see eu.synligare.sgraphml.GeometryType#getY()
	 * @see #getGeometryType()
	 * @generated
	 */
	EAttribute getGeometryType_Y();

	/**
	 * Returns the meta object for class '{@link eu.synligare.sgraphml.GroupNodeType <em>Group Node Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Group Node Type</em>'.
	 * @see eu.synligare.sgraphml.GroupNodeType
	 * @generated
	 */
	EClass getGroupNodeType();

	/**
	 * Returns the meta object for class '{@link eu.synligare.sgraphml.ImageIconType <em>Image Icon Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Image Icon Type</em>'.
	 * @see eu.synligare.sgraphml.ImageIconType
	 * @generated
	 */
	EClass getImageIconType();

	/**
	 * Returns the meta object for the attribute '{@link eu.synligare.sgraphml.ImageIconType#getImage <em>Image</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Image</em>'.
	 * @see eu.synligare.sgraphml.ImageIconType#getImage()
	 * @see #getImageIconType()
	 * @generated
	 */
	EAttribute getImageIconType_Image();

	/**
	 * Returns the meta object for class '{@link eu.synligare.sgraphml.LabelType <em>Label Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Label Type</em>'.
	 * @see eu.synligare.sgraphml.LabelType
	 * @generated
	 */
	EClass getLabelType();

	/**
	 * Returns the meta object for the attribute list '{@link eu.synligare.sgraphml.LabelType#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see eu.synligare.sgraphml.LabelType#getMixed()
	 * @see #getLabelType()
	 * @generated
	 */
	EAttribute getLabelType_Mixed();

	/**
	 * Returns the meta object for the attribute '{@link eu.synligare.sgraphml.LabelType#getFontFamily <em>Font Family</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Font Family</em>'.
	 * @see eu.synligare.sgraphml.LabelType#getFontFamily()
	 * @see #getLabelType()
	 * @generated
	 */
	EAttribute getLabelType_FontFamily();

	/**
	 * Returns the meta object for the attribute '{@link eu.synligare.sgraphml.LabelType#getFontSize <em>Font Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Font Size</em>'.
	 * @see eu.synligare.sgraphml.LabelType#getFontSize()
	 * @see #getLabelType()
	 * @generated
	 */
	EAttribute getLabelType_FontSize();

	/**
	 * Returns the meta object for the attribute '{@link eu.synligare.sgraphml.LabelType#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see eu.synligare.sgraphml.LabelType#getHeight()
	 * @see #getLabelType()
	 * @generated
	 */
	EAttribute getLabelType_Height();

	/**
	 * Returns the meta object for the attribute '{@link eu.synligare.sgraphml.LabelType#getTextColor <em>Text Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Color</em>'.
	 * @see eu.synligare.sgraphml.LabelType#getTextColor()
	 * @see #getLabelType()
	 * @generated
	 */
	EAttribute getLabelType_TextColor();

	/**
	 * Returns the meta object for the attribute '{@link eu.synligare.sgraphml.LabelType#isVisible <em>Visible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Visible</em>'.
	 * @see eu.synligare.sgraphml.LabelType#isVisible()
	 * @see #getLabelType()
	 * @generated
	 */
	EAttribute getLabelType_Visible();

	/**
	 * Returns the meta object for the attribute '{@link eu.synligare.sgraphml.LabelType#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see eu.synligare.sgraphml.LabelType#getWidth()
	 * @see #getLabelType()
	 * @generated
	 */
	EAttribute getLabelType_Width();

	/**
	 * Returns the meta object for the attribute '{@link eu.synligare.sgraphml.LabelType#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see eu.synligare.sgraphml.LabelType#getX()
	 * @see #getLabelType()
	 * @generated
	 */
	EAttribute getLabelType_X();

	/**
	 * Returns the meta object for the attribute '{@link eu.synligare.sgraphml.LabelType#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see eu.synligare.sgraphml.LabelType#getY()
	 * @see #getLabelType()
	 * @generated
	 */
	EAttribute getLabelType_Y();

	/**
	 * Returns the meta object for class '{@link eu.synligare.sgraphml.LineStyleType <em>Line Style Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Line Style Type</em>'.
	 * @see eu.synligare.sgraphml.LineStyleType
	 * @generated
	 */
	EClass getLineStyleType();

	/**
	 * Returns the meta object for the attribute '{@link eu.synligare.sgraphml.LineStyleType#getColor <em>Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Color</em>'.
	 * @see eu.synligare.sgraphml.LineStyleType#getColor()
	 * @see #getLineStyleType()
	 * @generated
	 */
	EAttribute getLineStyleType_Color();

	/**
	 * Returns the meta object for the attribute '{@link eu.synligare.sgraphml.LineStyleType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see eu.synligare.sgraphml.LineStyleType#getType()
	 * @see #getLineStyleType()
	 * @generated
	 */
	EAttribute getLineStyleType_Type();

	/**
	 * Returns the meta object for the attribute '{@link eu.synligare.sgraphml.LineStyleType#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see eu.synligare.sgraphml.LineStyleType#getWidth()
	 * @see #getLineStyleType()
	 * @generated
	 */
	EAttribute getLineStyleType_Width();

	/**
	 * Returns the meta object for class '{@link eu.synligare.sgraphml.NodeLabelType <em>Node Label Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node Label Type</em>'.
	 * @see eu.synligare.sgraphml.NodeLabelType
	 * @generated
	 */
	EClass getNodeLabelType();

	/**
	 * Returns the meta object for the attribute '{@link eu.synligare.sgraphml.NodeLabelType#getHorizontalTextPosition <em>Horizontal Text Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Horizontal Text Position</em>'.
	 * @see eu.synligare.sgraphml.NodeLabelType#getHorizontalTextPosition()
	 * @see #getNodeLabelType()
	 * @generated
	 */
	EAttribute getNodeLabelType_HorizontalTextPosition();

	/**
	 * Returns the meta object for the attribute '{@link eu.synligare.sgraphml.NodeLabelType#getIconData <em>Icon Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Icon Data</em>'.
	 * @see eu.synligare.sgraphml.NodeLabelType#getIconData()
	 * @see #getNodeLabelType()
	 * @generated
	 */
	EAttribute getNodeLabelType_IconData();

	/**
	 * Returns the meta object for the attribute '{@link eu.synligare.sgraphml.NodeLabelType#getIconTextGap <em>Icon Text Gap</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Icon Text Gap</em>'.
	 * @see eu.synligare.sgraphml.NodeLabelType#getIconTextGap()
	 * @see #getNodeLabelType()
	 * @generated
	 */
	EAttribute getNodeLabelType_IconTextGap();

	/**
	 * Returns the meta object for the attribute '{@link eu.synligare.sgraphml.NodeLabelType#getPlacement <em>Placement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Placement</em>'.
	 * @see eu.synligare.sgraphml.NodeLabelType#getPlacement()
	 * @see #getNodeLabelType()
	 * @generated
	 */
	EAttribute getNodeLabelType_Placement();

	/**
	 * Returns the meta object for the attribute '{@link eu.synligare.sgraphml.NodeLabelType#getVerticalTextPosition <em>Vertical Text Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Vertical Text Position</em>'.
	 * @see eu.synligare.sgraphml.NodeLabelType#getVerticalTextPosition()
	 * @see #getNodeLabelType()
	 * @generated
	 */
	EAttribute getNodeLabelType_VerticalTextPosition();

	/**
	 * Returns the meta object for class '{@link eu.synligare.sgraphml.PathType <em>Path Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Path Type</em>'.
	 * @see eu.synligare.sgraphml.PathType
	 * @generated
	 */
	EClass getPathType();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.synligare.sgraphml.PathType#getPoint <em>Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Point</em>'.
	 * @see eu.synligare.sgraphml.PathType#getPoint()
	 * @see #getPathType()
	 * @generated
	 */
	EReference getPathType_Point();

	/**
	 * Returns the meta object for the attribute '{@link eu.synligare.sgraphml.PathType#getSx <em>Sx</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sx</em>'.
	 * @see eu.synligare.sgraphml.PathType#getSx()
	 * @see #getPathType()
	 * @generated
	 */
	EAttribute getPathType_Sx();

	/**
	 * Returns the meta object for the attribute '{@link eu.synligare.sgraphml.PathType#getSy <em>Sy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sy</em>'.
	 * @see eu.synligare.sgraphml.PathType#getSy()
	 * @see #getPathType()
	 * @generated
	 */
	EAttribute getPathType_Sy();

	/**
	 * Returns the meta object for the attribute '{@link eu.synligare.sgraphml.PathType#getTx <em>Tx</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tx</em>'.
	 * @see eu.synligare.sgraphml.PathType#getTx()
	 * @see #getPathType()
	 * @generated
	 */
	EAttribute getPathType_Tx();

	/**
	 * Returns the meta object for the attribute '{@link eu.synligare.sgraphml.PathType#getTy <em>Ty</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ty</em>'.
	 * @see eu.synligare.sgraphml.PathType#getTy()
	 * @see #getPathType()
	 * @generated
	 */
	EAttribute getPathType_Ty();

	/**
	 * Returns the meta object for class '{@link eu.synligare.sgraphml.PointType <em>Point Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Point Type</em>'.
	 * @see eu.synligare.sgraphml.PointType
	 * @generated
	 */
	EClass getPointType();

	/**
	 * Returns the meta object for the attribute '{@link eu.synligare.sgraphml.PointType#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see eu.synligare.sgraphml.PointType#getX()
	 * @see #getPointType()
	 * @generated
	 */
	EAttribute getPointType_X();

	/**
	 * Returns the meta object for the attribute '{@link eu.synligare.sgraphml.PointType#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see eu.synligare.sgraphml.PointType#getY()
	 * @see #getPointType()
	 * @generated
	 */
	EAttribute getPointType_Y();

	/**
	 * Returns the meta object for class '{@link eu.synligare.sgraphml.PolyLineEdgeType <em>Poly Line Edge Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Poly Line Edge Type</em>'.
	 * @see eu.synligare.sgraphml.PolyLineEdgeType
	 * @generated
	 */
	EClass getPolyLineEdgeType();

	/**
	 * Returns the meta object for class '{@link eu.synligare.sgraphml.PortNodeType <em>Port Node Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port Node Type</em>'.
	 * @see eu.synligare.sgraphml.PortNodeType
	 * @generated
	 */
	EClass getPortNodeType();

	/**
	 * Returns the meta object for class '{@link eu.synligare.sgraphml.ResourceBlockType <em>Resource Block Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Block Type</em>'.
	 * @see eu.synligare.sgraphml.ResourceBlockType
	 * @generated
	 */
	EClass getResourceBlockType();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.synligare.sgraphml.ResourceBlockType#getResource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resource</em>'.
	 * @see eu.synligare.sgraphml.ResourceBlockType#getResource()
	 * @see #getResourceBlockType()
	 * @generated
	 */
	EReference getResourceBlockType_Resource();

	/**
	 * Returns the meta object for class '{@link eu.synligare.sgraphml.ResourceType <em>Resource Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Type</em>'.
	 * @see eu.synligare.sgraphml.ResourceType
	 * @generated
	 */
	EClass getResourceType();

	/**
	 * Returns the meta object for the attribute list '{@link eu.synligare.sgraphml.ResourceType#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see eu.synligare.sgraphml.ResourceType#getMixed()
	 * @see #getResourceType()
	 * @generated
	 */
	EAttribute getResourceType_Mixed();

	/**
	 * Returns the meta object for the attribute list '{@link eu.synligare.sgraphml.ResourceType#getAny <em>Any</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any</em>'.
	 * @see eu.synligare.sgraphml.ResourceType#getAny()
	 * @see #getResourceType()
	 * @generated
	 */
	EAttribute getResourceType_Any();

	/**
	 * Returns the meta object for the attribute '{@link eu.synligare.sgraphml.ResourceType#getFormat <em>Format</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Format</em>'.
	 * @see eu.synligare.sgraphml.ResourceType#getFormat()
	 * @see #getResourceType()
	 * @generated
	 */
	EAttribute getResourceType_Format();

	/**
	 * Returns the meta object for the attribute '{@link eu.synligare.sgraphml.ResourceType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see eu.synligare.sgraphml.ResourceType#getId()
	 * @see #getResourceType()
	 * @generated
	 */
	EAttribute getResourceType_Id();

	/**
	 * Returns the meta object for the attribute '{@link eu.synligare.sgraphml.ResourceType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see eu.synligare.sgraphml.ResourceType#getType()
	 * @see #getResourceType()
	 * @generated
	 */
	EAttribute getResourceType_Type();

	/**
	 * Returns the meta object for class '{@link eu.synligare.sgraphml.ScaledIconType <em>Scaled Icon Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Scaled Icon Type</em>'.
	 * @see eu.synligare.sgraphml.ScaledIconType
	 * @generated
	 */
	EClass getScaledIconType();

	/**
	 * Returns the meta object for the containment reference '{@link eu.synligare.sgraphml.ScaledIconType#getImageIcon <em>Image Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Image Icon</em>'.
	 * @see eu.synligare.sgraphml.ScaledIconType#getImageIcon()
	 * @see #getScaledIconType()
	 * @generated
	 */
	EReference getScaledIconType_ImageIcon();

	/**
	 * Returns the meta object for the attribute '{@link eu.synligare.sgraphml.ScaledIconType#getXScale <em>XScale</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>XScale</em>'.
	 * @see eu.synligare.sgraphml.ScaledIconType#getXScale()
	 * @see #getScaledIconType()
	 * @generated
	 */
	EAttribute getScaledIconType_XScale();

	/**
	 * Returns the meta object for the attribute '{@link eu.synligare.sgraphml.ScaledIconType#getYScale <em>YScale</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>YScale</em>'.
	 * @see eu.synligare.sgraphml.ScaledIconType#getYScale()
	 * @see #getScaledIconType()
	 * @generated
	 */
	EAttribute getScaledIconType_YScale();

	/**
	 * Returns the meta object for class '{@link eu.synligare.sgraphml.ShapeNodeType <em>Shape Node Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Shape Node Type</em>'.
	 * @see eu.synligare.sgraphml.ShapeNodeType
	 * @generated
	 */
	EClass getShapeNodeType();

	/**
	 * Returns the meta object for the containment reference '{@link eu.synligare.sgraphml.ShapeNodeType#getShape <em>Shape</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Shape</em>'.
	 * @see eu.synligare.sgraphml.ShapeNodeType#getShape()
	 * @see #getShapeNodeType()
	 * @generated
	 */
	EReference getShapeNodeType_Shape();

	/**
	 * Returns the meta object for class '{@link eu.synligare.sgraphml.ShapeType <em>Shape Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Shape Type</em>'.
	 * @see eu.synligare.sgraphml.ShapeType
	 * @generated
	 */
	EClass getShapeType();

	/**
	 * Returns the meta object for the attribute '{@link eu.synligare.sgraphml.ShapeType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see eu.synligare.sgraphml.ShapeType#getType()
	 * @see #getShapeType()
	 * @generated
	 */
	EAttribute getShapeType_Type();

	/**
	 * Returns the meta object for enum '{@link eu.synligare.sgraphml.ArrowTypeType <em>Arrow Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Arrow Type Type</em>'.
	 * @see eu.synligare.sgraphml.ArrowTypeType
	 * @generated
	 */
	EEnum getArrowTypeType();

	/**
	 * Returns the meta object for enum '{@link eu.synligare.sgraphml.HorizontalTextPositionType <em>Horizontal Text Position Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Horizontal Text Position Type</em>'.
	 * @see eu.synligare.sgraphml.HorizontalTextPositionType
	 * @generated
	 */
	EEnum getHorizontalTextPositionType();

	/**
	 * Returns the meta object for enum '{@link eu.synligare.sgraphml.LineTypeType <em>Line Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Line Type Type</em>'.
	 * @see eu.synligare.sgraphml.LineTypeType
	 * @generated
	 */
	EEnum getLineTypeType();

	/**
	 * Returns the meta object for enum '{@link eu.synligare.sgraphml.PlacementType <em>Placement Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Placement Type</em>'.
	 * @see eu.synligare.sgraphml.PlacementType
	 * @generated
	 */
	EEnum getPlacementType();

	/**
	 * Returns the meta object for enum '{@link eu.synligare.sgraphml.ShapeTypeType <em>Shape Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Shape Type Type</em>'.
	 * @see eu.synligare.sgraphml.ShapeTypeType
	 * @generated
	 */
	EEnum getShapeTypeType();

	/**
	 * Returns the meta object for enum '{@link eu.synligare.sgraphml.VerticalTextPositionType <em>Vertical Text Position Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Vertical Text Position Type</em>'.
	 * @see eu.synligare.sgraphml.VerticalTextPositionType
	 * @generated
	 */
	EEnum getVerticalTextPositionType();

	/**
	 * Returns the meta object for data type '{@link eu.synligare.sgraphml.ArrowTypeType <em>Arrow Type Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Arrow Type Type Object</em>'.
	 * @see eu.synligare.sgraphml.ArrowTypeType
	 * @model instanceClass="eu.synligare.sgraphml.ArrowTypeType"
	 *        extendedMetaData="name='arrowType.type:Object' baseType='arrowType.type'"
	 * @generated
	 */
	EDataType getArrowTypeTypeObject();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Color Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Color Type</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='color.type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' pattern='#(([A-F]|[0-9]){2}){3,4}'"
	 * @generated
	 */
	EDataType getColorType();

	/**
	 * Returns the meta object for data type '{@link eu.synligare.sgraphml.HorizontalTextPositionType <em>Horizontal Text Position Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Horizontal Text Position Type Object</em>'.
	 * @see eu.synligare.sgraphml.HorizontalTextPositionType
	 * @model instanceClass="eu.synligare.sgraphml.HorizontalTextPositionType"
	 *        extendedMetaData="name='horizontalTextPosition.type:Object' baseType='horizontalTextPosition.type'"
	 * @generated
	 */
	EDataType getHorizontalTextPositionTypeObject();

	/**
	 * Returns the meta object for data type '{@link eu.synligare.sgraphml.LineTypeType <em>Line Type Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Line Type Type Object</em>'.
	 * @see eu.synligare.sgraphml.LineTypeType
	 * @model instanceClass="eu.synligare.sgraphml.LineTypeType"
	 *        extendedMetaData="name='lineType.type:Object' baseType='lineType.type'"
	 * @generated
	 */
	EDataType getLineTypeTypeObject();

	/**
	 * Returns the meta object for data type '{@link eu.synligare.sgraphml.PlacementType <em>Placement Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Placement Type Object</em>'.
	 * @see eu.synligare.sgraphml.PlacementType
	 * @model instanceClass="eu.synligare.sgraphml.PlacementType"
	 *        extendedMetaData="name='placement.type:Object' baseType='placement.type'"
	 * @generated
	 */
	EDataType getPlacementTypeObject();

	/**
	 * Returns the meta object for data type '{@link eu.synligare.sgraphml.ShapeTypeType <em>Shape Type Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Shape Type Type Object</em>'.
	 * @see eu.synligare.sgraphml.ShapeTypeType
	 * @model instanceClass="eu.synligare.sgraphml.ShapeTypeType"
	 *        extendedMetaData="name='shapeType.type:Object' baseType='shapeType.type'"
	 * @generated
	 */
	EDataType getShapeTypeTypeObject();

	/**
	 * Returns the meta object for data type '{@link eu.synligare.sgraphml.VerticalTextPositionType <em>Vertical Text Position Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Vertical Text Position Type Object</em>'.
	 * @see eu.synligare.sgraphml.VerticalTextPositionType
	 * @model instanceClass="eu.synligare.sgraphml.VerticalTextPositionType"
	 *        extendedMetaData="name='verticalTextPosition.type:Object' baseType='verticalTextPosition.type'"
	 * @generated
	 */
	EDataType getVerticalTextPositionTypeObject();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SgraphmlFactory getSgraphmlFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link eu.synligare.sgraphml.impl.ArrowsTypeImpl <em>Arrows Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.synligare.sgraphml.impl.ArrowsTypeImpl
		 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getArrowsType()
		 * @generated
		 */
		EClass ARROWS_TYPE = eINSTANCE.getArrowsType();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARROWS_TYPE__SOURCE = eINSTANCE.getArrowsType_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARROWS_TYPE__TARGET = eINSTANCE.getArrowsType_Target();

		/**
		 * The meta object literal for the '{@link eu.synligare.sgraphml.impl.BaseEdgeTypeImpl <em>Base Edge Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.synligare.sgraphml.impl.BaseEdgeTypeImpl
		 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getBaseEdgeType()
		 * @generated
		 */
		EClass BASE_EDGE_TYPE = eINSTANCE.getBaseEdgeType();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BASE_EDGE_TYPE__PATH = eINSTANCE.getBaseEdgeType_Path();

		/**
		 * The meta object literal for the '<em><b>Line Style</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BASE_EDGE_TYPE__LINE_STYLE = eINSTANCE.getBaseEdgeType_LineStyle();

		/**
		 * The meta object literal for the '<em><b>Arrows</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BASE_EDGE_TYPE__ARROWS = eINSTANCE.getBaseEdgeType_Arrows();

		/**
		 * The meta object literal for the '<em><b>Edge Label</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BASE_EDGE_TYPE__EDGE_LABEL = eINSTANCE.getBaseEdgeType_EdgeLabel();

		/**
		 * The meta object literal for the '{@link eu.synligare.sgraphml.impl.BaseNodeTypeImpl <em>Base Node Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.synligare.sgraphml.impl.BaseNodeTypeImpl
		 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getBaseNodeType()
		 * @generated
		 */
		EClass BASE_NODE_TYPE = eINSTANCE.getBaseNodeType();

		/**
		 * The meta object literal for the '<em><b>Geometry</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BASE_NODE_TYPE__GEOMETRY = eINSTANCE.getBaseNodeType_Geometry();

		/**
		 * The meta object literal for the '<em><b>Fill</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BASE_NODE_TYPE__FILL = eINSTANCE.getBaseNodeType_Fill();

		/**
		 * The meta object literal for the '<em><b>Border Style</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BASE_NODE_TYPE__BORDER_STYLE = eINSTANCE.getBaseNodeType_BorderStyle();

		/**
		 * The meta object literal for the '<em><b>Node Label</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BASE_NODE_TYPE__NODE_LABEL = eINSTANCE.getBaseNodeType_NodeLabel();

		/**
		 * The meta object literal for the '{@link eu.synligare.sgraphml.impl.DocumentRootImpl <em>Document Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.synligare.sgraphml.impl.DocumentRootImpl
		 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getDocumentRoot()
		 * @generated
		 */
		EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

		/**
		 * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

		/**
		 * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

		/**
		 * The meta object literal for the '<em><b>Group Node</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__GROUP_NODE = eINSTANCE.getDocumentRoot_GroupNode();

		/**
		 * The meta object literal for the '<em><b>Poly Line Edge</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__POLY_LINE_EDGE = eINSTANCE.getDocumentRoot_PolyLineEdge();

		/**
		 * The meta object literal for the '<em><b>Port Node</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__PORT_NODE = eINSTANCE.getDocumentRoot_PortNode();

		/**
		 * The meta object literal for the '<em><b>Resources</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__RESOURCES = eINSTANCE.getDocumentRoot_Resources();

		/**
		 * The meta object literal for the '<em><b>Scaled Icon</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SCALED_ICON = eINSTANCE.getDocumentRoot_ScaledIcon();

		/**
		 * The meta object literal for the '<em><b>Shape Node</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SHAPE_NODE = eINSTANCE.getDocumentRoot_ShapeNode();

		/**
		 * The meta object literal for the '{@link eu.synligare.sgraphml.impl.EdgeLabelTypeImpl <em>Edge Label Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.synligare.sgraphml.impl.EdgeLabelTypeImpl
		 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getEdgeLabelType()
		 * @generated
		 */
		EClass EDGE_LABEL_TYPE = eINSTANCE.getEdgeLabelType();

		/**
		 * The meta object literal for the '{@link eu.synligare.sgraphml.impl.FillTypeImpl <em>Fill Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.synligare.sgraphml.impl.FillTypeImpl
		 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getFillType()
		 * @generated
		 */
		EClass FILL_TYPE = eINSTANCE.getFillType();

		/**
		 * The meta object literal for the '<em><b>Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILL_TYPE__COLOR = eINSTANCE.getFillType_Color();

		/**
		 * The meta object literal for the '<em><b>Color2</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILL_TYPE__COLOR2 = eINSTANCE.getFillType_Color2();

		/**
		 * The meta object literal for the '{@link eu.synligare.sgraphml.impl.GeometryTypeImpl <em>Geometry Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.synligare.sgraphml.impl.GeometryTypeImpl
		 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getGeometryType()
		 * @generated
		 */
		EClass GEOMETRY_TYPE = eINSTANCE.getGeometryType();

		/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GEOMETRY_TYPE__HEIGHT = eINSTANCE.getGeometryType_Height();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GEOMETRY_TYPE__WIDTH = eINSTANCE.getGeometryType_Width();

		/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GEOMETRY_TYPE__X = eINSTANCE.getGeometryType_X();

		/**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GEOMETRY_TYPE__Y = eINSTANCE.getGeometryType_Y();

		/**
		 * The meta object literal for the '{@link eu.synligare.sgraphml.impl.GroupNodeTypeImpl <em>Group Node Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.synligare.sgraphml.impl.GroupNodeTypeImpl
		 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getGroupNodeType()
		 * @generated
		 */
		EClass GROUP_NODE_TYPE = eINSTANCE.getGroupNodeType();

		/**
		 * The meta object literal for the '{@link eu.synligare.sgraphml.impl.ImageIconTypeImpl <em>Image Icon Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.synligare.sgraphml.impl.ImageIconTypeImpl
		 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getImageIconType()
		 * @generated
		 */
		EClass IMAGE_ICON_TYPE = eINSTANCE.getImageIconType();

		/**
		 * The meta object literal for the '<em><b>Image</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMAGE_ICON_TYPE__IMAGE = eINSTANCE.getImageIconType_Image();

		/**
		 * The meta object literal for the '{@link eu.synligare.sgraphml.impl.LabelTypeImpl <em>Label Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.synligare.sgraphml.impl.LabelTypeImpl
		 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getLabelType()
		 * @generated
		 */
		EClass LABEL_TYPE = eINSTANCE.getLabelType();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL_TYPE__MIXED = eINSTANCE.getLabelType_Mixed();

		/**
		 * The meta object literal for the '<em><b>Font Family</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL_TYPE__FONT_FAMILY = eINSTANCE.getLabelType_FontFamily();

		/**
		 * The meta object literal for the '<em><b>Font Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL_TYPE__FONT_SIZE = eINSTANCE.getLabelType_FontSize();

		/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL_TYPE__HEIGHT = eINSTANCE.getLabelType_Height();

		/**
		 * The meta object literal for the '<em><b>Text Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL_TYPE__TEXT_COLOR = eINSTANCE.getLabelType_TextColor();

		/**
		 * The meta object literal for the '<em><b>Visible</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL_TYPE__VISIBLE = eINSTANCE.getLabelType_Visible();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL_TYPE__WIDTH = eINSTANCE.getLabelType_Width();

		/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL_TYPE__X = eINSTANCE.getLabelType_X();

		/**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL_TYPE__Y = eINSTANCE.getLabelType_Y();

		/**
		 * The meta object literal for the '{@link eu.synligare.sgraphml.impl.LineStyleTypeImpl <em>Line Style Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.synligare.sgraphml.impl.LineStyleTypeImpl
		 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getLineStyleType()
		 * @generated
		 */
		EClass LINE_STYLE_TYPE = eINSTANCE.getLineStyleType();

		/**
		 * The meta object literal for the '<em><b>Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE_STYLE_TYPE__COLOR = eINSTANCE.getLineStyleType_Color();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE_STYLE_TYPE__TYPE = eINSTANCE.getLineStyleType_Type();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE_STYLE_TYPE__WIDTH = eINSTANCE.getLineStyleType_Width();

		/**
		 * The meta object literal for the '{@link eu.synligare.sgraphml.impl.NodeLabelTypeImpl <em>Node Label Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.synligare.sgraphml.impl.NodeLabelTypeImpl
		 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getNodeLabelType()
		 * @generated
		 */
		EClass NODE_LABEL_TYPE = eINSTANCE.getNodeLabelType();

		/**
		 * The meta object literal for the '<em><b>Horizontal Text Position</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE_LABEL_TYPE__HORIZONTAL_TEXT_POSITION = eINSTANCE.getNodeLabelType_HorizontalTextPosition();

		/**
		 * The meta object literal for the '<em><b>Icon Data</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE_LABEL_TYPE__ICON_DATA = eINSTANCE.getNodeLabelType_IconData();

		/**
		 * The meta object literal for the '<em><b>Icon Text Gap</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE_LABEL_TYPE__ICON_TEXT_GAP = eINSTANCE.getNodeLabelType_IconTextGap();

		/**
		 * The meta object literal for the '<em><b>Placement</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE_LABEL_TYPE__PLACEMENT = eINSTANCE.getNodeLabelType_Placement();

		/**
		 * The meta object literal for the '<em><b>Vertical Text Position</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE_LABEL_TYPE__VERTICAL_TEXT_POSITION = eINSTANCE.getNodeLabelType_VerticalTextPosition();

		/**
		 * The meta object literal for the '{@link eu.synligare.sgraphml.impl.PathTypeImpl <em>Path Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.synligare.sgraphml.impl.PathTypeImpl
		 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getPathType()
		 * @generated
		 */
		EClass PATH_TYPE = eINSTANCE.getPathType();

		/**
		 * The meta object literal for the '<em><b>Point</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PATH_TYPE__POINT = eINSTANCE.getPathType_Point();

		/**
		 * The meta object literal for the '<em><b>Sx</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PATH_TYPE__SX = eINSTANCE.getPathType_Sx();

		/**
		 * The meta object literal for the '<em><b>Sy</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PATH_TYPE__SY = eINSTANCE.getPathType_Sy();

		/**
		 * The meta object literal for the '<em><b>Tx</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PATH_TYPE__TX = eINSTANCE.getPathType_Tx();

		/**
		 * The meta object literal for the '<em><b>Ty</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PATH_TYPE__TY = eINSTANCE.getPathType_Ty();

		/**
		 * The meta object literal for the '{@link eu.synligare.sgraphml.impl.PointTypeImpl <em>Point Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.synligare.sgraphml.impl.PointTypeImpl
		 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getPointType()
		 * @generated
		 */
		EClass POINT_TYPE = eINSTANCE.getPointType();

		/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POINT_TYPE__X = eINSTANCE.getPointType_X();

		/**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POINT_TYPE__Y = eINSTANCE.getPointType_Y();

		/**
		 * The meta object literal for the '{@link eu.synligare.sgraphml.impl.PolyLineEdgeTypeImpl <em>Poly Line Edge Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.synligare.sgraphml.impl.PolyLineEdgeTypeImpl
		 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getPolyLineEdgeType()
		 * @generated
		 */
		EClass POLY_LINE_EDGE_TYPE = eINSTANCE.getPolyLineEdgeType();

		/**
		 * The meta object literal for the '{@link eu.synligare.sgraphml.impl.PortNodeTypeImpl <em>Port Node Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.synligare.sgraphml.impl.PortNodeTypeImpl
		 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getPortNodeType()
		 * @generated
		 */
		EClass PORT_NODE_TYPE = eINSTANCE.getPortNodeType();

		/**
		 * The meta object literal for the '{@link eu.synligare.sgraphml.impl.ResourceBlockTypeImpl <em>Resource Block Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.synligare.sgraphml.impl.ResourceBlockTypeImpl
		 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getResourceBlockType()
		 * @generated
		 */
		EClass RESOURCE_BLOCK_TYPE = eINSTANCE.getResourceBlockType();

		/**
		 * The meta object literal for the '<em><b>Resource</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_BLOCK_TYPE__RESOURCE = eINSTANCE.getResourceBlockType_Resource();

		/**
		 * The meta object literal for the '{@link eu.synligare.sgraphml.impl.ResourceTypeImpl <em>Resource Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.synligare.sgraphml.impl.ResourceTypeImpl
		 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getResourceType()
		 * @generated
		 */
		EClass RESOURCE_TYPE = eINSTANCE.getResourceType();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_TYPE__MIXED = eINSTANCE.getResourceType_Mixed();

		/**
		 * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_TYPE__ANY = eINSTANCE.getResourceType_Any();

		/**
		 * The meta object literal for the '<em><b>Format</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_TYPE__FORMAT = eINSTANCE.getResourceType_Format();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_TYPE__ID = eINSTANCE.getResourceType_Id();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_TYPE__TYPE = eINSTANCE.getResourceType_Type();

		/**
		 * The meta object literal for the '{@link eu.synligare.sgraphml.impl.ScaledIconTypeImpl <em>Scaled Icon Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.synligare.sgraphml.impl.ScaledIconTypeImpl
		 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getScaledIconType()
		 * @generated
		 */
		EClass SCALED_ICON_TYPE = eINSTANCE.getScaledIconType();

		/**
		 * The meta object literal for the '<em><b>Image Icon</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCALED_ICON_TYPE__IMAGE_ICON = eINSTANCE.getScaledIconType_ImageIcon();

		/**
		 * The meta object literal for the '<em><b>XScale</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCALED_ICON_TYPE__XSCALE = eINSTANCE.getScaledIconType_XScale();

		/**
		 * The meta object literal for the '<em><b>YScale</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCALED_ICON_TYPE__YSCALE = eINSTANCE.getScaledIconType_YScale();

		/**
		 * The meta object literal for the '{@link eu.synligare.sgraphml.impl.ShapeNodeTypeImpl <em>Shape Node Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.synligare.sgraphml.impl.ShapeNodeTypeImpl
		 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getShapeNodeType()
		 * @generated
		 */
		EClass SHAPE_NODE_TYPE = eINSTANCE.getShapeNodeType();

		/**
		 * The meta object literal for the '<em><b>Shape</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SHAPE_NODE_TYPE__SHAPE = eINSTANCE.getShapeNodeType_Shape();

		/**
		 * The meta object literal for the '{@link eu.synligare.sgraphml.impl.ShapeTypeImpl <em>Shape Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.synligare.sgraphml.impl.ShapeTypeImpl
		 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getShapeType()
		 * @generated
		 */
		EClass SHAPE_TYPE = eINSTANCE.getShapeType();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SHAPE_TYPE__TYPE = eINSTANCE.getShapeType_Type();

		/**
		 * The meta object literal for the '{@link eu.synligare.sgraphml.ArrowTypeType <em>Arrow Type Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.synligare.sgraphml.ArrowTypeType
		 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getArrowTypeType()
		 * @generated
		 */
		EEnum ARROW_TYPE_TYPE = eINSTANCE.getArrowTypeType();

		/**
		 * The meta object literal for the '{@link eu.synligare.sgraphml.HorizontalTextPositionType <em>Horizontal Text Position Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.synligare.sgraphml.HorizontalTextPositionType
		 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getHorizontalTextPositionType()
		 * @generated
		 */
		EEnum HORIZONTAL_TEXT_POSITION_TYPE = eINSTANCE.getHorizontalTextPositionType();

		/**
		 * The meta object literal for the '{@link eu.synligare.sgraphml.LineTypeType <em>Line Type Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.synligare.sgraphml.LineTypeType
		 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getLineTypeType()
		 * @generated
		 */
		EEnum LINE_TYPE_TYPE = eINSTANCE.getLineTypeType();

		/**
		 * The meta object literal for the '{@link eu.synligare.sgraphml.PlacementType <em>Placement Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.synligare.sgraphml.PlacementType
		 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getPlacementType()
		 * @generated
		 */
		EEnum PLACEMENT_TYPE = eINSTANCE.getPlacementType();

		/**
		 * The meta object literal for the '{@link eu.synligare.sgraphml.ShapeTypeType <em>Shape Type Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.synligare.sgraphml.ShapeTypeType
		 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getShapeTypeType()
		 * @generated
		 */
		EEnum SHAPE_TYPE_TYPE = eINSTANCE.getShapeTypeType();

		/**
		 * The meta object literal for the '{@link eu.synligare.sgraphml.VerticalTextPositionType <em>Vertical Text Position Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.synligare.sgraphml.VerticalTextPositionType
		 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getVerticalTextPositionType()
		 * @generated
		 */
		EEnum VERTICAL_TEXT_POSITION_TYPE = eINSTANCE.getVerticalTextPositionType();

		/**
		 * The meta object literal for the '<em>Arrow Type Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.synligare.sgraphml.ArrowTypeType
		 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getArrowTypeTypeObject()
		 * @generated
		 */
		EDataType ARROW_TYPE_TYPE_OBJECT = eINSTANCE.getArrowTypeTypeObject();

		/**
		 * The meta object literal for the '<em>Color Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.String
		 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getColorType()
		 * @generated
		 */
		EDataType COLOR_TYPE = eINSTANCE.getColorType();

		/**
		 * The meta object literal for the '<em>Horizontal Text Position Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.synligare.sgraphml.HorizontalTextPositionType
		 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getHorizontalTextPositionTypeObject()
		 * @generated
		 */
		EDataType HORIZONTAL_TEXT_POSITION_TYPE_OBJECT = eINSTANCE.getHorizontalTextPositionTypeObject();

		/**
		 * The meta object literal for the '<em>Line Type Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.synligare.sgraphml.LineTypeType
		 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getLineTypeTypeObject()
		 * @generated
		 */
		EDataType LINE_TYPE_TYPE_OBJECT = eINSTANCE.getLineTypeTypeObject();

		/**
		 * The meta object literal for the '<em>Placement Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.synligare.sgraphml.PlacementType
		 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getPlacementTypeObject()
		 * @generated
		 */
		EDataType PLACEMENT_TYPE_OBJECT = eINSTANCE.getPlacementTypeObject();

		/**
		 * The meta object literal for the '<em>Shape Type Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.synligare.sgraphml.ShapeTypeType
		 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getShapeTypeTypeObject()
		 * @generated
		 */
		EDataType SHAPE_TYPE_TYPE_OBJECT = eINSTANCE.getShapeTypeTypeObject();

		/**
		 * The meta object literal for the '<em>Vertical Text Position Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.synligare.sgraphml.VerticalTextPositionType
		 * @see eu.synligare.sgraphml.impl.SgraphmlPackageImpl#getVerticalTextPositionTypeObject()
		 * @generated
		 */
		EDataType VERTICAL_TEXT_POSITION_TYPE_OBJECT = eINSTANCE.getVerticalTextPositionTypeObject();

	}

} //SgraphmlPackage
