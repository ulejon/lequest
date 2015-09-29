package gui;

import characters.Player;
import maps.Segment;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import gamelogic.GameLogic;
/**
 * This Class is the Controlpanel thats is the part of the gamegui with arrow buttons to
 * move the player.. 
 *
 */
public class ControlPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private Player player;
	private JButton north,south,east,west;
	private JPanel logoPanel,arrowPanelInner,arrowPanelOuter,roomInfoPanel,itemPanel,boxPanel;
	private JLabel logoLabel;
	private LookPanel lookPanel;
	private GameLogic gamelogic;
	/**
	 * Creates the ControlPanel
	 * @param player the current player.. 
	 */
	public ControlPanel(Player player){
		this.player = player;
		this.gamelogic = GameLogic.getInstance();
		makePanel();
		activateListeners();
	}
	private void makePanel(){
		makeLogoPanel();
		makeArrowPanel();
		makeRoomItemPanel();
		combine();
	}
	private void makeRoomItemPanel(){
		
		roomInfoPanel = new JPanel(new BorderLayout());
		boxPanel = new JPanel();
		boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
		itemPanel = new JPanel();
		lookPanel = new LookPanel(player);
		boxPanel.add(itemPanel);
		boxPanel.add(lookPanel);
		roomInfoPanel.add(boxPanel, BorderLayout.NORTH);
	}
	
	private void makeArrowPanel(){
		this.arrowPanelInner = new JPanel();
		this.arrowPanelOuter = new JPanel();
		north = new JButton(new ImageIcon(this.getClass().getResource("/pictures/arrow_north.png")));
		east = new JButton(new ImageIcon(this.getClass().getResource("/pictures/arrow_east.png")));
		south = new JButton(new ImageIcon(this.getClass().getResource("/pictures/arrow_south.png")));
		west = new JButton(new ImageIcon(this.getClass().getResource("/pictures/arrow_west.png")));
		
		
		this.arrowPanelOuter.setLayout(new BoxLayout(arrowPanelOuter,BoxLayout.Y_AXIS));
		this.arrowPanelOuter.add(arrowPanelInner);
		this.arrowPanelInner.setLayout(new GridLayout(3,3));
		
		arrowPanelInner.add(new JLabel()); //dummy label
		arrowPanelInner.add(north);
		arrowPanelInner.add(new JLabel()); //dummy label
		arrowPanelInner.add(west);
		arrowPanelInner.add(new JLabel()); //dummy label
		arrowPanelInner.add(east);
		arrowPanelInner.add(new JLabel()); //dummy label
		arrowPanelInner.add(south);
		arrowPanelInner.add(new JLabel()); //dummy label
	}
	private void makeLogoPanel(){
		this.logoPanel = new JPanel();
		this.logoLabel = new JLabel();
		this.logoPanel.add(logoLabel);
	}
	private void combine(){
		this.setLayout(new BorderLayout());
		this.add(logoPanel,BorderLayout.NORTH);
		this.add(arrowPanelOuter,BorderLayout.SOUTH);
		this.add(roomInfoPanel,BorderLayout.CENTER);
	}
	private void activateListeners(){
		this.north.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ 
					if(!gamelogic.move(player, GameLogic.NORTH)){
						//this is a illegal move..
						//TODO Change this to somethis else.. like playing a sound
						// or something
					}
				}
		});
		this.east.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ 
				if(!gamelogic.move(player, GameLogic.EAST)){
						//this is a illegal move..
						//TODO Change this to somethis else.. like playing a sound
						// or something
					}
				}
		});
		this.south.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ 
				if(!gamelogic.move(player, GameLogic.SOUTH)){
						//this is a illegal move..
						//TODO Change this to somethis else.. like playing a sound
						// or something
					}
				}
		});
		this.west.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ 
				if(!gamelogic.move(player, GameLogic.WEST)){
						//this is a illegal move..
						//TODO Change this to somethis else.. like playing a sound
						// or something
					}
				}
		});
	}
	/**
	 * Update the currentplayer. 
	 * @param player
	 */
	public void setPlayer(Player player){
		this.player = player;
	}
}
