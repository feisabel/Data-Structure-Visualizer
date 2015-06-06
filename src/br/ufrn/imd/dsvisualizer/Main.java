package br.ufrn.imd.dsvisualizer;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import br.ufrn.imd.dsvisualizer.datastructures.AVLTree;
import br.ufrn.imd.dsvisualizer.datastructures.DataStructure;
import br.ufrn.imd.dsvisualizer.datastructures.Factory;


public class Main {
	public static void main(String[] args){
		AVLTree u = (AVLTree)Factory.create(Factory.AVL, 0);
		u.insert(29);
		u.insert(2);
		u.insert(25);
		u.insert(9);
		u.insert(15);
		u.insert(29);
		u.insert(2);
		u.insert(20);
		u.insert(0);
		u.insert(2);
		u.draw();
		JFrame j = new JFrame();
		j.getContentPane().add(new JScrollPane(u.getJGraph()));
		j.pack();
		j.setVisible(true);
	}
}
