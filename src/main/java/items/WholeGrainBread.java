package items;

import characters.Player;

public class WholeGrainBread extends UsableItem {
	private static final String NAME = "whole grain bread";
	private static final int SELL_VALUE = 4;
	private static final String PICTURE = "pictures/wholegrain.jpg";
	private static final int LEVEL = 1;
	private static final int HEALTH_GAIN_VALUE = 2;
	public WholeGrainBread() {
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
