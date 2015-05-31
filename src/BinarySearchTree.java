/**
 * Implements a binary search tree using Node.
 * 
 * @author Ana Caroline, Fernanda, João Pedro e Leonardo. 
 * @version 27.04.2015
 */
public class BinarySearchTree
{
    private Node head;

    /**
     * Constructor for class Tree
     */
    
    
    public BinarySearchTree()
    {
        head = new Node();

    }
    

    /**
     * Gets the root of the tree.
     * @return  the root of the tree
     */
    public Node root()
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
    public void insert(int key)
    {
  
    	privateSearch(root(), key, true);
    }
    
    /**
     * Removes node with the given key.
     * @param  key  key of the node to be removed
     * @return  removed node
     */
    public Node delete(int key)
    {
        Node q = search(key);
        
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
                Node m = max(q.getLeftKid());  // gets the immediate predecessor
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
    private void remove(Node node)
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
    public Node search(int key)
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
     * Helper method for depthSearch. Searches for a node, if not found insert one.
     * @param  r  root of the tree to search on
     * @param  key  ley to search
     * @return  node with the key 'key'
     */
    private Node depthSearchNew(Node r, int key)
    {
        if(isEmpty()){
            root(new Node(head, null, null, 0));
            return root();
        }
        else {
            if(r.getKey() == key){
                return r;
            }
            else{
                if(r.getKey() > key){
                    if(r.getLeftKid() == null){
                        r.setLeftKid(new Node(r, null, null, 0));
                        return r.getLeftKid();
                    }
                    else{
                        r = r.getLeftKid();
                    }
                }
                else{
                    if(r.getRightKid() == null) {
                        r.setRightKid(new Node(r, null, null, 0));
                        return r.getRightKid();
                    }
                    else{
                        r = r.getRightKid();
                    }
                }
            }
            return depthSearchNew(r, key);
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
}


