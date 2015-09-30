package se.lequest.lequest.characters;
import se.lequest.lequest.items.Bone;

public class Skeleton extends Enemy {
	private static final int MAX_HEALTH = 20; 
	private static final int ITEM_LIMIT = 2;
	private static final int MAP_LEVEL = 1;
	private static final int EXPERIENCE_POINTS = 11;
	private static final String IMAGE = "pictures/skeleton.gif";
	public Skeleton() {
		super("Skeleton", MAX_HEALTH, MAX_HEALTH, ITEM_LIMIT,MAP_LEVEL,IMAGE, EXPERIENCE_POINTS);
		this.setCurrentWeapon(new Bone());
		this.generatedropps();
	}
}
