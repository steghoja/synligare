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

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ListDialog;


public class OpenDiagramDialog extends ListDialog {

	Object[] elements;
	
	public OpenDiagramDialog(Shell parent, Object[] elementsToDisplay) {
		super(parent);
		
		this.elements = elementsToDisplay;
		
		this.setContentProvider(new SelectDiagramContentProvider(elements));
		this.setTitle("Choose diagram");
		this.setMessage("Choose diagram to open");
		this.setLabelProvider(new DialogDiagramLabelProvider());
		this.setAddCancelButton(true);
		this.setHelpAvailable(false);
		this.setInput(new Object());		
	}

}
