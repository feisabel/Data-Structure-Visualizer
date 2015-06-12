package br.ufrn.imd.dsvisualizer.datastructures;

/**
 * HeapMax class.
 * @author Leonardo
 *
 */
public class HeapMin extends AbstractHeap{
	private static final long serialVersionUID = 6924159814911487873L;
	
	/**
	 * Assistant method for the removing method.
	 * @param index Starter index for the goDown method (increases recursively)
	 */
	protected void goDown(int index){
		int aux = getLeftIndex(index);
		if(aux <= myVector.size()-1){
			if(aux + 1 <= myVector.size()-1){
				if(myVector.get(aux+1) < myVector.get(aux)){
					aux++;
				}
			}
			if(myVector.get(index) > myVector.get(aux)){
				swap(index, aux);
				goDown(aux);
			}
		}
	}
	
	/**
	 * Assistant method for the inserting method.
	 * @param index Starter index for the goUp method (decreases recursively)
	 */
	protected void goUp(int index){
		int aux = getParentIndex(index);
		if(aux >= 0){
			if(myVector.get(index) < myVector.get(aux)){
				swap(index, aux);
				if(aux > 0)
					goUp(aux);
			}
		}
	}
}
