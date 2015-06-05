package br.ufrn.imd.dsvisualizer.datastructures;

import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.jgraph.graph.DefaultGraphCell;

import br.ufrn.imd.dsvisualizer.gui.Drawer;

public class MyStack extends DataStructure{ /*Pilha*/
	List<Integer> stack;
	public MyStack(){
		stack = new LinkedList<Integer>();
		drawer = new MyStackDrawer();
	}
	
	public boolean insert(int a){
		if(!stack.contains(a)){
			stack.add(stack.size(), new Integer(a));
			return true;
		}
		return false;
	}
	
	public boolean remove(int a){
		if(stack.size() > 0){
			stack.remove(stack.size()-1);
			return true;
		}
		return false;
	}
	
	public boolean search(int a){
		if(stack.contains(a)){
			a = stack.get(a);
			return true;
		}
		return false;
	}
	
	class MyStackDrawer extends Drawer{
		public void draw(){
			HashMap<Integer, DefaultGraphCell> c = new HashMap<Integer, DefaultGraphCell>();
			int x = 30, y = 30;
			boolean d = true;
			for(int i = 0; i < stack.size(); i++){
				System.out.println(stack.get(i));
				createMyVertex(c, stack.get(i), x, y, Color.red);
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
					insertEdge(getDefaultPort((c.get(stack.get(i-1))), model),
							getDefaultPort(c.get(stack.get(i)), model));
					insertEdge(getDefaultPort((c.get(stack.get(i))), model),
							getDefaultPort(c.get(stack.get(i-1)), model));
				}
			}
			jgraph.getGraphLayoutCache().insert(c.values().toArray());
		}
	}
}
