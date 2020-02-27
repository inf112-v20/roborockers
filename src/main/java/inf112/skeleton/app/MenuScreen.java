package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;



public class MenuScreen extends InputAdapter implements Screen {

    private static final int PLAY_BUTTON_WIDTH = 300;
    private static final int PLAY_BUTTON_HEIGHT = 150;
    private static final int EXIT_BUTTON_WIDTH = 200;
    private static final int EXIT_BUTTON_HEIGHT = 120;

    Texture playButtonActive;
    Texture playButtonInactive;
    Texture exitButtonActive;
    Texture exitButtonInactive;

    RoboGame game;
    Texture img;


    public MenuScreen(RoboGame game) {
        this.game = game;
        this.img = new Texture("background.png");
        playButtonActive = new Texture("playButton.png");
        playButtonInactive = new Texture("playIN.png");
        exitButtonActive  = new Texture("exit.png");
        exitButtonInactive = new Texture("exit.png");

    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);


    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);


        game.batch.begin();
        game.batch.draw(img, 200, 200);


        game.batch.draw(playButtonActive, 500 - PLAY_BUTTON_WIDTH / 2, 100, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        if(Gdx.input.isTouched()) {
            showGame();
            System.out.print("hei");

        }






        game.batch.draw(exitButtonActive, 200 - EXIT_BUTTON_WIDTH / 2, 100, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        if(Gdx.input.isTouched()) {
            Gdx.app.exit();
        }

        game.batch.end();



    }

    @Override
    public void resize(int i, int i1) {


    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public void showGame() {
        game.setScreen(new GameScree(game));
    }

}
