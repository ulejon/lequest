package se.lequest.lequest.highscore;

import se.lequest.lequest.characters.Player;

/**
 * This class is a highscore representation of a player
 * Containing playername, amount of gold and experience level
 */
public class HighscoreItem implements java.io.Serializable {
    private String playername;
    private int gold;
    private int rank = -1;
    private int experience;

    /**
     * Creates a new highscore item
     *
     * @param player current player
     */
    public HighscoreItem(Player player) {
        this.playername = player.getName();
        this.gold = player.getCoins();
        this.experience = player.getExperience();
    }

    /**
     * Returns the playername
     *
     * @return name
     */
    public String getPlayerName() {
        return this.playername;
    }

    /**
     * Returns the amount of gold
     *
     * @return gold amount
     */
    public int getGoldAmount() {
        return this.gold;
    }

    /**
     * Sets the new rank of the player
     *
     * @param rank
     */
    public void setRank(int rank) {
        this.rank = rank;
    }

    /**
     * Returns the experience
     *
     * @return
     */
    public int getExperience() {
        return this.experience;
    }

    /**
     * Returns the rank
     *
     * @return
     */
    public int getRank() {
        return this.rank;
    }

    /**
     * Update the amount of gold and experience of
     * the highscore item
     *
     * @param score
     */
    public void sync(HighscoreItem score) {
        if (score.gold > this.gold) {
            this.gold = score.gold;
        }
        if (score.experience > this.experience) {
            this.experience = score.experience;
        }
    }

    /**
     * Checks if this item's experience/gold is higher than the other's
     *
     * @param other
     * @return
     */
    public boolean isHighestScored(HighscoreItem other) {
        if (this.experience > other.experience) {
            return true;
        }
        if (this.experience == other.experience) {
            if (this.gold > other.gold) {
                return true;
            }
        }

        return false;
    }
}
