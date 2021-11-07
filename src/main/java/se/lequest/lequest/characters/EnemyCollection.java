package se.lequest.lequest.characters;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This Class Takes care of all the enemys..
 * It can generate a random enemy or lists of enemys
 */
public class EnemyCollection implements java.io.Serializable {
    private final List<Enemy> enemyList;
    private static EnemyCollection enemyCollection;
    private final Random rand;
    private int Currentlevellist;

    /**
     * Returns a EnemyCollection object..
     * Singelton.. only one can exist
     *
     * @return
     */
    public static EnemyCollection getInstance() {
        if (EnemyCollection.enemyCollection == null) {
            EnemyCollection.enemyCollection = new EnemyCollection();
        }
        return EnemyCollection.enemyCollection;
    }

    private EnemyCollection() {
        this.enemyList = new ArrayList<Enemy>();
        rand = new Random(System.currentTimeMillis());
        this.Currentlevellist = 0;
    }

    /**
     * Function to get a complete Enemy collection
     *
     * @param level
     * @return the list of enemy
     */
    public List<Enemy> getEnemyCollection(int level) {
        fillEnemyList(level);
        return enemyList;
    }

    /**
     * Fills the enemy list with new enemys, depending on which
     * level the player is on
     */
    private void fillEnemyList(int level) {
		/*if(Currentlevellist == level){
			return;
		}
		/*/
        Currentlevellist = level;
        this.enemyList.clear();
        switch (level) {
            case 1:
                enemyList.add(new Skeleton());
                enemyList.add(new Zombie());
                enemyList.add(new Ghost());
                enemyList.add(new Gahroot());
                enemyList.add(new QuarrySlave());
                enemyList.add(new DarkShade());
                enemyList.add(new CaveYeti());
                enemyList.add(new CavernLurker());
                enemyList.add(new LeySprite());
                enemyList.add(new IceLord());

                break;
            case 2:
                enemyList.add(new Hornstriker());

                break;
        }

        //TODO: Add more Enemys here later on
    }

    /**
     * Picks a random enemy from the level collection of enemys
     *
     * @param level
     * @return the item
     */
    public Enemy getRandomEnemy(int level) {
        fillEnemyList(level);
        return enemyList.get(rand.nextInt(enemyList.size()));
    }
}
