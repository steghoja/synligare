package org.eclipse.eatop.examples.explorer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.geastadl.ginfrastructure.gelements.GIdentifiable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

public class CategorizationNode implements IEObjectContainer {

	private EClass type;
	private GIdentifiable definition;
	private Object parent;

	public CategorizationNode(Object parent, EClass type) {
		this(parent, type, null);
	}

	public CategorizationNode(Object parent, EClass type, GIdentifiable definition) {
		this.type = type;
		this.parent = parent;
		this.definition = definition;
	}

	public static CategorizationNode create(Object parent, EClass type) {
		return create(parent, type, null);
	}

	public static CategorizationNode create(Object parent, EClass type, GIdentifiable definition) {
		CategorizationNode node = new CategorizationNode(parent, type, definition);
		return node;
	}

	public Object[] getChildren() {
		List<Object> children = new ArrayList<Object>();
		if (parent instanceof EObject) {
			EObject eo = (EObject) parent;
			for (EObject eChild : eo.eContents()) {
				if (eChild.eClass() == type && !(eChild instanceof EAPackage)) {
					children.add(eChild);
				}
			}
		}

		return children.toArray();
	}

	public String getName() {
		String name = type.getName();
		if (!name.endsWith("s")) { //$NON-NLS-1$
			name += "s"; //$NON-NLS-1$
		}
		if (definition != null) {
			name += " (" + definition.gGetShortName() + ")"; //$NON-NLS-1$//$NON-NLS-2$
		}
		name += " [" + Integer.toString(getChildren().length) + " items]"; //$NON-NLS-1$//$NON-NLS-2$
		return name;
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public Iterable<EObject> getObjects() {
		List<EObject> lst = new ArrayList<EObject>();
		for (Object o : getChildren()) {
			lst.add((EObject) o);
		}
		return lst;
	}

	public Object getParent() {
		return parent;
	}

	/*
	 * hashCode() and equals() overriden with auto-generated functions. Lets the tree viewer controls correctly control
	 * expanded/collapsed state of these nodes
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (parent == null ? 0 : parent.hashCode());
		result = prime * result + (type == null ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CategorizationNode other = (CategorizationNode) obj;
		if (parent == null) {
			if (other.parent != null) {
				return false;
			}
		} else if (!parent.equals(other.parent)) {
			return false;
		}
		if (type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!type.equals(other.type)) {
			return false;
		}
		return true;
	}
}
