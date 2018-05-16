package cps2002.game;

public class MapCreator {

    /* create a map
    * Parameters: type-> the type of map
    *             numPlayers-> the number of players participating in the game
    * Returns:    Map-> returns the required map*/
    public Map create(String type, int numPlayers){

        MapType mapType = MapType.valueOf(type);

        Map map = null;

        /*switch(mapType){
            case Safe:
                map = SafeMap.getInstance(numPlayers);
                break;
            case Hazardous:
                map =  HazardousMap.getInstance(numPlayers);
                break;
            default:
                throw new IllegalArgumentException("unsupported map type");
        }*/

        switch(mapType){
            case Safe:
                SafeMapCreator safeMapCreator = new SafeMapCreator();
                map = safeMapCreator.creator(numPlayers);
                break;
            case Hazardous:
                HazardousMapCreator hazardousMapCreator = new HazardousMapCreator();
                map = hazardousMapCreator.creator(numPlayers);
                break;
            default:
                throw new IllegalArgumentException("unsupported map type");
        }

        return map;

        /*if(type.equals("Safe")){
            return SafeMap.getInstance(numPlayers);
        } else if(type.equals("Hazardous")){
            return HazardousMap.getInstance(numPlayers);
        } else {
            return null;
        }*/
    }
}
