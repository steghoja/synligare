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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.SafetyGoal;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class SafetyGoalTitleAreaDialogview extends TitleAreaDialog { 
	private Image image;
	SafetyGoal saftyob ;


public SafetyGoalTitleAreaDialogview(Shell parentShell ,EObject ob) {
    super(parentShell);
    setShellStyle(getShellStyle() | SWT.MAX | SWT.RESIZE);
    saftyob= (SafetyGoal) ob ; 
}


@Override
protected Control createContents(Composite parent) {
    Control contents = super.createContents(parent);

    setTitle("SafetyGoal");
    setMessage("SafetyRequirements");
    
    if (image != null)
        setTitleImage(image);

    return contents;
}

public void CreateTreeView(){
	
}
protected Control createDialogArea(Composite parent) {
    Composite composite = (Composite) super.createDialogArea(parent);
  
    // YOUR LINE HERE!
    Label line = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
    line.setLayoutData(new GridData(SWT.FILL, SWT.END, true, true));
   
    final TreeViewer viewer;
    viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
    List<SafetyGoal> sgs = new ArrayList<SafetyGoal>();
	
		 sgs.add(saftyob); 
		
    // Provide the input to the ContentProvider
    viewer.setContentProvider(new RequirementsContentProvider(saftyob));
	viewer.setLabelProvider(new RequirementsLabelProvider(saftyob));
	  viewer.setSorter(new RequirementSorter());
	// Expand the tree
   viewer.setAutoExpandLevel(3);
	  viewer.setInput(sgs.toArray());

	 viewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));//
	 // viewer.getTree().setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,1));
	  // Add a doubleclicklistenerObject[] elements;Object[]
	  viewer.addDoubleClickListener(new IDoubleClickListener() {
		
		@Override
		public void doubleClick(DoubleClickEvent event) {
			 TreeViewer viewer = (TreeViewer) event.getViewer();
		        IStructuredSelection thisSelection = (IStructuredSelection) event
		            .getSelection();
		        Object selectedNode = thisSelection.getFirstElement();
		        viewer.setExpandedState(selectedNode,
		            !viewer.getExpandedState(selectedNode));
		      }

	   
	  } );

	  viewer.getTree().addKeyListener(new KeyAdapter() {
		  @Override
	      public void keyReleased(final KeyEvent e) {
	        if (e.keyCode == SWT.DEL) {
	          final IStructuredSelection selection = (IStructuredSelection) viewer
	              .getSelection();
	          if (selection.getFirstElement() instanceof Requirement) {
	        	  Requirement o = (Requirement) selection.getFirstElement();
	            // TODO Delete the selected element from the model
	          }

	        }
	      }
	});

    return composite;
	}


}
