package org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd;

import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.naming.Context;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Platform;
import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.eatop.common.metamodel.EastADLReleaseDescriptor;
import org.eclipse.eatop.common.resource.EastADLURIFactory;
import org.eclipse.eatop.common.ui.util.ModelSearcher;
import org.eclipse.eatop.eastadl21.ClampConnector;
import org.eclipse.eatop.eastadl21.ClampConnector_port;
import org.eclipse.eatop.eastadl21.EAConnector;
import org.eclipse.eatop.eastadl21.EAElement;
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.EAPort;
import org.eclipse.eatop.eastadl21.EAPrototype;
import org.eclipse.eatop.eastadl21.EAValue;
import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.eatop.eastadl21.Feature;
import org.eclipse.eatop.eastadl21.Referrable;
import org.eclipse.eatop.eastadl21.impl.EAPackageImpl;
import org.eclipse.eatop.examples.explorer.ChildWrapper;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.Utils;
import org.eclipse.eatop.workspace.natures.EastADLNature;
import org.eclipse.eatop.workspace.preferences.IEastADLWorkspacePreferences;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.sphinx.emf.util.EcorePlatformUtil;
import org.eclipse.sphinx.platform.util.ExtendedPlatform;
import org.osgi.framework.PrototypeServiceFactory;

public class EAXMLprocessor {

	/**
	 * Returns the EObject with the given path. Takes virtual containment into account. 
	 *
	 * Note that the same toppackage (and subpackages) may be in several loaded EAXML files
	 * It's only the full path that is required to be unique.
	 * 
	 * @param path The path in the model where we expect to find the element. Format "/toppack/a/b/c"
	 * @return The EAElement in the model (always real) or null if the element wasn't found
	 */
	public static EObject getEObjectbyEastADLPath(String virtualpath) {
		String pathNonitialSlash = virtualpath.substring(1);
		List<EAPackage> topPackages = findTopPackages(pathNonitialSlash);
		if (topPackages.size() == 0) return null;

		String[] parts = pathNonitialSlash.split("/");
	
		List<String> partsList = new ArrayList<String>();
		for (int i = 0; i < parts.length; i++) {
			partsList.add(parts[i]);
		}

	
		for (EAPackage top : topPackages){
			List<String> tempPartsList = new ArrayList<String>(partsList);
			EObject theEObject = recursiveGetEObject(top, tempPartsList);
			if (theEObject != null){
				return theEObject;
			}
		}

		//Not found in any toppackage
		return null;
	}

	//Finds all possible EAPackage objects corresponding to the right toppack.
	//We don't now yet in which eaxml file our object is located.
	//Format "toppack/a/b"
	public static List<EAPackage> findTopPackages(String pathNoInitialSlash){
	
		List<EAPackage>  topPacks  = new ArrayList<EAPackage>();
		
		String packageName;
		int firstSlash = pathNoInitialSlash.indexOf("/");
		
		if (firstSlash != -1){
			packageName = pathNoInitialSlash.substring(0, firstSlash);
		}
		else{
			packageName = pathNoInitialSlash;
		}

		//Iterate over the projects, files & packages in the platform
		for(IProject project : ExtendedPlatform.getProjects(EastADLNature.ID)) {
			for(IFile file : ExtendedPlatform.getAllFiles(project, false)) {
				Resource resource  = EcorePlatformUtil.getResource(file);
				if (resource == null) {
					//resource not read - assume all have been read 
				}
				else{
					EList<EObject> elist = resource.getContents();
					EAXML platformEAXML = (EAXML)elist.get(0);
					EList<EAPackage> topPackages = platformEAXML.getTopLevelPackage();

					for (EAPackage p : topPackages) {
						if (p.getShortName().equals(packageName))
							topPacks.add(p);
					}
				}
			}
		}
		return topPacks;
	}

