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

