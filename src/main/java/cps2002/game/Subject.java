package cps2002.game;

import java.util.*;

public class Subject {

    // add observers to the array list
    private ArrayList<Observer> observers = new ArrayList<Observer>();

    // state parameters
    // x coordinate of the tile to be updated
    private int x;
    // y coordinate of the tile to be updated
    private int y;
    // colour of the tile to be updated
    private char colour;

    // store the team number
    private int teamID;

    // boolean to determine if observers are notified
    public boolean areNotified = false;

    /* setter for the current team's map and notify the observers
     of the change in map
      Parameters: x-> x coordinate of the tile to be updated
                  y-> y coordinate of the tile to be updated
                  colour-> colour of the tile to be updated*/
    public void setState(int x, int y, char colour){
        this.colour = colour;
        this.x = x;
        this.y = y;

        if(observers.size() > 0) {
            notifyAllObservers();
        }
    }

    /* getter for the colour
    * Returns: char-> the colour*/
    public char getColour(){

        return colour;
    }

    /* getter for the x coordinate
    * Returns: int-> x coordinate*/
    public int getx(){

        return x;
    }

    /* getter for the y coordinate
    * Returns: int-> the y coordinate*/
    public int gety(){
        return y;
    }

    /* setter for the team ID to identify the team
    * Parameters: teamID-> the team ID*/
    public void setTeamID(int teamID){
        this.teamID = teamID;
    }

    /* getter for the team ID
    * Returns: int-> the team ID*/
    public int getTeamID(){

        return teamID;
    }


    /* add an observer to the array list
    * Parameters: o-> observer to be added to the list*/
    public void attach(Observer o){
        observers.add(o);
    }

    /* getter for observers
    * Returns arrayList-> the observers*/
    public ArrayList<Observer> getObservers(){

        return observers;
    }

    /* notify all observers in the list of the change in map */
    public void notifyAllObservers(){

        areNotified = false;

        observers.get(getTeamID()).update();

        areNotified = true;
    }
}
