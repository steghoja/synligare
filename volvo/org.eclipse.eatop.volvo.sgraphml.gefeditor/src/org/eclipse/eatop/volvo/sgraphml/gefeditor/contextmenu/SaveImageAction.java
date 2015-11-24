package org.eclipse.eatop.volvo.sgraphml.gefeditor.contextmenu;

import java.util.List;

import org.eclipse.eatop.volvo.sgraphml.gefeditor.ImageSaveUtil;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.Utils;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

public class SaveImageAction extends SelectionAction {

	
	public static final String SAVE_IMAGE = "SaveImage";
		
    /**
     * Create a new instance of the class.
     * @param part
     */
    public SaveImageAction(IWorkbenchPart part) {
        super(part);
        setId(SAVE_IMAGE);
        setText("Save Image");
        
    }
	
	@Override
	protected boolean calculateEnabled() {
		return true;
	}

	  
    @Override
    public void run() {
        ImageSaveUtil.save(Utils.getEditorPart(), Utils.getGraphicalViewer());
        
        
        
    }	
}
