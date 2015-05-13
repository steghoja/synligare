/**
 */
package eu.synligare.sgraphml;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Path Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 *         Attribute group that describes a two-dimensional path, consisting of a point list together with optional source
 *         and target locations.
 *         <p xmlns="http://www.synligare.eu/sgraphml">
 *           The source resp. target location is specified by the sx and sy resp. tx and ty attributes.
 *         </p>
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.synligare.sgraphml.PathType#getPoint <em>Point</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.PathType#getSx <em>Sx</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.PathType#getSy <em>Sy</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.PathType#getTx <em>Tx</em>}</li>
 *   <li>{@link eu.synligare.sgraphml.PathType#getTy <em>Ty</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.synligare.sgraphml.SgraphmlPackage#getPathType()
 * @model extendedMetaData="name='Path.type' kind='elementOnly'"
 * @generated
 */
public interface PathType extends EObject {
	/**
	 * Returns the value of the '<em><b>Point</b></em>' containment reference list.
	 * The list contents are of type {@link eu.synligare.sgraphml.PointType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             Single control point for two dimensional paths.
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Point</em>' containment reference list.
	 * @see eu.synligare.sgraphml.SgraphmlPackage#getPathType_Point()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='Point' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<PointType> getPoint();

	/**
	 * Returns the value of the '<em><b>Sx</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *           The x coordinate of the source point, relative to the center of the start node.
	 *         
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Sx</em>' attribute.
	 * @see #isSetSx()
	 * @see #unsetSx()
	 * @see #setSx(double)
	 * @see eu.synligare.sgraphml.SgraphmlPackage#getPathType_Sx()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Double"
	 *        extendedMetaData="kind='attribute' name='sx'"
	 * @generated
	 */
	double getSx();

	/**
	 * Sets the value of the '{@link eu.synligare.sgraphml.PathType#getSx <em>Sx</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sx</em>' attribute.
	 * @see #isSetSx()
	 * @see #unsetSx()
	 * @see #getSx()
	 * @generated
	 */
	void setSx(double value);

	/**
	 * Unsets the value of the '{@link eu.synligare.sgraphml.PathType#getSx <em>Sx</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSx()
	 * @see #getSx()
	 * @see #setSx(double)
	 * @generated
	 */
	void unsetSx();

	/**
	 * Returns whether the value of the '{@link eu.synligare.sgraphml.PathType#getSx <em>Sx</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Sx</em>' attribute is set.
	 * @see #unsetSx()
	 * @see #getSx()
	 * @see #setSx(double)
	 * @generated
	 */
	boolean isSetSx();

	/**
	 * Returns the value of the '<em><b>Sy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *           The y coordinate of the source point, relative to the center of the start node.
	 *         
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Sy</em>' attribute.
	 * @see #isSetSy()
	 * @see #unsetSy()
	 * @see #setSy(double)
	 * @see eu.synligare.sgraphml.SgraphmlPackage#getPathType_Sy()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Double"
	 *        extendedMetaData="kind='attribute' name='sy'"
	 * @generated
	 */
	double getSy();

	/**
	 * Sets the value of the '{@link eu.synligare.sgraphml.PathType#getSy <em>Sy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sy</em>' attribute.
	 * @see #isSetSy()
	 * @see #unsetSy()
	 * @see #getSy()
	 * @generated
	 */
	void setSy(double value);

	/**
	 * Unsets the value of the '{@link eu.synligare.sgraphml.PathType#getSy <em>Sy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSy()
	 * @see #getSy()
	 * @see #setSy(double)
	 * @generated
	 */
	void unsetSy();

	/**
	 * Returns whether the value of the '{@link eu.synligare.sgraphml.PathType#getSy <em>Sy</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Sy</em>' attribute is set.
	 * @see #unsetSy()
	 * @see #getSy()
	 * @see #setSy(double)
	 * @generated
	 */
	boolean isSetSy();

	/**
	 * Returns the value of the '<em><b>Tx</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *           The x coordinate of the target point, relative to the center of the start node.
	 *         
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Tx</em>' attribute.
	 * @see #isSetTx()
	 * @see #unsetTx()
	 * @see #setTx(double)
	 * @see eu.synligare.sgraphml.SgraphmlPackage#getPathType_Tx()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Double"
	 *        extendedMetaData="kind='attribute' name='tx'"
	 * @generated
	 */
	double getTx();

	/**
	 * Sets the value of the '{@link eu.synligare.sgraphml.PathType#getTx <em>Tx</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tx</em>' attribute.
	 * @see #isSetTx()
	 * @see #unsetTx()
	 * @see #getTx()
	 * @generated
	 */
	void setTx(double value);

	/**
	 * Unsets the value of the '{@link eu.synligare.sgraphml.PathType#getTx <em>Tx</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTx()
	 * @see #getTx()
	 * @see #setTx(double)
	 * @generated
	 */
	void unsetTx();

	/**
	 * Returns whether the value of the '{@link eu.synligare.sgraphml.PathType#getTx <em>Tx</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Tx</em>' attribute is set.
	 * @see #unsetTx()
	 * @see #getTx()
	 * @see #setTx(double)
	 * @generated
	 */
	boolean isSetTx();

	/**
	 * Returns the value of the '<em><b>Ty</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *           The y coordinate of the target point, relative to the center of the start node.
	 *         
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Ty</em>' attribute.
	 * @see #isSetTy()
	 * @see #unsetTy()
	 * @see #setTy(double)
	 * @see eu.synligare.sgraphml.SgraphmlPackage#getPathType_Ty()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Double"
	 *        extendedMetaData="kind='attribute' name='ty'"
	 * @generated
	 */
	double getTy();

	/**
	 * Sets the value of the '{@link eu.synligare.sgraphml.PathType#getTy <em>Ty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ty</em>' attribute.
	 * @see #isSetTy()
	 * @see #unsetTy()
	 * @see #getTy()
	 * @generated
	 */
	void setTy(double value);

	/**
	 * Unsets the value of the '{@link eu.synligare.sgraphml.PathType#getTy <em>Ty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTy()
	 * @see #getTy()
	 * @see #setTy(double)
	 * @generated
	 */
	void unsetTy();

	/**
	 * Returns whether the value of the '{@link eu.synligare.sgraphml.PathType#getTy <em>Ty</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Ty</em>' attribute is set.
	 * @see #unsetTy()
	 * @see #getTy()
	 * @see #setTy(double)
	 * @generated
	 */
	boolean isSetTy();

} // PathType
