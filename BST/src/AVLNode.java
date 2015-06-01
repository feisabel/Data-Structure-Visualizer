
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
     * @param leftKid Object to be assigned to the BSTNode leftKid.
     * @param rightKid Object to be assigned to the BSTNode rightKid.
     * @param key int to be assigned to the integer key.
     * @param balance int to be assigned to the integer balance.
     */
	public AVLNode(AVLNode parent, AVLNode leftKid, AVLNode rightKid, int key, int balance) {
		super(parent, leftKid, rightKid, key);
		this.balance = balance;
	}
	
	public Node newNode(AVLNode parent){
		return new AVLNode(parent, null, null, 0, 0);
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
}
