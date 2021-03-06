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
package org.eclipse.eatop.examples.graphicaleditor.ed.property;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.eatop.eastadl21.AnalysisFunctionPrototype;
import org.eclipse.eatop.eastadl21.AnalysisFunctionType;
import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignFunctionType;
import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.eatop.eastadl21.FunctionPort;
import org.eclipse.eatop.eastadl21.FunctionPrototype;
import org.eclipse.eatop.eastadl21.HardwareComponentPrototype;
import org.eclipse.eatop.eastadl21.HardwareComponentType;
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
import org.eclipse.graphiti.mm.pictograms.Diagram;
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


public class ProtoTypesPropertySection extends GFPropertySection
implements ITabbedPropertyConstants  {

	private CCombo textList;
	public static int x = 1;
	public static int y = 1;
	public ArrayList<AnalysisFunctionType> types = new ArrayList<AnalysisFunctionType>();
	public ArrayList<DesignFunctionType> types2 = new ArrayList<DesignFunctionType>();
	public ArrayList<HardwareComponentType> types3 = new ArrayList<HardwareComponentType>();
	public ProtoTypesPropertySection() {
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
		EObject eob = pe.getLink().getBusinessObjects().get(0);
		
		if(eob instanceof FunctionPrototype){
			if(!(pe.eContainer() instanceof Diagram))//can select only in environment 
			{
		if(eob instanceof AnalysisFunctionPrototype)
		{
			itsAnalysisFunctionProtoType();

		}
		if(eob instanceof DesignFunctionPrototype)
		{
			itsDesignFunctionProtoType();

		}}
		}
		
	}

	public List<AnalysisFunctionType> getElements(AnalysisFunctionPrototype prototype) {
		List<AnalysisFunctionType> result = new LinkedList<AnalysisFunctionType>();
		EAXML root = (EAXML) EcoreUtil.getRootContainer(prototype);

		TreeIterator<EObject> iter = root.eAllContents();
		while (iter.hasNext()) {
			EObject element = (EObject) iter.next();
			if (element instanceof AnalysisFunctionType) {
				AnalysisFunctionType ob = (AnalysisFunctionType) element;
				result.add(ob);
			}
		}
		y= 1; 
		System.out.println();
		return result;
	}
	
	public List<DesignFunctionType> getDesignPrototypeElements(DesignFunctionPrototype prototype) {
		List<DesignFunctionType> result = new LinkedList<DesignFunctionType>();
		EAXML root = (EAXML) EcoreUtil.getRootContainer(prototype);

		TreeIterator<EObject> iter = root.eAllContents();
		while (iter.hasNext()) {
			EObject element = (EObject) iter.next();
			if (element instanceof DesignFunctionType) {
				DesignFunctionType ob = (DesignFunctionType) element;
				result.add(ob);
			}
		}
		y= 1; 
		System.out.println();
		return result;
	}
	public List<HardwareComponentType> getHardWareComponentElements(HardwareComponentPrototype prototype) {
		List<HardwareComponentType> result = new LinkedList<HardwareComponentType>();
		EAXML root = (EAXML) EcoreUtil.getRootContainer(prototype);

		TreeIterator<EObject> iter = root.eAllContents();
		while (iter.hasNext()) {
			EObject element = (EObject) iter.next();
			if (element instanceof HardwareComponentType) {
				HardwareComponentType ob = (HardwareComponentType) element;
				result.add(ob);
			}
		}
		y= 1; 
		System.out.println();
		return result;
	}
	
	public void itsAnalysisFunctionProtoType()
	{
		PictogramElement pe = getSelectedPictogramElement();
		
		if (pe != null) {
			Object bo = Graphiti.getLinkService()
					.getBusinessObjectForLinkedPictogramElement(pe);
			// the filter assured, that it is a EClass
			if (bo == null)
				return;
			types.clear();

			List<AnalysisFunctionType> elemList = getElements((AnalysisFunctionPrototype) bo);
			ArrayList<String> shortnames = new ArrayList<String>();

			for (AnalysisFunctionType j : elemList) {
				shortnames.add(j.getShortName());
				types.add(j);

			}

			String[] fTShortnames = new String[shortnames.size()];
			shortnames.toArray(fTShortnames);
			textList.setItems(fTShortnames);

			AnalysisFunctionPrototype k = (AnalysisFunctionPrototype) bo;
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
							if (bo instanceof DesignFunctionPrototype){
								if (!(((DesignFunctionPrototype) bo).getType() == null)) {
									kindname = ((DesignFunctionPrototype) bo)
											.getType().getShortName();
								}
							}
							if (bo instanceof AnalysisFunctionPrototype){
								if (!(((AnalysisFunctionPrototype) bo).getType() == null)) {
									kindname = ((AnalysisFunctionPrototype) bo)
											.getType().getShortName();
								}
							}
							if (bo instanceof HardwareComponentPrototype){
								if (!(((HardwareComponentPrototype) bo).getType() == null)) {
									kindname = ((HardwareComponentPrototype) bo)
											.getType().getShortName();
								}
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
									EObject eClass = null;
									if (bo instanceof DesignFunctionPrototype){
										eClass = (DesignFunctionPrototype) bo;
									}
									if (bo instanceof AnalysisFunctionPrototype){
										eClass = (AnalysisFunctionPrototype) bo;
									}
									if (bo instanceof HardwareComponentPrototype){
										eClass = (HardwareComponentPrototype) bo;
									}
									
									
									if (bo instanceof AnalysisFunctionPrototype){
										if (types.isEmpty())
											return;
										
										for (AnalysisFunctionType aft : types) {

											if (aft.getShortName().equals(typedValue)) {
												((AnalysisFunctionPrototype) eClass).setType(aft);

												

												EList<FunctionPort> ports = aft
														.getPort();
												
												ContainerShape hh = (ContainerShape) pe;
												EList<Anchor> anchorsList = hh.getAnchors();
													
												Anchor[] myList = anchorsList.toArray(new Anchor[anchorsList.size()]);
												for(int x = 0 ; x < myList.length ; x++)
												{
													if ((!(myList[x] == null))&&!(myList[x] instanceof ChopboxAnchor))
													{
														RemoveContext dContext = new RemoveContext(myList[x]);
														IRemoveFeature df = getFeatureProvider().getRemoveFeature(dContext);
														df.remove(dContext);
													}
												}
												
												ContainerShape fe = (ContainerShape) pe;
												EList<Shape> children = fe.getChildren();
												Shape[] childrenArray = (Shape[]) children.toArray();
												for (Shape b : childrenArray)
												{
													GraphicsAlgorithm aa = b.getGraphicsAlgorithm();
													if(aa instanceof Text)
													{
														Text deleteText = (Text) aa;
//														FunctionPort fp = (FunctionPort) pe.getLink().getBusinessObjects().get(0);
														
														if((deleteText.getValue().startsWith("F")))
														{
															IRemoveContext remContext =  new RemoveContext(b);
															IFeatureProvider  featureProvider2 = getFeatureProvider();
															IRemoveFeature removeFeature2 = featureProvider2.getRemoveFeature(remContext);
															if (removeFeature2 != null) {
																removeFeature2.remove(remContext);
																// Bug 347421: Set hasDoneChanges flag only after first modification
//															setDoneChanges(true);
															}
														}
													}
												}


												for (FunctionPort fp : ports) {
													AddContext context2 = new AddContext();
													
													context2.setLocation(x, y);
													context2.setTargetContainer((ContainerShape) pe);
													context2.setNewObject(fp);

													
													getFeatureProvider()
															.addIfPossible(new AddContext(context2,fp));
													y= y + 5;
													

												}
											}
										}
									}
									if (bo instanceof DesignFunctionPrototype){
										if (types2.isEmpty() )
											return;
										
										for (DesignFunctionType aft : types2) {

											if (aft.getShortName().equals(typedValue)) {
												((DesignFunctionPrototype) eClass).setType(aft);


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
	
	
	public void itsDesignFunctionProtoType()
	{
		PictogramElement pe = getSelectedPictogramElement();

		if (pe != null) {
			Object bo = Graphiti.getLinkService()
					.getBusinessObjectForLinkedPictogramElement(pe);
			// the filter assured, that it is a EClass
			if (bo == null)
				return;
			types2.clear();

			List<DesignFunctionType> elemList = getDesignPrototypeElements((DesignFunctionPrototype) bo);
			ArrayList<String> shortnames = new ArrayList<String>();

			for (DesignFunctionType j : elemList) {
				shortnames.add(j.getShortName());
				types2.add(j);

			}

			String[] fTShortnames = new String[shortnames.size()];
			shortnames.toArray(fTShortnames);
			textList.setItems(fTShortnames);

			DesignFunctionPrototype k = (DesignFunctionPrototype) bo;
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
							if (bo instanceof DesignFunctionPrototype){
								if (!(((DesignFunctionPrototype) bo).getType() == null)) {
									kindname = ((DesignFunctionPrototype) bo)
											.getType().getShortName();
								}
							}
							if (bo instanceof AnalysisFunctionPrototype){
								if (!(((AnalysisFunctionPrototype) bo).getType() == null)) {
									kindname = ((AnalysisFunctionPrototype) bo)
											.getType().getShortName();
								}
							}
							if (bo instanceof HardwareComponentPrototype){
								if (!(((HardwareComponentPrototype) bo).getType() == null)) {
									kindname = ((HardwareComponentPrototype) bo)
											.getType().getShortName();
								}
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
									EObject eClass = null;
									if (bo instanceof DesignFunctionPrototype){
										eClass = (DesignFunctionPrototype) bo;
									}
									if (bo instanceof AnalysisFunctionPrototype){
										eClass = (AnalysisFunctionPrototype) bo;
									}
									if (bo instanceof HardwareComponentPrototype){
										eClass = (HardwareComponentPrototype) bo;
									}
									
									
									
									if (bo instanceof AnalysisFunctionPrototype){
										if (types.isEmpty())
											return;
										for (AnalysisFunctionType aft : types) {

											if (aft.getShortName().equals(typedValue)) {
												((AnalysisFunctionPrototype) eClass).setType(aft);

											}
										}
									}
									if (bo instanceof DesignFunctionPrototype){
										if (types2.isEmpty())
											return;
										
										
										for (DesignFunctionType aft : types2) {

											if (aft.getShortName().equals(typedValue)) {
												((DesignFunctionPrototype) eClass).setType(aft);

												EList<FunctionPort> ports = aft
														.getPort();
												
												ContainerShape hh = (ContainerShape) pe;
												EList<Anchor> anchorsList = hh.getAnchors();
													
												Anchor[] myList = anchorsList.toArray(new Anchor[anchorsList.size()]);
												for(int x = 0 ; x < myList.length ; x++)
												{
													if (!(myList[x]== null))
													{
														RemoveContext dContext = new RemoveContext(myList[x]);
														IRemoveFeature df = getFeatureProvider().getRemoveFeature(dContext);
														df.remove(dContext);
													}
												}
												
												ContainerShape fe = (ContainerShape) pe;
												EList<Shape> children = fe.getChildren();
												Shape[] childrenArray = (Shape[]) children.toArray();
												for (Shape b : childrenArray)
												{
													GraphicsAlgorithm aa = b.getGraphicsAlgorithm();
													if(aa instanceof Text)
													{
														Text deleteText = (Text) aa;
//														FunctionPort fp = (FunctionPort) pe.getLink().getBusinessObjects().get(0);
														
														if((deleteText.getValue().startsWith("F")))
														{
															IRemoveContext remContext =  new RemoveContext(b);
															IFeatureProvider  featureProvider2 = getFeatureProvider();
															IRemoveFeature removeFeature2 = featureProvider2.getRemoveFeature(remContext);
															if (removeFeature2 != null) {
																removeFeature2.remove(remContext);
																// Bug 347421: Set hasDoneChanges flag only after first modification
//															setDoneChanges(true);
															}
														}
													}
												}


												for (FunctionPort fp : ports) {
													AddContext context2 = new AddContext();
													
													context2.setLocation(x, y);
													context2.setTargetContainer((ContainerShape) pe);
													context2.setNewObject(fp);

													
													getFeatureProvider()
															.addIfPossible(new AddContext(context2,fp));
													y= y + 5;
													

												}

											
											}
										
									
										}
									}
									if (bo instanceof HardwareComponentPrototype){
										if (types3.isEmpty())
											return;
										for (HardwareComponentType aft : types3) {

											if (aft.getShortName().equals(typedValue)) {
												((HardwareComponentPrototype) eClass).setType(aft);

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
	
	
	public void itsHardwareComponentProtoType()
	{
		PictogramElement pe = getSelectedPictogramElement();
		
		if (pe != null) {
			Object bo = Graphiti.getLinkService()
					.getBusinessObjectForLinkedPictogramElement(pe);
			// the filter assured, that it is a EClass
			if (bo == null)
				return;
			types3.clear();

			List<HardwareComponentType> elemList = getHardWareComponentElements((HardwareComponentPrototype) bo);
			ArrayList<String> shortnames = new ArrayList<String>();

			for (HardwareComponentType j : elemList) {
				shortnames.add(j.getShortName());
				types3.add(j);

			}

			String[] fTShortnames = new String[shortnames.size()];
			shortnames.toArray(fTShortnames);
			textList.setItems(fTShortnames);

			HardwareComponentPrototype k = (HardwareComponentPrototype) bo;
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
							if (bo instanceof DesignFunctionPrototype){
								if (!(((DesignFunctionPrototype) bo).getType() == null)) {
									kindname = ((DesignFunctionPrototype) bo)
											.getType().getShortName();
								}
							}
							if (bo instanceof AnalysisFunctionPrototype){
								if (!(((AnalysisFunctionPrototype) bo).getType() == null)) {
									kindname = ((AnalysisFunctionPrototype) bo)
											.getType().getShortName();
								}
							}
							if (bo instanceof HardwareComponentPrototype){
								if (!(((HardwareComponentPrototype) bo).getType() == null)) {
									kindname = ((HardwareComponentPrototype) bo)
											.getType().getShortName();
								}
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
									EObject eClass = null;
									if (bo instanceof DesignFunctionPrototype){
										eClass = (DesignFunctionPrototype) bo;
									}
									if (bo instanceof AnalysisFunctionPrototype){
										eClass = (AnalysisFunctionPrototype) bo;
									}
									if (bo instanceof HardwareComponentPrototype){
										eClass = (HardwareComponentPrototype) bo;
									}
									
									
									
									if (bo instanceof AnalysisFunctionPrototype){
										if (types.isEmpty() )
											return;
										for (AnalysisFunctionType aft : types) {

											if (aft.getShortName().equals(typedValue)) {
												((AnalysisFunctionPrototype) eClass).setType(aft);

											}
										}
									}
									if (bo instanceof DesignFunctionPrototype){
										if (types2.isEmpty() )
											return;
										
										for (DesignFunctionType aft : types2) {

											if (aft.getShortName().equals(typedValue)) {
												((DesignFunctionPrototype) eClass).setType(aft);


											}
										}
									}
									if (bo instanceof HardwareComponentPrototype){
										if (types3.isEmpty() )
											return;
										for (HardwareComponentType aft : types3) {

											if (aft.getShortName().equals(typedValue)) {
												((HardwareComponentPrototype) eClass).setType(aft);

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

	
	
	
	
	
}
