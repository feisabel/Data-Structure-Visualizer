package br.ufrn.imd.dsvisualizer.datastructures;

/**
 * Abstract class tree.
 * @author Fernanda Isabel, João Pedro Holanda
 *
 */
public abstract class Tree extends DataStructure {
	
	/**
	 * Constructor with no parameters. 
	 */
	public Tree() {
		support("insert", 1);
		support("delete", 1);
		support("search", 1);
	}
	
	/**
	 * Method insert to be implemented.
	 * @param key new node's key
	 */
	abstract public void insert(int key);
	
	/**
	 * Method delete to be implemented.
	 * @param key node to be deleted
	 */
	abstract public Node delete(int key);
	
	/**
	 * Method search to be implemented.
	 * @param key key searched
	 */
	abstract public Node search(int key);
}
