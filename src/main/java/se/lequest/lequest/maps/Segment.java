package se.lequest.lequest.maps;
import se.lequest.lequest.items.*;
import se.lequest.lequest.characters.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import se.lequest.lequest.constants.GameConstants;

/**
 * This class describes a Map segment or a "room" on the map..
 * it contains the Items Monsters or Enemy's drop.. and the enemy's in the 
 * segment..
 *
 */


public class Segment extends Observable implements java.io.Serializable{
	
	
	private ArrayList<Item> Itemslist ;
	private ArrayList<Enemy> Enemylist ;
	
	//only for use in Constructor 
	public static final int WEST_DOOR_OPEN = 8;
	public static final int NORTH_DOOR_OPEN = 4;
	public static final int EAST_DOOR_OPEN = 2;
	public static final int SOUTH_DOOR_OPEN = 1;
	public static final int NONE_OPEN = 0;
	
	//	To be used in doorarray
	private final int WEST_OPEN = 0; 
	private final int NORTH_OPEN = 1;
	private final int EAST_OPEN = 2;
	private final int SOUTH_OPEN = 3;
	
	private boolean[] doorarray;
	
	// Return types for DoorCodes
	private final int Error = -1;
	private final int OK = 1;
	
	private Position position;
	
	private boolean isVisible;
	private boolean isStart;
	private boolean isEnd;
	private boolean isBoss;
	/**
	 * Use the Constants as Doorcode.. 
	 * Example.. to have West and East-door open and South & North closed (Wall) 
	 * use the constructor as:
	 * Segement segmentname = new Segment(Segment.VEST_DOOR_OPEN + Segment.EAST_DOOR_OPEN);
	 * 
	 * @param doorCode the open doors
	 * @param position the position of the segment
	 */
	public Segment(int doorCode,Position position){
		doorarray = new boolean[4];
		if(setDoors(doorCode) == Error){
			System.out.println("Wrong DoorCode..(Segment.java)");
		}
		this.position = position;
		this.isVisible = false;
		this.isStart = false;
		this.isEnd = false;
		this.isBoss = false;
		Itemslist = new ArrayList<Item>();
		Enemylist = new ArrayList<Enemy>();
		
	}
	/**
	 * Creates a segment, use doorstring as "VS" to open West and South door or "NSE" to open North,South & East
	 * @param doorString Code to set doors
	 * @param position the position of the room
	 * @param isVisible if the room is visible by default
	 */
	public Segment(String doorString,Position position,boolean isVisible){
		doorarray = new boolean[4];
		if(setDoors(doorString) == Error){
			System.out.println("Wrong DoorCode..(Segment.java)");
		}
		this.position = position;
		this.isVisible = isVisible;
		this.isStart = false;
		this.isEnd = false;
		this.isBoss = false;
		Itemslist = new ArrayList<Item>();
		Enemylist = new ArrayList<Enemy>();
		
	}
	/**
	 * Creates a segment, use doorstring as "VS" to open West and South door or "NSE" to open North,South & East
	 * @param doorString the doorcode
	 * @param position the position of the room
	 * @param isVisible if the room is visible by default
	 * @param thaenemy a enemy in the room ( can be used to add bosses )(Or sertain enemys)
	 */
	public Segment(String doorString,Position position,boolean isVisible,Enemy thaenemy){
		doorarray = new boolean[4];
		if(setDoors(doorString) == Error){
			System.out.println("Wrong DoorCode..(Segment.java)");
		}
		this.position = position;
		this.isVisible = isVisible;
		this.isStart = false;
		this.isEnd = false;
		this.isBoss = false;
		Itemslist = new ArrayList<Item>();
		Enemylist = new ArrayList<Enemy>();
		Enemylist.add(thaenemy);
		
	}
	/**
	 * Construct a new Segment with X,doors open and ItemList with Items in the room
	 * And a number of Enemys 
	 * 
	 * @param doorCode the doorcode
	 * @param ItemList the list of items in the room
	 * @param EnemyList the list of enemys in to room
	 * @param position the position of the room
	 */
	
	public Segment(int doorCode,ArrayList<Item> ItemList,ArrayList<Enemy> EnemyList,Position position){
		doorarray = new boolean[4];
		if(setDoors(doorCode) == Error){
			System.out.println("Wrong DoorCode..(Segment.java)");
		}
		this.position = position;
		this.isVisible = false;
		this.isStart = false;
		this.isEnd = false;
		this.isBoss = false;
		this.Itemslist = ItemList;
		this.Enemylist = EnemyList;
	}
	
