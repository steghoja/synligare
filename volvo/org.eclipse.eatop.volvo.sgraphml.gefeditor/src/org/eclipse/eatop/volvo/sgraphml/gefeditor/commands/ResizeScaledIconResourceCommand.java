package org.eclipse.eatop.volvo.sgraphml.gefeditor.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.eatop.volvo.sgraphml.gefeditor.Utils;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.model.ModelProcessor;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.model.resources.ResourceManager;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.model.resources.ScaledIconResource;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.gef.commands.Command;
import org.eclipse.swt.graphics.Image;
import org.graphdrawing.graphml.xmlns.EdgeType;

import eu.synligare.sgraphml.ResourceType;
import eu.synligare.sgraphml.ScaledIconType;

public class ResizeScaledIconResourceCommand extends Command {

	double newXScale;
	double newYScale;

	
	String sResourceID;
	//Image oldSWT;
	double oldXScale;
	double oldYScale;
	
	
	public void setResourceID(String resourceID) {
		sResourceID = resourceID;
	}

	/***
	 * 
	 * 
	 */
	public void setNewSize(double xScale, double yScale) {
		newXScale = xScale;
		newYScale = yScale;
	}

	/***
	 * Updates the model with a base 64 string representing an image with the new size. 
	 * Due to efficiency, we do not listen to the resource object and then update the 
	 * AWTBufferedImageResource object from the model (as in the pure MVC-pattern). Instead
	 * we update the image directly in the resource.
	 */
    @Override public void execute() {
	    //ScaledIconType scaledIcon = (ScaledIconType)ResourceManager.INSTANCE.getModelResource(sResourceID);
	    ScaledIconResource scaledIconResource = (ScaledIconResource)Utils.INSTANCE.getResourceManager().getSgraphmlResource(sResourceID);
	    //	    ResourceType modelResource = ResourceManager.INSTANCE.getModelResource(sResourceID);
	
	    ScaledIconType scaledIcon = scaledIconResource.getModelScaledIcon();
	    
	    oldXScale = scaledIcon.getXScale();
	    oldYScale = scaledIcon.getYScale();
	    
	//	newXScale = newWidth / oldXScale;
	//	newYScale = newHeight / oldYScale;

	    
	    scaledIcon.setXScale(newXScale);
	    scaledIcon.setYScale(newYScale);
	    
	    //TODO: Kanske den egentligen skulle lyssna på objektet här : TODO
	    scaledIconResource.scaleImage();
	    
    	
/*	    String encodedImage = awtResource.encodeAWTImageBase64();
	    
		FeatureMap feamap = modelResource.getMixed();
		for (FeatureMap.Entry fEntry : feamap){
			EStructuralFeature feature = fEntry.getEStructuralFeature();
			if (feature.getName().equals("text")) {
				//XMLTypeFeatures.TEXT is many
				List<String> strings = new ArrayList<String>();
				strings.add(encodedImage);
				modelResource.eSet(feature, strings);
				
				break;
			}
			
		}
	*/
	    
    }
 
    @Override public void undo() {
	    ScaledIconType scaledIcon = (ScaledIconType)Utils.INSTANCE.getResourceManager().getModelResource(sResourceID);

    	scaledIcon.setXScale(oldXScale);
	    scaledIcon.setYScale(oldYScale);

	    ScaledIconResource scaledIconResource = (ScaledIconResource)Utils.INSTANCE.getResourceManager().getSgraphmlResource(sResourceID);
	    //TODO: Kanske den egentligen skulle lyssna på objektet här : TODO
	    scaledIconResource.scaleImage();
	    
    	
//    	AWTBufferedImageResource awtResource = (AWTBufferedImageResource)ResourceManager.INSTANCE.getSgraphmlResource(sResourceID);
//	    awtResource.setCurrentSWTImage(oldSWT);
    }
    
    
	
	
}
