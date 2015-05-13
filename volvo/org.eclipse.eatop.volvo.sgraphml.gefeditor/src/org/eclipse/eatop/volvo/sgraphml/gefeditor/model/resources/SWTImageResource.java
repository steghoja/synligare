package org.eclipse.eatop.volvo.sgraphml.gefeditor.model.resources;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.Display;

import eu.synligare.sgraphml.ResourceType;

public class SWTImageResource implements ISgraphmlResource {

	Image swtImage;
	byte[] imageBytes; //(decoded base64 string)
	
	protected ResourceType modelResource;
	
	public byte[] getImageBytes(){
		return imageBytes;
	}
	

	@Override
	public String getID() {
		return (String)modelResource.getId();
	}
	

	@Override
	public ResourceType getModelElement() {
		return modelResource;
	}

	@Override
	public void setModelElement(ResourceType modelElement) {
	
		ResourceType r = (ResourceType)modelElement;
		
		if (r.getType().toString().equals(getType()))
		{
			this.modelResource = r;
		}
		else
		{
			System.out.println("Cannot set modelElement as SWTImageResource");
		}
	}
	public Image getSwtImage() {
		return swtImage;
	}

	public void setSwtImage(Image swtImage) {
		this.swtImage = swtImage;
	}

	/***
	 * 
	 * @param encodedImage - base64 string, including newlines
	 */
	public void decodeBase64(String encodedImage){
			
		imageBytes = DatatypeConverter.parseBase64Binary(encodedImage);
		
		swtImage = new Image(Display.getCurrent(), new ByteArrayInputStream(imageBytes));
	}
	
	static public String getType(){
		return "image";
	}
	
	
	/***
	 * 
	 * @return the buffered image as a base 64 string, with newlines added
	 */
		
	static public String encodeImageBase64(Image image){

		String encodedImage = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		ImageLoader imageLoader = new ImageLoader();
		imageLoader.data = new ImageData[] {image.getImageData()};
		imageLoader.save(baos, SWT.IMAGE_PNG);
		try {
			baos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		encodedImage = DatatypeConverter.printBase64Binary(baos.toByteArray());
	
//		return encodedImage;
		
		try {
			baos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//Keep line lengths reasonable 
		String eolString  = "\r\n"; 
		int nImageBytesPerRow = 76;
		
		int nLength = encodedImage.length() + encodedImage.length() / nImageBytesPerRow * eolString.length(); 
		StringBuilder builder = new StringBuilder(nLength);
		int nPos = 0;
		while (nPos + nImageBytesPerRow < encodedImage.length()){
			builder.append(encodedImage.substring(nPos, nPos + nImageBytesPerRow));
			builder.append(eolString);
			nPos += nImageBytesPerRow;
		}
		int nRemaining = encodedImage.length() - nPos;
		if (nRemaining > 0)
		{
			builder.append(encodedImage.substring(nPos, nPos + nRemaining));
		}
		
		return builder.toString();
		
		
	}

}
