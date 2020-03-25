package inf112.skeleton.app;

import com.badlogic.gdx.math.Vector2;

public interface BoardObject {
    void updateBoard(Player player);
    Vector2 getPushingTo();
}
