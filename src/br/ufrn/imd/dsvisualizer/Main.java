package br.ufrn.imd.dsvisualizer;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import br.ufrn.imd.dsvisualizer.datastructures.DataStructure;
import br.ufrn.imd.dsvisualizer.datastructures.Factory;
import br.ufrn.imd.dsvisualizer.datastructures.AVLTree;
import br.ufrn.imd.dsvisualizer.datastructures.HeapMax;
import br.ufrn.imd.dsvisualizer.datastructures.HeapMin;
import br.ufrn.imd.dsvisualizer.datastructures.MyList;


public class Main {
	public static void main(String[] args){
		HeapMin u = (HeapMin)Factory.create(Factory.HEAPMIN, 10);
		u.remove();
		u.remove();
		u.remove();
		u.redraw();
		JFrame j = new JFrame();
		j.getContentPane().add(new JScrollPane(u.getJGraph()));
		j.setSize(1000, 900); //PQ ISSO NAO ADIANTA?
		j.pack();
		j.setVisible(true);
	}
}
