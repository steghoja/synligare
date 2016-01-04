package org.eclipse.eatop.volvo.sgraphml.gefeditor;

import java.awt.List;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;

import org.eclipse.eatop.eastadl21.Referrable;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.commands.TouchNodeLabelCommand;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.commands.UpdateLabelTextCommand;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.commands.UpdateNodeIDReferenceCommand;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.commands.UpdateNodeLabelTypeCommand;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd.EAXMLprocessor;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.model.ModelProcessor;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.gef.commands.CompoundCommand;
import org.graphdrawing.graphml.xmlns.GraphType;
import org.graphdrawing.graphml.xmlns.NodeType;
import org.graphdrawing.graphml.xmlns.XmlnsPackage;
import org.omg.PortableInterceptor.INACTIVE;

import eu.synligare.sgraphml.BaseNodeType;
import eu.synligare.sgraphml.GroupNodeType;
import eu.synligare.sgraphml.NodeLabelType;
import eu.synligare.sgraphml.SgraphmlPackage;
import eu.synligare.sgraphml.ShapeNodeType;

//Synchronizes the East-ADL Model and the SgraphML Model

//Listens to changes in EastAdl model and updates SgraphmlObject with Commands

public class EastAdlSgraphmlSynchronizer {


	public void CreateAdapters(){
		for (@SuppressWarnings("rawtypes")
		Iterator iter =  
				EcoreUtil.getAllContents(Collections.singleton(ModelProcessor.INSTANCE.getRootGraph()));
				iter.hasNext(); ) {
			EObject eObject = (EObject)iter.next();

			if (eObject instanceof NodeType){

				//All GraphML nodes have an attribute ID, that reference the East-ADL model object
						 
				 	  if (eObject instanceof NodeType){
					  	NodeType node = (NodeType)eObject;
					  	String eaDotPath = node.getId();
					  	EObject eaEObject = EAXMLprocessor.getEObjectbyDotPath(eaDotPath);
					  	
					  	if (eaEObject != null){
					  		addEastAdlObjectAdapter(eaEObject, eaDotPath, node);
					  	}

			}

			if (eObject instanceof NodeLabelType){
								
					NodeLabelType nodeLabel = (NodeLabelType)eObject;
					EObject eaObject = Utils.INSTANCE.getLabelContainer(nodeLabel);
					String eaDotPath = ((NodeType)nodeLabel.eContainer().eContainer().eContainer()).getId();
					
					if (eaObject != null){
						addEastAdlObjectAdapter(eaObject, eaDotPath, nodeLabel);
					}
			}
			

		}
	}
	}

	class EastAdlSgraphmlAdapter implements Adapter{

		String targetDotPath;
		Notifier target;
		EObject sGraphmlObject;
		
		public String getTargetDotPath() {
			return targetDotPath;
		}

		public void setTargetDotPath(String targetDotPath) {
			this.targetDotPath = targetDotPath;
		}

		
		
		public EObject getsGraphmlObject() {
			return sGraphmlObject;
		}

		public void setsGraphmlObject(EObject sGraphmlObject) {
			this.sGraphmlObject = sGraphmlObject;
		}

		@Override
		public void notifyChanged(Notification notification) {
			if (notification.getEventType() == Notification.SET) 
			{
				if (sGraphmlObject instanceof NodeLabelType){
					//Send a command to the model and execute using GEF

					TouchNodeLabelCommand updateCommand = new TouchNodeLabelCommand();
					updateCommand.setNodeLabel((NodeLabelType)sGraphmlObject);

					Utils.INSTANCE.executeGEFCommand(updateCommand);				
					
				}
				
				//graphML NodeType
				else if (sGraphmlObject instanceof NodeType){
					NodeType graphMLNode = (NodeType)sGraphmlObject;
					EAttribute feature  = (EAttribute)notification.getFeature();
						if (feature.getName().equals("shortName")) {
							//UpdateNodeIDReferenceCommand UpdateId = new UpdateNodeIDReferenceCommand();
							//UpdateId.setNode(graphMLNode);
							
							String oldDotPath = graphMLNode.getId();					
							int lastDotindx = oldDotPath.lastIndexOf('.');
							String newDotPath = oldDotPath.substring(0, lastDotindx) + "." + ((Referrable)target).getShortName();
							//UpdateId.setDotPath(newDotPath);				
							//TODO: Do this for all editors...
						
							CompoundCommand cc = ModelProcessor.INSTANCE.getRenameIDCommand(oldDotPath, newDotPath);
							
							//Add one more command to change the label itself
							UpdateLabelTextCommand command = new UpdateLabelTextCommand();	
							BaseNodeType baseNode = ModelProcessor.INSTANCE.getSgraphmlNode(graphMLNode);
							
							command.setNode(baseNode);
							command.setText(((Referrable)target).getShortName());
							cc.add(command);
							
							
							if (cc != null){
								Utils.INSTANCE.executeGEFCommand(cc);
							}
							
						}
				}
				
			}
		}
	

		@Override
		public Notifier getTarget() {
			return target;
		}

		@Override
		public void setTarget(Notifier newTarget) {
			target = newTarget;
		}

		@Override
		public boolean isAdapterForType(Object type) {

			return type.equals(EObject.class);
		}
	
	}
	
	/***
	 * 
	 * @param eaLabelContainer - the East-ADL object that owns the label that nodeLabel refer to
	 * @param nodeLabel
	 */
	public void addEastAdlObjectAdapter(EObject eaLabelContainer, String eaDotPath, EObject sgraphmlObj){

		//Create adapter
		EastAdlSgraphmlAdapter adapter = new EastAdlSgraphmlAdapter();
		adapter.setsGraphmlObject(sgraphmlObj);
		adapter.setTarget(eaLabelContainer);
		adapter.setTargetDotPath(eaDotPath);
		
		//store
		//		adapterMap.put(nodeLabel, adapter);
		eaLabelContainer.eAdapters().add(adapter);
	}

	public void removeEastAdlObjectAdapter(EObject eaLabel, EObject sgraphmlObj){
		
		//Find the adapter to remove
		Adapter toRemove = null;
		ListIterator<Adapter> litr = eaLabel.eAdapters().listIterator();
		while (litr.hasNext()){
			EastAdlSgraphmlAdapter adapter = (EastAdlSgraphmlAdapter)litr.next();
			if (adapter.getsGraphmlObject().equals(sgraphmlObj)){
				toRemove = adapter;
				break;
			}
		}

		if (toRemove != null){
			eaLabel.eAdapters().remove(toRemove);
		}

	}



}
