package br.ufrn.imd.dsvisualizer.datastructures;

import java.awt.Color;

public class RBNode extends BSTNode {	
	public RBNode() {
		color = Color.RED;
	}
	
	public RBNode(RBNode parent, RBNode left, RBNode right, int key, Color color) {
		super(parent, left, right, key);
		this.color = color;
	}
	
	public RBNode getLeft() {
		return (RBNode)super.getLeft();
	}
	   
	public RBNode getRight() {
		return (RBNode)super.getRight();
	}
	   
	public RBNode getParent() {
		return (RBNode)super.getParent();
	}
}