	//EAPackages are always in the top only - i.e. the parent of an EAPackage is always an EAPackage
	private static EObject recursiveGetEObject(Referrable referrable, List<String> path) {

		//Special handling when lastSegment is a tricky one...
		if (path.size() == 2 && path.get(1).contains("@")){
			if (elementName(referrable).equals(path.get(0))){
				String lastSegment = path.get(1);
				if (lastSegment.contains(".")){
					//instanceref, i.e. .../MySatisfy/@satisfiedby-2
					int dotIndx = lastSegment.indexOf('.');
					int indx = Integer.parseInt(lastSegment.substring(dotIndx + 1));
					EList<EObject> children = referrable.eContents();
					return children.get(indx);
					
				}
				else{
					if (lastSegment.equals("@value")){
						//For values, return the first and only child, i.e..../MaxPowerGC/@value 
						return referrable.eContents().get(0);
					}
					else if (lastSegment.equals("@selectionCriterion")){
						//For selectionCriterion, return the first and only child .../F3_CD/@selectionCriterion
						return referrable.eContents().get(0); 
					}
					else {
						Utils.showErrorMessage("Not implemented lookup of EObject with last path segment = " + lastSegment);
					}
				}
			}
		}
		
		//base case
		if (path.size() == 1){
			if (elementName(referrable).equals(path.get(0))){
				return referrable;
			}
			else {
				return null;
			}
		}

		//Search deeper
		else {
			EObject eObject = (EObject)referrable;

			if (eObject instanceof EAPrototype){
				EObject type = getEObjectType(eObject);
				return recursiveGetEObject((Referrable)type, path);
			}
			else{
				EList<EObject> content = eObject.eContents();
				for (EObject child : content) {
					if (child instanceof Referrable){
						Referrable refChild = (Referrable)child;
	
						if (elementName(refChild).equals(path.get(1))){
							if (refChild instanceof EAPrototype){
								//For prototypes, keep looking in its type to handle virtual containment 
								child = getEObjectType(child);
							}
							if (child != null){
								path.remove(0);
								return recursiveGetEObject(refChild, path);
							}
						}
				}
			}
		}
		return null;
		}
	}

	static public EObject getEObjectbyDotPath(String dotPath){
		String eastADLPath = dotPath2EastADLPath(dotPath);
		return getEObjectbyEastADLPath(eastADLPath);
}

	
	/*** Gets the EObject that is set as the type of the parentElement
	 *  
	 * @param parentElement
	 * @return
	 */
	
	private static EObject getEObjectType(EObject parentElement) {
		EStructuralFeature eStructuralFeature = parentElement.eClass().getEStructuralFeature("type"); //$NON-NLS-1$

		if (eStructuralFeature != null) {
			Object eGet = parentElement.eGet(eStructuralFeature);

			if (eGet != null && eGet instanceof EObject) {
				return (EObject) eGet;
			}
		}
		return null;
	}

	/**
	 * Like EastADLURIFactory.getAbsoluteQualifiedName but if any segment has an empty shortname (for referrable objects)
	 * then the metaclass name is used instead. 
	 *
	 * @param eObject
	 * @return
	 */
	public static String getSafeAbsoluteQualifiedName(EObject eObject){

		if (eObject == null){
			Utils.showInformationMessage("eObject null in getSafeAbsoluteQualifiedName, missing model reference?");
			return "";
		}
		
		//Use built in function to retreive last segment, i.e. for example @sat�sfied-2 or @value
		String absQualifiedName = EastADLURIFactory.getAbsoluteQualifiedName(eObject);
		String lastSegment = "";	
		EObject eo = eObject;
		String path;
		
		if (absQualifiedName.contains("@")){
			int index = absQualifiedName.indexOf('@');
			lastSegment = absQualifiedName.substring(index);
	
			EObject eFather = eo.eContainer();
			String fatherPath = safePath(eFather);
			path = fatherPath + "/" + lastSegment;
		}
		else{
			path = safePath(eo);			
		}
		
		return path;
	}
		
