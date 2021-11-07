package se.lequest.lequest.items;

/**
 * Abstract class to model gloves
 * There can be several subclasses to gloves
 *
 * @author ulle
 */
public abstract class Gloves extends Armor {

    public Gloves(int maxProtectionValue, int minProtectionValue, String name, int value, int level,
                  String image) {
        super(maxProtectionValue, minProtectionValue, name, value, level, image);

    }

}
