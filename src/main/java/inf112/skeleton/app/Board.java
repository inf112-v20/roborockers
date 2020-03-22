package inf112.skeleton.app;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;
import java.util.Map;

public class Board {
    // Default pixels per tile

    private int boardWidth;
    private int boardHeight;
    private int tileSize;

    //public Player player;
    public TiledMapTileLayer boardLayer;
    public TiledMapTileLayer holeLayer;
    public TiledMapTileLayer flagLayer;
    public TiledMapTileLayer playerLayer;
    public TiledMapTileLayer startPosition;
    public TiledMapTileLayer conveyorBelt;
    public TiledMapTileLayer wall;
    public Wall[][] wallObjects;

    private TiledMap board;
    private Map<String, TiledMapTileLayer> boardLayers;

    public Board(String boardName) {
        tileSize = 300;
        board = new TmxMapLoader().load(boardName);
        //board = new TmxMapLoader().load("assets/Test_Board.tmx");
        boardLayers = new HashMap<>();

        // Stores all the layers in the input map/board
        for (int i = 0; i < board.getLayers().size(); i++) {
            boardLayers.put(board.getLayers().get(i).getName(), (TiledMapTileLayer) board.getLayers().get(i));
        }

        boardWidth = board.getProperties().get("width", Integer.class);
        boardHeight = board.getProperties().get("height", Integer.class);

        boardLayer = (TiledMapTileLayer) board.getLayers().get("Board");
        holeLayer = (TiledMapTileLayer) board.getLayers().get("Hole");
        flagLayer = (TiledMapTileLayer) board.getLayers().get("Flag");
        playerLayer = (TiledMapTileLayer) board.getLayers().get("Player");
        startPosition = (TiledMapTileLayer) board.getLayers().get("StartPosition");
        conveyorBelt = (TiledMapTileLayer) board.getLayers().get("ConveyorBelt");
        wall = (TiledMapTileLayer) board.getLayers().get("Wall");
        wallObjects = new Wall[boardWidth][boardHeight];
        for (int x = 0; x < boardWidth; x++) {
            for (int y = 0; y < boardHeight; y++) {
                if (wall.getCell(x, y) != null) {
                    TiledMapTile tile = wall.getCell(x, y).getTile();
                    Wall w = new Wall(x, y, tile.getId());
                    wallObjects[x][y] = w;
                }
            }
        }
    }

    /**
     * Constructor for testing certain methods
     *
     * @param width  testWidth
     * @param height testHeight
     */
    public Board(int width, int height) {
        boardWidth = width;
        boardHeight = height;
    }

    public Map<String, TiledMapTileLayer> getBoardLayers() {

        return boardLayers;
    }

    /**
     * Returns the width of the board
     *
     * @return width
     */
    public int getBoardWidth() {
        return boardWidth;
    }

    /**
     * Returns the height of the board
     *
     * @return height
     */
    public int getBoardHeight() {
        return boardHeight;
    }

    /**
     * Returns the board
     *
     * @return board
     */
    public TiledMap getBoard() {
        return board;
    }
    public int getTileSize(){return tileSize;}

    public boolean willCollideWithWall(int x, int y, Direction dir){
        Vector2 position = dir.getPositionInDirection(x,y,dir.heading);
        try {
            if (wallObjects[x][y].blocksMovementTowards(dir.heading) == true) {
                return true;
            } else if (wallObjects[(int) position.x][(int) position.y].blocksMovementTowards(dir.rotate180(dir.heading)) == true) {
                return true;
            }
        }
        catch (NullPointerException e){
            //There are no walls stored in any of the cells therefore we will not collide with anything
        }
        return false;
    }

    public boolean willGoOutOfTheMap(int x, int y, Direction dir){
        Vector2 position = dir.getPositionInDirection(x,y,dir.heading);
        if(position.x > boardWidth || position.x < 0) return true;
        else if(position.y > boardHeight || position.y < 0) return true;
        return false;
    }

    public boolean willGoIntoHole(int x, int y, Direction dir){
        Vector2 position = dir.getPositionInDirection(x,y,dir.heading);
        if(holeLayer.getCell((int)position.x,(int)position.y) != null)return true;
        return false;
    }
    public boolean willCollideWithPlayer(int x, int y, Direction dir){
        Vector2 position = dir.getPositionInDirection(x,y,dir.heading);
        if(playerLayer.getCell((int)position.x,(int)position.y) != null) return true;
        return false;
    }

}
