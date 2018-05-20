package cps2002.game;

public class SafeMapCreator {

    /* create the map
    * Parameters: numOfPlayers-> the number of players in the game
    * Returns: Map-> the created map*/
    public Map creator(int numOfPlayers){

        return SafeMap.getInstance(numOfPlayers);
    }
}
