package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;


public class GameScreen extends InputAdapter implements Screen {
    RallyGame game;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera;
    private final int MAP_WIDTH = 10;
    private final int MAP_HEIGHT = 18;
    private final int TILE_WIDTH = 300;
    private TmxMapLoader mapLoader;
    private Player player;
    private TiledMapTileLayer boardLayer;
    private TiledMapTileLayer holeLayer;
    private TiledMapTileLayer flagLayer;
    private TiledMapTileLayer playerLayer;
    private TiledMapTileLayer startPosition;
    private TiledMapTileLayer conveyorBelt;
    private TiledMapTileLayer wall;

    public GameScreen() {
        //  this.game = game;
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("tiles.tmx");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, MAP_WIDTH, MAP_HEIGHT);
        camera.update();
        mapRenderer = new OrthogonalTiledMapRenderer(map, (float) 1 / TILE_WIDTH);
        player = new Player(2, 2, "Name", 3, 1, 4);
        boardLayer = (TiledMapTileLayer) map.getLayers().get("Board");
        holeLayer = (TiledMapTileLayer) map.getLayers().get("Hole");
        flagLayer = (TiledMapTileLayer) map.getLayers().get("Flag");
        playerLayer = (TiledMapTileLayer) map.getLayers().get("Player");
        startPosition = (TiledMapTileLayer) map.getLayers().get("StartPosition");
        conveyorBelt = (TiledMapTileLayer) map.getLayers().get("ConveyorBelt");
        wall = (TiledMapTileLayer) map.getLayers().get("Wall");
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
        camera.update();
        mapRenderer.render();
        playerLayer.setCell(player.xPosition, player.yPosition, player.playerCell);
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

    @Override
    public boolean keyUp(int keyCode){
        playerLayer.setCell(player.xPosition, player.yPosition, null);
        int x = player.xPosition;
        int y = player.yPosition;
        switch (keyCode){
            case Input.Keys.UP:
                if(y+1 <0 || y+1 >= 13){
                    return false;
                }
                else{
                    player.yPosition += 1;
                    return true;
                }
            case Input.Keys.DOWN:
                if(y-1 < 0 || y-1 >= 13){
                    return false;
                }
                else{
                    player.yPosition -= 1;
                    return true;
                }
            case Input.Keys.LEFT:
                if(x-1 <0 || x-1 >= 10){
                    return false;
                }
                else{
                    player.xPosition -= 1;
                    return true;
                }
            case Input.Keys.RIGHT:
                if(x+1 <0 || x+1 >= 10){
                    return false;
                }
                else{
                    player.xPosition += 1;
                    return true;
                }
            case Input.Keys.NUM_1:
                player.rotateClockWise(1);
                return true;
            case Input.Keys.NUM_2:
                player.rotateClockWise(2);
                return true;
            case Input.Keys.NUM_3:
                player.rotateClockWise(3);
                return true;

            default:
                return false;
        }
    }
}


