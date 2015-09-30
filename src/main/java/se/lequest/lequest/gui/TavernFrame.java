package se.lequest.lequest.gui;
import javax.swing.*;

import java.awt.*;
import javax.swing.JFrame;
import java.awt.event.*;

import se.lequest.lequest.characters.*;
/**
 * A frame to show the tavern gui
 *
 */
public class TavernFrame extends JFrame {
	private static TavernFrame instance;
	private TownFrame townframe;
	private Player player;
	private JPanel picturePanel,buttonPanel;
	private JLabel pictureLabel;
	private JButton eat,sleep,town;
	private String PICTURE = "/pictures/tavern.jpg";
	private TavernFrame(TownFrame townframe,Player player){
		this.player = player;
		this.townframe = townframe;
		makeFrame();
		activateListeners();
		this.setPreferredSize(new Dimension(640,480));
		pack();
		setVisible(true);
	}
	/**
	 * Returns the instance of the tavern frame
	 * @param townframe
	 * @param player
	 * @return
	 */
	public static TavernFrame getInstance(TownFrame townframe,Player player){
		if(TavernFrame.instance==null){
			TavernFrame.instance = new TavernFrame(townframe,player);
		}
		return TavernFrame.instance;
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
		picturePanel.add(pictureLabel);
		return picturePanel;
	}
	private JPanel makeButtonPanel(){
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		town = new JButton("Go to town");
		sleep = new JButton("Rent a room");
		eat = new JButton("Eat something");
		buttonPanel.add(town);
		buttonPanel.add(sleep);
		buttonPanel.add(eat);
		return buttonPanel;
	}
	private void activateListeners(){
		town.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				goBackToTown();};
		});
		sleep.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sleep();};
		});
		eat.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				goBackToTown();};
		});
	}
	private void goBackToTown(){
		this.setVisible(false);
		townframe.setVisible(true);
	}
	private void sleep(){
		int playerloss = player.getMaxHealth() - player.getHealth();
		if(player.getCoins() >= playerloss){
			player.addHealth(playerloss);
			player.decreaseCoins(playerloss);
			JOptionPane.showMessageDialog(null, "You are now fully rested and your health is at maximum level " +
											"\nYou rested for " +playerloss +" days");
		}else{
			int playerfund = player.getCoins();
			if(playerfund > 0){
				player.addHealth(playerfund);
				player.decreaseCoins(playerfund);
				JOptionPane.showMessageDialog(null, "You didn't have enough money to maximize your health"+
												"\nBut you rested for "+playerfund+" days");
			}else{
				JOptionPane.showMessageDialog(null,"You don't have any money, go get some");
			}
		}
	}
}
