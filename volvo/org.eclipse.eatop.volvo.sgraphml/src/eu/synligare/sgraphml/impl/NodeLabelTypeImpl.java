/**
 */
package eu.synligare.sgraphml.impl;

import eu.synligare.sgraphml.HorizontalTextPositionType;
import eu.synligare.sgraphml.NodeLabelType;
import eu.synligare.sgraphml.PlacementType;
import eu.synligare.sgraphml.SgraphmlPackage;
import eu.synligare.sgraphml.VerticalTextPositionType;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node Label Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.synligare.sgraphml.impl.NodeLabelTypeImpl#getHorizontalTextPosition <em>Horizontal Text Position</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.impl.NodeLabelTypeImpl#getIconData <em>Icon Data</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.impl.NodeLabelTypeImpl#getIconTextGap <em>Icon Text Gap</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.impl.NodeLabelTypeImpl#getPlacement <em>Placement</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.impl.NodeLabelTypeImpl#getVerticalTextPosition <em>Vertical Text Position</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NodeLabelTypeImpl extends LabelTypeImpl implements NodeLabelType {
	/**
	 * The default value of the '{@link #getHorizontalTextPosition() <em>Horizontal Text Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHorizontalTextPosition()
	 * @generated
	 * @ordered
	 */
	protected static final HorizontalTextPositionType HORIZONTAL_TEXT_POSITION_EDEFAULT = HorizontalTextPositionType.LEFT;

	/**
	 * The cached value of the '{@link #getHorizontalTextPosition() <em>Horizontal Text Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHorizontalTextPosition()
	 * @generated
	 * @ordered
	 */
	protected HorizontalTextPositionType horizontalTextPosition = HORIZONTAL_TEXT_POSITION_EDEFAULT;

	/**
	 * This is true if the Horizontal Text Position attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean horizontalTextPositionESet;

	/**
	 * The default value of the '{@link #getIconData() <em>Icon Data</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIconData()
	 * @generated
	 * @ordered
	 */
	protected static final String ICON_DATA_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIconData() <em>Icon Data</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIconData()
	 * @generated
	 * @ordered
	 */
	protected String iconData = ICON_DATA_EDEFAULT;

	/**
	 * The default value of the '{@link #getIconTextGap() <em>Icon Text Gap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIconTextGap()
	 * @generated
	 * @ordered
	 */
	protected static final byte ICON_TEXT_GAP_EDEFAULT = 0x00;

	/**
	 * The cached value of the '{@link #getIconTextGap() <em>Icon Text Gap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIconTextGap()
	 * @generated
	 * @ordered
	 */
	protected byte iconTextGap = ICON_TEXT_GAP_EDEFAULT;

	/**
	 * This is true if the Icon Text Gap attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean iconTextGapESet;

	/**
	 * The default value of the '{@link #getPlacement() <em>Placement</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPlacement()
	 * @generated
	 * @ordered
	 */
	protected static final PlacementType PLACEMENT_EDEFAULT = PlacementType.TOP;

	/**
	 * The cached value of the '{@link #getPlacement() <em>Placement</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPlacement()
	 * @generated
	 * @ordered
	 */
	protected PlacementType placement = PLACEMENT_EDEFAULT;

	/**
	 * This is true if the Placement attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean placementESet;

	/**
	 * The default value of the '{@link #getVerticalTextPosition() <em>Vertical Text Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVerticalTextPosition()
	 * @generated
	 * @ordered
	 */
	protected static final VerticalTextPositionType VERTICAL_TEXT_POSITION_EDEFAULT = VerticalTextPositionType.TOP;

	/**
	 * The cached value of the '{@link #getVerticalTextPosition() <em>Vertical Text Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVerticalTextPosition()
	 * @generated
	 * @ordered
	 */
	protected VerticalTextPositionType verticalTextPosition = VERTICAL_TEXT_POSITION_EDEFAULT;

	/**
	 * This is true if the Vertical Text Position attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean verticalTextPositionESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NodeLabelTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SgraphmlPackage.Literals.NODE_LABEL_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HorizontalTextPositionType getHorizontalTextPosition() {
		return horizontalTextPosition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHorizontalTextPosition(HorizontalTextPositionType newHorizontalTextPosition) {
		HorizontalTextPositionType oldHorizontalTextPosition = horizontalTextPosition;
		horizontalTextPosition = newHorizontalTextPosition == null ? HORIZONTAL_TEXT_POSITION_EDEFAULT : newHorizontalTextPosition;
		boolean oldHorizontalTextPositionESet = horizontalTextPositionESet;
		horizontalTextPositionESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SgraphmlPackage.NODE_LABEL_TYPE__HORIZONTAL_TEXT_POSITION, oldHorizontalTextPosition, horizontalTextPosition, !oldHorizontalTextPositionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetHorizontalTextPosition() {
		HorizontalTextPositionType oldHorizontalTextPosition = horizontalTextPosition;
		boolean oldHorizontalTextPositionESet = horizontalTextPositionESet;
		horizontalTextPosition = HORIZONTAL_TEXT_POSITION_EDEFAULT;
		horizontalTextPositionESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, SgraphmlPackage.NODE_LABEL_TYPE__HORIZONTAL_TEXT_POSITION, oldHorizontalTextPosition, HORIZONTAL_TEXT_POSITION_EDEFAULT, oldHorizontalTextPositionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetHorizontalTextPosition() {
		return horizontalTextPositionESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIconData() {
		return iconData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIconData(String newIconData) {
		String oldIconData = iconData;
		iconData = newIconData;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SgraphmlPackage.NODE_LABEL_TYPE__ICON_DATA, oldIconData, iconData));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public byte getIconTextGap() {
		return iconTextGap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIconTextGap(byte newIconTextGap) {
		byte oldIconTextGap = iconTextGap;
		iconTextGap = newIconTextGap;
		boolean oldIconTextGapESet = iconTextGapESet;
		iconTextGapESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SgraphmlPackage.NODE_LABEL_TYPE__ICON_TEXT_GAP, oldIconTextGap, iconTextGap, !oldIconTextGapESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetIconTextGap() {
		byte oldIconTextGap = iconTextGap;
		boolean oldIconTextGapESet = iconTextGapESet;
		iconTextGap = ICON_TEXT_GAP_EDEFAULT;
		iconTextGapESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, SgraphmlPackage.NODE_LABEL_TYPE__ICON_TEXT_GAP, oldIconTextGap, ICON_TEXT_GAP_EDEFAULT, oldIconTextGapESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetIconTextGap() {
		return iconTextGapESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PlacementType getPlacement() {
		return placement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPlacement(PlacementType newPlacement) {
		PlacementType oldPlacement = placement;
		placement = newPlacement == null ? PLACEMENT_EDEFAULT : newPlacement;
		boolean oldPlacementESet = placementESet;
		placementESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SgraphmlPackage.NODE_LABEL_TYPE__PLACEMENT, oldPlacement, placement, !oldPlacementESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetPlacement() {
		PlacementType oldPlacement = placement;
		boolean oldPlacementESet = placementESet;
		placement = PLACEMENT_EDEFAULT;
		placementESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, SgraphmlPackage.NODE_LABEL_TYPE__PLACEMENT, oldPlacement, PLACEMENT_EDEFAULT, oldPlacementESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetPlacement() {
		return placementESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VerticalTextPositionType getVerticalTextPosition() {
		return verticalTextPosition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVerticalTextPosition(VerticalTextPositionType newVerticalTextPosition) {
		VerticalTextPositionType oldVerticalTextPosition = verticalTextPosition;
		verticalTextPosition = newVerticalTextPosition == null ? VERTICAL_TEXT_POSITION_EDEFAULT : newVerticalTextPosition;
		boolean oldVerticalTextPositionESet = verticalTextPositionESet;
		verticalTextPositionESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SgraphmlPackage.NODE_LABEL_TYPE__VERTICAL_TEXT_POSITION, oldVerticalTextPosition, verticalTextPosition, !oldVerticalTextPositionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetVerticalTextPosition() {
		VerticalTextPositionType oldVerticalTextPosition = verticalTextPosition;
		boolean oldVerticalTextPositionESet = verticalTextPositionESet;
		verticalTextPosition = VERTICAL_TEXT_POSITION_EDEFAULT;
		verticalTextPositionESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, SgraphmlPackage.NODE_LABEL_TYPE__VERTICAL_TEXT_POSITION, oldVerticalTextPosition, VERTICAL_TEXT_POSITION_EDEFAULT, oldVerticalTextPositionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetVerticalTextPosition() {
		return verticalTextPositionESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SgraphmlPackage.NODE_LABEL_TYPE__HORIZONTAL_TEXT_POSITION:
				return getHorizontalTextPosition();
			case SgraphmlPackage.NODE_LABEL_TYPE__ICON_DATA:
				return getIconData();
			case SgraphmlPackage.NODE_LABEL_TYPE__ICON_TEXT_GAP:
				return getIconTextGap();
			case SgraphmlPackage.NODE_LABEL_TYPE__PLACEMENT:
				return getPlacement();
			case SgraphmlPackage.NODE_LABEL_TYPE__VERTICAL_TEXT_POSITION:
				return getVerticalTextPosition();
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
			case SgraphmlPackage.NODE_LABEL_TYPE__HORIZONTAL_TEXT_POSITION:
				setHorizontalTextPosition((HorizontalTextPositionType)newValue);
				return;
			case SgraphmlPackage.NODE_LABEL_TYPE__ICON_DATA:
				setIconData((String)newValue);
				return;
			case SgraphmlPackage.NODE_LABEL_TYPE__ICON_TEXT_GAP:
				setIconTextGap((Byte)newValue);
				return;
			case SgraphmlPackage.NODE_LABEL_TYPE__PLACEMENT:
				setPlacement((PlacementType)newValue);
				return;
			case SgraphmlPackage.NODE_LABEL_TYPE__VERTICAL_TEXT_POSITION:
				setVerticalTextPosition((VerticalTextPositionType)newValue);
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
			case SgraphmlPackage.NODE_LABEL_TYPE__HORIZONTAL_TEXT_POSITION:
				unsetHorizontalTextPosition();
				return;
			case SgraphmlPackage.NODE_LABEL_TYPE__ICON_DATA:
				setIconData(ICON_DATA_EDEFAULT);
				return;
			case SgraphmlPackage.NODE_LABEL_TYPE__ICON_TEXT_GAP:
				unsetIconTextGap();
				return;
			case SgraphmlPackage.NODE_LABEL_TYPE__PLACEMENT:
				unsetPlacement();
				return;
			case SgraphmlPackage.NODE_LABEL_TYPE__VERTICAL_TEXT_POSITION:
				unsetVerticalTextPosition();
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
			case SgraphmlPackage.NODE_LABEL_TYPE__HORIZONTAL_TEXT_POSITION:
				return isSetHorizontalTextPosition();
			case SgraphmlPackage.NODE_LABEL_TYPE__ICON_DATA:
				return ICON_DATA_EDEFAULT == null ? iconData != null : !ICON_DATA_EDEFAULT.equals(iconData);
			case SgraphmlPackage.NODE_LABEL_TYPE__ICON_TEXT_GAP:
				return isSetIconTextGap();
			case SgraphmlPackage.NODE_LABEL_TYPE__PLACEMENT:
				return isSetPlacement();
			case SgraphmlPackage.NODE_LABEL_TYPE__VERTICAL_TEXT_POSITION:
				return isSetVerticalTextPosition();
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
		result.append(" (horizontalTextPosition: ");
		if (horizontalTextPositionESet) result.append(horizontalTextPosition); else result.append("<unset>");
		result.append(", iconData: ");
		result.append(iconData);
		result.append(", iconTextGap: ");
		if (iconTextGapESet) result.append(iconTextGap); else result.append("<unset>");
		result.append(", placement: ");
		if (placementESet) result.append(placement); else result.append("<unset>");
		result.append(", verticalTextPosition: ");
		if (verticalTextPositionESet) result.append(verticalTextPosition); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //NodeLabelTypeImpl
