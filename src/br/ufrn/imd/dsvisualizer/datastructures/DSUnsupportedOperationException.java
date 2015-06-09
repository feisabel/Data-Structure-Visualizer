package br.ufrn.imd.dsvisualizer.datastructures;

/**
 * Class to support exceptionds.
 * @author João Pedro Holanda
 *
 */
public class DSUnsupportedOperationException extends RuntimeException {
	private static final long serialVersionUID = -4586266027780129181L;

	public DSUnsupportedOperationException(String operation, Class<?> c) {
		super("Unsupported operation " + operation + " on " + c.getName());
	}

	public DSUnsupportedOperationException(String operation, Class<?> c, Throwable cause) {
		super("Unsupported operation " + operation + " on " + c.getName(), cause);
	}
}
