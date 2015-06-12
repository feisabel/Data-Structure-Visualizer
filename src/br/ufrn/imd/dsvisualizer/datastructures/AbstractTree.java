package br.ufrn.imd.dsvisualizer.datastructures;

/**
 * Abstract class tree.
 * @author Fernanda Isabel, João Pedro Holanda
 */
public abstract class AbstractTree extends DataStructure {
	
	/**
	 * Generated serial ID.
	 */
	private static final long serialVersionUID = -3867486410885252376L;

	/**
	 * Constructor with no parameters. 
	 */
	public AbstractTree() {
		support("insert", "key"); // supported operation
		support("delete", "key"); // supported operation
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
