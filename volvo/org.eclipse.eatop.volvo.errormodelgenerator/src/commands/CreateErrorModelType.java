package commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import modelquery.ModelQueryException;

import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import org.eclipse.eatop.eastadl21.Dependability;
import org.eclipse.eatop.eastadl21.CompositeDatatype;
import org.eclipse.eatop.eastadl21.EADatatype;
//import eastadl21.east_adl.infrastructure.elements.ElementsPackage;
import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.Eastadl21Package;
import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignFunctionType;
import org.eclipse.eatop.eastadl21.Enumeration;
import org.eclipse.eatop.eastadl21.EnumerationLiteral;
import org.eclipse.eatop.eastadl21.ErrorBehavior;
import org.eclipse.eatop.eastadl21.ErrorBehaviorKind;
import org.eclipse.eatop.eastadl21.FaultFailurePort_functionTarget;
import org.eclipse.eatop.eastadl21.FunctionClientServerInterface;
import org.eclipse.eatop.eastadl21.FunctionClientServerPort;
import org.eclipse.eatop.eastadl21.FunctionConnector;
import org.eclipse.eatop.eastadl21.FunctionConnector_port;
import org.eclipse.eatop.eastadl21.ErrorModelType;
import org.eclipse.eatop.eastadl21.ErrorModelPrototype;
import org.eclipse.eatop.eastadl21.FailureOutPort;
import org.eclipse.eatop.eastadl21.FaultInPort;
import org.eclipse.eatop.eastadl21.FunctionFlowPort;
import org.eclipse.eatop.eastadl21.FunctionPort;
import org.eclipse.eatop.eastadl21.FaultFailurePropagationLink;
import org.eclipse.eatop.eastadl21.FaultFailurePropagationLink_fromPort;
import org.eclipse.eatop.eastadl21.FaultFailurePropagationLink_toPort;
import org.eclipse.eatop.eastadl21.FunctionPowerPort;
import org.eclipse.eatop.eastadl21.FunctionPrototype;
import org.eclipse.eatop.eastadl21.RangeableValueType;
import org.eclipse.eatop.eastadl21.impl.Eastadl21FactoryImpl;

import errorpropagationplugin.Activator;


public class CreateErrorModelType implements IErrorModelCreation{

	private static final ErrorBehaviorKind HIP_HOPS = null;
	private String uuID;
	private DesignFunctionType OriDfcn = null;
	private EObject selectedEObject;
	private EAXML root;
	private DesignFunctionPrototype selecteddfpt;
	private List<List<EAPackage>> allPak= new ArrayList<List<EAPackage>>();
//	private EAPackage superLocalPackage;
	private Dependability superLocalDep;
	private FaultFailurePropagationLink ffplFc;
	private int numOfType = 0;
	EAPackage holdOnPackage;
//	private DesignFunctionType Dfcn;
//	static DesignFunctionType Dfcn;
	