	//assumes eo is not instance ref or value, i.e. gives no @ in getAbsoluteRequired
	static protected String safePath(EObject eo){
		String path = "";
		while (!(eo instanceof EAXML)){
			if (path.isEmpty()){
				path = elementName(eo); 
			}
			else{
				path = elementName(eo) + "/" + path;
			}
			eo = eo.eContainer();
		}
		path = "/" + path;
		return path;
	}
	
		
	/***
	 * Default is shortName, but if that is empty then the metaclass is used instead.
	 * 
	 * Presentable name   MaxPowerGC/@value => 16 if value is 16.
	 * 
	 * @param object
	 * @return
	 */
	public static String elementName(EObject object){

		if (object instanceof Referrable)
		{
			Referrable r = (Referrable)object;
			if (!r.getShortName().isEmpty()){
				return r.getShortName();
			}
			else{
				return object.eClass().getName();
			}
		}
		else
		{
			String absQualifiedNameString = getSafeAbsoluteQualifiedName(object);
			int lastSegmentIndex = absQualifiedNameString.lastIndexOf('/'); 
			String lastSegment = absQualifiedNameString.substring(lastSegmentIndex + 1);
		
			if (object instanceof EAValue){

				String featureName = lastSegment.substring(1);
				
				EStructuralFeature feature = object.eClass().getEStructuralFeature(featureName);

				if (feature == null){
					//Qualified Name = ....F3_CD/@selectionCriterion => there is no feature for selectionCriterion use that string instead. 
					return featureName;
				}
				else
				{
					///Qualified Name = ASL_workshop/Structure/AnalysisLevelelements/DataTypes/MaxPower/@value => value is a true property
				
					String typeValue = (object.eGet(feature)).toString();
					return typeValue;
				}
			}
			else{
				//Assume instance ref, return "satisfiedBy.2"
				return lastSegment;
			}
		}

	}

	
	/***
	 * Example
	 *  "Top.x.y"                 --> "/Top/x/y"               
	 *  "Top.x.:satisfiedBy-2"    --> "/Top/X/@satisfiedBy.2" 
     *  "Top.X.MaxPowerGC.:value" --> "/Top/X/MaxPowerGC/@value"
	 */
	public static String dotPath2EastADLPath(String dotPath){

		String eastADLPath = dotPath;
		eastADLPath = eastADLPath.replace(':',  '@');
		eastADLPath = "/" + eastADLPath.replace('.', '/');
		eastADLPath = eastADLPath.replace('-', '.');

		return eastADLPath;
	}

	/***
	 * dotPaths are NMTOKEN, and may only contain 
	 * letters, digits, periods (.), hyphens (-), underscores (_), and colons (:)
	 * 
	 * Example
	 *  "/Top/x/y"                 --> "Top.x.y"
	 *  "/Top/X/@satisfiedBy.2"    --> "Top.X.:satisfiedBy-2
	 *  "/Top/X/MaxPowerGC/@value" --> "Top.X.MaxPowerGC.:value"
	 * 
	 * @param eastadlPath
	 * @return
	 */
	public static String eastADLPath2dotPath(String eastadlPath){
	
		String changeAt = eastadlPath.replace("@", ":");
		String noDotString = changeAt.replace(".", "-");
		return (noDotString.substring(1).replace('/', '.'));	
	}

	
	/**
	 *
	 * Standard pattern for instance Ref path, where context has j prototype members
	 * Note that context is assumed to be specified in reverse, since that is how the EATOP instance ref editor works. 
	 * 
	 * path = full real path to context(j-1) + "." + shortName(context(j-2)) + ... + "." + shortName(context(0)) + "." + shortName(target)
	 
	 * Used by ClampConnectors etc, i.e. when context can be a prototype list
	 */
	
