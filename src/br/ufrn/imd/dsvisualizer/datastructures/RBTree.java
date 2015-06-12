package br.ufrn.imd.dsvisualizer.datastructures;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.List;

import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultPort;
import org.jgraph.graph.GraphConstants;

import br.ufrn.imd.dsvisualizer.gui.Drawer;
/**
 * Class Red-Black Tree.
 * @author Fernanda Isabel
 */
public class RBTree extends BinarySearchTree {
	private static final long serialVersionUID = -2112618915954107608L;
	
	private RBNode root;
	
	/**
	 * Constructor with no parameters.
	 */
	public RBTree() {
		root = null;
		drawer = new RBTreeDrawer();
	}
	
	/**
	 * Access method to the root.		
	 * @return root
	 */
	protected RBNode root() {
		return root;
	}
	
	/**
	 * Modifier method to the root.
	 * @param the new root
	 */
	protected void root(BSTNode root) {
		this.root = (RBNode)root;
	}
	
	/**
	 * Inserts a new node.
	 * @param key new node's key
	 */
	public void insert(int key) {
		privateInsert(key, root, null, new Ref<Integer>(1));
	}
	
	/**
	 * Helper method to insert a new node.
	 * @param key new node's key
	 * @param node current node
	 * @param dad current node's dad
	 * @param b indicates current case 
	 * @return node used to recursion
	 */
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
					adjustColorsInsertion(node, dad, b);
				else if (b.get() == 0)
					b.set(1);
			}
			else
				b.set(2);
			return dad;
		}
	}
	/**
	 * Does adjust colors nodes.
	 * @param node current node
	 * @param dad current node's dad
	 * @param b indicates if needs continue or no
	 */
	private void adjustColorsInsertion(RBNode node, RBNode dad, Ref<Integer> b) {
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
	
	/**
	 * Does the rotate.
	 * @param node current node
	 * @param dad current node's dad
	 * @param grandad current node's grandad
	 */
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
	 * Deletes a node given a key.
	 * @param key node's key
	 * @return deleted node
	 */
	public RBNode delete(int key) {
        RBNode node = (RBNode)search(key); 
        if (node != null) { 
        	if (node.getLeft() != null && node.getRight() != null) {
        		RBNode left = (RBNode)max(node.getLeft()), leftParent = left.getParent();
        		replace(node, left);
        		if (left.getColor() == Color.BLACK) {
        			if (node.getColor() == Color.RED)
        				left.setColor(Color.RED);
        			if (leftParent != node) {
        				if (leftParent.getRight() != null)
        					leftParent.getRight().setColor(Color.BLACK);
        				else
        					adjustColorsRemoval(leftParent, false);
        			}
        			else {
        				if (left.getLeft() != null)
        					left.getLeft().setColor(Color.BLACK);
        				else
        					adjustColorsRemoval(left, true);
        			}
        		}
        		else if (node.getColor() == Color.BLACK)
        			left.setColor(Color.BLACK);
        	}
        	else if (node.getLeft() == null && node.getRight() == null) {
            	remove(node, null); 
            	if (root != null && node.getColor() == Color.BLACK)
            		adjustColorsRemoval(node.getParent(), node.getKey() < node.getParent().getKey());
            }
        	else if (node.getLeft() != null && node.getRight() == null) {
        		remove(node, node.getLeft());
        		node.getLeft().setColor(Color.BLACK);
        	}
	        else {
	        	remove(node, node.getRight()); 
	        	node.getRight().setColor(Color.BLACK);
	        }
        }
        return node;
    }
	
	/**
	 * Adjusts the colors.
	 * @param node current node
	 * @param b boolean that indicates if the removed node was the left son of node
	 */
	private void adjustColorsRemoval(RBNode node, boolean b) {
		if (node != null) {
			RBNode son;
			if (b)
				son = node.getRight();
			else
				son = node.getLeft();
			if (son.getColor() == Color.RED) {
				son.setColor(Color.BLACK);
				node.setColor(Color.RED);
				if (b)
					leftRotation(node, son);
				else
					rightRotation(node, son);
				if (node == root)
					root = son;
				adjustColorsRemoval(node, b);
			}
			else {
				if ((son.getLeft() != null && son.getRight() != null && son.getLeft().getColor() == Color.BLACK && son.getRight().getColor() == Color.BLACK) || (son.getRight() != null && son.getRight().getColor() == Color.BLACK && son.getLeft() == null) || (son.getLeft() != null && son.getLeft().getColor() == Color.BLACK && son.getRight() == null) || (son.getLeft() == null && son.getRight() == null)) {
						son.setColor(Color.RED);
						if (node.getColor() == Color.RED)
							node.setColor(Color.BLACK);
						else if (node != root)
							adjustColorsRemoval(node.getParent(), node.getKey() < node.getParent().getKey());
				}
				else if (b) {
					if (son.getRight() == null || son.getRight().getColor() == Color.BLACK) {
						son.setColor(Color.RED);
						son.getLeft().setColor(Color.BLACK);
						rightRotation(son, son.getLeft());
						adjustColorsRemoval(node, true);
					}
					else if (son.getRight().getColor() == Color.RED) {
						son.setColor(node.getColor());
						node.setColor(Color.BLACK);
						son.getRight().setColor(Color.BLACK);
						leftRotation(node, son);
						if (node == root)
							root = son;
					}
				}
				else {
					if (son.getLeft() == null || son.getLeft().getColor() == Color.BLACK) {
						son.setColor(Color.RED);
						son.getRight().setColor(Color.BLACK);
						leftRotation(son, son.getRight());
						adjustColorsRemoval(node, false);
					}
					else if (son.getLeft().getColor() == Color.RED) {
						son.setColor(node.getColor());
						node.setColor(Color.BLACK);
						son.getLeft().setColor(Color.BLACK);
						rightRotation(node, son);
						if (node == root)
							root = son;
					}
				}
			}
		}
	}
	
	/**
	 * Class to draw the RBTree.
	 * @author Ana Caroline 
	 *
	 */
	class RBTreeDrawer extends Drawer{
		/**
		 * Generated serial ID.
		 */	
		private static final long serialVersionUID = 7900579170125657322L;
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
			insertEdge(getDefaultPort(dad), getDefaultPort(v));
		}
		
		/**
		 * Starts the process to draw the structure.
		 */
		public void draw(){
			int x = getPreferredSize().width/2;
			int y = 10;
			if (root() != null)
				preOrderCell(root(), x, y, root().getColor());
			jgraph.getGraphLayoutCache().insert(cells.values().toArray());
			jgraph.getGraphLayoutCache().insert(nullnodes.toArray());
		}
    	
		/**
		 * Clears the graph screen.
		 */
		public void clear() {
			super.clear();
			jgraph.getGraphLayoutCache().remove(nullnodes.toArray());
			nullnodes = new LinkedList<DefaultGraphCell>();
		}
		
    	/**
    	 * Method to help the draw process. Using pre order access.
    	 * @param c used to mapping the jgraph cells
    	 * @param root node to be inserted
    	 * @param x node's position axis x
    	 * @param y node's position axis y
    	 * @param col node's color
    	 */
		void preOrderCell(BSTNode root, int x, int y, java.awt.Color col){
			if(root != null){
				createMyVertex(root.getKey(), x, y, col);
				if(root.getLeft() != null){
					preOrderCell(root.getLeft(), (int) (x - getPreferredSize().width/Math.scalb(1., 1 + root.nodeLevel(root()))),
							y + deltaY, root.getLeft().getColor());
					insertEdge(getDefaultPort(cells.get(root.getKey())),
							getDefaultPort(cells.get(root.getLeft().getKey())));	
				}else{
					createNullVertex((int) (x - getPreferredSize().width/Math.scalb(1., 1 + root.nodeLevel(root()))), y + deltaY, 
							cells.get(root.getKey()) );
				}
				if(root.getRight() != null){
					preOrderCell(root.getRight(), (int)(x + getPreferredSize().width/Math.scalb(1., 1 + root.nodeLevel(root()))), 
							y + deltaY, root.getRight().getColor());
					insertEdge(getDefaultPort((cells.get(root.getKey()))),
							getDefaultPort(cells.get(root.getRight().getKey())));
				}else{
					createNullVertex((int) (x + getPreferredSize().width/Math.scalb(1., 1 + root.nodeLevel(root()))), y + deltaY, 
							cells.get(root.getKey()) );
				}
			}
		} 
	}
}
