package cps2002.game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/* this class tests the functionality of the SafeMapCreator class */

public class SafeMapCreatorTest {

    private SafeMapCreator safeMapCreator;

    @Before
    public void setup(){
        safeMapCreator = new SafeMapCreator();
    }

    // test create the map
    @Test
    public void testCreator(){
        Map safeMap = safeMapCreator.creator(3);
        Map expectedMap = SafeMap.getInstance(3);
        assertEquals(expectedMap, safeMap);
    }

    @After
    public void teardown(){
        safeMapCreator = null;
    }

}
