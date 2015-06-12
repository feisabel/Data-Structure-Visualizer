package br.ufrn.imd.dsvisualizer.datastructures;

import br.ufrn.imd.dsvisualizer.gui.DSManager;

/**
 * Exception for invalid arguments.
 * @author João Pedro Holanda
 */
public class DSIllegalArgumentsException extends Exception {
	/**
	 * Constructor with parameters.
	 * @param ds  data structure which threw the exception
	 * @param operation  operation which caused the exception
	 * @param args    arguments which were invalid
	 */
	public DSIllegalArgumentsException(DataStructure ds, String operation, Integer[] args) {
		super("Illegal arguments: " + strFromArray(args) + "to "
	          + DSManager.getName(ds) + "." + operation);
	}
	
	/**
	 * Constructor with parameters and cause.
	 * @param ds  data structure which threw the exception
	 * @param operation  operation which caused the exception
	 * @param args      arguments which were invalid
	 * @param cause     cause
	 */
	public DSIllegalArgumentsException(DataStructure ds, String operation, Integer[] args, Throwable cause) {
		super("Illegal arguments: " + strFromArray(args) + "to "
	          + DSManager.getName(ds) + "." + operation, cause);
	}
	
	private static String strFromArray(Object[] as) {
		String str = "";
		for (Object a : as) {
			str += a + " ";
		}
		return str;
	}
}
