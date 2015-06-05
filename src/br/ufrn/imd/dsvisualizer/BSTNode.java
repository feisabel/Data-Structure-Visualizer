package br.ufrn.imd.dsvisualizer;


/**
 * BSTNode implements a simple BSTNode type.
 * 
 * @author Ana Caroline, Fernanda, João Pedro e Leonardo. 
 * @version 27.04.2015
 */
public class BSTNode extends Node
{
    private BSTNode parent;
    private BSTNode left;
    private BSTNode right;
    private int key;
    private int x;
    /**
     * Constructor of the class BSTNode with no parameters.
     */
    public BSTNode() {
        parent = null;
        left = null;
        right = null;
        key = 0;
    }
    
    /**
     * Constructor of the class BSTNode with three parameters.
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
    
    public BSTNode(int a){
        left = null;
        right = null;
        key = a;
    	
    }
    
    public void setX(int a){
    	x = a;
    }
    
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
    
    public boolean isLeaf(){
    	if(this.getLeft() != null && this.getRight() != null){
    		return true;
    	}
    	else{
    		return false;
    	}
    }

    public int BSTNodeLevel(){
    	int i;
    	BSTNode a = this;
    	for(i = 0; a != null; a = a.getParent(), i++);	
    	return i;
    }
}