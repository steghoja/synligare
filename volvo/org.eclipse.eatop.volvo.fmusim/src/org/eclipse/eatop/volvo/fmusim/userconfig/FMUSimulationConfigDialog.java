package org.eclipse.eatop.volvo.fmusim.userconfig;


import org.eclipse.eatop.volvo.fmusim.FMUSimulationDialog;
import org.eclipse.eatop.volvo.fmusim.Utils;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Group;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.custom.StyledText;

/**
 * This dialog is an example of a detached window launched
 * from an action in the workbench.
 */
public class FMUSimulationConfigDialog extends Dialog {
 
	private Text valueVector;
	private Text timeVector;
	private Text sampleTime;
	private Text textStartTime;
	private Text textStopTime;
	private ListViewer inportListViewer;
	private Button btnApply;
	private Button btnRevert;
	
	
	private VVStimuliDataManager vvSDM;
	private VVStimuliXMLData vvSXMLData;
	private InportXMLData currentInport;
	private boolean bPortDirty = false;
	private Shell shell;
	
	/**
     * Creates a new FMUSectionDialog.
     */
    public FMUSimulationConfigDialog(Shell parentShell)
    {
        super(parentShell);
        shell = parentShell;
        vvSDM = VVStimuliDataManager.getInstance();
        vvSXMLData = vvSDM.getVVStimuliXMLData();
    }

    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText("FMU Simulation Configuration"); //$NON-NLS-1$
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);
        composite.setLayout(null);
        
        Group grpInputSignalConfiguration = new Group(composite, SWT.NONE);
        grpInputSignalConfiguration.setText("Inport Signal Configuration");
        grpInputSignalConfiguration.setBounds(23, 92, 576, 210);
        
        Label lblInput = new Label(grpInputSignalConfiguration, SWT.NONE);
        lblInput.setBounds(18, 27, 77, 15);
        lblInput.setText("Select inport");
        
        inportListViewer = new ListViewer(grpInputSignalConfiguration, SWT.BORDER | SWT.V_SCROLL| SWT.SINGLE);
        List inportList = inportListViewer.getList();
        inportList.setBounds(18, 52, 140, 131);
        inportListViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                
            	inportSelectionClicked(event);
                	
                }
        	}
            );
        
        
        
        Group grpEditSignal = new Group(grpInputSignalConfiguration, SWT.NONE);
        grpEditSignal.setBounds(171, 27, 395, 156);
        grpEditSignal.setText("Edit signal");
        
        Label lblValue = new Label(grpEditSignal, SWT.NONE);
        lblValue.setBounds(26, 26, 55, 15);
        lblValue.setText("Value");
        
        valueVector = new Text(grpEditSignal, SWT.BORDER);
        valueVector.addModifyListener(new ModifyListener() {
        	public void modifyText(ModifyEvent e) {
        		portDataModified();
       	
        	}
        });
        valueVector.setBounds(96, 23, 289, 21);
        
        Label lblTime = new Label(grpEditSignal, SWT.NONE);
        lblTime.setBounds(26, 59, 55, 15);
        lblTime.setText("Time");
        
        timeVector = new Text(grpEditSignal, SWT.BORDER);
        timeVector.addModifyListener(new ModifyListener() {
        	public void modifyText(ModifyEvent e) {
        		portDataModified();

        	}
        });
        timeVector.setBounds(96, 56, 289, 21);

        
        
        Label lblSampletime = new Label(grpEditSignal, SWT.NONE);
        lblSampletime.setBounds(26, 92, 66, 15);
        lblSampletime.setText("SampleTime");
        
        sampleTime = new Text(grpEditSignal, SWT.BORDER);
        sampleTime.addModifyListener(new ModifyListener() {
        	public void modifyText(ModifyEvent e) {
        		portDataModified();
        	}
        });
        sampleTime.setBounds(96, 89, 76, 21);
        
        
        btnApply = new Button(grpEditSignal, SWT.NONE);
        btnApply.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseUp(MouseEvent e) {
        	applyClicked();
        	
        	}
        });
        btnApply.setBounds(96, 121, 76, 25);
        btnApply.setText("Apply");
        btnApply.setEnabled(false);
        
        btnRevert = new Button(grpEditSignal, SWT.NONE);
        btnRevert.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseUp(MouseEvent e) {
        		revertClicked();
        	}
        });
        btnRevert.setBounds(310, 121, 75, 25);
        btnRevert.setText("Revert");
        btnRevert.setEnabled(false);

        
        Group grpSimulationTime = new Group(composite, SWT.NONE);
        grpSimulationTime.setText("Simulation time");
        grpSimulationTime.setBounds(23, 10, 576, 64);
        
        Label lblSimulationTimems = new Label(grpSimulationTime, SWT.NONE);
        lblSimulationTimems.setBounds(18, 30, 63, 15);
        lblSimulationTimems.setText("Start time");
        
        Label lblStopTime = new Label(grpSimulationTime, SWT.NONE);
        lblStopTime.setText("Stop time");
        lblStopTime.setBounds(249, 30, 63, 15);
        
        textStartTime = new Text(grpSimulationTime, SWT.BORDER);
        textStartTime.setBounds(77, 27, 76, 21);
        
        textStopTime = new Text(grpSimulationTime, SWT.BORDER);
        textStopTime.setBounds(315, 27, 76, 21);
        
        Label lblMarginBugfix = new Label(composite, SWT.NONE);
        lblMarginBugfix.setBounds(598, 239, 23, 15);
        lblMarginBugfix.setText(" ");

        SetWidgetValues();
        
                
        return composite;
    }
    
   
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
    	// Change parent layout data to fill the whole bar
    	//  parent.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    	  
    	Button simulatebutton = createButton(parent, IDialogConstants.OK_ID, 
            "Simulate", true); 	
     	simulatebutton.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseUp(MouseEvent e) 
    		{
    			 simulateClicked();    			
    		}  		
    	});
    	
        Button cancelbutton = createButton(parent, IDialogConstants.CANCEL_ID, 
            IDialogConstants.CANCEL_LABEL, false);
        //No on click handler
  }
    
    protected void portDataModified(){
    	btnApply.setEnabled(true);
		btnRevert.setEnabled(true);
		bPortDirty = true;
    }
    
    
    
    protected void simulateClicked()
    {
    	
    	if (bPortDirty)
		{
			if (Utils.showQuestion("Apply your changes to " + currentInport.getName() + " before starting simulation?"));
				if (!applyClicked()) return;
		}

    	
    	//save start & stop time
    	GetWidgetValues();
    	
    	//save current values at vvStimuli node
    	vvSDM.WriteXMLString(vvSXMLData);
    	
    }
    
    protected boolean applyClicked()
    {
    
    	//check sampleTime format in widget
    	Float fSampleTime;
    	try {
			fSampleTime = Float.parseFloat(sampleTime.getText());	
			} 
    	catch (NumberFormatException e) {
    		Utils.showErrorMessage("Sample time format is invalid. Use float format 10 or 10.0");
    		return false;
		}

    	InportXMLData tempPort = new InportXMLData("tempPort", fSampleTime, valueVector.getText(), timeVector.getText());
    	
    	//check other data in the widgets
    	if (!tempPort.IsValueVectorFormatValid()){
    		Utils.showErrorMessage("Value vector format is invalid. Use format \"[10 12 13.2]\""); 
    		return false;
    	}
    	
    	if (!tempPort.IsTimeVectorFormatValid()){
    		Utils.showErrorMessage("Time vector format is invalid. Use format \"[10 12 13.2]\""); 
    		return false;
    	}


    	if (tempPort.valueVectorLength() <= 0){
    		Utils.showErrorMessage("The value vector contains no data.");
    		return false;
    	}

    	if (tempPort.timeVectorLength() <= 0){
    		Utils.showErrorMessage("The time vector contains no data.");
    		return false;
    	}

    	if (tempPort.valueVectorLength() !=  tempPort.timeVectorLength()){
    		Utils.showErrorMessage("The value and time vector must have the same length.");
    		return false;
    	}

    	if (!(tempPort.IsTimeVectorMonotonic())){
    		Utils.showErrorMessage("The time vector must be monotonic.");
    		return false;
    	}
    	
    	
    	//Update port variable from widgets
    	currentInport.setValueVector(valueVector.getText());
    	currentInport.setTimeVector(timeVector.getText());
     	currentInport.setSampleTime(Float.parseFloat(sampleTime.getText()));
       	
     	
     	bPortDirty = false;
     	return true;
    }

    protected void revertClicked()
    {
    	//Update widget values from port
    	valueVector.setText(currentInport.getValueVector());
    	timeVector.setText(currentInport.getTimeVector());
    	sampleTime.setText(Float.toString(currentInport.getSampleTime()));
    	
    	bPortDirty = false;
    	btnApply.setEnabled(false);
		btnRevert.setEnabled(false);
    }

	protected void inportSelectionClicked(final SelectionChangedEvent event){
		IStructuredSelection selection = (IStructuredSelection)event.getSelection();
	
		InportXMLData selectedPort = (InportXMLData)selection.getFirstElement();	
		
		//has user changed port?

		if (!selectedPort.equals(currentInport))
		{
			
			//has user edited data in old port?
			if (bPortDirty)
			{
				if (Utils.showQuestion("Apply your changes to " + currentInport.getName() + " before changing inport to " + selectedPort.getName() + "?"))
					if (!applyClicked()) return;
			}
			
			//update widget values from inport
			currentInport = (InportXMLData)selection.getFirstElement();	
			
			valueVector.setText(currentInport.getValueVector());
			timeVector.setText(currentInport.getTimeVector());
			sampleTime.setText(Float.toString(currentInport.sampleTime));
		
			btnApply.setEnabled(false);
			btnRevert.setEnabled(false);
			bPortDirty = false;
		}
		
	}
    
    //Data exchange program variables --> widget values
    protected void SetWidgetValues()
    {
    	
    	textStartTime.setText(Float.toString(vvSXMLData.getStartTime()));
    	textStopTime.setText(Float.toString(vvSXMLData.getStopTime()));

    	for (InportXMLData p : vvSXMLData.getListOfInports())
    	{ 		
    		
    		inportListViewer.add(p);
    	}    	
    }
    
    protected void GetWidgetValues()
    {
    	vvSXMLData.setStartTime(Float.parseFloat(textStartTime.getText()));
       	vvSXMLData.setStopTime(Float.parseFloat(textStopTime.getText()));
 	 	
    
    }
    
}
