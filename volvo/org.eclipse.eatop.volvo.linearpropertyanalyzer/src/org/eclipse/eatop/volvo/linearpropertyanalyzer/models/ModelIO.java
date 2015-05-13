package org.eclipse.eatop.volvo.linearpropertyanalyzer.models;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sphinx.emf.util.WorkspaceEditingDomainUtil;
import org.eclipse.sphinx.emf.util.WorkspaceTransactionUtil;
import org.eclipse.eatop.eastadl21.GenericConstraint;
import org.eclipse.eatop.eastadl21.GenericConstraintKind;
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.EAStringValue;
import org.eclipse.eatop.eastadl21.VVActualOutcome;
import org.eclipse.eatop.eastadl21.util.Eastadl21Factory;
import org.eclipse.eatop.volvo.linearpropertyanalyzer.analysis.helpers.AnalysisResult;
import org.eclipse.eatop.volvo.linearpropertyanalyzer.metamodelExchanger.FindRef_getGenericConstraint_target;
import org.eclipse.eatop.volvo.linearpropertyanalyzer.popup.actions.Analyzer;

/** This class will take care of all the IO operations necessary with the model,
 * for example writing the analysis result to it.
 * 
 * @author Erik
 *
 */
public enum ModelIO {
	INSTANCE;

	public Resource resource;
	
	public void setResource(Resource resource) {
		this.resource = resource;
	}

	/**
	 * Overwrites GenericConstraint information in model
	 * @param genericConstraint the genericConstraint to overwrite
	 * @param genericConstraintKind the new kind of genericConstraint
	 * @param genericConstraintValue the new value for the genericConstraint
	 */
	public void writeElementInformationToModel(GenericConstraint genericConstraint, GenericConstraintKind genericConstraintKind, String genericConstraintValue)
	{
		//write information to element
		if(genericConstraintKind != null)
		{
			genericConstraint.setKind(genericConstraintKind);
		}
		if(genericConstraintValue != null)
		{
			if(genericConstraintValue.length() > 0)
			{
				
				EAStringValue temp = Eastadl21Factory.eINSTANCE.createEAStringValue();
				temp.setValue(genericConstraintValue);
				genericConstraint.setValue(temp);//.setGenericConstraintValue(genericConstraintValue);
			}
		}

		try 
		{
			//Save information to the model
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * Writes result of analysis to model
	 * @param result the result of the analysis
	 * @throws Exception 
	 */
	public void writeResultToModel(AnalysisResult result) throws Exception
	{
		//Create a new root package for the analysis result
		final EAPackage eaPackage = Eastadl21Factory.eINSTANCE.createEAPackage(); 
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		String status;
		if(result.hasPassed()){
			status = "Passed";
		}
		else{
			status = "Failed";
		}

		eaPackage.setName("Analysis result: " + status + ". Message: " + result.getResult() + ". Done on: " + dateFormat.format(cal.getTime()));
		eaPackage.setShortName("Analysis result: " + status + ". Message: " + result.getResult() + ". Done on: " + dateFormat.format(cal.getTime()));

		VVActualOutcome outCome = Eastadl21Factory.eINSTANCE.createVVActualOutcome();
		outCome.setName("Outcome");

		//Create the constraint on the result
		GenericConstraint resultConstraint = Eastadl21Factory.eINSTANCE.createGenericConstraint(); 
		if(result.getType().length() > 0){
			resultConstraint.setName(result.getType() + " Result");
			resultConstraint.setShortName(result.getType() + " Result");
		}
		else{
			resultConstraint.setName("Constraint Result");
			resultConstraint.setShortName("Constraint Result");
		}
//		resultConstraint.setGenericConstraintValue(result.getActual() + "");
		
		EAStringValue temp3 = Eastadl21Factory.eINSTANCE.createEAStringValue();
		temp3.setValue(result.getActual() + "");
		resultConstraint.setValue(temp3);
		
		resultConstraint.setKind(result.getGenericConstraintKind());

		//Create the constraint on the target
		GenericConstraint targetConstraint = Eastadl21Factory.eINSTANCE.createGenericConstraint();
		if(result.getType().length() > 0)
		{
			targetConstraint.setName(result.getType() + " Target");
			targetConstraint.setShortName(result.getType() + " Target");
		}
		else{
			targetConstraint.setName("Constraint Target");
			targetConstraint.setShortName("Constraint Target");
		}
//		targetConstraint.setGenericConstraintValue(result.getTarget() + "");
		EAStringValue temp2 = Eastadl21Factory.eINSTANCE.createEAStringValue();
		temp2.setValue(result.getTarget() + "");
		targetConstraint.setValue(temp2);
		
		targetConstraint.setKind(result.getGenericConstraintKind());

		//TODO: Change it to satisfy etc.
		//Adds the different elements to the package
		if(result.getTarget() != -1){
			eaPackage.getElement().add(targetConstraint);
		}
		eaPackage.getElement().add(resultConstraint);
//		if(result.getTarget() != -1){
//			FindRef_getGenericConstraint_target temp= new FindRef_getGenericConstraint_target();
//			EList<GenericConstraint> resultGenericConstraint = temp.getGenericConstraint_target(outCome); 
//			resultGenericConstraint.add((GenericConstraint) eaPackage.getElement().get(0));
//			resultGenericConstraint.add((GenericConstraint) eaPackage.getElement().get(1));
//		}
//		else
//		{
//			FindRef_getGenericConstraint_target temp= new FindRef_getGenericConstraint_target();
//			EList<GenericConstraint> resultGenericConstraint = temp.getGenericConstraint_target(outCome);
//			resultGenericConstraint.add((GenericConstraint) eaPackage.getElement().get(0));
//		}

		eaPackage.getElement().add(outCome);

		
		//Add the EAPackage to the model
		
		Runnable runnable = new Runnable() {
			public void run() {
				
				Analyzer.rootEAPackage.getSubPackage().add(eaPackage);
			}
		};
		
		TransactionalEditingDomain editingDomain = WorkspaceEditingDomainUtil.getEditingDomain(Analyzer.rootEAPackage);
		if (editingDomain != null) {
			try {
				WorkspaceTransactionUtil.executeInWriteTransaction(editingDomain, runnable, "addResultEAPackage"); //$NON-NLS-1$
			} catch (Exception ex) {
				// Ignore exception
			}
		} else {
			runnable.run();
		}
		
	}
}
