package se.lequest.lequest.items;

/**
 * The leather boots is the best kind of boots
 * Protects against sharp objects lying on the ground
 * @author ulle
 *
 */
public class LeatherBoots extends Boots {

	private static final int MAX_PROTECTION_VALUE = 3; 
	/*
	 * this plate will reduce the damage by 40% (this is min) when the enemy hit the torso
	 */
	private static final int MIN_PROTECTION_VALUE = 2;
	private static final int ARMOR_VALUE = 5;  //The amount of gold when you sell this
	private static final String ARMOR_NAME = "Leather boots";
	private static final int ARMOR_LEVEL = 1; //first avaliable on this level
	private static final String IMAGE = "pictures/leatherboots.gif";
	public LeatherBoots() {
		super(MAX_PROTECTION_VALUE, MIN_PROTECTION_VALUE, ARMOR_NAME, ARMOR_VALUE,ARMOR_LEVEL,IMAGE);
	}

}
