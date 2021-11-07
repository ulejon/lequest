package se.lequest.lequest.items;

public class Mace extends Weapon {
    public static final int MIN_DAMAGE_VALUE = 3;  //max-min interval of damage dealt.
    public static final int MAX_DAMAGE_VALUE = 10;
    public static final int ITEM_VALUE = 25; //The purchase value.
    public static final int MAP_LEVEL = 1;  //Available first on this map.
    public static final String image = "pictures/mace.gif";

    public Mace() {
        super(MAX_DAMAGE_VALUE, MIN_DAMAGE_VALUE, "Mace", ITEM_VALUE, MAP_LEVEL, image);
        this.setSellable(true);
        this.setDropable(true);
    }
}
