package br.ufrn.imd.dsvisualizer.datastructures;

/**
 * HeapMax class.
 * @author Leonardo
 *
 */
public class HeapMax extends AbstractHeap{
	private static final long serialVersionUID = -9077515488358323361L;

	/**
	 * Assistant method for the removing method.
	 * 
	 * @param index Starter index for the goDown method (increases recursively)
	 */
	protected void goDown(int index){
		int aux = getLeftIndex(index);
		if(aux <= myVector.size()-1){
			if(aux + 1 <= myVector.size()-1){
				if(myVector.get(aux+1) > myVector.get(aux)){
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

	public String getShortName() {
		return "HeapMax";
	}
	
	/**
     * Returns structure description.
     * @return description
     */
    public String getDescription(){
    	return "A principal caracterísica da Heap Max é cada pai ser maior que seus filhos.\n" + 
    			" Além disso, ela é implementada em um vetor, é necessário que ela seja completa à esquerda.\n"
    			+ "Apesar da representação como árvore, por ser implementada em vetor, os acessos são feitos "+
    			" calculando os índices.\n" + "Inserção é feita inserindo um novo valor no final do vetor e subindo" +
    			" até o lugar adequado na árvore, enquanto ele for maior que o pai.\n" + "Remoção só é feita do topo.\n"
    			+ "Suas operações são log n.\n" +"Uma conhecida aplicação é na heapsort, possibilitando um algoritmo" +
    					" de ordenação ótimo.\n";
    }
}
