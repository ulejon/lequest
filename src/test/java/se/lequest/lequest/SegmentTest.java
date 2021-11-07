package se.lequest.lequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lequest.lequest.maps.Position;
import se.lequest.lequest.maps.Segment;

import static org.fest.assertions.Assertions.assertThat;

class SegmentTest {
    private Segment testobject;

    @BeforeEach
    void setUp() throws Exception {
        testobject = new Segment(Segment.NONE_OPEN, new Position());
    }

    @Test
    void east() {
        assertThat(testobject.setDoors(Segment.EAST_DOOR_OPEN)).isEqualTo(1);
        assertThat(testobject.isEastDoorOpen()).isTrue();
        assertThat(testobject.isNorthDoorOpen()).isFalse();
        assertThat(testobject.isSouthDoorOpen()).isFalse();
        assertThat(testobject.isWestDoorOpen()).isFalse();
    }

    @Test
    void west() {
        assertThat(testobject.setDoors(Segment.WEST_DOOR_OPEN)).isEqualTo(1);
        assertThat(testobject.isEastDoorOpen()).isFalse();
        assertThat(testobject.isNorthDoorOpen()).isFalse();
        assertThat(testobject.isSouthDoorOpen()).isFalse();
        assertThat(testobject.isWestDoorOpen()).isTrue();
    }

    @Test
    void south() {
        assertThat(testobject.setDoors(Segment.SOUTH_DOOR_OPEN)).isEqualTo(1);
        assertThat(testobject.isEastDoorOpen()).isFalse();
        assertThat(testobject.isNorthDoorOpen()).isFalse();
        assertThat(testobject.isSouthDoorOpen()).isTrue();
        assertThat(testobject.isWestDoorOpen()).isFalse();
    }

    @Test
    void north() {
        assertThat(testobject.setDoors(Segment.NORTH_DOOR_OPEN)).isEqualTo(1);
        assertThat(testobject.isEastDoorOpen()).isFalse();
        assertThat(testobject.isNorthDoorOpen()).isTrue();
        assertThat(testobject.isSouthDoorOpen()).isFalse();
        assertThat(testobject.isWestDoorOpen()).isFalse();
    }

    @Test
    void northAndSouth() {
        assertThat(testobject.setDoors(Segment.SOUTH_DOOR_OPEN + Segment.NORTH_DOOR_OPEN)).isEqualTo(1);
        assertThat(testobject.isEastDoorOpen()).isFalse();
        assertThat(testobject.isNorthDoorOpen()).isTrue();
        assertThat(testobject.isSouthDoorOpen()).isTrue();
        assertThat(testobject.isWestDoorOpen()).isFalse();
    }

    @Test
    void eastAndVest() {
        assertThat(testobject.setDoors(Segment.WEST_DOOR_OPEN + Segment.EAST_DOOR_OPEN)).isEqualTo(1);
        assertThat(testobject.isEastDoorOpen()).isTrue();
        assertThat(testobject.isNorthDoorOpen()).isFalse();
        assertThat(testobject.isSouthDoorOpen()).isFalse();
        assertThat(testobject.isWestDoorOpen()).isTrue();
    }

    @Test
    void allDoorsOpen() {
        assertThat(testobject.setDoors(Segment.SOUTH_DOOR_OPEN + Segment.NORTH_DOOR_OPEN
                + Segment.EAST_DOOR_OPEN + Segment.WEST_DOOR_OPEN)).isEqualTo(1);
        assertThat(testobject.isEastDoorOpen()).isTrue();
        assertThat(testobject.isNorthDoorOpen()).isTrue();
        assertThat(testobject.isSouthDoorOpen()).isTrue();
        assertThat(testobject.isWestDoorOpen()).isTrue();

    }

    @Test
    void noDoorsOpen() {
        assertThat(testobject.setDoors(Segment.NONE_OPEN)).isEqualTo(1);
        assertThat(testobject.isEastDoorOpen()).isFalse();
        assertThat(testobject.isNorthDoorOpen()).isFalse();
        assertThat(testobject.isSouthDoorOpen()).isFalse();
        assertThat(testobject.isWestDoorOpen()).isFalse();

    }

    @Test
    void stringDoor() {
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
