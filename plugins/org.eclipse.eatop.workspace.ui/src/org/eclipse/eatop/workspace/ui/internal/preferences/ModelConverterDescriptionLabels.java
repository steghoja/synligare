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
package org.eclipse.eatop.workspace.ui.internal.preferences;

import org.eclipse.eatop.workspace.ui.internal.messages.Messages;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.sphinx.emf.resource.IModelConverter;
import org.eclipse.sphinx.emf.resource.IModelConverterDescription;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * A widget for displaying the description of an {@link IModelConverter}.
 */
public class ModelConverterDescriptionLabels extends Composite {

	private static class Section {
		Label icon;
		Label title;
		Label content;
	}

	private static final String WARNING_TITLE_TEXT = Messages.Warning_TitleText;
	private static final String DESC_TITLE_TEXT = Messages.Description_TitleText;

	private static final IModelConverterDescription NULL_DESCRIPTION = new IModelConverterDescription() {
		@Override
		public boolean isEmpty() {
			return false;
		}

		@Override
		public boolean hasWarning() {
			return false;
		}

		@Override
		public boolean hasBehaviorDetails() {
			return false;
		}

		@Override
		public String getWarning() {
			return null;
		}

		@Override
		public String getBehaviorDetails() {
			return null;
		}
	};

	private Section fWarning;
	private Section fDescription;

	/**
	 * Creates a new ModelConverterDescriptionLabels instance
	 * 
	 * @param parent
	 *            a widget which will be the parent of the new instance (cannot be null)
	 */
	public ModelConverterDescriptionLabels(Composite parent) {
		super(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout(2, false);
		setLayout(gridLayout);

		fWarning = createNewSection(Dialog.DLG_IMG_MESSAGE_WARNING, WARNING_TITLE_TEXT);
		fDescription = createNewSection(Dialog.DLG_IMG_MESSAGE_INFO, DESC_TITLE_TEXT);

		setConverterDescription(null);
	}

	private Section createNewSection(String iconSymbolicName, String titleText) {
		Section section = new Section();
		section.icon = createIcon(iconSymbolicName);
		section.title = createTitle(titleText);
		section.content = createContent();
		return section;
	}

	private Label createIcon(String imageKey) {
		Label icon = new Label(this, SWT.SHADOW_NONE);
		icon.setImage(JFaceResources.getImage(imageKey));
		return icon;
	}

	private Label createTitle(String titleText) {
		Label title = new Label(this, SWT.SHADOW_NONE);
		title.setText(titleText);
		FontData[] fontData = title.getFont().getFontData();
		fontData[0].setStyle(SWT.BOLD);
		title.setFont(new Font(getDisplay(), fontData[0]));
		return title;
	}

	private Label createContent() {
		Label dummy = new Label(this, SWT.NONE); // Fill first column to skip
		// it.
		setSizeHints(dummy, 0, 0);
		final Label content = new Label(this, SWT.READ_ONLY | SWT.WRAP);
		GridData gridData = new GridData(SWT.FILL, SWT.TOP, true, false);
		content.setLayoutData(gridData);

		getParent().addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				Point size = content.computeSize(getSize().x, SWT.DEFAULT, true);
				setSizeHints(content, size.x, size.y);

				resize();
			}
		});

		return content;
	}

	public void setConverterDescription(IModelConverterDescription converterDescription) {
		if (converterDescription == null) {
			converterDescription = NULL_DESCRIPTION;
		}

		update(fWarning, converterDescription.getWarning());
		update(fDescription, converterDescription.getBehaviorDetails());

		GridLayout gridLayout = (GridLayout) getLayout();
		if (converterDescription.hasWarning() == false || converterDescription.hasBehaviorDetails() == false) {
			gridLayout.verticalSpacing = 0;
		} else {
			gridLayout.verticalSpacing = 5;
		}

		resize();
	}

	private void update(Section section, String content) {
		if (content != null) {
			section.content.setText(content);
			setSizeHints(section.icon, SWT.DEFAULT, SWT.DEFAULT);
			setSizeHints(section.title, SWT.DEFAULT, SWT.DEFAULT);
			Point size = section.content.computeSize(getSize().x, SWT.DEFAULT, true);
			setSizeHints(section.content, size.x, size.y);
		} else {
			section.content.setText(""); //$NON-NLS-1$
			setSizeHints(section.icon, 0, 0);
			setSizeHints(section.title, 0, 0);
			setSizeHints(section.content, 0, 0);
		}
	}

	private void setSizeHints(Label label, int width, int height) {
		GridData gridData = (GridData) label.getLayoutData();
		if (gridData == null) {
			gridData = new GridData();
		}
		gridData.widthHint = width;
		gridData.heightHint = height;
		label.setLayoutData(gridData);
	}

	private void resize() {
		// explicitly set our size since we are the contents of a ScrolledComposite which expects this
		setSize(computeSize(getParent().getClientArea().width, SWT.DEFAULT));
	}
}
