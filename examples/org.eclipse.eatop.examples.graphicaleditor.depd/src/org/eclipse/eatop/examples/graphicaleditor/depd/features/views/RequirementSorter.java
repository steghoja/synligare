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
package org.eclipse.eatop.examples.graphicaleditor.depd.features.views;

import java.text.Collator;

import org.eclipse.eatop.eastadl21.Identifiable;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;


public class RequirementSorter extends ViewerSorter {

	public RequirementSorter() {
	}

	public RequirementSorter(Collator collator) {
		super(collator);
	}

	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		if(e1 instanceof Requirement&&e2 instanceof Requirement){
		Requirement item1=(Requirement) e1;
		Requirement item2=(Requirement) e2;
		return item1.getShortName().compareTo(item2.getShortName());
		}if(e1 instanceof Identifiable&&e2 instanceof Identifiable){
			Identifiable item1=(Identifiable) e1;
			Identifiable item2=(Identifiable) e2;
			return item1.getShortName().compareTo(item2.getShortName());
			}
		return 0;
	}

}
