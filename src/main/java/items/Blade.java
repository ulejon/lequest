package items;

public class Blade extends Weapon{
	public static final int MIN_DAMAGE_VALUE = 4;	//max-min interval of damage dealt.
	public static final int MAX_DAMAGE_VALUE = 12;
	public static final int ITEM_VALUE = 30;		//The purchase value.
	public static final int MAP_LEVEL = 1;			//Available first on this map.
	public static final String image = "pictures/blade.gif";

	public Blade(){
		super(MAX_DAMAGE_VALUE,MIN_DAMAGE_VALUE,"Blade",ITEM_VALUE,MAP_LEVEL, image);
		this.setSellable(true); 
		this.setDropable(true);
	}
}
