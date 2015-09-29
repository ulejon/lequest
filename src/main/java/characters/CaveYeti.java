package characters;
import items.SpikedClub;;

public class CaveYeti extends Enemy {
	private static final int MAX_HEALTH = 70; 
	private static final int ITEM_LIMIT = 1;
	private static final int MAP_LEVEL = 1;
	private static final int EXPERIENCE_POINTS = 35;
	private static final String IMAGE = "pictures/caveyeti.jpg";
	public CaveYeti() {
		super("Cave Yeti", MAX_HEALTH, MAX_HEALTH, ITEM_LIMIT,MAP_LEVEL,IMAGE,EXPERIENCE_POINTS);
		this.setCurrentWeapon(new SpikedClub());
		this.generatedropps();
	}
}
