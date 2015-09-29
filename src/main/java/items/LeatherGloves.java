package items;

/**
 * 
 * @author ulle
 *
 */
public class LeatherGloves extends Gloves {
	/*
	 * the helmet will reduce the damage by 30% (this is max) when the enemy hit the head
	 */
	private static final int MAX_PROTECTION_VALUE = 3; 
	/*
	 * the helmet will reduce the damage by 10% (this is min) when the enemy hit the head
	 */
	private static final int MIN_PROTECTION_VALUE = 2;
	private static final int ARMOR_VALUE = 5;  //The amount of gold when you sell this
	private static final String ARMOR_NAME = "Leather gloves";
	private static final int ARMOR_LEVEL = 1; //first avaliable on this level
	private static final String IMAGE = "pictures/leathergloves.gif";
	public LeatherGloves() {
		super(MAX_PROTECTION_VALUE, MIN_PROTECTION_VALUE, ARMOR_NAME, ARMOR_VALUE,ARMOR_LEVEL,IMAGE);
	}

}
