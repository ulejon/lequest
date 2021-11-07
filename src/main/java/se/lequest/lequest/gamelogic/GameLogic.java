package se.lequest.lequest.gamelogic;

import se.lequest.lequest.characters.Enemy;
import se.lequest.lequest.characters.Player;
import se.lequest.lequest.constants.GameConstants;
import se.lequest.lequest.gui.FightFrame;
import se.lequest.lequest.gui.MapFrame;
import se.lequest.lequest.highscore.Highscore;
import se.lequest.lequest.maps.Position;
import se.lequest.lequest.maps.Segment;

import javax.swing.JOptionPane;
import java.util.Observable;
import java.util.Random;

/**
 * This Class handels the gamelogic of the game.. fights and movment of the player & Enemys
 */
public class GameLogic extends Observable {
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;
    public static GameLogic instance;
    private MapFrame mainframe;
    private final Random randgen;

    private GameLogic() {
        randgen = new Random(System.currentTimeMillis());
    }

    private GameLogic(MapFrame mainframe) {
        randgen = new Random(System.currentTimeMillis());
        this.mainframe = mainframe;
    }

    /**
     * Move the player in a direction.. returns true on success.. else false.
     *
     * @param player
     * @param direction se constans in this class for directions..
     * @return
     */
    public boolean move(Player player, int direction) {
        if (this.isDoorOpen(player, direction)) {
            //the door is open..
            //check if the segemt we are moving to exsists
            Position newplayerpos = this.calculateNextPosition(player, direction);
            Segment newsegment = player.getCurrentMap().getSegment(newplayerpos);
            if (newsegment == null) {
                return false;
            }
            player.getCurrentMap().getSegment(newplayerpos).setVisible(true);
            player.setCurrentPosition(newplayerpos);
            checkifStartFrame(player);
            this.checkIfEnemysInRoom(player);
            this.checkIfItemsInRoom(player);
            checkifendframe(player);
            return true;

        }
        return false;
    }

    private boolean isDoorOpen(Player player, int direction) {
        Segment CurrentSegment = player.getCurrentMap().getSegment(player.getCurrentPosition());

        switch (direction) {
            case NORTH:
                return CurrentSegment.isNorthDoorOpen();
            case EAST:
                return CurrentSegment.isEastDoorOpen();
            case SOUTH:
                return CurrentSegment.isSouthDoorOpen();
            case WEST:
                return CurrentSegment.isWestDoorOpen();
            default:
                return false; // This should never happen.
        }
    }

    private Position calculateNextPosition(Player player, int direction) {
        int oldx = player.getCurrentPosition().getX();
        int oldy = player.getCurrentPosition().getY();
        int newx = oldx;
        int newy = oldy;

        switch (direction) {
            case NORTH:
                newy--;
                break;
            case EAST:
                newx++;
                break;
            case SOUTH:
                newy++;
                break;
            case WEST:
                newx--;
                break;
        }

        return new Position(newx, newy);
    }

    /**
     * Returns the instance of the Gamelogic..
     *
     * @param mainframe the Map frame( game gui)
     * @return
     */
    public static GameLogic getInstance(MapFrame mainframe) {
        if (instance == null) {
            instance = new GameLogic(mainframe);
        }
        return instance;
    }

    /**
     * Returns the instace of the Game logic
     *
     * @return
     */
    public static GameLogic getInstance() {
        if (instance == null) {
            instance = new GameLogic();
        }
        return instance;
    }

    private void checkIfEnemysInRoom(Player player) {
        int enemycount = player.getCurrentMap().getSegment(player.getCurrentPosition()).getEnemyCount();
        if (enemycount != 0) {
            FightFrame fightframe = new FightFrame(mainframe, player);//start upp fighting frame..
            setChanged();
            notifyObservers();
        }
    }

    private void checkIfItemsInRoom(Player player) {

        //int itemcount = player.getCurrentMap().getSegment(player.getCurrentPosition()).getItemCount();
        //String itemnames = "";
        //ArrayList<Item> itemlist = player.getCurrentMap().getSegment(player.getCurrentPosition()).GetItemList();
        //if(itemcount != 0){
        //for(int i=0; i<itemlist.size(); i++){
        //itemnames += itemlist.get(i).getName() + " ";
        //}
        //javax.swing.JOptionPane.showMessageDialog(null, "ItemTEST : There are " +itemnames+ "in this room.");

        //}
        setChanged();
        notifyObservers();
    }

