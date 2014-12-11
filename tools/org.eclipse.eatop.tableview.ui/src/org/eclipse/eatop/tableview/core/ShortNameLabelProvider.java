package org.eclipse.eatop.tableview.core;

import org.eclipse.eatop.eastadl21.Identifiable;
import org.eclipse.eatop.geastadl.ginfrastructure.gelements.GReferrable;
import org.eclipse.sphinx.emf.explorer.BasicExplorerLabelProvider;
import org.eclipse.swt.graphics.Image;

public class ShortNameLabelProvider extends BasicExplorerLabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof GReferrable) {
			GReferrable eobj = (GReferrable) element;
			return eobj.gGetShortName();
		} else if (element instanceof Identifiable) {
			Identifiable ref = (Identifiable) element;
			return ref.getShortName();
		} 
		return element.toString();
	}
	@Override
	public Image getImage(Object element) {
		Image img = super.getImage(element);
		
		if (img != null) {
			return img;
		}
		return null;
	}

}
