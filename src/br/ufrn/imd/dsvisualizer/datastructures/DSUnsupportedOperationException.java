package br.ufrn.imd.dsvisualizer.datastructures;

/**
 * Class to support exceptionds.
 * @author João Pedro Holanda
 *
 */
public class DSUnsupportedOperationException extends RuntimeException {
	public DSUnsupportedOperationException(String operation, Class<?> c) {
		super("Unsupported operation " + operation + " on " + c.getName());
	}

	public DSUnsupportedOperationException(String operation, Class<?> c, Throwable cause) {
		super("Unsupported operation " + operation + " on " + c.getName(), cause);
	}
}
