package org.eclipse.eatop.volvo.visualizer.common;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.eclipse.eatop.eastadl21.EAElement;
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.EAStringValue;
import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.Eastadl21Package;
import org.eclipse.eatop.eastadl21.Identifiable;
import org.eclipse.eatop.eastadl21.UserAttributeDefinition;
import org.eclipse.eatop.eastadl21.UserAttributedElement;
import org.eclipse.eatop.eastadl21.UserElementType;
import org.eclipse.eatop.volvo.modelprocessor.ModelProcessor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * Handles the visual tagging of elements
 * 
 * @author Joanna Svantesson
 *
 */
public class VisualTagsHandler {

	public static final String TAGS_PACKAGE_NAME = "VisualTagging"; 
	public static final String ELEMENTS_PACKAGE_NAME = "TaggedElements";
	public static final String TYPES_PACKAGE_NAME = "Types";
	public static final String ABSTRACTION_LEVEL = "AbstractionLevel";
	public static final String CATEGORY = "Category";

	protected EAXML root;
	protected EditingDomain ed;

	protected EAPackage tagsPackage, taggedElementsPackage, typesPackage;

	private int namingNr = 0;

	/**
	 * 
	 * @param root
	 * @return
	 */
	public EAPackage getTagsPackage(EAXML root) {
		EList<EAPackage> topLevelPackages = root.getTopLevelPackage();

		EAPackage tags = null;
		for (EAPackage p : topLevelPackages) {
			if (p.getShortName().startsWith(VisualTagsHandler.TAGS_PACKAGE_NAME)) {
				tags = p;
				break;
			}
		}

		return tags;
	}

	protected void createPackages(EditingDomain ed, EAXML root,String tagsPackageName) {
		tagsPackage = ModelProcessor.findPackageBeginningWithName(TAGS_PACKAGE_NAME, root);

		if (tagsPackage == null) {
			// Create visual tag package 
			tagsPackage = Eastadl21Factory.eINSTANCE.createEAPackage();
			tagsPackage.setShortName(tagsPackageName);
			Command addTagsPackage = AddCommand.create(ed, root, Eastadl21Package.EA_PACKAGE, tagsPackage);
			ed.getCommandStack().execute(addTagsPackage);

			taggedElementsPackage = Eastadl21Factory.eINSTANCE.createEAPackage();
			taggedElementsPackage.setShortName(ELEMENTS_PACKAGE_NAME);
			Command addElementsPackage = AddCommand.create(ed, tagsPackage, Eastadl21Package.EA_PACKAGE, taggedElementsPackage);
			ed.getCommandStack().execute(addElementsPackage);

			typesPackage = Eastadl21Factory.eINSTANCE.createEAPackage();
			typesPackage.setShortName(TYPES_PACKAGE_NAME);
			Command addTypesPackage = AddCommand.create(ed, tagsPackage, Eastadl21Package.EA_PACKAGE, typesPackage);
			ed.getCommandStack().execute(addTypesPackage);
		} else {
			taggedElementsPackage = ModelProcessor.findPackageWithName(ELEMENTS_PACKAGE_NAME, root);
		}
	}

	/**
	 * 
	 * @param e
	 * @param category
	 * @param level
	 */
	protected void createTagForElement(EObject e, String category, String level) {

		String shortName = "";
		if (e instanceof EAElement) {
			shortName = ((EAElement) e).getShortName();
			if (shortName == null || shortName.equals("")) {
				shortName = e.eClass().getName() + Integer.toString(namingNr);
				namingNr++;
			}
		}
		if (e instanceof EAPackage) shortName = ((EAPackage) e).getShortName();
		if (shortName == null || shortName.equals("")) {
			shortName = Integer.toString(namingNr);
			namingNr++;
		}

		// Create UserElementTypes for all elements
		UserElementType elementType = Eastadl21Factory.eINSTANCE.createUserElementType();
		elementType.setShortName(shortName+"_Type");
		Command addType = AddCommand.create(ed, typesPackage, Eastadl21Package.USER_ELEMENT_TYPE, elementType);
		ed.getCommandStack().execute(addType);

		// Create UserAttributeDefinition for visual abstraction level
		UserAttributeDefinition levelAttributeDef = Eastadl21Factory.eINSTANCE.createUserAttributeDefinition();
		levelAttributeDef.setShortName(ABSTRACTION_LEVEL);
		Command addLevelDef = AddCommand.create(ed, elementType, Eastadl21Package.USER_ATTRIBUTE_DEFINITION, levelAttributeDef);
		ed.getCommandStack().execute(addLevelDef);

		// Create abstraction level value for each element
		EAStringValue levelValue = Eastadl21Factory.eINSTANCE.createEAStringValue();
		levelValue.setValue(level);
		Command addLevelValue = AddCommand.create(ed, levelAttributeDef, Eastadl21Package.EA_NUMERICAL_VALUE, levelValue);
		ed.getCommandStack().execute(addLevelValue);

		// Create UserAttributeDefinition for visual category
		UserAttributeDefinition catAttributeDef = Eastadl21Factory.eINSTANCE.createUserAttributeDefinition();
		catAttributeDef.setShortName(CATEGORY);
		Command addCatDef = AddCommand.create(ed, elementType, Eastadl21Package.USER_ATTRIBUTE_DEFINITION, catAttributeDef);
		ed.getCommandStack().execute(addCatDef);

		// Create category value for each element
		EAStringValue categoryValue = Eastadl21Factory.eINSTANCE.createEAStringValue();
		categoryValue.setValue(category);
		Command addCatValue = AddCommand.create(ed, catAttributeDef, Eastadl21Package.EA_NUMERICAL_VALUE, categoryValue);
		ed.getCommandStack().execute(addCatValue);

		// Create Reference Element to connect visual tag and original element	
		UserAttributedElement uae = Eastadl21Factory.eINSTANCE.createUserAttributedElement();
		uae.setShortName(shortName+"_TaggedElement");
		uae.getUaType().add(elementType);

		uae.setAttributedElement((Identifiable) e);

		Command adduae = AddCommand.create(ed, taggedElementsPackage, Eastadl21Package.USER_ATTRIBUTED_ELEMENT, uae);
		ed.getCommandStack().execute(adduae);
	}

