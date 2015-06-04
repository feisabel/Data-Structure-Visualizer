package br.ufrn.imd.dsvisualizer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;
import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;

public class UnionFind extends DataStructure {
	private int[] unionfind;
	private int[] ordem;
	private int len;
	
	/*Só para funcionar!*/
	public boolean search(int num){ return true; }
	public boolean insert(int num){ return true; }
	public boolean remove(int num){ return true; }
	
	
	public UnionFind (int n){
		drawer = new UnionFindDrawer();
		len = n;
		unionfind = new int[n];
		ordem = new int[n];
		for(int i = 0; i < n; i++){
			unionfind[i] = i; 
			ordem[i] = 0;
		}
	}
	
	public void unite(int x, int y){
		link(unionfind[x], unionfind[y]);
	}
	
	private void link(int x, int y){
		if(ordem[x] > ordem[y]){
			unionfind[y] = x;
		}
		else{
			unionfind[x] = y;
			ordem[y]++;
		}
	}
	public int searchUni(int x){
		try{
			if(x != unionfind[x]){
				unionfind[x] = searchUni(unionfind[x]);	
			}
			return unionfind[x];
		}
		catch(Exception e){
			System.out.println("Invalid argument");
			return -1;
		}
	}
	
	private Kid kids(int i){
		int numberKids = 0;
		int[] a = new int[len-1];
		for(int k = 0, b = 0; k < len-1; k++){
			if(unionfind[k] == i){
				a[b] = k;
				b++;
				numberKids++;
			}
		}
		Kid res = new Kid(numberKids, a);
		if(numberKids > 0)
			return res;
		else
			return null;
	}
	
	private int maxOrdem(){
		int m = ordem[0];
		for(int i = 1; i < len; i++){
			if(m < ordem[i]){
				m = ordem[i];
			}
		}
		System.out.println("max ordem " + m);
		return m;
	}
	
	private int brothers(int k){
		int b = 0;
		for(int i = 0; i < len; i++){
			if(unionfind[i] == k)
				b++;
		}
		return b;
	}
	
	class Kid{
		int number; 
		int[] arrayKids;
		Kid(int x, int[] y){
			number = x;
			arrayKids = y;
		}
	}
	
	class Pos{
		int a;
		int b;
		Pos(int x, int y){
			a = x;
			b = y;
		}
	}

	class UnionFindDrawer extends Drawer{
		public void draw(){
			HashMap<Integer, DefaultGraphCell> c = new HashMap<Integer, DefaultGraphCell>();
			HashMap<Integer, Pos> pos = new HashMap<Integer, Pos>();
			
			int x = 50, y = 50;
			
			for(int i = 0; i < len; i++){
				if(unionfind[i] == i){
						createMyVertex(c, i, x, y, Color.red);
						insertEdge(getDefaultPort((c.get(i)), model),
								getDefaultPort((c.get(i)), model));
						x+=100;
						pos.put(i, new Pos(x, y));
					}
			}
			
			for(int j = maxOrdem() - 1 ; j >= 0; j--){
				for(int i = 0; i < len; i++){
					System.out.println("pai "+i);
					Kid myKids = kids(i);
					if(myKids != null){
						System.out.println(myKids.number);
						Pos parentPos = pos.get(i);
						int deltaX = 100/myKids.number;
						for(int k = 0, posX = parentPos.a; 
								k < myKids.number; k++, posX+=deltaX){
							
							System.out.println("filho " + myKids.arrayKids[k]);
							
							createMyVertex(c, myKids.arrayKids[k], posX, parentPos.b + 50, Color.red );
							
							insertEdge(getDefaultPort((c.get(myKids.arrayKids[k])), model),
									   getDefaultPort((c.get(i)), model));
						
						}
					}
					//pegar todos os irmãos de um i que estão na ordem j
					//tem que saber quando "irmãos há nessa ordem"
				}
				y+=30;
			}
			jgraph.getGraphLayoutCache().insert(c.values().toArray());
			
		}
		
	}
}
