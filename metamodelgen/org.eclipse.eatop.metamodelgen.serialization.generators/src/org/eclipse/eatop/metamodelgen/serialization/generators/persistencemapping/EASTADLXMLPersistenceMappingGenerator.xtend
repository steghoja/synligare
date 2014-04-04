/**
 * <copyright>
 *  
 * Copyright (c) 2014 itemis and others.
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 
 * which accompanies this distribution, and is
 * available at http://www.eclipse.org/org/documents/epl-v10.php
 *  
 * Contributors: 
 *     itemis - Initial API and implementation
 *  
 * </copyright>
 * 
 */
 package org.eclipse.eatop.metamodelgen.serialization.generators.persistencemapping

import org.eclipse.emf.ecore.EPackage
import org.eclipse.sphinx.emf.serialization.generators.persistencemapping.CreateDefaultXSDExtendedMetaData
import org.eclipse.sphinx.emf.serialization.generators.persistencemapping.XMLPersistenceMappingGenerator
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.emf.ecore.EObject
import org.eclipse.core.runtime.SubMonitor
import org.eclipse.core.runtime.OperationCanceledException

class EASTADLXMLPersistenceMappingGenerator extends XMLPersistenceMappingGenerator {
	new(EPackage rootModel) {
		super(rootModel);
	} 

	override public EObject execute(IProgressMonitor monitor) {
		monitor.beginTask("XML persistence mapping generatering ...", 100);
		monitor.subTask("XML persistence mapping generatering ...");
		
		val SubMonitor progress = SubMonitor.convert(monitor, 100);
			if (progress.isCanceled()) {
				throw new OperationCanceledException();
			}
				
		// Configure Default XSD Extended MetaData
		val CreateDefaultXSDExtendedMetaData createDefaultXSDExtendedMetaData = createDefaultXSDExtendedMetaData();
		rootModel = createDefaultXSDExtendedMetaData.execute(progress.newChild(40));
		
		// Configure InstanceRef Association
		val ConfigureInstanceRefAssociation cfgInstanceRefAssociation = new ConfigureInstanceRefAssociation(rootModel);
		rootModel = cfgInstanceRefAssociation.execute(progress.newChild(30));
		 
		// Configure InstanceRef Class
		val ConfigureInstanceRefClass cfgInstanceRefClass = new ConfigureInstanceRefClass(rootModel);
		return cfgInstanceRefClass.execute(progress.newChild(30));
	} 

	// create EAST-ADL default extended meta data to provide the custom simple type and the pattern
	override protected CreateDefaultXSDExtendedMetaData createDefaultXSDExtendedMetaData(){	
		return new CreateEASTADLDefaultXSDExtendedMetaData(rootModel, "EAXML");
	}
}