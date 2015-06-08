package br.ufrn.imd.dsvisualizer.datastructures;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultPort;
import org.jgraph.graph.GraphConstants;

import br.ufrn.imd.dsvisualizer.gui.Drawer;

public class RBTree extends BinarySearchTree {
	
	private RBNode root;
	
	public RBTree() {
		root = null;
		drawer = new RBTreeDrawer();
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
	/**
	 * Class to draw the RBTree.
	 * @author Ana Caroline 
	 *
	 */
	class RBTreeDrawer extends Drawer{
	
		/**
		 * Default serial version.
		 */
		private static final long serialVersionUID = 1L;
		private List<DefaultGraphCell> nullnodes = new LinkedList<DefaultGraphCell>();
		
		/**
		 * Special method to draw a null node, according this structure type. 
		 * @param x position x
		 * @param y position y
		 * @param dad vertex dad
		 */
		private void createNullVertex(int x, int y, DefaultGraphCell dad){
			DefaultGraphCell v = new DefaultGraphCell("");
			nullnodes.add(v);
			DefaultPort port = new DefaultPort();
			v.add(port);
			port.setParent(v);
			GraphConstants.setBounds(v.getAttributes(), new
					 Rectangle2D.Double(x,y,15,15));
			GraphConstants.setGradientColor(v.getAttributes(), Color.black);
			GraphConstants.setOpaque(v.getAttributes(), true);
			insertEdge(getDefaultPort(dad, model), 
					getDefaultPort(v, model));
		}

		public void draw(){
			int x = DEFAULT_SIZE.width/2;
			int y = 10;
			HashMap<Integer,DefaultGraphCell> cells = new HashMap<Integer, DefaultGraphCell>();
			preOrderCell(cells, root(), x, y, root().getColor());
			jgraph.getGraphLayoutCache().insert(cells.values().toArray());
			jgraph.getGraphLayoutCache().insert(nullnodes.toArray());
		}
    	
    	/**
    	 * Method to help the draw process. Using pre order access.
    	 * @param c used to mapping the jgraph cells
    	 * @param root node to be inserted
    	 * @param x node's position axis x
    	 * @param y node's position axis y
    	 * @param col node's color
    	 */
		void preOrderCell(HashMap<Integer, DefaultGraphCell> c, BSTNode root, int x, int y, java.awt.Color col){
			if(root != null){
				createMyVertex(c, root.getKey(), x, y, col);
				if(root.getLeft() != null){
					preOrderCell(c, root.getLeft(), (int) (x - DEFAULT_SIZE.width/Math.scalb(1., 1 + root.nodeLevel(root()))),
							y + deltaY, root.getLeft().getColor());
					insertEdge(getDefaultPort((c.get(root.getKey())), model),
							getDefaultPort(c.get(root.getLeft().getKey()), model));	
				}else{
					createNullVertex((int) (x - DEFAULT_SIZE.width/Math.scalb(1., 1 + root.nodeLevel(root()))), y + deltaY, 
							c.get(root.getKey()) );
				}
				if(root.getRight() != null){
					preOrderCell(c, root.getRight(), (int)(x + DEFAULT_SIZE.width/Math.scalb(1., 1 + root.nodeLevel(root()))), 
							y + deltaY, root.getRight().getColor());
					insertEdge(getDefaultPort((c.get(root.getKey())), model),
							getDefaultPort(c.get(root.getRight().getKey()), model));
				}else{
					createNullVertex((int) (x + DEFAULT_SIZE.width/Math.scalb(1., 1 + root.nodeLevel(root()))), y + deltaY, 
							c.get(root.getKey()) );
				}
			}
		} 
	}
}
