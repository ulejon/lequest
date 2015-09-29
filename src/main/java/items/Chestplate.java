package items;

/**
 * Abstract class to model chest plates
 * There can be several subclasses to chestplate; iron plate, brynja (don't know english word)
 * and lighter plates
 * @author ulle
 *
 */
public abstract class Chestplate extends Armor {

	public Chestplate(int maxProtectionValue, int minProtectionValue, String name,int value,int level,
			String image) {
		super(maxProtectionValue, minProtectionValue, name, value,level,image);
		
	}

}
