/**
 * <copyright>
 *  
 * Copyright (c) 2014 Continental AG and others.
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 
 * which accompanies this distribution, and is
 * available at http://www.eclipse.org/org/documents/epl-v10.php
 *  
 * Contributors:
 * 		Continental AG, Matthias Nick - Initial API and implementation
 * </copyright>
 * 
 */
package eaadapter.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import eaadapter.EAAttribute;
import eaadapter.EAAttributeTag;
import eaadapter.EAConnector;
import eaadapter.EAConnectorConstraint;
import eaadapter.EAConnectorEnd;
import eaadapter.EAConnectorTag;
import eaadapter.EAConstraint;
import eaadapter.EAElement;
import eaadapter.EAMethod;
import eaadapter.EAMethodTag;
import eaadapter.EAPackage;
import eaadapter.EAParameter;
import eaadapter.EARepository;
import eaadapter.EARoleTag;
import eaadapter.EATaggedValue;
import eaadapter.EaadapterFactory;
import eaadapter.EaadapterPackage;
import eaadapter.abstracthierachy.AbstracthierachyPackage;
import eaadapter.abstracthierachy.impl.AbstracthierachyPackageImpl;
import eaadapter.datatypes.DatatypesPackage;
import eaadapter.datatypes.impl.DatatypesPackageImpl;
import eaadapter.util.EaadapterValidator;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class EaadapterPackageImpl extends EPackageImpl implements EaadapterPackage {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final String copyright = "<copyright>\r\nCopyright (c) Continental AG and others.\r\nAll rights reserved. This program and the accompanying materials are made navailable under the terms of the Eclipse Public License \r\nwhich accompanies this distribution, and is navailable at http://www.eclipse.org/org/documents/epl-v10.php\r\n\r\nContributors:\r\n\tContinental AG, 2012 Matthias Nick - Initial API and implementation\r\n</copyright>\r\n";

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass eaAttributeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass eaAttributeTagEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass eaConnectorEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass eaConnectorTagEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass eaElementEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass eaMethodEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass eaMethodTagEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass eaPackageEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass eaParameterEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass eaRepositoryEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass eaTaggedValueEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass eaConnectorEndEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass eaConnectorConstraintEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass eaRoleTagEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass eaConstraintEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
	 * EPackage.Registry} by the package package URI value.
	 * <p>
	 * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also
	 * performs initialization of the package, or returns the registered package, if one already exists. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see eaadapter.EaadapterPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private EaadapterPackageImpl() {
		super(eNS_URI, EaadapterFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * <p>
	 * This method is used to initialize {@link EaadapterPackage#eINSTANCE} when that field is accessed. Clients should
	 * not invoke it directly. Instead, they should simply access that field to obtain the package. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static EaadapterPackage init() {
		if (isInited) {
			return (EaadapterPackage) EPackage.Registry.INSTANCE.getEPackage(EaadapterPackage.eNS_URI);
		}

		// Obtain or create and register package
		EaadapterPackageImpl theEaadapterPackage = (EaadapterPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof EaadapterPackageImpl ? EPackage.Registry.INSTANCE
				.get(eNS_URI) : new EaadapterPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		DatatypesPackageImpl theDatatypesPackage = (DatatypesPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(DatatypesPackage.eNS_URI) instanceof DatatypesPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(DatatypesPackage.eNS_URI) : DatatypesPackage.eINSTANCE);
		AbstracthierachyPackageImpl theAbstracthierachyPackage = (AbstracthierachyPackageImpl) (EPackage.Registry.INSTANCE
				.getEPackage(AbstracthierachyPackage.eNS_URI) instanceof AbstracthierachyPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(AbstracthierachyPackage.eNS_URI) : AbstracthierachyPackage.eINSTANCE);

		// Create package meta-data objects
		theEaadapterPackage.createPackageContents();
		theDatatypesPackage.createPackageContents();
		theAbstracthierachyPackage.createPackageContents();

		// Initialize created meta-data
		theEaadapterPackage.initializePackageContents();
		theDatatypesPackage.initializePackageContents();
		theAbstracthierachyPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put(theEaadapterPackage, new EValidator.Descriptor() {
			@Override
			public EValidator getEValidator() {
				return EaadapterValidator.INSTANCE;
			}
		});

		// Mark meta-data to indicate it can't be changed
		theEaadapterPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(EaadapterPackage.eNS_URI, theEaadapterPackage);
		return theEaadapterPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getEAAttribute() {
		return eaAttributeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAAttribute_Containment() {
		return (EAttribute) eaAttributeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAAttribute_Collection() {
		return (EAttribute) eaAttributeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAAttribute_Ordered() {
		return (EAttribute) eaAttributeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAAttribute_AllowDuplicates() {
		return (EAttribute) eaAttributeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAAttribute_Derived() {
		return (EAttribute) eaAttributeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAAttribute_Container() {
		return (EAttribute) eaAttributeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAAttribute_Scale() {
		return (EAttribute) eaAttributeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAAttribute_Precision() {
		return (EAttribute) eaAttributeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAAttribute_Length() {
		return (EAttribute) eaAttributeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAAttribute_LowerBound() {
		return (EAttribute) eaAttributeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAAttribute_UpperBound() {
		return (EAttribute) eaAttributeEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAAttribute_Default() {
		return (EAttribute) eaAttributeEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getEAAttribute_Element() {
		return (EReference) eaAttributeEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAAttribute_EaLink() {
		return (EAttribute) eaAttributeEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getEAAttribute_TaggedValues() {
		return (EReference) eaAttributeEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAAttribute_Visibility() {
		return (EAttribute) eaAttributeEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getEAAttributeTag() {
		return eaAttributeTagEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAAttributeTag_EaLink() {
		return (EAttribute) eaAttributeTagEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getEAConnector() {
		return eaConnectorEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConnector_ConnectorID() {
		return (EAttribute) eaConnectorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConnector_Direction() {
		return (EAttribute) eaConnectorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConnector_Subtype() {
		return (EAttribute) eaConnectorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConnector_IsLeaf() {
		return (EAttribute) eaConnectorEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConnector_IsRoot() {
		return (EAttribute) eaConnectorEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConnector_IsSpec() {
		return (EAttribute) eaConnectorEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConnector_VirtualInheritance() {
		return (EAttribute) eaConnectorEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConnector_TransitionEvent() {
		return (EAttribute) eaConnectorEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConnector_TransitionAction() {
		return (EAttribute) eaConnectorEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConnector_TransitionGuard() {
		return (EAttribute) eaConnectorEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConnector_RouteStyle() {
		return (EAttribute) eaConnectorEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConnector_SequenceNo() {
		return (EAttribute) eaConnectorEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConnector_EaLink() {
		return (EAttribute) eaConnectorEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getEAConnector_TaggedValues() {
		return (EReference) eaConnectorEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getEAConnector_Client() {
		return (EReference) eaConnectorEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getEAConnector_Supplier() {
		return (EReference) eaConnectorEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getEAConnector_Constraints() {
		return (EReference) eaConnectorEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getEAConnector_ClientEnd() {
		return (EReference) eaConnectorEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConnector_ClientID() {
		return (EAttribute) eaConnectorEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConnector_SupplierID() {
		return (EAttribute) eaConnectorEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConnector_EventFlags() {
		return (EAttribute) eaConnectorEClass.getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConnector_StyleEx() {
		return (EAttribute) eaConnectorEClass.getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getEAConnector_SupplierEnd() {
		return (EReference) eaConnectorEClass.getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConnector_Derived() {
		return (EAttribute) eaConnectorEClass.getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getEAConnectorTag() {
		return eaConnectorTagEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConnectorTag_EaLink() {
		return (EAttribute) eaConnectorTagEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getEAElement() {
		return eaElementEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAElement_ClassifierName() {
		return (EAttribute) eaElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAElement_ClassifierType() {
		return (EAttribute) eaElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAElement_Complexity() {
		return (EAttribute) eaElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAElement_Difficulty() {
		return (EAttribute) eaElementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAElement_ExtensionPoints() {
		return (EAttribute) eaElementEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAElement_Genlinks() {
		return (EAttribute) eaElementEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAElement_Genfile() {
		return (EAttribute) eaElementEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAElement_Gentype() {
		return (EAttribute) eaElementEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAElement_Multiplicity() {
		return (EAttribute) eaElementEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAElement_Phase() {
		return (EAttribute) eaElementEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAElement_Priority() {
		return (EAttribute) eaElementEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAElement_PropertyType() {
		return (EAttribute) eaElementEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAElement_Status() {
		return (EAttribute) eaElementEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAElement_Subtype() {
		return (EAttribute) eaElementEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAElement_Tablespace() {
		return (EAttribute) eaElementEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAElement_Tag() {
		return (EAttribute) eaElementEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getEAElement_Element() {
		return (EReference) eaElementEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getEAElement_Package() {
		return (EReference) eaElementEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getEAElement_Attributes() {
		return (EReference) eaElementEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getEAElement_Connectors() {
		return (EReference) eaElementEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getEAElement_Elements() {
		return (EReference) eaElementEClass.getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getEAElement_Methods() {
		return (EReference) eaElementEClass.getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getEAElement_TaggedValues() {
		return (EReference) eaElementEClass.getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAElement_EaLink() {
		return (EAttribute) eaElementEClass.getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAElement_ParentID() {
		return (EAttribute) eaElementEClass.getEStructuralFeatures().get(24);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAElement_PackageID() {
		return (EAttribute) eaElementEClass.getEStructuralFeatures().get(25);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getEAElement_AllConnectors() {
		return (EReference) eaElementEClass.getEStructuralFeatures().get(26);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAElement_Visibility() {
		return (EAttribute) eaElementEClass.getEStructuralFeatures().get(27);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAElement_IsAbstract() {
		return (EAttribute) eaElementEClass.getEStructuralFeatures().get(28);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAElement_IsActive() {
		return (EAttribute) eaElementEClass.getEStructuralFeatures().get(29);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getEAElement_Constraints() {
		return (EReference) eaElementEClass.getEStructuralFeatures().get(30);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAElement_MetaType() {
		return (EAttribute) eaElementEClass.getEStructuralFeatures().get(31);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getEAMethod() {
		return eaMethodEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAMethod_ReturnType() {
		return (EAttribute) eaMethodEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAMethod_ReturnIsArray() {
		return (EAttribute) eaMethodEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAMethod_Code() {
		return (EAttribute) eaMethodEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAMethod_Throws() {
		return (EAttribute) eaMethodEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAMethod_IsPure() {
		return (EAttribute) eaMethodEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAMethod_IsRoot() {
		return (EAttribute) eaMethodEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAMethod_IsLeaf() {
		return (EAttribute) eaMethodEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAMethod_IsQuery() {
		return (EAttribute) eaMethodEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAMethod_IsSynchronized() {
		return (EAttribute) eaMethodEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAMethod_IsAbstract() {
		return (EAttribute) eaMethodEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAMethod_Concurrency() {
		return (EAttribute) eaMethodEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getEAMethod_Element() {
		return (EReference) eaMethodEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAMethod_EaLink() {
		return (EAttribute) eaMethodEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getEAMethod_Parameters() {
		return (EReference) eaMethodEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getEAMethod_TaggedValues() {
		return (EReference) eaMethodEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getEAMethodTag() {
		return eaMethodTagEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAMethodTag_EaLink() {
		return (EAttribute) eaMethodTagEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getEAPackage() {
		return eaPackageEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getEAPackage_Elements() {
		return (EReference) eaPackageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getEAPackage_Packages() {
		return (EReference) eaPackageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getEAPackage_SuperPackage() {
		return (EReference) eaPackageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAPackage_EaLink() {
		return (EAttribute) eaPackageEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAPackage_Stereotype() {
		return (EAttribute) eaPackageEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getEAParameter() {
		return eaParameterEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAParameter_Default() {
		return (EAttribute) eaParameterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAParameter_Position() {
		return (EAttribute) eaParameterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAParameter_IsConst() {
		return (EAttribute) eaParameterEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAParameter_Kind() {
		return (EAttribute) eaParameterEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAParameter_OperationID() {
		return (EAttribute) eaParameterEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getEAParameter_Method() {
		return (EReference) eaParameterEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAParameter_EaLink() {
		return (EAttribute) eaParameterEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getEARepository() {
		return eaRepositoryEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEARepository_EaLink() {
		return (EAttribute) eaRepositoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEARepository_File() {
		return (EAttribute) eaRepositoryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getEARepository_Models() {
		return (EReference) eaRepositoryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getEATaggedValue() {
		return eaTaggedValueEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEATaggedValue_EaLink() {
		return (EAttribute) eaTaggedValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getEAConnectorEnd() {
		return eaConnectorEndEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getEAConnectorEnd_TaggedValues() {
		return (EReference) eaConnectorEndEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConnectorEnd_End() {
		return (EAttribute) eaConnectorEndEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConnectorEnd_Cardinality() {
		return (EAttribute) eaConnectorEndEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConnectorEnd_Visibility() {
		return (EAttribute) eaConnectorEndEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConnectorEnd_Role() {
		return (EAttribute) eaConnectorEndEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConnectorEnd_RoleType() {
		return (EAttribute) eaConnectorEndEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConnectorEnd_RoleNote() {
		return (EAttribute) eaConnectorEndEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConnectorEnd_Containment() {
		return (EAttribute) eaConnectorEndEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConnectorEnd_Aggregation() {
		return (EAttribute) eaConnectorEndEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConnectorEnd_Ordering() {
		return (EAttribute) eaConnectorEndEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConnectorEnd_Qualifier() {
		return (EAttribute) eaConnectorEndEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConnectorEnd_Constraint() {
		return (EAttribute) eaConnectorEndEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConnectorEnd_IsNavigable() {
		return (EAttribute) eaConnectorEndEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConnectorEnd_IsChangable() {
		return (EAttribute) eaConnectorEndEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConnectorEnd_EaLink() {
		return (EAttribute) eaConnectorEndEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConnectorEnd_Derived() {
		return (EAttribute) eaConnectorEndEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getEAConnectorConstraint() {
		return eaConnectorConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConnectorConstraint_ConnectorID() {
		return (EAttribute) eaConnectorConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConnectorConstraint_Type() {
		return (EAttribute) eaConnectorConstraintEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getEAConnectorConstraint_Connector() {
		return (EReference) eaConnectorConstraintEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConnectorConstraint_EaLink() {
		return (EAttribute) eaConnectorConstraintEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getEARoleTag() {
		return eaRoleTagEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEARoleTag_BaseClass() {
		return (EAttribute) eaRoleTagEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEARoleTag_ElementGUID() {
		return (EAttribute) eaRoleTagEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEARoleTag_PropertyGUID() {
		return (EAttribute) eaRoleTagEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEARoleTag_Tag() {
		return (EAttribute) eaRoleTagEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getEARoleTag_ConnectorEnd() {
		return (EReference) eaRoleTagEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEARoleTag_EaLink() {
		return (EAttribute) eaRoleTagEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEARoleTag_Value() {
		return (EAttribute) eaRoleTagEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getEAConstraint() {
		return eaConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConstraint_Status() {
		return (EAttribute) eaConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConstraint_Type() {
		return (EAttribute) eaConstraintEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEAConstraint_Weight() {
		return (EAttribute) eaConstraintEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EaadapterFactory getEaadapterFactory() {
		return (EaadapterFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package. This method is guarded to have no affect on any invocation but
	 * its first. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) {
			return;
		}
		isCreated = true;

		// Create classes and their features
		eaAttributeEClass = createEClass(EA_ATTRIBUTE);
		createEAttribute(eaAttributeEClass, EA_ATTRIBUTE__CONTAINMENT);
		createEAttribute(eaAttributeEClass, EA_ATTRIBUTE__COLLECTION);
		createEAttribute(eaAttributeEClass, EA_ATTRIBUTE__ORDERED);
		createEAttribute(eaAttributeEClass, EA_ATTRIBUTE__ALLOW_DUPLICATES);
		createEAttribute(eaAttributeEClass, EA_ATTRIBUTE__DERIVED);
		createEAttribute(eaAttributeEClass, EA_ATTRIBUTE__CONTAINER);
		createEAttribute(eaAttributeEClass, EA_ATTRIBUTE__SCALE);
		createEAttribute(eaAttributeEClass, EA_ATTRIBUTE__PRECISION);
		createEAttribute(eaAttributeEClass, EA_ATTRIBUTE__LENGTH);
		createEAttribute(eaAttributeEClass, EA_ATTRIBUTE__LOWER_BOUND);
		createEAttribute(eaAttributeEClass, EA_ATTRIBUTE__UPPER_BOUND);
		createEAttribute(eaAttributeEClass, EA_ATTRIBUTE__DEFAULT);
		createEReference(eaAttributeEClass, EA_ATTRIBUTE__ELEMENT);
		createEAttribute(eaAttributeEClass, EA_ATTRIBUTE__EA_LINK);
		createEReference(eaAttributeEClass, EA_ATTRIBUTE__TAGGED_VALUES);
		createEAttribute(eaAttributeEClass, EA_ATTRIBUTE__VISIBILITY);

		eaAttributeTagEClass = createEClass(EA_ATTRIBUTE_TAG);
		createEAttribute(eaAttributeTagEClass, EA_ATTRIBUTE_TAG__EA_LINK);

		eaConnectorEClass = createEClass(EA_CONNECTOR);
		createEAttribute(eaConnectorEClass, EA_CONNECTOR__CONNECTOR_ID);
		createEAttribute(eaConnectorEClass, EA_CONNECTOR__DIRECTION);
		createEAttribute(eaConnectorEClass, EA_CONNECTOR__SUBTYPE);
		createEAttribute(eaConnectorEClass, EA_CONNECTOR__IS_LEAF);
		createEAttribute(eaConnectorEClass, EA_CONNECTOR__IS_ROOT);
		createEAttribute(eaConnectorEClass, EA_CONNECTOR__IS_SPEC);
		createEAttribute(eaConnectorEClass, EA_CONNECTOR__VIRTUAL_INHERITANCE);
		createEAttribute(eaConnectorEClass, EA_CONNECTOR__TRANSITION_EVENT);
		createEAttribute(eaConnectorEClass, EA_CONNECTOR__TRANSITION_ACTION);
		createEAttribute(eaConnectorEClass, EA_CONNECTOR__TRANSITION_GUARD);
		createEAttribute(eaConnectorEClass, EA_CONNECTOR__ROUTE_STYLE);
		createEAttribute(eaConnectorEClass, EA_CONNECTOR__SEQUENCE_NO);
		createEAttribute(eaConnectorEClass, EA_CONNECTOR__EA_LINK);
		createEReference(eaConnectorEClass, EA_CONNECTOR__TAGGED_VALUES);
		createEReference(eaConnectorEClass, EA_CONNECTOR__CLIENT);
		createEReference(eaConnectorEClass, EA_CONNECTOR__SUPPLIER);
		createEReference(eaConnectorEClass, EA_CONNECTOR__CONSTRAINTS);
		createEReference(eaConnectorEClass, EA_CONNECTOR__CLIENT_END);
		createEAttribute(eaConnectorEClass, EA_CONNECTOR__CLIENT_ID);
		createEAttribute(eaConnectorEClass, EA_CONNECTOR__SUPPLIER_ID);
		createEAttribute(eaConnectorEClass, EA_CONNECTOR__EVENT_FLAGS);
		createEAttribute(eaConnectorEClass, EA_CONNECTOR__STYLE_EX);
		createEReference(eaConnectorEClass, EA_CONNECTOR__SUPPLIER_END);
		createEAttribute(eaConnectorEClass, EA_CONNECTOR__DERIVED);

		eaConnectorTagEClass = createEClass(EA_CONNECTOR_TAG);
		createEAttribute(eaConnectorTagEClass, EA_CONNECTOR_TAG__EA_LINK);

		eaElementEClass = createEClass(EA_ELEMENT);
		createEAttribute(eaElementEClass, EA_ELEMENT__CLASSIFIER_NAME);
		createEAttribute(eaElementEClass, EA_ELEMENT__CLASSIFIER_TYPE);
		createEAttribute(eaElementEClass, EA_ELEMENT__COMPLEXITY);
		createEAttribute(eaElementEClass, EA_ELEMENT__DIFFICULTY);
		createEAttribute(eaElementEClass, EA_ELEMENT__EXTENSION_POINTS);
		createEAttribute(eaElementEClass, EA_ELEMENT__GENLINKS);
		createEAttribute(eaElementEClass, EA_ELEMENT__GENFILE);
		createEAttribute(eaElementEClass, EA_ELEMENT__GENTYPE);
		createEAttribute(eaElementEClass, EA_ELEMENT__MULTIPLICITY);
		createEAttribute(eaElementEClass, EA_ELEMENT__PHASE);
		createEAttribute(eaElementEClass, EA_ELEMENT__PRIORITY);
		createEAttribute(eaElementEClass, EA_ELEMENT__PROPERTY_TYPE);
		createEAttribute(eaElementEClass, EA_ELEMENT__STATUS);
		createEAttribute(eaElementEClass, EA_ELEMENT__SUBTYPE);
		createEAttribute(eaElementEClass, EA_ELEMENT__TABLESPACE);
		createEAttribute(eaElementEClass, EA_ELEMENT__TAG);
		createEReference(eaElementEClass, EA_ELEMENT__ELEMENT);
		createEReference(eaElementEClass, EA_ELEMENT__PACKAGE);
		createEReference(eaElementEClass, EA_ELEMENT__ATTRIBUTES);
		createEReference(eaElementEClass, EA_ELEMENT__CONNECTORS);
		createEReference(eaElementEClass, EA_ELEMENT__ELEMENTS);
		createEReference(eaElementEClass, EA_ELEMENT__METHODS);
		createEReference(eaElementEClass, EA_ELEMENT__TAGGED_VALUES);
		createEAttribute(eaElementEClass, EA_ELEMENT__EA_LINK);
		createEAttribute(eaElementEClass, EA_ELEMENT__PARENT_ID);
		createEAttribute(eaElementEClass, EA_ELEMENT__PACKAGE_ID);
		createEReference(eaElementEClass, EA_ELEMENT__ALL_CONNECTORS);
		createEAttribute(eaElementEClass, EA_ELEMENT__VISIBILITY);
		createEAttribute(eaElementEClass, EA_ELEMENT__IS_ABSTRACT);
		createEAttribute(eaElementEClass, EA_ELEMENT__IS_ACTIVE);
		createEReference(eaElementEClass, EA_ELEMENT__CONSTRAINTS);
		createEAttribute(eaElementEClass, EA_ELEMENT__META_TYPE);

		eaMethodEClass = createEClass(EA_METHOD);
		createEAttribute(eaMethodEClass, EA_METHOD__RETURN_TYPE);
		createEAttribute(eaMethodEClass, EA_METHOD__RETURN_IS_ARRAY);
		createEAttribute(eaMethodEClass, EA_METHOD__CODE);
		createEAttribute(eaMethodEClass, EA_METHOD__THROWS);
		createEAttribute(eaMethodEClass, EA_METHOD__IS_PURE);
		createEAttribute(eaMethodEClass, EA_METHOD__IS_ROOT);
		createEAttribute(eaMethodEClass, EA_METHOD__IS_LEAF);
		createEAttribute(eaMethodEClass, EA_METHOD__IS_QUERY);
		createEAttribute(eaMethodEClass, EA_METHOD__IS_SYNCHRONIZED);
		createEAttribute(eaMethodEClass, EA_METHOD__IS_ABSTRACT);
		createEAttribute(eaMethodEClass, EA_METHOD__CONCURRENCY);
		createEReference(eaMethodEClass, EA_METHOD__ELEMENT);
		createEAttribute(eaMethodEClass, EA_METHOD__EA_LINK);
		createEReference(eaMethodEClass, EA_METHOD__PARAMETERS);
		createEReference(eaMethodEClass, EA_METHOD__TAGGED_VALUES);

		eaMethodTagEClass = createEClass(EA_METHOD_TAG);
		createEAttribute(eaMethodTagEClass, EA_METHOD_TAG__EA_LINK);

		eaPackageEClass = createEClass(EA_PACKAGE);
		createEReference(eaPackageEClass, EA_PACKAGE__ELEMENTS);
		createEReference(eaPackageEClass, EA_PACKAGE__PACKAGES);
		createEReference(eaPackageEClass, EA_PACKAGE__SUPER_PACKAGE);
		createEAttribute(eaPackageEClass, EA_PACKAGE__EA_LINK);
		createEAttribute(eaPackageEClass, EA_PACKAGE__STEREOTYPE);

		eaParameterEClass = createEClass(EA_PARAMETER);
		createEAttribute(eaParameterEClass, EA_PARAMETER__DEFAULT);
		createEAttribute(eaParameterEClass, EA_PARAMETER__POSITION);
		createEAttribute(eaParameterEClass, EA_PARAMETER__IS_CONST);
		createEAttribute(eaParameterEClass, EA_PARAMETER__KIND);
		createEAttribute(eaParameterEClass, EA_PARAMETER__OPERATION_ID);
		createEReference(eaParameterEClass, EA_PARAMETER__METHOD);
		createEAttribute(eaParameterEClass, EA_PARAMETER__EA_LINK);

		eaRepositoryEClass = createEClass(EA_REPOSITORY);
		createEAttribute(eaRepositoryEClass, EA_REPOSITORY__EA_LINK);
		createEAttribute(eaRepositoryEClass, EA_REPOSITORY__FILE);
		createEReference(eaRepositoryEClass, EA_REPOSITORY__MODELS);

		eaTaggedValueEClass = createEClass(EA_TAGGED_VALUE);
		createEAttribute(eaTaggedValueEClass, EA_TAGGED_VALUE__EA_LINK);

		eaConnectorEndEClass = createEClass(EA_CONNECTOR_END);
		createEReference(eaConnectorEndEClass, EA_CONNECTOR_END__TAGGED_VALUES);
		createEAttribute(eaConnectorEndEClass, EA_CONNECTOR_END__END);
		createEAttribute(eaConnectorEndEClass, EA_CONNECTOR_END__CARDINALITY);
		createEAttribute(eaConnectorEndEClass, EA_CONNECTOR_END__VISIBILITY);
		createEAttribute(eaConnectorEndEClass, EA_CONNECTOR_END__ROLE);
		createEAttribute(eaConnectorEndEClass, EA_CONNECTOR_END__ROLE_TYPE);
		createEAttribute(eaConnectorEndEClass, EA_CONNECTOR_END__ROLE_NOTE);
		createEAttribute(eaConnectorEndEClass, EA_CONNECTOR_END__CONTAINMENT);
		createEAttribute(eaConnectorEndEClass, EA_CONNECTOR_END__AGGREGATION);
		createEAttribute(eaConnectorEndEClass, EA_CONNECTOR_END__ORDERING);
		createEAttribute(eaConnectorEndEClass, EA_CONNECTOR_END__QUALIFIER);
		createEAttribute(eaConnectorEndEClass, EA_CONNECTOR_END__CONSTRAINT);
		createEAttribute(eaConnectorEndEClass, EA_CONNECTOR_END__IS_NAVIGABLE);
		createEAttribute(eaConnectorEndEClass, EA_CONNECTOR_END__IS_CHANGABLE);
		createEAttribute(eaConnectorEndEClass, EA_CONNECTOR_END__EA_LINK);
		createEAttribute(eaConnectorEndEClass, EA_CONNECTOR_END__DERIVED);

		eaConnectorConstraintEClass = createEClass(EA_CONNECTOR_CONSTRAINT);
		createEAttribute(eaConnectorConstraintEClass, EA_CONNECTOR_CONSTRAINT__CONNECTOR_ID);
		createEAttribute(eaConnectorConstraintEClass, EA_CONNECTOR_CONSTRAINT__TYPE);
		createEReference(eaConnectorConstraintEClass, EA_CONNECTOR_CONSTRAINT__CONNECTOR);
		createEAttribute(eaConnectorConstraintEClass, EA_CONNECTOR_CONSTRAINT__EA_LINK);

		eaRoleTagEClass = createEClass(EA_ROLE_TAG);
		createEAttribute(eaRoleTagEClass, EA_ROLE_TAG__BASE_CLASS);
		createEAttribute(eaRoleTagEClass, EA_ROLE_TAG__ELEMENT_GUID);
		createEAttribute(eaRoleTagEClass, EA_ROLE_TAG__PROPERTY_GUID);
		createEAttribute(eaRoleTagEClass, EA_ROLE_TAG__TAG);
		createEReference(eaRoleTagEClass, EA_ROLE_TAG__CONNECTOR_END);
		createEAttribute(eaRoleTagEClass, EA_ROLE_TAG__EA_LINK);
		createEAttribute(eaRoleTagEClass, EA_ROLE_TAG__VALUE);

		eaConstraintEClass = createEClass(EA_CONSTRAINT);
		createEAttribute(eaConstraintEClass, EA_CONSTRAINT__STATUS);
		createEAttribute(eaConstraintEClass, EA_CONSTRAINT__TYPE);
		createEAttribute(eaConstraintEClass, EA_CONSTRAINT__WEIGHT);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model. This method is guarded to have no affect on any
	 * invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) {
			return;
		}
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		DatatypesPackage theDatatypesPackage = (DatatypesPackage) EPackage.Registry.INSTANCE.getEPackage(DatatypesPackage.eNS_URI);
		AbstracthierachyPackage theAbstracthierachyPackage = (AbstracthierachyPackage) EPackage.Registry.INSTANCE
				.getEPackage(AbstracthierachyPackage.eNS_URI);

		// Add subpackages
		getESubpackages().add(theDatatypesPackage);
		getESubpackages().add(theAbstracthierachyPackage);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		eaAttributeEClass.getESuperTypes().add(theAbstracthierachyPackage.getEAModifiableElement());
		eaAttributeEClass.getESuperTypes().add(theAbstracthierachyPackage.getEAClassifierIDLong());
		eaAttributeTagEClass.getESuperTypes().add(theAbstracthierachyPackage.getEATaggedElement());
		eaConnectorEClass.getESuperTypes().add(theAbstracthierachyPackage.getEATypedElement());
		eaConnectorTagEClass.getESuperTypes().add(theAbstracthierachyPackage.getEATaggedElement());
		eaElementEClass.getESuperTypes().add(theAbstracthierachyPackage.getEAClassifierIDLong());
		eaElementEClass.getESuperTypes().add(theAbstracthierachyPackage.getEAOwnedElement());
		eaMethodEClass.getESuperTypes().add(theAbstracthierachyPackage.getEAModifiableElement());
		eaMethodEClass.getESuperTypes().add(theAbstracthierachyPackage.getEAClassifierIDLong());
		eaMethodTagEClass.getESuperTypes().add(theAbstracthierachyPackage.getEATaggedElement());
		eaPackageEClass.getESuperTypes().add(theAbstracthierachyPackage.getEAAbstractPackage());
		eaParameterEClass.getESuperTypes().add(theAbstracthierachyPackage.getEAClassifierIDLong());
		eaTaggedValueEClass.getESuperTypes().add(theAbstracthierachyPackage.getEATaggedElement());
		eaConnectorEndEClass.getESuperTypes().add(theAbstracthierachyPackage.getEANamedElement());
		eaConnectorConstraintEClass.getESuperTypes().add(theAbstracthierachyPackage.getEANamedElement());
		eaRoleTagEClass.getESuperTypes().add(theAbstracthierachyPackage.getEANamedElement());
		eaConstraintEClass.getESuperTypes().add(theAbstracthierachyPackage.getEANamedElement());

		// Initialize classes and features; add operations and parameters
		initEClass(eaAttributeEClass, EAAttribute.class, "EAAttribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEAAttribute_Containment(), ecorePackage.getEString(), "containment", null, 0, 1, EAAttribute.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAAttribute_Collection(), ecorePackage.getEBooleanObject(), "collection", null, 0, 1, EAAttribute.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAAttribute_Ordered(), ecorePackage.getEBooleanObject(), "ordered", null, 0, 1, EAAttribute.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAAttribute_AllowDuplicates(), ecorePackage.getEBooleanObject(), "allowDuplicates", null, 0, 1, EAAttribute.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAAttribute_Derived(), ecorePackage.getEBooleanObject(), "derived", null, 0, 1, EAAttribute.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAAttribute_Container(), ecorePackage.getEString(), "container", null, 0, 1, EAAttribute.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAAttribute_Scale(), ecorePackage.getEString(), "scale", null, 0, 1, EAAttribute.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAAttribute_Precision(), ecorePackage.getEString(), "precision", null, 0, 1, EAAttribute.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAAttribute_Length(), ecorePackage.getEString(), "length", null, 0, 1, EAAttribute.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAAttribute_LowerBound(), ecorePackage.getEString(), "lowerBound", null, 0, 1, EAAttribute.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAAttribute_UpperBound(), ecorePackage.getEString(), "upperBound", null, 0, 1, EAAttribute.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAAttribute_Default(), ecorePackage.getEString(), "default", null, 0, 1, EAAttribute.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEAAttribute_Element(), getEAElement(), getEAElement_Attributes(), "element", null, 0, 1, EAAttribute.class, IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAAttribute_EaLink(), theDatatypesPackage.getT_Attribute(), "eaLink", null, 0, 1, EAAttribute.class, IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEAAttribute_TaggedValues(), getEAAttributeTag(), null, "taggedValues", null, 0, -1, EAAttribute.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAAttribute_Visibility(), ecorePackage.getEString(), "visibility", null, 0, 1, EAAttribute.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = addEOperation(eaAttributeEClass, ecorePackage.getEBoolean(), "validate", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostic", 0, 1, IS_UNIQUE, IS_ORDERED);
		EGenericType g1 = createEGenericType(ecorePackage.getEMap());
		EGenericType g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(eaAttributeTagEClass, EAAttributeTag.class, "EAAttributeTag", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEAAttributeTag_EaLink(), theDatatypesPackage.getT_AttributeTag(), "eaLink", null, 0, 1, EAAttributeTag.class, IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eaConnectorEClass, EAConnector.class, "EAConnector", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEAConnector_ConnectorID(), ecorePackage.getEIntegerObject(), "connectorID", null, 0, 1, EAConnector.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAConnector_Direction(), ecorePackage.getEString(), "direction", null, 0, 1, EAConnector.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAConnector_Subtype(), ecorePackage.getEString(), "subtype", null, 0, 1, EAConnector.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAConnector_IsLeaf(), ecorePackage.getEBooleanObject(), "isLeaf", null, 0, 1, EAConnector.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAConnector_IsRoot(), ecorePackage.getEBooleanObject(), "isRoot", null, 0, 1, EAConnector.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAConnector_IsSpec(), ecorePackage.getEBooleanObject(), "isSpec", null, 0, 1, EAConnector.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAConnector_VirtualInheritance(), ecorePackage.getEString(), "virtualInheritance", null, 0, 1, EAConnector.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAConnector_TransitionEvent(), ecorePackage.getEString(), "transitionEvent", null, 0, 1, EAConnector.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAConnector_TransitionAction(), ecorePackage.getEString(), "transitionAction", null, 0, 1, EAConnector.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAConnector_TransitionGuard(), ecorePackage.getEString(), "transitionGuard", null, 0, 1, EAConnector.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAConnector_RouteStyle(), ecorePackage.getEIntegerObject(), "routeStyle", null, 0, 1, EAConnector.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAConnector_SequenceNo(), ecorePackage.getEIntegerObject(), "sequenceNo", null, 0, 1, EAConnector.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAConnector_EaLink(), theDatatypesPackage.getT_Connector(), "eaLink", null, 0, 1, EAConnector.class, IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEAConnector_TaggedValues(), getEAConnectorTag(), null, "taggedValues", null, 0, -1, EAConnector.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEAConnector_Client(), getEAElement(), getEAElement_Connectors(), "client", null, 1, 1, EAConnector.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEAConnector_Supplier(), getEAElement(), null, "supplier", null, 1, 1, EAConnector.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEAConnector_Constraints(), getEAConnectorConstraint(), getEAConnectorConstraint_Connector(), "constraints", null, 0, -1,
				EAConnector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getEAConnector_ClientEnd(), getEAConnectorEnd(), null, "clientEnd", null, 0, 1, EAConnector.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAConnector_ClientID(), ecorePackage.getELong(), "clientID", null, 0, 1, EAConnector.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAConnector_SupplierID(), ecorePackage.getELong(), "supplierID", null, 0, 1, EAConnector.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAConnector_EventFlags(), ecorePackage.getEString(), "eventFlags", null, 0, 1, EAConnector.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAConnector_StyleEx(), ecorePackage.getEString(), "styleEx", null, 0, 1, EAConnector.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEAConnector_SupplierEnd(), getEAConnectorEnd(), null, "supplierEnd", null, 0, 1, EAConnector.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAConnector_Derived(), ecorePackage.getEBoolean(), "derived", null, 0, 1, EAConnector.class, IS_TRANSIENT, IS_VOLATILE,
				!IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(eaConnectorTagEClass, EAConnectorTag.class, "EAConnectorTag", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEAConnectorTag_EaLink(), theDatatypesPackage.getT_ConntectorTag(), "eaLink", null, 0, 1, EAConnectorTag.class,
				IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eaElementEClass, EAElement.class, "EAElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEAElement_ClassifierName(), ecorePackage.getEString(), "classifierName", null, 0, 1, EAElement.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAElement_ClassifierType(), ecorePackage.getEString(), "classifierType", null, 0, 1, EAElement.class, !IS_TRANSIENT,
				!IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAElement_Complexity(), ecorePackage.getEString(), "complexity", null, 0, 1, EAElement.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAElement_Difficulty(), ecorePackage.getEString(), "difficulty", null, 0, 1, EAElement.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAElement_ExtensionPoints(), ecorePackage.getEString(), "extensionPoints", null, 0, 1, EAElement.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAElement_Genlinks(), ecorePackage.getEString(), "genlinks", null, 0, 1, EAElement.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAElement_Genfile(), ecorePackage.getEString(), "genfile", null, 0, 1, EAElement.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAElement_Gentype(), ecorePackage.getEString(), "gentype", null, 0, 1, EAElement.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAElement_Multiplicity(), ecorePackage.getEString(), "multiplicity", null, 0, 1, EAElement.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAElement_Phase(), ecorePackage.getEString(), "phase", null, 0, 1, EAElement.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAElement_Priority(), ecorePackage.getEString(), "priority", null, 0, 1, EAElement.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAElement_PropertyType(), ecorePackage.getEIntegerObject(), "propertyType", null, 0, 1, EAElement.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAElement_Status(), ecorePackage.getEString(), "status", null, 0, 1, EAElement.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAElement_Subtype(), ecorePackage.getEIntegerObject(), "subtype", null, 0, 1, EAElement.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAElement_Tablespace(), ecorePackage.getEString(), "tablespace", null, 0, 1, EAElement.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAElement_Tag(), ecorePackage.getEString(), "tag", null, 0, 1, EAElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEAElement_Element(), getEAElement(), getEAElement_Elements(), "element", null, 0, 1, EAElement.class, IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEAElement_Package(), getEAPackage(), getEAPackage_Elements(), "package", null, 0, 1, EAElement.class, IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEAElement_Attributes(), getEAAttribute(), getEAAttribute_Element(), "attributes", null, 0, -1, EAElement.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEAElement_Connectors(), getEAConnector(), getEAConnector_Client(), "connectors", null, 0, -1, EAElement.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEAElement_Elements(), getEAElement(), getEAElement_Element(), "elements", null, 0, -1, EAElement.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEAElement_Methods(), getEAMethod(), getEAMethod_Element(), "methods", null, 0, -1, EAElement.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEAElement_TaggedValues(), getEATaggedValue(), null, "taggedValues", null, 0, -1, EAElement.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAElement_EaLink(), theDatatypesPackage.getT_Element(), "eaLink", null, 0, 1, EAElement.class, IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAElement_ParentID(), ecorePackage.getEIntegerObject(), "parentID", null, 0, 1, EAElement.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAElement_PackageID(), ecorePackage.getEIntegerObject(), "packageID", null, 0, 1, EAElement.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEAElement_AllConnectors(), getEAConnector(), null, "allConnectors", null, 0, -1, EAElement.class, IS_TRANSIENT,
				IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAElement_Visibility(), ecorePackage.getEString(), "visibility", null, 0, 1, EAElement.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAElement_IsAbstract(), ecorePackage.getEBoolean(), "isAbstract", null, 0, 1, EAElement.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAElement_IsActive(), ecorePackage.getEBoolean(), "isActive", null, 0, 1, EAElement.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEAElement_Constraints(), getEAConstraint(), null, "constraints", null, 0, -1, EAElement.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAElement_MetaType(), ecorePackage.getEString(), "metaType", null, 0, 1, EAElement.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(eaElementEClass, ecorePackage.getEBoolean(), "validate", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostic", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(eaMethodEClass, EAMethod.class, "EAMethod", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEAMethod_ReturnType(), ecorePackage.getEString(), "returnType", null, 0, 1, EAMethod.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAMethod_ReturnIsArray(), ecorePackage.getEBooleanObject(), "returnIsArray", null, 0, 1, EAMethod.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAMethod_Code(), ecorePackage.getEString(), "code", null, 0, 1, EAMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAMethod_Throws(), ecorePackage.getEString(), "throws", null, 0, 1, EAMethod.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAMethod_IsPure(), ecorePackage.getEBooleanObject(), "isPure", null, 0, 1, EAMethod.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAMethod_IsRoot(), ecorePackage.getEBooleanObject(), "isRoot", null, 0, 1, EAMethod.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAMethod_IsLeaf(), ecorePackage.getEBooleanObject(), "isLeaf", null, 0, 1, EAMethod.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAMethod_IsQuery(), ecorePackage.getEBooleanObject(), "isQuery", null, 0, 1, EAMethod.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAMethod_IsSynchronized(), ecorePackage.getEBooleanObject(), "isSynchronized", null, 0, 1, EAMethod.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAMethod_IsAbstract(), ecorePackage.getEBooleanObject(), "isAbstract", null, 0, 1, EAMethod.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAMethod_Concurrency(), ecorePackage.getEString(), "concurrency", null, 0, 1, EAMethod.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEAMethod_Element(), getEAElement(), getEAElement_Methods(), "element", null, 0, 1, EAMethod.class, IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAMethod_EaLink(), theDatatypesPackage.getT_Method(), "eaLink", null, 0, 1, EAMethod.class, IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEAMethod_Parameters(), getEAParameter(), getEAParameter_Method(), "parameters", null, 0, -1, EAMethod.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEAMethod_TaggedValues(), getEAMethodTag(), null, "taggedValues", null, 0, -1, EAMethod.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eaMethodTagEClass, EAMethodTag.class, "EAMethodTag", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEAMethodTag_EaLink(), theDatatypesPackage.getT_MethodTag(), "eaLink", null, 0, 1, EAMethodTag.class, IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eaPackageEClass, EAPackage.class, "EAPackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEAPackage_Elements(), getEAElement(), getEAElement_Package(), "elements", null, 0, -1, EAPackage.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEAPackage_Packages(), getEAPackage(), getEAPackage_SuperPackage(), "packages", null, 0, -1, EAPackage.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEAPackage_SuperPackage(), getEAPackage(), getEAPackage_Packages(), "superPackage", null, 0, 1, EAPackage.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAPackage_EaLink(), theDatatypesPackage.getT_Package(), "eaLink", null, 0, 1, EAPackage.class, IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAPackage_Stereotype(), ecorePackage.getEString(), "stereotype", null, 0, 1, EAPackage.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(eaPackageEClass, ecorePackage.getEBoolean(), "validate", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostic", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(eaParameterEClass, EAParameter.class, "EAParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEAParameter_Default(), ecorePackage.getEString(), "default", null, 0, 1, EAParameter.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAParameter_Position(), ecorePackage.getEIntegerObject(), "position", null, 0, 1, EAParameter.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAParameter_IsConst(), ecorePackage.getEBooleanObject(), "isConst", null, 0, 1, EAParameter.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAParameter_Kind(), ecorePackage.getEString(), "kind", null, 0, 1, EAParameter.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAParameter_OperationID(), ecorePackage.getEIntegerObject(), "operationID", null, 0, 1, EAParameter.class, !IS_TRANSIENT,
				!IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEAParameter_Method(), getEAMethod(), getEAMethod_Parameters(), "method", null, 0, 1, EAParameter.class, IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAParameter_EaLink(), theDatatypesPackage.getT_Parameter(), "eaLink", null, 0, 1, EAParameter.class, IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eaRepositoryEClass, EARepository.class, "EARepository", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEARepository_EaLink(), theDatatypesPackage.getT_Repository(), "eaLink", null, 0, 1, EARepository.class, IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEARepository_File(), theDatatypesPackage.getFile(), "file", null, 0, 1, EARepository.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEARepository_Models(), getEAPackage(), null, "models", null, 0, -1, EARepository.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(eaRepositoryEClass, null, "load", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, theDatatypesPackage.getException());

		op = addEOperation(eaRepositoryEClass, theAbstracthierachyPackage.getEANamedElement(), "getEANamedElementByGUID", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "guid", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(eaRepositoryEClass, getEAElement(), "getEAElementByGUID", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "guid", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(eaRepositoryEClass, theAbstracthierachyPackage.getEANamedElement(), "getEANamedElementByID", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getELong(), "id", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(eaRepositoryEClass, getEAElement(), "getEAElementByID", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getELong(), "id", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(eaTaggedValueEClass, EATaggedValue.class, "EATaggedValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEATaggedValue_EaLink(), theDatatypesPackage.getT_TaggedValue(), "eaLink", null, 0, 1, EATaggedValue.class, IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eaConnectorEndEClass, EAConnectorEnd.class, "EAConnectorEnd", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEAConnectorEnd_TaggedValues(), getEARoleTag(), getEARoleTag_ConnectorEnd(), "TaggedValues", null, 0, -1,
				EAConnectorEnd.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAConnectorEnd_End(), ecorePackage.getEString(), "end", null, 0, 1, EAConnectorEnd.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAConnectorEnd_Cardinality(), ecorePackage.getEString(), "cardinality", null, 0, 1, EAConnectorEnd.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAConnectorEnd_Visibility(), ecorePackage.getEString(), "visibility", null, 0, 1, EAConnectorEnd.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAConnectorEnd_Role(), ecorePackage.getEString(), "role", null, 0, 1, EAConnectorEnd.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAConnectorEnd_RoleType(), ecorePackage.getEString(), "roleType", null, 0, 1, EAConnectorEnd.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAConnectorEnd_RoleNote(), ecorePackage.getEString(), "roleNote", null, 0, 1, EAConnectorEnd.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAConnectorEnd_Containment(), ecorePackage.getEString(), "containment", null, 0, 1, EAConnectorEnd.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAConnectorEnd_Aggregation(), theDatatypesPackage.getT_ConnectorAggregation(), "aggregation", null, 0, 1,
				EAConnectorEnd.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAConnectorEnd_Ordering(), ecorePackage.getELong(), "ordering", null, 0, 1, EAConnectorEnd.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAConnectorEnd_Qualifier(), ecorePackage.getEString(), "qualifier", null, 0, 1, EAConnectorEnd.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAConnectorEnd_Constraint(), ecorePackage.getEString(), "constraint", null, 0, 1, EAConnectorEnd.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAConnectorEnd_IsNavigable(), ecorePackage.getEBoolean(), "isNavigable", null, 0, 1, EAConnectorEnd.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAConnectorEnd_IsChangable(), ecorePackage.getEString(), "isChangable", null, 0, 1, EAConnectorEnd.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAConnectorEnd_EaLink(), theDatatypesPackage.getT_ConnectorEnd(), "eaLink", null, 0, 1, EAConnectorEnd.class, IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAConnectorEnd_Derived(), ecorePackage.getEBoolean(), "derived", null, 0, 1, EAConnectorEnd.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eaConnectorConstraintEClass, EAConnectorConstraint.class, "EAConnectorConstraint", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEAConnectorConstraint_ConnectorID(), ecorePackage.getELong(), "connectorID", null, 0, 1, EAConnectorConstraint.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAConnectorConstraint_Type(), ecorePackage.getEString(), "type", null, 0, 1, EAConnectorConstraint.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEAConnectorConstraint_Connector(), getEAConnector(), getEAConnector_Constraints(), "connector", null, 0, 1,
				EAConnectorConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAConnectorConstraint_EaLink(), theDatatypesPackage.getT_ConnectorConstraint(), "eaLink", null, 0, 1,
				EAConnectorConstraint.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eaRoleTagEClass, EARoleTag.class, "EARoleTag", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEARoleTag_BaseClass(), ecorePackage.getEString(), "baseClass", null, 0, 1, EARoleTag.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEARoleTag_ElementGUID(), ecorePackage.getEString(), "elementGUID", null, 0, 1, EARoleTag.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEARoleTag_PropertyGUID(), ecorePackage.getEString(), "propertyGUID", null, 0, 1, EARoleTag.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEARoleTag_Tag(), ecorePackage.getEString(), "tag", null, 0, 1, EARoleTag.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEARoleTag_ConnectorEnd(), getEAConnectorEnd(), getEAConnectorEnd_TaggedValues(), "connectorEnd", null, 0, 1,
				EARoleTag.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getEARoleTag_EaLink(), theDatatypesPackage.getT_RoleTag(), "eaLink", null, 0, 1, EARoleTag.class, IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEARoleTag_Value(), ecorePackage.getEString(), "value", null, 0, 1, EARoleTag.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eaConstraintEClass, EAConstraint.class, "EAConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEAConstraint_Status(), ecorePackage.getEString(), "status", null, 0, 1, EAConstraint.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAConstraint_Type(), ecorePackage.getEString(), "type", null, 0, 1, EAConstraint.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEAConstraint_Weight(), ecorePackage.getEString(), "weight", null, 0, 1, EAConstraint.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// null
		createNullAnnotations();
	}

	/**
	 * Initializes the annotations for <b>null</b>. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void createNullAnnotations() {
		String source = null;
		addAnnotation(getEAConnector_SupplierID(), source, new String[] {});
	}

} // EaadapterPackageImpl
