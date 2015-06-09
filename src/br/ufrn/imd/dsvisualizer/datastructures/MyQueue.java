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
		
		support("enqueue", "num");
		support("dequeue");
	}
	
	/**
	 * Inserts new node in the last position.
	 * @param a new node
	 * @return true if inserted, false otherwise
	 */
	public boolean enqueue(int a){ //insere sempre no último
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
	public boolean dequeue(){ //remove no primeiro
		if(queue.size() > 0){
			queue.remove(0);
			return true;
		}
		return false;
	}
	
	/**
	 * Class to draw the structure.
	 * @author Ana Caroline
	 *
	 */
	class MyQueueDrawer extends Drawer{
		public void draw(){
			int x = 30, y = 30;
			boolean d = true;
			for(int i = 0; i < queue.size(); i++){
				createMyVertex(queue.get(i), x, y, Color.red);
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
					insertEdge(getDefaultPort((cells.get(queue.get(i-1))), model),
							getDefaultPort(cells.get(queue.get(i)), model));
					insertEdge(getDefaultPort((cells.get(queue.get(i))), model),
							getDefaultPort(cells.get(queue.get(i-1)), model));
				}
			}
			jgraph.getGraphLayoutCache().insert(cells.values().toArray());
		}
	}

}
