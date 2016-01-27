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
package org.eclipse.eatop.metamodelgen.templates.internal.util;

import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.ecore.EAttribute;

public class GenFeatures {

	/**
	 * Indicates if given {@link GenFeature feature} has an opposite which has been made volatile for enabling an
	 * alternative, memory-optimized implementation for that feature to be generated. If so, the EMF code generator
	 * considers the given feature volatile too (see GenFeatureImpl#isVolatile() for details) and would normally
	 * generate a TODO comment + throw new UnsupportedOperationException() implementation for it. By the result of this
	 * method we can identify such cases and tell the EMF code generator to generate the usual non-volatile
	 * implementation instead.
	 * 
	 * @param genFeature
	 *            The {@link GenFeature feature} to be investigated.
	 * @return <code>true</code> if the given {@link GenFeature feature} has a memory-optimized volatile opposite and a
	 *         usual non-volatile implementation implementation should be generated for it; <code>false</code>
	 *         otherwise.
	 * @see #hasMemoryOptimizedImplementation(GenFeature)
	 */
	public static boolean hasMemoryOptimizedVolatileOpposite(GenFeature genFeature) {
		if (genFeature.isBidirectional()) {
			return hasMemoryOptimizedImplementation(genFeature.getReverse());
		}
		return false;
	}

	/**
	 * Indicates if given {@link GenFeature feature} is volatile and an alternative, memory-optimized implementation is
	 * generated for that feature.
	 * 
	 * @param genFeature
	 *            The {@link GenFeature feature} to be investigated.
	 * @return <code>true</code> if the given {@link GenFeature feature} is volatile and has a memory-optimized
	 *         implementation; <code>false</code> otherwise.
	 */
	public static boolean hasMemoryOptimizedImplementation(GenFeature genFeature) {
		if ("Identifiable".equals(genFeature.getGenClass().getName()) && "category".equals(genFeature.getName())) {//$NON-NLS-1$ //$NON-NLS-2$
			return true;
		}
		if ("Identifiable".equals(genFeature.getGenClass().getName()) && "uuid".equals(genFeature.getName())) {//$NON-NLS-1$ //$NON-NLS-2$
			return true;
		}
		if ("Identifiable".equals(genFeature.getGenClass().getName()) && "adminData".equals(genFeature.getName())) {//$NON-NLS-1$ //$NON-NLS-2$
			return true;
		}
		if ("Identifiable".equals(genFeature.getGenClass().getName()) && "longName".equals(genFeature.getName())) {//$NON-NLS-1$ //$NON-NLS-2$
			return true;
		}
		if ("Identifiable".equals(genFeature.getGenClass().getName()) && "desc".equals(genFeature.getName())) {//$NON-NLS-1$ //$NON-NLS-2$
			return true;
		}
		return false;
	}

	public static boolean haveCustomDatatypeImpl(GenFeature feature) {
		if (feature.getEcoreFeature() instanceof EAttribute) {
			EAttribute attr = (EAttribute) feature.getEcoreFeature();
			if (attr.getEType() != null
					&& ("PositiveInteger".equals(attr.getEType().getName()) || "UnlimitedInteger".equals(attr.getEType().getName()) || "Integer".equals(attr.getEType().getName()))) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ 
				return true;
			}
		}
		return false;
	}

	public static String getCustomDatatypeClass(GenFeature feature) {
		if (feature.getEcoreFeature() instanceof EAttribute) {
			EAttribute attr = (EAttribute) feature.getEcoreFeature();
			if (attr.getEType() != null) {
				if ("PositiveInteger".equals(attr.getEType().getName())) { //$NON-NLS-1$
					return "org.eclipse.eatop.common.datatypes.PositiveIntegerDatatype"; //$NON-NLS-1$
				} else if ("UnlimitedInteger".equals(attr.getEType().getName())) { //$NON-NLS-1$
					return "org.eclipse.eatop.common.datatypes.UnlimitedIntegerDatatype"; //$NON-NLS-1$
				} else if ("Integer".equals(attr.getEType().getName())) { //$NON-NLS-1$
					return "org.eclipse.eatop.common.datatypes.IntegerDatatype"; //$NON-NLS-1$
				}
			}
		}
		return null;
	}

	public static String getCustomListDatatypeClass(GenFeature feature) {
		if (feature.getEcoreFeature() instanceof EAttribute) {
			EAttribute attr = (EAttribute) feature.getEcoreFeature();
			if (attr.getEType() != null) {
				if ("PositiveInteger".equals(attr.getEType().getName())) { //$NON-NLS-1$
					return "org.eclipse.eatop.common.datatypes.PositiveIntegerDatatype.ToLongDelegateEList"; //$NON-NLS-1$
				} else if ("UnlimitedInteger".equals(attr.getEType().getName())) { //$NON-NLS-1$
					return "org.eclipse.eatop.common.datatypes.UnlimitedIntegerDatatype.ToBigIntegerDelegateEList"; //$NON-NLS-1$
				} else if ("Integer".equals(attr.getEType().getName())) { //$NON-NLS-1$
					return "org.eclipse.eatop.common.datatypes.IntegerDatatype.ToIntDelegateEList"; //$NON-NLS-1$
				}
			}
		}
		return null;
	}
}
