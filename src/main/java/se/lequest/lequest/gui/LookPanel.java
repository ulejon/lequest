package se.lequest.lequest.gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Observable;
import java.util.Observer;
import java.util.ArrayList;

import se.lequest.lequest.characters.Player;
import se.lequest.lequest.items.*;
import se.lequest.lequest.maps.Segment;
import se.lequest.lequest.constants.GameConstants;
import se.lequest.lequest.constants.MessageConstants;

/**
 *	The look panel holds information about items that
 *	are lying somewhere in the room.
 * @author Eric, Jonas
 *
 */
public class LookPanel extends JPanel
			implements Observer{
	
	private static final long serialVersionUID = 1L;
	private Player player;
	private Segment room;
	private JComboBox itemScroll;
	private JPanel boxPanel,infoPanel,buttonPanel,imagePanel,itemPanel;
	private JLabel itemLabel,image;
	private JTextArea itemInfo;
	private JButton pickupButton;
	
	/**
	 * Creates a new look panel
	 * @param player the current player
	 */
	public LookPanel(Player player){
		this.player = player;
		player.addObserver(this);
		room = player.getCurrentMap().getSegment(player.getCurrentPosition());
		room.addObserver(this);
		makeFrame();
		boxPanel.setVisible(false); //Shold only be visible when there's an item present.
		buttonPanel.setVisible(false);
	}
	
	private void makeFrame(){
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		add(makeItemInfoPanel());
		add(makeButtonPanel());
	}
	
	private JPanel makeItemInfoPanel(){
		itemPanel = new JPanel();
		itemLabel = new JLabel();
		itemLabel.setFont(GameConstants.BOLD_FONT);
		itemPanel.add(itemLabel);
		add(itemPanel);
		
		boxPanel = new JPanel();
		boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
		itemScroll = new JComboBox();
		itemScroll.setFocusable(false); //To fix arrow-key bugg..
		itemScroll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				selectedItemScrollBox();
			}
		});
		boxPanel.add(itemScroll);
		
		imagePanel = new JPanel();
		image = new JLabel();
		imagePanel.add(image);
		boxPanel.add(imagePanel);
		infoPanel = new JPanel();
		infoPanel.setBorder(new TitledBorder("Item info"));
		itemInfo = new JTextArea();
		itemInfo.setBackground(infoPanel.getBackground());
		itemInfo.setEditable(false);
		itemInfo.setFont(GameConstants.DEFAULT_FONT);
		infoPanel.add(itemInfo);
		boxPanel.add(infoPanel);
		return boxPanel;		
	}
	
	private JPanel makeButtonPanel(){
		buttonPanel = new JPanel();
		pickupButton = new JButton("Pick up");
		pickupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ 
				pickupButtonPressed();
				}
			});
		buttonPanel.add(pickupButton);
		return buttonPanel;
	}
	
	/*
	 * Performs action when an item is selected from the
	 * inventory scroll box. If an item is selected, print
	 * out details about it.
	 */
	private void selectedItemScrollBox(){
		String selectedName = (String)itemScroll.getSelectedItem();
		if(selectedName != null){
			itemInfo.setText(room.getItem(selectedName).getDescription());
			setItemImage(room.getItem(selectedName));
		}else{
			itemInfo.setText(MessageConstants.NO_ITEM_SELECTED);
		}
	}
	
	/*
	 * Draws an image to the selected item in list.
	 */
	private void setItemImage(Item item){
		image.removeAll();
		if(item.getImage() != null){
			image.setIcon(item.getImage());
		}else{
			image.setIcon(null);
			image.setText("No image");
		}
	}
	
	/*
	 * Updates the Combobox with items from the current room.
	 */
	private void updateItemScroll(){
		ArrayList<Item> items = room.GetItemList();
		itemScroll.removeAllItems();
		if(items.size() != 0){
			itemLabel.setText("<html>There are " +room.getItemCount()+
			" item(s)<br>in this room.</html>");
			for(int i=0; i<items.size(); i++){
				itemScroll.addItem(items.get(i).getName());
			}
			boxPanel.setVisible(true);
			buttonPanel.setVisible(true);
		}else{
			itemLabel.setText("<html>You found nothing<br>in this room.</html>");
			boxPanel.setVisible(false);
			buttonPanel.setVisible(false);
		}
	}
	
	/*
	 * Adds an item to players inventory.
	 * Don't add items to inventory if it is full,
	 * exept fot Money or special items (items
	 * needed to complete levels.. keys, Questitems etc).
	 */
	private void pickupButtonPressed(){
		String selectedName = (String)itemScroll.getSelectedItem();
		Item theItem = room.getItem(selectedName);
		if(theItem instanceof Money){ //Money always added.. and special items.. keys etc.
			player.addItem(theItem);
			room.removeItem(theItem);
			return;
		}
		if(player.getItems().size() == GameConstants.MAX_NR_OF_ITEMS_ON_PLAYER){
			JOptionPane.showMessageDialog(null, "Your inventory is full!");
			return;
		}
		player.addItem(theItem);
		room.removeItem(theItem);
	}
	
	/**
	 * Extended from Observer.. 
	 */
	public void update(Observable obj,Object o){
		if(o instanceof Integer){
			int updatecode = ((Integer)o).intValue();
			switch(updatecode){
			case GameConstants.PLAYER_CHANGE_POSITION:
				this.room.deleteObserver(this);
				this.room = player.getCurrentMap().getSegment(player.getCurrentPosition());
				this.room.addObserver(this);
				updateItemScroll();
				break;
			case GameConstants.SEGMENT_ITEMS_CHANGE:
				this.room = (Segment)obj;
				updateItemScroll();break;
			}
		}
	}
}
