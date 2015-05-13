// Copyright (c) 2005 Alex Blewitt
// All rights reserved. This program and the accompanying materials
package command;
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// Alex Blewitt - Initial API and implementation
//

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
/**
 * Provides a browser as a view that can be called on to display URLs. Can be
 * loaded using its ID, which is available programmatically as {@link #ID}.
 * 
 * @see #gotoURL(String)
 */
public class BrowserView extends ViewPart {
	/**
	 * Returns the ID that this browser is known as,
	 * <code>org.rcpapps.base.ui.BrowserView</code>.
	 */
	public static final String ID = BrowserView.class.getName();
	/**
	 * Stores the browser's instance, created in
	 * {@link #createPartControl(Composite)}.
	 */
	private Browser browser = null;
	/**
	 * Create the browser component by initialising the browser in the frame.
	 */
	public void createPartControl(Composite frame) {
		browser = new Browser(frame, SWT.NONE);
	}
	/**
	 * Disposes the internal browser widget.
	 */
	public void dispose() {
		browser.dispose();
		browser = null;
		super.dispose();
	}
	/**
	 * Directs the browser to the given URL.
	 * 
	 * @param url
	 *            the URL to navigate to
	 */
	public void gotoURL(String url) {
		browser.setUrl(url);
	}
	/**
	 * Delegates focussing this view to the browser, so that it can handle
	 * mouse-clicks etc.
	 */
	public void setFocus() {
		browser.setFocus();
	}
}
