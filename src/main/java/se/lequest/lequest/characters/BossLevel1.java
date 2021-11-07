package se.lequest.lequest.characters;

import se.lequest.lequest.items.GreatSword;

public class BossLevel1 extends Enemy {
    private static final int MAX_HEALTH = 160;
    private static final int ITEM_LIMIT = 5;
    private static final int MAP_LEVEL = 1;
    private static final int EXPERIENCE_POINTS = 120;
    private static final String IMAGE = "pictures/level1boss.jpg";

    public BossLevel1() {
        super("Level1 Boss", MAX_HEALTH, MAX_HEALTH, ITEM_LIMIT, MAP_LEVEL, IMAGE, EXPERIENCE_POINTS);
        this.setCurrentWeapon(new GreatSword());
        this.generatedropps();
    }
}
