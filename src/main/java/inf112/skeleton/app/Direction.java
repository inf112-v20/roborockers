package inf112.skeleton.app;

import com.badlogic.gdx.math.Vector2;

public class Direction{
    public NominalDirection heading;

    public Direction(){
        this.heading = NominalDirection.NORTH;
    }
    public Direction(NominalDirection nominalDirection){
        this.heading = nominalDirection;
    }

    public enum NominalDirection{
        NORTH, EAST, SOUTH, WEST
    }

    public NominalDirection rotate90(NominalDirection dir){
        if(dir == NominalDirection.NORTH) return NominalDirection.EAST;
        if(dir == NominalDirection.EAST) return NominalDirection.SOUTH;
        if(dir == NominalDirection.SOUTH) return NominalDirection.WEST;
        else return NominalDirection.NORTH;
    }

    public NominalDirection rotate180(NominalDirection dir){
        if(dir == NominalDirection.NORTH) return NominalDirection.SOUTH;
        if(dir == NominalDirection.EAST) return NominalDirection.WEST;
        if(dir == NominalDirection.SOUTH) return NominalDirection.NORTH;
        else return NominalDirection.EAST;
    }

    public NominalDirection rotate270(NominalDirection dir){
        if(dir == NominalDirection.NORTH) return NominalDirection.WEST;
        if(dir == NominalDirection.EAST) return NominalDirection.NORTH;
        if(dir == NominalDirection.SOUTH) return NominalDirection.EAST;
        else return NominalDirection.SOUTH;
    }
    public Vector2 getPositionInDirection(int x, int y, NominalDirection dir){
        Vector2 position = new Vector2(x,y);
        if(dir == NominalDirection.NORTH){ position.y += 1; }
        else if(dir == NominalDirection.EAST){ position.x += 1; }
        else if(dir == NominalDirection.SOUTH){ position.y -= 1; }
        else{ position.x -= 1;}
        return position;
    }
}