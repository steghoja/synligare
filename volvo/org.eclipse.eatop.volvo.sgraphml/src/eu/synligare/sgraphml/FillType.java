/**
 */
package eu.synligare.sgraphml;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Fill Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.synligare.sgraphml.FillType#getColor <em>Color</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.FillType#getColor2 <em>Color2</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.synligare.sgraphml.SgraphmlPackage#getFillType()
 * @model extendedMetaData="name='Fill_._type' kind='empty'"
 * @generated
 */
public interface FillType extends EObject {
	/**
	 * Returns the value of the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                 The first fill color.
	 *               
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Color</em>' attribute.
	 * @see #setColor(String)
	 * @see eu.synligare.sgraphml.SgraphmlPackage#getFillType_Color()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='color'"
	 * @generated
	 */
	String getColor();

	/**
	 * Sets the value of the '{@link eu.synligare.sgraphml.FillType#getColor <em>Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Color</em>' attribute.
	 * @see #getColor()
	 * @generated
	 */
	void setColor(String value);

	/**
	 * Returns the value of the '<em><b>Color2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                 The second fill color.
	 *               
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Color2</em>' attribute.
	 * @see #setColor2(String)
	 * @see eu.synligare.sgraphml.SgraphmlPackage#getFillType_Color2()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='color2'"
	 * @generated
	 */
	String getColor2();

	/**
	 * Sets the value of the '{@link eu.synligare.sgraphml.FillType#getColor2 <em>Color2</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Color2</em>' attribute.
	 * @see #getColor2()
	 * @generated
	 */
	void setColor2(String value);

} // FillType
