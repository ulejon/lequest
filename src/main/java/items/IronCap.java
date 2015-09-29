package items;

/**
 * Iron helmet is the best kind of helmet
 * Protects against the most kinds of weapons
 * @author ulle
 *
 */
public class IronCap extends Helmet {
	/*
	 * the helmet will reduce the damage by 70% (this is max) when the enemy hit the head
	 */
	private static final int MAX_PROTECTION_VALUE = 11; 
	/*
	 * the helmet will reduce the damage by 40% (this is min) when the enemy hit the head
	 */
	private static final int MIN_PROTECTION_VALUE = 8;
	private static final int ARMOR_VALUE = 15;  //The amount of gold when you sell this
	private static final String ARMOR_NAME = "Iron cap";
	private static final int ARMOR_LEVEL = 3; //first avaliable on this level
	private static final String IMAGE = "pictures/ironcap.gif";
	public IronCap() {
		super(MAX_PROTECTION_VALUE, MIN_PROTECTION_VALUE, ARMOR_NAME, ARMOR_VALUE,ARMOR_LEVEL,IMAGE);
	}

}
