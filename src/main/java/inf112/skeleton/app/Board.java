package inf112.skeleton.app;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Board {
    private int boardWidth;
    private int boardHeight;
    private int tileSize;

    public TiledMapTileLayer boardLayer;
    public TiledMapTileLayer holeLayer;
    public TiledMapTileLayer flagLayer;
    public TiledMapTileLayer playerLayer;
    public TiledMapTileLayer startPosition;
    public TiledMapTileLayer playerAdjusterLayer;
    public TiledMapTileLayer wallLayer;
    public Wall[][] wallObjects;
    public BoardObject[][] playerAdjuster;
    public ArrayList<Laser> lasers = new ArrayList<Laser>();
    public ArrayList<Player> playerObjects;
    private TiledMap board;
    private Map<String, TiledMapTileLayer> boardLayers;

    /**
     * Constructor for creating and setting up a playing board
     * @param boardName  filename of the board to be started up
     */
    public Board(String boardName) {
        tileSize = 300;
        board = new TmxMapLoader().load(boardName);
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
        playerAdjusterLayer = (TiledMapTileLayer) board.getLayers().get("PlayerAdjuster");
        wallLayer = (TiledMapTileLayer) board.getLayers().get("Wall");
        wallObjects = new Wall[boardWidth][boardHeight];
        playerAdjuster = new BoardObject[boardWidth][boardHeight];
        playerObjects = new ArrayList<Player>();

        //Fills the 2D arrays with the objects needed to implement the functionality required
        for (int x = 0; x < boardWidth; x++) {
            for (int y = 0; y < boardHeight; y++) {
                if (wallLayer.getCell(x, y) != null) {
                    TiledMapTile tile = wallLayer.getCell(x, y).getTile();
                    Wall w = new Wall(x, y, tile.getId());
                    wallObjects[x][y] = w;
                    Laser laser = new Laser(tile.getId(),x,y);
                    if(laser.getDamage() != 0) lasers.add(laser);
                }
                if(playerAdjusterLayer.getCell(x,y) != null){
                    TiledMapTile tile = playerAdjusterLayer.getCell(x,y).getTile();
                    BoardObject b = new Belt(x,y,tile.getId());
                    if(b.getPushingTo() == null){
                        b = new Rotator(tile.getId(), x, y);
                    }
                    playerAdjuster[x][y] = b;
                }
            }
        }
    }

    /**
     * Constructor for testing certain methods
     * @param width  testWidth
     * @param height testHeight
     */
    public Board(int width, int height) {
        boardWidth = width;
        boardHeight = height;
    }

    public Map<String, TiledMapTileLayer> getBoardLayers() { return boardLayers; }

    /**
     * Returns the width of the board
     * @return width
     */
    public int getBoardWidth() { return boardWidth; }

    /**
     * Returns the height of the board
     * @return height
     */
    public int getBoardHeight() { return boardHeight; }

    /**
     * Returns the board
     * @return board
     */
    public TiledMap getBoard() { return board; }

    /**
     * Returns the tileSize for the board
     * @return board
     */
    public int getTileSize(){return tileSize;}

    /**
     * Returns whether or not a an attempted move will result in stopping in a wall
     * @param x x value on board
     * @param y y value on board
     * @param dir Nominal direction player wants to move
     * @return true / false
     */
    public boolean willCollideWithWall(int x, int y, Direction.NominalDirection dir){
        Direction direction = new Direction(dir);
        Vector2 position = direction.getPositionInDirection(x,y,dir);
        Wall currentPosition = wallObjects[x][y];
        Wall nextPosition = null;
        if(!positionIsOutOfBounds(position)) nextPosition = wallObjects[(int)position.x][(int)position.y];
        if(nextPosition == null){
            if(currentPosition != null) return currentPosition.blocksMovementTowards(dir);
            else return false;
        }
        if(currentPosition != null){
            return currentPosition.blocksMovementTowards(dir) || nextPosition.blocksMovementTowards(direction.rotate180(dir));
        }
        else return nextPosition.blocksMovementTowards(direction.rotate180(dir));
        }

    /**
     * Returns whether or not a an attempted move will result in moving off of the map
     * @param x x value on board
     * @param y y value on board
     * @param dir Nominal direction player wants to move
     * @return true / false
     */
    public boolean willGoOutOfTheMap(int x, int y, Direction.NominalDirection dir){
        Direction direction = new Direction(dir);
        Vector2 position = direction.getPositionInDirection(x,y,dir);
        return positionIsOutOfBounds(position);
    }
    /**
     * Returns whether or not a an attempted move will result in going into a hole
     * @param x x value on board
     * @param y y value on board
     * @param dir Nominal direction player wants to move
     * @return true / false
     */
    public boolean willGoIntoHole(int x, int y, Direction.NominalDirection dir){
        Direction direction = new Direction(dir);
        Vector2 position = direction.getPositionInDirection(x,y,dir);
        if(holeLayer.getCell((int)position.x,(int)position.y) != null)return true;
        return false;
    }
    /**
     * Returns whether or not a an attempted move will result in colliding with a player
     * @param x x value on board
     * @param y y value on board
     * @param dir Nominal direction player wants to move
     * @return true / false
     */
    public boolean willCollideWithPlayer(int x, int y, Direction.NominalDirection dir){
        Direction direction = new Direction(dir);
        Vector2 position = direction.getPositionInDirection(x,y,dir);
        if(playerLayer.getCell((int)position.x,(int)position.y) != null) return true;
        return false;
    }
    /**
     * Returns whether or not a an attempted move will result in stopping in a wall
     * @param position Vector2 with x and y values for the coordinate we want to check for
     * @return true / false
     */
    public boolean positionIsOutOfBounds(Vector2 position){
        if(position.x < 0 || position.x >= boardWidth) return true;
        if(position.y < 0 || position.y >= boardHeight) return true;
        return false;
    }

    /**
     * Registers all players that are supposed to be struck by lasers before striking them in order
     * not to have a player die and be moved to checkpoint before all lasers have been fired
     */
    public void fireLasers(){
        Map<Vector2, Integer> positionsToHit = new HashMap<>();
        Map<Player, Integer> playersToHit = new HashMap<>();
        //Get all lasers on the board
        for (Laser laser: lasers) {
            positionsToHit.put(laser.laserHit(this), laser.getDamage());
        }
        //Get all lasers on players
        for (Player player: playerObjects) {
            Laser laser = player.getPlayerLaser();
            positionsToHit.put(laser.laserHit(this), laser.getDamage());
        }
        //Iterate through all lasersHits keeping track of how much damage each player is supposed to take
        for(Map.Entry<Vector2, Integer> positionToHit : positionsToHit.entrySet()){
            if(positionToHit != null){
                for (Player player: playerObjects) {
                    try{
                    if(positionToHit.getKey().x == player.xPosition && positionToHit.getKey().y == player.yPosition){
                        if(playersToHit.containsKey(player)){
                            playersToHit.put(player, playersToHit.get(player)+positionToHit.getValue());
                        }
                        else{
                            playersToHit.put(player, positionToHit.getValue());
                        }
                    }
                }
                    catch (NullPointerException e){}
                }

            }
        }
        //Lastly register the damage onto each player
        for(Map.Entry<Player, Integer> player : playersToHit.entrySet()) {
            player.getKey().takeDamage(player.getValue());
        }
    }

    /**
     * Updates the board objects that impact the players
     * rotates the players and moves them if they are on conveyor belts
     * then fires off lasers
     */
    public void updateBoard(){
        Map<Player, Vector2> newPlayerPositions = new HashMap<Player, Vector2>();
        try {
            Thread.sleep(200);
        }
        catch (InterruptedException e){}
        for (Player p : playerObjects) {
            if(playerAdjuster[p.xPosition][p.yPosition] != null){
                BoardObject boardObject = playerAdjuster[p.xPosition][p.yPosition];
                newPlayerPositions.put(p, boardObject.getPushingTo());
            }
        for (Map.Entry<Player, Vector2> newPlayerPosition : newPlayerPositions.entrySet()) {
            Player player = newPlayerPosition.getKey();
            Vector2 vector = newPlayerPosition.getValue();
            int i = 0;
            for (Vector2 vector2 : newPlayerPositions.values()){
                if(vector.equals(vector2)) i++;
            }
            if(i == 1){
                player.xPosition = (int)vector.x;
                player.yPosition = (int)vector.y;
            }
        }
        fireLasers();
        }
    }

}
