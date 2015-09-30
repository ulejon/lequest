package regressionTestPack;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Le Quest testsuit");
		
		//Add more tests here.. 
		suite.addTestSuite(SegmentTest.class);
		suite.addTestSuite(CharacterTest.class);
		suite.addTestSuite(WeaponTest.class);
		suite.addTestSuite(EnemyTest.class);

		return suite;
	}

}
