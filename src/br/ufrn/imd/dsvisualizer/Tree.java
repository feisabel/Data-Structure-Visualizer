package br.ufrn.imd.dsvisualizer;


public abstract class Tree {

	abstract public void insert(int key);
	
	abstract public Node delete(int key);
	
	abstract public Node search(int key);
}
