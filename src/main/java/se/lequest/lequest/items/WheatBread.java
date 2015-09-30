package se.lequest.lequest.items;

import se.lequest.lequest.characters.Player;

public class WheatBread extends UsableItem {
	private static final String NAME = "wheat bread";
	private static final int SELL_VALUE = 3;
	private static final String PICTURE = "pictures/wheatbread.jpg";
	private static final int LEVEL = 1;
	private static final int HEALTH_GAIN_VALUE = 1;
	public WheatBread() {
		super(NAME, SELL_VALUE, PICTURE,LEVEL);
	}
	@Override
	/**
	 * Increase the players health with the health gain value
	 */
	public void use(Player player) {
		player.addHealth(HEALTH_GAIN_VALUE);
	}
	public String getDescription(){
		return super.getDescription()+ 
						"\n\nUse this item to\n" +
						"get " +HEALTH_GAIN_VALUE+
						" more HP.";
	}
}