	@Override
	public ErrorModelType createErrorModel(EditingDomain ed,
			EObject selectedEObject,Dependability superDep)
			throws ModelQueryException, Exception {
		
		superLocalDep = superDep;
//		superLocalPackage = superPackage;
		
		//read all the selected DesignFunctionType(Dfcn)
		DesignFunctionType Dfcn = getDesignFunctionType(selectedEObject);
		
		// Check if the requested ErrorModelType exists
		EList<ErrorModelType> existEMTs = superLocalDep.getErrorModelType();
		for(ErrorModelType tempEMT: existEMTs){
			if(tempEMT.getShortName().equals(Dfcn.getShortName()+"_EMT")){
				return tempEMT;
			}
		}
		
		// Get all packages where the selected DesignFunctionType belongs to
//		List<EAPackage> eac = getAllEAPackage(Dfcn);
//		allPak.add(numOfType-1, eac);
		
		//Compare with previous generated packages whether exist
//		int typeSize = allPak.size();
//		for (int t = typeSize-1; t>0; t--){
//			int pakSize = allPak.get(t).size();
//			
//		}// end of typeSize
		
//		int pakSize = allPak.set(index, element);
//		List<List<String>> listOfLists = new ArrayList<List<String>>();
//		listOfLists.add(new ArrayList<String>());
//		listOfLists.get(index1).get(index2);
		
		// Get the top level package
//		if(numOfType==1){
//			holdOnPackage = superLocalPackage;
//			//Create Package for selected elements packages
//			for (int i=0; i<=eac.size()-2;i++){
//		        EAPackage tempPackage = Eastadl21Factory.eINSTANCE.createEAPackage();
//		        int packagenum = eac.size()-2;
//		        String packageName = eac.get(packagenum-i).getShortName()+"_EM";
//		        tempPackage.setShortName(packageName);
//		        Command addtempPackage = AddCommand.create(ed,  holdOnPackage, Eastadl21Package.EA_PACKAGE, tempPackage);
//				ed.getCommandStack().execute(addtempPackage);
//				holdOnPackage = tempPackage;
//			}
//		}
				
		//Create the corresponding ErrorModelType for the DesignFunctionType which the selected DesignFunctionPrototype belongs to
		ErrorModelType emtDfcn = Eastadl21Factory.eINSTANCE.createErrorModelType();
		emtDfcn.setShortName(Dfcn.getShortName()+"_EMT");
		emtDfcn.setUuid(setUUID());
		// Set target for Error Model Type	
		emtDfcn.getTarget().add(Dfcn);
		// Add command
		Command addemtDfcn = AddCommand.create(ed, superLocalDep, Eastadl21Package.ERROR_MODEL_TYPE, emtDfcn);
		ed.getCommandStack().execute(addemtDfcn);
		
		/**
		 *  Get and Set all the contained design function prototype	
		 */
		if(Dfcn.getPart().size()>0)
		{
			for(int idfpt=0; idfpt<Dfcn.getPart().size();idfpt++)
			{ 
				selecteddfpt = Dfcn.getPart().get(idfpt);
				
				//Create the corresponding ErrorModelProrotype for the selected DesignFunctionPrototype
				final ErrorModelPrototype empDfpt = Eastadl21Factory.eINSTANCE.createErrorModelPrototype();
				empDfpt.setShortName(selecteddfpt.getShortName()+"_EMp");
				empDfpt.setUuid(setUUID());
				// Set target for Error Model Prototype
				empDfpt.setTarget(selecteddfpt);
				// Add command
				Command addempDfpt = AddCommand.create(ed, emtDfcn, Eastadl21Package.ERROR_MODEL_PROTOTYPE, empDfpt);
				ed.getCommandStack().execute(addempDfpt);
				
				// Create a DesignFunctionType for the selecteddfpt
				DesignFunctionType dftDfpt = Eastadl21Factory.eINSTANCE.createDesignFunctionType();
				// Get the type of prototype if exists
				dftDfpt = selecteddfpt.getType();
				if(dftDfpt == null){
					 String msg = "DesignFunctionPrototype \"" + selecteddfpt.getShortName().toString()+"\" in \""+ "DesignFunctionType \"" +Dfcn.getShortName().toString() +"\" has no type!";
	                 log(msg);					
//						MessageDialog.openInformation(null, "Selected Prototype",selecteddfpt.getShortName().toString()+" in "+Dfcn.getShortName().toString() +" has no type!");						
				}
				else{//selecteddfpt has a type
					selectedEObject = toEObject(dftDfpt);
					// Create the ErrorModelType and all elements belong to it for the type of selecteddfpt
					final ErrorModelType emtEmp = createErrorModel(ed, selectedEObject,superLocalDep);
					//Set error model type for this error model prototype
					ed.getCommandStack().execute(new RecordingCommand((TransactionalEditingDomain) ed) {
					    protected void doExecute() {
					    	empDfpt.setType(emtEmp);
					    }
					});										
				}

			}// end of each DesignFunctionPrototype		
		}
		else
		{
		}// end of DFPT	
		
		/**
		 *  Get and Set all the contained function ports
		 */
		if(Dfcn.getPort().size()>0)			
		{ 
			// Create Package for containing all ErrorModelType
//			EAPackage dataPackage = Eastadl21Factory.eINSTANCE.createEAPackage();
//	        dataPackage.setShortName("EM_Datatypes");
//	        Command adddataPackage = AddCommand.create(ed, superLocalDep, Eastadl21Package.EA_PACKAGE, dataPackage);
//			ed.getCommandStack().execute(adddataPackage);	
			
			for (int ifp=0; ifp<Dfcn.getPort().size();ifp++)
			{
				FunctionPort selectedfp = Dfcn.getPort().get(ifp);			
				// Get the value of direction attribute of each FunctionPort
				String portDirection = getDirection(selectedfp);
				// Create the selected functionPort
				createErrorModelPort(ed, emtDfcn, portDirection,selectedfp);							
			} // end of all ports
			
			
		} // end of if port exists
		else
		{
		}// end of Function Port	
		
		/**
		 * Get and Set all the contained function connectors
		 */
		if(Dfcn.getConnector().size()>0)
		{
			for (int ifc=0; ifc<Dfcn.getConnector().size();ifc++)
			{
				FunctionConnector selectedfc = Dfcn.getConnector().get(ifc);
				//Create the corresponding FaultFailurePropagationLink for the selected FunctionConnector
				ffplFc = Eastadl21Factory.eINSTANCE.createFaultFailurePropagationLink();
				ffplFc.setShortName(selectedfc.getShortName()+"_ffpl");
				ffplFc.setUuid(setUUID());
				Command addffplFc = AddCommand.create(ed, emtDfcn, Eastadl21Package.FAULT_FAILURE_PROPAGATION_LINK, ffplFc);
				ed.getCommandStack().execute(addffplFc);		
				
				// If connector has ports
				if(selectedfc.getPort().size()>0){
					EList<FunctionConnector_port> connectorPorts = selectedfc.getPort();
					for(FunctionConnector_port connectorPort:connectorPorts){
						// Get functionPort of each connectorPort
						FunctionPort funPort = connectorPort.getFunctionPort();
						if(funPort != null){
							EObject funPortConntainer = toEObject((DesignFunctionType) funPort.eContainer());
							// Create the ErrorModelType and all elements belong to it for the funPort
							final ErrorModelType emtFPort = createErrorModel(ed, funPortConntainer,superLocalDep);					
							
							// Create FaultFailurePropagationLink_Ports 
							String connectorPortDirection = getDirection(funPort);						
							
							// Get functionPrototype of each connectorPort
							FunctionPrototype funPro = connectorPort.getFunctionPrototype();
							ErrorModelType emtFProtoType;
							if(funPro != null){
								EObject funProContainer = toEObject((DesignFunctionType) funPro.eContainer());
								// Create the ErrorModelType and all elements belong to it for the funPro
								final ErrorModelType emtFProto = createErrorModel(ed, funProContainer,superLocalDep);
								emtFProtoType = emtFProto;
							}// end of connectorPort has functionPrototype
							else{
								String msg = "One ConnectorPort in Connector \""+selectedfc.getShortName().toString()+"\" has no functionPrototype!";
				                log(msg);
				                emtFProtoType = null;
								
							} // end of connectorPort has no functionprototype
	
							
							if(connectorPortDirection==null){// FunctionPowerPort is pointed by this connector
								// Create the FaultFailurePropagaionLink for the other direction
								//Initiate the corresponding back FaultFailurePropagationLink for the selected FunctionConnector
								FaultFailurePropagationLink ffplBackFc = Eastadl21Factory.eINSTANCE.createFaultFailurePropagationLink();
								ffplBackFc.setShortName(selectedfc.getShortName()+"_back_ffpl");
								ffplBackFc.setUuid(setUUID());
								// Check if back_ffpl already exists under emtDfcn
								boolean existance = false;
								EList<FaultFailurePropagationLink> existFFCs = emtDfcn.getFaultFailureConnector();
								for(FaultFailurePropagationLink tempFFC : existFFCs){
									if(tempFFC.getShortName().toString().equals(selectedfc.getShortName().toString()+"_back_ffpl")){
										existance = true;
										FaultFailurePropagationLink tempffpl = ffplFc;
										ffplFc = tempFFC;
										ffplBackFc = tempffpl;
									}
								}
								if(existance == false){
									Command addffplBackFc = AddCommand.create(ed, emtDfcn, Eastadl21Package.FAULT_FAILURE_PROPAGATION_LINK, ffplBackFc);
									ed.getCommandStack().execute(addffplBackFc);								
								}
								
								String fipName = "_across_fip";
								String fopName = "_through_fop";
								// Create ports and prototypes for FaultFailurePropagationLink_PowerPorts							
								createFaultFailurePropagationLink_DualPort(ed,ffplFc,ffplBackFc,emtFPort,funPort,emtFProtoType,funPro,fipName,fopName);
							}
							else{
								// FunctionFlowPort is pointed by this connector
								if(connectorPortDirection.equals("IN") || connectorPortDirection.equals("OUT")){								
									createFaultFailurePropagationLink_FlowPort(ed,connectorPortDirection,emtFPort,funPort,emtFProtoType,funPro);																	
								}
								// FunctionClientServerPort is pointed by this connector
								if(connectorPortDirection.equals("CLIENT") || connectorPortDirection.equals("SERVER")){
									//Initiate the corresponding back FaultFailurePropagationLink for the selected FunctionConnector
									FaultFailurePropagationLink ffplBackFc = Eastadl21Factory.eINSTANCE.createFaultFailurePropagationLink();
									ffplBackFc.setShortName(selectedfc.getShortName()+"_back_ffpl");
									ffplBackFc.setUuid(setUUID());
									// Check if back_ffpl already exists under emtDfcn
									boolean existance = false;
									EList<FaultFailurePropagationLink> existFFCs = emtDfcn.getFaultFailureConnector();
									for(FaultFailurePropagationLink tempFFC : existFFCs){
										if(tempFFC.getShortName().toString().equals(selectedfc.getShortName().toString()+"_back_ffpl")){
											existance = true;
											FaultFailurePropagationLink tempffpl = ffplFc;
											ffplFc = tempFFC;
											ffplBackFc = tempffpl;
										}
									}
									if(existance == false){
										Command addffplBackFc = AddCommand.create(ed, emtDfcn, Eastadl21Package.FAULT_FAILURE_PROPAGATION_LINK, ffplBackFc);
										ed.getCommandStack().execute(addffplBackFc);								
									}
									
									if(connectorPortDirection.equals("CLIENT")){
										String fipName = "_receive_fip";
										String fopName = "_request_fop";
										// Create ports and prototypes for FaultFailurePropagationLink_ClientSeverPorts							
										createFaultFailurePropagationLink_DualPort(ed,ffplFc,ffplBackFc,emtFPort,funPort,emtFProtoType,funPro,fipName,fopName);
										
									}
									else{
										if(connectorPortDirection.equals("SERVER")){
											String fipName = "_receive_fip";
											String fopName = "_response_fop";
											// Create ports and prototypes for FaultFailurePropagationLink_ClientSeverPorts							
											createFaultFailurePropagationLink_DualPort(ed,ffplFc,ffplBackFc,emtFPort,funPort,emtFProtoType,funPro,fipName,fopName);
										}
									}
								} // End of ClientSeverPorts
							} // End of Else
																			
						}// end of connectorPort has functionPort
						else{
							String msg = "One ConnectorPort in Connector \""+selectedfc.getShortName().toString()+"\" has no functionPort!";
			                log(msg);	
						}
					}// end of each port in connector
					
					
			
				}// end of connector has ports
				else{
					String msg = "The Connector \""+selectedfc.getShortName().toString()+"\" has no ConnectorPort!";
	                log(msg);					
				}
			}
		}
		else
		{
		}// end of Function Connector	
		
		createErrorBehavior(ed,emtDfcn);
		
		
		
		return emtDfcn;
				
	} // end of Method CreateErrorModel
 
