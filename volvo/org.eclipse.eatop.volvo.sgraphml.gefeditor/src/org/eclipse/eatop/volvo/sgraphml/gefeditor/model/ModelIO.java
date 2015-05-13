package org.eclipse.eatop.volvo.sgraphml.gefeditor.model;

import java.util.Collections;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.graphdrawing.graphml.xmlns.DocumentRoot;
import org.graphdrawing.graphml.xmlns.GraphType;
import org.graphdrawing.graphml.xmlns.GraphmlType;
import org.graphdrawing.graphml.xmlns.XmlnsPackage;
import org.graphdrawing.graphml.xmlns.util.XmlnsResourceFactoryImpl;

public class ModelIO {
	private ModelIO(){};
	public static ModelIO INSTANCE = new ModelIO();
	
	ResourceSet resourceSet;
	Resource resource;
	URI uri;

	
	public GraphmlType ReadModel(IFile file)  {
	
		resourceSet = new ResourceSetImpl();
		
		// Register the appropriate resource factory to handle all file extensions.
		//
		// The resource can be loaded either as XmlnsResourceFactoryImpl or GraphmlResourceFactoryImpl
		// since both namespaces are registered both EPackages will be used in either case.
		// But to make use of the graphml structure we need to read it as XmlnsResource.
		
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put
		("sgrapml", 		
		 new XmlnsResourceFactoryImpl());

		// Register all packages in the local resource set to ensure they are available during loading.
		// Actually accessing XmlnsPackage.eInstance => it gets registered in the global registry AND 
		// all dependent EPackages too (in same genmodel)
		
		resourceSet.getPackageRegistry().put
					(XmlnsPackage.eNS_URI, 
					XmlnsPackage.eINSTANCE);		
		
		// Construct the URI for the instance file.
		uri = URI.createURI(file.getLocationURI().toString());
		
		try {
			// Demand load resource for this file.
			//
			resource = resourceSet.getResource(uri, true);
					
			
			// Validate the contents of the loaded resource.
			//
			for (EObject eObject : resource.getContents()) {
				Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eObject);
				if (diagnostic.getSeverity() != Diagnostic.OK) {
					printDiagnostic(diagnostic, "");
				}
			}

			//Locate the first graph
			DocumentRoot docRoot = (DocumentRoot)resource.getContents().get(0); 
			return docRoot.getGraphml();
			
			
		
		}
		catch (Exception exception) {
			System.out.println("Problem loading " + uri);
			exception.printStackTrace();
			return null;
		}
	}
	
	public boolean WriteModel(){
		try{
		
			if (resource != null){
				resource.save(Collections.EMPTY_MAP);
				return true;
			}
			return false;
		}
		
		catch (Exception exception) {
			System.out.println("Problem saving " + uri);
			exception.printStackTrace();
			return false;
		}
	}
	
	
		/**
		 * <!-- begin-user-doc -->
		 * Prints diagnostics with indentation.
		 * <!-- end-user-doc -->
		 * @param diagnostic the diagnostic to print.
		 * @param indent the indentation for printing.
		 * @generated
		 */
		protected static void printDiagnostic(Diagnostic diagnostic, String indent) {
			System.out.print(indent);
			System.out.println(diagnostic.getMessage());
			for (Diagnostic child : diagnostic.getChildren()) {
				printDiagnostic(child, indent + "  ");
			}
		}
		
}
