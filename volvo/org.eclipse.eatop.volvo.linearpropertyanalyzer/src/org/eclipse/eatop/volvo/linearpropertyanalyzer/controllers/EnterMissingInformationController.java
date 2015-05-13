package org.eclipse.eatop.volvo.linearpropertyanalyzer.controllers;

import java.util.Scanner;

import javax.swing.JOptionPane;

import org.eclipse.eatop.eastadl21.GenericConstraint;
import org.eclipse.eatop.eastadl21.GenericConstraintKind;
import org.eclipse.eatop.eastadl21.EAStringValue;
import org.eclipse.eatop.eastadl21.EAValue;
import org.eclipse.eatop.volvo.linearpropertyanalyzer.models.ModelIO;

public enum EnterMissingInformationController {
	INSTANCE;

	/**
	 * This method will create a popup asking the user for inputing a missing GenericConstraintValue
	 * @param genCon the GenericConstraint that is missing a value
	 * @return the value the user entered, null if nothing or wrong kind of input
	 */
	public String startRequestMissingInformation(GenericConstraint genCon)
	{
		String result = RequestMissingInformation(genCon);
		if(result!=null)
		{
			ModelIO.INSTANCE.writeElementInformationToModel(genCon, genCon.getKind(), result);
		}
		return result;
	}

	private String RequestMissingInformation(GenericConstraint genCon)
	{
		boolean inputResult = false;
		EAValue temps= genCon.getValue();
		String value =null;
		if (temps instanceof EAStringValue)
			value = ((EAStringValue) temps).getValue();
		Scanner scanner = new Scanner(value);
		while(inputResult==false){
			String input = JOptionPane.showInputDialog(null, genCon.getShortName()+ " is missing a value, please enter it:" , "Missing GenericConstriant value",
					JOptionPane.WARNING_MESSAGE);
			if(input!=null)
			{
				scanner = new Scanner(input);
				if(scanner.hasNextDouble()){
					inputResult = true;
					return scanner.nextDouble()+"";
				}
			}
			else
			{
				return null;
			}
		}
		return null;
	}
}
