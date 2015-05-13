package org.eclipse.eatop.volvo.fmusim.visualization;




import java.util.ArrayList;
import java.util.Collections;

import org.eclipse.eatop.eastadl21.EAElement;
import org.eclipse.eatop.eastadl21.VVLog;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;



public class FMUSimResultDialog <T extends EAElement> extends Dialog {


	String title;
	ListViewer passedViewer;
	ListViewer failedViewer;
	java.util.List<T> passed; //VVLog or Requirement
	java.util.List<T> failed;

	T selectedElement;
	Button gotoButton;
	
		
	public FMUSimResultDialog(Shell parentShell, String title, java.util.List<T> passed, java.util.List<T> failed) {
		super(parentShell);
		this.title = title;
		this.passed = passed;
		this.failed = failed;
		
		
		
	}


	    @Override
	    protected void configureShell(Shell newShell) {
	        super.configureShell(newShell);
	        newShell.setText(title); 
	    }

	    //run on open
	    @Override
	    protected Control createDialogArea(Composite parent) {
	        Composite composite = (Composite) super.createDialogArea(parent);
	        composite.setLayout(null);
	        
	        passedViewer = new ListViewer(composite, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
	        passedViewer.addSelectionChangedListener(new ISelectionChangedListener() {
	        	public void selectionChanged(SelectionChangedEvent event) {
	        		verifiedSelectionChanged(event);
	        	}

				
	        });
	        //Use a ILabelProvider to get the shortName attribute displayed in the list 
	        passedViewer.setLabelProvider(new EAElementLabelProvider());
	        List verifiedList = passedViewer.getList();
	        verifiedList.setBounds(10, 43, 452, 103);
	        
	        failedViewer = new ListViewer(composite, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
	        failedViewer.addSelectionChangedListener(new ISelectionChangedListener() {
	        	public void selectionChanged(SelectionChangedEvent event) {
	        		failedSelectionChanged(event);
	        	}

				
	        });
	        failedViewer.setLabelProvider(new EAElementLabelProvider());
	        List failingList = failedViewer.getList();
	        failingList.setBounds(10, 199, 452, 108);
	        
	        Label lblNewLabel = new Label(composite, SWT.NONE);
	        lblNewLabel.setBounds(10, 22, 376, 15);
	        lblNewLabel.setText("Passed  ");
	        
	        Label lblVvlogsFalsifyingRequirement = new Label(composite, SWT.NONE);
	        lblVvlogsFalsifyingRequirement.setText("Failed");
	        lblVvlogsFalsifyingRequirement.setBounds(10, 178, 392, 15);
	        
	        
	        Label lblBugfixMargin = new Label(composite, SWT.NONE);
	        lblBugfixMargin.setBounds(460, 178, 15, 15);
	    
	        
	        SetListData();
	        
	        return composite;
	        
	    }
	    
	    public void SetListData(){
	    	if (passed != null)
	    		passedViewer.add(passed.toArray());

	    	if (failed != null)
	    		failedViewer.add(failed.toArray());	    	
	    
	   
	    
	    }
	    
	    @Override
	    protected void createButtonsForButtonBar(Composite parent) {
	    	  
	    	Button okButton = createButton(parent, IDialogConstants.OK_ID, 
	            "Ok", true); 	
	        gotoButton = createButton(parent, IDialogConstants.PROCEED_ID, 
	            "Go to", false);
	        gotoButton.setEnabled(false);
	     	gotoButton.addMouseListener(new MouseAdapter() {
	    		@Override
	    		public void mouseUp(MouseEvent e) 
	    		{
	    			goToClicked();    			
	    		}  		
	    	});
	    	

	        //No on click handler
	  }
	    
	    private void verifiedSelectionChanged(SelectionChangedEvent event) {
			
	 	   //get selection from verified list
	 	   IStructuredSelection selection = (IStructuredSelection)event.getSelection();

	 	   if (selection.isEmpty()){
	 		   //selection changed by failedSelectionClicked code above, do nothin'
	 	   }
	 	   else{
	 		   //Triggered by mouse click, i.e. a real change 
	 		   
	 		   //clear selection from verified list
			   ISelection empty = new ISelection() {
				   @Override
				   public boolean isEmpty() {
					   return true;
				   }
			   };
			   
	 		   failedViewer.setSelection(empty, true);
	 		   	 		   
	 		   //get selection from verified list
		   	   selectedElement = (T)selection.getFirstElement();

	 	
	 			gotoButton.setEnabled(true);
	 	   }
	    	
		}
	    
   private void failedSelectionChanged(SelectionChangedEvent event) {
			
	   //get selection from failed list
	   IStructuredSelection selection = (IStructuredSelection)event.getSelection();

	   if (selection.isEmpty()){
		   //selection changed by verifiedSelectionClicked code above, do nothin'
	   }
	   else{
		   //Triggered by mouse click, i.e. a real change 
		   
		   //clear selection from verified list
		   ISelection empty = new ISelection() {
			   @Override
			   public boolean isEmpty() {
				   return true;
			   }
		   };
		   
		  passedViewer.setSelection(empty, true);
		   		   
		   //get selection from failed list
	   	   selectedElement = (T)selection.getFirstElement();
	
			gotoButton.setEnabled(true);
	   }
	}
	    
	    protected void goToClicked(){
	    
	    	if (selectedElement != null)
	    	{
	    	IWorkbenchPartSite treeSite = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart().getSite();
	    	
    		TreeSelection selection = getElementSelection(selectedElement);
	   		treeSite.getSelectionProvider().setSelection(selection);
	    	}
	    }
	    
	    //calculates path from project down to specified VVLog node
	    private TreeSelection getElementSelection(T t) {
			
	    	//Build treepath backwards from VVLog up to root
	    	java.util.List<Object> objectList = new ArrayList<Object>();
			objectList.add(t);
			
			EObject parent = t.eContainer();
			objectList.add(parent);
			
			while(parent.eContainer() != null){
				parent = parent.eContainer();
				objectList.add(parent);
			}
			
			addHead(objectList);
			
			//And reverse path to get it root ---> vvLog
			Collections.reverse(objectList);
			
			TreePath treePath = new TreePath(objectList.toArray());
			TreeSelection selection = new TreeSelection(treePath);
					
			return selection;
		}

	    
	    //adds project & file nodes
		private void addHead(java.util.List<Object> objectList) {

			IWorkbenchPartSite treeSite = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart().getSite();
			TreeSelection reqSelection = (TreeSelection)treeSite.getSelectionProvider().getSelection();
			TreePath treePath = reqSelection.getPaths()[0];
			
			objectList.add(treePath.getSegment(1));
			objectList.add(treePath.getSegment(0));
			
		}
}
	
	 