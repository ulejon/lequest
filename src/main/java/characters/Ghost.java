package characters;
import items.GhostWhip;

public class Ghost extends Enemy {
	private static final int MAX_HEALTH = 60; 
	private static final int ITEM_LIMIT = 4;
	private static final int MAP_LEVEL = 1;
	private static final int EXPERIENCE_POINTS = 60;
	private static final String IMAGE = "pictures/ghost.jpg";
	public Ghost() {
		super("Ghost", MAX_HEALTH, MAX_HEALTH, ITEM_LIMIT,MAP_LEVEL,IMAGE,EXPERIENCE_POINTS);
		this.setCurrentWeapon(new GhostWhip());
		this.generatedropps();
	}
}
