package br.ufrn.imd.dsvisualizer.datastructures;

import br.ufrn.imd.dsvisualizer.gui.DSManager;

/**
 * Class to support exceptions.
 * @author João Pedro Holanda
 */
public class DSUnsupportedOperationException extends RuntimeException {
	private static final long serialVersionUID = -4586266027780129181L;

	/**
	 * Constructor. Generates custom message.
	 * @param operation  failed operation
	 * @param ds  data structure on which the operation failed
	 */
	public DSUnsupportedOperationException(DataStructure ds, String operation) {
		super("Unsupported operation " + operation + " on " + DSManager.getName(ds));
	}
	
	/**
	 * Constructor. Generates custom message.
	 * @param operation  failed operation
	 * @param ds  data structure on which the operation failed
	 * @param cause  exception 
	 */
	public DSUnsupportedOperationException(DataStructure ds, String operation, Throwable cause) {
		super("Unsupported operation " + operation + " on " + DSManager.getName(ds), cause);
	}
}
