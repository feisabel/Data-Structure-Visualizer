
public class BSTNode extends Node{
	
    private BSTNode left;
    private BSTNode right;
    private int key;
    
    /**
     * Constructor of the class BSTNode with no parameters.
     */
    public BSTNode() {
        left = null;
        right = null;
        key = 0;
    }
    
    /**
     * Constructor of the class BSTNode with three parameters.
     * @param parent Object to be assigned to the Node parent.
     * @param left Object to be assigned to the Node left.
     * @param right Object to be assigned to the Node right.
     * @param key int to be assigned to the key.
     */
    public BSTNode(BSTNode parent, BSTNode left, BSTNode right, int key) {
        super(parent);
        this.left = left;
        this.right = right;
        this.key = key;
    }
    
    /**
     * Access method to left.
     * @return The Node left.
     */
    public BSTNode getLeft() {
        return left;
    }
    
    /**
     * Access method to right.
     * @return The Node right.
     */
    public BSTNode getRight() {
        return right;
    }
    
    /**
     * Modifier method of left.
     * @param left Object to be assigned to the Node left.
     */
    public void setLeft(BSTNode left) {
        this.left = left;
    }
    
    /**
     * Modifier method of right.
     * @param right Object to be assigned to the Node right.
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
    
    public BSTNode getParent() {
    	return (BSTNode)super.getParent();
    }
}
