/**
 */
package eu.synligare.sgraphml;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Arrows Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.synligare.sgraphml.ArrowsType#getSource <em>Source</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.ArrowsType#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.synligare.sgraphml.SgraphmlPackage#getArrowsType()
 * @model extendedMetaData="name='Arrows_._type' kind='empty'"
 * @generated
 */
public interface ArrowsType extends EObject {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' attribute.
	 * The literals are from the enumeration {@link eu.synligare.sgraphml.ArrowTypeType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                 The source arrow type.
	 *               
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Source</em>' attribute.
	 * @see eu.synligare.sgraphml.ArrowTypeType
	 * @see #isSetSource()
	 * @see #unsetSource()
	 * @see #setSource(ArrowTypeType)
	 * @see eu.synligare.sgraphml.SgraphmlPackage#getArrowsType_Source()
	 * @model unsettable="true"
	 *        extendedMetaData="kind='attribute' name='source'"
	 * @generated
	 */
	ArrowTypeType getSource();

	/**
	 * Sets the value of the '{@link eu.synligare.sgraphml.ArrowsType#getSource <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' attribute.
	 * @see eu.synligare.sgraphml.ArrowTypeType
	 * @see #isSetSource()
	 * @see #unsetSource()
	 * @see #getSource()
	 * @generated
	 */
	void setSource(ArrowTypeType value);

	/**
	 * Unsets the value of the '{@link eu.synligare.sgraphml.ArrowsType#getSource <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSource()
	 * @see #getSource()
	 * @see #setSource(ArrowTypeType)
	 * @generated
	 */
	void unsetSource();

	/**
	 * Returns whether the value of the '{@link eu.synligare.sgraphml.ArrowsType#getSource <em>Source</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Source</em>' attribute is set.
	 * @see #unsetSource()
	 * @see #getSource()
	 * @see #setSource(ArrowTypeType)
	 * @generated
	 */
	boolean isSetSource();

	/**
	 * Returns the value of the '<em><b>Target</b></em>' attribute.
	 * The literals are from the enumeration {@link eu.synligare.sgraphml.ArrowTypeType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                 The target arrow type.
	 *               
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Target</em>' attribute.
	 * @see eu.synligare.sgraphml.ArrowTypeType
	 * @see #isSetTarget()
	 * @see #unsetTarget()
	 * @see #setTarget(ArrowTypeType)
	 * @see eu.synligare.sgraphml.SgraphmlPackage#getArrowsType_Target()
	 * @model unsettable="true"
	 *        extendedMetaData="kind='attribute' name='target'"
	 * @generated
	 */
	ArrowTypeType getTarget();

	/**
	 * Sets the value of the '{@link eu.synligare.sgraphml.ArrowsType#getTarget <em>Target</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' attribute.
	 * @see eu.synligare.sgraphml.ArrowTypeType
	 * @see #isSetTarget()
	 * @see #unsetTarget()
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(ArrowTypeType value);

	/**
	 * Unsets the value of the '{@link eu.synligare.sgraphml.ArrowsType#getTarget <em>Target</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTarget()
	 * @see #getTarget()
	 * @see #setTarget(ArrowTypeType)
	 * @generated
	 */
	void unsetTarget();

	/**
	 * Returns whether the value of the '{@link eu.synligare.sgraphml.ArrowsType#getTarget <em>Target</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Target</em>' attribute is set.
	 * @see #unsetTarget()
	 * @see #getTarget()
	 * @see #setTarget(ArrowTypeType)
	 * @generated
	 */
	boolean isSetTarget();

} // ArrowsType
