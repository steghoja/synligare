package org.eclipse.eatop.volvo.fmusim.ecore;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.apache.xml.serialize.LineSeparator;
import org.eclipse.eatop.common.resource.EastADLURIFactory;
import org.eclipse.eatop.eastadl21.Behavior;
import org.eclipse.eatop.eastadl21.ClampConnector;
import org.eclipse.eatop.eastadl21.ClampConnector_port;
import org.eclipse.eatop.eastadl21.Comment;
import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignFunctionType;
import org.eclipse.eatop.eastadl21.DesignLevel;
import org.eclipse.eatop.eastadl21.EABoolean;
import org.eclipse.eatop.eastadl21.EABooleanValue;
import org.eclipse.eatop.eastadl21.EADirectionKind;
import org.eclipse.eatop.eastadl21.EAElement;
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.EAPackageableElement;
import org.eclipse.eatop.eastadl21.EAValue;
import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.Environment;
import org.eclipse.eatop.eastadl21.Event;
import org.eclipse.eatop.eastadl21.EventFunction;
import org.eclipse.eatop.eastadl21.ExecutionTimeConstraint;
import org.eclipse.eatop.eastadl21.FunctionBehavior;
import org.eclipse.eatop.eastadl21.FunctionConnector;
import org.eclipse.eatop.eastadl21.FunctionConnector_port;
import org.eclipse.eatop.eastadl21.FunctionFlowPort;
import org.eclipse.eatop.eastadl21.FunctionPort;
import org.eclipse.eatop.eastadl21.FunctionPrototype;
import org.eclipse.eatop.eastadl21.FunctionTrigger;
import org.eclipse.eatop.eastadl21.FunctionType;
import org.eclipse.eatop.eastadl21.GenericConstraint;
import org.eclipse.eatop.eastadl21.Identifiable;
import org.eclipse.eatop.eastadl21.PeriodicConstraint;
import org.eclipse.eatop.eastadl21.Refine;
import org.eclipse.eatop.eastadl21.Refine_refinedBy;
import org.eclipse.eatop.eastadl21.Relationship;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.RequirementsModel;
import org.eclipse.eatop.eastadl21.Satisfy;
import org.eclipse.eatop.eastadl21.Satisfy_satisfiedBy;
import org.eclipse.eatop.eastadl21.Timing;
import org.eclipse.eatop.eastadl21.TimingConstraint;
import org.eclipse.eatop.eastadl21.TriggerPolicyKind;
import org.eclipse.eatop.eastadl21.VVCase;
import org.eclipse.eatop.eastadl21.VVLog;
import org.eclipse.eatop.eastadl21.VVProcedure;
import org.eclipse.eatop.eastadl21.VVStimuli;
import org.eclipse.eatop.eastadl21.VVTarget;
import org.eclipse.eatop.eastadl21.VVTarget_element;
import org.eclipse.eatop.eastadl21.VerificationValidation;
import org.eclipse.eatop.eastadl21.Verify;
import org.eclipse.eatop.volvo.fmusim.Utils;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.DFTExecutionInfo;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.EventTriggeredDFTExecutionInfo;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.TimeTriggeredDFTExecutionInfo;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.SimulinkSystem;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.SimulinkSystemBuilder;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.Outport;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.Subsystem;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks.TriggeredSubsystem;
import org.eclipse.core.internal.expressions.InstanceofExpression;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.ComposedImage.Size;
import org.omg.CORBA.PRIVATE_MEMBER;


/** This class have methods which will traverse the model/tree and find different information. 
 *  Example would be find all the Inports in the ExperimentSetup of the model. 
 * 
 * 
 */

public class ModelProcessor {

	static Map<DesignFunctionType, DFTExecutionInfo> mapDFTExecutionInfo;

	static List<DesignFunctionPrototype> monitors = new ArrayList<DesignFunctionPrototype>();
	
	public ModelProcessor(){};


	public VVStimuli getVVStimuli(VVProcedure vp) {
		 //Find the VVStimuli 
		 EList<VVStimuli> vvSList = vp.getVvStimuli();
		 if (vvSList.size() != 1)
		 {
			 Utils.showErrorMessage("The VVProcedure does not contain exactly one VVStimuli. ");
	 				return null;
		 }
		 VVStimuli vvS = vvSList.get(0);
		return vvS;
	}

    
	public VVCase getVVCase(VVProcedure vp){
		EAElement ea = (EAElement)vp;
		VVCase vc = (VVCase)ea.eContainer();
		return vc;

	}
	
	//gets VVLogs related to vp
	public List<VVLog> getVVLogs (VVProcedure vp){
		List<VVLog> result = new ArrayList<VVLog>(); 
		
		VVCase vc = getVVCase(vp);
		List<VVLog> vvLogList = vc.getVvLog();
		
		for (VVLog vl : vvLogList) {
			if (vl.getPerformedVVProcedure() == vp){
				result.add(vl);
			}
		}
		return result;
	}
	
	public void CheckVVTarget(VVProcedure vp){
		VVCase vc = getVVCase(vp);
		EList<VVTarget> vvtList = vc.getVvTarget();
		if (vvtList.size() != 1)
		 {
			 Utils.showErrorMessage("The VVCase does not contain exactly one VVTarget element. ");
	 				return;
		 }
		
		 VVTarget vvt = vvtList.get(0);
		
		 
		 EList<VVTarget_element> vvt_eList= vvt.getElement();
		if (vvt_eList.size() != 2)
		 {
			 Utils.showErrorMessage("The VVTarget does not contain exactly two VVTarget_elements (SUT+Environment). ");
	 				return;
		 }
		 
		boolean bFoundSUT = false;
		boolean bFoundEnvironment = false;
		
		for (VVTarget_element vvt_e : vvt_eList) {
			
			 Identifiable identifiable = vvt_e.getIdentifiable_target();
			 
			 if (identifiable instanceof DesignFunctionPrototype)
			 {
				 bFoundSUT = true;
			 }
			 else if (identifiable instanceof Environment)
			 {
				 bFoundEnvironment = true;
			 }
			 else
			 {
				 Utils.showErrorMessage("The VVTarget_element Identifiable_target shall be a DesignFunctionPrototype or Environment element. ");
				return;	 
			 }
		}
		
		if (!(bFoundEnvironment))
				{
					Utils.showErrorMessage("Failed to find environment element in vvtarget " + vvt.getShortName() );
		}

		if (!(bFoundSUT))
				{
					Utils.showErrorMessage("Failed to find a DesignFunctionPrototype element (SUT) in vvtarget " + vvt.getShortName() );
		}

		
	}	
	
	
	public DesignFunctionPrototype findSUT(VVProcedure vp)
	{
		VVCase vc = getVVCase(vp);
		EList<VVTarget> vvtList = vc.getVvTarget();
		//know exactly 1 element
		VVTarget vvt = vvtList.get(0);
		 EList<VVTarget_element> vvt_eList= vvt.getElement();
		 for (VVTarget_element vvt_e : vvt_eList) {
			 Identifiable identifiable = vvt_e.getIdentifiable_target();
			 if (identifiable instanceof DesignFunctionPrototype)
			 {
				 return (DesignFunctionPrototype)identifiable;
			 }
		 }
		 //shall not ocurr
		 return null;
	}

