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

public class GameScreen extends InputAdapter implements Screen {
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera;
    private Player player;
    private Board board;
    private SpriteBatch batch;
    //private Deck deck;
    private BitmapFont font = new BitmapFont();
    //MoveCard [] temp = new MoveCard[5];

    public GameScreen(Board board) {
        batch = new SpriteBatch();
        this.board = board;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, board.getBoardWidth(), board.getBoardHeight() + 5);
        camera.update();
        mapRenderer = new OrthogonalTiledMapRenderer(board.getBoard(), (float) 1 / board.getTileSize());
        player = (Player)board.playerObjects.get(0);
        //deck = new Deck(null);
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
        for (GameActor individual : board.playerObjects){
            board.playerLayer.setCell(individual.getXPosition(), individual.getYPosition(), individual.getPlayerCell());
        }
        batch.begin();
        int unselectedStartPositionX = 0;
        int selectedStartPositionX = 100;
        int cardWidth = 77;
        int cardHeight = 97;

        if(player.getRemainingLives() > 0){
            for (int i = 0; i < player.hand.size(); i++) {//i<player.hand.size()
                MoveCard card = player.hand.get(i);//player.hand.get(i)
                if (!card.isSelected) batch.draw(card.texture,unselectedStartPositionX, board.getBoardHeight()+680, cardWidth, cardHeight);
                unselectedStartPositionX+=cardWidth;
            }

            for (int i = 0; i < 5; i++) {
                if(player.programCard[i]!=null) batch.draw(player.programCard[i].texture, selectedStartPositionX, 575, cardWidth, cardHeight);
                selectedStartPositionX+=cardWidth;
            }
        }


        font.setColor(Color.BLACK);
        font.draw(batch, player.createPlayerStatus(), 10, 690);
        font.draw(batch, "Go to flag: " + ((int)player.getNumberOfFlagsVisited()+1), 10, 670);
        for(int i = 1; i < board.playerObjects.size(); i++){
            GameActor ga = board.playerObjects.get(i);
            font.draw(batch, ga.createPlayerStatus(), 490, 700 - (15 * i));
        }

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
        if(board.playerObjects.get(0) instanceof Player && !((Player) board.playerObjects.get(0)).hasProgrammedRobot){

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

                case Input.Keys.R:
                    player.xPosition = (int)player.checkpoint.x;
                    player.yPosition = (int)player.checkpoint.y;
                    return true;

                case Input.Keys.U:
                    board.updateBoard();

                case Input.Keys.NUM_1:
                    return cardInput(1);

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
                    for (int i = 0; i < player.programCard.length; i++) {
                        if(player.programCard[i] != null)player.programCard[i].toggleCard();
                        player.programCard[i] = null;
                    }

                case Input.Keys.ENTER:
                    if(player.programCard[4] == null){
                        //Did not input enough cards
                        for (int i = 0; i < player.programCard.length; i++) {
                            if(player.programCard[i] != null)player.programCard[i].toggleCard();
                            player.programCard[i] = null;
                        }

                        return false;
                    }
                    else{
                        player.hasProgrammedRobot = true;
                    //Start start up the game loop
                    }

                default:
                    return false;
            }
        }
        return false;
    }


    public boolean cardInput(int number){
        player.hand.get(number-1).isSelected=false;

        System.out.println("Selected card: " + player.hand.get(number-1).isSelected);

        if (player.hand.get(number).isSelected) return false;
        else if (player.programCard[4] == null){
            for (int i = 0; i < player.programCard.length; i++) {
                if(player.programCard[i] == null){
                    player.programCard[i] = player.hand.get(number-1);
                    break;
                }
            }
            player.hand.get(number-1).toggleCard();
            System.out.println(player.hand.get(number-1).isSelected);
        }
        return true;
    }
}

