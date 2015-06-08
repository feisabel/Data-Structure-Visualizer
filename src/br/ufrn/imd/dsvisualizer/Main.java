package br.ufrn.imd.dsvisualizer;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import br.ufrn.imd.dsvisualizer.datastructures.DataStructure;
import br.ufrn.imd.dsvisualizer.datastructures.Factory;


public class Main {
	public static void main(String[] args){
		DataStructure u = Factory.create(Factory.RB, 9);
		/*u.unite(2, 3);
		u.unite(3, 4);
		u.unite(5, 6);
		u.unite(6, 2);
		u.find(30);*/
		u.draw();
		JFrame j = new JFrame();
		j.getContentPane().add(new JScrollPane(u.getJGraph()));
		j.setSize(1000, 900); //PQ ISSO NAO ADIANTA?
		j.pack();
		j.setVisible(true);
	}
}
