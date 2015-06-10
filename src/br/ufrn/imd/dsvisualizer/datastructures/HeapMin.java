package br.ufrn.imd.dsvisualizer.datastructures;

public class HeapMin extends AbstractHeap{
	private static final long serialVersionUID = 6924159814911487873L;
	
	/**
	 * Assistant method for the removing method.
	 * 
	 * @param index Starter index for the goDown method (increases recursively)
	 */
	private void goDown(int index){
		int aux = getLeftIndex(index);
		if(aux <= super.myVector.size()-1){
			if(aux + 1 <= super.myVector.size()-1){
				if(super.myVector.get(aux+1) < super.myVector.get(aux)){
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
	 * Get the data structure's name.
	 * @return the data structure's name
	 */
	public String getName() {
		return "Heap Min";
	}
	
	/**
     * Returns structure description.
     * @return description
     */
	public String getDescription(){
		return "A principal caracterísica da Heap Min é cada pai ser menor que seus filhos.\n" + 
    			"Além disso, ela é implementada em um vetor, é necessário que ela seja completa à esquerda.\n"
    			+ "Apesar da representação como árvore, por ser implementada em vetor, os acessos são feitos "+
    			" calculando os índices.\n" + "Inserção é feita inserindo um novo valor no final do vetor e subindo" +
    			" até o lugar adequado na árvore, enquanto ele for menor que o pai.\n" + "Remoção só é feita do topo."
    			+ "Suas operações são log n.\n" +"Uma conhecida aplicação é na heapsort, possibilitando um algoritmo" +
    			" de ordenação ótimo.\n";	
	}
	
	/**
	 * Assistant method for the inserting method.
	 * 
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
	/**
	 * Insertion method for this variation of priority queue (HeapMin)
	 * 
	 * @param key Value wished to be inserted in vector
	 */
	public void insert(int key){
		if(!myVector.contains(key)){
			myVector.add(myVector.size(), key);
			if(myVector.size() > 1)
				goUp(myVector.size()-1);
		}
	}
	/**
	 * Removing method for this variation of priority queue (HeapMin)
	 * 
	 * @param key Value wished to be removed from vector
	 */
	public void remove(){
		super.swap(0, super.myVector.size()-1);
		super.myVector.remove(super.myVector.size()-1);
		goDown(0);
	}
}
