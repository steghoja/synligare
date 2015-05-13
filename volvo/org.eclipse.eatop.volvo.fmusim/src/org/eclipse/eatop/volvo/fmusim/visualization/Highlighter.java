package org.eclipse.eatop.volvo.fmusim.visualization;



import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.management.modelmbean.RequiredModelMBean;

import org.eclipse.eatop.common.resource.EastADLURIFactory;
import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignFunctionType;
import org.eclipse.eatop.eastadl21.EAElement;
import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.eatop.eastadl21.FunctionFlowPort;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.VVCase;
import org.eclipse.eatop.eastadl21.VVLog;
import org.eclipse.eatop.eastadl21.VVProcedure;
import org.eclipse.eatop.eastadl21.VerificationValidation;
import org.eclipse.eatop.eastadl21.impl.RequirementImpl;
import org.eclipse.eatop.volvo.fmusim.FMUSimViewXML;
import org.eclipse.eatop.volvo.fmusim.FMUSimViewXMLManager;
import org.eclipse.eatop.volvo.fmusim.HighlightedVVLogsinEAXML;
import org.eclipse.eatop.volvo.fmusim.Utils;
import org.eclipse.eatop.volvo.fmusim.VVLogTextXML;
import org.eclipse.eatop.volvo.fmusim.VVLogTextXMLManager;
import org.eclipse.eatop.volvo.fmusim.ecore.ModelProcessor;
import org.eclipse.eatop.volvo.fmusim.ecore.ModelUpdate;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EDataType.Internal.ConversionDelegate.Factory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sphinx.emf.util.EcorePlatformUtil;
import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

public class Highlighter {
	//singleton
	private static Highlighter instance = null;
	private Highlighter() {

	}
	public static synchronized Highlighter getInstance() {
		if (instance == null) {
			instance = new Highlighter();
		}
		return instance;
	}


	//For an EAXML, list all VVLogs currently highlighted
	Map<EAXML, List<VVLog>> highlightedEAXMLVVLogsMap = new HashMap<EAXML, List<VVLog>>();


	//For each VVProcedure with a highlighted VVLog, list the single VVLog
	Map<VVProcedure, VVLog> highlightedVVProc2LogsMap = new HashMap<VVProcedure, VVLog>();


	class VVLogsAndStatus{
		Set<VVLog> vvLogs; //highlighted VVLogs that is related to the port
		boolean status;    //ok/nok, considering all highlighted VVLogs
	}

	
/*	
	class VVLogsAndStatusAndReqs extends VVLogsAndStatus {
		List<Requirement> requirements; //the reqs that the highlighted VVLogs test
		List<Integer> refcount;         //refcount[i] = #VVLogs that tests requirements[i]
	}
*/
	
	//For each analyzed requirement R, keep a set of all highlighted VVLogs that has bearing on R and status 
	Map<Requirement, VVLogsAndStatus> requirementMap = new HashMap<Requirement, VVLogsAndStatus>();

	//For all requirement with a verdict, state Passed/Failed
//	Map<Requirement, Boolean> requirementStatusMap = new HashMap<Requirement, Boolean>();

	//For each functionflowport FFP with a verdict, keep a list of  the hightligthed VVLogs that are related to the port
	Map<FunctionFlowPort, VVLogsAndStatus> portMap = new HashMap<FunctionFlowPort, VVLogsAndStatus>();

	//For each DesignFunction with a verdict, keep a list of the hightligthed VVLogs that are related to the function
	Map<DesignFunctionType, VVLogsAndStatus> dftMap = new HashMap<DesignFunctionType, VVLogsAndStatus>();

	//For each DesignFunctionPrototype with a verdict, keep a list of the hightligthed VVLogs that are related to the dfp
	Map<DesignFunctionPrototype, VVLogsAndStatus> dfpMap = new HashMap<DesignFunctionPrototype, VVLogsAndStatus>();

	
	ModelUpdate modelUpdate = new ModelUpdate();	
	ModelProcessor modelProcessor = new ModelProcessor();	




