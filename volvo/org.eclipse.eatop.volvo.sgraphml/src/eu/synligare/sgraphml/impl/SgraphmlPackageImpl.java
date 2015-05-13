/**
 */
package eu.synligare.sgraphml.impl;

import eu.synligare.sgraphml.ArrowTypeType;
import eu.synligare.sgraphml.ArrowsType;
import eu.synligare.sgraphml.BaseEdgeType;
import eu.synligare.sgraphml.BaseNodeType;
import eu.synligare.sgraphml.DocumentRoot;
import eu.synligare.sgraphml.EdgeLabelType;
import eu.synligare.sgraphml.FillType;
import eu.synligare.sgraphml.GeometryType;
import eu.synligare.sgraphml.GroupNodeType;
import eu.synligare.sgraphml.HorizontalTextPositionType;
import eu.synligare.sgraphml.ImageIconType;
import eu.synligare.sgraphml.LabelType;
import eu.synligare.sgraphml.LineStyleType;
import eu.synligare.sgraphml.LineTypeType;
import eu.synligare.sgraphml.NodeLabelType;
import eu.synligare.sgraphml.PathType;
import eu.synligare.sgraphml.PlacementType;
import eu.synligare.sgraphml.PointType;
import eu.synligare.sgraphml.PolyLineEdgeType;
import eu.synligare.sgraphml.PortNodeType;
import eu.synligare.sgraphml.ResourceBlockType;
import eu.synligare.sgraphml.ResourceType;
import eu.synligare.sgraphml.ScaledIconType;
import eu.synligare.sgraphml.SgraphmlFactory;
import eu.synligare.sgraphml.SgraphmlPackage;
import eu.synligare.sgraphml.ShapeNodeType;
import eu.synligare.sgraphml.ShapeType;
import eu.synligare.sgraphml.ShapeTypeType;
import eu.synligare.sgraphml.VerticalTextPositionType;

import eu.synligare.sgraphml.util.SgraphmlValidator;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.graphdrawing.graphml.xmlns.XmlnsPackage;

import org.graphdrawing.graphml.xmlns.impl.XmlnsPackageImpl;

import org.w3._1999.xlink.XlinkPackage;

