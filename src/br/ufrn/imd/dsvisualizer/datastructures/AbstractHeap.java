package structures;

import java.util.Vector;

public abstract class AbstractHeap<T>{
	Vector<T> myVector;
	
	
	/**
	 * Standard constructor, initializes a generic vector according to the type passed as a parameter (default capacity set to 10, doubles when needed).
	 * 
	 */
	public AbstractHeap (){
		this.myVector = new Vector<T>();
	}
	
	/**
	 *  Method to check if myVector contains a certain key.
	 *  
	 *  @param key The specified key to be searched throughout the vector
	 *  @return true if contains the specified key
	 */
	public boolean containsKey(T key) {
		boolean contains = false;
		for(T i : this.myVector) {
			if(i == key){
				contains = true;
			}
		}
		return contains;
	}
	
	/**
	 * Method to clear all elements from myVector
	 */
	public void clear() {
		this.myVector.clear();
	}
	
	/**
	 * Method that returns the element's right kid
	 * 
	 * @param index Defines the element
	 * @return right kid of said element
	 */
	public T getRight(int index){
		return myVector.get((2*index)+2);
	}
	
	/**
	 * Method that returns the element's left kid
	 * 
	 * @param index Defines the element
	 * @return left kid of said element
	 */
	public T getLeft(int index){
		return myVector.get((2*index)+1);
	}
	
	/**
	 * Method that returns the element's parent
	 * 
	 * @param index Defines the element
	 * @return element's parent
	 */
	public T getParent(int index){
		if(index%2 == 0 && index != 0){
			return myVector.get((index/2)-1);
		} else if (index == 0){
			return myVector.get(0);
		} else {
			return myVector.get(index/2);
		}
	}
	
	/**
	 * A method similar to getParent, but returns the element's parent's index instead of value.
	 * 
	 * @param index Defines the element
	 * @return element's parent's index
	 */
	public int getParentIndex(int index){
		if(index%2 == 0 && index != 0){
			return (index/2)-1;
		} else if (index == 0){
			return 0;
		} else {
			return index/2;
		}
	}
	
	/**
	 * A method similar to getLeft, but returns the element's left kid's index instead of value.
	 * 
	 * @param index Defines the element
	 * @return element's left kid's index
	 */
	public int getLeftIndex(int index){
		return (2*index)+1;
	}
	
	/**
	 * A method similar to getRight, but returns the element's right kid's index instead of value.
	 * 
	 * @param index Defines the element
	 * @return element's right kid's index
	 */
	public int getRightIndex(int index){
		return (2*index)+2;
	}
	
	/**
	 * Internal swap method for the vector's elements
	 * 
	 * @param i Position of first element to be swapped
	 * @param j Position of second element to be swapped
	 */
	protected void swap(int i, int j){
		T aux = myVector.get(i);
		myVector.add(i, myVector.get(j));
		myVector.add(j, aux);
	}
}
