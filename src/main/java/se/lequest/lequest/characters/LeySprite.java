package se.lequest.lequest.characters;
import se.lequest.lequest.items.Dagger;;

public class LeySprite extends Enemy {
	private static final int MAX_HEALTH = 34; 
	private static final int ITEM_LIMIT = 2;
	private static final int MAP_LEVEL = 1;
	private static final int EXPERIENCE_POINTS = 14;
	private static final String IMAGE = "pictures/leysprite.jpg";
	public LeySprite() {
		super("Ley Sprite", MAX_HEALTH, MAX_HEALTH, ITEM_LIMIT,MAP_LEVEL,IMAGE, EXPERIENCE_POINTS);
		this.setCurrentWeapon(new Dagger());
		this.generatedropps();
	}
}
