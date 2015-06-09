package br.ufrn.imd.dsvisualizer.datastructures;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

import org.jgraph.JGraph;

import br.ufrn.imd.dsvisualizer.gui.Drawer;

/**
 * Defines methods to draw a structure.
 * @author Ana Caroline, João Pedro Holanda
 *
 */
abstract public class DataStructure {
	// data structure drawer
	protected Drawer drawer;
	
	 // associates each operation to a minimum number of arguments
	private HashMap<String, String[]> operationParams;

	/**
	 * Default constructor.
	 */
	public DataStructure() {
		// initializes HashMap
		operationParams = new HashMap<String, String[]>();
	}
	
	/* Draw-related methods */
	
	/**
	 * Redraws the data structure on the screen.
	 */
	public void redraw() {
		drawer.redraw();
	}
	
	
	
	/**
	 * Gets the graph representation of the data structure.
	 * @return  the JGraph associated to this structure 
	 */
	public JGraph getJGraph() {
		return drawer.getJGraph();
	}
		
	/* Call-related methods */	
	
	/**
	 * Adds command to the list of supported operations
	 * @param command  command to be supported
	 * @param numOfParams command's minimum number of parameters
	 */
	protected void support(String command, String... params) {
		operationParams.put(command, params);
	}
	
	/**
	 * Gets the set of operations supported by this data structure.
	 * @return set of operations supported by this data structure
	 */
	public Set<String> getSupportedOperations() {
		return operationParams.keySet();
	}
	
	/**
	 * Gets the number of parameters accepted by the given operation
	 * @param operation  selected operation, must be defined on this data structure 
	 * @return  number of parameters accepted by the given operation
	 */
	public String[] getParams(String operation) {
		return operationParams.get(operation);
	}
	
	/**
	 * Calls the command, passing it the given parameters.
	 * @param command  command to execute
	 * @param params   parameters to be passed to the command
	 * @throws DSIllegalNumberOfArgumentsException
	 * @throws DSUnsupportedOperationException
	 */
	public void call(String command, Integer... params)
			throws DSIllegalNumberOfArgumentsException, DSUnsupportedOperationException
	{
		Set<String> operations = operationParams.keySet();
		boolean success = false;
		
		for(String operation : operations) {
			if (command.equals(operation)) {
				int numOfParams = operationParams.get(operation).length;
				
				if (params.length != numOfParams)
					throw new DSIllegalNumberOfArgumentsException(command, this.getClass(), numOfParams, params.length);
				Class<?>[] paramsTypes = new Class<?>[params.length];
				Arrays.fill(paramsTypes, int.class);
				
				Method method = null;
				try {
					method = this.getClass().getMethod(command, paramsTypes);
					method.invoke(this, params);
				} catch (Exception e) {
					e.printStackTrace();
					throw new DSUnsupportedOperationException(command, this.getClass());
				}
				
				success = true;
			}
		}
		
		if (!success)
			throw new DSUnsupportedOperationException(command, this.getClass());
	}
}
