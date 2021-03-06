package inf112.skeleton.app.BoardObjects;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Direction;
import inf112.skeleton.app.Participants.GameActor;

public class Rotator implements BoardObject {
    private boolean isClockwiseRotator;
    private Vector2 position;
    private int distance;

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
    public void update(GameActor player) {
        if(isClockwiseRotator) player.rotateClockWise(1);
        else player.rotateClockWise(3);
    }

    @Override
    public Vector2 getPushingTo() { return null;}

    @Override
    public int getDistance() { return 0;}

    @Override
    public Direction.NominalDirection getDirection() {
        return null;
    }
}
