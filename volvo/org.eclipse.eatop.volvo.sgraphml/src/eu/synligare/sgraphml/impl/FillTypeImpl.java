/**
 */
package eu.synligare.sgraphml.impl;

import eu.synligare.sgraphml.FillType;
import eu.synligare.sgraphml.SgraphmlPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Fill Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.synligare.sgraphml.impl.FillTypeImpl#getColor <em>Color</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.impl.FillTypeImpl#getColor2 <em>Color2</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FillTypeImpl extends MinimalEObjectImpl.Container implements FillType {
	/**
	 * The default value of the '{@link #getColor() <em>Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColor()
	 * @generated
	 * @ordered
	 */
	protected static final String COLOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getColor() <em>Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColor()
	 * @generated
	 * @ordered
	 */
	protected String color = COLOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getColor2() <em>Color2</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColor2()
	 * @generated
	 * @ordered
	 */
	protected static final String COLOR2_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getColor2() <em>Color2</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColor2()
	 * @generated
	 * @ordered
	 */
	protected String color2 = COLOR2_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FillTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SgraphmlPackage.Literals.FILL_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getColor() {
		return color;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setColor(String newColor) {
		String oldColor = color;
		color = newColor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SgraphmlPackage.FILL_TYPE__COLOR, oldColor, color));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getColor2() {
		return color2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setColor2(String newColor2) {
		String oldColor2 = color2;
		color2 = newColor2;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SgraphmlPackage.FILL_TYPE__COLOR2, oldColor2, color2));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SgraphmlPackage.FILL_TYPE__COLOR:
				return getColor();
			case SgraphmlPackage.FILL_TYPE__COLOR2:
				return getColor2();
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
			case SgraphmlPackage.FILL_TYPE__COLOR:
				setColor((String)newValue);
				return;
			case SgraphmlPackage.FILL_TYPE__COLOR2:
				setColor2((String)newValue);
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
			case SgraphmlPackage.FILL_TYPE__COLOR:
				setColor(COLOR_EDEFAULT);
				return;
			case SgraphmlPackage.FILL_TYPE__COLOR2:
				setColor2(COLOR2_EDEFAULT);
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
			case SgraphmlPackage.FILL_TYPE__COLOR:
				return COLOR_EDEFAULT == null ? color != null : !COLOR_EDEFAULT.equals(color);
			case SgraphmlPackage.FILL_TYPE__COLOR2:
				return COLOR2_EDEFAULT == null ? color2 != null : !COLOR2_EDEFAULT.equals(color2);
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
		result.append(" (color: ");
		result.append(color);
		result.append(", color2: ");
		result.append(color2);
		result.append(')');
		return result.toString();
	}

} //FillTypeImpl
