/**
 * <copyright>
 * 
 * Copyright (c) 2014 itemis and others.
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
package org.eclipse.eatop.examples.editor.internal.widgets;

import org.eclipse.sphinx.platform.ui.widgets.IWidgetFactory;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormText;

/**
 * @since 1.1.1
 */
public interface IFormWidgetFactory extends IWidgetFactory {

	// TODO Remove this interface because it is also provided in Sphinx

	FormText createFormText(Composite parent);

	FormText createFormText(Composite parent, int colspan, boolean grabHorizontal);
}
