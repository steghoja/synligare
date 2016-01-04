/**
 */
package eu.synligare.sgraphml;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Placement Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 *         The valid placements for labels.
 *         <p xmlns="http://www.synligare.eu/sgraphml">
 *           Valid values are:
 *           <ul>
 *             <li>
 *               <b>top</b>: internal top placement 
 *             </li>
 *             <li>
 *               <b>bottom</b>: internal bottom placement 
 *             </li>
 *             <li>
 *               <b>center</b>: internal centered placement
 *             </li>
 *             <li>
 *               <b>left</b>: internal left placement
 *             </li>
 *             <li>
 *               <b>right</b>: internal right placement
 *             </li>
 *             <li>
 *               <b>free</b>: any placement specified by x and y attributes
 *             </li>
 * 
 *         </ul>
 *         </p>
 *       
 * <!-- end-model-doc -->
 * @see eu.synligare.sgraphml.SgraphmlPackage#getPlacementType()
 * @model extendedMetaData="name='placement.type'"
 * @generated
 */
public enum PlacementType implements Enumerator {
	/**
	 * The '<em><b>Top</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TOP_VALUE
	 * @generated
	 * @ordered
	 */
	TOP(0, "top", "top"),

	/**
	 * The '<em><b>Bottom</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BOTTOM_VALUE
	 * @generated
	 * @ordered
	 */
	BOTTOM(1, "bottom", "bottom"),

	/**
	 * The '<em><b>Center</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CENTER_VALUE
	 * @generated
	 * @ordered
	 */
	CENTER(2, "center", "center"),

	/**
	 * The '<em><b>Left</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LEFT_VALUE
	 * @generated
	 * @ordered
	 */
	LEFT(3, "left", "left"),

	/**
	 * The '<em><b>Right</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RIGHT_VALUE
	 * @generated
	 * @ordered
	 */
	RIGHT(4, "right", "right"),

	/**
	 * The '<em><b>Free</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FREE_VALUE
	 * @generated
	 * @ordered
	 */
	FREE(5, "free", "free");

	/**
	 * The '<em><b>Top</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Top</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TOP
	 * @model name="top"
	 * @generated
	 * @ordered
	 */
	public static final int TOP_VALUE = 0;

	/**
	 * The '<em><b>Bottom</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Bottom</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BOTTOM
	 * @model name="bottom"
	 * @generated
	 * @ordered
	 */
	public static final int BOTTOM_VALUE = 1;

	/**
	 * The '<em><b>Center</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Center</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CENTER
	 * @model name="center"
	 * @generated
	 * @ordered
	 */
	public static final int CENTER_VALUE = 2;

	/**
	 * The '<em><b>Left</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Left</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LEFT
	 * @model name="left"
	 * @generated
	 * @ordered
	 */
	public static final int LEFT_VALUE = 3;

	/**
	 * The '<em><b>Right</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Right</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RIGHT
	 * @model name="right"
	 * @generated
	 * @ordered
	 */
	public static final int RIGHT_VALUE = 4;

	/**
	 * The '<em><b>Free</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Free</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FREE
	 * @model name="free"
	 * @generated
	 * @ordered
	 */
	public static final int FREE_VALUE = 5;

	/**
	 * An array of all the '<em><b>Placement Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final PlacementType[] VALUES_ARRAY =
		new PlacementType[] {
			TOP,
			BOTTOM,
			CENTER,
			LEFT,
			RIGHT,
			FREE,
		};

	/**
	 * A public read-only list of all the '<em><b>Placement Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<PlacementType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Placement Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PlacementType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PlacementType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Placement Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PlacementType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PlacementType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Placement Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PlacementType get(int value) {
		switch (value) {
			case TOP_VALUE: return TOP;
			case BOTTOM_VALUE: return BOTTOM;
			case CENTER_VALUE: return CENTER;
			case LEFT_VALUE: return LEFT;
			case RIGHT_VALUE: return RIGHT;
			case FREE_VALUE: return FREE;
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
	private PlacementType(int value, String name, String literal) {
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
	
} //PlacementType
