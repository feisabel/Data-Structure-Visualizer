package br.ufrn.imd.dsvisualizer;

public class DSUnsupportedOperationException extends RuntimeException {
	public DSUnsupportedOperationException(String operation, Class<?> c) {
		super("Unsupported operation " + operation + " on " + c.getName());
	}

	public DSUnsupportedOperationException(String operation, Class<?> c, Throwable cause) {
		super("Unsupported operation " + operation + " on " + c.getName(), cause);
	}
}
