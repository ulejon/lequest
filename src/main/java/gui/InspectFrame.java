package gui;

import java.awt.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.*;
import javax.swing.JTextArea;
import items.*;

/**
 * Class to show a frame with information about an item 
 *
 */
public class InspectFrame extends JFrame {
	
	private Item theitem;
	private JButton goback;
	private JPanel picturePanel,lowerPanel,buttonPanel,infoPanel;
	private JLabel pictureLabel;
	private JTextArea infoField;
	/**
	 * Create a new inspect frame
	 * @param theitem to inspect
	 */
	public InspectFrame(Item theitem){
		this.setTitle(theitem.getName());
		this.theitem = theitem;
		this.setAlwaysOnTop(true);
		makeFrame();
		activateListeners();
		setInfo();
		setItem(theitem);
		this.setPreferredSize(new Dimension(200,300));
		pack();
		setVisible(true);
	}
	private void makeFrame(){
		this.setLayout(new BorderLayout());
		this.getContentPane().add(makePicturePanel(),BorderLayout.NORTH);
		this.getContentPane().add(makeLowerPanel(),BorderLayout.CENTER);
	}
	private JPanel makeLowerPanel(){
		lowerPanel = new JPanel(new BorderLayout());
		lowerPanel.add(makeInfoField(),BorderLayout.CENTER);
		lowerPanel.add(makeButtonPanel(),BorderLayout.SOUTH);
		return lowerPanel;
	}
	private JPanel makeInfoField(){
		infoPanel = new JPanel();
		infoPanel.setBackground(picturePanel.getBackground());
		infoField = new JTextArea();
		infoField.setEditable(false);
		infoField.setBackground(picturePanel.getBackground());
		infoField.setForeground(Color.white);
		infoPanel.add(infoField);
		return infoPanel;
	}
	private JPanel makeButtonPanel(){
		buttonPanel = new JPanel();
		buttonPanel.setBackground(picturePanel.getBackground());
		goback = new JButton("Close");
		buttonPanel.add(goback);
		return buttonPanel;
	}
	private JPanel makePicturePanel(){
		picturePanel = new JPanel();
		picturePanel.setBackground(Color.BLACK);
		pictureLabel = new JLabel();
		setItem(theitem);
		picturePanel.add(pictureLabel);
		return picturePanel;
	}
	private void setItem(Item theitem){
		this.theitem = theitem;
		pictureLabel.setIcon(theitem.getImage());
	}
	private void setInfo(){
		infoField.setText(theitem.getDescription());
	}
	private void activateListeners(){
		goback.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				goBack();
				};
		});
	}
	
	private void goBack(){
		this.dispose();// dispose the window..no use for it no more..
	}
	
}
