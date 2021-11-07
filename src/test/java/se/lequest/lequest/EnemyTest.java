package se.lequest.lequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lequest.lequest.characters.Skeleton;
import se.lequest.lequest.items.Bone;

import static org.fest.assertions.Assertions.assertThat;

class EnemyTest {
    private Skeleton skeleton;

    @BeforeEach
    void setUp() throws Exception {
        skeleton = new Skeleton();
    }

    @Test
    void skeletonItemCollection() {
        skeleton.getItems().clear();
        skeleton.addItem(new Bone());
        assertThat(skeleton.getNumberOfItems()).isEqualTo(1);
        skeleton.addItem(new Bone());
        assertThat(skeleton.getNumberOfItems()).isEqualTo(2);
        skeleton.addItem(new Bone());
        assertThat(skeleton.getNumberOfItems()).isEqualTo(2);

    }

    @Test
    void skeleton() {
        boolean gotmaxnumberofitems = false;
        for (int i = 0; i < 10000; i++) {
            skeleton = new Skeleton();
            if (skeleton.getMaxnrofItems() == skeleton.getNumberOfItems()) {
                gotmaxnumberofitems = true;
            }
        }
        assertThat(gotmaxnumberofitems).isTrue();
    }

}
