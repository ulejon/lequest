package characters;
import items.Item;
import items.Money;
import items.Weapon;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
/**
 * Character is a Abstrct class used by Enemies and players in the game
 * it contains stuff that is common for all characters
 * 
 * @author Ulrik, Per, Magnus, Eric
 * @version v0.01
 */

public abstract class Character extends Observable implements java.io.Serializable {
	private String name;
	private int health;
	private int maxHealth;
	private boolean isAlive;
	private ArrayList<Item> items;
	private Weapon currentWeapon;
	private int maxNrofItems;
	private ImageIcon picture;
	/**
	 * Creates a Character object. The Character is set to be alive as default
	 * @param name the name of the character
	 * @param health Set the current health of the Character
	 * @param maxHealth Set the Maximum health of the Character
	 */
	public Character(String name,int health, int maxHealth,int maxNrofItems){	
		// To prevent stupid errors later on.
		if( health > maxHealth )
			health = maxHealth;
		
		this.name = name;
		this.health = health;
		this.maxHealth = maxHealth;
		this.isAlive = true;
		items = new ArrayList<Item>();
		this.maxNrofItems=  maxNrofItems;
	}
	/**
	 * Creates a Character object. The Character is set to be alive as default
	 * @param name name the name of the character
	 * @param health health Set the current health of the Character
	 * @param maxHealth maxHealth Set the Maximum health of the Character
	 * @param maxNrofItems the number of items the character can have in backpack
	 * @param picture the path to the picture of the character
	 */
	public Character(String name,int health, int maxHealth,int maxNrofItems,String picture){
//		 To prevent stupid errors later on.
		if( health > maxHealth )
			health = maxHealth;
		
		this.name = name;
		this.health = health;
		this.maxHealth = maxHealth;
		this.isAlive = true;
		items = new ArrayList<Item>();
		this.maxNrofItems=  maxNrofItems;
		this.picture = new ImageIcon(this.getClass().getResource("/" + picture));
	}
	/**
	 * Gets the list of items
	 * @return items
	 */
	public ArrayList<Item> getItems(){
		return items;
	}
	/**
	 * Function to get the current weapon
	 * @return current weapon
	 */
	public Weapon getCurrentWeapon(){
		return this.currentWeapon;
	}
	/**
	 * Function to set a new current weapon
	 * @param newWeapon
	 */
	public void setCurrentWeapon(Weapon newWeapon){
		if(newWeapon != null){
			if(this.items.contains(newWeapon)){//delete the current weapon from bag..
				this.removeItem(newWeapon);
			}
			if(this.currentWeapon != null){
				this.addItem(currentWeapon); // add the currentweapon to the bag..
			}
			this.currentWeapon = newWeapon; // change the current weapon..
		}
	}
	/**
	 * Returns the max nr of items the Player can carry
	 * @return
	 */
	public int getMaxnrofItems(){
		return maxNrofItems;
	}
	/**
	 * Set the max nr of items the Player can carry
	 * @param value
	 */
	public void setMaxNrOfItems(int value){
		this.maxNrofItems = value;
	}
	/**
	 * Returns the number of items
	 * @return number of items
	 */
	public int getNumberOfItems(){
		return this.items.size();
	}
	/**
	 * Try to add a Item (Weapon or other item) to the Player..
	 * returns true if OK
	 * return false if players stash is full
	 * @param addItem
	 * @return true if ok, false if full
	 */
	public boolean addItem(Item addItem){
		if(this.items.size() < maxNrofItems){
			this.items.add(addItem);
			return true;
		}
		return false;
	}
	/**
	 * Returns the name of the Character
	 * @return Character name
	 */
	public String getName(){
		return name;
	}
	/**
	 * Set a new name for the Character
	 * @param new Name
	 */
	public void setName(String name){
		this.name = name;
	}
	/**
	 * Returns the current health of the Character
	 * @return
	 */
	public int getHealth(){
		return this.health;
	}
	/**
	 * Set new healthpoints for the Character..
	 * it will check that the new helth is less or equal to Max,health
	 * if more than maxhealth.. the new health will be set to the max value.
	 * @param newHealth
	 */
	public void setHealth(int newHealth){
		if(newHealth <= this.maxHealth){
			this.health = newHealth;
		}else{
			this.health = this.maxHealth;
		}
	}
	/**
	 * This function adds health to the Character
	 * if currenthealth + points to add > maxHealth then
	 * health will be set to the maximum value.
	 * @param pointstoadd
	 */
	
	public void addHealth(int pointstoadd){
		if(this.health + pointstoadd > this.maxHealth){
			this.health = this.maxHealth;
		}else{
			this.health += pointstoadd;
		}
	}
	/**
	 * This function decreses the healthpoints of the Character
	 * if healthpoints becomes less then 0 the Alive flagg is sett to false,
	 * that is the character is dead.
	 * @param points
	 */
	public void decreaseHealth(int points){
		this.health -= points;
		if(this.health <= 0){
			this.health = 0; // cant be more than dead..
			this.isAlive = false;
		}
	}
	/**
	 * Returns the maximum value of health the character can have..
	 * @return
	 */
	public int getMaxHealth(){
		return this.maxHealth;
	}
	/**
	 * This function sets the alive flag of the character..
	 * if false the character is dead if true = alive
	 * 
	 */
	public void setIsAlive(boolean isalive){
		this.isAlive = isalive;
		if(this.isAlive == false){
			this.health = 0;
		}
	}
	/**
	 * This function returns true if the Characters is alive 
	 * And false if the Character is dead
	 * @return
	 */
	public boolean isAlive(){
	
		return this.isAlive;
	}
	/**
	 * Removes an item from the Player.
	 * @param removeItem
	 */
	public void removeItem(Item removeItem){
		items.remove(removeItem);
	}
	/**
	 * Gets a single item from the player.
	 * @param itemName
	 * @return the item, null if not found.
	 */
	public Item getItem(String itemName){
		Iterator<Item> it = items.iterator();
		while(it.hasNext()){
			Item item = it.next();
			if(item.getName().equals(itemName)){
				return item;
			}
		}
		return null;	//if the item wasn't found.
	}
	/**
	 * Returns the image/picture of a character
	 * @return picture
	 */
	public ImageIcon getPicture(){
		return this.picture;
	}
	/**
	 * sets the picture of a character
	 * @param picture
	 */
	public void setPicture(ImageIcon picture){
		this.picture = picture;
	}
}
