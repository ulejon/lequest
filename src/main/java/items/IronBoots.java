package items;

/**
 * The iron boots is the best kind of boots
 * Protects against sharp objects lying on the ground
 * @author ulle
 *
 */
public class IronBoots extends Boots {
	
	private static final int MAX_PROTECTION_VALUE = 9; 
	/*
	 * this plate will reduce the damage by 40% (this is min) when the enemy hit the torso
	 */
	private static final int MIN_PROTECTION_VALUE = 8;
	private static final int ARMOR_VALUE = 10;  //The amount of gold when you sell this
	private static final String ARMOR_NAME = "Iron boots";
	private static final int ARMOR_LEVEL = 2; //first avaliable on this level
	private static final String IMAGE = "pictures/ironboots.gif";
	public IronBoots() {
		super(MAX_PROTECTION_VALUE, MIN_PROTECTION_VALUE, ARMOR_NAME, ARMOR_VALUE,ARMOR_LEVEL,IMAGE);
	}

}
