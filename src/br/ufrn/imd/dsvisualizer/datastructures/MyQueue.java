package br.ufrn.imd.dsvisualizer.datastructures;

import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.jgraph.graph.DefaultGraphCell;

import br.ufrn.imd.dsvisualizer.gui.Drawer;
/**
 * Class queue.
 * @author Ana Caroline
 *
 */
public class MyQueue extends DataStructure {
	List<Integer> queue;
	
	/**
	 * Constructor with no parameters.
	 */
	public MyQueue(){
		queue = new LinkedList<Integer>();
		drawer = new MyQueueDrawer();
	}
	
	/**
	 * Inserts new node in the last position.
	 * @param a new node
	 * @return true if inserted, false otherwise
	 */
	public boolean insertLast(int a){ //insere sempre no último
		if(!queue.contains(a)){
			queue.add(queue.size(), new Integer(a));
			return true;
		}
		return false;
	}
	
	/**
	 * Removes node given a key if it exists. 
	 * @return true if removed, false otherwise
	 */
	public boolean removeFirst(){ //remove no primeiro
		if(queue.size() > 0){
			queue.remove(0);
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
		if(queue.contains(a)){
			a = queue.indexOf(a);
			return a;
		}
		return 0;
	}
	
	/**
	 * Class to draw the structure.
	 * @author Ana Caroline
	 *
	 */
	class MyQueueDrawer extends Drawer{
		public void draw(){
			HashMap<Integer, DefaultGraphCell> c = new HashMap<Integer, DefaultGraphCell>();
			int x = 30, y = 30;
			boolean d = true;
			for(int i = 0; i < queue.size(); i++){
				createMyVertex(c, queue.get(i), x, y, Color.red);
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
					insertEdge(getDefaultPort((c.get(queue.get(i-1))), model),
							getDefaultPort(c.get(queue.get(i)), model));
					insertEdge(getDefaultPort((c.get(queue.get(i))), model),
							getDefaultPort(c.get(queue.get(i-1)), model));
				}
			}
			jgraph.getGraphLayoutCache().insert(c.values().toArray());
		}
	}

}
