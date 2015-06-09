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
			String[] paramsStr = ds.getParams(command);
			Integer[] params = new Integer[paramsStr.length];
			
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
			
			ds.redraw();
		}
	}
}
