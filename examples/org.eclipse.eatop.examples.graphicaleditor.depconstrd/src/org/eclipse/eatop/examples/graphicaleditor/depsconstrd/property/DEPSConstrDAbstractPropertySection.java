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
package org.eclipse.eatop.examples.graphicaleditor.depsconstrd.property;

import org.eclipse.eatop.eastadl21.ErrorModelType;
import org.eclipse.eatop.eastadl21.FaultFailure;
import org.eclipse.eatop.eastadl21.QuantitativeSafetyConstraint;
import org.eclipse.eatop.eastadl21.SafetyConstraint;
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


public class DEPSConstrDAbstractPropertySection extends GFPropertySection
		implements ITabbedPropertyConstants {
	
	private Text nameText;
	public DEPSConstrDAbstractPropertySection() {
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
        data.right = new FormAttachment(80, 0);
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
            
            String shortname = "";
            if (bo instanceof SafetyConstraint) {
            	shortname = ((SafetyConstraint) bo).getShortName();
            } else if (bo instanceof QuantitativeSafetyConstraint) {
            	shortname = ((QuantitativeSafetyConstraint) bo).getShortName();
            } else if (bo instanceof FaultFailure) {
            	shortname = ((FaultFailure) bo).getShortName();
            } else if (bo instanceof ErrorModelType) {
            	shortname = ((ErrorModelType) bo).getShortName();
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
						Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
						// the filter assured, that it is a EClass
						if (bo == null)
							return;
						
						String shortname = "";
			            if (bo instanceof SafetyConstraint) {
			            	shortname = ((SafetyConstraint) bo).getShortName();
			            } else if (bo instanceof QuantitativeSafetyConstraint) {
			            	shortname = ((QuantitativeSafetyConstraint) bo).getShortName();
			            } else if (bo instanceof FaultFailure) {
			            	shortname = ((FaultFailure) bo).getShortName();
			            } else if (bo instanceof ErrorModelType) {
			            	shortname = ((ErrorModelType) bo).getShortName();
			            }
			            
						if (value.equals(shortname))
							return;
					}
					final String typedValue = value;
					IFeature feature = new AbstractFeature(getDiagramTypeProvider().getFeatureProvider()) {
						
						@Override
						public void execute(IContext context) {
							PictogramElement pe = getSelectedPictogramElement();
							if (pe != null) {
								Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
								// the filter assured, that it is a EClass
								if (bo == null)
									return;

								if (bo instanceof SafetyConstraint) {
									SafetyConstraint eClass = (SafetyConstraint) bo;
									eClass.setShortName(typedValue);
					            } else if (bo instanceof QuantitativeSafetyConstraint) {
					            	QuantitativeSafetyConstraint eClass = (QuantitativeSafetyConstraint) bo;
									eClass.setShortName(typedValue);
					            } else if (bo instanceof FaultFailure) {
					            	FaultFailure eClass = (FaultFailure) bo;
									eClass.setShortName(typedValue);
					            } else if (bo instanceof ErrorModelType) {
									ErrorModelType eClass = (ErrorModelType) bo;
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
