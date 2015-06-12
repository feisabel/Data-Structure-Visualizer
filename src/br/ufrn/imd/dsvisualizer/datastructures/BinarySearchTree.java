package br.ufrn.imd.dsvisualizer.datastructures;

import br.ufrn.imd.dsvisualizer.gui.Drawer;

/**
 * Implements a binary search tree using Node.
 * @author Ana Caroline, Fernanda, João Pedro e Leonardo. 
 * @version 09.06.2015
 */
public class BinarySearchTree extends AbstractTree
{
	/**
	 * Generated serial ID.
	 */
	private static final long serialVersionUID = -7455650965315566127L;
	private BSTNode head;
	protected BSTNode action;
    /**
     * Constructor for class BinarySearchTree with no parameters.
     */
    public BinarySearchTree()
    {
    	head = new BSTNode();
        drawer = new BSTDrawer();
    }

    /**
     * Gets the root of the tree.
     * @return the root of the tree
     */
    protected BSTNode root()
    {
    	return head.getLeft();
    }
    
    /**
     * Sets the root of the tree to the given node.
     * @param  node  the node to which the root will be set
     */
    protected void root(BSTNode node)
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
            if (q.getLeft() == null && q.getRight() == null) // if q is leaf
            	remove(q, null);  // delete it
            else if (q.getLeft() != null && q.getRight() == null) // if q has one child, at the left
                remove(q, q.getLeft());  // delete child
            else if (q.getLeft() == null && q.getRight() != null) // if q has one child, at the right
                remove(q, q.getRight());  // delete child
            else // if q has two children
                replace(q, max(q.getLeft()));  // value swaps q with it
        }
        return q;
    }
    
    /**
     * Replaces one node by another.
     * @param  node1  node to be replace
     * @param  node2  node that will replace
     */
    protected void replace(BSTNode node1, BSTNode node2)
    {
    	if (root() == node1)
    		root(node2);
        if (node1 != null && node2 != null) {
            BSTNode parent1 = node1.getParent(), parent2 = node2.getParent(), left = node2.getLeft();
            node2.setParent(parent1);
            if (parent1 != null) {
            	if (parent1.getLeft() == node1)
            		parent1.setLeft(node2);
            	else
            		parent1.setRight(node2);
            }
            if (node1.getLeft() != node2)
            	node2.setLeft(node1.getLeft());
            node2.setRight(node1.getRight());
            if (node2.getLeft() != null)
            	node2.getLeft().setParent(node2);
            if (node2.getRight() != null)
            	node2.getRight().setParent(node2);
            if (parent2 != node1) {
            	parent2.setRight(left);
            	if (left != null)
            		left.setParent(parent2);
            }
        }
    }
    
    /**
     * Removes the given node. Must have a parent.
     * @param  node  the node to be removed
     */
    protected void remove(BSTNode node, BSTNode son)
    {
    	if (root() == node)
    		root(son);
        if (node != null) {
        	BSTNode parent = node.getParent();
            if (parent != null) {
                if (parent.getLeft() == node)
                    parent.setLeft(son);
                else
                    parent.setRight(son);
            }
            if (son != null)
            	son.setParent(parent);
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
     * @param  insert  if true inserts new node, otherwise the method just to a search
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
    protected BSTNode max(BSTNode r)
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
	
	/**
	 * Class BSTDrawer contains the methods to draw the structure.
	 * @author Ana Caroline
	 */
    protected class BSTDrawer extends Drawer {
		/**
		 * Default serial version.
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * Method to draw the structure.
		 */
    	public void draw(){
			int x = DEFAULT_BG_SIZE.width/2;
			int y = 10;
			if (root() != null)
				preOrderCell(root(), x, y, root().getColor());
			jgraph.getGraphLayoutCache().insert(cells.values().toArray());
		}
    	
    	/**
    	 * Method to help the draw process. Using pre-order access.
    	 * @param root  node to be inserted
    	 * @param x     node's x-axis position
    	 * @param y     node's y-axis position
    	 * @param col   node's color
    	 */
		void preOrderCell(BSTNode root, int x, int y, java.awt.Color col){
			if(root != null) {
				createMyVertex(root.getKey(), x, y, col);
				if(root.getLeft() != null){
					preOrderCell(root.getLeft(), (int) (x - getPreferredSize().width/Math.scalb(1, 1 + root.nodeLevel(root()))),
							y + deltaY, root.getLeft().getColor());
					insertEdge(getDefaultPort((cells.get(root.getKey()))),
							getDefaultPort(cells.get(root.getLeft().getKey())));	
				}
				if(root.getRight() != null){
					preOrderCell(root.getRight(), (int)(x + getPreferredSize().width/Math.scalb(1, 1 + root.nodeLevel(root()))), 
							y + deltaY, root.getRight().getColor());
					insertEdge(getDefaultPort((cells.get(root.getKey()))),
							getDefaultPort(cells.get(root.getRight().getKey())));
				}
			}
		} 
    }
}


