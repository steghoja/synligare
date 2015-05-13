package commands;

import java.util.Collection;

import modelquery.ModelQueryException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;

public interface ICollapseErrorModelPrototype {

	void collapseErrorModelPrototype(EditingDomain ed, Collection<EObject> selectedEObjects, int num)
			throws ModelQueryException, Exception;
}
