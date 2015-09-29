package gui;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import maps.Map;
import maps.Position;
import maps.Segment;
import characters.Player;
import constants.GameConstants;
/**
 * Class that shows the map
 *
 */
public class MapViewer extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;
	private HashMap<String, JLabel> labelarr;
	private final int width = GameConstants.MAPVIEWER_NR_CELLS_WIDTH; //how big is the Map? value av 10 will become 10^2 cells.
	private final int imageWidth = GameConstants.MAP_IMAGE_CELL_WIDTH;// width of all the images in viewer..
	private segmentImageGenerator imagegenerator;
	private Player player;
	private ImageIcon invisablesegment; 
	
	/**
	 * Creates a new map viewer
	 * @param player
	 */
	public MapViewer(Player player){
		this.labelarr = new HashMap<String,JLabel>();
		
		//Create empty JLabels and add them to this..
		//also add them to a HashMap so they can be modified later..
		JLabel templabel;
		for(int i = 0; i < width;i++){
			for(int j = 0; j < width; j++){
				templabel = new JLabel();
				this.labelarr.put(j + "," + i, templabel);
				this.add(templabel);
			}
			
		}
		this.player = player;
		// Set this object to Observe on player..
		this.player.addObserver(this);		
		this.imagegenerator = new segmentImageGenerator(imageWidth);
		this.setLayout(new GridLayout(width,0));
		/*
		 * Set the JPanel fixed size.. so the Images sit tight.. 
		 */
		Dimension fixedsize = new Dimension(imageWidth * width,imageWidth * width);
		this.setMaximumSize(fixedsize);
		this.setMinimumSize(fixedsize);
		this.setPreferredSize(fixedsize);
		this.invisablesegment = this.imagegenerator.getSolidBackgroundPicture();
		/*
		 * Update the cells..
		 */
		this.updateAllCells();
		
	}
	/*
	 * go thru all of the positions. and set them correct..
	 * 
	 */
	private void updateAllCells(){
		Segment seg;
		for(int i = 0;i < width; i++){
			for(int j = 0; j < width; j++){
				seg = player.getCurrentMap().getSegment(new Position(j,i));
				// if no segment.. then set black
				if(seg == null){
					this.labelarr.get(j+"," +i).setIcon(invisablesegment);
				}else{
					//if segment is visible.. show it..if player is on segment sett true..
					//if not show black..
					if(seg.isvisible()){
						this.labelarr.get(j + "," + i).setIcon(this.imagegenerator.getImageIcon(seg,seg.getPosition().equals(player.getCurrentPosition())));
					}else{
						this.labelarr.get(j + "," + i).setIcon(invisablesegment);
					}
				}
			}
		}
	}
	/**
	 * Returns the current map in use..
	 * @return
	 */
	public Map getCurrentMap(){
		return this.player.getCurrentMap();
	}
	/**
	 * Extended from Observer
	 * Updates the map to show the new position of the player
	 */
	public void update(Observable obj,Object o){
		if(o instanceof Integer){
			int updatecode = ((Integer)o).intValue();
			this.player = (Player)obj;
			if(updatecode == GameConstants.PLAYER_CHANGE_POSITION ){
				this.updateOneCell(player.getCurrentPosition());
				this.updateOneCell(player.getOldPos());
			}else if(updatecode == GameConstants.PLAYER_CHANGE_MAP){
				this.updateAllCells();
			}
		}
		
	}
	/** 
	 * Only update a one cell.. 
	 */
	public void updateOneCell(Position positiontoupdate){
		JLabel thelabel = this.labelarr.get(positiontoupdate.toString());
		Segment seg = this.player.getCurrentMap().getSegment(positiontoupdate);
		if(seg == null){
			thelabel.setIcon(this.invisablesegment);
		}else{
			thelabel.setIcon(this.imagegenerator.getImageIcon(seg,seg.getPosition().equals(player.getCurrentPosition())));
		}
	}
	/**
	 * Set a new player to observAt.
	 * @param player
	 */
	public void setPlayer(Player player){
		if(this.player != null){
			this.player.deleteObserver(this);
		}
		this.player = player;
		this.player.addObserver(this);
	}
	
}
