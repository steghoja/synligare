package org.eclipse.eatop.connectorcreator.dialog;

import java.util.List;

import org.eclipse.eatop.connectorcreator.ports.PortRepresentationInterface;
import org.eclipse.eatop.connectorcreator.swcomponents.SwComponentPrototypeInterface;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.AbstractElementListSelectionDialog;
import org.eclipse.ui.dialogs.FilteredList;

public class ConnectorDialog extends AbstractElementListSelectionDialog {
	
    private Text rightFilterText;
    private FilteredList rightList;
    
	private ILabelProvider rightListLabelProvider;
	private SwComponentPrototypeInterface swComponent;
	private Text infoText;
	
	public ConnectorDialog(Shell parent, SwComponentPrototypeInterface swComponent) {
		super(parent, new LeftListLabelProvider());
		setSize(60, 20);
		
		setAllowDuplicates(false);
		setMultipleSelection(false);

		this.rightListLabelProvider = new RightListLabelProvider();
		this.swComponent = swComponent;
		
		setTitle("Create or delete Connector");
		setHelpAvailable(false);
		
		open();
	}

	@Override
	public Control createDialogArea(Composite parent) {
		Composite upper = (Composite) super.createDialogArea(parent);
		Composite lower = (Composite) super.createDialogArea(parent);
		
		GridLayout layout = new GridLayout(2, true);
		upper.setLayout(layout);
				
		Composite left = (Composite) super.createDialogArea(upper);
		Composite right = (Composite) super.createDialogArea(upper);
		
		createLabel(left, swComponent.getName());
		createFilterText(left);
		createFilteredList(left);
		
		createInfoText(lower);
		createLabel(right, "Choose second port");
		createRightText(right);
		createRightList(right);
		
		rightList.addSelectionListener(new SelectionListener() {	
			@Override
			public void widgetSelected(SelectionEvent e) {
				getButton(IDialogConstants.OK_ID).setEnabled(true);

				if (getSelectedElements().length > 0 && rightList.getSelection().length > 0) {
					Object rightElement = rightList.getSelection()[0];
					Object leftElement = getSelectedElements()[0];

					if (rightElement instanceof PortRepresentationInterface && leftElement instanceof PortRepresentationInterface) {
						PortRepresentationInterface rightPort = (PortRepresentationInterface) rightElement;
						PortRepresentationInterface leftPort = (PortRepresentationInterface) leftElement;
						boolean connected = leftPort.isConnected(rightPort);
						if(connected) {
							getButton(IDialogConstants.OK_ID).setText("Delete");
							infoText.setText("You are about to delete the Connector between ["
									+ rightListLabelProvider.getText(leftPort)
									+ "] and ["
									+ rightListLabelProvider.getText(rightPort)
									+ "]");
						} else if (!connected) {
							getButton(IDialogConstants.OK_ID).setText("Create");
							infoText.setText("You are about to create a Connector between ["
									+ rightListLabelProvider.getText(leftPort)
									+ "] and ["
									+ rightListLabelProvider.getText(rightPort)
									+ "]");
						}
					}
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {}			
		});
		
		setListElements(swComponent.getInitialList().toArray());
		return parent;
	}
	
	private void createInfoText(Composite lower) {
		infoText = new Text(lower, SWT.BORDER | SWT.WRAP);
		GridData data = new GridData(GridData.FILL, GridData.FILL, true, true);
		data.widthHint = convertWidthInCharsToPixels(50);
		data.heightHint = convertHeightInCharsToPixels(2);
		infoText.setLayoutData(data);
		infoText.setFont(lower.getFont());
		infoText.setBackground(lower.getBackground());
		infoText.setEditable(false);
	}

	private Text createRightText(Composite parent) {
		Text text = new Text(parent, SWT.BORDER);

		GridData data = new GridData();
		data.grabExcessVerticalSpace = false;
		data.grabExcessHorizontalSpace = true;
		data.horizontalAlignment = GridData.FILL;
		data.verticalAlignment = GridData.BEGINNING;
		text.setLayoutData(data);
		text.setFont(parent.getFont());

		text.setText(""); //$NON-NLS-1$

		Listener listener = new Listener() {
			public void handleEvent(Event e) {
				rightList.setFilter(rightFilterText.getText());
			}
		};
		text.addListener(SWT.Modify, listener);

		text.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.ARROW_DOWN) {
					fFilteredList.setFocus();
				}
			}

			public void keyReleased(KeyEvent e) {
			}
		});

		rightFilterText = text;
		return text;
	}

	private FilteredList createRightList(Composite parent) {
		int flags = SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.SINGLE;

        FilteredList list = new FilteredList(parent, flags, rightListLabelProvider, true, false, true);

        GridData data = new GridData();
        data.widthHint = convertWidthInCharsToPixels(60);
        data.heightHint = convertHeightInCharsToPixels(18);
        data.grabExcessVerticalSpace = true;
        data.grabExcessHorizontalSpace = true;
        data.horizontalAlignment = GridData.FILL;
        data.verticalAlignment = GridData.FILL;
        list.setLayoutData(data);
        list.setFont(parent.getFont());

        list.addSelectionListener(new SelectionListener() {
            public void widgetDefaultSelected(SelectionEvent e) {
                
            }

            public void widgetSelected(SelectionEvent e) {

            }
        });

        rightList = list;
        return list;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		super.createButtonsForButtonBar(parent);
		getButton(IDialogConstants.OK_ID).setText("Create");
		getButton(IDialogConstants.OK_ID).setEnabled(false);
		getButton(IDialogConstants.CANCEL_ID).setText("Done");
	}
	
	private Label createLabel(Composite parent, String name) {
		Label label = new Label(parent, SWT.NONE);
		label.setText(name);
		label.setFont(parent.getFont());
		return label;
	}
	
	@Override
	protected void buttonPressed(int id) {
		if(IDialogConstants.OK_ID == id) {
			PortRepresentationInterface leftPort = (PortRepresentationInterface) getSelectedElements()[0];
			PortRepresentationInterface rightPort = (PortRepresentationInterface) rightList.getSelection()[0];
			
			boolean connected = rightPort.isConnected(leftPort);
			if (connected) {
				leftPort.deleteConnection(rightPort);
			} else if (!connected) {
				leftPort.addConnection(rightPort);
			}
			addToLowerListWidget(leftPort.getRightList(leftPort.getPortDirection()).toArray());
		} else {
			super.buttonPressed(id);
		}
	}

	private void addToLowerListWidget(Object[] elements) {
		rightList.setElements(elements);
	}
	
	@Override
	protected void handleSelectionChanged() {
		if (getSelectedElements().length > 0) {
			PortRepresentationInterface port = (PortRepresentationInterface) getSelectedElements()[0];
			List<PortRepresentationInterface> rightList = port.getRightList(port.getPortDirection());
			this.rightList.setElements(rightList.toArray());
			addToLowerListWidget(rightList.toArray());
		}
	}

	@Override
	public boolean close() {
		rightList.dispose();
		return super.close();
	}

	@Override
	protected void computeResult() {}
}
