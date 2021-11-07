package se.lequest.lequest.items;

public class Bone extends Weapon {
    private static final int MAX_DAMAGE = 3;
    private static final int MIN_DAMAGE = 1;
    private static final int ITEM_VALUE = 2;
    public static final int MAP_LEVEL = 1;
    public static final String image = "pictures/bone.gif";

    public Bone() {
        super(MAX_DAMAGE, MIN_DAMAGE, "Bone", ITEM_VALUE, MAP_LEVEL, image);
        this.setSellable(true);
        this.setDropable(true);
    }

}