	/**
	 * Helper to check if the given package name includes any of the given names
	 * @param p The package to check
	 * @param names List of names to check for
	 * @return <code>true</code> if the package name includes any of the given names from <code>name</code>
	 */
	protected boolean packageNameContains(EAPackage p, ArrayList<String> names) {
		String packageName = p.getShortName();
		for (String name : names) {
			if (packageName.toLowerCase().contains(name.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets all the elements with visual tagging
	 * 
	 * @param tags The package with the visual tags
	 * @return A list with all the elements with visual tagging
	 */
	public List<EAElement> getAllElements(EAPackage tags) {
		ArrayList<EAElement> elements = new ArrayList<EAElement>();

		EAPackage taggedElementsPackage = null;

		for (EAPackage sp : tags.getSubPackage()) {
			if (sp.getShortName().equals(ELEMENTS_PACKAGE_NAME)) { 
				taggedElementsPackage = sp;
			} 
		}

		for (EAElement e : taggedElementsPackage.getElement()) { // for all elements in the TaggedElements package
			EAElement attrElem = (EAElement) ((UserAttributedElement)e).getAttributedElement();
			elements.add(attrElem);
		}

		return elements;
	}

	/**
	 * Gets all elements tagged with the abstraction level <code>level</code>
	 * 
	 * @param tags The package with the visual tags
	 * @param level 
	 * @return A list of all elements tagged with the abstraction level <code>level</code>
	 */
	public List<EAElement> getLevelElements(EAPackage tags, String level) {
		ArrayList<EAElement> elements = new ArrayList<EAElement>();

		EAPackage taggedElementsPackage = null;

		for (EAPackage sp : tags.getSubPackage()) {
			if (sp.getShortName().equals(ELEMENTS_PACKAGE_NAME)) { 
				taggedElementsPackage = sp;
			} 
		}

		for (EAElement e : taggedElementsPackage.getElement()) { // for all elements in the TaggedElements package
			// Get the ua type and check the abstraction level
			UserElementType type = ((UserAttributedElement)e).getUaType().get(0);

			for (UserAttributeDefinition uad : type.getUaDefinition()) {

				if (uad.getShortName().equals(ABSTRACTION_LEVEL)) {
					// Get the value of the abstraction level attribute
					// and add the UserAttributedElement if the value is 
					// equal to level
					String value = ""; 
					for (EObject o : uad.eContents()) {
						if (o instanceof EAStringValue) {
							value = ((EAStringValue)o).getValue();
						}
					}

					if (value.equals(level)) {
						EAElement attrElem = (EAElement) ((UserAttributedElement)e).getAttributedElement();
						elements.add(attrElem);
					}
				}
			}
		}

		return elements;
	}

	/**
	 * Gets all elements tagged with the category <code>category</code>
	 * 
	 * @param tags The package with the visual tags
	 * @param category 
	 * @return A list of all elements tagged with the category <code>category</code>
	 */
	public List<EAElement> getCategoryElements(EAPackage tags, String category) {
		ArrayList<EAElement> elements = new ArrayList<EAElement>();

		EAPackage taggedElementsPackage = null;

		for (EAPackage sp : tags.getSubPackage()) {
			if (sp.getShortName().equals(ELEMENTS_PACKAGE_NAME)) { 
				taggedElementsPackage = sp;
			} 
		}

		for (EAElement e : taggedElementsPackage.getElement()) { // for all elements in the TaggedElements package
			// Get the ua type and check the abstraction level
			UserElementType type = ((UserAttributedElement)e).getUaType().get(0);

			for (UserAttributeDefinition uad : type.getUaDefinition()) {

				if (uad.getShortName().equals(CATEGORY)) {
					// Get the value of the category attribute
					// and add the UserAttributedElement if the value is 
					// equal to category
					String value = ""; 
					for (EObject o : uad.eContents()) {
						if (o instanceof EAStringValue) {
							value = ((EAStringValue)o).getValue();
						}
					}

					if (value.equals(category)) {
						EAElement attrElem = (EAElement) ((UserAttributedElement)e).getAttributedElement();
						elements.add(attrElem);
					}
				}
			}
		}

		return elements;
	}

	/**
	 * Gets all elements tagged with the abstraction level <code>level</code> and the
	 * category <code>category</code>
	 * 
	 * @param tags The package with the visual tags
	 * @param category
	 * @param level 
	 * @return A list of all elements tagged with the abstraction level <code>level</code>
	 * and the category <code>category</code>
	 */
	public List<EAElement> getElements(EAPackage tags, String category, String level) {
		ArrayList<EAElement> elements = new ArrayList<EAElement>();
		boolean correctLevel = false;
		boolean correctCategory = false;

		EAPackage taggedElementsPackage = null;

		for (EAPackage sp : tags.getSubPackage()) {
			if (sp.getShortName().equals(ELEMENTS_PACKAGE_NAME)) { 
				taggedElementsPackage = sp;
			} 
		}

		for (EAElement e : taggedElementsPackage.getElement()) { // for all elements in the TaggedElements package
			// Get the ua type and check the abstraction level
			UserElementType type = ((UserAttributedElement)e).getUaType().get(0);

			for (UserAttributeDefinition uad : type.getUaDefinition()) {

				if (uad.getShortName().equals(ABSTRACTION_LEVEL)) {
					// Get the value of the abstraction level attribute
					// and check if the value is 
					// equal to level
					String value = ""; 
					for (EObject o : uad.eContents()) {
						if (o instanceof EAStringValue) {
							value = ((EAStringValue)o).getValue();
						}
					}

					if (value.equals(level)) {
						correctLevel = true;
					}
				}

				if (uad.getShortName().equals(CATEGORY)) {
					// Get the value of the category attribute
					// and add check if the value is 
					// equal to category
					String value = ""; 
					for (EObject o : uad.eContents()) {
						if (o instanceof EAStringValue) {
							value = ((EAStringValue)o).getValue();
						}
					}

					if (value.equals(category)) {
						correctCategory = true;
					}
				}
			}

			if (correctLevel && correctCategory) {
				EAElement attrElem = (EAElement) ((UserAttributedElement)e).getAttributedElement();
				elements.add(attrElem);
			}
			correctLevel = false;
			correctCategory = false;
		}

		return elements;
	}

	/**
	 * Gets all elements and returns them in a Hashtable together with their category and level
	 * 
	 * @param tags The package with the visual tags
	 * @return A Hashtable of all elements with the element as the key and an array with the
	 * category and level as the value. Note that the category will be at index 0 and the 
	 * level at index 1.
	 */
	public Hashtable<EAElement, String[]> getTableOfElements(EAPackage tags) {
		Hashtable<EAElement, String[]> elements = new Hashtable<EAElement, String[]>();

		EAPackage taggedElementsPackage = null;

		for (EAPackage sp : tags.getSubPackage()) {
			if (sp.getShortName().equals(ELEMENTS_PACKAGE_NAME)) { 
				taggedElementsPackage = sp;
			} 
		}

		for (EAElement e : taggedElementsPackage.getElement()) { // for all elements in the TaggedElements package
			String level = "";
			String category = "";

			// Get the ua type and check the abstraction level
			if (((UserAttributedElement)e).getUaType().size() < 1) {
				return null;
			}
			UserElementType type = ((UserAttributedElement)e).getUaType().get(0);
			for (UserAttributeDefinition uad : type.getUaDefinition()) {

				if (uad.getShortName().equals(ABSTRACTION_LEVEL)) {
					// Get the value of the abstraction level attribute
					for (EObject o : uad.eContents()) {
						if (o instanceof EAStringValue) {
							level = ((EAStringValue)o).getValue();
						}
					}
				}

				if (uad.getShortName().equals(CATEGORY)) {
					// Get the value of the category attribute
					for (EObject o : uad.eContents()) {
						if (o instanceof EAStringValue) {
							category = ((EAStringValue)o).getValue();
						}
					}
				}
			}

			String[] list = {category, level};
			// Add the element with its category and level to the table
			EAElement attrElem = (EAElement) ((UserAttributedElement)e).getAttributedElement();
			elements.put(attrElem, list);
		}

		return elements;
	}
}
