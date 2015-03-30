package org.eclipse.eatop.examples.emf.compare.filter;

import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.DifferenceKind;
import com.google.common.base.Predicate;
import static com.google.common.base.Predicates.and;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.tree.TreeNode;




public class ReorderingFilter extends AbstractIsDiffFilter{
		
	/**
	 * Predicate to know if the given TreeNode is a diff and the diff is a move
	 */
	private static final Predicate<EObject> DIFF_IS_MOVE = new Predicate<EObject>() {

		public boolean apply(EObject eobject) {
			if(eobject instanceof TreeNode && ((TreeNode) eobject).getData() instanceof Diff){
				DifferenceKind diffKind = ((Diff) ((TreeNode) eobject).getData()).getKind();
				if(diffKind != null && ("MOVE").equals(diffKind.getName())){
					return true;
				}
			}			
			return false;
		}
	};

	/**
	 * The predicate use by this filter when it is selected.
	 */
	private static final Predicate<? super EObject> PREDICATE_WHEN_SELECTED = and(
			DATA_IS_DIFF, DIFF_IS_MOVE
			);
	
	@Override
	public Predicate<? super EObject> getPredicateWhenSelected() {
		return PREDICATE_WHEN_SELECTED;		
	}
	

}