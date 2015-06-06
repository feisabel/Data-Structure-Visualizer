package br.ufrn.imd.dsvisualizer.datastructures;

public abstract class Node {

	private Node parent;
	private java.awt.Color c;
	
	public Node(){
		parent = null;
		c = java.awt.Color.red;
	}
	
	public java.awt.Color getColor(){
		return c;
	}
	
	public void setColor(java.awt.Color c){
		this.c = c;
	}
	
	public Node(Node parent){
		this.parent = parent;
	}
	
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