	public Environment findEnvironment(VVProcedure vp)
	{
		VVCase vc = getVVCase(vp);
		
		EList<VVTarget> vvtList = vc.getVvTarget();

		//know exactly 1 element
		VVTarget vvt = vvtList.get(0);
		 EList<VVTarget_element> vvt_eList= vvt.getElement();
		 for (VVTarget_element vvt_e : vvt_eList) {
			 Identifiable identifiable = vvt_e.getIdentifiable_target();
			 if (identifiable instanceof Environment)
			 {
				 return (Environment)identifiable;
			 }
		 }
		 
		 Utils.showErrorMessage("Failed to find Environment element in vvtarget " + vvt.getShortName());
		 
		 //shall not ocurr
		 return null;
	}

		
	public List<FunctionFlowPort> findPortsInDesignFunctionType(DesignFunctionType dft, EADirectionKind dir){
	
		EList<FunctionPort> portList = dft.getPort();
		List<FunctionFlowPort> fportList = new ArrayList<FunctionFlowPort>(); 
		for (FunctionPort fp:portList)
		{
			if (fp instanceof FunctionFlowPort)
			{
				FunctionFlowPort ffp = (FunctionFlowPort)fp;
				if (ffp.getDirection() == dir)
				{
					//Improvement: Check if the port is connected to anything, else filter it away.
					fportList.add(ffp);	
				}
			}
		}

		return fportList;

	}
    /***
     * Main function for translating an ECore model to a simulink model
     *
	 *
     * @param experimentSetup
     * @return
     */
	public SimulinkSystem Translate(DesignFunctionPrototype dfpSUT, Environment environment){
		
		//Collect timing annotations from the model
		mapDFTExecutionInfo = findExecutionInfo((EAElement)dfpSUT);
		
		DesignFunctionType dftSUT = dfpSUT.getType();
		
		monitors = findMonitors(dftSUT, environment);
		
	    //create the top level system of the simulink model - just ports and a single subsystem
		SimulinkSystemBuilder sTopLevelBuilder = new SimulinkSystemBuilder(true);
		sTopLevelBuilder.setName("FMUSim");
	    
	    
		//Add SUT flowports in as simulink inports on top level = repeatingsequenceinterpolated
		List<FunctionFlowPort> ffpInList = findPortsInDesignFunctionType(dftSUT, EADirectionKind.IN);
		for (FunctionFlowPort ffp:ffpInList){
			sTopLevelBuilder.addInport(ffp.getShortName());
		}

		//Add Monitor flowports out as simulink top-level outports = scope
		for (DesignFunctionPrototype monitor : monitors){		
			for (FunctionPort monport : monitor.getType().getPort()){
				FunctionFlowPort ffp = (FunctionFlowPort) monport;
				if (ffp.getDirection() == EADirectionKind.OUT)
				{
					sTopLevelBuilder.addOutport(ffp.getShortName());
				}
			}
		}

		//The start path will be like "Top_Package_CompReuse/MySystemModel/DesignLevel/pSUTCompReuse"
		String pathDFP = EastADLURIFactory.getAbsoluteQualifiedName(dfpSUT);

		//Add SUT top-level subsystem
		SimulinkSystemBuilder subBuilder = sTopLevelBuilder.addTopLevelSubsystem(pathDFP, dfpSUT);
		
		
		recursiveTranslate (dfpSUT, pathDFP, subBuilder, 1);

		//when all DFP:s have been added, we can add event connections between them
		SimulinkSystemBuilder.addEventConnections();
		
		//Add Lines for top level (Delegate connectors)
		sTopLevelBuilder.addTopLevelDelegateConnectorLines(subBuilder);

		//Add Environment
		addEnvironment(environment, dftSUT, sTopLevelBuilder);
				
		return sTopLevelBuilder.getSimulinkSystem();
		
		
		
	}

	//Environment model DFP and ClampConnectors from Environment element 
	private void addEnvironment(Environment environment, DesignFunctionType dftSUT, SimulinkSystemBuilder topLevelBuilder){

		//Add Environment Model DFP
		DesignFunctionPrototype dfpEnv = (DesignFunctionPrototype)environment.getEnvironmentModel();

		//The start path will be like "EASTADLExtension/EnvironmentModel/pEnvironemt_CompReuse"
		String pathDFPEnv = EastADLURIFactory.getAbsoluteQualifiedName(dfpEnv);
	
		SimulinkSystemBuilder envSubBuilder = topLevelBuilder.addTopLevelSubsystem(pathDFPEnv, dfpEnv);

				
		recursiveTranslate(dfpEnv, pathDFPEnv, envSubBuilder, 1);
		
		//Add clampconnectors
		EList<ClampConnector> clampConnectors = environment.getClampConnector();
		for (ClampConnector cc:clampConnectors){
			if (!CheckClampConnector(cc, dftSUT, dfpEnv)){
				return;
			}
		
			EList<ClampConnector_port> cc_ports = cc.getPort();
			
			FunctionFlowPort ffpSource = (FunctionFlowPort)cc_ports.get(0).getFunctionPort();
			EList<FunctionPrototype> fpSource = cc_ports.get(0).getFunctionPrototype();
			String dfpPathSource = getPrototypePath(fpSource);

			FunctionFlowPort ffpDest = (FunctionFlowPort)cc_ports.get(1).getFunctionPort();
			EList<FunctionPrototype> fpDest = cc_ports.get(1).getFunctionPrototype();
			String dfpPathDest = getPrototypePath(fpDest);

			topLevelBuilder.addGotoFromConnection(cc.getShortName(), dfpPathSource, ffpSource, dfpPathDest, ffpDest);
			
		}
	}
	
	

