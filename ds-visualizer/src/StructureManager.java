import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class StructureManager extends JApplet {
	private Factory myFactory;
	private JFrame jframe;
	
	public StructureManager(){
		myFactory = new Factory();
		jframe = new JFrame();
	}
	
	public void show(JPanel jpanel){
		jframe.add(jpanel);
		jframe.pack();
		jframe.setSize(500, 350);
		jframe.setVisible(true);
	}
	
	public static void main(String[] args) {
		Factory f = new Factory();
		StructureManager s = new StructureManager();
		s.show( ( (BinarySearchTree) Factory.create(0, 10) ).draw());
		s.show( ( (HeapFake) Factory.create(1, 10) ).draw());
	}
}
