import java.util.Random;


public class Factory {
	public BinarySearchTree binarySearchTree(int a){
		BinarySearchTree tree = new BinarySearchTree();
		for(int i = 0; i < a; i++){
			tree.insert(number(30));
		}

		return tree;
	}
	
	private int number(int a){
		Random r = new Random();
		return r.nextInt(a);
	}
}
