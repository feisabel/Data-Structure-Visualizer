package br.ufrn.imd.dsvisualizer.datastructures;

import java.awt.Color;
import java.awt.Dimension;
import java.util.LinkedList;
import java.util.List;

import br.ufrn.imd.dsvisualizer.gui.Drawer;

public class AbstractList extends DataStructure {
	protected List<Integer> list;
	
	public AbstractList() {
		list = new LinkedList<Integer>();
		drawer = new ListDrawer();
	}
	
	/**
	 * Class to draw the structure.
	 * @author Ana Caroline
	 */
	class ListDrawer extends Drawer{
		/**
		 * Default serial version.
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * Default constructor.
		 */
		public ListDrawer() {
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
				if(x + 60 > getPreferredSize().width && d){
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
