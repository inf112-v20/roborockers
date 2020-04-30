package inf112.skeleton.app;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RallyGame extends Game {

    public static int SCREEN_WIDTH = 700;
    public static int SCREEN_HEIGHT = 800;
    public static final float TILE_SIZE = 300;

    @Override
    public void create() {
        this.setScreen(new MenuScreen(this));
    }
}
