package br.ufrn.imd.dsvisualizer.datastructures;


/**
 * BSTNode implements a simple BSTNode type.
 * 
 * @author Ana Caroline, Fernanda, João Pedro e Leonardo. 
 * @version 27.04.2015
 */
public class BSTNode extends Node
{
    /**
	 * Generated serial ID.
	 */
	private static final long serialVersionUID = -4505870553104085672L;
	
	private BSTNode left;
    private BSTNode right;
    private int key;
    private int x;
    /**
     * Constructor of the class BSTNode with no parameters.
     */
    public BSTNode() {
        left = null;
        right = null;
        key = 0;
    }
    
    /**
     * Constructor of the class BSTNode with four parameters.
     * @param parent Object to be assigned to the BSTNode parent.
     * @param left Object to be assigned to the BSTNode left.
     * @param right Object to be assigned to the BSTNode right.
     * @param key int to be assigned to the key.
     */
    public BSTNode(BSTNode parent, BSTNode left, BSTNode right, int key) {
        super(parent);
        this.left = left;
        this.right = right;
        this.key = key;
    }
    
    /**
     * Constructor with one parameter.
     * @param a node's key
     */
    public BSTNode(int a){
        left = null;
        right = null;
        key = a;
    }
    
    /**
     * Modifier method to set x.
     * @param a new position x
     */
    public void setX(int a){
    	x = a;
    }
    
    /**
     * Access method to get position x.
     * @return position x
     */
    public int getX(){
    	return x;
    }
    
    /**
     * Access method to parent.
     * @return The BSTNode parent.
     */
    public BSTNode getParent() {
    	return (BSTNode)super.getParent();
    }
    
    /**
     * Access method to left.
     * @return The BSTNode left.
     */
    public BSTNode getLeft() {
        return left;
    }
    
    /**
     * Access method to right.
     * @return The BSTNode right.
     */
    public BSTNode getRight() {
        return right;
    }
    
    /**
     * Modifier method of left.
     * @param left Object to be assigned to the BSTNode left.
     */
    public void setLeft(BSTNode left) {
        this.left = left;
    }
    
    /**
     * Modifier method of right.
     * @param right Object to be assigned to the BSTNode right.
     */
    public void setRight(BSTNode right) {
        this.right = right;
    }
    
    /**
     * Access method to Key.
     * @return The int key.
     */
    public int getKey(){
        return key;
    }
     
    /**
     * Modifier method of Key.
     * @param key int to be assigned to the key.
     */
    public void setKey(int key){
        this.key = key;
    }
    
    /**
     * Returns if the current node is a leaf or no.
     * @return true if is a leaf, false otherwise 
     */
    public boolean isLeaf(){
    	if(this.getLeft() != null && this.getRight() != null){
    		return true;
    	}
    	else{
    		return false;
    	}
    }

    /**
     * Returns the node's level.
     * @return level 
     */
    public int nodeLevel(Node root){
    	int i;
    	BSTNode a = this;
    	for(i = 1; a != root; a = a.getParent(), i++);	
    	return i;
    }

}
