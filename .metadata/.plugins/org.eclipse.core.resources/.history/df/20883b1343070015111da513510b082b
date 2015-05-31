import java.awt.Color;
import java.awt.Dimension;

import java.awt.geom.Rectangle2D;

import java.util.HashMap;


import javax.swing.JApplet;
import javax.swing.JFrame;
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
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.ListenableDirectedGraph;

public class Drawer extends JApplet {
	private static final long serialVersionUID = 6046505950682742359L;
	private static final Color     DEFAULT_BG_COLOR = Color.decode( "#92E9FF" );
    private static final Dimension DEFAULT_SIZE = new Dimension( 700, 320 );
	private static final int deltaY = 30;
	private JFrame frame;
	private GraphModel model;
	
	public Drawer(){
		frame = new JFrame();
		model = new DefaultGraphModel();
	}
	
	public void drawStructure(JGraph myGraph){
		myGraph.setPreferredSize( DEFAULT_SIZE );
		frame.getContentPane().add(new JScrollPane(myGraph));
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		Drawer d = new Drawer();
		Factory f = new Factory();
		d.draw(f.binarySearchTree(6));
	}
	
	public void lala(){
		GraphModel model = new DefaultGraphModel();
		
		GraphLayoutCache view = new GraphLayoutCache(model,
								new DefaultCellViewFactory());

		JGraph graph = new JGraph(model, view);
		
		DefaultGraphCell[] cells = new DefaultGraphCell[3];
		cells[0] = new DefaultGraphCell(new String("Hello"));
		
		GraphConstants.setBounds(cells[0].getAttributes(), new
								 Rectangle2D.Double(20,20,40,40));
		GraphConstants.setGradientColor(cells[0].getAttributes(),
										Color.orange);
		GraphConstants.setOpaque(cells[0].getAttributes(), true);
		
		DefaultPort port0 = new DefaultPort();
		cells[0].add(port0);
		cells[1] = new DefaultGraphCell(new String("World"));
		
		GraphConstants.setBounds(cells[1].getAttributes(), new
								 Rectangle2D.Double(140,140,60,60));
		GraphConstants.setGradientColor(cells[1].getAttributes(),
										Color.red);
		GraphConstants.setOpaque(cells[1].getAttributes(), true);
		DefaultPort port1 = new DefaultPort();
		cells[1].add(port1);

		DefaultEdge edge = new DefaultEdge();
		edge.setSource(cells[0].getChildAt(0));
		edge.setTarget(cells[1]);
		cells[2] = edge;
		
		int arrow = GraphConstants.ARROW_CLASSIC;
		GraphConstants.setLineEnd(edge.getAttributes(), arrow);
		GraphConstants.setEndFill(edge.getAttributes(), true);
		graph.getGraphLayoutCache().insert(cells);
		
		if(graph != null)
			System.out.println("aaaa");
		drawStructure(graph);
		}

	private void adjustDisplaySettings( JGraph jg ) {
        jg.setPreferredSize( DEFAULT_SIZE );
        Color  c        = DEFAULT_BG_COLOR;
        jg.setBackground( c );
    }

	public void draw(BinarySearchTree myTree){
		
		GraphLayoutCache view = new GraphLayoutCache(model,
								new DefaultCellViewFactory());
		JGraph jgraph = new JGraph( model, view );
	    
	    adjustDisplaySettings( jgraph );
	    getContentPane().add( jgraph );
	    resize( DEFAULT_SIZE );
	    
		int x = DEFAULT_SIZE.width/2;
		int y = 10;
		HashMap<Integer,DefaultGraphCell> cells = new HashMap<Integer, DefaultGraphCell>();
		preOrderCell(cells, myTree.root(), x, y, Color.blue);
		
		jgraph.getGraphLayoutCache().insert(cells.values().toArray());
		
		drawStructure(jgraph);
	}

	void preOrderCell(HashMap<Integer, DefaultGraphCell> c, Node root, int x, int y, Color col){
		if(root != null){
			createMyVertex(c, root, x, y, col);
			if(root.getLeftKid() != null){
				preOrderCell(c, root.getLeftKid(), (int) (x - DEFAULT_SIZE.width/Math.scalb(1., root.nodeLevel())), y + deltaY, Color.blue);
				insertEdge(getDefaultPort((c.get(root.getKey())), model),
						getDefaultPort(c.get(root.getLeftKid().getKey()), model));	
			}
			if(root.getRightKid() != null){
				preOrderCell(c, root.getRightKid(), (int)(x + DEFAULT_SIZE.width/Math.scalb(1., root.nodeLevel())), y + deltaY, Color.blue);
				insertEdge(getDefaultPort((c.get(root.getKey())), model),
						getDefaultPort(c.get(root.getRightKid().getKey()), model));
			}
		}
	}
	
	public String toString(int a){
		return "" + a;
	}
	
	private void createMyVertex(HashMap<Integer, DefaultGraphCell> g, Node n, int x, int y, Color c){
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
	
	void insertEdge( Port source, Port target) {
		 // Create Edge
		DefaultEdge edge = new DefaultEdge();
		// Create ConnectionSet for Insertion
		ConnectionSet cs = new ConnectionSet(edge, source, target);
		// Add Edge and Connections to the Model
		Object[] insert = new Object[]{edge};
		model.insert(insert,null, cs, null, null);
	}
	
	private Port getDefaultPort(Object vertex, GraphModel model) {
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
