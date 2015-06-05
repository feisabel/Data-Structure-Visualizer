package br.ufrn.imd.dsvisualizer;

import java.awt.Color;
import java.util.HashMap;

import javax.swing.JPanel;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultCellViewFactory;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphLayoutCache;

/**
 * Implements a binary search tree using Node.
 * @author Ana Caroline, Fernanda, João Pedro e Leonardo. 
 * @version 27.04.2015
 */
public class BinarySearchTree extends Tree
{
	private BSTNode head;
	
    /**
     * Constructor for class Tree
     */
    public BinarySearchTree()
    {
        head = new BSTNode();
        drawer = new BSTDrawer();
    }

    /**
     * Gets the root of the tree.
     * @return  the root of the tree
     */
    private BSTNode root()
    {
        return head.getLeft();
    }
    
    /**
     * Sets the root of the tree to the given node.
     * @param  node  the node to which the root will be set
     */
    private void root(BSTNode node)
    {
        head.setLeft(node);
    }
    
    /**
     * Check if the tree is empty.
     * @return  true if the tree is empty, false otherwise
     */
    private boolean isEmpty()
    {
        return root() == null;
    }
    
    /**
     * Inserts a node to the tree.
     * @param key key of the note to be inserted
     */
    public void insert(int key)
    {
        privateSearch(root(), key, true);
    }
    
    /**
     * Deletes node with the given key.
     * @param  key  key of the node to be removed
     * @return  removed node
     */
    public BSTNode delete(int key)
    {
        BSTNode q = search(key);
        
        if (q != null) {  // if is in the tree
            if (q.getLeft() == null && q.getRight() == null) {  // if q is leaf
                remove(q);  // delete it
            }
            else if (q.getLeft() != null && q.getRight() == null) {  // if q has one child, at the left
                swap(q, q.getLeft());  // value swap with child
                remove(q.getLeft());  // delete child
            }
            else if (q.getLeft() == null && q.getRight() != null) {  // if q has one child, at the right
                swap(q, q.getRight());  // value swap with child
                remove(q.getRight());  // delete child
            }
            else {  // if q has two children
                BSTNode m = max(q.getLeft());  // gets the immediate predecessor
                swap(q, m);  // value swaps q with it
                remove(m);  // delete m
            }
        }
        return q;
    }
    
    /**
     * Swaps contents of the nodes.
     * @param  node1  node to be swapped
     * @param  node2  node to be swapped
     */
    private void swap(BSTNode node1, BSTNode node2)
    {
        if (node1 != null && node2 != null) {
            int p = node1.getKey();
            node1.setKey(node2.getKey());
            node2.setKey(p);
        }
    }
    
    /**
     * Removes the given node. Must have a parent.
     * @param  node  the node to be removed
     */
    private void remove(BSTNode node)
    {
        if (node != null) {
        	BSTNode parent = (BSTNode) node.getParent();
            if (parent != null) {
                if (parent.getLeft() == node) {
                    parent.setLeft(null);
                }
                else {
                    parent.setRight(null);
                }
            }
        }
    }
    
    /**
     * Searches depth-first for the node with the given key.
     * @param  key  key to be searched
     * @return  node with key 'key', or null if not found
     */
    public BSTNode search(int key)
    {
        return privateSearch(root(), key, false);
    }
    
    /**
     * Helper method for search.
     * @param  node  root of the tree to search
     * @param  key  key to search
     * @param  insert  if creates a new node when not found
     * @return  node with key 'key'
     */
    private BSTNode privateSearch(BSTNode node, int key, boolean insert)
    {
        if(node == null) {
            if (isEmpty() && insert) {
                root(new BSTNode(head, null, null, key));
                return root();
            }
            return null;
        } else if (node.getKey() == key) {
            return node;
        } else if(node.getKey() > key) {
            if (node.getLeft() == null && insert) {
                node.setLeft(new BSTNode(node, null, null, key));
                return node.getLeft();
            }
            return privateSearch(node.getLeft(), key, insert);
        } else {
            if (node.getRight() == null && insert) {
                node.setRight(new BSTNode(node, null, null, key));
                return node.getRight();
            }
            return privateSearch(node.getRight(), key, insert);
        }
    }
    
    /**
     * Gets the node with the max key in the given subtree.
     * @param  r  the root of the subtree
     * @return  the node with the max key in the subtree r
     */
    private BSTNode max(BSTNode r)
    {
        if (r != null) {
            if (r.getRight() == null) {
                return r;
            }
            else {
                return max(r.getRight());
            }
        }
        return null;
    }

    class BSTDrawer extends Drawer {
		public void draw(){
			int x = DEFAULT_SIZE.width/2;
			int y = 10;
			HashMap<Integer,DefaultGraphCell> cells = new HashMap<Integer, DefaultGraphCell>();
			preOrderCell(cells, root(), x, y, Color.blue);
			
			jgraph.getGraphLayoutCache().insert(cells.values().toArray());
		}
	
		void preOrderCell(HashMap<Integer, DefaultGraphCell> c, BSTNode root, int x, int y, Color col){
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


