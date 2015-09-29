package items;

/**
 * Abstract class to model boots
 * There can be several subclasses to boots, for example leather boots
 * and boots made from less durable material 
 * @author ulle
 *
 */
public abstract class Boots extends Armor {

	public Boots(int maxProtectionValue, int minProtectionValue, String name,int value,int level,
			String image) {
		super(maxProtectionValue, minProtectionValue, name, value,level,image);
		
	}

}
