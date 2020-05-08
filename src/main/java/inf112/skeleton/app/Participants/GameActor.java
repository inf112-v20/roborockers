
package inf112.skeleton.app.Participants;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import inf112.skeleton.app.Board;
import inf112.skeleton.app.Laser;
import inf112.skeleton.app.Direction;
import inf112.skeleton.app.MoveCard;

import java.util.ArrayList;


abstract public class GameActor {
    public int xPosition;
    public int yPosition;
    public Direction heading;
    public Vector2 checkpoint;
    public int remainingLives;
    public int healthPoints = 9;
    public String name;
    public MoveCard[] programCard;
    public int powerdownStatus;
    public ArrayList<MoveCard> hand = new ArrayList<>();
    public Texture playerTexture;
    public Cell playerCell = new Cell();
    public TextureRegion[][] playerTxRegion;
    public int numberOfFlagsVisited = 0;
    public Laser playerLaser;
    public boolean hasProgrammedRobot = false;


    public int getXPosition(){
        return xPosition;
    }

    public int getYPosition(){
        return yPosition;
    }

    public void setXPosition(int x){
        xPosition = x;
    }

    public void setYPosition(int y){
        yPosition = y;
    }

    public int getRemainingLives(){
        return remainingLives;
    }


    public int getPowerDownStatus(){
        return powerdownStatus;
    }

    public int getHealthPoints(){
        return healthPoints;
    }

    public void healPlayer(int healAmount){
        healthPoints += healAmount;
        if(healthPoints > 9) healthPoints = 9;
    }

    public MoveCard[] getProgramCard(){
        return programCard;
    }

    public int getNumberOfFlagsVisited(){return numberOfFlagsVisited;}

    public void setNumberOfFlagsVisited(){numberOfFlagsVisited += 1;}

    public Cell getPlayerCell(){ return playerCell; }

    abstract public void startRound();

    public void doMove(Board board, int phaseNumber){
        if(healthPoints > 0){
            MoveCard nextMove = programCard[phaseNumber];
            if(nextMove.isRotator){
                rotateClockWise(nextMove.amountOfMoves);
            }
            else{
                if(nextMove.amountOfMoves > 0){
                    attemptToMoveForward(board, nextMove.amountOfMoves);
                }
                else attemptToMoveBackward(board, nextMove.amountOfMoves);
            }
        }

    }

    public void clearHand(){
        hand.clear();
    }



    abstract public void programRobot();

    public void announcePowerdown (){
        powerdownStatus = 2;
    }

    public void fullHeal(){
        healthPoints = 9;
    }

    abstract public void receiveCards(ArrayList<MoveCard> dealtCards);

    public void attemptToMoveForward(Board board, int steps){
        if(attemptToMoveInDirection(board, heading.heading) == false) return;
        //Recusively call to move as far as possible
        if(steps > 1) attemptToMoveForward(board, steps - 1);
    }

    public void attemptToMoveBackward(Board board, int steps){
        if(attemptToMoveInDirection(board, heading.rotate180(heading.heading)) == false) return;

        if(steps > 1){
            attemptToMoveBackward(board, steps - 1);
        }
    }

    public boolean attemptToMoveInDirection(Board board, Direction.NominalDirection nominalDirection){
        if(board.willCollideWithWall(xPosition,yPosition,nominalDirection)) return false;
        if(board.willGoIntoHole(xPosition,yPosition,nominalDirection) || board.willGoOutOfTheMap(xPosition,yPosition,nominalDirection)){
            loseALife();
            return false;
        }

        if(board.willCollideWithPlayer(xPosition,yPosition,nominalDirection)){
            Direction dir = new Direction(nominalDirection);
            Vector2 position = dir.getPositionInDirection(xPosition,yPosition,dir.heading);
            GameActor playerToPush = board.playerAtPosition(position);
            if(playerToPush.attemptToMoveInDirection(board, nominalDirection)){
                if(nominalDirection == Direction.NominalDirection.NORTH) setYPosition(getYPosition()+1);
                else if(nominalDirection == Direction.NominalDirection.EAST) setXPosition(getXPosition()+1);
                else if(nominalDirection == Direction.NominalDirection.SOUTH) setYPosition(getYPosition()-1);
                else setXPosition(getXPosition()-1);
            }
            else{return false;}
            //push player if possible, update yPosition and return true
            // otherwise return false
        }
        else{
            if(nominalDirection == Direction.NominalDirection.NORTH) yPosition += 1;
            else if(nominalDirection == Direction.NominalDirection.EAST) xPosition += 1;
            else if(nominalDirection == Direction.NominalDirection.SOUTH) yPosition -= 1;
            else xPosition -= 1;
        }
        return true;
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
            remainingLives = 0;
            playerCell.setTile(new StaticTiledMapTile(playerTxRegion[0][1]));
            xPosition = (int)checkpoint.x;
            yPosition = (int)checkpoint.y;
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

    public String getName(){
        return name;
    }

    /**
     * Updates the value of players laser to correspond with players current position and heading and returns laser
     */

    public Laser getPlayerLaser(){
        Vector2 laserStart = heading.getPositionInDirection(xPosition,yPosition,heading.heading);
        playerLaser.position.x = laserStart.x;
        playerLaser.position.y = laserStart.y;
        playerLaser.setDirection(heading);
        return playerLaser;
    }

    public void updateCheckpoint(){
        checkpoint.x = xPosition;
        checkpoint.y = yPosition;
    }

    public Integer positionOfSpecificMoveCardInProgramCard(MoveCard card){
        for (int i = 0; i < programCard.length; i++) {
            if(programCard[i] == card) return i;
        }
        return null;
    }
    public void gainALife(){
        remainingLives +=1;
    }

    public String createPlayerStatus(){
        String string = "";
        string += getName() + ", Lives: ";
        string += getRemainingLives() + ", HP: ";
        string += getHealthPoints() + ", PDS: ";
        string += powerdownStatus;
        return string;
    }
}