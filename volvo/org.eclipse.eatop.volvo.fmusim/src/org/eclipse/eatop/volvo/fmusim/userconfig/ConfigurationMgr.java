package org.eclipse.eatop.volvo.fmusim.userconfig;

import java.util.List;

import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignFunctionType;
import org.eclipse.eatop.eastadl21.EADirectionKind;
import org.eclipse.eatop.eastadl21.FunctionFlowPort;
import org.eclipse.eatop.eastadl21.VVProcedure;
import org.eclipse.eatop.eastadl21.VVStimuli;
import org.eclipse.eatop.volvo.fmusim.Utils;
import org.eclipse.eatop.volvo.fmusim.ecore.ModelProcessor;


/***
 * API towards user config package
 * 
 *
 */
public class ConfigurationMgr {

	//Singleton stuff 
	private static ConfigurationMgr instance = null;
	private ConfigurationMgr() {
	
	}	
	public static synchronized ConfigurationMgr getInstance() {
		if (instance == null) {
                	instance = new ConfigurationMgr();
		}
		return instance;
	}
	
    VVStimuliDataManager vvSDM = VVStimuliDataManager.getInstance();
	
	public boolean GetUserConfiguration(VVProcedure vp){
	
		ModelProcessor modelProcessor = new ModelProcessor();
		
		//Obtain VVStimuli 
	    VVStimuli vvS = modelProcessor.getVVStimuli(vp);

	    modelProcessor.CheckVVTarget(vp);   
	    DesignFunctionPrototype dfpSUT = modelProcessor.findSUT(vp); 

	    if (dfpSUT == null)
	    	return false;
	    
	    DesignFunctionType dftSUT = dfpSUT.getType();	    
	    
	    if (dftSUT == null)
	    {
	    	Utils.showErrorMessage("No type set for " + dfpSUT.getShortName());
	    }
	    
	    //Find inports from model
	    List<FunctionFlowPort> ffpList = modelProcessor.findPortsInDesignFunctionType(dftSUT,EADirectionKind.IN);

	    
	    //Read XML string from VVStimuli element 	 		 
		 vvSDM.Initialize(Utils.getShell(), vvS, ffpList);
		 if (vvSDM.isEmptyXMLString())
		 {
			 vvSDM.generateDefaultXMLString();
		 }
		 
		 if (!(vvSDM.ReadXMLString())){
			 return false;
		 }

		 vvSDM.alignPorts();
		 
		//show FMU Configuration dialog
		 FMUSimulationConfigDialog simConfDialog = new FMUSimulationConfigDialog(Utils.getShell()); 
		 if (simConfDialog.open() == org.eclipse.jface.window.Window.CANCEL) return false;

		 return true;

	}
	
	public VVStimuliXMLData getVVStimuliXMLData() {
		return vvSDM.getVVStimuliXMLData();
	}

	
	public InportXMLData findXMLPort(String name){
		return vvSDM.findXMLPort(name);
	}
}

