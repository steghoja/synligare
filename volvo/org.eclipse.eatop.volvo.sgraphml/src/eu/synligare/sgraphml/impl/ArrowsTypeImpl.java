/**
 */
package eu.synligare.sgraphml.impl;

import eu.synligare.sgraphml.ArrowTypeType;
import eu.synligare.sgraphml.ArrowsType;
import eu.synligare.sgraphml.SgraphmlPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Arrows Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.synligare.sgraphml.impl.ArrowsTypeImpl#getSource <em>Source</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.impl.ArrowsTypeImpl#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ArrowsTypeImpl extends MinimalEObjectImpl.Container implements ArrowsType {
	/**
	 * The default value of the '{@link #getSource() <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected static final ArrowTypeType SOURCE_EDEFAULT = ArrowTypeType.NONE;

	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected ArrowTypeType source = SOURCE_EDEFAULT;

	/**
	 * This is true if the Source attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean sourceESet;

	/**
	 * The default value of the '{@link #getTarget() <em>Target</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected static final ArrowTypeType TARGET_EDEFAULT = ArrowTypeType.NONE;

	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected ArrowTypeType target = TARGET_EDEFAULT;

	/**
	 * This is true if the Target attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean targetESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArrowsTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SgraphmlPackage.Literals.ARROWS_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrowTypeType getSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(ArrowTypeType newSource) {
		ArrowTypeType oldSource = source;
		source = newSource == null ? SOURCE_EDEFAULT : newSource;
		boolean oldSourceESet = sourceESet;
		sourceESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SgraphmlPackage.ARROWS_TYPE__SOURCE, oldSource, source, !oldSourceESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetSource() {
		ArrowTypeType oldSource = source;
		boolean oldSourceESet = sourceESet;
		source = SOURCE_EDEFAULT;
		sourceESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, SgraphmlPackage.ARROWS_TYPE__SOURCE, oldSource, SOURCE_EDEFAULT, oldSourceESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetSource() {
		return sourceESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrowTypeType getTarget() {
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTarget(ArrowTypeType newTarget) {
		ArrowTypeType oldTarget = target;
		target = newTarget == null ? TARGET_EDEFAULT : newTarget;
		boolean oldTargetESet = targetESet;
		targetESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SgraphmlPackage.ARROWS_TYPE__TARGET, oldTarget, target, !oldTargetESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetTarget() {
		ArrowTypeType oldTarget = target;
		boolean oldTargetESet = targetESet;
		target = TARGET_EDEFAULT;
		targetESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, SgraphmlPackage.ARROWS_TYPE__TARGET, oldTarget, TARGET_EDEFAULT, oldTargetESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetTarget() {
		return targetESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SgraphmlPackage.ARROWS_TYPE__SOURCE:
				return getSource();
			case SgraphmlPackage.ARROWS_TYPE__TARGET:
				return getTarget();
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
			case SgraphmlPackage.ARROWS_TYPE__SOURCE:
				setSource((ArrowTypeType)newValue);
				return;
			case SgraphmlPackage.ARROWS_TYPE__TARGET:
				setTarget((ArrowTypeType)newValue);
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
			case SgraphmlPackage.ARROWS_TYPE__SOURCE:
				unsetSource();
				return;
			case SgraphmlPackage.ARROWS_TYPE__TARGET:
				unsetTarget();
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
			case SgraphmlPackage.ARROWS_TYPE__SOURCE:
				return isSetSource();
			case SgraphmlPackage.ARROWS_TYPE__TARGET:
				return isSetTarget();
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
		result.append(" (source: ");
		if (sourceESet) result.append(source); else result.append("<unset>");
		result.append(", target: ");
		if (targetESet) result.append(target); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //ArrowsTypeImpl
