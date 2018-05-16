package cps2002.game;

public class HazardousMapCreator {

    public Map creator(int numOfPlayers){

        return HazardousMap.getInstance(numOfPlayers);
    }
}
