package br.ufrn.imd.dsvisualizer.datastructures;

public class BSTree {
	private BSTNode root;
	
	public BSTree() {
	}
	
	public BSTNode search(int key) {
		return search(root, key);
	}
	
	private BSTNode search(BSTNode node, int key) {
		if (node != null) {
			if (node.getKey() == key) {
				return node;
			} else if (node.getKey() > key) {
				return search(node.getLeft(), key);
			} else {
				return search(node.getRight(), key);
			}
		}
		return null;
	}
	
	public void insert(int key) {
		if (root == null) {
			root = new BSTNode();
		} else {
			insert(root, key);
		}
	}
	
	private void insert(BSTNode node, int key) {
		if (node.getKey() > key) {
			if (node.getLeft() == null) {
				node.setLeft(new BSTNode(node, null, null, key));
			} else {
				insert(node.getLeft(), key);
			}
		} else if (node.getKey() < key){
			if (node.getRight() == null) {
				node.setRight(new BSTNode(node, null, null, key));
			} else {
				insert(node.getRight(), key);
			}
		}
	}
	
	public BSTNode remove(int key) {
		return root;
	}
}
