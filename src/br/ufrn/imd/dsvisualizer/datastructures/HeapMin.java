package structures;

public class HeapMin<T> extends AbstractHeap<T>{
	
	/**
	 * Assistant method for the removing method.
	 * 
	 * @param index Starter index for the goDown method (increases recursively)
	 */
	private void goDown(int index){
		int aux = getLeftIndex(index);
		if(aux <= super.myVector.size()-1){
			if(aux + 1 <= super.myVector.size()-1){
				if(super.myVector.get(aux+1) < super.myVector.get(index)){
					aux++;
				}
			}
			if(super.myVector.get(index) > super.myVector.get(aux)){
				super.swap(index, aux);
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
		if(aux > 0){
			if(super.myVector.get(index) < super.myVector.get(aux)){
				super.swap(index, aux);
				goUp(aux);
			}
		}
	}
	
	/**
	 * Insertion method for this variation of priority queue (HeapMin)
	 * 
	 * @param key Value wished to be inserted in vector
	 */
	public void insert(T key){
		if(!super.myVector.contains(key)){
			super.myVector.add(super.myVector.size(), key);
			goUp(super.myVector.size());
		}
	}
	
	/**
	 * Removing method for this variation of priority queue (HeapMin)
	 * 
	 * @param key Value wished to be removed from vector
	 */
	public void remove(T key){
		super.swap(0, super.myVector.size()-1);
		super.myVector.remove(super.myVector.size()-1);
		goDown(0);
	}
}
