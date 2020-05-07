package inf112.skeleton.app.Screens;

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
import inf112.skeleton.app.*;
import inf112.skeleton.app.Participants.GameActor;
import inf112.skeleton.app.Participants.Player;

public class GameScreen extends InputAdapter implements Screen {
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera;
    private Player player;
    private Board board;
    private SpriteBatch batch;
    private BitmapFont font = new BitmapFont();
    private Game gameloop;
    private boolean debugModeEnabled;

    public GameScreen(Board board) {
        batch = new SpriteBatch();
        this.board = board;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, board.getBoardWidth(), board.getBoardHeight() + 5);
        camera.update();
        mapRenderer = new OrthogonalTiledMapRenderer(board.getBoard(), (float) 1 / board.getTileSize());
        player = (Player)board.playerObjects.get(0);
        gameloop = board.gameLoop;
        debugModeEnabled = false;
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

        if(board.winner != null) board.gameLoop.game.setScreen(new WinnerAnnouncementScreen(board.gameLoop.game, board.winner));
        for (GameActor individual : board.playerObjects){
            board.playerLayer.setCell(individual.getXPosition(), individual.getYPosition(), individual.getPlayerCell());
        }
        batch.begin();

        font.setColor(Color.BLACK);
        font.draw(batch, player.createPlayerStatus(), 10, 690);
        font.draw(batch, "Go to flag: " + ((int)player.getNumberOfFlagsVisited()+1), 10, 670);
        for(int i = 1; i < board.playerObjects.size(); i++){
            GameActor ga = board.playerObjects.get(i);
            font.draw(batch, ga.createPlayerStatus(), 490, 700 - (15 * i));
        }

        int unselectedStartPositionX = 0;
        int selectedStartPositionX = 100;
        int cardWidth = 77;
        int cardHeight = 97;
        if (player.getRemainingLives() > 0) {
            for (int i = 0; i < player.hand.size(); i++) {//i<player.hand.size()
                MoveCard card = player.hand.get(i);//player.hand.get(i)
                if (player.positionOfSpecificMoveCardInProgramCard(card) == null) {
                    batch.draw(card.texture, unselectedStartPositionX, board.getBoardHeight() + 680, cardWidth, cardHeight);
                    font.draw(batch, String.valueOf(card.priorityValue), unselectedStartPositionX + 10, board.getBoardHeight() + 750);
                    unselectedStartPositionX += cardWidth;
                } else {
                    unselectedStartPositionX += cardWidth;
                }

            }
            for (int i = 0; i < player.programCard.length; i++) {
                MoveCard card = player.programCard[i];
                if (card != null) {
                    batch.draw(card.texture, selectedStartPositionX, 575, cardWidth, cardHeight);
                    font.draw(batch, String.valueOf(card.priorityValue), selectedStartPositionX + 10, 645);
                }

                selectedStartPositionX += cardWidth;
            }
        }
        if(debugModeEnabled) font.draw(batch, "Debug mode is enabled, press the right ctrl button to toggle off", 100, 600);
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
        if(board.playerObjects.get(0) instanceof Player && !((Player) board.playerObjects.get(0)).hasProgrammedRobot){

            switch (keyCode){
                case Input.Keys.CONTROL_RIGHT:
                    debugModeEnabled = !debugModeEnabled;
                    return true;

                case Input.Keys.UP:
                    if(debugModeEnabled){
                        direction.heading = Direction.NominalDirection.NORTH;
                        player.attemptToMoveInDirection(board,direction.heading);
                        board.updateBoard();
                        return true;
                    } else return false;


                case Input.Keys.DOWN:
                    if(debugModeEnabled) {
                        direction.heading = Direction.NominalDirection.SOUTH;
                        player.attemptToMoveInDirection(board, direction.heading);
                        board.updateBoard();
                        return true;
                    } else return false;

                case Input.Keys.LEFT:
                    if(debugModeEnabled){
                        direction.heading = Direction.NominalDirection.WEST;
                        player.attemptToMoveInDirection(board,direction.heading);
                        board.updateBoard();
                        return true;
                    } else return false;

                case Input.Keys.RIGHT:
                    if(debugModeEnabled){
                        direction.heading = Direction.NominalDirection.EAST;
                        player.attemptToMoveInDirection(board,direction.heading);
                        board.updateBoard();
                        return true;
                    } else return false;

                case Input.Keys.G:
                    if(debugModeEnabled){
                        player.rotateClockWise(1);
                        return true;
                    } else return false;


                case Input.Keys.H:
                    if(debugModeEnabled){
                        player.rotateClockWise(2);
                        return true;
                    } else return false;


                case Input.Keys.J:
                    if(debugModeEnabled){
                        player.rotateClockWise(3);
                        return true;
                    } else return false;

                case Input.Keys.R:
                    if(debugModeEnabled){
                        player.xPosition = (int)player.checkpoint.x;
                        player.yPosition = (int)player.checkpoint.y;
                        return true;
                    } else return false;


                case Input.Keys.U:
                    if(debugModeEnabled){
                        board.updateBoard();
                    } else return false;


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
                    //check math min for correct numbers
                    for (int i = 0; i < Math.min(5, (9-(9-player.getHealthPoints()))); i++) {
                        player.programCard[i] = null;
                    }
                    return true;

                case Input.Keys.ENTER:
                    for (int i = 0; i < player.programCard.length; i++) {
                        if (player.programCard[i] == null) {
                            return false;
                        }
                    }
                    player.hasProgrammedRobot = true;
                    board.gameLoop.gamePhases();
                    player.hasProgrammedRobot = false;
                    return true;

                case Input.Keys.P:
                    if(player.hasProgrammedRobot == false){
                        if(player.powerdownStatus == 0) player.powerdownStatus = 2;
                        else if(player.powerdownStatus == 2) player.powerdownStatus = 0;
                    }
                    return false;

                default:
                    return false;
            }
        }
        else{
            board.gameLoop.gamePhases();
        }
        return false;
    }


    public boolean cardInput(int number){
        //Make sure you cannot break the game selecting an index greater than the players hand size
        if(number > 9 - (9 - player.getHealthPoints())) return false;
        MoveCard card = player.hand.get(number-1);
        Integer positionOfCard = player.positionOfSpecificMoveCardInProgramCard(card);
        if(positionOfCard == null){
            for(int i = 0; i < player.programCard.length; i++){
                if(player.programCard[i] == null){
                    player.programCard[i] = card;
                    return true;
                }
            }
        }
        else{
            player.programCard[positionOfCard] = null;
            return false;
        }
        return false;
    }
}


