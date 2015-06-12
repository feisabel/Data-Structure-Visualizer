package br.ufrn.imd.dsvisualizer.datastructures;

import java.awt.Color;
import java.util.HashMap;

import br.ufrn.imd.dsvisualizer.gui.Drawer;
/**
 * Class Union Find.
 * @author Ana Caroline
 *
 */
public class UnionFind extends DataStructure {
	/**
	 * Generated serial ID.
	 */
	private static final long serialVersionUID = 8165513710592468614L;
	private int[] unionfind;
	private int[] order;
	private int len;
	
	/**
	 * Constructor with one parameter.
	 * @param n number of trees
	 */
	public UnionFind(int n){
		if(n > 10)
			n = 10; //tamanho máximo permitido
		drawer = new UnionFindDrawer();
		len = n;
		unionfind = new int[n];
		order = new int[n];
		for(int i = 0; i < n; i++){
			unionfind[i] = i; 
			order[i] = 0;
		}
		
		support("unite", "num1", "num2");
		support("find", "num");
	}
	
	/**
	 * Unites x with y.
	 * @param x element to be united 
	 * @param y element to be united
	 */
	public void unite(int x, int y){
		link(find(x), find(y));
	}
	
	/**
	 * Helper method to links two elements.
	 * @param x element to be united
	 * @param y element to be united
	 * @throws DSIllegalArgumentsException  
	 */
	private void link(int x, int y){
		if (x != y) {
			if(order[x] > order[y]){
				unionfind[y] = x;
			}
			else{
				unionfind[x] = y;
				order[y]++;
			}
		}
	}
	/**
	 * Finds the root.
	 * @param x element to be found.
	 * @return root
	 */
	public int find(int x){
		if(x != unionfind[x]){
			unionfind[x] = find(unionfind[x]);	
		}
		return unionfind[x];
	}
	
	/**
	 * Returns the greater order.
	 * @return greater order
	 */
	private int maxOrder(){
		int m = order[0];
		for(int i = 1; i < len; i++){
			if(m < order[i]){
				m = order[i];
			}
		}
		
		return m;
	}
	
	/**
	 * Returns node level.
	 * @param i node
	 * @return level
	 */
	private int level(int i) {
		int res = 0;
		boolean t = false;
		while(!t){
			if(unionfind[i] == i)
				t = true;
			else{
				int k = unionfind[i];
				i = k;
				res++;
			}
		}
		return res;
	}
	
	/**
	 * Class to draw UnionFind.
	 * @author Ana Caroline
	 */
	private class UnionFindDrawer extends Drawer{
		/**
		 * Default serial version.
		 */
		private static final long serialVersionUID = 1L;
		/**
		 * Helper class to draw UnionFind.
		 * @author Ana Caroline
		 *
		 */		
		private class Pos {
			int a;
			int b;
			/**
			 * Constructor with no parameters.
			 * @param x position axis x
			 * @param y position axis y
			 */
			Pos(int x, int y){
				a = x;
				b = y;
			}
		}
		/**
		 * Method to draw UnionFind.
		 */
		public void draw(){
			HashMap<Integer, Pos> pos = new HashMap<Integer, Pos>();
			
			int x = 50, y = 0;
			int max = maxOrder();
			for(int j = 0; j <= max; j++){
				for(int i = 0; i < len; i++){
					if(level(i) == j){
						int X, Y;
						
						if(x >= getPreferredSize().width - 50){
							x = 50;
							y += 150;
						}
						if(j == 0){
							X = x;
							Y = y;
							x += 150;
						}else{
							Pos parentPos = pos.get(unionfind[i]); //pega a posição do pai
							X = parentPos.a;
							Y = parentPos.b;
							parentPos.a += 50;
						}
						createMyVertex(i, X, Y + 50, Color.red );
						pos.put(i, new Pos(X, Y + 50));
						if(j == 0){
							insertEdge(getDefaultPort(cells.get(i)), getDefaultPort(cells.get(i)));
						}else{
							insertEdge(getDefaultPort(cells.get(i)), getDefaultPort(cells.get(unionfind[i])));
						}
					}
				}//pegar todos os irmãos de um i que estão na ordem j
				//tem que saber quando "irmãos há nessa ordem"
			}			
			jgraph.getGraphLayoutCache().insert(cells.values().toArray());
		}		
	}
}
