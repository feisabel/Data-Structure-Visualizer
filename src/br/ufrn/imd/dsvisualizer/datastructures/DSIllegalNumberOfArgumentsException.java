package br.ufrn.imd.dsvisualizer.datastructures;

/**
 * Defines exceptions used in DataStructure.
 * @author João Pedro Holanda
 *
 */

public class DSIllegalNumberOfArgumentsException extends Exception {
	public DSIllegalNumberOfArgumentsException(String operation, Class<?> c, int expected, int got) {
		super("Illegal number of arguments on " + c.getName() + "." + operation + ":" +
			  "expected " + expected + ", got " + got);
		// TODO Auto-generated constructor stub
	}

	public DSIllegalNumberOfArgumentsException(String operation, Class<?> c, int expected, int got, Throwable cause) {
		super("Illegal number of arguments on " + c.getName() + "." + operation + ":" +
				  "expected " + expected + ", got " + got, cause);
		// TODO Auto-generated constructor stub
	}
}
