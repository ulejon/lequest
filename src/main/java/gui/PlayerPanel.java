package gui;
import java.util.Observable;
import java.util.Observer;
import java.util.Iterator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

import constants.GameConstants;
import constants.MessageConstants;
import items.*;
import characters.Player;
import gamelogic.Experience;
import java.util.ArrayList;

/**
 * Class PlayerPanel constructs GUI components regarding
 * the players information, such as the current health,
 * weapon, armor, inventory and so on. It updates them
 * when the class Player is changed.
 * @author Eric, Jonas 
 */
public class PlayerPanel extends JPanel
		implements Observer {
	
	private static final long serialVersionUID = 1L;
	private Player player;
	private JPanel mainPanel,buttonPanel,inventoryPanel,itemInfoPanel,lifePanel,experiencePanel,
				   characterArmorPanel;
	private JLabel HPLabel,levelLabel,expLabel,playerNameLabel,inventory,goldLabel,
				   weaponLabel,armorLabel,
				   capLabel,bodyLabel,shieldLabel,glovesLabel,bootsLabel,
				   capLabelHeading,bodyLabelHeading,shieldLabelHeading,glovesLabelHeading,bootsLabelHeading;
	private JTextArea itemInfo;
	private JButton useButton,dropButton;
	private JProgressBar lifeMeter,experienceMeter;
	private JComboBox inventoryScrollBox;
	private Experience experience;
	
	/**
	 * Creates a new object of PlayerPanel
	 * @param player current player
	 */
	public PlayerPanel(Player player){
		this.player = player;
		this.player.addObserver(this);
		this.capLabel = new JLabel("<none>");
		this.bodyLabel = new JLabel("<none>");
		this.shieldLabel = new JLabel("<none>");
		this.glovesLabel = new JLabel("<none>");
		this.bootsLabel = new JLabel("<none>");
		experience = Experience.getInstance();
		experience.addObserver(this);
		createPlayerMainPanel();

		activateListeners();
		showSelectedInventoryScrollBox();
	}

	private void createPlayerMainPanel(){
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(new EtchedBorder());
		mainPanel.add( makePlayerInfoPanel() );
		mainPanel.add( makeInventoryPanel() );
		mainPanel.add( makeButtonPanel() );
		mainPanel.add( makeCharacterArmorPanel() );
		mainPanel.add( makeLifePanel() );
		mainPanel.add( makeExperiencePanel() );
		add(mainPanel);
	}
	
	private JComponent makePlayerInfoPanel(){
		JPanel panel = new JPanel(new GridLayout(0,2));
		panel.add(new JLabel("Name:"));
		playerNameLabel = new JLabel( player.getName() );
		panel.add(playerNameLabel);
		panel.add(new JLabel("Gold:"));
		goldLabel = new JLabel();
		updateGoldLabel();
		panel.add(goldLabel);
		panel.add(new JLabel("Weapon:"));
		weaponLabel = new JLabel();
		updateWeaponLabel();
		panel.add(weaponLabel);
		panel.add(new JLabel("Armor:"));
		armorLabel = new JLabel();
		updateArmorLabel();
		panel.add(armorLabel);
		panel.add(new JLabel()); //Dummy
		return panel;
	}
	
	private JComponent makeLifePanel(){
		lifePanel = new JPanel(new GridLayout(0,1));
		HPLabel = new JLabel();
		HPLabel.setFont(GameConstants.BOLD_FONT);
		lifePanel.add(HPLabel);
		lifeMeter = new JProgressBar(JProgressBar.HORIZONTAL);
		lifeMeter.setBackground(Color.black);
		lifeMeter.setForeground(Color.red);
		lifeMeter.setBorderPainted(false);
		updatePlayerLife();
		lifePanel.add(lifeMeter);
		return lifePanel;
	}
	
	private JComponent makeExperiencePanel(){
		experiencePanel = new JPanel(new GridLayout(0,1));
		expLabel = new JLabel();
		expLabel.setToolTipText("Current Experience/Next experience to level up");
		expLabel.setFont(GameConstants.BOLD_FONT);
		experiencePanel.add(expLabel);
		experienceMeter = new JProgressBar(JProgressBar.HORIZONTAL);
		experienceMeter.setBackground(Color.black);
		experienceMeter.setForeground(Color.yellow);
		experienceMeter.setBorderPainted(false);
		experiencePanel.add(experienceMeter);
		levelLabel = new JLabel();
		levelLabel.setFont(GameConstants.BOLD_FONT);
		experiencePanel.add(levelLabel);
		updatePlayerLevel();
		updatePlayerExperience();
		return experiencePanel;
	}
	
	private JComponent makeInventoryPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JPanel labelbox = new JPanel(new BorderLayout()); //has to put label in a panel..
		inventory = new JLabel();
		inventory.setFont(GameConstants.BOLD_FONT);
		labelbox.add(inventory, BorderLayout.WEST);
		panel.add(labelbox);
	    inventoryPanel = new JPanel();
		inventoryPanel.setLayout(new BoxLayout(inventoryPanel, BoxLayout.Y_AXIS));
		inventoryScrollBox = new JComboBox();
		inventoryScrollBox.setFocusable(false);//to fix arrow-key bugg..
		inventoryPanel.add(inventoryScrollBox);
		itemInfoPanel = new JPanel();
		itemInfoPanel.setBorder(new TitledBorder("Item Info"));
		itemInfo = new JTextArea();
		itemInfo.setBackground(inventoryPanel.getBackground());
		itemInfo.setEditable(false);
		itemInfo.setFont(GameConstants.DEFAULT_FONT);
		itemInfoPanel.add(itemInfo);
		inventoryPanel.add(itemInfoPanel);
		updateInventory();
		panel.add(inventoryPanel);
		return panel;
	}
	
	private JComponent makeButtonPanel(){
		buttonPanel = new JPanel();
		useButton = new JButton("Use");
		dropButton = new JButton("Drop");
		useButton.setToolTipText("Use the selected item in inventory.");
		dropButton.setToolTipText("Drop the selected item in inventory.");
		buttonPanel.add(useButton);
		buttonPanel.add(dropButton);
		return buttonPanel;
	}
	private JPanel makeCharacterArmorPanel(){
		characterArmorPanel = new JPanel(new GridLayout(6,2));
		capLabelHeading = new JLabel("Cap: ");
		bodyLabelHeading = new JLabel("Body: ");
		shieldLabelHeading = new JLabel("Shield: ");
		glovesLabelHeading = new JLabel("Gloves: ");
		bootsLabelHeading = new JLabel("Boots: ");
				
		characterArmorPanel.add(new JLabel("ACTIVE ARMOR "));
		characterArmorPanel.add(new JLabel()); //DUMMY
		
		characterArmorPanel.add(capLabelHeading);
		characterArmorPanel.add(capLabel);
		
		characterArmorPanel.add(bodyLabelHeading);
		characterArmorPanel.add(bodyLabel);
		
		characterArmorPanel.add(shieldLabelHeading);
		characterArmorPanel.add(shieldLabel);
		
		characterArmorPanel.add(glovesLabelHeading);
		characterArmorPanel.add(glovesLabel);
		
		characterArmorPanel.add(bootsLabelHeading);
		characterArmorPanel.add(bootsLabel);
		
		return characterArmorPanel;
	}
	
	private void activateListeners(){
		useButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				useButtonPressed();
			}
		});
		dropButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				dropButtonPressed();
			}
		});
		this.inventoryScrollBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				showSelectedInventoryScrollBox();
			}
		});
	}
	
	/*
	 * Performs action when the Use button is pressed.
	 * Depends on the selected item in inventory.
	 */
	private void useButtonPressed(){
		String itemName = (String)inventoryScrollBox.getSelectedItem();
		if(itemName == null){	//if nothing is selected
			JOptionPane.showMessageDialog(null,MessageConstants.NO_ITEM_SELECTED);
			return;
		}
		Iterator<Item> it = player.getItems().iterator();
		while(it.hasNext()){
			Item item = it.next();
			if(item.getName().equals(itemName)){
				if(item instanceof Weapon){
					player.setCurrentWeapon((Weapon)item);  //Set a new current weapon.
					break;
				}
				else if(item instanceof Armor){
					player.addArmor((Armor)item);
					break;
				}
				if(item instanceof UsableItem){
					UsableItem useItem = (UsableItem)item;
					useItem.use(player);
					player.removeItem(item); //TODO: Change to the Specific item insted.. so that sertain items can be reused,,
					break;
				}
			}
		}
	}
	
	/*
	 * Performs action when the Drop button is pressed.
	 * Depends on the selected item in inventory. Can only
	 * drop an item if 'isDropable' is set to true.
	 */
	private void dropButtonPressed(){
		String itemName = (String)inventoryScrollBox.getSelectedItem();
		Item dropItem = player.getItem(itemName);
		if( dropItem != null && dropItem.isDropable() ){
			player.removeItem(dropItem);
			player.getCurrentMap().getSegment(player.getCurrentPosition()).addItem(dropItem);
		}else{
			JOptionPane.showMessageDialog(null,"You can't drop that item.");
		}
		
	}
	/*
	 * Performs action when an item is selected from the
	 * inventory scroll box. If an item is selected, print
	 * out details about it.
	 */
	private void showSelectedInventoryScrollBox(){
		String selectedName = (String)inventoryScrollBox.getSelectedItem();
		if(selectedName != null){
			Item activeItem = player.getItem(selectedName);
			itemInfo.setText(activeItem.getDescription());
		}else{
			itemInfo.setText(MessageConstants.NO_ITEM_SELECTED);
		}
	}
	
	/*
	 * @param integer the integer to convert to a String
	 * @return The String
	 */
	private String toString(int integer){
		return Integer.toString(integer);
	}
	/*
	 * Generates a string to <html> code (used by ex. ToolTip).
	 * @param text the text
	 * @return String html-code
	 */
	private String generateHTMLString(String text){
		String theText = "<html>";
		theText += text.replaceAll("\n", "<br>");
		return theText + "</html>";
	}
	
	/*
	 * Asks for Players health and updates the HP Label.
	 * if HP is less than 20, change textcolor to red.
	 */
	private void updatePlayerLife(){
		HPLabel.setForeground(Color.BLACK);
		if(player.getHealth() <= 20){
			HPLabel.setForeground(Color.RED);
		}
		HPLabel.setText( "Health Points (" + toString(player.getHealth()) +
				"/" + toString(GameConstants.MAX_PLAYER_HEALTH)+ ")" );
		//the life progressbar shows life in percent (0-100)
		Double health = new Double(player.getHealth());
		Double lifePercent = new Double((health / GameConstants.MAX_PLAYER_HEALTH)*100);
		lifeMeter.setValue(lifePercent.intValue());
	}
	
	private void updatePlayerExperience(){
		expLabel.setText("Experience (" +player.getExperience() +"/"+
								experience.getNextExperienceLevel() +")" );
		Double exp = new Double(player.getExperience());
		Double interval = new Double(experience.getNextExperienceLevel()-experience.getLastExperience());
		Double expPercent = new Double(((exp-experience.getLastExperience()) / interval)*100);
		experienceMeter.setValue(expPercent.intValue());
	}
	
	private void updatePlayerLevel(){
		levelLabel.setText(player.getName() + " level: " +player.getPlayerLevel());
	}
	/*
	 * Updates the Gold Label.
	 * @param gold The new Gold value.
	 */
	private void updateGoldLabel(){
		goldLabel.setText( toString(player.getCoins()) + " GC" );
	}
	
	/*
	 * Updates the Weapon Label.
	 */
	private void updateWeaponLabel(){
		Weapon weapon = player.getCurrentWeapon();
		weaponLabel.setText( weapon.getName() );
		weaponLabel.setToolTipText(generateHTMLString(weapon.getDescription()));
	}
	
	/*
	 * Updates the Armor Label.
	 */
	private void updateArmorLabel(){
		//TODO: this is temporarty fix this..
		armorLabel.setText("Min:" + this.player.getActiveArmor().getTotalMinimumProtection() +
				" / Max:" + this.player.getActiveArmor().getTotalMaximumProtection());
		
		ArrayList<Armor> list = new ArrayList<Armor>();
		list = player.getActiveArmor().getArmorList();
		Armor tmp = null;
		for(int i=0;i < list.size();i++){
			tmp = list.get(i);
			if(tmp instanceof Helmet)
				this.capLabel.setText(tmp.getName());
			else if(tmp instanceof Chestplate)
				this.bodyLabel.setText(tmp.getName());
			else if(tmp instanceof Shield)
				this.shieldLabel.setText(tmp.getName());
			else if(tmp instanceof Gloves)
				this.glovesLabel.setText(tmp.getName());
			else if(tmp instanceof Boots)
				this.bootsLabel.setText(tmp.getName());
		}
	}
	
	/*
	 * Updates the Inventory scroll-box.
	 * Doesn't add the item if it is the players activeItem
	 * Active Armor, Weapon and so on...
	 */
	private void updateInventory(){
		Iterator<Item> it = player.getItems().iterator();
		inventoryScrollBox.removeAllItems();
		while(it.hasNext()){
			Item item = it.next();
			inventoryScrollBox.addItem(item.getName());
		}
		inventory.setText("Inventory (" + 
				player.getItems().size() + 
				"/"+ GameConstants.MAX_NR_OF_ITEMS_ON_PLAYER + ")");
	}
	
	/**
	 * Gets an updated object of the Player and
	 * updates panels depending on the change of player.
	 */
	public void update(Observable obj,Object o){
		if(obj instanceof Experience){
			this.experience = (Experience)obj;
			updatePlayerExperience();
			return;
		}
		else if(obj instanceof Player){
			this.player = (Player)obj;
			if(o instanceof Integer){
				int updatecode = ((Integer)o).intValue();
				switch(updatecode){
				case GameConstants.PLAYER_CHANGE_HP:
					updatePlayerLife();break;
				case GameConstants.PLAYER_CHANGE_CURRENTWEAPON:
					updateWeaponLabel();
					updateInventory();
					break;
				case GameConstants.PLAYER_CHANGE_ARMOR:
					updateArmorLabel();
					updateInventory();
					break;
				case GameConstants.PLAYER_CHANGE_MONEY:
					updateGoldLabel();break;
				case GameConstants.PLAYER_CHANGE_INVENTORY:
					updateInventory();break;
				case GameConstants.PLAYER_GAINS_EXPERIENCE:
					updatePlayerExperience();break;
				case GameConstants.PLAYER_LEVELS_UP:
					updatePlayerExperience();
					updatePlayerLevel();break;
				}
			}
		}
	}
	/**
	 * Update the player ..
	 */
	public void setPlayer(Player player){
		if(this.player != null){
			this.player.deleteObserver(this);
		}
		this.player = player;
		this.player.addObserver(this);
	}
	
}
