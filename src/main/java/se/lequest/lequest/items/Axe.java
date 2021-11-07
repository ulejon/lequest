package se.lequest.lequest.items;


public class Axe extends Weapon {
    public static final int MIN_DAMAGE_VALUE = 3; //max-min interval of damage dealt.
    public static final int MAX_DAMAGE_VALUE = 11;
    public static final int ITEM_VALUE = 30;      //The purchase value.
    public static final int MAP_LEVEL = 1;          //Available first on this map.
    public static final String image = "pictures/axe.gif"; //image

    public Axe() {
        super(MAX_DAMAGE_VALUE, MIN_DAMAGE_VALUE, "Axe", ITEM_VALUE, MAP_LEVEL, image);
        this.setSellable(true);
        this.setDropable(true);
    }
}
