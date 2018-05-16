package cps2002.game;

import java.util.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PlayerObserverTest {

    private Subject subject;
    private PlayerObserver playerobserver;
    private Player player;

    @Before
    public void setup(){

        subject = new Subject();
        player = new Player(10,2,"Safe");
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(player);

        playerobserver = new PlayerObserver(subject, players);
    }

    /* test that the teamMembers array list is set up correctly */
    @Test
    public void testTeamMembersArrayList(){

        assertEquals(true, playerobserver.teamMembers.contains(player));
    }

    /* test that subject is set up correctly */
    @Test
    public void testSubject(){

        assertEquals(subject, playerobserver.subject);
    }

    /* test that update is working correctly
     * First you need to set up the subject state and the player object */
    @Test
    public void testUpdate(){

        subject.setState(0,0,'G');
        subject.setTeamID(0);

        player.setTeamID(0);
        player.generatePlayerMap();

        playerobserver.update();

        assertEquals('G', player.getTileType(0,0));

    }

    @After
    public void teardown(){

        subject = null;
        playerobserver = null;
        player = null;
    }

}
