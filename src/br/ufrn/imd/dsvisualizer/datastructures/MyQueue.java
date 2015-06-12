package br.ufrn.imd.dsvisualizer.datastructures;
/**
 * Class queue.
 * @author Ana Caroline
 *
 */
public class MyQueue extends AbstractList {
	/**
	 * Generated serial ID.
	 */
	private static final long serialVersionUID = -6295021590949956107L;
	
	/**
	 * Constructor with no parameters.
	 */
	public MyQueue(){
		support("enqueue", "num");
		support("dequeue");
	}
	
	/**
	 * Inserts new node in the last position.
	 * @param a new node
	 * @return true if inserted, false otherwise
	 */
	public boolean enqueue(int a){ //insere sempre no último
		if(!list.contains(a)){
			list.add(list.size(), a);
			return true;
		}
		return false;
	}
	
	/**
	 * Removes node given a key if it exists. 
	 * @return true if removed, false otherwise
	 */
	public boolean dequeue(){ //remove no primeiro
		if(list.size() > 0){
			list.remove(0);
			return true;
		}
		return false;
	}
}
