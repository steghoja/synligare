/**
 */
package eu.synligare.sgraphml;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Scaled Icon Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 *         Complex type for a Scaled Icon.
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.synligare.sgraphml.ScaledIconType#getImageIcon <em>Image Icon</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.ScaledIconType#getXScale <em>XScale</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.ScaledIconType#getYScale <em>YScale</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.synligare.sgraphml.SgraphmlPackage#getScaledIconType()
 * @model extendedMetaData="name='ScaledIcon.type' kind='elementOnly'"
 * @generated
 */
public interface ScaledIconType extends EObject {
	/**
	 * Returns the value of the '<em><b>Image Icon</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             A shared image icon resource.
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Image Icon</em>' containment reference.
	 * @see #setImageIcon(ImageIconType)
	 * @see eu.synligare.sgraphml.SgraphmlPackage#getScaledIconType_ImageIcon()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='ImageIcon' namespace='##targetNamespace'"
	 * @generated
	 */
	ImageIconType getImageIcon();

	/**
	 * Sets the value of the '{@link eu.synligare.sgraphml.ScaledIconType#getImageIcon <em>Image Icon</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Image Icon</em>' containment reference.
	 * @see #getImageIcon()
	 * @generated
	 */
	void setImageIcon(ImageIconType value);

	/**
	 * Returns the value of the '<em><b>XScale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *           The x scale factor of the icon. 
	 *         
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>XScale</em>' attribute.
	 * @see #isSetXScale()
	 * @see #unsetXScale()
	 * @see #setXScale(double)
	 * @see eu.synligare.sgraphml.SgraphmlPackage#getScaledIconType_XScale()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Double" required="true"
	 *        extendedMetaData="kind='attribute' name='xScale'"
	 * @generated
	 */
	double getXScale();

	/**
	 * Sets the value of the '{@link eu.synligare.sgraphml.ScaledIconType#getXScale <em>XScale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>XScale</em>' attribute.
	 * @see #isSetXScale()
	 * @see #unsetXScale()
	 * @see #getXScale()
	 * @generated
	 */
	void setXScale(double value);

	/**
	 * Unsets the value of the '{@link eu.synligare.sgraphml.ScaledIconType#getXScale <em>XScale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetXScale()
	 * @see #getXScale()
	 * @see #setXScale(double)
	 * @generated
	 */
	void unsetXScale();

	/**
	 * Returns whether the value of the '{@link eu.synligare.sgraphml.ScaledIconType#getXScale <em>XScale</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>XScale</em>' attribute is set.
	 * @see #unsetXScale()
	 * @see #getXScale()
	 * @see #setXScale(double)
	 * @generated
	 */
	boolean isSetXScale();

	/**
	 * Returns the value of the '<em><b>YScale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *            The y scale factor of the icon.
	 *          
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>YScale</em>' attribute.
	 * @see #isSetYScale()
	 * @see #unsetYScale()
	 * @see #setYScale(double)
	 * @see eu.synligare.sgraphml.SgraphmlPackage#getScaledIconType_YScale()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Double" required="true"
	 *        extendedMetaData="kind='attribute' name='yScale'"
	 * @generated
	 */
	double getYScale();

	/**
	 * Sets the value of the '{@link eu.synligare.sgraphml.ScaledIconType#getYScale <em>YScale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>YScale</em>' attribute.
	 * @see #isSetYScale()
	 * @see #unsetYScale()
	 * @see #getYScale()
	 * @generated
	 */
	void setYScale(double value);

	/**
	 * Unsets the value of the '{@link eu.synligare.sgraphml.ScaledIconType#getYScale <em>YScale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetYScale()
	 * @see #getYScale()
	 * @see #setYScale(double)
	 * @generated
	 */
	void unsetYScale();

	/**
	 * Returns whether the value of the '{@link eu.synligare.sgraphml.ScaledIconType#getYScale <em>YScale</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>YScale</em>' attribute is set.
	 * @see #unsetYScale()
	 * @see #getYScale()
	 * @see #setYScale(double)
	 * @generated
	 */
	boolean isSetYScale();

} // ScaledIconType
