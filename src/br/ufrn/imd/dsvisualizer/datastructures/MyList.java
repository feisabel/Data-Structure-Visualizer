package br.ufrn.imd.dsvisualizer.datastructures;

import java.awt.Color;
import java.awt.Dimension;
import java.util.LinkedList;
import java.util.List;

import br.ufrn.imd.dsvisualizer.gui.Drawer;

/**
 * Class list.
 * @author Ana Caroline
 *
 */
public class MyList extends DataStructure {
	/**
	 * Generated serial ID.
	 */
	private static final long serialVersionUID = 2554109786706088450L;
	private List<Integer> list;
	
	/**
	 * Constructor with no parameters. 
	 */
	public MyList(){
		list = new LinkedList<Integer>();
		drawer = new MyListDrawer();
		
		support("insert", "key", "position");
		support("remove", "key");
	}
	
	public String getShortName() {
		return "List";
	}
	
	/**
     * Returns structure description.
     * @return description
     */
	public String getDescription(){
		return "Lista é uma estrutura em que cada node tem referência para o node anterior e seguinte. " +
				"Dessa forma para inserir a lista é percorrida até o local indicado e então o node é inserido," +
				" mesma ideia para remoção. Portanto as operações são log n. A pesquisa também percorre a lista de" +
				" nodes até encontrar ou até chegar ao final.";
	}
	
	
	/**
	 * Inserts new node given a position.
	 * @param a new node 
	 * @param position the node position
	 * @return true if inserted, false otherwise
	 */
	public boolean insert(int a, int position){
		if(!list.contains(a) && position >= 0 && position <= list.size()){
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
		return -1;
	}
	
	/**
	 * Class to draw the structure.
	 * @author Ana Caroline
	 *
	 */
	class MyListDrawer extends Drawer{
		/**
		 * Default serial version.
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * Default constructor.
		 */
		public MyListDrawer() {
			setPreferredSize(new Dimension(500, 350));
		}
		
		/**
		 * Method to draw the structure.
		 */
		public void draw(){
			int x = 30, y = 30;
			boolean d = true;
			for(int i = 0; i < list.size(); i++){
				createMyVertex(list.get(i), x, y, Color.red);
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
					insertEdge(getDefaultPort(cells.get(list.get(i-1))),
							getDefaultPort(cells.get(list.get(i))));
					insertEdge(getDefaultPort(cells.get(list.get(i))),
							getDefaultPort(cells.get(list.get(i-1))));
				}
			}
			jgraph.getGraphLayoutCache().insert(cells.values().toArray());
		}
	}
}
