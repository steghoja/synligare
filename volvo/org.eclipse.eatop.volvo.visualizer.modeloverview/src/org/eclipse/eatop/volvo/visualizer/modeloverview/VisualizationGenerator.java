package org.eclipse.eatop.volvo.visualizer.modeloverview;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.eclipse.eatop.eastadl21.EAElement;
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.eatop.eastadl21.SystemModel;
import org.eclipse.eatop.volvo.modelprocessor.ModelProcessor;
import org.eclipse.eatop.volvo.modelprocessor.TreeModelProcessor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.eatop.volvo.visualizer.common.Activator;
import org.eclipse.eatop.volvo.visualizer.common.AutoTaggingHandler;
import org.eclipse.eatop.volvo.visualizer.common.VisualTagsHandler;
import org.eclipse.eatop.volvo.visualizer.modeloverview.models.VisualModel;
import org.eclipse.eatop.volvo.visualizer.modeloverview.models.VisualRepresentation;
import org.eclipse.eatop.volvo.visualizer.modeloverview.models.VisualModel.VisualElement;
import org.eclipse.eatop.volvo.visualizer.modeloverview.views.VisualModelView;
import org.eclipse.eatop.volvo.visualizer.modeloverview.xml.XMLVisualRepresentationParser;

public class VisualizationGenerator {

	private ISelection selection;

	private EAXML root;
	private AutoTaggingHandler handler;

	public VisualModel createVisualModel(ISelection selection, VisualModelView view) {
		this.selection = selection;
		root = TreeModelProcessor.findRoot(selection);
		if (root == null) {
			System.out.println("root is null");
			return null;
		}
		
		EList<EAPackage> topLevelPackages = root.getTopLevelPackage();
		SystemModel sm = null;
		String smName = "";
		for (EAPackage p : topLevelPackages) {
			sm = ModelProcessor.findSystemModel(p);
			if (sm != null) {
				smName = sm.getShortName();
				break;
			}	
		}		

		// Create visual auto tags
		handler = new AutoTaggingHandler();

		doTagging();

		EAPackage tags = handler.getTagsPackage(root);
		if (tags == null) {
			// TODO throw exception or pop up or something
			//throw new Exception("No available tags");
			System.out.println("No available tags");
			return null;
		}

		Hashtable<EAElement, String[]> elements = handler.getTableOfElements(tags);

		IWorkbenchPage page= Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
		String fileName = TreeModelProcessor.findFileName(selection, page);

		// Create visual model
		VisualModel visualModel = new VisualModel();
		visualModel.setTitle(smName);

		// Set file
		visualModel.setModelFile(fileName);
		
		VisualRepresentation representation = XMLVisualRepresentationParser.
				readRepresentation(view.getRepresentationPath());
		visualModel.setVisualRepresentation(representation);

		// Set elements
		List<VisualElement> visualElements = new ArrayList<VisualElement>();
		for (EAElement e : elements.keySet()) {
			VisualElement element = visualModel.new VisualElement();
			// Set name
			String shortName = e.getShortName();
			if (shortName.equals("") || shortName == null) {
				shortName = e.eClass().getName();
			}
			element.setName(shortName);

			// Set path
			element.setPath(ModelProcessor.findPath(e));

			// Set category and level
			element.setCategory(elements.get(e)[0]);
			element.setLevel(elements.get(e)[1]);

			// Set type
			element.setType(e.eClass().getName());
			visualElements.add(element);
		}

		visualModel.setElements(visualElements);

		return visualModel;
	}
	
	private void doTagging() {
		String tagsPackageName = "";

		tagsPackageName = VisualTagsHandler.TAGS_PACKAGE_NAME; 
		IWorkbenchPage page= Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();  

		// Get the file name. 
		// This is used as a suffix in the tags package name.
		// Because of the way the elements are found problems may occur if the tags packages
		// has the same name (the same path). Elements from different east-adl models might 
		// be mixed up.
		String name = TreeModelProcessor.findFileName(selection, page);

		//Get the EditingDomain for the first element of the selected EObjects.
		EditingDomain ed = AdapterFactoryEditingDomain.getEditingDomainFor(root);
		if(ed == null){
			// TODO exception
			System.out.println("Cannot create EditingDomain for object " + root);
			return;
		}

		// TODO remove white space from name
		tagsPackageName += "_"+name;

		handler.createAutoTags(ed, root, tagsPackageName);
	}
}

