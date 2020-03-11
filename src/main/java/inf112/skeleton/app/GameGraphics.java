package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

import java.util.ArrayList;


public class GameGraphics extends InputAdapter implements ApplicationListener {
    private ArrayList<Player> playerList;
    private SpriteBatch batch;
    private BitmapFont font;
    private TiledMap tiledMap;
    private TiledMapTileLayer boardLayer;
    private TiledMapTileLayer holeLayer;
    private TiledMapTileLayer flagLayer;
    private TiledMapTileLayer playerLayer;
    private TiledMapTileLayer startPosition;
    private TiledMapTileLayer converbelt;
    private TiledMapTileLayer wall;
    private TmxMapLoader mapLoader;
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera mapCamera;
    private Player player;
    

    @Override
    public void create() {
        //Show board
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);
        tiledMap = new TmxMapLoader().load("tiles.tmx");
        boardLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Board");
        holeLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Hole");
        flagLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Flag");
        playerLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Player");
        startPosition = (TiledMapTileLayer) tiledMap.getLayers().get("StartPosition");
        converbelt = (TiledMapTileLayer) tiledMap.getLayers().get("Converbelt");
        wall = (TiledMapTileLayer) tiledMap.getLayers().get("Wall");
        mapCamera = new OrthographicCamera();
        mapCamera.setToOrtho(false, 10, 13);
        mapCamera.update();
        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap, (float)1/300);
        mapRenderer.setView(mapCamera);

        player = new Player(2, 2, "Something", 3, 1, 1);
        playerList = new ArrayList<Player>();
        playerList.add(player);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        mapRenderer.render();
        playerLayer.setCell(player.xPosition, player.yPosition, player.playerCell);

        //batch.begin();
        //font.draw(batch, "Hello World", 200, 200);
        //batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
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


