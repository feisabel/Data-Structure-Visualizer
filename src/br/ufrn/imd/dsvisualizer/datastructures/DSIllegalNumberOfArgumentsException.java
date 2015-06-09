package br.ufrn.imd.dsvisualizer.datastructures;

/**
 * Defines exceptions used in DataStructure.
 * @author João Pedro Holanda
 *
 */

public class DSIllegalNumberOfArgumentsException extends Exception {
	private static final long serialVersionUID = 5782646695153433654L;

	public DSIllegalNumberOfArgumentsException(String operation, DataStructure ds, int expected, int got) {
		super("Illegal number of arguments on " + ds.getShortName() + "." + operation + ":" +
			  "expected " + expected + ", got " + got);
		// TODO Auto-generated constructor stub
	}

	public DSIllegalNumberOfArgumentsException(String operation, DataStructure ds, int expected, int got, Throwable cause) {
		super("Illegal number of arguments on " + ds.getShortName() + "." + operation + ":" +
				  "expected " + expected + ", got " + got, cause);
		// TODO Auto-generated constructor stub
	}
}
