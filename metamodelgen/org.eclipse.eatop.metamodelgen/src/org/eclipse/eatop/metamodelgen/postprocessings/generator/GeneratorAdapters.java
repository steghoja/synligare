/**
 * <copyright>
 * 
 * Copyright (c) 2014 Continental AG and others.
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 
 * which accompanies this distribution, and is
 * available at http://www.eclipse.org/org/documents/epl-v10.php
 * 
 * Contributors: 
 *     Continental AG - Initial API and implementation
 * 
 * </copyright>
 */
package org.eclipse.eatop.metamodelgen.postprocessings.generator;

import java.lang.reflect.Method;

import org.eclipse.emf.codegen.jet.JETEmitter;

@SuppressWarnings("nls")
public class GeneratorAdapters {
	private static String templatePluginPrefix = "org.eclipse.eatop.metamodelgen";

	public static JETEmitter createJETEmitter(ClassLoader cl, String classname, String templatePathName) {
		JETEmitter jetEmitter = new JETEmitter(new String[] {}, templatePathName, cl);

		GeneratorAdapters.setStaticTemplateClass(jetEmitter, cl, classname.replace("org.eclipse", templatePluginPrefix), classname);

		return jetEmitter;
	}

	/**
	 * Sets the template class and method used by the passed in JET emitter. First tries to set a custom class overriden
	 * for Artop. If this doesn't work the method falls back to the default class.
	 * 
	 * @param jetEmitter
	 * @param cl
	 * @param classname1
	 * @param classname2
	 */
	public static void setStaticTemplateClass(JETEmitter jetEmitter, ClassLoader cl, String classname1, String classname2) {
		try {
			Class<?> templateClass = cl.loadClass(classname1);
			Method emitterMethod = templateClass.getDeclaredMethod("generate", new Class[] { Object.class });
			jetEmitter.setMethod(emitterMethod);
			return;
		} catch (Exception exception) {
			// ignore
		}
		try {
			Class<?> templateClass = cl.loadClass(classname2);
			Method emitterMethod = templateClass.getDeclaredMethod("generate", new Class[] { Object.class });
			jetEmitter.setMethod(emitterMethod);
		} catch (ClassNotFoundException cnf) {
			throw new IllegalStateException("A Tmeplate" + classname1 + " or " + classname2
					+ " could not be resolved. Please check if a template plugin is available.", cnf);
		} catch (Exception exception) {
			// ignore
		}
	}
}
