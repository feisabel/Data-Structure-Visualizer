package br.ufrn.imd.dsvisualizer;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import br.ufrn.imd.dsvisualizer.datastructures.AVLTree;
import br.ufrn.imd.dsvisualizer.datastructures.RBTree;
import br.ufrn.imd.dsvisualizer.datastructures.BinarySearchTree;
import br.ufrn.imd.dsvisualizer.datastructures.DataStructure;
import br.ufrn.imd.dsvisualizer.datastructures.Factory;


public class Main {
	public static void main(String[] args){
		DataStructure u = Factory.create(Factory.HEAPMAX, 15);
		u.draw();
		JFrame j = new JFrame();
		j.getContentPane().add(new JScrollPane(u.getJGraph()));
		j.pack();
		j.setVisible(true);
	}
}
