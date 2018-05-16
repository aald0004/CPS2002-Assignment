package cps2002.game;

public abstract class Observer {

    // holds the subject
    protected Subject subject;


    // update the players in the team
    public abstract void update();

}