	/**
	 * Creates a Segment
	 * @param doorCode the doorcode 
	 * @param ItemList the items in the room
	 * @param position the positon of the room
	 */
	
	public Segment(int doorCode,ArrayList<Item> ItemList,Position position){
		doorarray = new boolean[4];
		if(setDoors(doorCode) == Error){
			System.out.println("Wrong DoorCode..(Segment.java)");
		}
		this.Itemslist = ItemList;
		this.isVisible = false;
		this.isStart = false;
		this.isEnd = false;
		this.isBoss = false;
		this.position = position;		
		this.Enemylist = new ArrayList<Enemy>();
	}
	
	/**
	 * Returns the list of Enemys.. A empty List if none..
	 * 
	 */
	
	public ArrayList<Enemy> GetEnemyList(){
		return this.Enemylist;
	}
	/**
	 * Returns the Enemy in topp of the list..
	 * @return
	 */
	public Enemy getAEnemy(){
		if(Enemylist.size() > 0){
			return this.Enemylist.get(0);
		}
		return null;
	}
	/**
	 * Returns the list of Items.. A empty List if none..
	 * 
	 */
	
	public ArrayList<Item> GetItemList(){
		return this.Itemslist;
	}

	/**
	 * Set the Enemys in a Segment.. A empty list if none..
	 */

	public void SetEnemyList(ArrayList<Enemy> EnemyList){
		this.Enemylist = EnemyList;
		this.setChanged();
		this.notifyObservers(GameConstants.SEGMENT_ENEMYS_CHANGE);

	}

	/**
	 * Set the Items in a Segment.. A empty list if none..
	 */
	
	public void SetItemList(ArrayList<Item> ItemList){
		this.Itemslist = ItemList;
		this.setChanged();
		this.notifyObservers(GameConstants.SEGMENT_ITEMS_CHANGE);
	}

	/**
	 * Add a Enemy tho the collection of Enemys..
	 */
	
	public void addEnemy(Enemy newEnemy){
		Enemylist.add(newEnemy);
		this.setChanged();
		this.notifyObservers(GameConstants.SEGMENT_ENEMYS_CHANGE);
	}
	
	/**
	 * Remove a Enemy from the room
	 */
	
	public void removeEnemy(Enemy remEnemy){
		if(Enemylist.contains(remEnemy)){
			Enemylist.remove(remEnemy);
			this.setChanged();
			this.notifyObservers(GameConstants.SEGMENT_ENEMYS_CHANGE);
		}else{
			//throw something?
			System.out.println("Enemy not in list.. Segment.java removeEnemy()");
		}
	}
	
	/**
	 * Removes an item from the room.
	 */
	public void removeItem(Item remItem){
		if(Itemslist.contains(remItem)){
			Itemslist.remove(remItem);
			this.setChanged();
			this.notifyObservers(GameConstants.SEGMENT_ITEMS_CHANGE);
		}else{
			//throw something?
			System.out.println("Item not in list.. Segment.java removeItem()");
		}
	}
	
	/**
	 * Adds an item to the room (player drops an item).
	 */
	public void addItem(Item item){
		Itemslist.add(item);
		this.setChanged();
		this.notifyObservers(GameConstants.SEGMENT_ITEMS_CHANGE);
	}
	
	/**
	 * Gets an item from the itemslist.
	 * @param Name of requested item.
	 * @return the item.
	 */
	public Item getItem(String name){
		Iterator<Item> it = Itemslist.iterator();
		while(it.hasNext()){
			Item item = it.next();
			if(item.getName().equals(name)){
				return item;
			}
		}
		return null; //item not found.
	}
	
	/**
	 * returns the number of items in the segment..
	 * @return the size
	 */
	public int getItemCount(){
		return Itemslist.size();
	}
	
	/**
	 * This function will replace all of the Enemys in the room with
	 * the replacement enemy..
	 * 
	 * That is.. Clear the collection and add the replacementEnemy
	 */
	
	public void setEnemy(Enemy replacementEnemy){
		Enemylist.clear();
		Enemylist.add(replacementEnemy);
		this.setChanged();
		this.notifyObservers(GameConstants.SEGMENT_ENEMYS_CHANGE);
	}
	/**
	 * Clear all of the Enemys from the Room..
	 *
	 */
	public void clearEnemys(){
		this.Enemylist.clear();
	}
	/**
	 * Returns the number of enemys in the segement..
	 * @return
	 */
	public int getEnemyCount(){
		if(this.Enemylist == null ){
			return 0;
		}
		return this.Enemylist.size();
	}
		
