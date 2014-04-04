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

package org.eclipse.eatop.eaadapter.ea2ecore;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.eatop.eaadapter.logging.Logger;
import org.eclipse.eatop.eaadapter.logging.LoggerImpl;
import org.eclipse.emf.ecore.EPackage;

import eaadapter.EARepository;

public class EA2Ecore {

	protected EARepository repository;
	protected EPackage model;
	private List<PostProcessingTemplate> postProcessings;
	private EcoreMapperTemplate ecoreMapper;
	private Logger logger;
	private boolean enableLoggingMapper = false;
	private boolean enableLoggingPostProcessing = false;

	public EA2Ecore(EARepository r) {
		repository = r;
		postProcessings = new LinkedList<PostProcessingTemplate>();
		logger = new LoggerImpl();
	}

	public void generateEcore() throws Exception {
		ecoreMapper = new EcoreMapperImpl(repository);
		ecoreMapper.setLogger(logger);

		if (enableLoggingMapper) {
			ecoreMapper.setLoggingEnabled();
		} else {
			ecoreMapper.setLoggingDisabled();
		}

		model = ecoreMapper.execute();

		for (PostProcessingTemplate p : postProcessings) {
			p.setModel(model);
			p.setRepository(repository);
			p.setLogger(logger);
			if (enableLoggingPostProcessing) {
				p.setLoggingEnabled();
			} else {
				p.setLoggingDisabled();
			}
			model = p.invoke();
		}
	}

	public void generateEcore(IProgressMonitor monitor) throws Exception {
		SubMonitor progress = SubMonitor.convert(monitor, 100);
		if (progress.isCanceled()) {
			throw new OperationCanceledException();
		}

		ecoreMapper = new EcoreMapperImpl(repository);
		ecoreMapper.setLogger(logger);

		if (enableLoggingMapper) {
			ecoreMapper.setLoggingEnabled();
		} else {
			ecoreMapper.setLoggingDisabled();
		}

		model = ecoreMapper.execute();

		progress.worked(10);
		SubMonitor ppProgress = progress.newChild(90).setWorkRemaining(postProcessings.size());
		for (PostProcessingTemplate p : postProcessings) {
			p.setModel(model);
			p.setRepository(repository);
			p.setLogger(logger);
			if (enableLoggingPostProcessing) {
				p.setLoggingEnabled();
			} else {
				p.setLoggingDisabled();
			}
			model = p.invoke();

			ppProgress.worked(1);
			if (ppProgress.isCanceled()) {
				throw new OperationCanceledException();
			}
		}
	}

	public EPackage getEcore() {
		return model;
	}

	public List<PostProcessingTemplate> getPostProcessings() {
		return postProcessings;
	}

	public void setPostProcessings(List<PostProcessingTemplate> postProcessings) {
		this.postProcessings = postProcessings;
	}

	public boolean isEnableLoggingMapper() {
		return enableLoggingMapper;
	}

	public void setEnableLoggingMapper(boolean enableLoggingMapper) {
		this.enableLoggingMapper = enableLoggingMapper;
	}

	public boolean isEnableLoggingPostProcessing() {
		return enableLoggingPostProcessing;
	}

	public void setEnableLoggingPostProcessing(boolean enableLoggingPostProcessing) {
		this.enableLoggingPostProcessing = enableLoggingPostProcessing;
	}

}

/*
 * Problems: 1) EASTADL - Infrastructure - Datatypes: some Elements (EnumerationValueType) have attributes which
 * reference EABoolean, EAString is it okay just to use Boolean and String ?! 4) Problem stereotypes EAElement hat
 * "stereotypes" als String. Ich bräuchte aber die Stereotypes als ID findStereotypeByString() ist ganz schlecht!
 */
