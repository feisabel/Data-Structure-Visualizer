package br.ufrn.imd.dsvisualizer.gui;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import br.ufrn.imd.dsvisualizer.datastructures.DataStructure;
import br.ufrn.imd.dsvisualizer.datastructures.Factory;

public final class DSManager {
	static private List<DataStructure> dataStructures = new LinkedList<DataStructure>();
	static private Random rand = new Random();
	
	/**
	 * Hidden constructor.
	 */
	private DSManager() {}
	
	/**
	 * Creates a data structure based on the given parameter
	 * @param selectedDS  selected data structure
	 * @return  constructed data structure
	 */
	public static DataStructure create(int selectedDS) {
		DataStructure ds = Factory.create(selectedDS, rand.nextInt(12));
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
