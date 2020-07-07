package Tiles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TileInfo {

    //protected final int tilePosition;

    private static final Map<String, Double> CREATE_TILE_RESOURCE = createAllTileResource();
    private static final String[] resourceList = new String[] {"wood", ""};

    //creates a grid of empty tiles and puts them in order to a map
    //Key is 0 - 99 with each one responding to a tile
    private static Map<String, Double> createAllTileResource() {
        final Map<String, Double> tileResourceMap = new HashMap<>();

        for (int i = 0; i < 100; i++) {

        }

        //returning emptyTileMap that cannot be edited, only a list of tile information
        return Collections.unmodifiableMap(tileResourceMap);
    }



}
