package test_model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestGameConfig.class, TestMapEditor.class, TestGeneric.class,
				TestPlayer.class, TestTerritory.class, TestValidMaps.class,
				TestAggressive.class, TestBenevolent.class, TestCheater.class, TestRandom.class,
				TestController.class})


public class AllTests {

}
