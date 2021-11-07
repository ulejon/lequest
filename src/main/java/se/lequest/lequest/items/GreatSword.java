package se.lequest.lequest.items;

public class GreatSword extends Weapon {
    public static final int MIN_DAMAGE_VALUE = 10;  //max-min interval of damage dealt.
    public static final int MAX_DAMAGE_VALUE = 20;
    public static final int ITEM_VALUE = 100; //The purchase value
    public static final int MAP_LEVEL = 2;  //Available first on this map
    public static final String image = "pictures/greatsword.gif";

    public GreatSword() {

        super(MAX_DAMAGE_VALUE, MIN_DAMAGE_VALUE, "Great Sword", ITEM_VALUE, MAP_LEVEL, image);
        this.setSellable(true);
        this.setDropable(true);
    }
}
