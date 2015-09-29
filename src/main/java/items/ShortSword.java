package items;

public class ShortSword extends Weapon {
	public static final int MIN_DAMAGE_VALUE = 4;  //max-min interval of damage dealt.
	public static final int MAX_DAMAGE_VALUE = 6; 
	public static final int ITEM_VALUE 			 = 10; //The purchase value
	public static final int MAP_LEVEL 		 = 1;  //Available first on this map
	public static final String image = "pictures/shortsword.gif";
	public ShortSword(){
		super(MAX_DAMAGE_VALUE,MIN_DAMAGE_VALUE,"Short Sword", ITEM_VALUE,MAP_LEVEL, image);
		this.setSellable(true); 
		this.setDropable(true);
	}
}
