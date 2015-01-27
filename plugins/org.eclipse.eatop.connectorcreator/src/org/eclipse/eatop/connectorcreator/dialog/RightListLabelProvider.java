package org.eclipse.eatop.connectorcreator.dialog;

import org.eclipse.eatop.connectorcreator.ports.PortRepresentationInterface;
import org.eclipse.eatop.connectorcreator.ports.PortPrototypeInterface.PortDirection;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

public class RightListLabelProvider implements ILabelProvider {

	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof PortRepresentationInterface) {
			PortRepresentationInterface portRepresentation = (PortRepresentationInterface) element;
			if (portRepresentation.getIsConnected()) {
				return new Image(null, this.getClass().getClassLoader().getResourceAsStream("lib/ok.png"));
			} else if (portRepresentation.getPortDirection().equals(PortDirection.IN)) {
				return new Image(null, this.getClass().getClassLoader().getResourceAsStream("lib/RxChannel.png"));
			} else if (portRepresentation.getPortDirection().equals(PortDirection.OUT)) {
				return new Image(null, this.getClass().getClassLoader().getResourceAsStream("lib/TxChannel.png"));
			} else if (portRepresentation.getPortDirection().equals(PortDirection.INOUT)) {
				return new Image(null, this.getClass().getClassLoader().getResourceAsStream("lib/arrow_left_right.gif"));
			}
		}
		return null;
	}

	@Override
	public String getText(Object element) {
		if (element instanceof PortRepresentationInterface) {
			PortRepresentationInterface portRepresentation = (PortRepresentationInterface) element;
			return portRepresentation.getContainerName() + ": " + portRepresentation.getPortName();
		}
		return "No port representation";
	}

}
