package br.ufrn.imd.dsvisualizer;
import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.JPanel;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphModel;


public class HeapFake extends DataStructure {
	LinkedList<Node> list;
	public HeapFake(){
		drawer = new HeapDrawer();
		list = new LinkedList<Node>();
	}
	
	public boolean insert(int a){
		if(!search(a)){
			list.add(list.size(), new Node(a));
			return true;
		}
		return false;
	}
	
	public boolean search(int a){
		for(Node b : list){
			if(b.getKey() == a)
				return true;
		}
		return false;
	}
	
	public boolean remove(int num) {
		// TODO Auto-generated method stub
		return false;
	}
	
	class HeapDrawer extends Drawer {
		public void draw() {
			int x = 0;
			int y = 30;
			int lvl = 1;
			HashMap<Integer,DefaultGraphCell> cells = new HashMap<Integer, DefaultGraphCell>();
			Node help = null;
			for(int i = 0; i < list.size(); i++){
				if(i%2 == 0){
					if(i != 0){
						help = list.get(i/2 - 1);
						x = (int) (help.getX() + DEFAULT_SIZE.width/Math.scalb(1.0, lvl));
					}else
						x = (int) (DEFAULT_SIZE.width/Math.scalb(1.0, lvl));
				}
				else{
					help = list.get(i/2);
					x = (int) (help.getX() - DEFAULT_SIZE.width/Math.scalb(1.0, lvl+1));
					
				}
				if(Math.scalb(1.0, lvl) == i+1){
					lvl++;
					y+=deltaY;
				}
				createMyVertex(cells, list.get(i), x, y, Color.red);
				list.get(i).setX(x);
				if(i != 0){
					insertEdge(getDefaultPort(cells.get(help.getKey()), model), 
							getDefaultPort(cells.get(list.get(i).getKey()), model));
				}
			}
			jgraph.getGraphLayoutCache().insert(asda, asda);
			jgraph.getGraphLayoutCache().insert(cells.values().toArray());
		}
	}
}
