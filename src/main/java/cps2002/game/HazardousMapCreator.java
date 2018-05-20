package cps2002.game;

public class HazardousMapCreator {

    /* create the map
     * Parameters: numOfPlayers-> the number of players in the game
     * Returns: Map-> the created map*/
    public Map creator(int numOfPlayers){

        return HazardousMap.getInstance(numOfPlayers);
    }
}
