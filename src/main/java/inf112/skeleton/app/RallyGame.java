package inf112.skeleton.app;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RallyGame extends Game {

    SpriteBatch batch;



    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new MenuScreen(this));

    }

    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
    }



}
