package br.ufrn.imd.dsvisualizer;

public class RedBlackNode extends BSTNode {
	
	private Color color;
	
	public RedBlackNode() {
		color = RED;
	}
	
	public RedBlackNode(RedBlackNode parent, RedBlackNode left, RedBlackNode right, int key, Color color) {
		super(parent, left, right, key);
		this.color = color;
	}
	
	public char getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public RedBlackNode getLeft() {
		   return (RedBlackNode)super.getLeft();
	   }
	   
	   public RedBlackNode getRight() {
		   return (RedBlackNode)super.getRight();
	   }
	   
	   public RedBlackNode getParent() {
		   return (RedBlackNode)super.getParent();
	   }
}
