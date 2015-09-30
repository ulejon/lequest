package se.lequest.lequest.gui;

import javax.swing.*;
import se.lequest.lequest.characters.Player;
/**
 * Panel to hold the map view
 *
 */
public class MapPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private MapViewer viewer;
	private Player player;
	/**
	 * Creates a new map panel
	 * @param player current player
	 */
	public MapPanel(Player player){
		this.player = player;
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));		
		this.viewer = new MapViewer(player);
		this.add(viewer);		
	}
	/**
	 * Update the current player
	 * Used after load player
	 * @param player
	 */
	public void setPlayer(Player player){
		this.player = player;
		viewer.setPlayer(player);
	}
	

}
