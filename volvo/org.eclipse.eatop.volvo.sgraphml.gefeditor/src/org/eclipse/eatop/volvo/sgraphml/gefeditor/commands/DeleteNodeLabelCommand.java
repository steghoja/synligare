package org.eclipse.eatop.volvo.sgraphml.gefeditor.commands;

import org.eclipse.eatop.volvo.sgraphml.gefeditor.Utils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.graphdrawing.graphml.xmlns.NodeType;

import eu.synligare.sgraphml.BaseNodeType;
import eu.synligare.sgraphml.NodeLabelType;

public class DeleteNodeLabelCommand extends Command {
	  private BaseNodeType node;
	  private int index;
	  private NodeLabelType label;
	  private EObject eaLabelContainer; //EAST-ADL model object
	  private String oldDotPath;
	  public BaseNodeType getNode() {
		return node;
	}

	public void setNode(BaseNodeType node) {
		this.node = node;
	}

	public void setLabel(NodeLabelType label) {
		this.label = label;
	}

	@Override
	  public void execute() {

		EObject parent = Utils.INSTANCE.getLabelContainer(label);
		if (eaLabelContainer != null)
		{
			eaLabelContainer = parent; 
			NodeType graphmlContainerNode = (NodeType)label.eContainer().eContainer().eContainer();
			oldDotPath = graphmlContainerNode.getId();
			Utils.INSTANCE.getModelSynchronizer().removeEastAdlObjectAdapter(eaLabelContainer, label);
		}
		
		index = node.getNodeLabel().indexOf(label);
		node.getNodeLabel().remove(label);
	  }
	 
	  @Override
	  public void undo() {	    
			if (eaLabelContainer != null)
			{
				Utils.INSTANCE.getModelSynchronizer().addEastAdlObjectAdapter(eaLabelContainer, oldDotPath, label);
			}
		  
			node.getNodeLabel().add(index, label);
	  }
	 
}
