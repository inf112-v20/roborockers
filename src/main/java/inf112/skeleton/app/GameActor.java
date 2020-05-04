package inf112.skeleton.app;



import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

import java.util.ArrayList;

public interface GameActor {
    void startRound();
    void doMove(Board board, int phaseNumber);
    void programRobot();
    void announcePowerdown();
    void fullHeal();
    void receiveCards(ArrayList<MoveCard> dealtCards);
    void attemptToMoveForward(Board board, int steps);
    void attemptToMoveBackward(Board board, int steps);
    boolean attemptToMoveInDirection(Board board, Direction.NominalDirection direction);
    void rotateClockWise(int numberOf90Degrees);
    int rotateHelper();
    void takeDamage(int amount);
    void loseALife();
    void updateTxRegion();
    String getName();
    Laser getPlayerLaser();
    void updateCheckpoint();
    int getXPosition();
    int getYPosition();
    void setXPosition(int x);
    void setYPosition(int y);
    int getRemainingLives();
    int getHealthPoints();
    int getPowerDownStatus();
    MoveCard[] getProgramCard();
    int getNumberOfFlagsVisited();
    void setNumberOfFlagsVisited();
    Cell getPlayerCell();
    String createPlayerStatus();
    void healPlayer(int healAmount);
}
