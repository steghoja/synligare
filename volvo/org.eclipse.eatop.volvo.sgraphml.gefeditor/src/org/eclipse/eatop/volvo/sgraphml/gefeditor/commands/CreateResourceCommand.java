package org.eclipse.eatop.volvo.sgraphml.gefeditor.commands;

import org.eclipse.gef.commands.Command;
import org.graphdrawing.graphml.xmlns.GraphType;
import org.graphdrawing.graphml.xmlns.NodeType;

import eu.synligare.sgraphml.ResourceBlockType;
import eu.synligare.sgraphml.ResourceType;

public class CreateResourceCommand extends Command {
	
	 protected ResourceType resource; 
	 protected ResourceBlockType parentResource;
	 
	  @Override 
	  public void execute() {
		  parentResource.getResource().add(resource);
	  }

	  @Override 
	  public void undo() {
		   parentResource.getResource().remove(resource);
	  }

	  public void setResource(ResourceType resource) {
		this.resource = resource;
	}

	public void setParentResourceBlock(ResourceBlockType block) {
		this.parentResource = block;
	}
	  
}
