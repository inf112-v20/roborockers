package inf112.skeleton.app;

import com.badlogic.gdx.math.Vector2;

public interface BoardObject {
    void update(GameActor player);
    Vector2 getPushingTo();
    int getDistance();
    Direction.NominalDirection getDirection();
}
