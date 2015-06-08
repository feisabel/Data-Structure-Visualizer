package br.ufrn.imd.dsvisualizer;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import br.ufrn.imd.dsvisualizer.datastructures.AVLTree;
import br.ufrn.imd.dsvisualizer.datastructures.HeapMax;
import br.ufrn.imd.dsvisualizer.datastructures.HeapMin;
import br.ufrn.imd.dsvisualizer.datastructures.RBTree;
import br.ufrn.imd.dsvisualizer.datastructures.BinarySearchTree;
import br.ufrn.imd.dsvisualizer.datastructures.DataStructure;
import br.ufrn.imd.dsvisualizer.datastructures.Factory;


public class Main {
	public static void main(String[] args){
		HeapMin u = (HeapMin) Factory.create(Factory.HEAPMIN, 9);
		u.remove();
		u.remove();
		u.remove();
		u.remove();
		u.remove();
		/*	u.insert(3);
		u.insert(20);
		u.insert(1);
		u.insert(27);
		u.insert(20);
		u.insert(29);
		u.insert(17);
		u.insert(12);
		u.insert(21);*/
		u.draw();
		JFrame j = new JFrame();
		j.getContentPane().add(new JScrollPane(u.getJGraph()));
		j.pack();
		j.setVisible(true);
	}
}
