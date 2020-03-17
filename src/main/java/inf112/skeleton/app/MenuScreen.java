package inf112.skeleton.app;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.Vector3;


public class MenuScreen extends ScreenAdapter  {

    private static final int PLAY_BUTTON_WIDTH = 300;
    private static final int PLAY_BUTTON_HEIGHT = 150;
    private static final int EXIT_BUTTON_WIDTH = 200;
    private static final int EXIT_BUTTON_HEIGHT = 100;
    private static final int PLAY_Y = 200;

    private Texture playButtonActive;
    private Texture playButtonInactive;
    private Texture exitButtonActive;
    private Texture exitButtonInactive;
    private OrthographicCamera camera;


    private RallyGame game;
    private Texture img;


    public MenuScreen(RallyGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, RallyGame.SCREEN_WIDTH,RallyGame.SCREEN_HEIGHT);
        this.img = new Texture("BAKGRUNN.png");
        playButtonActive = new Texture("PlaybuttonActive.png");
        playButtonInactive = new Texture("PlayButtonIn.png");
        exitButtonActive  = new Texture("ExitButtonAct.png");
        exitButtonInactive = new Texture("ExitIna.png");

    }


    @Override
    public void render(float v) {

        // Unproject the coordinates. Because the input.getY does not work as normal
        Vector3 vec=new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
        camera.unproject(vec);

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(img,0, 0,game.SCREEN_WIDTH , game.SCREEN_HEIGHT);

        int x = RallyGame.SCREEN_WIDTH / 2 - PLAY_BUTTON_WIDTH / 2;

        // tallene for å justere hvor den skal kutte X aksen og Y aksen
        if(vec.x < (RallyGame.SCREEN_WIDTH / 2) - (PLAY_BUTTON_WIDTH / 2) + PLAY_BUTTON_WIDTH && vec.x > (RallyGame.SCREEN_WIDTH / 2) - (PLAY_BUTTON_WIDTH / 2) && vec.y < (RallyGame.SCREEN_HEIGHT / 2) - (PLAY_BUTTON_HEIGHT/ 2) + PLAY_BUTTON_HEIGHT && vec.y > (RallyGame.SCREEN_HEIGHT / 2) - (PLAY_BUTTON_HEIGHT) ) {
            game.batch.draw(playButtonActive, x, PLAY_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
            if(Gdx.input.isTouched()) {
                dispose();
                game.setScreen(new GameScree());
            }
        } else {
            game.batch.draw(playButtonInactive, x, PLAY_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        }
        // tallene for å justere hvor den skal kutte X aksen og Y aksen
        if(vec.x < (RallyGame.SCREEN_WIDTH / 2) - (EXIT_BUTTON_WIDTH / 2) + EXIT_BUTTON_WIDTH  && vec.x > (RallyGame.SCREEN_WIDTH / 2) - (EXIT_BUTTON_WIDTH / 2) && vec.y < (RallyGame.SCREEN_HEIGHT / 2) - (EXIT_BUTTON_HEIGHT / 2) + EXIT_BUTTON_HEIGHT - 250 && vec.y > (RallyGame.SCREEN_HEIGHT / 2) - (EXIT_BUTTON_HEIGHT) - 250) {
            game.batch.draw(exitButtonActive, x + 50, 50, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
            if(Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
        } else {
            game.batch.draw(exitButtonInactive, x + 50, 50, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        }

        game.batch.end();


    }

    @Override
    public void dispose() {
        img.dispose();
        playButtonActive.dispose();
        // lukke skjermen, eller siden

    }


}

