package br.ufrn.imd.dsvisualizer.datastructures;

import java.util.LinkedList;
import java.util.Random;


public final class Factory {
	public static final int BINARYTREE = 0;
	public static final int HEAPMAX = 1;
	public static final int HEAPMIN = 2;
	public static final int AVL = 3;
	public static final int RN = 4;
	public static final int NARIA = 5;
	public static final int UNIONFIND = 6;
	public static final int LIST = 7; //okay
	public static final int STACK = 8; //okay
	public static final int QUEUE = 9; //
	public static final int DEQUE = 10; //
	
	private Factory() {}
	
	public static DataStructure create(int dataStructure, int initialSize){
		switch (dataStructure){
		case 0:
			return binarySearchTree(initialSize);
		case 1: 
			return heapfake(initialSize);
		case 2:
			return heapfake(initialSize);
		case 3: 
			return avl(initialSize);
/*		case 4: 
			return
		case 5: 
			return
*/		case 6: 
			return unionfind(initialSize);
		case 7: 
			return list(initialSize);
		case 8:
			return stack(initialSize);
		case 9:
			return queue(initialSize);
		case 10:
			return deque(initialSize);
		default:
			return null;
		}
	}
	
	private static MyQueue queue(int size){
		MyQueue m = new MyQueue();
		for(int i = 0; i < size; i++)
			m.insertLast(number(30));
		return m;
	}
	
	private static MyDeque deque(int size){
		MyDeque m = new MyDeque();
		for(int i = 0; i < size; i++)
			m.insertFirst(number(30));
		return m;
	}
	
	private static MyStack stack(int size){
		MyStack m = new MyStack();
		for(int i = 0; i < size; i++)
			m.push(number(30));
		return m;
	}
	
	private static MyList list(int size){
		MyList m = new MyList();
		for(int i = 0; i < size; i++)
			m.insert(number(30));
		return m;
	}
	
	private static UnionFind unionfind(int size){
		UnionFind u = new UnionFind(size);
		return u;
	}
	
	private static BinarySearchTree binarySearchTree(int size){
		BinarySearchTree tree = new BinarySearchTree();
		for(int i = 0; i < size; i++){
			int n = number(30);
			System.out.println(n);
			tree.insert(n);
		}
		return tree;
	}
	
	private static int number(int max){
		Random r = new Random();
		return r.nextInt(max);
	}
	
	private static HeapFake heapfake(int a){
		HeapFake h = new HeapFake();
		for(int i = 0; i < a;){
			if(h.insert(number(30)))
				i++;
		}
		return h;
	}
	
	private static AVLTree avl(int size){
		AVLTree m = new AVLTree();
		for(int i = 0; i < size; i++){
			m.insert(number(30));
		}
		return m;
	}
}
