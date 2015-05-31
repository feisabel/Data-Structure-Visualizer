
/**
 * Node implements a simple node type.
 * 
 * @author Ana Caroline, Fernanda, João Pedro e Leonardo. 
 * @version 27.04.2015
 */
public class Node
{
    private Node parent;
    private Node leftKid;
    private Node rightKid;
    private int key;
    private int x;
    /**
     * Constructor of the class Node with no parameters.
     */
    public Node() {
        parent = null;
        leftKid = null;
        rightKid = null;
        key = 0;
    }
    
    /**
     * Constructor of the class Node with three parameters.
     * @param parent Object to be assigned to the Node parent.
     * @param leftKid Object to be assigned to the Node leftKid.
     * @param rightKid Object to be assigned to the Node rightKid.
     * @param key int to be assigned to the key.
     */
    public Node(Node parent, Node leftKid, Node rightKid, int key) {
        this.parent = parent;
        this.leftKid = leftKid;
        this.rightKid = rightKid;
        this.key = key;
    }
    
    public Node(int a){
        parent = null;
        leftKid = null;
        rightKid = null;
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
     * @return The Node parent.
     */
    public Node getParent() {
        return parent;
    }
    
    /**
     * Access method to leftKid.
     * @return The Node leftKid.
     */
    public Node getLeftKid() {
        return leftKid;
    }
    
    /**
     * Access method to rightKid.
     * @return The Node rightKid.
     */
    public Node getRightKid() {
        return rightKid;
    }
       
     /**
     * Modifier method of parent.
     * @param parent Object to be assigned to the Node parent.
     */
    public void setParent(Node parent) {
        this.parent = parent;
    }
    
    /**
     * Modifier method of leftKid.
     * @param leftKid Object to be assigned to the Node leftKid.
     */
    public void setLeftKid(Node leftKid) {
        this.leftKid = leftKid;
    }
    
    /**
     * Modifier method of rightKid.
     * @param rightKid Object to be assigned to the Node rightKid.
     */
    public void setRightKid(Node rightKid) {
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
    
    public boolean isLeaf(){
    	if(this.getLeftKid() != null && this.getRightKid() != null){
    		return true;
    	}
    	else{
    		return false;
    	}
    }

    public int nodeLevel(){
    	int i = 0;
    	Node a = this;
    	if(a != null){
    		for(i = 1; a != null; a = a.getParent(), i++);	
    	}
    	return i;
    }
}
