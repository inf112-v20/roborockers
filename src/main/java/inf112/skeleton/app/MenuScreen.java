package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class MenuScreen extends ScreenAdapter {


    private Stage stage;
    private RallyGame game;


    public MenuScreen(final RallyGame game) {
        this.game = game;
        //this.board = board;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        Skin skin = new Skin(Gdx.files.internal(("Skin/skin/clean-crispy-ui.json")));


        Texture logoTexture = new Texture(Gdx.files.internal("bakgrunn.png"));
        Image logo = new Image(logoTexture);

        CheckBox checkBox = new CheckBox("", skin);

        Label mapSelectorLabel = new Label("Map: ", skin);
        mapSelectorLabel.setFontScale(1.3f);
        final SelectBox<String> mapSelectorBox = new SelectBox<>(skin);


        // "save" valget i en variabel elns
        String[] mapSelectorOptions = {"tiles.tmx","tiles3.tmx"};
        mapSelectorBox.setItems(mapSelectorOptions);

        Label playerNumberLabel = new Label("Number of players: ", skin);
        playerNumberLabel.setFontScale(1.3f);
        final SelectBox<Integer> playerNumberBox = new SelectBox<>(skin);

        Integer[] playerNumberOptions = {2,3,4,5,6,7,8};
        playerNumberBox.setItems(playerNumberOptions);



        TextButton playButton = new TextButton("Play", skin);
        playButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // Create a board with 2-8 players on the selected map
                int numberOfPlayers = playerNumberBox.getSelected();
                game.setScreen(new GameScreen(new Board(mapSelectorBox.getSelected(), numberOfPlayers)));
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
        table.add(logo).top().colspan(2).padTop(50).size(500, 250);
        table.row();
        table.add(playButton).prefHeight(70).prefWidth(200).colspan(2);
        table.row();
        table.add(mapSelectorLabel).right();
        table.add(mapSelectorBox).left();
        table.row();
        table.add(playerNumberLabel).right();
        table.add(playerNumberBox).left();
        table.row();
        table.add(exitButton).prefHeight(50).prefWidth(200).colspan(2).padTop(50);

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
