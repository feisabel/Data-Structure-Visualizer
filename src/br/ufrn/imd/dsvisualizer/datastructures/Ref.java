package br.ufrn.imd.dsvisualizer.datastructures;

/**
 * Class to simulate references.
 * @author João Pedro
 *
 * @param <T>
 */
public class Ref<T> {
	private T internal;
	/**
	 * Constructor.
	 * @param obj
	 */
	public Ref(T obj) {
		internal = obj;
	}
	/**
	 * Access method.
	 * @return type
	 */
	public T get() {
		return internal;
	}
	/**
	 * Set method. 
	 * @param obj type
	 */
	public void set(T obj) {
		internal = obj;
	}
}
