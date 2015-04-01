/*
 * Copyright 2009-2015 ArcCore AB. All rights reserved.
 */
package org.eclipse.eatop.examples.emf.compare.filter;

import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.rcp.ui.structuremergeviewer.filters.AbstractDifferenceFilter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.tree.TreeNode;

import com.google.common.base.Predicate;

public abstract class AbstractIsDiffFilter extends AbstractDifferenceFilter{
	/**
	 * Predicate to know if the given TreeNode is a diff.
	 */
	protected static final Predicate<EObject> DATA_IS_DIFF = new Predicate<EObject>() {
		public boolean apply(EObject treeNode) {
			return treeNode instanceof TreeNode && ((TreeNode)treeNode).getData() instanceof Diff;
		}
	};
	
}
