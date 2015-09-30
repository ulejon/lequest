package se.lequest.lequest.items;

/**
 * @author ulle
 *
 */
public class WoodenShield extends Shield {

	private static final int MAX_PROTECTION_VALUE = 6; 
	/*
	 * this plate will reduce the damage by 40% (this is min) when the enemy hit the torso
	 */
	private static final int MIN_PROTECTION_VALUE = 4;
	private static final int ARMOR_VALUE = 30;  //The amount of gold when you sell this
	private static final String ARMOR_NAME = "Wooden shield";
	private static final int ARMOR_LEVEL = 1; //first avaliable on this level
	private static final String IMAGE = "pictures/woodenshield.gif";
	public WoodenShield() {
		super(MAX_PROTECTION_VALUE, MIN_PROTECTION_VALUE, ARMOR_NAME, ARMOR_VALUE,ARMOR_LEVEL,IMAGE);
	}

}
