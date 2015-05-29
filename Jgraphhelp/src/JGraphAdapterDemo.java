import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingDeque;

import javax.swing.JApplet;
import javax.swing.JFrame;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;

import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.ListenableDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class JGraphAdapterDemo extends JApplet {
	private static final long serialVersionUID = 6046505950682742359L;
	private static final Color     DEFAULT_BG_COLOR = Color.decode( "#92E9FF" );
    private static final Dimension DEFAULT_SIZE = new Dimension( 530, 320 );

    // 
    private JGraphModelAdapter m_jgAdapter;

    public static void main(String[] args){
    	System.out.println("chorando mto");
    	JGraphAdapterDemo a = new JGraphAdapterDemo();
    	BinarySearchTree tree = new BinarySearchTree();
    	tree.insert(3);
    	tree.insert(1);
    	tree.insert(4);
    	System.out.println("chorando");
    	a.draw(tree);
    }

    private void adjustDisplaySettings( JGraph jg ) {
        jg.setPreferredSize( DEFAULT_SIZE );

        Color  c        = DEFAULT_BG_COLOR;
        String colorStr = null;

        try {
            colorStr = getParameter( "bgcolor" );
        }
         catch( Exception e ) {}

        if( colorStr != null ) {
            c = Color.decode( colorStr );
        }	
        
        jg.setBackground( c );
    }

    private void positionVertexAt( Object vertex, int x, int y ) {
        DefaultGraphCell cell = m_jgAdapter.getVertexCell( vertex );
        Map              attr = cell.getAttributes(  );
        RectangularShape b    = GraphConstants.getBounds( attr );
        Rectangle c = b.getBounds();
        GraphConstants.setBounds( attr, new Rectangle( x, y, c.width, c.height ) );
        //GraphConstants.setAutoSize(attr, true);
        GraphConstants.setForeground(
				cell.getAttributes(), Color.green); //MUDA A PORRA DA COR DA LETRA
        GraphConstants.setBackground(
				cell.getAttributes(), Color.blue); //MUDA A PORRA DA COR DA LETRA
        
        Map cellAttr = new HashMap(  );
        cellAttr.put( cell, attr );
        m_jgAdapter.edit( cellAttr, null, null, null );
    }
    
    private Stack<Node> makeQueue(Node myRoot){
    	Stack<Node> myStackHelp = new Stack<Node>();
    	if(myRoot != null){
    		LinkedBlockingDeque<Node> myQueue = new LinkedBlockingDeque<Node>();
	    	myQueue.add(myRoot);
	    	myStackHelp.add(myRoot);
	    	while(!myQueue.isEmpty()){
	    		Node n = myQueue.pop();
	    		if(n != null){
	    			myQueue.add(n.getLeftKid());
	    			myQueue.add(n.getRightKid());
	    			myStackHelp.add(n.getLeftKid());
	    			myStackHelp.add(n.getRightKid());
	    		}
	    	}
    	}
    	for(Node n : myStackHelp)
    		System.out.println(n.getKey());
    	
    	return myStackHelp;
    }
    
    public void draw(BinarySearchTree myTree){
    	ListenableGraph g = new ListenableDirectedGraph( DefaultEdge.class );
        // create a visualization using JGraph, via an adapter
        m_jgAdapter = new JGraphModelAdapter( g );
        JGraph jgraph = new JGraph( m_jgAdapter );
        
        adjustDisplaySettings( jgraph );
        getContentPane(  ).add( jgraph );
        resize( DEFAULT_SIZE );
        
    	int numberNodes = myTree.getNumberOfNodes();
    	int lala = (int) ((int) Math.log10(numberNodes)/Math.log10(2));
    	int deltaY = DEFAULT_SIZE.width / ((int)lala);
    	int deltaX = DEFAULT_SIZE.height / exp(lala);
    	int x = DEFAULT_SIZE.height-30;
    	int y = DEFAULT_SIZE.width-30;
    	Node root = myTree.root();
    	if(root != null)
    		System.out.println("LAALALALAL");
    	else
    		System.out.println("CHOREI");
    	HashMap<Integer, Dimension> vertexs = new HashMap<Integer, Dimension>();
    	Stack<Node> myStack = makeQueue(root);
    	while(!myStack.isEmpty()){
    		Node n = myStack.pop();
    		g.addVertex(toString(n.getKey()));
    		if(!n.isLeaf()){
    			int dx = 0, dy = 0;
    			Dimension d = null;
    			if(n.getLeftKid() != null){
    				g.addEdge(toString(n.getKey()), toString(n.getLeftKid().getKey()));
    				d = vertexs.get(n.getLeftKid().getKey());
    				dx+= d.width;
    			}
    			if(n.getRightKid() != null){
    				g.addEdge(toString(n.getKey()), toString(n.getRightKid().getKey()));
    				d = vertexs.get(n.getRightKid().getKey());
    				dx+= d.width;
    				dy+= d.height;
    			}
    			dy += deltaY;
    			positionVertexAt(toString(n.getKey()), dx, (int)dy/2);
    			vertexs.put(n.getKey(), new Dimension(dx, (int)dy/2));
    		}else{
    			positionVertexAt( toString(n.getKey()), x, y );
    			vertexs.put(n.getKey(), new Dimension(x,y));
    			x-=deltaX;
    		}
    	}
    	g.addVertex("OI");
    	positionVertexAt("OI", 10, 10);
    }
    public String toString(int a){
    	return "" + a;
    }
    public int exp(int a){
    	int b = 1;
    	while(a > 0){
    		b*=2;
    		a--;
    	}
    	return b;
    }
}