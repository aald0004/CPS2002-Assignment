package cps2002.game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SubjectTest {

    private Subject subject;

    @Before
    public void setup(){
        subject = new Subject();
    }

    /* Test setState, setting the current x coordinate state variable */
    @Test
    public void testSetStateXCoordinate(){

        subject.setState(1,2,'g');

        assertEquals(1,subject.getx());
    }

    /* Test setState, setting the current y coordinate state variable */
    @Test
    public void testSetStateYCoordinate(){

        subject.setState(2,3,'b');
        assertEquals(3,subject.gety());
    }

    /* Test setState, setting the current tile colour state variable */
    @Test
    public void testSetStateTileColour(){

        subject.setState(0,0,'b');
        assertEquals('b', subject.getColour());
    }

    /* Test setTeamID, setting the current team ID */
    @Test
    public void testSetTeamID(){

        subject.setTeamID(123);

        assertEquals(123, subject.getTeamID());
    }

    /* Test attachObserver, attach an observer to the array list and check if its size increases */
    @Test
    public void testAttachObserver(){

        Observer observer = new PlayerObserver(subject, null);

        subject.attach(observer);

        assertEquals(2, subject.getObservers().size());

        observer = null;
    }


    /* test notifyAllObservers by notifying dummy observers */
    @Test
    public void testNotifyAllObservers(){

        Observer observer = new PlayerObserver(subject, null);

        subject.notifyAllObservers();

        assertEquals(true, subject.areNotified);

        observer = null;
    }

    /* test setState, testing that it notifies observers */
    @Test
    public void testSetStateNotify(){

        Observer observer = new PlayerObserver(subject, null);

        subject.attach(observer);

        subject.setState(0,1,'b');

        assertEquals(true, subject.areNotified);

        observer = null;
    }

    @After
    public void teardown(){
        subject = null;
    }
}
