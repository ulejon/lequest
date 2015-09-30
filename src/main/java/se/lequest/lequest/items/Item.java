package se.lequest.lequest.items;

import javax.swing.ImageIcon;

import se.lequest.lequest.constants.GameConstants;
/**
 * This class is a abstract class for all kinds of items in the game..
 * Weapons, Armor, UsebleItems, other items.. 
 *
 */
public abstract class Item implements java.io.Serializable{
	private String name;
	private boolean sellable;
	private boolean dropable;
	protected int value;
	private ImageIcon image;
	private int itemLevel;
	/**
	 * Creates a new Item which is sellable and dropable by default
	 * @param name Name of the item
	 * @param value Value of the item
	 */
	public Item(String name, int value, String image,int itemLevel){
		this.name = name;
		this.image = new ImageIcon(this.getClass().getResource("/" + image));
		this.sellable = true;
		this.dropable = true;
		this.value = value;
		this.itemLevel = itemLevel;
	}
	/**
	 * Creates a new Item sellable and dropable by default
	 * @param name the name of the item
	 * @param value the value of the item
	 * @param itemLevel the level the item 
	 */
	
	public Item(String name, int value,int itemLevel){
		this.name = name;
		this.sellable = true;
		this.dropable = true;
		this.value = value;
		this.itemLevel = itemLevel;
	}
	/**
	 * Returns the image of the item
	 * @return
	 */
	public ImageIcon getImage(){
		return image;
	}
	/**
	 * Sets the image of the item
	 * @param image
	 */
	public void setImage(ImageIcon image){
		this.image = image;
	}
	/**
	 * Returns the PurchaseValue
	 * @return the PurchaseValue of the item
	 */
	public int getPurchaseValue(){
		return value;
	}
	/**
	 * Returns the sellValue
	 * @return the sellValue of the item
	 */
	public int getSellValue(){
		return (int)(value * GameConstants.SELL_VALUE_MULTIPLE);
	}
	/**
	 * Returns the Name of the Item
	 * @return item name
	 */
	public String getName(){
		return this.name;
	}
	/**
	 * Function to see if a Item is sellable in market.
	 * Returns true if sellable
	 * @return sellable flag
	 */
	public boolean isSellable(){
		return this.sellable;
	}
	/**
	 * Function to set the sellable flag
	 * @param issellable. True if the item can be sold
	 */
	public void setSellable(boolean issellable){
		this.sellable = issellable;
	}
	/**
	 * Function to see if a Item is dropable.
	 * Returns true if dropable
	 * @return dropable flag
	 */
	public boolean isDropable(){
		return this.dropable;
	}
	/**
	 * Function to set the dropable flag
	 * @param isdropable. True if the item can be dropped
	 */
	public void setDropable(boolean isdropable){
		this.dropable = isdropable;
	}
	/**
	 * Function to get the item level
	 * With a item level we mean on which game level the item can be found
	 * @return item level
	 */
	public int getItemLevel(){
		return this.itemLevel;
	}
	/**
	 * Function to set the level of a item
	 * @param level
	 */
	public void setItemLevel(int level){
		this.itemLevel = level;
	}
	/**
	 * Returns the Description of the file..
	 * @return
	 */
	public String getDescription(){
		return "Name: "+this.name+
			   "\nDescription: Not avaliable";
	}


}
