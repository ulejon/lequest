package se.lequest.lequest.constants;

import java.awt.Font;
import java.awt.event.KeyEvent;

/**
 * This Class takes care of Global constans of the game..
 * Usefull for quickly changeing how the game works..
 * Version nr Height & Width etc.
 */
public final class GameConstants {

    //	Add Game constans here..
    public static final String VERSION = "0.9";
    public final static String NAME = "Le Quest";

    public final static int WINDOW_STARUP_WIDTH = 800;
    public final static int WINDOW_STARUP_HEIGHT = 600;

    //the Mapviewer..
    public final static int MAPVIEWER_NR_CELLS_WIDTH = 20;//nr of = x^2
    public final static int MAP_IMAGE_CELL_WIDTH = 30;//pixels


    public static final int MAX_NR_OF_ITEMS_ON_PLAYER = 10;
    public static final int MAX_PLAYER_HEALTH = 100;
    public static final int MIN_PLAYER_HEALTH = 0;

    public static final double SELL_VALUE_MULTIPLE = 0.7; //70% when you sell
    // User Input
    public static final int KEY_MOVE_NORTH = KeyEvent.VK_UP;
    public static final int KEY_MOVE_SOUTH = KeyEvent.VK_DOWN;
    public static final int KEY_MOVE_EAST = KeyEvent.VK_RIGHT;
    public static final int KEY_MOVE_WEST = KeyEvent.VK_LEFT;

    //Fonts
    public static final Font DEFAULT_FONT = new Font("Verdana", Font.PLAIN, 10);
    public static final Font BOLD_FONT = new Font("Verdana", Font.BOLD, 10);
    public static final Font ITALIC_FONT = new Font("Verdana", Font.ITALIC, 10);

    //Player change constants
    public static final int PLAYER_CHANGE_POSITION = 1;
    public static final int PLAYER_CHANGE_MAP = 2;
    public static final int PLAYER_CHANGE_CURRENTWEAPON = 3;
    public static final int PLAYER_CHANGE_INVENTORY = 4;
    public static final int PLAYER_CHANGE_MONEY = 5;
    public static final int PLAYER_CHANGE_HP = 6;
    public static final int PLAYER_CHANGE_ARMOR = 7;
    public static final int ENEMY_CHANGE_HP = 8;
    public static final int PLAYER_GAINS_EXPERIENCE = 9;
    public static final int PLAYER_LEVELS_UP = 10;

    //Attack constants
    public static final int ENEMY_HIT_HEAD = 0;
    public static final int ENEMY_HIT_CHEST = 1;
    public static final int ENEMY_HIT_HAND = 2;
    public static final int ENEMY_HIT_FEET = 3;
    public static final int ENEMY_HIT_SHIELD = 4;

    public static final int PLAYER_MANAGES_TO_RUN = 2; // 1 / 2 chance the player can run from a enemy..
    //Map /Segment
    public static final int SEGMENT_ITEMS_CHANGE = 11; // When Itemslist in Segment is Changed
    public static final int SEGMENT_ENEMYS_CHANGE = 12; // when Enemylist in Segment is Changed.

    //Market
    public static final int KEY_TO_GET_ALL = 10000;
}
