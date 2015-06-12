package br.ufrn.imd.dsvisualizer.datastructures;

/**
 * Class AVL Tree.
 * @author Fernanda Isabel
 */

public class AVLTree extends BalancedTree {
	
	/**
	 * Generated serial ID.
	 */
	private static final long serialVersionUID = 2759166979168262260L;
	private AVLNode root;
	
    /**
     * Constructor for class AVLTree with no parameters. 
     */
    public AVLTree()
    {
        root = null;
    }

    /**
     * Access method to the root.
     * @return the root
     */
    protected AVLNode root() {
    	return root;
    }
    
    /**
     * Modifier method to the root.
     * @param new root.
     */
    protected void root(BSTNode root) {
    	this.root = (AVLNode)root;
    }
    
    /**
     * Inserts a new node if does not exists one with the same key.
     * @param key new node's key
     */
	public void insert(int key) {
		privateInsert(key, root(), null, new Ref<Boolean>(true));
	}
	
	/**
	 * Inserts a new node if one with the same key does not exist.
	 * @param key new node's key
	 * @param node current node
	 * @param dad current's father node
	 * @param b indicates if the node is already inserted
	 */
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
			if (key == node.getKey()) {
				b.set(false);
				return;
			}
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
						rightRotationsInsertion(node);
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
						leftRotationsInsertion(node);
						b.set(false);
					}
				}
			}
		}
	}
	

	/**
	 * Deletes a node given a key.
	 * @param key node's key
	 * @return deleted node
	 */
	public AVLNode delete(int key) {
		AVLNode node = (AVLNode)search(key);
        if (node != null) {  
        	if (node.getLeft() != null && node.getRight() != null) {
        		AVLNode left = (AVLNode)max(node.getLeft()), leftParent = left.getParent();
        		replace(node, left);
        		if (leftParent == node) {
        			left.setBalance(node.getBalance());
        			adjustBalance(left, left.getKey() - 1);
        		}
        		else {
        			left.setBalance(node.getBalance());
        			adjustBalance(leftParent, left.getKey());
        		}	
        	}
        	else {
	            if (node.getLeft() == null && node.getRight() == null)
	            	remove(node, null);
	            else if (node.getLeft() != null && node.getRight() == null) 
	                remove(node, node.getLeft());  
	            else
	                remove(node, node.getRight()); 
	            adjustBalance(node.getParent(), node.getKey());
        	}
        }
        return node;
	}
	
	/**
	 * Adjusts the balance.
	 * @param node current node
	 * @param key key
	 */
	private void adjustBalance(AVLNode node, int key) {
		if (node != null) {
			AVLNode parent = node.getParent();
			if (key < node.getKey())
				node.setBalance(node.getBalance() + 1);
			else
				node.setBalance(node.getBalance() - 1);
			if (node.getBalance() != 1 && node.getBalance() != -1) {
				if (node.getBalance() == 2)
					leftRotationsRemoval(node);
				else if (node.getBalance() == -2) 
					rightRotationsRemoval(node);
				adjustBalance(parent, node.getKey());
			}
		}
	}
	
	/**
	 * Does the appropriate right rotation to correct the tree's balance after an insertion.
	 * @param node node will be balanced
	 */
	private void rightRotationsInsertion(AVLNode node) {
		AVLNode left = node.getLeft(), right;
		if (left.getBalance() == -1 ) {
			rightRotation(node, left);
			node.setBalance(0);
			left.setBalance(0);
		}
		else {
			right = left.getRight();
			doubleRightRotation(node, left, right);
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
	}
	
	/**
	 * Does the appropriate left rotation to correct the tree's balance after an insertion.
	 * @param node node will be balanced
	 */
	private void leftRotationsInsertion(AVLNode node) {
		AVLNode right = node.getRight(), left;
		if (right.getBalance() == 1) {
			leftRotation(node, right);
			node.setBalance(0);
			right.setBalance(0);
		}
		else {
			left = right.getLeft();
			doubleLeftRotation(node, right, left);
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
	
	/**
	 * Does the appropriate right rotation to correct the tree's balance after a removal.
	 * @param node node will be balanced
	 */
	private void rightRotationsRemoval(AVLNode node) {
		AVLNode left = node.getLeft(), right;
		if (left.getBalance() == -1 || left.getBalance() == 0) {
			rightRotation(node, left);
			if (left.getBalance() == -1) {
				node.setBalance(0);
				left.setBalance(0);
			}
			else {
				node.setBalance(-1);
				left.setBalance(1);
			}
		}
		else {
			right = left.getRight();
			doubleRightRotation(node, left, right);
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
	}
	
	/**
	 * Does the appropriate left rotation to correct the tree's balance after a removal.
	 * @param node node will be balanced.
	 */
	private void leftRotationsRemoval(AVLNode node) {
		AVLNode right = node.getRight(), left;
		if (right.getBalance() == 1 || right.getBalance() == 0) {
			leftRotation(node, right);
			if (right.getBalance() == 1) {
				node.setBalance(0);
				right.setBalance(0);
			}
			else {
				node.setBalance(1);
				right.setBalance(-1);
			}
		}
		else {
			left = right.getLeft();
			doubleLeftRotation(node, right, left);
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
}

