package br.ufrn.imd.dsvisualizer.datastructures;

/**
 * Class to support exceptions.
 * @author João Pedro Holanda
 *
 */
public class DSUnsupportedOperationException extends RuntimeException {
	private static final long serialVersionUID = -4586266027780129181L;

	/**
	 * Constructor. Generates custom message.
	 * @param operation  failed operation
	 * @param ds  data structure on which the operation failed
	 */
	public DSUnsupportedOperationException(String operation, DataStructure ds) {
		super("Unsupported operation " + operation + " on " + ds.getShortName());
	}
	
	/**
	 * Constructor. Generates custom message.
	 * @param operation  failed operation
	 * @param ds  data structure on which the operation failed
	 * @param cause  exception 
	 */
	public DSUnsupportedOperationException(String operation, DataStructure ds, Throwable cause) {
		super("Unsupported operation " + operation + " on " + ds.getShortName(), cause);
	}
}
