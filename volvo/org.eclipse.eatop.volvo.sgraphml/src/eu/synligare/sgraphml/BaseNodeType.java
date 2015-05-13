/**
 */
package eu.synligare.sgraphml;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Base Node Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 *         Complex type that describes the common attributes of a node. 
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.synligare.sgraphml.BaseNodeType#getGeometry <em>Geometry</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.BaseNodeType#getFill <em>Fill</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.BaseNodeType#getBorderStyle <em>Border Style</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.BaseNodeType#getNodeLabel <em>Node Label</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.synligare.sgraphml.SgraphmlPackage#getBaseNodeType()
 * @model extendedMetaData="name='BaseNode.type' kind='elementOnly'"
 * @generated
 */
public interface BaseNodeType extends EObject {
	/**
	 * Returns the value of the '<em><b>Geometry</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             The location and size of the node.
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Geometry</em>' containment reference.
	 * @see #setGeometry(GeometryType)
	 * @see eu.synligare.sgraphml.SgraphmlPackage#getBaseNodeType_Geometry()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='Geometry' namespace='##targetNamespace'"
	 * @generated
	 */
	GeometryType getGeometry();

	/**
	 * Sets the value of the '{@link eu.synligare.sgraphml.BaseNodeType#getGeometry <em>Geometry</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Geometry</em>' containment reference.
	 * @see #getGeometry()
	 * @generated
	 */
	void setGeometry(GeometryType value);

	/**
	 * Returns the value of the '<em><b>Fill</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             The fill attributes of the node.
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Fill</em>' containment reference.
	 * @see #setFill(FillType)
	 * @see eu.synligare.sgraphml.SgraphmlPackage#getBaseNodeType_Fill()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='Fill' namespace='##targetNamespace'"
	 * @generated
	 */
	FillType getFill();

	/**
	 * Sets the value of the '{@link eu.synligare.sgraphml.BaseNodeType#getFill <em>Fill</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fill</em>' containment reference.
	 * @see #getFill()
	 * @generated
	 */
	void setFill(FillType value);

	/**
	 * Returns the value of the '<em><b>Border Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             The LineStyle of the border.
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Border Style</em>' containment reference.
	 * @see #setBorderStyle(LineStyleType)
	 * @see eu.synligare.sgraphml.SgraphmlPackage#getBaseNodeType_BorderStyle()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='BorderStyle' namespace='##targetNamespace'"
	 * @generated
	 */
	LineStyleType getBorderStyle();

	/**
	 * Sets the value of the '{@link eu.synligare.sgraphml.BaseNodeType#getBorderStyle <em>Border Style</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Border Style</em>' containment reference.
	 * @see #getBorderStyle()
	 * @generated
	 */
	void setBorderStyle(LineStyleType value);

	/**
	 * Returns the value of the '<em><b>Node Label</b></em>' containment reference list.
	 * The list contents are of type {@link eu.synligare.sgraphml.NodeLabelType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             The (optional) node label.
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Node Label</em>' containment reference list.
	 * @see eu.synligare.sgraphml.SgraphmlPackage#getBaseNodeType_NodeLabel()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='NodeLabel' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<NodeLabelType> getNodeLabel();

} // BaseNodeType
