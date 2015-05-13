package org.eclipse.eatop.volvo.fmuimport.popup.actions;

// FMImport EATOP plugin
// 
//Revision History 
// Original v1.0   Gong Pei, Volvo ATR
// Version  v1.0.1 Henrik Kaijser, Volvo ATR
//   - Conversion to EAST-ADL 2.1.12
//   - Targettype introduced - users chooses between DesignFunctionType, HardwareFunctionType etc.
//   - import of .fmu instead of .xml
//   - FunctionBehavior created
//   - bugfixes

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.eatop.eastadl21.EADirectionKind;
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.FunctionBehavior;
import org.eclipse.eatop.eastadl21.FunctionBehaviorKind;
import org.eclipse.eatop.eastadl21.FunctionType;
import org.eclipse.eatop.eastadl21.RangeableValueType;
import org.eclipse.eatop.eastadl21.Unit;
import org.eclipse.eatop.volvo.fmuimport.Activator;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xml.type.InvalidDatatypeValueException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.sphinx.emf.metamodel.IMetaModelDescriptor;
import org.eclipse.sphinx.emf.metamodel.MetaModelDescriptorRegistry;
import org.eclipse.sphinx.emf.util.WorkspaceEditingDomainUtil;
import org.eclipse.sphinx.emf.util.WorkspaceTransactionUtil;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ImportFromFMUAction implements IObjectActionDelegate {

	private Shell shell;
	protected static final String EAST_ADL_TYPE_NAME = "EAXML";
    protected static final String EAPACKAGE_TYPE_NAME = "EAPackage"; 
    protected static final String SHORTNAME_FEATURE_NAME = "shortName"; 
    protected static final String NAME_FEATURE_NAME = "name"; 
    protected static final String TOPLEVELPACKAGES_FEATURE_NAME = "topLevelPackage"; 
    
    IMetaModelDescriptor metaModelDescriptor = null;
    //IFile eastadlFile = null;
   
    Map<String, TreeNode> myTypes = new HashMap<String, TreeNode>();
    int RealCounter =1;
    int IntCounter=1;
    
    TreeNode EADataTypeNode= new TreeNode();
	/**
	 * Constructor for Action1.
	 */
	public ImportFromFMUAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {


		try {
			IWorkbenchPage page= Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();  
			ISelection myselec= page.getSelection();
			TreeSelection myselecTree= (TreeSelection) myselec;
			TreePath[] paths = myselecTree.getPaths();
			final EObject rootnode = (EObject)paths[0].getLastSegment();

			//obtain .fmu filename from user dialog
			FileDialog fileDialog = new FileDialog(shell);
			fileDialog.setFilterExtensions(new String[]{"*.fmu"});
			final String selected = fileDialog.open();

			File dir = new File("working-dir");
			if (!dir.exists()) {
		        dir.mkdir();
			}
			
			//extract modelDescription.fmi to a temp file
			File f = File.createTempFile("modelDescription",".xml", dir);
			final String tempFilename = f.getAbsolutePath();
			f.delete();

			UnZip unZip = new UnZip();
			try{
				unZip.UnZipFile(selected, "modelDescription.xml", tempFilename);

			} catch (Exception e) {
				throw new Exception("Failed to unzip modelDescription.xml from zip archive.");
			}

			final Document doc = Parser.parse(tempFilename);
			if(doc == null) {
				throw new Exception("Failed to parse modelDescription.xml from zip archive.");
			}

			String target = actionToTypename(action);
			if (target == null){
				//shall not occurr
				throw new Exception("Internal error - no targetClass.");
			}

			final String targetClass = target;

			//Update tree using importFMI function in a transaction
			metaModelDescriptor = MetaModelDescriptorRegistry.INSTANCE.getDescriptor(
					rootnode);
			Runnable runnable = new Runnable() {
				public void run() {

					importFMI( rootnode, rootnode.eClass() ,doc, targetClass, tempFilename, selected); 
				}
			};

			TransactionalEditingDomain editingDomain = WorkspaceEditingDomainUtil.getEditingDomain(rootnode);
			if (editingDomain != null) {
				try {
					WorkspaceTransactionUtil.executeInWriteTransaction(editingDomain, runnable, "setShortName"); //$NON-NLS-1$
				} catch (Exception ex) {
					// Ignore exception
				}
			} else {
				runnable.run();
			}

		} catch (Exception e) {
			MessageDialog.openError(shell, "Import Fails", e.getMessage());
		}
	}

	private String actionToTypename(IAction action) {
		String target;
		if (action.getId().equals("org.eclipse.eatop.fmuImport.designfunctiontypeaction"))
		{
			target = "DesignFunctionType";
		}
		else if (action.getId().equals("org.eclipse.eatop.fmuImport.basicsoftwarefunctiontypeaction"))
		{
			target = "BasicSoftwareFunctionType";
		}
		else if (action.getId().equals("org.eclipse.eatop.fmuImport.localdevicemanageraction"))
		{
			target = "LocalDeviceManager";
		}
		else if (action.getId().equals("org.eclipse.eatop.fmuImport.hardwarefunctiontypeaction"))
		{
			target = "HardwareFunctionType";
		}
		else if (action.getId().equals("org.eclipse.eatop.fmuImport.analysisfunctiontypeaction"))
		{
			target = "AnalysisFunctionType";
		}
		else {
			target = null;
		}
		return target;
	}
	
	
	public TreeNode createChildNode(String childType, List<String> featureName, List<Object> featureValue, EClass parentType,String parentFeature, EObject parentObject){
		EClass mType = (EClass) metaModelDescriptor.getRootEPackage().getEClassifier(childType);
//		System.out.print(mType.toString()+'\n');
		EObject mObject = metaModelDescriptor.getRootEFactory().create(mType);
		int i=0;
		for (i=0;i<featureName.size();i++){
			EStructuralFeature mFeature = mType.getEStructuralFeature(featureName.get(i));
			mObject.eSet(mFeature, featureValue.get(i));
		}
		
		EStructuralFeature branchFeature = parentType.getEStructuralFeature(parentFeature);
		EList<EObject> parentPackages = (EList<EObject>) parentObject.eGet(branchFeature);
		parentPackages.add(mObject);
		
		TreeNode a;
		a = new TreeNode();
		a.nodeObject=(mObject);
		a.nodeType=(mType);
		return a;
	}
	private TreeNode addEnumType(String typeName, EClass parentClass, EObject parentObj, Element propertyOfType){
		List<String> featureName = new ArrayList<String>();
		List<Object> featureValue = new ArrayList<Object>();
		featureName.add(SHORTNAME_FEATURE_NAME);
		featureValue.add(typeName);
		TreeNode enumParent = new TreeNode() ;
		enumParent=createChildNode("Enumeration",featureName,featureValue,parentClass,"element",parentObj);
		
		NodeList enumItems = propertyOfType.getChildNodes();
		
		for (int l = 0; l < enumItems.getLength(); l++) {
			
			if ( Element.class.isAssignableFrom(enumItems.item(l).getClass()) ){
				Element enumItem = (Element) enumItems.item(l); 
				featureName.clear();
				featureValue.clear();
				featureName.add(SHORTNAME_FEATURE_NAME);
				featureValue.add(enumItem.getAttribute("name"));
				TreeNode itemParent = new TreeNode() ;
				itemParent=createChildNode("EnumerationLiteral",featureName,featureValue,enumParent.getNodeType(),"literal",enumParent.getNodeObject());
				
				featureName.clear();
				featureValue.clear();
				featureName.add("body");
				featureValue.add(enumItem.getAttribute("description"));
				
				createChildNode("Comment", featureName,featureValue,itemParent.getNodeType(),"ownedComment",itemParent.getNodeObject());
				
			}
		}
		return enumParent;
	}
	private TreeNode addStringType(String typeName, EClass parentClass, EObject parentObj){
		List<String> featureName = new ArrayList<String>();
		List<Object> featureValue = new ArrayList<Object>();
		
		featureName.add(SHORTNAME_FEATURE_NAME);
		featureValue.add(typeName);
		
		TreeNode typeNode = new TreeNode() ;
		typeNode= createChildNode("EAString",featureName,featureValue,parentClass,"element",parentObj);
		
		return typeNode;
	}

	private TreeNode addBooleanType(String typeName, EClass parentClass, EObject parentObj){
		List<String> featureName = new ArrayList<String>();
		List<Object> featureValue = new ArrayList<Object>();
		
		featureName.add(SHORTNAME_FEATURE_NAME);
		featureValue.add(typeName);
		
		TreeNode typeNode = new TreeNode() ;
		typeNode= createChildNode("EABoolean",featureName,featureValue,parentClass,"element",parentObj);
		
		return typeNode;
	}
	
	private TreeNode addRealType(String typeName, EClass parentClass, EObject parentObj, Element propertyOfType){
		List<String> featureName = new ArrayList<String>();
		List<Object> featureValue = new ArrayList<Object>();
		
		if (propertyOfType.hasAttribute("unit")){
			boolean hasQuantity= false;
			TreeNode quantityNode = new TreeNode() ;
			if  (propertyOfType.hasAttribute("quantity")){
				List<String> featureNameQuantity = new ArrayList<String>();
				List<Object> featureValueQuantity = new ArrayList<Object>();
				featureNameQuantity.add(SHORTNAME_FEATURE_NAME);
				featureValueQuantity.add(typeName+"_quantity");
				
				quantityNode= createChildNode("Quantity",featureNameQuantity,featureValueQuantity,parentClass,"element",parentObj);
				hasQuantity=true;
			}
			featureName.add(SHORTNAME_FEATURE_NAME);
			featureValue.add(typeName+"_unit");
			featureName.add("symbol");
			featureValue.add( propertyOfType.getAttribute("unit"));
			if (hasQuantity){
				featureName.add("quantity");
				featureValue.add( quantityNode.getNodeObject());
			}
			
			TreeNode unitNode = new TreeNode() ;
			unitNode= createChildNode("Unit",featureName,featureValue,parentClass,"element",parentObj);
			featureName.clear();
			featureValue.clear();
			featureName.add("unit");
			featureValue.add( unitNode.getNodeObject());	
		}
		
		featureName.add(SHORTNAME_FEATURE_NAME);
		featureValue.add(typeName+"_EANumerical");
		featureName.add("min");
		featureValue.add(propertyOfType.getAttribute("min"));
		featureName.add("max");
		featureValue.add(propertyOfType.getAttribute("max"));
		TreeNode enumNode = new TreeNode() ;
		enumNode=createChildNode("EANumerical",featureName,featureValue,parentClass,"element",parentObj);
		
		
		featureName.clear();
		featureValue.clear();
		
		
		featureName.add(SHORTNAME_FEATURE_NAME);
		featureValue.add(typeName);
		featureName.add("baseRangeable");
		
		featureValue.add( enumNode.getNodeObject());
		
		TreeNode typeNode = new TreeNode() ;
		typeNode= createChildNode("RangeableValueType",featureName,featureValue,parentClass,"element",parentObj);
		
		return typeNode;
	}
	
	private TreeNode addIntegerType(String typeName, EClass parentClass,
			EObject parentObj, Element propertyOfType) {
		List<String> featureName = new ArrayList<String>();
		List<Object> featureValue = new ArrayList<Object>();
		
		featureName.add(SHORTNAME_FEATURE_NAME);
		featureValue.add(typeName+"_EANumerical");
		featureName.add("min");
		featureValue.add(propertyOfType.getAttribute("min"));
		featureName.add("max");
		featureValue.add(propertyOfType.getAttribute("max"));
		TreeNode enumNode = new TreeNode() ;
		enumNode=createChildNode("EANumerical",featureName,featureValue,parentClass,"element",parentObj);
		
		featureName.clear();
		featureValue.clear();
		featureName.add(SHORTNAME_FEATURE_NAME);
		featureValue.add(typeName);
		featureName.add("baseRangeable");
		featureValue.add( enumNode.getNodeObject());
		
		featureName.add("resolution");
		Double tempvalue = (double) 1;
		featureValue.add(tempvalue);
		TreeNode typeNode = new TreeNode() ;
		typeNode= createChildNode("RangeableValueType",featureName,featureValue,parentClass,"element",parentObj);
		return typeNode;
	}
	
	private void addTypeDefinitions(EObject parentObj,EClass parentClass,Document d){
		NodeList nList = d.getElementsByTagName("TypeDefinitions"); 
		for (int i = 0; i < nList.getLength(); i++) { // Assuming there could be more than one block_diagram
			Element eElement = (Element) nList.item(i);

			NodeList typeDefinitionsList = eElement.getChildNodes();
			
			for (int j = 0; j < typeDefinitionsList.getLength(); j++) { // Assuming there could be more than one block_diagram
				if ( Element.class.isAssignableFrom(typeDefinitionsList.item(j).getClass()) ){

					Element ftElement = (Element) typeDefinitionsList.item(j); 
					
					String typeName= ftElement.getAttribute("name");
					
					NodeList childoftype = ftElement.getChildNodes();
					
					for (int k = 0; k < childoftype.getLength(); k++) {
						
						if ( Element.class.isAssignableFrom(childoftype.item(k).getClass()) ){
							Element propertyOfType = (Element) childoftype.item(k); 
							String propertyNameOfType = propertyOfType.getNodeName();
							
								
							if (propertyNameOfType=="EnumerationType"){
								
								TreeNode typeNode = new TreeNode() ;
								typeNode= addEnumType(typeName, parentClass, parentObj,propertyOfType);
								myTypes.put(typeName, typeNode);
							}
							else if (propertyNameOfType=="RealType"){
								TreeNode typeNode = new TreeNode() ;
								typeNode= addRealType(typeName, parentClass, parentObj,propertyOfType);
								myTypes.put(typeName, typeNode);
							}
							else if (propertyNameOfType=="IntegerType"){
								TreeNode typeNode = new TreeNode() ;
								typeNode= addIntegerType(typeName, parentClass, parentObj,propertyOfType);
								myTypes.put(typeName, typeNode);
							}
							else{
								System.out.print("New TypeDefinition is found.\n");
							}
						}
					}
				}
			}
		}
	}
	

	private void addModelVariables(EObject parentObj,EClass parentClass,Document d){
		NodeList nList = d.getElementsByTagName("ModelVariables"); 
		for (int i = 0; i < nList.getLength(); i++) { // Assuming there could be more than one ModelVariables
			Element eElement = (Element) nList.item(i);

			
			NodeList scalarVariableList = eElement.getChildNodes();
			
			for (int j = 0; j < scalarVariableList.getLength(); j++) { // ScalarVariable
				
				if ( Element.class.isAssignableFrom(scalarVariableList.item(j).getClass()) ){
					
					
					Element ftElement = (Element) scalarVariableList.item(j); 
					List<String> featureName = new ArrayList<String>();
					List<Object> featureValue = new ArrayList<Object>();
					featureName.clear();
					featureValue.clear();
					
					NodeList typeChildList = ftElement.getChildNodes();
					for (int z = 0; z < typeChildList.getLength(); z++) {
						if ( Element.class.isAssignableFrom(typeChildList.item(z).getClass()) ){
							Element typeChild = (Element) typeChildList.item(z); 
							if (typeChild.hasAttribute("declaredType")){
								String targetType= typeChild.getAttribute("declaredType");
								Object temp= myTypes.get(targetType);
								EObject parentEObject = ((TreeNode)temp).getNodeObject();
								featureName.add("type");
								featureValue.add(parentEObject);
							}
							else if (typeChild.getNodeName()=="Integer"){
								String typeName= typeChild.getAttribute("quantity");
								if (typeName==""){
									
									if (typeChild.hasAttribute("min") | typeChild.hasAttribute("max") ){
									
										for (int hashcnt=0; hashcnt<myTypes.size();hashcnt++){
											String tempname= (String) myTypes.keySet().toArray()[hashcnt];
											TreeNode tempnode = (TreeNode) myTypes.get(tempname);
											
											if (tempnode.nodeObject instanceof RangeableValueType){
												RangeableValueType typeItem = (RangeableValueType) tempnode.nodeObject;
												String m1= typeItem.getBaseRangeable().getMax();
												String m1b = typeChild.getAttribute("max");
												String m2= typeItem.getBaseRangeable().getMin();
												String m2b =typeChild.getAttribute("min");
												
												if(m1.equals(m1b) &m2.equals(m2b) ){
													typeName=tempname;
													break;
												}
											}
											
										}
										if (typeName==""){
											typeName="genInt__"+this.IntCounter;
											this.IntCounter++;
										}
										
									}
									else typeName= "IntegerType";
								}
								if(myTypes.containsKey(typeName)){
									Object temp= myTypes.get(typeName);
									EObject parentEObject = ((TreeNode)temp).getNodeObject();
									featureName.add("type");
									featureValue.add(parentEObject);
								}else{
									TreeNode typeNode = new TreeNode() ;
									typeNode=addIntegerType(typeName, EADataTypeNode.getNodeType(), EADataTypeNode.getNodeObject(),typeChild);
									myTypes.put(typeName, typeNode);
									EObject parentEObject = typeNode.getNodeObject();
									featureName.add("type");
									featureValue.add(parentEObject);
								}
								
							}
							else if (typeChild.getNodeName()=="Real"){
								String typeName= typeChild.getAttribute("quantity");
								if (typeName==""){
									if (typeChild.hasAttribute("min") | typeChild.hasAttribute("max")| typeChild.hasAttribute("unit") ){
//										typeName= "Real_"+ ((Element)(typeChild.getParentNode())).getAttribute("name");
										for (int hashcnt=0; hashcnt<myTypes.size();hashcnt++){
											String tempname= (String) myTypes.keySet().toArray()[hashcnt];
											TreeNode tempnode = (TreeNode) myTypes.get(tempname);
											
											if (tempnode.nodeObject instanceof RangeableValueType){
												RangeableValueType typeItem = (RangeableValueType) tempnode.nodeObject;
												String m1= typeItem.getBaseRangeable().getMax();
												String m1b = typeChild.getAttribute("max");
												String m2= typeItem.getBaseRangeable().getMin();
												String m2b =typeChild.getAttribute("min");
												String m3 = "";
												String m3b = typeChild.getAttribute("unit");
												if (typeItem.getBaseRangeable().getUnit() instanceof Unit){
													m3= typeItem.getBaseRangeable().getUnit().getSymbol();
												}
												if(m1.equals(m1b) &m2.equals(m2b) &m3.equals(m3b)){
													typeName=tempname;
													break;
												}
											}
											
										}
										if (typeName==""){
											typeName="genReal__"+this.RealCounter;
											this.RealCounter++;
										}
										
									}
									else typeName= "RealType";
								}
								if(myTypes.containsKey(typeName)){
									Object temp= myTypes.get(typeName);
									EObject parentEObject = ((TreeNode)temp).getNodeObject();
									featureName.add("type");
									featureValue.add(parentEObject);
								}else{
									TreeNode typeNode = new TreeNode() ;
									typeNode=addRealType(typeName, EADataTypeNode.getNodeType(), EADataTypeNode.getNodeObject(),typeChild);
									myTypes.put(typeName, typeNode);
									EObject parentEObject = typeNode.getNodeObject();
									featureName.add("type");
									featureValue.add(parentEObject);
								}
							}
							else if (typeChild.getNodeName()=="String"){
								String typeName="StringType";
								if(myTypes.containsKey(typeName)){
									Object temp= myTypes.get(typeName);
									EObject parentEObject = ((TreeNode)temp).getNodeObject();
									featureName.add("type");
									featureValue.add(parentEObject);
								}else{
									TreeNode typeNode = new TreeNode() ;
									typeNode=addStringType(typeName, EADataTypeNode.getNodeType(), EADataTypeNode.getNodeObject());
									myTypes.put(typeName, typeNode);
									EObject parentEObject = typeNode.getNodeObject();
									featureName.add("type");
									featureValue.add(parentEObject);
								}
							}
							else if (typeChild.getNodeName()=="Boolean"){
								String typeName="BooleanType";
								if(myTypes.containsKey(typeName)){
									Object temp= myTypes.get(typeName);
									EObject parentEObject = ((TreeNode)temp).getNodeObject();
									featureName.add("type");
									featureValue.add(parentEObject);
								}else{
									TreeNode typeNode = new TreeNode() ;
									typeNode=addBooleanType(typeName, EADataTypeNode.getNodeType(), EADataTypeNode.getNodeObject());
									myTypes.put(typeName, typeNode);
									EObject parentEObject = typeNode.getNodeObject();
									featureName.add("type");
									featureValue.add(parentEObject);
								}
							}
						}
					}
					
							
	
					String flowPortVariability= ftElement.getAttribute("variability");
					
					//Skip "parameters" for now
					if (!flowPortVariability.contains("parameter"))
					{					
						//But add "input" and "output" variables as ports
						String flowPortName= ftElement.getAttribute("name");
						String flowPortDirection= ftElement.getAttribute("causality");
						EADirectionKind mflowPortDirection = null;

						boolean bDirectionFound = false;
						if ((flowPortDirection.contains("input"))){
							mflowPortDirection=EADirectionKind.IN;
							bDirectionFound = true;
						}
						else if (flowPortDirection.contains("output")){
							mflowPortDirection=EADirectionKind.OUT;
							bDirectionFound = true;
						}
						
						//only add a port when a direction has been found, i.e. skip "EventIndications", "internal" stuff
						if (bDirectionFound)
						{						
							featureName.add(SHORTNAME_FEATURE_NAME);
							featureValue.add(flowPortName);
							featureName.add("name");
							featureValue.add(flowPortName);
							
							featureName.add("direction");
							featureValue.add(mflowPortDirection);
							
							TreeNode tempParent = new TreeNode() ;
							tempParent =createChildNode("FunctionFlowPort",featureName,featureValue,parentClass,"port",parentObj);
						}
					}
					
					
				}
			}
		}
	}
	
	private String getModelName(Document d){
		Node top = d.getFirstChild();
				
		Element e = (Element)top;
		String name = e.getAttribute("modelName");
		
		if (name.isEmpty())
		{
			name = "default";
		}
		
		return name;
	}
	
	private void addFunctionBehaviour(String modelName, EObject eaPackage, EClass eaPackageType, FunctionType functionType,
			String sFMUFileName){
				
		//Create Function behaviour node with path set
        List<String> featureName = new ArrayList<String>();
		List<Object> featureValue = new ArrayList<Object>();
		featureName.clear();
		featureValue.clear();
		featureName.add(SHORTNAME_FEATURE_NAME);
		featureValue.add("FuncBehavior" + modelName);
		featureName.add("path");
		featureValue.add(sFMUFileName);
		featureName.add("representation");
		featureValue.add(FunctionBehaviorKind.OTHER);
		
		TreeNode temp = createChildNode("FunctionBehavior",featureName,featureValue,eaPackageType,"element",eaPackage);

		//Connect Functionbehaviour to FunctionType node
		FunctionBehavior fb = (FunctionBehavior)temp.getNodeObject();
		fb.setFunction(functionType);
				
	}
	
	
	private void importFMI(	EObject eaPackage,EClass eaPackageType, Document d, String targetClass, String xmiFilename, String fmuFilename) {
		

		
		if(!(eaPackage instanceof EAPackage)) 
			throw new InvalidDatatypeValueException("parent parameter must be an instance of EAPackage"); 
		
		
		TreeNode tempParent = new TreeNode() ;
		//Element docEle = d.getDocumentElement();
		
		String modelName = getModelName(d);				
		
		this.RealCounter=1;
		this.IntCounter=1;
		myTypes.clear();
        
		//Create EAPackage to contain datatypes
        List<String> featureName = new ArrayList<String>();
		List<Object> featureValue = new ArrayList<Object>();
		featureName.clear();
		featureValue.clear();
		featureName.add(SHORTNAME_FEATURE_NAME);
		featureValue.add("Types" + modelName);
		tempParent = createChildNode("EAPackage",featureName,featureValue,eaPackageType,"subPackage",eaPackage);
		EADataTypeNode = tempParent;
        
        addTypeDefinitions(tempParent.getNodeObject(), tempParent.getNodeType(),d);
		
        
        
       //Create FunctionType to contain ports 
		featureName.clear();
		featureValue.clear();
		featureName.add(SHORTNAME_FEATURE_NAME);
		featureValue.add(modelName);
		featureName.add("isElementary");
		featureValue.add(true);
		tempParent = createChildNode(targetClass,featureName,featureValue,eaPackageType,"element",eaPackage);
		
		FunctionType ft = (FunctionType)tempParent.getNodeObject(); 
		
		addModelVariables(ft, tempParent.getNodeType(),d);
		
	
		
		addFunctionBehaviour(modelName, eaPackage, eaPackageType, ft, fmuFilename);
			
		
	}
	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

}
