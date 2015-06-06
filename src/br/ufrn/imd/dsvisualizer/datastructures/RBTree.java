package br.ufrn.imd.dsvisualizer.datastructures;

import java.awt.Color;

public class RBTree extends BinarySearchTree {
	
	private RBNode root;
	
	public RBTree() {
		root = null;
	}
	
	public void insert(int key) {
		privateInsert(key, root, null, 1);
	}
	
	private void privateInsert(int key, RBNode node, RBNode dad, int b) {
		if (node == null) {
			node = new RBNode(null, null, null, key);
			if (root == null) {
				root = node;
				node.setColor(Color.BLACK);
			}
			else {
				if (key < dad.getKey())
					dad.setLeft(node);
				else
					dad.setRight(node);
				node.setParent(dad);
			}
		}
		else {
			if (key != node.getKey()) {
				dad = node;
				if (key < node.getKey())
					node = node.getLeft();
				else
					node = node.getRight();
				privateInsert(key, node, dad, b);
				if (b == 1);
			}
		}
	}
}