	//level 1 = the system in the subsystem of the top-level. Used to guard against infinite loops (which is possible to define in the EAST-ADL model). 
	//adds the dfp to the simulink system parent block
	//the parent system block is either the top level system block, or a system block in a subsystem.
	public void recursiveTranslate (DesignFunctionPrototype parentDFP, String pathDFP, SimulinkSystemBuilder parentSystemBuilder, int level){
			
		if (level == 1000)
		{
			Utils.showErrorMessage("Translate recursion has reached depth level = 1000. There is probably infinite recursion in the model definition.");
			return;
		}
		
		DesignFunctionType parentDFT = parentDFP.getType();
		
		//Add inports
		List<FunctionFlowPort> ffpInList = findPortsInDesignFunctionType(parentDFT, EADirectionKind.IN);
		for (FunctionFlowPort ffp:ffpInList){
			parentSystemBuilder.addInport(ffp.getShortName());
		}

		//Add outports
		List<FunctionFlowPort> ffpOutList = findPortsInDesignFunctionType(parentDFT, EADirectionKind.OUT);
		for (FunctionFlowPort ffp:ffpOutList){		
			//adds outports as scope
			parentSystemBuilder.addOutport(ffp.getShortName());
		}

		
		//add designfunctionprototypes as subsystems (leafs will get FMUBlock and other stuff in them) 
		EList<DesignFunctionPrototype> dfpList = parentDFT.getPart();
		for (DesignFunctionPrototype dfp:dfpList){
			
			DesignFunctionType dft = dfp.getType();
			String path = pathDFP + "/" + dfp.getShortName();
			
			if (!IsLeaf(dft)){
				SimulinkSystemBuilder subsystemBuilder = parentSystemBuilder.addSubsystem(path, dfp, dfp.getShortName(), false);
				//Improvement: implement warning if execution info exists (will be ignored)
				recursiveTranslate (dfp, path, subsystemBuilder, level + 1);

			}
			else{
				//A leaf
				
				boolean bIsMonitor = isMonitor(dfp); 

				//Add a subsystem that will contain whole pattern
				SimulinkSystemBuilder subsystemBuilder = parentSystemBuilder.addSubsystem(path, dfp, dfp.getShortName(), bIsMonitor);
				
				//Add ports for the subsystem
				recursiveTranslate (dfp, path, subsystemBuilder, level + 1);

				List<FunctionFlowPort> inports = findPortsInDesignFunctionType(dft, EADirectionKind.IN);
				List<FunctionFlowPort> outports = findPortsInDesignFunctionType(dft, EADirectionKind.OUT);


				//obtain execution info 
				DFTExecutionInfo executionInfo = mapDFTExecutionInfo.get(dft);
				if (executionInfo == null){
					Utils.showErrorMessage("No behaviour specified for leaf function prototype " + dfp.getShortName());
					return;	
				}

				if (executionInfo instanceof TimeTriggeredDFTExecutionInfo)
				{
					//Add a subsystem that will contain whole pattern
					subsystemBuilder.addTimeTriggedDFPLeafPattern(dfp, bIsMonitor, (TimeTriggeredDFTExecutionInfo)executionInfo, inports, outports);
				}
				else if (executionInfo instanceof EventTriggeredDFTExecutionInfo)
				{   
					//Add a subsystem that will contain whole pattern
					PortPrototype eventSource = getEventSource(dfp, ((EventTriggeredDFTExecutionInfo)executionInfo).getPort());
			
//					String pathEventSourceDFP = get
					
					subsystemBuilder.addEventTriggedDFPLeafPattern(dfp, bIsMonitor, (EventTriggeredDFTExecutionInfo)executionInfo, inports, outports, eventSource);
				}		
				else {
						
					//Continous (or raw user specified function)
					subsystemBuilder.addRawUserModelPattern(dfp, executionInfo);
				}
			}
	

		} //for dfp

		//Add functionconnectors as lines
		EList<FunctionConnector> fcList = parentDFT.getConnector();
		for (FunctionConnector fc:fcList){
			EList<FunctionConnector_port> portList = fc.getPort();
			if (!CheckFunctionConnector(fc)){
				return;
			}
			
			parentSystemBuilder.addLine(fc);
			
		}
						
	}

	//Lines reaching ffp port in dfp
	private List<FunctionConnector> findLinesReaching(DesignFunctionPrototype dfp, FunctionFlowPort ffp)
	{
		List<FunctionConnector> lines = new ArrayList<FunctionConnector>();
				
		//find parent type of dfp, i.e. where the lines are 
		EAElement ea = (EAElement)dfp;
		DesignFunctionType parentDFT = (DesignFunctionType)ea.eContainer();
		
		EList<FunctionConnector> fcList = parentDFT.getConnector();
		
		for (FunctionConnector fc : fcList) {
		   EList<FunctionConnector_port> fcp = fc.getPort();
		   if (fcp.size() == 2)
		   {
			   if (((fcp.get(0).getFunctionPrototype() == dfp) && (fcp.get(0).getFunctionPort() == ffp)) ||
			   		(fcp.get(1).getFunctionPrototype() == dfp) && (fcp.get(1).getFunctionPort() == ffp))
			   {
				   lines.add(fc);
			   }
		   }	
		}
		return lines;
	}
	
	//Finds dfp that is connected to ffp, in type dft
	private PortPrototype findDFPfromPort(DesignFunctionType dft, FunctionFlowPort ffp){
	
	EList<FunctionConnector> fcList = dft.getConnector();
		
		for (FunctionConnector fc : fcList) {
		   EList<FunctionConnector_port> fcp = fc.getPort();
		   if (fcp.size() == 2)
		   {
			   if ((fcp.get(0).getFunctionPrototype() == null) && (fcp.get(0).getFunctionPort() == ffp))
			   {
				   return new PortPrototype((DesignFunctionPrototype)fcp.get(1).getFunctionPrototype(), (FunctionFlowPort)fcp.get(1).getFunctionPort());
			   }
			   else if ((fcp.get(1).getFunctionPrototype() == null) && (fcp.get(1).getFunctionPort() == ffp))
			   {
				   return new PortPrototype((DesignFunctionPrototype)fcp.get(0).getFunctionPrototype(), (FunctionFlowPort)fcp.get(0).getFunctionPort());
			   }

		   }	
		}

	   	Utils.showErrorMessage("Found no line connected to port " + ffp.getShortName() + " in functiontype " + dft.getShortName());
	   	return null;

	}
	
	
	//FInds the EAST-ADL objects corresponding to the simulink block & Port that is connected to the eventPort with a single line
	//
	//In: The DFP we shall identify event source for
	//In: eventPort - out port of the dfp which the outgoing event is connected to 
	//
	//Out: the event source (DesignFunctionPrototype & FunctionPort)
	private PortPrototype getEventSource(DesignFunctionPrototype dfp, FunctionFlowPort eventPort)
	{
		List<FunctionConnector> lines = findLinesReaching(dfp, eventPort);
		PortPrototype eventSource = new PortPrototype();
		
		if (lines.size() != 1)
		{
			 Utils.showErrorMessage("There shall be exactly one line connected to event port for " + dfp.getShortName());
			return null;			
		}
	 
		FunctionConnector fc = lines.get(0);
		EList<FunctionConnector_port> fc_ports = fc.getPort();
		
		int index = 0;
		if (fc_ports.get(0).getFunctionPort() == eventPort)
		{
			index = 1;
		}
		FunctionFlowPort eventSourceFFP = (FunctionFlowPort)fc_ports.get(index).getFunctionPort();
		DesignFunctionPrototype eventSourceDFP = (DesignFunctionPrototype)fc_ports.get(index).getFunctionPrototype();
		eventSource.setPort(eventSourceFFP);
		eventSource.setFunctionPrototype(eventSourceDFP);
		
		return eventSource;
	}

	
	
