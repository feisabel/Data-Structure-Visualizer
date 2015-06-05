package br.ufrn.imd.dsvisualizer.gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.ufrn.imd.dsvisualizer.datastructures.DataStructure;
import br.ufrn.imd.dsvisualizer.datastructures.Factory;

public final class DSManager {
	static private HashMap<DataStructure, DSActionListener> actionListeners = new HashMap<DataStructure, DSActionListener>();
	static private Random rand = new Random();
	
	private DSManager() {}
	
	public static DataStructure create(int dataStructure) {
		DataStructure ds = Factory.create(dataStructure, rand.nextInt(9));
		actionListeners.put(ds, new DSManager.DSActionListener(ds));
		return ds;
	}
	
	public static DSActionListener getActionListener(DataStructure ds) {
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
		
		public void actionPerformed(ActionEvent ev) {
			String command = ev.getActionCommand();
			String[] input = ((String)JOptionPane.showInputDialog(frame, "Numbers to " + command + ":",
													"", JOptionPane.PLAIN_MESSAGE)).split(" *");
			int[] args = new int[input.length];
			for (int i = 0; i < args.length; i++) {
				args[i] = Integer.parseInt(input[i]);
			}
			
			try {
				ds.call(command, args);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(frame, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
			
			ds.draw();
		}
	}
}
