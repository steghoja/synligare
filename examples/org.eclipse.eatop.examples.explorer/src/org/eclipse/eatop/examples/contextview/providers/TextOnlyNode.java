package org.eclipse.eatop.examples.contextview.providers;

public class TextOnlyNode {

	private String text;
	private NodeType type;
	private String imageName;

	public static enum NodeType {
		REFERENCES, REFERENCED_BY, INSTANCE_REFERENCED_BY, OTHER
	}

	public TextOnlyNode(String text, NodeType type) {
		setText(text);
		this.type = type;
		if (type == NodeType.REFERENCES) {
			imageName = "right";
		} else if (type == NodeType.REFERENCED_BY) {
			imageName = "left";
		} else if (type == NodeType.INSTANCE_REFERENCED_BY) {
			imageName = "instref";
		} else {
			imageName = "";
		}
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	@Override
	public String toString() {
		return text;
	}

	public NodeType getType() {
		return type;
	}

	public String getImageName() {
		return imageName;
	}

}
