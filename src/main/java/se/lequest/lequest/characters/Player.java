package se.lequest.lequest.characters;

import se.lequest.lequest.constants.GameConstants;
import se.lequest.lequest.items.Armor;
import se.lequest.lequest.items.Dagger;
import se.lequest.lequest.items.Fists;
import se.lequest.lequest.items.Item;
import se.lequest.lequest.items.Money;
import se.lequest.lequest.items.Weapon;
import se.lequest.lequest.maps.Map;
import se.lequest.lequest.maps.Position;

/**
 * The Player class represents the main character that a user creates.
 * Player is responsible for the current weapon and armor that is active,
 * aswell as other items the player collect during the game.
 * It also keeps track of the players last and current position.
 *
 * @author Ulrik, Magnus, Per, Eric, Jonas
 */
public class Player extends Character {

    private Map currentMap;
    private Position currentPos;
    private Position oldPos;
    private final ActivePlayerArmor activeArmor;
    private static final String IMAGE = "pictures/player.jpg";
    private int experience;
    private int level;
    private int coins;

    /**
     * Creates a Playerobject..
     *
     * @param name       The name of the player
     * @param health     Current health of the player
     * @param maxHealth  The maximum health of the player
     * @param currentMap The current Map of the player..
     */
    public Player(String name, int health, int maxHealth, Map currentMap) {
        super(name, health, maxHealth, GameConstants.MAX_NR_OF_ITEMS_ON_PLAYER, IMAGE);
        this.currentMap = currentMap;
        this.currentPos = currentMap.getStartPos();//set player default on Maps,startpos
        this.oldPos = currentPos; //set as default the same one..
        super.addItem(new Fists()); //set default weapon to fists
        super.addItem(new Dagger()); //remove later?
        super.setCurrentWeapon((Weapon) super.getItems().get(0)); //set to current weapon to first one
        this.activeArmor = new ActivePlayerArmor();
        this.experience = 0;
        this.level = 1;
        this.coins = 20; //temp
    }

    /**
     * Adds Experiece to the player. If the player reaches a new
     * level given the new experience added, player increases to
     * a new level.
     *
     * @param newExp       The experience points to add.
     * @param nextExpValue Limit for the next experience level.
     * @return returns true if the player levels up, else false.
     */
    public boolean addExperience(int newExp, int nextExpValue) {
        boolean levelup = false;
        if (experience + newExp >= nextExpValue) {
            addLevelUpToPlayer();
            levelup = true;
        }
        experience += newExp;
        setChanged();
        this.notifyObservers(GameConstants.PLAYER_GAINS_EXPERIENCE);
        return levelup;
    }

    /**
     * level up a player one step..
     */
    public void addLevelUpToPlayer() {
        level++;
        setChanged();
        this.notifyObservers(GameConstants.PLAYER_LEVELS_UP);
    }

    /**
     * Returns the Experience points of the player..
     *
     * @return The experience.
     */
    public int getExperience() {
        return experience;
    }

    /**
     * Returns the Experience level of the player.
     *
     * @return the current level.
     */
    public int getPlayerLevel() {
        return level;
    }

    /**
     * Adds an item to players backpack..
     * returns true on success else false
     */
    public boolean addItem(Item addItem) {
        boolean success;

        if (addItem instanceof Money) {
            this.coins += ((Money) addItem).getSellValue();
            this.setChanged();
            this.notifyObservers(GameConstants.PLAYER_CHANGE_MONEY);
            return true;
        } else {
            success = super.addItem(addItem);
        }

        if (success) {
            this.setChanged();
            this.notifyObservers(GameConstants.PLAYER_CHANGE_INVENTORY); //an item has been added.. notify observers..
        }
        return success;
    }

    /**
     * Removes an item from the Players backpack..
     *
     * @param removeItem
     */
    public void removeItem(Item removeItem) {
        super.removeItem(removeItem);
        this.setChanged();
        this.notifyObservers(GameConstants.PLAYER_CHANGE_INVENTORY);//Inventory Change.. notify observers..

    }

    /**
     * Sett the current Weapon in use..
     * deletes it from the items (backpack)
     */
    public void setCurrentWeapon(Weapon newWeapon) {
        super.setCurrentWeapon(newWeapon);
        this.setChanged();
        this.notifyObservers(GameConstants.PLAYER_CHANGE_CURRENTWEAPON); //Current Weapon has been changed.. notifyobservers.
    }

    /**
     * Returns the Current map
     *
     * @return
     */
    public Map getCurrentMap() {
        return this.currentMap;
    }

