package test_model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestGameConfig.class, TestMapEditor.class, 
				TestPlayer.class, TestTerritory.class, TestValidMaps.class})


public class AllTests {

}
