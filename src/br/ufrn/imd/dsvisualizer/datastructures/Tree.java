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
		support("insert", "key");
		support("delete", "key");
		support("search", "key");
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
