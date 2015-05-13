/**
 */
package eu.synligare.sgraphml;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Shape Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.synligare.sgraphml.ShapeType#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.synligare.sgraphml.SgraphmlPackage#getShapeType()
 * @model extendedMetaData="name='Shape_._type' kind='empty'"
 * @generated
 */
public interface ShapeType extends EObject {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link eu.synligare.sgraphml.ShapeTypeType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                     The shape type.
	 *                   
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see eu.synligare.sgraphml.ShapeTypeType
	 * @see #isSetType()
	 * @see #unsetType()
	 * @see #setType(ShapeTypeType)
	 * @see eu.synligare.sgraphml.SgraphmlPackage#getShapeType_Type()
	 * @model unsettable="true" required="true"
	 *        extendedMetaData="kind='attribute' name='type'"
	 * @generated
	 */
	ShapeTypeType getType();

	/**
	 * Sets the value of the '{@link eu.synligare.sgraphml.ShapeType#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see eu.synligare.sgraphml.ShapeTypeType
	 * @see #isSetType()
	 * @see #unsetType()
	 * @see #getType()
	 * @generated
	 */
	void setType(ShapeTypeType value);

	/**
	 * Unsets the value of the '{@link eu.synligare.sgraphml.ShapeType#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetType()
	 * @see #getType()
	 * @see #setType(ShapeTypeType)
	 * @generated
	 */
	void unsetType();

	/**
	 * Returns whether the value of the '{@link eu.synligare.sgraphml.ShapeType#getType <em>Type</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Type</em>' attribute is set.
	 * @see #unsetType()
	 * @see #getType()
	 * @see #setType(ShapeTypeType)
	 * @generated
	 */
	boolean isSetType();

} // ShapeType
