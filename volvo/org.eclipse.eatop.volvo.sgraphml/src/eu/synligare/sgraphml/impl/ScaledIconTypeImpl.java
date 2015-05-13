/**
 */
package eu.synligare.sgraphml.impl;

import eu.synligare.sgraphml.ImageIconType;
import eu.synligare.sgraphml.ScaledIconType;
import eu.synligare.sgraphml.SgraphmlPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Scaled Icon Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.synligare.sgraphml.impl.ScaledIconTypeImpl#getImageIcon <em>Image Icon</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.impl.ScaledIconTypeImpl#getXScale <em>XScale</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.impl.ScaledIconTypeImpl#getYScale <em>YScale</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScaledIconTypeImpl extends MinimalEObjectImpl.Container implements ScaledIconType {
	/**
	 * The cached value of the '{@link #getImageIcon() <em>Image Icon</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImageIcon()
	 * @generated
	 * @ordered
	 */
	protected ImageIconType imageIcon;

	/**
	 * The default value of the '{@link #getXScale() <em>XScale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXScale()
	 * @generated
	 * @ordered
	 */
	protected static final double XSCALE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getXScale() <em>XScale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXScale()
	 * @generated
	 * @ordered
	 */
	protected double xScale = XSCALE_EDEFAULT;

	/**
	 * This is true if the XScale attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean xScaleESet;

	/**
	 * The default value of the '{@link #getYScale() <em>YScale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYScale()
	 * @generated
	 * @ordered
	 */
	protected static final double YSCALE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getYScale() <em>YScale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYScale()
	 * @generated
	 * @ordered
	 */
	protected double yScale = YSCALE_EDEFAULT;

	/**
	 * This is true if the YScale attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean yScaleESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScaledIconTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SgraphmlPackage.Literals.SCALED_ICON_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImageIconType getImageIcon() {
		return imageIcon;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetImageIcon(ImageIconType newImageIcon, NotificationChain msgs) {
		ImageIconType oldImageIcon = imageIcon;
		imageIcon = newImageIcon;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SgraphmlPackage.SCALED_ICON_TYPE__IMAGE_ICON, oldImageIcon, newImageIcon);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImageIcon(ImageIconType newImageIcon) {
		if (newImageIcon != imageIcon) {
			NotificationChain msgs = null;
			if (imageIcon != null)
				msgs = ((InternalEObject)imageIcon).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SgraphmlPackage.SCALED_ICON_TYPE__IMAGE_ICON, null, msgs);
			if (newImageIcon != null)
				msgs = ((InternalEObject)newImageIcon).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SgraphmlPackage.SCALED_ICON_TYPE__IMAGE_ICON, null, msgs);
			msgs = basicSetImageIcon(newImageIcon, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SgraphmlPackage.SCALED_ICON_TYPE__IMAGE_ICON, newImageIcon, newImageIcon));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getXScale() {
		return xScale;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setXScale(double newXScale) {
		double oldXScale = xScale;
		xScale = newXScale;
		boolean oldXScaleESet = xScaleESet;
		xScaleESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SgraphmlPackage.SCALED_ICON_TYPE__XSCALE, oldXScale, xScale, !oldXScaleESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetXScale() {
		double oldXScale = xScale;
		boolean oldXScaleESet = xScaleESet;
		xScale = XSCALE_EDEFAULT;
		xScaleESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, SgraphmlPackage.SCALED_ICON_TYPE__XSCALE, oldXScale, XSCALE_EDEFAULT, oldXScaleESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetXScale() {
		return xScaleESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getYScale() {
		return yScale;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setYScale(double newYScale) {
		double oldYScale = yScale;
		yScale = newYScale;
		boolean oldYScaleESet = yScaleESet;
		yScaleESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SgraphmlPackage.SCALED_ICON_TYPE__YSCALE, oldYScale, yScale, !oldYScaleESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetYScale() {
		double oldYScale = yScale;
		boolean oldYScaleESet = yScaleESet;
		yScale = YSCALE_EDEFAULT;
		yScaleESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, SgraphmlPackage.SCALED_ICON_TYPE__YSCALE, oldYScale, YSCALE_EDEFAULT, oldYScaleESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetYScale() {
		return yScaleESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SgraphmlPackage.SCALED_ICON_TYPE__IMAGE_ICON:
				return basicSetImageIcon(null, msgs);
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
			case SgraphmlPackage.SCALED_ICON_TYPE__IMAGE_ICON:
				return getImageIcon();
			case SgraphmlPackage.SCALED_ICON_TYPE__XSCALE:
				return getXScale();
			case SgraphmlPackage.SCALED_ICON_TYPE__YSCALE:
				return getYScale();
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
			case SgraphmlPackage.SCALED_ICON_TYPE__IMAGE_ICON:
				setImageIcon((ImageIconType)newValue);
				return;
			case SgraphmlPackage.SCALED_ICON_TYPE__XSCALE:
				setXScale((Double)newValue);
				return;
			case SgraphmlPackage.SCALED_ICON_TYPE__YSCALE:
				setYScale((Double)newValue);
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
			case SgraphmlPackage.SCALED_ICON_TYPE__IMAGE_ICON:
				setImageIcon((ImageIconType)null);
				return;
			case SgraphmlPackage.SCALED_ICON_TYPE__XSCALE:
				unsetXScale();
				return;
			case SgraphmlPackage.SCALED_ICON_TYPE__YSCALE:
				unsetYScale();
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
			case SgraphmlPackage.SCALED_ICON_TYPE__IMAGE_ICON:
				return imageIcon != null;
			case SgraphmlPackage.SCALED_ICON_TYPE__XSCALE:
				return isSetXScale();
			case SgraphmlPackage.SCALED_ICON_TYPE__YSCALE:
				return isSetYScale();
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
		result.append(" (xScale: ");
		if (xScaleESet) result.append(xScale); else result.append("<unset>");
		result.append(", yScale: ");
		if (yScaleESet) result.append(yScale); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //ScaledIconTypeImpl
