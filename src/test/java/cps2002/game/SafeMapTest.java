package cps2002.game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SafeMapTest {

    private Map tm;

    // set two instances of a map. The first one with 3 players
    // and the second one with 8 players
    @Before
    public void setup(){
        MapCreator creator = new MapCreator();
        tm = creator.create("Safe", 3);
    }


    // test that set map functions correctly
    @Test
    public void testSetMap(){

        // set the map size
        tm.mapSizeSetter(5);

        // generate the map
        char[][] map = tm.setMap();

        // create an expected map
        char[][] expectedMap = new char[tm.getSize()][tm.getSize()];

        expectedMap[0][0] = 'g';
        expectedMap[0][1] = 'g';
        expectedMap[0][2] = 'g';
        expectedMap[0][3] = 'g';
        expectedMap[0][4] = 'g';

        expectedMap[1][0] = 'g';
        expectedMap[1][1] = 'g';
        expectedMap[1][2] = 'g';
        expectedMap[1][3] = 'g';
        expectedMap[1][4] = 'g';

        expectedMap[2][0] = 'g';
        expectedMap[2][1] = 'g';
        expectedMap[2][2] = 'g';
        expectedMap[2][3] = 'g';
        expectedMap[2][4] = 'g';

        expectedMap[3][0] = 'g';
        expectedMap[3][1] = 'g';
        expectedMap[3][2] = 'g';
        expectedMap[3][3] = 'g';
        expectedMap[3][4] = 'g';

        expectedMap[4][0] = 'g';
        expectedMap[4][1] = 'g';
        expectedMap[4][2] = 'g';
        expectedMap[4][3] = 'g';
        expectedMap[4][4] = 'g';

        expectedMap[3][4] = 'g';
        expectedMap[4][1] = 'g';

        assertArrayEquals(expectedMap,map);

    }

    // test addWaterTiles()
    @Test
    public void testWaterTiles(){

        // set the map size
        tm.mapSizeSetter(5);

        // generate the map
        tm.setGameMap(tm.setMap());

        // add the water tiles to the map
        tm.addWaterTiles();

        assertEquals('w', tm.getMap()[4][1]);
    }

    // test addTreasureTile()
    @Test
    public void testTreasureTile(){

        // set the map size
        tm.mapSizeSetter(5);

        // generate the map
        tm.setGameMap(tm.setMap());


        // add the water tiles to the map
        tm.addWaterTiles();

        // add the treasure tile to the map
        tm.addTreasureTile();

        boolean treasureTileFound = false;


        // check if there exists a treasure tile in the map
        for(int i = 0; i < tm.getSize();i++){

            for(int j = 0;j < tm.getSize();j++){
                if(tm.getTileType(j,i) == 't'){
                    treasureTileFound = true;
                    break;
                }

                if(treasureTileFound == true){
                    break;
                }
            }

        }

        assertEquals(true, treasureTileFound);
    }

    // test that get the tile type when the tile is a treasure tile works
    @Test
    public void testGetTileTypeTreasure(){

        // set the map size
        tm.mapSizeSetter(5);

        // generate the map
        tm.setGameMap(tm.setMap());


        tm.addTreasureTile();

        char type = tm.getTileType(tm.getTreasurex(),tm.getTreasurey());
        assertEquals('t', type);
    }

    // test that get the tile type when the tile is a water tile works
    @Test
    public void testGetTileTypeWater(){

        // set the map size
        tm.mapSizeSetter(5);

        // generate the map
        tm.setGameMap(tm.setMap());

        tm.addWaterTiles();

        char type = tm.getTileType(1,4);
        assertEquals('w', type);
    }

    // test that get the tile type when the tile is a grey tile works
    @Test
    public void testGetTileTypeGrey(){

        // set the map size
        tm.mapSizeSetter(5);

        // generate the map
        tm.setGameMap(tm.setMap());


        char type = tm.getTileType(4,4);
        assertEquals('g', type);
    }

    // test that the number of players variable is being set correctly
    @Test
    public void testNumberOfPlayers(){

        tm.setNumPlayers(3);
        int num = tm.getNumPlayers();
        assertEquals(3,num);
    }

    // test that the setSize function works
    @Test
    public void testSetSize(){
        assertEquals(true, tm.setSize(6));
    }

    // test that the setSize function works when the constraints fail
    @Test
    public void testSetSizeFail(){
        assertEquals(false, tm.setSize(4));
    }

    // test set map size when number of players is less than 5. Expecting true
    @Test
    public void testSetMapSizeWithTrueConditionWhenPlayerIsLessThan5(){
        tm.setNumPlayers(2);
        boolean ans = tm.setMapSize(6);
        boolean expected = true;
        assertEquals(expected, ans);
    }

    // test set map size when number of players is less than 9. Expecting true
    @Test
    public void testSetMapSizeWithTrueConditionWhenPlayerIsLessThan9(){

        tm.setNumPlayers(8);
        boolean ans = tm.setMapSize(10);
        boolean expected = true;
        assertEquals(expected, ans);
    }

    // test set map size when number of players is less than 9. Expecting false
    @Test
    public void testSetMapSizeWithFalseConditionWhenPlayerIsLessThan9(){

        tm.setNumPlayers(8);
        boolean ans = tm.setMapSize(7);
        boolean expected = false;
        assertEquals(expected, ans);
    }

    // test set map size when number of players is less than 5. Expecting false
    @Test
    public void testSetMapSizeWithFalseConditionWhenPlayerIsLessThan5(){

        boolean ans = tm.setMapSize(3);
        boolean expected = false;
        assertEquals(expected, ans);
    }

    // test that the generatePlayerMap function's map is full of grey tiles
    @Test
    public void testGeneratePlayerMap(){
        tm.mapSizeSetter(2);
        tm.generatePlayerMap();
        char[][] m = new char[2][2];
        m[0][0] = 'g';
        m[0][1] = 'g';
        m[1][0] = 'g';
        m[1][1] = 'g';
        assertArrayEquals(m, tm.getMap());

    }

    // test that revelColour works
    @Test
    public void testRevealColour(){
        // set map size
        tm.mapSizeSetter(2);

        // generate player map
        tm.generatePlayerMap();

        // set the tile to water tile at position x = 0,y = 0
        tm.revealColour(0,0,'w');

        char[][] map = new char[2][2];

        map[0][0] = 'w';
        map[0][1] = 'g';
        map[1][1] = 'g';
        map[1][0] = 'g';

        assertArrayEquals(map, tm.getMap());

    }


    // test getx
    @Test
    public void testGetx(){

        tm.mapSizeSetter(5);
        tm.generateMap();
        int expected_x = tm.getTreasurex();

        assertNotEquals(null, expected_x);
    }

    // test gety
    @Test
    public void testGety(){

        tm.mapSizeSetter(5);
        tm.generateMap();
        int expected_y = tm.getTreasurey();

        assertNotEquals(null, expected_y);
    }

    // test if more than one water tile is found
    @Test
    public void testNumOfWaterTiles(){

        // set the map size
        tm.mapSizeSetter(50);

        // generate the map
        tm.setGameMap(tm.setMap());

        tm.addWaterTiles();
        tm.addTreasureTile();

        int counter = 0;

        boolean morethan1found = false;

        // check if more than 2 water tiles are found
        for(int i = 0; i < tm.getSize();i++){

            for(int j = 0;j < tm.getSize();j++){
                if(tm.getTileType(j,i) == 'w'){
                    counter++;
                }

                if(counter > 0){
                    morethan1found = true;
                    break;
                }
            }

        }

        assertEquals(true, morethan1found);


    }

    // test to check if valid water tile, middle section, with valid tile
    @Test
    public void testValidWaterTileMiddleSection(){


        // set the map size
        tm.mapSizeSetter(5);

        // generate the map
        tm.setGameMap(tm.setMap());


        int[][] numOfBlueNeigh = new int[tm.getSize()][tm.getSize()];


        numOfBlueNeigh[0][0] = 1;
        numOfBlueNeigh[0][1] = 1;
        numOfBlueNeigh[0][2] = 1;
        numOfBlueNeigh[0][3] = 1;
        numOfBlueNeigh[0][4] = 1;

        numOfBlueNeigh[1][0] = 1;
        numOfBlueNeigh[1][1] = 1;
        numOfBlueNeigh[1][2] = 1;
        numOfBlueNeigh[1][3] = 1;
        numOfBlueNeigh[1][4] = 1;

        numOfBlueNeigh[2][0] = 1;
        numOfBlueNeigh[2][1] = 1;
        numOfBlueNeigh[2][2] = 1;
        numOfBlueNeigh[2][3] = 1;
        numOfBlueNeigh[2][4] = 1;

        numOfBlueNeigh[3][0] = 1;
        numOfBlueNeigh[3][1] = 1;
        numOfBlueNeigh[3][2] = 1;
        numOfBlueNeigh[3][3] = 1;
        numOfBlueNeigh[3][4] = 1;

        numOfBlueNeigh[4][0] = 1;
        numOfBlueNeigh[4][1] = 1;
        numOfBlueNeigh[4][2] = 1;
        numOfBlueNeigh[4][3] = 1;
        numOfBlueNeigh[4][4] = 1;

        tm.setNumberOfWaterNeighbours(numOfBlueNeigh);

        assertEquals(true, tm.checkIfValidWaterPosition(3,2));


    }

    // test to check if valid water tile, middle section, with invalid tile
    @Test
    public void testInvalidTileInMiddle(){

        // set the map size
        tm.mapSizeSetter(5);

        // generate the map
        tm.setGameMap(tm.setMap());


        int[][] numOfBlueNeigh = new int[tm.getSize()][tm.getSize()];
        numOfBlueNeigh[0][0] = 1;
        numOfBlueNeigh[0][1] = 1;
        numOfBlueNeigh[0][2] = 1;
        numOfBlueNeigh[0][3] = 1;
        numOfBlueNeigh[0][4] = 1;

        numOfBlueNeigh[1][0] = 1;
        numOfBlueNeigh[1][1] = 1;
        numOfBlueNeigh[1][2] = 1;
        numOfBlueNeigh[1][3] = 1;
        numOfBlueNeigh[1][4] = 1;

        numOfBlueNeigh[2][0] = 1;
        numOfBlueNeigh[2][1] = 1;
        numOfBlueNeigh[2][2] = 1;
        numOfBlueNeigh[2][3] = 1;
        numOfBlueNeigh[2][4] = 1;

        numOfBlueNeigh[3][0] = 1;
        numOfBlueNeigh[3][1] = 1;
        numOfBlueNeigh[3][2] = 1;
        numOfBlueNeigh[3][3] = 1;
        numOfBlueNeigh[3][4] = 1;

        numOfBlueNeigh[4][0] = 1;
        numOfBlueNeigh[4][1] = 1;
        numOfBlueNeigh[4][2] = 1;
        numOfBlueNeigh[4][3] = 1;
        numOfBlueNeigh[4][4] = 1;

        tm.setNumberOfWaterNeighbours(numOfBlueNeigh);

        tm.setValueInWaterNeighbour(2,1,3);


        assertEquals(false, tm.checkIfValidWaterPosition(2,2));
    }


    // test to check if valid water tile, corners
    @Test
    public void checkValidWaterTileCorners(){

        // set the map size
        tm.mapSizeSetter(5);

        // generate the map
        tm.setGameMap(tm.setMap());


        int[][] numOfBlueNeigh = new int[tm.getSize()][tm.getSize()];
        numOfBlueNeigh[0][0] = 1;
        numOfBlueNeigh[0][1] = 1;
        numOfBlueNeigh[0][2] = 1;
        numOfBlueNeigh[0][3] = 1;
        numOfBlueNeigh[0][4] = 1;

        numOfBlueNeigh[1][0] = 1;
        numOfBlueNeigh[1][1] = 1;
        numOfBlueNeigh[1][2] = 1;
        numOfBlueNeigh[1][3] = 1;
        numOfBlueNeigh[1][4] = 1;

        numOfBlueNeigh[2][0] = 1;
        numOfBlueNeigh[2][1] = 1;
        numOfBlueNeigh[2][2] = 1;
        numOfBlueNeigh[2][3] = 1;
        numOfBlueNeigh[2][4] = 1;

        numOfBlueNeigh[3][0] = 1;
        numOfBlueNeigh[3][1] = 1;
        numOfBlueNeigh[3][2] = 1;
        numOfBlueNeigh[3][3] = 1;
        numOfBlueNeigh[3][4] = 1;

        numOfBlueNeigh[4][0] = 1;
        numOfBlueNeigh[4][1] = 1;
        numOfBlueNeigh[4][2] = 1;
        numOfBlueNeigh[4][3] = 1;
        numOfBlueNeigh[4][4] = 1;

        tm.setNumberOfWaterNeighbours(numOfBlueNeigh);

        assertEquals(true, tm.checkIfValidWaterPosition(0,0));

    }

    // test to check if valid water tile, top row, valid
    @Test
    public void testCheckIfValidWaterTileTopRow(){
        // set the map size
        tm.mapSizeSetter(5);

        // generate the map
        tm.setGameMap(tm.setMap());


        int[][] numOfBlueNeigh = new int[tm.getSize()][tm.getSize()];
        numOfBlueNeigh[0][0] = 1;
        numOfBlueNeigh[0][1] = 1;
        numOfBlueNeigh[0][2] = 1;
        numOfBlueNeigh[0][3] = 1;
        numOfBlueNeigh[0][4] = 1;

        numOfBlueNeigh[1][0] = 1;
        numOfBlueNeigh[1][1] = 1;
        numOfBlueNeigh[1][2] = 1;
        numOfBlueNeigh[1][3] = 1;
        numOfBlueNeigh[1][4] = 1;

        numOfBlueNeigh[2][0] = 1;
        numOfBlueNeigh[2][1] = 1;
        numOfBlueNeigh[2][2] = 1;
        numOfBlueNeigh[2][3] = 1;
        numOfBlueNeigh[2][4] = 1;

        numOfBlueNeigh[3][0] = 1;
        numOfBlueNeigh[3][1] = 1;
        numOfBlueNeigh[3][2] = 1;
        numOfBlueNeigh[3][3] = 1;
        numOfBlueNeigh[3][4] = 1;

        numOfBlueNeigh[4][0] = 1;
        numOfBlueNeigh[4][1] = 1;
        numOfBlueNeigh[4][2] = 1;
        numOfBlueNeigh[4][3] = 1;
        numOfBlueNeigh[4][4] = 1;

        tm.setNumberOfWaterNeighbours(numOfBlueNeigh);

        tm.setValueInWaterNeighbour(2,1,2);

        assertEquals(true, tm.checkIfValidWaterPosition(2,0));

    }

    // test to check if valid water tile, top row, false
    @Test
    public void testCheckValidWaterTileTopRow(){
        // set the map size
        tm.mapSizeSetter(5);

        // generate the map
        tm.setGameMap(tm.setMap());


        int[][] numOfBlueNeigh = new int[tm.getSize()][tm.getSize()];
        numOfBlueNeigh[0][0] = 1;
        numOfBlueNeigh[0][1] = 1;
        numOfBlueNeigh[0][2] = 1;
        numOfBlueNeigh[0][3] = 1;
        numOfBlueNeigh[0][4] = 1;

        numOfBlueNeigh[1][0] = 1;
        numOfBlueNeigh[1][1] = 1;
        numOfBlueNeigh[1][2] = 1;
        numOfBlueNeigh[1][3] = 1;
        numOfBlueNeigh[1][4] = 1;

        numOfBlueNeigh[2][0] = 1;
        numOfBlueNeigh[2][1] = 1;
        numOfBlueNeigh[2][2] = 1;
        numOfBlueNeigh[2][3] = 1;
        numOfBlueNeigh[2][4] = 1;

        numOfBlueNeigh[3][0] = 1;
        numOfBlueNeigh[3][1] = 1;
        numOfBlueNeigh[3][2] = 1;
        numOfBlueNeigh[3][3] = 1;
        numOfBlueNeigh[3][4] = 1;

        numOfBlueNeigh[4][0] = 1;
        numOfBlueNeigh[4][1] = 1;
        numOfBlueNeigh[4][2] = 1;
        numOfBlueNeigh[4][3] = 1;
        numOfBlueNeigh[4][4] = 1;

        tm.setNumberOfWaterNeighbours(numOfBlueNeigh);

        tm.setValueInWaterNeighbour(2,1,3);

        assertEquals(false, tm.checkIfValidWaterPosition(2,0));

    }


    // test to check if valid water tile, bottom row, valid
    @Test
    public void testCheckIfValidWaterTileBottomRow(){
        // set the map size
        tm.mapSizeSetter(5);

        // generate the map
        tm.setGameMap(tm.setMap());


        int[][] numOfBlueNeigh = new int[tm.getSize()][tm.getSize()];
        numOfBlueNeigh[0][0] = 1;
        numOfBlueNeigh[0][1] = 1;
        numOfBlueNeigh[0][2] = 1;
        numOfBlueNeigh[0][3] = 1;
        numOfBlueNeigh[0][4] = 1;

        numOfBlueNeigh[1][0] = 1;
        numOfBlueNeigh[1][1] = 1;
        numOfBlueNeigh[1][2] = 1;
        numOfBlueNeigh[1][3] = 1;
        numOfBlueNeigh[1][4] = 1;

        numOfBlueNeigh[2][0] = 1;
        numOfBlueNeigh[2][1] = 1;
        numOfBlueNeigh[2][2] = 1;
        numOfBlueNeigh[2][3] = 1;
        numOfBlueNeigh[2][4] = 1;

        numOfBlueNeigh[3][0] = 1;
        numOfBlueNeigh[3][1] = 1;
        numOfBlueNeigh[3][2] = 1;
        numOfBlueNeigh[3][3] = 1;
        numOfBlueNeigh[3][4] = 1;

        numOfBlueNeigh[4][0] = 1;
        numOfBlueNeigh[4][1] = 1;
        numOfBlueNeigh[4][2] = 1;
        numOfBlueNeigh[4][3] = 1;
        numOfBlueNeigh[4][4] = 1;

        tm.setNumberOfWaterNeighbours(numOfBlueNeigh);

        tm.setValueInWaterNeighbour(3,2,2);

        assertEquals(true, tm.checkIfValidWaterPosition(2,4));
    }

    // test to check if valid water tile, bottom row, false
    @Test
    public void testCheckValidWaterTileBottomRow(){
        // set the map size
        tm.mapSizeSetter(5);

        // generate the map
        tm.setGameMap(tm.setMap());

        int[][] numOfBlueNeigh = new int[tm.getSize()][tm.getSize()];
        numOfBlueNeigh[0][0] = 1;
        numOfBlueNeigh[0][1] = 1;
        numOfBlueNeigh[0][2] = 1;
        numOfBlueNeigh[0][3] = 1;
        numOfBlueNeigh[0][4] = 1;

        numOfBlueNeigh[1][0] = 1;
        numOfBlueNeigh[1][1] = 1;
        numOfBlueNeigh[1][2] = 1;
        numOfBlueNeigh[1][3] = 1;
        numOfBlueNeigh[1][4] = 1;

        numOfBlueNeigh[2][0] = 1;
        numOfBlueNeigh[2][1] = 1;
        numOfBlueNeigh[2][2] = 1;
        numOfBlueNeigh[2][3] = 1;
        numOfBlueNeigh[2][4] = 1;

        numOfBlueNeigh[3][0] = 1;
        numOfBlueNeigh[3][1] = 1;
        numOfBlueNeigh[3][2] = 1;
        numOfBlueNeigh[3][3] = 1;
        numOfBlueNeigh[3][4] = 1;

        numOfBlueNeigh[4][0] = 1;
        numOfBlueNeigh[4][1] = 1;
        numOfBlueNeigh[4][2] = 1;
        numOfBlueNeigh[4][3] = 1;
        numOfBlueNeigh[4][4] = 1;

        tm.setNumberOfWaterNeighbours(numOfBlueNeigh);

        tm.setValueInWaterNeighbour(2,3,3);

        assertEquals(false, tm.checkIfValidWaterPosition(2,4));

    }

    // test to check if valid water tile, first column, valid
    @Test
    public void testIfValidWaterTileFirstColumn(){

        // set the map size
        tm.mapSizeSetter(5);

        // generate the map
        tm.setGameMap(tm.setMap());

        int[][] numOfBlueNeigh = new int[tm.getSize()][tm.getSize()];
        numOfBlueNeigh[0][0] = 1;
        numOfBlueNeigh[0][1] = 1;
        numOfBlueNeigh[0][2] = 1;
        numOfBlueNeigh[0][3] = 1;
        numOfBlueNeigh[0][4] = 1;

        numOfBlueNeigh[1][0] = 1;
        numOfBlueNeigh[1][1] = 1;
        numOfBlueNeigh[1][2] = 1;
        numOfBlueNeigh[1][3] = 1;
        numOfBlueNeigh[1][4] = 1;

        numOfBlueNeigh[2][0] = 1;
        numOfBlueNeigh[2][1] = 1;
        numOfBlueNeigh[2][2] = 1;
        numOfBlueNeigh[2][3] = 1;
        numOfBlueNeigh[2][4] = 1;

        numOfBlueNeigh[3][0] = 1;
        numOfBlueNeigh[3][1] = 1;
        numOfBlueNeigh[3][2] = 1;
        numOfBlueNeigh[3][3] = 1;
        numOfBlueNeigh[3][4] = 1;

        numOfBlueNeigh[4][0] = 1;
        numOfBlueNeigh[4][1] = 1;
        numOfBlueNeigh[4][2] = 1;
        numOfBlueNeigh[4][3] = 1;
        numOfBlueNeigh[4][4] = 1;

        tm.setNumberOfWaterNeighbours(numOfBlueNeigh);

        assertEquals(true, tm.checkIfValidWaterPosition(0,2));

    }


    // test to check if valid water tile, first column, false
    @Test
    public void testIfInvalidWaterTileFirstColumn(){

        // set the map size
        tm.mapSizeSetter(5);

        // generate the map
        tm.setGameMap(tm.setMap());

        int[][] numOfBlueNeigh = new int[tm.getSize()][tm.getSize()];
        numOfBlueNeigh[0][0] = 1;
        numOfBlueNeigh[0][1] = 1;
        numOfBlueNeigh[0][2] = 1;
        numOfBlueNeigh[0][3] = 1;
        numOfBlueNeigh[0][4] = 1;

        numOfBlueNeigh[1][0] = 1;
        numOfBlueNeigh[1][1] = 1;
        numOfBlueNeigh[1][2] = 1;
        numOfBlueNeigh[1][3] = 1;
        numOfBlueNeigh[1][4] = 1;

        numOfBlueNeigh[2][0] = 1;
        numOfBlueNeigh[2][1] = 1;
        numOfBlueNeigh[2][2] = 1;
        numOfBlueNeigh[2][3] = 1;
        numOfBlueNeigh[2][4] = 1;

        numOfBlueNeigh[3][0] = 1;
        numOfBlueNeigh[3][1] = 1;
        numOfBlueNeigh[3][2] = 1;
        numOfBlueNeigh[3][3] = 1;
        numOfBlueNeigh[3][4] = 1;

        numOfBlueNeigh[4][0] = 1;
        numOfBlueNeigh[4][1] = 1;
        numOfBlueNeigh[4][2] = 1;
        numOfBlueNeigh[4][3] = 1;
        numOfBlueNeigh[4][4] = 1;

        tm.setNumberOfWaterNeighbours(numOfBlueNeigh);

        tm.setValueInWaterNeighbour(1,2,3);

        assertEquals(false, tm.checkIfValidWaterPosition(0,2));

    }




    // test to check if valid water tile, last column, valid
    @Test
    public void testIfValidWaterTileLastColumn(){

        // set the map size
        tm.mapSizeSetter(5);

        // generate the map
        tm.setGameMap(tm.setMap());

        int[][] numOfBlueNeigh = new int[tm.getSize()][tm.getSize()];
        numOfBlueNeigh[0][0] = 1;
        numOfBlueNeigh[0][1] = 1;
        numOfBlueNeigh[0][2] = 1;
        numOfBlueNeigh[0][3] = 1;
        numOfBlueNeigh[0][4] = 1;

        numOfBlueNeigh[1][0] = 1;
        numOfBlueNeigh[1][1] = 1;
        numOfBlueNeigh[1][2] = 1;
        numOfBlueNeigh[1][3] = 1;
        numOfBlueNeigh[1][4] = 1;

        numOfBlueNeigh[2][0] = 1;
        numOfBlueNeigh[2][1] = 1;
        numOfBlueNeigh[2][2] = 1;
        numOfBlueNeigh[2][3] = 1;
        numOfBlueNeigh[2][4] = 1;

        numOfBlueNeigh[3][0] = 1;
        numOfBlueNeigh[3][1] = 1;
        numOfBlueNeigh[3][2] = 1;
        numOfBlueNeigh[3][3] = 1;
        numOfBlueNeigh[3][4] = 1;

        numOfBlueNeigh[4][0] = 1;
        numOfBlueNeigh[4][1] = 1;
        numOfBlueNeigh[4][2] = 1;
        numOfBlueNeigh[4][3] = 1;
        numOfBlueNeigh[4][4] = 1;

        tm.setNumberOfWaterNeighbours(numOfBlueNeigh);

        assertEquals(true, tm.checkIfValidWaterPosition(4,2));

    }


    // test to check if valid water tile, last column, false
    @Test
    public void testIfInvalidWaterTileLastColumn(){

        // set the map size
        tm.mapSizeSetter(5);

        // generate the map
        tm.setGameMap(tm.setMap());

        int[][] numOfBlueNeigh = new int[tm.getSize()][tm.getSize()];
        numOfBlueNeigh[0][0] = 1;
        numOfBlueNeigh[0][1] = 1;
        numOfBlueNeigh[0][2] = 1;
        numOfBlueNeigh[0][3] = 1;
        numOfBlueNeigh[0][4] = 1;

        numOfBlueNeigh[1][0] = 1;
        numOfBlueNeigh[1][1] = 1;
        numOfBlueNeigh[1][2] = 1;
        numOfBlueNeigh[1][3] = 1;
        numOfBlueNeigh[1][4] = 1;

        numOfBlueNeigh[2][0] = 1;
        numOfBlueNeigh[2][1] = 1;
        numOfBlueNeigh[2][2] = 1;
        numOfBlueNeigh[2][3] = 1;
        numOfBlueNeigh[2][4] = 1;

        numOfBlueNeigh[3][0] = 1;
        numOfBlueNeigh[3][1] = 1;
        numOfBlueNeigh[3][2] = 1;
        numOfBlueNeigh[3][3] = 1;
        numOfBlueNeigh[3][4] = 1;

        numOfBlueNeigh[4][0] = 1;
        numOfBlueNeigh[4][1] = 1;
        numOfBlueNeigh[4][2] = 1;
        numOfBlueNeigh[4][3] = 1;
        numOfBlueNeigh[4][4] = 1;

        tm.setNumberOfWaterNeighbours(numOfBlueNeigh);

        tm.setValueInWaterNeighbour(3,2,3);

        assertEquals(false, tm.checkIfValidWaterPosition(4,2));

    }




    // set the maps to null
    @After
    public void teardown(){
        tm = null;
    }
}
