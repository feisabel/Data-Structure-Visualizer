package br.ufrn.imd.dsvisualizer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

import org.jgraph.JGraph;

abstract public class DataStructure {
	// data structure drawer
	protected Drawer drawer;
	
	 // associates each operation to a minimum number of arguments
	private HashMap<String, Integer> operationNumOfParams;

	/**
	 * Default constructor.
	 */
	public DataStructure() {
		// initializes HashMap
		operationNumOfParams = new HashMap<String, Integer>();
	}
	
	/* Draw-related methods */
	
	/**
	 * Redraws the data structure on the screen.
	 */
	public void draw() {
		drawer.draw();
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
	protected void support(String command, int numOfParams) {
		operationNumOfParams.put(command, numOfParams);
	}
	
	/**
	 * Calls the command, passing it the given parameters.
	 * @param command  command to execute
	 * @param params   parameters to be passed to the command
	 * @throws DSIllegalNumberOfArgumentsException
	 * @throws DSUnsupportedOperationException
	 */
	public void call(String command, int[] params)
			throws DSIllegalNumberOfArgumentsException, DSUnsupportedOperationException
	{
		Set<String> operations = operationNumOfParams.keySet();
		boolean success = false;
		
		for(String operation : operations) {
			if (command.equals(operation)) {
				int numOfParams = operationNumOfParams.get(operation);
				
				if (params.length < numOfParams)
					throw new DSIllegalNumberOfArgumentsException(command, this.getClass(), numOfParams, params.length);
				Class<?>[] paramsTypes = new Class<?>[params.length];
				Arrays.fill(paramsTypes, int.class);
				
				Method method = null;
				try {
					method = this.getClass().getMethod(command, paramsTypes);
					for (int i=0; i < params.length - numOfParams; i++) {
						method.invoke(this, Arrays.copyOfRange(params, i, i + numOfParams + 1));
					}
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
