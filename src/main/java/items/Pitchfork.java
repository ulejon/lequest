package items;

public class Pitchfork extends Weapon {
	public static final int MIN_DAMAGE_VALUE = 1; //Stab somewhere on the body except face
	public static final int MAX_DAMAGE_VALUE = 5; //Stab in the face
	public static final int MAP_LEVEL 				 = 1;
	public static final int ITEM_VALUE				 = 3;
	public static final String image = "pictures/pitchfork.jpg";
	public Pitchfork(){
		super(MAX_DAMAGE_VALUE,MIN_DAMAGE_VALUE,"Pitchfork", ITEM_VALUE,MAP_LEVEL, image);
		this.setSellable(true); 
		this.setDropable(true);
	}
}