	/**
     *  Write the message to Problem view
     */ 
       public void log(String msg) {
              log(msg, null);
           }
       
       public void log(String msg, Exception e) {
    		// TODO Auto-generated method stub
          String myPluginID = "org.eatop.errorpropagation";         
          Activator activator = Activator.getDefault();
          activator.getLog().log(new Status(Status.INFO, myPluginID, Status.OK, msg, e));
       }
	
	/**
	 * Set UUID for elements
	 */	
	private String setUUID() {
	// TODO Auto-generated method stub
		// Get timeStamp and generate UniqueID
		String timeStamp = String.valueOf(System.currentTimeMillis());
		UUID uniqueID = UUID.randomUUID();
		String tempUUID = uniqueID + "-" + timeStamp;
	return tempUUID;
}


	/**
	 * Create ErrorBehavior for each errorModelType which has faultInPort and FailureOutPort
	 * Set the failureLogic
	 * Set the Type as "HIP_HOPS"
	 */
	public void createErrorBehavior(EditingDomain ed, ErrorModelType emtDfcn) {
		// TODO Auto-generated method stub
//		emtDfcn.getProcessFault();
//		emtDfcn.getInternalFault();
		if(emtDfcn.getFailure().size()>0 && emtDfcn.getExternalFault().size()>0){
			// Set Error Behavior for each ErrorModelType
			EList<FaultInPort> faultInPorts = emtDfcn.getExternalFault();
			EList<FailureOutPort> failureOutPorts = emtDfcn.getFailure();
			
			// Initiate ErrorBehavior
			ErrorBehavior errorBehavior = Eastadl21Factory.eINSTANCE.createErrorBehavior();
			String failureLogic = "";

			for(FailureOutPort tempfailureOutPort : failureOutPorts){
				String failureLogicSegment = "";
				String rightSide = "";
				int countOR = faultInPorts.size()-1;
				String leftSide = "Failure-"+tempfailureOutPort.getShortName().toString();
				for(FaultInPort tempfaultInPort : faultInPorts){
					String rightSideSegment = "Failure-"+tempfaultInPort.getShortName().toString();
					if(countOR >0){
						rightSideSegment = rightSideSegment+" OR ";
						countOR--;
					}
					rightSide = rightSide + rightSideSegment;
				}
				failureLogicSegment = leftSide +" = "+rightSide;
				
				failureLogic = failureLogic + failureLogicSegment+"; ";
			}
//			System.out.println(failureLogic);
			errorBehavior.setFailureLogic(failureLogic);
			errorBehavior.setShortName(emtDfcn.getShortName().toString()+"_ErrorBehavior");
			errorBehavior.setUuid(setUUID());
			ErrorBehaviorKind value = HIP_HOPS;		
			errorBehavior.setType(value);
			Command addErrorBehavior = AddCommand.create(ed, emtDfcn, Eastadl21Package.ERROR_BEHAVIOR, errorBehavior);
			ed.getCommandStack().execute(addErrorBehavior);			
		}
		
	}

