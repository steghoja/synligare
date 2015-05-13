/*package examples;

import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.eclipse.core.runtime.FileLocator;

import visualizer.Activator;
import visualizer.modeloverview.models.VisualModel;

/**
 * 
 * @author Joanna Svantesson
 *
 */
/*public class VisualModelToXMLParser {
	
	private final static String ROOT_TAG = "VisualModel";
	private String file = "";

	public void setFile(String file) {
		this.file = file;
	}

	/**
	 * 
	 * @param model
	 * @throws Exception
	 */
	/*public void saveVisualModel(VisualModel model) throws Exception {
		List<String> nodeNames = new ArrayList<String>();
		List<String> nodeValues = new ArrayList<String>();
		
		nodeNames.add(VisualModel.MODEL_FILE);
		nodeValues.add(model.getModelFile());
		
		nodeNames.add(VisualModel.BACKGROUND);
		//nodeValues.add(model.getBackground().getPath());
		nodeValues.add(model.getVisualRepresentation().getPath());
		
		for (VisualModel.VisualElement e : model.getElements()) {
			nodeNames.add(VisualModel.ELEMENT);
			nodeValues.add(e.getName());
			
			nodeNames.add(VisualModel.PATH);
			nodeValues.add(e.getPath());
			nodeNames.add(VisualModel.CATEGORY);
			nodeValues.add(e.getCategory());
			nodeNames.add(VisualModel.LEVEL);
			nodeValues.add(e.getLevel());
			nodeNames.add(VisualModel.TYPE);
			nodeValues.add(e.getType());
		}
		
		saveVisualModel(nodeNames, nodeValues);
	}
	
	/**
	 * 
	 * @param nodeNames
	 * @param nodeValues
	 * @throws Exception
	 */
/*	public void saveVisualModel(List<String> nodeNames, List<String> nodeValues) throws Exception {
		if (nodeNames.size() != nodeValues.size()) {
			throw new Exception("List of names and list of values not equal in size");
		}
				
		// create an XMLOutputFactory
		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
		// create XMLEventWriter

		// TODO the path to visualModelFiles should not be "backgrounds" and it 
		// should not be magic
		
		// Get the path to background file
		URL url = Activator.getDefault().getBundle().getEntry("backgrounds");
		URL backgroundUrl = FileLocator.toFileURL(url);		
		
		String path = backgroundUrl.getPath();
		if (path.startsWith("/")) {
			path = path.substring(1);
		}
		
		XMLEventWriter eventWriter = outputFactory
				.createXMLEventWriter(new FileOutputStream(path + "/" + file));
		// create an EventFactory
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent end = eventFactory.createDTD("\n");
		// create and write Start Tag
		StartDocument startDocument = eventFactory.createStartDocument();
		eventWriter.add(startDocument);

		// create model open tag
		StartElement visualStartElement = eventFactory.createStartElement("",
				"", ROOT_TAG);
		eventWriter.add(visualStartElement);
		eventWriter.add(end);
		
		// Write the different nodes
		int i = 0;
		while (i < nodeNames.size()) { 
			
			if (nodeNames.get(i).equals(VisualModel.MODEL_FILE)) {
				createNode(eventWriter, nodeNames.get(i), nodeValues.get(i), 1);
				i++;
			}
			
			if (nodeNames.get(i).equals(VisualModel.BACKGROUND)) {
				createNode(eventWriter, nodeNames.get(i), nodeValues.get(i), 1);
				i++;
			} 
			
			if (nodeNames.get(i).equals(VisualModel.ELEMENT)) {
				// create element open tag
				StartElement elemStartElement = eventFactory.createStartElement("",
						"", nodeNames.get(i));
				Attribute nameAttr = eventFactory.createAttribute(
						VisualModel.ELEMENT_NAME, nodeValues.get(i));
				
				eventWriter.add(eventFactory.createDTD("\t"));
				eventWriter.add(elemStartElement);
				eventWriter.add(nameAttr);
				eventWriter.add(end);
				i++;
				while (i < nodeNames.size() && !nodeNames.get(i).equals(VisualModel.ELEMENT)) {
					createNode(eventWriter, nodeNames.get(i), nodeValues.get(i), 2);
					i++;
				}
				// create element close tag
				eventWriter.add(eventFactory.createDTD("\t"));
				eventWriter.add(eventFactory.createEndElement("", "", ROOT_TAG));
				eventWriter.add(end);
			}			
		}

		// create visual close tag		
		eventWriter.add(eventFactory.createEndElement("", "", ROOT_TAG));
		eventWriter.add(end);
		eventWriter.add(eventFactory.createEndDocument());
		eventWriter.close();
	}

	private void createNode(XMLEventWriter eventWriter, String name,
			String value, int steps) throws XMLStreamException {
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent end = eventFactory.createDTD("\n");
		XMLEvent tab = eventFactory.createDTD("\t");
		// create Start node
		StartElement sElement = eventFactory.createStartElement("", "", name);
		for (int i = 0; i < steps; i++) {
			eventWriter.add(tab);
		}
		eventWriter.add(sElement);

		// create Content
		Characters characters = eventFactory.createCharacters(value);
		eventWriter.add(characters);
		// create End node
		EndElement eElement = eventFactory.createEndElement("", "", name);
		eventWriter.add(eElement);
		eventWriter.add(end);

	}


}*/
