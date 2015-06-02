import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class StructureManager extends JApplet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; //o que deabos é isso?
	private JFrame jframe;
	
	public StructureManager(){
		jframe = new JFrame("DS-Visualizer");
		jframe.setSize(700, 400);
		jframe.setName("DS-Visualizer");
		
		System.out.println(jframe.getSize().width);
	}
	
	public void show(JPanel jpanel){
		//configurar jpanel aqui também
		jframe.add(jpanel);
	//	jframe.pack();
		jframe.setSize(500, 350);
		jframe.setVisible(true);
	}
	
	public static void main(String[] args) {
		StructureManager s = new StructureManager();
//		s.show( ( (BinarySearchTree) Factory.create(0, 10) ).draw());
		s.show( ( (HeapFake) Factory.create(1, 10) ).draw());
	}
}
