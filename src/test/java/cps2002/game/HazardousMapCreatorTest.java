package cps2002.game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HazardousMapCreatorTest {

    private HazardousMapCreator hazardousMapCreator;

    @Before
    public void setup(){
        hazardousMapCreator = new HazardousMapCreator();
    }

    // test create the map
    @Test
    public void testCreator(){
        Map hazardousMap = hazardousMapCreator.creator(3);
        Map expectedMap = HazardousMap.getInstance(3);
        assertEquals(expectedMap, hazardousMap);
    }

    @After
    public void teardown(){
        hazardousMapCreator = null;
    }
}