	public boolean IsLeaf(DesignFunctionType dft){
		return dft.getPart().isEmpty();
	}
	
	public boolean IsLeaf(DesignFunctionPrototype dfp){
		return IsLeaf(dfp.getType());
	}
	
	
	
	public boolean CheckFunctionConnector(FunctionConnector fc){

		EList<FunctionConnector_port> portList = fc.getPort();

		if (portList.size() != 2)
		{
			 Utils.showErrorMessage("The FunctionConnector " + fc.getShortName() + " does not have 2 ports.");
				return false;	 
		}
		
		for (int p=0; p<2;p++)
		{
			FunctionConnector_port fcp = portList.get(p);
			FunctionPort fPort = fcp.getFunctionPort();

			//there must be a port element
			if (fPort == null)
			{
	  		    Utils.showErrorMessage("FunctionConnector_port for FunctionConnector" + fc.getShortName() + " has an invalid Function Port");
				return false;	 
				
			}
		
			if (!(fPort instanceof FunctionFlowPort)){
				Utils.showErrorMessage("Only FunctionFlowPort supported. " + fPort.getShortName() + " has wrong type. ");
				return false;	 
			}
			
		}
		return true;	
	}
		
	public boolean CheckClampConnector(ClampConnector cc, DesignFunctionType experimentSetup, DesignFunctionPrototype envModel){

		EList<ClampConnector_port> portList = cc.getPort();

		if (portList.size() != 2)
		{
			 Utils.showErrorMessage("The ClampConnector " + cc.getShortName() + " does not have 2 ports.");
				return false;	 
		}
		
		for (int p=0; p<2;p++)
		{
			ClampConnector_port ccp = portList.get(p);
			FunctionPort fPort = ccp.getFunctionPort();
			EList<FunctionPrototype> fProtList = ccp.getFunctionPrototype();
			
		
			
			
			
			//there must be a port element
			if (fPort == null)
			{
	  		    Utils.showErrorMessage("ClampConnector_port for ClampConnector" + cc.getShortName() + " has an invalid Function Port");
				return false;	 
				
			}

			if (!(fPort instanceof FunctionFlowPort)){
				Utils.showErrorMessage("Only FunctionFlowPort supported. " + fPort.getShortName() + " has wrong type. ");
				return false;	 
			}

			
			//The fProtList could be any size - it's an instref.context
		
			if (!(fProtList.get(0) instanceof DesignFunctionPrototype)){
				Utils.showErrorMessage("Only DesignFunctionType supported. " + fProtList.get(0).getShortName() + " has wrong type. ");
				return false;	 
			}
			
			//make sure the dfp is either part of the environment model or part of the SUT model - skip for now
			//Utils.showErrorMessage("ClampConnector DesignFunctionPrototype must be part of the environment model or a part of the SUT");
		
		}
			
		
		return true;	
	}

	
	
	/**
	 * Returns the Root Package of the tree that Eobj is a member of as an EAElement.
	 * @param eObj (typically selected tree element)
	 * @return
	 */
	public EAPackage getRootPackage(EObject eObj){
		EAXML temp;
		temp = (EAXML)EcoreUtil.getRootContainer(eObj);
		
		return temp.getTopLevelPackage().get(0);
	}

