package test_model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestGameConfig.class, TestInvalidMaps.class, TestTerritory.class, TestValidMaps.class,
		TestEditMap.class })
public class AllTests {

}