	public void UnHighLight (VVLog vvLog){
		//Remove VVLog from highlightedEAXMLVVLogsMap
		EAXML eaxml = (EAXML)EcoreUtil.getRootContainer(vvLog);
		List<VVLog> highlightedVvLogs = highlightedEAXMLVVLogsMap.get(eaxml);
		if (highlightedVvLogs != null){
			highlightedVvLogs.remove(vvLog);
			
			if (highlightedVvLogs.isEmpty()){
				highlightedEAXMLVVLogsMap.remove(eaxml);	
			}
		}
		
		//Remove VVLog from highlightedVVProc2LogsMap
		VVProcedure vp = vvLog.getPerformedVVProcedure();
		highlightedVVProc2LogsMap.remove(vp);

/*		//Remove VVLog from requirementVVLogMap
		List<Requirement> toRemoveList = new ArrayList<Requirement>();
		for (Map.Entry<Requirement, Set<VVLog>> entry : requirementVVLogMap.entrySet()){

			Set<VVLog> logSet = entry.getValue();
			if (logSet.contains(vvLog)){
				logSet.remove(vvLog);
				if (logSet.isEmpty()){
					toRemoveList.add(entry.getKey());
				}
			}
		}

		//Remove any requirement map entries that are no longer needed
		for (Requirement r: toRemoveList) {
			requirementVVLogMap.remove(r);
			requirementStatusMap.remove(r);
		}
*/
		//removeVVLogFromrRequirementMap(vvLog);
		removeVVLogFromObjMap(requirementMap, vvLog);
		removeVVLogFromObjMap(portMap, vvLog);
		removeVVLogFromObjMap(dfpMap, vvLog);
		removeVVLogFromObjMap(dftMap, vvLog);
		
	}

/*
	//Removes the VVLog from the map
	private  void removeVVLogFromrRequirementMap(VVLog vvLog) {
		//Remove VVLog from portMap
		List<Requirement> toRemoveList = new ArrayList<Requirement>();
		for (Map.Entry<Requirement, VVLogsAndStatus> entry : requirementMap.entrySet()){

			Set<VVLog> logSet = entry.getValue().vvLogs;
			if (logSet.contains(vvLog)){
				logSet.remove(vvLog);
				if (logSet.isEmpty()){
					toRemoveList.add(entry.getKey());
				}
	
			}
		}
		//Remove any portMap entries no longer needed
		for (Requirement obj: toRemoveList) {
			requirementMap.remove(obj);
		}
	}	

*/
	//Removes the VVLog from the map
	private <T extends Object> void removeVVLogFromObjMap(Map<T, VVLogsAndStatus> map, VVLog vvLog) {
		//Remove VVLog from portMap
		List<T> toRemoveList = new ArrayList<T>();
		for (Map.Entry<T, VVLogsAndStatus> entry : map.entrySet()){

			Set<VVLog> logSet = entry.getValue().vvLogs;
			if (logSet.contains(vvLog)){
				logSet.remove(vvLog);
				if (logSet.isEmpty()){
					toRemoveList.add(entry.getKey());
				}
/*	
				//Obtain the requirements related to the vv procedure
				VVProcedure vp = vvLog.getPerformedVVProcedure();
				List<Requirement> reqs = modelProcessor.findRequirements(vp);

				//Decrease refcount for those reqs, and remove those who reach zero
				for (Requirement r : reqs){
					List<Requirement> reqList = entry.getValue().requirements;
					List<Integer> refcountList = entry.getValue().refcount;
					int indx = reqList.indexOf(r);
					
					if (indx == -1){
						Utils.showErrorMessage("removeVVLogFromObjMap: index of failed!");
					}
					
					Integer oldrefcount = entry.getValue().refcount.get(indx);
					if (oldrefcount == 1){
						refcountList.remove(indx);
						reqList.remove(indx);
								
					}
					else{
						refcountList.set(indx, oldrefcount - 1);		
					}
				}*/
			}
		}
		//Remove any portMap entries no longer needed
		for (T obj: toRemoveList) {
			map.remove(obj);
		}
	}	

		
	
	public void UnHighlight (VVProcedure vp)
	{
		VVLog vvLog = highlightedVVProc2LogsMap.get(vp);
		UnHighLight(vvLog);
	}

	public void UnHighlight (VVCase vvcase)
	{
		EList<VVProcedure> vpList = vvcase.getVvProcedure();
		for (VVProcedure vp : vpList) {
			UnHighlight(vp);
		}
	}

