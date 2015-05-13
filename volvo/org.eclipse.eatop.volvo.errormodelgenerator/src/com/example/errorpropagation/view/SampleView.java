package com.example.errorpropagation.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

public class SampleView extends ViewPart {

	public SampleView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		Label label = new Label(parent, SWT.LEFT);
		label.setText("Here you'll see the corresponding error model");
	}
	
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
