package br.ufrn.imd.dsvisualizer;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import br.ufrn.imd.dsvisualizer.datastructures.BinarySearchTree;
import br.ufrn.imd.dsvisualizer.datastructures.DataStructure;
import br.ufrn.imd.dsvisualizer.datastructures.Factory;
import br.ufrn.imd.dsvisualizer.datastructures.MyList;
import br.ufrn.imd.dsvisualizer.datastructures.MyStack;
import br.ufrn.imd.dsvisualizer.datastructures.UnionFind;


public class Main {
	public static void main(String[] args){
		MyStack u = (MyStack)Factory.create(Factory.STACK, 15);
		u.remove(1);
		u.draw();
		JFrame j = new JFrame();
		j.getContentPane().add(new JScrollPane(u.getJGraph()));
		j.pack();
		j.setVisible(true);
	}
}
