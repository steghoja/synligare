/**
 * <copyright>
 * 
 * Copyright (c) 2014 Arccore and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *     Arccore - Initial API and implementation
 * 
 * </copyright>
 */

package org.eclipse.eatop.examples.explorer.dialogs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.eatop.common.ui.util.ModelSearcher;
import org.eclipse.eatop.examples.explorer.internal.messages.Messages;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ItemProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;

public class InstanceRefEditorDialog extends Dialog {

	protected ILabelProvider labelProvider;
	protected IContentProvider contentProvider;
	protected Object object;
	protected EClassifier eClassifier;
	protected String displayName;
	protected ItemProvider values;
	protected List<?> choiceOfValues;
	protected Object result;
	protected boolean multiLine;
	protected boolean unique;
	protected boolean isManyFeature;

	protected Object selectedObject;
	protected boolean autoresolve;
	protected boolean forceAutoresolve;
	protected TreePath resultPath;

	protected boolean isTarget;

	public InstanceRefEditorDialog(Shell parent, ILabelProvider labelProvider, EObject eObject, EStructuralFeature eStructuralFeature,
			String displayName, List<?> choiceOfValues, boolean forceAutoresolve) {
		super(parent);
		this.labelProvider = labelProvider;
		object = eObject;
		this.choiceOfValues = choiceOfValues;
		List<Object> l = new ArrayList<Object>();
		for (Object o : choiceOfValues) {
			if (o != null) {
				l.add(o);
			}
		}
		this.choiceOfValues = l;
		autoresolve = true;
		this.forceAutoresolve = forceAutoresolve;
		isTarget = isTargetFeature(eStructuralFeature);
		isManyFeature = eStructuralFeature.isMany();
		setShellStyle(getShellStyle() | SWT.RESIZE | SWT.MAX);
	}

	private boolean isTargetFeature(EStructuralFeature feature) {
		EAnnotation annotation = feature.getEAnnotation(Messages.Annotation_Stereotype);
		if (annotation != null && annotation.getDetails().containsValue(Messages.InstanceRef_Target)) {
			return true;
		}
		return false;
	}

	class InstanceRefTreeProvider implements ITreeContentProvider {

