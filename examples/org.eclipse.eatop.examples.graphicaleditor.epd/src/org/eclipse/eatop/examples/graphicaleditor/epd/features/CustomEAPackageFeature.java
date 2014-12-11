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
package org.eclipse.eatop.examples.graphicaleditor.epd.features;

import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.graphiti.examples.common.ExampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;


public class CustomEAPackageFeature extends AbstractCustomFeature {
	private boolean hasDoneChanges = false;

	public CustomEAPackageFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public String getDescription() {
		 return "Change the name of the EAPackage";

	}

	@Override
	public boolean canExecute(ICustomContext context) {
        boolean ret = false;
        PictogramElement[] pes = context.getPictogramElements();
        if (pes != null && pes.length == 1) {
            Object bo = getBusinessObjectForPictogramElement(pes[0]);
            if (bo instanceof EAPackage) {
                ret = true;
            }
        }
        return ret;

	}

	@Override
	public String getName() {
		 return "Rename EAPackage";

	}

	@Override
	public void execute(ICustomContext context) {
		PictogramElement[] pes = context.getPictogramElements();
        if (pes != null && pes.length == 1) {
            Object bo = getBusinessObjectForPictogramElement(pes[0]);
            if (bo instanceof EAPackage) {
                EAPackage req = (EAPackage) bo;
                String currentShortName = req.getShortName();
                // ask user for a new class name
                String newShortName = ExampleUtil.askString(getName(), getDescription(),
                		currentShortName);
                if (newShortName != null && !newShortName.equals(currentShortName)) {
                    this.hasDoneChanges = true;
                    req.setShortName(newShortName);
                    updatePictogramElement(pes[0]);
                }
            }

	}

	}

	@Override
	public boolean hasDoneChanges() {
		
		return this.hasDoneChanges;
	}
	
}