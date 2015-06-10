package br.ufrn.imd.dsvisualizer;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import br.ufrn.imd.dsvisualizer.datastructures.DataStructure;
import br.ufrn.imd.dsvisualizer.datastructures.Factory;
import br.ufrn.imd.dsvisualizer.datastructures.AVLTree;
import br.ufrn.imd.dsvisualizer.datastructures.HeapMax;
import br.ufrn.imd.dsvisualizer.datastructures.HeapMin;
import br.ufrn.imd.dsvisualizer.datastructures.MyList;
import br.ufrn.imd.dsvisualizer.datastructures.BinarySearchTree;
import br.ufrn.imd.dsvisualizer.datastructures.RBTree;


public class Main {
	public static void main(String[] args){
		RBTree u = (RBTree)Factory.create(Factory.RB, 0);
		u.insert(20); u.insert(10); u.insert(50); u.insert(9);
		u.insert(17); u.insert(23); u.insert(95); u.insert(7);
		u.insert(16); u.insert(19); u.insert(53); u.insert(99);
		//u.remove(10);
		u.redraw();
		JFrame j = new JFrame();
		j.getContentPane().add(new JScrollPane(u.getJGraph()));
		j.setSize(1000, 900); //PQ ISSO NAO ADIANTA?
		j.pack();
		j.setVisible(true);
	}
}
