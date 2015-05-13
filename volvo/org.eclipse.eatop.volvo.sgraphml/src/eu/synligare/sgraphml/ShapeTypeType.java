/**
 */
package eu.synligare.sgraphml;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Shape Type Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 *         non-rectangular shape types
 *         <p xmlns="http://www.synligare.eu/sgraphml">
 *           Valid values are:
 *           <ul>
 *             <li>
 *               <b>rectangle</b>
 *               : for a rectangular shape
 *             </li>
 *             <li>
 *               <b>roundrectangle</b>
 *               : for a rectangular shape with rounded corners
 *             </li>
 *             <li>
 *               <b>cutrectangle</b>
 *               : for a rectangular shape with cut corners
 *             </li>
 *             <li>
 *               <b>ellipse</b>
 *               : for an elliptical shape
 *             </li>
 *             <li>
 *               <b>triangle</b>
 *               : for a triangular shape
 *             </li>
 *           </ul>
 *         </p>
 *       
 * <!-- end-model-doc -->
 * @see eu.synligare.sgraphml.SgraphmlPackage#getShapeTypeType()
 * @model extendedMetaData="name='shapeType.type'"
 * @generated
 */
public enum ShapeTypeType implements Enumerator {
	/**
	 * The '<em><b>Rectangle</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RECTANGLE_VALUE
	 * @generated
	 * @ordered
	 */
	RECTANGLE(0, "rectangle", "rectangle"),

	/**
	 * The '<em><b>Roundrectangle</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ROUNDRECTANGLE_VALUE
	 * @generated
	 * @ordered
	 */
	ROUNDRECTANGLE(1, "roundrectangle", "roundrectangle"),

	/**
	 * The '<em><b>Cutrectangle</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CUTRECTANGLE_VALUE
	 * @generated
	 * @ordered
	 */
	CUTRECTANGLE(2, "cutrectangle", "cutrectangle"),

	/**
	 * The '<em><b>Ellipse</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ELLIPSE_VALUE
	 * @generated
	 * @ordered
	 */
	ELLIPSE(3, "ellipse", "ellipse"),

	/**
	 * The '<em><b>Triangle</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TRIANGLE_VALUE
	 * @generated
	 * @ordered
	 */
	TRIANGLE(4, "triangle", "triangle");

	/**
	 * The '<em><b>Rectangle</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Rectangle</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RECTANGLE
	 * @model name="rectangle"
	 * @generated
	 * @ordered
	 */
	public static final int RECTANGLE_VALUE = 0;

	/**
	 * The '<em><b>Roundrectangle</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Roundrectangle</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ROUNDRECTANGLE
	 * @model name="roundrectangle"
	 * @generated
	 * @ordered
	 */
	public static final int ROUNDRECTANGLE_VALUE = 1;

	/**
	 * The '<em><b>Cutrectangle</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Cutrectangle</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CUTRECTANGLE
	 * @model name="cutrectangle"
	 * @generated
	 * @ordered
	 */
	public static final int CUTRECTANGLE_VALUE = 2;

	/**
	 * The '<em><b>Ellipse</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Ellipse</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ELLIPSE
	 * @model name="ellipse"
	 * @generated
	 * @ordered
	 */
	public static final int ELLIPSE_VALUE = 3;

	/**
	 * The '<em><b>Triangle</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Triangle</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TRIANGLE
	 * @model name="triangle"
	 * @generated
	 * @ordered
	 */
	public static final int TRIANGLE_VALUE = 4;

	/**
	 * An array of all the '<em><b>Shape Type Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ShapeTypeType[] VALUES_ARRAY =
		new ShapeTypeType[] {
			RECTANGLE,
			ROUNDRECTANGLE,
			CUTRECTANGLE,
			ELLIPSE,
			TRIANGLE,
		};

	/**
	 * A public read-only list of all the '<em><b>Shape Type Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ShapeTypeType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Shape Type Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ShapeTypeType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ShapeTypeType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Shape Type Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ShapeTypeType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ShapeTypeType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Shape Type Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ShapeTypeType get(int value) {
		switch (value) {
			case RECTANGLE_VALUE: return RECTANGLE;
			case ROUNDRECTANGLE_VALUE: return ROUNDRECTANGLE;
			case CUTRECTANGLE_VALUE: return CUTRECTANGLE;
			case ELLIPSE_VALUE: return ELLIPSE;
			case TRIANGLE_VALUE: return TRIANGLE;
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
	private ShapeTypeType(int value, String name, String literal) {
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
	
} //ShapeTypeType
