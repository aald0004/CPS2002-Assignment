package cps2002.game;

public class MapCreator {

    /* create a map
    * Parameters: type-> the type of map
    *             numPlayers-> the number of players participating in the game
    * Returns:    Map-> returns the required map*/
    public Map create(String type, int numPlayers){

        MapType mapType = MapType.valueOf(type.toUpperCase());

        Map map = null;


        switch(mapType){
            case SAFE:
                SafeMapCreator safeMapCreator = new SafeMapCreator();
                map = safeMapCreator.creator(numPlayers);
                break;
            case HAZARDOUS:
                HazardousMapCreator hazardousMapCreator = new HazardousMapCreator();
                map = hazardousMapCreator.creator(numPlayers);
                break;
            default:
                throw new IllegalArgumentException("unsupported map type");
        }

        return map;

    }
}
