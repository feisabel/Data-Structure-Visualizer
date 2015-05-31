
public abstract class Node {

	private Node parent;
	
	public Node(){
		parent = null;
	}
	
	public Node(Node parent){
		this.parent = parent;
	}
	
	abstract public Node newNode(Node parent);

	
    /**
     * Access method to parent.
     * @return The Node parent.
     */
    public Node getParent() {
        return parent;
    }
    
    /**
    * Modifier method of parent.
    * @param parent Object to be assigned to the Node parent.
    */
   public void setParent(Node parent) {
       this.parent = parent;
   }
}
