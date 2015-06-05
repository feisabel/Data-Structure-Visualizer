package br.ufrn.imd.dsvisualizer;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import br.ufrn.imd.dsvisualizer.UnionFind;


public class Main {
	public static void main(String[] args){
		UnionFind u = new UnionFind(6);
		u.unite(3, 4);
		u.unite(5, 2);
		u.unite(3, 2);
		u.unite(0, 1);
		u.unite(0, 2);
		u.draw();
		JFrame j = new JFrame();
		j.getContentPane().add(new JScrollPane(u.getJGraph()));
		j.pack();
		j.setVisible(true);
	}
}
