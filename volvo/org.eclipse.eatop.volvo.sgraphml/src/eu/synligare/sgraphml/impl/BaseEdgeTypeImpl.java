/**
 */
package eu.synligare.sgraphml.impl;

import eu.synligare.sgraphml.ArrowsType;
import eu.synligare.sgraphml.BaseEdgeType;
import eu.synligare.sgraphml.EdgeLabelType;
import eu.synligare.sgraphml.LineStyleType;
import eu.synligare.sgraphml.PathType;
import eu.synligare.sgraphml.SgraphmlPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Base Edge Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.synligare.sgraphml.impl.BaseEdgeTypeImpl#getPath <em>Path</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.impl.BaseEdgeTypeImpl#getLineStyle <em>Line Style</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.impl.BaseEdgeTypeImpl#getArrows <em>Arrows</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.impl.BaseEdgeTypeImpl#getEdgeLabel <em>Edge Label</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BaseEdgeTypeImpl extends MinimalEObjectImpl.Container implements BaseEdgeType {
	/**
	 * The cached value of the '{@link #getPath() <em>Path</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected PathType path;

	/**
	 * The cached value of the '{@link #getLineStyle() <em>Line Style</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineStyle()
	 * @generated
	 * @ordered
	 */
	protected LineStyleType lineStyle;

	/**
	 * The cached value of the '{@link #getArrows() <em>Arrows</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArrows()
	 * @generated
	 * @ordered
	 */
	protected ArrowsType arrows;

	/**
	 * The cached value of the '{@link #getEdgeLabel() <em>Edge Label</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEdgeLabel()
	 * @generated
	 * @ordered
	 */
	protected EdgeLabelType edgeLabel;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BaseEdgeTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SgraphmlPackage.Literals.BASE_EDGE_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PathType getPath() {
		return path;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPath(PathType newPath, NotificationChain msgs) {
		PathType oldPath = path;
		path = newPath;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SgraphmlPackage.BASE_EDGE_TYPE__PATH, oldPath, newPath);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPath(PathType newPath) {
		if (newPath != path) {
			NotificationChain msgs = null;
			if (path != null)
				msgs = ((InternalEObject)path).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SgraphmlPackage.BASE_EDGE_TYPE__PATH, null, msgs);
			if (newPath != null)
				msgs = ((InternalEObject)newPath).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SgraphmlPackage.BASE_EDGE_TYPE__PATH, null, msgs);
			msgs = basicSetPath(newPath, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SgraphmlPackage.BASE_EDGE_TYPE__PATH, newPath, newPath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LineStyleType getLineStyle() {
		return lineStyle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLineStyle(LineStyleType newLineStyle, NotificationChain msgs) {
		LineStyleType oldLineStyle = lineStyle;
		lineStyle = newLineStyle;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SgraphmlPackage.BASE_EDGE_TYPE__LINE_STYLE, oldLineStyle, newLineStyle);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLineStyle(LineStyleType newLineStyle) {
		if (newLineStyle != lineStyle) {
			NotificationChain msgs = null;
			if (lineStyle != null)
				msgs = ((InternalEObject)lineStyle).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SgraphmlPackage.BASE_EDGE_TYPE__LINE_STYLE, null, msgs);
			if (newLineStyle != null)
				msgs = ((InternalEObject)newLineStyle).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SgraphmlPackage.BASE_EDGE_TYPE__LINE_STYLE, null, msgs);
			msgs = basicSetLineStyle(newLineStyle, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SgraphmlPackage.BASE_EDGE_TYPE__LINE_STYLE, newLineStyle, newLineStyle));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrowsType getArrows() {
		return arrows;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetArrows(ArrowsType newArrows, NotificationChain msgs) {
		ArrowsType oldArrows = arrows;
		arrows = newArrows;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SgraphmlPackage.BASE_EDGE_TYPE__ARROWS, oldArrows, newArrows);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setArrows(ArrowsType newArrows) {
		if (newArrows != arrows) {
			NotificationChain msgs = null;
			if (arrows != null)
				msgs = ((InternalEObject)arrows).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SgraphmlPackage.BASE_EDGE_TYPE__ARROWS, null, msgs);
			if (newArrows != null)
				msgs = ((InternalEObject)newArrows).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SgraphmlPackage.BASE_EDGE_TYPE__ARROWS, null, msgs);
			msgs = basicSetArrows(newArrows, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SgraphmlPackage.BASE_EDGE_TYPE__ARROWS, newArrows, newArrows));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EdgeLabelType getEdgeLabel() {
		return edgeLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEdgeLabel(EdgeLabelType newEdgeLabel, NotificationChain msgs) {
		EdgeLabelType oldEdgeLabel = edgeLabel;
		edgeLabel = newEdgeLabel;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SgraphmlPackage.BASE_EDGE_TYPE__EDGE_LABEL, oldEdgeLabel, newEdgeLabel);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEdgeLabel(EdgeLabelType newEdgeLabel) {
		if (newEdgeLabel != edgeLabel) {
			NotificationChain msgs = null;
			if (edgeLabel != null)
				msgs = ((InternalEObject)edgeLabel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SgraphmlPackage.BASE_EDGE_TYPE__EDGE_LABEL, null, msgs);
			if (newEdgeLabel != null)
				msgs = ((InternalEObject)newEdgeLabel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SgraphmlPackage.BASE_EDGE_TYPE__EDGE_LABEL, null, msgs);
			msgs = basicSetEdgeLabel(newEdgeLabel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SgraphmlPackage.BASE_EDGE_TYPE__EDGE_LABEL, newEdgeLabel, newEdgeLabel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SgraphmlPackage.BASE_EDGE_TYPE__PATH:
				return basicSetPath(null, msgs);
			case SgraphmlPackage.BASE_EDGE_TYPE__LINE_STYLE:
				return basicSetLineStyle(null, msgs);
			case SgraphmlPackage.BASE_EDGE_TYPE__ARROWS:
				return basicSetArrows(null, msgs);
			case SgraphmlPackage.BASE_EDGE_TYPE__EDGE_LABEL:
				return basicSetEdgeLabel(null, msgs);
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
			case SgraphmlPackage.BASE_EDGE_TYPE__PATH:
				return getPath();
			case SgraphmlPackage.BASE_EDGE_TYPE__LINE_STYLE:
				return getLineStyle();
			case SgraphmlPackage.BASE_EDGE_TYPE__ARROWS:
				return getArrows();
			case SgraphmlPackage.BASE_EDGE_TYPE__EDGE_LABEL:
				return getEdgeLabel();
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
			case SgraphmlPackage.BASE_EDGE_TYPE__PATH:
				setPath((PathType)newValue);
				return;
			case SgraphmlPackage.BASE_EDGE_TYPE__LINE_STYLE:
				setLineStyle((LineStyleType)newValue);
				return;
			case SgraphmlPackage.BASE_EDGE_TYPE__ARROWS:
				setArrows((ArrowsType)newValue);
				return;
			case SgraphmlPackage.BASE_EDGE_TYPE__EDGE_LABEL:
				setEdgeLabel((EdgeLabelType)newValue);
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
			case SgraphmlPackage.BASE_EDGE_TYPE__PATH:
				setPath((PathType)null);
				return;
			case SgraphmlPackage.BASE_EDGE_TYPE__LINE_STYLE:
				setLineStyle((LineStyleType)null);
				return;
			case SgraphmlPackage.BASE_EDGE_TYPE__ARROWS:
				setArrows((ArrowsType)null);
				return;
			case SgraphmlPackage.BASE_EDGE_TYPE__EDGE_LABEL:
				setEdgeLabel((EdgeLabelType)null);
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
			case SgraphmlPackage.BASE_EDGE_TYPE__PATH:
				return path != null;
			case SgraphmlPackage.BASE_EDGE_TYPE__LINE_STYLE:
				return lineStyle != null;
			case SgraphmlPackage.BASE_EDGE_TYPE__ARROWS:
				return arrows != null;
			case SgraphmlPackage.BASE_EDGE_TYPE__EDGE_LABEL:
				return edgeLabel != null;
		}
		return super.eIsSet(featureID);
	}

} //BaseEdgeTypeImpl
