package items;

public class Dirk extends Weapon{
	public static final int MIN_DAMAGE_VALUE = 3; //max-min interval of damage dealt.
	public static final int MAX_DAMAGE_VALUE = 6; 
	public static final int ITEM_VALUE = 16;	  //The purchase value.
	public static final int MAP_LEVEL = 1;		  //Available first on this map.
	public static final String image = "pictures/dirk.gif";

	public Dirk(){
		super(MAX_DAMAGE_VALUE,MIN_DAMAGE_VALUE,"Dirk",ITEM_VALUE,MAP_LEVEL, image);
		this.setSellable(true); 
		this.setDropable(true);
	}
}
