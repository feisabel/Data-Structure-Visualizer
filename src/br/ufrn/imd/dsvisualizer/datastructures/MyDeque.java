package br.ufrn.imd.dsvisualizer.datastructures;

import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.jgraph.graph.DefaultGraphCell;

import br.ufrn.imd.dsvisualizer.gui.Drawer;


public class MyDeque extends DataStructure{
	List<Integer> deque;
	public MyDeque(){
		deque = new LinkedList<Integer>();
		drawer = new MyDequeDrawer();
	}
	public boolean insertFirst(int a){ //insere sempre no primeiro
		if(!deque.contains(a)){
			deque.add(0, new Integer(a));
			return true;
		}
		return false;
	}
	
	public boolean insertLast(int a){ //insere sempre no último
		if(!deque.contains(a)){
			deque.add(deque.size(), new Integer(a));
			return true;
		}
		return false;
	}
	
	public boolean removeFirst(){ //remove no primeiro
		if(deque.size() > 0){
			deque.remove(0);
			return true;
		}
		return false;
	}
	
	public boolean removeLast(){ //remove no último
		if(deque.size() > 0){
			deque.remove(deque.size()-1);
			return true;
		}
		return false;
	}
	
	public boolean search(int a){
		if(deque.contains(a)){
			a = deque.get(a);
			return true;
		}
		return false;
	}
	
	class MyDequeDrawer extends Drawer{
		public void draw(){
			HashMap<Integer, DefaultGraphCell> c = new HashMap<Integer, DefaultGraphCell>();
			int x = 30, y = 30;
			boolean d = true;
			for(int i = 0; i < deque.size(); i++){
				createMyVertex(c, deque.get(i), x, y, Color.red);
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
					insertEdge(getDefaultPort((c.get(deque.get(i-1))), model),
							getDefaultPort(c.get(deque.get(i)), model));
					insertEdge(getDefaultPort((c.get(deque.get(i))), model),
							getDefaultPort(c.get(deque.get(i-1)), model));
				}
			}
			jgraph.getGraphLayoutCache().insert(c.values().toArray());
		}
	}
	
}
