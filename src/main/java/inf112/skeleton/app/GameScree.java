package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScree extends InputAdapter implements Screen {
    RoboGame game;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera;
    private Viewport gridPort;
    private final int MAP_WIDTH = 12;
    private final int TILE_WIDTH = 300;
    private final int MAP_WITDTH = MAP_WIDTH * TILE_WIDTH;
    private TmxMapLoader mapLoader;

    public GameScree(RoboGame game) {
        this.game = game;
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("tiles.tmx");
        camera = new OrthographicCamera();
        gridPort = new StretchViewport(MAP_WITDTH, MAP_WITDTH, camera);
        camera.setToOrtho(false, MAP_WIDTH, MAP_WIDTH);
        camera.update();
        mapRenderer = new OrthogonalTiledMapRenderer(map, (float) 1 / TILE_WIDTH);


    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mapRenderer.setView(camera);
        update();
        mapRenderer.render();
    }

    public void update() {
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

    //TODO understand and implement dispose
    @Override
    public void dispose() {

    }
}






