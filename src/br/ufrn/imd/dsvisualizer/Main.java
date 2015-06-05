package br.ufrn.imd.dsvisualizer;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import br.ufrn.imd.dsvisualizer.BinarySearchTree;
import br.ufrn.imd.dsvisualizer.DataStructure;
import br.ufrn.imd.dsvisualizer.Factory;
import br.ufrn.imd.dsvisualizer.MyList;
import br.ufrn.imd.dsvisualizer.UnionFind;


public class Main {
	public static void main(String[] args){
		DataStructure u = Factory.create(Factory.LIST, 9);
		u.draw();
		JFrame j = new JFrame();
		j.getContentPane().add(new JScrollPane(u.getJGraph()));
		j.pack();
		j.setVisible(true);
	}
}
