package br.ufrn.imd.dsvisualizer;

public class RBNode extends BSTNode {
	
	private Color color;
	
	public RBNode() {
		color = RED;
	}
	
	public RBNode(RBNode parent, RBNode left, RBNode right, int key, Color color) {
		super(parent, left, right, key);
		this.color = color;
	}
	
	public char getColor() {
		return color;
	}
	
	public void setColor(Color color) {
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
