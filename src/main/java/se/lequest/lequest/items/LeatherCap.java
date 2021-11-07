package se.lequest.lequest.items;

/**
 * The light helmet is a simple helmet made of some light materials
 * Protects against weak weapons like daggers, not powerful swords and such
 *
 * @author ulle
 */
public class LeatherCap extends Helmet {
    /*
     * the helmet will reduce the damage by 30% (this is max) when the enemy hit the head
     */
    private static final int MAX_PROTECTION_VALUE = 5;
    /*
     * the helmet will reduce the damage by 10% (this is min) when the enemy hit the head
     */
    private static final int MIN_PROTECTION_VALUE = 3;
    private static final int ARMOR_VALUE = 10;  //The amount of gold when you sell this
    private static final String ARMOR_NAME = "Leather cap";
    private static final int ARMOR_LEVEL = 1; //first avaliable on this level
    private static final String IMAGE = "pictures/leathercap.gif";

    public LeatherCap() {
        super(MAX_PROTECTION_VALUE, MIN_PROTECTION_VALUE, ARMOR_NAME, ARMOR_VALUE, ARMOR_LEVEL, IMAGE);
    }

}
