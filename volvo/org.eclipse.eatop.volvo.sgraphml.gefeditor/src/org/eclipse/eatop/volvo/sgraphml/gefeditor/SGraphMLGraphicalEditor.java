package org.eclipse.eatop.volvo.sgraphml.gefeditor;

import java.io.File;
import java.util.Arrays;
import java.util.EventObject;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.contextmenu.GEFEditorContextMenuProvider;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.contextmenu.GotoModelElementAction;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.contextmenu.SaveImageAction;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.controller.GraphMLTypeEditPart;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.controller.SGraphMLEditPartFactory;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd.EAObjectTransferDropTargetListener;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd.VisualAttributes;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.model.ModelIO;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.model.ModelProcessor;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.model.resources.ResourceManager;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.requests.UpdateLabelRectanglesRequest;
import org.eclipse.eatop.volvo.sgraphml.testcode.SgraphmlEditDomain;
import org.eclipse.eatop.volvo.sgraphml.testcode.ToggleGridVisibilityAction;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor.PropertyValueWrapper;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.tools.MarqueeDragTracker;
import org.eclipse.gef.tools.MarqueeSelectionTool;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.gef.ui.actions.ToggleGridAction;
import org.eclipse.gef.ui.actions.ToggleSnapToGeometryAction;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.gef.ui.properties.UndoablePropertySheetEntry;
import org.eclipse.gef.ui.properties.UndoablePropertySheetPage;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Handle;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.MouseWheelZoomHandler;
import org.eclipse.gef.Request;
import org.eclipse.gef.RootEditPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.eclipse.core.resources.IFile;
import org.graphdrawing.graphml.xmlns.GraphmlType;

import eu.synligare.sgraphml.PortNodeType;
import eu.synligare.sgraphml.SgraphmlPackage;
import eu.synligare.sgraphml.provider.SgraphmlItemProviderAdapterFactory;

public class SGraphMLGraphicalEditor extends GraphicalEditorWithFlyoutPalette {

	protected GraphmlType theModel;
	PropertySheetPage propertyPage;
	ResourceManager resourceManager;
	
	
	public SGraphMLGraphicalEditor() {
		DefaultEditDomain ed = new DefaultEditDomain(this);
		SgraphmlEditDomain.iNSTANCE.setEditDomain(ed);
		setEditDomain(ed);

		

	}

	@Override
	public Object getAdapter(Class type) {
		//Make our editor understand ZoomManager
		if (type == ZoomManager.class)
			return getGraphicalViewer().getProperty(ZoomManager.class.toString());

		return super.getAdapter(type);
	};

	/*** called first time user opens editor on specific sgraphml file, not every time ***/
	/* called before initializeGraphicalViewer */
	@Override 
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();

		
		//Override ScalableRootEditPart to change the Marquee behavior on the root level
			getGraphicalViewer().setRootEditPart(new ScalableRootEditPart(){
	            @Override
	            public DragTracker getDragTracker(Request req) {
	                MarqueeDragTracker tracker = (MarqueeDragTracker) super.getDragTracker(req);
	                tracker.setMarqueeBehavior(MarqueeSelectionTool.BEHAVIOR_NODES_CONTAINED_AND_RELATED_CONNECTIONS);
	                return tracker;
	            }
	        });

			getGraphicalViewer().setEditPartFactory(new SGraphMLEditPartFactory());

		getActionRegistry().registerAction(new ToggleGridAction(getGraphicalViewer())); 
		// getActionRegistry().registerAction(new ToggleGridVisibilityAction(getGraphicalViewer())); 

		getActionRegistry().registerAction(new ToggleSnapToGeometryAction(getGraphicalViewer()));   

		List zoomContributions = Arrays.asList(new String[] {
				ZoomManager.FIT_ALL, 
				ZoomManager.FIT_HEIGHT, 
				ZoomManager.FIT_WIDTH });

		ScalableRootEditPart rootEditPart = (ScalableRootEditPart)getGraphicalViewer().getRootEditPart();//;.getZoomManager().setZoomLevelContributions(zoomContributions);
		rootEditPart.getZoomManager().setZoomLevelContributions(zoomContributions);