	/**
	 * Create the corresponding FaultFailurePropagationLink_PowerPort for the connectorPort
	 * @param fopName 
	 * @param fipName 
	 */	
	private void createFaultFailurePropagationLink_DualPort(EditingDomain ed,
			FaultFailurePropagationLink ffplFc, FaultFailurePropagationLink ffplBackFc, ErrorModelType emtFPort,
			FunctionPort funPort, ErrorModelType emtFProto,
			FunctionPrototype funPro, String fipName, String fopName) {
		// TODO Auto-generated method stub
		
		// Initiate the corresponding propagationLinkPort_ffport for connectorPort(OUT)
		FaultFailurePropagationLink_fromPort ffpl_fp = Eastadl21Factory.eINSTANCE.createFaultFailurePropagationLink_fromPort();				
		
		// Get a list of FailureOutPorts of the PropagaionLink_Port points to
		EList<FailureOutPort> emt_fops = emtFPort.getFailure();
		// Get and set the corrresponding fop which propagaionLink_fromport points to
		for(FailureOutPort temp_fop : emt_fops){
			if(temp_fop.getShortName().toString().equals(funPort.getShortName().toString()+fopName)){
				ffpl_fp.setFaultFailurePort(temp_fop);						
			}
		}// End of loop all fops in ErrorModelType
		
		// Get and Set the prototype attribute
		if(emtFProto!=null){
			getFFPLFromPort_Proto(emtFProto,funPro,ffpl_fp);
		}
			
		// Create the corresponding propagationLinkPort_ffport for connectorPort(OUT)
		Command addffpl_fp = AddCommand.create(ed, ffplFc, Eastadl21Package.FAULT_FAILURE_PROPAGATION_LINK__FROM_PORT, ffpl_fp);
		ed.getCommandStack().execute(addffpl_fp);
		
		
		// Initiate the corresponding propagationLinkPort_ffport for connectorPort(IN)
		FaultFailurePropagationLink_toPort ffpl_tp = Eastadl21Factory.eINSTANCE.createFaultFailurePropagationLink_toPort();
		// Get a list of faultInPorts of the PropagaionLink_Port points to
		EList<FaultInPort> emt_fips=emtFPort.getExternalFault();
		// Get and Set the corrresponding fip which propagaionLink_toport points to
		for(FaultInPort temp_fip: emt_fips){
			if(temp_fip.getShortName().toString().equals(funPort.getShortName().toString()+fipName)){
				ffpl_tp.setFaultFailurePort(temp_fip);						
			}
		}// End of loop all fips in ErrorModelType
		
		
		// Get and Set the prototype attribute
		if(emtFProto!=null){
			getFFPLToPort_Proto(emtFProto,funPro,ffpl_tp);
		}
									
		// Create the corresponding propagationLinkPort_ffport for connectorPort(IN)
		Command addffpl_tp = AddCommand.create(ed, ffplBackFc, Eastadl21Package.FAULT_FAILURE_PROPAGATION_LINK__TO_PORT, ffpl_tp);
		ed.getCommandStack().execute(addffpl_tp);
	}

	/**
	 * Get and Set the prototype for ffpl_fromPort
	 */
	private void getFFPLFromPort_Proto(ErrorModelType emtFProto,
			FunctionPrototype funPro, FaultFailurePropagationLink_fromPort ffpl_fp) {
		// TODO Auto-generated method stub
		// Get a list of ErrorModelPrototype of the propagationLink_Port points to
				EList<ErrorModelPrototype> emt_pros = emtFProto.getPart();
				// Get and Set the corresponding ErrorModelPrototype which propagationLink_fromport points to
				for(ErrorModelPrototype temp_pro : emt_pros){
					if(temp_pro.getShortName().toString().equals(funPro.getShortName().toString()+"_EMp")){
						ffpl_fp.getErrorModelPrototype().add(temp_pro);
					}
				}// End of loop all ErrorModelPrototypes in ErrorModelType
		
	}

	/**
	 * Get and Set the prototype for ffpl_toPort
	 */
	private void getFFPLToPort_Proto(ErrorModelType emtFProto,
			FunctionPrototype funPro, FaultFailurePropagationLink_toPort ffpl_tp) {
		// TODO Auto-generated method stub
		// Get a list of ErrorModelPrototype of the propagationLink_Port points to
		EList<ErrorModelPrototype> emt_pros = emtFProto.getPart();
		// Get and Set the corresponding ErrorModelPrototype which propagationLink_fromport points to
		for(ErrorModelPrototype temp_pro : emt_pros){
			if(temp_pro.getShortName().toString().equals(funPro.getShortName().toString()+"_EMp")){
				ffpl_tp.getErrorModelPrototype().add(temp_pro);
			}
		}// End of loop all ErrorModelPrototypes in ErrorModelType
	}


