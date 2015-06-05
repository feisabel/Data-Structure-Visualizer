package br.ufrn.imd.dsvisualizer.datastructures;

public abstract class Tree extends DataStructure {
	
	public Tree() {
		support("insert", 1);
		support("delete", 1);
		support("search", 1);
	}
	
	abstract public void insert(int key);
	abstract public Node delete(int key);
	abstract public Node search(int key);
}
