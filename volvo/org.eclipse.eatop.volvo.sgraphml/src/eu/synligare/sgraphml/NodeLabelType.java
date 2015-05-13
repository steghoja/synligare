/**
 */
package eu.synligare.sgraphml;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node Label Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 *         Complex type that describes the additional attributes of node labels.
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.synligare.sgraphml.NodeLabelType#getHorizontalTextPosition <em>Horizontal Text Position</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.NodeLabelType#getIconData <em>Icon Data</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.NodeLabelType#getIconTextGap <em>Icon Text Gap</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.NodeLabelType#getPlacement <em>Placement</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.NodeLabelType#getVerticalTextPosition <em>Vertical Text Position</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.synligare.sgraphml.SgraphmlPackage#getNodeLabelType()
 * @model extendedMetaData="name='NodeLabel.type' kind='mixed'"
 * @generated
 */
public interface NodeLabelType extends LabelType {
	/**
	 * Returns the value of the '<em><b>Horizontal Text Position</b></em>' attribute.
	 * The literals are from the enumeration {@link eu.synligare.sgraphml.HorizontalTextPositionType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *               The horizontal text position wrt to an optional icon.
	 *             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Horizontal Text Position</em>' attribute.
	 * @see eu.synligare.sgraphml.HorizontalTextPositionType
	 * @see #isSetHorizontalTextPosition()
	 * @see #unsetHorizontalTextPosition()
	 * @see #setHorizontalTextPosition(HorizontalTextPositionType)
	 * @see eu.synligare.sgraphml.SgraphmlPackage#getNodeLabelType_HorizontalTextPosition()
	 * @model unsettable="true"
	 *        extendedMetaData="kind='attribute' name='horizontalTextPosition'"
	 * @generated
	 */
	HorizontalTextPositionType getHorizontalTextPosition();

	/**
	 * Sets the value of the '{@link eu.synligare.sgraphml.NodeLabelType#getHorizontalTextPosition <em>Horizontal Text Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Horizontal Text Position</em>' attribute.
	 * @see eu.synligare.sgraphml.HorizontalTextPositionType
	 * @see #isSetHorizontalTextPosition()
	 * @see #unsetHorizontalTextPosition()
	 * @see #getHorizontalTextPosition()
	 * @generated
	 */
	void setHorizontalTextPosition(HorizontalTextPositionType value);

	/**
	 * Unsets the value of the '{@link eu.synligare.sgraphml.NodeLabelType#getHorizontalTextPosition <em>Horizontal Text Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetHorizontalTextPosition()
	 * @see #getHorizontalTextPosition()
	 * @see #setHorizontalTextPosition(HorizontalTextPositionType)
	 * @generated
	 */
	void unsetHorizontalTextPosition();

	/**
	 * Returns whether the value of the '{@link eu.synligare.sgraphml.NodeLabelType#getHorizontalTextPosition <em>Horizontal Text Position</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Horizontal Text Position</em>' attribute is set.
	 * @see #unsetHorizontalTextPosition()
	 * @see #getHorizontalTextPosition()
	 * @see #setHorizontalTextPosition(HorizontalTextPositionType)
	 * @generated
	 */
	boolean isSetHorizontalTextPosition();

	/**
	 * Returns the value of the '<em><b>Icon Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *               Reference to an embedded arbitrary serializable resource.
	 *             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Icon Data</em>' attribute.
	 * @see #setIconData(String)
	 * @see eu.synligare.sgraphml.SgraphmlPackage#getNodeLabelType_IconData()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='iconData'"
	 * @generated
	 */
	String getIconData();

	/**
	 * Sets the value of the '{@link eu.synligare.sgraphml.NodeLabelType#getIconData <em>Icon Data</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Icon Data</em>' attribute.
	 * @see #getIconData()
	 * @generated
	 */
	void setIconData(String value);

	/**
	 * Returns the value of the '<em><b>Icon Text Gap</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *               The distance between the label text and an optional icon.
	 *             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Icon Text Gap</em>' attribute.
	 * @see #isSetIconTextGap()
	 * @see #unsetIconTextGap()
	 * @see #setIconTextGap(byte)
	 * @see eu.synligare.sgraphml.SgraphmlPackage#getNodeLabelType_IconTextGap()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Byte"
	 *        extendedMetaData="kind='attribute' name='iconTextGap'"
	 * @generated
	 */
	byte getIconTextGap();

	/**
	 * Sets the value of the '{@link eu.synligare.sgraphml.NodeLabelType#getIconTextGap <em>Icon Text Gap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Icon Text Gap</em>' attribute.
	 * @see #isSetIconTextGap()
	 * @see #unsetIconTextGap()
	 * @see #getIconTextGap()
	 * @generated
	 */
	void setIconTextGap(byte value);

