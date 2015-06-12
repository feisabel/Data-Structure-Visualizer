package br.ufrn.imd.dsvisualizer.datastructures;

public abstract class BalancedTree extends BinarySearchTree {
	
	/**
     * Does the right rotation to correct the tree's balance.
     * @param node to be changed position
     * @param left to be changed position
     */
    protected void rightRotation(BSTNode node, BSTNode left) {
		left.setParent(node.getParent());
		if (left.getParent() != null) {
			if (node.getKey() < left.getParent().getKey())
				left.getParent().setLeft(left);
			else
				left.getParent().setRight(left);
		}
		node.setLeft(left.getRight());
		if (node.getLeft() != null)
			node.getLeft().setParent(node);
		left.setRight(node);
		node.setParent(left);
		if (node == root())
			root(left);
	}
		
    /**
     * Does the double right rotation to correct the tree's balance.
     * @param node to be changed position
     * @param left to be changed position
     * @param right to be changed position
     */
	protected void doubleRightRotation(BSTNode node, BSTNode left, BSTNode right) {
		right.setParent(node.getParent());
		if (right.getParent() != null) {
			if (node.getKey() < right.getParent().getKey())
				right.getParent().setLeft(right);
			else
				right.getParent().setRight(right);
		}
		left.setRight(right.getLeft());
		if (left.getRight() != null)
			left.getRight().setParent(left);
		node.setLeft(right.getRight());
		if (node.getLeft() != null)
			node.getLeft().setParent(node);
		right.setLeft(left);
		left.setParent(right);
		right.setRight(node);
		node.setParent(right);
		if (node == root())
			root(right);
	}
	
	/**
	 * Does the double left rotation to correct the tree's balance.
	 * @param node to be balanced
	 * @param right to be balanced
	 */
	protected void leftRotation(BSTNode node, BSTNode right) {
		right.setParent(node.getParent());
		if (right.getParent() != null) {
			if (node.getKey() < right.getParent().getKey())
				right.getParent().setLeft(right);
			else
				right.getParent().setRight(right);
		}
		node.setRight(right.getLeft());
		if (node.getRight() != null)
			node.getRight().setParent(node);
		right.setLeft(node);
		node.setParent(right);
		if (node == root())
			root(right);
	}
	
	/**
	 * Does double left rotation to correct the tree's balance.
	 * @param node to be changed location
	 * @param right to be changed location
	 * @param left to bem changed location
	 */
	protected void doubleLeftRotation(BSTNode node, BSTNode right, BSTNode left) {
		left.setParent(node.getParent());
		if (left.getParent() != null) {
			if (node.getKey() < left.getParent().getKey())
				left.getParent().setLeft(left);
			else
				left.getParent().setRight(left);
		}
		right.setLeft(left.getRight());
		if (right.getLeft() != null)
			right.getLeft().setParent(right);
		node.setRight(left.getLeft());
		if (node.getRight() != null)
			node.getRight().setParent(node);
		left.setRight(right);
		right.setParent(left);
		left.setLeft(node);
		node.setParent(left);
		if (node == root())
			root(left);
	}
}
