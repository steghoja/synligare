/**
 */
package eu.synligare.sgraphml;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Base Edge Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 *         Complex type that describes the common attributes of an edge.
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.synligare.sgraphml.BaseEdgeType#getPath <em>Path</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.BaseEdgeType#getLineStyle <em>Line Style</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.BaseEdgeType#getArrows <em>Arrows</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.BaseEdgeType#getEdgeLabel <em>Edge Label</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.synligare.sgraphml.SgraphmlPackage#getBaseEdgeType()
 * @model extendedMetaData="name='BaseEdge.type' kind='elementOnly'"
 * @generated
 */
public interface BaseEdgeType extends EObject {
	/**
	 * Returns the value of the '<em><b>Path</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             The edge path.
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Path</em>' containment reference.
	 * @see #setPath(PathType)
	 * @see eu.synligare.sgraphml.SgraphmlPackage#getBaseEdgeType_Path()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='Path' namespace='##targetNamespace'"
	 * @generated
	 */
	PathType getPath();

	/**
	 * Sets the value of the '{@link eu.synligare.sgraphml.BaseEdgeType#getPath <em>Path</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path</em>' containment reference.
	 * @see #getPath()
	 * @generated
	 */
	void setPath(PathType value);

	/**
	 * Returns the value of the '<em><b>Line Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             The line style used to draw the edge.
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Line Style</em>' containment reference.
	 * @see #setLineStyle(LineStyleType)
	 * @see eu.synligare.sgraphml.SgraphmlPackage#getBaseEdgeType_LineStyle()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='LineStyle' namespace='##targetNamespace'"
	 * @generated
	 */
	LineStyleType getLineStyle();

	/**
	 * Sets the value of the '{@link eu.synligare.sgraphml.BaseEdgeType#getLineStyle <em>Line Style</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Line Style</em>' containment reference.
	 * @see #getLineStyle()
	 * @generated
	 */
	void setLineStyle(LineStyleType value);

	/**
	 * Returns the value of the '<em><b>Arrows</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             The source and target arrows.
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Arrows</em>' containment reference.
	 * @see #setArrows(ArrowsType)
	 * @see eu.synligare.sgraphml.SgraphmlPackage#getBaseEdgeType_Arrows()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='Arrows' namespace='##targetNamespace'"
	 * @generated
	 */
	ArrowsType getArrows();

	/**
	 * Sets the value of the '{@link eu.synligare.sgraphml.BaseEdgeType#getArrows <em>Arrows</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Arrows</em>' containment reference.
	 * @see #getArrows()
	 * @generated
	 */
	void setArrows(ArrowsType value);

	/**
	 * Returns the value of the '<em><b>Edge Label</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             The edge label.
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Edge Label</em>' containment reference.
	 * @see #setEdgeLabel(EdgeLabelType)
	 * @see eu.synligare.sgraphml.SgraphmlPackage#getBaseEdgeType_EdgeLabel()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='EdgeLabel' namespace='##targetNamespace'"
	 * @generated
	 */
	EdgeLabelType getEdgeLabel();

	/**
	 * Sets the value of the '{@link eu.synligare.sgraphml.BaseEdgeType#getEdgeLabel <em>Edge Label</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Edge Label</em>' containment reference.
	 * @see #getEdgeLabel()
	 * @generated
	 */
	void setEdgeLabel(EdgeLabelType value);

} // BaseEdgeType
