package br.ufrn.imd.dsvisualizer.gui;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import br.ufrn.imd.dsvisualizer.datastructures.AVLTree;
import br.ufrn.imd.dsvisualizer.datastructures.BinarySearchTree;
import br.ufrn.imd.dsvisualizer.datastructures.DataStructure;
import br.ufrn.imd.dsvisualizer.datastructures.Factory;
import br.ufrn.imd.dsvisualizer.datastructures.HeapMax;
import br.ufrn.imd.dsvisualizer.datastructures.HeapMin;
import br.ufrn.imd.dsvisualizer.datastructures.MyDeque;
import br.ufrn.imd.dsvisualizer.datastructures.MyList;
import br.ufrn.imd.dsvisualizer.datastructures.MyQueue;
import br.ufrn.imd.dsvisualizer.datastructures.MyStack;
import br.ufrn.imd.dsvisualizer.datastructures.RBTree;
import br.ufrn.imd.dsvisualizer.datastructures.UnionFind;

public final class DSManager {
	static private List<DataStructure> dataStructures = new LinkedList<DataStructure>();
	static private HashMap<Integer, String[]> dsInfo =
			new HashMap<Integer, String[]>() {{
				put(Factory.AVL, new String[]{"AVL Tree", "Toda AVL é também uma árvore binária de busca, exceto que visando sempre ter a maior"
						+ " otimização, a árvore é mantida com altura log n (n sendo o número de nós).\n" + 
						"Para isso a diferença entre as alturas das subárvores de um nó não pode ser maior do "
						+ "que módulo de 1.\n" + "Inserção é feita similar a da binária de busca exceto que é necessário"
						+ " fazer ajustes para se manter balanceada.\n" + "A busca é exatamente a mesma da BST.\n" + 
						" A cor azul do node representa balanço 0, a cor verde balanço 1 e laranja -1."});
				put(Factory.RB, new String[]{"Red-Black Tree", "A Red-Black Tree é uma árvore binária de busca que possui algumas características específicas.\n" +
						"Primeiro, ela conta com nós externos, que possuem altura 0.\n" +
						"Além disso, há um esquema de coloração dos nós. Em todos caminhos para um nó externo tem que " +
						"haver a mesma quantidade de nós negros, não pode haver nós rubros seguidos e os nós externos são" +
						" sempre pretos.\n" + "A pesquisa é feita da mesma maneira que a BST e as remoções e inserções exigem" +
						" a manutenção da quantidade de nodes negros, após uma dessas alterações na estrutura é necessário" +
						" conferir o caminho percorrido e fazer as rotações necessárias para manutenção da estrutura.\n" +
						" A complexidade está relacionada a quantidade de rotações que foram necessárias fazer e a altura da" +
						" árvore.\n"});
				put(Factory.BST, new String[]{"Binary Search Tree", "BinarySearchTree possui como característica principal o fato de cada nó ter até dois filhos, " +
		    			"os valores armazenados são organizados conforme a ordenação natural dos inteiros.\n" +
		    			"A busca é feita comparando o valor buscado com o valor de cada nó, caso seja maior " +
		    			"que o valor do nó, chama-se o filho da direita, caso contrário o da direita.\n" +
		    			"A remoção é feita substituindo o nó removido pelo nó mais a direita da subárvore " +
		    			" à direita.\n" + "A complexidade das operações estão baseadas na altura da árvore, uma vez"
		    			+ " que no máximo será acessado um caminho da raiz até o nó mais distante, ou seja, altura"
		    			+ " da árvore."});
				put(Factory.HEAPMAX, new String[]{"Heap Max", "A principal caracterísica da Heap Max é cada pai ser maior que seus filhos.\n" + 
		    			" Além disso, ela é implementada em um vetor, é necessário que ela seja completa à esquerda.\n"
		    			+ "Apesar da representação como árvore, por ser implementada em vetor, os acessos são feitos "+
		    			" calculando os índices.\n" + "Inserção é feita inserindo um novo valor no final do vetor e subindo" +
		    			" até o lugar adequado na árvore, enquanto ele for maior que o pai.\n" + "Remoção só é feita do topo.\n"
		    			+ "Suas operações são log n.\n" +"Uma conhecida aplicação é na heapsort, possibilitando um algoritmo" +
		    					" de ordenação ótimo.\n"});
				put(Factory.HEAPMIN, new String[]{"Heap Min", "A principal caracterísica da Heap Min é cada pai ser menor que seus filhos.\n" + 
		    			"Além disso, ela é implementada em um vetor, é necessário que ela seja completa à esquerda.\n"
		    			+ "Apesar da representação como árvore, por ser implementada em vetor, os acessos são feitos "+
		    			" calculando os índices.\n" + "Inserção é feita inserindo um novo valor no final do vetor e subindo" +
		    			" até o lugar adequado na árvore, enquanto ele for menor que o pai.\n" + "Remoção só é feita do topo."
		    			+ "Suas operações são log n.\n" +"Uma conhecida aplicação é na heapsort, possibilitando um algoritmo" +
		    			" de ordenação ótimo.\n"});
				put(Factory.UNIONFIND, new String[]{"Union-Find", "Representam a ideia matemática de conjuntos que não possuem elementos em comum," +
						" cada um aponta para si mesmo, é possível fazer a união entre dois elementos que na" +
						" verdade consiste na união de toda a árvore onde está esse elemento.\n" + " A operação find"
						+ " além de retornar o representante (raiz) do conjunto também aproveita e faz a modificação" +
						" na estrutura diminuindo o caminho do nó mandado até a root.\n A complexidade de find está relacionada a" +
						" altura da árvore.\n Já unite é O(1), nem chega a percorrer o vetor.\n Uma utilidade comum dessa estrutura" +
						" é fazer labirintos válidos.\n"});
				put(Factory.LIST, new String[]{"List", "List é uma estrutura em que cada node tem referência para o node anterior e seguinte.\n" +
						"Dessa forma para inserir a lista é percorrida até o local indicado e então o node é inserido," +
						" mesma ideia para remoção; assim, as operações são log n.\nA pesquisa também percorre a lista de" +
						" nodes até encontrar ou até chegar ao final."});
				put(Factory.STACK, new String[]{"Stack", "Stack é uma estrutura composta por uma sequência de nodes que possuem referência para o " +
						"anterior e para o seguinte.\n" +
						"Ela só permite remoção e inserção no topo, isto é, no último lugar da sequência.\n" + 
						"A pesquisa percorre toda a pilha, logo é O(n). Inserção e remoção possui complexidade O(1)" +
						"quando é guardada referência do topo.\n"});
				put(Factory.QUEUE, new String[]{"Queue", "Queue é uma estrutura que permite inserção sempre na última posição, e" +
						" remoção na primeira posição.\n" + "Dessa forma, essas operações são O(1) quando a implementação"
						+ " guarda referência para primeiro e último lugar."});
				put(Factory.DEQUE, new String[]{"Deque", "Deque é uma estrutura em que cada node contém uma referência para o próximo e para o anterior" +
						" , sendo permitido apenas inserção no início e no final e remoção só no início e no final.\n " +
						"Logo a complexidade dessas operações é O(1) caso a implementação guarde refências diretas para o" +
						" início e para o final.\n"});
			}};
	static private Random rand = new Random(); 
	
