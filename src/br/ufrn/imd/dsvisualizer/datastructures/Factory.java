package br.ufrn.imd.dsvisualizer.datastructures;

import java.util.Random;
/**
 * Factory class that creates a new structure with a given number of nodes.
 * @author Ana Caroline, João Pedro Holanda
 *
 */
public final class Factory {
	public static final int BST = 0;
	public static final int HEAPMAX = 2;
	public static final int HEAPMIN = 3;
	public static final int AVL = 4;
	public static final int RB = 5;
	public static final int NARIA = 6;
	public static final int UNIONFIND = 7;
	public static final int LIST = 8; 
	public static final int STACK = 9; 
	public static final int QUEUE = 10; 
	public static final int DEQUE = 11; 
	
	private Factory() {}
	/**
	 * Creates a new structure given a initial size and a constant to indicates what structure
	 * @param dataStructure constant to indicates the structure
	 * @param initialSize initial number of nodes
	 * @return the new structure
	 */
	public static DataStructure create(int dataStructure, int initialSize){
		switch (dataStructure){
		case BST:
			return binarySearchTree(initialSize);
		case HEAPMAX: 
			return heapmax(initialSize);
		case HEAPMIN:
			return heapmin(initialSize);
		case AVL:
			return avl(initialSize);
		case RB: 
			return rb(initialSize);
		case UNIONFIND: 
			return unionfind(initialSize);
		case LIST: 
			return list(initialSize);
		case STACK:
			return stack(initialSize);
		case QUEUE:
			return queue(initialSize);
		case DEQUE:
			return deque(initialSize);
		default:
			return null;
		}
	}
	
	/**
	 * Creates a heapmax given an initial size.
	 * @param size initial size
	 * @return new heapmax
	 */
	private static HeapMax heapmax(int size){
		HeapMax h = new HeapMax();
		for(int i = 0; i < size; i++){
			h.insert(number(30));
		}
		return h;
	}
	
	/**
	 * Creates a heapmin given an initial size.
	 * @param size initial size
	 * @return new heapmin
	 */
	private static HeapMin heapmin(int size){
		HeapMin h = new HeapMin();
		for(int i = 0; i < size; i++){
			h.insert(number(30));
		}
		return h;
	}
	
	/**
	 * Creates a RBTRee given an initial size.
	 * @param size initial size
	 * @return new RBTree
	 */
	private static RBTree rb(int size) {
		RBTree rb = new RBTree();
		for (int i = 0; i < size; i++) 
			rb.insert(number(30));
		return rb;
	}
	
	/**
	 * Creates a queue given a number of nodes.
	 * @param size number of nodes
	 * @return the new queue
	 */
	private static MyQueue queue(int size){
		MyQueue m = new MyQueue();
		for(int i = 0; i < size; i++)
			m.insertLast(number(30));
		return m;
	}
	
	/**
	 * Creates a deque given a number of nodes.
	 * @param size number of nodes
	 * @return the new deque
	 */
	private static MyDeque deque(int size){
		MyDeque m = new MyDeque();
		for(int i = 0; i < size; i++)
			m.insertFirst(number(30));
		return m;
	}
	
	/**
	 * Creates a stack given a number of nodes.
	 * @param size number of nodes
	 * @return the new stack
	 */
	private static MyStack stack(int size){
		MyStack m = new MyStack();
		for(int i = 0; i < size; i++)
			m.push(number(30));
		return m;
	}
	
	/**
	 * Creates a list given a number of nodes.
	 * @param size
	 * @return
	 */
	private static MyList list(int size){
		MyList m = new MyList();
		for(int i = 0; i < size; i++)
			m.insert(number(30), 0);
		return m;
	}
	
	/**
	 * Creates a union find given an initial size.
	 * @param size initial size
	 * @return new union find
	 */
	private static UnionFind unionfind(int size){
		UnionFind u = new UnionFind(size);
		return u;
	}
	
	/**
	 * Creates a binary search tree given a number of nodes.
	 * @param size number of nodes
	 * @return new binary search tree
	 */
	private static BinarySearchTree binarySearchTree(int size){
		BinarySearchTree tree = new BinarySearchTree();
		for(int i = 0; i < size; i++){
			int n = number(30);
			tree.insert(n);
		}
		return tree;
	}
	
	/**
	 * Returns a new random int number given a max value. 
	 * @param max max value
	 * @return random int
	 */
	private static int number(int max){
		Random r = new Random();
		return r.nextInt(max);
	}
	
	/**
	 * Creates a heapmax given a number of nodes.
	 * @param a number of nodes
	 * @return new heapmax
	 */
	private static HeapFake heapfake(int a){
		HeapFake h = new HeapFake();
		for(int i = 0; i < a;){
			if(h.insert(number(30)))
				i++;
		}
		return h;
	}
	
	/**
	 * Creates a AVL tree given a initial size.
	 * @param size initial size
	 * @return new AVL tree
	 */
	private static AVLTree avl(int size){
		AVLTree m = new AVLTree();
		for(int i = 0; i < size; i++)
			m.insert(number(30));
		return m;
	}
}
