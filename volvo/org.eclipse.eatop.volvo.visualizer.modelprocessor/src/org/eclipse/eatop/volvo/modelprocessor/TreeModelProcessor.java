package org.eclipse.eatop.volvo.modelprocessor;

import org.eclipse.core.resources.IFile;
import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IWorkbenchPage;

/**
 * Finds information about the model from selections in the model tree
 * 
 * @author Joanna Svantesson
 *
 */
public class TreeModelProcessor {

	/**
	 * Finds the root of the given selection
	 * @param selection
	 * @return The root of the given selection or null if no root were found
	 */
	public static EAXML findRoot(ISelection selection) {
		if (selection == null) {
			throw new IllegalArgumentException("Input can't be null");
		}

		if (selection instanceof TreeSelection) {
			TreeSelection treeSelec= (TreeSelection) selection;
			TreePath[] selections = treeSelec.getPaths();

			if (selections.length <= 0) {
				return null;
			}

			for (int i = 0; i < selections[0].getSegmentCount(); i++) {
				if (selections[0].getSegment(i) instanceof EAXML) {
					return (EAXML) selections[0].getSegment(i);
				}
			}
		}
		return null;
	}

	/**
	 * Finds the file for a given selection in the package explorer tree
	 * 
	 * @param selection
	 * @return The file the selection belongs to, or null if no file were found
	 */
	public static IFile findFile(ISelection selection) {
		TreeSelection treeSelec= (TreeSelection) selection;
		TreePath[] selections = treeSelec.getPaths();

		for (int i = 0; i < selections[0].getSegmentCount(); i++) {
			if (selections[0].getSegment(i) instanceof IFile) {
				return (IFile) selections[0].getSegment(i);
			}
		}

		return null;
	}

	/**
	 * Finds the file name for a given selection in a east-adl editor or
	 * the package explorer tree
	 * 
	 * @param selection
	 * @return The name of the file the given selection belongs to, or null if no
	 * file were found
	 */
	public static String findFileName(ISelection selection, IWorkbenchPage page) {
		// Check the first element in the selection
		// If it is not an EAXML, we probably are in the tree in the package explorer
		// and we can find the file and just get the name from the file
		// If it is an EAXML we are probably in the tree in the editor and can get the
		// file name from the active editor.
		String name = null;
		TreeSelection treeSelec= (TreeSelection) selection;
		TreePath[] selections = treeSelec.getPaths();

		Object firstSegment = selections[0].getFirstSegment();

		if (firstSegment instanceof EAXML) {
			name = page.getActiveEditor().getEditorInput().getName();
		} else {
			IFile file = findFile(selection);
			name = file.getName();
		}
		return name;
	}
}
