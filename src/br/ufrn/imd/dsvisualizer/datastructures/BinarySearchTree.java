package br.ufrn.imd.dsvisualizer.datastructures;

import java.util.HashMap;

import org.jgraph.graph.DefaultGraphCell;

import br.ufrn.imd.dsvisualizer.gui.Drawer;

/**
 * Implements a binary search tree using Node.
 * @author Ana Caroline, Fernanda, João Pedro e Leonardo. 
 * @version 27.04.2015
 */
public class BinarySearchTree extends Tree
{
	private BSTNode head;
	
    /**
     * Constructor for class BInarySearchTree with no parameters.
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
    private void root(BSTNode node)
    {
        head.setLeft(node);
    }
    /**
     * Returns structure description.
     * @return description
     */
    public String getDescription(){
    	return "BST possui como característica principal o fato de cada node ter até dois filhos, " +
    			"os valores armazenados são organizados conforme a ordenação natural dos inteiros." +
    			"a busca é feita comparando o valor buscado com o valor de cada node, caso seja maior " +
    			"que o valor do node, chama-se o filho da direita, caso contrário o da direita." +
    			"A remoção é feita substituindo o node removido pelo node mais a direita da subárvore " +
    			" à direita." + " A complexidade das operações estão baseadas na altura da árvore, uma vez"
    			+ " que no máximo será acessados um caminho da raiz até o node mais distante, ou seja, altura"
    			+ " da árvore.";
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
            	if (q == q.getParent().getLeft()) 
            		q.setLeft(q.getParent()); // arranjo técnico para remoção da AVL (indica que q era filho esquerdo)
            	remove(q);  // delete it
                return q;
            }
            else if (q.getLeft() != null && q.getRight() == null) {  // if q has one child, at the left
                swap(q, q.getLeft());  // value swap with child
                BSTNode left = q.getLeft();
                left.setLeft(q); // arranjo técnico para remoção da AVL (indica que q era filho esquerdo)
                remove(left);  // delete child
                return left;
            }
            else if (q.getLeft() == null && q.getRight() != null) {  // if q has one child, at the right
                swap(q, q.getRight());  // value swap with child
                BSTNode right = q.getRight();
                remove(right);  // delete child
                return right;
            }
            else {  // if q has two children
                BSTNode m = max(q.getLeft());  // gets the immediate predecessor
                swap(q, m);  // value swaps q with it
                if (m == m.getParent().getLeft())
                	m.setLeft(q); // arranjo técnico para remoção da AVL (indica que q era filho esquerdo)
                remove(m);  // delete m
                return m;
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
	}
	
	/**
	 * Class BSTDrawer contains the methods to draw the structure.
	 * @author Ana Caroline
	 *
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
			int x = DEFAULT_SIZE.width/2;
			int y = 10;
			HashMap<Integer,DefaultGraphCell> cells = new HashMap<Integer, DefaultGraphCell>();
			preOrderCell(cells, root(), x, y, root().getColor());
			jgraph.getGraphLayoutCache().insert(cells.values().toArray());
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
				}
				if(root.getRight() != null){
					preOrderCell(c, root.getRight(), (int)(x + DEFAULT_SIZE.width/Math.scalb(1., 1 + root.nodeLevel(root()))), 
							y + deltaY, root.getRight().getColor());
					insertEdge(getDefaultPort((c.get(root.getKey())), model),
							getDefaultPort(c.get(root.getRight().getKey()), model));
				}
			}
		} 
    }
}


