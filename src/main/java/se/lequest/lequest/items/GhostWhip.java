package se.lequest.lequest.items;

public class GhostWhip extends Weapon {

    public static final int MIN_DAMAGE_VALUE = 10; //Stab somewhere on the body except face
    public static final int MAX_DAMAGE_VALUE = 20; //Stab in the face
    public static final int ITEM_VALUE = 50;
    public static final int MAP_LEVEL = 1;
    public static final String image = "pictures/ghostwhip.jpg";

    public GhostWhip() {
        super(MAX_DAMAGE_VALUE, MIN_DAMAGE_VALUE, "Ghostwhip", ITEM_VALUE, MAP_LEVEL, image);
        this.setSellable(true);
        this.setDropable(true);
    }
}
