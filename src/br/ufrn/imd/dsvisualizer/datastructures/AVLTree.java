package br.ufrn.imd.dsvisualizer.datastructures;

public class AVLTree extends BinarySearchTree {
	
	private AVLNode root;
    
    /**
     * Constructor for class Tree
     */
    public AVLTree()
    {
        root = null;
 //       drawer = new AVLTreeDrawer();
    }
    
    private AVLNode root() {
    	return root;
    }

	public void insert(int key) {
		privateInsert(key, root, null, new Ref<Boolean>(true));
	}
	
	private void privateInsert(int key, AVLNode node, AVLNode dad, Ref<Boolean> b) {
		if (node == null) {
			AVLNode newNode = new AVLNode(dad, null, null, key, 0);
			if (root == null)
				root = newNode;
			else if (key < dad.getKey())
				dad.setLeft(newNode);
			else
				dad.setRight(newNode);
			b.set(true);
		}
		else {
			if (key == node.getKey())
				return;
			else if (key < node.getKey()) {
				privateInsert(key, node.getLeft(), node, b);
				if (b.get()) {
					if (node.getBalance() == 1) {
						node.setBalance(0);
						b.set(false);
					}
					else if (node.getBalance() == 0)
						node.setBalance(-1);
					else {
						rightRotations(node);
						b.set(false);
					}
				}
			}
			else {
				privateInsert(key, node.getRight(), node, b);
				if (b.get()) {
					if (node.getBalance() == -1) {
						node.setBalance(0);
						b.set(false);
					}
					else if (node.getBalance() == 0)
						node.setBalance(1);
					else {
						leftRotations(node);
						b.set(false);
					}
				}
			}
		}
	}
	
	public void rightRotations(AVLNode node) {
		if (node.getLeft().getBalance() == -1 ) {
			rightRotation(node);
		}
		else 
			doubleRightRotation(node);
		
	}
	
	public void leftRotations(AVLNode node) {
		if (node.getRight().getBalance() == 1)
			leftRotation(node);
		else
			doubleLeftRotation(node);
	}
	
	public void rightRotation(AVLNode node) {
		AVLNode left = node.getLeft();
		super.rightRotation(node, left);
		node.setBalance(0);
		left.setBalance(0);
		if (root == node)
			root = left;
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
		if (root == node)
			root = right;
	}
	
	public void leftRotation(AVLNode node) {
		AVLNode right = node.getRight();
		super.leftRotation(node, right);
		node.setBalance(0);
		right.setBalance(0);
		if (root == node)
			root = right;
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
		if (root == node)
			root = left;
	}	
}

