/**
 */
package eu.synligare.sgraphml;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Shape Node Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 *         Complex type that describes the attributes of a ShapeNode
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.synligare.sgraphml.ShapeNodeType#getShape <em>Shape</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.synligare.sgraphml.SgraphmlPackage#getShapeNodeType()
 * @model extendedMetaData="name='ShapeNode.type' kind='elementOnly'"
 * @generated
 */
public interface ShapeNodeType extends BaseNodeType {
	/**
	 * Returns the value of the '<em><b>Shape</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                 The actual shape of a ShapeNode.
	 *               
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Shape</em>' containment reference.
	 * @see #setShape(ShapeType)
	 * @see eu.synligare.sgraphml.SgraphmlPackage#getShapeNodeType_Shape()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='Shape' namespace='##targetNamespace'"
	 * @generated
	 */
	ShapeType getShape();

	/**
	 * Sets the value of the '{@link eu.synligare.sgraphml.ShapeNodeType#getShape <em>Shape</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Shape</em>' containment reference.
	 * @see #getShape()
	 * @generated
	 */
	void setShape(ShapeType value);

} // ShapeNodeType
