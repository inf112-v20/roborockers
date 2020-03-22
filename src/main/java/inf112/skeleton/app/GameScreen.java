package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;


public class GameScreen extends InputAdapter implements Screen {
    RallyGame game;
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera;
    private Player player;
    private Board board;


    public GameScreen(Board board) {
        //  this.game = game;
        this.board = board;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, board.getBoardWidth(), board.getBoardHeight() + 5);
        camera.update();
        mapRenderer = new OrthogonalTiledMapRenderer(board.getBoard(), (float) 1 / board.getTileSize());
        player = new Player(5, 3, "Name", 3, 1, 4);

        System.out.println(board.getBoardHeight());

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
        board.playerLayer.setCell(player.xPosition, player.yPosition, player.playerCell);
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
        Direction direction = new Direction();
        board.wallObjects[8][0].blocksMovementTowards(Direction.NominalDirection.EAST);
        board.playerLayer.setCell(player.xPosition, player.yPosition, null);
        int x = player.xPosition;
        int y = player.yPosition;
        switch (keyCode){
            case Input.Keys.UP:
                direction.heading = Direction.NominalDirection.NORTH;
                if(board.willGoOutOfTheMap(x,y,direction) == true) return false;
                if(board.willCollideWithWall(x,y,direction) == true) return false;
                if(board.willGoIntoHole(x,y,direction) == true){
                    player.loseALife();
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


