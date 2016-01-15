package org.eclipse.eatop.app.semcon.allocationassistant.filters;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;


/**
 * This is a {@link org.eclipse.jface.dialogs.Dialog <em>dialog</em>} that provides 
 * search and filter functionality for a {@link org.eclipse.jface.viewers.StructuredViewer <em>StructuredViewer</em>}.
 * It shows two tabs: 
 * <li> Search tab - for searching by type and attribute value of the elements in the treeviewer. </li>
 * <li> Search and Filter tab - for searching by type and excluding by attribute value. </li>
 * @author Andreea Olaru
 */
public class FilterDialog extends Dialog {
	private String title; 
	private final StructuredViewer viewer;
	TreeMap<EClass, List<EStructuralFeature>> elementTypes;
	Set<EClass> classes;
	List<EClass> classList;
	List<EStructuralFeature> features;
	
	/**
	 * The constructor for the {@link org.eclipse.eatop.app.semcon.allocationassistant.filters.FilterDialog <em>FilterDialog</em>}
	 * @param shell -  for the {@link org.eclipse.eatop.app.semcon.allocationassistant.filters.FilterDialog <em>dialog</em>} to open on
	 * @param viewer - that contains the elements to be filtered. It can be a {@link org.eclipse.jface.viewers.TreeViewer <em>TreeViewer</em>}
	 * @param elementTypes - a {@link java.util.TreeMap <em>set</em>} of classes and their attributes that can be found on the <b>viewer</b>.
	 */
	public FilterDialog(Shell shell, StructuredViewer viewer, TreeMap<EClass, List<EStructuralFeature>> elementTypes) {
		super(shell);
		this.viewer = viewer;

		this.elementTypes = elementTypes;
		classes = elementTypes.keySet();
		features = null;
		setTitle("Search and filter");
		createContents(shell);
	}

	@Override
	protected Control createContents(Composite parent) {
		TabFolder tabFolder = new TabFolder(parent, SWT.BORDER);
		tabFolder.setVisible(true);
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

		//Create first tab with the search option
		TabItem searchTab = new TabItem(tabFolder, SWT.NONE);
		searchTab.setText("Search");

		Composite mainSearchContainer = new Composite(tabFolder, SWT.NULL);
		mainSearchContainer.setLayout(new GridLayout(1, false));
		mainSearchContainer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		searchTab.setControl(mainSearchContainer);
		int justSearchApproach = 1;
		populateTab(mainSearchContainer, "that have the attribute", justSearchApproach);

		//Create second tab with the search and filter option
		TabItem selectAndFilterTab = new TabItem(tabFolder, SWT.NONE);
		selectAndFilterTab.setText("SearchAndFilter");

		Composite searchAndFilterContainer = new Composite(tabFolder, SWT.NULL);
		searchAndFilterContainer.setLayout(new GridLayout(1, false));
		searchAndFilterContainer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		selectAndFilterTab.setControl(searchAndFilterContainer);
		int searchAndFilterApproach = 2;
		populateTab(searchAndFilterContainer, "but exclude those that have the attribute", searchAndFilterApproach);


		return parent;
	}

	private void populateTab(Composite parent, String textAboutAttributes, final int searchApproach) {

		//Create the label and combo that enable choosing an element type
		Composite compositeWithTypeElements = new Composite(parent, SWT.NONE);
		compositeWithTypeElements.setLayout(new GridLayout(2, false));
		Label typesLabel = new Label(compositeWithTypeElements, SWT.LEFT);
		typesLabel.setText("Search elements with type:");
		typesLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true));
		final Combo typesCombo = new Combo(compositeWithTypeElements, SWT.LEFT);

		for (EClass cls : classes){
			typesCombo.add(cls.getName());
		}
		classList = new ArrayList<EClass>(classes);


		//Create the label and combo that enable choosing an attribute
		Composite compositewithAttributeElements = new Composite(parent, SWT.NONE);
		compositewithAttributeElements.setLayout(new GridLayout(2, false));
		Label attributesLabel = new Label(compositewithAttributeElements, SWT.LEFT);
		attributesLabel.setText(textAboutAttributes);
		attributesLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true));
		final Combo attributesCombo = new Combo(compositewithAttributeElements, SWT.LEFT);
		
		//Populate the attribute combo with attributes according to the selected type
		typesCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				int selection = typesCombo.getSelectionIndex();
				attributesCombo.deselectAll();
				attributesCombo.removeAll();
				if (selection != -1) {
					EClass selectedClass = classList.get(selection);
					features= null;
					features = elementTypes.get(selectedClass);
					for (EStructuralFeature feature : features){
						attributesCombo.add(feature.getName());
					}
					classList = new ArrayList<EClass>(classes);
				} 
			}
		});
		
		//Create the label and combo textbox that enable writing a value for the attribute
		Composite compositeWithAttributeValueElements = new Composite(parent, SWT.NONE);
		compositeWithAttributeValueElements.setLayout(new GridLayout(2, false));
		Label label = new Label(compositeWithAttributeValueElements, SWT.LEFT);
		label.setText("with the value: ");
		final Text givenValue = new Text(compositeWithAttributeValueElements, SWT.LEFT|SWT.BORDER);	

		//Create the buttons for adding and removing the search/filter
		Composite buttonsComposite = new Composite(parent, SWT.NONE);
		buttonsComposite.setLayout(new GridLayout(2, false));
		
		Button buttonAddSearch = new Button(buttonsComposite, SWT.PUSH);
		if (searchApproach == 1) buttonAddSearch.setText("Search");
		else buttonAddSearch.setText("SearchAndFilter");

		Button buttonRemoveFilters = new Button(buttonsComposite, SWT.PUSH);
		buttonRemoveFilters.setText("Remove all SearchAndFilters");

		Listener filterListener = new Listener() {
			@Override
			public void handleEvent(Event event) {
				int typeSelection = typesCombo.getSelectionIndex();
				int attributeSelection = attributesCombo.getSelectionIndex();
				if (typeSelection != -1){
					EClass selectedClass = classList.get(typeSelection);
					EStructuralFeature attribute;
					if (attributeSelection !=-1)
						attribute = features.get(attributeSelection);
					else 
						attribute = null;
					String value = givenValue.getText();
					addFilter(searchApproach, selectedClass, attribute, value);
				}

			}
		};
		buttonAddSearch.addListener(SWT.Selection, filterListener);

		Listener removeFiltersListener = new Listener() {
			@Override
			public void handleEvent(Event event) {
				viewer.resetFilters();	
			}
		};
		buttonRemoveFilters.addListener(SWT.Selection,removeFiltersListener);

	}

	public void addFilter(int searchApproach, EClass selectedClass, EStructuralFeature attribute, String value){
		ViewerFilter[] vfilters = new ViewerFilter[1];
		if (searchApproach == 1){
			vfilters[0] = new TypeAndAttributeFilter(selectedClass, attribute, value);
		}
		else{
			vfilters[0] = new TypeAndExcludeByAttributeFilter(selectedClass, attribute, value);
		}
		viewer.setFilters(vfilters);
		viewer.refresh();

	}


	@Override
	protected Control getContents() {
		// TODO Auto-generated method stub
		return super.getContents();
	}

	/*
	 * (non-Javadoc) Method declared in Window.
	 */
	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		if (title != null) {
			shell.setText(title);
		}
	}
	/**
	 * Sets the title for this dialog.
	 * 
	 * @param title
	 *            the title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

}