	/**
	 * Returns the Root Package of the tree that Eobj is a member of as an EAElement.
	 * @param eObj (typically selected tree element)
	 * @return
	 */
	public EList<EAPackage> getRootPackages(EObject eObj){
		EAXML temp;
		temp = (EAXML)EcoreUtil.getRootContainer(eObj);
		
		return temp.getTopLevelPackage();
	}
	
	
	
	
		/***
	 * 
	 * Obtains timing & behavior annotations for the DFTs.
	 * 
	 * All:
	 * filename of fmu/mdl
	 * execution time (WCET)
	 * 
	 * Time Trigged: 
	 * execution period (T)
	 * 
	 * Event trigged: (when no ExecutionTime Constraint or t=0)
	 * event flowport
	 * 
	 * @param eObj any EObject to specify the right ECore tree
	 * @return
	 */
	public Map<DesignFunctionType, DFTExecutionInfo> findExecutionInfo(EObject eObj){
	
		Map<DesignFunctionType, DFTExecutionInfo> mapDFTExecutionInfo = new HashMap<DesignFunctionType, DFTExecutionInfo>();

		//Find Behavior element
		EAElement e = FindElementByMetaclass(eObj, "Behavior");
		
		if (e == null){
			Utils.showErrorMessage("Failed to find <<Behavior>> element.");
			return null;
		}
		Behavior behavior = (Behavior)e;

		//Find Timing element
		EAElement e2 = FindElementByMetaclass(eObj, "Timing");
		
		if (e2 == null){
			Utils.showErrorMessage("Failed to find <<Timing>> element.");
			return null;
		}
		Timing timing = (Timing)e2;

		
		//Function triggers
		EList<FunctionTrigger> ftList = behavior.getFunctionTrigger();
		
		for (FunctionTrigger ft : ftList){

			//ft
			FunctionType functionType = ft.getFunction();

			
			//TIME or EVENT
			TriggerPolicyKind tpKind = ft.getTriggerPolicy();
			boolean bIsTimeTrigged = (tpKind == TriggerPolicyKind.TIME);
			
			DFTExecutionInfo ei = mapDFTExecutionInfo.get((DesignFunctionType)functionType);
			if (ei != null){
				Utils.showErrorMessage("Error two function triggers have same function type " + functionType.getShortName());
				return null;
			}
			
			if (bIsTimeTrigged)
			{
				ei = new TimeTriggeredDFTExecutionInfo();
			}
			else //EVENT triggered
			{
				ei = new EventTriggeredDFTExecutionInfo();
				EList<FunctionPort> ports = ft.getPort();
			
				if (ports.size() != 1) {
					Utils.showErrorMessage("The EVENT functiontrigger " + ft.getShortName() + " must have exactly one port defined.");
					return null;
				}
				
				if (!(ports.get(0) instanceof FunctionFlowPort)) {
					Utils.showErrorMessage("The port of " + ft.getShortName() + " must be a FunctionFlowPort ");
					return null;
				}
				
				//Set event port @ trigged dft
				((EventTriggeredDFTExecutionInfo)ei).setPort((FunctionFlowPort)ports.get(0));
				
										
			}				
			
			if (functionType instanceof DesignFunctionType)
			{
				DesignFunctionType dft = (DesignFunctionType)functionType;

				EList<TimingConstraint> constraintsList = timing.getConstraint();
			
				PeriodicConstraint pc = null;
			    ExecutionTimeConstraint etc  = null;
				boolean bFoundPC = false;
				boolean bFoundET = false;
				
				//Find Execution time constraint && TimingConstraint 
				for (int i=0; i<constraintsList.size() && !(bFoundPC && bFoundET); i++){
					TimingConstraint tc = constraintsList.get(i);
					if (tc instanceof PeriodicConstraint){	
						pc = (PeriodicConstraint)tc;
						Event event = pc.getEvent();
						if (event instanceof EventFunction){
							EventFunction eventFunction = (EventFunction)event;
							if (eventFunction.getFunctionType() == dft)
								bFoundPC = true;
						}
					}
					else if (tc instanceof ExecutionTimeConstraint){
						etc = (ExecutionTimeConstraint)tc;
						Event event = etc.getStart();
						if (event instanceof EventFunction){
							EventFunction eventFunction = (EventFunction)event;
							if (eventFunction.getFunctionType() == dft)
								bFoundET = true;
						}
						
					}
					
					
				}
			
				
				//take the periodic time from the pc name attribute
				if (bFoundPC && bIsTimeTrigged)
				{
					try {
							float f = Float.parseFloat(pc.getName());
							
							if (f == 0)
							{
								Utils.showErrorMessage("periodic time 0 not allowed for TIME triggered function");
								return null;
							}
							
							((TimeTriggeredDFTExecutionInfo)ei).setExecutionPeriod(f);
							
					
					} catch (NumberFormatException ex) {
							Utils.showErrorMessage("Periodic constraint " + pc.getShortName() + " name attribute does not contain a valid float. Name = " + pc.getName());
					}
				}
				

				//take the execution time from the etc name attribute
				if (bFoundET)
				{
					try {
						float f = Float.parseFloat(etc.getName());
						
						ei.setExecutionTime(f);
					} 
					catch (NumberFormatException ex) {
						Utils.showErrorMessage("Timing constraint " + etc.getShortName() + " name attribute does not contain a valid float.");
					}
				}
	
				
				//checks
				if (bIsTimeTrigged && !bFoundPC){
					Utils.showErrorMessage("The time trigged function type " + ft.getFunction().getShortName() + " must have a periodic constraint ");
				}
				
				
				//add time trigger info to map
				mapDFTExecutionInfo.put(dft, ei);
				
			}
		}
		
		addBehaviourFilenames(behavior, mapDFTExecutionInfo);
		
		return mapDFTExecutionInfo;
	
	}
	

	
	private void addBehaviourFilenames(Behavior behavior,
				Map<DesignFunctionType, DFTExecutionInfo> mapDFTExecutionInfo) {
			
		EList<FunctionBehavior> bList = behavior.getBehavior();
		for (FunctionBehavior fb : bList){
			
			
			if (!(fb.getFunction() instanceof DesignFunctionType)){
				Utils.showErrorMessage("FunctionBehaviour " + fb.getShortName() + " is not DesignFunctionType.");
				return;
			}
			
			DesignFunctionType dft = (DesignFunctionType)fb.getFunction();
			if (dft != null)
			{
				DFTExecutionInfo ei  = mapDFTExecutionInfo.get(dft);
				if (ei == null)				{
					//Function with behavior only, no triggering i.e continous or user-defined triggering
						
					ei = new DFTExecutionInfo();
					mapDFTExecutionInfo.put(dft,  ei);				
				}
				ei.setFilenameBehaviour(fb.getPath());
			}			
		}
	}
		

	//Traverse all packages on root level, in the eaxml specified by any
	private EAElement FindElementByMetaclass(EObject any, String targetEClass)
	{
		EList<EAPackage> rootPackages = getRootPackages(any);

		for (EAPackage eaPackage : rootPackages){
			EAElement eaElement = recursiveFindElementByMetaclass(eaPackage, targetEClass);
			if (eaElement != null){
				return eaElement;
			}
		}
		return null;
		
	}
	
	//returns the first hit for targetEClass (see also ECoreUtil.getObjectByType)
	private EAElement recursiveFindElementByMetaclass(EAElement eaElement, String targetEClass)
	{
		if (eaElement.eClass().getName().equals(targetEClass))
		{
			return eaElement;
		}
		
		if(eaElement instanceof EAPackage)
		{
			EAPackage eap = (EAPackage) eaElement;
			//check packages in eap
			if(eap.getSubPackage()!=null && eap.getSubPackage().size()>0)
			{
				for(EAPackage p: eap.getSubPackage())
				{
					EAElement e = recursiveFindElementByMetaclass(p, targetEClass);
					if (e != null){
						return e;
					}
				}					
			}
			//Check elements in eap
			if(eap.getElement()!=null)
			{
				for(EAPackageableElement pE : eap.getElement())
				{
					if (pE.eClass().getName().equals(targetEClass))
					{
						return pE;
					}
				}
			}
		}
		else
		{
			//not a package
		}
		return null;
	}
	
