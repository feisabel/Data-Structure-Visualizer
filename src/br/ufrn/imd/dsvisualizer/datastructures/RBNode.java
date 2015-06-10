package br.ufrn.imd.dsvisualizer.datastructures;


public class RBNode extends BSTNode {	
	
	/**
	 * Default constructor.
	 */
	public RBNode () {
	}
	
	/**
	 * Constructor.
	 * @param parent this node's parent
	 * @param left   this node's left child
	 * @param right  this node's right child
	 * @param key    this node's key
	 */
	public RBNode(RBNode parent, RBNode left, RBNode right, int key) {
		super(parent, left, right, key);
	}
	
	/**
	 * Get left child.
	 * @return  left child
	 */
	public RBNode getLeft() {
		return (RBNode)super.getLeft();
	}
	

	/**
	 * Get right child.
	 * @return  right child
	 */
	public RBNode getRight() {
		return (RBNode)super.getRight();
	}

	/**
	 * Get parent.
	 * @return  parent
	 */
   public RBNode getParent() {
	   return (RBNode)super.getParent();
   }
}