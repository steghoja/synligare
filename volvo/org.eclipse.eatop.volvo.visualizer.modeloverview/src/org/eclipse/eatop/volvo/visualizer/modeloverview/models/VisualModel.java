package org.eclipse.eatop.volvo.visualizer.modeloverview.models;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.eatop.volvo.visualizer.modeloverview.models.VisualRepresentation.VisualBackground;


/**
 * A visual model of an east-adl model
 * 
 * @author Joanna Svantesson
 *
 */
public class VisualModel {
	
	public final static String MODEL_FILE = "ModelFile";
	public final static String BACKGROUND = "Background";
	public final static String ELEMENT = "Element";
	public final static String ELEMENT_NAME = "name";
	public final static String PATH = "Path";
	public final static String CATEGORY = "Category";
	public final static String LEVEL = "Level";
	public final static String TYPE = "Type";
	
	private String title;
	private String modelFile;
	private VisualRepresentation representation;
	private List<VisualElement> elements; 
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getModelFile() {
		return modelFile;
	}

	public void setModelFile(String modelFile) {
		this.modelFile = modelFile;
	}

	public VisualBackground getBackground() {
		return representation.getBackground();
	}
	
	public VisualRepresentation getVisualRepresentation() {
		return representation;
	}
	
	public void setVisualRepresentation(VisualRepresentation representation) {
		this.representation = representation;
	}
	
	public List<VisualElement> getElements() {
		return elements;
	}

	public void setElements(List<VisualElement> elements) {
		this.elements = elements;
	}
	
	/**
	 * Returns a list of elements with the given category and level
	 * 
	 * @param category
	 * @param level
	 * @return A list of elements with the given category and level
	 */
	public List<VisualElement> getElements(String category, String level) {
		List<VisualElement> result = new ArrayList<VisualElement>();
		for (VisualElement e : elements) {
			if (e.getCategory().equals(category) && e.getLevel().equals(level)) {
				result.add(e);
			}
		}
		return result;
	}
	
	/**
	 * Returns a list of elements with the given category, level and zPos
	 * 
	 * @param category
	 * @param level
	 * @param zPos
	 * @return A list of elements with the given category, level and zPos
	 */
	public List<VisualElement> getElements(String category, String level, int zPos) {
		
		List<VisualElement> result = new ArrayList<VisualElement>();
		for (VisualElement e : elements) {
			if (e.getCategory().equals(category) && e.getLevel().equals(level)
					&& representation.getElementRepresentation(e).getShape().getZPos() == zPos) {
				result.add(e);
			}
		}
		return result;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("VisualModel of model file " + modelFile + 
				" with visual representation " + representation.getPath() +
				" and elements: \n");
		for (VisualElement e : elements) {
			sb.append(e.toString());
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * A class that represents a visual element in the visual model
	 *
	 */
	public class VisualElement {
		private String path;
		private String name;
		private String category;
		private String level;
		private String type;
		
		public String getPath() {
			return path;
		}
		
		public void setPath(String path) {
			this.path = path;
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public String getLevel() {
			return level;
		}

		public void setLevel(String level) {
			this.level = level;
		}
		
		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
		
		public String toString() {
			return "VisualElement " + name + " [" + path + "] with category "
					+ category + " and level " + level + "\n";
		}
	}
}
