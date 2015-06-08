package br.ufrn.imd.dsvisualizer;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import br.ufrn.imd.dsvisualizer.datastructures.DataStructure;
import br.ufrn.imd.dsvisualizer.datastructures.Factory;
import br.ufrn.imd.dsvisualizer.datastructures.AVLTree;


public class Main {
	public static void main(String[] args){
		AVLTree u = (AVLTree)Factory.create(Factory.AVL, 0);
		u.insert(2);
		u.insert(45);
		u.insert(90);
		u.insert(12);
		u.insert(47);
		u.insert(99);
		u.insert(16);
		u.insert(3);
		u.insert(10);
		u.insert(75);
		u.insert(14);
		u.insert(3);
		u.insert(7);
		u.insert(27);
		u.delete(10);
		u.delete(45);
		u.delete(-1);
		u.draw();
		JFrame j = new JFrame();
		j.getContentPane().add(new JScrollPane(u.getJGraph()));
		j.setSize(1000, 900); //PQ ISSO NAO ADIANTA?
		j.pack();
		j.setVisible(true);
	}
}
