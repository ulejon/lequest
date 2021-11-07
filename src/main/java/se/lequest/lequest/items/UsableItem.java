package se.lequest.lequest.items;

import se.lequest.lequest.characters.Player;

/**
 * This class represents a item that can be used of a player..
 * like bread or potions or a similar..
 */
public abstract class UsableItem extends Item {
    /**
     * Creates a new UsableItem
     *
     * @param name  the name of the item
     * @param value the value of the item
     * @param image the image of the item
     * @param level the level of the item
     */
    public UsableItem(String name, int value, String image, int level) {
        super(name, value, image, level);

    }

    /**
     * Creates a new Useble item .. without a picture..
     *
     * @param name  the name of the item
     * @param value the value of the item
     * @param level the level of the item
     */
    public UsableItem(String name, int value, int level) {
        super(name, value, level);
    }

    /**
     * Returns the description of the item
     */
    public String getDescription() {
        String description = getName() + "\n" +
                "Item level: " + super.getItemLevel() + "\n";
        if (isSellable()) {
            return description + "Sell value: " + getSellValue() + " GC";
        } else {
            return description + getName() + " is not sellable.";
        }
    }

    /**
     * Use the item! on the player or by the player
     *
     * @param player
     */
    public abstract void use(Player player);
}