	public void UnHighlight (VerificationValidation vv)
	{
		EList<VVCase> vcList = vv.getVvCase();

		for (VVCase vc : vcList) {
			UnHighlight(vc);
		}
	}

	
	public void Highlight (VVLog vvLog)
	{
		//Check if we need to unhighlight another already highlighted vvLog for the common VVProcedure...
		VVProcedure vp = vvLog.getPerformedVVProcedure();
		VVLog oldLog = null;
		if (highlightedVVProc2LogsMap.containsKey(vp)){
			oldLog = highlightedVVProc2LogsMap.get(vp);

			UnHighLight(oldLog);
		}

		//Add the vvLog node to the list of highlighted logs
		EAXML eaxml = (EAXML)EcoreUtil.getRootContainer(vvLog);
		List<VVLog> highlightedVvLogs = highlightedEAXMLVVLogsMap.get(eaxml);
		if (highlightedVvLogs == null){
			highlightedVvLogs = new ArrayList<VVLog>();
			highlightedEAXMLVVLogsMap.put(eaxml, highlightedVvLogs);

		}
		highlightedVvLogs.add(vvLog);
		highlightedVVProc2LogsMap.put(vp, vvLog);


		//TODO: Remove any other vvLog for same VVProcedure


		//Go through the applicable requirements and update the requirementStatusmap
		List<Requirement> applicableReqs = modelProcessor.findRequirements(vp);
		
		for (Requirement r : applicableReqs){

			//calculate highlighting status of R and save in requirementMap
			boolean bPhony = false;
			addVVLog2objMap(requirementMap, vvLog, bPhony, r);
			VVLogsAndStatus v = requirementMap.get(r);
			//calculate highlighting status of R and save in requirementMap
			v.status = NetReqAnalysisResult(v.vvLogs, r);
		
			}

		//Go through the reqs again and color the corresponding monitor output ports
		for (Requirement r: applicableReqs){
			
			//find the corresponding monitor output port
			FunctionFlowPort monPort = modelProcessor.findMonitourOutPort(r);
			addVVLog2objMap(portMap, vvLog, requirementMap.get(r).status, monPort); 
	

			//Satisfy relations
			//find the corresponding satisfy instance_ref.target. We skip instance_ref.context for now since can't color prototype tree.
			EAElement satisfyTarget = modelProcessor.findSatisfyTarget(r);
		
			if (satisfyTarget instanceof FunctionFlowPort){
				FunctionFlowPort port = (FunctionFlowPort)satisfyTarget;
				addVVLog2objMap(portMap, vvLog, requirementMap.get(r).status,port); 
				
			}
			else if (satisfyTarget instanceof DesignFunctionType) {
				DesignFunctionType dft = (DesignFunctionType)satisfyTarget;
				addVVLog2objMap(dftMap, vvLog, requirementMap.get(r).status, dft);
			}
			else if (satisfyTarget instanceof DesignFunctionPrototype) {
				DesignFunctionPrototype dfp = (DesignFunctionPrototype)satisfyTarget;
				addVVLog2objMap(dfpMap, vvLog, requirementMap.get(r).status, dfp);
				
			}
			
		}
		
		
		
		
		
	
	}
	/*
	private void addVVLog2portMap(VVLog vvLog, boolean Status, FunctionFlowPort port) {
		Set<VVLog> logSet;
		//Add VVLog to the set for FFP
		VVLogsAndStatus ffpMapData;
		if (portMap.containsKey(port)){
			ffpMapData = portMap.get(port);
		}
		else{
			logSet = new HashSet<VVLog>();
			ffpMapData = new VVLogsAndStatus();
			ffpMapData.vvLogs = new HashSet<VVLog>();
			portMap.put(port, ffpMapData);
		}
		ffpMapData.vvLogs.add(vvLog);
		//The status of the port is the same as the status of the requirement corresponding to the port
		ffpMapData.status = Status;
	}

	private void addVVLog2typeMap(VVLog vvLog, boolean status, DesignFunctionType dft) {
		Set<VVLog> logSet;
		//Add VVLog to the set for dft
		VVLogsAndStatus dftMapData;
		if (dftMap.containsKey(dft)){
			dftMapData = dftMap.get(dft);
		}
		else{
			logSet = new HashSet<VVLog>();
			dftMapData = new VVLogsAndStatus();
			dftMapData.vvLogs = new HashSet<VVLog>();
			dftMap.put(dft, dftMapData);
		}
		dftMapData.vvLogs.add(vvLog);
		//The status of the port is the same as the status of the requirement corresponding to the port
		dftMapData.status = status;
	}

	private void addVVLog2dfpMap(VVLog vvLog, boolean status, DesignFunctionPrototype dfp) {
		Set<VVLog> logSet;
		//Add VVLog to the set for dft
		VVLogsAndStatus dfpMapData;
		if (dfpMap.containsKey(dfp)){
			dfpMapData = dftMap.get(dfp);
		}
		else{
			logSet = new HashSet<VVLog>();
			dfpMapData = new VVLogsAndStatus();
			dfpMapData.vvLogs = new HashSet<VVLog>();
			dfpMap.put(dfp, dfpMapData);
		}
		dfpMapData.vvLogs.add(vvLog);
		//The status of the port is the same as the status of the requirement corresponding to the port
		dfpMapData.status = status;
	}
	*/

/*	private void addVVLog2requiremntMap(VVLog vvLog, boolean status, Requirement r) {
		//Add VVLog to the set for dft
		VVLogsAndStatus mapData = null;
		if (requirementMap.containsKey(r)){
			mapData = requirementMap.get(r);
		}
		else{

			mapData = new VVLogsAndStatus();
			mapData.vvLogs = new HashSet<VVLog>();
			requirementMap.put(r, mapData);
		}
		mapData.vvLogs.add(vvLog);
		mapData.status = status;
	}
*/
	
