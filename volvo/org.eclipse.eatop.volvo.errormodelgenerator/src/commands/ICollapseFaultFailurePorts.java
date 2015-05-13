package commands;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;

public interface ICollapseFaultFailurePorts {

	void collapseFFP(EditingDomain ed, List<EObject> selectedEObjects,
			int numSelected);
}
