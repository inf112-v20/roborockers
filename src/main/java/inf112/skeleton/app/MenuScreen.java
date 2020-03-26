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
    private Board board;


    public MenuScreen(RallyGame game, Board board) {
        this.game = game;
        this.board = board;
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
        // Unproject kordinatene, de funker ikke som normalt, men ved å lage en vector så blir det enklere å justere 
        Vector3 vec = new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
        camera.unproject(vec);

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(img,0, 0,game.SCREEN_WIDTH , game.SCREEN_HEIGHT);

        // -150 og -400 er for å justere knappen til høyden
        // hvordan få knappen til å justere seg ved forskjellige høyder?
        int PLAYBUTTONx = RallyGame.SCREEN_WIDTH / 2 - PLAY_BUTTON_WIDTH / 2;
        int PLAYBUTTONy = RallyGame.SCREEN_HEIGHT / 2 - PLAY_BUTTON_HEIGHT / 2 - 150;
        int EXITBUTTONx = (RallyGame.SCREEN_WIDTH / 2  - EXIT_BUTTON_WIDTH / 2);
        int EXITBUTTONy = RallyGame.SCREEN_HEIGHT / 2 - EXIT_BUTTON_HEIGHT / 2 - 400;

        if(vec.x < PLAYBUTTONx + PLAY_BUTTON_WIDTH && vec.x > PLAYBUTTONx && vec.y < PLAYBUTTONy + PLAY_BUTTON_HEIGHT && vec.y > PLAYBUTTONy) {
            game.batch.draw(playButtonActive, PLAYBUTTONx, PLAY_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
            if(Gdx.input.isTouched()) {
                dispose();
                game.setScreen(new GameScreen(board));
            }
        } else {
            game.batch.draw(playButtonInactive, PLAYBUTTONx, PLAY_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        }

        if(vec.x < EXITBUTTONx + EXIT_BUTTON_WIDTH  && vec.x > EXITBUTTONx && vec.y < EXITBUTTONy + EXIT_BUTTON_HEIGHT && vec.y > EXITBUTTONy) {
            game.batch.draw(exitButtonActive, EXITBUTTONx , 50, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
            if(Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
        } else {
            game.batch.draw(exitButtonInactive, EXITBUTTONx, 50, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
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

