package se.lequest.lequest.items;

import se.lequest.lequest.characters.Player;

public class LightHealthPotion extends UsableItem {
    private static final String NAME = "light health potion";
    private static final int SELL_VALUE = 10;
    private static final String PICTURE = "pictures/potion.jpg";
    private static final int LEVEL = 1;
    private static final int HEALTH_GAIN_VALUE = 10;

    public LightHealthPotion() {
        super(NAME, SELL_VALUE, PICTURE, LEVEL);
    }

    @Override
    /**
     * Increase the players health with the health gain value
     */
    public void use(Player player) {
        player.addHealth(HEALTH_GAIN_VALUE);
    }

    public String getDescription() {
        return super.getDescription() +
                "\n\nUse this item to\n" +
                "get " + HEALTH_GAIN_VALUE +
                " more HP.";
    }
}
