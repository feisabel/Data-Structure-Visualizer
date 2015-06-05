package br.ufrn.imd.dsvisualizer;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import br.ufrn.imd.dsvisualizer.datastructures.Factory;
import br.ufrn.imd.dsvisualizer.datastructures.MyQueue;


public class Main {
	public static void main(String[] args){
		MyQueue u = (MyQueue)Factory.create(Factory.QUEUE, 6);
		u.removeFirst();
		u.draw();
		JFrame j = new JFrame();
		j.getContentPane().add(new JScrollPane(u.getJGraph()));
		j.pack();
		j.setVisible(true);
	}
}
