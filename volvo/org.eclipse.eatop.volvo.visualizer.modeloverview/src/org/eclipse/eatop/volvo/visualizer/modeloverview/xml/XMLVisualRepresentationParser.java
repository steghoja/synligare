package org.eclipse.eatop.volvo.visualizer.modeloverview.xml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.swt.graphics.Color;
import org.eclipse.eatop.volvo.visualizer.common.Activator;
import org.eclipse.eatop.volvo.visualizer.common.Constants;
import org.eclipse.eatop.volvo.visualizer.common.graphicalmodels.Shape;
import org.eclipse.eatop.volvo.visualizer.common.graphicalmodels.Shape.Box;
import org.eclipse.eatop.volvo.visualizer.common.graphicalmodels.Shape.Line;
import org.eclipse.eatop.volvo.visualizer.modeloverview.models.VisualRepresentation;
import org.eclipse.eatop.volvo.visualizer.modeloverview.models.VisualRepresentation.ElementRepresentation;
import org.eclipse.eatop.volvo.visualizer.modeloverview.models.VisualRepresentation.VisualBackground;
import org.eclipse.eatop.volvo.visualizer.modeloverview.models.VisualRepresentation.VisualBackground.Category;
import org.eclipse.eatop.volvo.visualizer.modeloverview.models.VisualRepresentation.VisualBackground.Level;
import org.eclipse.eatop.volvo.visualizer.modeloverview.models.VisualRepresentation.VisualBackground.SuperCategory;

/**
 * A parser for visual representation xml files
 * 
 * @author Joanna Svantesson
 *
 */
public class XMLVisualRepresentationParser {

