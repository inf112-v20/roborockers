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



public class GameGraphics extends InputAdapter implements ApplicationListener {
    private SpriteBatch batch;
    private BitmapFont font;
    private TiledMap tiledMap;
    private TiledMapTileLayer boardLayer;
    private TiledMapTileLayer holeLayer;
    private TiledMapTileLayer flagLayer;
    private TiledMapTileLayer playerLayer;
    private TmxMapLoader mapLoader;
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera mapCamera;


    private Vector2 playerPosition;
    private Cell playerCell;
    private Cell playerDeadCell;
    private Cell playerWon;
    

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
        mapCamera = new OrthographicCamera();
        mapCamera.setToOrtho(false, 5, 5);
        mapCamera.update();
        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap, (float)1/300);
        mapRenderer.setView(mapCamera);

        Texture texture = new Texture("player.png");
        TextureRegion[][] playerTxRegion = TextureRegion.split(texture, 300, 300);

        playerCell = new Cell();
        playerCell.setTile(new StaticTiledMapTile(playerTxRegion[0][0]));
        playerDeadCell = new Cell();
        playerDeadCell.setTile(new StaticTiledMapTile(playerTxRegion[0][1]));
        playerWon = new Cell();
        playerWon.setTile(new StaticTiledMapTile(playerTxRegion[0][2]));

        playerPosition = new Vector2(0, 0);
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
        playerLayer.setCell((int)playerPosition.x,(int)playerPosition.y, playerCell);



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
        playerLayer.setCell((int)playerPosition.x, (int)playerPosition.y, null);
        int x = (int)playerPosition.x;
        int y = (int)playerPosition.y;
        switch (keyCode){
            case Input.Keys.UP:
                if(y+1 <0 || y+1 >= 5){
                    return false;
                }
                else{
                    playerPosition.set(x, y+1); return true;
                }
            case Input.Keys.DOWN:
                if(y-1 < 0 || y-1 >= 5){
                    return false;
                }
                else{
                    playerPosition.set(x, y-1); return true;
                }
            case Input.Keys.LEFT:
                if(x-1 <0 || x-1 >= 5){
                    return false;
                }
                else{
                    playerPosition.set(x-1, y); return true;
                }
            case Input.Keys.RIGHT:
                if(x+1 <0 || x+1 >= 5){
                    return false;
                }
                else{
                    playerPosition.set(x+1, y); return true;
                }
            default:
                return false;
        }


    }

}


