package org.eclipse.eatop.connectorcreator.dialog;

import java.util.HashMap;

import org.eclipse.eatop.connectorcreator.ports.PortPrototypeInterface.PortDirection;
import org.eclipse.eatop.connectorcreator.ports.PortRepresentationInterface;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

public class LeftListLabelProvider implements ILabelProvider {
	HashMap<String, Image> imageMap = new HashMap<String, Image>() {
		private static final long serialVersionUID = -1881667562059095910L;
		{
			put("lib/RxChannel.png", new Image(null, this.getClass().getClassLoader().getResourceAsStream("lib/RxChannel.png")));
			put("lib/TxChannel.png", new Image(null, this.getClass().getClassLoader().getResourceAsStream("lib/TxChannel.png")));
			put("lib/arrow_left_right.gif", new Image(null, this.getClass().getClassLoader().getResourceAsStream("lib/arrow_left_right.gif")));
			put("lib/pin.png", new Image(null, this.getClass().getClassLoader().getResourceAsStream("lib/pin.png")));
		}
	};
	@Override
	public void addListener(ILabelProviderListener listener) {
	}

	@Override
	public void dispose() {
		imageMap.values().stream().forEach((image) -> {
			image.dispose();
		});
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof PortRepresentationInterface) {
			PortRepresentationInterface portRepresentation = (PortRepresentationInterface) element;
			if (portRepresentation.getPortDirection().equals(PortDirection.IN)) {
				return imageMap.get("lib/RxChannel.png");
			} else if (portRepresentation.getPortDirection().equals(PortDirection.OUT)) {
				return imageMap.get("lib/TxChannel.png");
			} else if (portRepresentation.getPortDirection().equals(PortDirection.INOUT)) {
				return imageMap.get("lib/arrow_left_right.gif");
			} else if (portRepresentation.getPortDirection().equals(PortDirection.PIN)) {
				return imageMap.get("lib/pin.png");
			}
		}
		return null;
	}

	@Override
	public String getText(Object element) {
		if (element instanceof PortRepresentationInterface) {
			PortRepresentationInterface portRepresentation = (PortRepresentationInterface) element;
			return portRepresentation.getPortName();
		}
		return "No port representation";
	}

}
