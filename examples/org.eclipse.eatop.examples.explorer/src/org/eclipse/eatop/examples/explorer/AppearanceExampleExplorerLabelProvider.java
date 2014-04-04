/**
 * <copyright>
 * 
 * Copyright (c) 2014 Arccore and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *     Arccore - Initial API and implementation
 * 
 * </copyright>
 */
package org.eclipse.eatop.examples.explorer;

import java.net.URL;

import org.eclipse.eatop.examples.common.ui.providers.TypeNameLabelDecorator;
import org.eclipse.eatop.examples.explorer.internal.Activator;
import org.eclipse.eatop.examples.explorer.internal.messages.Messages;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sphinx.emf.explorer.BasicExplorerLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;

/**
 * Extends the BasicExplorerLabelProvider to add the interface name of the element to display after the element's name.
 * This functionality can optionally be enabled via the view menu.
 */
public class AppearanceExampleExplorerLabelProvider extends BasicExplorerLabelProvider implements IFontProvider, IColorProvider {
	private TypeNameLabelDecorator typeNameLabelDecorator;

	@Override
	public void init(ICommonContentExtensionSite aConfig) {
		super.init(aConfig);
		typeNameLabelDecorator = new TypeNameLabelDecorator();
	}

	@Override
	public String getText(Object element) {
		if (element instanceof ChildWrapper) {
			return getText(((ChildWrapper) element).getObject());
		}
		if (element instanceof IStructuredSelection) {
			IStructuredSelection selection = (IStructuredSelection) element;
			element = selection.getFirstElement();
		}

		String text = super.getText(element);
		if (isShowTypeName()) {
			return typeNameLabelDecorator.decorateText(text, element);
		} else {
			return text;
		}
	}

	private boolean isShowTypeName() {
		return Activator.getPlugin().getDialogSettings().getBoolean(AppearanceExampleActionProvider.MODEL_ELEMENT_APPEARANCE_PROPERTY);
	}

	@Override
	public Font getFont(Object element) {
		if (element instanceof ChildWrapper) {
			return new Font(Display.getDefault(), new FontData("Segoe UI", 9, SWT.ITALIC)); //$NON-NLS-1$
		}
		return new Font(Display.getDefault(), new FontData("Segoe UI", 9, SWT.NONE)); //$NON-NLS-1$
	}

	@Override
	public Image getImage(Object element) {
		Image img = super.getImage(element);
		if (element instanceof ChildWrapper) {
			img = getImage(((ChildWrapper) element).getObject());
		}
		if (img != null) {
			return img;
		}
		try {
			URL url = new URL(Messages.Path_EACountImage);
			return new Image(Display.getDefault(), url.openConnection().getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Color getForeground(Object element) {
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		return null;
	}
}
