/**
 */
package org.graphdrawing.graphml.xmlns;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Extension Type1</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.graphdrawing.graphml.xmlns.DataExtensionType1#getAny <em>Any</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getDataExtensionType1()
 * @model extendedMetaData="name='data-extension.type' kind='mixed'"
 * @generated
 */
public interface DataExtensionType1 extends DataExtensionType {
	/**
	 * Returns the value of the '<em><b>Any</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Any</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Any</em>' attribute list.
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getDataExtensionType1_Any()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='elementWildcard' wildcards='##any' name=':1' processing='strict'"
	 * @generated
	 */
	FeatureMap getAny();

} // DataExtensionType1
