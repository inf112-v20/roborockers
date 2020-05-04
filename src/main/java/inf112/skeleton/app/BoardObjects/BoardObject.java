package inf112.skeleton.app.BoardObjects;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Direction;
import inf112.skeleton.app.GameActor;

public interface BoardObject {
    void update(GameActor player);
    Vector2 getPushingTo();
    int getDistance();
    Direction.NominalDirection getDirection();
}
