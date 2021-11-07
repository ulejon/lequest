package se.lequest.lequest.maps;
/**
 * This Class represents a map all of the rooms
 */

import se.lequest.lequest.characters.EnemyCollection;

import java.util.HashMap;
import java.util.Random;

public abstract class Map implements java.io.Serializable {
    protected HashMap<String, Segment> themap;
    private Position startpos;
    private String mapName;
    private EnemyCollection enemycollection;
    protected int level;
    private Random randgen;

    /**
     * Create a empty map object
     *
     * @param mapname map name
     * @param level   the level of the map
     */
    public Map(String mapname, int level) {
        this.mapName = mapname;
        this.themap = new HashMap<String, Segment>();
        this.enemycollection = EnemyCollection.getInstance();
        this.level = level;
        randgen = new Random(System.currentTimeMillis());
    }

    /**
     * Returns the name of the map
     *
     * @return name
     */
    public String getName() {
        return this.mapName;
    }

    /**
     * Returns a segment (room) at a sertain position
     * null if it dont exist
     */
    public Segment getSegment(Position pos) {
		/*for(Segment tempseg : themap){
			if(tempseg.getPosition().equals(pos)){
				return tempseg;
			}
		}*/
        if (themap.containsKey(pos.toString())) {
            return themap.get(pos.toString());
        }
        return null;
    }

    /**
     * Returns the position of the Segment the player starts at
     *
     * @return
     */
    public Position getStartPos() {
        return this.startpos;
    }

    /**
     * Set the start segment(room) of the map
     *
     * @param seg
     */
    public void setStartSegment(Segment seg) {
        seg.setStart(true);
        themap.put(seg.getPosition().toString(), seg);
        this.startpos = seg.getPosition();
    }

    /**
     * Add a segment (room) to the map
     *
     * @param seg
     */
    public void addSegment(Segment seg) {
        themap.put(seg.getPosition().toString(), seg);
    }

    /**
     * This function generates random enemys to random rooms..
     * it does not add enemys to the start or end segment..
     */
    public void generateEnemys() {
        java.util.Collection<Segment> segmentcollection = themap.values();
        for (Segment seg : segmentcollection) {
            if (!seg.isEnd()) { //the endsegment contains the boss.. dont delete it!
                if (!seg.getIsBoss()) {
                    seg.clearEnemys();
                }
            }
            if (randgen.nextInt(3) == 1) { //1 in 3 rooms contains a enemy..
                if (!seg.istart()) {//dont add enemy to startroom..
                    if (!seg.isEnd()) {//dont add enemy to end room..
                        if (!seg.getIsBoss()) {
                            seg.addEnemy(enemycollection.getRandomEnemy(this.level));
                        }
                    }
                }
            }
        }
    }
}