    /**
     * Set the currentMap also updates players currentpos to
     * the maps startposition...
     *
     * @param currentMap
     */
    public void setCurrentMap(Map currentMap) {
        this.currentMap = currentMap;
        this.currentPos = this.currentMap.getStartPos();
        this.oldPos = currentPos;
        this.setChanged();
        this.notifyObservers(GameConstants.PLAYER_CHANGE_MAP); //CurrentMap has been changed notify Observers.
    }

    /**
     * Returns the CurrentPosition of the player..
     *
     * @return
     */
    public Position getCurrentPosition() {
        return this.currentPos;
    }

    /**
     * Set a new Position of the player..
     *
     * @param newPos
     */
    public void setCurrentPosition(Position newPos) {
        this.oldPos = this.currentPos;
        this.currentPos = newPos;
        setChanged();
        notifyObservers(GameConstants.PLAYER_CHANGE_POSITION);
    }

    /**
     * Returns the players old position.-
     *
     * @return
     */
    public Position getOldPos() {
        return this.oldPos;
    }

    /**
     * Returns the founds of the player..
     *
     * @return
     */
    public int getCoins() {
        return coins;
    }

    /**
     * Add money to the player..
     *
     * @param coins
     */
    public void addCoins(int coins) {
        this.coins += coins;
        setChanged();
        notifyObservers(GameConstants.PLAYER_CHANGE_MONEY);
    }

    /**
     * Decrease the players money..
     *
     * @param coins
     */
    public void decreaseCoins(int coins) {
        if (this.coins >= coins) {
            this.coins -= coins;
            setChanged();
            notifyObservers(GameConstants.PLAYER_CHANGE_MONEY);
        }
    }

    /**
     * Setts the active health to somthing new..
     */
    public void setHealth(int newHealth) {
        super.setHealth(newHealth);
        setChanged();
        notifyObservers(GameConstants.PLAYER_CHANGE_HP);
    }

    /**
     * Decrese Player health..
     * if < 0 then 0
     */
    public void decreaseHealth(int points) {
        super.decreaseHealth(points);
        setChanged();
        notifyObservers(GameConstants.PLAYER_CHANGE_HP);
    }

    /**
     * Adds active Health to the player..
     * if more then Maximum health then maximum
     */
    public void addHealth(int pointstoadd) {
        super.addHealth(pointstoadd);
        setChanged();
        notifyObservers(GameConstants.PLAYER_CHANGE_HP);
    }

    /**
     * Addes a Armor to the Activeones.. removes it from the players backpack..
     */
    public void addArmor(Armor armor) {
        Armor oldarmor = this.activeArmor.addArmor(armor);
        if (this.getItems().contains(armor)) {
            this.removeItem(armor); //remove it from the bag..
        }
        if (oldarmor != null) {
            this.addItem(oldarmor); //add the oldactive armor back to bag..
        }
        setChanged();
        notifyObservers(GameConstants.PLAYER_CHANGE_ARMOR);
    }

    /**
     * Removes a activeArmor from the active ones.. puts it back in players backpack..
     *
     * @param armor
     */
    public void removeActiveArmor(Armor armor) {
        this.activeArmor.removeActiveArmor(armor);
        if (this.getItems().contains(armor)) {
            this.addItem(armor); //add the removed armor back to bag...
        }
        setChanged();
        notifyObservers(GameConstants.PLAYER_CHANGE_ARMOR);
    }

    /**
     * Returns the Active Armor object for the player... See ActivePlayerArmor.java
     *
     * @return
     */
    public ActivePlayerArmor getActiveArmor() {
        return this.activeArmor;
    }

    /**
     * This method can be used to update all the observers of the player..
     * can be usefull when for example loading a player
     */
    public void updateObservers() {
        setChanged();
        notifyObservers(GameConstants.PLAYER_CHANGE_ARMOR);
        setChanged();
        notifyObservers(GameConstants.PLAYER_CHANGE_POSITION);
        setChanged();
        notifyObservers(GameConstants.PLAYER_CHANGE_MAP);
        setChanged();
        notifyObservers(GameConstants.PLAYER_CHANGE_CURRENTWEAPON);
        setChanged();
        notifyObservers(GameConstants.PLAYER_CHANGE_INVENTORY);
        setChanged();
        notifyObservers(GameConstants.PLAYER_CHANGE_MONEY);
        setChanged();
        notifyObservers(GameConstants.PLAYER_CHANGE_HP);
    }
}
