package org.eclipse.eatop.volvo.visualizer.modeloverview.models;

import java.util.List;

import org.eclipse.eatop.volvo.visualizer.common.Constants;
import org.eclipse.eatop.volvo.visualizer.common.graphicalmodels.Shape;
import org.eclipse.eatop.volvo.visualizer.modeloverview.models.VisualModel.VisualElement;

/**
 * 
 * @author Joanna Svantesson
 *
 */
public class VisualRepresentation {

	public final static String DEFAULT_REPRESENTATION_PATH = "resources/visual_representation.xml";
		
	private String path;
	private VisualBackground background;
	private List<ElementRepresentation> elementRepresentations;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public VisualBackground getBackground() {
		return background;
	}

	public void setBackground(VisualBackground background) {
		this.background = background;
	}

	public List<ElementRepresentation> getElementRepresentations() {
		return elementRepresentations;
	}

	public void setElementRepresentations(List<ElementRepresentation> elementRepresentations) {
		this.elementRepresentations = elementRepresentations;
	}

	public ElementRepresentation getElementRepresentation(VisualElement element) {
		ElementRepresentation elementRepresentation = null;
		for (ElementRepresentation er : elementRepresentations) {
			for (String t : er.getTypes()) {
				if (t.equals(element.getType())) {
					elementRepresentation = er;
					break;
				} else if (t.equals("default")) {
					elementRepresentation = er;
				}
			}
		}
		return elementRepresentation;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("Visual Representation: \n");
		sb.append(background.toString() + "\n");
		for (ElementRepresentation e : elementRepresentations) {
			sb.append(e.toString() + "\n");
		}
		return sb.toString();
	}
	
	/**
	 * Class representing a graphical background of a model visualization
	 * 
	 * @author Joanna Svantesson
	 *
	 */
	public class VisualBackground {

		private List<Category> categories;
		private List<Level> levels;

		public List<Category> getCategories() {
			return categories;
		}

		public void setCategories(List<Category> categories) {
			this.categories = categories;
		}

		public List<Level> getLevels() {
			return levels;
		}

		public void setLevels(List<Level> levels) {
			this.levels = levels;
		}

		/**
		 * Returns the category with the given name or null if no such category were found.
		 * 
		 * @param category The name of the category to get
		 * @return The found category or null
		 */
		public Category getCategory(String category) {
			for (Category c : categories) {
				if (c.getName().equals(category)) {
					return c;
				}
			}
			return null;
		}

		/**
		 * Returns the level with the given name or null if no such level were found.
		 * 
		 * @param level The name of the level to get
		 * @return The found level or null
		 */
		public Level getLevel(String level) {
			for (Level l : levels) {
				if (l.getName().equals(level)) {
					return l;
				}
			}
			return null;
		}

		public String toString() {
			StringBuilder sb = new StringBuilder("Background ");
			for (Category c : categories) {
				sb.append(c.toString() + "\n");
			}
			for (Level l : levels) {
				sb.append(l.toString() + "\n");
			}
			return sb.toString();
		}

		/**
		 * Super class for categories and levels. Not intended to be instanced. 
		 * 
		 */
		public class SuperCategory {
			protected String name;
			protected Shape shape;

			protected boolean title;
			protected boolean label;
			protected boolean tooltip;
			
			public boolean isTitle() {
				return title;
			}

			public void setTitle(boolean title) {
				this.title = title;
			}

			public boolean hasLabel() {
				return label;
			}

			public void setLabel(boolean label) {
				this.label = label;
			}

			public boolean hasTooltip() {
				return tooltip;
			}

			public void setTooltip(boolean tooltip) {
				this.tooltip = tooltip;
			}
			
			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public Shape getShape() {
				return shape;
			}

			public void setShape(Shape shape) {
				this.shape = shape;
			}

		}

		/**
		 * 
		 * Class representing a category in a graphical background
		 * 
		 */
		public class Category extends SuperCategory { 

			public String toString() {
				return "Category " + name + " " + shape.toString();
			}
		}

		/**
		 * 
		 * Class representing a level in a graphical background
		 *
		 */
		public class Level extends SuperCategory {

			public String toString() {
				return "Level " + name + " " + shape.toString();
			}
		}

	}

	/**
	 * Class describing the way one or several types of elements are represented visually
	 * 
	 * @author Joanna Svantesson
	 *
	 */
	public class ElementRepresentation {
		
		protected boolean label;
		protected boolean tooltip;
		
		private String placement;
		private String direction;
		private boolean overlapping;
		private List<String> types;
		private Shape shape;

		public ElementRepresentation() {
			// Some values are allowed to be unspecified in xml file
			// Set these to default values
			placement = Constants.TOPLEFT;
			direction = Constants.DIAGONAL;
			overlapping = true;
		}
		
		public boolean hasLabel() {
			return label;
		}

		public void setLabel(boolean label) {
			this.label = label;
		}

		public boolean hasTooltip() {
			return tooltip;
		}

		public void setTooltip(boolean tooltip) {
			this.tooltip = tooltip;
		}

		public String getPlacement() {
			return placement;
		}

		public void setPlacement(String placement) {
			this.placement = placement;
		}
		
		public String getDirection() {
			return direction;
		}

		public void setDirection(String direction) {
			this.direction = direction;
		}
		
		public boolean getOverlapping() {
			return overlapping;
		}

		public void setOverlapping(boolean overlapping) {
			this.overlapping = overlapping;
		}
		
		public List<String> getTypes() {
			return types;
		}

		public void setTypes(List<String> types) {
			this.types = types;
		}

		public Shape getShape() {
			return shape;
		}

		public void setShape(Shape shape) {
			this.shape = shape;
		}
		
		public String toString() {
			StringBuilder sb = new StringBuilder("ElementRepresentation for types: \n");
			for (String s : types) {
				sb.append(s + "\n");
			}
			sb.append("with shape " + shape.toString());
			return sb.toString();
		}
	}

}