	/**
	 * Create the corresponding FaultFailurePropagationLink_FlowPort for the connectorPort
	 */	
	private void createFaultFailurePropagationLink_FlowPort(EditingDomain ed,
		String connectorPortDirection,
		ErrorModelType emtFP, FunctionPort funPort,
		ErrorModelType emtFProto, FunctionPrototype funPro) {
	// TODO Auto-generated method stub		
		FaultFailurePropagationLink_fromPort existFPort = ffplFc.getFromPort();
		FaultFailurePropagationLink_toPort existTPort = ffplFc.getToPort();
		
		if(existFPort == null){
			if(existTPort == null){
				if(connectorPortDirection.equals("OUT")){
					// Initiate the corresponding propagationLinkPort_ffport for connectorPort(OUT)
					FaultFailurePropagationLink_fromPort ffpl_fp = Eastadl21Factory.eINSTANCE.createFaultFailurePropagationLink_fromPort();				
					
					// Get a list of FailureOutPorts of the PropagaionLink_Port points to
					EList<FailureOutPort> emt_fops = emtFP.getFailure();
					// Get and set the corrresponding fop which propagaionLink_fromport points to
					for(FailureOutPort temp_fop : emt_fops){
						if(temp_fop.getShortName().toString().equals(funPort.getShortName().toString()+"_fop")){
							ffpl_fp.setFaultFailurePort(temp_fop);						
						}
					}// End of loop all fops in ErrorModelType
					
					// Get and Set the prototype attribute
					if(emtFProto != null){
						getFFPLFromPort_Proto(emtFProto,funPro,ffpl_fp);
					}
					
					// Create the corresponding propagationLinkPort_ffport for connectorPort(OUT)
					Command addffpl_fp = AddCommand.create(ed, ffplFc, Eastadl21Package.FAULT_FAILURE_PROPAGATION_LINK__FROM_PORT, ffpl_fp);
					ed.getCommandStack().execute(addffpl_fp);

				}// End of if the connectorPort has direction as "OUT"

				else{
					if(connectorPortDirection.equals("IN")){
						// Initiate the corresponding propagationLinkPort_ffport for connectorPort(IN)
						FaultFailurePropagationLink_toPort ffpl_tp = Eastadl21Factory.eINSTANCE.createFaultFailurePropagationLink_toPort();
						// Get a list of faultInPorts of the PropagaionLink_Port points to
						EList<FaultInPort> emt_fips=emtFP.getExternalFault();
						// Get and Set the corrresponding fip which propagaionLink_toport points to
						for(FaultInPort temp_fip: emt_fips){
							if(temp_fip.getShortName().toString().equals(funPort.getShortName().toString()+"_fip")){
								ffpl_tp.setFaultFailurePort(temp_fip);						
							}
						}// End of loop all fips in ErrorModelType
						
						// Get and Set the prototype attribute
						if(emtFProto != null){
							getFFPLToPort_Proto(emtFProto,funPro,ffpl_tp);
						}
						
											
						// Create the corresponding propagationLinkPort_ffport for connectorPort(IN)
						Command addffpl_tp = AddCommand.create(ed, ffplFc, Eastadl21Package.FAULT_FAILURE_PROPAGATION_LINK__TO_PORT, ffpl_tp);
						ed.getCommandStack().execute(addffpl_tp);
					}// End of if the connectorPort has direction as "IN"
					
				}// End of setting ports for FAULT_FAILURE_PROPAGATION_LINK	
				
			} // End of if ToPort does not exist
			else {
				if(connectorPortDirection.equals("OUT")){
					// Initiate the corresponding propagationLinkPort_ffport for connectorPort(OUT)
					FaultFailurePropagationLink_fromPort ffpl_fp = Eastadl21Factory.eINSTANCE.createFaultFailurePropagationLink_fromPort();				
					
					// Get a list of FailureOutPorts of the PropagaionLink_Port points to
					EList<FailureOutPort> emt_fops = emtFP.getFailure();
					// Get and set the corrresponding fop which propagaionLink_fromport points to
					for(FailureOutPort temp_fop : emt_fops){
						if(temp_fop.getShortName().toString().equals(funPort.getShortName().toString()+"_fop")){
							ffpl_fp.setFaultFailurePort(temp_fop);						
						}
					}// End of loop all fops in ErrorModelType
					
					// Get and Set the prototype attribute
					if(emtFProto != null){
						getFFPLFromPort_Proto(emtFProto,funPro,ffpl_fp);
					}
					

					// Create the corresponding propagationLinkPort_ffport for connectorPort(OUT)
					Command addffpl_fp = AddCommand.create(ed, ffplFc, Eastadl21Package.FAULT_FAILURE_PROPAGATION_LINK__FROM_PORT, ffpl_fp);
					ed.getCommandStack().execute(addffpl_fp);					
				}
				else{ // Delegated Connector create from port for in (in to in)
					FaultFailurePropagationLink_fromPort ffpl_fp = Eastadl21Factory.eINSTANCE.createFaultFailurePropagationLink_fromPort();									
					// Get a list of FailureOutPorts of the PropagaionLink_Port points to
					EList<FaultInPort> emt_fips = emtFP.getExternalFault();
					// Get and set the corrresponding fop which propagaionLink_fromport points to
					for(FaultInPort temp_fip : emt_fips){
						if(temp_fip.getShortName().toString().equals(funPort.getShortName().toString()+"_fip")){
							ffpl_fp.setFaultFailurePort(temp_fip);						
						}
					}// End of loop all fips in ErrorModelType
					
					// Get and Set the prototype attribute
					if(emtFProto != null){
						getFFPLFromPort_Proto(emtFProto,funPro,ffpl_fp);
					}
					

					// Create the corresponding propagationLinkPort_ffport for connectorPort(OUT)
					Command addffpl_fp = AddCommand.create(ed, ffplFc, Eastadl21Package.FAULT_FAILURE_PROPAGATION_LINK__FROM_PORT, ffpl_fp);
					ed.getCommandStack().execute(addffpl_fp);
				}
				
			}
		} // End of if FromPort does not exist
		else{
			if(connectorPortDirection.equals("IN")){
				// Initiate the corresponding propagationLinkPort_ffport for connectorPort(IN)
				FaultFailurePropagationLink_toPort ffpl_tp = Eastadl21Factory.eINSTANCE.createFaultFailurePropagationLink_toPort();
				// Get a list of faultInPorts of the PropagaionLink_Port points to
				EList<FaultInPort> emt_fips=emtFP.getExternalFault();
				// Get and Set the corrresponding fip which propagaionLink_toport points to
				for(FaultInPort temp_fip: emt_fips){
					if(temp_fip.getShortName().toString().equals(funPort.getShortName().toString()+"_fip")){
						ffpl_tp.setFaultFailurePort(temp_fip);						
					}
				}// End of loop all fips in ErrorModelType
				
				// Get and Set the prototype attribute
				if(emtFProto != null){
					getFFPLToPort_Proto(emtFProto,funPro,ffpl_tp);
				}
				
									
				// Create the corresponding propagationLinkPort_ffport for connectorPort(IN)
				Command addffpl_tp = AddCommand.create(ed, ffplFc, Eastadl21Package.FAULT_FAILURE_PROPAGATION_LINK__TO_PORT, ffpl_tp);
				ed.getCommandStack().execute(addffpl_tp);
			}// End of if the connectorPort has direction as "IN"
			else{ // Delegate connector create to port for out(out to out)
				FaultFailurePropagationLink_toPort ffpl_tp = Eastadl21Factory.eINSTANCE.createFaultFailurePropagationLink_toPort();
				// Get a list of faultInPorts of the PropagaionLink_Port points to
				EList<FailureOutPort> emt_fops=emtFP.getFailure();
				// Get and Set the corrresponding fip which propagaionLink_toport points to
				for(FailureOutPort temp_fop: emt_fops){
					if(temp_fop.getShortName().toString().equals(funPort.getShortName().toString()+"_fop")){
						ffpl_tp.setFaultFailurePort(temp_fop);						
					}
				}// End of loop all fops in ErrorModelType
				
				// Get and Set the prototype attribute
				if(emtFProto != null){
					getFFPLToPort_Proto(emtFProto,funPro,ffpl_tp);
				}
				
									
				// Create the corresponding propagationLinkPort_ffport for connectorPort(IN)
				Command addffpl_tp = AddCommand.create(ed, ffplFc, Eastadl21Package.FAULT_FAILURE_PROPAGATION_LINK__TO_PORT, ffpl_tp);
				ed.getCommandStack().execute(addffpl_tp);
			}
			
		}
			
}