import org.w3._1999.xlink.impl.XlinkPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SgraphmlPackageImpl extends EPackageImpl implements SgraphmlPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass arrowsTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass baseEdgeTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass baseNodeTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass documentRootEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass edgeLabelTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fillTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass geometryTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass groupNodeTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass imageIconTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass labelTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass lineStyleTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nodeLabelTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pathTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pointTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass polyLineEdgeTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass portNodeTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceBlockTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scaledIconTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass shapeNodeTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass shapeTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum arrowTypeTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum horizontalTextPositionTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum lineTypeTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum placementTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum shapeTypeTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum verticalTextPositionTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType arrowTypeTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType colorTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType horizontalTextPositionTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType lineTypeTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType placementTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType shapeTypeTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType verticalTextPositionTypeObjectEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see eu.synligare.sgraphml.SgraphmlPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private SgraphmlPackageImpl() {
		super(eNS_URI, SgraphmlFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link SgraphmlPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static SgraphmlPackage init() {
		if (isInited) return (SgraphmlPackage)EPackage.Registry.INSTANCE.getEPackage(SgraphmlPackage.eNS_URI);

		// Obtain or create and register package
		SgraphmlPackageImpl theSgraphmlPackage = (SgraphmlPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof SgraphmlPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new SgraphmlPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XMLTypePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		XmlnsPackageImpl theXmlnsPackage = (XmlnsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(XmlnsPackage.eNS_URI) instanceof XmlnsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(XmlnsPackage.eNS_URI) : XmlnsPackage.eINSTANCE);
		XlinkPackageImpl theXlinkPackage = (XlinkPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(XlinkPackage.eNS_URI) instanceof XlinkPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(XlinkPackage.eNS_URI) : XlinkPackage.eINSTANCE);

		// Create package meta-data objects
		theSgraphmlPackage.createPackageContents();
		theXmlnsPackage.createPackageContents();
		theXlinkPackage.createPackageContents();

		// Initialize created meta-data
		theSgraphmlPackage.initializePackageContents();
		theXmlnsPackage.initializePackageContents();
		theXlinkPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theSgraphmlPackage, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return SgraphmlValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theSgraphmlPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(SgraphmlPackage.eNS_URI, theSgraphmlPackage);
		return theSgraphmlPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArrowsType() {
		return arrowsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArrowsType_Source() {
		return (EAttribute)arrowsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArrowsType_Target() {
		return (EAttribute)arrowsTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBaseEdgeType() {
		return baseEdgeTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBaseEdgeType_Path() {
		return (EReference)baseEdgeTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBaseEdgeType_LineStyle() {
		return (EReference)baseEdgeTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBaseEdgeType_Arrows() {
		return (EReference)baseEdgeTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBaseEdgeType_EdgeLabel() {
		return (EReference)baseEdgeTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBaseNodeType() {
		return baseNodeTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBaseNodeType_Geometry() {
		return (EReference)baseNodeTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBaseNodeType_Fill() {
		return (EReference)baseNodeTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBaseNodeType_BorderStyle() {
		return (EReference)baseNodeTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBaseNodeType_NodeLabel() {
		return (EReference)baseNodeTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDocumentRoot() {
		return documentRootEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Mixed() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XMLNSPrefixMap() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XSISchemaLocation() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_GroupNode() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_PolyLineEdge() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_PortNode() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Resources() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ScaledIcon() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ShapeNode() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEdgeLabelType() {
		return edgeLabelTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFillType() {
		return fillTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFillType_Color() {
		return (EAttribute)fillTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFillType_Color2() {
		return (EAttribute)fillTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGeometryType() {
		return geometryTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGeometryType_Height() {
		return (EAttribute)geometryTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGeometryType_Width() {
		return (EAttribute)geometryTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGeometryType_X() {
		return (EAttribute)geometryTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGeometryType_Y() {
		return (EAttribute)geometryTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGroupNodeType() {
		return groupNodeTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getImageIconType() {
		return imageIconTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getImageIconType_Image() {
		return (EAttribute)imageIconTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLabelType() {
		return labelTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLabelType_Mixed() {
		return (EAttribute)labelTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLabelType_FontFamily() {
		return (EAttribute)labelTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLabelType_FontSize() {
		return (EAttribute)labelTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLabelType_Height() {
		return (EAttribute)labelTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLabelType_TextColor() {
		return (EAttribute)labelTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLabelType_Width() {
		return (EAttribute)labelTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLabelType_Visible() {
		return (EAttribute)labelTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLabelType_X() {
		return (EAttribute)labelTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLabelType_Y() {
		return (EAttribute)labelTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLineStyleType() {
		return lineStyleTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLineStyleType_Color() {
		return (EAttribute)lineStyleTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLineStyleType_Type() {
		return (EAttribute)lineStyleTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLineStyleType_Width() {
		return (EAttribute)lineStyleTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNodeLabelType() {
		return nodeLabelTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNodeLabelType_HorizontalTextPosition() {
		return (EAttribute)nodeLabelTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNodeLabelType_IconData() {
		return (EAttribute)nodeLabelTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNodeLabelType_IconTextGap() {
		return (EAttribute)nodeLabelTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNodeLabelType_Placement() {
		return (EAttribute)nodeLabelTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNodeLabelType_VerticalTextPosition() {
		return (EAttribute)nodeLabelTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPathType() {
		return pathTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPathType_Point() {
		return (EReference)pathTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPathType_Sx() {
		return (EAttribute)pathTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPathType_Sy() {
		return (EAttribute)pathTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPathType_Tx() {
		return (EAttribute)pathTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPathType_Ty() {
		return (EAttribute)pathTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPointType() {
		return pointTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPointType_X() {
		return (EAttribute)pointTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPointType_Y() {
		return (EAttribute)pointTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPolyLineEdgeType() {
		return polyLineEdgeTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPortNodeType() {
		return portNodeTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getResourceBlockType() {
		return resourceBlockTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResourceBlockType_Resource() {
		return (EReference)resourceBlockTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getResourceType() {
		return resourceTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResourceType_Mixed() {
		return (EAttribute)resourceTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResourceType_Any() {
		return (EAttribute)resourceTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResourceType_Format() {
		return (EAttribute)resourceTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResourceType_Id() {
		return (EAttribute)resourceTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResourceType_Type() {
		return (EAttribute)resourceTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getScaledIconType() {
		return scaledIconTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScaledIconType_ImageIcon() {
		return (EReference)scaledIconTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getScaledIconType_XScale() {
		return (EAttribute)scaledIconTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getScaledIconType_YScale() {
		return (EAttribute)scaledIconTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getShapeNodeType() {
		return shapeNodeTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getShapeNodeType_Shape() {
		return (EReference)shapeNodeTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getShapeType() {
		return shapeTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getShapeType_Type() {
		return (EAttribute)shapeTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getArrowTypeType() {
		return arrowTypeTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getHorizontalTextPositionType() {
		return horizontalTextPositionTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getLineTypeType() {
		return lineTypeTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getPlacementType() {
		return placementTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getShapeTypeType() {
		return shapeTypeTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getVerticalTextPositionType() {
		return verticalTextPositionTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getArrowTypeTypeObject() {
		return arrowTypeTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getColorType() {
		return colorTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getHorizontalTextPositionTypeObject() {
		return horizontalTextPositionTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getLineTypeTypeObject() {
		return lineTypeTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getPlacementTypeObject() {
		return placementTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getShapeTypeTypeObject() {
		return shapeTypeTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getVerticalTextPositionTypeObject() {
		return verticalTextPositionTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SgraphmlFactory getSgraphmlFactory() {
		return (SgraphmlFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		arrowsTypeEClass = createEClass(ARROWS_TYPE);
		createEAttribute(arrowsTypeEClass, ARROWS_TYPE__SOURCE);
		createEAttribute(arrowsTypeEClass, ARROWS_TYPE__TARGET);

		baseEdgeTypeEClass = createEClass(BASE_EDGE_TYPE);
		createEReference(baseEdgeTypeEClass, BASE_EDGE_TYPE__PATH);
		createEReference(baseEdgeTypeEClass, BASE_EDGE_TYPE__LINE_STYLE);
		createEReference(baseEdgeTypeEClass, BASE_EDGE_TYPE__ARROWS);
		createEReference(baseEdgeTypeEClass, BASE_EDGE_TYPE__EDGE_LABEL);

		baseNodeTypeEClass = createEClass(BASE_NODE_TYPE);
		createEReference(baseNodeTypeEClass, BASE_NODE_TYPE__GEOMETRY);
		createEReference(baseNodeTypeEClass, BASE_NODE_TYPE__FILL);
		createEReference(baseNodeTypeEClass, BASE_NODE_TYPE__BORDER_STYLE);
		createEReference(baseNodeTypeEClass, BASE_NODE_TYPE__NODE_LABEL);

		documentRootEClass = createEClass(DOCUMENT_ROOT);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__GROUP_NODE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__POLY_LINE_EDGE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__PORT_NODE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__RESOURCES);
		createEReference(documentRootEClass, DOCUMENT_ROOT__SCALED_ICON);
		createEReference(documentRootEClass, DOCUMENT_ROOT__SHAPE_NODE);

		edgeLabelTypeEClass = createEClass(EDGE_LABEL_TYPE);

		fillTypeEClass = createEClass(FILL_TYPE);
		createEAttribute(fillTypeEClass, FILL_TYPE__COLOR);
		createEAttribute(fillTypeEClass, FILL_TYPE__COLOR2);

		geometryTypeEClass = createEClass(GEOMETRY_TYPE);
		createEAttribute(geometryTypeEClass, GEOMETRY_TYPE__HEIGHT);
		createEAttribute(geometryTypeEClass, GEOMETRY_TYPE__WIDTH);
		createEAttribute(geometryTypeEClass, GEOMETRY_TYPE__X);
		createEAttribute(geometryTypeEClass, GEOMETRY_TYPE__Y);

		groupNodeTypeEClass = createEClass(GROUP_NODE_TYPE);

		imageIconTypeEClass = createEClass(IMAGE_ICON_TYPE);
		createEAttribute(imageIconTypeEClass, IMAGE_ICON_TYPE__IMAGE);

		labelTypeEClass = createEClass(LABEL_TYPE);
		createEAttribute(labelTypeEClass, LABEL_TYPE__MIXED);
		createEAttribute(labelTypeEClass, LABEL_TYPE__FONT_FAMILY);
		createEAttribute(labelTypeEClass, LABEL_TYPE__FONT_SIZE);
		createEAttribute(labelTypeEClass, LABEL_TYPE__HEIGHT);
		createEAttribute(labelTypeEClass, LABEL_TYPE__TEXT_COLOR);
		createEAttribute(labelTypeEClass, LABEL_TYPE__WIDTH);
		createEAttribute(labelTypeEClass, LABEL_TYPE__VISIBLE);
		createEAttribute(labelTypeEClass, LABEL_TYPE__X);
		createEAttribute(labelTypeEClass, LABEL_TYPE__Y);

		lineStyleTypeEClass = createEClass(LINE_STYLE_TYPE);
		createEAttribute(lineStyleTypeEClass, LINE_STYLE_TYPE__COLOR);
		createEAttribute(lineStyleTypeEClass, LINE_STYLE_TYPE__TYPE);
		createEAttribute(lineStyleTypeEClass, LINE_STYLE_TYPE__WIDTH);

		nodeLabelTypeEClass = createEClass(NODE_LABEL_TYPE);
		createEAttribute(nodeLabelTypeEClass, NODE_LABEL_TYPE__HORIZONTAL_TEXT_POSITION);
		createEAttribute(nodeLabelTypeEClass, NODE_LABEL_TYPE__ICON_DATA);
		createEAttribute(nodeLabelTypeEClass, NODE_LABEL_TYPE__ICON_TEXT_GAP);
		createEAttribute(nodeLabelTypeEClass, NODE_LABEL_TYPE__PLACEMENT);
		createEAttribute(nodeLabelTypeEClass, NODE_LABEL_TYPE__VERTICAL_TEXT_POSITION);

		pathTypeEClass = createEClass(PATH_TYPE);
		createEReference(pathTypeEClass, PATH_TYPE__POINT);
		createEAttribute(pathTypeEClass, PATH_TYPE__SX);
		createEAttribute(pathTypeEClass, PATH_TYPE__SY);
		createEAttribute(pathTypeEClass, PATH_TYPE__TX);
		createEAttribute(pathTypeEClass, PATH_TYPE__TY);

		pointTypeEClass = createEClass(POINT_TYPE);
		createEAttribute(pointTypeEClass, POINT_TYPE__X);
		createEAttribute(pointTypeEClass, POINT_TYPE__Y);

		polyLineEdgeTypeEClass = createEClass(POLY_LINE_EDGE_TYPE);

		portNodeTypeEClass = createEClass(PORT_NODE_TYPE);

		resourceBlockTypeEClass = createEClass(RESOURCE_BLOCK_TYPE);
		createEReference(resourceBlockTypeEClass, RESOURCE_BLOCK_TYPE__RESOURCE);

		resourceTypeEClass = createEClass(RESOURCE_TYPE);
		createEAttribute(resourceTypeEClass, RESOURCE_TYPE__MIXED);
		createEAttribute(resourceTypeEClass, RESOURCE_TYPE__ANY);
		createEAttribute(resourceTypeEClass, RESOURCE_TYPE__FORMAT);
		createEAttribute(resourceTypeEClass, RESOURCE_TYPE__ID);
		createEAttribute(resourceTypeEClass, RESOURCE_TYPE__TYPE);

		scaledIconTypeEClass = createEClass(SCALED_ICON_TYPE);
		createEReference(scaledIconTypeEClass, SCALED_ICON_TYPE__IMAGE_ICON);
		createEAttribute(scaledIconTypeEClass, SCALED_ICON_TYPE__XSCALE);
		createEAttribute(scaledIconTypeEClass, SCALED_ICON_TYPE__YSCALE);

		shapeNodeTypeEClass = createEClass(SHAPE_NODE_TYPE);
		createEReference(shapeNodeTypeEClass, SHAPE_NODE_TYPE__SHAPE);

		shapeTypeEClass = createEClass(SHAPE_TYPE);
		createEAttribute(shapeTypeEClass, SHAPE_TYPE__TYPE);

		// Create enums
		arrowTypeTypeEEnum = createEEnum(ARROW_TYPE_TYPE);
		horizontalTextPositionTypeEEnum = createEEnum(HORIZONTAL_TEXT_POSITION_TYPE);
		lineTypeTypeEEnum = createEEnum(LINE_TYPE_TYPE);
		placementTypeEEnum = createEEnum(PLACEMENT_TYPE);
		shapeTypeTypeEEnum = createEEnum(SHAPE_TYPE_TYPE);
		verticalTextPositionTypeEEnum = createEEnum(VERTICAL_TEXT_POSITION_TYPE);

		// Create data types
		arrowTypeTypeObjectEDataType = createEDataType(ARROW_TYPE_TYPE_OBJECT);
		colorTypeEDataType = createEDataType(COLOR_TYPE);
		horizontalTextPositionTypeObjectEDataType = createEDataType(HORIZONTAL_TEXT_POSITION_TYPE_OBJECT);
		lineTypeTypeObjectEDataType = createEDataType(LINE_TYPE_TYPE_OBJECT);
		placementTypeObjectEDataType = createEDataType(PLACEMENT_TYPE_OBJECT);
		shapeTypeTypeObjectEDataType = createEDataType(SHAPE_TYPE_TYPE_OBJECT);
		verticalTextPositionTypeObjectEDataType = createEDataType(VERTICAL_TEXT_POSITION_TYPE_OBJECT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		edgeLabelTypeEClass.getESuperTypes().add(this.getLabelType());
		groupNodeTypeEClass.getESuperTypes().add(this.getShapeNodeType());
		nodeLabelTypeEClass.getESuperTypes().add(this.getLabelType());
		polyLineEdgeTypeEClass.getESuperTypes().add(this.getBaseEdgeType());
		portNodeTypeEClass.getESuperTypes().add(this.getBaseNodeType());
		shapeNodeTypeEClass.getESuperTypes().add(this.getBaseNodeType());

		// Initialize classes, features, and operations; add parameters
		initEClass(arrowsTypeEClass, ArrowsType.class, "ArrowsType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getArrowsType_Source(), this.getArrowTypeType(), "source", null, 0, 1, ArrowsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArrowsType_Target(), this.getArrowTypeType(), "target", null, 0, 1, ArrowsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(baseEdgeTypeEClass, BaseEdgeType.class, "BaseEdgeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBaseEdgeType_Path(), this.getPathType(), null, "path", null, 0, 1, BaseEdgeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBaseEdgeType_LineStyle(), this.getLineStyleType(), null, "lineStyle", null, 0, 1, BaseEdgeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBaseEdgeType_Arrows(), this.getArrowsType(), null, "arrows", null, 0, 1, BaseEdgeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBaseEdgeType_EdgeLabel(), this.getEdgeLabelType(), null, "edgeLabel", null, 0, 1, BaseEdgeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(baseNodeTypeEClass, BaseNodeType.class, "BaseNodeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBaseNodeType_Geometry(), this.getGeometryType(), null, "geometry", null, 0, 1, BaseNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBaseNodeType_Fill(), this.getFillType(), null, "fill", null, 0, 1, BaseNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBaseNodeType_BorderStyle(), this.getLineStyleType(), null, "borderStyle", null, 0, 1, BaseNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBaseNodeType_NodeLabel(), this.getNodeLabelType(), null, "nodeLabel", null, 0, -1, BaseNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_GroupNode(), this.getGroupNodeType(), null, "groupNode", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_PolyLineEdge(), this.getPolyLineEdgeType(), null, "polyLineEdge", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_PortNode(), this.getPortNodeType(), null, "portNode", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Resources(), this.getResourceBlockType(), null, "resources", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ScaledIcon(), this.getScaledIconType(), null, "scaledIcon", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ShapeNode(), this.getShapeNodeType(), null, "shapeNode", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(edgeLabelTypeEClass, EdgeLabelType.class, "EdgeLabelType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(fillTypeEClass, FillType.class, "FillType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFillType_Color(), theXMLTypePackage.getString(), "color", null, 0, 1, FillType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFillType_Color2(), theXMLTypePackage.getString(), "color2", null, 0, 1, FillType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(geometryTypeEClass, GeometryType.class, "GeometryType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGeometryType_Height(), theXMLTypePackage.getDouble(), "height", null, 1, 1, GeometryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGeometryType_Width(), theXMLTypePackage.getDouble(), "width", null, 1, 1, GeometryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGeometryType_X(), theXMLTypePackage.getDouble(), "x", null, 1, 1, GeometryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGeometryType_Y(), theXMLTypePackage.getDouble(), "y", null, 1, 1, GeometryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(groupNodeTypeEClass, GroupNodeType.class, "GroupNodeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(imageIconTypeEClass, ImageIconType.class, "ImageIconType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getImageIconType_Image(), theXMLTypePackage.getAnySimpleType(), "image", null, 1, 1, ImageIconType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(labelTypeEClass, LabelType.class, "LabelType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLabelType_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, LabelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabelType_FontFamily(), theXMLTypePackage.getString(), "fontFamily", null, 0, 1, LabelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabelType_FontSize(), theXMLTypePackage.getUnsignedByte(), "fontSize", null, 0, 1, LabelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabelType_Height(), theXMLTypePackage.getDouble(), "height", null, 0, 1, LabelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabelType_TextColor(), this.getColorType(), "textColor", null, 0, 1, LabelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabelType_Width(), theXMLTypePackage.getDouble(), "width", null, 0, 1, LabelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabelType_Visible(), theXMLTypePackage.getBoolean(), "visible", null, 0, 1, LabelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabelType_X(), theXMLTypePackage.getDouble(), "x", null, 0, 1, LabelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabelType_Y(), theXMLTypePackage.getDouble(), "y", null, 0, 1, LabelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(lineStyleTypeEClass, LineStyleType.class, "LineStyleType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLineStyleType_Color(), this.getColorType(), "color", null, 0, 1, LineStyleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLineStyleType_Type(), this.getLineTypeType(), "type", null, 0, 1, LineStyleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLineStyleType_Width(), theXMLTypePackage.getDouble(), "width", null, 0, 1, LineStyleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(nodeLabelTypeEClass, NodeLabelType.class, "NodeLabelType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNodeLabelType_HorizontalTextPosition(), this.getHorizontalTextPositionType(), "horizontalTextPosition", null, 0, 1, NodeLabelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNodeLabelType_IconData(), theXMLTypePackage.getString(), "iconData", null, 0, 1, NodeLabelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNodeLabelType_IconTextGap(), theXMLTypePackage.getByte(), "iconTextGap", null, 0, 1, NodeLabelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNodeLabelType_Placement(), this.getPlacementType(), "placement", null, 0, 1, NodeLabelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNodeLabelType_VerticalTextPosition(), this.getVerticalTextPositionType(), "verticalTextPosition", null, 0, 1, NodeLabelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pathTypeEClass, PathType.class, "PathType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPathType_Point(), this.getPointType(), null, "point", null, 0, -1, PathType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPathType_Sx(), theXMLTypePackage.getDouble(), "sx", null, 0, 1, PathType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPathType_Sy(), theXMLTypePackage.getDouble(), "sy", null, 0, 1, PathType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPathType_Tx(), theXMLTypePackage.getDouble(), "tx", null, 0, 1, PathType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPathType_Ty(), theXMLTypePackage.getDouble(), "ty", null, 0, 1, PathType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pointTypeEClass, PointType.class, "PointType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPointType_X(), theXMLTypePackage.getDouble(), "x", null, 1, 1, PointType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPointType_Y(), theXMLTypePackage.getDouble(), "y", null, 1, 1, PointType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(polyLineEdgeTypeEClass, PolyLineEdgeType.class, "PolyLineEdgeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(portNodeTypeEClass, PortNodeType.class, "PortNodeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(resourceBlockTypeEClass, ResourceBlockType.class, "ResourceBlockType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getResourceBlockType_Resource(), this.getResourceType(), null, "resource", null, 0, -1, ResourceBlockType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(resourceTypeEClass, ResourceType.class, "ResourceType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getResourceType_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, ResourceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResourceType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, 1, ResourceType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getResourceType_Format(), theXMLTypePackage.getAnySimpleType(), "format", null, 0, 1, ResourceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResourceType_Id(), theXMLTypePackage.getAnySimpleType(), "id", null, 1, 1, ResourceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResourceType_Type(), theXMLTypePackage.getAnySimpleType(), "type", null, 0, 1, ResourceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scaledIconTypeEClass, ScaledIconType.class, "ScaledIconType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getScaledIconType_ImageIcon(), this.getImageIconType(), null, "imageIcon", null, 0, 1, ScaledIconType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScaledIconType_XScale(), theXMLTypePackage.getDouble(), "xScale", null, 1, 1, ScaledIconType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScaledIconType_YScale(), theXMLTypePackage.getDouble(), "yScale", null, 1, 1, ScaledIconType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(shapeNodeTypeEClass, ShapeNodeType.class, "ShapeNodeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getShapeNodeType_Shape(), this.getShapeType(), null, "shape", null, 0, 1, ShapeNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(shapeTypeEClass, ShapeType.class, "ShapeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getShapeType_Type(), this.getShapeTypeType(), "type", null, 1, 1, ShapeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(arrowTypeTypeEEnum, ArrowTypeType.class, "ArrowTypeType");
		addEEnumLiteral(arrowTypeTypeEEnum, ArrowTypeType.NONE);
		addEEnumLiteral(arrowTypeTypeEEnum, ArrowTypeType.PLAIN);
		addEEnumLiteral(arrowTypeTypeEEnum, ArrowTypeType.DIAMOND);

		initEEnum(horizontalTextPositionTypeEEnum, HorizontalTextPositionType.class, "HorizontalTextPositionType");
		addEEnumLiteral(horizontalTextPositionTypeEEnum, HorizontalTextPositionType.LEFT);
		addEEnumLiteral(horizontalTextPositionTypeEEnum, HorizontalTextPositionType.RIGHT);
		addEEnumLiteral(horizontalTextPositionTypeEEnum, HorizontalTextPositionType.CENTER);

		initEEnum(lineTypeTypeEEnum, LineTypeType.class, "LineTypeType");
		addEEnumLiteral(lineTypeTypeEEnum, LineTypeType.LINE);
		addEEnumLiteral(lineTypeTypeEEnum, LineTypeType.DASHED);
		addEEnumLiteral(lineTypeTypeEEnum, LineTypeType.DOTTED);
		addEEnumLiteral(lineTypeTypeEEnum, LineTypeType.DASHED_DOTTED);

		initEEnum(placementTypeEEnum, PlacementType.class, "PlacementType");
		addEEnumLiteral(placementTypeEEnum, PlacementType.TOP);
		addEEnumLiteral(placementTypeEEnum, PlacementType.BOTTOM);
		addEEnumLiteral(placementTypeEEnum, PlacementType.CENTER);
		addEEnumLiteral(placementTypeEEnum, PlacementType.LEFT);
		addEEnumLiteral(placementTypeEEnum, PlacementType.RIGHT);

		initEEnum(shapeTypeTypeEEnum, ShapeTypeType.class, "ShapeTypeType");
		addEEnumLiteral(shapeTypeTypeEEnum, ShapeTypeType.RECTANGLE);
		addEEnumLiteral(shapeTypeTypeEEnum, ShapeTypeType.ROUNDRECTANGLE);
		addEEnumLiteral(shapeTypeTypeEEnum, ShapeTypeType.CUTRECTANGLE);
		addEEnumLiteral(shapeTypeTypeEEnum, ShapeTypeType.ELLIPSE);
		addEEnumLiteral(shapeTypeTypeEEnum, ShapeTypeType.TRIANGLE);

		initEEnum(verticalTextPositionTypeEEnum, VerticalTextPositionType.class, "VerticalTextPositionType");
		addEEnumLiteral(verticalTextPositionTypeEEnum, VerticalTextPositionType.TOP);
		addEEnumLiteral(verticalTextPositionTypeEEnum, VerticalTextPositionType.BOTTOM);
		addEEnumLiteral(verticalTextPositionTypeEEnum, VerticalTextPositionType.CENTER);

		// Initialize data types
		initEDataType(arrowTypeTypeObjectEDataType, ArrowTypeType.class, "ArrowTypeTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(colorTypeEDataType, String.class, "ColorType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(horizontalTextPositionTypeObjectEDataType, HorizontalTextPositionType.class, "HorizontalTextPositionTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(lineTypeTypeObjectEDataType, LineTypeType.class, "LineTypeTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(placementTypeObjectEDataType, PlacementType.class, "PlacementTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(shapeTypeTypeObjectEDataType, ShapeTypeType.class, "ShapeTypeTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(verticalTextPositionTypeObjectEDataType, VerticalTextPositionType.class, "VerticalTextPositionTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";	
		addAnnotation
		  (arrowsTypeEClass, 
		   source, 
		   new String[] {
			 "name", "Arrows_._type",
			 "kind", "empty"
		   });	
		addAnnotation
		  (getArrowsType_Source(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "source"
		   });	
		addAnnotation
		  (getArrowsType_Target(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "target"
		   });	
		addAnnotation
		  (arrowTypeTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "arrowType.type"
		   });	
		addAnnotation
		  (arrowTypeTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "arrowType.type:Object",
			 "baseType", "arrowType.type"
		   });	
		addAnnotation
		  (baseEdgeTypeEClass, 
		   source, 
		   new String[] {
			 "name", "BaseEdge.type",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getBaseEdgeType_Path(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Path",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getBaseEdgeType_LineStyle(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "LineStyle",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getBaseEdgeType_Arrows(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Arrows",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getBaseEdgeType_EdgeLabel(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "EdgeLabel",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (baseNodeTypeEClass, 
		   source, 
		   new String[] {
			 "name", "BaseNode.type",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getBaseNodeType_Geometry(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Geometry",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getBaseNodeType_Fill(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Fill",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getBaseNodeType_BorderStyle(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "BorderStyle",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getBaseNodeType_NodeLabel(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "NodeLabel",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (colorTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "color.type",
			 "baseType", "http://www.eclipse.org/emf/2003/XMLType#string",
			 "pattern", "#(([A-F]|[0-9]){2}){3,4}"
		   });	
		addAnnotation
		  (documentRootEClass, 
		   source, 
		   new String[] {
			 "name", "",
			 "kind", "mixed"
		   });	
		addAnnotation
		  (getDocumentRoot_Mixed(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "name", ":mixed"
		   });	
		addAnnotation
		  (getDocumentRoot_XMLNSPrefixMap(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "xmlns:prefix"
		   });	
		addAnnotation
		  (getDocumentRoot_XSISchemaLocation(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "xsi:schemaLocation"
		   });	
		addAnnotation
		  (getDocumentRoot_GroupNode(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "GroupNode",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_PolyLineEdge(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "PolyLineEdge",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_PortNode(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "PortNode",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_Resources(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Resources",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_ScaledIcon(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ScaledIcon",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_ShapeNode(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ShapeNode",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (edgeLabelTypeEClass, 
		   source, 
		   new String[] {
			 "name", "EdgeLabel.type",
			 "kind", "mixed"
		   });	
		addAnnotation
		  (fillTypeEClass, 
		   source, 
		   new String[] {
			 "name", "Fill_._type",
			 "kind", "empty"
		   });	
		addAnnotation
		  (getFillType_Color(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "color"
		   });	
		addAnnotation
		  (getFillType_Color2(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "color2"
		   });	
		addAnnotation
		  (geometryTypeEClass, 
		   source, 
		   new String[] {
			 "name", "Geometry.type",
			 "kind", "empty"
		   });	
		addAnnotation
		  (getGeometryType_Height(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "height"
		   });	
		addAnnotation
		  (getGeometryType_Width(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "width"
		   });	
		addAnnotation
		  (getGeometryType_X(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "x"
		   });	
		addAnnotation
		  (getGeometryType_Y(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "y"
		   });	
		addAnnotation
		  (groupNodeTypeEClass, 
		   source, 
		   new String[] {
			 "name", "GroupNode.type",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (horizontalTextPositionTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "horizontalTextPosition.type"
		   });	
		addAnnotation
		  (horizontalTextPositionTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "horizontalTextPosition.type:Object",
			 "baseType", "horizontalTextPosition.type"
		   });	
		addAnnotation
		  (imageIconTypeEClass, 
		   source, 
		   new String[] {
			 "name", "ImageIcon.type",
			 "kind", "empty"
		   });	
		addAnnotation
		  (getImageIconType_Image(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "image"
		   });	
		addAnnotation
		  (labelTypeEClass, 
		   source, 
		   new String[] {
			 "name", "Label.type",
			 "kind", "mixed"
		   });	
		addAnnotation
		  (getLabelType_Mixed(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "name", ":mixed"
		   });	
		addAnnotation
		  (getLabelType_FontFamily(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "fontFamily"
		   });	
		addAnnotation
		  (getLabelType_FontSize(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "fontSize"
		   });	
		addAnnotation
		  (getLabelType_Height(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "height"
		   });	
		addAnnotation
		  (getLabelType_TextColor(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "textColor"
		   });	
		addAnnotation
		  (getLabelType_Width(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "width"
		   });	
		addAnnotation
		  (getLabelType_Visible(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "visible"
		   });	
		addAnnotation
		  (getLabelType_X(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "x"
		   });	
		addAnnotation
		  (getLabelType_Y(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "y"
		   });	
		addAnnotation
		  (lineStyleTypeEClass, 
		   source, 
		   new String[] {
			 "name", "LineStyle.type",
			 "kind", "empty"
		   });	
		addAnnotation
		  (getLineStyleType_Color(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "color"
		   });	
		addAnnotation
		  (getLineStyleType_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "type"
		   });	
		addAnnotation
		  (getLineStyleType_Width(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "width"
		   });	
		addAnnotation
		  (lineTypeTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "lineType.type"
		   });	
		addAnnotation
		  (lineTypeTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "lineType.type:Object",
			 "baseType", "lineType.type"
		   });	
		addAnnotation
		  (nodeLabelTypeEClass, 
		   source, 
		   new String[] {
			 "name", "NodeLabel.type",
			 "kind", "mixed"
		   });	
		addAnnotation
		  (getNodeLabelType_HorizontalTextPosition(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "horizontalTextPosition"
		   });	
		addAnnotation
		  (getNodeLabelType_IconData(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "iconData"
		   });	
		addAnnotation
		  (getNodeLabelType_IconTextGap(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "iconTextGap"
		   });	
		addAnnotation
		  (getNodeLabelType_Placement(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "placement"
		   });	
		addAnnotation
		  (getNodeLabelType_VerticalTextPosition(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "verticalTextPosition"
		   });	
		addAnnotation
		  (pathTypeEClass, 
		   source, 
		   new String[] {
			 "name", "Path.type",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getPathType_Point(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Point",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getPathType_Sx(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "sx"
		   });	
		addAnnotation
		  (getPathType_Sy(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "sy"
		   });	
		addAnnotation
		  (getPathType_Tx(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "tx"
		   });	
		addAnnotation
		  (getPathType_Ty(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "ty"
		   });	
		addAnnotation
		  (placementTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "placement.type"
		   });	
		addAnnotation
		  (placementTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "placement.type:Object",
			 "baseType", "placement.type"
		   });	
		addAnnotation
		  (pointTypeEClass, 
		   source, 
		   new String[] {
			 "name", "Point_._type",
			 "kind", "empty"
		   });	
		addAnnotation
		  (getPointType_X(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "x"
		   });	
		addAnnotation
		  (getPointType_Y(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "y"
		   });	
		addAnnotation
		  (polyLineEdgeTypeEClass, 
		   source, 
		   new String[] {
			 "name", "PolyLineEdge.type",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (portNodeTypeEClass, 
		   source, 
		   new String[] {
			 "name", "PortNode.type",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (resourceBlockTypeEClass, 
		   source, 
		   new String[] {
			 "name", "ResourceBlock.type",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getResourceBlockType_Resource(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Resource",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (resourceTypeEClass, 
		   source, 
		   new String[] {
			 "name", "Resource.type",
			 "kind", "mixed"
		   });	
		addAnnotation
		  (getResourceType_Mixed(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "name", ":mixed"
		   });	
		addAnnotation
		  (getResourceType_Any(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "wildcards", "##any",
			 "name", ":1",
			 "processing", "strict"
		   });	
		addAnnotation
		  (getResourceType_Format(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "format"
		   });	
		addAnnotation
		  (getResourceType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });	
		addAnnotation
		  (getResourceType_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "type"
		   });	
		addAnnotation
		  (scaledIconTypeEClass, 
		   source, 
		   new String[] {
			 "name", "ScaledIcon.type",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getScaledIconType_ImageIcon(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ImageIcon",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getScaledIconType_XScale(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "xScale"
		   });	
		addAnnotation
		  (getScaledIconType_YScale(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "yScale"
		   });	
		addAnnotation
		  (shapeNodeTypeEClass, 
		   source, 
		   new String[] {
			 "name", "ShapeNode.type",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getShapeNodeType_Shape(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Shape",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (shapeTypeEClass, 
		   source, 
		   new String[] {
			 "name", "Shape_._type",
			 "kind", "empty"
		   });	
		addAnnotation
		  (getShapeType_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "type"
		   });	
		addAnnotation
		  (shapeTypeTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "shapeType.type"
		   });	
		addAnnotation
		  (shapeTypeTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "shapeType.type:Object",
			 "baseType", "shapeType.type"
		   });	
		addAnnotation
		  (verticalTextPositionTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "verticalTextPosition.type"
		   });	
		addAnnotation
		  (verticalTextPositionTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "verticalTextPosition.type:Object",
			 "baseType", "verticalTextPosition.type"
		   });
	}

} //SgraphmlPackageImpl
