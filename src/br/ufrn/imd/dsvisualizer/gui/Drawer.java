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

/*Responsável pelos métodos que facilitam o desenho das estruturas 
 * e também pelo método que a desenha, cada filho implementa a sua
 * própria maneira de se desenhar, portanto trata-se de uma classe
 * abstrata.*/
public abstract class Drawer extends JApplet {
	private static final long serialVersionUID = 1L;
	private static final Color     DEFAULT_BG_COLOR = Color.decode( "#FFFFFF" );
    protected static final Dimension DEFAULT_SIZE = new Dimension( 500, 300 );
	protected static final int deltaY = 40;
	protected GraphModel model;
	protected JGraph jgraph;
	protected GraphLayoutCache view;
	
	public abstract void draw();
	
	/*Construtur sem parâmetros.
	 * */
	public Drawer(){
		model = new DefaultGraphModel();
		view = new GraphLayoutCache(model, new DefaultCellViewFactory());
		jgraph = new JGraph( model, view );
		jgraph.setPreferredSize( DEFAULT_SIZE );
		
		adjustDisplaySettings( jgraph );
		getContentPane().add( jgraph );
		resize( DEFAULT_SIZE );
	}
	
	/*Retorna o JGraph.
	 * 
	 * */
	public JGraph getJGraph(){
		return jgraph;
	}
	
	/*Responsável por alguns ajustes de tamanho e cor do background.
	 * */	
	protected void adjustDisplaySettings( JGraph jg ) {
        jg.setPreferredSize( DEFAULT_SIZE );
        Color  c        = DEFAULT_BG_COLOR;
        jg.setBackground( c );
    }
	
	/*Recebe um inteiro e retorna uma string.
	 * */
	public String toString(int a){
		return "" + a;
	}
	
	/*Método responsável por criar um vértice novo dado um inteiro que ser o valor do
	 *node, os inteiros x e y das coordenadas e uma cor, o HashMap é utilizado para mapear
	 *os nodes e vértices que já foram criados.
	 * */
	protected void createMyVertex(HashMap<Integer, DefaultGraphCell> g, int n, int x, int y, java.awt.Color c){
		DefaultGraphCell v = new DefaultGraphCell(new String(toString(n)));
		g.put(n, v);
		DefaultPort port = new DefaultPort();
		v.add(port);
		port.setParent(v);
		GraphConstants.setBounds(v.getAttributes(), new
				 Rectangle2D.Double(x,y,30,15));
		GraphConstants.setGradientColor(v.getAttributes(), c);
		GraphConstants.setOpaque(v.getAttributes(), true);
	
	}
	
	/*Insere um vértice dado um node de onde ele sai (source) e um node para onde
	 *ele vai.
	 * */
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
	
	/*Dado um objeto, um vértice, retorna um port (tipo específico da biblioteca JGraph).
	 * */
	protected Port getDefaultPort(Object vertex, GraphModel model) {
		for(int i = 0; i < model.getChildCount(vertex); i++) {
			Object child = model.getChild(vertex, i);
		 	return (Port) child;
		}
	     // No Ports Found
	    return null;
	 }
}
