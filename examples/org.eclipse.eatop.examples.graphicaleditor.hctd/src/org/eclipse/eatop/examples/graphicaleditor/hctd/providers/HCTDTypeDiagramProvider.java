/**
 * <copyright>
 *  
 * Copyright (c) 2014 Continental AG and others.
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 
 * which accompanies this distribution, and is
 * available at http://www.eclipse.org/org/documents/epl-v10.php
 *  
 * Contributors: 
 *     Continental AG - Initial API and implementation
 *  
 * </copyright>
 * 
 */
package org.eclipse.eatop.examples.graphicaleditor.hctd.providers;

import org.eclipse.eatop.eastadl21.Actuator;
import org.eclipse.eatop.eastadl21.CommunicationHardwarePin;
import org.eclipse.eatop.eastadl21.ElectricalComponent;
import org.eclipse.eatop.eastadl21.HardwareComponentType;
import org.eclipse.eatop.eastadl21.HardwarePin;
import org.eclipse.eatop.eastadl21.IOHardwarePin;
import org.eclipse.eatop.eastadl21.Node;
import org.eclipse.eatop.eastadl21.PowerHardwarePin;
import org.eclipse.eatop.eastadl21.Sensor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;


public class HCTDTypeDiagramProvider extends AbstractDiagramTypeProvider {

	public static int counter = 50;
	public static int y = 50;
	private IToolBehaviorProvider[] toolBehaviorProviders;
	public static String DIAGRAM_TYPE_ID = "org.eclipse.eatop.examples.graphicaleditor.hctd.diagramType";

	public HCTDTypeDiagramProvider() {
		setFeatureProvider(new HCTDFeatureProvider(this));
	}

	@Override
	public IToolBehaviorProvider[] getAvailableToolBehaviorProviders() {
		if (toolBehaviorProviders == null) {
			toolBehaviorProviders = new IToolBehaviorProvider[] { new HCTDToolBehaviorProvider(
					this) };
		}
		return toolBehaviorProviders;

	}

	/**
	 * If isAutoUpdateAtRuntime returns true, then the diagram will be updated,
	 * when it is already open in the graphical editor, but only if the editor
	 * is already dirty.
	 */
	@Override
	public boolean isAutoUpdateAtRuntime() {
		getports();
		return true;
	}

	/**
	 * If isAutoUpdateAtStartup returns true, then the diagram will be updated,
	 * when it is initially opened in the graphical editor. This will make the
	 * editor dirty.
	 */
	@Override
	public boolean isAutoUpdateAtStartup() {
		getports();

		return true;
	}

	/**
	 * If the editor is already dirty and the user chooses to discard his
	 * changes (reset the diagram), when a change from outside the diagram
	 * occurs.
	 */
	@Override
	public boolean isAutoUpdateAtReset() {
		getports();
		return true;
	}