	//map: requirementMap, portMap, dftMap or dfpMap
	//vvLog: the element to add
	//status: true if verdict is true for obj considering all vvLogs highlighted
	//obj: the object used as a key for the map. (Requirement, FunctionFlowPort, DesignFunctionPrototype or DesignFunctionType)
	private <K extends Object> void addVVLog2objMap(Map<K, VVLogsAndStatus> map, VVLog vvLog, boolean status, K obj) {
		//Add VVLog to the set for dft
		VVLogsAndStatus mapData = null;
		if (map.containsKey(obj)){
			mapData = map.get(obj);
		}
		else{

			mapData = new VVLogsAndStatus();
			mapData.vvLogs = new HashSet<VVLog>();
//			mapData.requirements = new ArrayList<Requirement>();
			map.put(obj, mapData);
		
		}
		mapData.vvLogs.add(vvLog);

		/*if (!mapData.requirements.contains(r)){
			mapData.requirements.add(r);
			mapData.refcount.add(1);
		}
		else {
			Integer indx = mapData.requirements.indexOf(r);
			int currentrefcount = mapData.refcount.get(indx);
			mapData.refcount.set(indx, currentrefcount+1);
		}
		*/
		//The status of the port/type/prototype is the same as the status of the requirement corresponding to the port
		mapData.status = status;
	}
	
	
	public void Highlight (VVProcedure vp)
	{
		List<VVLog> vvLogList = modelProcessor.getVVLogs(vp);

		
		//VVLog tail = vvLogList.get(vvLogList.size() - 1);
		//Highlight (tail);
		
		VVLog tail = findNewestVVLog(vvLogList);
		Highlight (tail);
	}

	private VVLog findNewestVVLog(List<VVLog> vvLogs){

		//2014-06-09 10:20:34
		SimpleDateFormat formatter = new SimpleDateFormat(Utils.prettyDateFormat());

		try{

			VVLog newest = vvLogs.get(0);
			Date d0 = formatter.parse(vvLogs.get(0).getDate());
			for (VVLog vvLog : vvLogs) {

				Date d1 = formatter.parse(vvLog.getDate());
				if (d1.after(d0)){
					newest = vvLog;
				}
			}

			return newest;
		}
		catch(ParseException e)
		{
			Utils.showErrorMessage("Unknown date format in VVLog. Additional info: " + e.getMessage());
			return null;
		}

	}
	
	
	
	public void Highlight (VVCase vc)
	{

		List<VVProcedure> vpList = vc.getVvProcedure();
		for (VVProcedure vp : vpList) {
			Highlight(vp);
		}

	}

