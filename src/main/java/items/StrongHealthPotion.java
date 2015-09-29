package items;

import characters.Player;

public class StrongHealthPotion extends UsableItem {
	private static final String NAME = "strong health potion";
	private static final int SELL_VALUE = 70;
	private static final String PICTURE = "pictures/strongpotion.jpg";
	private static final int LEVEL = 1;
	public StrongHealthPotion() {
		super(NAME, SELL_VALUE, PICTURE,LEVEL);
	}
	@Override
	/**
	 * Increase the players health to max
	 */
	public void use(Player player) {
		player.addHealth(player.getMaxHealth());
	}
	
	public String getDescription(){
		
		return super.getDescription()+ 
						"\n\nUse this item to\n" +
						"get full HP.";
	}
}
