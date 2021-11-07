package se.lequest.lequest.items;

/**
 * Iron chestplate is the best kind of plate
 * Protects against the most kinds of weapons
 *
 * @author ulle
 */
public class IronChestplate extends Chestplate {
    /*
     * this plate will reduce the damage by 70% (this is max) when the enemy hit the torso
     */
    private static final int MAX_PROTECTION_VALUE = 65;
    /*
     * this plate will reduce the damage by 40% (this is min) when the enemy hit the torso
     */
    private static final int MIN_PROTECTION_VALUE = 50;
    private static final int ARMOR_VALUE = 35;  //The amount of gold when you sell this
    private static final String ARMOR_NAME = "Iron chestplate";
    private static final int ARMOR_LEVEL = 3; //first avaliable on this level
    private static final String IMAGE = "pictures/ironarmor.gif";

    public IronChestplate() {
        super(MAX_PROTECTION_VALUE, MIN_PROTECTION_VALUE, ARMOR_NAME, ARMOR_VALUE, ARMOR_LEVEL, IMAGE);
    }

}
