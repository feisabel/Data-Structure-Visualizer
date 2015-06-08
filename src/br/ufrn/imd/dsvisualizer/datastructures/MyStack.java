package br.ufrn.imd.dsvisualizer.datastructures;

import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.jgraph.graph.DefaultGraphCell;

import br.ufrn.imd.dsvisualizer.gui.Drawer;

/**
 * Class stack.
 * @author Ana Caroline
 *
 */
public class MyStack extends DataStructure{ /*Pilha*/
	List<Integer> stack;
	/**
	 * Constructor with no parameters.
	 */
	public MyStack(){
		stack = new LinkedList<Integer>();
		drawer = new MyStackDrawer();
		support("push", 1);
		support("pop", 1);
	}
	
	/**
	 * Adds new node in the top.
	 * @param a new node
	 * @return true if inserted, false otherwise
	 */
	public boolean push(int a){
		if(!stack.contains(a)){
			stack.add(stack.size(), new Integer(a));
			return true;
		}
		return false;
	}
	
	/**
     * Returns structure description.
     * @return description
     */
	public String getDescription(){
		return "Stack é uma estrutura composta por uma sequência de nodes que possuem referência para o " +
				"anterior e para o seguinte. " +
				"Ela só permite remoção e inserção no topo, isto é, no último lugar da sequência." + 
				"A pesquisa percorre toda a pilha, logo é O(n). Inserção e remoção possui complexidade O(1)" +
				"quando é guardada referência do topo.";
	}
	
	/**
	 * Removes the node in the top if exists.
	 * @return true if removed, false otherwise
	 */
	public boolean pop(){
		if(stack.size() > 0){
			stack.remove(stack.size()-1);
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
		if(stack.contains(a)){
			a = stack.indexOf(a);
			return a;
		}
		return 0;
	}
	
	/**
	 * Class to draw the structure.
	 * @author Ana Caroline
	 *
	 */
	class MyStackDrawer extends Drawer{
		/**
		 * Method to draw the structure.
		 */
		public void draw(){
			HashMap<Integer, DefaultGraphCell> c = new HashMap<Integer, DefaultGraphCell>();
			int x = 30, y = 30;
			boolean d = true;
			for(int i = 0; i < stack.size(); i++){
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
