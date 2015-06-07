package br.ufrn.imd.dsvisualizer.datastructures;

import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.jgraph.graph.DefaultGraphCell;

import br.ufrn.imd.dsvisualizer.gui.Drawer;

/**
 * Class list.
 * @author Ana Caroline
 *
 */
public class MyList extends DataStructure {
	List<Integer> list;
	
	/**
	 * Constructor with no parameters. 
	 */
	public MyList(){
		list = new LinkedList<Integer>();
		drawer = new MyListDrawer();
		
		support("insert", 1);
		support("delete", 1);
		support("search", 1);
	}
	
	/**
	 * Inserts new node given a position.
	 * @param a new node 
	 * @param position the node position
	 * @return true if inserted, false otherwise
	 */
	public boolean insert(int a, int position){
		if(!list.contains(a) && position >= 0 && position < list.size()){
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
		return 0;
	}
	
	/**
	 * Class to draw the structure.
	 * @author Ana Caroline
	 *
	 */
	class MyListDrawer extends Drawer{
		/**
		 * Method to draw the structure.
		 */
		public void draw(){
			HashMap<Integer, DefaultGraphCell> c = new HashMap<Integer, DefaultGraphCell>();
			int x = 30, y = 30;
			boolean d = true;
			for(int i = 0; i < list.size(); i++){
				createMyVertex(c, list.get(i), x, y, Color.red);
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
					insertEdge(getDefaultPort((c.get(list.get(i-1))), model),
							getDefaultPort(c.get(list.get(i)), model));
					insertEdge(getDefaultPort((c.get(list.get(i))), model),
							getDefaultPort(c.get(list.get(i-1)), model));
				}
			}
			jgraph.getGraphLayoutCache().insert(c.values().toArray());
		}
	}
}