	public void Highlight (VerificationValidation vv)
	{
		EList<VVCase> vcList = vv.getVvCase();

		for (VVCase vc : vcList) {
			Highlight(vc);
		}
	}


	/***
	 * Checks if R is ok according to all VVLogs.
	 */

	private boolean NetReqAnalysisResult(Set<VVLog> vvLogs, Requirement R){

		VVLogTextXMLManager vvLogTextMgr = VVLogTextXMLManager.getInstance();
		String pathR = EastADLURIFactory.getAbsoluteQualifiedName(R);		

		for (VVLog vvLog : vvLogs) {

			VVLogTextXML vvLogText = vvLogTextMgr.GetVVLogTextXML(vvLog);

			if (vvLogText.getFailedRequirementPaths().size() > 0){
				{
					if (vvLogText.getFailedRequirementPaths().contains(pathR)){
						return false;
					}
				}
			}
		}	
		return true;
	}

	
	public enum FMUSimStatus {NONE, SUCCESS, FAILURE};
	public FMUSimStatus GetFMUSimStatus(Object element){
		{
			if (element instanceof FunctionFlowPort)
			{
				FunctionFlowPort ffp = (FunctionFlowPort)element;
				
				if (portMap.containsKey(ffp)){
					return portMap.get(ffp).status ? FMUSimStatus.SUCCESS : FMUSimStatus.FAILURE;
				}
				
				return FMUSimStatus.NONE;
			}

			else if (element instanceof DesignFunctionType)
			{
				DesignFunctionType dft = (DesignFunctionType)element;
				
				if (dftMap.containsKey(dft)){
					return dftMap.get(dft).status ? FMUSimStatus.SUCCESS : FMUSimStatus.FAILURE;
				}
				
				return FMUSimStatus.NONE;
			}

			else if (element instanceof DesignFunctionPrototype)
			{
				DesignFunctionPrototype dfp = (DesignFunctionPrototype)element;
				
				if (dfpMap.containsKey(dfp)){
					return dfpMap.get(dfp).status ? FMUSimStatus.SUCCESS : FMUSimStatus.FAILURE;
				}
				
				return FMUSimStatus.NONE;
			}

			
			else if (element instanceof VerificationValidation){
				//Check subtree, set to failure if any of the highlighted vvcases are false
				VerificationValidation vv = (VerificationValidation)element;
				
				FMUSimStatus status = FMUSimStatus.NONE;
				boolean bSuccessFound = false;
				
				for (VVCase vc : vv.getVvCase()){
					FMUSimStatus stat = GetFMUSimStatus(vc);
					if (stat == FMUSimStatus.FAILURE){
						return FMUSimStatus.FAILURE;
					}
					else if (stat == FMUSimStatus.SUCCESS){
						bSuccessFound = true;
					}
				}
					
				if (bSuccessFound){
					return FMUSimStatus.SUCCESS;
				}
				else{
					return FMUSimStatus.NONE;
				}
			}

			
			else if (element instanceof VVCase){
				//Check subtree, set to failure if any of the highlighted logs are false
				VVCase vc = (VVCase)element;

				FMUSimStatus status = FMUSimStatus.NONE;

				for (VVProcedure vp : vc.getVvProcedure()){
					if (highlightedVVProc2LogsMap.containsKey(vp)){
						VVLog vvLog = highlightedVVProc2LogsMap.get(vp);
						if (vvLogStatus(vvLog) == FMUSimStatus.FAILURE){
							return FMUSimStatus.FAILURE;
						}
						else {
							status = FMUSimStatus.SUCCESS;
						}
					}
				}
				return status;
			}
			else if (element instanceof VVProcedure){
				//Same as current VVLog
				VVProcedure vp = (VVProcedure)element;

				if (highlightedVVProc2LogsMap.containsKey(vp)){
					VVLog vvLog = highlightedVVProc2LogsMap.get(vp);
					return vvLogStatus(vvLog);
				}

				return FMUSimStatus.NONE;
			}

			else if (element instanceof VVLog){
				VVLog vvLog = (VVLog)element;
				return vvLogStatus(vvLog);
			}			


			else if (element instanceof Requirement){
				Requirement R = (Requirement)element;
				if (requirementMap.containsKey(R)){
					return requirementMap.get(R).status ? FMUSimStatus.SUCCESS : FMUSimStatus.FAILURE;
				}
				return FMUSimStatus.NONE;
			}
				


			
			return FMUSimStatus.NONE;
			
			
			
		}
	}

