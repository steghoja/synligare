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
package org.eclipse.eatop.testutils;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Map;

import org.eclipse.eatop.common.preferences.IEastADLPreferences;
import org.eclipse.eatop.common.resource.impl.EastADLResourceFactoryImpl;
import org.eclipse.eatop.geastadl.ginfrastructure.gelements.GEAPackage;
import org.eclipse.eatop.geastadl.ginfrastructure.gelements.GEAPackageableElement;
import org.eclipse.eatop.geastadl.ginfrastructure.gelements.GIdentifiable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.sphinx.platform.util.ReflectUtil;
import org.eclipse.sphinx.testutils.AbstractTestCase;

@SuppressWarnings("nls")
public abstract class AbstractEastadlTestCase extends AbstractTestCase {

	public static final String ID_SEPARATOR = ".";

	protected EPackage ePackage;

	protected EastADLResourceFactoryImpl resourceFactory = null;

	protected int nameIndex = 0;

	public AbstractEastadlTestCase() {
	}

	public AbstractEastadlTestCase(EPackage ePackage, EastADLResourceFactoryImpl resourceFactory) {
		this.ePackage = ePackage;
		this.resourceFactory = resourceFactory;
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		// Generally speaking, validate with schema when loading EASTADL XML files during tests
		setValidateOnLoad(true);
	}

	public void setValidateOnLoad(boolean validateOnLoad) {
		IEastADLPreferences.XSD_VALIDATION_ON_LOAD.set(validateOnLoad);
		if (validateOnLoad) {
			setIgnoreLoadProblems(false);
		}
	}

	@Override
	public void setIgnoreLoadProblems(boolean ignoreLoadProblems) {
		super.setIgnoreLoadProblems(ignoreLoadProblems);
		if (ignoreLoadProblems) {
			IEastADLPreferences.XSD_VALIDATION_ON_LOAD.set(false);
		}
	}

	protected EObject loadInputFile(String fileName) throws Exception {
		return loadInputFile(fileName, resourceFactory, null);
	}

	protected EObject loadInputFile(String fileName, Map<?, ?> options) throws Exception {
		return loadInputFile(fileName, resourceFactory, options);
	}

	@Override
	protected String loadInputFileAsString(String fileName) throws Exception {
		return loadFileAsString(getTestFileAccessor().openInputFileInputStream(fileName));
	}

	protected EObject loadWorkingFile(String fileName) throws Exception {
		return loadWorkingFile(fileName, resourceFactory, null);
	}

	@Override
	protected String loadWorkingFileAsString(String fileName) throws Exception {
		return loadFileAsString(getTestFileAccessor().openWorkingFileInputStream(fileName));
	}

	@Override
	protected String loadFileAsString(InputStream inputStream) throws Exception {
		inputStream = new BufferedInputStream(inputStream);
		try {
			byte[] buffer = new byte[1024];
			int bufferLength;
			StringBuilder content = new StringBuilder();
			while ((bufferLength = inputStream.read(buffer)) > -1) {
				content.append(new String(buffer, 0, bufferLength));
			}
			return content.toString();
		} finally {
			inputStream.close();
		}
	}

	protected static String normalizeNewlines(String s) {
		// normalize line breaks for test portability
		return s.replace("\r\n", java.lang.System.getProperty("line.separator"));
	}

	protected String loadWorkingFileAsNormalizedString(String fileName) throws Exception {
		String s = loadWorkingFileAsString(fileName);

		s = normalizeNewlines(s);

		return s;
	}

	protected String loadInputFileAsNormalizedString(String fileName) throws Exception {
		String s = loadInputFileAsString(fileName);

		s = normalizeNewlines(s);

		return s;
	}

	protected void saveWorkingFile(String fileName, EObject eaxml) throws Exception {
		saveWorkingFile(fileName, eaxml, resourceFactory, null);
	}

	protected void saveWorkingFile(String fileName, EObject eaxml, ResourceFactoryImpl rfi) throws Exception {
		saveWorkingFile(fileName, eaxml, rfi, null);
	}

	public <T extends GIdentifiable> T createIdentifiable(Class<T> clz) throws Exception {
		return createIdentifiable(clz, clz.getSimpleName() + "_" + nameIndex++);
	}

	public <T extends GIdentifiable> T createIdentifiable(Class<T> clz, String shortName) throws Exception {
		EClass eClass = (EClass) ReflectUtil.invokeMethod(ePackage, "getEClass", clz);
		@SuppressWarnings("unchecked")
		T identifiable = (T) ePackage.getEFactoryInstance().create(eClass);
		identifiable.gSetShortName(shortName);
		return identifiable;
	}

	public <T extends GEAPackageableElement> T createElement(Class<T> clz, GEAPackage ownerPack) throws Exception {
		return createElement(clz, clz.getSimpleName() + "_" + nameIndex++, ownerPack);
	}

	public <T extends GEAPackageableElement> T createElement(Class<T> clz, String shortName, GEAPackage ownerPack) throws Exception {
		T identifiable = createIdentifiable(clz, shortName);
		ownerPack.gGetElement().add(identifiable);
		return identifiable;
	}

}
