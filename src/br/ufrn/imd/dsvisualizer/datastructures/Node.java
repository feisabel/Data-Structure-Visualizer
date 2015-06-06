package br.ufrn.imd.dsvisualizer.datastructures;

import java.awt.Color;

public abstract class Node {

	private Node parent;
	protected Color color;
	
	public Node(){
		parent = null;
		color = Color.RED;
	}
	
	public Node(Node parent){
		this.parent = parent;
		color = Color.RED;
	}
	
	public Color getColor(){
		return color;
	}
	
	public void setColor(Color color){
		this.color = color;
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