	//Traverse all packages on root level, in the eaxml specified by any
	private EAElement FindElementByShortNameAndMetaClass(EObject any, String name, String metaClass)
	{
		EList<EAPackage> rootPackages = getRootPackages(any);
		for (EAPackage eaPackage : rootPackages){
				EAElement eaElement = recursiveFindElementByShortNameAndMetaClass(eaPackage, name, metaClass);
				if (eaElement != null){
					return eaElement;
				}
			}
			return null;
	}
		
	
	//returns the first hit for nametargetEClass (see also ECoreUtil.getObjectByType)
	private EAElement recursiveFindElementByShortNameAndMetaClass(EAElement eaElement, String name, String metaClass)
	{
		if (eaElement.getShortName().equals(name) && eaElement.eClass().getName().equals(metaClass))
		{
			return eaElement;
		}
		
		if(eaElement instanceof EAPackage)
		{
			EAPackage eap = (EAPackage) eaElement;
			//check packages in eap
			if(eap.getSubPackage()!=null && eap.getSubPackage().size()>0)
			{
				for(EAPackage p: eap.getSubPackage())
				{
					EAElement e = recursiveFindElementByShortNameAndMetaClass(p, name, metaClass);
					if (e != null){
						return e;
					}
				}					
			}
			//Check elements in eap
			if(eap.getElement()!=null)
			{
				for(EAPackageableElement pE : eap.getElement())
				{
					if (pE.getShortName().equals(name) && pE.eClass().getName().equals(metaClass))
					{
						return pE;
					}
				}
			}
		}
		else
		{
			//not a package
		}
		return null;
	}
	
	/***
	 * 
	 * 
	 * returns all verify elements that are applicable for the VVProcedure, i.e. a verify element v
	 * is returned if v has "verified by Procedure = vp", or "Verified by Case = parent VVCase of vp" 
	 * 
	 */
	public List<Verify> findApplicableVerify(VVProcedure vp){

		List <Verify> vList = new ArrayList<Verify>();

		//find VVCase
		VVCase vc = getVVCase(vp);

		//find parent "Analysis" Verification&Validation node
		EAElement ea = (EAElement)vc;
		VerificationValidation vv = (VerificationValidation)ea.eContainer();


		//owned relationships of vv
		EList<Relationship> ownedRels = vv.getOwnedRelationship();
		for (Relationship relationship : ownedRels) {
			if (relationship instanceof Verify){
				Verify v = (Verify)relationship;
					if (((v.getVerifiedByCase().size()  != 0) && (v.getVerifiedByCase().get(0) == vc)) ||
						((v.getVerifiedByProcedure().size() != 0) && (v.getVerifiedByProcedure().get(0) == vp))){
						vList.add(v);
					}
			}

		}


		//filter the list of verification elements
		EList<Verify> grossList = vv.getVerify();
		for (Verify v:grossList){
			if (((v.getVerifiedByCase().size() > 0) && (v.getVerifiedByCase().get(0) == vc)) || 
				((v.getVerifiedByProcedure().size() > 0) && (v.getVerifiedByProcedure().get(0) == vp))){
				vList.add(v);
			}	
		}

		return vList;

	}

//	public Map<Requirement, GenericConstraint> findApplicableReqToGenConstraintMap ()

	/***
	 * Returns list of unique requirements analysed by the VVProcedure
	 */
	public List<Requirement> findRequirements(VVProcedure vp){
	
		List<Verify> verifyList = findApplicableVerify(vp);
		Set<Requirement> reqs = new HashSet<Requirement>();
		
		for (Verify verify : verifyList) {
			List<Requirement> rList = verify.getVerifiedRequirement();
			reqs.addAll(rList);
		}
	
		return new ArrayList<Requirement>(reqs);
		
	}
	

	//Finds a monitor outport by name. Assumes all monitor outports have different names.
	public FunctionFlowPort findMonitorOutPort(String portName){
		if (monitors.size() == 0){
			Utils.showErrorMessage("No monitors in list.");
		}

		for (DesignFunctionPrototype mdfp : monitors){
			DesignFunctionType mType = mdfp.getType();
			for (FunctionPort fp : mType.getPort()){
				FunctionFlowPort ffp = (FunctionFlowPort)fp;
				if ((ffp.getDirection() == EADirectionKind.OUT) && ffp.getShortName() == portName) {
					return ffp;
				}
			}
		}
		Utils.showErrorMessage("Failed to find monitor with outport name " + portName);
		return null;
	}
	
	

	
	//Assumes model is ok, no checks
	public FunctionFlowPort findMonitourOutPort(Requirement R){
		RequirementsModel rm = (RequirementsModel)R.eContainer();
		
		EList<Relationship> elements = rm.getOwnedRelationship();
		
		for (Relationship r : elements){
	
			if (r instanceof Refine) {
				Refine refine = (Refine)r;
				
				if (R == refine.getRefinedRequirement().get(0)){
					
					Refine_refinedBy refBy = refine.getRefinedBy().get(0);
					GenericConstraint monitorGC = (GenericConstraint)refBy.getIdentifiable_target();

					FunctionFlowPort out = (FunctionFlowPort)monitorGC.getTarget().get(0);
					
					return out;
					}
				}
			}
		return null;
	}
		
	//Assumes model is ok, no checks
	public EAElement findSatisfyTarget(Requirement R){
			RequirementsModel rm = (RequirementsModel)R.eContainer();
			
			EList<Relationship> elements = rm.getOwnedRelationship();
			
			for (Relationship r : elements){
		
				if (r instanceof Satisfy) {
					Satisfy satisfy = (Satisfy)r;
					
					if (R == satisfy.getSatisfiedRequirement().get(0)){
						
						//we've find the right Satisfy element
						Satisfy_satisfiedBy satBy = satisfy.getSatisfiedBy().get(0);
						EAElement ea = (EAElement)satBy.getIdentifiable_target();
						
						if (!((ea instanceof FunctionFlowPort) ||
								(ea instanceof DesignFunctionPrototype) ||
								(ea instanceof DesignFunctionType))){
								Utils.showErrorMessage("Satisfy target for " + satisfy.getShortName() + " must be FunctionFlowPort, DesignFunctionPrototype or DesignFunctionType.");
						}
						
						
						return ea;
						}
					}
				}
			return null;
		}
	
	
	
