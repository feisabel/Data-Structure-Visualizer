package br.ufrn.imd.dsvisualizer.datastructures;

import java.awt.Color;
import java.awt.Dimension;
import java.util.LinkedList;
import java.util.List;

import br.ufrn.imd.dsvisualizer.gui.Drawer;

/**
 * Class stack.
 * @author Ana Caroline
 */
public class MyStack extends DataStructure{ /*Pilha*/
	private static final long serialVersionUID = -2034904035673369825L;
	
	private List<Integer> stack;
	
	/**
	 * Constructor with no parameters.
	 */
	public MyStack(){
		stack = new LinkedList<Integer>();
		drawer = new MyStackDrawer();
		support("push", "num");
		support("pop");
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
	 * Class to draw the structure.
	 * @author Ana Caroline
	 *
	 */
	class MyStackDrawer extends Drawer{
		private static final long serialVersionUID = 5841607402665557330L;
		
		public MyStackDrawer() {
			setPreferredSize(new Dimension(500,350));
		}
		
		/**
		 * Method to draw the structure.
		 */
		public void draw(){
			int x = 30, y = 30;
			boolean d = true;
			for(int i = 0; i < stack.size(); i++){
				createMyVertex(stack.get(i), x, y, Color.red);
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
					insertEdge(getDefaultPort(cells.get(stack.get(i-1))),
							getDefaultPort(cells.get(stack.get(i))));
					insertEdge(getDefaultPort(cells.get(stack.get(i))),
							getDefaultPort(cells.get(stack.get(i-1))));
				}
			}
			jgraph.getGraphLayoutCache().insert(cells.values().toArray());
		}
	}
}
