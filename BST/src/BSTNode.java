
public class BSTNode extends Node{
	
    private BSTNode leftKid;
    private BSTNode rightKid;
    private int key;
    
    /**
     * Constructor of the class BSTNode with no parameters.
     */
    public BSTNode() {
        leftKid = null;
        rightKid = null;
        key = 0;
    }
    
    /**
     * Constructor of the class BSTNode with three parameters.
     * @param parent Object to be assigned to the Node parent.
     * @param leftKid Object to be assigned to the Node leftKid.
     * @param rightKid Object to be assigned to the Node rightKid.
     * @param key int to be assigned to the key.
     */
    public BSTNode(BSTNode parent, BSTNode leftKid, BSTNode rightKid, int key) {
        super(parent);
        this.leftKid = leftKid;
        this.rightKid = rightKid;
        this.key = key;
    }
    
    public Node newNode(BSTNode parent){
    	return new BSTNode(parent, null, null, 0);
    }
    
    /**
     * Access method to leftKid.
     * @return The Node leftKid.
     */
    public BSTNode getLeftKid() {
        return leftKid;
    }
    
    /**
     * Access method to rightKid.
     * @return The Node rightKid.
     */
    public BSTNode getRightKid() {
        return rightKid;
    }
    
    /**
     * Modifier method of leftKid.
     * @param leftKid Object to be assigned to the Node leftKid.
     */
    public void setLeftKid(BSTNode leftKid) {
        this.leftKid = leftKid;
    }
    
    /**
     * Modifier method of rightKid.
     * @param rightKid Object to be assigned to the Node rightKid.
     */
    public void setRightKid(BSTNode rightKid) {
        this.rightKid = rightKid;
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
}
