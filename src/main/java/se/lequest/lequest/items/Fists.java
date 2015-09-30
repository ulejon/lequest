package se.lequest.lequest.items;

public class Fists extends Weapon {
	public static final int MIN_DAMAGE_VALUE = 1; //puch somewhere on the body except face
	public static final int MAX_DAMAGE_VALUE = 3; //punch in the face
	public static final int MAP_LEVEL = 1;
	public static final int ITEM_VALUE = 0;
	public static final String image = "pictures/fists.jpg";


	public Fists(){

		super(MAX_DAMAGE_VALUE,MIN_DAMAGE_VALUE,"Fists", ITEM_VALUE,MAP_LEVEL, image);
		this.setSellable(false); //cant sell the fists
		this.setDropable(false); //cant drop your fists
	}
}