	/**
	 * Hidden constructor.
	 */
	private DSManager() {}
	
	/**
	 * Gets the data structure's name
	 * @param selectedDS  selected data structure
	 * @return  name of the data structure
	 */
	public static String getName(int selectedDS) {
		return dsInfo.get(selectedDS)[0];
	}
	
	/**
	 * Gets the data structure's name
	 * @param selectedDS  selected data structure
	 * @return  name of the data structure
	 */
	public static String getDescription(int selectedDS) {
		return dsInfo.get(selectedDS)[1];
	}
	
	/**
	 * Gets the data structure's name
	 * @param ds  data structure
	 * @return  name of the data structure
	 */
	public static String getName(DataStructure ds) {
		return dsInfo.get(getType(ds))[0];
	}
	
	/**
	 * Gets the data structure's name
	 * @param ds  data structure
	 * @return  name of the data structure
	 */
	public static String getDescription(DataStructure ds) {
		return dsInfo.get(getType(ds))[1];
	}
	
	/**
	 * Gets the data structure's type as a Factory constant
	 * @param  ds  data structure
	 * @return  data structure's type
	 */
	public static int getType(DataStructure ds) {
		if (ds instanceof AVLTree)
			return Factory.AVL;
		if (ds instanceof RBTree)
			return Factory.RB;
		if (ds instanceof BinarySearchTree)
			return Factory.BST;
		if (ds instanceof UnionFind)
			return Factory.UNIONFIND;
		if (ds instanceof HeapMax)
			return Factory.HEAPMAX;
		if (ds instanceof HeapMin)
			return Factory.HEAPMIN;
		if (ds instanceof MyList)
			return Factory.LIST;
		if (ds instanceof MyStack)
			return Factory.STACK;
		if (ds instanceof MyQueue)
			return Factory.QUEUE;
		if (ds instanceof MyDeque)
			return Factory.DEQUE;
		return -1;
	}
	
	/**
	 * Creates a data structure based on the given parameter
	 * @param selectedDS  selected data structure
	 * @return  constructed data structure
	 */
	public static DataStructure create(int selectedDS, int size) {
		if (size < 0)
			size = 5 + rand.nextInt(10); 
		DataStructure ds = Factory.create(selectedDS, size);
		dataStructures.add(ds);
		return ds;
	}
	
	/**
	 * Get current active data structures.
	 * @return  a list of the active data structures.
	 */
	public static final List<DataStructure> getDataStructures() {
		return dataStructures;
	}
	
	/**
	 * Destroy the given data structure.
	 * @param ds  the data structure to be destroyed
	 */
	public static void destroy(DataStructure ds) {
		dataStructures.remove(ds);
	}
	
	/**
	 * Destroy all active data structures.
	 */
	public static void destroyAll() {
		dataStructures = new LinkedList<DataStructure>();
	}
	
	/**
	 * Saves the data structures to a file.
	 * @param fos  file output stream to save
	 * @throws IOException
	 */
	public static void saveDataStructures(FileOutputStream fos) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeInt(dataStructures.size());
		for (DataStructure ds : dataStructures) {
			oos.writeObject(ds);
		}
		oos.close();
	}
	
	/**
	 * Loads the data structures from a file
	 * @param fis  file input stream to load
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void loadDataStructures(FileInputStream fis) throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(fis);
		int size = ois.readInt();
		for (int i = 0; i < size; i++) {
			dataStructures.add((DataStructure) ois.readObject());
		}
	}
}
