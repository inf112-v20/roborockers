package inf112.skeleton.app;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RallyGame extends Game {

    public static int SCREEN_WIDTH = 700;
    public static int SCREEN_HEIGHT = 1000;
    public static final float TILE_SIZE = 300;


    public SpriteBatch batch;
    private Board board;

    @Override
    public void create() {
        batch = new SpriteBatch();
        board = new Board("tiles.tmx");
        this.setScreen(new MenuScreen(this, board));
    }
}

/*
fem kort som er programkortet
opptil 9 kort som er hånden
antall liv spilleren har igjen
antall livspoeng spilleren har
må gi en mulighet til å powerdowne
 */