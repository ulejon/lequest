package se.lequest.lequest.items;

public class DoubleAxe extends Weapon {
    public static final int MIN_DAMAGE_VALUE = 5; //max-min interval of damage dealt.
    public static final int MAX_DAMAGE_VALUE = 12;
    public static final int ITEM_VALUE = 35;      //The purchase value.
    public static final int MAP_LEVEL = 2;          //Available first on this map.
    public static final String image = "pictures/doubleaxe.gif";

    public DoubleAxe() {
        super(MAX_DAMAGE_VALUE, MIN_DAMAGE_VALUE, "Double Axe", ITEM_VALUE, MAP_LEVEL, image);
        this.setSellable(true);
        this.setDropable(true);
    }
}
