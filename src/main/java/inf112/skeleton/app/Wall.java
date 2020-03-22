package inf112.skeleton.app;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

import java.util.ArrayList;
import java.util.Arrays;

public class Wall {
    private Cell wallCell;
    private Vector2 position;
    private boolean blockActionUp;
    private boolean blockActionRight;
    private boolean blockActionDown;
    private boolean blockActionLeft;
    private ArrayList<Integer> blockUp = new ArrayList<>(Arrays.asList(16, 24, 45, 31, 94));
    private ArrayList<Integer> blockRight = new ArrayList<>(Arrays.asList(8, 16, 23, 46, 95));
    private ArrayList<Integer> blockDown = new ArrayList<>(Arrays.asList(8, 29, 37, 32, 87));
    private ArrayList<Integer> blockLeft = new ArrayList<>(Arrays.asList(24, 30, 32, 38, 93));

    public Wall(int x, int y, int ID){
        this.wallCell = new Cell();
        this.position = new Vector2(x, y);
        if(blockUp.contains(ID)){
            blockActionUp = true;
        }
        if(blockRight.contains(ID)){
            blockActionRight = true;
        }
        if(blockDown.contains(ID)){
            blockActionDown = true;
        }
        if(blockLeft.contains(ID)){
            blockActionLeft = true;
        }
    }

    public boolean blocksMovementTowards(Direction.NominalDirection dir){
        if(dir == Direction.NominalDirection.NORTH){
            return blockActionUp;
        }
        else if(dir == Direction.NominalDirection.EAST){
            return blockActionRight;
        }

        else if(dir == Direction.NominalDirection.SOUTH){
            return blockActionDown;
        }
        else{
            return blockActionRight;
        }
    }

    public Vector2 getPosition(){ return position;}
    public Cell getCell(){ return wallCell;}
}


