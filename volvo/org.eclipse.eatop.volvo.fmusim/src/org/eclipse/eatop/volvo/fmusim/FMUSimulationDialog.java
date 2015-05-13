package org.eclipse.eatop.volvo.fmusim;


import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;



import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;

public class FMUSimulationDialog extends Dialog {

	protected ExtStyledText log;
	private Runnable logReadyCallback;
	
	public FMUSimulationDialog(Shell parentShell, Runnable logReadyCallback) {
		super(parentShell);
		this.logReadyCallback = logReadyCallback;
		}

	public ExtStyledText getLogWidget(){
		return log;
	}
	
	    @Override
	    protected void configureShell(Shell newShell) {
	        super.configureShell(newShell);
	        newShell.setText("FMU Simulation"); //$NON-NLS-1$
	    }

	    //run on open
	    @Override
	    protected Control createDialogArea(Composite parent) {
	        Composite composite = (Composite) super.createDialogArea(parent);
	        composite.setLayout(null);
	        
	        log = new ExtStyledText(composite, SWT.BORDER);
	        log.setDoubleClickEnabled(false);
	        log.setBounds(20, 24, 645, 425);
	        logReadyCallback.run();
	        
	        Label lblBugfixMargin = new Label(composite, SWT.NONE);
	        lblBugfixMargin.setBounds(664, 231, 20, 15);
	        
	        
	        return composite;
	    
	    
	     
	        
	    }
	    
	    
	    
}
	 