	public static String instRef2DotPath(EObject instanceRef){ 
	
		EObject target = getInstanceRefTarget(instanceRef);
		List<EObject> context = getInstanceRefContext(instanceRef);

		if (context.isEmpty()){
			//If the instance ref has no context defined, the target must be a real element (i.e. port in a type)
			String eastadlPath = EAXMLprocessor.getSafeAbsoluteQualifiedName(target);
			return EAXMLprocessor.eastADLPath2dotPath(eastadlPath);		
		}
		int last = context.size() - 1;
		
		String eastadlPathStartContext = EAXMLprocessor.getSafeAbsoluteQualifiedName(context.get(last));
		String path = EAXMLprocessor.eastADLPath2dotPath(eastadlPathStartContext);		
		for (int i=last-1; i >= 0; i--){
				path += "." + elementName(context.get(i));
		}
		path += "." + elementName(target);
		
		return path;
	}

	//The instance ref applies to an EAConnector = FunctionConnector, HardwareConnector or FaultFailurePropagationLink
	//                         
	//We do not use the standard pattern instref = prototypepath + "/" + shortname(port) since that
	//is not unique when having nested prototypes.
	public static String instRef2DotPath(String connectorDotPath, EObject connector_instanceRef){
		
		EObject port = getInstanceRefTarget(connector_instanceRef);
		List<EObject> prototypes = getInstanceRefContext(connector_instanceRef);
		
		if (!(connector_instanceRef.eContainer() instanceof EAConnector)){
			Utils.showErrorMessage("Expected instance of EAConnector in instRef2DotPath");
			return null;
		}
		
		if (prototypes.isEmpty()){
			//If the instance ref has no context defined, the target must be a real element (i.e. port in a type)
			String eastadlPath = EAXMLprocessor.getSafeAbsoluteQualifiedName(port);
			return EAXMLprocessor.eastADLPath2dotPath(eastadlPath);
		}

		if ((prototypes.size() > 1)){
			Utils.showErrorMessage("Expected max one prototype for EAConnector instanceRef");
			return null;
		}
			
		//EAConnector  - Base instref path on (virtual) path of father = type or prototype that contains the connector = group node
		EObject prototype = prototypes.get(0);
		String dotPath = connectorDotPath;
		String fatherDotPath = toFatherPath(dotPath);
		return fatherDotPath + "." + EAXMLprocessor.elementName((Referrable)prototype) + "." + EAXMLprocessor.elementName((Referrable)port);
	}
	
	public static String toFatherPath(String dotPath){
		
		int lastDot = dotPath.lastIndexOf('.');
		if (lastDot != -1){
			return dotPath.substring(0, dotPath.lastIndexOf('.'));		
		}
		
		//EAXML node
		return "";
	}


    public static EObject getInstanceRefTarget(EObject instanceRef){
    	return ModelSearcher.getInstanceRefTarget(instanceRef);
    }

    public static List<EObject> getInstanceRefContext(EObject instanceRef){
		List<EObject> context = null;
		for (EStructuralFeature feature : instanceRef.eClass().getEAllStructuralFeatures()) {
			EAnnotation annotation = feature.getEAnnotation("Stereotype"); //$NON-NLS-1$
			if (annotation != null && annotation.getDetails().containsValue("instanceRef.context")) { //$NON-NLS-1$
				
				if (feature.isMany())
					context = (List<EObject>) instanceRef.eGet(feature);
				else{
					context = new ArrayList<EObject>();
					EObject eo = (EObject)instanceRef.eGet(feature);
					if (eo != null){
						context.add((EObject)instanceRef.eGet(feature));
					}
				}
		}
		}
		return context;
    	
    }

    /***
     * Extracts the role name from the MetaClass name, i.e. Satisfy_satisfiedBy -> satisfiedBy
     * @param instRef
     * @return
     */
    
