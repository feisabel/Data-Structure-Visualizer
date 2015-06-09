package br.ufrn.imd.dsvisualizer;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import br.ufrn.imd.dsvisualizer.datastructures.DataStructure;
import br.ufrn.imd.dsvisualizer.datastructures.Factory;
import br.ufrn.imd.dsvisualizer.datastructures.AVLTree;


public class Main {
	public static void main(String[] args){
		AVLTree u = (AVLTree)Factory.create(Factory.AVL, 10);
		u.redraw();
		JFrame j = new JFrame();
		j.getContentPane().add(new JScrollPane(u.getJGraph()));
		j.setSize(1000, 900); //PQ ISSO NAO ADIANTA?
		j.pack();
		j.setVisible(true);
	}
}
