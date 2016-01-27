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
package org.eclipse.eatop.examples.graphicaleditor.depd.features.property;

import org.eclipse.eatop.eastadl21.ControllabilityClassKind;
import org.eclipse.eatop.eastadl21.HazardousEvent;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.impl.CustomContext;
import org.eclipse.graphiti.features.impl.AbstractFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
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


public class DEPDHazardousEventControllabilityPropertySection extends
		GFPropertySection implements ITabbedPropertyConstants {
	private CCombo textList;
	public DEPDHazardousEventControllabilityPropertySection() {
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
        data.right = new FormAttachment(80, 0);
        data.top = new FormAttachment(0, VSPACE);
        textList.setLayoutData(data);
 
        CLabel valueLabel = factory.createCLabel(composite, "Controllability:");
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
            String[]items={
            		      "C0",
            	          "C1",
            	          "C2",
            	          "C3",};
	   
       textList.setItems(items);
       String kindname = ((HazardousEvent) bo).getControllability().toString();
       textList.setText(kindname==null?"" :kindname);
       textList.addSelectionListener(new SelectionListener() {
     	  
				
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				 String value = textList.getText();
					if (value == null) {
						value = "";//$NON-NLS-1$
					}
					PictogramElement pe = getSelectedPictogramElement();
					
					if (pe != null) {
						Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
						// the filter assured, that it is a EClass
						if (bo == null)
							return;
						String kindname = ((HazardousEvent) bo).getControllability().toString();
						if (value.equals(kindname))
							return;
					}
					final String selectedValue = value;
					IFeature feature = new AbstractFeature(getDiagramTypeProvider().getFeatureProvider()) {
						
						@Override
						public void execute(IContext context) {
							PictogramElement pe = getSelectedPictogramElement();
							if (pe != null) {
								Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
								// the filter assured, that it is a EClass
								if (bo == null)
									return;
								HazardousEvent eClass = (HazardousEvent) bo;
								eClass.setControllability(ControllabilityClassKind.get(selectedValue));
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
		} );
        }
	}
}
