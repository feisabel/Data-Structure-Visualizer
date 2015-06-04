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
 * CAROL E JP ESTIVERAM AQUI
 * @author Ana Caroline, Fernanda, João Pedro e Leonardo. 
 * @version 27.04.2015
 */
public class BinarySearchTree extends DataStructure
{
	private Node head;

	// TODO Ajeitar métodos de inserção, remoção e busca para combinar com interface de DataStructure
	
    /**
     * Constructor for class Tree
     */
    public BinarySearchTree()
    {
        head = new Node();
        drawer = new BSTDrawer();
    }

    /**
     * Gets the root of the tree.
     * @return  the root of the tree
     */
    private Node root()
    {
        return head.getLeftKid();
    }
    
    /**
     * Sets the root of the tree to the given node.
     * @param  node  the node to which the root will be set
     */
    private void root(Node node)
    {
        head.setLeftKid(node);
    }
    
    /**
     * Check if the tree is empty.
     * @return  true if the tree is empty, false otherwise
     */
    public boolean isEmpty()
    {
        return root() == null;
    }
    
    /**
     * Inserts a node to the tree.
     */
    public boolean insert(int key) {
    	privateSearch(root(), key, true);
    	return true;
    }
    
    public boolean remove(int key) {
    	return removeHelper(key) != null;
    }
    
    public boolean search(int key) {
    	return searchHelper(key) != null;
    }
    
    /**
     * Removes node with the given key.
     * @param  key  key of the node to be removed
     * @return  removed node
     */
    public Node removeHelper(int key)
    {
        Node q = searchHelper(key);
        
        if (q != null) {  // if is in the tree
            if (q.getLeftKid() == null && q.getRightKid() == null) {  // if q is leaf
            	delete(q);  // delete it
            }
            else if (q.getLeftKid() != null && q.getRightKid() == null) {  // if q has one child, at the left
                swap(q, q.getLeftKid());  // value swap with child
                delete(q.getLeftKid());  // delete child 
            }
            else if (q.getLeftKid() == null && q.getRightKid() != null) {  // if q has one child, at the right
                swap(q, q.getRightKid());  // value swap with child
                delete(q.getRightKid());  // delete child
            }
            else {  // if q has two children
                Node m = max(q.getLeftKid());  // gets the immediate predecessor
                swap(q, m);  // value swaps q with it
                delete(m);  // delete m
            }
        }
       
        return q;
    }
    
    /**
     * Swaps contents of the nodes.
     * @param  node1  node to be swapped
     * @param  node2  node to be swapped
     */
    private void swap(Node node1, Node node2)
    {
        if (node1 != null && node2 != null) {
            int p = node1.getKey();
            node1.setKey(node2.getKey());
            node2.setKey(p);
        }
    }
    
    /**
     * Deletes the given node. Must have a parent.
     * @param  node  the node to be removed
     */
    private void delete(Node node)
    {
        if (node != null) {
            Node parent = node.getParent();
            if (parent != null) {
                if (parent.getLeftKid() == node) {
                    parent.setLeftKid(null);
                }
                else {
                    parent.setRightKid(null);
                }
            }
        }
    }
    
    /**
     * Searches depth-first for the node with the given key.
     * @param  key  key to be searched
     * @return  node with key 'key', or null if not found
     */
    public Node searchHelper(int key)
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
    private Node privateSearch(Node node, int key, boolean insert)
    {
        if(node == null) {
            if (isEmpty() && insert) {
                root(new Node(head, null, null, key));
                return root();
            }
            return null;
        } else if (node.getKey() == key) {
            return node;
        } else if(node.getKey() > key) {
            if (node.getLeftKid() == null && insert) {
                node.setLeftKid(new Node(node, null, null, key));
                return node.getLeftKid();
            }
            return privateSearch(node.getLeftKid(), key, insert);
        } else {
            if (node.getRightKid() == null && insert) {
                node.setRightKid(new Node(node, null, null, key));
                return node.getRightKid();
            }
            return privateSearch(node.getRightKid(), key, insert);
        }
    }
    
    /**
     * @return  the node with the min key in the tree
     */
    public Node min()
    {
        return min(root());
    }
    
    /**
     * Gets the node with the min key of the given subtree. 
     * @param  r  the root of the subtree
     * @return  the node with the min key in the tree r
     */
    public Node min(Node r)
    {
        if (r != null) {
            if (r.getLeftKid() == null) {
                return r;
            }
            else {
                return min(r.getLeftKid());
            }
        }
        return null;
    }
    
    /**
     * @return  the node with the max key in the tree
     */
    public Node max()
    {
        return max(root());
    }
    
    /**
     * Gets the node with the max key in the given subtree.
     * @param  r  the root of the subtree
     * @return  the node with the max key in the subtree r
     */
    public Node max(Node r)
    {
        if (r != null) {
            if (r.getRightKid() == null) {
                return r;
            }
            else {
                return max(r.getRightKid());
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
	
		void preOrderCell(HashMap<Integer, DefaultGraphCell> c, Node root, int x, int y, Color col){
			if(root != null){
				createMyVertex(c, root.getKey(), x, y, col);
				if(root.getLeftKid() != null){
					preOrderCell(c, root.getLeftKid(), (int) (x - DEFAULT_SIZE.width/Math.scalb(1., root.nodeLevel())), 
							y + deltaY, Color.blue);
					insertEdge(getDefaultPort((c.get(root.getKey())), model),
							getDefaultPort(c.get(root.getLeftKid().getKey()), model));	
				}
				if(root.getRightKid() != null){
					preOrderCell(c, root.getRightKid(), (int)(x + DEFAULT_SIZE.width/Math.scalb(1., root.nodeLevel())), 
							y + deltaY, Color.blue);
					insertEdge(getDefaultPort((c.get(root.getKey())), model),
							getDefaultPort(c.get(root.getRightKid().getKey()), model));
				}
			}
		}
    }
}


