
public class BinarySearchTree extends Tree {
	
	private BSTNode head;
    
    /**
     * Constructor for class Tree
     */
    public BinarySearchTree()
    {
        head = new BSTNode();
    }
    
    /**
     * Gets the root of the tree.
     * @return  the root of the tree
     */
    private BSTNode root()
    {
        return head.getLeftKid();
    }
    
    /**
     * Sets the root of the tree to the given node.
     * @param  node  the node to which the root will be set
     */
    private void root(BSTNode node)
    {
        head.setLeftKid(node);
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
    public BSTNode insert(int key)
    {
        return privateSearch(root(), key, true);
    }
    
    /**
     * Removes node with the given key.
     * @param  key  key of the node to be removed
     * @return  removed node
     */
    public BSTNode delete(int key)
    {
        BSTNode q = search(key);
        
        if (q != null) {  // if is in the tree
            if (q.getLeftKid() == null && q.getRightKid() == null) {  // if q is leaf
                remove(q);  // delete it
            }
            else if (q.getLeftKid() != null && q.getRightKid() == null) {  // if q has one child, at the left
                swap(q, q.getLeftKid());  // value swap with child
                remove(q.getLeftKid());  // delete child 
            }
            else if (q.getLeftKid() == null && q.getRightKid() != null) {  // if q has one child, at the right
                swap(q, q.getRightKid());  // value swap with child
                remove(q.getRightKid());  // delete child
            }
            else {  // if q has two children
                BSTNode m = max(q.getLeftKid());  // gets the immediate predecessor
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
     * Deletes the given node. Must have a parent.
     * @param  node  the node to be removed
     */
    private void remove(BSTNode node)
    {
        if (node != null) {
        	BSTNode parent = (BSTNode) node.getParent();
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
            if (node.getLeftKid() == null && insert) {
                node.setLeftKid(new BSTNode(node, null, null, key));
                return node.getLeftKid();
            }
            return privateSearch(node.getLeftKid(), key, insert);
        } else {
            if (node.getRightKid() == null && insert) {
                node.setRightKid(new BSTNode(node, null, null, key));
                return node.getRightKid();
            }
            return privateSearch(node.getRightKid(), key, insert);
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
            if (r.getRightKid() == null) {
                return r;
            }
            else {
                return max(r.getRightKid());
            }
        }
        return null;
    }
}
