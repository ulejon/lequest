package regressionTestPack;

import characters.Skeleton;
import items.Bone;
import junit.framework.TestCase;

import static org.fest.assertions.Assertions.assertThat;

public class EnemyTest extends TestCase {
    private Skeleton skeleton;

    protected void setUp() throws Exception {
        super.setUp();
        skeleton = new Skeleton();
    }

    public void testSkeletonItemCollection() {
        skeleton.getItems().clear();
        skeleton.addItem(new Bone());
        assertThat(skeleton.getNumberOfItems()).isEqualTo(1);
        skeleton.addItem(new Bone());
        assertThat(skeleton.getNumberOfItems()).isEqualTo(2);
        skeleton.addItem(new Bone());
        assertThat(skeleton.getNumberOfItems()).isEqualTo(2);

    }

    public void testSkeleton() {
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
