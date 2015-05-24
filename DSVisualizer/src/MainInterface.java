import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 
 */

/**
 * @author Ana Caroline
 *
 */
public class MainInterface {
	private Frame mainFrame;
	private Panel controlPanelStructure;
	private static MyCanvas myStructure;
	public MainInterface(){
		prepareGUI();
	}
	
	private void prepareGUI(){
		myStructure = new MyCanvas(new StructureDesigner());
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
		// TODO Auto-generated method stub
		
		MainInterface test = new MainInterface();
		test.showDemo();
	}

}
