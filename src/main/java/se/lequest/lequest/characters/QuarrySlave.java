package se.lequest.lequest.characters;

import se.lequest.lequest.items.Pitchfork;

public class QuarrySlave extends Enemy {
    private static final int MAX_HEALTH = 25;
    private static final int ITEM_LIMIT = 3;
    private static final int MAP_LEVEL = 1;
    private static final int EXPERIENCE_POINTS = 16;
    private static final String IMAGE = "pictures/quarryslave.gif";

    public QuarrySlave() {
        super("QuarrySlave", MAX_HEALTH, MAX_HEALTH, ITEM_LIMIT, MAP_LEVEL, IMAGE, EXPERIENCE_POINTS);
        this.setCurrentWeapon(new Pitchfork());
        this.generatedropps();
    }
}
