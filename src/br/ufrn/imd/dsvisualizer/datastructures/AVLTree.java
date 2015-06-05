package br.ufrn.imd.dsvisualizer.datastructures;

import java.awt.Color;
import java.util.HashMap;

import org.jgraph.graph.DefaultGraphCell;

import br.ufrn.imd.dsvisualizer.datastructures.BinarySearchTree.BSTDrawer;
import br.ufrn.imd.dsvisualizer.gui.Drawer;

public class AVLTree extends BinarySearchTree {
	
	private AVLNode root;
    
    /**
     * Constructor for class Tree
     */
    public AVLTree()
    {
        root = null;
        drawer = new AVLTreeDrawer();
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
		left.setParent(node.getParent());
		left.getParent().setLeft(left);
		node.setLeft(left.getRight());
		node.getLeft().setParent(node);
		left.setRight(node);
		node.setParent(left);
		node.setBalance(0);
		left.setBalance(0);
	}
	
	public void doubleRightRotation(AVLNode node) {
		AVLNode left = node.getLeft(), right = left.getRight();
		right.setParent(node.getParent());
		right.getParent().setLeft(right);
		left.setRight(right.getLeft());
		left.getRight().setParent(left);
		node.setLeft(right.getRight());
		node.getLeft().setParent(node);
		right.setLeft(left);
		left.setParent(right);
		right.setRight(node);
		node.setParent(right);
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
		right.setParent(node.getParent());
		right.getParent().setRight(right);
		node.setRight(right.getLeft());
		node.getRight().setParent(node);
		right.setLeft(node);
		node.setParent(right);
		node.setBalance(0);
		right.setBalance(0);
	}
	
	public void doubleLeftRotation(AVLNode node) {
		AVLNode right = node.getRight(), left = right.getLeft();
		left.setParent(node.getParent());
		left.getParent().setRight(left);
		right.setLeft(left.getRight());
		right.getLeft().setParent(right);
		node.setRight(left.getLeft());
		node.getRight().setParent(node);
		left.setRight(right);
		right.setParent(left);
		right.setLeft(node);
		node.setParent(left);
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

	class AVLTreeDrawer extends Drawer{
		public void draw(){
			int x = DEFAULT_SIZE.width/2;
			int y = 10;
			HashMap<Integer,DefaultGraphCell> cells = new HashMap<Integer, DefaultGraphCell>();
			preOrderCell(cells, root(), x, y, Color.blue);
			
			jgraph.getGraphLayoutCache().insert(cells.values().toArray());
		}
	
		void preOrderCell(HashMap<Integer, DefaultGraphCell> c, AVLNode root, int x, int y, Color col){
			if(root != null){
				createMyVertex(c, root.getKey(), x, y, col);
				if(root.getLeft() != null){
					preOrderCell(c, root.getLeft(), (int) (x - DEFAULT_SIZE.width/Math.scalb(1., root.nodeLevel())),
							y + deltaY, Color.blue);
					insertEdge(getDefaultPort((c.get(root.getKey())), model),
							getDefaultPort(c.get(root.getLeft().getKey()), model));	
				}
				if(root.getRight() != null){
					preOrderCell(c, root.getRight(), (int)(x + DEFAULT_SIZE.width/Math.scalb(1., root.nodeLevel())), 
							y + deltaY, Color.blue);
					insertEdge(getDefaultPort((c.get(root.getKey())), model),
							getDefaultPort(c.get(root.getRight().getKey()), model));
				}
			}
		} 
    }
	
}

