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
package org.eclipse.eatop.metamodelgen.templates.internal.genmodel.geastadl;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.ecore.EOperation;

/**
 * An operation which is part of the generic GEASTADL metamodel API. GEASTADL operations are defined in an extra
 * GEASTADL ecore file.
 */
public class GOperation {

	public enum GOpType {

		GET, SET

	}

	private static final int OP_PREFIX_LENGTH = 4;
	private static final String GETTER_PREFIX = "gGet"; //$NON-NLS-1$
	private static final String SETTER_PREFIX = "gSet"; //$NON-NLS-1$

	private GenOperation fGenOperation;

	private static Map<String, GOpType> fPrefixTypeMap = new HashMap<String, GOpType>();

	static {
		fPrefixTypeMap.put(GETTER_PREFIX, GOpType.GET);
		fPrefixTypeMap.put(SETTER_PREFIX, GOpType.SET);
	}

	/**
	 * Constructs a GOperation.
	 * 
	 * @param genOperation
	 *            The GenOperation
	 */
	public GOperation(GenOperation genOperation) {
		fGenOperation = genOperation;
	}

	private EOperation getEcoreOperation() {
		return fGenOperation.getEcoreOperation();
	}

	public GOpType getOperationType() {
		return fPrefixTypeMap.get(getOpPrefix());
	}

	public String getOperationNameBase() {
		return getOperationName().substring(OP_PREFIX_LENGTH);
	}

	private String getOpPrefix() {
		String opName = getOperationName();
		return opName.substring(0, OP_PREFIX_LENGTH);
	}

	private String getOperationName() {
		return getEcoreOperation().getName();
	}

	public GenFeature getTargetFeature(GenClass concreteGenClass) {
		for (GenFeature feature : concreteGenClass.getGenFeatures()) {
			if (getOperationNameBaseLowerInitial().equals(feature.getName())) {
				return feature;
			}
		}
		return null;
	}

	private String getOperationNameBaseLowerInitial() {
		String nameBase = getOperationNameBase();
		String initial = nameBase.substring(0, 1);
		return initial.toLowerCase() + nameBase.substring(1);
	}

}
