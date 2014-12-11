package org.eclipse.eatop.examples.contextview.providers;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.eatop.common.ui.util.ModelSearcher;
import org.eclipse.eatop.examples.common.ui.providers.TypeNameLabelDecorator;
import org.eclipse.eatop.examples.explorer.AppearanceExampleExplorerLabelProvider;
import org.eclipse.eatop.examples.explorer.internal.Activator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;

public class ContextViewLabelProvider extends AppearanceExampleExplorerLabelProvider {

	private boolean showPaths;
	private boolean showTypes;

	private TypeNameLabelDecorator typeNameLabelDecorator;

	private Image arrowLeft;
	private Image arrowRight;
	private Image instRef;

	public ContextViewLabelProvider() {
		typeNameLabelDecorator = new TypeNameLabelDecorator();
	}

	@Override
	public void init(ICommonContentExtensionSite aConfig) {
		URL url = FileLocator.find(Platform.getBundle(Activator.PLUGIN_ID), new Path("icons/full/obj16/arrow_left.gif"), null); //$NON-NLS-1$
		if (url != null) {
			arrowLeft = new Image(Display.getDefault(), url.getFile());
		}
		url = FileLocator.find(Platform.getBundle(Activator.PLUGIN_ID), new Path("icons/full/obj16/arrow_right.gif"), null); //$NON-NLS-1$
		if (url != null) {
			arrowRight = new Image(Display.getDefault(), url.getFile());
		}
		url = FileLocator.find(Platform.getBundle(Activator.PLUGIN_ID), new Path("icons/full/obj16/instref.gif"), null); //$NON-NLS-1$
		if (url != null) {
			instRef = new Image(Display.getDefault(), url.getFile());
		}

	}

	@Override
	public String getText(Object element) {
		String str = super.getText(element);
		if (element instanceof TextOnlyNode) {
			str = element.toString();
		}
		if (showTypes) {
			str = typeNameLabelDecorator.decorateText(str, element);
		}
		if (element instanceof EObject && showPaths) {
			str += " [" + ModelSearcher.getPathTo((EObject) element) + "]"; //$NON-NLS-1$ //$NON-NLS-2$
		}
		return str;
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof TextOnlyNode) {
			String imageName = ((TextOnlyNode) element).getImageName();
			Image img = null;
			if (imageName.equals("right")) {
				img = arrowRight;
			} else if (imageName.equals("left")) {
				img = arrowLeft;
			} else if (imageName.equals("instref")) {
				img = instRef;
			}
			if (img != null) {
				return img;
			}
		}
		return super.getImage(element);
	}

	public void setShowPaths(boolean show) {
		showPaths = show;
	}

	public void setShowTypes(boolean show) {
		showTypes = show;
	}

	@Override
	public void dispose() {
		if (arrowLeft != null) {
			arrowLeft.dispose();
		}
		if (arrowRight != null) {
			arrowRight.dispose();
		}
		if (instRef != null) {
			instRef.dispose();
		}
	}
}
