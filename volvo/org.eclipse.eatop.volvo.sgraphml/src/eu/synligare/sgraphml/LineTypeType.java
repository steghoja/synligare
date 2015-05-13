/**
 */
package eu.synligare.sgraphml;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Line Type Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 *         The valid line types for an edge or node.
 *         <p xmlns="http://www.synligare.eu/sgraphml">
 *           Valid values are:
 *           <ul>
 *             <li>
 *               <b>line</b>: for a continous line
 *             </li>
 *             <li>
 *               <b>dashed</b>: for a dashed line
 *             </li>
 *             <li>
 *               <b>dotted</b>: for a dotted line
 *             </li>
 *             <li>
 *               <b>dashed_dotted</b>: for a dash-dotted line
 *             </li>
 *           </ul>
 *         </p>
 *       
 * <!-- end-model-doc -->
 * @see eu.synligare.sgraphml.SgraphmlPackage#getLineTypeType()
 * @model extendedMetaData="name='lineType.type'"
 * @generated
 */
public enum LineTypeType implements Enumerator {
	/**
	 * The '<em><b>Line</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LINE_VALUE
	 * @generated
	 * @ordered
	 */
	LINE(0, "line", "line"),

	/**
	 * The '<em><b>Dashed</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DASHED_VALUE
	 * @generated
	 * @ordered
	 */
	DASHED(1, "dashed", "dashed"),

	/**
	 * The '<em><b>Dotted</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DOTTED_VALUE
	 * @generated
	 * @ordered
	 */
	DOTTED(2, "dotted", "dotted"),

	/**
	 * The '<em><b>Dashed Dotted</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DASHED_DOTTED_VALUE
	 * @generated
	 * @ordered
	 */
	DASHED_DOTTED(3, "dashedDotted", "dashed_dotted");

	/**
	 * The '<em><b>Line</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Line</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LINE
	 * @model name="line"
	 * @generated
	 * @ordered
	 */
	public static final int LINE_VALUE = 0;

	/**
	 * The '<em><b>Dashed</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Dashed</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DASHED
	 * @model name="dashed"
	 * @generated
	 * @ordered
	 */
	public static final int DASHED_VALUE = 1;

	/**
	 * The '<em><b>Dotted</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Dotted</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DOTTED
	 * @model name="dotted"
	 * @generated
	 * @ordered
	 */
	public static final int DOTTED_VALUE = 2;

	/**
	 * The '<em><b>Dashed Dotted</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Dashed Dotted</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DASHED_DOTTED
	 * @model name="dashedDotted" literal="dashed_dotted"
	 * @generated
	 * @ordered
	 */
	public static final int DASHED_DOTTED_VALUE = 3;

	/**
	 * An array of all the '<em><b>Line Type Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final LineTypeType[] VALUES_ARRAY =
		new LineTypeType[] {
			LINE,
			DASHED,
			DOTTED,
			DASHED_DOTTED,
		};

	/**
	 * A public read-only list of all the '<em><b>Line Type Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<LineTypeType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Line Type Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LineTypeType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LineTypeType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Line Type Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LineTypeType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LineTypeType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Line Type Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LineTypeType get(int value) {
		switch (value) {
			case LINE_VALUE: return LINE;
			case DASHED_VALUE: return DASHED;
			case DOTTED_VALUE: return DOTTED;
			case DASHED_DOTTED_VALUE: return DASHED_DOTTED;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private LineTypeType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //LineTypeType
