/*package examples;

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
import javax.xml.stream.events.XMLEvent;

import org.eclipse.core.runtime.FileLocator;

import visualizer.Activator;
import visualizer.modeloverview.models.VisualModel;
import visualizer.modeloverview.models.VisualRepresentation;
import visualizer.modeloverview.models.VisualModel.VisualElement;
import visualizer.modeloverview.models.VisualRepresentation.VisualBackground;
import visualizer.modeloverview.xml.XMLVisualRepresentationParser;

public class XMLVisualModelParser {
	
	@SuppressWarnings({ "unchecked" })
	public VisualModel readVisualModel(String visualModelFile) {
		VisualModel visualModel = new VisualModel();
		//background.setPath(visualModelFile);

		List<VisualElement> elements = new ArrayList<VisualElement>();

		try {
			// First, create a new XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			
			// TODO the path to visualModelFiles should not be "backgrounds" and it 
			// should not be magic
			URL url = Activator.getDefault().getBundle().getEntry("backgrounds/"+visualModelFile);
			URL backgroundUrl = FileLocator.toFileURL(url);		
			
			String path = backgroundUrl.getPath();
			if (path.startsWith("/")) {
				path = path.substring(1);
			}
			// Setup a new eventReader
			InputStream in = new FileInputStream(path);
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

			// read the XML document
			VisualElement element = null;
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();

				if (event.isStartElement()) {
	
					if (event.asStartElement().getName().getLocalPart().equals(VisualModel.MODEL_FILE)) {
						event = eventReader.nextEvent();
						String value = event.asCharacters().getData(); 
						visualModel.setModelFile(value);
						continue;
					}
					
					if (event.asStartElement().getName().getLocalPart().equals(VisualModel.BACKGROUND)) {
						event = eventReader.nextEvent();
						String value = event.asCharacters().getData(); 
						//VisualBackground background = 
						VisualRepresentation representation =
								XMLVisualRepresentationParser.readRepresentation(value);
						// TODO figure out a way to not parse this if we already have a repr/background ready?
						//visualModel.setBackground(representation.getBackground());
						visualModel.setVisualRepresentation(representation);
						continue;
					}

					if (event.asStartElement().getName().getLocalPart().equals(VisualModel.ELEMENT)) {
						element = visualModel.new VisualElement();
						
						// We read the attributes from this tag and add the name
						Iterator<Attribute> attributes = event.asStartElement().getAttributes();
						while (attributes.hasNext()) {
							Attribute attribute = attributes.next();
							if (attribute.getName().toString().equals(VisualModel.ELEMENT_NAME)) {
								element.setName(attribute.getValue());
							}
						}
						
						continue;
					}

					if (event.asStartElement().getName().getLocalPart().equals(VisualModel.PATH)) {
						event = eventReader.nextEvent();
						String value = event.asCharacters().getData(); 
						element.setPath(value);
						continue;
					}					

					if (event.asStartElement().getName().getLocalPart().equals(VisualModel.CATEGORY)) {
						event = eventReader.nextEvent();
						String value = event.asCharacters().getData(); 
						element.setCategory(value);
						continue;
					}	

					if (event.asStartElement().getName().getLocalPart().equals(VisualModel.LEVEL)) {
						event = eventReader.nextEvent();
						String value = event.asCharacters().getData(); 
						element.setLevel(value);
						continue;
					}		
					
					if (event.asStartElement().getName().getLocalPart().equals(VisualModel.TYPE)) {
						event = eventReader.nextEvent();
						String value = event.asCharacters().getData(); 
						element.setType(value);
						continue;
					}

				} // End of start elements 

				if (event.isEndElement()) {
					// If we reach the end of an "element" element
					if (event.asEndElement().getName().getLocalPart().equals(VisualModel.ELEMENT)) {
						elements.add(element);
						element = null;
					}
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		visualModel.setElements(elements);
		return visualModel;
	}
}*/
