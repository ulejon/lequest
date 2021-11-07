package se.lequest.lequest.items;

public class LongSword extends Weapon {
    public static final int MIN_DAMAGE_VALUE = 3;  //max-min interval of damage dealt.
    public static final int MAX_DAMAGE_VALUE = 19;
    public static final int ITEM_VALUE = 50; //The purchase value
    public static final int MAP_LEVEL = 2;  //Available first on this map
    public static final String image = "pictures/longsword.gif";

    public LongSword() {
        super(MAX_DAMAGE_VALUE, MIN_DAMAGE_VALUE, "Long Sword", ITEM_VALUE, MAP_LEVEL, image);
        this.setSellable(true);
        this.setDropable(true);
    }
}
