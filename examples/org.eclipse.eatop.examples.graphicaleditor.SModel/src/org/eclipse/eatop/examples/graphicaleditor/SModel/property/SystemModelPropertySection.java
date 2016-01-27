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
package org.eclipse.eatop.examples.graphicaleditor.SModel.property;

import org.eclipse.eatop.eastadl21.Allocation;
import org.eclipse.eatop.eastadl21.AnalysisFunctionPrototype;
import org.eclipse.eatop.eastadl21.AnalysisLevel;
import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignLevel;
import org.eclipse.eatop.eastadl21.HardwareComponentPrototype;
import org.eclipse.eatop.eastadl21.ImplementationLevel;
import org.eclipse.eatop.eastadl21.SystemModel;
import org.eclipse.eatop.eastadl21.VehicleLevel;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.impl.CustomContext;
import org.eclipse.graphiti.features.impl.AbstractFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.GFPropertySection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;


public class SystemModelPropertySection extends GFPropertySection implements
		ITabbedPropertyConstants {
	private Text nameText;

	public SystemModelPropertySection() {
	}

	@Override
	public void createControls(Composite parent,
		TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		TabbedPropertySheetWidgetFactory factory = getWidgetFactory();
		Composite composite = factory.createFlatFormComposite(parent);
		FormData data;

		nameText = factory.createText(composite, "");
		data = new FormData();
		data.left = new FormAttachment(0, STANDARD_LABEL_WIDTH);
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(0, VSPACE);
		nameText.setLayoutData(data);
		CLabel valueLabel = factory.createCLabel(composite, "Short Name:");
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(nameText, -HSPACE);
		data.top = new FormAttachment(nameText, 0, SWT.CENTER);
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
			String shortname = null;
			if (bo instanceof SystemModel){
				 shortname = ((SystemModel) bo).getShortName();
			}
			if (bo instanceof AnalysisLevel){
				 shortname = ((AnalysisLevel) bo).getShortName();
			}
			if (bo instanceof DesignLevel){
				 shortname = ((DesignLevel) bo).getShortName();
			}
			if (bo instanceof VehicleLevel){
				 shortname = ((VehicleLevel) bo).getShortName();
			}
			if (bo instanceof ImplementationLevel){
				 shortname = ((ImplementationLevel) bo).getShortName();
			}
			if (bo instanceof AnalysisFunctionPrototype){
				 shortname = ((AnalysisFunctionPrototype) bo).getShortName();
			}
			if (bo instanceof DesignFunctionPrototype){
				 shortname = ((DesignFunctionPrototype) bo).getShortName();
			}
			if (bo instanceof HardwareComponentPrototype){
				 shortname = ((HardwareComponentPrototype) bo).getShortName();
			}
			if (bo instanceof Allocation){
				 shortname = ((Allocation) bo).getShortName();
			}
			nameText.setText(shortname == null ? "" : shortname);
			nameText.addModifyListener(new ModifyListener() {

				@Override
				public void modifyText(ModifyEvent e) {
					String value = nameText.getText();
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
						String shortname = null;
						if (bo instanceof SystemModel){
							 shortname = ((SystemModel) bo).getShortName();
						}
						if (bo instanceof AnalysisLevel){
							 shortname = ((AnalysisLevel) bo).getShortName();
						}
						if (bo instanceof DesignLevel){
							 shortname = ((DesignLevel) bo).getShortName();
						}
						if (bo instanceof VehicleLevel){
							 shortname = ((VehicleLevel) bo).getShortName();
						}
						if (bo instanceof ImplementationLevel){
							 shortname = ((ImplementationLevel) bo).getShortName();
						}
						if (bo instanceof AnalysisFunctionPrototype){
							 shortname = ((AnalysisFunctionPrototype) bo).getShortName();
						}
						if (bo instanceof DesignFunctionPrototype){
							 shortname = ((DesignFunctionPrototype) bo).getShortName();
						}
						if (bo instanceof HardwareComponentPrototype){
							 shortname = ((HardwareComponentPrototype) bo).getShortName();
						}
						if (bo instanceof Allocation){
							 shortname = ((Allocation) bo).getShortName();
						}
						
						if (value.equals(shortname))
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

								if (bo instanceof SystemModel){
									SystemModel eClass = (SystemModel) bo;
									eClass.setShortName(typedValue);
								}
								if (bo instanceof AnalysisLevel){
									AnalysisLevel eClass = (AnalysisLevel) bo;
									eClass.setShortName(typedValue);
								}
								if (bo instanceof DesignLevel){
									DesignLevel eClass = (DesignLevel) bo;
									eClass.setShortName(typedValue);
								}
								if (bo instanceof VehicleLevel){
									VehicleLevel eClass = (VehicleLevel) bo;
									eClass.setShortName(typedValue);
								}
								if (bo instanceof ImplementationLevel){
									ImplementationLevel eClass = (ImplementationLevel) bo;
									eClass.setShortName(typedValue);
								}
								if (bo instanceof AnalysisFunctionPrototype){
									AnalysisFunctionPrototype eClass = (AnalysisFunctionPrototype) bo;
									eClass.setShortName(typedValue);
								}
								if (bo instanceof DesignFunctionPrototype){
									DesignFunctionPrototype eClass = (DesignFunctionPrototype) bo;
									eClass.setShortName(typedValue);
								}
								if (bo instanceof HardwareComponentPrototype){
									HardwareComponentPrototype eClass = (HardwareComponentPrototype) bo;
									eClass.setShortName(typedValue);
								}
								if (bo instanceof Allocation){
									Allocation eClass = (Allocation) bo;
									eClass.setShortName(typedValue);
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
			});
		}
	}
}