	private FMUSimStatus vvLogStatus(VVLog vvLog){
		EAXML eaxml = (EAXML)EcoreUtil.getRootContainer(vvLog);
		List<VVLog> highlightedVvLogs = highlightedEAXMLVVLogsMap.get(eaxml);

		
		
		if (highlightedVvLogs != null && highlightedVvLogs.contains(vvLog))
		{
			VVLogTextXML vvLogTextXML = VVLogTextXMLManager.getInstance().GetVVLogTextXML(vvLog);
			return (vvLogTextXML.isSimulationResult()) ? FMUSimStatus.SUCCESS : FMUSimStatus.FAILURE;
		}
		return FMUSimStatus.NONE;

	}


	public List<VVLog> getPassedVVLogs(Requirement R){
		Set<VVLog> vvLogs = requirementMap.get(R).vvLogs;
		if (vvLogs == null){
			return null;
		}

		List<VVLog> passed = new ArrayList<VVLog>();
		for (VVLog vvLog : vvLogs) {
			
						
			VVLogTextXML vvLogText = VVLogTextXMLManager.getInstance().GetVVLogTextXML(vvLog);

			if (vvLogText.getPassedRequirementPaths().contains(EastADLURIFactory.getAbsoluteQualifiedName(R))){
				passed.add(vvLog);
			}
		}		
		return passed;
	}

/*
	//Returns a map that contains status of all requirements related to F
	public Map<Requirement, Boolean> getRequirementStatus(FunctionFlowPort F){
		Set<VVLog> vvLogs = portMap.get(F).vvLogs;
			
 
		Map<Requirement, Boolean> netReqResultMap = new HashMap<Requirement, Boolean>();
		
		//For all vvLogs, check the requirements
		for (VVLog vvLog : vvLogs) {
			VVLogTextXML vvLogText = VVLogTextXMLManager.getInstance().GetVVLogTextXML(vvLog);
			EAXML eaxml = (EAXML)EcoreUtil.getRootContainer(vvLog);

			
			for (String uriPassed  : vvLogText.getPassedRequirementPaths()){
				String uriPassedWithType = uriPassed + "?type=Requirement";
				Requirement r = (Requirement)EastADLURIFactory.getEObject(eaxml, uriPassedWithType); 
				if (r == null)
				{
					Utils.showErrorMessage("Failed to find requirement element for " + uriPassedWithType);
				}
				else if (!netReqResultMap.containsKey(r)){
					netReqResultMap.put(r, true);
				}
			}

			for (String uriFailed  : vvLogText.getFailedRequirementPaths()){
				String uriFailedWithType = uriFailed + "?type=Requirement";
				Requirement r = (Requirement)EastADLURIFactory.getEObject(eaxml, uriFailedWithType); 
				if (r == null)
				{
					Utils.showErrorMessage("Failed to find requirement element for " + uriFailedWithType);
				}
				else {	
				netReqResultMap.put(r, false);
				}
			}
		}
		return netReqResultMap;
	}
	*/	
	//Returns a map that contains status of all requirements related to F
	public Map<Requirement, Boolean> getRequirementStatus(EAElement ea){
		Map<Requirement, Boolean> netReqResultMap = new HashMap<Requirement, Boolean>();
	
		Set<VVLog> vvLogs = null;
		
		if (ea instanceof FunctionFlowPort){
			if (portMap.containsKey(ea)){
				vvLogs = portMap.get(ea).vvLogs;
			}
			else return netReqResultMap;	
		}
		else if (ea instanceof DesignFunctionType){
			if (dftMap.containsKey(ea)){
				vvLogs = dftMap.get(ea).vvLogs;
				}		
			else return netReqResultMap;
		}
		else if (ea instanceof DesignFunctionPrototype){
			if (dfpMap.containsKey(ea)){
				vvLogs = dfpMap.get(ea).vvLogs;
			}
			else return netReqResultMap;
		}		
 
		
		//For all vvLogs, check the requirements
		for (VVLog vvLog : vvLogs) {
			VVLogTextXML vvLogText = VVLogTextXMLManager.getInstance().GetVVLogTextXML(vvLog);
			EAXML eaxml = (EAXML)EcoreUtil.getRootContainer(vvLog);

			
			for (String uriPassed  : vvLogText.getPassedRequirementPaths()){
				String uriPassedWithType = uriPassed + "?type=Requirement";
				Requirement r = (Requirement)EastADLURIFactory.getEObject(eaxml, uriPassedWithType); 
				if (r == null)
				{
					Utils.showErrorMessage("Failed to find requirement element for " + uriPassedWithType);
				}
				else if (!netReqResultMap.containsKey(r)){
					netReqResultMap.put(r, true);
				}
			}

			for (String uriFailed  : vvLogText.getFailedRequirementPaths()){
				String uriFailedWithType = uriFailed + "?type=Requirement";
				Requirement r = (Requirement)EastADLURIFactory.getEObject(eaxml, uriFailedWithType); 
				if (r == null)
				{
					Utils.showErrorMessage("Failed to find requirement element for " + uriFailedWithType);
				}
				else {	
				netReqResultMap.put(r, false);
				}
			}
		}
		return netReqResultMap;
	}

	
	
