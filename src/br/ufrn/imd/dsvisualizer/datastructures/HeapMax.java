package br.ufrn.imd.dsvisualizer.datastructures;


public class HeapMax extends AbstractHeap{
	
	/**
	 * Assistant method for the removing method.
	 * 
	 * @param index Starter index for the goDown method (increases recursively)
	 */
	private void goDown(int index){
		int aux = getLeftIndex(index);
		if(aux <= myVector.size()-1){
			if(aux + 1 <= myVector.size()-1){
				if(myVector.get(aux+1) < myVector.get(index)){
					aux++;
				}
			}
			if(myVector.get(index) < myVector.get(aux)){
				swap(index, aux);
				goDown(aux);
			}
		}
	}
	
	/**
	 * Assistant method for the inserting method.
	 * 
	 * @param index Starter index for the goUp method (decreases recursively)
	 */
	protected void goUp(int index){
		int aux = getParentIndex(index);
		if(aux >= 0){
			if(myVector.get(index) > myVector.get(aux)){
				swap(index, aux);
				if(aux > 0)
					goUp(aux);
			}
		}
	}
	
	/**
	 * Insertion method for this variation of priority queue (HeapMax)
	 * 
	 * @param key Value wished to be inserted in vector
	 */
	public void insert(int key){
		System.out.println(key);
		if(!myVector.contains(key)){
			myVector.add(myVector.size(), key);
			if(myVector.size() > 1)
				goUp(myVector.size()-1);
		}
	}
	
	/**
	 * Removing method for this variation of priority queue (HeapMax)
	 * 
	 * @param key Value wished to be removed from vector
	 */
	public void remove(int key){
		swap(0, myVector.size()-1);
		myVector.remove(myVector.size()-1);
		goDown(0);
	}
}
