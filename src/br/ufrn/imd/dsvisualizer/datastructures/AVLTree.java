package br.ufrn.imd.dsvisualizer.datastructures;

public class AVLTree extends BinarySearchTree {
	
	/**
	 * Generated serial ID.
	 */
	private static final long serialVersionUID = 2759166979168262260L;
	private AVLNode root;
	
    /**
     * Constructor for class AVLTree with no parameters. 
     */
    public AVLTree()
    {
        root = null;
    }

    /**
     * Access method to the root.
     * @return the root
     */
    protected AVLNode root() {
    	return root;
    }
    
    /**
     * Inserts a new node if does not exists one with the same key.
     * @param key new node's key
     */
	public void insert(int key) {
		privateInsert(key, root(), null, new Ref<Boolean>(true));
	}
	
	public String getShortName() {
		return "AVL";
	}	
    
    /**
     * Returns structure description.
     * @return description
     */
	static public String getDescription(){
		return "Toda AVL é também uma árvore binária de busca, exceto que visando sempre ter a maior"
				+ " otimização, a árvore é mantida com altura log n (n sendo o número de nós)." + 
				" Para isso a diferença entre as altudas das subárvores de um nó não pode ser maior do "
				+ "que módulo de 1." + " Inserção é feita similar a da binária de busca exceto que é necessário"
				+ " fazer ajustes para se manter balanceada." + " A busca é exatamente a mesma da BST." + 
				" A cor azul do node representa balanço 0, a cor verde balanço 1 e laranja -1.";
	}
	/**
	 * Inserts a new node if does not exists one with the same key.
	 * @param key new node's key
	 * @param node current node
	 * @param dad current's father node
	 * @param b indicates if the node is already inserted
	 */
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
			if (key == node.getKey()) {
				b.set(false);
				return;
			}
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
	
	/**
	 * Deletes a node given a key.
	 * @param key node's key
	 * @return deleted node
	 */
	public AVLNode delete(int key) {
		AVLNode node = (AVLNode)super.delete(key);
		if (node != null) {
			if (node.getParent() != null) {
				if (node.getLeft() != null)
					key = node.getParent().getKey() - 1;
				else
					key = node.getParent().getKey() + 1;
			}
			updateBalance(node.getParent(), key);
		}
		return node;
	}
	
	private void updateBalance(AVLNode node, int key) {
		if (node != null) {
			AVLNode parent = node.getParent();
			if (key < node.getKey())
				node.setBalance(node.getBalance() + 1);
			else
				node.setBalance(node.getBalance() - 1);
			if (node.getBalance() != 1 && node.getBalance() != -1) {
				if (node.getBalance() == 2)
					leftRotations(node);
				else if (node.getBalance() == -2) 
					rightRotations(node);
				updateBalance(parent, key);
			}
		}
	}
	
	/**
	 * Does the right rotation to correct the tree's balance.
	 * @param node node will be balanced.
	 */
	private void rightRotations(AVLNode node) {
		AVLNode left = node.getLeft(), right;
		if (left.getBalance() == -1 ) {
			rightRotation(node, left);
			node.setBalance(0);
			left.setBalance(0);
			if (root == node)
				root = left;
		}
		else {
			right = left.getRight();
			doubleRightRotation(node, left, right);
			if (right.getBalance() == -1)
				node.setBalance(1);
			else
				node.setBalance(0);
			if (right.getBalance() == 1)
				left.setBalance(-1);
			else
				left.setBalance(0);
			right.setBalance(0);
			if (root == node)
				root = right;
		}
	}
	
	/**
	 * Does the right rotation to correct the tree's balance.
	 * @param node node will be balanced.
	 */
	private void leftRotations(AVLNode node) {
		AVLNode right = node.getRight(), left;
		if (right.getBalance() == 1) {
			leftRotation(node, right);
			node.setBalance(0);
			right.setBalance(0);
			if (root == node)
				root = right;
		}
		else {
			left = right.getLeft();
			doubleLeftRotation(node, right, left);
			if (left.getBalance() == 1)
				node.setBalance(-1);
			else
				node.setBalance(0);
			if (left.getBalance() == -1)
				right.setBalance(1);
			else
				right.setBalance(0);
			left.setBalance(0);
			if (root == node)
				root = left;
		}
	}
}

