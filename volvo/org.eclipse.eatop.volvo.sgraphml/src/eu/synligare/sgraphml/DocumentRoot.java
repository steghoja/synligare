/**
 */
package eu.synligare.sgraphml;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.synligare.sgraphml.DocumentRoot#getMixed <em>Mixed</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.DocumentRoot#getGroupNode <em>Group Node</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.DocumentRoot#getPolyLineEdge <em>Poly Line Edge</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.DocumentRoot#getPortNode <em>Port Node</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.DocumentRoot#getResources <em>Resources</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.DocumentRoot#getScaledIcon <em>Scaled Icon</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.DocumentRoot#getShapeNode <em>Shape Node</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.synligare.sgraphml.SgraphmlPackage#getDocumentRoot()
 * @model extendedMetaData="name='' kind='mixed'"
 * @generated
 */
public interface DocumentRoot extends EObject {
	/**
	 * Returns the value of the '<em><b>Mixed</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mixed</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mixed</em>' attribute list.
	 * @see eu.synligare.sgraphml.SgraphmlPackage#getDocumentRoot_Mixed()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='elementWildcard' name=':mixed'"
	 * @generated
	 */
	FeatureMap getMixed();

	/**
	 * Returns the value of the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XMLNS Prefix Map</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XMLNS Prefix Map</em>' map.
	 * @see eu.synligare.sgraphml.SgraphmlPackage#getDocumentRoot_XMLNSPrefixMap()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
	 *        extendedMetaData="kind='attribute' name='xmlns:prefix'"
	 * @generated
	 */
	EMap<String, String> getXMLNSPrefixMap();

	/**
	 * Returns the value of the '<em><b>XSI Schema Location</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XSI Schema Location</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XSI Schema Location</em>' map.
	 * @see eu.synligare.sgraphml.SgraphmlPackage#getDocumentRoot_XSISchemaLocation()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
	 *        extendedMetaData="kind='attribute' name='xsi:schemaLocation'"
	 * @generated
	 */
	EMap<String, String> getXSISchemaLocation();

	/**
	 * Returns the value of the '<em><b>Group Node</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *         GroupNode element definition.
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Group Node</em>' containment reference.
	 * @see #setGroupNode(GroupNodeType)
	 * @see eu.synligare.sgraphml.SgraphmlPackage#getDocumentRoot_GroupNode()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='GroupNode' namespace='##targetNamespace'"
	 * @generated
	 */
	GroupNodeType getGroupNode();

	/**
	 * Sets the value of the '{@link eu.synligare.sgraphml.DocumentRoot#getGroupNode <em>Group Node</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Group Node</em>' containment reference.
	 * @see #getGroupNode()
	 * @generated
	 */
	void setGroupNode(GroupNodeType value);

	/**
	 * Returns the value of the '<em><b>Poly Line Edge</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *         PolyLineEdge element definition.
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Poly Line Edge</em>' containment reference.
	 * @see #setPolyLineEdge(PolyLineEdgeType)
	 * @see eu.synligare.sgraphml.SgraphmlPackage#getDocumentRoot_PolyLineEdge()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='PolyLineEdge' namespace='##targetNamespace'"
	 * @generated
	 */
	PolyLineEdgeType getPolyLineEdge();

	/**
	 * Sets the value of the '{@link eu.synligare.sgraphml.DocumentRoot#getPolyLineEdge <em>Poly Line Edge</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Poly Line Edge</em>' containment reference.
	 * @see #getPolyLineEdge()
	 * @generated
	 */
	void setPolyLineEdge(PolyLineEdgeType value);

	/**
	 * Returns the value of the '<em><b>Port Node</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *         PortNode element definition.
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Port Node</em>' containment reference.
	 * @see #setPortNode(PortNodeType)
	 * @see eu.synligare.sgraphml.SgraphmlPackage#getDocumentRoot_PortNode()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='PortNode' namespace='##targetNamespace'"
	 * @generated
	 */
	PortNodeType getPortNode();

	/**
	 * Sets the value of the '{@link eu.synligare.sgraphml.DocumentRoot#getPortNode <em>Port Node</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Port Node</em>' containment reference.
	 * @see #getPortNode()
	 * @generated
	 */
	void setPortNode(PortNodeType value);

	/**
	 * Returns the value of the '<em><b>Resources</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *         Resource block element definition.
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Resources</em>' containment reference.
	 * @see #setResources(ResourceBlockType)
	 * @see eu.synligare.sgraphml.SgraphmlPackage#getDocumentRoot_Resources()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Resources' namespace='##targetNamespace'"
	 * @generated
	 */
	ResourceBlockType getResources();

	/**
	 * Sets the value of the '{@link eu.synligare.sgraphml.DocumentRoot#getResources <em>Resources</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resources</em>' containment reference.
	 * @see #getResources()
	 * @generated
	 */
	void setResources(ResourceBlockType value);

	/**
	 * Returns the value of the '<em><b>Scaled Icon</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *         ScaledIcon node element definition.
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Scaled Icon</em>' containment reference.
	 * @see #setScaledIcon(ScaledIconType)
	 * @see eu.synligare.sgraphml.SgraphmlPackage#getDocumentRoot_ScaledIcon()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ScaledIcon' namespace='##targetNamespace'"
	 * @generated
	 */
	ScaledIconType getScaledIcon();

	/**
	 * Sets the value of the '{@link eu.synligare.sgraphml.DocumentRoot#getScaledIcon <em>Scaled Icon</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scaled Icon</em>' containment reference.
	 * @see #getScaledIcon()
	 * @generated
	 */
	void setScaledIcon(ScaledIconType value);

	/**
	 * Returns the value of the '<em><b>Shape Node</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *         ShapeNode element definition.
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Shape Node</em>' containment reference.
	 * @see #setShapeNode(ShapeNodeType)
	 * @see eu.synligare.sgraphml.SgraphmlPackage#getDocumentRoot_ShapeNode()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ShapeNode' namespace='##targetNamespace'"
	 * @generated
	 */
	ShapeNodeType getShapeNode();

	/**
	 * Sets the value of the '{@link eu.synligare.sgraphml.DocumentRoot#getShapeNode <em>Shape Node</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Shape Node</em>' containment reference.
	 * @see #getShapeNode()
	 * @generated
	 */
	void setShapeNode(ShapeNodeType value);

} // DocumentRoot
