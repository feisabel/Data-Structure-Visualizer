package br.ufrn.imd.dsvisualizer;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public final class DSManager {
	static private HashMap<DataStructure, ActionListener> actionListeners = new HashMap<DataStructure, ActionListener>();
	static private Random rand = new Random();
	
	private DSManager() {}
	
	public static DataStructure create(int dataStructure) {
		DataStructure ds = Factory.create(dataStructure, rand.nextInt(9));
		actionListeners.put(ds, new DSManager.DSActionListener(ds));
		return ds;
	}
	
	public static ActionListener getActionListener(DataStructure ds) {
		return actionListeners.get(ds);
	}
	
	public static class DSActionListener implements ActionListener {
		private DataStructure ds;
		private JFrame frame;
		
		public DSActionListener(DataStructure ds) {
			this.ds = ds;
		}
		
		public void setFrame(JFrame frame) {
			this.frame = frame;
		}
		
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			String arg = (String)JOptionPane.showInputDialog(frame, "Number to " + command + ":",
													"", JOptionPane.PLAIN_MESSAGE, null, null, null);
			int num = Integer.parseInt(arg);
			if (command.startsWith("search")) {
				ds.search(num);
			} else if (command.startsWith("insert")) {
				ds.insert(num);
			} else {
				ds.remove(num);
			}
			ds.draw();
		}
	}
}