	/***
	 * 
	 * all GenericConstraints, that refines the requirement, and whose target is an out port in the ExperimentSetup
	 * 
	 */
	public GenericConstraint findGenericConstraint(Requirement R, Environment environment){
		
		//Assume all refine element that are siblings to R, i.e. hangs under a common "RequirementsModel"
		//The refine & satisfy elements are owned relationships by the RequirementsModel
	
	
		RequirementsModel rm = (RequirementsModel)R.eContainer();
		
		EList<Relationship> elements = rm.getOwnedRelationship();
		
		for (Relationship r : elements){
	
			if (r instanceof Refine) {
				Refine refine = (Refine)r;
				if (!CheckRefine(refine)) return null;
				
				if (R == refine.getRefinedRequirement().get(0)){
					
					Refine_refinedBy refBy = refine.getRefinedBy().get(0);
					GenericConstraint monitorGC = (GenericConstraint)refBy.getIdentifiable_target();

					if (CheckGenericConstraint(monitorGC) && (IsGenericConstraintTargetOutPortinEnvironmentDFP(monitorGC, environment))){
						return monitorGC;
						//assume there are no other GenericConstraints that refines R
					}
				}
			}
		}
		return null;
	}

	
/*
	//saves result in monitors
	public List<DesignFunctionPrototype> findMonitors(DesignFunctionType experimentSetup){

		List<DesignFunctionPrototype> monList = new ArrayList<DesignFunctionPrototype>();
		EAElement root = getRoot(experimentSetup);

		List<GenericConstraint> gcs = findAllGenericConstraints(root);
		
	
		for (GenericConstraint monitorGC : gcs) {
		
			if (CheckGenericConstraint(monitorGC) && (IsGenericConstraintTargetOutportInExperimentSetup(monitorGC, experimentSetup))){
				DesignFunctionPrototype monitor = findMonitor(monitorGC, experimentSetup);
				if (!monList.contains(monitor)){
					monList.add(monitor);
				}
			}
			else {
					Utils.showInformationMessage("Found no DesignFunctionPrototype connected to the generic constraint " + monitorGC.getShortName());
				}	
			} //for
		
		return monList;
	}
*/
	//all monitors are in the EnvironmentModel, but there is also environment dfp:s
	//use GC to find out which are truly monitors
	public List<DesignFunctionPrototype> findMonitors(DesignFunctionType dftSUT, Environment environment){

		List<DesignFunctionPrototype> monList = new ArrayList<DesignFunctionPrototype>();


		
		EAElement root = getRootPackage(dftSUT);
		List<GenericConstraint> gcs = findAllGenericConstraints(root);
		
	
		for (GenericConstraint monitorGC : gcs) {
		
			if (CheckGenericConstraint(monitorGC) && (IsGenericConstraintTargetOutPortinEnvironmentDFP(monitorGC, environment))){
				DesignFunctionPrototype monitor = findMonitor(monitorGC, environment);
				if (!monList.contains(monitor)){
					monList.add(monitor);
				}
			}
			else {
					Utils.showInformationMessage("Found no DesignFunctionPrototype connected to the generic constraint " + monitorGC.getShortName());
				}	
			} //for
		
		return monList;
	}

	

	public List<GenericConstraint> findAllGenericConstraints(EAElement root){
		List<GenericConstraint> gcs = new ArrayList<GenericConstraint>();
	
		EAElement e = FindElementByShortNameAndMetaClass(root, "RequirementsPackage", "EAPackage");
		
		if (e == null){
			Utils.showErrorMessage("Failed to find EAPackage RequirementsPackage.");
			return null;
		}
		EAPackage reqs = (EAPackage)e;

		for (EAPackageableElement eape : reqs.getElement()){
			if (eape instanceof GenericConstraint)
			{
				gcs.add((GenericConstraint)eape);
			}
		}
		
		return gcs;
	}
	
	
	public Requirement findRequirement(String monitorOutPortName, VVProcedure vp){
		
		//Find port object p
		Environment environment = findEnvironment(vp);
			
		FunctionFlowPort p= null;
		
		for (DesignFunctionPrototype m : monitors){
			List<FunctionPort> fpList = m.getType().getPort();
			for (FunctionPort fp : fpList){
				FunctionFlowPort ffp = (FunctionFlowPort)fp;
				if ((ffp.getDirection() == EADirectionKind.OUT) && ffp.getShortName().equals(monitorOutPortName))
					p = ffp;
					continue;
			}
		
		}

	/*		for (FunctionPort fp : portList) {
			if (fp instanceof FunctionFlowPort) {
				FunctionFlowPort ffp = (FunctionFlowPort)fp;
				if ((ffp.getDirection() == EADirectionKind.OUT) && ffp.getShortName().equals(expSetupOutPortName))
					p = ffp;
					continue;
			}
		}
	*/	
		if (p==null)
		{
			Utils.showErrorMessage("Failed to find out port " + monitorOutPortName + " among monitors. ");
			return null;
		}

		//Port object p found
		
		//Find the Requirement R that is related to p
		List<GenericConstraint> genConst = new ArrayList<GenericConstraint>();
		
		List<Verify> verifyList = findApplicableVerify(vp);
		
		for (Verify v: verifyList){
			List<Requirement> Reqs =  v.getVerifiedRequirement();
			for (Requirement R:Reqs){
				GenericConstraint monConstraint = findGenericConstraint(R, environment);
				FunctionFlowPort mffp = (FunctionFlowPort)monConstraint.getTarget().get(0);
				if (mffp == p){
					return R;
				}
			}
		}

		Utils.showErrorMessage("Failed to requirement related to port " + monitorOutPortName + " in ExperimentSetup. ");
		return null;
	}

	//A monitor is a dfp in the environment model, whose outport a gc has as target
	public DesignFunctionPrototype findMonitor(GenericConstraint gc, Environment environment){
		EList<Identifiable> targets = gc.getTarget();
		FunctionFlowPort ffp = (FunctionFlowPort)targets.get(0);
		
		if (!(ffp.getDirection() == EADirectionKind.OUT))
			return null;
		
		DesignFunctionPrototype envModel = (DesignFunctionPrototype)environment.getEnvironmentModel();
		List<DesignFunctionPrototype> envModelParts = envModel.getType().getPart();
		
		for (DesignFunctionPrototype dfp : envModelParts) {
			if (dfp.getType().getPort().contains(ffp))
				return dfp;
		}
		return null;
	}
/*
	//A monitor is a DFP Leaf, that has an outport connected directly, or via composite DFPs, to an outport in ExperimentSetup.
	public DesignFunctionPrototype findMonitor(GenericConstraint gc, DesignFunctionType experimentSetup ){
		EList<Identifiable> targets = gc.getTarget();
		FunctionFlowPort ffp = (FunctionFlowPort)targets.get(0); //only support 1 target port
	
		//start with experimentSetup
		//ffp is an outport of experimentSetup
		
		boolean bStop = false;
		DesignFunctionType dft = experimentSetup;
		DesignFunctionPrototype dfp = null;
		
		//Search through the composition of dfp until there's a leaf
		while (!bStop){
			
			InstRef instRef = findDFPfromPort(dft, ffp);
			if (instRef == null){
				dfp = null;
				bStop = true;
			}
			
			dfp = instRef.getFunctionPrototype();
			if (IsLeaf(dfp)){
				bStop = true;
			}
			else{
				ffp = instRef.getPort();
				dft = dfp.getType();
			}
		}
		return dfp;
	}
*/	
	public boolean isMonitor(DesignFunctionPrototype dfp){
		return monitors.contains(dfp);
	}
	
