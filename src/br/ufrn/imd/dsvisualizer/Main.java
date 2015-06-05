package br.ufrn.imd.dsvisualizer;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import br.ufrn.imd.dsvisualizer.MyList;
import br.ufrn.imd.dsvisualizer.UnionFind;


public class Main {
	public static void main(String[] args){
		MyList u = new MyList();
		for(int i = 0; i < 30; i++)
			u.insert(i);
		u.draw();
		JFrame j = new JFrame();
		j.getContentPane().add(new JScrollPane(u.getJGraph()));
		j.pack();
		j.setVisible(true);
	}
}
