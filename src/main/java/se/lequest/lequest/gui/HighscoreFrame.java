package se.lequest.lequest.gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import se.lequest.lequest.highscore.*;
import java.util.*;
/**
 * This Class shows the highscore ..
 *
 */
public class HighscoreFrame extends JFrame {
	private JPanel tablePanel,buttonPanel,mainpanel;
	private JTable table;
	private JScrollPane scroller;
	private JButton exit;
	private TableSorter sorter;
	private HighscoreModel themodel;
	/**
	 * Creates a new highscore frame.
	 *
	 */
	public HighscoreFrame(){
		makeFrame();
		pack();
		setVisible(true);
	}
	private void makeFrame(){
		mainpanel = new JPanel();
		mainpanel.setLayout(new BorderLayout());
		this.getContentPane().add(mainpanel);
		mainpanel.add(makeTablePanel(),BorderLayout.CENTER);
		mainpanel.add(makeButtonPanel(),BorderLayout.SOUTH);
	}
	private JPanel makeTablePanel(){	
		tablePanel = new JPanel();
		table = new JTable();	
		themodel = new HighscoreModel();
		sorter = new TableSorter(themodel);
		table.setModel(sorter);
		sorter.setTableHeader(table.getTableHeader());
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		tablePanel.setPreferredSize(new Dimension(640,480));
		scroller = new JScrollPane(table);	
		tablePanel.add(scroller);		
		filltable();
		sorter.asort();//exprimental!!
		return tablePanel;
	}
	private JPanel makeButtonPanel(){
		buttonPanel = new JPanel();
		exit = new JButton("Exit highscore");
		buttonPanel.add(exit);
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				close();
			}
		});
		return buttonPanel;
	}
	private void close(){
		this.dispose();
	}
	private void filltable(){
		Highscore highobj = Highscore.getInstance();
		Collection<HighscoreItem> thelist = highobj.getList();
		themodel.clearData();
		for(HighscoreItem tmp : thelist){
			themodel.insertRow(tmp);
		}	
		themodel.fireTableStructureChanged();
		table.updateUI();
	}
}