	public List<FunctionFlowPort> findUsedMonitorOutPorts(VVProcedure vp){
	
		List<GenericConstraint> genericConstraints = findUsedGenConstraints(vp);
		
		List<FunctionFlowPort> ports = new ArrayList<FunctionFlowPort>();
		
		for (GenericConstraint gc: genericConstraints){
				ports.add((FunctionFlowPort)gc.getTarget().get(0));
		}
		
		return ports;
	}

	public List<GenericConstraint> findUsedGenConstraints(VVProcedure vp){
		
		List<GenericConstraint> genConst = new ArrayList<GenericConstraint>();
		
		List<Verify> verifyList = findApplicableVerify(vp);
		
		for (Verify v: verifyList){
			List<Requirement> Reqs =  v.getVerifiedRequirement();
			Environment environment = findEnvironment(vp);
			for (Requirement R:Reqs){
				GenericConstraint monConstraint = findGenericConstraint(R, environment);
				genConst.add(monConstraint);
			}
		}
		
		return genConst;
			
	}

	
	private boolean CheckRefine(Refine refine) {
		
		if (refine.getRefinedRequirement().size() > 1){
			Utils.showErrorMessage("Refine element " + refine.getShortName() + " is connected to more than one requirement, not supported.");
			 return false;	
		}
		
		if (refine.getRefinedBy().size() > 1){
			Utils.showErrorMessage("Refine element " + refine.getShortName() + " has more than one Refined_refinedBy element, not supported.");
			 return false;	
		}

		Refine_refinedBy refBy = refine.getRefinedBy().get(0);
		if (!refBy.getIdentifiable_context().isEmpty()){
			Utils.showErrorMessage("Refine_refinedBy element of Refine element " + refine.getShortName() + " has a non-empty context, not supported.");
			 return false;	
		
		}
		
		if (!(refBy.getIdentifiable_target() instanceof GenericConstraint)){
			Utils.showErrorMessage("Refine_refinedBy element of Refine element " + refine.getShortName() + " has a target that is not GenericConstraint, not supported.");
			 return false;	
		
		}

		return true;
	}

	//Is the generic constraint target a monitor outport?
	private boolean IsGenericConstraintTargetOutPortinEnvironmentDFP(GenericConstraint monitorGC, Environment environment){
		EList<Identifiable> targets = monitorGC.getTarget();
		FunctionFlowPort ffp = (FunctionFlowPort)targets.get(0);
		
		if (!(ffp.getDirection() == EADirectionKind.OUT))
			return false;
		
		DesignFunctionPrototype envModel = (DesignFunctionPrototype)environment.getEnvironmentModel();
		List<DesignFunctionPrototype> envModelParts = envModel.getType().getPart();
		
		for (DesignFunctionPrototype dfp : envModelParts) {
			if (dfp.getType().getPort().contains(ffp))
				return true;
		}
		return false;
	}
	
	/*
		//Target shall always be an outport in the experimentSetup, even for SUTinternal monitors
		public boolean IsGenericConstraintTargetOutportInExperimentSetup(GenericConstraint monitor, VVProcedure vp){
			DesignFunctionType experimentSetup = findExperimentSetup(vp);
			return IsGenericConstraintTargetOutportInExperimentSetup(monitor, experimentSetup);
		}
		
		//Target shall always be an outport in the experimentSetup, even for SUTinternal monitors
		public boolean IsGenericConstraintTargetOutportInExperimentSetup(GenericConstraint monitor, DesignFunctionType experimentSetup){
			EList<Identifiable> targets = monitor.getTarget();
			FunctionFlowPort ffp = (FunctionFlowPort)targets.get(0);
					
			return ((ffp.getDirection() == EADirectionKind.OUT) && experimentSetup.getPort().contains(ffp));
		}
	*/
	
		public boolean CheckGenericConstraint(GenericConstraint monitor){
			
			EList<Identifiable> targets = monitor.getTarget();
			
			if (targets.size() == 0)
			{
				 Utils.showErrorMessage("GenericConstraint " + monitor.getShortName() + " has no target defined. ");
				 return false;
			}
		
			
			if (targets.size() > 1)
			{
				 Utils.showErrorMessage("GenericConstraint " + monitor.getShortName() + " has more than one target, not supported.");
				 return false;
			}
		
			if (!(targets.get(0) instanceof FunctionFlowPort)){
				 Utils.showErrorMessage("GenericConstraint " + monitor.getShortName() + " target required to be a FunctionFlowPort. ");
				 return false;
		
			}
			
			EAValue eValue = monitor.getValue();
			if (!(eValue instanceof EABooleanValue)){
			
				 Utils.showErrorMessage("GenericConstraint " + monitor.getShortName() + " does not have a EABooleanValue.");
				 return false;
			
			}
			EABooleanValue eaBool = (EABooleanValue)eValue;
			
			if (!(eaBool.getValue() == true)){
				 Utils.showErrorMessage("GenericConstraint " + monitor.getShortName() + " does not have a EABooleanValue with value true.");
				 return false;
			}
				
			return true;
		
		}
		

		//sorted context: A list of FunctionPrototypes that are sorted, first element is most detailed ("furthest in")
		//Example: 
		// [pX, pComposition1, pSystemUnderTest, pSUT_CompReuse] ------ returns -----> 
		//"Top_Package_CompReuse/MySystemModel/DesignLevel/pSuTCompReuse/pSystemUnderTest/pComposition1/pX"
		public String getPrototypePath(EList<FunctionPrototype> sortedContext){
			String path = "";
		
			int nSize = sortedContext.size();
			
			if (nSize == 0){
				Utils.showErrorMessage("getPrototype called with an empty sortedCcontext");
				return "";
			}
			
			FunctionPrototype fpLast = sortedContext.get(nSize-1);
			path = EastADLURIFactory.getAbsoluteQualifiedName(fpLast); //gives no "/" in the end
			
			ListIterator li = sortedContext.listIterator(nSize - 1); //skip last element, i.e. point between element n-1 and n

			// Iterate in reverse.
			while(li.hasPrevious()) {

				FunctionPrototype fp = (FunctionPrototype)li.previous();
				path = path + "/" + fp.getShortName();
			}
			
			return path;
		}
		
		
		
}