		//Add Ctrl+Mouse wheel support for zoom 
		getGraphicalViewer().setProperty(MouseWheelHandler.KeyGenerator.getKey(SWT.MOD1), MouseWheelZoomHandler.SINGLETON);

  	    getGraphicalViewer().setContextMenu(new GEFEditorContextMenuProvider(getGraphicalViewer(), getActionRegistry()));
	} 


	
	@Override
	/*** called first time user opens editor on specific sgraphml file, not every time ***/
		
	protected void initializeGraphicalViewer() {
		super.initializeGraphicalViewer();
		getGraphicalViewer().setContents(theModel);

		updateLabelRectangles();

		getGraphicalViewer().addDropTargetListener(new EAObjectTransferDropTargetListener(getGraphicalViewer()));
		Utils.setGraphicalViewer(getGraphicalViewer());
		Utils.setEditorPart(this);
	}

	

	
	
	@Override 
	/*** this function is called the first time user opens the GEF editor on a specific file
	 *   not every time the user switch back to the file.
	 */
	
	

	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		super.init(site, input);

		if(input instanceof IFileEditorInput) {
			IFileEditorInput fileInput = (IFileEditorInput) input;
			IFile file = fileInput.getFile();
			{
				theModel = ModelIO.INSTANCE.ReadModel(file);
				ModelProcessor.INSTANCE.setTheModel(theModel);
				
				resourceManager = new ResourceManager();
				resourceManager.init();
				Utils.INSTANCE.setResourceManager(resourceManager);

				//Use IListener2 to detect user switching to another GEF Editor.
				//We need to update the singleton helper classes with the proper objects owned by this editor.
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService().addPartListener(new IPartListener2() {

					@Override
					public void partActivated(IWorkbenchPartReference partRef) {}

					@Override
					public void partBroughtToTop(IWorkbenchPartReference partRef) {
						if(partRef.getPart(false) == SGraphMLGraphicalEditor.this) { 
							reInit();
						}
					}

					@Override
					public void partClosed(IWorkbenchPartReference partRef) {}

					@Override
					public void partDeactivated(IWorkbenchPartReference partRef) {}

					@Override
					public void partOpened(IWorkbenchPartReference partRef) {}

					@Override
					public void partHidden(IWorkbenchPartReference partRef) {}
					
					@Override
					public void partVisible(IWorkbenchPartReference partRef) {}

					@Override
					public void partInputChanged(IWorkbenchPartReference partRef) {}
				});
			}
			Shell shell = getSite().getWorkbenchWindow().getShell();
			Utils.setShell(shell);
			
			VisualAttributes.INSTANCE.readFile();

			setPartName(file.getName());
		}
	}

	/***
	 * When changing the file that is edited, we need to prepare some global stuff.
	 */
	protected void reInit() {
		Utils.INSTANCE.setResourceManager(resourceManager);
		ModelProcessor.INSTANCE.setTheModel(theModel);
		Utils.INSTANCE.setGraphicalViewer(getGraphicalViewer());
	}

	
	
	
	protected void updateLabelRectangles() {
		RootEditPart root = getGraphicalViewer().getRootEditPart();
		GraphMLTypeEditPart graphMLEditPart = (GraphMLTypeEditPart)root.getChildren().get(0);
		graphMLEditPart.performRequest(new UpdateLabelRectanglesRequest());
	}

	@Override
	protected PaletteRoot getPaletteRoot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		//update the label rectangle attributes, since we don't do this during editing yet.
		updateLabelRectangles();

		ModelIO.INSTANCE.WriteModel();
		getCommandStack().markSaveLocation();   

	}



	
	/**
	 * Fire a {@link IEditorPart#PROP_DIRTY} property change 
	 * to get the save button enabled, and then 
	 * call super implementation.
	 */
	@Override 
	public void commandStackChanged(EventObject event) {
		firePropertyChange(PROP_DIRTY);
		super.commandStackChanged(event);
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	protected void createActions() {
		SelectionAction action = new GotoModelElementAction(this);
	    getActionRegistry().registerAction(action);
	    getSelectionActions().add(action.getId());

	    action  = new SaveImageAction(this);
	    getActionRegistry().registerAction(action);
	    
	    super.createActions();
	}
	
	

	//The code below for showing a propertySheetPage when a user has selected an object in the view
	//works, but it's useless. This is because the ItemProviders supply only the attributes 
	//as propertyDescriptors, and the model objects related to the selectable objects typically
	//have zero or few attributes. Instead, they have containment references to other types that
	//have properties. For example, a ShapeNode has zero attributes, but several containment references
	//to child features like FillType, LineStyle etc. 
	//This is evident in the generated editor, where selecting a ShapeNode gives zero properties to edit.
	//We actually need a more sophisticated propertySheet, that is divided into categories, where each
	//category is a child feature.
	/**
	 * This method implements adapting to {@link IPropertySheetPage}. All other requests are
	 * forwarded to the {@link GraphicalEditorWithFlyoutPalette#getAdapter(Class) parent}
	 * implementation.
	 */
	/*    @Override
	    public Object getAdapter(@SuppressWarnings("rawtypes") Class type) {
	    	//Make our editor understand ZoomManager
			if (type == ZoomManager.class)
			     return getGraphicalViewer().getProperty(ZoomManager.class.toString());


			else if(type.equals(IPropertySheetPage.class)) {
	            if(propertyPage == null) {
	                propertyPage = (UndoablePropertySheetPage) super
	                        .getAdapter(type);
	                // A new PropertySourceProvider was implemented to fetch the model
	                // from the edit part when required. The property source is provided
	                // by the generated EMF classes and wrapped by the AdapterFactoryContentProvider
	                // to yield standard eclipse interfaces.
	                IPropertySourceProvider sourceProvider = new IPropertySourceProvider() {
	                    IPropertySourceProvider modelPropertySourceProvider = new AdapterFactoryContentProvider(new SgraphmlItemProviderAdapterFactory());

	                    @Override
	                    public IPropertySource getPropertySource(Object object) {
	                        IPropertySource source = null;
	                        if(object instanceof EditPart) {
	                            source = modelPropertySourceProvider.getPropertySource(((EditPart) object).getModel());
	                        } else {
	                            source = modelPropertySourceProvider.getPropertySource(object);
	                        }

	                        if(source != null) {
	                            return new UnwrappingPropertySource(source);
	                        } else {
	                            return null;
	                        }
	                    }

	                };
	                UndoablePropertySheetEntry root = new UndoablePropertySheetEntry(getCommandStack());
	                root.setPropertySourceProvider(sourceProvider);
	                propertyPage.setRootEntry(root);
	            }
	            return propertyPage;
	        }
	        return super.getAdapter(type);
	    }
	 */	 
	/**
	 * A property source which unwraps values that are wrapped in an EMF
	 * {@link PropertyValueWrapper}
	 *
	 */
	/*	    public class UnwrappingPropertySource implements IPropertySource {
	        private IPropertySource source;

	        public UnwrappingPropertySource(final IPropertySource source) {
	            this.source = source;
	        }

	        @Override
	        public Object getEditableValue() {
	            Object value = source.getEditableValue();
	            if(value instanceof PropertyValueWrapper) {
	                PropertyValueWrapper wrapper = (PropertyValueWrapper) value;
	                return wrapper.getEditableValue(null);
	            } else {
	                return source.getEditableValue();
	            }
	        }

	        @Override
	        public IPropertyDescriptor[] getPropertyDescriptors() {
	            return source.getPropertyDescriptors();
	        }

	        @Override
	        public Object getPropertyValue(Object id) {
	            Object value = source.getPropertyValue(id);
	            if(value instanceof PropertyValueWrapper) {
	                PropertyValueWrapper wrapper = (PropertyValueWrapper) value;
	                return wrapper.getEditableValue(null);
	            } else {
	                return source.getPropertyValue(id);
	            }
	        }

	        @Override
	        public boolean isPropertySet(Object id) {
	            return source.isPropertySet(id);
	        }

	        @Override
	        public void resetPropertyValue(Object id) {
	            source.resetPropertyValue(id);
	        }

	        @Override
	        public void setPropertyValue(Object id, Object value) {
	            source.setPropertyValue(id, value);
	        }
	    }
	 */
}
