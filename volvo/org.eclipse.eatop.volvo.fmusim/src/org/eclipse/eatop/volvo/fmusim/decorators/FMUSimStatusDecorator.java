/**
 * <copyright>
 * 
 * Copyright (c) 2013 itemis and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *     itemis - Initial API and implementation
 * 
 * </copyright>
 */
package org.eclipse.eatop.volvo.fmusim.decorators;

import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.FunctionFlowPort;
import org.eclipse.eatop.eastadl21.VVProcedure;
import org.eclipse.eatop.volvo.fmusim.visualization.Highlighter;
import org.eclipse.eatop.volvo.fmusim.visualization.Highlighter.FMUSimStatus;
import org.eclipse.emf.edit.provider.DecoratorAdapterFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class FMUSimStatusDecorator implements ILightweightLabelDecorator {

	
	Highlighter highlighter = Highlighter.getInstance();
	
	private static ImageDescriptor successOverlay = AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.eatop.fmusim", "icons/decorators/green.png");	
	private static ImageDescriptor failOverlay = AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.eatop.fmusim", "icons/decorators/red.png");	
	/*
	 * @see org.eclipse.jface.viewers.ILightweightLabelDecorator#decorate(java.lang.Object,
	 * org.eclipse.jface.viewers.IDecoration)
	 */
	
	@Override
	public void decorate(Object element, IDecoration decoration) {
		
	//	decoration.addOverlay(successOverlay);	
	//	return;
	
		
		Highlighter.FMUSimStatus status = highlighter.GetFMUSimStatus(element);
		
		
/*		if (element instanceof FunctionFlowPort)
		{
			FunctionFlowPort ffp = (FunctionFlowPort)element;
			if (ffp.getShortName().equals("FM1In"))
			{
				int bp = 12;
				
				
			}
		}
	*/	
			
		switch (status)
		
		{
		case SUCCESS:
			decoration.addOverlay(successOverlay);	
			break;
		case FAILURE:
			decoration.addOverlay(failOverlay);
			break;
		case NONE:
			decoration.addOverlay(null);
			break;	
		}		
	}
	
	
	/*
	private static boolean _success = false;
	public static void SetSuccess()
	{		
		_success = true;
	}

	public static boolean IsSuccess(Object element) {
		
		// return GetFMUSimStatus(element) == FMUSimStatus.SUCCESS;
		
		return _success;
	}
	
	private static boolean IsFailed(Object element)
	{
		// return GetFMUSimStatus(element) == FMUSimStatus.FAILURE;
		return !_success;
	}
*/
	/*
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
	 */
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	/*
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	public void addListener(ILabelProviderListener listener) {
		// Do nothing
	}

	/*
	 * @see
	 * org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	public void removeListener(ILabelProviderListener listener) {
		// Do nothing
	}

	/*
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
	 */
	public void dispose() {
		// Do nothing
	}
}
