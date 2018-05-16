package cps2002.game;

import java.util.Random;

public class HazardousMap implements Map{

    // string to hold the type of map
    public String type = "Hazardous";

    // constraints for map size
    public static final int MAX_SIZE = 50;
    public static final int MIN_SIZE_1 = 5;
    public static final int MIN_SIZE_2 = 8;

    // char 2D-array to store the map
    char[][] map;

    // size of the map
    int size;


    // number of players in the game
    int numOfPlayers;

    // used to generate random map coordinates
    Random rand = new Random();

    // stores the x coordinate of the treasure tile
    private int treasureTile_x = -1;

    // stores the y coordinate of the treasure tile
    private int treasureTile_y = -1;

    // stores the number of water tiles surrounding a tile
    int[][] numberOfWaterNeighbours;

    // stores a private instance of the map
    private static HazardousMap instance = null;


    /* Map constructor
     * Parameters: numOfPlayers-> sets the number of players in the game*/
    private HazardousMap(int numOfPlayers){

        this.numOfPlayers = numOfPlayers;


    }

    /* getter for the private instance. Creates a new map if the instance is null
    * Parameters: numOfPlayers-> num of players taking part in the game
    * Returns:   HazardousMap-> the private hazardous map instance*/
    public static HazardousMap getInstance(int numOfPlayers){
        if(instance == null){
            instance = new HazardousMap(numOfPlayers);
        }

        return instance;
    }

    /* check if the map size fits the constraints
     * Parameters: s-> size of map
     * Returns: boolean-> returns true if map size is valid*/
    public boolean setSize(int s){


        boolean validNumber = false;

        validNumber = setMapSize(s);


        return validNumber;
    }

    /* set the size of the map
    * Parameters: size-> the size of the map*/
    public void mapSizeSetter(int s){
        size = s;
    }

    /* getter for the map size
    Returns: int-> map size */
    public int getSize(){

        return size;
    }


    /* determines if the map size obeys the constraints
     * Parameters: s-> map size
     * Returns: boolean-> returns true if the size obeys the constraints*/
    public boolean setMapSize(int s){

        boolean valid = false;

        // check if the number of players is less than 5
        if(numOfPlayers<=4){

            // check if the map size is in the required range
            if(s >= MIN_SIZE_1 && s<=MAX_SIZE){
                size = s;
                valid =  true;
            } else {
                valid = false;
            }
        }

        // check if the number of players is between 5 and 8
        if(numOfPlayers<=8 && numOfPlayers>4){

            // check if the map size is in the required range
            if(s >= MIN_SIZE_2 && s<=MAX_SIZE){
                valid = true;
            } else {
                valid = false;
            }
        }

        return valid;


    }

    /* generate the map */
    public void generateMap(){

        map = setMap();
        addWaterTiles();
        addTreasureTile();
    }

