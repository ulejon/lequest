package se.lequest.lequest.items;

/**
 * Abstract class to model helmets
 * There can be several subclasses to helmet, for example iron helmet 
 * @author ulle
 *
 */
public abstract class Helmet extends Armor {

	public Helmet(int maxProtectionValue, int minProtectionValue, String name,int value,int level,
			String image) {
		super(maxProtectionValue, minProtectionValue, name, value,level,image);
		
	}

}
