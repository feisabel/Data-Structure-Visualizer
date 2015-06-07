package br.ufrn.imd.dsvisualizer.datastructures;

import java.awt.Color;

public class RBTree extends BinarySearchTree {
	
	private RBNode root;
	
	public RBTree() {
		root = null;
	}
	
	protected RBNode root() {
		return root;
	}
	
	public void insert(int key) {
		privateInsert(key, root, null, new Ref<Integer>(1));
	}
	
	private RBNode privateInsert(int key, RBNode node, RBNode dad, Ref<Integer> b) {
		if (node == null) {
			node = new RBNode(dad, null, null, key);
			if (root == null) {
				root = node;
				node.setColor(Color.BLACK);
			}
			else {
				if (key < dad.getKey())
					dad.setLeft(node);
				else
					dad.setRight(node);
			}
			return node;
		}
		else {
			if (key != node.getKey()) {
				dad = node;
				if (key < node.getKey())
					node = node.getLeft();
				else
					node = node.getRight();
				node = privateInsert(key, node, dad, b);
				if (dad.getColor() == Color.BLACK)
					b.set(2);
				else if (b.get() == 1)
					adjustColors(node, dad, b);
				else if (b.get() == 0)
					b.set(1);
			}
			else
				b.set(2);
			return dad;
		}
	}
	
	private void adjustColors(RBNode node, RBNode dad, Ref<Integer> b) {
		RBNode grandad = dad.getParent(), uncle;
		if (grandad.getLeft() == dad)
			uncle = grandad.getRight();
		else
			uncle = grandad.getLeft();
		if (uncle != null && uncle.getColor() == Color.RED) {
			b.set(0);
			uncle.setColor(Color.BLACK);
			dad.setColor(Color.BLACK);
			grandad.setColor(Color.RED);
		}
		else {
			rotate(node, dad, grandad);
			b.set(2);
		}
		if (root.getColor() == Color.RED)
			root.setColor(Color.BLACK);
	}
	
	private void rotate(RBNode node, RBNode dad, RBNode grandad) {
		if (dad == grandad.getLeft()) {
			if (node == dad.getLeft()) {
				rightRotation(grandad, dad);
				if (root == grandad)
					root = dad;
				dad.setColor(Color.BLACK);
			}
			else {
				doubleRightRotation(grandad, dad, node);
				if (root == grandad)
					root = node;
				node.setColor(Color.BLACK);
			}
			grandad.setColor(Color.RED);
		}
		else {
			if (node == dad.getRight()) {
				leftRotation(grandad, dad);
				if (root == grandad)
					root = dad;
				dad.setColor(Color.BLACK);
			}
			else {
				doubleLeftRotation(grandad, dad, node);
				if (root == grandad)
					root = node;
				node.setColor(Color.BLACK);
			}
			grandad.setColor(Color.RED);
		}
	}
}
