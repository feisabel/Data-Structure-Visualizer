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
	 * @param left left 
	 * @param right right
	 * @param key key
	 */
	public RBNode(RBNode parent, RBNode left, RBNode right, int key) {
		super(parent, left, right, key);
	}
	
	/**
	 * Access method.
	 * @return RBNode left kid
	 */
	public RBNode getLeft() {
		return (RBNode)super.getLeft();
	}
	
	/**
	 * Accesse method.
	 * @return RBNode right kid
	 */
	public RBNode getRight() {
		return (RBNode)super.getRight();
	}
   
	/**
	 * Access method.
	 * @return parent node
	 */
	public RBNode getParent() {
		return (RBNode)super.getParent();
	}

}