    /* generate game map
     * Returns: char[][]-> returns a 2D-array containing the game map.
     * g symbolises a grey tile
     * t symbolises a treasure tile
     * w symbolises a water tile
     * however, this method fills the map with just grey tiles.
     * The water tiles and the treasure tiles are in a separate method*/
    public char[][] setMap(){

        // declare a new 2D char array
        char[][] tm = new char[size][size];

        // fill the array with grey tiles
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                tm[i][j] = 'g';

            }
        }


        return tm;

    }

    // add water tiles to the map
    public void addWaterTiles(){

        numberOfWaterNeighbours = new int[size][size];

        // set the initial water neighbours to 0
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                numberOfWaterNeighbours[i][j] = 0;
            }
        }

        // set the random number of water tiles
        double randomNum = 0.25 + (0.35-0.25)*rand.nextDouble();

        // around 25% to 35% of the map will be filled with water tiles
        double percentage = size*randomNum;
        int numToGenerate = (int)Math.floor(percentage);

        int x;
        int y;

        x = 1;
        y = 4;

        // set a water tile
        map[y][x] = 'w';

        // increase number of neighbours in tile below
        if(y != size-1){
            numberOfWaterNeighbours[y+1][x] = numberOfWaterNeighbours[y+1][x]+1;
        }

        // increase number of neighbours in tile above
        if(y != 0){
            numberOfWaterNeighbours[y-1][x] = numberOfWaterNeighbours[y-1][x]+1;

        }

        // increase number of neighbours in tile to the right
        if(x != size-1){
            numberOfWaterNeighbours[y][x+1] = numberOfWaterNeighbours[y][x+1]+1;

        }

        // increase number of neighbours in tile to the left
        if(x != 0){
            numberOfWaterNeighbours[y][x-1] = numberOfWaterNeighbours[y][x-1]+1;

        }

        // fill the map with water tiles
        for(int i = 1; i < numToGenerate;i++){

            // generate a random x and y coordinate
            x = rand.nextInt(size - 1);
            y = rand.nextInt(size - 1);

            // make sure x and y are in required range
            while(x >=size && y >=size) {
                x = rand.nextInt(size - 1);
                y = rand.nextInt(size - 1);
            }

            // check if (x,y) is a valid water tile position, if no decrement i
            if(checkIfValidWaterPosition(x,y) == false){
                i--;
            }

        }

    }

    /*  Determines if a water tile can be placed in a
     * particular position.
     * Parameters: x-> x coordinate of the proposed water tile
     *             y-> y coordinate of the proposed water tile
     * Returns: boolean-> true if valid position, otherwise false*/
    public boolean checkIfValidWaterPosition(int x, int y) {

        boolean valid = false;

        // check if the new water tile does not create a box around a green tile, when the blue tile is not on the border
        if (y < size - 1 && y> 0 && x < size - 1 && x > 0) {
            if (numberOfWaterNeighbours[y + 1][x] < 3 &&
                    numberOfWaterNeighbours[y - 1][x] < 3 &&
                    numberOfWaterNeighbours[y][x + 1] < 3 &&
                    numberOfWaterNeighbours[y][x - 1] < 3) {

                map[y][x] = 'w';

                // update the neighbours
                numberOfWaterNeighbours[y][x - 1] = numberOfWaterNeighbours[y][x - 1] + 1;
                numberOfWaterNeighbours[y][x + 1] = numberOfWaterNeighbours[y][x + 1] + 1;
                numberOfWaterNeighbours[y - 1][x] = numberOfWaterNeighbours[y - 1][x] + 1;
                numberOfWaterNeighbours[y + 1][x] = numberOfWaterNeighbours[y + 1][x] + 1;

                valid = true;

            } else {
                valid =  false;
            }


        }

        // if water tile is in a corner, change tile to blue
        if (x == 0 && y == 0 || y == 0 && x == size - 1 || y == size - 1 && x == 0 || y == size - 1 && x == size - 1) {
            map[y][x] = 'w';
            valid = true;
        }

        // if water tile is in the first column
        if (x == 0 && valid == false) {
            // if tile will not box in green tile, change to water tile
            if (numberOfWaterNeighbours[y + 1][x] < 3 &&
                    numberOfWaterNeighbours[y - 1][x] < 3 &&
                    numberOfWaterNeighbours[y][x + 1] < 3) {

                map[y][x] = 'w';

                // update the neighbours
                numberOfWaterNeighbours[y][x + 1] = numberOfWaterNeighbours[y][x + 1] + 1;
                numberOfWaterNeighbours[y - 1][x] = numberOfWaterNeighbours[y - 1][x] + 1;
                numberOfWaterNeighbours[y + 1][x] = numberOfWaterNeighbours[y + 1][x] + 1;

                valid = true;

            } else {
                valid = false;
            }

        }

        // if water tile is in the last column
        if (x == size - 1 && valid == false) {
            // if tile will not box in green tile, change to water tile
            if (numberOfWaterNeighbours[y + 1][x] < 3 &&
                    numberOfWaterNeighbours[y - 1][x] < 3 &&
                    numberOfWaterNeighbours[y][x - 1] < 3) {

                map[y][x] = 'w';

                // update neighbours
                numberOfWaterNeighbours[y][x - 1] = numberOfWaterNeighbours[y][x - 1] + 1;
                numberOfWaterNeighbours[y - 1][x] = numberOfWaterNeighbours[y - 1][x] + 1;
                numberOfWaterNeighbours[y + 1][x] = numberOfWaterNeighbours[y + 1][x] + 1;

                valid = true;

            } else {
                valid = false;
            }
        }


        // if water tile is in the first row
        if (y == 0 && valid == false) {
            // if tile will not box in green tile, change to water tile
            if (numberOfWaterNeighbours[y + 1][x] < 3 &&
                    numberOfWaterNeighbours[y][x - 1] < 3 &&
                    numberOfWaterNeighbours[y][x + 1] < 3) {

                map[y][x] = 'w';

                // update neighbours
                numberOfWaterNeighbours[y][x + 1] = numberOfWaterNeighbours[y][x + 1] + 1;
                numberOfWaterNeighbours[y][x - 1] = numberOfWaterNeighbours[y][x - 1] + 1;
                numberOfWaterNeighbours[y + 1][x] = numberOfWaterNeighbours[y + 1][x] + 1;

                valid = true;

            } else {
                valid = false;
            }
        }

        // if water tile is in the last column
        if (y == size - 1 && valid == false) {
            // if tile will not box in green tile, change to water tile
            if (numberOfWaterNeighbours[y - 1][x] < 3 &&
                    numberOfWaterNeighbours[y][x - 1] < 3 &&
                    numberOfWaterNeighbours[y][x + 1] < 3) {

                map[y][x] = 'w';

                // update neighbours
                numberOfWaterNeighbours[y][x - 1] = numberOfWaterNeighbours[y][x - 1] + 1;
                numberOfWaterNeighbours[y][x + 1] = numberOfWaterNeighbours[y][x + 1] + 1;
                numberOfWaterNeighbours[y - 1][x] = numberOfWaterNeighbours[y - 1][x] + 1;

                valid =  true;

            } else {
                valid =  false;
            }
        }

        return valid;
    }

    // add a treasure tile to the map
    public void addTreasureTile(){


        int x;
        int y;

        // generate random x and y coordinates
        x = rand.nextInt(size - 1);
        y = rand.nextInt(size - 1);

        // set the treasure coordinates to the generated coordinates
        treasureTile_x = x;
        treasureTile_y = y;

        map[y][x] = 't';


    }

    // getter for the treasure tile's x position
    // returns the treasure x coordinate
    public int getTreasurex(){

        return treasureTile_x;
    }

    // getter for the treasure tile's y position
    // returns the treasure y coordinate
    public int getTreasurey(){

        return treasureTile_y;
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


    /* set the number of players
    * Parameters: numPlayers-> the number of players*/
    public void setNumPlayers(int numPlayers){

        numOfPlayers = numPlayers;
    }

    /* get the number of players
    * Returns: int-> the number of players*/
    public int getNumPlayers(){
        return  numOfPlayers;
    }

    /* set the map
    Parameters: map-> the game map */
    public void setGameMap(char[][] map){
        this.map = map;
    }

    /* get the map
    Returns: char[][]-> the game map */
    public char[][] getMap(){

        return map;
    }

    /* set the number of water neighbours
     * Parameters: waterNieghbours-> the water neighbours*/
    public void setNumberOfWaterNeighbours(int[][] waterNeighbours){

        numberOfWaterNeighbours = waterNeighbours;

    }

    /* set a particular value to an index in water neighbour
     * Parameters: x-> x index
     *             y-> y index
     *             value-> value to be set*/
    public void setValueInWaterNeighbour(int x, int y, int value){
        numberOfWaterNeighbours[y][x] = value;
    }

}
