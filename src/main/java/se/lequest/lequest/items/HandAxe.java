package se.lequest.lequest.items;

public class HandAxe extends Weapon {

	public static final int MIN_DAMAGE_VALUE = 2; //Stab somewhere on the body except face
	public static final int MAX_DAMAGE_VALUE = 7; //Stab in the face
	public static final int MAP_LEVEL 				 = 1;
	public static final int ITEM_VALUE				 = 15;
	public static final String image = "pictures/handaxe.gif";

	public HandAxe(){

		super(MAX_DAMAGE_VALUE,MIN_DAMAGE_VALUE,"HandAxe", ITEM_VALUE,MAP_LEVEL, image);
		this.setSellable(true); 
		this.setDropable(true);
	}
}
