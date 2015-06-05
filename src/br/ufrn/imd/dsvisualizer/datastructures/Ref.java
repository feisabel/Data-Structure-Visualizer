package br.ufrn.imd.dsvisualizer.datastructures;

public class Ref<T> {
	private T internal;
	
	public Ref(T obj) {
		internal = obj;
	}
	
	T get() {
		return internal;
	}
	
	void set(T obj) {
		internal = obj;
	}
}
