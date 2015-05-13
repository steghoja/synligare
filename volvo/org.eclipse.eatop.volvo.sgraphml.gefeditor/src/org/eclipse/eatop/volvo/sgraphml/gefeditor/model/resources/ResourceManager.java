package org.eclipse.eatop.volvo.sgraphml.gefeditor.model.resources;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.eclipse.draw2d.graph.EdgeList;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.model.ModelProcessor;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.actions.AddBookmarkAction;
import org.graphdrawing.graphml.xmlns.GraphType;

import eu.synligare.sgraphml.GroupNodeType;
import eu.synligare.sgraphml.ResourceBlockType;
import eu.synligare.sgraphml.ResourceType;
import eu.synligare.sgraphml.ScaledIconType;
import eu.synligare.sgraphml.SgraphmlFactory;
import eu.synligare.sgraphml.SgraphmlPackage;
import eu.synligare.sgraphml.ShapeNodeType;

public class ResourceManager {
	
	//Singleton
	public ResourceManager(){};
//	static public ResourceManager INSTANCE = new ResourceManager();
	
	protected ResourceBlockTypeAdapter resourceBlockAdapter = new ResourceBlockTypeAdapter();
	

	//Use a map from Resource ID attribute to ResourceData 
	protected Map<String, ISgraphmlResource> mapID2SgraphmlResource = new HashMap<String, ISgraphmlResource>();

	public void init(){
		 ModelProcessor.INSTANCE.getResourceBlock().eAdapters().add(resourceBlockAdapter);
    	 addResources(ModelProcessor.INSTANCE.getResourceBlock());
	}

	//TODO: Make sure this is called somewhere
	public void cleanUp(){
	    ModelProcessor.INSTANCE.getResourceBlock().eAdapters().remove(resourceBlockAdapter);
	}
	
	
	public ISgraphmlResource getSgraphmlResource(String resourceID){
		if (mapID2SgraphmlResource.containsKey(resourceID)){
			return mapID2SgraphmlResource.get(resourceID);
		}
		else {
			System.out.print("Unknown resourceID = " + resourceID);
			return null;
		}
	}

	public ResourceType getModelResource(String resourceID){
		if (mapID2SgraphmlResource.containsKey(resourceID)){
			return mapID2SgraphmlResource.get(resourceID).getModelElement();
		}
		else {
			System.out.print("Unknown resourceID = " + resourceID);
			return null;
		}
	}

	
	/**
	 * Adds all resources in the .sgrahpml model to the manager and creates the corresponding resources.
	 */
	public void addResources(ResourceBlockType resources)
	{
		EList<ResourceType> modelResources = resources.getResource();

		for (ResourceType modelResource : modelResources) {
					addResource(modelResource);
		}
	}

	protected void addResource(ResourceType modelResource) {
		String id = (String)modelResource.getId();

		if (modelResource.getType() == null)
		{
			//xsd processContents = "strict" rather than "skip" means that we get the types here.
			//with "skip" we need to work with anyType which requires more work.

			//Assume this is a scaled icon resource
			boolean bFoundScaledIcon = false;
			ScaledIconType modelScaledIcon = null;
			FeatureMap feamap = modelResource.getMixed();
			for (FeatureMap.Entry fEntry : feamap){
				EStructuralFeature feature = fEntry.getEStructuralFeature();
				if (fEntry.getEStructuralFeature().getFeatureID() == SgraphmlPackage.DOCUMENT_ROOT__SCALED_ICON) {		//7
					modelScaledIcon = (ScaledIconType)fEntry.getValue();
					bFoundScaledIcon = true;	
					break;
				}
			}

			if (!bFoundScaledIcon){
				System.out.println ("Expected a scaled icon type in resource id = " + id);
			}
			
			ScaledIconResource scaledIconResource = new ScaledIconResource();
			scaledIconResource.setModelElement(modelResource);
			scaledIconResource.setID(id);
			mapID2SgraphmlResource.put(id, scaledIconResource);

		}

		//type = "image"
		else if (modelResource.getType().equals(SWTImageResource.getType()))
		{

			//get base64 string
			String sBase64 = null;
			FeatureMap feamap = modelResource.getMixed();
			for (FeatureMap.Entry fEntry : feamap){
				EStructuralFeature feature = fEntry.getEStructuralFeature();
				if (feature.getName().equals("text")) {
					sBase64 = (String)fEntry.getValue();
					break;
				}
			}
			
			SWTImageResource swtImageRes = new SWTImageResource();
			swtImageRes.setModelElement(modelResource);
				
			swtImageRes.decodeBase64(sBase64);
			mapID2SgraphmlResource.put(id, swtImageRes);

		}
	}

