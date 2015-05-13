package command;

import java.util.Collection;
import java.util.List;


import org.eclipse.eatop.common.ui.util.ModelSearcher;
import org.eclipse.eatop.eastadl21.ErrorModelPrototype;
import org.eclipse.eatop.eastadl21.ErrorModelType;
import org.eclipse.eatop.eastadl21.FunctionPrototype;
//import org.eatop.eel.examples.explorer.internal.Activator;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.sphinx.emf.domain.factory.EditingDomainFactoryListenerRegistry;
import org.eclipse.sphinx.emf.domain.factory.ITransactionalEditingDomainFactoryListener;
import org.eclipse.sphinx.emf.metamodel.MetaModelDescriptorRegistry;
import org.eclipse.sphinx.emf.util.WorkspaceTransactionUtil;
//import org.eclipse.sphinx.emf.workspace.domain.WorkspaceEditingDomainManager;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
//import command.CheckConsistencyEMT;

public class CutSetDecorator implements ILightweightLabelDecorator{
//	CheckConsistencyEMT ccEMT = new CheckConsistencyEMT();

	private static ImageDescriptor cutsetOverlay = AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.eatop.modelassistant.hiphopsfta",
			"icon/cutset.png");
	
	
	@Override
	public void decorate(Object element, IDecoration decoration) {
		// TODO Auto-generated method stub
		if (elementIsCutSet(element)||elementIsCutSetTarget(element)) {
			decoration.addOverlay(cutsetOverlay, IDecoration.TOP_RIGHT);
		} else {
			decoration.addOverlay(null);
		}

	}
	
//	private boolean elementIsCutSetTarget(Object element) {
//		// TODO Auto-generated method stub
//		if(element instanceof FunctionPrototype){
//			FunctionPrototype funProto = (FunctionPrototype) element;
//			List<EObject> refs = ModelSearcher.findReferences(funProto);
//			for(EObject ref: refs){
//				if(ref instanceof ErrorModelPrototype){
//					ErrorModelPrototype errRef = (ErrorModelPrototype) ref;
//					if(errRef.getName().contains("cutset")){
//						return true;
//					}
//				}
//			}
//		}
//		
//		return false;
//	}
	
	private boolean elementIsCutSetTarget(Object element) {
	// TODO Auto-generated method stub
	if(element instanceof FunctionPrototype){
		FunctionPrototype funProto = (FunctionPrototype) element;
		if(funProto.getName().contains("cutset")){
			return true;
		}
	}
	
	return false;
}

	private boolean elementIsCutSet(Object element) {
		// TODO Auto-generated method stub
		if (element instanceof ErrorModelPrototype) {
			ErrorModelPrototype emProto = (ErrorModelPrototype) element;
			if(emProto.getName().contains("cutset")){
				return true;
			}
			
		}
		return false;
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}






}
