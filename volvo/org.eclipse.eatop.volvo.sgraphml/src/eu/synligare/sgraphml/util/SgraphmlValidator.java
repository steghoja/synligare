/**
 */
package eu.synligare.sgraphml.util;

import eu.synligare.sgraphml.*;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

import org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil;
import org.eclipse.emf.ecore.xml.type.util.XMLTypeValidator;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see eu.synligare.sgraphml.SgraphmlPackage
 * @generated
 */
public class SgraphmlValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final SgraphmlValidator INSTANCE = new SgraphmlValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "eu.synligare.sgraphml";

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * The cached base package validator.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected XMLTypeValidator xmlTypeValidator;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SgraphmlValidator() {
		super();
		xmlTypeValidator = XMLTypeValidator.INSTANCE;
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return SgraphmlPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case SgraphmlPackage.ARROWS_TYPE:
				return validateArrowsType((ArrowsType)value, diagnostics, context);
			case SgraphmlPackage.BASE_EDGE_TYPE:
				return validateBaseEdgeType((BaseEdgeType)value, diagnostics, context);
			case SgraphmlPackage.BASE_NODE_TYPE:
				return validateBaseNodeType((BaseNodeType)value, diagnostics, context);
			case SgraphmlPackage.DOCUMENT_ROOT:
				return validateDocumentRoot((DocumentRoot)value, diagnostics, context);
			case SgraphmlPackage.EDGE_LABEL_TYPE:
				return validateEdgeLabelType((EdgeLabelType)value, diagnostics, context);
			case SgraphmlPackage.FILL_TYPE:
				return validateFillType((FillType)value, diagnostics, context);
			case SgraphmlPackage.GEOMETRY_TYPE:
				return validateGeometryType((GeometryType)value, diagnostics, context);
			case SgraphmlPackage.GROUP_NODE_TYPE:
				return validateGroupNodeType((GroupNodeType)value, diagnostics, context);
			case SgraphmlPackage.IMAGE_ICON_TYPE:
				return validateImageIconType((ImageIconType)value, diagnostics, context);
			case SgraphmlPackage.LABEL_TYPE:
				return validateLabelType((LabelType)value, diagnostics, context);
			case SgraphmlPackage.LINE_STYLE_TYPE:
				return validateLineStyleType((LineStyleType)value, diagnostics, context);
			case SgraphmlPackage.NODE_LABEL_TYPE:
				return validateNodeLabelType((NodeLabelType)value, diagnostics, context);
			case SgraphmlPackage.PATH_TYPE:
				return validatePathType((PathType)value, diagnostics, context);
			case SgraphmlPackage.POINT_TYPE:
				return validatePointType((PointType)value, diagnostics, context);
			case SgraphmlPackage.POLY_LINE_EDGE_TYPE:
				return validatePolyLineEdgeType((PolyLineEdgeType)value, diagnostics, context);
			case SgraphmlPackage.PORT_NODE_TYPE:
				return validatePortNodeType((PortNodeType)value, diagnostics, context);
			case SgraphmlPackage.RESOURCE_BLOCK_TYPE:
				return validateResourceBlockType((ResourceBlockType)value, diagnostics, context);
			case SgraphmlPackage.RESOURCE_TYPE:
				return validateResourceType((ResourceType)value, diagnostics, context);
			case SgraphmlPackage.SCALED_ICON_TYPE:
				return validateScaledIconType((ScaledIconType)value, diagnostics, context);
			case SgraphmlPackage.SHAPE_NODE_TYPE:
				return validateShapeNodeType((ShapeNodeType)value, diagnostics, context);
			case SgraphmlPackage.SHAPE_TYPE:
				return validateShapeType((ShapeType)value, diagnostics, context);
			case SgraphmlPackage.ARROW_TYPE_TYPE:
				return validateArrowTypeType((ArrowTypeType)value, diagnostics, context);
			case SgraphmlPackage.HORIZONTAL_TEXT_POSITION_TYPE:
				return validateHorizontalTextPositionType((HorizontalTextPositionType)value, diagnostics, context);
			case SgraphmlPackage.LINE_TYPE_TYPE:
				return validateLineTypeType((LineTypeType)value, diagnostics, context);
			case SgraphmlPackage.PLACEMENT_TYPE:
				return validatePlacementType((PlacementType)value, diagnostics, context);
			case SgraphmlPackage.SHAPE_TYPE_TYPE:
				return validateShapeTypeType((ShapeTypeType)value, diagnostics, context);
			case SgraphmlPackage.VERTICAL_TEXT_POSITION_TYPE:
				return validateVerticalTextPositionType((VerticalTextPositionType)value, diagnostics, context);
			case SgraphmlPackage.ARROW_TYPE_TYPE_OBJECT:
				return validateArrowTypeTypeObject((ArrowTypeType)value, diagnostics, context);
			case SgraphmlPackage.COLOR_TYPE:
				return validateColorType((String)value, diagnostics, context);
			case SgraphmlPackage.HORIZONTAL_TEXT_POSITION_TYPE_OBJECT:
				return validateHorizontalTextPositionTypeObject((HorizontalTextPositionType)value, diagnostics, context);
			case SgraphmlPackage.LINE_TYPE_TYPE_OBJECT:
				return validateLineTypeTypeObject((LineTypeType)value, diagnostics, context);
			case SgraphmlPackage.PLACEMENT_TYPE_OBJECT:
				return validatePlacementTypeObject((PlacementType)value, diagnostics, context);
			case SgraphmlPackage.SHAPE_TYPE_TYPE_OBJECT:
				return validateShapeTypeTypeObject((ShapeTypeType)value, diagnostics, context);
			case SgraphmlPackage.VERTICAL_TEXT_POSITION_TYPE_OBJECT:
				return validateVerticalTextPositionTypeObject((VerticalTextPositionType)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateArrowsType(ArrowsType arrowsType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(arrowsType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBaseEdgeType(BaseEdgeType baseEdgeType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(baseEdgeType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBaseNodeType(BaseNodeType baseNodeType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(baseNodeType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDocumentRoot(DocumentRoot documentRoot, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(documentRoot, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEdgeLabelType(EdgeLabelType edgeLabelType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(edgeLabelType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFillType(FillType fillType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(fillType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGeometryType(GeometryType geometryType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(geometryType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGroupNodeType(GroupNodeType groupNodeType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(groupNodeType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateImageIconType(ImageIconType imageIconType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(imageIconType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLabelType(LabelType labelType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(labelType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLineStyleType(LineStyleType lineStyleType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(lineStyleType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNodeLabelType(NodeLabelType nodeLabelType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(nodeLabelType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePathType(PathType pathType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(pathType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePointType(PointType pointType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(pointType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePolyLineEdgeType(PolyLineEdgeType polyLineEdgeType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(polyLineEdgeType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePortNodeType(PortNodeType portNodeType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(portNodeType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateResourceBlockType(ResourceBlockType resourceBlockType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(resourceBlockType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateResourceType(ResourceType resourceType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(resourceType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScaledIconType(ScaledIconType scaledIconType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(scaledIconType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateShapeNodeType(ShapeNodeType shapeNodeType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(shapeNodeType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateShapeType(ShapeType shapeType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(shapeType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateArrowTypeType(ArrowTypeType arrowTypeType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHorizontalTextPositionType(HorizontalTextPositionType horizontalTextPositionType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLineTypeType(LineTypeType lineTypeType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePlacementType(PlacementType placementType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateShapeTypeType(ShapeTypeType shapeTypeType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateVerticalTextPositionType(VerticalTextPositionType verticalTextPositionType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateArrowTypeTypeObject(ArrowTypeType arrowTypeTypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateColorType(String colorType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateColorType_Pattern(colorType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateColorType_Pattern
	 */
	public static final  PatternMatcher [][] COLOR_TYPE__PATTERN__VALUES =
		new PatternMatcher [][] {
			new PatternMatcher [] {
				XMLTypeUtil.createPatternMatcher("#(([A-F]|[0-9]){2}){3,4}")
			}
		};

	/**
	 * Validates the Pattern constraint of '<em>Color Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateColorType_Pattern(String colorType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validatePattern(SgraphmlPackage.Literals.COLOR_TYPE, colorType, COLOR_TYPE__PATTERN__VALUES, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHorizontalTextPositionTypeObject(HorizontalTextPositionType horizontalTextPositionTypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLineTypeTypeObject(LineTypeType lineTypeTypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePlacementTypeObject(PlacementType placementTypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateShapeTypeTypeObject(ShapeTypeType shapeTypeTypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateVerticalTextPositionTypeObject(VerticalTextPositionType verticalTextPositionTypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} //SgraphmlValidator
