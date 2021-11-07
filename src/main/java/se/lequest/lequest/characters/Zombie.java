package se.lequest.lequest.characters;

import se.lequest.lequest.items.HandAxe;

public class Zombie extends Enemy {
    private static final int MAX_HEALTH = 40;
    private static final int ITEM_LIMIT = 3;
    private static final int MAP_LEVEL = 1;
    private static final int EXPERIENCE_POINTS = 18;
    private static final String IMAGE = "pictures/zombie.gif";

    public Zombie() {
        super("Zombie", MAX_HEALTH, MAX_HEALTH, ITEM_LIMIT, MAP_LEVEL, IMAGE, EXPERIENCE_POINTS);
        this.setCurrentWeapon(new HandAxe());
        this.generatedropps();
    }
}
