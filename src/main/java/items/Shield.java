package items;

/**
 * Abstract class to model shields
 * There can be several subclasses to shields, for example iron shields and lighter shields 
 * @author ulle
 *
 */
public abstract class Shield extends Armor {
	
	public Shield(int maxProtectionValue, int minProtectionValue, String name,int value,int level,
			String image) {
		super(maxProtectionValue, minProtectionValue, name, value,level,image);
		
	}

}
