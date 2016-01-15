package org.eclipse.eatop.app.semcon.allocationassistant.pages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.eatop.eastadl21.Dependability;
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.Identifiable;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.RequirementsModel;
import org.eclipse.eatop.eastadl21.Satisfy;
import org.eclipse.eatop.eastadl21.Satisfy_satisfiedBy;
import org.eclipse.eatop.eastadl21.impl.RequirementImpl;
import org.eclipse.eatop.eastadl21.impl.SatisfyImpl;
import org.eclipse.eatop.eastadl21.impl.Satisfy_satisfiedByImpl;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.sphinx.emf.util.EObjectUtil;
import org.eclipse.sphinx.emf.util.WorkspaceEditingDomainUtil;
import org.eclipse.sphinx.emf.util.WorkspaceTransactionUtil;

/**
 * This class contains the allocation functionality that creates Satisfy links between requirements
 * and system model elements.
 * @author Andreea Olaru
 *
 */
public class AllocationFunctionality {

	/**
	 * This functionality accesses the {@link org.eclipse.jface.viewers.StructuredViewer <em>views</em>} 
	 * for requirements and model elements and takes the selected elements for each. It checks that at least one element 
	 * was selected in each view.
	 * It needs the root of the requirements model in order to get the 
	 * {@link org.eclipse.emf.transaction.TransactionalEditingDomain <em>editing domain</em>} and create satisfy linksin the requirements model.
	 * 2 {@link org.eclipse.jface.viewers.TreeViewer <em>TreeViewers</em>}.
	 * @param rootOfRequirementsView
	 * @param reqViewer
	 * @param modelViewer
	 */
	public static void allocateRequirements(Object rootOfRequirementsView, final StructuredViewer reqViewer, final StructuredViewer modelViewer){
		try {
			TransactionalEditingDomain editingDomain = WorkspaceEditingDomainUtil.getEditingDomain(rootOfRequirementsView);
			WorkspaceTransactionUtil.executeInWriteTransaction(editingDomain, new Runnable() {


				@Override
				public void run() {
					
					//get the selected requirements and model elements
					IStructuredSelection requirementsSelection = (IStructuredSelection) ((TreeViewer)reqViewer).getSelection();
					IStructuredSelection modelElemSelection = (IStructuredSelection) ((TreeViewer)modelViewer).getSelection();
					
					// If at least 1 requirements and 1 model element were selected, proceed in creating the Satisfy link
					if (!requirementsSelection.isEmpty() && !modelElemSelection.isEmpty()){

						
						List<Requirement> requirementsList = new ArrayList<Requirement>();

						Object[] requirements = requirementsSelection.toArray();
						for (int i = 0; i < requirements.length; i++){
							if (requirements[i] instanceof Requirement){
								Requirement eob = (Requirement) requirements[i];
								requirementsList.add(eob);

							}

						}
						//get the parent of the first requirement selected to create the Satisfy under it
						EObject parentPackage = requirementsList.get(0).eContainer();
						
						//Create the Satisfy link
						Satisfy satisfy =  createSatisfy(parentPackage, requirementsList);

						//Create satisfyBy elements under Satisfy (created previously) and the identifiable target to each of the selected system model elements
						for (Object modelElem : modelElemSelection.toList()){
							if ((modelElem instanceof Identifiable) && !(modelElem instanceof Requirement)){
								Satisfy_satisfiedBy satisfyBy = createSatisfiedBy(satisfy, (Identifiable) modelElem);
							}

						}
						reqViewer.refresh();
					}
				}
			}, "CreateSatisfy");

		} catch (OperationCanceledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private static Satisfy createSatisfy(EObject parentPackage, List<Requirement> requirementsList ){
		Satisfy satisfy = null;
		if (parentPackage instanceof RequirementsModel){
			satisfy = Eastadl21Factory.eINSTANCE.createSatisfy();
			RequirementsModel rm = (RequirementsModel) parentPackage;
			rm.getOwnedRelationship().add(satisfy);
		}
		else if (parentPackage instanceof Dependability){
			satisfy = Eastadl21Factory.eINSTANCE.createSatisfy();
			 Dependability dp = (Dependability) parentPackage;
			 dp.getOwnedRelationship().add(satisfy);
		}else if (parentPackage instanceof EAPackage){
			// It's not possible to create a satisfy in an EAPackage
			// A solution should be found here for the case where
			// requirements are created directly in an EAPackage,
			// and then they are selected for allocation. Where should the Satisfy be created?
		}
		String satisfyName = "Satisfy" ;
		if (requirementsList.size()>0)
			satisfyName = satisfyName.concat(requirementsList.get(0).getShortName());
		satisfy.setShortName(satisfyName);
		EStructuralFeature satisfiedRequirementFeature = EObjectUtil.getEStructuralFeature(satisfy, "satisfiedRequirement");
		satisfy.eSet(satisfiedRequirementFeature, requirementsList);
		return satisfy;
	}
	private static Satisfy_satisfiedBy createSatisfiedBy(Satisfy satisfyParent, Identifiable identifiable){
		if (satisfyParent!=null){
			// If the Satisfy element was created so is not null, then create the satisfyBy under it
			Satisfy_satisfiedBy satisfiedBy = Eastadl21Factory.eINSTANCE.createSatisfy_satisfiedBy();
			Satisfy satisfy = (Satisfy) satisfyParent;
			satisfy.getSatisfiedBy().add(satisfiedBy);
			satisfiedBy.setIdentifiable_target(identifiable);
			return satisfiedBy;
		}else return null;
	}
}
