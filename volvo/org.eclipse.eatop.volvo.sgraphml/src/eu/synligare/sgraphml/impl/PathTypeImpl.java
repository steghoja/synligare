/**
 */
package eu.synligare.sgraphml.impl;

import eu.synligare.sgraphml.PathType;
import eu.synligare.sgraphml.PointType;
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
 * An implementation of the model object '<em><b>Path Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.synligare.sgraphml.impl.PathTypeImpl#getPoint <em>Point</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.impl.PathTypeImpl#getSx <em>Sx</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.impl.PathTypeImpl#getSy <em>Sy</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.impl.PathTypeImpl#getTx <em>Tx</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.impl.PathTypeImpl#getTy <em>Ty</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PathTypeImpl extends MinimalEObjectImpl.Container implements PathType {
	/**
	 * The cached value of the '{@link #getPoint() <em>Point</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPoint()
	 * @generated
	 * @ordered
	 */
	protected EList<PointType> point;

	/**
	 * The default value of the '{@link #getSx() <em>Sx</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSx()
	 * @generated
	 * @ordered
	 */
	protected static final double SX_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getSx() <em>Sx</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSx()
	 * @generated
	 * @ordered
	 */
	protected double sx = SX_EDEFAULT;

	/**
	 * This is true if the Sx attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean sxESet;

	/**
	 * The default value of the '{@link #getSy() <em>Sy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSy()
	 * @generated
	 * @ordered
	 */
	protected static final double SY_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getSy() <em>Sy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSy()
	 * @generated
	 * @ordered
	 */
	protected double sy = SY_EDEFAULT;

	/**
	 * This is true if the Sy attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean syESet;

	/**
	 * The default value of the '{@link #getTx() <em>Tx</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTx()
	 * @generated
	 * @ordered
	 */
	protected static final double TX_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getTx() <em>Tx</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTx()
	 * @generated
	 * @ordered
	 */
	protected double tx = TX_EDEFAULT;

	/**
	 * This is true if the Tx attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean txESet;

	/**
	 * The default value of the '{@link #getTy() <em>Ty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTy()
	 * @generated
	 * @ordered
	 */
	protected static final double TY_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getTy() <em>Ty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTy()
	 * @generated
	 * @ordered
	 */
	protected double ty = TY_EDEFAULT;

	/**
	 * This is true if the Ty attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean tyESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PathTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SgraphmlPackage.Literals.PATH_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PointType> getPoint() {
		if (point == null) {
			point = new EObjectContainmentEList<PointType>(PointType.class, this, SgraphmlPackage.PATH_TYPE__POINT);
		}
		return point;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getSx() {
		return sx;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSx(double newSx) {
		double oldSx = sx;
		sx = newSx;
		boolean oldSxESet = sxESet;
		sxESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SgraphmlPackage.PATH_TYPE__SX, oldSx, sx, !oldSxESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetSx() {
		double oldSx = sx;
		boolean oldSxESet = sxESet;
		sx = SX_EDEFAULT;
		sxESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, SgraphmlPackage.PATH_TYPE__SX, oldSx, SX_EDEFAULT, oldSxESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetSx() {
		return sxESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getSy() {
		return sy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSy(double newSy) {
		double oldSy = sy;
		sy = newSy;
		boolean oldSyESet = syESet;
		syESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SgraphmlPackage.PATH_TYPE__SY, oldSy, sy, !oldSyESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetSy() {
		double oldSy = sy;
		boolean oldSyESet = syESet;
		sy = SY_EDEFAULT;
		syESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, SgraphmlPackage.PATH_TYPE__SY, oldSy, SY_EDEFAULT, oldSyESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetSy() {
		return syESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getTx() {
		return tx;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTx(double newTx) {
		double oldTx = tx;
		tx = newTx;
		boolean oldTxESet = txESet;
		txESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SgraphmlPackage.PATH_TYPE__TX, oldTx, tx, !oldTxESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetTx() {
		double oldTx = tx;
		boolean oldTxESet = txESet;
		tx = TX_EDEFAULT;
		txESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, SgraphmlPackage.PATH_TYPE__TX, oldTx, TX_EDEFAULT, oldTxESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetTx() {
		return txESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getTy() {
		return ty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTy(double newTy) {
		double oldTy = ty;
		ty = newTy;
		boolean oldTyESet = tyESet;
		tyESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SgraphmlPackage.PATH_TYPE__TY, oldTy, ty, !oldTyESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetTy() {
		double oldTy = ty;
		boolean oldTyESet = tyESet;
		ty = TY_EDEFAULT;
		tyESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, SgraphmlPackage.PATH_TYPE__TY, oldTy, TY_EDEFAULT, oldTyESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetTy() {
		return tyESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SgraphmlPackage.PATH_TYPE__POINT:
				return ((InternalEList<?>)getPoint()).basicRemove(otherEnd, msgs);
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
			case SgraphmlPackage.PATH_TYPE__POINT:
				return getPoint();
			case SgraphmlPackage.PATH_TYPE__SX:
				return getSx();
			case SgraphmlPackage.PATH_TYPE__SY:
				return getSy();
			case SgraphmlPackage.PATH_TYPE__TX:
				return getTx();
			case SgraphmlPackage.PATH_TYPE__TY:
				return getTy();
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
			case SgraphmlPackage.PATH_TYPE__POINT:
				getPoint().clear();
				getPoint().addAll((Collection<? extends PointType>)newValue);
				return;
			case SgraphmlPackage.PATH_TYPE__SX:
				setSx((Double)newValue);
				return;
			case SgraphmlPackage.PATH_TYPE__SY:
				setSy((Double)newValue);
				return;
			case SgraphmlPackage.PATH_TYPE__TX:
				setTx((Double)newValue);
				return;
			case SgraphmlPackage.PATH_TYPE__TY:
				setTy((Double)newValue);
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
			case SgraphmlPackage.PATH_TYPE__POINT:
				getPoint().clear();
				return;
			case SgraphmlPackage.PATH_TYPE__SX:
				unsetSx();
				return;
			case SgraphmlPackage.PATH_TYPE__SY:
				unsetSy();
				return;
			case SgraphmlPackage.PATH_TYPE__TX:
				unsetTx();
				return;
			case SgraphmlPackage.PATH_TYPE__TY:
				unsetTy();
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
			case SgraphmlPackage.PATH_TYPE__POINT:
				return point != null && !point.isEmpty();
			case SgraphmlPackage.PATH_TYPE__SX:
				return isSetSx();
			case SgraphmlPackage.PATH_TYPE__SY:
				return isSetSy();
			case SgraphmlPackage.PATH_TYPE__TX:
				return isSetTx();
			case SgraphmlPackage.PATH_TYPE__TY:
				return isSetTy();
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
		result.append(" (sx: ");
		if (sxESet) result.append(sx); else result.append("<unset>");
		result.append(", sy: ");
		if (syESet) result.append(sy); else result.append("<unset>");
		result.append(", tx: ");
		if (txESet) result.append(tx); else result.append("<unset>");
		result.append(", ty: ");
		if (tyESet) result.append(ty); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //PathTypeImpl
