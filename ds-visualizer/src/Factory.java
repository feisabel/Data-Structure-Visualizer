import java.util.Random;


public class Factory {
	public BinarySearchTree binarySearchTree(int a){
		BinarySearchTree tree = new BinarySearchTree();
		for(int i = 0; i < a; i++){
			int b = number(30);
			System.out.println(b);
			tree.insert(b);
		}

		return tree;
	}
	
	private int number(int a){
		Random r = new Random();
		return r.nextInt(a);
	}
}