	/**
	 * Create the corresponding ErrorModelPort for the functionPort
	 */		
	private void createErrorModelPort(EditingDomain ed, ErrorModelType emType, String direction, FunctionPort funPort) {
	// TODO Auto-generated method stub
		ErrorModelType tempEMT = emType;
		String tempDirection = direction;
		FunctionPort tempFunPort = funPort;
				
		if (tempDirection==null){
			// Initiate the ports
			FailureOutPort fopFpp = Eastadl21Factory.eINSTANCE.createFailureOutPort();
			FaultInPort fipFpp = Eastadl21Factory.eINSTANCE.createFaultInPort();
			// Get type of funPort(functionPowerPort)
			FunctionPowerPort tempPowerPort = (FunctionPowerPort) tempFunPort;
			EADatatype powerPortType = tempPowerPort.getType();
			
			if(powerPortType!=null){
				String portName = powerPortType.getShortName();
				Enumeration portErrorType = createErrorType(ed,portName);
				fopFpp.setType(portErrorType);
				fipFpp.setType(portErrorType);
			}
						
			// Create the corresponding failureOutPorts and FaultInPorts for the selected functionPowerPort			
			fopFpp.setShortName(tempFunPort.getShortName()+"_through_fop");
			fopFpp.setUuid(setUUID());
			Command addfopFpp = AddCommand.create(ed, tempEMT, Eastadl21Package.FAILURE_OUT_PORT, fopFpp);
			ed.getCommandStack().execute(addfopFpp);
			addFunTargetfop(ed,tempFunPort,fopFpp);
						
			fipFpp.setShortName(tempFunPort.getShortName()+"_across_fip");
			fipFpp.setUuid(setUUID());
			Command addfipFpp = AddCommand.create(ed, tempEMT, Eastadl21Package.FUNCTION_FLOW_PORT, fipFpp);
			ed.getCommandStack().execute(addfipFpp);
			addFunTargetfip(ed,tempFunPort,fipFpp);
			

		}
		else{
			if (tempDirection.equals("OUT")){
				// Initiate the FailureOutPort
				FailureOutPort fopFfp = Eastadl21Factory.eINSTANCE.createFailureOutPort();
				// Get type of funPort(functionFlowPort)
				FunctionFlowPort tempFlowPort = (FunctionFlowPort) tempFunPort;				
				EADatatype flowPortType = tempFlowPort.getType();
				if(flowPortType!=null){
					String portName = flowPortType.getShortName();
					Enumeration portErrorType = createErrorType(ed,portName);
					fopFfp.setType(portErrorType);
				}
								
				//Create the corresponding FailureOutPorts for the selected FunctionFlowPort(Out)
				fopFfp.setShortName(tempFunPort.getShortName()+"_fop");
				fopFfp.setUuid(setUUID());
				Command addfopFfp = AddCommand.create(ed, tempEMT, Eastadl21Package.FAILURE_OUT_PORT, fopFfp);
				ed.getCommandStack().execute(addfopFfp);
				addFunTargetfop(ed,tempFunPort,fopFfp);
		
			}//end of if direction = "OUT"
			else{
				if (tempDirection.equals("IN")){
					// Initiate the FaultInPort
					FaultInPort fipFfp = Eastadl21Factory.eINSTANCE.createFaultInPort();
					// Get type of funPort(functionFlowPort)
					FunctionFlowPort tempFlowPort = (FunctionFlowPort) tempFunPort;
					EADatatype flowPortType = tempFlowPort.getType();
					if(flowPortType!=null){
						String portName = flowPortType.getShortName();
						Enumeration portErrorType = createErrorType(ed,portName);
						fipFfp.setType(portErrorType);	
					}
														
					//Create the corresponding FaultInPorts for the selected FunctionFlowPort(In)					
					fipFfp.setShortName(tempFunPort.getShortName()+"_fip");
					fipFfp.setUuid(setUUID());
					Command addfipFfp = AddCommand.create(ed, tempEMT, Eastadl21Package.FUNCTION_FLOW_PORT, fipFfp);
					ed.getCommandStack().execute(addfipFfp);
					addFunTargetfip(ed,tempFunPort,fipFfp);
				}	
			}//end of if direction != "OUT"
			
			if(tempDirection.equals("CLIENT")){
				// Initiate the ports
				FailureOutPort fopCsp = Eastadl21Factory.eINSTANCE.createFailureOutPort();
				FaultInPort fipCsp = Eastadl21Factory.eINSTANCE.createFaultInPort();
				// Get type of funPort(functionFlowPort)
				FunctionClientServerPort tempClientPort = (FunctionClientServerPort) tempFunPort;
				FunctionClientServerInterface clientPortInterface = tempClientPort.getType();
				if(clientPortInterface!=null){
					String portName = clientPortInterface.getShortName();
					Enumeration portErrorType = createErrorType(ed,portName);
					fipCsp.setType(portErrorType);
					fopCsp.setType(portErrorType);
				}
				
				// Create the corresponding failureOutPorts and FaultInPorts for the selected ClientSeverPort(CLIENT)
				fopCsp.setShortName(tempFunPort.getShortName()+"_request_fop");
				fopCsp.setUuid(setUUID());
				Command addfopCsp = AddCommand.create(ed, tempEMT, Eastadl21Package.FAILURE_OUT_PORT, fopCsp);
				ed.getCommandStack().execute(addfopCsp);
				addFunTargetfop(ed,tempFunPort,fopCsp);
				
				fipCsp.setShortName(tempFunPort.getShortName()+"_receive_fip");
				fipCsp.setUuid(setUUID());
				Command addfipCsp = AddCommand.create(ed, tempEMT, Eastadl21Package.FUNCTION_FLOW_PORT, fipCsp);
				ed.getCommandStack().execute(addfipCsp);
				addFunTargetfip(ed,tempFunPort,fipCsp);
				
			}// end of if kind = "CLIENT"
			else{
				if(tempDirection.equals("SERVER")){
					// Initiate the ports
					FailureOutPort fopCsp = Eastadl21Factory.eINSTANCE.createFailureOutPort();
					FaultInPort fipCsp = Eastadl21Factory.eINSTANCE.createFaultInPort();
					// Get type of funPort(functionFlowPort)
					FunctionClientServerPort tempServerPort = (FunctionClientServerPort) tempFunPort;
					FunctionClientServerInterface serverPortInterface = tempServerPort.getType();
					if(serverPortInterface!=null){						
						String portName = serverPortInterface.getShortName();
						Enumeration portErrorType = createErrorType(ed,portName);
						fipCsp.setType(portErrorType);
						fopCsp.setType(portErrorType);
					}
					
					// Create the corresponding failureOutPorts and FaultInPorts for the selected ClientSeverPort(CLIENT)
					fopCsp.setShortName(tempFunPort.getShortName()+"_response_fop");
					fopCsp.setUuid(setUUID());
					Command addfopCsp = AddCommand.create(ed, tempEMT, Eastadl21Package.FAILURE_OUT_PORT, fopCsp);
					ed.getCommandStack().execute(addfopCsp);
					addFunTargetfop(ed,tempFunPort,fopCsp);
					
					fipCsp.setShortName(tempFunPort.getShortName()+"_receive_fip");
					fipCsp.setUuid(setUUID());
					Command addfipCsp = AddCommand.create(ed, tempEMT, Eastadl21Package.FUNCTION_FLOW_PORT, fipCsp);
					ed.getCommandStack().execute(addfipCsp);
					addFunTargetfip(ed,tempFunPort,fipCsp);
				}// end of if kind = "CLIENT"
			}
		}
	

	}
	