	/***
	 * Looks for the image among the loaded resources. Comparison done on actual image data.
	 * 
	 * @param image - the image to find
	 * @return id of image, or null if not found
	 */
	public String findID(Image image) {
	
		//convert image to byte stream to be able to compare it easily
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageLoader imageLoader = new ImageLoader();
		imageLoader.data = new ImageData[] {image.getImageData()};
		imageLoader.save(baos, SWT.IMAGE_PNG);
		try {
			baos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] targetImageBytes = baos.toByteArray();

		
		//Go through the resources and see if there is excatly that image
		for (Map.Entry<String, ISgraphmlResource> entry : mapID2SgraphmlResource.entrySet()) {
		    String key = entry.getKey();
		    ISgraphmlResource sgraphmlResource = entry.getValue();
		   
		    if (sgraphmlResource instanceof SWTImageResource)
		    {
		    	SWTImageResource swtImageResource = (SWTImageResource)sgraphmlResource;
		    	if (Arrays.equals(targetImageBytes, swtImageResource.getImageBytes())){
		    		return key;
		    	}
		    }
		}
		
		return null;
	}

	/**
	 * 
	 * @param imageID - the ID of the ImageID we need a scaledIcon resource for
	 * @return
	 */
	public String findUnScaledIconResourceID(String imageID){
		
		for (Map.Entry<String, ISgraphmlResource> entry : mapID2SgraphmlResource.entrySet()) {
		    String key = entry.getKey();
		    ISgraphmlResource sgraphmlResource = entry.getValue();
		   
		    if (sgraphmlResource instanceof ScaledIconResource)
		    {
		    	ScaledIconResource scaledIconResource = (ScaledIconResource)sgraphmlResource;
		    	
		    	String refID = (String)scaledIconResource.getModelScaledIcon().getImageIcon().getImage();
		    	
	    	  	if ((refID.equals(imageID) && scaledIconResource.isUnscaled())){
		    		return scaledIconResource.getID();
		    	}
		    }
		}		
		return null;
	}
	
	
	/***
	 * Finds the highest id used by the current resources in the map. (Would be same as
	 * #resources if there are no gaps, but current implementation does not guarantee that.) 
	 * 
	 */
	public int highestID(){

		int highest = 0;
		
		for (String key : mapID2SgraphmlResource.keySet()) {
			int id = Integer.parseInt(key);
			if (id > highest){
				highest = id;
			}
		}
		
		return highest;
	}
	
   
	 public class ResourceBlockTypeAdapter implements Adapter {
		 
		    @Override
		    public void notifyChanged(Notification notification) {
		    	
	    		if (notification.getEventType() == Notification.ADD){
	    			
	    			ResourceType r = (ResourceType)notification.getNewValue();
	    			addResource (r);
	    		}
		    }
		 
		    @Override
		    public Notifier getTarget() {
		      return ModelProcessor.INSTANCE.getResourceBlock();
		    }
		 
		    @Override
		    public void setTarget(Notifier newTarget) {
		      // Do nothing.
		    }
		 
		    @Override 
		    public boolean isAdapterForType(Object type) {
		    	return type.equals(ResourceBlockType.class);
		    }
		  }
	}