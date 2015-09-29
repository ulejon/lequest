package characters;
import items.ShortSword;

public class CavernLurker extends Enemy {
	private static final int MAX_HEALTH = 53; 
	private static final int ITEM_LIMIT = 3;
	private static final int MAP_LEVEL = 1;
	private static final int EXPERIENCE_POINTS = 27;
	private static final String IMAGE = "pictures/cavernlurker.gif";
	public CavernLurker() {
		super("Cavern Lurker", MAX_HEALTH, MAX_HEALTH, ITEM_LIMIT,MAP_LEVEL,IMAGE,EXPERIENCE_POINTS);
		this.setCurrentWeapon(new ShortSword());
		this.generatedropps();
	}
}
