package se.lequest.lequest.items;

/**
 * The iron shield is a heavy shield
 * Protects against heave weapons like swords and clubs
 * @author ulle
 *
 */
public class IronShield extends Shield {

	private static final int MAX_PROTECTION_VALUE = 14; 
	private static final int MIN_PROTECTION_VALUE = 12; 
	private static final int ARMOR_VALUE = 50;  //The amount of gold when you sell this
	private static final String ARMOR_NAME = "Iron shield";
	private static final int ARMOR_LEVEL = 2; //first avaliable on this level
	private static final String IMAGE = "pictures/ironshield.gif";
	public IronShield() {
		super(MAX_PROTECTION_VALUE, MIN_PROTECTION_VALUE, ARMOR_NAME, ARMOR_VALUE,ARMOR_LEVEL,IMAGE);
	}

}
