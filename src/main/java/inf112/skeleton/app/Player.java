
package inf112.skeleton.app;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

import java.util.ArrayList;

public class Player{
    public int xPosition;
    public int yPosition;
    public Direction heading;
    public Vector2 checkpoint;
    public int remainingLives;
    public int healthPoints = 9;
    public static  String name;
    public static MoveCard[] programCard;
    public ArrayList<MoveCard> selectableCards;
    public int powerdownStatus;
    public ArrayList<MoveCard> hand;
    public static Texture playerTexture;
    public Cell playerCell = new Cell();
    public TextureRegion[][] playerTxRegion;
    public boolean[] flagWinCondition;

    public Player(int x, int y, String name, int amountOfLives, int playerNumber, int nmrOfFlags){
        this.xPosition = x;
        this.yPosition = y;
        this.name = name;
        this.remainingLives = amountOfLives;
        this.checkpoint = new Vector2(x, y);
        this.powerdownStatus = 0;
        this.heading = new Direction();
        this.playerTexture = new Texture("player"+ playerNumber +".png");
        this.playerTxRegion = TextureRegion.split(playerTexture, 300, 300);
        this.playerCell.setTile(new StaticTiledMapTile(playerTxRegion[0][0]));
        this.flagWinCondition = new boolean[nmrOfFlags];
    }

    public void startRound(Game game){
        if(powerdownStatus == 1){
            powerdownStatus = 0;
            fullHeal();
            return;
        }
        if(powerdownStatus > 1) powerdownStatus -= 1;
        programRobot();
    }

    public void doMove(Game game, int phaseNumber){
        if(healthPoints > 0){
            MoveCard nextMove = programCard[phaseNumber];
            if(nextMove.isRotator){
                rotateClockWise(nextMove.amountOfMoves);
            }
            else{
                if(nextMove.amountOfMoves<0){
                    attemptToMoveForward(game, nextMove.amountOfMoves);
                }
                else attemptToMoveBackward(game, nextMove.amountOfMoves);
            }
        }

    }

    public void programRobot(){

    }

    public void announcePowerdown (){
        powerdownStatus = 2;
    }
    public void fullHeal(){
        healthPoints = 9;
    }
    public void receiveCards(ArrayList<MoveCard> dealtCards){
        hand.clear();
        for(int i = 0; i < 9 - (9-healthPoints); i++){
            hand.add(dealtCards.remove(dealtCards.size()-1));
        }
    }

    public void attemptToMoveForward(Game game, int steps){
        if(heading.heading == Direction.NominalDirection.NORTH){
            attemptToMoveNorth(game);
        }
        else if(heading.heading == Direction.NominalDirection.EAST){
            attemptToMoveEast(game);
        }
        else if(heading.heading == Direction.NominalDirection.SOUTH){
            attemptToMoveSouth(game);
        }
        else if(heading.heading == Direction.NominalDirection.WEST){
            attemptToMoveWest(game);
        }
        //Recusively call to move as far as possible
        if(steps > 1){
            attemptToMoveForward(game, steps - 1);
        }
    }

    public boolean attemptToMoveBackward(Game game, int steps){
        if(heading.heading == Direction.NominalDirection.NORTH){
            attemptToMoveSouth(game);
        }
        else if(heading.heading == Direction.NominalDirection.EAST){
            attemptToMoveWest(game);
        }
        else if(heading.heading == Direction.NominalDirection.SOUTH){
            attemptToMoveSouth(game);
        }
        else if(heading.heading == Direction.NominalDirection.WEST){
            attemptToMoveEast(game);
        }
        if(steps > 1){
            attemptToMoveBackward(game, steps - 1);
        }
    }

    public boolean attemptToMoveWest(Game game){

        int newX = xPosition - 1;
        //Has hole, has corresponding wall,
        //walls: 22, 15, 7, 45
        //if(position to west is valid)yPosition -= 1;
    }
    public boolean attemptToMoveNorth(Game game){
        //walls: 7, 31, 28, 36
        int newY = yPosition + 1;
    }
    public boolean attemptToMoveSouth(Game game){
        //walls: 23, 15, 30, 44
        int newY = yPosition - 1;
    }
    public boolean attemptToMoveEast(Game game){
        //walls: 31, 29, 23, 37
        int newX = xPosition + 1;
    }

    public void rotateClockWise(int numberOf90Degrees){
        if(numberOf90Degrees < 0 || numberOf90Degrees > 3){
            throw new IllegalArgumentException("This amount of clock wise rotations dont make sense game wise");
        }
        else{
            if(numberOf90Degrees == 1){
                heading.heading = heading.rotate90(heading.heading);
            }
            else if(numberOf90Degrees == 2){
                heading.heading = heading.rotate180(heading.heading);
            }
            else heading.heading = heading.rotate270(heading.heading);
        }
        playerCell.setRotation(4 - rotateHelper());
    }
    public int rotateHelper(){
        if(heading.heading == Direction.NominalDirection.NORTH){
            return 0;
        }
        else if(heading.heading == Direction.NominalDirection.EAST){
            return 1;
        }
        else if(heading.heading == Direction.NominalDirection.SOUTH){
            return 2;
        }
        else{
            return 3;
        }

    }

    public void takeDamage(int damage){
        if(healthPoints - damage <= 0){
            loseALife();
        }
        else{
            healthPoints = healthPoints - damage;
        }
    }

    public void loseALife(){
        if(remainingLives > 1){
            remainingLives -= 1;
            healthPoints = 9;
            xPosition = (int)checkpoint.x;
            yPosition = (int)checkpoint.y;
        }
        else{
            /*
            Notify server that this player has died and does not have any more lives
             */
            remainingLives = 0;
        }
    }
    public void updateTxRegion(){
        if(healthPoints == 0){
            playerCell.setTile(new StaticTiledMapTile(playerTxRegion[0][1]));
        }
        else if(healthPoints > 0 ){
            playerCell.setTile(new StaticTiledMapTile(playerTxRegion[0][0]));
        }
    }
}