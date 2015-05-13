package commands;

import java.util.Collection;

import modelquery.ModelQueryException;

import org.eclipse.eatop.eastadl21.Dependability;
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.ErrorModelType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;




public interface IErrorModelCreation {
	//TODO: return info about synthesis
	ErrorModelType createErrorModel(EditingDomain ed, EObject tempEObject, Dependability superDep)
	throws ModelQueryException, Exception;
}
