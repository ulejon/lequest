package se.lequest.lequest.gui;

import javax.swing.*;

import se.lequest.lequest.constants.GameConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import se.lequest.lequest.characters.Player;
import se.lequest.lequest.gamelogic.GameLogic;

/**
 * Creates the menu bar visible in the map frame
 * @author hagen
 *
 */
public class MapMenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;
	private JMenu help;
	private JMenu move;

	
	private JMenuItem about;
	private JMenuItem North,East,West,South;
	private Player player;
	private GameLogic gamelogic;
	/**
	 * Creates a new menu bar
	 * @param player current player
	 */
	public MapMenuBar(Player player){
		this.player = player;
		this.gamelogic = GameLogic.getInstance();
		makeMenus();
		makeMenuItems();
		combine();
		activateListeners();
		setaccselerators();
	}
	private void makeMenus(){
		this.help = new JMenu("Help");
		this.move = new JMenu("Move"); 
	}
	private void makeMenuItems(){

		this.about = new JMenuItem("About the game");
		this.North = new JMenuItem("North");
		this.South = new JMenuItem("South");
		this.East = new JMenuItem("East"); 
		this.West = new JMenuItem("West"); 

	}
	private void combine(){
		this.add(this.help);
		this.help.add(this.about);		
		this.add(this.move); 	
		this.move.add(this.North);
		this.move.add(this.South);
		this.move.add(this.West);
		this.move.add(this.East);
	}
	private void setaccselerators(){
		North.setAccelerator(KeyStroke.getKeyStroke(GameConstants.KEY_MOVE_NORTH, 0));
		South.setAccelerator(KeyStroke.getKeyStroke(GameConstants.KEY_MOVE_SOUTH, 0));
		East.setAccelerator(KeyStroke.getKeyStroke(GameConstants.KEY_MOVE_EAST, 0));
		West.setAccelerator(KeyStroke.getKeyStroke(GameConstants.KEY_MOVE_WEST, 0));
	}
	private void activateListeners(){
		this.about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
					JOptionPane.showMessageDialog(null,GameConstants.NAME+ " "+GameConstants.VERSION);
				}
		});
		this.North.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
					movenorth();
				}
		});
		this.South.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
					movesouth();
				}
		});
		this.East.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
					moveeast();
				}
		});
		this.West.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
					movewest();
				}
		});
	}
	private void movenorth(){
		this.gamelogic.move(player, GameLogic.NORTH);
	}
	private void moveeast(){
		this.gamelogic.move(player, GameLogic.EAST);
	}
	private void movewest(){
		this.gamelogic.move(player, GameLogic.WEST);
	}
	private void movesouth(){
		this.gamelogic.move(player, GameLogic.SOUTH);
	}
	/**
	 * Update the current player
	 * Used after load player
	 * @param player
	 */
	public void setPlayer(Player player){
		this.player = player;
	}

}
