
public class AVLTree extends BinarySearchTree{
	
	public AVLNode insert(int key) {
		AVLNode node = super.insert(key);
		balance();
	}
	
	public void balance() {
		
	}
	
}
