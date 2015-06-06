package br.ufrn.imd.dsvisualizer.datastructures;

public class Ref<T> {
	private T internal;
	
	public Ref(T obj) {
		internal = obj;
	}
	
	public T get() {
		return internal;
	}
	
	public void set(T obj) {
		internal = obj;
	}
}
