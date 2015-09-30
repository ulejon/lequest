package se.lequest.lequest.characters;
import se.lequest.lequest.items.Club;

public class IceLord extends Enemy {
	private static final int MAX_HEALTH = 70; 
	private static final int ITEM_LIMIT = 4;
	private static final int MAP_LEVEL = 1;
	private static final int EXPERIENCE_POINTS = 33;
	private static final String IMAGE = "pictures/icelord.jpg";
	public IceLord() {
		super("Ice Lord", MAX_HEALTH, MAX_HEALTH, ITEM_LIMIT,MAP_LEVEL,IMAGE,EXPERIENCE_POINTS);
		this.setCurrentWeapon(new Club());
		this.generatedropps();
	}
}
