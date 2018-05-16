package cps2002.game;

public class SafeMapCreator {

    public Map creator(int numOfPlayers){

        return SafeMap.getInstance(numOfPlayers);
    }
}
