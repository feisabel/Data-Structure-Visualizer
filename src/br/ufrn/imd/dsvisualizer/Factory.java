package br.ufrn.imd.dsvisualizer;
import java.util.LinkedList;
import java.util.Random;


public final class Factory {
	public static final int BINARYTREE = 0;
	public static final int HEAPMAX = 1;
	public static final int HEAPMIN = 2;
	public static final int AVL = 3;
	public static final int RN = 4;
	public static final int NARIA = 3;
	public static final int UNIONFIND = 4;
	public static final int LIST = 5;
	public static final int STACK = 5;
	public static final int QUEUE = 5;
	public static final int DEQUE = 5;
	
	private Factory() {}
	
	public static DataStructure create(int dataStructure, int initialSize){
		switch (dataStructure){
		case 0:
			return binarySearchTree(initialSize);
		case 1: 
			return heapfake(initialSize);
		case 2:
			return heapfake(initialSize);
		default:
			return null;
		}
	}
	
	private static BinarySearchTree binarySearchTree(int size){
		BinarySearchTree tree = new BinarySearchTree();
		for(int i = 0; i < size; i++){
			tree.insert(number(30));
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
}
