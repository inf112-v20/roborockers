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

import java.util.ArrayList;


public class GameScreen extends InputAdapter implements Screen {
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera;
    private Player player;
    private Player player2;
    private Board board;
    private SpriteBatch batch;
    private MoveCard card;
    private Deck deck;
    private BitmapFont font = new BitmapFont();
    MoveCard [] temp = new MoveCard[5];

    public GameScreen(Board board) {
        batch = new SpriteBatch();
        this.board = board;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, board.getBoardWidth(), board.getBoardHeight() + 5);
        camera.update();
        mapRenderer = new OrthogonalTiledMapRenderer(board.getBoard(), (float) 1 / board.getTileSize());
        player = new Player(1, 1, "Name", 3, 1, 4);
        player2 = new Player(2,4, "Player 2",3,2,4);
        board.playerObjects.add(player);
        board.playerObjects.add(player2);
        card = new MoveCard(20, 0, false);

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
        board.playerLayer.setCell(player.xPosition, player.yPosition, player.playerCell);
        board.playerLayer.setCell(player2.xPosition, player2.yPosition, player2.playerCell);
        batch.begin();
        int x = 0;
        int xVal = 154;
        int w = 77;
        int h = 97;

        for (int i = 0; i < 9; i++) {
            MoveCard card = deck.listOfMoveCards.get(i);
            if (!card.isSelected) batch.draw(card.texture,x, board.getBoardHeight()+680, w, h);
            x+=w;
        }

        for (int i = 0; i < 5; i++) {
            if(temp[i]!=null) batch.draw(temp[i].texture, xVal, 575, w, h);
            xVal+=w;
        }

        font.setColor(Color.BLACK);
        font.draw(batch, "Player 1:"+ player.healthPoints, 50, 600);
        font.draw(batch, "Player 2:"+player2.healthPoints, 600, 600);
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

            case Input.Keys.G:
                player.rotateClockWise(1);
                return true;
            case Input.Keys.H:

                player.rotateClockWise(2);
                return true;

            case Input.Keys.J:
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
            case Input.Keys.NUM_1:
                deck.listOfMoveCards.get(0).isSelected=false;

                System.out.println(deck.listOfMoveCards.get(0).isSelected);

                if (deck.listOfMoveCards.get(0).isSelected) return false;
                else if (temp[4] == null){
                    for (int i = 0; i < temp.length; i++) {
                        if(temp[i] == null){
                            temp[i] = deck.listOfMoveCards.get(0);
                            break;
                        }
                    }
                    deck.listOfMoveCards.get(0).toggleCard();
                    System.out.println(deck.listOfMoveCards.get(0).isSelected);
                }
                return true;


            case Input.Keys.NUM_2:
                return cardInput(2);

            case Input.Keys.NUM_3:
                return cardInput(3);

            case Input.Keys.NUM_4:
                return cardInput(4);

            case Input.Keys.NUM_5:
                return cardInput(5);

            case Input.Keys.NUM_6:
                return cardInput(6);

            case Input.Keys.NUM_7:
                return cardInput(7);

            case Input.Keys.NUM_8:
                return cardInput(8);

            case Input.Keys.NUM_9:
                return cardInput(9);

            case Input.Keys.BACKSPACE:
                for (int i = 0; i < temp.length; i++) {
                    if(temp[i] != null)temp[i].toggleCard();
                    temp[i] = null;
                }

            default:
                return false;
        }
    }


    public boolean cardInput(int number){
        number-=1;
        deck.listOfMoveCards.get(number).isSelected=false;

        System.out.println(deck.listOfMoveCards.get(number).isSelected);

        if (deck.listOfMoveCards.get(number).isSelected) return false;
        else if (temp[4] == null){
            for (int i = 0; i < temp.length; i++) {
                if(temp[i] == null){
                    temp[i] = deck.listOfMoveCards.get(number);
                    break;
                }
            }
            deck.listOfMoveCards.get(number).toggleCard();
            System.out.println(deck.listOfMoveCards.get(number).isSelected);
        }
        return true;
    }
}


