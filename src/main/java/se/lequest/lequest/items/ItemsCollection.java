package se.lequest.lequest.items;

import java.util.ArrayList;
import java.util.Random;
/**
 * This class contains a collection of weapons.
 * It picks a random Item for the enemy to drop if he is defeated by the player
 * @author Ulrik,Per
 *
 */
public class ItemsCollection implements java.io.Serializable {
	//private ArrayList<Item> itemsList;
	private static ItemsCollection theinstance;
	private Random rand;
	private se.lequest.lequest.classloader.ClassLoader cl;
	public static ItemsCollection getInstace(){
		if(ItemsCollection.theinstance == null){
			ItemsCollection.theinstance = new ItemsCollection();
		}
		return ItemsCollection.theinstance;
	}
	private ItemsCollection(){
		 this.cl = new se.lequest.lequest.classloader.ClassLoader();
		 rand = new Random(System.currentTimeMillis());
	}
	/**
	 * Function to get a complete item collection
	 * @param level
	 * @return the list of items
	 */
	public ArrayList<Item> getItemCollection(int level){
		//fillItemList(level);
		//return itemsList;
		return this.cl.getItemCollection(level);
	}


	/*
	 * Produces a money bag with a random value..
	 * the maxvalue can be modified with the level..
	 */
	private int randomMoneyValue(int level){
		return rand.nextInt(level*10) + 1;
	}
	/**
	 * Picks a random item that the enemy should drop when defeated
	 * @param level
	 * @return the item
	 */
	public Item getRandomItem(int level){
		int num = rand.nextInt(2);
		Item item = null;
		if(num == 0){ //money
			item = new Money(randomMoneyValue(level));
		}else{
			item = cl.getRandomitem(level);
		}
		return item;
	}
	/**
	 * Picks a random item that the enemy should drop.. 
	 * random level between 1-Maxlevel..
	 * @param Maxlevel
	 * @return
	 */
	public Item getRandomItemRRandomLevel(int Maxlevel){
		if(Maxlevel == 1){
			return getRandomItem(1);
		}
		return getRandomItem(rand.nextInt(Maxlevel) + 1);
	}
}
