package inf112.skeleton.app;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RallyGame extends Game {

    public static int SCREEN_WIDTH = 700;
    public static int SCREEN_HEIGHT = 700;
    public static final float TILE_SIZE = 300;


    public SpriteBatch batch;
    private Board board;
    private Player player;



    @Override
    public void create() {
        batch = new SpriteBatch();
        this.setScreen(new MenuScreen(this));

    }

    /*public void updatePlayer() {
        board.getBoardLayers()
                .get("player")
                .setCell(player.getPos().getX(), player.getPos().getY(), player.setImage());
    }
*/

}
