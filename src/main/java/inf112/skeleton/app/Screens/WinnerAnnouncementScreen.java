package inf112.skeleton.app.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.GameActor;
import inf112.skeleton.app.RallyGame;



public class WinnerAnnouncementScreen extends ScreenAdapter {

    private Stage stage;
    private RallyGame game;
    private Table table;


    public WinnerAnnouncementScreen(final RallyGame game, GameActor actor) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        Skin skin = new Skin(Gdx.files.internal(("Skin/skin/clean-crispy-ui.json")));



        Texture winnerTexture = new Texture(Gdx.files.internal("Winner.png"));
        Image winnerImage = new Image(winnerTexture);

        TextButton playerWinner = new TextButton("The winner is " + actor.getName(), skin);
       // Label playerWinner = new Label("The winner is " + actor, skin);

        Label credits = new Label("Game made by Johan, Anna, Peter, Vegard and Oystein", skin);


        TextButton playButton = new TextButton("Start new game", skin);
        playButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // Create a board with 2-8 players on the selected map
                game.setScreen(new MenuScreen(new RallyGame()));
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        // Exitbutton
        Button exitButton = new TextButton("Exit", skin);
        exitButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                System.exit(0);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        table.top();
        table.add(winnerImage).top().colspan(2).padTop(50).size(500, 250);
        table.row();
        table.add(playerWinner).size(200,50).colspan(2);
        table.row();
        table.add(exitButton).prefHeight(70).prefWidth(200).colspan(2).padTop(80);
        table.row();
        table.add(playButton).prefHeight(50).prefWidth(185).colspan(2).padTop(50);
        table.row();
        table.add(credits).colspan(2).padTop(100);
        table.setFillParent(true);
        table.setDebug(false);
        stage.addActor(table);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }
}
