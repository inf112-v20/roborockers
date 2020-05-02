package inf112.skeleton.app;

import com.badlogic.gdx.math.Vector2;

public class Mender implements BoardObject {
    public int repairValue;
    public int xPosition;
    public int yPosition;

    public Mender(int ID, int x, int y){
        if (ID == 7) repairValue = 2;
        else if(ID == 15) repairValue = 1;
        xPosition = x;
        yPosition = y;

    }

    @Override
    public void update(GameActor player) {
        player.healPlayer(repairValue);
    }

    @Override
    public Vector2 getPushingTo() {
        return null;
    }

    @Override
    public int getDistance() {
        return -1;
    }

    @Override
    public Direction.NominalDirection getDirection() {
        return null;
    }
}
