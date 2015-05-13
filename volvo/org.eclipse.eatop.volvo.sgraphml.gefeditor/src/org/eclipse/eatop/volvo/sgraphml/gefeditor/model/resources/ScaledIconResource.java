package org.eclipse.eatop.volvo.sgraphml.gefeditor.model.resources;

import org.eclipse.eatop.volvo.sgraphml.gefeditor.Utils;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.model.resources.ISgraphmlResource;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;

import eu.synligare.sgraphml.ImageIconType;
import eu.synligare.sgraphml.ResourceType;
import eu.synligare.sgraphml.ScaledIconType;
import eu.synligare.sgraphml.SgraphmlPackage;

public class ScaledIconResource implements ISgraphmlResource {

	
	ResourceType modelResource;
	ScaledIconType modelScaledIcon;
	Image	imageSWT;
	String ID;
	

	public void setID(String id) {
		this.ID =id;
	}

	@Override
	public String getID() {
		return ID;
	}

	@Override
	public ResourceType getModelElement() {
		return modelResource;
	}

	@Override
	public void setModelElement(ResourceType modelElement) {
		modelResource = (ResourceType)modelElement;
		
		modelScaledIcon = findScaledIcon();
	}

	public ScaledIconType getModelScaledIcon(){
		return modelScaledIcon;
	}


	public ScaledIconType findScaledIcon(){
		FeatureMap fm = modelResource.getMixed();
		
		for (FeatureMap.Entry fEntry : fm){
			EStructuralFeature feature = fEntry.getEStructuralFeature();
			if (fEntry.getEStructuralFeature().getFeatureID() == SgraphmlPackage.DOCUMENT_ROOT__SCALED_ICON) {		//7
				return (ScaledIconType)fEntry.getValue();
			}
		}
		return null;
	}
	
/*	public String getType() {
		return (String)modelResource.getType();
	}
*/
	public Image getImage(){
		if (imageSWT == null)
		{
			return scaleImage();
		}
		
		return imageSWT;
	}
	
	public Image scaleImage(){
		if (isUnscaled() &&
			imageSWT != null)
			{
				return imageSWT; 
			}

		//find the underlying awt image
		ImageIconType imageIcon = modelScaledIcon.getImageIcon();
		String refID = (String)imageIcon.getImage();
		
	
		ISgraphmlResource resource = Utils.INSTANCE.getResourceManager().getSgraphmlResource(refID);

		ImageData imageData = null;
	/*	if (resource instanceof AWTBufferedImageResource)
		{
			AWTBufferedImageResource awtResource = (AWTBufferedImageResource)resource;
			imageData = awtResource.getOriginalSWTImage().getImageData();
		}
		else*/ 
		if (resource instanceof SWTImageResource){
			SWTImageResource swtResource = (SWTImageResource)resource;
			imageData = swtResource.getSwtImage().getImageData();			
		}
	
		int newWidth = (int)Math.round(modelScaledIcon.getXScale() * imageData.width);
		int newHeight = (int)Math.round(modelScaledIcon.getYScale() * imageData.height);
		imageSWT = new Image(Display.getCurrent(), imageData.scaledTo(newWidth, newHeight));
		return imageSWT;
	
	}
	
	protected boolean isUnscaled(){
		return (modelScaledIcon.getXScale() == 1.0) && (modelScaledIcon.getYScale() == 1.0);  
	}

}