	/**
	 * 
	 * This function translates the doorCodes to Open/Closed Doors;
	 * Returns OK och success, or Error on failure
	 * 
	 * Usage: to set Vest and East-door open, and,  South & North closed (Wall) use:
	 * setDoors(Segment.VEST_DOOR_OPEN + Segment.EAST_DOOR_OPEN);
	 * @return -1 on Error and 1 on success;
	 */
	public int setDoors(int doorCode){
		if(doorCode > (Segment.EAST_DOOR_OPEN + Segment.NORTH_DOOR_OPEN + Segment.SOUTH_DOOR_OPEN + Segment.WEST_DOOR_OPEN )){
			return Error;
		}
		int[] codes = {WEST_DOOR_OPEN,NORTH_DOOR_OPEN,EAST_DOOR_OPEN,SOUTH_DOOR_OPEN};
		int[] doors = {WEST_OPEN,NORTH_OPEN,EAST_OPEN,SOUTH_OPEN};
		
		/* loop threw the Codes .. Open/Close the doors and check if it matches. */
		for(int i = 0; i < 4; i++){
			if(doorCode >= codes[i]){
				doorCode = doorCode - codes[i];
				this.doorarray[doors[i]] = true;
			}else{
				this.doorarray[doors[i]] = false;
			}
		}		
		if(doorCode != 0){
			return Error;
		}
		return OK;
	}
	/**
	 * Set Doors open.. Example "VS" sets the Vest & South door open.
	 * "NE" sets the North and East door open..
	 * @param doorString
	 * @return
	 */
	public int setDoors(String doorString){
		String testString = doorString.toLowerCase();
		if(testString.length() > 4){
			return Error;
		}
		String[] regexp = {"^.*v{1}.*","^.*n{1}.*","^.*e{1}.*","^.*s{1}.*"};
		for(int i= 0; i < 4;i++){
			if(testString.matches(regexp[i])){
				this.doorarray[i] = true;
			}
			else{
				this.doorarray[i] = false;
			}
		}		
		return OK;
	}
	/**
	 * Returns the Currentpostion of the segment
	 * @return current position
	 */
	public Position getPosition(){
		return this.position;
	}
	/**
	 * Set a new position for the segment
	 * @param newPosition
	 */
	public void setPosition(Position newPosition){
		this.position = newPosition;
	}
	/**
	 * Returns true if Segments EastDoor is Open..
	 * @return true if open, else false
	 */
	public boolean isEastDoorOpen(){
		return doorarray[EAST_OPEN];
	}
	/**
	 * Returns true if Segments VestDoor is Open..
	 * @return true if open, else false
	 */
	public boolean isWestDoorOpen(){
		return doorarray[WEST_OPEN];
	}
	/**
	 * Returns true if Segments NorthDoor is Open..
	 * @return true if open, else false
	 */
	public boolean isNorthDoorOpen(){
		return doorarray[NORTH_OPEN];
	}
	/**
	 * Returns true if Segments SouthDoor is Open..
	 * @return true if open, else false
	 */
	public boolean isSouthDoorOpen(){
		return doorarray[SOUTH_OPEN];
	}
	/**
	 * Returns true if segment is visible
	 * @return isvisible = true
	 */
	public boolean isvisible(){
		return this.isVisible;
	}
	/**
	 * returns true if the segment is the startsegemnt..
	 * 
	 * @return
	 */
	public boolean istart(){
		return this.isStart;
	}
	/**
	 * Set if the segment is visible
	 * @param visable
	 */
	public void setVisible(boolean visable){
		this.isVisible = visable;
	}
	/**
	 * Set if the segment is the startsegment..
	 * if true then isvisible = true
	 * @param visable
	 */
	public void setStart(boolean isstart){
		this.isStart = isstart;
		if(isstart){
			this.setVisible(true);
		}
	}
	/**
	 * returns true if this is the room that leads to the other map..
	 * or ends the game..
	 */
	public boolean isEnd(){
		return this.isEnd;
	}
	/**
	 * Set if this is the final segemnt on the map.. that leads to the next map or ends the game..
	 */
	public void setIsEnd(boolean isend){
		this.isEnd = isend;
	}
	public void setIsBoss(boolean val){
		this.isBoss = val;
	}
	public boolean getIsBoss(){
		return this.isBoss;
	}
}
