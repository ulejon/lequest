package se.lequest.lequest.characters;
import se.lequest.lequest.items.LongSword;

public class Hornstriker extends Enemy {
	private static final int MAX_HEALTH = 40; 
	private static final int ITEM_LIMIT = 5;
	private static final int MAP_LEVEL = 2;
	private static final int EXPERIENCE_POINTS = 30;
	private static final String IMAGE = "pictures/hornstriker.jpg";
	public Hornstriker() {
		super("Hornstriker", MAX_HEALTH, MAX_HEALTH, ITEM_LIMIT,MAP_LEVEL,IMAGE,EXPERIENCE_POINTS);
		this.setCurrentWeapon(new LongSword());
		this.generatedropps();
	}
}