	/**
	 * 
	 * @param representationFile Name or path to a file
	 * @param getPath If true the method will get the path fort the given file using 
	 * 		  <code>Activator.getDefault().getBundle().getEntry(representationFile)</code>.
	 * 		  Set this parameter to false if you already have the full path.
	 * @return A <code>VisualRepresentation</code> read from the given file
	 */
	@SuppressWarnings({ "unchecked" })
	public static VisualRepresentation readRepresentation(String representationFile) {
		if (representationFile.equals("")) return null;
		String path = representationFile;

		if (path.startsWith("/")) {
			path = path.substring(1);
		}

		// Checks if the path has the format C:.... (An absolute path)
		// If no we assume that we need to get the path in the following way:
		if (!(Character.isLetter(path.charAt(0)) && path.charAt(1) == ':')) {
			// We don't have an absolute path
			// Get the path to representation file
			URL url = Activator.getDefault().getBundle().getEntry(representationFile);
			if (url == null) return null;
			URL representationUrl;
			try {
				representationUrl = FileLocator.toFileURL(url);
				path = representationUrl.getPath();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		VisualRepresentation representation = new VisualRepresentation();

		VisualBackground background = representation.new VisualBackground();		
		representation.setPath(representationFile);
		List<Category> categories = new ArrayList<Category>();
		List<Level> levels = new ArrayList<Level>();
		boolean isBackground = false;

		List<ElementRepresentation> elementRepresentations = new ArrayList<ElementRepresentation>();
		boolean isElement = false;
		ElementRepresentation element = null;
		List<String> types = null;

		SuperCategory cat = null;
		Shape shape = null;

		try {
			// First, create a new XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();

			// Setup a new eventReader
			InputStream in = new FileInputStream(path);
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

			// read the XML document
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();

				if (event.isStartElement()) {

					if (event.asStartElement().getName().getLocalPart().equals(Constants.BACKGROUND)) {
						isBackground = true;
						continue;
					}

					if (event.asStartElement().getName().getLocalPart().equals(Constants.ELEMENT)) {
						isElement = true;
						element = representation.new ElementRepresentation();
						types = new ArrayList<String>();
						continue;
					}

					StartElement startElement = event.asStartElement();
					String elemKind = startElement.getName().getLocalPart();
					// If we have a category element, we create a new category
					if (elemKind.equals(Constants.CATEGORY) 
							|| elemKind.equals(Constants.LEVEL)) {
						if (elemKind.equals(Constants.CATEGORY)) {
							cat = background.new Category();
						} else {
							cat = background.new Level();
						}

						// We read the attributes from this tag and add the name
						Iterator<Attribute> attributes = startElement
								.getAttributes();
						while (attributes.hasNext()) {
							Attribute attribute = attributes.next();
							if (attribute.getName().toString().equals(Constants.NAME)) {
								cat.setName(attribute.getValue());
							}

						}
						continue;
					}

					if (event.asStartElement().getName().getLocalPart().equals(Constants.BOX)) {
						shape = new Shape().new Box();
						continue;
					}

					if (event.asStartElement().getName().getLocalPart().equals(Constants.LINE)) {
						shape = new Shape().new Line();
						continue;
					}

					if (event.asStartElement().getName().getLocalPart().equals(Constants.START)) {

						// We read the attributes from this tag and add the x and y values
						Iterator<Attribute> attributes = event.asStartElement().getAttributes();
						while (attributes.hasNext()) {
							Attribute attribute = attributes.next();
							if (attribute.getName().toString().equals(Constants.X)) {
								int value = Integer.parseInt(attribute.getValue());
								((Line)shape).setStartX(value);
								continue;
							}
							if (attribute.getName().toString().equals(Constants.Y)) {
								int value = Integer.parseInt(attribute.getValue());
								((Line)shape).setStartY(value);
								continue;
							}
						}
						continue;
					}

					// TODO fix checks if Line or Box etc to avoid exceptions if badly formatted xml
					if (event.asStartElement().getName().getLocalPart().equals(Constants.END)) {

						// We read the attributes from this tag and add the x and y values
						Iterator<Attribute> attributes = event.asStartElement().getAttributes();
						while (attributes.hasNext()) {
							Attribute attribute = attributes.next();
							if (attribute.getName().toString().equals(Constants.X)) {
								int value = Integer.parseInt(attribute.getValue());
								((Line)shape).setEndX(value);
								continue;
							}
							if (attribute.getName().toString().equals(Constants.Y)) {
								int value = Integer.parseInt(attribute.getValue());
								((Line)shape).setEndY(value);
								continue;
							}
						}
						continue;
					}

					if (event.asStartElement().getName().getLocalPart().equals(Constants.XPOS)) {
						event = eventReader.nextEvent();
						int value = Integer.valueOf(event.asCharacters().getData()); 
						if (shape instanceof Box) {
							((Box)shape).setXPos(value);
						}
						continue;
					}

					if (event.asStartElement().getName().getLocalPart().equals(Constants.YPOS)) {
						event = eventReader.nextEvent();
						int value = Integer.valueOf(event.asCharacters().getData()); 
						if (shape instanceof Box) {
							((Box)shape).setYPos(value);
						}
						continue;
					}

					if (event.asStartElement().getName().getLocalPart().equals(Constants.ZPOS)) {
						event = eventReader.nextEvent();
						int value = Integer.valueOf(event.asCharacters().getData()); 
						shape.setZPos(value);
						continue;
					}

					if (event.asStartElement().getName().getLocalPart().equals(Constants.SIZE)) {

						// We read the attributes from this tag and add the x and y values
						Iterator<Attribute> attributes = event.asStartElement().getAttributes();
						while (attributes.hasNext()) {
							Attribute attribute = attributes.next();
							if (attribute.getName().toString().equals(Constants.X)) {
								int value = Integer.parseInt(attribute.getValue());
								((Box)shape).setXSize(value);
								continue;
							}
							if (attribute.getName().toString().equals(Constants.Y)) {
								int value = Integer.parseInt(attribute.getValue());
								((Box)shape).setYSize(value);
								continue;
							}
						}
						continue;
					}

					if (event.asStartElement().getName().getLocalPart().equals(Constants.COLOR)) {						
						int color_r = 0;
						int color_g = 0;
						int color_b = 0;

						// We read the attributes from this tag and add the r, g, b values
						Iterator<Attribute> attributes = event.asStartElement().getAttributes();
						while (attributes.hasNext()) {
							Attribute attribute = attributes.next();
							if (attribute.getName().toString().equals(Constants.R)) {
								color_r = Integer.parseInt(attribute.getValue());
								continue;
							}
							if (attribute.getName().toString().equals(Constants.G)) {
								color_g = Integer.parseInt(attribute.getValue());
								continue;
							}
							if (attribute.getName().toString().equals(Constants.B)) {
								color_b = Integer.parseInt(attribute.getValue());
								continue;
							}
						}
						if (shape != null) 
							shape.setColor(new Color(null, color_r, color_g, color_b));
						continue;
					}

					if (event.asStartElement().getName().getLocalPart().equals(Constants.ALIGNMENT)) {						
						if (element != null) {

							// We read the attributes from this tag and add the placement, 
							// direction and overlapping values
							Iterator<Attribute> attributes = event.asStartElement().getAttributes();
							while (attributes.hasNext()) {
								Attribute attribute = attributes.next();
								if (attribute.getName().toString().equals(Constants.PLACEMENT)) {
									element.setPlacement(attribute.getValue().toLowerCase());
									continue;
								}
								if (attribute.getName().toString().equals(Constants.DIRECTION)) {
									element.setDirection(attribute.getValue().toLowerCase());
									continue;
								}
								if (attribute.getName().toString().equals(Constants.OVERLAPPING)) {
									boolean value = Boolean.parseBoolean(attribute.getValue());
									element.setOverlapping(value);
									continue;
								}
							}
						}
						continue;
					}

					if (event.asStartElement().getName().getLocalPart().equals(Constants.TITLE)) {
						event = eventReader.nextEvent();
						boolean value = Boolean.parseBoolean(event.asCharacters().getData());
						if (isBackground && cat != null) {
							cat.setTitle(value);
						}
						continue;
					}

					if (event.asStartElement().getName().getLocalPart().equals(Constants.LABEL)) {
						event = eventReader.nextEvent();
						boolean value = Boolean.parseBoolean(event.asCharacters().getData());
						if (isBackground && cat != null) {
							cat.setLabel(value);
						} else if (isElement && element != null) {
							element.setLabel(value);
						}
						continue;
					}

					if (event.asStartElement().getName().getLocalPart().equals(Constants.TOOLTIP)) {
						event = eventReader.nextEvent();
						boolean value = Boolean.parseBoolean(event.asCharacters().getData());
						if (isBackground && cat != null) {
							cat.setTooltip(value);
						} else if (isElement && element != null) {
							element.setTooltip(value);
						}
						continue;
					}

					if (event.asStartElement().getName().getLocalPart().equals(Constants.OUTLINE)) {
						event = eventReader.nextEvent();
						boolean value = Boolean.parseBoolean(event.asCharacters().getData());
						shape.setOutline(value);
						continue;
					}	

					if (event.asStartElement().getName().getLocalPart().equals(Constants.TYPE)) {
						event = eventReader.nextEvent();
						String value = event.asCharacters().getData(); 
						types.add(value);
						continue;
					}

				} // End of start elements 

				if (event.isEndElement()) {

					// If we reach the end of the background element
					if (event.asEndElement().getName().getLocalPart().equals(Constants.BACKGROUND)) {
						isBackground = false;
					}

					// If we reach the end of an "element" element
					if (event.asEndElement().getName().getLocalPart().equals(Constants.ELEMENT)) {
						element.setTypes(types);
						elementRepresentations.add(element);
						isElement = false;
						element = null;
						types = null;
					}

					// If we reach the end of a shape element
					if (event.asEndElement().getName().getLocalPart().equals(Constants.BOX)
							|| event.asEndElement().getName().getLocalPart().equals(Constants.LINE) /*TODO || other shape*/) {
						if (isBackground) {
							cat.setShape(shape);
						} 
						if (isElement && element != null) {
							element.setShape(shape);
						}
						shape = null;
					}

					// If we reach the end of a category element, we add it to the list
					if (event.asEndElement().getName().getLocalPart().equals(Constants.CATEGORY)) {
						categories.add((Category)cat);
					}

					if (event.asEndElement().getName().getLocalPart().equals(Constants.LEVEL)) {
						levels.add((Level)cat);
					}
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}

		background.setCategories(categories);
		background.setLevels(levels);
		representation.setBackground(background);
		representation.setElementRepresentations(elementRepresentations);
		return representation;
	}

}
