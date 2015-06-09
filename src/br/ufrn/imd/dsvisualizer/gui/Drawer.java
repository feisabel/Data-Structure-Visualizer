package br.ufrn.imd.dsvisualizer.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import javax.swing.JApplet;
import javax.swing.JPanel;

import org.jgraph.JGraph;
import org.jgraph.graph.ConnectionSet;
import org.jgraph.graph.DefaultCellViewFactory;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.DefaultPort;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.GraphModel;
import org.jgraph.graph.Port;

/**
 * Class Drawer helps to draw the structures, contains methods to creates vertexes end edges.
 * @author Ana Caroline, João Pedro Holanda
 *
 */
public abstract class Drawer extends JApplet {
	private static final long serialVersionUID = 1L;
	private static final Color     DEFAULT_BG_COLOR = Color.decode( "#FFFFFF" );
    protected static final Dimension DEFAULT_SIZE = new Dimension( 500, 300 );
	protected static final int deltaY = 40;
	protected GraphModel model;
	protected JGraph jgraph;
	protected GraphLayoutCache view;
	protected HashMap<Integer, DefaultGraphCell> cells;
	
	public abstract void draw();
	
	public void clear() {
		jgraph.getGraphLayoutCache().remove(cells.values().toArray());
		cells = new HashMap<Integer, DefaultGraphCell>();
	}
	
	public void redraw() {
		clear();
		draw();
	}
	
	/*
	 * Constructor with no parameters.
	 */
	public Drawer(){
		cells = new HashMap<Integer, DefaultGraphCell>();
		model = new DefaultGraphModel();
		view = new GraphLayoutCache(model, new DefaultCellViewFactory());
		jgraph = new JGraph( model, view );
		jgraph.setPreferredSize( DEFAULT_SIZE );
		
		adjustDisplaySettings( jgraph );
		getContentPane().add( jgraph );
		resize( DEFAULT_SIZE );
	}
	
	/**
	 * Access method jgraph.
	 * @return the graph
	 */
	public JGraph getJGraph(){
		return jgraph;
	}
	
	/**
	 * Does some adjusts. 
	 * @param jg a graph to be adjusted
	 */
	protected void adjustDisplaySettings( JGraph jg ) {
        jg.setPreferredSize( DEFAULT_SIZE );
        Color  c        = DEFAULT_BG_COLOR;
        jg.setBackground( c );
    }
	
	/**
	 * Casts int to string.
	 * @param a int
	 * @return string
	 */
	public String toString(int a){
		return "" + a;
	}
	
	/**
	 * Method that create a new vertex.
	 * @param g hashmap with the cells
	 * @param n node's key
	 * @param x position x
	 * @param y position y
	 * @param c node's color
	 */
	protected void createMyVertex(int n, int x, int y, java.awt.Color c){
		DefaultGraphCell v = new DefaultGraphCell(new String(toString(n)));
		cells.put(n, v);
		DefaultPort port = new DefaultPort();
		v.add(port);
		port.setParent(v);
		GraphConstants.setBounds(v.getAttributes(), new Rectangle2D.Double(x,y,30,15));
		GraphConstants.setGradientColor(v.getAttributes(), c);
		GraphConstants.setOpaque(v.getAttributes(), true);
	}
	
	/**
	 * Inserts an edge given a port source and a port target.
	 * @param source the edge is from here
	 * @param target the edge is to here
	 */
	protected void insertEdge( Port source, Port target) {
		 // Create Edge
		DefaultEdge edge = new DefaultEdge();
		// Create ConnectionSet for Insertion
		ConnectionSet cs = new ConnectionSet(edge, source, target);
		int arrow = GraphConstants.ARROW_CLASSIC;
		GraphConstants.setLineEnd(edge.getAttributes(), arrow);
		GraphConstants.setEndFill(edge.getAttributes(), true);
		// Add Edge and Connections to the Model
		Object[] insert = new Object[]{edge};
		model.insert(insert,null, cs, null, null);
	}
	
	/**
	 * Returns a port, structure used by the jgraph library.
	 * @param vertex current vertex
	 * @param model model used to create the jgraph
	 * @return current vertex's port
	 */
	protected Port getDefaultPort(Object vertex, GraphModel model) {
		for(int i = 0; i < model.getChildCount(vertex); i++) {
			Object child = model.getChild(vertex, i);
		 	return (Port) child;
		}
	     // No Ports Found
	    return null;
	 }
}