    /**
     * Starts upp a fighting round.. player hits enemy , enemy hits player returns a string describing what happend.
     *
     * @param player
     * @param theEnemy
     * @return
     */
    public String startFightRound(Player player, Enemy theEnemy) {
        String result;
        int playergivesattack = player.getCurrentWeapon().attack();
        result = "You inflicted " + playergivesattack + " damage to the Enemy \n";
        theEnemy.decreaseHealth(playergivesattack);
        if (theEnemy.isAlive()) {
            result += EnemyHitsPlayer(player, theEnemy);

        }
        return result;
    }

    /**
     * The enemy hits the player.. returns a string describing what happend..
     *
     * @param player
     * @param theEnemy
     */
    public String EnemyHitsPlayer(Player player, Enemy theEnemy) {
        String result = "";
        int playerRandarmor = 0;
        String[] hits = {"head", "chest", "hand", "feet", "shield"};
        int enemyhit = randgen.nextInt(5);
        switch (enemyhit) {
            case GameConstants.ENEMY_HIT_HEAD:
                playerRandarmor = player.getActiveArmor().getRandomProtectionOfHelmetArmor();
                break;
            case GameConstants.ENEMY_HIT_CHEST:
                playerRandarmor = player.getActiveArmor().getRandomProtectionOfBodyArmor();
                break;
            case GameConstants.ENEMY_HIT_HAND:
                playerRandarmor = player.getActiveArmor().getRandomProtectionOfGlovesArmor();
                break;
            case GameConstants.ENEMY_HIT_FEET:
                playerRandarmor = player.getActiveArmor().getRandomProtectionOfBootsArmor();
                break;
            case GameConstants.ENEMY_HIT_SHIELD:
                playerRandarmor = player.getActiveArmor().getRandomProtectionOfShieldArmor();
                break;
        }
        int Enemygivesattac = theEnemy.getCurrentWeapon().attack();
        int EnemyAttacpoints = Enemygivesattac - playerRandarmor;
        result += theEnemy.getName() + " made " + Enemygivesattac + " damagepoints on " + hits[enemyhit] + ", your armor absorbed " + playerRandarmor + "\n";
        if (EnemyAttacpoints > 0) {
            player.decreaseHealth(Enemygivesattac);
            result += "Total Hp decreased : " + EnemyAttacpoints + "\n";
        } else {
            result += "You absorbed the hit.. 0 Hp decresed";
        }
        return result;
    }


    /**
     * Returns true if the player manages to run..
     *
     * @param player
     * @return
     */
    public boolean playerTryToRun(Player player) {
        int randint = randgen.nextInt(GameConstants.PLAYER_MANAGES_TO_RUN);
        if (randint == 0) {
            player.setCurrentPosition(player.getOldPos());
            return true;
        }
        return false;
    }

    /**
     * Brings the player to town.. updates the highscore..
     *
     * @param player
     */
    private void goToTown(Player player) {
        if (JOptionPane.showConfirmDialog(null, "Go to town?", "Do you want to go to the town?", JOptionPane.YES_NO_OPTION)
                == JOptionPane.OK_OPTION) {

            this.mainframe.setVisible(false);
            this.mainframe.getTownFrame().setVisible(true);
            Highscore highobj = Highscore.getInstance();
            highobj.insertPlayer(player);

        } else {
            this.mainframe.setVisible(true); //fixes a bug whith run to startpos..
        }
    }

    /**
     * True if player is in the startsegement..
     * bring a chooice up.. Go to town?
     *
     * @param player
     */
    public boolean checkifStartFrame(Player player) {
        if (player.getCurrentMap().getSegment(player.getCurrentPosition()).istart()) {
            goToTown(player);
            return true;
        }
        return false;
    }

    /**
     * Checks if the segment is the Ending of the map..
     * if it is.. then End the game or change levell..
     *
     * @param player
     */
    public void checkifendframe(Player player) {
        if (player.getCurrentMap().getSegment(player.getCurrentPosition()).isEnd() && (player.getCurrentMap().getSegment(player.getCurrentPosition()).getEnemyCount() == 0)) {
            JOptionPane.showMessageDialog(null, "You killed the big boss! .. Game Over!");
        }
    }
}

