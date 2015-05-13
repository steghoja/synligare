package org.eclipse.eatop.volvo.sgraphml.gefeditor;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.ui.actions.RetargetAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;

public class GridRetargetAction extends RetargetAction {

		/**
		 * Constructs a new GridRetargetAction with the default ID, label and
		 * image.
		 */
		public GridRetargetAction() {	
			super(GEFActionConstants.TOGGLE_GRID_VISIBILITY, "Enable Grid", IAction.AS_CHECK_BOX);
									
			setToolTipText("Enable Grid");
		
			//Enabled image
		    URL urlEnabled = null;
		    try{
		    urlEnabled = new URL(Activator.getDefault().getBundle().getEntry("/"),
		                  "icons/grid-icon-enabled_16x16.png");
		    }
		    catch (MalformedURLException e){
		    	System.out.print(e);
		    }
		    ImageDescriptor imageEnabled = ImageDescriptor.createFromURL(urlEnabled);
			setImageDescriptor(imageEnabled);

			
			//Disabled image
		    URL urlDisabled = null;
		    try{
		    urlDisabled = new URL(Activator.getDefault().getBundle().getEntry("/"),
		                  "icons/grid-icon-disabled_16x16.png");
		    }
		    catch (MalformedURLException e){
		    	System.out.print(e);
		    }
		    ImageDescriptor imageDisabled = ImageDescriptor.createFromURL(urlDisabled);
			setDisabledImageDescriptor(imageDisabled);
		}
		
	}

