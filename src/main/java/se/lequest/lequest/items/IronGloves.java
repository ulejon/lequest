package se.lequest.lequest.items;

/**
 * Iron gloves are the best kind of gloves
 * Protects against the most kinds of weapons hitting your hands
 *
 * @author ulle
 */
public class IronGloves extends Gloves {
    /*
     * this plate will reduce the damage by 50% (this is max) when the enemy hit the torso
     */
    private static final int MAX_PROTECTION_VALUE = 6;
    /*
     * this plate will reduce the damage by 25% (this is min) when the enemy hit the torso
     */
    private static final int MIN_PROTECTION_VALUE = 5;
    private static final int ARMOR_VALUE = 20;  //The amount of gold when you sell this
    private static final String ARMOR_NAME = "Iron gloves";
    private static final int ARMOR_LEVEL = 3; //first avaliable on this level
    private static final String IMAGE = "pictures/irongloves.gif";

    public IronGloves() {
        super(MAX_PROTECTION_VALUE, MIN_PROTECTION_VALUE, ARMOR_NAME, ARMOR_VALUE, ARMOR_LEVEL, IMAGE);
    }

}
