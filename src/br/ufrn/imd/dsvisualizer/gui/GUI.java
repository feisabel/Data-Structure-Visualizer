package br.ufrn.imd.dsvisualizer.gui;

import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeSelectionModel;

import br.ufrn.imd.dsvisualizer.datastructures.DataStructure;
import br.ufrn.imd.dsvisualizer.datastructures.Factory;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.regex.Pattern;

import javax.swing.tree.DefaultTreeModel;

public class GUI {

	private JFrame frame;
	private JTabbedPane tabs;
	private JPanel homepage;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (UnsupportedLookAndFeelException e1) {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
				e1.printStackTrace();
			}
		} catch (Exception e) {
			System.err.println("Error: could not initialize application.");
			e.printStackTrace();
		}
		
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
	 * Initialize the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		createMenuBar();
		createContent();
		
		frame.pack();
	}
	
	/**
	 * Create menu bar for the frame. 
	 */
	private void createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu file = new JMenu("File");
		menuBar.add(file);
		
		JMenuItem fileNew = new JMenuItem("New");
		fileNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabs.removeAll();
				DSManager.destroyAll();
				tabs.addTab("Home", homepage);
				frame.pack();
			}
		});
		file.add(fileNew);
		
		JMenuItem fileSave = new JMenuItem("Save");
		fileSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser saver = new JFileChooser();
				
				int rVal = saver.showSaveDialog(frame);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					saveToFile(saver.getSelectedFile());
				}
			}
		});
		file.add(fileSave);
		
		JMenuItem fileLoad = new JMenuItem("Load");
		fileLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser loader = new JFileChooser();
				
				int rVal = loader.showOpenDialog(frame);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					loadFromFile(loader.getSelectedFile());
				}
			}
		});
		file.add(fileLoad);
		
		JMenu options = new JMenu("Options");
		menuBar.add(options);
		
		JMenuItem optionsAbout = new JMenuItem("About");
		optionsAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JLabel text = new JLabel(getAboutString());
				text.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
				JOptionPane.showMessageDialog(frame, text, "About", JOptionPane.PLAIN_MESSAGE, null);
			}
		});
		options.add(optionsAbout);
		
		JMenuItem optionsHelp = new JMenuItem("Help");
		optionsHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file = new File("");
				JOptionPane.showMessageDialog(frame, "Open " + file.getAbsolutePath() + "/help/usermanual.pdf"
						                      + " to access the user manual.", null, JOptionPane.PLAIN_MESSAGE);
			}
		});
		options.add(optionsHelp);
	}
	
	/**
	 * Create frame content.
	 */
	private void createContent() {
		tabs = new JTabbedPane();
		frame.getContentPane().add(tabs);
		
		homepage = new JPanel();
		homepage.setLayout(new BoxLayout(homepage, BoxLayout.PAGE_AXIS));
		tabs.addTab("Home", homepage);
		
		JLabel welcome = new JLabel("Welcome to the DSVisualizer!", JLabel.CENTER);
		welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
		welcome.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		homepage.add(welcome);
		
		homepage.add(Box.createGlue());
		
		JPanel lists = new JPanel();
		lists.setLayout(new FlowLayout(FlowLayout.CENTER));
		lists.setBorder(BorderFactory.createTitledBorder("Lists"));
		homepage.add(lists);
		
		JButton linkedList = new JButton("Linked List");
		linkedList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataStructure ds = DSManager.create(Factory.LIST);
				newTab(ds, ds.getShortName() + tabs.getComponentCount());
			}
		});
		lists.add(linkedList);
		
		JButton stack = new JButton("Stack");
		stack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataStructure ds = DSManager.create(Factory.STACK);
				newTab(ds, ds.getShortName() + tabs.getComponentCount());
			}
		});
		lists.add(stack);
		
		JButton queue = new JButton("Queue");
		queue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataStructure ds = DSManager.create(Factory.QUEUE);
				newTab(ds, ds.getShortName() + tabs.getComponentCount());
			}
		});
		lists.add(queue);
		
		JButton deque = new JButton("Deque");
		deque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataStructure ds = DSManager.create(Factory.DEQUE);
				newTab(ds, ds.getShortName() + tabs.getComponentCount());
			}
		});
		lists.add(deque);
		
		JPanel trees = new JPanel();
		trees.setLayout(new FlowLayout(FlowLayout.CENTER));
		trees.setBorder(BorderFactory.createTitledBorder("Trees"));
		homepage.add(trees);
		
		JButton binaryTree = new JButton("Binary Search Tree");
		binaryTree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataStructure ds = DSManager.create(Factory.BST);
				newTab(ds, ds.getShortName() + tabs.getComponentCount());
			}
		});
		trees.add(binaryTree);
		
		JButton heapMax = new JButton("HeapMax");
		heapMax.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataStructure ds = DSManager.create(Factory.HEAPMAX);
				newTab(ds, ds.getShortName() + tabs.getComponentCount());
			}
		});
		trees.add(heapMax);
		
		JButton heapMin = new JButton("HeapMin");
		heapMin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataStructure ds = DSManager.create(Factory.HEAPMIN);
				newTab(ds, ds.getShortName() + tabs.getComponentCount());
			}
		});
		trees.add(heapMin);
		
		JButton aVLTree = new JButton("AVL Tree");
		aVLTree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataStructure ds = DSManager.create(Factory.AVL);
				newTab(ds, ds.getShortName() + tabs.getComponentCount());
			}
		});
		trees.add(aVLTree);
		
		JButton rBTree = new JButton("Red-Black Tree");
		rBTree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataStructure ds = DSManager.create(Factory.RB);
				newTab(ds, ds.getShortName() + tabs.getComponentCount());
			}
		});
		trees.add(rBTree);
		
		JButton unionFind = new JButton("Union-Find");
		unionFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataStructure ds = DSManager.create(Factory.UNIONFIND);
				newTab(ds, ds.getShortName() + tabs.getComponentCount());
			}
		});
		trees.add(unionFind);
	}
	
	/**
	 * Create new tab with given data structure and title.
	 * @param ds  data structure to be manipulated
	 * @param title  title of the tab
	 */
	private void newTab(final DataStructure ds, String title) {
		ds.redraw();
		
		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				String command = ev.getActionCommand();
				String[] paramsStr = ds.getParams(command);
				Integer[] params = new Integer[paramsStr.length];
				
				try {
					for (int i = 0; i < params.length; i++) {
						String input = (String)JOptionPane.showInputDialog(frame, paramsStr[i],
															"", JOptionPane.PLAIN_MESSAGE);
						params[i] = Integer.parseInt(input);
					}
					
					try {
						ds.call(command, params);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(frame, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
					}
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frame, "Error: Invalid number!", "", JOptionPane.ERROR_MESSAGE);
				}
				
				ds.redraw();
			}
		};
		
		final JPanel view = new JPanel();
		view.setLayout(new BorderLayout(10, 10));
		view.add(new JScrollPane(ds.getJGraph()), BorderLayout.CENTER);
		
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
		
		operations.add(Box.createGlue());
		
		JButton close = new JButton("close");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    int i = tabs.indexOfComponent(view);
			    if (i != -1) {
			    	tabs.remove(i);
			    }
			    DSManager.destroy(ds);
			    frame.pack();
			}
		});
		operations.add(close);
		
		tabs.addTab(title, view);
		tabs.setSelectedIndex(tabs.getComponentCount()-1);
		frame.pack();
	}
	
	/**
	 * Save data structures to file.
	 * @param file  file to save to
	 */
	private void saveToFile(File file) {
		String filename = file.getAbsolutePath();
		String[] format = filename.split(Pattern.quote("."));
		if (format.length > 0) {
			String ext = format[format.length-1];
			if (!ext.equals("dsvisu")) {
				filename += ".dsvisu";
			}
		}
		
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(filename);
			DSManager.saveDataStructures(fos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Error: Could not create or open " + filename,
					                             "Error", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Error: Could not save into " + filename,
					                             "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Load data structures from file.
	 * @param file  file to be loaded from
	 */
	private void loadFromFile(File file) {
		tabs.removeAll();
		DSManager.destroyAll();
		tabs.addTab("Home", homepage);
		
		String filename = file.getAbsolutePath();
		String[] format = filename.split(Pattern.quote("."));
		String ext = format[format.length-1];
		if (!ext.equals("dsvisu")) {
			JOptionPane.showMessageDialog(frame, "Error: Could not load from " + filename,
                    "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			
			DSManager.loadDataStructures(fis);
			
			for (DataStructure ds : DSManager.getDataStructures()) {
				newTab(ds, ds.getShortName() + tabs.getComponentCount());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Error: Could not open " + filename,
                    "Error", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Error: Could not load from " + filename,
                    "Error", JOptionPane.ERROR_MESSAGE);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Error: Could not load from " + filename + 
					                           ": File is corrupted", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Gets the 'about' dialog content. 
	 * @return  application info
	 */
	private String getAboutString() {
		return "<html><b>Version:</b> 0.1.0<br>" +
	           "<b>Authors:</b><br>" +
	           "&nbsp;&nbsp;&nbsp;&nbsp;Ana Caroline Medeiros Brito<br>" +
			   "&nbsp;&nbsp;&nbsp;&nbsp;Fernanda Menezes Paes Isabel<br>" +
	           "&nbsp;&nbsp;&nbsp;&nbsp;João Pedro Alencar Rocha de Holanda<br>" +
			   "&nbsp;&nbsp;&nbsp;&nbsp;Leonardo Bezerra Leibovitz<br>" +
	           "<b>Institution:</b><br>" +
			   "&nbsp;&nbsp;&nbsp;&nbsp;Instituto Metrópole Digital/UFRN</html>";
	}
}
