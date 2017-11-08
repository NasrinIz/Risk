package test_model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import src.model.Maps;

public class TestInvalidMaps {

	private String disconnectedMapName = null;
	private String inconsistentAdjacencyMapName = null;
	private Maps objMap = null; 
	
	TestInvalidMaps(String inDisconnectedMapName, String inInconsistentAdjacencyMapName)
	{
		this.disconnectedMapName = inDisconnectedMapName;
		this.inconsistentAdjacencyMapName = inInconsistentAdjacencyMapName;
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// do nothing
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// do nothing
	}

	@Before
	public void setUp() throws Exception {
		objMap = null;
	}

	@After
	public void tearDown() throws Exception {
		// do nothing
	}



}
