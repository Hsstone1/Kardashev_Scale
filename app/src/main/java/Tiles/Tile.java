package Tiles;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

//Source code from Software Architecture & Design Youtube

//immutable abstract class for tiles
public abstract class Tile {

    //field can only be set once at construction time
    protected final int tileCoord;
    private static final Map<Integer, EmptyTile> EMPTY_TILE_CACHE = createAllGameTiles();

    //creates a grid of empty tiles and puts them in order to a map
    //Key is 0 - 99 with each one responding to a tile
    private static Map<Integer, EmptyTile> createAllGameTiles() {
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();

        for (int i = 0; i < 100; i++) {
            emptyTileMap.put(i, new EmptyTile(i));
        }

        //returning emptyTileMap that cannot be edited, only a list of tile information
        return Collections.unmodifiableMap(emptyTileMap);
    }

    //This is the only method to create a tile
    //If the tile has info, then create an occupied tile, else get the tile coordinate
    public static Tile createTile(final int tileCoord, final TileInfo tileInfo) {
        return tileInfo != null ? new OccupiedTile(tileCoord, tileInfo) : EMPTY_TILE_CACHE.get(tileCoord);
    }

    private Tile(int tileCoord) {
        this.tileCoord = tileCoord;
    }

    public abstract boolean isTileOccupied();

    public abstract TileInfo getTileInfo();


    public static final class EmptyTile extends Tile {
        private EmptyTile(final int tileCoord) {
            super(tileCoord);
        }

        @Override
        public boolean isTileOccupied() {
            return false;
        }

        @Override
        public TileInfo getTileInfo() {
            return null;
            //Add tile resources here
            //Pregenerate all tile resources from the start with the ones closest to player having less resources
        }
    }

    public static final class OccupiedTile extends Tile {

        private final TileInfo tileInfo;

        private OccupiedTile(int tileCoord, TileInfo tileInfo) {
            super(tileCoord);
            this.tileInfo = tileInfo;
        }

        @Override
        public boolean isTileOccupied() {
            return true;
        }

        @Override
        public TileInfo getTileInfo() {
            return this.tileInfo;
            //Add tile resources here
            //Pregenerate all tile resources from the start with the ones closest to player having less resources
        }


    }


}