	/**
	 * Unsets the value of the '{@link eu.synligare.sgraphml.NodeLabelType#getIconTextGap <em>Icon Text Gap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIconTextGap()
	 * @see #getIconTextGap()
	 * @see #setIconTextGap(byte)
	 * @generated
	 */
	void unsetIconTextGap();

	/**
	 * Returns whether the value of the '{@link eu.synligare.sgraphml.NodeLabelType#getIconTextGap <em>Icon Text Gap</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Icon Text Gap</em>' attribute is set.
	 * @see #unsetIconTextGap()
	 * @see #getIconTextGap()
	 * @see #setIconTextGap(byte)
	 * @generated
	 */
	boolean isSetIconTextGap();

	/**
	 * Returns the value of the '<em><b>Placement</b></em>' attribute.
	 * The literals are from the enumeration {@link eu.synligare.sgraphml.PlacementType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *               The actual label position in the current label model.
	 *             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Placement</em>' attribute.
	 * @see eu.synligare.sgraphml.PlacementType
	 * @see #isSetPlacement()
	 * @see #unsetPlacement()
	 * @see #setPlacement(PlacementType)
	 * @see eu.synligare.sgraphml.SgraphmlPackage#getNodeLabelType_Placement()
	 * @model unsettable="true"
	 *        extendedMetaData="kind='attribute' name='placement'"
	 * @generated
	 */
	PlacementType getPlacement();

	/**
	 * Sets the value of the '{@link eu.synligare.sgraphml.NodeLabelType#getPlacement <em>Placement</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Placement</em>' attribute.
	 * @see eu.synligare.sgraphml.PlacementType
	 * @see #isSetPlacement()
	 * @see #unsetPlacement()
	 * @see #getPlacement()
	 * @generated
	 */
	void setPlacement(PlacementType value);

	/**
	 * Unsets the value of the '{@link eu.synligare.sgraphml.NodeLabelType#getPlacement <em>Placement</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPlacement()
	 * @see #getPlacement()
	 * @see #setPlacement(PlacementType)
	 * @generated
	 */
	void unsetPlacement();

	/**
	 * Returns whether the value of the '{@link eu.synligare.sgraphml.NodeLabelType#getPlacement <em>Placement</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Placement</em>' attribute is set.
	 * @see #unsetPlacement()
	 * @see #getPlacement()
	 * @see #setPlacement(PlacementType)
	 * @generated
	 */
	boolean isSetPlacement();

	/**
	 * Returns the value of the '<em><b>Vertical Text Position</b></em>' attribute.
	 * The literals are from the enumeration {@link eu.synligare.sgraphml.VerticalTextPositionType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *               The vertical text position wrt to an optional icon.
	 *             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Vertical Text Position</em>' attribute.
	 * @see eu.synligare.sgraphml.VerticalTextPositionType
	 * @see #isSetVerticalTextPosition()
	 * @see #unsetVerticalTextPosition()
	 * @see #setVerticalTextPosition(VerticalTextPositionType)
	 * @see eu.synligare.sgraphml.SgraphmlPackage#getNodeLabelType_VerticalTextPosition()
	 * @model unsettable="true"
	 *        extendedMetaData="kind='attribute' name='verticalTextPosition'"
	 * @generated
	 */
	VerticalTextPositionType getVerticalTextPosition();

	/**
	 * Sets the value of the '{@link eu.synligare.sgraphml.NodeLabelType#getVerticalTextPosition <em>Vertical Text Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Vertical Text Position</em>' attribute.
	 * @see eu.synligare.sgraphml.VerticalTextPositionType
	 * @see #isSetVerticalTextPosition()
	 * @see #unsetVerticalTextPosition()
	 * @see #getVerticalTextPosition()
	 * @generated
	 */
	void setVerticalTextPosition(VerticalTextPositionType value);

	/**
	 * Unsets the value of the '{@link eu.synligare.sgraphml.NodeLabelType#getVerticalTextPosition <em>Vertical Text Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetVerticalTextPosition()
	 * @see #getVerticalTextPosition()
	 * @see #setVerticalTextPosition(VerticalTextPositionType)
	 * @generated
	 */
	void unsetVerticalTextPosition();

	/**
	 * Returns whether the value of the '{@link eu.synligare.sgraphml.NodeLabelType#getVerticalTextPosition <em>Vertical Text Position</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Vertical Text Position</em>' attribute is set.
	 * @see #unsetVerticalTextPosition()
	 * @see #getVerticalTextPosition()
	 * @see #setVerticalTextPosition(VerticalTextPositionType)
	 * @generated
	 */
	boolean isSetVerticalTextPosition();

} // NodeLabelType
