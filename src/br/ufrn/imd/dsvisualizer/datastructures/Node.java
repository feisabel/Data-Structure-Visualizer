package br.ufrn.imd.dsvisualizer.datastructures;

import java.awt.Color;

/**
 * Represents a node.
 * @author Fernanda Isabel, João Pedro Holanda
 *
 */
public abstract class Node {

	private Node parent;
	protected Color color;
	
	/**
	 * Constructor with no parameters. 
	 */
	public Node(){
		parent = null;
		color = Color.RED;
	}
	
	/**
	 * Constructor with one parameter.
	 * @param parent node's parent
	 */
	public Node(Node parent){
		this.parent = parent;
		color = Color.RED;
	}
	
	/**
	 * Access method to the color.
	 * @return node's color
	 */
	public Color getColor(){
		return color;
	}
	
	/**
	 * Modifier method the color.
	 * @param color new color
	 */
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
    * @param parent object to be assigned to the Node parent
    */
   public void setParent(Node parent) {
       this.parent = parent;
   }
}
