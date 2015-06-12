package br.ufrn.imd.dsvisualizer.datastructures;

/**
 * Class list.
 * @author Ana Caroline
 *
 */
public class MyList extends AbstractList {
	/**
	 * Generated serial ID.
	 */
	private static final long serialVersionUID = 2554109786706088450L;
	
	/**
	 * Constructor with no parameters. 
	 */
	public MyList(){
		support("insert", "key", "position");
		support("remove", "key");
	}
	
	/**
	 * Inserts new node given a position.
	 * @param a new node 
	 * @param position the node position
	 * @return true if inserted, false otherwise
	 */
	public boolean insert(int a, int position){
		if(!list.contains(a) && position >= 0 && position <= list.size()){
			list.add(position, new Integer(a));
			return true;
		}
		return false;
	}
	
	/**
	 * Removes the node given the key if exists in the structure.
	 * @return true if removed, false otherwise
	 */
	public boolean remove(int a){
		if(list.contains(a)){
			list.remove(new Integer(a));
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
		return -1;
	}
}