	/**
	 * Add target port for each FaultInPort
	 */	
	private void addFunTargetfip(EditingDomain ed, FunctionPort funPort,
			FaultInPort fip) {
		// TODO Auto-generated method stub
		FunctionPort tempFunPort = funPort;
		FaultInPort tempFip = fip;
		// Set target for each FaultInPort
		FaultFailurePort_functionTarget functionTarget = Eastadl21Factory.eINSTANCE.createFaultFailurePort_functionTarget();
		functionTarget.setFunctionPort(tempFunPort);
		Command addfunTarget = AddCommand.create(ed, tempFip, Eastadl21Package.FAULT_FAILURE_PORT__FUNCTION_TARGET, functionTarget);
		ed.getCommandStack().execute(addfunTarget);
	}

	/**
	 * Add target port for each FailureOutPort
	 */	
	private void addFunTargetfop(EditingDomain ed, FunctionPort funPort,
			FailureOutPort fop) {
		// TODO Auto-generated method stub
		FunctionPort tempFunPort = funPort;
		FailureOutPort tempFop = fop;
		FaultFailurePort_functionTarget functionTarget = Eastadl21Factory.eINSTANCE.createFaultFailurePort_functionTarget();
		functionTarget.setFunctionPort(tempFunPort);
		Command addfunTarget = AddCommand.create(ed, tempFop, Eastadl21Package.FAULT_FAILURE_PORT__FUNCTION_TARGET, functionTarget);
		ed.getCommandStack().execute(addfunTarget);
		
	}


