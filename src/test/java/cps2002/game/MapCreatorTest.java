package cps2002.game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/* This class tests the functionality of the MapCreator class */

public class MapCreatorTest {

    private MapCreator creator;

    @Before
    public void setup(){
        creator = new MapCreator();
    }

    // test creating a safe map
    @Test
    public void testCreateSafeMap(){

        Map sm = creator.create("Safe", 4);
        assertEquals(sm.getClass().getName(), "cps2002.game.SafeMap");
    }

    // test creating a hazardous map
    @Test
    public void testCreateHazardousMap(){

        Map sm = creator.create("Hazardous", 4);
        assertEquals(sm.getClass().getName(), "cps2002.game.HazardousMap");
    }

    // test creating a null map
    @Test (expected = IllegalArgumentException.class)
    public void testCreateNullMap(){

        Map sm = creator.create("Hadous", 4);
    }




    @After
    public void treardown(){
        creator = null;
    }

}
