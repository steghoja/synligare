/**
 */
package eu.synligare.sgraphml.impl;

import eu.synligare.sgraphml.DocumentRoot;
import eu.synligare.sgraphml.GroupNodeType;
import eu.synligare.sgraphml.PolyLineEdgeType;
import eu.synligare.sgraphml.PortNodeType;
import eu.synligare.sgraphml.ResourceBlockType;
import eu.synligare.sgraphml.ScaledIconType;
import eu.synligare.sgraphml.SgraphmlPackage;
import eu.synligare.sgraphml.ShapeNodeType;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.synligare.sgraphml.impl.DocumentRootImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.impl.DocumentRootImpl#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.impl.DocumentRootImpl#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.impl.DocumentRootImpl#getGroupNode <em>Group Node</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.impl.DocumentRootImpl#getPolyLineEdge <em>Poly Line Edge</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.impl.DocumentRootImpl#getPortNode <em>Port Node</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.impl.DocumentRootImpl#getResources <em>Resources</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.impl.DocumentRootImpl#getScaledIcon <em>Scaled Icon</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.impl.DocumentRootImpl#getShapeNode <em>Shape Node</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DocumentRootImpl extends MinimalEObjectImpl.Container implements DocumentRoot {
	/**
	 * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMixed()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap mixed;

	/**
	 * The cached value of the '{@link #getXMLNSPrefixMap() <em>XMLNS Prefix Map</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXMLNSPrefixMap()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> xMLNSPrefixMap;

	/**
	 * The cached value of the '{@link #getXSISchemaLocation() <em>XSI Schema Location</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXSISchemaLocation()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> xSISchemaLocation;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DocumentRootImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SgraphmlPackage.Literals.DOCUMENT_ROOT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getMixed() {
		if (mixed == null) {
			mixed = new BasicFeatureMap(this, SgraphmlPackage.DOCUMENT_ROOT__MIXED);
		}
		return mixed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getXMLNSPrefixMap() {
		if (xMLNSPrefixMap == null) {
			xMLNSPrefixMap = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, SgraphmlPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		}
		return xMLNSPrefixMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getXSISchemaLocation() {
		if (xSISchemaLocation == null) {
			xSISchemaLocation = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, SgraphmlPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		}
		return xSISchemaLocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GroupNodeType getGroupNode() {
		return (GroupNodeType)getMixed().get(SgraphmlPackage.Literals.DOCUMENT_ROOT__GROUP_NODE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGroupNode(GroupNodeType newGroupNode, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(SgraphmlPackage.Literals.DOCUMENT_ROOT__GROUP_NODE, newGroupNode, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGroupNode(GroupNodeType newGroupNode) {
		((FeatureMap.Internal)getMixed()).set(SgraphmlPackage.Literals.DOCUMENT_ROOT__GROUP_NODE, newGroupNode);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PolyLineEdgeType getPolyLineEdge() {
		return (PolyLineEdgeType)getMixed().get(SgraphmlPackage.Literals.DOCUMENT_ROOT__POLY_LINE_EDGE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPolyLineEdge(PolyLineEdgeType newPolyLineEdge, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(SgraphmlPackage.Literals.DOCUMENT_ROOT__POLY_LINE_EDGE, newPolyLineEdge, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPolyLineEdge(PolyLineEdgeType newPolyLineEdge) {
		((FeatureMap.Internal)getMixed()).set(SgraphmlPackage.Literals.DOCUMENT_ROOT__POLY_LINE_EDGE, newPolyLineEdge);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortNodeType getPortNode() {
		return (PortNodeType)getMixed().get(SgraphmlPackage.Literals.DOCUMENT_ROOT__PORT_NODE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPortNode(PortNodeType newPortNode, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(SgraphmlPackage.Literals.DOCUMENT_ROOT__PORT_NODE, newPortNode, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPortNode(PortNodeType newPortNode) {
		((FeatureMap.Internal)getMixed()).set(SgraphmlPackage.Literals.DOCUMENT_ROOT__PORT_NODE, newPortNode);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceBlockType getResources() {
		return (ResourceBlockType)getMixed().get(SgraphmlPackage.Literals.DOCUMENT_ROOT__RESOURCES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetResources(ResourceBlockType newResources, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(SgraphmlPackage.Literals.DOCUMENT_ROOT__RESOURCES, newResources, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResources(ResourceBlockType newResources) {
		((FeatureMap.Internal)getMixed()).set(SgraphmlPackage.Literals.DOCUMENT_ROOT__RESOURCES, newResources);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaledIconType getScaledIcon() {
		return (ScaledIconType)getMixed().get(SgraphmlPackage.Literals.DOCUMENT_ROOT__SCALED_ICON, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetScaledIcon(ScaledIconType newScaledIcon, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(SgraphmlPackage.Literals.DOCUMENT_ROOT__SCALED_ICON, newScaledIcon, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScaledIcon(ScaledIconType newScaledIcon) {
		((FeatureMap.Internal)getMixed()).set(SgraphmlPackage.Literals.DOCUMENT_ROOT__SCALED_ICON, newScaledIcon);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ShapeNodeType getShapeNode() {
		return (ShapeNodeType)getMixed().get(SgraphmlPackage.Literals.DOCUMENT_ROOT__SHAPE_NODE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetShapeNode(ShapeNodeType newShapeNode, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(SgraphmlPackage.Literals.DOCUMENT_ROOT__SHAPE_NODE, newShapeNode, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setShapeNode(ShapeNodeType newShapeNode) {
		((FeatureMap.Internal)getMixed()).set(SgraphmlPackage.Literals.DOCUMENT_ROOT__SHAPE_NODE, newShapeNode);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SgraphmlPackage.DOCUMENT_ROOT__MIXED:
				return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
			case SgraphmlPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				return ((InternalEList<?>)getXMLNSPrefixMap()).basicRemove(otherEnd, msgs);
			case SgraphmlPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				return ((InternalEList<?>)getXSISchemaLocation()).basicRemove(otherEnd, msgs);
			case SgraphmlPackage.DOCUMENT_ROOT__GROUP_NODE:
				return basicSetGroupNode(null, msgs);
			case SgraphmlPackage.DOCUMENT_ROOT__POLY_LINE_EDGE:
				return basicSetPolyLineEdge(null, msgs);
			case SgraphmlPackage.DOCUMENT_ROOT__PORT_NODE:
				return basicSetPortNode(null, msgs);
			case SgraphmlPackage.DOCUMENT_ROOT__RESOURCES:
				return basicSetResources(null, msgs);
			case SgraphmlPackage.DOCUMENT_ROOT__SCALED_ICON:
				return basicSetScaledIcon(null, msgs);
			case SgraphmlPackage.DOCUMENT_ROOT__SHAPE_NODE:
				return basicSetShapeNode(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SgraphmlPackage.DOCUMENT_ROOT__MIXED:
				if (coreType) return getMixed();
				return ((FeatureMap.Internal)getMixed()).getWrapper();
			case SgraphmlPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				if (coreType) return getXMLNSPrefixMap();
				else return getXMLNSPrefixMap().map();
			case SgraphmlPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				if (coreType) return getXSISchemaLocation();
				else return getXSISchemaLocation().map();
			case SgraphmlPackage.DOCUMENT_ROOT__GROUP_NODE:
				return getGroupNode();
			case SgraphmlPackage.DOCUMENT_ROOT__POLY_LINE_EDGE:
				return getPolyLineEdge();
			case SgraphmlPackage.DOCUMENT_ROOT__PORT_NODE:
				return getPortNode();
			case SgraphmlPackage.DOCUMENT_ROOT__RESOURCES:
				return getResources();
			case SgraphmlPackage.DOCUMENT_ROOT__SCALED_ICON:
				return getScaledIcon();
			case SgraphmlPackage.DOCUMENT_ROOT__SHAPE_NODE:
				return getShapeNode();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SgraphmlPackage.DOCUMENT_ROOT__MIXED:
				((FeatureMap.Internal)getMixed()).set(newValue);
				return;
			case SgraphmlPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				((EStructuralFeature.Setting)getXMLNSPrefixMap()).set(newValue);
				return;
			case SgraphmlPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				((EStructuralFeature.Setting)getXSISchemaLocation()).set(newValue);
				return;
			case SgraphmlPackage.DOCUMENT_ROOT__GROUP_NODE:
				setGroupNode((GroupNodeType)newValue);
				return;
			case SgraphmlPackage.DOCUMENT_ROOT__POLY_LINE_EDGE:
				setPolyLineEdge((PolyLineEdgeType)newValue);
				return;
			case SgraphmlPackage.DOCUMENT_ROOT__PORT_NODE:
				setPortNode((PortNodeType)newValue);
				return;
			case SgraphmlPackage.DOCUMENT_ROOT__RESOURCES:
				setResources((ResourceBlockType)newValue);
				return;
			case SgraphmlPackage.DOCUMENT_ROOT__SCALED_ICON:
				setScaledIcon((ScaledIconType)newValue);
				return;
			case SgraphmlPackage.DOCUMENT_ROOT__SHAPE_NODE:
				setShapeNode((ShapeNodeType)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case SgraphmlPackage.DOCUMENT_ROOT__MIXED:
				getMixed().clear();
				return;
			case SgraphmlPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				getXMLNSPrefixMap().clear();
				return;
			case SgraphmlPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				getXSISchemaLocation().clear();
				return;
			case SgraphmlPackage.DOCUMENT_ROOT__GROUP_NODE:
				setGroupNode((GroupNodeType)null);
				return;
			case SgraphmlPackage.DOCUMENT_ROOT__POLY_LINE_EDGE:
				setPolyLineEdge((PolyLineEdgeType)null);
				return;
			case SgraphmlPackage.DOCUMENT_ROOT__PORT_NODE:
				setPortNode((PortNodeType)null);
				return;
			case SgraphmlPackage.DOCUMENT_ROOT__RESOURCES:
				setResources((ResourceBlockType)null);
				return;
			case SgraphmlPackage.DOCUMENT_ROOT__SCALED_ICON:
				setScaledIcon((ScaledIconType)null);
				return;
			case SgraphmlPackage.DOCUMENT_ROOT__SHAPE_NODE:
				setShapeNode((ShapeNodeType)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case SgraphmlPackage.DOCUMENT_ROOT__MIXED:
				return mixed != null && !mixed.isEmpty();
			case SgraphmlPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
			case SgraphmlPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
			case SgraphmlPackage.DOCUMENT_ROOT__GROUP_NODE:
				return getGroupNode() != null;
			case SgraphmlPackage.DOCUMENT_ROOT__POLY_LINE_EDGE:
				return getPolyLineEdge() != null;
			case SgraphmlPackage.DOCUMENT_ROOT__PORT_NODE:
				return getPortNode() != null;
			case SgraphmlPackage.DOCUMENT_ROOT__RESOURCES:
				return getResources() != null;
			case SgraphmlPackage.DOCUMENT_ROOT__SCALED_ICON:
				return getScaledIcon() != null;
			case SgraphmlPackage.DOCUMENT_ROOT__SHAPE_NODE:
				return getShapeNode() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (mixed: ");
		result.append(mixed);
		result.append(')');
		return result.toString();
	}

} //DocumentRootImpl
