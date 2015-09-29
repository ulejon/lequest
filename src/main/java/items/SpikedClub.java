package items;

public class SpikedClub extends Weapon {
	public static final int MIN_DAMAGE_VALUE = 5;  //max-min interval of damage dealt.
	public static final int MAX_DAMAGE_VALUE = 8;
	public static final int ITEM_VALUE 			 = 15; //The purchase value
	public static final int MAP_LEVEL 		 = 1;  //Available first on this map
	public static final String image = "pictures/spikedclub.gif";
	public SpikedClub(){
		super(MAX_DAMAGE_VALUE,MIN_DAMAGE_VALUE,"SpikedClub", ITEM_VALUE,MAP_LEVEL, image);
		this.setSellable(true); 
		this.setDropable(true);
	}
}