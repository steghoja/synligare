/**
 * <copyright>
 * 
 * Copyright (c) 10, 2014 Continental AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *     Continental AG - Initial API and implementation
 * 
 * </copyright>
 */

package org.eclipse.eatop.eastadl21.validation.ocl.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.validation.model.ConstraintSeverity;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLMetaInformationReader {

	private InputStream configInputStream;
	private Document doc;
	private ArrayList<Integer> eventList = null;
	private DocumentBuilder dBuilder;
	private DocumentBuilderFactory dbFactory;

	public XMLMetaInformationReader() throws ParserConfigurationException {

		dbFactory = DocumentBuilderFactory.newInstance();

		dBuilder = dbFactory.newDocumentBuilder();
	}

	private EvaluationMode<?> getMode(String mode) {
		if (mode.equals("Live")) { //$NON-NLS-1$
			return EvaluationMode.LIVE;
		} else if (mode.equals("Batch")) { //$NON-NLS-1$
			return EvaluationMode.BATCH;
		} else {
			return EvaluationMode.BATCH;
		}
	}

	private ConstraintSeverity getSeverity(String severity) {
		if (severity.equals("Warning")) { //$NON-NLS-1$
			return ConstraintSeverity.WARNING;
		} else if (severity.equals("Error")) { //$NON-NLS-1$
			return ConstraintSeverity.ERROR;
		} else if (severity.equals("Cancel")) { //$NON-NLS-1$
			return ConstraintSeverity.CANCEL;
		} else {
			return ConstraintSeverity.WARNING;
		}
	}

	public Hashtable<String, IXMLMetaInformation> getDescription(final InputStream inputStream) throws SAXException, IOException,
			ParserConfigurationException {

		Hashtable<String, IXMLMetaInformation> descriptionList = new Hashtable<String, IXMLMetaInformation>();

		configInputStream = inputStream;

		dBuilder = dbFactory.newDocumentBuilder();
		doc = dBuilder.parse(configInputStream);

		doc.getDocumentElement().normalize();

		NodeList contextNodes = doc.getElementsByTagName("constraint"); //$NON-NLS-1$

		for (int i = 0; i < contextNodes.getLength(); i++) {

			IXMLMetaInformation description = new XMLMetaInformation();
			if (contextNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
				NamedNodeMap namedNodeMap = contextNodes.item(i).getAttributes();
				Node nameAttributeNode = namedNodeMap.getNamedItem("oclName"); //$NON-NLS-1$
				NodeList constraintNodes = contextNodes.item(i).getChildNodes();

				// if (nameAttributeNode.getNodeValue().toString().equals(description.getName())) {
				description.setOCLName(nameAttributeNode.getNodeValue());
				description.setName(namedNodeMap.getNamedItem("name").getNodeValue()); //$NON-NLS-1$
				description.setCode(Integer.valueOf(namedNodeMap.getNamedItem("code").getNodeValue())); //$NON-NLS-1$

				for (int j = 0; j < constraintNodes.getLength(); j++) {
					Node childNode = constraintNodes.item(j);
					if (childNode.getNodeType() == Node.ELEMENT_NODE) {

						if (childNode.hasAttributes()) {
							NamedNodeMap childNodeMap = childNode.getAttributes();
							Node childAttributeValue = childNodeMap.getNamedItem("value"); //$NON-NLS-1$ 
							if (childNode.getNodeName().equals("mode")) { //$NON-NLS-1$
								description.setEvaludationMode(getMode(childAttributeValue.getNodeValue()));

								if (childAttributeValue.getNodeValue().equals("Live")) { //$NON-NLS-1$
									if (childNode.hasChildNodes()) {
										NodeList eventNodes = childNode.getChildNodes();
										eventList = new ArrayList<Integer>();
										for (int h = 0; h < eventNodes.getLength(); h++) {
											if (eventNodes.item(h).getNodeType() == Node.ELEMENT_NODE) {
												NamedNodeMap events = eventNodes.item(h).getAttributes();
												Node event = events.getNamedItem("value");//$NON-NLS-1$ 
												if (event.getNodeValue().equals("SET")) { //$NON-NLS-1$
													eventList.add(Notification.SET);
												}
												if (event.getNodeValue().equals("ADD")) {//$NON-NLS-1$ 
													eventList.add(Notification.ADD);
												}
												if (event.getNodeValue().equals("REMOVE")) { //$NON-NLS-1$
													eventList.add(Notification.REMOVE);
												}
											}
										}
										description.setNotificationEvent(eventList);
									}
								}

							}
							if (childNode.getNodeName().equals("severity")) { //$NON-NLS-1$
								description.setSeverity(getSeverity(childAttributeValue.getNodeValue()));
							}
						} else if (childNode.getNodeName().equals("description")) { //$NON-NLS-1$ 
							description.setDescription(childNode.getTextContent());
						} else if (childNode.getNodeName().equals("message")) { //$NON-NLS-1$
							description.setMessage(childNode.getTextContent());
						}
					}
				}
				// }
			}
			descriptionList.put(description.getOCLName(), description);
		}
		return descriptionList;
	}
}
