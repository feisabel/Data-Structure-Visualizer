package br.ufrn.imd.dsvisualizer.datastructures;

/**
 * Class deque.
 * @author Ana Caroline
 */
public class MyDeque extends AbstractList {
	/**
	 * Generated serial ID.
	 */
	private static final long serialVersionUID = -1888305749422593009L;

	/**
	 * Constructor with no parameters. 
	 */
	public MyDeque(){
		support("insertFirst", "key");
		support("insertLast", "key");
		support("removeFirst");
		support("removeLast");
	}
	
	/**
	 * Inserts new node in the first position.
	 * @param a new node 
	 * @return true if inserted, false otherwise
	 */
	public boolean insertFirst(int a){ //insere sempre no primeiro
		if(!list.contains(a)){
			list.add(0, new Integer(a));
			return true;
		}
		return false;
	}
	
	/**
	 * Inserts new node in the last position. 
	 * @param a new node
	 * @return true if inserted, false otherwise
	 */
	public boolean insertLast(int a){ //insere sempre no último
		if(!list.contains(a)){
			list.add(list.size(), new Integer(a));
			return true;
		}
		return false;
	}
	
	/**
	 * Removes the first node if exists in the structure.
	 * @return true if removed, false otherwise
	 */
	public boolean removeFirst(){ //remove no primeiro
		if(list.size() > 0){
			list.remove(0);
			return true;
		}
		return false;
	}
	
	/**
	 * Removes the last node if exists in the structure.
	 * @return true if removed, false otherwise
	 */
	public boolean removeLast(){ //remove no último
		if(list.size() > 0){
			list.remove(list.size()-1);
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the node's position.  
	 * @param a node to be searched
	 * @return position 
	 */
	public int search(int a){
		if(list.contains(a)){
			a = list.indexOf(a);
			return a;
		}
		return 0;
	}
}
