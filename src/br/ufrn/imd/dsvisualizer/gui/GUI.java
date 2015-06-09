package br.ufrn.imd.dsvisualizer.gui;

import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

import br.ufrn.imd.dsvisualizer.datastructures.DataStructure;
import br.ufrn.imd.dsvisualizer.datastructures.Factory;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Set;

public class GUI {

	private JFrame frame;
	private JTabbedPane tabs;
	private JPanel homepage;
	
	/**
	 * Launch the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
					window.frame.pack();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu file = new JMenu("File");
		menuBar.add(file);
		
		JMenuItem fileNew = new JMenuItem("New");
		file.add(fileNew);
		
		JMenuItem fileSave = new JMenuItem("Save");
		file.add(fileSave);
		
		JMenuItem fileLoad = new JMenuItem("Load");
		file.add(fileLoad);
		
		JMenu options = new JMenu("Options");
		menuBar.add(options);
		
		JMenuItem optionsPrefs = new JMenuItem("Preferences");
		options.add(optionsPrefs);
		
		JMenuItem optionsAbout = new JMenuItem("About");
		options.add(optionsAbout);
		
		JMenuItem optionsHelp = new JMenuItem("Help");
		options.add(optionsHelp);
		
		tabs = new JTabbedPane();
		frame.getContentPane().add(tabs);
		
		homepage = new JPanel();
		homepage.setLayout(new BoxLayout(homepage, BoxLayout.PAGE_AXIS));
		tabs.addTab("Home", homepage);
		
		JLabel welcome = new JLabel("Welcome to the DSVisualizer!", JLabel.CENTER);
		welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
		welcome.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		homepage.add(welcome);
		
		JPanel structures = new JPanel();
		structures.setLayout(new BoxLayout(structures, BoxLayout.PAGE_AXIS));
		structures.setBorder(BorderFactory.createTitledBorder("Structures"));
		homepage.add(structures);
		
		JPanel lists = new JPanel();
		lists.setLayout(new FlowLayout(FlowLayout.LEFT));
		lists.setBorder(BorderFactory.createTitledBorder("Lists"));
		structures.add(lists);
		
		JButton linkedList = new JButton("Linked List");
		linkedList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newTab(Factory.LIST, "List" + tabs.getComponentCount());
			}
		});
		lists.add(linkedList);
		
		JButton stack = new JButton("Stack");
		stack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newTab(Factory.STACK, "Stack" + tabs.getComponentCount());
			}
		});
		lists.add(stack);
		
		JButton queue = new JButton("Queue");
		queue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newTab(Factory.QUEUE, "Queue" + tabs.getComponentCount());
			}
		});
		lists.add(queue);
		
		JButton deque = new JButton("Deque");
		deque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newTab(Factory.DEQUE, "Deque" + tabs.getComponentCount());
			}
		});
		lists.add(deque);
		
		JPanel trees = new JPanel();
		trees.setLayout(new FlowLayout(FlowLayout.LEFT));
		trees.setBorder(BorderFactory.createTitledBorder("Trees"));
		structures.add(trees);
		
		JButton binaryTree = new JButton("Binary Search Tree");
		binaryTree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newTab(Factory.BST, "BST" + tabs.getComponentCount());
			}
		});
		trees.add(binaryTree);
		
		JButton heapMax = new JButton("HeapMax");
		heapMax.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newTab(Factory.HEAPMAX, "HeapMax" + tabs.getComponentCount());
			}
		});
		trees.add(heapMax);
		
		JButton aVLTree = new JButton("AVL Tree");
		aVLTree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newTab(Factory.AVL, "AVL" + tabs.getComponentCount());
			}
		});
		trees.add(aVLTree);
		
		JButton rBTree = new JButton("Red-Black Tree");
		rBTree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newTab(Factory.RB, "RBTree" + tabs.getComponentCount());
			}
		});
		trees.add(rBTree);
		
		JButton unionFind = new JButton("Union-Find");
		unionFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newTab(Factory.UNIONFIND, "UnionFind" + tabs.getComponentCount());
			}
		});
	}
	
	private void newTab(int selectedDS, String title) {
		DataStructure ds = DSManager.create(selectedDS);
		DSManager.DSActionListener al = DSManager.getActionListener(ds);
		al.setFrame(frame);
		ds.redraw();
		
		JPanel view = new JPanel();
		
		view.setLayout(new BorderLayout(10, 10));
		view.add(ds.getJGraph(), BorderLayout.CENTER);
	
		JPanel operations = new JPanel();
		operations.setLayout(new BoxLayout(operations, BoxLayout.PAGE_AXIS));
		view.add(operations, BorderLayout.WEST);
	
		Set<String> ops = ds.getSupportedOperations();
		for (String op : ops) {
			JButton opButton = new JButton(op);
			opButton.setActionCommand(op);
			opButton.addActionListener(al);
			operations.add(opButton);
		}
		
		tabs.addTab(title, view);
	}
}
