package se.lequest.lequest.items;

import java.util.Random;

/**
 * This is a abstract class for Weapons..
 *
 * @author hagen
 */
public abstract class Weapon extends Item {

    private int minDamageValue, maxDamageValue;
    private Random randomgen;

    /**
     * Create a new Weapon
     *
     * @param maxDamageValue The maximum value of Damage made by the weapon
     * @param minDamageValue The minimum value of Damage made by the weapon
     * @param name           the name of the weapon
     * @param value          the value of the weapon
     * @param weaponlevel    the level of the weapon
     */
    public Weapon(int maxDamageValue, int minDamageValue, String name, int value, int weaponlevel) {
        super(name, value, weaponlevel);
        this.maxDamageValue = maxDamageValue;
        this.minDamageValue = minDamageValue;
        randomgen = new Random(System.currentTimeMillis());
    }

    /**
     * creates a Weapon with a picture
     *
     * @param maxDamageValue The maximum value of Damage made by the weapon
     * @param minDamageValue The minimum value of Damage made by the weapon
     * @param name           the name of the weapon
     * @param value          the value of the weapon
     * @param weaponlevel    the level of the weapon
     * @param image          the path of the picture
     */
    public Weapon(int maxDamageValue, int minDamageValue, String name, int value, int weaponlevel, String image) {
        super(name, value, image, weaponlevel);
        this.maxDamageValue = maxDamageValue;
        this.minDamageValue = minDamageValue;
        randomgen = new Random(System.currentTimeMillis());
    }

    /**
     * Function to get the weapon level
     * With a weapon level we mean on which game level the weapon can be found
     *
     * @return weapon level
     */
    public int getWeaponLevel() {
        return super.getItemLevel();
    }

    /**
     * Function to set the level of a weapon
     *
     * @param level
     */
    public void setWeaponLevel(int level) {
        super.setItemLevel(level);
    }

    /**
     * Function to get the min damage value of the current weapon
     *
     * @return damage level
     */
    public int getMinDamageValue() {
        return this.minDamageValue;
    }

    /**
     * Function to get the max damage value of the current weapon
     *
     * @return damage level
     */
    public int getMaxDamageValue() {
        return this.maxDamageValue;
    }

    /**
     * Function to calculate how much damage a weapon makes.
     * Generates a value between max and min damage values
     *
     * @return how much damage
     */
    public int attack() {
        return randomgen.nextInt((this.maxDamageValue - this.minDamageValue) + 1) + this.minDamageValue;
    }

    /**
     * Get a description of the weapon.
     * name, attack-rating, level & sellvalue.
     *
     * @return the String description
     */
    public String getDescription() {
        String description = getName() + " (Weapon) \n" +
                "Attack damage: " + minDamageValue + "-" + maxDamageValue + "\n" +
                "Weapon level: " + super.getItemLevel() + "\n";
        if (isSellable()) {
            return description + "Sell value: " + getSellValue() + " GC";
        } else {
            return description + getName() + " is not sellable.";
        }
    }

}
