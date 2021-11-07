package se.lequest.lequest.items;

public class Club extends Weapon {
    public static final int MIN_CLUB_DAMAGE_VALUE = 1;  //max-min interval of damage dealt.
    public static final int MAX_CLUB_DAMAGE_VALUE = 7;
    public static final int CLUB_VALUE = 8;  //The purchase value
    public static final int CLUB_MAP_LEVEL = 1;  //Available first on this map
    public static final String image = "pictures/club.gif";

    public Club() {
        super(MAX_CLUB_DAMAGE_VALUE, MIN_CLUB_DAMAGE_VALUE, "Club", CLUB_VALUE, CLUB_MAP_LEVEL, image);
        this.setSellable(true);
        this.setDropable(true);
    }
}
