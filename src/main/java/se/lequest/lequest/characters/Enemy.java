package se.lequest.lequest.characters;
import se.lequest.lequest.items.ItemsCollection;
import java.util.Random;

import se.lequest.lequest.constants.GameConstants;
public class Enemy extends Character {
	private ItemsCollection itemcollection;
	private int level;
	private int experiencePoints;
	private Random rand;
	private static int seed = 0;
	/**
	 * Creates a Enemy object
	 * @param name The name of the Enemy (Ex, Skeleton, Zoombie etc) 
	 * @param health The current health of the Enemy
	 * @param maxHealth The maximum health the Enemy can have..
	 * @param maxNrofItems The number of items the Enemy can have in backpack
	 * @param level The level the enemy exist on
	 */
	public Enemy(String name, int health, int maxHealth, int maxNrofItems,int level) {
		super(name, health, maxHealth, maxNrofItems);
		this.itemcollection = ItemsCollection.getInstace();
		this.level = level;
		rand = new Random(System.currentTimeMillis()+ ++Enemy.seed);
	}
	/**
	 * Creates a Enemy object
	 * @param name The name of the Enemy (Ex, Skeleton, Zoombie etc) 
	 * @param health The current health of the Enemy
	 * @param maxHealth The maximum health the Enemy can have..
	 * @param maxNrofItems The number of items the Enemy can have in backpack
	 * @param level The level the enemy exist on
	 * @param picture The path to the picture representing the Enemy
	 */
	public Enemy(String name, int health, int maxHealth, int maxNrofItems,int level,String picture) {
		super(name, health, maxHealth, maxNrofItems,picture);
		this.itemcollection = ItemsCollection.getInstace();
		this.level = level;
		rand = new Random(System.currentTimeMillis()+ ++Enemy.seed);
	}
	/**
	 * 
	 * Creates a Enemy object
	 * @param name The name of the Enemy (Ex, Skeleton, Zoombie etc) 
	 * @param health The current health of the Enemy
	 * @param maxHealth The maximum health the Enemy can have..
	 * @param maxNrofItems The number of items the Enemy can have in backpack
	 * @param level The level the enemy exist on
	 * @param picture The path to the picture representing the Enemy
	 * @param exp The Experiense level of the Enemy
	 */
	public Enemy(String name, int health, int maxHealth, int maxNrofItems,int level,String picture, int exp) {
		super(name, health, maxHealth, maxNrofItems,picture);
		this.itemcollection = ItemsCollection.getInstace();
		this.level = level;
		this.experiencePoints = exp;
		rand = new Random(System.currentTimeMillis()+ ++Enemy.seed);
	}
	
	//Use this if a enemy of a sertain level only droops weapons of the same level..
	/*public void generatedropps(){
		int randint = rand.nextInt(this.getMaxnrofItems() +1);//+1 to get right amount..
		for(int i = 0; i < randint; i++){
			this.addItem(itemcollection.getRandomItem(this.level));
		}
	}*/
	
	
	/**
	 * Generate the Items the enemy can drop..
	 * 
	 */
	public void generatedropps(){
		int randint = rand.nextInt(this.getMaxnrofItems() +1);//+1 to get right amount..
		for(int i = 0; i < randint; i++){
			this.addItem(itemcollection.getRandomItemRRandomLevel(this.level));
		}
	}
	/**
	 * Set the Current health of the Enemy
	 */
	public void setHealth(int newHealth){
		super.setHealth(newHealth);
		setChanged();
		notifyObservers(GameConstants.ENEMY_CHANGE_HP);
	}
	/**
	 * Decrease the health of the enemy
	 */
	public void decreaseHealth(int points){
		super.decreaseHealth(points);
		setChanged();
		notifyObservers(GameConstants.ENEMY_CHANGE_HP);
	}
	/**
	 * Add health to the enemy
	 */
	public void addHealth(int pointstoadd){
		super.addHealth(pointstoadd);
		setChanged();
		notifyObservers(GameConstants.ENEMY_CHANGE_HP);
	}
	/**
	 * Returns the level the enemy exist on.
	 * @return
	 */
	public int getLevel(){
		return level;
	}
	/**
	 * Returns the Exp points of the enemy.. 
	 * @return
	 */
	public int getExperiencePoints(){
		return experiencePoints;
	}

}
