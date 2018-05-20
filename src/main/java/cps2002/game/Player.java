package cps2002.game;

public class Player {

    // player's current Position
    Position position;

    // holds the map size
    int size = 0;

    // player's map
    Map tm;

    // player's starting Position
    Position startingPosition;

    // holds the player's team id
    private int teamID;


    char[][] map;

    /* Player constructor
     * Parameters: mpSize-> sets the map size
      *            numPlayers-> sets the number of players in the game*/
    public Player(int mpSize, int numPlayers, String type){

        // create map
        size = mpSize;

        generatePlayerMap();


    }

    /* set the player's Position
    * Parameters: newPos-> the player's new position */
    public boolean setPosition(Position newPos){

        position = newPos;

        return true;
    }


    /* get the tile type
     * Parameters: x-> the x coordinate of the tile
     *             y-> the y coordinate of the tile
     * Returns: char-> the char representing the type of tile*/
    public char getTileType(int x, int y){

        return map[y][x];

    }

    // generate the initial player map - all grey tiles
    public void generatePlayerMap(){

        map = new char[size][size];

        // generate a map filled of grey tiles
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){

                map[i][j] = 'g';

            }
        }

    }

    /* set the map tile to a different tile (update the player map to reflect discovered tiles)
     * Parameters: x-> x coordinate of tile
     *             y-> y coordinate of tile
     *             colour-> the new colour of the tile*/
    public void revealColour(int x, int y, char colour){

        map[y][x] = colour;
    }

    /* setter for the team ID
    * Parameters: teamID-> the player's team ID*/
    public void setTeamID(int teamID){
        this.teamID = teamID;
    }

    /* getter for the team ID
    * Returns: int-> the team ID */
    public int getTeamID(){

        return teamID;
    }


}