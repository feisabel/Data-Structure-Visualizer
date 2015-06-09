package br.ufrn.imd.dsvisualizer.datastructures;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import br.ufrn.imd.dsvisualizer.gui.Drawer;

/**
 * Class deque.
 * @author Ana Caroline
 */
public class MyDeque extends DataStructure{
	/**
	 * Generated serial ID.
	 */
	private static final long serialVersionUID = -1888305749422593009L;
	
	private List<Integer> deque;
	
	/**
	 * Constructor with no parameters. 
	 */
	public MyDeque(){
		deque = new LinkedList<Integer>();
		drawer = new MyDequeDrawer();

		support("insertFirst", "key");
		support("insertLast", "key");
		support("removeFirst");
		support("removeLast");
	}
	
	/**
     * Returns structure description.
     * @return description
     */
	public String getDescription(){
		return "Deque é uma estrutura em que cada node contém uma referência para o próximo e para o anterior" +
				" , sendo permitido apenas inserção no início e no final e só remoção no início e no final. " +
				"Logo a complexidade dessas operações é O(1) caso a implementação guarde refências diretas para o" +
				" início e para o final.";
	}
	
	/**
	 * Inserts new node in the first position.
	 * @param a new node 
	 * @return true if inserted, false otherwise
	 */
	public boolean insertFirst(int a){ //insere sempre no primeiro
		if(!deque.contains(a)){
			deque.add(0, new Integer(a));
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
		if(!deque.contains(a)){
			deque.add(deque.size(), new Integer(a));
			return true;
		}
		return false;
	}
	
	/**
	 * Removes the first node if exists in the structure.
	 * @return true if removed, false otherwise
	 */
	public boolean removeFirst(){ //remove no primeiro
		if(deque.size() > 0){
			deque.remove(0);
			return true;
		}
		return false;
	}
	
	/**
	 * Removes the last node if exists in the structure.
	 * @return true if removed, false otherwise
	 */
	public boolean removeLast(){ //remove no último
		if(deque.size() > 0){
			deque.remove(deque.size()-1);
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
		if(deque.contains(a)){
			a = deque.indexOf(a);
			return a;
		}
		return 0;
	}
	
	/**
	 * Class to draw the structure.
	 * @author Ana Caroline
	 *
	 */
	class MyDequeDrawer extends Drawer{
		/**
		 * Default serial version.
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * Method to draw the structure.
		 */
		public void draw(){
			int x = 30, y = 30;
			boolean d = true;
			for(int i = 0; i < deque.size(); i++){
				createMyVertex(deque.get(i), x, y, Color.red);
				if(x + 60 > DEFAULT_SIZE.width && d){
					d = false;
					y+= 60;
				}else if(x - 60 < 0 && !d){
					d = true;
					y+=60;
				}
				if(d){
					x+=60;
				}else{
					x-=60;
				}
				if(i != 0){
					insertEdge(getDefaultPort(cells.get(deque.get(i-1))),
							getDefaultPort(cells.get(deque.get(i))));
					insertEdge(getDefaultPort(cells.get(deque.get(i))),
							getDefaultPort(cells.get(deque.get(i-1))));
				}
			}
			jgraph.getGraphLayoutCache().insert(cells.values().toArray());
		}
	}
	
}
