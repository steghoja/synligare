package org.eclipse.eatop.app.semcon.allocationassistant.sections;


import org.eclipse.eatop.app.semcon.allocationassistant.editor.AllocationFormEditor;
import org.eclipse.eatop.app.semcon.allocationassistant.filters.FilterAction;
import org.eclipse.eatop.app.semcon.allocationassistant.pages.AllocationContentsTreePage;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.sphinx.emf.editors.forms.sections.GenericContentsTreeSection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.SectionPart;

public class AllocationContentsTreeSection extends GenericContentsTreeSection {

	public AllocationContentsTreeSection(AllocationContentsTreePage formPage,
			Object sectionInput) {
		super(formPage, sectionInput);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.sphinx.emf.editors.forms.sections.GenericContentsTreeSection#createSectionClientContent(org.eclipse
	 * .ui.forms.IManagedForm, org.eclipse.ui.forms.SectionPart, org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void createSectionClientContent(final IManagedForm managedForm, final SectionPart sectionPart, Composite sectionClient) {
		super.createSectionClientContent(managedForm, sectionPart, sectionClient);
		ColumnViewerToolTipSupport.enableFor((ColumnViewer) viewer);
	}
	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.sphinx.emf.editors.forms.sections.AbstractFormSection#fillSectionToolBarActions(org.eclipse.jface
	 * .action.ToolBarManager)
	 */
	@Override
	protected void fillSectionToolBarActions(ToolBarManager toolBarManager) {
		super.fillSectionToolBarActions(toolBarManager);
		toolBarManager.add(new FilterAction(viewer));
		toolBarManager.add(new AppearanceAction(viewer, (AllocationFormEditor) formPage.getEditor()));
	}

}
