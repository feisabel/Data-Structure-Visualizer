package br.ufrn.imd.dsvisualizer.datastructures;

public class RBNode extends BSTNode {
	
	private MyColor color;
	
	public RBNode() {
		color = MyColor.RED;
	}
	
	public RBNode(RBNode parent, RBNode left, RBNode right, int key, MyColor color) {
		super(parent, left, right, key);
		this.color = color;
	}
	
	public MyColor getMyColor() {
		return color;
	}
	
	public void setMyColor(MyColor color) {
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

	   public enum MyColor {
	   	RED, BLACK
	   }
}
