package se.lequest.lequest.maps;

import se.lequest.lequest.characters.BossLevel1;

/**
 * This is the first level1
 */
public class CatacombMap extends Map {
    private static final String CATACOMB_NAME = "Catacombs";
    private static final int MAP_LEVEL = 1;

    public CatacombMap() {
        super(CATACOMB_NAME, MAP_LEVEL);
        //Create the startsegment
        this.setStartSegment(new Segment("E", new Position(0, 0), true));
        //Create a segment more..				x,y
        this.addSegment(new Segment("VES", new Position(1, 0), false));
        this.addSegment(new Segment("V", new Position(2, 0), false));
        this.addSegment(new Segment("NS", new Position(1, 1), false));
        this.addSegment(new Segment("NE", new Position(1, 2), false));
        this.addSegment(new Segment("VE", new Position(2, 2), false));
        this.addSegment(new Segment("VE", new Position(3, 2), false));
        this.addSegment(new Segment("VES", new Position(4, 2), false));
        this.addSegment(new Segment("V", new Position(5, 2), false));
        this.addSegment(new Segment("S", new Position(7, 2), false));
        this.addSegment(new Segment("S", new Position(9, 2), false));
        this.addSegment(new Segment("NS", new Position(4, 3), false));
        this.addSegment(new Segment("ES", new Position(6, 3), false));
        this.addSegment(new Segment("VESN", new Position(7, 3), false));
        this.addSegment(new Segment("VE", new Position(8, 3), false));
        this.addSegment(new Segment("NEV", new Position(9, 3), false));
        this.addSegment(new Segment("V", new Position(10, 3), false));
        this.addSegment(new Segment("ES", new Position(1, 4), false));
        this.addSegment(new Segment("VES", new Position(2, 4), false));
        this.addSegment(new Segment("VE", new Position(3, 4), false));
        this.addSegment(new Segment("VEN", new Position(4, 4), false));
        this.addSegment(new Segment("VE", new Position(5, 4), false));
        this.addSegment(new Segment("VESN", new Position(6, 4), false));
        this.addSegment(new Segment("VN", new Position(7, 4), false));
        this.addSegment(new Segment("N", new Position(1, 5), false));
        this.addSegment(new Segment("NS", new Position(2, 5), false));
        this.addSegment(new Segment("N", new Position(6, 5), false));
        this.addSegment(new Segment("ES", new Position(8, 5), false));
        this.addSegment(new Segment("VE", new Position(9, 5), false));
        this.addSegment(new Segment("VS", new Position(10, 5), false));
        this.addSegment(new Segment("NES", new Position(2, 6), false));
        this.addSegment(new Segment("VE", new Position(3, 6), false));
        this.addSegment(new Segment("VE", new Position(4, 6), false));
        this.addSegment(new Segment("VS", new Position(5, 6), false));
        this.addSegment(new Segment("NS", new Position(8, 6), false));
        this.addSegment(new Segment("N", new Position(10, 6), false));
        this.addSegment(new Segment("E", new Position(1, 7), false));
        this.addSegment(new Segment("VNS", new Position(2, 7), false));
        this.addSegment(new Segment("E", new Position(4, 7), false));
        this.addSegment(new Segment("VEN", new Position(5, 7), false));
        this.addSegment(new Segment("VES", new Position(6, 7), false));
        this.addSegment(new Segment("VE", new Position(7, 7), false));
        this.addSegment(new Segment("NV", new Position(8, 7), false));
        this.addSegment(new Segment("NS", new Position(6, 8), false));
        this.addSegment(new Segment("NE", new Position(6, 9), false));
        this.addSegment(new Segment("V", new Position(7, 9), false));
        this.addSegment(new Segment("NS", new Position(2, 8), false));
        this.addSegment(new Segment("NVE", new Position(2, 9), false));
        this.addSegment(new Segment("VN", new Position(3, 9), false));
        this.addSegment(new Segment("NS", new Position(3, 8), false));
        this.addSegment(new Segment("S", new Position(3, 7), false));
        this.addSegment(new Segment("ES", new Position(1, 9), false));
        this.addSegment(new Segment("NSE", new Position(1, 10), false));
        this.addSegment(new Segment("VE", new Position(2, 10), false));
        this.addSegment(new Segment("VE", new Position(3, 10), false));
        this.addSegment(new Segment("VS", new Position(4, 10), false));
        this.addSegment(new Segment("NVE", new Position(4, 11), false));
        this.addSegment(new Segment("E", new Position(3, 11), false));
        this.addSegment(new Segment("V", new Position(5, 11), false));
        this.addSegment(new Segment("NE", new Position(1, 11), false));
        this.addSegment(new Segment("VS", new Position(2, 11), false));
        this.addSegment(new Segment("NS", new Position(2, 12), false));
        this.addSegment(new Segment("NE", new Position(2, 13), false));
        this.addSegment(new Segment("VS", new Position(3, 13), false));
        Segment endsegment = new Segment("NS", new Position(3, 14), false, new BossLevel1());
        endsegment.setIsBoss(true);
        this.addSegment(endsegment);

        Segment endmapsegment = new Segment("N", new Position(3, 15), false);
        endmapsegment.setIsEnd(true);
        this.addSegment(endmapsegment);

        //this.generateMultipalEnemys(10 + new java.util.Random(System.currentTimeMillis()).nextInt(50)); //tester..
        this.generateEnemys();
    }

}
