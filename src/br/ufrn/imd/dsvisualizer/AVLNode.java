package br.ufrn.imd.dsvisualizer;


public class AVLNode extends BSTNode {
	
	int balance;
	
	/**
     * Constructor of the class AVLNode with no parameters.
     */
	public AVLNode() {
		balance = 0;
	}
	
	/**
     * Constructor of the class AVLNode with three parameters.
     * @param parent Object to be assigned to the Node parent.
     * @param left Object to be assigned to the BSTNode left.
     * @param right Object to be assigned to the BSTNode right.
     * @param key int to be assigned to the integer key.
     * @param balance int to be assigned to the integer balance.
     */
	public AVLNode(AVLNode parent, AVLNode left, AVLNode right, int key, int balance) {
		super(parent, left, right, key);
		this.balance = balance;
	}

	/**
     * Access method to balance.
     * @return The integer balance.
     */
    public int getBalance() {
        return balance;
    }
    
    /**
    * Modifier method of balance.
    * @param balance Object to be assigned to the integer balance.
    */
   public void setBalance(int balance) {
       this.balance = balance;
   }
   
   public AVLNode getLeft() {
	   return (AVLNode)super.getLeft();
   }
   
   public AVLNode getRight() {
	   return (AVLNode)super.getRight();
   }
   
   public AVLNode getParent() {
	   return (AVLNode)super.getParent();
   }
}
