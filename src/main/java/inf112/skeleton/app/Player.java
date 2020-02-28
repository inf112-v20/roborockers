
package inf112.skeleton.app;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Player{

    public static Texture playerTexture;
    public int xPosition;
    public int yPosition;
    public Direction heading;
    public Vector2 checkpoint;
    public int remainingLives;
    public int healthPoints = 9;
    public static  String name;
    public static MoveCard[] programCard;
    public static ArrayList<MoveCard> selectableCards;
    public static int powerdownStatus;
    public ArrayList<MoveCard> hand;

    public Player(int x, int y, String name, int amountOfLives, int playerNumber){
        this.xPosition = x;
        this.yPosition = y;
        this.name = name;
        this.remainingLives = amountOfLives;
        this.checkpoint = new Vector2(x, y);
        this.powerdownStatus = 0;
        this.heading = new Direction();
        this.playerTexture = new Texture("player"+ playerNumber +".png");
    }
    /*public void startOfRound(Game game){
        functionality that starts round and prompts the user to program their robot
    }
     */
    public void announcePowerdown (){
        powerdownStatus = 3;
    }
    public void fullHeal(){
        healthPoints = 9;
    }
    public void receiveCards(ArrayList<MoveCard> dealtCards){
        hand = dealtCards;
    }
/*
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
        if(position to the west is valid)yPosition -= 1;
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
*/

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
}