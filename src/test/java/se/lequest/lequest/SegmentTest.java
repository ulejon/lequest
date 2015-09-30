package se.lequest.lequest;

import junit.framework.TestCase;
import se.lequest.lequest.maps.Position;
import se.lequest.lequest.maps.Segment;

import static org.fest.assertions.Assertions.assertThat;

public class SegmentTest extends TestCase {
    private Segment testobject;

    protected void setUp() throws Exception {
        super.setUp();
        testobject = new Segment(Segment.NONE_OPEN, new Position());
    }

    public void testEast() {
        assertThat(testobject.setDoors(Segment.EAST_DOOR_OPEN)).isEqualTo(1);
        assertThat(testobject.isEastDoorOpen()).isTrue();
        assertThat(testobject.isNorthDoorOpen()).isFalse();
        assertThat(testobject.isSouthDoorOpen()).isFalse();
        assertThat(testobject.isWestDoorOpen()).isFalse();
    }

    public void testWest() {
        assertThat(testobject.setDoors(Segment.WEST_DOOR_OPEN)).isEqualTo(1);
        assertThat(testobject.isEastDoorOpen()).isFalse();
        assertThat(testobject.isNorthDoorOpen()).isFalse();
        assertThat(testobject.isSouthDoorOpen()).isFalse();
        assertThat(testobject.isWestDoorOpen()).isTrue();
    }

    public void testSouth() {
        assertThat(testobject.setDoors(Segment.SOUTH_DOOR_OPEN)).isEqualTo(1);
        assertThat(testobject.isEastDoorOpen()).isFalse();
        assertThat(testobject.isNorthDoorOpen()).isFalse();
        assertThat(testobject.isSouthDoorOpen()).isTrue();
        assertThat(testobject.isWestDoorOpen()).isFalse();
    }

    public void testNorth() {
        assertThat(testobject.setDoors(Segment.NORTH_DOOR_OPEN)).isEqualTo(1);
        assertThat(testobject.isEastDoorOpen()).isFalse();
        assertThat(testobject.isNorthDoorOpen()).isTrue();
        assertThat(testobject.isSouthDoorOpen()).isFalse();
        assertThat(testobject.isWestDoorOpen()).isFalse();
    }

    public void testNorthAndSouth() {
        assertThat(testobject.setDoors(Segment.SOUTH_DOOR_OPEN + Segment.NORTH_DOOR_OPEN)).isEqualTo(1);
        assertThat(testobject.isEastDoorOpen()).isFalse();
        assertThat(testobject.isNorthDoorOpen()).isTrue();
        assertThat(testobject.isSouthDoorOpen()).isTrue();
        assertThat(testobject.isWestDoorOpen()).isFalse();
    }

    public void testEastAndVest() {
        assertThat(testobject.setDoors(Segment.WEST_DOOR_OPEN + Segment.EAST_DOOR_OPEN)).isEqualTo(1);
        assertThat(testobject.isEastDoorOpen()).isTrue();
        assertThat(testobject.isNorthDoorOpen()).isFalse();
        assertThat(testobject.isSouthDoorOpen()).isFalse();
        assertThat(testobject.isWestDoorOpen()).isTrue();
    }

    public void AllDoorsOpen() {
        assertThat(testobject.setDoors(Segment.SOUTH_DOOR_OPEN + Segment.NORTH_DOOR_OPEN
                + Segment.EAST_DOOR_OPEN + Segment.WEST_DOOR_OPEN)).isEqualTo(1);
        assertThat(testobject.isEastDoorOpen()).isTrue();
        assertThat(testobject.isNorthDoorOpen()).isTrue();
        assertThat(testobject.isSouthDoorOpen()).isTrue();
        assertThat(testobject.isWestDoorOpen()).isTrue();

    }

    public void NoDoorsOpen() {
        assertThat(testobject.setDoors(Segment.NONE_OPEN)).isEqualTo(1);
        assertThat(testobject.isEastDoorOpen()).isFalse();
        assertThat(testobject.isNorthDoorOpen()).isFalse();
        assertThat(testobject.isSouthDoorOpen()).isFalse();
        assertThat(testobject.isWestDoorOpen()).isFalse();

    }

    public void testStringDoor() {
        assertThat(testobject.setDoors("")).isEqualTo(1);
        assertThat(testobject.isEastDoorOpen()).isFalse();
        assertThat(testobject.isNorthDoorOpen()).isFalse();
        assertThat(testobject.isSouthDoorOpen()).isFalse();
        assertThat(testobject.isWestDoorOpen()).isFalse();


        assertThat(testobject.setDoors(Segment.NONE_OPEN)).isEqualTo(1);
        assertThat(testobject.setDoors("E")).isEqualTo(1);
        assertThat(testobject.isEastDoorOpen()).isTrue();
        assertThat(testobject.isNorthDoorOpen()).isFalse();
        assertThat(testobject.isSouthDoorOpen()).isFalse();
        assertThat(testobject.isWestDoorOpen()).isFalse();

        assertThat(testobject.setDoors("V")).isEqualTo(1);
        assertThat(testobject.isEastDoorOpen()).isFalse();
        assertThat(testobject.isNorthDoorOpen()).isFalse();
        assertThat(testobject.isSouthDoorOpen()).isFalse();
        assertThat(testobject.isWestDoorOpen()).isTrue();

        assertThat(testobject.setDoors("S")).isEqualTo(1);
        assertThat(testobject.isEastDoorOpen()).isFalse();
        assertThat(testobject.isNorthDoorOpen()).isFalse();
        assertThat(testobject.isSouthDoorOpen()).isTrue();
        assertThat(testobject.isWestDoorOpen()).isFalse();

        assertThat(testobject.setDoors("n")).isEqualTo(1);
        assertThat(testobject.isEastDoorOpen()).isFalse();
        assertThat(testobject.isNorthDoorOpen()).isTrue();
        assertThat(testobject.isSouthDoorOpen()).isFalse();
        assertThat(testobject.isWestDoorOpen()).isFalse();

        assertThat(testobject.setDoors("ne")).isEqualTo(1);
        assertThat(testobject.isEastDoorOpen()).isTrue();
        assertThat(testobject.isNorthDoorOpen()).isTrue();
        assertThat(testobject.isSouthDoorOpen()).isFalse();
        assertThat(testobject.isWestDoorOpen()).isFalse();

        assertThat(testobject.setDoors("sn")).isEqualTo(1);
        assertThat(testobject.isEastDoorOpen()).isFalse();
        assertThat(testobject.isNorthDoorOpen()).isTrue();
        assertThat(testobject.isSouthDoorOpen()).isTrue();
        assertThat(testobject.isWestDoorOpen()).isFalse();
    }

}
