/**
 */
package eu.synligare.sgraphml.impl;

import eu.synligare.sgraphml.BaseNodeType;
import eu.synligare.sgraphml.FillType;
import eu.synligare.sgraphml.GeometryType;
import eu.synligare.sgraphml.LineStyleType;
import eu.synligare.sgraphml.NodeLabelType;
import eu.synligare.sgraphml.SgraphmlPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Base Node Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.synligare.sgraphml.impl.BaseNodeTypeImpl#getGeometry <em>Geometry</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.impl.BaseNodeTypeImpl#getFill <em>Fill</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.impl.BaseNodeTypeImpl#getBorderStyle <em>Border Style</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.impl.BaseNodeTypeImpl#getNodeLabel <em>Node Label</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BaseNodeTypeImpl extends MinimalEObjectImpl.Container implements BaseNodeType {
	/**
	 * The cached value of the '{@link #getGeometry() <em>Geometry</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGeometry()
	 * @generated
	 * @ordered
	 */
	protected GeometryType geometry;

	/**
	 * The cached value of the '{@link #getFill() <em>Fill</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFill()
	 * @generated
	 * @ordered
	 */
	protected FillType fill;

	/**
	 * The cached value of the '{@link #getBorderStyle() <em>Border Style</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBorderStyle()
	 * @generated
	 * @ordered
	 */
	protected LineStyleType borderStyle;

	/**
	 * The cached value of the '{@link #getNodeLabel() <em>Node Label</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodeLabel()
	 * @generated
	 * @ordered
	 */
	protected EList<NodeLabelType> nodeLabel;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BaseNodeTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SgraphmlPackage.Literals.BASE_NODE_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeometryType getGeometry() {
		return geometry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGeometry(GeometryType newGeometry, NotificationChain msgs) {
		GeometryType oldGeometry = geometry;
		geometry = newGeometry;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SgraphmlPackage.BASE_NODE_TYPE__GEOMETRY, oldGeometry, newGeometry);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGeometry(GeometryType newGeometry) {
		if (newGeometry != geometry) {
			NotificationChain msgs = null;
			if (geometry != null)
				msgs = ((InternalEObject)geometry).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SgraphmlPackage.BASE_NODE_TYPE__GEOMETRY, null, msgs);
			if (newGeometry != null)
				msgs = ((InternalEObject)newGeometry).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SgraphmlPackage.BASE_NODE_TYPE__GEOMETRY, null, msgs);
			msgs = basicSetGeometry(newGeometry, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SgraphmlPackage.BASE_NODE_TYPE__GEOMETRY, newGeometry, newGeometry));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FillType getFill() {
		return fill;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFill(FillType newFill, NotificationChain msgs) {
		FillType oldFill = fill;
		fill = newFill;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SgraphmlPackage.BASE_NODE_TYPE__FILL, oldFill, newFill);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFill(FillType newFill) {
		if (newFill != fill) {
			NotificationChain msgs = null;
			if (fill != null)
				msgs = ((InternalEObject)fill).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SgraphmlPackage.BASE_NODE_TYPE__FILL, null, msgs);
			if (newFill != null)
				msgs = ((InternalEObject)newFill).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SgraphmlPackage.BASE_NODE_TYPE__FILL, null, msgs);
			msgs = basicSetFill(newFill, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SgraphmlPackage.BASE_NODE_TYPE__FILL, newFill, newFill));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LineStyleType getBorderStyle() {
		return borderStyle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBorderStyle(LineStyleType newBorderStyle, NotificationChain msgs) {
		LineStyleType oldBorderStyle = borderStyle;
		borderStyle = newBorderStyle;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SgraphmlPackage.BASE_NODE_TYPE__BORDER_STYLE, oldBorderStyle, newBorderStyle);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBorderStyle(LineStyleType newBorderStyle) {
		if (newBorderStyle != borderStyle) {
			NotificationChain msgs = null;
			if (borderStyle != null)
				msgs = ((InternalEObject)borderStyle).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SgraphmlPackage.BASE_NODE_TYPE__BORDER_STYLE, null, msgs);
			if (newBorderStyle != null)
				msgs = ((InternalEObject)newBorderStyle).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SgraphmlPackage.BASE_NODE_TYPE__BORDER_STYLE, null, msgs);
			msgs = basicSetBorderStyle(newBorderStyle, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SgraphmlPackage.BASE_NODE_TYPE__BORDER_STYLE, newBorderStyle, newBorderStyle));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<NodeLabelType> getNodeLabel() {
		if (nodeLabel == null) {
			nodeLabel = new EObjectContainmentEList<NodeLabelType>(NodeLabelType.class, this, SgraphmlPackage.BASE_NODE_TYPE__NODE_LABEL);
		}
		return nodeLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SgraphmlPackage.BASE_NODE_TYPE__GEOMETRY:
				return basicSetGeometry(null, msgs);
			case SgraphmlPackage.BASE_NODE_TYPE__FILL:
				return basicSetFill(null, msgs);
			case SgraphmlPackage.BASE_NODE_TYPE__BORDER_STYLE:
				return basicSetBorderStyle(null, msgs);
			case SgraphmlPackage.BASE_NODE_TYPE__NODE_LABEL:
				return ((InternalEList<?>)getNodeLabel()).basicRemove(otherEnd, msgs);
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
			case SgraphmlPackage.BASE_NODE_TYPE__GEOMETRY:
				return getGeometry();
			case SgraphmlPackage.BASE_NODE_TYPE__FILL:
				return getFill();
			case SgraphmlPackage.BASE_NODE_TYPE__BORDER_STYLE:
				return getBorderStyle();
			case SgraphmlPackage.BASE_NODE_TYPE__NODE_LABEL:
				return getNodeLabel();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SgraphmlPackage.BASE_NODE_TYPE__GEOMETRY:
				setGeometry((GeometryType)newValue);
				return;
			case SgraphmlPackage.BASE_NODE_TYPE__FILL:
				setFill((FillType)newValue);
				return;
			case SgraphmlPackage.BASE_NODE_TYPE__BORDER_STYLE:
				setBorderStyle((LineStyleType)newValue);
				return;
			case SgraphmlPackage.BASE_NODE_TYPE__NODE_LABEL:
				getNodeLabel().clear();
				getNodeLabel().addAll((Collection<? extends NodeLabelType>)newValue);
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
			case SgraphmlPackage.BASE_NODE_TYPE__GEOMETRY:
				setGeometry((GeometryType)null);
				return;
			case SgraphmlPackage.BASE_NODE_TYPE__FILL:
				setFill((FillType)null);
				return;
			case SgraphmlPackage.BASE_NODE_TYPE__BORDER_STYLE:
				setBorderStyle((LineStyleType)null);
				return;
			case SgraphmlPackage.BASE_NODE_TYPE__NODE_LABEL:
				getNodeLabel().clear();
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
			case SgraphmlPackage.BASE_NODE_TYPE__GEOMETRY:
				return geometry != null;
			case SgraphmlPackage.BASE_NODE_TYPE__FILL:
				return fill != null;
			case SgraphmlPackage.BASE_NODE_TYPE__BORDER_STYLE:
				return borderStyle != null;
			case SgraphmlPackage.BASE_NODE_TYPE__NODE_LABEL:
				return nodeLabel != null && !nodeLabel.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //BaseNodeTypeImpl
