package se.lequest.lequest.gui;
import javax.swing.*;

import java.awt.*;

import javax.swing.JFrame;
import java.awt.event.*;

import se.lequest.lequest.characters.*;
/**
 * Wiseman gui
 * At the wiseman you can get advice about your quest
 */
public class WiseManFrame extends JFrame {
	private static WiseManFrame instance;
	private TownFrame townframe;
	private Player player;
	private JPanel picturePanel,buttonPanel;
	private JLabel pictureLabel;
	private JButton talk,magic,town,quest;
	private String PICTURE = "/pictures/wiseman.jpg";
	
	private WiseManFrame(TownFrame townframe,Player player){
		this.player = player;
		this.townframe = townframe;
		makeFrame();
		activateListeners();
		pack();
		setVisible(true);
	}
	/**
	 * Returns the instance of the wiseman frame
	 * @param player The player
	 * @param townframe TownFrame
	 * @return
	 */
	public static WiseManFrame getInstance(Player player,TownFrame townframe){
		if(WiseManFrame.instance==null){
			WiseManFrame.instance = new WiseManFrame(townframe,player);
		}
		return WiseManFrame.instance;
	}
	
	private void makeFrame(){
		this.setLayout(new BorderLayout());
		this.getContentPane().add(makePicturePanel(),BorderLayout.CENTER);
		this.getContentPane().add(makeButtonPanel(),BorderLayout.SOUTH);
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
	private JPanel makePicturePanel(){
		picturePanel = new JPanel();
		picturePanel.setBackground(Color.BLACK);
		pictureLabel = new JLabel(new ImageIcon(this.getClass().getResource(PICTURE)));
		picturePanel.setPreferredSize(new Dimension(pictureLabel.getIcon().getIconWidth()+10,pictureLabel.getIcon().getIconHeight()+10));
		picturePanel.add(pictureLabel);
		return picturePanel;
	}
	private JPanel makeButtonPanel(){
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		town = new JButton("Go to town");
		talk = new JButton("Talk to Wiseman");
		magic = new JButton("Learn Magic");
		quest = new JButton("Quests");
		buttonPanel.add(town);
		buttonPanel.add(talk);
		buttonPanel.add(magic);
		buttonPanel.add(quest);
		return buttonPanel;
	}
	private void activateListeners(){
		town.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				goBackToTown();};
		});
		talk.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				talkToWiseman();};
		});
		magic.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				learnMagic();};
		});
		quest.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				questInfo();};
		});
	}
	private void goBackToTown(){
		this.setVisible(false);
		townframe.setVisible(true);
	}
	private void talkToWiseman(){
		JOptionPane.showMessageDialog(null, "I have heard that...");
	}
	private void learnMagic(){
		JOptionPane.showMessageDialog(null, "You are too young to learn magic,\nget some more experience before wasting my time.");
	}
	private void questInfo(){
		JOptionPane.showMessageDialog(null, "No quests today kid.");
	}
}