import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

/**
 * @author Ana Caroline
 *
 */
public class MainInterface {
	private Frame mainFrame;
	private Panel controlPanelStructure;
	private MyCanvas myStructure;
	public MainInterface(){
		prepareGUI();
	}
	
	private void prepareGUI(){
		myStructure = new MyCanvas(500, 500);
		mainFrame = new Frame("DSVisualizer");
		mainFrame.setSize(700,700);
		mainFrame.setLayout(new GridLayout(1, 1)); //PARA QUE SERVE ESSA BAGAÇA?
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}	        
		});
		controlPanelStructure = new Panel();
		controlPanelStructure.setLayout(new FlowLayout());
		mainFrame.add(controlPanelStructure);
		mainFrame.setVisible(true);
	}
	
	/*
	 * Recebe o canvas que desenha a estrutura atual que o usuário quer ver.
	 */
	public void showDemo(){
		controlPanelStructure.add(myStructure);
		mainFrame.setVisible(true);
	}

	public static void main(String[] args) {
		MainInterface test = new MainInterface();
		test.showDemo();
	}
	
	public void createListInt(){
		LinkedList myList = new LinkedList<Integer>();
		NodeDesigner node1 = new NodeDesigner(myStructure);
		NodeDesigner node2 = new NodeDesigner(myStructure);
		NodeDesigner node3 = new NodeDesigner(myStructure);
		myList.add(1, 1); myStructure.draw(node1);
		myList.add(2, 2); myStructure.draw(node2);
		myList.add(3, 3); myStructure.draw(node3);
	}
}
