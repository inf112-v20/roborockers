package inf112.skeleton.app;
public class Direction{
    public NominalDirection heading;

    public Direction(){
        this.heading = NominalDirection.NORTH;
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

}