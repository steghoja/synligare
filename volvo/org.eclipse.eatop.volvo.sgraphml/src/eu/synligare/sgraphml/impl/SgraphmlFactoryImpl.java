/**
 */
package eu.synligare.sgraphml.impl;

import eu.synligare.sgraphml.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SgraphmlFactoryImpl extends EFactoryImpl implements SgraphmlFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SgraphmlFactory init() {
		try {
			SgraphmlFactory theSgraphmlFactory = (SgraphmlFactory)EPackage.Registry.INSTANCE.getEFactory(SgraphmlPackage.eNS_URI);
			if (theSgraphmlFactory != null) {
				return theSgraphmlFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SgraphmlFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SgraphmlFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case SgraphmlPackage.ARROWS_TYPE: return createArrowsType();
			case SgraphmlPackage.BASE_EDGE_TYPE: return createBaseEdgeType();
			case SgraphmlPackage.BASE_NODE_TYPE: return createBaseNodeType();
			case SgraphmlPackage.DOCUMENT_ROOT: return createDocumentRoot();
			case SgraphmlPackage.EDGE_LABEL_TYPE: return createEdgeLabelType();
			case SgraphmlPackage.FILL_TYPE: return createFillType();
			case SgraphmlPackage.GEOMETRY_TYPE: return createGeometryType();
			case SgraphmlPackage.GROUP_NODE_TYPE: return createGroupNodeType();
			case SgraphmlPackage.IMAGE_ICON_TYPE: return createImageIconType();
			case SgraphmlPackage.LABEL_TYPE: return createLabelType();
			case SgraphmlPackage.LINE_STYLE_TYPE: return createLineStyleType();
			case SgraphmlPackage.NODE_LABEL_TYPE: return createNodeLabelType();
			case SgraphmlPackage.PATH_TYPE: return createPathType();
			case SgraphmlPackage.POINT_TYPE: return createPointType();
			case SgraphmlPackage.POLY_LINE_EDGE_TYPE: return createPolyLineEdgeType();
			case SgraphmlPackage.PORT_NODE_TYPE: return createPortNodeType();
			case SgraphmlPackage.RESOURCE_BLOCK_TYPE: return createResourceBlockType();
			case SgraphmlPackage.RESOURCE_TYPE: return createResourceType();
			case SgraphmlPackage.SCALED_ICON_TYPE: return createScaledIconType();
			case SgraphmlPackage.SHAPE_NODE_TYPE: return createShapeNodeType();
			case SgraphmlPackage.SHAPE_TYPE: return createShapeType();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case SgraphmlPackage.ARROW_TYPE_TYPE:
				return createArrowTypeTypeFromString(eDataType, initialValue);
			case SgraphmlPackage.HORIZONTAL_TEXT_POSITION_TYPE:
				return createHorizontalTextPositionTypeFromString(eDataType, initialValue);
			case SgraphmlPackage.LINE_TYPE_TYPE:
				return createLineTypeTypeFromString(eDataType, initialValue);
			case SgraphmlPackage.PLACEMENT_TYPE:
				return createPlacementTypeFromString(eDataType, initialValue);
			case SgraphmlPackage.SHAPE_TYPE_TYPE:
				return createShapeTypeTypeFromString(eDataType, initialValue);
			case SgraphmlPackage.VERTICAL_TEXT_POSITION_TYPE:
				return createVerticalTextPositionTypeFromString(eDataType, initialValue);
			case SgraphmlPackage.ARROW_TYPE_TYPE_OBJECT:
				return createArrowTypeTypeObjectFromString(eDataType, initialValue);
			case SgraphmlPackage.COLOR_TYPE:
				return createColorTypeFromString(eDataType, initialValue);
			case SgraphmlPackage.HORIZONTAL_TEXT_POSITION_TYPE_OBJECT:
				return createHorizontalTextPositionTypeObjectFromString(eDataType, initialValue);
			case SgraphmlPackage.LINE_TYPE_TYPE_OBJECT:
				return createLineTypeTypeObjectFromString(eDataType, initialValue);
			case SgraphmlPackage.PLACEMENT_TYPE_OBJECT:
				return createPlacementTypeObjectFromString(eDataType, initialValue);
			case SgraphmlPackage.SHAPE_TYPE_TYPE_OBJECT:
				return createShapeTypeTypeObjectFromString(eDataType, initialValue);
			case SgraphmlPackage.VERTICAL_TEXT_POSITION_TYPE_OBJECT:
				return createVerticalTextPositionTypeObjectFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case SgraphmlPackage.ARROW_TYPE_TYPE:
				return convertArrowTypeTypeToString(eDataType, instanceValue);
			case SgraphmlPackage.HORIZONTAL_TEXT_POSITION_TYPE:
				return convertHorizontalTextPositionTypeToString(eDataType, instanceValue);
			case SgraphmlPackage.LINE_TYPE_TYPE:
				return convertLineTypeTypeToString(eDataType, instanceValue);
			case SgraphmlPackage.PLACEMENT_TYPE:
				return convertPlacementTypeToString(eDataType, instanceValue);
			case SgraphmlPackage.SHAPE_TYPE_TYPE:
				return convertShapeTypeTypeToString(eDataType, instanceValue);
			case SgraphmlPackage.VERTICAL_TEXT_POSITION_TYPE:
				return convertVerticalTextPositionTypeToString(eDataType, instanceValue);
			case SgraphmlPackage.ARROW_TYPE_TYPE_OBJECT:
				return convertArrowTypeTypeObjectToString(eDataType, instanceValue);
			case SgraphmlPackage.COLOR_TYPE:
				return convertColorTypeToString(eDataType, instanceValue);
			case SgraphmlPackage.HORIZONTAL_TEXT_POSITION_TYPE_OBJECT:
				return convertHorizontalTextPositionTypeObjectToString(eDataType, instanceValue);
			case SgraphmlPackage.LINE_TYPE_TYPE_OBJECT:
				return convertLineTypeTypeObjectToString(eDataType, instanceValue);
			case SgraphmlPackage.PLACEMENT_TYPE_OBJECT:
				return convertPlacementTypeObjectToString(eDataType, instanceValue);
			case SgraphmlPackage.SHAPE_TYPE_TYPE_OBJECT:
				return convertShapeTypeTypeObjectToString(eDataType, instanceValue);
			case SgraphmlPackage.VERTICAL_TEXT_POSITION_TYPE_OBJECT:
				return convertVerticalTextPositionTypeObjectToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrowsType createArrowsType() {
		ArrowsTypeImpl arrowsType = new ArrowsTypeImpl();
		return arrowsType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BaseEdgeType createBaseEdgeType() {
		BaseEdgeTypeImpl baseEdgeType = new BaseEdgeTypeImpl();
		return baseEdgeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BaseNodeType createBaseNodeType() {
		BaseNodeTypeImpl baseNodeType = new BaseNodeTypeImpl();
		return baseNodeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DocumentRoot createDocumentRoot() {
		DocumentRootImpl documentRoot = new DocumentRootImpl();
		return documentRoot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EdgeLabelType createEdgeLabelType() {
		EdgeLabelTypeImpl edgeLabelType = new EdgeLabelTypeImpl();
		return edgeLabelType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FillType createFillType() {
		FillTypeImpl fillType = new FillTypeImpl();
		return fillType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeometryType createGeometryType() {
		GeometryTypeImpl geometryType = new GeometryTypeImpl();
		return geometryType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GroupNodeType createGroupNodeType() {
		GroupNodeTypeImpl groupNodeType = new GroupNodeTypeImpl();
		return groupNodeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImageIconType createImageIconType() {
		ImageIconTypeImpl imageIconType = new ImageIconTypeImpl();
		return imageIconType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LabelType createLabelType() {
		LabelTypeImpl labelType = new LabelTypeImpl();
		return labelType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LineStyleType createLineStyleType() {
		LineStyleTypeImpl lineStyleType = new LineStyleTypeImpl();
		return lineStyleType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NodeLabelType createNodeLabelType() {
		NodeLabelTypeImpl nodeLabelType = new NodeLabelTypeImpl();
		return nodeLabelType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PathType createPathType() {
		PathTypeImpl pathType = new PathTypeImpl();
		return pathType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PointType createPointType() {
		PointTypeImpl pointType = new PointTypeImpl();
		return pointType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PolyLineEdgeType createPolyLineEdgeType() {
		PolyLineEdgeTypeImpl polyLineEdgeType = new PolyLineEdgeTypeImpl();
		return polyLineEdgeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortNodeType createPortNodeType() {
		PortNodeTypeImpl portNodeType = new PortNodeTypeImpl();
		return portNodeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceBlockType createResourceBlockType() {
		ResourceBlockTypeImpl resourceBlockType = new ResourceBlockTypeImpl();
		return resourceBlockType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceType createResourceType() {
		ResourceTypeImpl resourceType = new ResourceTypeImpl();
		return resourceType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaledIconType createScaledIconType() {
		ScaledIconTypeImpl scaledIconType = new ScaledIconTypeImpl();
		return scaledIconType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ShapeNodeType createShapeNodeType() {
		ShapeNodeTypeImpl shapeNodeType = new ShapeNodeTypeImpl();
		return shapeNodeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ShapeType createShapeType() {
		ShapeTypeImpl shapeType = new ShapeTypeImpl();
		return shapeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrowTypeType createArrowTypeTypeFromString(EDataType eDataType, String initialValue) {
		ArrowTypeType result = ArrowTypeType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertArrowTypeTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HorizontalTextPositionType createHorizontalTextPositionTypeFromString(EDataType eDataType, String initialValue) {
		HorizontalTextPositionType result = HorizontalTextPositionType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertHorizontalTextPositionTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LineTypeType createLineTypeTypeFromString(EDataType eDataType, String initialValue) {
		LineTypeType result = LineTypeType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLineTypeTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PlacementType createPlacementTypeFromString(EDataType eDataType, String initialValue) {
		PlacementType result = PlacementType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPlacementTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ShapeTypeType createShapeTypeTypeFromString(EDataType eDataType, String initialValue) {
		ShapeTypeType result = ShapeTypeType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertShapeTypeTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VerticalTextPositionType createVerticalTextPositionTypeFromString(EDataType eDataType, String initialValue) {
		VerticalTextPositionType result = VerticalTextPositionType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertVerticalTextPositionTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrowTypeType createArrowTypeTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createArrowTypeTypeFromString(SgraphmlPackage.Literals.ARROW_TYPE_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertArrowTypeTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertArrowTypeTypeToString(SgraphmlPackage.Literals.ARROW_TYPE_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createColorTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.STRING, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertColorTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.STRING, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HorizontalTextPositionType createHorizontalTextPositionTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createHorizontalTextPositionTypeFromString(SgraphmlPackage.Literals.HORIZONTAL_TEXT_POSITION_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertHorizontalTextPositionTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertHorizontalTextPositionTypeToString(SgraphmlPackage.Literals.HORIZONTAL_TEXT_POSITION_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LineTypeType createLineTypeTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createLineTypeTypeFromString(SgraphmlPackage.Literals.LINE_TYPE_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLineTypeTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertLineTypeTypeToString(SgraphmlPackage.Literals.LINE_TYPE_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PlacementType createPlacementTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createPlacementTypeFromString(SgraphmlPackage.Literals.PLACEMENT_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPlacementTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertPlacementTypeToString(SgraphmlPackage.Literals.PLACEMENT_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ShapeTypeType createShapeTypeTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createShapeTypeTypeFromString(SgraphmlPackage.Literals.SHAPE_TYPE_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertShapeTypeTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertShapeTypeTypeToString(SgraphmlPackage.Literals.SHAPE_TYPE_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VerticalTextPositionType createVerticalTextPositionTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createVerticalTextPositionTypeFromString(SgraphmlPackage.Literals.VERTICAL_TEXT_POSITION_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertVerticalTextPositionTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertVerticalTextPositionTypeToString(SgraphmlPackage.Literals.VERTICAL_TEXT_POSITION_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SgraphmlPackage getSgraphmlPackage() {
		return (SgraphmlPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static SgraphmlPackage getPackage() {
		return SgraphmlPackage.eINSTANCE;
	}

} //SgraphmlFactoryImpl
