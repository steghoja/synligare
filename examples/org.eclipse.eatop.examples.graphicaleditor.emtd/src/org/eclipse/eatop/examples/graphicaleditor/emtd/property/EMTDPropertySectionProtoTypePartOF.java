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
package org.eclipse.eatop.examples.graphicaleditor.emtd.property;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.eatop.eastadl21.ErrorModelPrototype;
import org.eclipse.eatop.eastadl21.ErrorModelType;
import org.eclipse.eatop.eastadl21.FailureOutPort;
import org.eclipse.eatop.eastadl21.FaultInPort;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.features.context.impl.CustomContext;
import org.eclipse.graphiti.features.context.impl.RemoveContext;
import org.eclipse.graphiti.features.impl.AbstractFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.ChopboxAnchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.GFPropertySection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public class EMTDPropertySectionProtoTypePartOF extends GFPropertySection
		implements ITabbedPropertyConstants {
	private CCombo textList;
	public static int x = 1;
	public static int y = 30;
	public ArrayList<ErrorModelType> types = new ArrayList<ErrorModelType>();

	public EMTDPropertySectionProtoTypePartOF() {
	}

	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		TabbedPropertySheetWidgetFactory factory = getWidgetFactory();
		Composite composite = factory.createFlatFormComposite(parent);
		FormData data;
		textList = factory.createCCombo(composite);
		data = new FormData();
		data.left = new FormAttachment(0, STANDARD_LABEL_WIDTH);
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(0, VSPACE);
		textList.setLayoutData(data);
		CLabel valueLabel = factory.createCLabel(composite, "Type:");
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(textList, -HSPACE);
		data.top = new FormAttachment(textList, 0, SWT.CENTER);
		valueLabel.setLayoutData(data);

	}

	@Override
	public void refresh() {
		PictogramElement pe = getSelectedPictogramElement();
		if (pe != null) {
			Object bo = Graphiti.getLinkService()
					.getBusinessObjectForLinkedPictogramElement(pe);
			// the filter assured, that it is a EClass
			if (bo == null)
				return;
			types.clear();

			List<ErrorModelType> elemList = getElements((ErrorModelPrototype) bo);
			ArrayList<String> shortnames = new ArrayList<String>();

			for (ErrorModelType j : elemList) {
				shortnames.add(j.getShortName());
				types.add(j);
			}

			String[] fTShortnames = new String[shortnames.size()];
			shortnames.toArray(fTShortnames);
			textList.setItems(fTShortnames);

			ErrorModelPrototype k = (ErrorModelPrototype) bo;
			String kindname = null;
			if (!(k.getType() == null)) {
				kindname = k.getType().getShortName();
			}

			textList.setText(kindname == null ? "" : kindname);
			textList.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {

					String value = textList.getText();
					if (value == null) {
						value = "";//$NON-NLS-1$
					}
					PictogramElement pe = getSelectedPictogramElement();

					if (pe != null) {
						Object bo = Graphiti.getLinkService()
								.getBusinessObjectForLinkedPictogramElement(pe);
						// the filter assured, that it is a EClass
						if (bo == null)
							return;
						String kindname = null;
						if (!(((ErrorModelPrototype) bo).getType() == null)) {
							kindname = ((ErrorModelPrototype) bo).getType()
									.getShortName();
						}

						if (value.equals(kindname))
							return;
					}
					final String typedValue = value;
					IFeature feature = new AbstractFeature(
							getDiagramTypeProvider().getFeatureProvider()) {

						@Override
						public void execute(IContext context) {
							PictogramElement pe = getSelectedPictogramElement();
							if (pe != null) {
								Object bo = Graphiti
										.getLinkService()
										.getBusinessObjectForLinkedPictogramElement(
												pe);
								// the filter assured, that it is a EClass
								if (bo == null)
									return;
								ErrorModelPrototype eClass = (ErrorModelPrototype) bo;
								if (types.isEmpty())
									return;

								for (ErrorModelType emt : types) {

									if (emt.getShortName().equals(typedValue)) {
										eClass.setType(emt);

										EList<FaultInPort> ports = emt
												.getExternalFault();
										EList<FailureOutPort> ports2 = emt
												.getFailure();

										ContainerShape hh = (ContainerShape) pe;
										EList<Anchor> anchorsList = hh
												.getAnchors();
										
										Anchor[] myList = anchorsList
												.toArray(new Anchor[anchorsList
														.size()]);
										for (int x = 0; x < myList.length; x++) {
											if ((!(myList[x] == null))&&!(myList[x] instanceof ChopboxAnchor)) {
												RemoveContext dContext = new RemoveContext(
														myList[x]);
												IRemoveFeature df = getFeatureProvider()
														.getRemoveFeature(
																dContext);
												df.remove(dContext);
												
											}
										}
										
										ContainerShape fe = (ContainerShape) pe;
										EList<Shape> children = fe
												.getChildren();
										Shape[] childrenArray = (Shape[]) children
												.toArray();
										for (Shape b : childrenArray) {
											GraphicsAlgorithm aa = b
													.getGraphicsAlgorithm();
											if (aa instanceof Text) {
												Text deleteText = (Text) aa;
												// FaultInPort fp =
												// (FaultInPort)
												// pe.getLink().getBusinessObjects().get(0);

												if ((deleteText.getValue()
														.startsWith("F"))) {
													IRemoveContext remContext = new RemoveContext(
															b);
													IFeatureProvider featureProvider2 = getFeatureProvider();
													IRemoveFeature removeFeature2 = featureProvider2
															.getRemoveFeature(remContext);
													if (removeFeature2 != null) {
														removeFeature2
																.remove(remContext);
														// Bug 347421: Set
														// hasDoneChanges flag
														// only after first
														// modification
														// setDoneChanges(true);
													}
												}
											}
										}

										y= 30; 
										for (FailureOutPort fp : ports2) {
											AddContext context2 = new AddContext();

											context2.setLocation(x, y);
											context2.setTargetContainer((ContainerShape) pe);
											context2.setNewObject(fp);

											getFeatureProvider()
													.addIfPossible(
															new AddContext(
																	context2,
																	fp));
											y = y + 12;

										}

										for (FaultInPort fp : ports) {
											AddContext context2 = new AddContext();

											context2.setLocation(x, y);
											context2.setTargetContainer((ContainerShape) pe);
											context2.setNewObject(fp);

											getFeatureProvider()
													.addIfPossible(
															new AddContext(
																	context2,
																	fp));
										}
									}
								}

							}

						}

						@Override
						public boolean canExecute(IContext context) {
							return true;
						}
					};
					CustomContext context = new CustomContext();
					execute(feature, context);

				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {

				}
			});

		}

	}

	public List<ErrorModelType> getElements(ErrorModelPrototype prototype) {
		List<ErrorModelType> result = new LinkedList<ErrorModelType>();
		EAXML root = (EAXML) EcoreUtil.getRootContainer(prototype);

		TreeIterator<EObject> iter = root.eAllContents();
		while (iter.hasNext()) {
			EObject element = (EObject) iter.next();
			if (element instanceof ErrorModelType) {
				ErrorModelType ob = (ErrorModelType) element;
				result.add(ob);
			}
		}
		System.out.println();
		return result;
	}

}