		@Override
		public void dispose() {
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		@Override
		public Object[] getElements(Object inputElement) {
			Set<EObject> elements = new HashSet<EObject>();
			for (Object o : (List<?>) inputElement) {
				if (o instanceof EObject) {
					elements.add(EcoreUtil.getRootContainer((EObject) o));
				}
			}
			return elements.toArray();
		}

		@SuppressWarnings("unchecked")
		@Override
		public Object[] getChildren(Object parentElement) {
			Set<EObject> children = new HashSet<EObject>();
			Object[] typeChildren = addTypeChildren((EObject) parentElement, new Object[] {});
			List<EObject> typeChildrenList = (List<EObject>) (List<?>) Arrays.asList(typeChildren);
			for (Object choice : choiceOfValues) {
				for (EObject child : ((EObject) parentElement).eContents()) {
					if (ModelSearcher.isIndirectRealOrVirtualAncestor(child, (EObject) choice)) {
						children.add(child);
					}
				}
				for (EObject eo : typeChildrenList) {
					if (ModelSearcher.isVirtualAncestorOrEquals(eo, (EObject) choice)) {
						children.add(eo);
					}
				}
			}
			return children.toArray();
		}

		@Override
		public Object getParent(Object element) {
			if (element instanceof EObject) {
				return ((EObject) element).eContainer();
			}
			return null;
		}

		@Override
		public boolean hasChildren(Object element) {
			for (Object choice : choiceOfValues) {
				if (element != choice && ModelSearcher.isRealOrVirtualAncestor((EObject) element, (EObject) choice)) {
					return true;
				}
			}
			return false;
		}

		private Object[] addTypeChildren(EObject parentElement, Object[] children) {
			EObject typeEObject = getEObjectType(parentElement);

			if (typeEObject != null) {
				ArrayList<Object> childrenList = new ArrayList<Object>();

				if (children != null) {
					childrenList.addAll(Arrays.asList(children));
				}

				childrenList.addAll(typeEObject.eContents());
				return childrenList.toArray();
			}
			return children;
		}

		private EObject getEObjectType(EObject parentElement) {
			EStructuralFeature eStructuralFeature = parentElement.eClass().getEStructuralFeature("type"); //$NON-NLS-1$

			if (eStructuralFeature != null) {
				Object eGet = parentElement.eGet(eStructuralFeature);

				if (eGet != null && eGet instanceof EObject) {
					return (EObject) eGet;
				}
			}

			return null;
		}

		public Deque<EObject> getPath(Object leaf) {
			Deque<EObject> path = new ArrayDeque<EObject>();
			path.addFirst((EObject) leaf);
			while (getParent(leaf) != null) {
				path.addFirst((EObject) getParent(leaf));
				leaf = getParent(leaf);
			}
			return path;
		}

	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		if (isTarget) {
			shell.setText("Instance reference target"); //$NON-NLS-1$
		} else {
			shell.setText("Instance reference context"); //$NON-NLS-1$
		}
		shell.setImage(labelProvider.getImage(object));
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite contents = (Composite) super.createDialogArea(parent);

		GridData contentsGridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		contentsGridData.horizontalAlignment = SWT.FILL;
		contentsGridData.verticalAlignment = SWT.FILL;

		GridLayout grid = new GridLayout();
		grid.numColumns = 1;
		contents.setLayout(grid);

		contents.setSize(400, 550);

		Tree tree = new Tree(contents, SWT.SINGLE | SWT.BORDER);

		GridData treeGridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		treeGridData.widthHint = 300;
		treeGridData.heightHint = 300;

		TreeViewer newTreeViewer = new TreeViewer(tree);
		newTreeViewer.getTree().setLayout(new FillLayout());
		newTreeViewer.getTree().setLayoutData(treeGridData);
		newTreeViewer.setContentProvider(new InstanceRefTreeProvider());

		ComposedAdapterFactory composedAdapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

		AdapterFactoryLabelProvider labelProvider = new AdapterFactoryLabelProvider(composedAdapterFactory);
		newTreeViewer.setLabelProvider(labelProvider);

		newTreeViewer.setInput(choiceOfValues);

		newTreeViewer.addSelectionChangedListener(createSelectionListener());

		if (!forceAutoresolve) {
			if (isTarget) {
				Button check = new Button(contents, SWT.CHECK);
				check.setSelection(true);
				check.setText("Try to automatically set context"); //$NON-NLS-1$
				check.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						autoresolve = ((Button) e.widget).getSelection();
					}
				});
			}
		}

		return contents;
	}

	private ISelectionChangedListener createSelectionListener() {
		return new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				if (event.getSelection().isEmpty()) {
					return;
				}
				if (event.getSelection() instanceof ITreeSelection) {
					ITreeSelection selection = (ITreeSelection) event.getSelection();
					resultPath = selection.getPaths()[0];
					Object o = selection.getFirstElement();
					if (o instanceof EObject) {
						selectedObject = o;
					}
				}
			}
		};
	}

	private EList<EObject> buildContextList() {
		EObject selectedContext = (EObject) selectedObject;
		// The path to the chosen context may depend on multiple EObjects
		// of this type. So build a list containing the full context path
		EObject contextClass = selectedContext.eClass();
		EList<EObject> l = new BasicEList<EObject>();
		l.add(selectedContext);
		for (int i = resultPath.getSegmentCount() - 2; i > 0; i--) {
			// skipping last segment because it is already added
			if (((EObject) resultPath.getSegment(i)).eClass() == contextClass) {
				l.add((EObject) resultPath.getSegment(i));
			}
		}
		return l;
	}

	@Override
	protected void okPressed() {
		if (!isManyFeature) {
			result = selectedObject;
		} else {
			result = buildContextList();
		}
		super.okPressed();
	}

	@Override
	public boolean close() {
		return super.close();
	}

	public Object getResult() {
		if (isManyFeature) {
			EList<EObject> l = new BasicEList<EObject>();
			return result;
		}
		return result;
	}

	public boolean getAutoresolveSelected() {
		return autoresolve;
	}

	public TreePath getTreePath() {
		return resultPath;
	}

}