	public List<VVLog> GetFailedVVLogs(Requirement R){
		Set<VVLog> vvLogs = requirementMap.get(R).vvLogs;
		
		if (vvLogs == null){
			return null;
		}

		List<VVLog> failed = new ArrayList<VVLog>();
		for (VVLog vvLog : vvLogs) {
			
			VVLogTextXML vvLogText = VVLogTextXMLManager.getInstance().GetVVLogTextXML(vvLog);

			if (vvLogText.getFailedRequirementPaths().contains(EastADLURIFactory.getAbsoluteQualifiedName(R))){
				failed.add(vvLog);
			}

		}		
		return failed;
	}

	
	public void LoadWorkspaceHighlighting(){
		
		FMUSimViewXML fmuSimView = FMUSimViewXMLManager.getInstance().ReadXML();

		if (fmuSimView != null){
			fmuSimView.getHighlightedVVLogs();
			//for each eaxml...
			for (HighlightedVVLogsinEAXML h : fmuSimView.getHighlightedVVLogs()) {
				String sURI = h.getEaxmlURI();
				URI uri = URI.createURI(sURI);

				EObject eaxml = EcorePlatformUtil.getEObject(uri);
				//...handle all VVLogs
				for (String vvLogURI : h.getHighlightedVVLogPaths()){
					EObject e = EastADLURIFactory.getEObject(eaxml, vvLogURI); 
					if (e != null){
						VVLog vvLog = (VVLog)e;
						Highlight(vvLog);
					}

				}

			}

		}
	}
	public void SaveWorkspaceHighlighting(){
		
		//convert highlightedEAXMLVVLogsMap --> FMUSimViewXML object
		FMUSimViewXML fmuSimView = new FMUSimViewXML();
		
		List<HighlightedVVLogsinEAXML> hVVLogsList = new ArrayList<HighlightedVVLogsinEAXML>();
	
		
		for (EAXML eaxml : highlightedEAXMLVVLogsMap.keySet()) {

			List<VVLog> vvLogs = highlightedEAXMLVVLogsMap.get(eaxml);
			HighlightedVVLogsinEAXML node = new HighlightedVVLogsinEAXML();

			//convert List<VVLog> --> List<String (uri)>
			List<String> vvLogsPath = new ArrayList<String>();
			for (VVLog vvLog : vvLogs) {
				vvLogsPath.add(EastADLURIFactory.getURIFragment(vvLog));
			}
			node.setHighlightedVVLogPaths(vvLogsPath);
			
			//path to EAXML in workspace
			String uri = EcoreUtil.getURI(eaxml).toString();
			node.setEaxmlURI(uri);
		
			hVVLogsList.add(node);
			
		}
		
		
		fmuSimView.setHighlightedVVLogs(hVVLogsList);
		
		
		FMUSimViewXMLManager.getInstance().SaveXML(fmuSimView);
		
		
	}			




}