	public static String getRoleName(EObject instRef) {

		String className = instRef.eClass().getName();
		String roleName;
		
		int indx = className.lastIndexOf('_');
		if (indx == -1){
			roleName = className;
		}
		else {
			roleName = className.substring(indx + 1);
		}
		return roleName;
	}
	
	
	/***
	 * Convert all selected treenodes to a list of eobjects with dotPaths
	 * 
	 * @param selectedTreeNodes
	 * @return
	 */
	 public static List<EObjectWithDotPath> findDotPaths(ITreeSelection selectedTreeNodes){
		
		List<EObjectWithDotPath> list = new ArrayList<EObjectWithDotPath>();
		
		@SuppressWarnings("rawtypes")
		Iterator iterSelectedNodes = selectedTreeNodes.iterator();
		while(iterSelectedNodes.hasNext())
		{
			Object object = iterSelectedNodes.next(); 
		
			String pathWithSlashes = null;
			String pathWithDots = null;
			
			if (object instanceof EObject){
				//a non-virtual EObject selected
				EObject eo = (EObject)object;
				pathWithSlashes = EAXMLprocessor.getSafeAbsoluteQualifiedName(eo);
				pathWithDots = EAXMLprocessor.eastADLPath2dotPath(pathWithSlashes);
				list.add(new EObjectWithDotPath(pathWithDots, eo));
			}
			else if (object instanceof ChildWrapper){
				// a virtual object selected
				TreePath[] treePaths = selectedTreeNodes.getPathsFor(object);
			
				TreePath path = treePaths[0];
				
				list.add(prototypePath(path));
			}
		}	
		return list;
	}

	
	/***
	 * Builds the virtual dotPath from a TreePath

	 * @param path
	 * @return
	 */
	protected static EObjectWithDotPath prototypePath(TreePath path) {
		//Skip first segments File
		String virtualPath = "";
		EObject eObj = null;
		for (int s = 3;  s < path.getSegmentCount(); s++){
			Object element = path.getSegment(s);
			
			if (element instanceof EObject){
				eObj = (EObject)element;
			}
			
			else if (element instanceof ChildWrapper){
				ChildWrapper wrapper = (ChildWrapper)element;
				eObj = wrapper.getObject();
			}

			if (!virtualPath.isEmpty())
			{
				virtualPath += ".";
			}
			virtualPath += ((EAElement)eObj).getShortName();
		}
		return new EObjectWithDotPath(virtualPath, eObj);
	}
	
	
	//assume dotPath ends with :attribute
	public static String getAttributeValue (String dotPath){
	
		int colon = dotPath.indexOf(':');
		String attribute = dotPath.substring(colon + 1);
		
		
		//Convert property text format "Is Elementary" to feature name "isElementary"
		//This is a simple approach, if it does not work out well maybe can use plugin.properties of east-adl.edit
		String attributeNoSpace = attribute.replaceAll(" ", "");
		String featureName = Character.toLowerCase(attributeNoSpace.charAt(0)) + attributeNoSpace.substring(1);

		String objPath = dotPath.substring(0,colon);
		EObject eObject = getEObjectbyDotPath(objPath);
		
	
		//Get attribute in eObject
		EStructuralFeature feature = eObject.eClass().getEStructuralFeature(featureName);
		
		if (feature == null){
			Utils.showErrorMessage("Unknown attribute " + attribute + ", can not find corresponding feature.");
		}
		
		String elementName = elementName(eObject);
		if (feature instanceof EAttribute){
			
			Object attributeValue = eObject.eGet(feature);
			
			if (attributeValue != null){
				return attributeValue.toString();
			}
			
			//For an attribute with no value, it will actually be null
			return "";	
		}
		else if (feature instanceof EReference){
			String absoluteQualifiedName = EastADLURIFactory.getAbsoluteQualifiedName(eObject);
			if (absoluteQualifiedName.length() > 0) {
				return elementName + " [" + absoluteQualifiedName + "]"; 
			}
		}
		
		return elementName; 
	}
	
}