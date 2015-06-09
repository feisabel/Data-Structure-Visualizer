package br.ufrn.imd.dsvisualizer.datastructures;


import java.awt.Color;
import java.util.HashMap;
import java.util.Vector;

import org.jgraph.graph.DefaultGraphCell;

import br.ufrn.imd.dsvisualizer.gui.Drawer;

public abstract class AbstractHeap extends DataStructure{
	protected Vector<Integer> myVector;
	
	
	/**
	 * Standard constructor, initializes a generic vector according to the type passed as a parameter (default capacity set to 10, doubles when needed).
	 * 
	 */
	public AbstractHeap (){
		this.myVector = new Vector<Integer>();
		drawer = new HeapDrawer();
		support("insert", "key");
		support("remove");
	}
	
	/**
	 *  Method to check if myVector contains a certain key.
	 *  
	 *  @param key The specified key to be searched throughout the vector
	 *  @return true if contains the specified key
	 */
	public boolean containsKey(int key) {
		boolean contains = false;
		for(Integer i : this.myVector) {
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
	public int getRight(int index){
		return myVector.get((2*index)+2);
	}
	
	/**
	 * Method that returns the element's left kid
	 * 
	 * @param index Defines the element
	 * @return left kid of said element
	 */
	public int getLeft(int index){
		return myVector.get((2*index)+1);
	}
	
	/**
	 * Method that returns the element's parent
	 * 
	 * @param index Defines the element
	 * @return element's parent
	 */
	public int getParent(int index){
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
		int aux1 = myVector.get(i);
		int aux2 = myVector.get(j);
		myVector.remove(i);
		myVector.add(i, aux2);
		myVector.remove(j);
		myVector.add(j, aux1);
	}


	abstract public void insert(int key);
	abstract public void remove();
	
	/**
	 * Class to draw the structure heap.
	 * @author Ana Caroline
	 *
	 */
	class HeapDrawer extends Drawer{
		/**
		 * Method to draw the heap.
		 */
		public void draw(){
			int x = 0;
			int y = 30;
			int lvl = 1;
			HashMap<Integer, Integer> positionX = new HashMap<Integer, Integer>();
			int help = 0; //dad index
			for(int i = 0; i < myVector.size(); i++){
				if(i%2 == 0){
					if(i != 0){
						help = myVector.get(i/2 - 1);
						x = (int) (positionX.get(help) + DEFAULT_SIZE.width/Math.scalb(1.0, lvl));
					}else
						x = (int) (DEFAULT_SIZE.width/Math.scalb(1.0, lvl));
				}
				else{
					help = myVector.get(i/2);
					x = (int) (positionX.get(help) - DEFAULT_SIZE.width/Math.scalb(1.0, lvl+1));
					
				}
				if(Math.scalb(1.0, lvl) == i+1){
					lvl++;
					y+=deltaY;
				}
				createMyVertex(myVector.get(i), x, y, Color.red);
				positionX.put(myVector.get(i), x);
				if(i != 0){
					insertEdge(getDefaultPort(cells.get(help), model), 
							getDefaultPort(cells.get(myVector.get(i)), model));
				}
			}
			jgraph.getGraphLayoutCache().insert(cells.values().toArray());
		}
	}
}
