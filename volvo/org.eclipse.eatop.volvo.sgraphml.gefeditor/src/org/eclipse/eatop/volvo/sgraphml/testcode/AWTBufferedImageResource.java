package org.eclipse.eatop.volvo.sgraphml.testcode;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.model.resources.ISgraphmlResource;
import org.eclipse.eatop.volvo.sgraphml.testcode.AWTSWTImageConverter;

import eu.synligare.sgraphml.ResourceType;

public class AWTBufferedImageResource implements ISgraphmlResource {

	protected BufferedImage originalBufferedImage;	//Image from embedded resourec in sgraphml, original size
	protected Image originalSWTImage;               //Converted image with original size
	
	//protected Image currentSWTImage;				//scaled image
	
	protected ResourceType modelResource;
	
		
	public ResourceType getModelResource() {
		return modelResource;
	}

	public void setModelResource(ResourceType modelResource) {
		if (modelResource.getType().toString().equals(getType()))
		{
			this.modelResource = modelResource;
		}
		else
		{
			System.out.println("Cannot set modelresource as AWTBufferedImageResource");
		}
	}

	static public String getType(){
		return "java.awt.image.BufferedImage";
	}
	
	public BufferedImage getOriginalBufferedImage() {
		return originalBufferedImage;
	}
	public Image getOriginalSWTImage() {
		return originalSWTImage;
	}

/*	
	public Image getCurrentSwtImage() {
		return currentSWTImage;
	}
	
	public void setCurrentSWTImage(Image imageSWT) {
		currentSWTImage = imageSWT; 
	}
*/
	/*
	public Dimension getCurrentSize(){
		return new Dimension (currentSWTImage.getImageData().width, currentSWTImage.getImageData().height);
	}
	*/
	/*
	public Image reSizeSWTImage(int newWidth, int newHeight){
		if (!(getCurrentSize().equals(newWidth, newHeight)))
		{
			ImageData imageData = originalSWTImage.getImageData();
			currentSWTImage = new Image(Display.getCurrent(), imageData.scaledTo(newWidth, newHeight));
		}
		return currentSWTImage;
	}
	*/
/***
 * 
 * @param encodedAWTImage the image represented as a base64 string
 */
	public void decodeBase64(String encodedAWTImage){

		byte[] bytes = DatatypeConverter.parseBase64Binary(encodedAWTImage);
		
		
		try {
			originalBufferedImage = ImageIO.read(new ByteArrayInputStream(bytes));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void convertAWTtoSWT(){
		ImageData imageData = AWTSWTImageConverter.convertToSWT(originalBufferedImage);
		originalSWTImage = new Image(Display.getCurrent(), imageData);
	}
	
	
	/***
	 * 
	 * @return the buffered image as a base 64 string
	 */
	public String encodeAWTImageBase64(){

		String encodedImage = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(originalBufferedImage, "png", baos);
		baos.flush();
		encodedImage = DatatypeConverter.printBase64Binary(baos.toByteArray());
		
		} catch (IOException e) {
			e.printStackTrace();
		}    
		finally{
			try {
				baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}

		//Keep line lengths reasonable 
		String eolString  = "&#13;\n";
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

	@Override
	public String getID() {
		return (String)modelResource.getId();
	}

	@Override
	public ResourceType getModelElement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setModelElement(ResourceType modelElement) {
		// TODO Auto-generated method stub
		
	}

}
	
