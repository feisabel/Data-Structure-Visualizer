package br.ufrn.imd.dsvisualizer.datastructures;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;

import br.ufrn.imd.dsvisualizer.gui.Drawer;
/**
 * Class Union Find.
 * @author Ana Caroline
 *
 */
public class UnionFind extends DataStructure {
	private int[] unionfind;
	private int[] ordem;
	private int len;
	
	/**
	 * Constructor with one parameter.
	 * @param n number of trees
	 */
	public UnionFind(int n){
		drawer = new UnionFindDrawer();
		len = n;
		unionfind = new int[n];
		ordem = new int[n];
		for(int i = 0; i < n; i++){
			unionfind[i] = i; 
			ordem[i] = 0;
		}
		
		support("unite", 2);
		support("find", 1);
	}
	
	/**
	 * Unites x with y.
	 * @param x element to be united 
	 * @param y element to be united
	 */
	public void unite(int x, int y){
		link(unionfind[x], unionfind[y]);
	}
	
	/**
	 * Helper method to links two elements.
	 * @param x element to be united
	 * @param y element to be united
	 */
	private void link(int x, int y){
		if(ordem[x] > ordem[y]){
			unionfind[y] = x;
		}
		else{
			unionfind[x] = y;
			ordem[y]++;
		}
	}
	/**
	 * Finds the ???.
	 * @param x element to be found.
	 * @return ???
	 */
	public int find(int x){
		try{
			if(x != unionfind[x]){
				unionfind[x] = find(unionfind[x]);	
			}
			return unionfind[x];
		}
		catch(Exception e){
			System.out.println("Invalid argument");
			return -1;
		}
	}
	
	private int maxOrdem(){
		int m = ordem[0];
		for(int i = 1; i < len; i++){
			if(m < ordem[i]){
				m = ordem[i];
			}
		}
		
		return m;
	}
	
	private int level(int i){
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

	private class UnionFindDrawer extends Drawer{
		private class Pos {
			int a;
			int b;
			Pos(int x, int y){
				a = x;
				b = y;
			}
		}
		
		public void draw(){
			HashMap<Integer, DefaultGraphCell> c = new HashMap<Integer, DefaultGraphCell>();
			HashMap<Integer, Pos> pos = new HashMap<Integer, Pos>();
			
			int x = 50, y = 50;
			
			for(int j = 0; j <= maxOrdem(); j++){
				for(int i = 0; i < len; i++){
					if(level(i) == j){
						int X, Y;
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
						createMyVertex(c, i, X, Y + 50, Color.red );
						pos.put(i, new Pos(X, Y + 50));
						if(j == 0){
							insertEdge(getDefaultPort((c.get(i)), model),
									getDefaultPort((c.get(i)), model));
						}else{
							insertEdge(getDefaultPort((c.get(i)), model),
							   getDefaultPort(c.get(unionfind[i]), model));
						}
					}
				}//pegar todos os irmãos de um i que estão na ordem j
				//tem que saber quando "irmãos há nessa ordem"
			}			
			jgraph.getGraphLayoutCache().insert(c.values().toArray());
	
		}
		
	}
}
