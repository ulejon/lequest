package se.lequest.lequest.characters;

import se.lequest.lequest.items.Mace;

public class DarkShade extends Enemy {
    private static final int MAX_HEALTH = 36;
    private static final int ITEM_LIMIT = 2;
    private static final int MAP_LEVEL = 1;
    private static final int EXPERIENCE_POINTS = 24;
    private static final String IMAGE = "pictures/darkshade.gif";

    public DarkShade() {
        super("Dark Shade", MAX_HEALTH, MAX_HEALTH, ITEM_LIMIT, MAP_LEVEL, IMAGE, EXPERIENCE_POINTS);
        this.setCurrentWeapon(new Mace());
        this.generatedropps();
    }
}
