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
	
	private Factory() {}
	
	public static Tree create(int a, int n){
		switch (a){
		case 0:
			return binarySearchTree(n);
		case 1: 
			return heapfake(n);
		case 2:
			return heapfake(n);
		default:
			return null;
		}
	}
	
	private static BinarySearchTree binarySearchTree(int a){
		BinarySearchTree tree = new BinarySearchTree();
		for(int i = 0; i < a; i++){
			tree.insert(number(30));
		}
		return tree;
	}
	
	private static int number(int a){
		Random r = new Random();
		return r.nextInt(a);
	}
	
	private static HeapFake heapfake(int a){
		HeapFake h = new HeapFake();
		for(int i = 0; i < a;){
			if(h.add(number(30)))
				i++;
		}
		return h;
	}
}
