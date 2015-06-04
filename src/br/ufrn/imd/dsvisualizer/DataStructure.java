package br.ufrn.imd.dsvisualizer;
import org.jgraph.JGraph;

abstract public class DataStructure {
	protected Drawer drawer;
	
	abstract public boolean search(int num);
	abstract public boolean insert(int num);
	abstract public boolean remove(int num);
	
	public void draw() {
		drawer.draw();
	}
	
	public JGraph getJGraph() {
		return drawer.getJGraph();
	}
}
