package items;

public class Fireblaster extends Weapon {
	public static final int MIN_DAMAGE_VALUE = 5; //Stab somewhere on the body except face
	public static final int MAX_DAMAGE_VALUE = 10; //Stab in the face
	public static final int MAP_LEVEL = 1;
	public static final int ITEM_VALUE = 30;
	public static final String image = "pictures/fireblaster.gif";

	public Fireblaster(){
		super(MAX_DAMAGE_VALUE,MIN_DAMAGE_VALUE,"Fireblaster",ITEM_VALUE,MAP_LEVEL, image);
		this.setSellable(true); 
		this.setDropable(true);
	}
}
