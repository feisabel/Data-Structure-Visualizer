package br.ufrn.imd.dsvisualizer.datastructures;

/**
 * Class stack.
 * @author Ana Caroline
 */
public class MyStack extends AbstractList { /*Pilha*/
	private static final long serialVersionUID = -2034904035673369825L;
	
	/**
	 * Constructor with no parameters.
	 */
	public MyStack(){
		support("push", "num");
		support("pop");
	}
	
	/**
	 * Adds new node in the top.
	 * @param a new node
	 * @return true if inserted, false otherwise
	 */
	public boolean push(int a){
		if(!list.contains(a)){
			list.add(list.size(), new Integer(a));
			return true;
		}
		return false;
	}
	
	/**
	 * Removes the node in the top if exists.
	 * @return true if removed, false otherwise
	 */
	public boolean pop(){
		if(list.size() > 0){
			list.remove(list.size()-1);
			return true;
		}
		return false;
	}
}
