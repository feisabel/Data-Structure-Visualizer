package br.ufrn.imd.dsvisualizer.datastructures;

/**
 * RBNode class.
 * @author Fernanda Isabel
 *
 */
public class RBNode extends BSTNode {	

	/**
	 * Constructor with no parameters.
	 */
	public RBNode () {
	}
	
	/**
	 * Constructor with parameters.
	 * @param parent parent node
	 * @param left   left child node
	 * @param right  right child node
	 * @param key    key
	 */
	public RBNode(RBNode parent, RBNode left, RBNode right, int key) {
		super(parent, left, right, key);
	}
	
	/**
	 * Access method.
	 * @return  left child
	 */
	public RBNode getLeft() {
		return (RBNode)super.getLeft();
	}
	
	/**
	 * Access method.
	 * @return  right child
	 */
	public RBNode getRight() {
		return (RBNode)super.getRight();
	}
   
	/**
	 * Access method.
	 * @return  parent node
	 */
	public RBNode getParent() {
		return (RBNode)super.getParent();
	}
}