package cps2002.game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/* this class tests the functionality of the Player class */

public class PlayerTest {

    private Player player;


    // set up a new player with map size 5 and number of players 2
    @Before
    public void setup(){
        player = new Player(5, 2, "Safe");
    }

    // test that setting a player's position functions correctly
    @Test
    public void testSetPosition(){
        Position pos = new Position(4,5);
        boolean ans = player.setPosition(pos);

        boolean expected = true;

        assertEquals(expected, ans);
    }


    // test generate player map
    @Test
    public void testGeneratePlayerMap(){

        // 2d array to store the player map
        char[][] playerMap = new char[player.size][player.size];

        // expected map
        playerMap[0][0] = 'g';
        playerMap[0][1] = 'g';
        playerMap[0][2] = 'g';
        playerMap[0][3] = 'g';
        playerMap[0][4] = 'g';

        playerMap[1][0] = 'g';
        playerMap[1][1] = 'g';
        playerMap[1][2] = 'g';
        playerMap[1][3] = 'g';
        playerMap[1][4] = 'g';

        playerMap[2][0] = 'g';
        playerMap[2][1] = 'g';
        playerMap[2][2] = 'g';
        playerMap[2][3] = 'g';
        playerMap[2][4] = 'g';

        playerMap[3][0] = 'g';
        playerMap[3][1] = 'g';
        playerMap[3][2] = 'g';
        playerMap[3][3] = 'g';
        playerMap[3][4] = 'g';

        playerMap[4][0] = 'g';
        playerMap[4][1] = 'g';
        playerMap[4][2] = 'g';
        playerMap[4][3] = 'g';
        playerMap[4][4] = 'g';

        // generate player map
        player.generatePlayerMap();


        assertArrayEquals(playerMap, player.map);
    }


    // test get tile type
    @Test
    public void testGetTileType(){

        // generate the player map
        player.generatePlayerMap();

        assertEquals('g', player.getTileType(0,0));
    }

    // test reveal colour
    @Test
    public void testRevealColour(){

        // generate player map
        player.generatePlayerMap();

        // set the tile type of the map at position (0,0) to t
        player.revealColour(0,0,'t');

        // 2d array to store the player map
        char[][] playerMap = new char[player.size][player.size];

        // expected map
        playerMap[0][0] = 't';
        playerMap[0][1] = 'g';
        playerMap[0][2] = 'g';
        playerMap[0][3] = 'g';
        playerMap[0][4] = 'g';

        playerMap[1][0] = 'g';
        playerMap[1][1] = 'g';
        playerMap[1][2] = 'g';
        playerMap[1][3] = 'g';
        playerMap[1][4] = 'g';

        playerMap[2][0] = 'g';
        playerMap[2][1] = 'g';
        playerMap[2][2] = 'g';
        playerMap[2][3] = 'g';
        playerMap[2][4] = 'g';

        playerMap[3][0] = 'g';
        playerMap[3][1] = 'g';
        playerMap[3][2] = 'g';
        playerMap[3][3] = 'g';
        playerMap[3][4] = 'g';

        playerMap[4][0] = 'g';
        playerMap[4][1] = 'g';
        playerMap[4][2] = 'g';
        playerMap[4][3] = 'g';
        playerMap[4][4] = 'g';


        assertArrayEquals(playerMap, player.map);
    }

    // set the player to null
    @After
    public void teardown(){
        player = null;
    }
}