	public void getports() {

		EObject parentBO = getDiagram().getLink().getBusinessObjects().get(0);
		if (parentBO instanceof Node) {
			Node newAFT = (Node) parentBO;
			EList<HardwarePin> portList = newAFT.getPin();
			for (HardwarePin h : portList) {

				if (h instanceof CommunicationHardwarePin) {
					CommunicationHardwarePin h1 = (CommunicationHardwarePin) h;

					boolean lock = true;
					EList<Anchor> sho = getDiagram().getAnchors();
					Anchor[] anchoArray = (Anchor[]) sho.toArray();
					for (Anchor gg : anchoArray) {
						Anchor gggg = gg;
						EObject eOb = gggg.getLink().getBusinessObjects()
								.get(0);
						if (!(eOb == null)) {

							if (eOb instanceof CommunicationHardwarePin) {

								CommunicationHardwarePin fFPort = (CommunicationHardwarePin) eOb;
								if ((fFPort.getShortName().equals(h
										.getShortName()))) {
									lock = false;
								}
							}
						}
					}

					if (sho.isEmpty() || lock) {
						AddContext context = new AddContext();
						context.setLocation(counter, y);
						context.setTargetContainer(this.getDiagram());
						context.setNewObject(h1);

						getFeatureProvider().addIfPossible(
								new AddContext(context, h1));

						counter = counter + 30;
					}

				}
				if (h instanceof PowerHardwarePin) {
					PowerHardwarePin h1 = (PowerHardwarePin) h;

					boolean lock = true;
					EList<Anchor> sho = getDiagram().getAnchors();
					Anchor[] anchoArray = (Anchor[]) sho.toArray();
					for (Anchor gg : anchoArray) {
						Anchor gggg = gg;
						EObject eOb = gggg.getLink().getBusinessObjects()
								.get(0);
						if (eOb instanceof PowerHardwarePin) {

							PowerHardwarePin fFPort = (PowerHardwarePin) eOb;
							if ((fFPort.getShortName().equals(h.getShortName()))) {
								lock = false;
							}
						}
					}

					if (sho.isEmpty() || lock) {
						AddContext context = new AddContext();
						context.setLocation(counter, y);
						context.setTargetContainer(this.getDiagram());
						context.setNewObject(h1);

						getFeatureProvider().addIfPossible(
								new AddContext(context, h1));

						counter = counter + 30;
					}

				}
				if (h instanceof IOHardwarePin) {

					IOHardwarePin h1 = (IOHardwarePin) h;

					boolean lock = true;
					EList<Anchor> sho = getDiagram().getAnchors();
					Anchor[] anchoArray = (Anchor[]) sho.toArray();
					for (Anchor gg : anchoArray) {
						Anchor gggg = gg;
						EObject eOb = gggg.getLink().getBusinessObjects()
								.get(0);
						if (eOb instanceof IOHardwarePin) {

							IOHardwarePin fFPort = (IOHardwarePin) eOb;
							if ((fFPort.getShortName().equals(h.getShortName()))) {
								lock = false;
							}
						}
					}

					if (sho.isEmpty() || lock) {
						AddContext context = new AddContext();
						context.setLocation(counter, y);
						context.setTargetContainer(this.getDiagram());
						context.setNewObject(h1);

						getFeatureProvider().addIfPossible(
								new AddContext(context, h1));

						counter = counter + 30;
					}

				}
			}
		} else if (parentBO instanceof Sensor) {

			Sensor newAFT = (Sensor) parentBO;
			EList<HardwarePin> portList = newAFT.getPin();
			for (HardwarePin h : portList) {

				if (h instanceof CommunicationHardwarePin) {
					CommunicationHardwarePin h1 = (CommunicationHardwarePin) h;

					boolean lock = true;
					EList<Anchor> sho = getDiagram().getAnchors();
					Anchor[] anchoArray = (Anchor[]) sho.toArray();
					for (Anchor gg : anchoArray) {
						Anchor gggg = gg;
						EList<EObject> fd = gggg.getLink().getBusinessObjects();
						if (!(fd.isEmpty())) {
							EObject eOb = gggg.getLink().getBusinessObjects()
									.get(0);
							if (!(eOb == null)) {

								if (eOb instanceof CommunicationHardwarePin) {

									CommunicationHardwarePin fFPort = (CommunicationHardwarePin) eOb;
									if ((fFPort.getShortName().equals(h
											.getShortName()))) {
										lock = false;
									}
								}
							}
						}
					}

					if (sho.isEmpty() || lock) {
						AddContext context = new AddContext();
						context.setLocation(counter, y);
						context.setTargetContainer(this.getDiagram());
						context.setNewObject(h1);

						getFeatureProvider().addIfPossible(
								new AddContext(context, h1));

						counter = counter + 30;
					}

				}
				if (h instanceof PowerHardwarePin) {
					PowerHardwarePin h1 = (PowerHardwarePin) h;

					boolean lock = true;
					EList<Anchor> sho = getDiagram().getAnchors();
					Anchor[] anchoArray = (Anchor[]) sho.toArray();
					for (Anchor gg : anchoArray) {
						Anchor gggg = gg;
						EObject eOb = gggg.getLink().getBusinessObjects()
								.get(0);
						if (eOb instanceof PowerHardwarePin) {

							PowerHardwarePin fFPort = (PowerHardwarePin) eOb;
							if ((fFPort.getShortName().equals(h.getShortName()))) {
								lock = false;
							}
						}
					}

					if (sho.isEmpty() || lock) {
						AddContext context = new AddContext();
						context.setLocation(counter, y);
						context.setTargetContainer(this.getDiagram());
						context.setNewObject(h1);

						getFeatureProvider().addIfPossible(
								new AddContext(context, h1));

						counter = counter + 30;
					}

				}
				if (h instanceof IOHardwarePin) {

					IOHardwarePin h1 = (IOHardwarePin) h;

					boolean lock = true;
					EList<Anchor> sho = getDiagram().getAnchors();
					Anchor[] anchoArray = (Anchor[]) sho.toArray();
					for (Anchor gg : anchoArray) {
						Anchor gggg = gg;
						EObject eOb = gggg.getLink().getBusinessObjects()
								.get(0);
						if (eOb instanceof IOHardwarePin) {

							IOHardwarePin fFPort = (IOHardwarePin) eOb;
							if ((fFPort.getShortName().equals(h.getShortName()))) {
								lock = false;
							}
						}
					}

					if (sho.isEmpty() || lock) {
						AddContext context = new AddContext();
						context.setLocation(counter, y);
						context.setTargetContainer(this.getDiagram());
						context.setNewObject(h1);

						getFeatureProvider().addIfPossible(
								new AddContext(context, h1));

						counter = counter + 30;
					}
				}
			}
		} else if (parentBO instanceof Actuator) {

			Actuator newAFT = (Actuator) parentBO;
			EList<HardwarePin> portList = newAFT.getPin();
			for (HardwarePin h : portList) {

				if (h instanceof CommunicationHardwarePin) {
					CommunicationHardwarePin h1 = (CommunicationHardwarePin) h;

					boolean lock = true;
					EList<Anchor> sho = getDiagram().getAnchors();
					Anchor[] anchoArray = (Anchor[]) sho.toArray();
					for (Anchor gg : anchoArray) {
						Anchor gggg = gg;
						EList<EObject> fd = gggg.getLink().getBusinessObjects();
						if (!(fd.isEmpty())) {
							EObject eOb = gggg.getLink().getBusinessObjects()
									.get(0);
							if (!(eOb == null)) {

								if (eOb instanceof CommunicationHardwarePin) {

									CommunicationHardwarePin fFPort = (CommunicationHardwarePin) eOb;
									if ((fFPort.getShortName().equals(h
											.getShortName()))) {
										lock = false;
									}
								}
							}
						}
					}

					if (sho.isEmpty() || lock) {
						AddContext context = new AddContext();
						context.setLocation(counter, y);
						context.setTargetContainer(this.getDiagram());
						context.setNewObject(h1);

						getFeatureProvider().addIfPossible(
								new AddContext(context, h1));

						counter = counter + 30;
					}

				}
				if (h instanceof PowerHardwarePin) {
					PowerHardwarePin h1 = (PowerHardwarePin) h;

					boolean lock = true;
					EList<Anchor> sho = getDiagram().getAnchors();
					Anchor[] anchoArray = (Anchor[]) sho.toArray();
					for (Anchor gg : anchoArray) {
						Anchor gggg = gg;
						EObject eOb = gggg.getLink().getBusinessObjects()
								.get(0);
						if (eOb instanceof PowerHardwarePin) {

							PowerHardwarePin fFPort = (PowerHardwarePin) eOb;
							if ((fFPort.getShortName().equals(h.getShortName()))) {
								lock = false;
							}
						}
					}

					if (sho.isEmpty() || lock) {
						AddContext context = new AddContext();
						context.setLocation(counter, y);
						context.setTargetContainer(this.getDiagram());
						context.setNewObject(h1);

						getFeatureProvider().addIfPossible(
								new AddContext(context, h1));

						counter = counter + 30;
					}

				}
				if (h instanceof IOHardwarePin) {

					IOHardwarePin h1 = (IOHardwarePin) h;

					boolean lock = true;
					EList<Anchor> sho = getDiagram().getAnchors();
					Anchor[] anchoArray = (Anchor[]) sho.toArray();
					for (Anchor gg : anchoArray) {
						Anchor gggg = gg;
						EObject eOb = gggg.getLink().getBusinessObjects()
								.get(0);
						if (eOb instanceof IOHardwarePin) {

							IOHardwarePin fFPort = (IOHardwarePin) eOb;
							if ((fFPort.getShortName().equals(h.getShortName()))) {
								lock = false;
							}
						}
					}

					if (sho.isEmpty() || lock) {
						AddContext context = new AddContext();
						context.setLocation(counter, y);
						context.setTargetContainer(this.getDiagram());
						context.setNewObject(h1);

						getFeatureProvider().addIfPossible(
								new AddContext(context, h1));

						counter = counter + 30;
					}

				}
			}
		} else if (parentBO instanceof ElectricalComponent) {

			ElectricalComponent newAFT = (ElectricalComponent) parentBO;
			EList<HardwarePin> portList = newAFT.getPin();
			for (HardwarePin h : portList) {

				if (h instanceof CommunicationHardwarePin) {
					CommunicationHardwarePin h1 = (CommunicationHardwarePin) h;

					boolean lock = true;
					EList<Anchor> sho = getDiagram().getAnchors();
					Anchor[] anchoArray = (Anchor[]) sho.toArray();
					for (Anchor gg : anchoArray) {
						Anchor gggg = gg;
						EList<EObject> fd = gggg.getLink().getBusinessObjects();
						if (!(fd.isEmpty())) {
							EObject eOb = gggg.getLink().getBusinessObjects()
									.get(0);
							if (!(eOb == null)) {

								if (eOb instanceof CommunicationHardwarePin) {

									CommunicationHardwarePin fFPort = (CommunicationHardwarePin) eOb;
									if ((fFPort.getShortName().equals(h
											.getShortName()))) {
										lock = false;
									}
								}
							}
						}
					}

					if (sho.isEmpty() || lock) {
						AddContext context = new AddContext();
						context.setLocation(counter, y);
						context.setTargetContainer(this.getDiagram());
						context.setNewObject(h1);

						getFeatureProvider().addIfPossible(
								new AddContext(context, h1));

						counter = counter + 30;
					}

				}
				if (h instanceof PowerHardwarePin) {
					PowerHardwarePin h1 = (PowerHardwarePin) h;

					boolean lock = true;
					EList<Anchor> sho = getDiagram().getAnchors();
					Anchor[] anchoArray = (Anchor[]) sho.toArray();
					for (Anchor gg : anchoArray) {
						Anchor gggg = gg;
						EObject eOb = gggg.getLink().getBusinessObjects()
								.get(0);
						if (eOb instanceof PowerHardwarePin) {

							PowerHardwarePin fFPort = (PowerHardwarePin) eOb;
							if ((fFPort.getShortName().equals(h.getShortName()))) {
								lock = false;
							}
						}
					}

					if (sho.isEmpty() || lock) {
						AddContext context = new AddContext();
						context.setLocation(counter, y);
						context.setTargetContainer(this.getDiagram());
						context.setNewObject(h1);

						getFeatureProvider().addIfPossible(
								new AddContext(context, h1));

						counter = counter + 30;
					}

				}
				if (h instanceof IOHardwarePin) {

					IOHardwarePin h1 = (IOHardwarePin) h;

					boolean lock = true;
					EList<Anchor> sho = getDiagram().getAnchors();
					Anchor[] anchoArray = (Anchor[]) sho.toArray();
					for (Anchor gg : anchoArray) {
						Anchor gggg = gg;
						EObject eOb = gggg.getLink().getBusinessObjects()
								.get(0);
						if (eOb instanceof IOHardwarePin) {

							IOHardwarePin fFPort = (IOHardwarePin) eOb;
							if ((fFPort.getShortName().equals(h.getShortName()))) {
								lock = false;
							}
						}
					}

					if (sho.isEmpty() || lock) {
						AddContext context = new AddContext();
						context.setLocation(counter, y);
						context.setTargetContainer(this.getDiagram());
						context.setNewObject(h1);

						getFeatureProvider().addIfPossible(
								new AddContext(context, h1));

						counter = counter + 30;
					}

				}
			}
		} else if (parentBO instanceof HardwareComponentType) {

			HardwareComponentType newAFT = (HardwareComponentType) parentBO;
			EList<HardwarePin> portList = newAFT.getPin();
			for (HardwarePin h : portList) {

				if (h instanceof CommunicationHardwarePin) {
					CommunicationHardwarePin h1 = (CommunicationHardwarePin) h;

					boolean lock = true;
					EList<Anchor> sho = getDiagram().getAnchors();
					Anchor[] anchoArray = (Anchor[]) sho.toArray();
					for (Anchor gg : anchoArray) {
						Anchor gggg = gg;
						EList<EObject> fd = gggg.getLink().getBusinessObjects();
						if (!(fd.isEmpty())) {
							EObject eOb = gggg.getLink().getBusinessObjects()
									.get(0);
							if (!(eOb == null)) {

								if (eOb instanceof CommunicationHardwarePin) {

									CommunicationHardwarePin fFPort = (CommunicationHardwarePin) eOb;
									if ((fFPort.getShortName().equals(h
											.getShortName()))) {
										lock = false;
									}
								}
							}
						}
					}

					if (sho.isEmpty() || lock) {
						AddContext context = new AddContext();
						context.setLocation(counter, y);
						context.setTargetContainer(this.getDiagram());
						context.setNewObject(h1);

						getFeatureProvider().addIfPossible(
								new AddContext(context, h1));

						counter = counter + 30;
					}

				}
				if (h instanceof PowerHardwarePin) {
					PowerHardwarePin h1 = (PowerHardwarePin) h;

					boolean lock = true;
					EList<Anchor> sho = getDiagram().getAnchors();
					Anchor[] anchoArray = (Anchor[]) sho.toArray();
					for (Anchor gg : anchoArray) {
						Anchor gggg = gg;
						EObject eOb = gggg.getLink().getBusinessObjects()
								.get(0);
						if (eOb instanceof PowerHardwarePin) {

							PowerHardwarePin fFPort = (PowerHardwarePin) eOb;
							if ((fFPort.getShortName().equals(h.getShortName()))) {
								lock = false;
							}
						}
					}

					if (sho.isEmpty() || lock) {
						AddContext context = new AddContext();
						context.setLocation(counter, y);
						context.setTargetContainer(this.getDiagram());
						context.setNewObject(h1);

						getFeatureProvider().addIfPossible(
								new AddContext(context, h1));

						counter = counter + 30;
					}
				}
				if (h instanceof IOHardwarePin) {

					IOHardwarePin h1 = (IOHardwarePin) h;

					boolean lock = true;
					EList<Anchor> sho = getDiagram().getAnchors();
					Anchor[] anchoArray = (Anchor[]) sho.toArray();
					for (Anchor gg : anchoArray) {
						Anchor gggg = gg;
						EObject eOb = gggg.getLink().getBusinessObjects()
								.get(0);
						if (eOb instanceof IOHardwarePin) {

							IOHardwarePin fFPort = (IOHardwarePin) eOb;
							if ((fFPort.getShortName().equals(h.getShortName()))) {
								lock = false;
							}
						}
					}

					if (sho.isEmpty() || lock) {
						AddContext context = new AddContext();
						context.setLocation(counter, y);
						context.setTargetContainer(this.getDiagram());
						context.setNewObject(h1);

						getFeatureProvider().addIfPossible(
								new AddContext(context, h1));

						counter = counter + 30;
					}
				}
			}
		}
	}
}
