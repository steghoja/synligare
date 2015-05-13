package org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.draw2d.RoundedRectangleAnchor;
import org.eclipse.eatop.eastadl21.AnalysisFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.EAPrototype;
import org.eclipse.eatop.eastadl21.ErrorModelPrototype;
import org.eclipse.eatop.eastadl21.FunctionPort;
import org.eclipse.eatop.eastadl21.FunctionPrototype;
import org.eclipse.eatop.eastadl21.HardwareComponentPrototype;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.Activator;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.Utils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;

import eu.synligare.sgraphml.ShapeTypeType;

public class VisualAttributes {

	//singleton
	static public VisualAttributes INSTANCE = new VisualAttributes();
	private VisualAttributes() {}

	//Column definitions
	protected static int METACLASSNAME_COLUMN 	 = 0;
	protected static int SHAPE_COLUMN 			 = 1;
	protected static int BACKGROUNDCOLOR_COLUMN  = 2;
	protected static int BACKGROUNDCOLOR2_COLUMN = 3;
	protected static int LINEWIDTH_COLUMN 		 = 4;
	protected static int LINECOLOR_COLUMN 		 = 5;
	protected static int COLUMNS                 = 6;




	class Attributes{
		String backgroundColor;
		String backgroundColor2;
		ShapeTypeType shape;
		float lineWidth;
		String lineColor;
		
	}
	
	
	Map<String, Attributes> mapMetaClass2Attributes = new HashMap<String, Attributes>();
	
	public boolean readFile(){

		//Only read once
		if (!mapMetaClass2Attributes.isEmpty()){
			return true;
		}
		
		//bundleentry://930.fwk487819219/visualattributes.csv
		URL bundleRootURL = null;
		try {
			bundleRootURL = new URL(Activator.getDefault().getBundle().getEntry("/"), "visualattributes.csv");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		// get URL = file:/C:/Synligare/.../org.eclipse.eatop.volvo.sgraphml.gefeditor/visualattributes.csv
		// Note: For FileLocator.resolve to work, the file must be unpacked from the jar-archive. See seeting in feature.xml.
		URL pluginUrl = null;
		try {
			pluginUrl = FileLocator.resolve(bundleRootURL); 

		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}
		String path = pluginUrl.getPath(); 
		
		
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ";";
	 
		
		
		try {
	 
			br = new BufferedReader(new FileReader(path));
			
			//skip header line
			br.readLine();
			
			while ((line = br.readLine()) != null) {
			
				//at least one alphanumeric character?
				if (line.matches(".*\\w.*")){
				
					String[] metaClassAttributes = line.split(cvsSplitBy);
				
					if (metaClassAttributes.length < COLUMNS){
						Utils.showErrorMessage("VisualAttributes.csv format error, line " + line);
						return false;
					}
					
					Attributes attrib = new Attributes();
					
					attrib.backgroundColor = metaClassAttributes[BACKGROUNDCOLOR_COLUMN];
					attrib.backgroundColor2 = metaClassAttributes[BACKGROUNDCOLOR2_COLUMN];

					String shape = metaClassAttributes[SHAPE_COLUMN];
					attrib.shape = ShapeTypeType.getByName(metaClassAttributes[SHAPE_COLUMN]);

					String lineWidth = metaClassAttributes[LINEWIDTH_COLUMN];
					if (lineWidth.equals("as type")){
						attrib.lineWidth =  -1;
					}
					else {
							attrib.lineWidth =  Float.parseFloat(metaClassAttributes[LINEWIDTH_COLUMN]);
					}

					attrib.lineColor = metaClassAttributes[LINECOLOR_COLUMN];

					
					mapMetaClass2Attributes.put(metaClassAttributes[METACLASSNAME_COLUMN], attrib);
				}
			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		
		return true;
	}
	
	
	public String getBackgroundColor(EObject eo){
		String metaClass = eo.eClass().getName();
		if (mapMetaClass2Attributes.containsKey(metaClass)){
			Attributes a = mapMetaClass2Attributes.get(metaClass);

			if (a.backgroundColor.equals("as type")){
				EObject type = prototype2Type(eo);
				if (type == null){
					return "#ffffff";
				}
				else {
					return getBackgroundColor(type);
				}
			}
			else{
				return a.backgroundColor;
			}
		}		
		
		return "#bbbbbb";
	}

	/***
	 * returns empty string if color not defined
	 * @param eo
	 * @return
	 */
	public String getBackgroundColor2(EObject eo){
		String metaClass = eo.eClass().getName();
		if (mapMetaClass2Attributes.containsKey(metaClass)){
			Attributes a = mapMetaClass2Attributes.get(metaClass);

			if (a.backgroundColor2.equals("as type")){
				EObject type = prototype2Type(eo);
				if (type == null){
					return "#ffffff";
				}
				else {
					return getBackgroundColor2(type);
				}
			}
			else if (a.backgroundColor2.equals("none")){
				return null;
			}
			else{
				return a.backgroundColor2;
			}
		}		
		return "#bbbbbb";
	}

	
	private EObject prototype2Type(EObject prototype){
		if (prototype instanceof AnalysisFunctionPrototype){
			return ((AnalysisFunctionPrototype)prototype).getType();
		}
		if (prototype instanceof DesignFunctionPrototype){
			return ((DesignFunctionPrototype)prototype).getType();
		}
		if (prototype instanceof HardwareComponentPrototype){
			return ((HardwareComponentPrototype)prototype).getType();
		}

		Utils.showErrorMessage("Unsopported type in getBackgroundColor ");
		return null;
	}
	
	
	public ShapeTypeType getShape(EObject eo){
		String metaClass = eo.eClass().getName();

		if (mapMetaClass2Attributes.containsKey(metaClass)){
			Attributes a = mapMetaClass2Attributes.get(metaClass);
	
			if (a.shape == null){
				EObject type = prototype2Type(eo);
				if (type == null){
					return ShapeTypeType.RECTANGLE;
				}
				else {
					return getShape(type);
				}
			}
			else{
				return a.shape;
			}
		}		
		return ShapeTypeType.RECTANGLE;
		
	}

	public float getLineWidth(EObject eo){
		String metaClass = eo.eClass().getName();

		if (mapMetaClass2Attributes.containsKey(metaClass)){

			Attributes a = mapMetaClass2Attributes.get(metaClass);
			if (a.lineWidth == -1){
				EObject type = prototype2Type(eo);
				if (type == null){
					return 1.0f;
				}
				else {
					return getLineWidth(type);
				}
			}
			else{
				return a.lineWidth;
			}
		}		
		
		return 1.0f;
	}

	
	public String getLineColor(EObject eo){
		String metaClass = eo.eClass().getName();
		if (mapMetaClass2Attributes.containsKey(metaClass)){
			Attributes a = mapMetaClass2Attributes.get(metaClass);

			if (a.lineColor.equals("as type")){
				EObject type = prototype2Type(eo);
				if (type == null){
					return "#000000";
				}
				else {
					return getLineColor(type);
				}
			}
			else{
				return a.lineColor;
			}
		}		
		
		return "#000000";
	}
	
	
}
