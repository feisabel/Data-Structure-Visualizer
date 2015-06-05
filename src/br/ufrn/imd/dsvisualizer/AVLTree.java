package br.ufrn.imd.dsvisualizer;

public class AVLTree extends BinarySearchTree {
	
	private AVLNode root;
    
    /**
     * Constructor for class Tree
     */
    public AVLTree()
    {
        root = null;
    }
    
    private AVLNode root() {
    	return root;
    }
	
	public void insert(int key) {
		privateInsert(key, root, null, true);
	}
	
	private void privateInsert(int key, AVLNode node, AVLNode dad, boolean b) {
		if (node == null) {
			AVLNode newNode = new AVLNode(dad, null, null, key, 0);
			if (dad == null)
				root = newNode;
			else if (key < dad.getKey())
				dad.setLeft(newNode);
			else
				dad.setRight(newNode);
			b = true;
		}
		else {
			if (key == node.getKey())
				return;
			else if (key < node.getKey()) {
				privateInsert(key, node.getLeft(), node, b);
				if (b) {
					if (node.getBalance() == 1) {
						node.setBalance(0);
						b = false;
					}
					else if (node.getBalance() == 0)
						node.setBalance(-1);
					else {
						rightRotations(node);
						b = false;
					}
				}
			}
			else {
				privateInsert(key, node.getRight(), node, b);
				if (b) {
					if (node.getBalance() == -1) {
						node.setBalance(0);
						b = false;
					}
					else if (node.getBalance() == 0)
						node.setBalance(1);
					else {
						leftRotations(node);
						b = false;
					}
				}
			}
		}
	}
	
	public void rightRotations(AVLNode node) {
		if (node.getLeft().balance == -1 ) {
			rightRotation(node);
		}
		else 
			doubleRightRotation(node);
		
	}
	
	public void leftRotations(AVLNode node) {
		if (node.getRight().balance == 1)
			leftRotation(node);
		else
			doubleLeftRotation(node);
	}
	
	public void rightRotation(AVLNode node) {
		AVLNode left = node.getLeft();
		super.rightRotation(node, left);
		node.setBalance(0);
		left.setBalance(0);
	}
	
	public void doubleRightRotation(AVLNode node) {
		AVLNode left = node.getLeft(), right = left.getRight();
		super.doubleRightRotation(node, left, right);
		if (right.getBalance() == -1)
			node.setBalance(1);
		else
			node.setBalance(0);
		if (right.getBalance() == 1)
			left.setBalance(-1);
		else
			left.setBalance(0);
		right.setBalance(0);
	}
	
	public void leftRotation(AVLNode node) {
		AVLNode right = node.getRight();
		super.leftRotation(node, right);
		node.setBalance(0);
		right.setBalance(0);
	}
	
	public void doubleLeftRotation(AVLNode node) {
		AVLNode right = node.getRight(), left = right.getLeft();
		super.doubleLeftRotation(node, right, left);
		if (left.getBalance() == 1)
			node.setBalance(-1);
		else
			node.setBalance(0);
		if (left.getBalance() == -1)
			right.setBalance(1);
		else
			right.setBalance(0);
		left.setBalance(0);
	}
}
