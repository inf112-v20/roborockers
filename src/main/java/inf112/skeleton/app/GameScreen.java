package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class GameScreen extends InputAdapter implements Screen {
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera;
    private Player player;
    private Player player2;
    private Board board;
    private SpriteBatch batch;
    private Deck deck;
    private BitmapFont font = new BitmapFont();


    public GameScreen(Board board) {
        batch = new SpriteBatch();
        this.board = board;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, board.getBoardWidth(), board.getBoardHeight() + 5);
        camera.update();
        mapRenderer = new OrthogonalTiledMapRenderer(board.getBoard(), (float) 1 / board.getTileSize());
        player = new Player(1, 2, "Name", 3, 1, 4);
        player2 = new Player(2,4, "Player 2",3,2,4);
        board.playerObjects.add(player);
        board.playerObjects.add(player2);

        deck = new Deck(null);
        deck.listOfMoveCards.get(0);
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
        for (GameActor player: board.playerObjects) {
            board.playerLayer.setCell(player.getXPosition(),player.getYPosition(), player.getPlayerCell());
        }
        batch.begin();
        int x = 0;
        int xVal = 154;
        int w = 77;
        int h = 97;

        for (int i = 0; i < 9; i++) {
            //cardsInStock[i] = deck.listOfMoveCards.get(i);
            batch.draw(deck.listOfMoveCards.get(i).texture,x, board.getBoardHeight()+680, w, h);
            x+=w;
        }

        for (int i = 0; i < 5; i++) {
            //cardsInStock[i] = deck.listOfMoveCards.get(i);
            batch.draw(deck.listOfMoveCards.get(i).texture,xVal, 575, w, h);
            xVal+=w;
        }
        int liv = 8;
        font.setColor(Color.BLACK);
        font.draw(batch, "Player 1: "+ player.healthPoints, 50, 600);
        font.draw(batch, "Player 2: "+ player2.healthPoints, 600, 600);
        batch.end();
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
        System.out.println(board.getBoardWidth());
        Direction direction = new Direction();
        System.out.println(player.healthPoints);

        board.playerLayer.setCell(player.xPosition, player.yPosition, null);
        board.playerLayer.setCell(player2.xPosition, player2.yPosition, null);

        switch (keyCode){
            case Input.Keys.UP:
                direction.heading = Direction.NominalDirection.NORTH;
                player.attemptToMoveInDirection(board,direction.heading);
                board.updateBoard();
                return true;

            case Input.Keys.DOWN:
                direction.heading = Direction.NominalDirection.SOUTH;
                player.attemptToMoveInDirection(board,direction.heading);
                board.updateBoard();
                return true;

            case Input.Keys.LEFT:
                direction.heading = Direction.NominalDirection.WEST;
                player.attemptToMoveInDirection(board,direction.heading);
                board.updateBoard();
                return true;

            case Input.Keys.RIGHT:
                direction.heading = Direction.NominalDirection.EAST;
                player.attemptToMoveInDirection(board,direction.heading);
                board.updateBoard();
                return true;

            case Input.Keys.NUM_1:
                player.rotateClockWise(1);
                return true;
            case Input.Keys.NUM_2:

                player.rotateClockWise(2);
                return true;

            case Input.Keys.NUM_3:
                player.rotateClockWise(3);
                return true;

            case Input.Keys.I:
                System.out.println(player2.getName());

            case Input.Keys.R:
                player.xPosition = (int)player.checkpoint.x;
                player.yPosition = (int)player.checkpoint.y;
                return true;

            case Input.Keys.U:
                for (GameActor p : board.playerObjects) {
                    if(board.playerAdjuster[p.getXPosition()][p.getYPosition()] != null){
                        BoardObject boardObject = board.playerAdjuster[p.getXPosition()][p.getYPosition()];
                        boardObject.update(p);
                    }
                    board.fireLasers();
                }

            default:
                return false;
        }
    }
}


