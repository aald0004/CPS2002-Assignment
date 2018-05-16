package cps2002.game;

import java.util.*;

public class PlayerObserver extends Observer{

    // Array list to store the players of the team
    public ArrayList<Player> teamMembers;

    /* PlayerObserver constructor.
    Attaches this observer to the array list in the subject class
    *Parameters: subject-> the subject
     *           teamMembers-> array list containing the players in this team */
    public PlayerObserver(Subject subject, ArrayList<Player> teamMembers){
        this.subject = subject;
        this.subject.attach(this);
        this.teamMembers = teamMembers;
    }

    // update the players' maps
    @Override
    public void update(){

        if(teamMembers != null) {

            for (int i = 0; i < teamMembers.size(); i++) {

                teamMembers.get(i).revealColour(subject.getx(), subject.gety(), subject.getColour());
            }
        }


    }
}
