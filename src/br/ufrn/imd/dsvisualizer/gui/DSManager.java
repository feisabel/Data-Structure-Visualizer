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
	
	private DSManager() {}
	
	public static DataStructure create(int dataStructure) {
		DataStructure ds = Factory.create(dataStructure, rand.nextInt(12));
		dataStructures.add(ds);
		return ds;
	}
	
	public static final List<DataStructure> getDataStructures() {
		return dataStructures;
	}
	
	public static void destroy(DataStructure ds) {
		dataStructures.remove(ds);
	}
	
	public static void destroyAll() {
		dataStructures = new LinkedList<DataStructure>();
	}
	
	public static void saveDataStructures(FileOutputStream fos) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeInt(dataStructures.size());
		for (DataStructure ds : dataStructures) {
			oos.writeObject(ds);
		}
		oos.close();
	}
	
	public static void loadDataStructures(FileInputStream fis) throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(fis);
		int size = ois.readInt();
		for (int i = 0; i < size; i++) {
			dataStructures.add((DataStructure) ois.readObject());
		}
	}
}
