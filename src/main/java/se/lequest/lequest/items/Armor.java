package se.lequest.lequest.items;
import javax.swing.ImageIcon;

import se.lequest.lequest.constants.ArmorConstants;

/**
 * A abstract superclass for Armor. Contains information about protection values , images etc. 
 *
 */
public abstract class Armor extends Item{
	private int maxProtectionValue,minProtectionValue;
	private int armorHealth;
	private ImageIcon image;
	/**
	 * Creates a new Armor object..
	 * @param maxProtectionValue the Maximum protection value of the Armor
	 * @param minProtectionValue the Minimum protection value of the Armor
	 * @param name The name of the Armor
	 * @param value Tha value of the Armor
	 * @param level The level it appers on
	 * @param image The path to the picture of the Armor
	 */
	public Armor(int maxProtectionValue,int minProtectionValue,String name, int value,int level
			,String image){
		super(name, value,level);
		this.armorHealth = ArmorConstants.MAX_ARMOR_HEALTH;
		this.maxProtectionValue = maxProtectionValue;
		this.minProtectionValue = minProtectionValue;
		this.image = new ImageIcon(this.getClass().getResource("/" + image));
	}
	/**
	 * Returns the minimum protection value
	 * @return
	 */
	public int getMinProtectionValue(){
		return this.minProtectionValue;
	}
	/**
	 * Returns the Maximum protection value
	 * @return
	 */
	public int getMaxProtectionValue(){
		return this.maxProtectionValue;
	}
	/**
	 * Returns the health of the armor
	 * @return
	 */
	public int getArmorHealth(){
		return this.armorHealth;
	}
	/**
	 * Returns the Description of the Armor
	 */
	public String getDescription(){
		return getName() +" (Armor)\n"+
			   "Armor Class: " +minProtectionValue+"-"+maxProtectionValue+"\n"+
			   "Armor Health: " +armorHealth;
	}
	/**
	 * Function to increase the current health of the armor
	 * Used for example when you repair the armor item
	 * @param amount
	 */
	public void increaseProtection(int amount){
		this.armorHealth+=amount;
	}
	/**
	 * Returns the Image of the Armor
	 */
	public ImageIcon getImage(){
		return image;
	}
	/**
	 * Set a new Image for the Armor
	 */
	public void setImage(ImageIcon image){
		this.image = image;
	}
	
}
