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
package org.eclipse.eatop.examples.graphicaleditor.common.dialog;

import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.platform.IPlatformImageConstants;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class DialogDiagramLabelProvider extends LabelProvider {

	Image image;

	public DialogDiagramLabelProvider() {
		super();
	}

	@Override
	public String getText(Object o) {
		String ret = null;
		if (o instanceof Diagram) {
			Diagram diagram = (Diagram) o;
			ret = diagram.getName() + " (" + diagram.getDiagramTypeId() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
		}
		return ret;
	}

	@Override
	public Image getImage(Object element) {
		if (this.image == null) {
			this.image = GraphitiUi.getImageService().getPlatformImageForId(IPlatformImageConstants.IMG_DIAGRAM);
		}
		return this.image;
	}
}
