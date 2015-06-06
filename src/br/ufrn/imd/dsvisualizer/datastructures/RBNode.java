package br.ufrn.imd.dsvisualizer.datastructures;


public class RBNode extends BSTNode {	
	
	public RBNode(RBNode parent, RBNode left, RBNode right, int key) {
		super(parent, left, right, key);
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