	/**
	 * Create the corresponding ErrorType for selected FunctionPorts
	 */	
	private Enumeration createErrorType(EditingDomain ed,
			String portName) {
		// TODO Auto-generated method stub
		String tempPortTypeName = portName;
		Enumeration errorType = Eastadl21Factory.eINSTANCE.createEnumeration();				
		// If the selected functionPort has a type
		if(tempPortTypeName!=null){		
			EList<EADatatype> tempEADatatypes = superLocalDep.getEaDatatype();
			boolean exist = false;
			for(EADatatype tempEADatatype : tempEADatatypes){
				// Check the existance of the errortype
				if(tempEADatatype.getShortName().toString().equals(tempPortTypeName+"_ErrorType")){
					exist = true;
					errorType = (Enumeration) tempEADatatype;
				}
			}
			if(exist==false){
				// Create ErrorModelType for each selected DataType if does not exist						
				errorType.setShortName(tempPortTypeName+"_ErrorType");
				errorType.setUuid(setUUID());
				Command adderrorType = AddCommand.create(ed, superLocalDep, Eastadl21Package.ENUMERATION, errorType);
				ed.getCommandStack().execute(adderrorType);
				
				// Create "Failure" and "NonFailure" for each Error Type
				EnumerationLiteral failure  = Eastadl21Factory.eINSTANCE.createEnumerationLiteral();
				failure.setShortName("Failure");
				failure.setUuid(setUUID());
				Command addfailure = AddCommand.create(ed, errorType, Eastadl21Package.ENUMERATION__LITERAL, failure);
				ed.getCommandStack().execute(addfailure);
				
				EnumerationLiteral nonfailure  = Eastadl21Factory.eINSTANCE.createEnumerationLiteral();
				nonfailure.setShortName("NonFailure");
				nonfailure.setUuid(setUUID());
				Command addnonfailure = AddCommand.create(ed, errorType, Eastadl21Package.ENUMERATION__LITERAL, nonfailure);
				ed.getCommandStack().execute(addnonfailure);
			}
								
		}
		return errorType;
	}


	/**
	 * Get the value of direction attribute of FunctionPort
	 */
	private String getDirection(FunctionPort selectedfp) {
	// TODO Auto-generated method stub
		FunctionPort tempPort = selectedfp;	
	    String directionName = "direction";
	    String kindName = "kind";
	    EList<EAttribute> eAllAttributes = tempPort.eClass().getEAllAttributes();
	    Object resultingDataType = null;
		for (EAttribute eAttribute : eAllAttributes) {
	        if(eAttribute.getName().equals(directionName)){
	        	//FunctionFlowPort
	        	resultingDataType = tempPort.eGet(eAttribute);
	        }
	        if(eAttribute.getName().equals(kindName)){
	        	//FunctionClientServerPort
	        	resultingDataType = tempPort.eGet(eAttribute);
	        }
	    }
		if(resultingDataType!=null){
			String directionAttribute = resultingDataType.toString();
	//	    System.out.println(resultingDataType.toString());
			return directionAttribute;
		}
		else
			// FunctionPowerPort
			return null;
		
	}



	/**
	 * Get the selected element and convert it into DesignFunctionType
	 */
	private DesignFunctionType getDesignFunctionType(EObject selectedEObjects2) throws Exception{
		// TODO Auto-generated method stub

			DesignFunctionType tempDfcn;
			tempDfcn = (DesignFunctionType) selectedEObjects2;
			return tempDfcn;	
	}
	
	
	/**
	 * Get all packages where the selected DesignFunctionType belongs to
	 */
	private List<EAPackage> getAllEAPackage(DesignFunctionType dfcn2) {
		// TODO Auto-generated method stub
		//read all the EAPackage that contains the selected DesignFunctionType
		numOfType++;
		EAPackage firstPak = (EAPackage) dfcn2.eContainer();
		int ieac=0;			
		List<EAPackage> tempPak = new ArrayList<EAPackage>();
		tempPak.add(firstPak);
		while(tempPak.get(ieac)!=null)
		{// Get upper level EAPackage
			ieac++;	
			tempPak.add(ieac, getContainer(tempPak.get(ieac-1)));
		}
		return tempPak;
	}
	

	/**
	 * Show an information dialog box.
	 */
	public void showInformation(final String title, final String message) {
		// TODO Auto-generated method stub
	  Display.getDefault().asyncExec(new Runnable() {
	    @Override
	    public void run() {
	      MessageDialog.openInformation(null, title, message);
	    }
	  });
	}
		
	/**
	 * Get the Container Package of eap
	 */
	public EAPackage getContainer(EAPackage eap){	
		// TODO Auto-generated method stub
		try{
			if(!(eap.eContainer() == null))
			{// If eap is contained in another package
				EAPackage Eap = (EAPackage) eap.eContainer();
				return Eap;	
			}	
			else
				return null;
		}
		catch (Exception e){
			return null;
		}
	} // end of getContainer
	
	
	/**
	 * Convert a DesignFunctionType to EObject
	 */
	private EObject toEObject(DesignFunctionType dftDfpt) {
		// TODO Auto-generated method stub
		EObject element = null;
		Object tmpElement = dftDfpt;
		if(tmpElement instanceof EObject){
			element = (EObject) tmpElement;
		}
		return element;
	}

}
