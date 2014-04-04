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

import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenClassGeneratorAdapter;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenEnumGeneratorAdapter;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapter;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenPackageGeneratorAdapter;
import org.eclipse.emf.codegen.jet.JETEmitter;
import org.eclipse.emf.common.notify.Adapter;

public class ExtendedGenModelGeneratorAdapterFactory extends GenModelGeneratorAdapterFactory {

	/**
	 * 
	 */
	public static final GeneratorAdapterFactory.Descriptor DESCRIPTOR = new GeneratorAdapterFactory.Descriptor() {
		@Override
		public GeneratorAdapterFactory createAdapterFactory() {
			return new ExtendedGenModelGeneratorAdapterFactory();
		}
	};

	/**
	 * Returns a singleton {@link GenModelGeneratorAdapter}.
	 */
	@Override
	public Adapter createGenModelAdapter() {
		if (genModelGeneratorAdapter == null) {
			genModelGeneratorAdapter = new GenModelGeneratorAdapter(this) {
				// A sub-class is created here to influence the class loading that happens at
				// org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter.setStaticTemplateClass(JETEmitter,
				// String, String, Class<?>[]). This is crucial to enable overriding of the static template classes with
				// our customized classes.

				@Override
				protected JETEmitter createJETEmitter(JETEmitterDescriptor jetEmitterDescriptor) {
					return GeneratorAdapters.createJETEmitter(getClass().getClassLoader(), jetEmitterDescriptor.className,
							jetEmitterDescriptor.templatePathName);
				}
			};
		}
		return genModelGeneratorAdapter;
	}

	/**
	 * Returns a singleton {@link GenPackageGeneratorAdapter}.
	 */
	@Override
	public Adapter createGenPackageAdapter() {
		if (genPackageGeneratorAdapter == null) {
			genPackageGeneratorAdapter = new ExtendedGenPackageGeneratorAdapter(this) {
				// see comment above in createGenModelAdapter() at corresponding location.
				@Override
				protected JETEmitter createJETEmitter(JETEmitterDescriptor jetEmitterDescriptor) {
					return GeneratorAdapters.createJETEmitter(getClass().getClassLoader(), jetEmitterDescriptor.className,
							jetEmitterDescriptor.templatePathName);
				}
			};
		}
		return genPackageGeneratorAdapter;
	}

	/**
	 * Returns a singleton {@link GenClassGeneratorAdapter}.
	 */
	@Override
	public Adapter createGenClassAdapter() {
		if (genClassGeneratorAdapter == null) {
			genClassGeneratorAdapter = new GenClassGeneratorAdapter(this) {
				// see comment above in createGenModelAdapter() at corresponding location.
				@Override
				protected JETEmitter createJETEmitter(JETEmitterDescriptor jetEmitterDescriptor) {
					return GeneratorAdapters.createJETEmitter(getClass().getClassLoader(), jetEmitterDescriptor.className,
							jetEmitterDescriptor.templatePathName);
				}
			};
		}
		return genClassGeneratorAdapter;
	}

	/**
	 * Returns a singleton {@link GenEnumGeneratorAdapter}.
	 */
	@Override
	public Adapter createGenEnumAdapter() {
		if (genEnumGeneratorAdapter == null) {
			genEnumGeneratorAdapter = new GenEnumGeneratorAdapter(this) {
				// see comment above in createGenModelAdapter() at corresponding location.
				@Override
				protected JETEmitter createJETEmitter(JETEmitterDescriptor jetEmitterDescriptor) {
					return GeneratorAdapters.createJETEmitter(getClass().getClassLoader(), jetEmitterDescriptor.className,
							jetEmitterDescriptor.templatePathName);
				}
			};
		}
		return genEnumGeneratorAdapter;
	}
}
