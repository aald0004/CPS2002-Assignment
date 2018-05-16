package cps2002.game;

/* Map interface */

public interface Map {

    /* set the number of players
     * Parameters: numPlayers-> the number of players*/
    public void setNumPlayers(int numPlayers);

    /* get the number of players
     * Returns: int-> the number of players*/
    public int getNumPlayers();

    /* set the map
    Parameters: map-> the game map */
    public void setGameMap(char[][] map);

    /* get the map
    Returns: char[][]-> the game map */
    public char[][] getMap();

    /* generate game map
     * Returns: char[][]-> returns a 2D-array containing the game map.
     * g symbolises a grey tile
     * t symbolises a treasure tile
     * w symbolises a water tile
     * however, this method fills the map with just grey tiles.
     * The water tiles and the treasure tiles are in a separate method*/
    public char[][] setMap();

    // add water tiles to the map
    public void addWaterTiles();

    /*  Determines if a water tile can be placed in a
     * particular position.
     * Parameters: x-> x coordinate of the proposed water tile
     *             y-> y coordinate of the proposed water tile
     * Returns: boolean-> true if valid position, otherwise false*/
    public boolean checkIfValidWaterPosition(int x, int y);

    // add a treasure tile to the map
    public void addTreasureTile();
    public String type = "";

    /* set the size of the map
     * Parameters: size-> the size of the map*/
    public void mapSizeSetter(int s);

    /* check if the map size fits the constraints
     * Parameters: s-> size of map
     * Returns: boolean-> returns true if map size is valid*/
    public boolean setSize(int s);

    // getter for the treasure tile's x position
    // returns the treasure x coordinate
    public int getTreasurex();

    // getter for the treasure tile's y position
    // returns the treasure y coordinate
    public int getTreasurey();

    /* get the tile type
     * Parameters: x-> the x coordinate of the tile
     *             y-> the y coordinate of the tile
     * Returns: char-> the char representing the type of tile*/
    public char getTileType(int x, int y);


    public void generatePlayerMap();
    public void revealColour(int x, int y, char colour);

    /* determines if the map size obeys the constraints
     * Parameters: s-> map size
     * Returns: boolean-> returns true if the size obeys the constraints*/
    public boolean setMapSize(int s);

    /* generate the map */
    public void generateMap();

    /* getter for the map size
    Returns: int-> map size */
    public int getSize();

    /* set the number of water neighbours
     * Parameters: waterNieghbours-> the water neighbours*/
    public void setNumberOfWaterNeighbours(int[][] waterNeighbours);

    /* set a particular value to an index in water neighbour
     * Parameters: x-> x index
     *             y-> y index
     *             value-> value to be set */
    public void setValueInWaterNeighbour(int x, int y, int value);



}
