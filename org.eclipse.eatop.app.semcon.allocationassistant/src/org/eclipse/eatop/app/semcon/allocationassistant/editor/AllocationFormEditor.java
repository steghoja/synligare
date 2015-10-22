package org.eclipse.eatop.app.semcon.allocationassistant.editor;


import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.eatop.app.semcon.allocationassistant.Activator;
import org.eclipse.eatop.app.semcon.allocationassistant.pages.AllocationContentsTreePage;
import org.eclipse.eatop.common.resource.EastADLURIFactory;
import org.eclipse.eatop.common.util.IdentifiableUtil;
import org.eclipse.eatop.geastadl.ginfrastructure.gelements.GEAXML;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sphinx.emf.editors.forms.BasicTransactionalFormEditor;
import org.eclipse.sphinx.emf.ui.util.EcoreUIUtil;
import org.eclipse.sphinx.platform.util.PlatformLogUtil;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableEditor;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
/**
 * This is similar to EastADLFormEditor from {@link org.eclipse.eatop.examples <em>EastADL plugin</em>} and uses some methods from it.
 * The difference is that it contains 2 {@link org.eclipse.eatop.app.semcon.allocationassistant.sections.AllocationContentsTreeSection <em>sections</em>}, therefore
 * 2 {@link org.eclipse.jface.viewers.TreeViewer <em>TreeViewers</em>}.
 *
 */
public class AllocationFormEditor extends BasicTransactionalFormEditor implements IPersistableEditor {
	// Show names and types => true ; Show names => false
	private static final String MODEL_ELEMENT_APPEARANCE_PROPERTY = "model_element_appearance_property"; //$NON-NLS-1$

			/*
			 * (non-Javadoc)
			 * @see org.eclipse.sphinx.emf.editors.forms.BasicTransactionalFormEditor#addPages()
			 */
	@Override
	protected void addPages() {
			try {
				Object input = getModelRoot();
				if (input instanceof EObject) {
					// Add overview page if editor input is an instance of Identifiable
					//if (IdentifiableUtil.isIdentifiable(input)) {
					//	addPage(new OverviewPage(this));
					//}
					addPage(new AllocationContentsTreePage(this));
				}
			} catch (PartInitException ex) {
				PlatformLogUtil.logAsError(Activator.getDefault(), ex);
			}
			getSite().getPage().removePartListener(partListener);
	}
	
	
		@Override
	public void menuAboutToShow(IMenuManager menuManager) {
		// TODO future work to support right click menu for editing and creating objects
		//For now: Do nothing to avoid Unhandled event loop exception
	}


		/*
		 * (non-Javadoc)
		 * @see org.eclipse.sphinx.emf.editors.forms.BasicTransactionalFormEditor#getEditorInputName()
		 */
		@Override
		protected String getEditorInputName() {
			// Has editor been opened on an EASTADL root object?
			@SuppressWarnings("deprecation")
			Object modelRoot = getModelRoot();
			if (modelRoot instanceof GEAXML) {
				// Use name defined by editor input descriptor (but not EASTADL root object label) as editor input name
				return getEditorInput().getName();
			}

			// Has object on which editor has been opened become unavailable?
			if (modelRoot == null) {
				// Does editor input descriptor yield a URI pointing at some EASTADL object?
				URI editorInputURI = EcoreUIUtil.getURIFromEditorInput(getEditorInput());
				if (editorInputURI != null) {
					// Try to use last segment of absolute qualified name of that EASTADL object as editor input name
					String fragment = editorInputURI.fragment();
					if (fragment != null) {
						return EastADLURIFactory.getTrailingAbsoluteQualifiedNameSegment(fragment);
					}
				}
			}

			return super.getEditorInputName();
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.sphinx.emf.editors.forms.BasicTransactionalFormEditor#getEditorInputImage()
		 */
		@Override
		protected Image getEditorInputImage() {
			// Has editor been opened on an EASTADL root object?
			@SuppressWarnings("deprecation")
			Object modelRoot = getModelRoot();
			if (modelRoot instanceof GEAXML) {
				// Use icon of underlying EASTADL XML file (but not that of EASTADL root object) as editor input image
				IFile file = EcoreUIUtil.getFileFromEditorInput(getEditorInput());
				if (file != null) {
					IContentType contentType = IDE.getContentType(file);
					ImageDescriptor imageDescriptor = PlatformUI.getWorkbench().getEditorRegistry().getImageDescriptor(file.getName(), contentType);
					return ExtendedImageRegistry.getInstance().getImage(imageDescriptor);
				}
			}

			return super.getEditorInputImage();
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.ui.part.EditorPart#getTitleToolTip()
		 */
		@Override
		public String getTitleToolTip() {
			// Has editor been opened on some EASTADL object?
			IEditorInput editorInput = getEditorInput();
			if (editorInput instanceof URIEditorInput) {
				// Make sure that tool tip yields workspace-relative (but not platform:/resource) URI to EASTADL object
				URI uri = EcoreUIUtil.getURIFromEditorInput(editorInput);
				if (uri.isPlatform()) {
					String path = uri.toPlatformString(true);
					return new Path(path).makeRelative().toString();
				}
			}

			return super.getTitleToolTip();
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.sphinx.emf.editors.forms.BasicTransactionalFormEditor#isSaveAsAllowed()
		 */
		@Override
		public boolean isSaveAsAllowed() {
			return false;
		}

		public boolean isShowTypeName() {
			return Activator.getDefault().getDialogSettings().getBoolean(MODEL_ELEMENT_APPEARANCE_PROPERTY);
		}

		public void setShowTypeName(boolean showTypeName) {
			Activator.getDefault().getDialogSettings().put(MODEL_ELEMENT_APPEARANCE_PROPERTY, showTypeName);
		}

	

}
