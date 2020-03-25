package inf112.skeleton.app;

import com.badlogic.gdx.math.Vector2;

public class Rotator implements BoardObject {
    private boolean isClockwiseRotator;
    private Vector2 position;

    public Rotator(int ID, int x, int y){
        position = new Vector2(x,y);
        if(ID == 54) this.isClockwiseRotator = true;
        else this.isClockwiseRotator = false;
    }

    public Direction.NominalDirection rotatePlayerToNewNominalDirection(Direction.NominalDirection direction){
        switch (direction){
            case NORTH:
                if(isClockwiseRotator) return Direction.NominalDirection.EAST;
                else return Direction.NominalDirection.WEST;
            case EAST:
                if(isClockwiseRotator) return Direction.NominalDirection.SOUTH;
                else return Direction.NominalDirection.NORTH;
            case SOUTH:
                if(isClockwiseRotator) return Direction.NominalDirection.WEST;
                else return Direction.NominalDirection.EAST;
            case WEST:
                if(isClockwiseRotator) return Direction.NominalDirection.NORTH;
                else return Direction.NominalDirection.SOUTH;
        }
        return null;
    }

    @Override
    public void updateBoard(Player player) {
        if(isClockwiseRotator) player.rotateClockWise(1);
        else player.rotateClockWise(3);
    }

    @Override
    public Vector2 getPushingTo() { return null;}


}
