import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;

import java.util.HashMap;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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


public abstract class Drawer extends JApplet {
	private static final Color     DEFAULT_BG_COLOR = Color.decode( "#FFFFFF" );
    protected static final Dimension DEFAULT_SIZE = new Dimension( 700, 500 );
	protected static final int deltaY = 40;
	protected GraphModel model;
	protected JGraph jgraph;
	protected GraphLayoutCache view;
	
	public abstract JPanel draw();
	
	public Drawer(){
		model = new DefaultGraphModel();
		view = new GraphLayoutCache(model, new DefaultCellViewFactory());
		jgraph = new JGraph( model, view );

		adjustDisplaySettings( jgraph );
		getContentPane().add( jgraph );
		resize( DEFAULT_SIZE );
	}
	protected JPanel jpanel;

	public JPanel drawStructure(JGraph myGraph){
		myGraph.setPreferredSize( DEFAULT_SIZE );
		jpanel = new JPanel();
		jpanel.add(myGraph);		
		return jpanel;
	}
		
	protected void adjustDisplaySettings( JGraph jg ) {
        jg.setPreferredSize( DEFAULT_SIZE );
        Color  c        = DEFAULT_BG_COLOR;
        jg.setBackground( c );
    }
	
	public String toString(int a){
		return "" + a;
	}
	
	protected void createMyVertex(HashMap<Integer, DefaultGraphCell> g, Node n, int x, int y, Color c){
		DefaultGraphCell v = new DefaultGraphCell(new String(toString(n.getKey())));
		g.put(n.getKey(), v);
		DefaultPort port = new DefaultPort();
		v.add(port);
		port.setParent(v);
		GraphConstants.setBounds(v.getAttributes(), new
				 Rectangle2D.Double(x,y,30,15));
		GraphConstants.setGradientColor(v.getAttributes(), c);
		GraphConstants.setOpaque(v.getAttributes(), true);
	
	}
	
	protected void insertEdge( Port source, Port target) {
		 // Create Edge
		DefaultEdge edge = new DefaultEdge();
		// Create ConnectionSet for Insertion
		ConnectionSet cs = new ConnectionSet(edge, source, target);
		// Add Edge and Connections to the Model
		Object[] insert = new Object[]{edge};
		model.insert(insert,null, cs, null, null);
	}
	
	protected Port getDefaultPort(Object vertex, GraphModel model) {
	 // Iterate over all Children
		for(int i = 0; i < model.getChildCount(vertex); i++) {
			// Fetch the Child of Vertex at Index i
			Object child = model.getChild(vertex, i);
		 	// Check if Child is a Portif (child instanceof Port)
		 	// Return the Child as a Port
		 	return (Port) child;
		}
	     // No Ports Found
	    return null;
	 }
}
