package se.lequest.lequest.items;

import se.lequest.lequest.soundplayer.SoundPlayer;

public class Dagger extends Weapon {
    public static final int MIN_DAMAGE_VALUE = 3; //Stab somewhere on the body except face
    public static final int MAX_DAMAGE_VALUE = 5; //Stab in the face
    public static final int ITEM_VALUE = 10;
    public static final int MAP_LEVEL = 1;
    public static final String image = "pictures/dagger.gif";

    private final SoundPlayer soundPlayer;

    public Dagger() {
        super(MAX_DAMAGE_VALUE, MIN_DAMAGE_VALUE, "Dagger", ITEM_VALUE, MAP_LEVEL, image);
        this.setSellable(true);
        this.setDropable(true);
        this.soundPlayer = new SoundPlayer();
    }

    /**
     * Dagger has a sound.. so play it when attacking..
     */
    public int attack() {
        int ret = super.attack();
        soundPlayer.play("/sounds/Sword1.wav");
        return ret;
    }
}
