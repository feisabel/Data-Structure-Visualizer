package br.ufrn.imd.dsvisualizer;

public class RBTree extends BinarySearchTree {
	
	private RBTree root;
	
	public RBTree() {
		root = null;
	}
	
	public void insert(int key) {
		privateInsert(key, root, null, 1);
	}
	
	private void privateInsert(int key, RBNode node, RBNode dad, int b) {
		if (node = null) {
			node = new RBNode(null, null, null, key, RED);
			if (root = null) {
				root = node;
				node.setColor(BLACK);
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
				insert(key, node, dad, b);
				if (b == 1)
					
			}
		}
	}
}
