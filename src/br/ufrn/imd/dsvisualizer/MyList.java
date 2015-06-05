package br.ufrn.imd.dsvisualizer;

import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.jgraph.graph.DefaultGraphCell;

public class MyList extends DataStructure {
	List<Integer> list;
	public MyList(){
		list = new LinkedList<Integer>();
		drawer = new MyListDrawer();
		
		support("insert", 1);
		support("delete", 1);
		support("search", 1);
	}
	
	public void insert(int a){
		list.add(new Integer(a));
	}
	
	public boolean delete(int a){
		if(list.contains(a)){
			list.remove(new Integer(a));
			return true;
		}
		return false;
	}
	
	public boolean search(int a){
		if(list.contains(a)){
			a = list.get(a);
			return true;
		}
		return false;
	}
	
	class MyListDrawer extends Drawer{
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
					insertEdge(getDefaultPort((c.get(i-1)), model),
							getDefaultPort(c.get(i), model));
					insertEdge(getDefaultPort((c.get(i)), model),
							getDefaultPort(c.get(i-1), model));
				}
			}
			jgraph.getGraphLayoutCache().insert(c.values().toArray());
		}
	}
}
